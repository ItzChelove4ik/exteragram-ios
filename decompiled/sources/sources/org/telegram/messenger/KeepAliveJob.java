package org.telegram.messenger;

import android.content.Intent;
import java.util.concurrent.CountDownLatch;
import org.telegram.messenger.support.JobIntentService;

/* loaded from: classes.dex */
public class KeepAliveJob extends JobIntentService {
    private static volatile CountDownLatch countDownLatch;
    private static Runnable finishJobByTimeoutRunnable;
    private static volatile boolean startingJob;
    private static final Object sync = null;

    public static /* synthetic */ void $r8$lambda$KunmSAUOG7za9CVFvrm3r_2vjUE() {
        if (startingJob == false) goto L5;
        return;
    L5:
        if (countDownLatch != null) goto L25;
        if (BuildVars.LOGS_ENABLED == false) goto L10;
        FileLog.d("starting keep-alive job");     // Catch: Exception -> L21
    L10:
        Object r0 = sync;     // Catch: Exception -> L21
        monitor-enter(r0);     // Catch: Exception -> L21
        startingJob = true;     // Catch: Throwable -> L17
        monitor-exit(r0);     // Catch: Throwable -> L17
        JobIntentService.enqueueWork(ApplicationLoader.applicationContext, KeepAliveJob.class, 1000, new Intent());     // Catch: Exception -> L21
        return;
    L17:
        th = move-exception;
        throw th;     // Catch: Exception -> L21
    L26:
        return;
    }

    public static /* synthetic */ void $r8$lambda$R3Qm7rjCixvSkJ9CuBP_gyavRqA() {
        finishJobInternal();
    }

    static {
        sync = new Object();
        finishJobByTimeoutRunnable = new KeepAliveJob$$ExternalSyntheticLambda0();
    }

    public KeepAliveJob() {
    }

    public static void finishJob() {
        Utilities.globalQueue.postRunnable(new KeepAliveJob$$ExternalSyntheticLambda0());
    }

    private static void finishJobInternal() {
        Object r0 = sync;
        monitor-enter(r0);
    L9:
        th = move-exception;
        throw th;
    L5:
        if (countDownLatch == null) goto L13;
        if (BuildVars.LOGS_ENABLED == false) goto L11;
        FileLog.d("finish keep-alive job");     // Catch: Throwable -> L9
    L11:
        countDownLatch.countDown();     // Catch: Throwable -> L9
    L13:
        if (startingJob == true) goto L15;
    L18:
        monitor-exit(r0);     // Catch: Throwable -> L9
        return;
    L15:
        if (BuildVars.LOGS_ENABLED == false) goto L17;
        FileLog.d("finish queued keep-alive job");     // Catch: Throwable -> L9
    L17:
        startingJob = false;     // Catch: Throwable -> L9
        goto L18
    }

    public static void startJob() {
        Utilities.globalQueue.postRunnable(new KeepAliveJob$$ExternalSyntheticLambda1());
    }

    @Override // org.telegram.messenger.support.JobIntentService
    public void onHandleWork(Intent r3) {
        Object r2 = sync;
        monitor-enter(r2);
    L8:
        th = move-exception;
        throw th;
    L5:
        if (startingJob == true) goto L10;
        monitor-exit(r2);     // Catch: Throwable -> L8
        return;
    L10:
        countDownLatch = new CountDownLatch(1);     // Catch: Throwable -> L8
        monitor-exit(r2);     // Catch: Throwable -> L8
        if (BuildVars.LOGS_ENABLED == false) goto L15;
        FileLog.d("started keep-alive job");
    L15:
        Utilities.globalQueue.postRunnable(finishJobByTimeoutRunnable, 60000);
        countDownLatch.await();     // Catch: Throwable -> L31
    L17:
        Utilities.globalQueue.cancelRunnable(finishJobByTimeoutRunnable);
        Object r32 = sync;
        monitor-enter(r32);
        countDownLatch = null;     // Catch: Throwable -> L26
        monitor-exit(r32);     // Catch: Throwable -> L26
        if (BuildVars.LOGS_ENABLED == false) goto L37;
        FileLog.d("ended keep-alive job");
        return;
    L37:
        return;
    L26:
        th = move-exception;
        throw th;
    }
}
