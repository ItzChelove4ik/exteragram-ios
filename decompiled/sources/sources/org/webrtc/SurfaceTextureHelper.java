package org.webrtc;

import android.graphics.SurfaceTexture;
import android.opengl.GLES20;
import android.os.Handler;
import android.os.HandlerThread;
import java.util.concurrent.Callable;
import kotlin.CharCodeKt$$ExternalSyntheticBUOutline0;
import okio.Segment$$ExternalSyntheticBUOutline1;
import org.webrtc.EglBase;
import org.webrtc.TextureBufferImpl;
import org.webrtc.VideoFrame;

/* loaded from: classes6.dex */
public class SurfaceTextureHelper {
    private static final String TAG = "SurfaceTextureHelper";
    private final EglBase eglBase;
    private final FrameRefMonitor frameRefMonitor;
    private int frameRotation;
    private final Handler handler;
    private boolean hasPendingTexture;
    private boolean isQuitting;
    private volatile boolean isTextureInUse;
    private VideoSink listener;
    private final int oesTextureId;
    private VideoSink pendingListener;
    final Runnable setListenerRunnable;
    private final SurfaceTexture surfaceTexture;
    private int textureHeight;
    private final TextureBufferImpl.RefCountMonitor textureRefCountMonitor;
    private int textureWidth;
    private final TimestampAligner timestampAligner;
    private final YuvConverter yuvConverter;

    /* renamed from: org.webrtc.SurfaceTextureHelper$1, reason: invalid class name */
    public class AnonymousClass1 implements Callable<SurfaceTextureHelper> {
        final /* synthetic */ boolean val$alignTimestamps;
        final /* synthetic */ FrameRefMonitor val$frameRefMonitor;
        final /* synthetic */ Handler val$handler;
        final /* synthetic */ EglBase.Context val$sharedContext;
        final /* synthetic */ String val$threadName;
        final /* synthetic */ YuvConverter val$yuvConverter;

        public AnonymousClass1(EglBase.Context r1, Handler r2, boolean r3, YuvConverter r4, FrameRefMonitor r5, String r6) {
            this.val$sharedContext = r1;
            this.val$handler = r2;
            this.val$alignTimestamps = r3;
            this.val$yuvConverter = r4;
            this.val$frameRefMonitor = r5;
            this.val$threadName = r6;
        }

        @Override // java.util.concurrent.Callable
        public /* bridge */ /* synthetic */ SurfaceTextureHelper call() {
            return call2();
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: call, reason: avoid collision after fix types in other method */
        public SurfaceTextureHelper call2() {
            return new SurfaceTextureHelper(this.val$sharedContext, this.val$handler, this.val$alignTimestamps, this.val$yuvConverter, this.val$frameRefMonitor, null);
        L4:
            e = move-exception;
            Logging.e(SurfaceTextureHelper.TAG, this.val$threadName + " create failure", e);
            return null;
        }
    }

    /* renamed from: org.webrtc.SurfaceTextureHelper$2, reason: invalid class name */
    public class AnonymousClass2 implements TextureBufferImpl.RefCountMonitor {
        final /* synthetic */ SurfaceTextureHelper this$0;

        public AnonymousClass2(SurfaceTextureHelper r1) {
            this.this$0 = r1;
        }

        @Override // org.webrtc.TextureBufferImpl.RefCountMonitor
        public void onDestroy(TextureBufferImpl r2) {
            SurfaceTextureHelper.m22386$$Nest$mreturnTextureFrame(this.this$0);
            if (SurfaceTextureHelper.m22380$$Nest$fgetframeRefMonitor(this.this$0) == null) goto L6;
            SurfaceTextureHelper.m22380$$Nest$fgetframeRefMonitor(this.this$0).onDestroyBuffer(r2);
            return;
        }

        @Override // org.webrtc.TextureBufferImpl.RefCountMonitor
        public void onRelease(TextureBufferImpl r2) {
            if (SurfaceTextureHelper.m22380$$Nest$fgetframeRefMonitor(this.this$0) == null) goto L6;
            SurfaceTextureHelper.m22380$$Nest$fgetframeRefMonitor(this.this$0).onReleaseBuffer(r2);
            return;
        }

        @Override // org.webrtc.TextureBufferImpl.RefCountMonitor
        public void onRetain(TextureBufferImpl r2) {
            if (SurfaceTextureHelper.m22380$$Nest$fgetframeRefMonitor(this.this$0) == null) goto L6;
            SurfaceTextureHelper.m22380$$Nest$fgetframeRefMonitor(this.this$0).onRetainBuffer(r2);
            return;
        }
    }

