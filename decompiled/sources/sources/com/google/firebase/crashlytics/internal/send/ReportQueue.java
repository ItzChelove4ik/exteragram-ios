package com.google.firebase.crashlytics.internal.send;

import android.annotation.SuppressLint;
import android.os.SystemClock;
import com.google.android.datatransport.Event;
import com.google.android.datatransport.Priority;
import com.google.android.datatransport.Transport;
import com.google.android.datatransport.runtime.ForcedSender;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.crashlytics.internal.Logger;
import com.google.firebase.crashlytics.internal.common.CrashlyticsReportWithSessionId;
import com.google.firebase.crashlytics.internal.common.OnDemandCounter;
import com.google.firebase.crashlytics.internal.common.Utils;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;
import com.google.firebase.crashlytics.internal.settings.Settings;
import java.util.Locale;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: classes.dex */
final class ReportQueue {
    private final double base;
    private long lastUpdatedMs;
    private final OnDemandCounter onDemandCounter;
    private final BlockingQueue<Runnable> queue;
    private final int queueCapacity;
    private final double ratePerMinute;
    private final ThreadPoolExecutor singleThreadExecutor;
    private final long startTimeMs;
    private int step;
    private final long stepDurationMs;
    private final Transport<CrashlyticsReport> transport;

    /* renamed from: com.google.firebase.crashlytics.internal.send.ReportQueue$1, reason: invalid class name */
    /* loaded from: classes5.dex */
    public static /* synthetic */ class AnonymousClass1 {
    }

    /* loaded from: classes5.dex */
    public final class ReportRunnable implements Runnable {
        private final CrashlyticsReportWithSessionId reportWithSessionId;
        private final TaskCompletionSource<CrashlyticsReportWithSessionId> tcs;
        final /* synthetic */ ReportQueue this$0;

        private ReportRunnable(ReportQueue r1, CrashlyticsReportWithSessionId r2, TaskCompletionSource<CrashlyticsReportWithSessionId> r3) {
            this.this$0 = r1;
            this.reportWithSessionId = r2;
            this.tcs = r3;
        }

        public /* synthetic */ ReportRunnable(ReportQueue r1, CrashlyticsReportWithSessionId r2, TaskCompletionSource r3, AnonymousClass1 r4) {
            this(r1, r2, r3);
        }

        @Override // java.lang.Runnable
        public void run() {
            ReportQueue.access$100(this.this$0, this.reportWithSessionId, this.tcs);
            ReportQueue.access$200(this.this$0).resetDroppedOnDemandExceptions();
            double r0 = ReportQueue.access$300(this.this$0);
            Logger.getLogger().d("Delay for: " + String.format(Locale.US, "%.2f", new Object[]{Double.valueOf(r0 / 1000.0d)}) + " s for report: " + this.reportWithSessionId.getSessionId());
            ReportQueue.access$400(r0);
        }
    }

    public static /* synthetic */ void $r8$lambda$4lFm4kq1MZXZfcwIBR3zscs57No(ReportQueue r0, TaskCompletionSource r1, boolean r2, CrashlyticsReportWithSessionId r3, Exception r4) {
        r0.getClass();
        if (r4 == null) goto L6;
        r1.trySetException(r4);
        return;
    L6:
        if (r2 == false) goto L8;
        r0.flushScheduledReportsIfAble();
    L8:
        r1.trySetResult(r3);
    }

    public static /* synthetic */ void $r8$lambda$8U76pxOta423qVuKYJ0HhfFLxJ4(ReportQueue r1, CountDownLatch r2) {
        r1.getClass();
        ForcedSender.sendBlocking(r1.transport, Priority.HIGHEST);     // Catch: Exception -> L6
    L4:
        r2.countDown();
    }

    @SuppressLint({"ThreadPoolCreation"})
    public ReportQueue(double r1, double r3, long r5, Transport<CrashlyticsReport> r7, OnDemandCounter r8) {
        this.ratePerMinute = r1;
        this.base = r3;
        this.stepDurationMs = r5;
        this.transport = r7;
        this.onDemandCounter = r8;
        this.startTimeMs = SystemClock.elapsedRealtime();
        int r12 = (int) r1;
        this.queueCapacity = r12;
        ArrayBlockingQueue r82 = new ArrayBlockingQueue(r12);
        this.queue = r82;
        this.singleThreadExecutor = new ThreadPoolExecutor(1, 1, 0, TimeUnit.MILLISECONDS, r82);
        this.step = 0;
        this.lastUpdatedMs = 0;
    }

    public ReportQueue(Transport<CrashlyticsReport> r10, Settings r11, OnDemandCounter r12) {
        this(r11.onDemandUploadRatePerMinute, r11.onDemandBackoffBase, r11.onDemandBackoffStepDurationSeconds * 1000, r10, r12);
    }

