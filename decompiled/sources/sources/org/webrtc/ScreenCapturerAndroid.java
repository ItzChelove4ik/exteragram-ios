package org.webrtc;

import android.content.Context;
import android.content.Intent;
import android.hardware.display.VirtualDisplay;
import android.media.projection.MediaProjection;
import android.media.projection.MediaProjectionManager;
import android.view.Surface;
import org.telegram.messenger.FileLog;

/* loaded from: classes6.dex */
public class ScreenCapturerAndroid implements VideoCapturer, VideoSink {
    private static final int DISPLAY_FLAGS = 3;
    private static final int VIRTUAL_DISPLAY_DPI = 400;
    private CapturerObserver capturerObserver;
    private int height;
    private boolean isDisposed;
    private MediaProjection mediaProjection;
    private final MediaProjection.Callback mediaProjectionCallback;
    private MediaProjectionManager mediaProjectionManager;
    private final Intent mediaProjectionPermissionResultData;
    private long numCapturedFrames;
    private SurfaceTextureHelper surfaceTextureHelper;
    private VirtualDisplay virtualDisplay;
    private int width;

    public static /* synthetic */ void $r8$lambda$FHApQHOrocm8tWEEO4fGLsDZkRE(ScreenCapturerAndroid r0) {
        r0.lambda$changeCaptureFormat$1();
    }

    /* renamed from: $r8$lambda$dVxA9FhKDp107efCvD20-LwlQBM, reason: not valid java name */
    public static /* synthetic */ void m22376$r8$lambda$dVxA9FhKDp107efCvD20LwlQBM(ScreenCapturerAndroid r0) {
        r0.lambda$stopCapture$0();
    }

    public ScreenCapturerAndroid(Intent r1, MediaProjection.Callback r2) {
        this.mediaProjectionPermissionResultData = r1;
        this.mediaProjectionCallback = r2;
    }

    private void checkNotDisposed() {
        if (this.isDisposed == true) goto L5;
        return;
    L5:
        GlShader$$ExternalSyntheticBUOutline1.m("capturer is disposed.");
    }

    private void createVirtualDisplay() {
        this.surfaceTextureHelper.setTextureSize(this.width, this.height);
        this.virtualDisplay = this.mediaProjection.createVirtualDisplay("WebRTC_ScreenCapture", this.width, this.height, 400, 3, new Surface(this.surfaceTextureHelper.getSurfaceTexture()), null, null);     // Catch: Throwable -> L5
        return;
    L5:
        th = move-exception;
        FileLog.e(th);
    }

    private /* synthetic */ void lambda$changeCaptureFormat$1() {
        this.virtualDisplay.release();
        createVirtualDisplay();
    }

    private /* synthetic */ void lambda$stopCapture$0() {
        this.surfaceTextureHelper.stopListening();
        this.capturerObserver.onCapturerStopped();
        VirtualDisplay r0 = this.virtualDisplay;
        if (r0 == null) goto L5;
        r0.release();
        this.virtualDisplay = null;
    L5:
        MediaProjection r02 = this.mediaProjection;
        if (r02 == null) goto L9;
        r02.unregisterCallback(this.mediaProjectionCallback);
        this.mediaProjection.stop();
        this.mediaProjection = null;
        return;
    }

    @Override // org.webrtc.VideoCapturer
    public synchronized void changeCaptureFormat(int r1, int r2, int r3) {
        monitor-enter(this);
        checkNotDisposed();     // Catch: Throwable -> L10
        this.width = r1;     // Catch: Throwable -> L10
        this.height = r2;     // Catch: Throwable -> L10
        if (this.virtualDisplay != null) goto L7;
        monitor-exit(this);
        return;
    L7:
        ThreadUtils.invokeAtFrontUninterruptibly(this.surfaceTextureHelper.getHandler(), new ScreenCapturerAndroid$$ExternalSyntheticLambda0(this));     // Catch: Throwable -> L10
        monitor-exit(this);
        return;
    L10:
        th = move-exception;
        throw th;
    }

    @Override // org.webrtc.VideoCapturer
    public synchronized void dispose() {
        monitor-enter(this);
        this.isDisposed = true;     // Catch: Throwable -> L7
        monitor-exit(this);
        return;
    L7:
        th = move-exception;
        throw th;
    }

    public MediaProjection getMediaProjection() {
        return this.mediaProjection;
    }

    public long getNumCapturedFrames() {
        return this.numCapturedFrames;
    }

    @Override // org.webrtc.VideoCapturer
    public synchronized void initialize(SurfaceTextureHelper r1, Context r2, CapturerObserver r3) {
        monitor-enter(this);
        checkNotDisposed();     // Catch: Throwable -> L10
        if (r3 == null) goto L15;
        this.capturerObserver = r3;     // Catch: Throwable -> L10
        if (r1 == null) goto L13;
        this.surfaceTextureHelper = r1;     // Catch: Throwable -> L10
        this.mediaProjectionManager = (MediaProjectionManager) r2.getSystemService("media_projection");     // Catch: Throwable -> L10
        monitor-exit(this);
        return;
    L13:
        throw new RuntimeException("surfaceTextureHelper not set.");     // Catch: Throwable -> L10
    L15:
        throw new RuntimeException("capturerObserver not set.");     // Catch: Throwable -> L10
    L10:
        th = move-exception;
        throw th;
    }

    @Override // org.webrtc.VideoCapturer
    public boolean isScreencast() {
        return true;
    }

    @Override // org.webrtc.VideoSink
    public void onFrame(VideoFrame r5) {
        this.numCapturedFrames++;
        this.capturerObserver.onFrameCaptured(r5);
    }

    @Override // org.webrtc.VideoCapturer
    public synchronized void startCapture(int r1, int r2, int r3) {
        monitor-enter(this);
    L14:
        th = move-exception;
        throw th;
    L4:
        if (this.mediaProjection == null) goto L6;
    L16:
        monitor-exit(this);
        return;
    L6:
        if (this.mediaProjectionManager == null) goto L16;
        checkNotDisposed();     // Catch: Throwable -> L10
        this.width = r1;     // Catch: Throwable -> L10
        this.height = r2;     // Catch: Throwable -> L10
        MediaProjection r12 = this.mediaProjectionManager.getMediaProjection(-1, this.mediaProjectionPermissionResultData);     // Catch: Throwable -> L10
        this.mediaProjection = r12;     // Catch: Throwable -> L10
        r12.registerCallback(this.mediaProjectionCallback, this.surfaceTextureHelper.getHandler());     // Catch: Throwable -> L10
        createVirtualDisplay();     // Catch: Throwable -> L10
        this.capturerObserver.onCapturerStarted(true);     // Catch: Throwable -> L10
        this.surfaceTextureHelper.startListening(this);     // Catch: Throwable -> L10
    L12:
        monitor-exit(this);
        return;
    L10:
        th = move-exception;
        this.mediaProjectionCallback.onStop();     // Catch: Throwable -> L14
        FileLog.e(th);     // Catch: Throwable -> L14
        goto L12
    }

    @Override // org.webrtc.VideoCapturer
    public synchronized void stopCapture() {
        monitor-enter(this);
        checkNotDisposed();     // Catch: Throwable -> L6
        ThreadUtils.invokeAtFrontUninterruptibly(this.surfaceTextureHelper.getHandler(), new ScreenCapturerAndroid$$ExternalSyntheticLambda1(this));     // Catch: Throwable -> L6
        monitor-exit(this);
        return;
    L6:
        th = move-exception;
        throw th;
    }
}
