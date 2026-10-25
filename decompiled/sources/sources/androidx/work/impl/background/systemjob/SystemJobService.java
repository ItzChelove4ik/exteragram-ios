package androidx.work.impl.background.systemjob;

import android.app.Application;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.net.Network;
import android.net.Uri;
import android.os.Build;
import android.os.Looper;
import android.os.PersistableBundle;
import androidx.lifecycle.LiveData$$ExternalSyntheticBUOutline0;
import androidx.work.Logger;
import androidx.work.WorkerParameters;
import androidx.work.impl.ExecutionListener;
import androidx.work.impl.Processor;
import androidx.work.impl.StartStopToken;
import androidx.work.impl.StartStopTokens;
import androidx.work.impl.WorkLauncher;
import androidx.work.impl.WorkLauncherImpl;
import androidx.work.impl.WorkManagerImpl;
import androidx.work.impl.model.WorkGenerationalId;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.mvel2.asm.Constants$$ExternalSyntheticBUOutline0;

/* loaded from: classes.dex */
public class SystemJobService extends JobService implements ExecutionListener {
    private static final String TAG = null;
    private final Map<WorkGenerationalId, JobParameters> mJobParameters;
    private final StartStopTokens mStartStopTokens;
    private WorkLauncher mWorkLauncher;
    private WorkManagerImpl mWorkManagerImpl;

    /* loaded from: classes4.dex */
    public static class Api24Impl {
        public static String[] getTriggeredContentAuthorities(JobParameters r0) {
            return r0.getTriggeredContentAuthorities();
        }

        public static Uri[] getTriggeredContentUris(JobParameters r0) {
            return r0.getTriggeredContentUris();
        }
    }

    /* loaded from: classes4.dex */
    public static class Api28Impl {
        public static Network getNetwork(JobParameters r0) {
            return r0.getNetwork();
        }
    }

    /* loaded from: classes4.dex */
    public static class Api31Impl {
        public static int getStopReason(JobParameters r0) {
            return SystemJobService.stopReason(r0.getStopReason());
        }
    }

    static {
        TAG = Logger.tagWithPrefix("SystemJobService");
    }

    public SystemJobService() {
        this.mJobParameters = new HashMap();
        this.mStartStopTokens = StartStopTokens.create(false);
    }

    private static void assertMainThread(String r2) {
        if (Looper.getMainLooper().getThread() != Thread.currentThread()) goto L5;
        return;
    L5:
        LiveData$$ExternalSyntheticBUOutline0.m("Cannot invoke ", r2, " on a background thread");
    }

    public static int stopReason(int r0) {
        switch(r0) {
            case 0: goto L5;
            case 1: goto L5;
            case 2: goto L5;
            case 3: goto L5;
            case 4: goto L5;
            case 5: goto L5;
            case 6: goto L5;
            case 7: goto L5;
            case 8: goto L5;
            case 9: goto L5;
            case 10: goto L5;
            case 11: goto L5;
            case 12: goto L5;
            case 13: goto L5;
            case 14: goto L5;
            case 15: goto L5;
            default: goto L3;
        };
    L3:
        return -512;
    L5:
        return r0;
    }

    private static WorkGenerationalId workGenerationalIdFromJobParameters(JobParameters r3) {
        PersistableBundle r32 = r3.getExtras();     // Catch: NullPointerException -> L11
        if (r32 != null) goto L6;
        return null;
    L6:
        if (r32.containsKey("EXTRA_WORK_SPEC_ID") == false) goto L14;
        return new WorkGenerationalId(r32.getString("EXTRA_WORK_SPEC_ID"), r32.getInt("EXTRA_WORK_SPEC_GENERATION"));
    L14:
        return null;
    L15:
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        WorkManagerImpl r0 = WorkManagerImpl.getInstance(getApplicationContext());     // Catch: IllegalStateException -> L5
        this.mWorkManagerImpl = r0;     // Catch: IllegalStateException -> L5
        Processor r02 = r0.getProcessor();     // Catch: IllegalStateException -> L5
        this.mWorkLauncher = new WorkLauncherImpl(r02, this.mWorkManagerImpl.getWorkTaskExecutor());     // Catch: IllegalStateException -> L5
        r02.addExecutionListener(this);     // Catch: IllegalStateException -> L5
        return;
    L5:
        e = move-exception;
        if (Application.class.equals(getApplication().getClass()) == false) goto L10;
        Logger.get().warning(TAG, "Could not find WorkManager instance; this may be because an auto-backup is in progress. Ignoring JobScheduler commands for now. Please make sure that you are initializing WorkManager if you have manually disabled WorkManagerInitializer.");
        return;
    L10:
        Constants$$ExternalSyntheticBUOutline0.m("WorkManager needs to be initialized via a ContentProvider#onCreate() or an Application#onCreate().", e);
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        WorkManagerImpl r0 = this.mWorkManagerImpl;
        if (r0 == null) goto L6;
        r0.getProcessor().removeExecutionListener(this);
        return;
    }

