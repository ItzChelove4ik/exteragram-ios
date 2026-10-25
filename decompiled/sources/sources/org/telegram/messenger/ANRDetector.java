package org.telegram.messenger;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import org.telegram.ui.Components.ForegroundDetector;

/* loaded from: classes5.dex */
public class ANRDetector implements ForegroundDetector.Listener {
    private static final int MSG_UI_PING = 1;
    private static final long TIMEOUT_MS = 5000;
    private volatile int acknowledgedPingId;
    private final Runnable anrDetected;
    private volatile boolean anrReported;
    private volatile boolean destroyed;
    private final Thread detectorThread;
    private volatile boolean foreground;
    private volatile int generation;
    private final Object lock;
    private final Handler mainHandler;
    private int nextPingId;

    /* renamed from: org.telegram.messenger.ANRDetector$1, reason: invalid class name */
    public class AnonymousClass1 extends Handler {
        final /* synthetic */ ANRDetector this$0;

        public AnonymousClass1(ANRDetector r1, Looper r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.os.Handler
        public void handleMessage(Message r3) {
            if (r3.what == 1) goto L5;
            return;
        L5:
            ANRDetector.m3867$$Nest$fputacknowledgedPingId(this.this$0, r3.arg1);
            ANRDetector.m3868$$Nest$fputanrReported(this.this$0, false);
        }
    }

    /* renamed from: $r8$lambda$wO_T_DBEhQNARi5EN7AjF0Dd1-Q, reason: not valid java name */
    public static /* synthetic */ void m3866$r8$lambda$wO_T_DBEhQNARi5EN7AjF0Dd1Q(ANRDetector r0) {
        r0.run();
    }

    /* renamed from: -$$Nest$fputacknowledgedPingId, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m3867$$Nest$fputacknowledgedPingId(ANRDetector r0, int r1) {
        r0.acknowledgedPingId = r1;
    }

    /* renamed from: -$$Nest$fputanrReported, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m3868$$Nest$fputanrReported(ANRDetector r0, boolean r1) {
        r0.anrReported = r1;
    }

    public ANRDetector(Runnable r3) {
        this.lock = new Object();
        this.acknowledgedPingId = -1;
        this.anrDetected = r3;
        this.mainHandler = new AnonymousClass1(this, Looper.getMainLooper());
        ForegroundDetector r32 = ForegroundDetector.getInstance();
        this.foreground = r32.isForeground();
        r32.addListener(this);
        Thread r33 = new Thread(new ANRDetector$$ExternalSyntheticLambda0(this), "ANRDetector");
        this.detectorThread = r33;
        r33.start();
    }

    private void run() {
    L2:
        Object r0 = this.lock;
        monitor-enter(r0);
    L42:
    L10:
        th = move-exception;
        throw th;
    L5:
        if (this.foreground == true) goto L13;
        if (this.destroyed == true) goto L13;
        this.lock.wait();     // Catch: InterruptedException -> L41 Throwable -> L10
    L13:
        if (this.destroyed == true) goto L14;
        int r1 = this.generation;     // Catch: Throwable -> L10
        int r2 = this.nextPingId + 1;     // Catch: Throwable -> L10
        this.nextPingId = r2;     // Catch: Throwable -> L10
        monitor-exit(r0);     // Catch: Throwable -> L10
        this.mainHandler.obtainMessage(1, r2, r1).sendToTarget();
        Thread.sleep(TIMEOUT_MS);     // Catch: InterruptedException -> L40
        if (this.destroyed == true) goto L68;
        if (this.foreground == false) goto L2;
        if (this.generation != r1) goto L2;
        if (this.acknowledgedPingId == r2) goto L2;
        if (this.anrReported == true) goto L2;
        this.anrReported = true;
        this.anrDetected.run();     // Catch: Throwable -> L36
    L36:
        th = move-exception;
        FileLog.e(th);
        goto L2
    L68:
        return;
    L14:
        monitor-exit(r0);     // Catch: Throwable -> L10
    }

    public void destroy() {
        Object r0 = this.lock;
        monitor-enter(r0);
    L8:
        th = move-exception;
        throw th;
    L5:
        if (this.destroyed == false) goto L10;
        monitor-exit(r0);     // Catch: Throwable -> L8
        return;
    L10:
        this.destroyed = true;     // Catch: Throwable -> L8
        this.foreground = false;     // Catch: Throwable -> L8
        this.generation++;
        this.lock.notifyAll();     // Catch: Throwable -> L8
        monitor-exit(r0);     // Catch: Throwable -> L8
        ForegroundDetector.getInstance().removeListener(this);
        this.mainHandler.removeMessages(1);
        this.detectorThread.interrupt();
    }

    @Override // org.telegram.ui.Components.ForegroundDetector.Listener
    public void onBecameBackground() {
        Object r0 = this.lock;
        monitor-enter(r0);
    L8:
        th = move-exception;
        throw th;
    L5:
        if (this.destroyed == false) goto L10;
        monitor-exit(r0);     // Catch: Throwable -> L8
        return;
    L10:
        this.generation++;
        this.foreground = false;     // Catch: Throwable -> L8
        monitor-exit(r0);     // Catch: Throwable -> L8
        this.mainHandler.removeMessages(1);
        this.detectorThread.interrupt();
    }

    @Override // org.telegram.ui.Components.ForegroundDetector.Listener
    public void onBecameForeground() {
        Object r0 = this.lock;
        monitor-enter(r0);
    L8:
        th = move-exception;
        throw th;
    L5:
        if (this.destroyed == false) goto L10;
        monitor-exit(r0);     // Catch: Throwable -> L8
        return;
    L10:
        this.generation++;
        this.foreground = true;     // Catch: Throwable -> L8
        this.anrReported = false;     // Catch: Throwable -> L8
        this.lock.notifyAll();     // Catch: Throwable -> L8
        monitor-exit(r0);     // Catch: Throwable -> L8
        this.detectorThread.interrupt();
    }
}
