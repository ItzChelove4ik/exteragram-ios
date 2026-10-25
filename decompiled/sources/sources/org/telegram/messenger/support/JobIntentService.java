package org.telegram.messenger.support;

import android.app.Service;
import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobServiceEngine;
import android.app.job.JobWorkItem;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.IBinder;
import android.os.PowerManager;
import c.f$$ExternalSyntheticBUOutline1;
import com.android.dex.Dex$$ExternalSyntheticBUOutline1;
import java.util.ArrayList;
import java.util.HashMap;
import org.telegram.messenger.FileLog;

/* loaded from: classes.dex */
public abstract class JobIntentService extends Service {
    static final boolean DEBUG = false;
    static final String TAG = "JobIntentService";
    static final HashMap<ComponentName, WorkEnqueuer> sClassWorkEnqueuer = null;
    static final Object sLock = null;
    final ArrayList<CompatWorkItem> mCompatQueue;
    WorkEnqueuer mCompatWorkEnqueuer;
    CommandProcessor mCurProcessor;
    boolean mDestroyed;
    boolean mInterruptIfStopped;
    CompatJobEngine mJobImpl;
    boolean mStopped;

    public final class CommandProcessor extends AsyncTask<Void, Void, Void> {
        final /* synthetic */ JobIntentService this$0;

        public CommandProcessor(JobIntentService r1) {
            this.this$0 = r1;
        }

        @Override // android.os.AsyncTask
        public /* bridge */ /* synthetic */ Void doInBackground(Void[] r1) {
            return doInBackground2(r1);
        }

        /* renamed from: doInBackground, reason: avoid collision after fix types in other method */
        public Void doInBackground2(Void... r3) {
        L2:
            GenericWorkItem r32 = this.this$0.dequeueWork();
            if (r32 == null) goto L7;
            this.this$0.onHandleWork(r32.getIntent());
            r32.complete();     // Catch: Throwable -> L9
            goto L2
        L7:
            return null;
        }

        @Override // android.os.AsyncTask
        public /* bridge */ /* synthetic */ void onCancelled(Void r1) {
            onCancelled2(r1);
        }

        /* renamed from: onCancelled, reason: avoid collision after fix types in other method */
        public void onCancelled2(Void r1) {
            this.this$0.processorFinished();
        }

        @Override // android.os.AsyncTask
        public /* bridge */ /* synthetic */ void onPostExecute(Void r1) {
            onPostExecute2(r1);
        }

        /* renamed from: onPostExecute, reason: avoid collision after fix types in other method */
        public void onPostExecute2(Void r1) {
            this.this$0.processorFinished();
        }
    }

    public interface CompatJobEngine {
        IBinder compatGetBinder();

        GenericWorkItem dequeueWork();
    }

    /* loaded from: classes3.dex */
    public static final class CompatWorkEnqueuer extends WorkEnqueuer {
        private final Context mContext;
        private final PowerManager.WakeLock mLaunchWakeLock;
        boolean mLaunchingService;
        private final PowerManager.WakeLock mRunWakeLock;
        boolean mServiceProcessing;

        public CompatWorkEnqueuer(Context r4, ComponentName r5) {
            super(r4, r5);
            this.mContext = r4.getApplicationContext();
            PowerManager r42 = (PowerManager) r4.getSystemService("power");
            PowerManager.WakeLock r0 = r42.newWakeLock(1, r5.getClassName() + ":launch");
            this.mLaunchWakeLock = r0;
            r0.setReferenceCounted(false);
            PowerManager.WakeLock r43 = r42.newWakeLock(1, r5.getClassName() + ":run");
            this.mRunWakeLock = r43;
            r43.setReferenceCounted(false);
        }

        @Override // org.telegram.messenger.support.JobIntentService.WorkEnqueuer
        public void enqueueWork(Intent r3) {
            Intent r0 = new Intent(r3);
            r0.setComponent(this.mComponentName);
            if (this.mContext.startService(r0) == null) goto L16;
            monitor-enter(this);
        L10:
            th = move-exception;
            throw th;
        L6:
            if (this.mLaunchingService == true) goto L12;
            this.mLaunchingService = true;     // Catch: Throwable -> L10
            if (this.mServiceProcessing == true) goto L12;
            this.mLaunchWakeLock.acquire(60000);     // Catch: Throwable -> L10
        L12:
            monitor-exit(this);     // Catch: Throwable -> L10
            return;
        }

