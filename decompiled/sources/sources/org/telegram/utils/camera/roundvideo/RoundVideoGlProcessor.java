package org.telegram.utils.camera.roundvideo;

import android.graphics.SurfaceTexture;
import android.opengl.EGL14;
import android.opengl.EGLConfig;
import android.opengl.EGLContext;
import android.opengl.EGLDisplay;
import android.opengl.EGLExt;
import android.opengl.EGLSurface;
import android.opengl.GLES20;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.SystemClock;
import android.util.Size;
import android.view.Surface;
import androidx.camera.core.processing.util.GLUtils$$ExternalSyntheticBUOutline0;
import c.f$$ExternalSyntheticBUOutline1;
import com.android.dx.dex.code.CstInsn$$ExternalSyntheticBUOutline0;
import com.android.dx.io.Opcodes;
import java.util.concurrent.CountDownLatch;
import kotlin.jvm.internal.LongCompanionObject;
import okio.Segment$$ExternalSyntheticBUOutline1;
import org.mvel2.asm.Constants$$ExternalSyntheticBUOutline0;
import org.telegram.utils.camera.roundvideo.RoundVideoSession;
import org.webrtc.EglBase;

/* loaded from: classes6.dex */
final class RoundVideoGlProcessor {
    private Surface cameraSurface;
    private long cameraSwitchStartNs;
    private boolean cameraTimestampRealtime;
    private final boolean compositionEnabled;
    private final RoundVideoDiagnostics diagnostics;
    private EGLContext eglContext;
    private EGLDisplay eglDisplay;
    private EGLSurface eglSurface;
    private final int[] eglSurfaceValue;
    private final ErrorListener errorListener;
    private long firstCameraTimestamp;
    private volatile Runnable firstFrameListener;
    private boolean firstFrameReported;
    private long firstPresentationTime;
    private final FrameTimingListener frameTimingListener;
    private long glStartedNs;
    private long gpuTimingMaxNs;
    private int gpuTimingSamples;
    private long gpuTimingTotalNs;
    private Handler handler;
    private boolean hasCameraFrame;
    private boolean hasNewCameraFrame;
    private volatile RuntimeException initializationError;
    private long inputFrames;
    private Size inputSize;
    private SurfaceTexture inputSurfaceTexture;
    private int largeFrameGaps;
    private long lastFrameTime;
    private long lastPresentationTime;
    private long maximumFrameGapNs;
    private boolean newCameraSessionReady;
    private int outputHeight;
    private final int outputSize;
    private int outputSizeCheckFrame;
    private final Surface outputSurface;
    private int outputWidth;
    private RoundVideoOverlayRenderer overlayRenderer;
    private long preOriginFrames;
    private volatile long presentationTimeLimitNs;
    private volatile long presentationTimeOriginNs;
    private volatile boolean recordingStarted;
    private int sourceCropSize;
    private volatile boolean started;
    private long submittedFrames;
    private int swapTimingFrames;
    private long swapTimingMaxNs;
    private long swapTimingTotalNs;
    private long switchCrossfadeDurationNs;
    private int switchExpectedWaitMs;
    private final Runnable switchFrameRunnable;
    private RoundVideoSession.CameraFacing switchFromFacing;
    private float switchOldBlurRadiusPx;
    private long switchStageStartNs;
    private int switchState;
    private RoundVideoSession.CameraFacing switchToFacing;
    private long switchUnblurDurationNs;
    private int syntheticFrames;
    private int textureId;
    private final float[] textureMatrix;
    private HandlerThread thread;

    public interface ErrorListener {
        void onError(Exception r1);
    }

    public interface FrameTimingListener {
        void onFrameSubmitted(long r1, long r3);
    }

    public static /* synthetic */ void $r8$lambda$D0AluyTK2f4VM1l9S8JQjaDa3x0(RoundVideoGlProcessor r0) {
        r0.lambda$onCameraSessionConfigured$3();
    }

    /* renamed from: $r8$lambda$GPZ33jvpFhFSwUrIE-QO8a5yYq0, reason: not valid java name */
    public static /* synthetic */ void m22167$r8$lambda$GPZ33jvpFhFSwUrIEQO8a5yYq0(RoundVideoGlProcessor r0, SurfaceTexture r1) {
        r0.lambda$createInputSurface$5(r1);
    }

