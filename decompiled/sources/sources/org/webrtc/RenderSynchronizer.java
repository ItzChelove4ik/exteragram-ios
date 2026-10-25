package org.webrtc;

import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Trace;
import android.view.Choreographer;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes6.dex */
public final class RenderSynchronizer {
    private static final float DEFAULT_TARGET_FPS = 30.0f;
    private static final String TAG = "RenderSynchronizer";
    private Choreographer choreographer;
    private boolean isListening;
    private long lastOpenedTimeNanos;
    private long lastRefreshTimeNanos;
    private final List<Listener> listeners;
    private final Object lock;
    private final Handler mainThreadHandler;
    private boolean renderWindowOpen;
    private final long targetFrameIntervalNanos;

    public interface Listener {
        void onRenderWindowClose();

        void onRenderWindowOpen();
    }

    public static /* synthetic */ void $r8$lambda$Kwx0GunH1gKAWBC69oYNNB3YFqk(RenderSynchronizer r0) {
        r0.lambda$registerListener$1();
    }

    public static /* synthetic */ void $r8$lambda$YXRGWXOjxocuAsugNaBTrqKEVnE(RenderSynchronizer r0, long r1) {
        r0.onDisplayRefreshCycleBegin(r1);
    }

    /* renamed from: $r8$lambda$tKSA8-mYxI36MBEVuSV3vy1-58I, reason: not valid java name */
    public static /* synthetic */ void m22374$r8$lambda$tKSA8mYxI36MBEVuSV3vy158I(RenderSynchronizer r0) {
        r0.lambda$new$0();
    }

    public RenderSynchronizer() {
        this(DEFAULT_TARGET_FPS);
    }

    public RenderSynchronizer(float r3) {
        this.lock = new Object();
        this.listeners = new CopyOnWriteArrayList();
        this.targetFrameIntervalNanos = Math.round(1.0E9f / r3);
        Handler r32 = new Handler(Looper.getMainLooper());
        this.mainThreadHandler = r32;
        r32.post(new RenderSynchronizer$$ExternalSyntheticLambda2(this));
        Logging.d(TAG, "Created");
    }

    private void closeRenderWindow() {
        this.renderWindowOpen = false;
        traceRenderWindowChange();
        Iterator<Listener> r1 = this.listeners.iterator();
    L4:
        if (r1.hasNext() == false) goto L6;
        r1.next().onRenderWindowClose();
        goto L4
    }

    private /* synthetic */ void lambda$new$0() {
        this.choreographer = Choreographer.getInstance();
    }

    private /* synthetic */ void lambda$registerListener$1() {
        this.choreographer.postFrameCallback(new RenderSynchronizer$$ExternalSyntheticLambda1(this));
    }

    private void onDisplayRefreshCycleBegin(long r9) {
        Object r0 = this.lock;
        monitor-enter(r0);
    L9:
        th = move-exception;
        throw th;
    L5:
        if (this.listeners.isEmpty() == false) goto L11;
        Logging.d(TAG, "No listeners, unsubscribing to frame callbacks");     // Catch: Throwable -> L9
        this.isListening = false;     // Catch: Throwable -> L9
        monitor-exit(r0);     // Catch: Throwable -> L9
        return;
    L11:
        monitor-exit(r0);     // Catch: Throwable -> L9
        this.choreographer.postFrameCallback(new RenderSynchronizer$$ExternalSyntheticLambda1(this));
        long r02 = r9 - this.lastOpenedTimeNanos;
        long r2 = r9 - this.lastRefreshTimeNanos;
        this.lastRefreshTimeNanos = r9;
        if (Math.abs(r02 - this.targetFrameIntervalNanos) >= Math.abs((r02 - this.targetFrameIntervalNanos) + r2)) goto L17;
        this.lastOpenedTimeNanos = r9;
        openRenderWindow();
        return;
    L17:
        if (this.renderWindowOpen == false) goto L23;
        closeRenderWindow();
        return;
    }

    private void openRenderWindow() {
        this.renderWindowOpen = true;
        traceRenderWindowChange();
        Iterator<Listener> r1 = this.listeners.iterator();
    L4:
        if (r1.hasNext() == false) goto L6;
        r1.next().onRenderWindowOpen();
        goto L4
    }

    private void traceRenderWindowChange() {
        if (Build.VERSION.SDK_INT >= 29) goto L5;
        return;
    L5:
        if (this.renderWindowOpen == false) goto L7;
        long r0 = 1;
    L8:
        Trace.setCounter("RenderWindow", r0);
        return;
    L7:
        r0 = 0;
        goto L8
    }

    public void registerListener(Listener r3) {
        this.listeners.add(r3);
        Object r32 = this.lock;
        monitor-enter(r32);
    L7:
        th = move-exception;
        throw th;
    L5:
        if (this.isListening == true) goto L9;
        Logging.d(TAG, "First listener, subscribing to frame callbacks");     // Catch: Throwable -> L7
        this.isListening = true;     // Catch: Throwable -> L7
        this.mainThreadHandler.post(new RenderSynchronizer$$ExternalSyntheticLambda0(this));     // Catch: Throwable -> L7
    L9:
        monitor-exit(r32);     // Catch: Throwable -> L7
    }

    public void removeListener(Listener r1) {
        this.listeners.remove(r1);
    }
}