    public static /* synthetic */ void access$100(ReportQueue r0, CrashlyticsReportWithSessionId r1, TaskCompletionSource r2) {
        r0.sendReport(r1, r2);
    }

    public static /* synthetic */ OnDemandCounter access$200(ReportQueue r0) {
        return r0.onDemandCounter;
    }

    public static /* synthetic */ double access$300(ReportQueue r2) {
        return r2.calcDelay();
    }

    public static /* synthetic */ void access$400(double r0) {
        sleep(r0);
    }

    private double calcDelay() {
        return Math.min(3600000.0d, (60000.0d / this.ratePerMinute) * Math.pow(this.base, calcStep()));
    }

    private int calcStep() {
        if (this.lastUpdatedMs != 0) goto L5;
        this.lastUpdatedMs = now();
    L5:
        int r0 = (int) ((now() - this.lastUpdatedMs) / this.stepDurationMs);
        boolean r1 = isQueueFull();
        int r2 = this.step;
        if (r1 == false) goto L8;
        int r02 = Math.min(100, r2 + r0);
    L10:
        if (this.step == r02) goto L12;
        this.step = r02;
        this.lastUpdatedMs = now();
    L12:
        return r02;
    L8:
        r02 = Math.max(0, r2 - r0);
        goto L10
    }

    private boolean isQueueAvailable() {
        if (this.queue.size() >= this.queueCapacity) goto L6;
        return true;
    L6:
        return false;
    }

    private boolean isQueueFull() {
        if (this.queue.size() != this.queueCapacity) goto L6;
        return true;
    L6:
        return false;
    }

    private long now() {
        return System.currentTimeMillis();
    }

    private void sendReport(CrashlyticsReportWithSessionId r5, TaskCompletionSource<CrashlyticsReportWithSessionId> r6) {
        Logger.getLogger().d("Sending report through Google DataTransport: " + r5.getSessionId());
        if ((SystemClock.elapsedRealtime() - this.startTimeMs) >= 2000) goto L5;
        boolean r0 = true;
    L6:
        this.transport.schedule(Event.ofUrgent(r5.getReport()), new ReportQueue$$ExternalSyntheticLambda0(this, r6, r0, r5));
        return;
    L5:
        r0 = false;
        goto L6
    }

    private static void sleep(double r0) {
        Thread.sleep((long) r0);     // Catch: InterruptedException -> L5
        return;
    }

    public TaskCompletionSource<CrashlyticsReportWithSessionId> enqueueReport(CrashlyticsReportWithSessionId r7, boolean r8) {
        BlockingQueue<Runnable> r4 = this.queue;
        monitor-enter(r4);
        TaskCompletionSource<CrashlyticsReportWithSessionId> r5 = new TaskCompletionSource();     // Catch: Throwable -> L11
        if (r8 == false) goto L16;
        this.onDemandCounter.incrementRecordedOnDemandExceptions();     // Catch: Throwable -> L11
        if (isQueueAvailable() == false) goto L13;
        Logger.getLogger().d("Enqueueing report: " + r7.getSessionId());     // Catch: Throwable -> L11
        Logger.getLogger().d("Queue size: " + this.queue.size());     // Catch: Throwable -> L11
        this.singleThreadExecutor.execute(new ReportRunnable(this, r7, r5, null));     // Catch: Throwable -> L11
        Logger.getLogger().d("Closing task for report: " + r7.getSessionId());     // Catch: Throwable -> L11
        r5.trySetResult(r7);     // Catch: Throwable -> L11
        monitor-exit(r4);     // Catch: Throwable -> L11
        return r5;
    L13:
        calcStep();     // Catch: Throwable -> L11
        Logger.getLogger().d("Dropping report due to queue being full: " + r7.getSessionId());     // Catch: Throwable -> L11
        this.onDemandCounter.incrementDroppedOnDemandExceptions();     // Catch: Throwable -> L11
        r5.trySetResult(r7);     // Catch: Throwable -> L11
        monitor-exit(r4);     // Catch: Throwable -> L11
        return r5;
    L16:
        sendReport(r7, r5);     // Catch: Throwable -> L11
        monitor-exit(r4);     // Catch: Throwable -> L11
        return r5;
    L11:
        th = move-exception;
        throw th;
    }

    @SuppressLint({"DiscouragedApi", "ThreadPoolCreation"})
    public void flushScheduledReportsIfAble() {
        CountDownLatch r0 = new CountDownLatch(1);
        new Thread(new ReportQueue$$ExternalSyntheticLambda1(this, r0)).start();
        Utils.awaitUninterruptibly(r0, 2, TimeUnit.SECONDS);
    }
}