    /* renamed from: $r8$lambda$Is280eD-ff6QwTALnH_N7zZF4UQ, reason: not valid java name */
    public static /* synthetic */ void m22168$r8$lambda$Is280eDff6QwTALnH_N7zZF4UQ(RoundVideoGlProcessor r0, RoundVideoSession.CameraFacing r1, RoundVideoSession.CameraFacing r2, Handler r3) {
        r0.lambda$beginCameraSwitch$2(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$V8xLYOIrG0lh7qmwkOuU5hwa8kM(RoundVideoGlProcessor r0, Size r1, int r2, boolean r3, RuntimeException[] r4, CountDownLatch r5) {
        r0.lambda$updateInputConfiguration$4(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$bZxn_lC6iqli2fv2POwKxF1ARbE(RoundVideoGlProcessor r0, CountDownLatch r1) {
        r0.lambda$start$0(r1);
    }

    public static /* synthetic */ void $r8$lambda$ogB9YLpzHQpiMC09pZ4ncBfs1Zc(RoundVideoGlProcessor r0) {
        r0.drawSwitchFrame();
    }

    public static /* synthetic */ void $r8$lambda$rt7ow4nR2CaQWIcuh_2PxTqRnOM(RoundVideoGlProcessor r0, HandlerThread r1, CountDownLatch r2) {
        r0.lambda$stop$1(r1, r2);
    }

    public RoundVideoGlProcessor(Size r4, Surface r5, int r6, int r7, boolean r8, boolean r9, RoundVideoDiagnostics r10, long r11, FrameTimingListener r13, ErrorListener r14) {
        this.presentationTimeLimitNs = LongCompanionObject.MAX_VALUE;
        this.eglDisplay = EGL14.EGL_NO_DISPLAY;
        this.eglContext = EGL14.EGL_NO_CONTEXT;
        this.eglSurface = EGL14.EGL_NO_SURFACE;
        boolean r0 = true;
        this.eglSurfaceValue = new int[1];
        this.textureMatrix = new float[16];
        this.firstCameraTimestamp = -1;
        this.lastPresentationTime = -1;
        this.switchFrameRunnable = new RoundVideoGlProcessor$$ExternalSyntheticLambda1(this);
        this.inputSize = r4;
        this.outputSurface = r5;
        this.outputSize = r6;
        this.sourceCropSize = r7;
        this.cameraTimestampRealtime = r8;
        this.compositionEnabled = r9;
        this.diagnostics = r10;
        this.outputWidth = r6;
        this.outputHeight = r6;
        this.presentationTimeOriginNs = r11;
        if (r11 != 0) goto L6;
        r0 = false;
    L6:
        this.recordingStarted = r0;
        this.frameTimingListener = r13;
        this.errorListener = r14;
    }

    private static void checkEglObject(boolean r1, String r2) {
        if (r1 == false) goto L4;
        return;
    L4:
        GLUtils$$ExternalSyntheticBUOutline0.m(r2, ": 0x", Integer.toHexString(EGL14.eglGetError()));
    }

    private static int clamp(int r0, int r1, int r2) {
        return Math.max(r1, Math.min(r2, r0));
    }

    private static float clamp01(float r1) {
        return Math.max(0.0f, Math.min(1.0f, r1));
    }

    private void configureSwitchTiming(int r6) {
        this.switchExpectedWaitMs = r6;
        this.switchCrossfadeDurationNs = ((clamp(300 - ((Math.max(0, r6 - 400) * 3) / 20), Opcodes.MUL_INT_LIT16, 300) * 45) / 100) * 1000000;
        this.switchUnblurDurationNs = (r6 - r0) * 1000000;
    }

    private void createInputSurface() {
        int[] r1 = new int[1];
        GLES20.glGenTextures(1, r1, 0);
        int r0 = r1[0];
        this.textureId = r0;
        GLES20.glBindTexture(36197, r0);
        setInputTextureFilter();
        GLES20.glTexParameteri(36197, 10242, 33071);
        GLES20.glTexParameteri(36197, 10243, 33071);
        SurfaceTexture r02 = new SurfaceTexture(this.textureId);
        this.inputSurfaceTexture = r02;
        r02.setDefaultBufferSize(this.inputSize.getWidth(), this.inputSize.getHeight());
        this.inputSurfaceTexture.setOnFrameAvailableListener(new RoundVideoGlProcessor$$ExternalSyntheticLambda0(this), this.handler);
        this.cameraSurface = new Surface(this.inputSurfaceTexture);
    }

    private void drawFrame() {
        if (this.started == false) goto L63;
        SurfaceTexture r3 = this.inputSurfaceTexture;
        if (r3 == null) goto L70;
        r3.updateTexImage();     // Catch: RuntimeException -> L10
        this.inputSurfaceTexture.getTransformMatrix(this.textureMatrix);     // Catch: RuntimeException -> L10
        long r32 = this.inputSurfaceTexture.getTimestamp();     // Catch: RuntimeException -> L10
        long r5 = this.inputFrames + 1;     // Catch: RuntimeException -> L10
        this.inputFrames = r5;     // Catch: RuntimeException -> L10
        if (r5 != 1) goto L13;
        this.diagnostics.log("first GL input frame: cameraTimestampNs=" + r32 + ", elapsedMs=" + elapsedMs(this.glStartedNs));     // Catch: RuntimeException -> L10
    L13:
        if (this.recordingStarted == true) goto L20;
        Runnable r0 = this.firstFrameListener;     // Catch: RuntimeException -> L10
        if (this.firstFrameReported == true) goto L64;
        if (r0 == null) goto L65;
        this.firstFrameReported = true;     // Catch: RuntimeException -> L10
        r0.run();     // Catch: RuntimeException -> L10
        return;
    L65:
        return;
    L64:
        return;
    L20:
        if (this.cameraTimestampRealtime == true) goto L22;
    L27:
        long r8 = SystemClock.elapsedRealtimeNanos();     // Catch: RuntimeException -> L10
        long r1 = this.lastFrameTime;     // Catch: RuntimeException -> L10
        if (r1 == 0) goto L32;
        long r12 = r8 - r1;     // Catch: RuntimeException -> L10
        this.maximumFrameGapNs = Math.max(this.maximumFrameGapNs, r12);     // Catch: RuntimeException -> L10
        if (r12 <= 50000000) goto L32;
        this.largeFrameGaps++;
    L32:
        long r13 = this.lastFrameTime;     // Catch: RuntimeException -> L10
        if (r13 != 0) goto L35;
    L38:
        updateOutputSize();     // Catch: RuntimeException -> L10
    L39:
        this.lastFrameTime = r8;     // Catch: RuntimeException -> L10
        if (this.switchState != 0) goto L42;
        this.hasCameraFrame = true;     // Catch: RuntimeException -> L10
        this.overlayRenderer.render(this.textureId, this.textureMatrix, r8, this.outputWidth, this.outputHeight);     // Catch: RuntimeException -> L10
        long r02 = getCameraPresentationTime(r32);     // Catch: RuntimeException -> L10
        if (r02 >= this.presentationTimeLimitNs) goto L69;
        submitFrame(r02);     // Catch: RuntimeException -> L10
        return;
    L69:
        return;
    L42:
        if (this.newCameraSessionReady == false) goto L67;
        boolean r14 = this.hasNewCameraFrame;     // Catch: RuntimeException -> L10
        this.overlayRenderer.captureSwitchFrame(this.textureId, this.textureMatrix, true);     // Catch: RuntimeException -> L10
        this.hasNewCameraFrame = true;     // Catch: RuntimeException -> L10
        if (r14 == true) goto L47;
        long r33 = SystemClock.elapsedRealtimeNanos();     // Catch: RuntimeException -> L10
        float r52 = (r33 - this.cameraSwitchStartNs) / 1000000.0f;     // Catch: RuntimeException -> L10
        int r15 = RoundVideoSwitchTimingStore.recordAndGetAverageMs(this.switchFromFacing, this.switchToFacing, Math.round(r52));     // Catch: RuntimeException -> L10
        this.diagnostics.log("camera switch first new frame: waitMs=" + r52 + ", rollingAverageMs=" + r15);     // Catch: RuntimeException -> L10
        this.switchState = 3;     // Catch: RuntimeException -> L10
        this.switchStageStartNs = r33;     // Catch: RuntimeException -> L10
    L47:
        int r03 = this.switchState;     // Catch: RuntimeException -> L10
        if (r03 != 3) goto L50;
    L51:
        Handler r04 = this.handler;     // Catch: RuntimeException -> L10
        if (r04 == null) goto L54;
        r04.removeCallbacks(this.switchFrameRunnable);     // Catch: RuntimeException -> L10
    L54:
        drawSwitchFrame();     // Catch: RuntimeException -> L10
        return;
    L50:
        if (r03 == 4) goto L51;
        return;
    L67:
        return;
    L35:
        if ((r8 - r13) > 50000000) goto L38;
        int r16 = this.outputSizeCheckFrame;     // Catch: RuntimeException -> L10
        this.outputSizeCheckFrame = r16 + 1;     // Catch: RuntimeException -> L10
        if ((r16 % 60) != 0) goto L39;
    L22:
        if (r32 >= this.presentationTimeOriginNs) goto L27;
        long r53 = this.preOriginFrames + 1;     // Catch: RuntimeException -> L10
        this.preOriginFrames = r53;     // Catch: RuntimeException -> L10
        if (r53 != 1) goto L66;
        this.diagnostics.log("dropping pre-origin camera frame: deltaUs=" + ((r32 - this.presentationTimeOriginNs) / 1000));     // Catch: RuntimeException -> L10
        return;
    L66:
        return;
    L10:
        e = move-exception;
        reportError(e);
        return;
    L70:
        return;
    }

    private void drawSwitchFrame() {
        if (this.started == true) goto L5;
        return;
    L5:
        if (this.switchState != 0) goto L71;
        return;
    L71:
        long r3 = SystemClock.elapsedRealtimeNanos();     // Catch: RuntimeException -> L44
        long r5 = r3 - this.switchStageStartNs;     // Catch: RuntimeException -> L44
        int r7 = this.switchState;     // Catch: RuntimeException -> L44
        if (r7 != 1) goto L20;
        this.switchOldBlurRadiusPx = getSwitchOldBlurRadiusPx(r3);     // Catch: RuntimeException -> L18
        if (r5 >= getExpectedSwitchWaitNs()) goto L14;
    L26:
        float r72 = 0.0f;
    L27:
        float r16 = r72;
        float r15 = 1.0f;
    L35:
        this.overlayRenderer.renderSwitch(this.switchOldBlurRadiusPx, r15, r16, this.outputWidth, this.outputHeight);     // Catch: RuntimeException -> L44
        this.syntheticFrames++;
        long r13 = getRealtimePresentationTime();     // Catch: RuntimeException -> L44
        if (r13 >= this.presentationTimeLimitNs) goto L39;
        submitFrame(r13);     // Catch: RuntimeException -> L18
    L39:
        long r10 = this.switchCrossfadeDurationNs + this.switchUnblurDurationNs;     // Catch: RuntimeException -> L44
        if (this.switchState == 4) goto L42;
    L46:
        int r0 = this.switchState;     // Catch: RuntimeException -> L44
        if (r0 != 0) goto L49;
        return;
    L49:
        if (this.handler != null) goto L51;
        return;
    L51:
        if (this.hasNewCameraFrame == false) goto L55;
        if (r0 == 3) goto L54;
        if (r0 != 4) goto L55;
    L54:
        boolean r73 = true;
    L56:
        if (r73 == false) goto L58;
        long r102 = r10 - r5;     // Catch: RuntimeException -> L44
    L59:
        long r2 = 33;
        if (r73 == false) goto L62;
        long r4 = 70;
    L64:
        if (this.switchState == 2) goto L67;
        r2 = Math.max(1, (r102 + 999999) / 1000000);     // Catch: RuntimeException -> L44
    L67:
        this.handler.postDelayed(this.switchFrameRunnable, Math.min(r4, r2));     // Catch: RuntimeException -> L44
        return;
    L62:
        r4 = 33;
        goto L64
    L58:
        r102 = getExpectedSwitchWaitNs() - r5;     // Catch: RuntimeException -> L44
    L55:
        r73 = false;
        goto L56
    L42:
        if (r5 < r10) goto L46;
        this.overlayRenderer.finishCameraSwitch(r3);     // Catch: RuntimeException -> L44
        this.switchState = 0;     // Catch: RuntimeException -> L44
        this.diagnostics.log("synthetic camera switch completed: elapsedMs=" + ((r3 - this.cameraSwitchStartNs) / 1000000.0f) + ", syntheticFrames=" + this.syntheticFrames);     // Catch: RuntimeException -> L44
        goto L46
    L14:
        if (this.hasNewCameraFrame == false) goto L16;
        int r74 = 3;
    L17:
        this.switchState = r74;     // Catch: RuntimeException -> L18
        this.switchStageStartNs = r3;     // Catch: RuntimeException -> L18
        goto L26
    L16:
        r74 = 2;
        goto L17
    L20:
        if (r7 != 2) goto L23;
        this.switchOldBlurRadiusPx = getSwitchOldBlurRadiusPx(r3);     // Catch: RuntimeException -> L18
        goto L26
    L23:
        if (r7 == 3) goto L28;
        if (r7 != 4) goto L26;
    L28:
        long r132 = this.switchCrossfadeDurationNs;     // Catch: RuntimeException -> L44
        if (r5 < r132) goto L32;
        this.switchState = 4;     // Catch: RuntimeException -> L44
        r15 = 1.0f - smoothStep((r5 - r132) / this.switchUnblurDurationNs);     // Catch: RuntimeException -> L44
        r16 = 1.0f;
        goto L35
    L32:
        r72 = smoothStep(r5 / r132);     // Catch: RuntimeException -> L18
    L18:
        e = e;
        int r152 = 0;
    L69:
        this.switchState = r152;
        reportError(e);
        return;
    L44:
        e = e;
        r152 = 0;
        goto L69
    }

    private static float easeOutQuart(float r2) {
        float r22 = 1.0f - clamp01(r2);
        return 1.0f - (((r22 * r22) * r22) * r22);
    }

    private static long elapsedMs(long r2) {
        return (System.nanoTime() - r2) / 1000000;
    }

    private static String frameRate(long r2, long r4) {
        if (r4 > 0) goto L7;
        return "n/a";
    L7:
        return String.valueOf((r2 * 1.0E9d) / r4);
    }

    private long getCameraPresentationTime(long r7) {
        if (this.presentationTimeOriginNs == 0) goto L12;
        if (this.cameraTimestampRealtime == false) goto L8;
        r7 = Math.max(0, r7 - this.presentationTimeOriginNs);
        goto L12
    L8:
        if (this.firstCameraTimestamp >= 0) goto L10;
        this.firstCameraTimestamp = r7;
        this.firstPresentationTime = getRealtimePresentationTime();
    L10:
        long r0 = this.firstPresentationTime;
        r7 = Math.max(0, r7 - this.firstCameraTimestamp) + r0;
    L12:
        return Math.max(r7, this.lastPresentationTime + 1);
    }

    private long getExpectedSwitchWaitNs() {
        return Math.max(33, this.switchExpectedWaitMs) * 1000000;
    }

    private long getRealtimePresentationTime() {
        if (this.presentationTimeOriginNs != 0) goto L5;
        long r0 = this.lastPresentationTime + 33333333;
    L7:
        return Math.max(r0, this.lastPresentationTime + 1);
    L5:
        r0 = Math.max(0, SystemClock.elapsedRealtimeNanos() - this.presentationTimeOriginNs);
        goto L7
    }

    private float getSwitchOldBlurRadiusPx(long r4) {
        long r42 = Math.max(0, r4 - this.cameraSwitchStartNs);
        long r0 = getExpectedSwitchWaitNs();
        float r3 = this.overlayRenderer.getSwitchTargetBlurRadiusPx();
        if (r42 > r0) goto L7;
        float r43 = easeOutQuart(r42 / r0);
    L6:
        return r3 * r43;
    L7:
        r43 = (float) Math.sqrt((Math.log1p((r42 - r0) / r0) * 0.3499999940395355d) + 1.0d);
        goto L6
    }

    private void initializeGl() {
        EGLDisplay r1 = EGL14.eglGetDisplay(0);
        this.eglDisplay = r1;
        if (r1 == EGL14.EGL_NO_DISPLAY) goto L24;
        int[] r3 = new int[2];
        if (EGL14.eglInitialize(r1, r3, 0, r3, 1) == false) goto L22;
        EGLConfig[] r8 = new EGLConfig[1];
        int[] r11 = new int[1];
        if (EGL14.eglChooseConfig(this.eglDisplay, new int[]{12324, 8, 12323, 8, 12322, 8, 12321, 8, 12352, 4, EglBase.EGL_RECORDABLE_ANDROID, 1, 12344}, 0, r8, 0, 1, r11, 0) == true) goto L9;
    L20:
        Segment$$ExternalSyntheticBUOutline1.m("Unable to choose EGL config");
        return;
    L9:
        if (r11[0] == 0) goto L20;
        EGLContext r12 = EGL14.eglCreateContext(this.eglDisplay, r8[0], EGL14.EGL_NO_CONTEXT, new int[]{12440, 2, 12344}, 0);
        this.eglContext = r12;
        if (r12 == EGL14.EGL_NO_CONTEXT) goto L13;
        boolean r13 = true;
    L14:
        checkEglObject(r13, "Unable to create EGL context");
        EGLSurface r14 = EGL14.eglCreateWindowSurface(this.eglDisplay, r8[0], this.outputSurface, new int[]{12344}, 0);
        this.eglSurface = r14;
        if (r14 == EGL14.EGL_NO_SURFACE) goto L17;
        boolean r15 = true;
    L18:
        checkEglObject(r15, "Unable to create EGL surface");
        makeCurrent();
        this.diagnostics.log("GL initialized: egl=" + r3[0] + "." + r3[1] + ", vendor=" + GLES20.glGetString(7936) + ", renderer=" + GLES20.glGetString(7937) + ", version=" + GLES20.glGetString(7938) + ", elapsedMs=" + elapsedMs(this.glStartedNs));
        createInputSurface();
        this.overlayRenderer = new RoundVideoOverlayRenderer(this.outputSize, this.inputSize, this.sourceCropSize, this.compositionEnabled);
        int r132 = this.outputSize;
        GLES20.glViewport(0, 0, r132, r132);
        return;
    L17:
        r15 = false;
        goto L18
    L13:
        r13 = false;
        goto L14
    L22:
        Segment$$ExternalSyntheticBUOutline1.m("Unable to initialize EGL");
        return;
    L24:
        Segment$$ExternalSyntheticBUOutline1.m("Unable to get EGL display");
    }

    private String inputFilterName() {
        if (this.sourceCropSize != this.outputSize) goto L6;
        return "NEAREST";
    L6:
        return "LINEAR";
    }

    private /* synthetic */ void lambda$beginCameraSwitch$2(RoundVideoSession.CameraFacing r5, RoundVideoSession.CameraFacing r6, Handler r7) {
        if (this.started == true) goto L5;
        return;
    L5:
        if (this.switchState == 0) goto L7;
        return;
    L7:
        if (this.hasCameraFrame == false) goto L13;
        this.overlayRenderer.captureSwitchFrame(this.textureId, this.textureMatrix, false);
        this.hasNewCameraFrame = false;
        this.newCameraSessionReady = false;
        this.switchOldBlurRadiusPx = 0.0f;
        this.switchState = 1;
        long r0 = SystemClock.elapsedRealtimeNanos();
        this.switchStageStartNs = r0;
        this.cameraSwitchStartNs = r0;
        this.switchFromFacing = r5;
        this.switchToFacing = r6;
        configureSwitchTiming(RoundVideoSwitchTimingStore.getAverageMs(r5, r6));
        this.diagnostics.log("synthetic camera switch started: from=" + r5 + ", to=" + r6 + ", expectedWaitMs=" + this.switchExpectedWaitMs + ", targetBlurRadiusPx=" + this.overlayRenderer.getSwitchTargetBlurRadiusPx() + ", overdueBlurGrowth=0.35, revealMs=" + ((this.switchCrossfadeDurationNs + this.switchUnblurDurationNs) / 1000000));
        this.firstCameraTimestamp = -1;
        r7.removeCallbacks(this.switchFrameRunnable);
        r7.post(this.switchFrameRunnable);
        return;
    }

    private /* synthetic */ void lambda$createInputSurface$5(SurfaceTexture r1) {
        drawFrame();
    }

    private /* synthetic */ void lambda$onCameraSessionConfigured$3() {
        if (this.switchState == 0) goto L6;
        this.newCameraSessionReady = true;
        return;
    }

    private /* synthetic */ void lambda$start$0(CountDownLatch r2) {
        initializeGl();     // Catch: Throwable -> L5 RuntimeException -> L7
        this.started = true;     // Catch: Throwable -> L5 RuntimeException -> L7
        r2.countDown();
        return;
    L5:
        th = move-exception;
        r2.countDown();
        throw th;
    L7:
        e = move-exception;
        this.initializationError = e;     // Catch: Throwable -> L5
        releaseGl();     // Catch: Throwable -> L5
        r2.countDown();
    }

    private /* synthetic */ void lambda$stop$1(HandlerThread r1, CountDownLatch r2) {
        releaseGl();     // Catch: Throwable -> L5
        r1.quitSafely();
        r2.countDown();
        return;
    L5:
        th = move-exception;
        r1.quitSafely();
        r2.countDown();
        throw th;
    }

    private /* synthetic */ void lambda$updateInputConfiguration$4(Size r4, int r5, boolean r6, RuntimeException[] r7, CountDownLatch r8) {
        this.inputSize = r4;     // Catch: Throwable -> L6 RuntimeException -> L8
        this.sourceCropSize = r5;     // Catch: Throwable -> L6 RuntimeException -> L8
        this.cameraTimestampRealtime = r6;     // Catch: Throwable -> L6 RuntimeException -> L8
        SurfaceTexture r62 = this.inputSurfaceTexture;     // Catch: Throwable -> L6 RuntimeException -> L8
        if (r62 == null) goto L10;
        r62.setDefaultBufferSize(r4.getWidth(), r4.getHeight());     // Catch: Throwable -> L6 RuntimeException -> L8
    L10:
        setInputTextureFilter();     // Catch: Throwable -> L6 RuntimeException -> L8
        RoundVideoOverlayRenderer r63 = this.overlayRenderer;     // Catch: Throwable -> L6 RuntimeException -> L8
        if (r63 == null) goto L13;
        r63.updateInputConfiguration(r4, r5);     // Catch: Throwable -> L6 RuntimeException -> L8
    L13:
        this.diagnostics.log("GL input updated: input=" + r4 + ", crop=" + r5 + ", filter=" + inputFilterName());     // Catch: Throwable -> L6 RuntimeException -> L8
        r8.countDown();
        return;
    L6:
        th = move-exception;
        r8.countDown();
        throw th;
    L8:
        e = move-exception;
        r7[0] = e;     // Catch: Throwable -> L6
        r8.countDown();
    }

    private void makeCurrent() {
        EGLDisplay r0 = this.eglDisplay;
        EGLSurface r1 = this.eglSurface;
        if (EGL14.eglMakeCurrent(r0, r1, r1, this.eglContext) == false) goto L5;
        return;
    L5:
        CstInsn$$ExternalSyntheticBUOutline0.m("Unable to make EGL context current: 0x", Integer.toHexString(EGL14.eglGetError()));
    }

    private void recordSwapTiming(long r5) {
        this.swapTimingFrames++;
        this.swapTimingTotalNs += r5;
        this.swapTimingMaxNs = Math.max(this.swapTimingMaxNs, r5);
        if ((this.swapTimingFrames % 30) != 0) goto L6;
        this.diagnostics.log("encoder swap: average=" + ((this.swapTimingTotalNs / this.swapTimingFrames) / 1000000.0f) + " ms, max=" + (this.swapTimingMaxNs / 1000000.0f) + " ms");
        return;
    }

    private void releaseGl() {
        long r2 = 0;
        if (this.glStartedNs == 0) goto L6;
        r2 = System.nanoTime() - this.glStartedNs;
    L6:
        RoundVideoDiagnostics r0 = this.diagnostics;
        StringBuilder r1 = new StringBuilder("GL summary: inputFrames=");
        r1.append(this.inputFrames);
        r1.append(", inputFps=");
        r1.append(frameRate(this.inputFrames, r2));
        r1.append(", preOriginFrames=");
        r1.append(this.preOriginFrames);
        r1.append(", submittedFrames=");
        r1.append(this.submittedFrames);
        r1.append(", submittedFps=");
        r1.append(frameRate(this.submittedFrames, r2));
        r1.append(", syntheticFrames=");
        r1.append(this.syntheticFrames);
        r1.append(", largeFrameGaps=");
        r1.append(this.largeFrameGaps);
        r1.append(", maxFrameGapMs=");
        r1.append(this.maximumFrameGapNs / 1000000.0f);
        r1.append(", gpuSamples=");
        r1.append(this.gpuTimingSamples);
        r1.append(", gpuAverageMs=");
        int r22 = this.gpuTimingSamples;
        Object r4 = "n/a";
        if (r22 != 0) goto L9;
        Object r23 = "n/a";
    L10:
        r1.append(r23);
        r1.append(", gpuMaxMs=");
        r1.append(this.gpuTimingMaxNs / 1000000.0f);
        r1.append(", swapAverageMs=");
        int r24 = this.swapTimingFrames;
        if (r24 == 0) goto L14;
        r4 = Float.valueOf((this.swapTimingTotalNs / r24) / 1000000.0f);
    L14:
        r1.append(r4);
        r1.append(", swapMaxMs=");
        r1.append(this.swapTimingMaxNs / 1000000.0f);
        r0.log(r1.toString());
        Handler r02 = this.handler;
        if (r02 == null) goto L17;
        r02.removeCallbacks(this.switchFrameRunnable);
    L17:
        EGLDisplay r03 = this.eglDisplay;
        if (r03 == EGL14.EGL_NO_DISPLAY) goto L24;
        EGLSurface r12 = this.eglSurface;
        if (r12 == EGL14.EGL_NO_SURFACE) goto L24;
        EGLContext r25 = this.eglContext;
        if (r25 == EGL14.EGL_NO_CONTEXT) goto L24;
        EGL14.eglMakeCurrent(r03, r12, r12, r25);
    L24:
        SurfaceTexture r04 = this.inputSurfaceTexture;
        if (r04 == null) goto L27;
        r04.setOnFrameAvailableListener(null);
    L27:
        Surface r05 = this.cameraSurface;
        if (r05 == null) goto L30;
        r05.release();
        this.cameraSurface = null;
    L30:
        SurfaceTexture r06 = this.inputSurfaceTexture;
        if (r06 == null) goto L33;
        r06.release();
        this.inputSurfaceTexture = null;
    L33:
        RoundVideoOverlayRenderer r07 = this.overlayRenderer;
        if (r07 == null) goto L36;
        r07.release();
        this.overlayRenderer = null;
    L36:
        int r08 = this.textureId;
        if (r08 == 0) goto L39;
        GLES20.glDeleteTextures(1, new int[]{r08}, 0);
        this.textureId = 0;
    L39:
        EGLDisplay r09 = this.eglDisplay;
        if (r09 == EGL14.EGL_NO_DISPLAY) goto L48;
        EGLSurface r13 = EGL14.EGL_NO_SURFACE;
        EGL14.eglMakeCurrent(r09, r13, r13, EGL14.EGL_NO_CONTEXT);
        EGLSurface r010 = this.eglSurface;
        if (r010 == EGL14.EGL_NO_SURFACE) goto L44;
        EGL14.eglDestroySurface(this.eglDisplay, r010);
    L44:
        EGLContext r011 = this.eglContext;
        if (r011 == EGL14.EGL_NO_CONTEXT) goto L47;
        EGL14.eglDestroyContext(this.eglDisplay, r011);
    L47:
        EGL14.eglReleaseThread();
        EGL14.eglTerminate(this.eglDisplay);
    L48:
        this.eglDisplay = EGL14.EGL_NO_DISPLAY;
        this.eglContext = EGL14.EGL_NO_CONTEXT;
        this.eglSurface = EGL14.EGL_NO_SURFACE;
        return;
    L9:
        r23 = Float.valueOf((this.gpuTimingTotalNs / r22) / 1000000.0f);
        goto L10
    }

    private void reportError(RuntimeException r3) {
        this.diagnostics.error("GL error", r3);
        ErrorListener r2 = this.errorListener;
        if (r2 == null) goto L6;
        r2.onError(r3);
        return;
    }

    private void setInputTextureFilter() {
        GLES20.glBindTexture(36197, this.textureId);
        if (this.sourceCropSize != this.outputSize) goto L5;
        int r2 = 9728;
    L6:
        GLES20.glTexParameteri(36197, 10241, r2);
        GLES20.glTexParameteri(36197, 10240, r2);
        return;
    L5:
        r2 = 9729;
        goto L6
    }

    private static float smoothStep(float r2) {
        float r22 = clamp01(r2);
        return (r22 * r22) * (3.0f - (r22 * 2.0f));
    }

    private void submitFrame(long r9) {
        this.lastPresentationTime = r9;
        EGLExt.eglPresentationTimeANDROID(this.eglDisplay, this.eglSurface, r9);
        long r0 = System.nanoTime();
        if (EGL14.eglSwapBuffers(this.eglDisplay, this.eglSurface) == false) goto L8;
        long r2 = System.nanoTime();
        this.submittedFrames++;
        recordSwapTiming(r2 - r0);
        FrameTimingListener r8 = this.frameTimingListener;
        if (r8 == null) goto L10;
        r8.onFrameSubmitted(r9 / 1000, r2);
        return;
    L10:
        return;
    L8:
        CstInsn$$ExternalSyntheticBUOutline0.m("Unable to swap EGL buffers: 0x", Integer.toHexString(EGL14.eglGetError()));
    }

    private void updateOutputSize() {
        if (EGL14.eglQuerySurface(this.eglDisplay, this.eglSurface, 12375, this.eglSurfaceValue, 0) == false) goto L20;
        int[] r0 = this.eglSurfaceValue;
        int r1 = r0[0];
        if (EGL14.eglQuerySurface(this.eglDisplay, this.eglSurface, 12374, r0, 0) == false) goto L21;
        int r02 = this.eglSurfaceValue[0];
        if (r1 <= 0) goto L17;
        if (r02 > 0) goto L12;
        return;
    L12:
        if (r1 == this.outputWidth) goto L14;
    L15:
        this.outputWidth = r1;
        this.outputHeight = r02;
        this.diagnostics.log("EGL output size changed: " + r1 + "x" + r02);
        return;
    L14:
        if (r02 != this.outputHeight) goto L15;
        return;
    L17:
        return;
    L21:
        return;
    }

    public void beginCameraSwitch(RoundVideoSession.CameraFacing r3, RoundVideoSession.CameraFacing r4) {
        Handler r0 = this.handler;
        if (this.started == false) goto L8;
        if (r0 == null) goto L9;
        r0.post(new RoundVideoGlProcessor$$ExternalSyntheticLambda4(this, r3, r4, r0));
        return;
    L9:
        return;
    }

    public Surface getCameraSurface() {
        Surface r0 = this.cameraSurface;
        if (r0 == null) goto L5;
        return r0;
    L5:
        Segment$$ExternalSyntheticBUOutline1.m("GL processor is not started");
        return null;
    }

    public void onCameraSessionConfigured() {
        Handler r0 = this.handler;
        if (this.started == false) goto L8;
        if (r0 == null) goto L9;
        r0.post(new RoundVideoGlProcessor$$ExternalSyntheticLambda6(this));
        return;
    L9:
        return;
    }

    public void setFirstFrameListener(Runnable r1) {
        this.firstFrameListener = r1;
    }

    public Surface start() {
        if (this.started == true) goto L5;
        CountDownLatch r0 = new CountDownLatch(1);
        HandlerThread r1 = new HandlerThread("RoundVideoGlProcessor");
        this.thread = r1;
        r1.start();
        this.glStartedNs = System.nanoTime();
        this.diagnostics.log("GL processor start requested: input=" + this.inputSize + ", crop=" + this.sourceCropSize + ", output=" + this.outputSize + "x" + this.outputSize + ", filter=" + inputFilterName() + ", composition=" + this.compositionEnabled);
        Handler r12 = new Handler(this.thread.getLooper());
        this.handler = r12;
        r12.post(new RoundVideoGlProcessor$$ExternalSyntheticLambda2(this, r0));
        r0.await();     // Catch: InterruptedException -> L14
        if (this.initializationError == null) goto L11;
        RuntimeException r02 = this.initializationError;
        this.initializationError = null;
        stop();
        throw r02;
    L11:
        return this.cameraSurface;
    L14:
        e = move-exception;
        Thread.currentThread().interrupt();
        stop();
        Constants$$ExternalSyntheticBUOutline0.m("GL initialization was interrupted", e);
        return null;
    L5:
        return this.cameraSurface;
    }

    public void startRecording(long r4) {
        if (r4 <= 0) goto L6;
        this.presentationTimeOriginNs = r4;
        this.firstCameraTimestamp = -1;
        this.firstPresentationTime = 0;
        this.lastPresentationTime = -1;
        this.presentationTimeLimitNs = LongCompanionObject.MAX_VALUE;
        this.recordingStarted = true;
        return;
    L6:
        f$$ExternalSyntheticBUOutline1.m("Invalid recording time origin");
    }

    public void stop() {
        Handler r0 = this.handler;
        HandlerThread r1 = this.thread;
        if (r0 == null) goto L5;
        r0.removeCallbacks(this.switchFrameRunnable);
    L5:
        this.handler = null;
        this.thread = null;
        this.started = false;
        if (r0 == null) goto L16;
        if (r1 == null) goto L17;
        CountDownLatch r2 = new CountDownLatch(1);
        r0.post(new RoundVideoGlProcessor$$ExternalSyntheticLambda3(this, r1, r2));
        r2.await();     // Catch: InterruptedException -> L12
        return;
    L12:
        Thread.currentThread().interrupt();
        return;
    L17:
        return;
    }

    public void stopRecordingAt(long r3) {
        this.presentationTimeLimitNs = Math.max(0, r3) * 1000;
        Handler r32 = this.handler;
        if (r32 == null) goto L6;
        r32.removeCallbacks(this.switchFrameRunnable);
        return;
    }

    public void updateInputConfiguration(Size r10, int r11, boolean r12) {
        Handler r0 = this.handler;
        if (this.started == false) goto L17;
        if (r0 == null) goto L18;
        CountDownLatch r8 = new CountDownLatch(1);
        RuntimeException[] r7 = new RuntimeException[1];
        r0.post(new RoundVideoGlProcessor$$ExternalSyntheticLambda5(this, r10, r11, r12, r7, r8));
        r8.await();     // Catch: InterruptedException -> L12
        RuntimeException r9 = r7[0];
        if (r9 == null) goto L19;
        throw r9;
    L19:
        return;
    L12:
        e = move-exception;
        Thread.currentThread().interrupt();
        Constants$$ExternalSyntheticBUOutline0.m("Input size update was interrupted", e);
        return;
    L18:
        return;
    }
}