    @Override // androidx.work.impl.ExecutionListener
    public void onExecuted(WorkGenerationalId r5, boolean r6) {
        assertMainThread("onExecuted");
        Logger.get().debug(TAG, r5.getWorkSpecId() + " executed on JobScheduler");
        JobParameters r0 = this.mJobParameters.remove(r5);
        this.mStartStopTokens.remove(r5);
        if (r0 == null) goto L6;
        jobFinished(r0, r6);
        return;
    }

    @Override // android.app.job.JobService
    public boolean onStartJob(JobParameters r7) {
        assertMainThread("onStartJob");
        if (this.mWorkManagerImpl != null) goto L6;
        Logger.get().debug(TAG, "WorkManager is not initialized; requesting retry.");
        jobFinished(r7, true);
        return false;
    L6:
        WorkGenerationalId r0 = workGenerationalIdFromJobParameters(r7);
        if (r0 != null) goto L11;
        Logger.get().error(TAG, "WorkSpec id not found!");
        return false;
    L11:
        if (this.mJobParameters.containsKey(r0) == false) goto L14;
        Logger.get().debug(TAG, "Job is already being executed by SystemJobService: " + r0);
        return false;
    L14:
        Logger.get().debug(TAG, "onStartJob for " + r0);
        this.mJobParameters.put(r0, r7);
        WorkerParameters.RuntimeExtras r2 = new WorkerParameters.RuntimeExtras();
        if (Api24Impl.getTriggeredContentUris(r7) == null) goto L18;
        r2.triggeredContentUris = Arrays.asList(Api24Impl.getTriggeredContentUris(r7));
    L18:
        if (Api24Impl.getTriggeredContentAuthorities(r7) == null) goto L21;
        r2.triggeredContentAuthorities = Arrays.asList(Api24Impl.getTriggeredContentAuthorities(r7));
    L21:
        if (Build.VERSION.SDK_INT < 28) goto L23;
        r2.network = Api28Impl.getNetwork(r7);
    L23:
        this.mWorkLauncher.startWork(this.mStartStopTokens.tokenFor(r0), r2);
        return true;
    }

    @Override // android.app.job.JobService
    public boolean onStopJob(JobParameters r7) {
        assertMainThread("onStopJob");
        if (this.mWorkManagerImpl != null) goto L6;
        Logger.get().debug(TAG, "WorkManager is not initialized; requesting retry.");
        return true;
    L6:
        WorkGenerationalId r0 = workGenerationalIdFromJobParameters(r7);
        if (r0 != null) goto L10;
        Logger.get().error(TAG, "WorkSpec id not found!");
        return false;
    L10:
        Logger.get().debug(TAG, "onStopJob for " + r0);
        this.mJobParameters.remove(r0);
        StartStopToken r2 = this.mStartStopTokens.remove(r0);
        if (r2 == null) goto L18;
        if (Build.VERSION.SDK_INT < 31) goto L15;
        int r72 = Api31Impl.getStopReason(r7);
    L16:
        this.mWorkLauncher.stopWorkWithReason(r2, r72);
        goto L18
    L15:
        r72 = -512;
    L18:
        return !this.mWorkManagerImpl.getProcessor().isCancelled(r0.getWorkSpecId());
    }
}