        @Override // org.telegram.messenger.support.JobIntentService.WorkEnqueuer
        public void serviceProcessingFinished() {
            monitor-enter(this);
        L8:
            th = move-exception;
            throw th;
        L4:
            if (this.mServiceProcessing == true) goto L6;
        L11:
            monitor-exit(this);     // Catch: Throwable -> L8
            return;
        L6:
            if (this.mLaunchingService == false) goto L10;
            this.mLaunchWakeLock.acquire(60000);     // Catch: Throwable -> L8
        L10:
            this.mServiceProcessing = false;     // Catch: Throwable -> L8
            this.mRunWakeLock.release();     // Catch: Throwable -> L8
            goto L11
        }

        @Override // org.telegram.messenger.support.JobIntentService.WorkEnqueuer
        public void serviceProcessingStarted() {
            monitor-enter(this);
        L10:
            th = move-exception;
            throw th;
        L4:
            if (this.mServiceProcessing == true) goto L12;
            this.mServiceProcessing = true;     // Catch: Throwable -> L10
            this.mRunWakeLock.acquire(120000);     // Catch: Throwable -> L8
            this.mLaunchWakeLock.release();     // Catch: Throwable -> L8
        L8:
            th = move-exception;
            FileLog.e(th);     // Catch: Throwable -> L10
            this.mServiceProcessing = false;     // Catch: Throwable -> L10
        L12:
            monitor-exit(this);     // Catch: Throwable -> L10
        }

        @Override // org.telegram.messenger.support.JobIntentService.WorkEnqueuer
        public void serviceStartReceived() {
            monitor-enter(this);
            this.mLaunchingService = false;     // Catch: Throwable -> L7
            monitor-exit(this);     // Catch: Throwable -> L7
            return;
        L7:
            th = move-exception;
            throw th;
        }
    }

    /* loaded from: classes3.dex */
    public final class CompatWorkItem implements GenericWorkItem {
        final Intent mIntent;
        final int mStartId;
        final /* synthetic */ JobIntentService this$0;

        public CompatWorkItem(JobIntentService r1, Intent r2, int r3) {
            this.this$0 = r1;
            this.mIntent = r2;
            this.mStartId = r3;
        }

        @Override // org.telegram.messenger.support.JobIntentService.GenericWorkItem
        public void complete() {
            this.this$0.stopSelf(this.mStartId);
        }

        @Override // org.telegram.messenger.support.JobIntentService.GenericWorkItem
        public Intent getIntent() {
            return this.mIntent;
        }
    }

    public interface GenericWorkItem {
        void complete();

        Intent getIntent();
    }

    public static final class JobServiceEngineImpl extends JobServiceEngine implements CompatJobEngine {
        static final boolean DEBUG = false;
        static final String TAG = "JobServiceEngineImpl";
        final Object mLock;
        JobParameters mParams;
        final JobIntentService mService;

        public final class WrapperWorkItem implements GenericWorkItem {
            final JobWorkItem mJobWork;
            final /* synthetic */ JobServiceEngineImpl this$0;

            public WrapperWorkItem(JobServiceEngineImpl r1, JobWorkItem r2) {
                this.this$0 = r1;
                this.mJobWork = r2;
            }

            @Override // org.telegram.messenger.support.JobIntentService.GenericWorkItem
            public void complete() {
                Object r0 = this.this$0.mLock;
                monitor-enter(r0);
                JobParameters r1 = this.this$0.mParams;     // Catch: Throwable -> L7
                if (r1 == null) goto L9;
                r1.completeWork(this.mJobWork);     // Catch: Throwable -> L7
            L9:
                monitor-exit(r0);     // Catch: Throwable -> L7
                return;
            L7:
                th = move-exception;
                throw th;
            }

            @Override // org.telegram.messenger.support.JobIntentService.GenericWorkItem
            public Intent getIntent() {
                return this.mJobWork.getIntent();
            }
        }

        public JobServiceEngineImpl(JobIntentService r2) {
            super(r2);
            this.mLock = new Object();
            this.mService = r2;
        }

        @Override // org.telegram.messenger.support.JobIntentService.CompatJobEngine
        public IBinder compatGetBinder() {
            return getBinder();
        }

        @Override // org.telegram.messenger.support.JobIntentService.CompatJobEngine
        public GenericWorkItem dequeueWork() {
            Object r0 = this.mLock;
            monitor-enter(r0);
            JobParameters r1 = this.mParams;     // Catch: Throwable -> L8
            if (r1 != null) goto L20;
            monitor-exit(r0);     // Catch: Throwable -> L8
            return null;
        L20:
            JobWorkItem r12 = r1.dequeueWork();     // Catch: Throwable -> L12
        L13:
            monitor-exit(r0);     // Catch: Throwable -> L8
            if (r12 == null) goto L17;
            r12.getIntent().setExtrasClassLoader(this.mService.getClassLoader());
            return new WrapperWorkItem(this, r12);
        L17:
            return null;
        L12:
            r12 = null;
        L8:
            th = move-exception;
            throw th;
        }

