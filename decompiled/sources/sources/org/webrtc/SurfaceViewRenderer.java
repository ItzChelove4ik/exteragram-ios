package org.webrtc;

import android.content.Context;
import android.graphics.Point;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import okhttp3.internal.url._UrlKt;
import org.webrtc.EglBase;
import org.webrtc.EglRenderer;
import org.webrtc.RendererCommon;

/* loaded from: classes6.dex */
public class SurfaceViewRenderer extends SurfaceView implements SurfaceHolder.Callback, VideoSink, RendererCommon.RendererEvents {
    private static final String TAG = "SurfaceViewRenderer";
    private final SurfaceEglRenderer eglRenderer;
    private boolean enableFixedSize;
    private RendererCommon.RendererEvents rendererEvents;
    private final String resourceName;
    private int rotatedFrameHeight;
    private int rotatedFrameWidth;
    private int surfaceHeight;
    private int surfaceWidth;
    private final RendererCommon.VideoLayoutMeasure videoLayoutMeasure;

    /* renamed from: $r8$lambda$XoEI0guvz9piuvUfW-Ihjj8k7kE, reason: not valid java name */
    public static /* synthetic */ void m22388$r8$lambda$XoEI0guvz9piuvUfWIhjj8k7kE(SurfaceViewRenderer r0, int r1, int r2) {
        r0.lambda$onFrameResolutionChanged$0(r1, r2);
    }

    public SurfaceViewRenderer(Context r2) {
        super(r2);
        this.videoLayoutMeasure = new RendererCommon.VideoLayoutMeasure();
        String r22 = getResourceName();
        this.resourceName = r22;
        SurfaceEglRenderer r0 = new SurfaceEglRenderer(r22);
        this.eglRenderer = r0;
        getHolder().addCallback(this);
        getHolder().addCallback(r0);
    }

    public SurfaceViewRenderer(Context r1, AttributeSet r2) {
        super(r1, r2);
        this.videoLayoutMeasure = new RendererCommon.VideoLayoutMeasure();
        String r12 = getResourceName();
        this.resourceName = r12;
        SurfaceEglRenderer r22 = new SurfaceEglRenderer(r12);
        this.eglRenderer = r22;
        getHolder().addCallback(this);
        getHolder().addCallback(r22);
    }

    private String getResourceName() {
        return getResources().getResourceEntryName(getId());
    L4:
        return _UrlKt.FRAGMENT_ENCODE_SET;
    }

    private /* synthetic */ void lambda$onFrameResolutionChanged$0(int r1, int r2) {
        this.rotatedFrameWidth = r1;
        this.rotatedFrameHeight = r2;
        updateSurfaceSize();
        requestLayout();
    }

    private void logD(String r2) {
        Logging.d(TAG, this.resourceName + ": " + r2);
    }

    private void postOrRun(Runnable r3) {
        if (Thread.currentThread() != Looper.getMainLooper().getThread()) goto L6;
        r3.run();
        return;
    L6:
        post(r3);
    }

    private void updateSurfaceSize() {
        ThreadUtils.checkIsOnMainThread();
        if (this.enableFixedSize == true) goto L5;
    L24:
        this.surfaceHeight = 0;
        this.surfaceWidth = 0;
        getHolder().setSizeFromLayout();
        return;
    L5:
        if (this.rotatedFrameWidth == 0) goto L24;
        if (this.rotatedFrameHeight == 0) goto L24;
        if (getWidth() == 0) goto L24;
        if (getHeight() == 0) goto L24;
        float r0 = getWidth() / getHeight();
        int r1 = this.rotatedFrameWidth;
        int r3 = this.rotatedFrameHeight;
        if ((r1 / r3) <= r0) goto L15;
        r1 = (int) (r3 * r0);
    L16:
        int r02 = Math.min(getWidth(), r1);
        int r12 = Math.min(getHeight(), r3);
        logD("updateSurfaceSize. Layout size: " + getWidth() + "x" + getHeight() + ", frame size: " + this.rotatedFrameWidth + "x" + this.rotatedFrameHeight + ", requested surface size: " + r02 + "x" + r12 + ", old surface size: " + this.surfaceWidth + "x" + this.surfaceHeight);
        if (r02 == this.surfaceWidth) goto L19;
    L22:
        this.surfaceWidth = r02;
        this.surfaceHeight = r12;
        getHolder().setFixedSize(r02, r12);
        return;
    L19:
        if (r12 != this.surfaceHeight) goto L22;
        return;
    L15:
        r3 = (int) (r1 / r0);
        goto L16
    }

