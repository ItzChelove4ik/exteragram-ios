package androidx.work.impl;

import android.content.BroadcastReceiver;
import android.content.Context;
import androidx.work.Configuration;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.ExistingWorkPolicy;
import androidx.work.Logger;
import androidx.work.Operation;
import androidx.work.PeriodicWorkRequest;
import androidx.work.TracerKt;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;
import androidx.work.impl.background.systemjob.SystemJobScheduler;
import androidx.work.impl.constraints.trackers.Trackers;
import androidx.work.impl.model.WorkGenerationalId;
import androidx.work.impl.utils.CancelWorkRunnable;
import androidx.work.impl.utils.ForceStopRunnable;
import androidx.work.impl.utils.PreferenceUtils;
import androidx.work.impl.utils.StopWorkRunnable;
import androidx.work.impl.utils.taskexecutor.TaskExecutor;
import c.f$$ExternalSyntheticBUOutline1;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import kotlin.Unit;
import kotlinx.coroutines.CoroutineScope;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes.dex */
public class WorkManagerImpl extends WorkManager {
    private static final String TAG = null;
    private static WorkManagerImpl sDefaultInstance;
    private static WorkManagerImpl sDelegatedInstance;
    private static final Object sLock = null;
    private Configuration mConfiguration;
    private Context mContext;
    private boolean mForceStopRunnableCompleted;
    private PreferenceUtils mPreferenceUtils;
    private Processor mProcessor;
    private BroadcastReceiver.PendingResult mRescheduleReceiverResult;
    private List<Scheduler> mSchedulers;
    private final Trackers mTrackers;
    private WorkDatabase mWorkDatabase;
    private final CoroutineScope mWorkManagerScope;
    private TaskExecutor mWorkTaskExecutor;

    public static class Api24Impl {
        public static boolean isDeviceProtectedStorage(Context r0) {
            return r0.isDeviceProtectedStorage();
        }
    }

    /* renamed from: $r8$lambda$Wf3V6NEp-TzV_1f6Vedbhp5FiGA, reason: not valid java name */
    public static /* synthetic */ Unit m978$r8$lambda$Wf3V6NEpTzV_1f6Vedbhp5FiGA(WorkManagerImpl r2) {
        SystemJobScheduler.cancelAllInAllNamespaces(r2.getApplicationContext());
        r2.getWorkDatabase().workSpecDao().resetScheduledState();
        Schedulers.schedule(r2.getConfiguration(), r2.getWorkDatabase(), r2.getSchedulers());
        return Unit.INSTANCE;
    }

    static {
        TAG = Logger.tagWithPrefix("WorkManagerImpl");
        sDelegatedInstance = null;
        sDefaultInstance = null;
        sLock = new Object();
    }

    public WorkManagerImpl(Context r3, Configuration r4, TaskExecutor r5, WorkDatabase r6, List<Scheduler> r7, Processor r8, Trackers r9) {
        this.mForceStopRunnableCompleted = false;
        Context r32 = r3.getApplicationContext();
        if (Api24Impl.isDeviceProtectedStorage(r32) == true) goto L6;
        Logger.setLogger(new Logger.LogcatLogger(r4.getMinimumLoggingLevel()));
        this.mContext = r32;
        this.mWorkTaskExecutor = r5;
        this.mWorkDatabase = r6;
        this.mProcessor = r8;
        this.mTrackers = r9;
        this.mConfiguration = r4;
        this.mSchedulers = r7;
        CoroutineScope r82 = WorkManagerImplExtKt.createWorkManagerScope(r5);
        this.mWorkManagerScope = r82;
        this.mPreferenceUtils = new PreferenceUtils(this.mWorkDatabase);
        Schedulers.registerRescheduling(r7, this.mProcessor, r5.getSerialTaskExecutor(), this.mWorkDatabase, r4);
        this.mWorkTaskExecutor.executeOnTaskThread(new ForceStopRunnable(r32, this));
        UnfinishedWorkListenerKt.maybeLaunchUnfinishedWorkListener(r82, this.mContext, r4, r6);
        return;
    L6:
        Segment$$ExternalSyntheticBUOutline1.m("Cannot initialize WorkManager in direct boot mode");
        throw null;
    }