        public boolean onStartJob(JobParameters r1) {
            this.mParams = r1;
            this.mService.ensureProcessorRunningLocked(false);
            return true;
        }

        public boolean onStopJob(JobParameters r3) {
            boolean r32 = this.mService.doStopCurrentWork();
            Object r0 = this.mLock;
            monitor-enter(r0);
            this.mParams = null;     // Catch: Throwable -> L8
            monitor-exit(r0);     // Catch: Throwable -> L8
            return r32;
        L8:
            th = move-exception;
            throw th;
        }
    }

    public static final class JobWorkEnqueuer extends WorkEnqueuer {
        private final JobInfo mJobInfo;
        private final JobScheduler mJobScheduler;

        public JobWorkEnqueuer(Context r3, ComponentName r4, int r5) {
            super(r3, r4);
            ensureJobId(r5);
            this.mJobInfo = new JobInfo.Builder(r5, this.mComponentName).setOverrideDeadline(0).setRequiredNetworkType(1).build();
            this.mJobScheduler = (JobScheduler) r3.getApplicationContext().getSystemService("jobscheduler");
        }

        @Override // org.telegram.messenger.support.JobIntentService.WorkEnqueuer
        public void enqueueWork(Intent r2) {
            this.mJobScheduler.enqueue(this.mJobInfo, JobIntentService$JobWorkEnqueuer$$ExternalSyntheticApiModelOutline0.m(r2));
        }
    }

    public static abstract class WorkEnqueuer {
        final ComponentName mComponentName;
        boolean mHasJobId;
        int mJobId;

        public WorkEnqueuer(Context r1, ComponentName r2) {
            this.mComponentName = r2;
        }

        public abstract void enqueueWork(Intent r1);

        public void ensureJobId(int r3) {
            if (this.mHasJobId == true) goto L7;
            this.mHasJobId = true;
            this.mJobId = r3;
            return;
        L7:
            if (this.mJobId != r3) goto L9;
            return;
        L9:
            Dex$$ExternalSyntheticBUOutline1.m("Given job ID ", r3, " is different than previous ", this.mJobId);
        }

        public void serviceProcessingFinished() {
        }

        public void serviceProcessingStarted() {
        }

        public void serviceStartReceived() {
        }
    }

    static {
        sLock = new Object();
        sClassWorkEnqueuer = new HashMap();
    }

    public JobIntentService() {
        this.mInterruptIfStopped = false;
        this.mStopped = false;
        this.mDestroyed = false;
        if (Build.VERSION.SDK_INT < 26) goto L6;
        this.mCompatQueue = null;
        return;
    L6:
        this.mCompatQueue = new ArrayList();
    }

    public static void enqueueWork(Context r2, ComponentName r3, int r4, Intent r5) {
        if (r5 == null) goto L12;
        Object r0 = sLock;
        monitor-enter(r0);
        WorkEnqueuer r22 = getWorkEnqueuer(r2, r3, true, r4);     // Catch: Throwable -> L9
        r22.ensureJobId(r4);     // Catch: Throwable -> L9
        r22.enqueueWork(r5);     // Catch: Throwable -> L9
        monitor-exit(r0);     // Catch: Throwable -> L9
        return;
    L9:
        th = move-exception;
        throw th;
    L12:
        f$$ExternalSyntheticBUOutline1.m("work must not be null");
    }

    public static void enqueueWork(Context r1, Class r2, int r3, Intent r4) {
        enqueueWork(r1, new ComponentName(r1, r2), r3, r4);
    }

    public static WorkEnqueuer getWorkEnqueuer(Context r3, ComponentName r4, boolean r5, int r6) {
        HashMap<ComponentName, WorkEnqueuer> r0 = sClassWorkEnqueuer;
        WorkEnqueuer r1 = r0.get(r4);
        if (r1 == null) goto L5;
        return r1;
    L5:
        if (Build.VERSION.SDK_INT < 26) goto L10;
        if (r5 == false) goto L8;
        WorkEnqueuer r52 = new JobWorkEnqueuer(r3, r4, r6);
    L11:
        r0.put(r4, r52);
        return r52;
    L8:
        f$$ExternalSyntheticBUOutline1.m("Can't be here without a job id");
        return null;
    L10:
        r52 = new CompatWorkEnqueuer(r3, r4);
        goto L11
    }

