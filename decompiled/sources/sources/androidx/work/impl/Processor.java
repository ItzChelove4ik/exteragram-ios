package androidx.work.impl;

import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import androidx.core.content.ContextCompat;
import androidx.work.Configuration;
import androidx.work.ForegroundInfo;
import androidx.work.Logger;
import androidx.work.WorkerParameters;
import androidx.work.impl.WorkerWrapper;
import androidx.work.impl.foreground.ForegroundProcessor;
import androidx.work.impl.foreground.SystemForegroundDispatcher;
import androidx.work.impl.model.WorkGenerationalId;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.utils.WakeLocks;
import androidx.work.impl.utils.taskexecutor.TaskExecutor;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes.dex */
public class Processor implements ForegroundProcessor {
    private static final String TAG = null;
    private Context mAppContext;
    private Set<String> mCancelledIds;
    private Configuration mConfiguration;
    private Map<String, WorkerWrapper> mEnqueuedWorkMap;
    private PowerManager.WakeLock mForegroundLock;
    private Map<String, WorkerWrapper> mForegroundWorkMap;
    private final Object mLock;
    private final List<ExecutionListener> mOuterListeners;
    private WorkDatabase mWorkDatabase;
    private Map<String, Set<StartStopToken>> mWorkRuns;
    private TaskExecutor mWorkTaskExecutor;

    public static /* synthetic */ WorkSpec $r8$lambda$BssWcu09GTCZw0rdzuoUVCHQ0NA(Processor r1, ArrayList r2, String r3) {
        r2.addAll(r1.mWorkDatabase.workTagDao().getTagsForWorkSpecId(r3));
        return r1.mWorkDatabase.workSpecDao().getWorkSpec(r3);
    }