    /* renamed from: org.webrtc.SurfaceTextureHelper$3, reason: invalid class name */
    public class AnonymousClass3 implements Runnable {
        final /* synthetic */ SurfaceTextureHelper this$0;

        public AnonymousClass3(SurfaceTextureHelper r1) {
            this.this$0 = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            Logging.d(SurfaceTextureHelper.TAG, "Setting listener to " + SurfaceTextureHelper.m22382$$Nest$fgetpendingListener(this.this$0));
            SurfaceTextureHelper r0 = this.this$0;
            SurfaceTextureHelper.m22384$$Nest$fputlistener(r0, SurfaceTextureHelper.m22382$$Nest$fgetpendingListener(r0));
            SurfaceTextureHelper.m22385$$Nest$fputpendingListener(this.this$0, null);
            if (SurfaceTextureHelper.m22381$$Nest$fgethasPendingTexture(this.this$0) == false) goto L6;
            SurfaceTextureHelper.m22387$$Nest$mupdateTexImage(this.this$0);
            SurfaceTextureHelper.m22383$$Nest$fputhasPendingTexture(this.this$0, false);
            return;
        }
    }

    public interface FrameRefMonitor {
        void onDestroyBuffer(VideoFrame.TextureBuffer r1);

        void onNewBuffer(VideoFrame.TextureBuffer r1);

        void onReleaseBuffer(VideoFrame.TextureBuffer r1);

        void onRetainBuffer(VideoFrame.TextureBuffer r1);
    }

    public static /* synthetic */ void $r8$lambda$4Cqr7wl04Qulpeh7F3bRqaUC9pw(SurfaceTextureHelper r0) {
        r0.lambda$returnTextureFrame$5();
    }

    public static /* synthetic */ void $r8$lambda$IuyTqbOAMbNDOOHXXpipRO0hzVw(SurfaceTextureHelper r0) {
        r0.lambda$dispose$6();
    }

    public static /* synthetic */ void $r8$lambda$K7gs6_oFyi8F29H9vvX3z2n4gyo(SurfaceTextureHelper r0) {
        r0.lambda$stopListening$1();
    }

    public static /* synthetic */ void $r8$lambda$_AGjc60SakLcee67Z_nMqYs5bzA(SurfaceTextureHelper r0, SurfaceTexture r1) {
        r0.lambda$new$0(r1);
    }