    public GenericWorkItem dequeueWork() {
        CompatJobEngine r0 = this.mJobImpl;
        if (r0 != null) goto L5;
        ArrayList<CompatWorkItem> r02 = this.mCompatQueue;
        monitor-enter(r02);
    L13:
        th = move-exception;
        throw th;
    L9:
        if (this.mCompatQueue.size() <= 0) goto L16;
        CompatWorkItem r2 = this.mCompatQueue.remove(0);     // Catch: Throwable -> L13
        monitor-exit(r02);     // Catch: Throwable -> L13
        return r2;
    L16:
        monitor-exit(r02);     // Catch: Throwable -> L13
        return null;
    L5:
        return r0.dequeueWork();
    }

    public boolean doStopCurrentWork() {
        CommandProcessor r0 = this.mCurProcessor;
        if (r0 == null) goto L5;
        r0.cancel(this.mInterruptIfStopped);
    L5:
        this.mStopped = true;
        return onStopCurrentWork();
    }

    public void ensureProcessorRunningLocked(boolean r2) {
        if (this.mCurProcessor != null) goto L10;
        this.mCurProcessor = new CommandProcessor(this);
        WorkEnqueuer r0 = this.mCompatWorkEnqueuer;
        if (r0 == null) goto L8;
        if (r2 == false) goto L8;
        r0.serviceProcessingStarted();
    L8:
        this.mCurProcessor.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        return;
    }

    public boolean isStopped() {
        return this.mStopped;
    }

    @Override // android.app.Service
    public IBinder onBind(Intent r1) {
        CompatJobEngine r0 = this.mJobImpl;
        if (r0 != null) goto L5;
        return null;
    L5:
        return r0.compatGetBinder();
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        if (Build.VERSION.SDK_INT < 26) goto L6;
        this.mJobImpl = new JobServiceEngineImpl(this);
        this.mCompatWorkEnqueuer = null;
        return;
    L6:
        this.mJobImpl = null;
        this.mCompatWorkEnqueuer = getWorkEnqueuer(this, new ComponentName(this, getClass()), false, 0);
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        ArrayList<CompatWorkItem> r0 = this.mCompatQueue;
        if (r0 == null) goto L12;
        monitor-enter(r0);
        this.mDestroyed = true;     // Catch: Throwable -> L9
        this.mCompatWorkEnqueuer.serviceProcessingFinished();     // Catch: Throwable -> L9
        monitor-exit(r0);     // Catch: Throwable -> L9
        return;
    L9:
        th = move-exception;
        throw th;
    }

    public abstract void onHandleWork(Intent r1);

    @Override // android.app.Service
    public int onStartCommand(Intent r3, int r4, int r5) {
        if (this.mCompatQueue == null) goto L17;
        this.mCompatWorkEnqueuer.serviceStartReceived();
        ArrayList<CompatWorkItem> r42 = this.mCompatQueue;
        monitor-enter(r42);
        ArrayList<CompatWorkItem> r0 = this.mCompatQueue;     // Catch: Throwable -> L14
        if (r3 != null) goto L10;
        r3 = new Intent();     // Catch: Throwable -> L14
    L10:
        r0.add(new CompatWorkItem(this, r3, r5));     // Catch: Throwable -> L14
        ensureProcessorRunningLocked(true);     // Catch: Throwable -> L14
        monitor-exit(r42);     // Catch: Throwable -> L14
        return 3;
    L14:
        th = move-exception;
        throw th;
    L17:
        return 2;
    }

    public boolean onStopCurrentWork() {
        return true;
    }

    public void processorFinished() {
        ArrayList<CompatWorkItem> r0 = this.mCompatQueue;
        if (r0 == null) goto L20;
        monitor-enter(r0);
        this.mCurProcessor = null;     // Catch: Throwable -> L11
        ArrayList<CompatWorkItem> r1 = this.mCompatQueue;     // Catch: Throwable -> L11
        if (r1 == null) goto L14;
        if (r1.size() <= 0) goto L14;
        ensureProcessorRunningLocked(false);     // Catch: Throwable -> L11
    L16:
        monitor-exit(r0);     // Catch: Throwable -> L11
        return;
    L14:
        if (this.mDestroyed == true) goto L16;
        this.mCompatWorkEnqueuer.serviceProcessingFinished();     // Catch: Throwable -> L11
    L11:
        th = move-exception;
        throw th;
    }

    public void setInterruptIfStopped(boolean r1) {
        this.mInterruptIfStopped = r1;
    }
}