    /* renamed from: $r8$lambda$DL8iM-B7FHX_9lduQzH0vxRBBwQ, reason: not valid java name */
    public static /* synthetic */ void m973$r8$lambda$DL8iMB7FHX_9lduQzH0vxRBBwQ(Processor r2, WorkGenerationalId r3, boolean r4) {
        Object r0 = r2.mLock;
        monitor-enter(r0);
        Iterator<ExecutionListener> r22 = r2.mOuterListeners.iterator();     // Catch: Throwable -> L8
    L6:
        if (r22.hasNext() == false) goto L10;
        r22.next().onExecuted(r3, r4);     // Catch: Throwable -> L8
        goto L6
    L10:
        monitor-exit(r0);     // Catch: Throwable -> L8
        return;
    L8:
        th = move-exception;
        throw th;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void $r8$lambda$muVKxZSPLZrj3l8zDW2pghfHiMM(Processor r0, ListenableFuture r1, WorkerWrapper r2) {
        r0.getClass();
        boolean r12 = ((Boolean) r1.get()).booleanValue();     // Catch: Throwable -> L5
    L6:
        r0.onExecuted(r2, r12);
        return;
    L5:
        r12 = true;
        goto L6
    }

    static {
        TAG = Logger.tagWithPrefix("Processor");
    }

    public Processor(Context r1, Configuration r2, TaskExecutor r3, WorkDatabase r4) {
        this.mAppContext = r1;
        this.mConfiguration = r2;
        this.mWorkTaskExecutor = r3;
        this.mWorkDatabase = r4;
        this.mEnqueuedWorkMap = new HashMap();
        this.mForegroundWorkMap = new HashMap();
        this.mCancelledIds = new HashSet();
        this.mOuterListeners = new ArrayList();
        this.mForegroundLock = null;
        this.mLock = new Object();
        this.mWorkRuns = new HashMap();
    }

    private WorkerWrapper cleanUpWorkerUnsafe(String r4) {
        WorkerWrapper r0 = this.mForegroundWorkMap.remove(r4);
        if (r0 == null) goto L5;
        boolean r1 = true;
    L6:
        if (r1 == true) goto L8;
        r0 = this.mEnqueuedWorkMap.remove(r4);
    L8:
        this.mWorkRuns.remove(r4);
        if (r1 == false) goto L11;
        stopForegroundService();
    L11:
        return r0;
    L5:
        r1 = false;
        goto L6
    }

    private WorkerWrapper getWorkerWrapperUnsafe(String r2) {
        WorkerWrapper r0 = this.mForegroundWorkMap.get(r2);
        if (r0 == null) goto L5;
        return r0;
    L5:
        return this.mEnqueuedWorkMap.get(r2);
    }

    private static boolean interrupt(String r2, WorkerWrapper r3, int r4) {
        if (r3 == null) goto L5;
        r3.interrupt(r4);
        Logger.get().debug(TAG, "WorkerWrapper interrupted for " + r2);
        return true;
    L5:
        Logger.get().debug(TAG, "WorkerWrapper could not be found for " + r2);
        return false;
    }

    private void onExecuted(WorkerWrapper r7, boolean r8) {
        Object r0 = this.mLock;
        monitor-enter(r0);
        WorkGenerationalId r1 = r7.getWorkGenerationalId();     // Catch: Throwable -> L7
        String r2 = r1.getWorkSpecId();     // Catch: Throwable -> L7
        if (getWorkerWrapperUnsafe(r2) != r7) goto L9;
        cleanUpWorkerUnsafe(r2);     // Catch: Throwable -> L7
    L9:
        Logger.get().debug(TAG, getClass().getSimpleName() + " " + r2 + " executed; reschedule = " + r8);     // Catch: Throwable -> L7
        Iterator<ExecutionListener> r6 = this.mOuterListeners.iterator();     // Catch: Throwable -> L7
    L11:
        if (r6.hasNext() == false) goto L13;
        r6.next().onExecuted(r1, r8);     // Catch: Throwable -> L7
        goto L11
    L13:
        monitor-exit(r0);     // Catch: Throwable -> L7
        return;
    L7:
        th = move-exception;
        throw th;
    }

    private void runOnExecuted(WorkGenerationalId r3, boolean r4) {
        this.mWorkTaskExecutor.getMainThreadExecutor().execute(new Processor$$ExternalSyntheticLambda2(this, r3, r4));
    }

    private void stopForegroundService() {
        Object r0 = this.mLock;
        monitor-enter(r0);
    L14:
        th = move-exception;
        throw th;
    L5:
        if (this.mForegroundWorkMap.isEmpty() == false) goto L16;
        Intent r1 = SystemForegroundDispatcher.createStopForegroundIntent(this.mAppContext);     // Catch: Throwable -> L14
        this.mAppContext.startService(r1);     // Catch: Throwable -> L9
    L11:
        PowerManager.WakeLock r12 = this.mForegroundLock;     // Catch: Throwable -> L14
        if (r12 == null) goto L16;
        r12.release();     // Catch: Throwable -> L14
        this.mForegroundLock = null;     // Catch: Throwable -> L14
        goto L16
    L9:
        th = move-exception;
        Logger.get().error(TAG, "Unable to stop foreground service", th);     // Catch: Throwable -> L14
    L16:
        monitor-exit(r0);     // Catch: Throwable -> L14
    }

    public void addExecutionListener(ExecutionListener r2) {
        Object r0 = this.mLock;
        monitor-enter(r0);
        this.mOuterListeners.add(r2);     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return;
    L7:
        th = move-exception;
        throw th;
    }

    public WorkSpec getRunningWorkSpec(String r2) {
        Object r0 = this.mLock;
        monitor-enter(r0);
        WorkerWrapper r1 = getWorkerWrapperUnsafe(r2);     // Catch: Throwable -> L9
        if (r1 == null) goto L12;
        WorkSpec r12 = r1.getWorkSpec();     // Catch: Throwable -> L9
        monitor-exit(r0);     // Catch: Throwable -> L9
        return r12;
    L12:
        monitor-exit(r0);     // Catch: Throwable -> L9
        return null;
    L9:
        th = move-exception;
        throw th;
    }

    public boolean isCancelled(String r2) {
        Object r0 = this.mLock;
        monitor-enter(r0);
        boolean r1 = this.mCancelledIds.contains(r2);     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return r1;
    L7:
        th = move-exception;
        throw th;
    }

    public boolean isEnqueued(String r2) {
        Object r0 = this.mLock;
        monitor-enter(r0);
    L10:
        th = move-exception;
        throw th;
    L5:
        if (getWorkerWrapperUnsafe(r2) == null) goto L7;
        boolean r1 = true;
    L8:
        monitor-exit(r0);     // Catch: Throwable -> L10
        return r1;
    L7:
        r1 = false;
        goto L8
    }

    public void removeExecutionListener(ExecutionListener r2) {
        Object r0 = this.mLock;
        monitor-enter(r0);
        this.mOuterListeners.remove(r2);     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return;
    L7:
        th = move-exception;
        throw th;
    }

    @Override // androidx.work.impl.foreground.ForegroundProcessor
    public void startForeground(String r6, ForegroundInfo r7) {
        Object r1 = this.mLock;
        monitor-enter(r1);
        Logger.get().info(TAG, "Moving WorkSpec (" + r6 + ") to the foreground");     // Catch: Throwable -> L9
        WorkerWrapper r0 = this.mEnqueuedWorkMap.remove(r6);     // Catch: Throwable -> L9
        if (r0 != null) goto L7;
    L12:
        monitor-exit(r1);     // Catch: Throwable -> L9
        return;
    L7:
        if (this.mForegroundLock != null) goto L11;
        PowerManager.WakeLock r2 = WakeLocks.newWakeLock(this.mAppContext, "ProcessorForegroundLck");     // Catch: Throwable -> L9
        this.mForegroundLock = r2;     // Catch: Throwable -> L9
        r2.acquire();     // Catch: Throwable -> L9
    L11:
        this.mForegroundWorkMap.put(r6, r0);     // Catch: Throwable -> L9
        ContextCompat.startForegroundService(this.mAppContext, SystemForegroundDispatcher.createStartForegroundIntent(this.mAppContext, r0.getWorkGenerationalId(), r7));     // Catch: Throwable -> L9
    L9:
        th = move-exception;
        throw th;
    }

    public boolean startWork(StartStopToken r13, WorkerParameters.RuntimeExtras r14) {
        WorkGenerationalId r1 = r13.getId();
        String r2 = r1.getWorkSpecId();
        ArrayList r10 = new ArrayList();
        WorkSpec r9 = (WorkSpec) this.mWorkDatabase.runInTransaction(new Processor$$ExternalSyntheticLambda0(this, r10, r2));
        if (r9 != null) goto L6;
        Logger.get().warning(TAG, "Didn't find WorkSpec for id " + r1);
        runOnExecuted(r1, false);
        return false;
    L6:
        Object r11 = this.mLock;
        monitor-enter(r11);
    L13:
        th = move-exception;
        throw th;
    L9:
        if (isEnqueued(r2) == false) goto L19;
        Set<StartStopToken> r142 = this.mWorkRuns.get(r2);     // Catch: Throwable -> L13
        if (r142.iterator().next().getId().getGeneration() != r1.getGeneration()) goto L15;
        r142.add(r13);     // Catch: Throwable -> L13
        Logger.get().debug(TAG, "Work " + r1 + " is already enqueued for processing");     // Catch: Throwable -> L13
    L16:
        monitor-exit(r11);     // Catch: Throwable -> L13
        return false;
    L15:
        runOnExecuted(r1, false);     // Catch: Throwable -> L13
        goto L16
    L19:
        if (r9.getGeneration() == r1.getGeneration()) goto L23;
        runOnExecuted(r1, false);     // Catch: Throwable -> L13
        monitor-exit(r11);     // Catch: Throwable -> L13
        return false;
    L23:
        WorkerWrapper r12 = new WorkerWrapper.Builder(this.mAppContext, this.mConfiguration, this.mWorkTaskExecutor, this, this.mWorkDatabase, r9, r10).withRuntimeExtras(r14).build();     // Catch: Throwable -> L13
        ListenableFuture<Boolean> r143 = r12.launch();     // Catch: Throwable -> L13
        r143.addListener(new Processor$$ExternalSyntheticLambda1(this, r143, r12), this.mWorkTaskExecutor.getMainThreadExecutor());     // Catch: Throwable -> L13
        this.mEnqueuedWorkMap.put(r2, r12);     // Catch: Throwable -> L13
        HashSet r122 = new HashSet();     // Catch: Throwable -> L13
        r122.add(r13);     // Catch: Throwable -> L13
        this.mWorkRuns.put(r2, r122);     // Catch: Throwable -> L13
        monitor-exit(r11);     // Catch: Throwable -> L13
        Logger.get().debug(TAG, getClass().getSimpleName() + ": processing " + r1);
        return true;
    }

    public boolean stopAndCancelWork(String r6, int r7) {
        Object r1 = this.mLock;
        monitor-enter(r1);
        Logger.get().debug(TAG, "Processor cancelling " + r6);     // Catch: Throwable -> L8
        this.mCancelledIds.add(r6);     // Catch: Throwable -> L8
        WorkerWrapper r5 = cleanUpWorkerUnsafe(r6);     // Catch: Throwable -> L8
        monitor-exit(r1);     // Catch: Throwable -> L8
        return interrupt(r6, r5, r7);
    L8:
        th = move-exception;
        throw th;
    }

    public boolean stopForegroundWork(StartStopToken r2, int r3) {
        String r22 = r2.getId().getWorkSpecId();
        Object r0 = this.mLock;
        monitor-enter(r0);
        WorkerWrapper r1 = cleanUpWorkerUnsafe(r22);     // Catch: Throwable -> L8
        monitor-exit(r0);     // Catch: Throwable -> L8
        return interrupt(r22, r1, r3);
    L8:
        th = move-exception;
        throw th;
    }

    public boolean stopWork(StartStopToken r6, int r7) {
        String r1 = r6.getId().getWorkSpecId();
        Object r2 = this.mLock;
        monitor-enter(r2);
    L9:
        th = move-exception;
        throw th;
    L5:
        if (this.mForegroundWorkMap.get(r1) == null) goto L11;
        Logger.get().debug(TAG, "Ignored stopWork. WorkerWrapper " + r1 + " is in foreground");     // Catch: Throwable -> L9
        monitor-exit(r2);     // Catch: Throwable -> L9
        return false;
    L11:
        Set<StartStopToken> r0 = this.mWorkRuns.get(r1);     // Catch: Throwable -> L9
        if (r0 != null) goto L14;
    L20:
        monitor-exit(r2);     // Catch: Throwable -> L9
        return false;
    L14:
        if (r0.contains(r6) == false) goto L20;
        WorkerWrapper r5 = cleanUpWorkerUnsafe(r1);     // Catch: Throwable -> L9
        monitor-exit(r2);     // Catch: Throwable -> L9
        return interrupt(r1, r5, r7);
    }
}