    public void addFrameListener(EglRenderer.FrameListener r1, float r2) {
        this.eglRenderer.addFrameListener(r1, r2);
    }

    public void addFrameListener(EglRenderer.FrameListener r1, float r2, RendererCommon.GlDrawer r3) {
        this.eglRenderer.addFrameListener(r1, r2, r3);
    }

    public void clearImage() {
        this.eglRenderer.clearImage();
    }

    public void disableFpsReduction() {
        this.eglRenderer.disableFpsReduction();
    }

    public void init(EglBase.Context r3, RendererCommon.RendererEvents r4) {
        init(r3, r4, EglBase.CONFIG_PLAIN, new GlRectDrawer());
    }

    public void init(EglBase.Context r1, RendererCommon.RendererEvents r2, int[] r3, RendererCommon.GlDrawer r4) {
        ThreadUtils.checkIsOnMainThread();
        this.rendererEvents = r2;
        this.rotatedFrameWidth = 0;
        this.rotatedFrameHeight = 0;
        this.eglRenderer.init(r1, this, r3, r4);
    }

    @Override // org.webrtc.RendererCommon.RendererEvents
    public void onFirstFrameRendered() {
        RendererCommon.RendererEvents r0 = this.rendererEvents;
        if (r0 == null) goto L6;
        r0.onFirstFrameRendered();
        return;
    }

    @Override // org.webrtc.VideoSink
    public void onFrame(VideoFrame r1) {
        this.eglRenderer.onFrame(r1);
    }

    @Override // org.webrtc.RendererCommon.RendererEvents
    public void onFrameResolutionChanged(int r3, int r4, int r5) {
        RendererCommon.RendererEvents r0 = this.rendererEvents;
        if (r0 == null) goto L6;
        r0.onFrameResolutionChanged(r3, r4, r5);
    L6:
        if (r5 == 0) goto L10;
        if (r5 == 180) goto L10;
        int r1 = r4;
    L11:
        if (r5 == 0) goto L13;
        if (r5 == 180) goto L13;
    L14:
        postOrRun(new SurfaceViewRenderer$$ExternalSyntheticLambda0(this, r1, r3));
        return;
    L13:
        r3 = r4;
    L10:
        r1 = r3;
        goto L11
    }

    @Override // android.view.View
    public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
        ThreadUtils.checkIsOnMainThread();
        this.eglRenderer.setLayoutAspectRatio((r4 - r2) / (r5 - r3));
        updateSurfaceSize();
    }

    @Override // android.view.SurfaceView, android.view.View
    public void onMeasure(int r7, int r8) {
        ThreadUtils.checkIsOnMainThread();
        Point r72 = this.videoLayoutMeasure.measure(true, r7, r8, this.rotatedFrameWidth, this.rotatedFrameHeight);
        setMeasuredDimension(r72.x, r72.y);
        logD("onMeasure(). New size: " + r72.x + "x" + r72.y);
    }

    public void pauseVideo() {
        this.eglRenderer.pauseVideo();
    }

    public void release() {
        this.eglRenderer.release();
    }

    public void removeFrameListener(EglRenderer.FrameListener r1) {
        this.eglRenderer.removeFrameListener(r1);
    }

    public void setEnableHardwareScaler(boolean r1) {
        ThreadUtils.checkIsOnMainThread();
        this.enableFixedSize = r1;
        updateSurfaceSize();
    }

    public void setFpsReduction(float r1) {
        this.eglRenderer.setFpsReduction(r1);
    }

    public void setMirror(boolean r1) {
        this.eglRenderer.setMirror(r1);
    }

    public void setScalingType(RendererCommon.ScalingType r2) {
        ThreadUtils.checkIsOnMainThread();
        this.videoLayoutMeasure.setScalingType(r2);
        requestLayout();
    }

    public void setScalingType(RendererCommon.ScalingType r2, RendererCommon.ScalingType r3) {
        ThreadUtils.checkIsOnMainThread();
        this.videoLayoutMeasure.setScalingType(r2, r3);
        requestLayout();
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceChanged(SurfaceHolder r1, int r2, int r3, int r4) {
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceCreated(SurfaceHolder r1) {
        ThreadUtils.checkIsOnMainThread();
        this.surfaceHeight = 0;
        this.surfaceWidth = 0;
        updateSurfaceSize();
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceDestroyed(SurfaceHolder r1) {
    }
}