    @Deprecated
    public static WorkManagerImpl getInstance() {
        Object r0 = sLock;
        monitor-enter(r0);
        WorkManagerImpl r1 = sDelegatedInstance;     // Catch: Throwable -> L8
        if (r1 == null) goto L10;
        monitor-exit(r0);     // Catch: Throwable -> L8
        return r1;
    L10:
        WorkManagerImpl r12 = sDefaultInstance;     // Catch: Throwable -> L8
        monitor-exit(r0);     // Catch: Throwable -> L8
        return r12;
    L8:
        th = move-exception;
        throw th;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static WorkManagerImpl getInstance(Context r2) {
        Object r0 = sLock;
        monitor-enter(r0);
        WorkManagerImpl r1 = getInstance();     // Catch: Throwable -> L9
        if (r1 != null) goto L13;
        Context r22 = r2.getApplicationContext();     // Catch: Throwable -> L9
        if ((r22 instanceof Configuration.Provider) == false) goto L12;
        initialize(r22, ((Configuration.Provider) r22).getWorkManagerConfiguration());     // Catch: Throwable -> L9
        r1 = getInstance(r22);     // Catch: Throwable -> L9
        goto L13
    L12:
        throw new IllegalStateException("WorkManager is not initialized properly.  You have explicitly disabled WorkManagerInitializer in your manifest, have not manually called WorkManager#initialize at this point, and your Application does not implement Configuration.Provider.");     // Catch: Throwable -> L9
    L13:
        monitor-exit(r0);     // Catch: Throwable -> L9
        return r1;
    L9:
        th = move-exception;
        throw th;
    }

    public static void initialize(Context r3, Configuration r4) {
        Object r0 = sLock;
        monitor-enter(r0);
        WorkManagerImpl r1 = sDelegatedInstance;     // Catch: Throwable -> L11
        if (r1 != null) goto L7;
    L13:
        if (r1 != null) goto L18;
        Context r32 = r3.getApplicationContext();     // Catch: Throwable -> L11
        if (sDefaultInstance != null) goto L17;
        sDefaultInstance = WorkManagerImplExtKt.createWorkManager(r32, r4);     // Catch: Throwable -> L11
    L17:
        sDelegatedInstance = sDefaultInstance;     // Catch: Throwable -> L11
    L18:
        monitor-exit(r0);     // Catch: Throwable -> L11
        return;
    L7:
        if (sDefaultInstance == null) goto L13;
        throw new IllegalStateException("WorkManager is already initialized.  Did you try to initialize it manually without disabling WorkManagerInitializer? See WorkManager#initialize(Context, Configuration) or the class level Javadoc for more information.");     // Catch: Throwable -> L11
    L11:
        th = move-exception;
        throw th;
    }

    public Operation cancelWorkById(UUID r1) {
        return CancelWorkRunnable.forId(r1, this);
    }

    public WorkContinuationImpl createWorkContinuationForUniquePeriodicWork(String r2, ExistingPeriodicWorkPolicy r3, PeriodicWorkRequest r4) {
        if (r3 != ExistingPeriodicWorkPolicy.KEEP) goto L5;
        ExistingWorkPolicy r32 = ExistingWorkPolicy.KEEP;
    L7:
        return new WorkContinuationImpl(this, r2, r32, Collections.singletonList(r4));
    L5:
        r32 = ExistingWorkPolicy.REPLACE;
        goto L7
    }

    @Override // androidx.work.WorkManager
    public Operation enqueue(List<? extends WorkRequest> r2) {
        if (r2.isEmpty() == false) goto L5;
        f$$ExternalSyntheticBUOutline1.m("enqueue needs at least one WorkRequest.");
        return null;
    L5:
        return new WorkContinuationImpl(this, r2).enqueue();
    }

    @Override // androidx.work.WorkManager
    public Operation enqueueUniquePeriodicWork(String r2, ExistingPeriodicWorkPolicy r3, PeriodicWorkRequest r4) {
        if (r3 != ExistingPeriodicWorkPolicy.UPDATE) goto L7;
        return WorkerUpdater.enqueueUniquelyNamedPeriodic(this, r2, r4);
    L7:
        return createWorkContinuationForUniquePeriodicWork(r2, r3, r4).enqueue();
    }

    public Context getApplicationContext() {
        return this.mContext;
    }

    public Configuration getConfiguration() {
        return this.mConfiguration;
    }

    public PreferenceUtils getPreferenceUtils() {
        return this.mPreferenceUtils;
    }

    public Processor getProcessor() {
        return this.mProcessor;
    }

    public List<Scheduler> getSchedulers() {
        return this.mSchedulers;
    }

    public Trackers getTrackers() {
        return this.mTrackers;
    }

    public WorkDatabase getWorkDatabase() {
        return this.mWorkDatabase;
    }

    public TaskExecutor getWorkTaskExecutor() {
        return this.mWorkTaskExecutor;
    }

    public void onForceStopRunnableCompleted() {
        Object r0 = sLock;
        monitor-enter(r0);
        this.mForceStopRunnableCompleted = true;     // Catch: Throwable -> L8
        BroadcastReceiver.PendingResult r1 = this.mRescheduleReceiverResult;     // Catch: Throwable -> L8
        if (r1 == null) goto L10;
        r1.finish();     // Catch: Throwable -> L8
        this.mRescheduleReceiverResult = null;     // Catch: Throwable -> L8
    L10:
        monitor-exit(r0);     // Catch: Throwable -> L8
        return;
    L8:
        th = move-exception;
        throw th;
    }

    public void rescheduleEligibleWork() {
        TracerKt.traced(getConfiguration().getTracer(), "ReschedulingWork", new WorkManagerImpl$$ExternalSyntheticLambda0(this));
    }

    public void setReschedulePendingResult(BroadcastReceiver.PendingResult r3) {
        Object r0 = sLock;
        monitor-enter(r0);
        BroadcastReceiver.PendingResult r1 = this.mRescheduleReceiverResult;     // Catch: Throwable -> L7
        if (r1 == null) goto L9;
        r1.finish();     // Catch: Throwable -> L7
    L9:
        this.mRescheduleReceiverResult = r3;     // Catch: Throwable -> L7
        if (this.mForceStopRunnableCompleted == false) goto L12;
        r3.finish();     // Catch: Throwable -> L7
        this.mRescheduleReceiverResult = null;     // Catch: Throwable -> L7
    L12:
        monitor-exit(r0);     // Catch: Throwable -> L7
        return;
    L7:
        th = move-exception;
        throw th;
    }

    public void stopForegroundWork(WorkGenerationalId r4, int r5) {
        this.mWorkTaskExecutor.executeOnTaskThread(new StopWorkRunnable(this.mProcessor, new StartStopToken(r4), true, r5));
    }
}
