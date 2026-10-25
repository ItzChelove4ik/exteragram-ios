package org.webrtc;

import android.view.SurfaceHolder;
import java.util.concurrent.CountDownLatch;
import org.telegram.ui.ActionBar.Theme$$ExternalSyntheticLambda9;
import org.webrtc.EglBase;
import org.webrtc.RendererCommon;

/* loaded from: classes6.dex */
public class SurfaceEglRenderer extends EglRenderer implements SurfaceHolder.Callback {
    private static final String TAG = "SurfaceEglRenderer";
    private int frameRotation;
    private boolean isFirstFrameRendered;
    private boolean isRenderingPaused;
    private final Object layoutLock;
    private RendererCommon.RendererEvents rendererEvents;
    private int rotatedFrameHeight;
    private int rotatedFrameWidth;

    public SurfaceEglRenderer(String r1) {
        super(r1);
        this.layoutLock = new Object();
    }

    private void logD(String r2) {
        Logging.d(TAG, this.name + ": " + r2);
    }

    private void updateFrameDimensionsAndReportEvents(VideoFrame r6) {
        Object r1 = this.layoutLock;
        monitor-enter(r1);
    L8:
        th = move-exception;
        throw th;
    L5:
        if (this.isRenderingPaused == false) goto L11;
        monitor-exit(r1);     // Catch: Throwable -> L8
        return;
    L11:
        if (this.isFirstFrameRendered == true) goto L16;
        this.isFirstFrameRendered = true;     // Catch: Throwable -> L8
        logD("Reporting first rendered frame.");     // Catch: Throwable -> L8
        RendererCommon.RendererEvents r2 = this.rendererEvents;     // Catch: Throwable -> L8
        if (r2 == null) goto L16;
        r2.onFirstFrameRendered();     // Catch: Throwable -> L8
    L16:
        if (this.rotatedFrameWidth == r6.getRotatedWidth()) goto L18;
    L21:
        logD("Reporting frame resolution changed to " + r6.getBuffer().getWidth() + "x" + r6.getBuffer().getHeight() + " with rotation " + r6.getRotation());     // Catch: Throwable -> L8
        RendererCommon.RendererEvents r0 = this.rendererEvents;     // Catch: Throwable -> L8
        if (r0 == null) goto L24;
        r0.onFrameResolutionChanged(r6.getBuffer().getWidth(), r6.getBuffer().getHeight(), r6.getRotation());     // Catch: Throwable -> L8
    L24:
        this.rotatedFrameWidth = r6.getRotatedWidth();     // Catch: Throwable -> L8
        this.rotatedFrameHeight = r6.getRotatedHeight();     // Catch: Throwable -> L8
        this.frameRotation = r6.getRotation();     // Catch: Throwable -> L8
    L25:
        monitor-exit(r1);     // Catch: Throwable -> L8
        return;
    L18:
        if (this.rotatedFrameHeight != r6.getRotatedHeight()) goto L21;
        if (this.frameRotation == r6.getRotation()) goto L25;
        goto L21
    }

    @Override // org.webrtc.EglRenderer
    public void disableFpsReduction() {
        Object r0 = this.layoutLock;
        monitor-enter(r0);
        this.isRenderingPaused = false;     // Catch: Throwable -> L9
        monitor-exit(r0);     // Catch: Throwable -> L9
        super.disableFpsReduction();
        return;
    L9:
        th = move-exception;
        throw th;
    }

    public void init(EglBase.Context r2, RendererCommon.RendererEvents r3, int[] r4, RendererCommon.GlDrawer r5) {
        ThreadUtils.checkIsOnMainThread();
        this.rendererEvents = r3;
        Object r32 = this.layoutLock;
        monitor-enter(r32);
        this.isFirstFrameRendered = false;     // Catch: Throwable -> L9
        this.rotatedFrameWidth = 0;     // Catch: Throwable -> L9
        this.rotatedFrameHeight = 0;     // Catch: Throwable -> L9
        this.frameRotation = 0;     // Catch: Throwable -> L9
        monitor-exit(r32);     // Catch: Throwable -> L9
        super.init(r2, r4, r5);
        return;
    L9:
        th = move-exception;
        throw th;
    }

    @Override // org.webrtc.EglRenderer
    public void init(EglBase.Context r2, int[] r3, RendererCommon.GlDrawer r4) {
        init(r2, null, r3, r4);
    }

    @Override // org.webrtc.EglRenderer, org.webrtc.VideoSink
    public void onFrame(VideoFrame r1) {
        updateFrameDimensionsAndReportEvents(r1);
        super.onFrame(r1);
    }

    @Override // org.webrtc.EglRenderer
    public void pauseVideo() {
        Object r0 = this.layoutLock;
        monitor-enter(r0);
        this.isRenderingPaused = true;     // Catch: Throwable -> L9
        monitor-exit(r0);     // Catch: Throwable -> L9
        super.pauseVideo();
        return;
    L9:
        th = move-exception;
        throw th;
    }

    @Override // org.webrtc.EglRenderer
    public void setFpsReduction(float r3) {
        Object r0 = this.layoutLock;
        monitor-enter(r0);
        if (r3 != 0.0f) goto L7;
        boolean r1 = true;
    L15:
        this.isRenderingPaused = r1;     // Catch: Throwable -> L12
        monitor-exit(r0);     // Catch: Throwable -> L12
        super.setFpsReduction(r3);
        return;
    L12:
        th = move-exception;
        throw th;
    L7:
        r1 = false;
        goto L15
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceChanged(SurfaceHolder r2, int r3, int r4, int r5) {
        ThreadUtils.checkIsOnMainThread();
        logD("surfaceChanged: format: " + r3 + " size: " + r4 + "x" + r5);
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceCreated(SurfaceHolder r1) {
        ThreadUtils.checkIsOnMainThread();
        createEglSurface(r1.getSurface());
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceDestroyed(SurfaceHolder r3) {
        ThreadUtils.checkIsOnMainThread();
        CountDownLatch r32 = new CountDownLatch(1);
        releaseEglSurface(new Theme$$ExternalSyntheticLambda9(r32), false);
        ThreadUtils.awaitUninterruptibly(r32);
    }
}