    public static /* synthetic */ void $r8$lambda$ceq41bdxleJURFZV_6JEQ4w2Qdc(SurfaceTextureHelper r0, int r1, int r2) {
        r0.lambda$setTextureSize$2(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$i1LAdT6w89q5xYNap_FN0iXrUhs(SurfaceTextureHelper r0, int r1) {
        r0.lambda$setFrameRotation$4(r1);
    }

    /* renamed from: $r8$lambda$k2eTKMhsKffQ5-5NS1zqKT7AAhY, reason: not valid java name */
    public static /* synthetic */ void m22379$r8$lambda$k2eTKMhsKffQ55NS1zqKT7AAhY(SurfaceTextureHelper r0) {
        r0.lambda$forceFrame$3();
    }

    /* renamed from: -$$Nest$fgetframeRefMonitor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameRefMonitor m22380$$Nest$fgetframeRefMonitor(SurfaceTextureHelper r0) {
        return r0.frameRefMonitor;
    }

    /* renamed from: -$$Nest$fgethasPendingTexture, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m22381$$Nest$fgethasPendingTexture(SurfaceTextureHelper r0) {
        return r0.hasPendingTexture;
    }

    /* renamed from: -$$Nest$fgetpendingListener, reason: not valid java name */
    public static /* bridge */ /* synthetic */ VideoSink m22382$$Nest$fgetpendingListener(SurfaceTextureHelper r0) {
        return r0.pendingListener;
    }

    /* renamed from: -$$Nest$fputhasPendingTexture, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m22383$$Nest$fputhasPendingTexture(SurfaceTextureHelper r0, boolean r1) {
        r0.hasPendingTexture = r1;
    }

    /* renamed from: -$$Nest$fputlistener, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m22384$$Nest$fputlistener(SurfaceTextureHelper r0, VideoSink r1) {
        r0.listener = r1;
    }

    /* renamed from: -$$Nest$fputpendingListener, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m22385$$Nest$fputpendingListener(SurfaceTextureHelper r0, VideoSink r1) {
        r0.pendingListener = r1;
    }

    /* renamed from: -$$Nest$mreturnTextureFrame, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m22386$$Nest$mreturnTextureFrame(SurfaceTextureHelper r0) {
        r0.returnTextureFrame();
    }

    /* renamed from: -$$Nest$mupdateTexImage, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m22387$$Nest$mupdateTexImage(SurfaceTextureHelper r0) {
        r0.updateTexImage();
    }

    private SurfaceTextureHelper(EglBase.Context r4, Handler r5, boolean r6, YuvConverter r7, FrameRefMonitor r8) {
        this.textureRefCountMonitor = new AnonymousClass2(this);
        this.setListenerRunnable = new AnonymousClass3(this);
        TimestampAligner r2 = null;
        if (r5.getLooper().getThread() != Thread.currentThread()) goto L14;
        this.handler = r5;
        if (r6 == false) goto L7;
        r2 = new TimestampAligner();
    L7:
        this.timestampAligner = r2;
        this.yuvConverter = r7;
        this.frameRefMonitor = r8;
        EglBase r42 = EglBase.create(r4, EglBase.CONFIG_PIXEL_BUFFER);
        this.eglBase = r42;
        r42.createDummyPbufferSurface();     // Catch: RuntimeException -> L11
        r42.makeCurrent();     // Catch: RuntimeException -> L11
        int r43 = GlUtil.generateTexture(36197);
        this.oesTextureId = r43;
        SurfaceTexture r62 = new SurfaceTexture(r43);
        this.surfaceTexture = r62;
        setOnFrameAvailableListener(r62, new SurfaceTextureHelper$$ExternalSyntheticLambda4(this), r5);
        return;
    L11:
        e = move-exception;
        this.eglBase.release();
        r5.getLooper().quit();
        throw e;
    L14:
        Segment$$ExternalSyntheticBUOutline1.m("SurfaceTextureHelper must be created on the handler thread");
        throw null;
    }

    public /* synthetic */ SurfaceTextureHelper(EglBase.Context r1, Handler r2, boolean r3, YuvConverter r4, FrameRefMonitor r5, SurfaceTextureHelperIA r6) {
        this(r1, r2, r3, r4, r5);
    }

    public static SurfaceTextureHelper create(String r3, EglBase.Context r4) {
        return create(r3, r4, false, new YuvConverter(), null);
    }

    public static SurfaceTextureHelper create(String r2, EglBase.Context r3, boolean r4) {
        return create(r2, r3, r4, new YuvConverter(), null);
    }

    public static SurfaceTextureHelper create(String r1, EglBase.Context r2, boolean r3, YuvConverter r4) {
        return create(r1, r2, r3, r4, null);
    }

    public static SurfaceTextureHelper create(String r8, EglBase.Context r9, boolean r10, YuvConverter r11, FrameRefMonitor r12) {
        HandlerThread r0 = new HandlerThread(r8);
        r0.start();
        Handler r3 = new Handler(r0.getLooper());
        return (SurfaceTextureHelper) ThreadUtils.invokeAtFrontUninterruptibly(r3, new AnonymousClass1(r9, r3, r10, r11, r12, r8));
    }

    private /* synthetic */ void lambda$dispose$6() {
        this.isQuitting = true;
        if (this.isTextureInUse == true) goto L6;
        release();
        return;
    }

    private /* synthetic */ void lambda$forceFrame$3() {
        this.hasPendingTexture = true;
        tryDeliverTextureFrame();
    }

    private /* synthetic */ void lambda$new$0(SurfaceTexture r2) {
        if (this.hasPendingTexture == false) goto L5;
        Logging.d(TAG, "A frame is already pending, dropping frame.");
    L5:
        this.hasPendingTexture = true;
        tryDeliverTextureFrame();
    }

    private /* synthetic */ void lambda$returnTextureFrame$5() {
        this.isTextureInUse = false;
        if (this.isQuitting == false) goto L6;
        release();
        return;
    L6:
        tryDeliverTextureFrame();
    }

    private /* synthetic */ void lambda$setFrameRotation$4(int r1) {
        this.frameRotation = r1;
    }

    private /* synthetic */ void lambda$setTextureSize$2(int r1, int r2) {
        this.textureWidth = r1;
        this.textureHeight = r2;
        tryDeliverTextureFrame();
    }

    private /* synthetic */ void lambda$stopListening$1() {
        this.listener = null;
        this.pendingListener = null;
    }

    private void release() {
        if (this.handler.getLooper().getThread() == Thread.currentThread()) goto L5;
        Segment$$ExternalSyntheticBUOutline1.m("Wrong thread.");
        return;
    L5:
        if (this.isTextureInUse == false) goto L7;
    L12:
        Segment$$ExternalSyntheticBUOutline1.m("Unexpected release.");
        return;
    L7:
        if (this.isQuitting == false) goto L12;
        this.yuvConverter.release();
        GLES20.glDeleteTextures(1, new int[]{this.oesTextureId}, 0);
        this.surfaceTexture.release();
        this.eglBase.release();
        this.handler.getLooper().quit();
        TimestampAligner r3 = this.timestampAligner;
        if (r3 == null) goto L16;
        r3.dispose();
        return;
    }

    private void returnTextureFrame() {
        this.handler.post(new SurfaceTextureHelper$$ExternalSyntheticLambda3(this));
    }

    private static void setOnFrameAvailableListener(SurfaceTexture r0, SurfaceTexture.OnFrameAvailableListener r1, Handler r2) {
        r0.setOnFrameAvailableListener(r1, r2);
    }

    private void tryDeliverTextureFrame() {
        if (this.handler.getLooper().getThread() == Thread.currentThread()) goto L5;
        Segment$$ExternalSyntheticBUOutline1.m("Wrong thread.");
        return;
    L5:
        if (this.isQuitting == false) goto L7;
        return;
    L7:
        if (this.hasPendingTexture == true) goto L9;
        return;
    L9:
        if (this.isTextureInUse == false) goto L11;
        return;
    L11:
        if (this.listener != null) goto L14;
        return;
    L14:
        if (this.textureWidth != 0) goto L16;
    L26:
        Logging.w(TAG, "Texture size has not been set.");
        return;
    L16:
        if (this.textureHeight == 0) goto L26;
        this.isTextureInUse = true;
        this.hasPendingTexture = false;
        updateTexImage();
        float[] r0 = new float[16];
        this.surfaceTexture.getTransformMatrix(r0);
        long r1 = this.surfaceTexture.getTimestamp();
        TimestampAligner r3 = this.timestampAligner;
        if (r3 == null) goto L21;
        r1 = r3.translateTimestamp(r1);
    L21:
        TextureBufferImpl r32 = new TextureBufferImpl(this.textureWidth, this.textureHeight, VideoFrame.TextureBuffer.Type.OES, this.oesTextureId, RendererCommon.convertMatrixToAndroidGraphicsMatrix(r0), this.handler, this.yuvConverter, this.textureRefCountMonitor);
        FrameRefMonitor r02 = this.frameRefMonitor;
        if (r02 == null) goto L24;
        r02.onNewBuffer(r32);
    L24:
        VideoFrame r03 = new VideoFrame(r32, this.frameRotation, r1);
        this.listener.onFrame(r03);
        r03.release();
    }

    private void updateTexImage() {
        Object r0 = EglBase.lock;
        monitor-enter(r0);
        this.surfaceTexture.updateTexImage();     // Catch: Throwable -> L10
    L13:
        monitor-exit(r0);     // Catch: Throwable -> L7
        return;
    L7:
        th = move-exception;
        throw th;
    }

    public void dispose() {
        Logging.d(TAG, "dispose()");
        ThreadUtils.invokeAtFrontUninterruptibly(this.handler, new SurfaceTextureHelper$$ExternalSyntheticLambda2(this));
    }

    public void forceFrame() {
        this.handler.post(new SurfaceTextureHelper$$ExternalSyntheticLambda6(this));
    }

    public Handler getHandler() {
        return this.handler;
    }

    public SurfaceTexture getSurfaceTexture() {
        return this.surfaceTexture;
    }

    public boolean isTextureInUse() {
        return this.isTextureInUse;
    }

    public void setFrameRotation(int r3) {
        this.handler.post(new SurfaceTextureHelper$$ExternalSyntheticLambda5(this, r3));
    }

    public void setTextureSize(int r3, int r4) {
        if (r3 <= 0) goto L8;
        if (r4 <= 0) goto L6;
        this.surfaceTexture.setDefaultBufferSize(r3, r4);
        this.handler.post(new SurfaceTextureHelper$$ExternalSyntheticLambda1(this, r3, r4));
        return;
    L6:
        CharCodeKt$$ExternalSyntheticBUOutline0.m("Texture height must be positive, but was ", r4);
        return;
    L8:
        CharCodeKt$$ExternalSyntheticBUOutline0.m("Texture width must be positive, but was ", r3);
    }

    public void startListening(VideoSink r2) {
        if (this.listener == null) goto L5;
    L8:
        Segment$$ExternalSyntheticBUOutline1.m("SurfaceTextureHelper listener has already been set.");
        return;
    L5:
        if (this.pendingListener != null) goto L8;
        this.pendingListener = r2;
        this.handler.post(this.setListenerRunnable);
    }

    public void stopListening() {
        Logging.d(TAG, "stopListening()");
        this.handler.removeCallbacks(this.setListenerRunnable);
        ThreadUtils.invokeAtFrontUninterruptibly(this.handler, new SurfaceTextureHelper$$ExternalSyntheticLambda0(this));
    }

    @Deprecated
    public VideoFrame.I420Buffer textureToYuv(VideoFrame.TextureBuffer r1) {
        return r1.toI420();
    }
}
