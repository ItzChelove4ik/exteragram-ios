.class final Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor$FrameTimingListener;,
        Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor$ErrorListener;
    }
.end annotation


# instance fields
.field private cameraSurface:Landroid/view/Surface;

.field private cameraSwitchStartNs:J

.field private cameraTimestampRealtime:Z

.field private final compositionEnabled:Z

.field private final diagnostics:Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;

.field private eglContext:Landroid/opengl/EGLContext;

.field private eglDisplay:Landroid/opengl/EGLDisplay;

.field private eglSurface:Landroid/opengl/EGLSurface;

.field private final eglSurfaceValue:[I

.field private final errorListener:Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor$ErrorListener;

.field private firstCameraTimestamp:J

.field private volatile firstFrameListener:Ljava/lang/Runnable;

.field private firstFrameReported:Z

.field private firstPresentationTime:J

.field private final frameTimingListener:Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor$FrameTimingListener;

.field private glStartedNs:J

.field private gpuTimingMaxNs:J

.field private gpuTimingSamples:I

.field private gpuTimingTotalNs:J

.field private handler:Landroid/os/Handler;

.field private hasCameraFrame:Z

.field private hasNewCameraFrame:Z

.field private volatile initializationError:Ljava/lang/RuntimeException;

.field private inputFrames:J

.field private inputSize:Landroid/util/Size;

.field private inputSurfaceTexture:Landroid/graphics/SurfaceTexture;

.field private largeFrameGaps:I

.field private lastFrameTime:J

.field private lastPresentationTime:J

.field private maximumFrameGapNs:J

.field private newCameraSessionReady:Z

.field private outputHeight:I

.field private final outputSize:I

.field private outputSizeCheckFrame:I

.field private final outputSurface:Landroid/view/Surface;

.field private outputWidth:I

.field private overlayRenderer:Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;

.field private preOriginFrames:J

.field private volatile presentationTimeLimitNs:J

.field private volatile presentationTimeOriginNs:J

.field private volatile recordingStarted:Z

.field private sourceCropSize:I

.field private volatile started:Z

.field private submittedFrames:J

.field private swapTimingFrames:I

.field private swapTimingMaxNs:J

.field private swapTimingTotalNs:J

.field private switchCrossfadeDurationNs:J

.field private switchExpectedWaitMs:I

.field private final switchFrameRunnable:Ljava/lang/Runnable;

.field private switchFromFacing:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;

.field private switchOldBlurRadiusPx:F

.field private switchStageStartNs:J

.field private switchState:I

.field private switchToFacing:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;

.field private switchUnblurDurationNs:J

.field private syntheticFrames:I

.field private textureId:I

.field private final textureMatrix:[F

.field private thread:Landroid/os/HandlerThread;


# direct methods
.method public static synthetic $r8$lambda$D0AluyTK2f4VM1l9S8JQjaDa3x0(Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->lambda$onCameraSessionConfigured$3()V

    return-void
.end method

.method public static synthetic $r8$lambda$GPZ33jvpFhFSwUrIE-QO8a5yYq0(Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;Landroid/graphics/SurfaceTexture;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->lambda$createInputSurface$5(Landroid/graphics/SurfaceTexture;)V

    return-void
.end method

.method public static synthetic $r8$lambda$Is280eD-ff6QwTALnH_N7zZF4UQ(Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;Landroid/os/Handler;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1, p2, p3}, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->lambda$beginCameraSwitch$2(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;Landroid/os/Handler;)V

    return-void
.end method

.method public static synthetic $r8$lambda$V8xLYOIrG0lh7qmwkOuU5hwa8kM(Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;Landroid/util/Size;IZ[Ljava/lang/RuntimeException;Ljava/util/concurrent/CountDownLatch;)V
    .locals 0

    .line 0
    invoke-direct/range {p0 .. p5}, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->lambda$updateInputConfiguration$4(Landroid/util/Size;IZ[Ljava/lang/RuntimeException;Ljava/util/concurrent/CountDownLatch;)V

    return-void
.end method

.method public static synthetic $r8$lambda$bZxn_lC6iqli2fv2POwKxF1ARbE(Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;Ljava/util/concurrent/CountDownLatch;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->lambda$start$0(Ljava/util/concurrent/CountDownLatch;)V

    return-void
.end method

.method public static synthetic $r8$lambda$ogB9YLpzHQpiMC09pZ4ncBfs1Zc(Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->drawSwitchFrame()V

    return-void
.end method

.method public static synthetic $r8$lambda$rt7ow4nR2CaQWIcuh_2PxTqRnOM(Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;Landroid/os/HandlerThread;Ljava/util/concurrent/CountDownLatch;)V
    .locals 0

    .line 0
    invoke-direct {p0, p1, p2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->lambda$stop$1(Landroid/os/HandlerThread;Ljava/util/concurrent/CountDownLatch;)V

    return-void
.end method

.method public constructor <init>(Landroid/util/Size;Landroid/view/Surface;IIZZLorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;JLorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor$FrameTimingListener;Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor$ErrorListener;)V
    .locals 3

    .line 124
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    const-wide v0, 0x7fffffffffffffffL

    .line 54
    iput-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->presentationTimeLimitNs:J

    .line 63
    sget-object v0, Landroid/opengl/EGL14;->EGL_NO_DISPLAY:Landroid/opengl/EGLDisplay;

    iput-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->eglDisplay:Landroid/opengl/EGLDisplay;

    .line 64
    sget-object v0, Landroid/opengl/EGL14;->EGL_NO_CONTEXT:Landroid/opengl/EGLContext;

    iput-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->eglContext:Landroid/opengl/EGLContext;

    .line 65
    sget-object v0, Landroid/opengl/EGL14;->EGL_NO_SURFACE:Landroid/opengl/EGLSurface;

    iput-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->eglSurface:Landroid/opengl/EGLSurface;

    const/4 v0, 0x1

    .line 70
    new-array v1, v0, [I

    iput-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->eglSurfaceValue:[I

    const/16 v1, 0x10

    .line 71
    new-array v1, v1, [F

    iput-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->textureMatrix:[F

    const-wide/16 v1, -0x1

    .line 76
    iput-wide v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->firstCameraTimestamp:J

    .line 78
    iput-wide v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->lastPresentationTime:J

    .line 111
    new-instance v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor$$ExternalSyntheticLambda1;

    invoke-direct {v1, p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor$$ExternalSyntheticLambda1;-><init>(Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;)V

    iput-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->switchFrameRunnable:Ljava/lang/Runnable;

    .line 125
    iput-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->inputSize:Landroid/util/Size;

    .line 126
    iput-object p2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->outputSurface:Landroid/view/Surface;

    .line 127
    iput p3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->outputSize:I

    .line 128
    iput p4, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->sourceCropSize:I

    .line 129
    iput-boolean p5, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->cameraTimestampRealtime:Z

    .line 130
    iput-boolean p6, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->compositionEnabled:Z

    .line 131
    iput-object p7, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->diagnostics:Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;

    .line 132
    iput p3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->outputWidth:I

    .line 133
    iput p3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->outputHeight:I

    .line 134
    iput-wide p8, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->presentationTimeOriginNs:J

    const-wide/16 p1, 0x0

    cmp-long p1, p8, p1

    if-eqz p1, :cond_0

    goto :goto_0

    :cond_0
    const/4 v0, 0x0

    .line 135
    :goto_0
    iput-boolean v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->recordingStarted:Z

    .line 136
    iput-object p10, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->frameTimingListener:Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor$FrameTimingListener;

    .line 137
    iput-object p11, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->errorListener:Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor$ErrorListener;

    return-void
.end method

.method private static checkEglObject(ZLjava/lang/String;)V
    .locals 1

    if-eqz p0, :cond_0

    return-void

    .line 854
    :cond_0
    invoke-static {}, Landroid/opengl/EGL14;->eglGetError()I

    move-result p0

    invoke-static {p0}, Ljava/lang/Integer;->toHexString(I)Ljava/lang/String;

    move-result-object p0

    const-string v0, ": 0x"

    invoke-static {p1, v0, p0}, Landroidx/camera/core/processing/util/GLUtils$$ExternalSyntheticBUOutline0;->m(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)V

    return-void
.end method

.method private static clamp(III)I
    .locals 0

    .line 725
    invoke-static {p2, p0}, Ljava/lang/Math;->min(II)I

    move-result p0

    invoke-static {p1, p0}, Ljava/lang/Math;->max(II)I

    move-result p0

    return p0
.end method

.method private static clamp01(F)F
    .locals 1

    const/high16 v0, 0x3f800000    # 1.0f

    .line 682
    invoke-static {v0, p0}, Ljava/lang/Math;->min(FF)F

    move-result p0

    const/4 v0, 0x0

    invoke-static {v0, p0}, Ljava/lang/Math;->max(FF)F

    move-result p0

    return p0
.end method

.method private configureSwitchTiming(I)V
    .locals 5

    .line 713
    iput p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->switchExpectedWaitMs:I

    add-int/lit16 p1, p1, -0x190

    const/4 v0, 0x0

    .line 715
    invoke-static {v0, p1}, Ljava/lang/Math;->max(II)I

    move-result p1

    mul-int/lit8 p1, p1, 0x3

    div-int/lit8 p1, p1, 0x14

    const/16 v0, 0x12c

    rsub-int p1, p1, 0x12c

    const/16 v1, 0xd2

    .line 714
    invoke-static {p1, v1, v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->clamp(III)I

    move-result p1

    mul-int/lit8 v0, p1, 0x2d

    .line 719
    div-int/lit8 v0, v0, 0x64

    int-to-long v1, v0

    const-wide/32 v3, 0xf4240

    mul-long/2addr v1, v3

    .line 720
    iput-wide v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->switchCrossfadeDurationNs:J

    sub-int/2addr p1, v0

    int-to-long v0, p1

    mul-long/2addr v0, v3

    .line 721
    iput-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->switchUnblurDurationNs:J

    return-void
.end method

.method private createInputSurface()V
    .locals 3

    const/4 v0, 0x1

    .line 408
    new-array v1, v0, [I

    const/4 v2, 0x0

    .line 409
    invoke-static {v0, v1, v2}, Landroid/opengl/GLES20;->glGenTextures(I[II)V

    .line 410
    aget v0, v1, v2

    iput v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->textureId:I

    const v1, 0x8d65

    .line 411
    invoke-static {v1, v0}, Landroid/opengl/GLES20;->glBindTexture(II)V

    .line 412
    invoke-direct {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->setInputTextureFilter()V

    const/16 v0, 0x2802

    const v2, 0x812f

    .line 413
    invoke-static {v1, v0, v2}, Landroid/opengl/GLES20;->glTexParameteri(III)V

    const/16 v0, 0x2803

    .line 418
    invoke-static {v1, v0, v2}, Landroid/opengl/GLES20;->glTexParameteri(III)V

    .line 424
    new-instance v0, Landroid/graphics/SurfaceTexture;

    iget v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->textureId:I

    invoke-direct {v0, v1}, Landroid/graphics/SurfaceTexture;-><init>(I)V

    iput-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->inputSurfaceTexture:Landroid/graphics/SurfaceTexture;

    .line 425
    iget-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->inputSize:Landroid/util/Size;

    invoke-virtual {v1}, Landroid/util/Size;->getWidth()I

    move-result v1

    iget-object v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->inputSize:Landroid/util/Size;

    invoke-virtual {v2}, Landroid/util/Size;->getHeight()I

    move-result v2

    invoke-virtual {v0, v1, v2}, Landroid/graphics/SurfaceTexture;->setDefaultBufferSize(II)V

    .line 426
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->inputSurfaceTexture:Landroid/graphics/SurfaceTexture;

    new-instance v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor$$ExternalSyntheticLambda0;

    invoke-direct {v1, p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor$$ExternalSyntheticLambda0;-><init>(Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;)V

    iget-object v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->handler:Landroid/os/Handler;

    invoke-virtual {v0, v1, v2}, Landroid/graphics/SurfaceTexture;->setOnFrameAvailableListener(Landroid/graphics/SurfaceTexture$OnFrameAvailableListener;Landroid/os/Handler;)V

    .line 427
    new-instance v0, Landroid/view/Surface;

    iget-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->inputSurfaceTexture:Landroid/graphics/SurfaceTexture;

    invoke-direct {v0, v1}, Landroid/view/Surface;-><init>(Landroid/graphics/SurfaceTexture;)V

    iput-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->cameraSurface:Landroid/view/Surface;

    return-void
.end method

.method private drawFrame()V
    .locals 15

    .line 446
    const-string v0, "camera switch first new frame: waitMs="

    const-string v1, "dropping pre-origin camera frame: deltaUs="

    const-string v2, "first GL input frame: cameraTimestampNs="

    iget-boolean v3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->started:Z

    if-eqz v3, :cond_c

    iget-object v3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->inputSurfaceTexture:Landroid/graphics/SurfaceTexture;

    if-nez v3, :cond_0

    goto/16 :goto_2

    .line 450
    :cond_0
    :try_start_0
    invoke-virtual {v3}, Landroid/graphics/SurfaceTexture;->updateTexImage()V

    .line 451
    iget-object v3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->inputSurfaceTexture:Landroid/graphics/SurfaceTexture;

    iget-object v4, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->textureMatrix:[F

    invoke-virtual {v3, v4}, Landroid/graphics/SurfaceTexture;->getTransformMatrix([F)V

    .line 452
    iget-object v3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->inputSurfaceTexture:Landroid/graphics/SurfaceTexture;

    invoke-virtual {v3}, Landroid/graphics/SurfaceTexture;->getTimestamp()J

    move-result-wide v3

    .line 453
    iget-wide v5, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->inputFrames:J

    const-wide/16 v7, 0x1

    add-long/2addr v5, v7

    iput-wide v5, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->inputFrames:J

    cmp-long v5, v5, v7

    if-nez v5, :cond_1

    .line 455
    iget-object v5, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->diagnostics:Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;

    new-instance v6, Ljava/lang/StringBuilder;

    invoke-direct {v6, v2}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v6, v3, v4}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    const-string v2, ", elapsedMs="

    invoke-virtual {v6, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-wide v9, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->glStartedNs:J

    .line 456
    invoke-static {v9, v10}, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->elapsedMs(J)J

    move-result-wide v9

    invoke-virtual {v6, v9, v10}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    invoke-virtual {v6}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    .line 455
    invoke-virtual {v5, v2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;->log(Ljava/lang/String;)V

    goto :goto_0

    :catch_0
    move-exception v0

    goto/16 :goto_1

    .line 458
    :cond_1
    :goto_0
    iget-boolean v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->recordingStarted:Z

    const/4 v5, 0x1

    if-nez v2, :cond_2

    .line 459
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->firstFrameListener:Ljava/lang/Runnable;

    .line 460
    iget-boolean v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->firstFrameReported:Z

    if-nez v1, :cond_c

    if-eqz v0, :cond_c

    .line 461
    iput-boolean v5, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->firstFrameReported:Z

    .line 462
    invoke-interface {v0}, Ljava/lang/Runnable;->run()V

    return-void

    .line 466
    :cond_2
    iget-boolean v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->cameraTimestampRealtime:Z

    if-eqz v2, :cond_3

    iget-wide v9, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->presentationTimeOriginNs:J

    cmp-long v2, v3, v9

    if-gez v2, :cond_3

    .line 467
    iget-wide v5, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->preOriginFrames:J

    add-long/2addr v5, v7

    iput-wide v5, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->preOriginFrames:J

    cmp-long v0, v5, v7

    if-nez v0, :cond_c

    .line 469
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->diagnostics:Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2, v1}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    iget-wide v5, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->presentationTimeOriginNs:J

    sub-long/2addr v3, v5

    const-wide/16 v5, 0x3e8

    div-long/2addr v3, v5

    invoke-virtual {v2, v3, v4}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;->log(Ljava/lang/String;)V

    return-void

    .line 474
    :cond_3
    invoke-static {}, Landroid/os/SystemClock;->elapsedRealtimeNanos()J

    move-result-wide v8

    .line 475
    iget-wide v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->lastFrameTime:J

    const-wide/16 v6, 0x0

    cmp-long v10, v1, v6

    const-wide/32 v11, 0x2faf080

    if-eqz v10, :cond_4

    sub-long v1, v8, v1

    .line 477
    iget-wide v13, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->maximumFrameGapNs:J

    invoke-static {v13, v14, v1, v2}, Ljava/lang/Math;->max(JJ)J

    move-result-wide v13

    iput-wide v13, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->maximumFrameGapNs:J

    cmp-long v1, v1, v11

    if-lez v1, :cond_4

    .line 478
    iget v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->largeFrameGaps:I

    add-int/2addr v1, v5

    iput v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->largeFrameGaps:I

    .line 480
    :cond_4
    iget-wide v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->lastFrameTime:J

    cmp-long v6, v1, v6

    if-eqz v6, :cond_5

    sub-long v1, v8, v1

    cmp-long v1, v1, v11

    if-gtz v1, :cond_5

    iget v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->outputSizeCheckFrame:I

    add-int/lit8 v2, v1, 0x1

    iput v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->outputSizeCheckFrame:I

    rem-int/lit8 v1, v1, 0x3c

    if-nez v1, :cond_6

    .line 483
    :cond_5
    invoke-direct {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->updateOutputSize()V

    .line 485
    :cond_6
    iput-wide v8, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->lastFrameTime:J

    .line 486
    iget v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->switchState:I

    if-eqz v1, :cond_b

    .line 487
    iget-boolean v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->newCameraSessionReady:Z

    if-nez v1, :cond_7

    goto/16 :goto_2

    .line 490
    :cond_7
    iget-boolean v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->hasNewCameraFrame:Z

    .line 491
    iget-object v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->overlayRenderer:Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;

    iget v3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->textureId:I

    iget-object v4, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->textureMatrix:[F

    invoke-virtual {v2, v3, v4, v5}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->captureSwitchFrame(I[FZ)V

    .line 492
    iput-boolean v5, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->hasNewCameraFrame:Z

    const/4 v2, 0x3

    if-nez v1, :cond_8

    .line 494
    invoke-static {}, Landroid/os/SystemClock;->elapsedRealtimeNanos()J

    move-result-wide v3

    .line 495
    iget-wide v5, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->cameraSwitchStartNs:J

    sub-long v5, v3, v5

    .line 496
    iget-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->switchFromFacing:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;

    iget-object v7, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->switchToFacing:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;

    long-to-float v5, v5

    const v6, 0x49742400    # 1000000.0f

    div-float/2addr v5, v6

    .line 499
    invoke-static {v5}, Ljava/lang/Math;->round(F)I

    move-result v6

    .line 496
    invoke-static {v1, v7, v6}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSwitchTimingStore;->recordAndGetAverageMs(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;I)I

    move-result v1

    .line 501
    iget-object v6, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->diagnostics:Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;

    new-instance v7, Ljava/lang/StringBuilder;

    invoke-direct {v7, v0}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v7, v5}, Ljava/lang/StringBuilder;->append(F)Ljava/lang/StringBuilder;

    const-string v0, ", rollingAverageMs="

    invoke-virtual {v7, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v7, v1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v7}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v6, v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;->log(Ljava/lang/String;)V

    .line 504
    iput v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->switchState:I

    .line 505
    iput-wide v3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->switchStageStartNs:J

    .line 507
    :cond_8
    iget v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->switchState:I

    if-eq v0, v2, :cond_9

    const/4 v1, 0x4

    if-ne v0, v1, :cond_c

    .line 508
    :cond_9
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->handler:Landroid/os/Handler;

    if-eqz v0, :cond_a

    .line 510
    iget-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->switchFrameRunnable:Ljava/lang/Runnable;

    invoke-virtual {v0, v1}, Landroid/os/Handler;->removeCallbacks(Ljava/lang/Runnable;)V

    .line 512
    :cond_a
    invoke-direct {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->drawSwitchFrame()V

    return-void

    .line 517
    :cond_b
    iput-boolean v5, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->hasCameraFrame:Z

    .line 527
    iget-object v5, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->overlayRenderer:Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;

    iget v6, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->textureId:I

    iget-object v7, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->textureMatrix:[F

    iget v10, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->outputWidth:I

    iget v11, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->outputHeight:I

    invoke-virtual/range {v5 .. v11}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->render(I[FJII)V

    .line 547
    invoke-direct {p0, v3, v4}, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->getCameraPresentationTime(J)J

    move-result-wide v0

    .line 548
    iget-wide v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->presentationTimeLimitNs:J

    cmp-long v2, v0, v2

    if-gez v2, :cond_c

    .line 549
    invoke-direct {p0, v0, v1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->submitFrame(J)V
    :try_end_0
    .catch Ljava/lang/RuntimeException; {:try_start_0 .. :try_end_0} :catch_0

    return-void

    .line 552
    :goto_1
    invoke-direct {p0, v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->reportError(Ljava/lang/RuntimeException;)V

    :cond_c
    :goto_2
    return-void
.end method

.method private drawSwitchFrame()V
    .locals 19

    move-object/from16 v1, p0

    .line 557
    const-string v0, "synthetic camera switch completed: elapsedMs="

    iget-boolean v2, v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->started:Z

    if-eqz v2, :cond_e

    iget v2, v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->switchState:I

    if-nez v2, :cond_0

    goto/16 :goto_b

    :cond_0
    const/4 v2, 0x0

    .line 561
    :try_start_0
    invoke-static {}, Landroid/os/SystemClock;->elapsedRealtimeNanos()J

    move-result-wide v3

    .line 564
    iget-wide v5, v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->switchStageStartNs:J

    sub-long v5, v3, v5

    .line 566
    iget v7, v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->switchState:I
    :try_end_0
    .catch Ljava/lang/RuntimeException; {:try_start_0 .. :try_end_0} :catch_1

    const/4 v8, 0x2

    const/4 v9, 0x3

    const/4 v10, 0x1

    const/high16 v11, 0x3f800000    # 1.0f

    const/4 v12, 0x4

    if-ne v7, v10, :cond_2

    .line 567
    :try_start_1
    invoke-direct {v1, v3, v4}, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->getSwitchOldBlurRadiusPx(J)F

    move-result v7

    iput v7, v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->switchOldBlurRadiusPx:F

    .line 568
    invoke-direct {v1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->getExpectedSwitchWaitNs()J

    move-result-wide v13

    cmp-long v7, v5, v13

    if-ltz v7, :cond_4

    .line 569
    iget-boolean v7, v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->hasNewCameraFrame:Z

    if-eqz v7, :cond_1

    move v7, v9

    goto :goto_0

    :cond_1
    move v7, v8

    :goto_0
    iput v7, v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->switchState:I

    .line 570
    iput-wide v3, v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->switchStageStartNs:J

    goto :goto_1

    :catch_0
    move-exception v0

    move v15, v2

    goto/16 :goto_a

    :cond_2
    if-ne v7, v8, :cond_3

    .line 573
    invoke-direct {v1, v3, v4}, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->getSwitchOldBlurRadiusPx(J)F

    move-result v7

    iput v7, v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->switchOldBlurRadiusPx:F
    :try_end_1
    .catch Ljava/lang/RuntimeException; {:try_start_1 .. :try_end_1} :catch_0

    goto :goto_1

    :cond_3
    if-eq v7, v9, :cond_5

    if-ne v7, v12, :cond_4

    goto :goto_3

    :cond_4
    :goto_1
    const/4 v7, 0x0

    :goto_2
    move/from16 v16, v7

    move v15, v11

    goto :goto_4

    .line 576
    :cond_5
    :goto_3
    :try_start_2
    iget-wide v13, v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->switchCrossfadeDurationNs:J
    :try_end_2
    .catch Ljava/lang/RuntimeException; {:try_start_2 .. :try_end_2} :catch_1

    cmp-long v7, v5, v13

    if-gez v7, :cond_6

    long-to-float v7, v5

    long-to-float v13, v13

    div-float/2addr v7, v13

    .line 577
    :try_start_3
    invoke-static {v7}, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->smoothStep(F)F

    move-result v7
    :try_end_3
    .catch Ljava/lang/RuntimeException; {:try_start_3 .. :try_end_3} :catch_0

    goto :goto_2

    .line 582
    :cond_6
    :try_start_4
    iput v12, v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->switchState:I

    sub-long v13, v5, v13

    long-to-float v7, v13

    .line 585
    iget-wide v13, v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->switchUnblurDurationNs:J

    long-to-float v13, v13

    div-float/2addr v7, v13

    invoke-static {v7}, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->smoothStep(F)F

    move-result v7

    sub-float v7, v11, v7

    move v15, v7

    move/from16 v16, v11

    .line 591
    :goto_4
    iget-object v13, v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->overlayRenderer:Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;

    iget v14, v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->switchOldBlurRadiusPx:F

    iget v7, v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->outputWidth:I

    iget v11, v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->outputHeight:I

    move/from16 v17, v7

    move/from16 v18, v11

    invoke-virtual/range {v13 .. v18}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->renderSwitch(FFFII)V

    .line 598
    iget v7, v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->syntheticFrames:I

    add-int/2addr v7, v10

    iput v7, v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->syntheticFrames:I

    .line 599
    invoke-direct {v1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->getRealtimePresentationTime()J

    move-result-wide v13

    .line 600
    iget-wide v10, v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->presentationTimeLimitNs:J
    :try_end_4
    .catch Ljava/lang/RuntimeException; {:try_start_4 .. :try_end_4} :catch_1

    cmp-long v10, v13, v10

    if-gez v10, :cond_7

    .line 601
    :try_start_5
    invoke-direct {v1, v13, v14}, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->submitFrame(J)V
    :try_end_5
    .catch Ljava/lang/RuntimeException; {:try_start_5 .. :try_end_5} :catch_0

    .line 603
    :cond_7
    :try_start_6
    iget-wide v10, v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->switchCrossfadeDurationNs:J

    iget-wide v13, v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->switchUnblurDurationNs:J

    add-long/2addr v10, v13

    .line 604
    iget v13, v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->switchState:I

    if-ne v13, v12, :cond_8

    cmp-long v13, v5, v10

    if-ltz v13, :cond_8

    .line 605
    iget-object v13, v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->overlayRenderer:Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;

    invoke-virtual {v13, v3, v4}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->finishCameraSwitch(J)V

    .line 606
    iput v2, v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->switchState:I

    .line 607
    iget-object v13, v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->diagnostics:Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;

    new-instance v14, Ljava/lang/StringBuilder;

    invoke-direct {v14, v0}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    move-wide/from16 v16, v3

    iget-wide v2, v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->cameraSwitchStartNs:J

    sub-long v3, v16, v2

    long-to-float v0, v3

    const v2, 0x49742400    # 1000000.0f

    div-float/2addr v0, v2

    invoke-virtual {v14, v0}, Ljava/lang/StringBuilder;->append(F)Ljava/lang/StringBuilder;

    const-string v0, ", syntheticFrames="

    invoke-virtual {v14, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget v0, v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->syntheticFrames:I

    invoke-virtual {v14, v0}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v14}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v13, v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;->log(Ljava/lang/String;)V

    goto :goto_5

    :catch_1
    move-exception v0

    const/4 v15, 0x0

    goto :goto_a

    .line 611
    :cond_8
    :goto_5
    iget v0, v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->switchState:I

    if-eqz v0, :cond_e

    iget-object v2, v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->handler:Landroid/os/Handler;

    if-eqz v2, :cond_e

    .line 613
    iget-boolean v2, v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->hasNewCameraFrame:Z

    if-eqz v2, :cond_a

    if-eq v0, v9, :cond_9

    if-ne v0, v12, :cond_a

    :cond_9
    const/4 v7, 0x1

    goto :goto_6

    :cond_a
    const/4 v7, 0x0

    :goto_6
    if-eqz v7, :cond_b

    sub-long/2addr v10, v5

    goto :goto_7

    .line 618
    :cond_b
    invoke-direct {v1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->getExpectedSwitchWaitNs()J

    move-result-wide v2

    sub-long v10, v2, v5

    :goto_7
    const-wide/16 v2, 0x21

    if-eqz v7, :cond_c

    const-wide/16 v4, 0x46

    goto :goto_8

    :cond_c
    move-wide v4, v2

    .line 622
    :goto_8
    iget v0, v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->switchState:I

    if-ne v0, v8, :cond_d

    goto :goto_9

    :cond_d
    const-wide/32 v2, 0xf423f

    add-long/2addr v10, v2

    const-wide/32 v2, 0xf4240

    .line 624
    div-long/2addr v10, v2

    const-wide/16 v2, 0x1

    invoke-static {v2, v3, v10, v11}, Ljava/lang/Math;->max(JJ)J

    move-result-wide v2

    .line 625
    :goto_9
    iget-object v0, v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->handler:Landroid/os/Handler;

    iget-object v6, v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->switchFrameRunnable:Ljava/lang/Runnable;

    .line 627
    invoke-static {v4, v5, v2, v3}, Ljava/lang/Math;->min(JJ)J

    move-result-wide v2

    .line 625
    invoke-virtual {v0, v6, v2, v3}, Landroid/os/Handler;->postDelayed(Ljava/lang/Runnable;J)Z
    :try_end_6
    .catch Ljava/lang/RuntimeException; {:try_start_6 .. :try_end_6} :catch_1

    return-void

    .line 631
    :goto_a
    iput v15, v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->switchState:I

    .line 632
    invoke-direct {v1, v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->reportError(Ljava/lang/RuntimeException;)V

    :cond_e
    :goto_b
    return-void
.end method

.method private static easeOutQuart(F)F
    .locals 2

    .line 691
    invoke-static {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->clamp01(F)F

    move-result p0

    const/high16 v0, 0x3f800000    # 1.0f

    sub-float p0, v0, p0

    mul-float v1, p0, p0

    mul-float/2addr v1, p0

    mul-float/2addr v1, p0

    sub-float/2addr v0, v1

    return v0
.end method

.method private static elapsedMs(J)J
    .locals 2

    .line 734
    invoke-static {}, Ljava/lang/System;->nanoTime()J

    move-result-wide v0

    sub-long/2addr v0, p0

    const-wide/32 p0, 0xf4240

    div-long/2addr v0, p0

    return-wide v0
.end method

.method private static frameRate(JJ)Ljava/lang/String;
    .locals 2

    const-wide/16 v0, 0x0

    cmp-long v0, p2, v0

    if-gtz v0, :cond_0

    .line 860
    const-string p0, "n/a"

    return-object p0

    :cond_0
    long-to-double p0, p0

    const-wide v0, 0x41cdcd6500000000L    # 1.0E9

    mul-double/2addr p0, v0

    long-to-double p2, p2

    div-double/2addr p0, p2

    invoke-static {p0, p1}, Ljava/lang/String;->valueOf(D)Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method private getCameraPresentationTime(J)J
    .locals 6

    .line 643
    iget-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->presentationTimeOriginNs:J

    const-wide/16 v2, 0x0

    cmp-long v0, v0, v2

    if-eqz v0, :cond_2

    .line 644
    iget-boolean v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->cameraTimestampRealtime:Z

    if-eqz v0, :cond_0

    .line 645
    iget-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->presentationTimeOriginNs:J

    sub-long/2addr p1, v0

    invoke-static {v2, v3, p1, p2}, Ljava/lang/Math;->max(JJ)J

    move-result-wide p1

    goto :goto_0

    .line 647
    :cond_0
    iget-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->firstCameraTimestamp:J

    cmp-long v0, v0, v2

    if-gez v0, :cond_1

    .line 648
    iput-wide p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->firstCameraTimestamp:J

    .line 649
    invoke-direct {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->getRealtimePresentationTime()J

    move-result-wide v0

    iput-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->firstPresentationTime:J

    .line 651
    :cond_1
    iget-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->firstPresentationTime:J

    iget-wide v4, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->firstCameraTimestamp:J

    sub-long/2addr p1, v4

    .line 652
    invoke-static {v2, v3, p1, p2}, Ljava/lang/Math;->max(JJ)J

    move-result-wide p1

    add-long/2addr p1, v0

    .line 655
    :cond_2
    :goto_0
    iget-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->lastPresentationTime:J

    const-wide/16 v2, 0x1

    add-long/2addr v0, v2

    invoke-static {p1, p2, v0, v1}, Ljava/lang/Math;->max(JJ)J

    move-result-wide p0

    return-wide p0
.end method

.method private getExpectedSwitchWaitNs()J
    .locals 4

    .line 709
    iget p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->switchExpectedWaitMs:I

    int-to-long v0, p0

    const-wide/16 v2, 0x21

    invoke-static {v2, v3, v0, v1}, Ljava/lang/Math;->max(JJ)J

    move-result-wide v0

    const-wide/32 v2, 0xf4240

    mul-long/2addr v0, v2

    return-wide v0
.end method

.method private getRealtimePresentationTime()J
    .locals 6

    .line 659
    iget-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->presentationTimeOriginNs:J

    const-wide/16 v2, 0x0

    cmp-long v0, v0, v2

    if-nez v0, :cond_0

    .line 660
    iget-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->lastPresentationTime:J

    const-wide/32 v2, 0x1fca055

    add-long/2addr v0, v2

    goto :goto_0

    .line 661
    :cond_0
    invoke-static {}, Landroid/os/SystemClock;->elapsedRealtimeNanos()J

    move-result-wide v0

    iget-wide v4, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->presentationTimeOriginNs:J

    sub-long/2addr v0, v4

    invoke-static {v2, v3, v0, v1}, Ljava/lang/Math;->max(JJ)J

    move-result-wide v0

    .line 662
    :goto_0
    iget-wide v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->lastPresentationTime:J

    const-wide/16 v4, 0x1

    add-long/2addr v2, v4

    invoke-static {v0, v1, v2, v3}, Ljava/lang/Math;->max(JJ)J

    move-result-wide v0

    return-wide v0
.end method

.method private getSwitchOldBlurRadiusPx(J)F
    .locals 3

    .line 696
    iget-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->cameraSwitchStartNs:J

    sub-long/2addr p1, v0

    const-wide/16 v0, 0x0

    invoke-static {v0, v1, p1, p2}, Ljava/lang/Math;->max(JJ)J

    move-result-wide p1

    .line 697
    invoke-direct {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->getExpectedSwitchWaitNs()J

    move-result-wide v0

    .line 698
    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->overlayRenderer:Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;

    invoke-virtual {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->getSwitchTargetBlurRadiusPx()F

    move-result p0

    cmp-long v2, p1, v0

    if-gtz v2, :cond_0

    long-to-float p1, p1

    long-to-float p2, v0

    div-float/2addr p1, p2

    .line 700
    invoke-static {p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->easeOutQuart(F)F

    move-result p1

    :goto_0
    mul-float/2addr p0, p1

    return p0

    :cond_0
    sub-long/2addr p1, v0

    long-to-float p1, p1

    long-to-float p2, v0

    div-float/2addr p1, p2

    float-to-double p1, p1

    .line 704
    invoke-static {p1, p2}, Ljava/lang/Math;->log1p(D)D

    move-result-wide p1

    const-wide v0, 0x3fd6666660000000L    # 0.3499999940395355

    mul-double/2addr p1, v0

    const-wide/high16 v0, 0x3ff0000000000000L    # 1.0

    add-double/2addr p1, v0

    .line 703
    invoke-static {p1, p2}, Ljava/lang/Math;->sqrt(D)D

    move-result-wide p1

    double-to-float p1, p1

    goto :goto_0
.end method

.method private initializeGl()V
    .locals 13

    const/4 v0, 0x0

    .line 334
    invoke-static {v0}, Landroid/opengl/EGL14;->eglGetDisplay(I)Landroid/opengl/EGLDisplay;

    move-result-object v1

    iput-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->eglDisplay:Landroid/opengl/EGLDisplay;

    .line 335
    sget-object v2, Landroid/opengl/EGL14;->EGL_NO_DISPLAY:Landroid/opengl/EGLDisplay;

    if-eq v1, v2, :cond_4

    const/4 v2, 0x2

    .line 339
    new-array v3, v2, [I

    const/4 v4, 0x1

    .line 340
    invoke-static {v1, v3, v0, v3, v4}, Landroid/opengl/EGL14;->eglInitialize(Landroid/opengl/EGLDisplay;[II[II)Z

    move-result v1

    if-eqz v1, :cond_3

    const/16 v1, 0xd

    .line 344
    new-array v6, v1, [I

    fill-array-data v6, :array_0

    const/4 v10, 0x1

    .line 353
    new-array v8, v10, [Landroid/opengl/EGLConfig;

    .line 354
    new-array v11, v4, [I

    .line 355
    iget-object v5, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->eglDisplay:Landroid/opengl/EGLDisplay;

    const/4 v9, 0x0

    const/4 v12, 0x0

    const/4 v7, 0x0

    invoke-static/range {v5 .. v12}, Landroid/opengl/EGL14;->eglChooseConfig(Landroid/opengl/EGLDisplay;[II[Landroid/opengl/EGLConfig;II[II)Z

    move-result v1

    if-eqz v1, :cond_2

    aget v1, v11, v0

    if-eqz v1, :cond_2

    const/16 v1, 0x3098

    const/16 v5, 0x3038

    .line 368
    filled-new-array {v1, v2, v5}, [I

    move-result-object v1

    .line 372
    iget-object v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->eglDisplay:Landroid/opengl/EGLDisplay;

    aget-object v6, v8, v0

    sget-object v7, Landroid/opengl/EGL14;->EGL_NO_CONTEXT:Landroid/opengl/EGLContext;

    invoke-static {v2, v6, v7, v1, v0}, Landroid/opengl/EGL14;->eglCreateContext(Landroid/opengl/EGLDisplay;Landroid/opengl/EGLConfig;Landroid/opengl/EGLContext;[II)Landroid/opengl/EGLContext;

    move-result-object v1

    iput-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->eglContext:Landroid/opengl/EGLContext;

    .line 379
    sget-object v2, Landroid/opengl/EGL14;->EGL_NO_CONTEXT:Landroid/opengl/EGLContext;

    if-eq v1, v2, :cond_0

    move v1, v4

    goto :goto_0

    :cond_0
    move v1, v0

    :goto_0
    const-string v2, "Unable to create EGL context"

    invoke-static {v1, v2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->checkEglObject(ZLjava/lang/String;)V

    .line 381
    filled-new-array {v5}, [I

    move-result-object v1

    .line 382
    iget-object v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->eglDisplay:Landroid/opengl/EGLDisplay;

    aget-object v5, v8, v0

    iget-object v6, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->outputSurface:Landroid/view/Surface;

    invoke-static {v2, v5, v6, v1, v0}, Landroid/opengl/EGL14;->eglCreateWindowSurface(Landroid/opengl/EGLDisplay;Landroid/opengl/EGLConfig;Ljava/lang/Object;[II)Landroid/opengl/EGLSurface;

    move-result-object v1

    iput-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->eglSurface:Landroid/opengl/EGLSurface;

    .line 389
    sget-object v2, Landroid/opengl/EGL14;->EGL_NO_SURFACE:Landroid/opengl/EGLSurface;

    if-eq v1, v2, :cond_1

    move v1, v4

    goto :goto_1

    :cond_1
    move v1, v0

    :goto_1
    const-string v2, "Unable to create EGL surface"

    invoke-static {v1, v2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->checkEglObject(ZLjava/lang/String;)V

    .line 391
    invoke-direct {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->makeCurrent()V

    .line 392
    iget-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->diagnostics:Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;

    new-instance v2, Ljava/lang/StringBuilder;

    const-string v5, "GL initialized: egl="

    invoke-direct {v2, v5}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    aget v5, v3, v0

    invoke-virtual {v2, v5}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string v5, "."

    invoke-virtual {v2, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    aget v3, v3, v4

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string v3, ", vendor="

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const/16 v3, 0x1f00

    .line 393
    invoke-static {v3}, Landroid/opengl/GLES20;->glGetString(I)Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v3, ", renderer="

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const/16 v3, 0x1f01

    .line 394
    invoke-static {v3}, Landroid/opengl/GLES20;->glGetString(I)Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v3, ", version="

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const/16 v3, 0x1f02

    .line 395
    invoke-static {v3}, Landroid/opengl/GLES20;->glGetString(I)Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v3, ", elapsedMs="

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-wide v3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->glStartedNs:J

    .line 396
    invoke-static {v3, v4}, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->elapsedMs(J)J

    move-result-wide v3

    invoke-virtual {v2, v3, v4}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    .line 392
    invoke-virtual {v1, v2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;->log(Ljava/lang/String;)V

    .line 397
    invoke-direct {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->createInputSurface()V

    .line 398
    new-instance v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;

    iget v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->outputSize:I

    iget-object v3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->inputSize:Landroid/util/Size;

    iget v4, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->sourceCropSize:I

    iget-boolean v5, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->compositionEnabled:Z

    invoke-direct {v1, v2, v3, v4, v5}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;-><init>(ILandroid/util/Size;IZ)V

    iput-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->overlayRenderer:Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;

    .line 404
    iget p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->outputSize:I

    invoke-static {v0, v0, p0, p0}, Landroid/opengl/GLES20;->glViewport(IIII)V

    return-void

    .line 365
    :cond_2
    const-string p0, "Unable to choose EGL config"

    invoke-static {p0}, Lokio/Segment$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-void

    .line 341
    :cond_3
    const-string p0, "Unable to initialize EGL"

    invoke-static {p0}, Lokio/Segment$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-void

    .line 336
    :cond_4
    const-string p0, "Unable to get EGL display"

    invoke-static {p0}, Lokio/Segment$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-void

    :array_0
    .array-data 4
        0x3024
        0x8
        0x3023
        0x8
        0x3022
        0x8
        0x3021
        0x8
        0x3040
        0x4
        0x3142
        0x1
        0x3038
    .end array-data
.end method

.method private inputFilterName()Ljava/lang/String;
    .locals 1

    .line 730
    iget v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->sourceCropSize:I

    iget p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->outputSize:I

    if-ne v0, p0, :cond_0

    const-string p0, "NEAREST"

    return-object p0

    :cond_0
    const-string p0, "LINEAR"

    return-object p0
.end method

.method private synthetic lambda$beginCameraSwitch$2(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;Landroid/os/Handler;)V
    .locals 4

    .line 231
    iget-boolean v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->started:Z

    if-eqz v0, :cond_1

    iget v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->switchState:I

    if-nez v0, :cond_1

    iget-boolean v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->hasCameraFrame:Z

    if-nez v0, :cond_0

    goto :goto_0

    .line 234
    :cond_0
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->overlayRenderer:Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;

    iget v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->textureId:I

    iget-object v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->textureMatrix:[F

    const/4 v3, 0x0

    invoke-virtual {v0, v1, v2, v3}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->captureSwitchFrame(I[FZ)V

    .line 235
    iput-boolean v3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->hasNewCameraFrame:Z

    .line 236
    iput-boolean v3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->newCameraSessionReady:Z

    const/4 v0, 0x0

    .line 237
    iput v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->switchOldBlurRadiusPx:F

    const/4 v0, 0x1

    .line 238
    iput v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->switchState:I

    .line 239
    invoke-static {}, Landroid/os/SystemClock;->elapsedRealtimeNanos()J

    move-result-wide v0

    iput-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->switchStageStartNs:J

    iput-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->cameraSwitchStartNs:J

    .line 240
    iput-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->switchFromFacing:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;

    .line 241
    iput-object p2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->switchToFacing:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;

    .line 242
    invoke-static {p1, p2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSwitchTimingStore;->getAverageMs(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;)I

    move-result v0

    invoke-direct {p0, v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->configureSwitchTiming(I)V

    .line 243
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->diagnostics:Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;

    new-instance v1, Ljava/lang/StringBuilder;

    const-string v2, "synthetic camera switch started: from="

    invoke-direct {v1, v2}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    const-string p1, ", to="

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    const-string p1, ", expectedWaitMs="

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->switchExpectedWaitMs:I

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string p1, ", targetBlurRadiusPx="

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->overlayRenderer:Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;

    .line 246
    invoke-virtual {p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->getSwitchTargetBlurRadiusPx()F

    move-result p1

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(F)Ljava/lang/StringBuilder;

    const-string p1, ", overdueBlurGrowth=0.35, revealMs="

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-wide p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->switchCrossfadeDurationNs:J

    iget-wide v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->switchUnblurDurationNs:J

    add-long/2addr p1, v2

    const-wide/32 v2, 0xf4240

    div-long/2addr p1, v2

    invoke-virtual {v1, p1, p2}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    .line 243
    invoke-virtual {v0, p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;->log(Ljava/lang/String;)V

    const-wide/16 p1, -0x1

    .line 250
    iput-wide p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->firstCameraTimestamp:J

    .line 251
    iget-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->switchFrameRunnable:Ljava/lang/Runnable;

    invoke-virtual {p3, p1}, Landroid/os/Handler;->removeCallbacks(Ljava/lang/Runnable;)V

    .line 252
    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->switchFrameRunnable:Ljava/lang/Runnable;

    invoke-virtual {p3, p0}, Landroid/os/Handler;->post(Ljava/lang/Runnable;)Z

    :cond_1
    :goto_0
    return-void
.end method

.method private synthetic lambda$createInputSurface$5(Landroid/graphics/SurfaceTexture;)V
    .locals 0

    .line 426
    invoke-direct {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->drawFrame()V

    return-void
.end method

.method private synthetic lambda$onCameraSessionConfigured$3()V
    .locals 1

    .line 264
    iget v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->switchState:I

    if-eqz v0, :cond_0

    const/4 v0, 0x1

    .line 265
    iput-boolean v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->newCameraSessionReady:Z

    :cond_0
    return-void
.end method

.method private synthetic lambda$start$0(Ljava/util/concurrent/CountDownLatch;)V
    .locals 1

    .line 158
    :try_start_0
    invoke-direct {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->initializeGl()V

    const/4 v0, 0x1

    .line 159
    iput-boolean v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->started:Z
    :try_end_0
    .catch Ljava/lang/RuntimeException; {:try_start_0 .. :try_end_0} :catch_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 164
    invoke-virtual {p1}, Ljava/util/concurrent/CountDownLatch;->countDown()V

    return-void

    :catchall_0
    move-exception p0

    goto :goto_0

    :catch_0
    move-exception v0

    .line 161
    :try_start_1
    iput-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->initializationError:Ljava/lang/RuntimeException;

    .line 162
    invoke-direct {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->releaseGl()V
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    .line 164
    invoke-virtual {p1}, Ljava/util/concurrent/CountDownLatch;->countDown()V

    return-void

    :goto_0
    invoke-virtual {p1}, Ljava/util/concurrent/CountDownLatch;->countDown()V

    .line 165
    throw p0
.end method

.method private synthetic lambda$stop$1(Landroid/os/HandlerThread;Ljava/util/concurrent/CountDownLatch;)V
    .locals 0

    .line 201
    :try_start_0
    invoke-direct {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->releaseGl()V
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 203
    invoke-virtual {p1}, Landroid/os/HandlerThread;->quitSafely()Z

    .line 204
    invoke-virtual {p2}, Ljava/util/concurrent/CountDownLatch;->countDown()V

    return-void

    :catchall_0
    move-exception p0

    .line 203
    invoke-virtual {p1}, Landroid/os/HandlerThread;->quitSafely()Z

    .line 204
    invoke-virtual {p2}, Ljava/util/concurrent/CountDownLatch;->countDown()V

    .line 205
    throw p0
.end method

.method private synthetic lambda$updateInputConfiguration$4(Landroid/util/Size;IZ[Ljava/lang/RuntimeException;Ljava/util/concurrent/CountDownLatch;)V
    .locals 3

    .line 303
    const-string v0, "GL input updated: input="

    :try_start_0
    iput-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->inputSize:Landroid/util/Size;

    .line 304
    iput p2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->sourceCropSize:I

    .line 305
    iput-boolean p3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->cameraTimestampRealtime:Z

    .line 306
    iget-object p3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->inputSurfaceTexture:Landroid/graphics/SurfaceTexture;

    if-eqz p3, :cond_0

    .line 307
    invoke-virtual {p1}, Landroid/util/Size;->getWidth()I

    move-result v1

    invoke-virtual {p1}, Landroid/util/Size;->getHeight()I

    move-result v2

    invoke-virtual {p3, v1, v2}, Landroid/graphics/SurfaceTexture;->setDefaultBufferSize(II)V

    goto :goto_0

    :catchall_0
    move-exception p0

    goto :goto_2

    :catch_0
    move-exception p0

    goto :goto_1

    .line 309
    :cond_0
    :goto_0
    invoke-direct {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->setInputTextureFilter()V

    .line 310
    iget-object p3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->overlayRenderer:Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;

    if-eqz p3, :cond_1

    .line 311
    invoke-virtual {p3, p1, p2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->updateInputConfiguration(Landroid/util/Size;I)V

    .line 313
    :cond_1
    iget-object p3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->diagnostics:Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1, v0}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    const-string p1, ", crop="

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, p2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string p1, ", filter="

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 315
    invoke-direct {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->inputFilterName()Ljava/lang/String;

    move-result-object p0

    invoke-virtual {v1, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    .line 313
    invoke-virtual {p3, p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;->log(Ljava/lang/String;)V
    :try_end_0
    .catch Ljava/lang/RuntimeException; {:try_start_0 .. :try_end_0} :catch_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 319
    invoke-virtual {p5}, Ljava/util/concurrent/CountDownLatch;->countDown()V

    return-void

    :goto_1
    const/4 p1, 0x0

    .line 317
    :try_start_1
    aput-object p0, p4, p1
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    .line 319
    invoke-virtual {p5}, Ljava/util/concurrent/CountDownLatch;->countDown()V

    return-void

    :goto_2
    invoke-virtual {p5}, Ljava/util/concurrent/CountDownLatch;->countDown()V

    .line 320
    throw p0
.end method

.method private makeCurrent()V
    .locals 2

    .line 749
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->eglDisplay:Landroid/opengl/EGLDisplay;

    iget-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->eglSurface:Landroid/opengl/EGLSurface;

    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->eglContext:Landroid/opengl/EGLContext;

    invoke-static {v0, v1, v1, p0}, Landroid/opengl/EGL14;->eglMakeCurrent(Landroid/opengl/EGLDisplay;Landroid/opengl/EGLSurface;Landroid/opengl/EGLSurface;Landroid/opengl/EGLContext;)Z

    move-result p0

    if-eqz p0, :cond_0

    return-void

    .line 751
    :cond_0
    invoke-static {}, Landroid/opengl/EGL14;->eglGetError()I

    move-result p0

    invoke-static {p0}, Ljava/lang/Integer;->toHexString(I)Ljava/lang/String;

    move-result-object p0

    const-string v0, "Unable to make EGL context current: 0x"

    invoke-static {v0, p0}, Lcom/android/dx/dex/code/CstInsn$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;Ljava/lang/Object;)V

    return-void
.end method

.method private recordSwapTiming(J)V
    .locals 4

    .line 738
    iget v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->swapTimingFrames:I

    add-int/lit8 v0, v0, 0x1

    iput v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->swapTimingFrames:I

    .line 739
    iget-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->swapTimingTotalNs:J

    add-long/2addr v0, p1

    iput-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->swapTimingTotalNs:J

    .line 740
    iget-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->swapTimingMaxNs:J

    invoke-static {v0, v1, p1, p2}, Ljava/lang/Math;->max(JJ)J

    move-result-wide p1

    iput-wide p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->swapTimingMaxNs:J

    .line 741
    iget p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->swapTimingFrames:I

    rem-int/lit8 p1, p1, 0x1e

    if-nez p1, :cond_0

    .line 742
    iget-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->diagnostics:Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;

    new-instance p2, Ljava/lang/StringBuilder;

    const-string v0, "encoder swap: average="

    invoke-direct {p2, v0}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    iget-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->swapTimingTotalNs:J

    long-to-float v0, v0

    iget v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->swapTimingFrames:I

    int-to-float v1, v1

    div-float/2addr v0, v1

    const v1, 0x49742400    # 1000000.0f

    div-float/2addr v0, v1

    invoke-virtual {p2, v0}, Ljava/lang/StringBuilder;->append(F)Ljava/lang/StringBuilder;

    const-string v0, " ms, max="

    invoke-virtual {p2, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-wide v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->swapTimingMaxNs:J

    long-to-float p0, v2

    div-float/2addr p0, v1

    invoke-virtual {p2, p0}, Ljava/lang/StringBuilder;->append(F)Ljava/lang/StringBuilder;

    const-string p0, " ms"

    invoke-virtual {p2, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    invoke-virtual {p1, p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;->log(Ljava/lang/String;)V

    :cond_0
    return-void
.end method

.method private releaseGl()V
    .locals 7

    .line 784
    iget-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->glStartedNs:J

    const-wide/16 v2, 0x0

    cmp-long v0, v0, v2

    if-nez v0, :cond_0

    goto :goto_0

    :cond_0
    invoke-static {}, Ljava/lang/System;->nanoTime()J

    move-result-wide v0

    iget-wide v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->glStartedNs:J

    sub-long v2, v0, v2

    .line 785
    :goto_0
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->diagnostics:Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;

    new-instance v1, Ljava/lang/StringBuilder;

    const-string v4, "GL summary: inputFrames="

    invoke-direct {v1, v4}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    iget-wide v4, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->inputFrames:J

    invoke-virtual {v1, v4, v5}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    const-string v4, ", inputFps="

    invoke-virtual {v1, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-wide v4, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->inputFrames:J

    .line 786
    invoke-static {v4, v5, v2, v3}, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->frameRate(JJ)Ljava/lang/String;

    move-result-object v4

    invoke-virtual {v1, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v4, ", preOriginFrames="

    invoke-virtual {v1, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-wide v4, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->preOriginFrames:J

    invoke-virtual {v1, v4, v5}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    const-string v4, ", submittedFrames="

    invoke-virtual {v1, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-wide v4, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->submittedFrames:J

    invoke-virtual {v1, v4, v5}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    const-string v4, ", submittedFps="

    invoke-virtual {v1, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-wide v4, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->submittedFrames:J

    .line 789
    invoke-static {v4, v5, v2, v3}, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->frameRate(JJ)Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v2, ", syntheticFrames="

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->syntheticFrames:I

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string v2, ", largeFrameGaps="

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->largeFrameGaps:I

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string v2, ", maxFrameGapMs="

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-wide v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->maximumFrameGapNs:J

    long-to-float v2, v2

    const v3, 0x49742400    # 1000000.0f

    div-float/2addr v2, v3

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(F)Ljava/lang/StringBuilder;

    const-string v2, ", gpuSamples="

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->gpuTimingSamples:I

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string v2, ", gpuAverageMs="

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 794
    iget v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->gpuTimingSamples:I

    const-string v4, "n/a"

    if-nez v2, :cond_1

    move-object v2, v4

    goto :goto_1

    .line 795
    :cond_1
    iget-wide v5, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->gpuTimingTotalNs:J

    long-to-float v5, v5

    int-to-float v2, v2

    div-float/2addr v5, v2

    div-float/2addr v5, v3

    invoke-static {v5}, Ljava/lang/Float;->valueOf(F)Ljava/lang/Float;

    move-result-object v2

    :goto_1
    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    const-string v2, ", gpuMaxMs="

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-wide v5, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->gpuTimingMaxNs:J

    long-to-float v2, v5

    div-float/2addr v2, v3

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(F)Ljava/lang/StringBuilder;

    const-string v2, ", swapAverageMs="

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 797
    iget v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->swapTimingFrames:I

    if-nez v2, :cond_2

    goto :goto_2

    .line 798
    :cond_2
    iget-wide v4, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->swapTimingTotalNs:J

    long-to-float v4, v4

    int-to-float v2, v2

    div-float/2addr v4, v2

    div-float/2addr v4, v3

    invoke-static {v4}, Ljava/lang/Float;->valueOf(F)Ljava/lang/Float;

    move-result-object v4

    :goto_2
    invoke-virtual {v1, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    const-string v2, ", swapMaxMs="

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-wide v4, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->swapTimingMaxNs:J

    long-to-float v2, v4

    div-float/2addr v2, v3

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(F)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    .line 785
    invoke-virtual {v0, v1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;->log(Ljava/lang/String;)V

    .line 800
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->handler:Landroid/os/Handler;

    if-eqz v0, :cond_3

    .line 801
    iget-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->switchFrameRunnable:Ljava/lang/Runnable;

    invoke-virtual {v0, v1}, Landroid/os/Handler;->removeCallbacks(Ljava/lang/Runnable;)V

    .line 803
    :cond_3
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->eglDisplay:Landroid/opengl/EGLDisplay;

    sget-object v1, Landroid/opengl/EGL14;->EGL_NO_DISPLAY:Landroid/opengl/EGLDisplay;

    if-eq v0, v1, :cond_4

    iget-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->eglSurface:Landroid/opengl/EGLSurface;

    sget-object v2, Landroid/opengl/EGL14;->EGL_NO_SURFACE:Landroid/opengl/EGLSurface;

    if-eq v1, v2, :cond_4

    iget-object v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->eglContext:Landroid/opengl/EGLContext;

    sget-object v3, Landroid/opengl/EGL14;->EGL_NO_CONTEXT:Landroid/opengl/EGLContext;

    if-eq v2, v3, :cond_4

    .line 806
    invoke-static {v0, v1, v1, v2}, Landroid/opengl/EGL14;->eglMakeCurrent(Landroid/opengl/EGLDisplay;Landroid/opengl/EGLSurface;Landroid/opengl/EGLSurface;Landroid/opengl/EGLContext;)Z

    .line 809
    :cond_4
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->inputSurfaceTexture:Landroid/graphics/SurfaceTexture;

    const/4 v1, 0x0

    if-eqz v0, :cond_5

    .line 810
    invoke-virtual {v0, v1}, Landroid/graphics/SurfaceTexture;->setOnFrameAvailableListener(Landroid/graphics/SurfaceTexture$OnFrameAvailableListener;)V

    .line 812
    :cond_5
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->cameraSurface:Landroid/view/Surface;

    if-eqz v0, :cond_6

    .line 813
    invoke-virtual {v0}, Landroid/view/Surface;->release()V

    .line 814
    iput-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->cameraSurface:Landroid/view/Surface;

    .line 816
    :cond_6
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->inputSurfaceTexture:Landroid/graphics/SurfaceTexture;

    if-eqz v0, :cond_7

    .line 817
    invoke-virtual {v0}, Landroid/graphics/SurfaceTexture;->release()V

    .line 818
    iput-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->inputSurfaceTexture:Landroid/graphics/SurfaceTexture;

    .line 820
    :cond_7
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->overlayRenderer:Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;

    if-eqz v0, :cond_8

    .line 821
    invoke-virtual {v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->release()V

    .line 822
    iput-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->overlayRenderer:Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;

    .line 824
    :cond_8
    iget v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->textureId:I

    if-eqz v0, :cond_9

    const/4 v1, 0x1

    .line 825
    filled-new-array {v0}, [I

    move-result-object v0

    const/4 v2, 0x0

    invoke-static {v1, v0, v2}, Landroid/opengl/GLES20;->glDeleteTextures(I[II)V

    .line 826
    iput v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->textureId:I

    .line 829
    :cond_9
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->eglDisplay:Landroid/opengl/EGLDisplay;

    sget-object v1, Landroid/opengl/EGL14;->EGL_NO_DISPLAY:Landroid/opengl/EGLDisplay;

    if-eq v0, v1, :cond_c

    .line 830
    sget-object v1, Landroid/opengl/EGL14;->EGL_NO_SURFACE:Landroid/opengl/EGLSurface;

    sget-object v2, Landroid/opengl/EGL14;->EGL_NO_CONTEXT:Landroid/opengl/EGLContext;

    invoke-static {v0, v1, v1, v2}, Landroid/opengl/EGL14;->eglMakeCurrent(Landroid/opengl/EGLDisplay;Landroid/opengl/EGLSurface;Landroid/opengl/EGLSurface;Landroid/opengl/EGLContext;)Z

    .line 836
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->eglSurface:Landroid/opengl/EGLSurface;

    sget-object v1, Landroid/opengl/EGL14;->EGL_NO_SURFACE:Landroid/opengl/EGLSurface;

    if-eq v0, v1, :cond_a

    .line 837
    iget-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->eglDisplay:Landroid/opengl/EGLDisplay;

    invoke-static {v1, v0}, Landroid/opengl/EGL14;->eglDestroySurface(Landroid/opengl/EGLDisplay;Landroid/opengl/EGLSurface;)Z

    .line 839
    :cond_a
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->eglContext:Landroid/opengl/EGLContext;

    sget-object v1, Landroid/opengl/EGL14;->EGL_NO_CONTEXT:Landroid/opengl/EGLContext;

    if-eq v0, v1, :cond_b

    .line 840
    iget-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->eglDisplay:Landroid/opengl/EGLDisplay;

    invoke-static {v1, v0}, Landroid/opengl/EGL14;->eglDestroyContext(Landroid/opengl/EGLDisplay;Landroid/opengl/EGLContext;)Z

    .line 842
    :cond_b
    invoke-static {}, Landroid/opengl/EGL14;->eglReleaseThread()Z

    .line 843
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->eglDisplay:Landroid/opengl/EGLDisplay;

    invoke-static {v0}, Landroid/opengl/EGL14;->eglTerminate(Landroid/opengl/EGLDisplay;)Z

    .line 846
    :cond_c
    sget-object v0, Landroid/opengl/EGL14;->EGL_NO_DISPLAY:Landroid/opengl/EGLDisplay;

    iput-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->eglDisplay:Landroid/opengl/EGLDisplay;

    .line 847
    sget-object v0, Landroid/opengl/EGL14;->EGL_NO_CONTEXT:Landroid/opengl/EGLContext;

    iput-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->eglContext:Landroid/opengl/EGLContext;

    .line 848
    sget-object v0, Landroid/opengl/EGL14;->EGL_NO_SURFACE:Landroid/opengl/EGLSurface;

    iput-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->eglSurface:Landroid/opengl/EGLSurface;

    return-void
.end method

.method private reportError(Ljava/lang/RuntimeException;)V
    .locals 2

    .line 637
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->diagnostics:Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;

    const-string v1, "GL error"

    invoke-virtual {v0, v1, p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;->error(Ljava/lang/String;Ljava/lang/Throwable;)V

    .line 638
    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->errorListener:Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor$ErrorListener;

    if-eqz p0, :cond_0

    invoke-interface {p0, p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor$ErrorListener;->onError(Ljava/lang/Exception;)V

    :cond_0
    return-void
.end method

.method private setInputTextureFilter()V
    .locals 2

    .line 431
    iget v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->textureId:I

    const v1, 0x8d65

    invoke-static {v1, v0}, Landroid/opengl/GLES20;->glBindTexture(II)V

    .line 432
    iget v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->sourceCropSize:I

    iget p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->outputSize:I

    if-ne v0, p0, :cond_0

    const/16 p0, 0x2600

    goto :goto_0

    :cond_0
    const/16 p0, 0x2601

    :goto_0
    const/16 v0, 0x2801

    .line 433
    invoke-static {v1, v0, p0}, Landroid/opengl/GLES20;->glTexParameteri(III)V

    const/16 v0, 0x2800

    .line 438
    invoke-static {v1, v0, p0}, Landroid/opengl/GLES20;->glTexParameteri(III)V

    return-void
.end method

.method private static smoothStep(F)F
    .locals 2

    .line 686
    invoke-static {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->clamp01(F)F

    move-result p0

    mul-float v0, p0, p0

    const/high16 v1, 0x40000000    # 2.0f

    mul-float/2addr p0, v1

    const/high16 v1, 0x40400000    # 3.0f

    sub-float/2addr v1, p0

    mul-float/2addr v0, v1

    return v0
.end method

.method private submitFrame(J)V
    .locals 8

    .line 666
    iput-wide p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->lastPresentationTime:J

    .line 667
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->eglDisplay:Landroid/opengl/EGLDisplay;

    iget-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->eglSurface:Landroid/opengl/EGLSurface;

    invoke-static {v0, v1, p1, p2}, Landroid/opengl/EGLExt;->eglPresentationTimeANDROID(Landroid/opengl/EGLDisplay;Landroid/opengl/EGLSurface;J)Z

    .line 668
    invoke-static {}, Ljava/lang/System;->nanoTime()J

    move-result-wide v0

    .line 669
    iget-object v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->eglDisplay:Landroid/opengl/EGLDisplay;

    iget-object v3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->eglSurface:Landroid/opengl/EGLSurface;

    invoke-static {v2, v3}, Landroid/opengl/EGL14;->eglSwapBuffers(Landroid/opengl/EGLDisplay;Landroid/opengl/EGLSurface;)Z

    move-result v2

    if-eqz v2, :cond_1

    .line 673
    invoke-static {}, Ljava/lang/System;->nanoTime()J

    move-result-wide v2

    .line 674
    iget-wide v4, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->submittedFrames:J

    const-wide/16 v6, 0x1

    add-long/2addr v4, v6

    iput-wide v4, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->submittedFrames:J

    sub-long v0, v2, v0

    .line 675
    invoke-direct {p0, v0, v1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->recordSwapTiming(J)V

    .line 676
    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->frameTimingListener:Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor$FrameTimingListener;

    if-eqz p0, :cond_0

    const-wide/16 v0, 0x3e8

    .line 677
    div-long/2addr p1, v0

    invoke-interface {p0, p1, p2, v2, v3}, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor$FrameTimingListener;->onFrameSubmitted(JJ)V

    :cond_0
    return-void

    .line 671
    :cond_1
    invoke-static {}, Landroid/opengl/EGL14;->eglGetError()I

    move-result p0

    invoke-static {p0}, Ljava/lang/Integer;->toHexString(I)Ljava/lang/String;

    move-result-object p0

    const-string p1, "Unable to swap EGL buffers: 0x"

    invoke-static {p1, p0}, Lcom/android/dx/dex/code/CstInsn$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;Ljava/lang/Object;)V

    return-void
.end method

.method private updateOutputSize()V
    .locals 6

    .line 756
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->eglDisplay:Landroid/opengl/EGLDisplay;

    iget-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->eglSurface:Landroid/opengl/EGLSurface;

    iget-object v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->eglSurfaceValue:[I

    const/16 v3, 0x3057

    const/4 v4, 0x0

    invoke-static {v0, v1, v3, v2, v4}, Landroid/opengl/EGL14;->eglQuerySurface(Landroid/opengl/EGLDisplay;Landroid/opengl/EGLSurface;I[II)Z

    move-result v0

    if-nez v0, :cond_0

    goto :goto_0

    .line 765
    :cond_0
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->eglSurfaceValue:[I

    aget v1, v0, v4

    .line 766
    iget-object v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->eglDisplay:Landroid/opengl/EGLDisplay;

    iget-object v3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->eglSurface:Landroid/opengl/EGLSurface;

    const/16 v5, 0x3056

    invoke-static {v2, v3, v5, v0, v4}, Landroid/opengl/EGL14;->eglQuerySurface(Landroid/opengl/EGLDisplay;Landroid/opengl/EGLSurface;I[II)Z

    move-result v0

    if-nez v0, :cond_1

    goto :goto_0

    .line 775
    :cond_1
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->eglSurfaceValue:[I

    aget v0, v0, v4

    if-lez v1, :cond_3

    if-lez v0, :cond_3

    .line 776
    iget v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->outputWidth:I

    if-ne v1, v2, :cond_2

    iget v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->outputHeight:I

    if-eq v0, v2, :cond_3

    .line 777
    :cond_2
    iput v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->outputWidth:I

    .line 778
    iput v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->outputHeight:I

    .line 779
    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->diagnostics:Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;

    new-instance v2, Ljava/lang/StringBuilder;

    const-string v3, "EGL output size changed: "

    invoke-direct {v2, v3}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v2, v1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string v1, "x"

    invoke-virtual {v2, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2, v0}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p0, v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;->log(Ljava/lang/String;)V

    :cond_3
    :goto_0
    return-void
.end method


# virtual methods
.method public beginCameraSwitch(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;)V
    .locals 2

    .line 226
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->handler:Landroid/os/Handler;

    .line 227
    iget-boolean v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->started:Z

    if-eqz v1, :cond_1

    if-nez v0, :cond_0

    goto :goto_0

    .line 230
    :cond_0
    new-instance v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor$$ExternalSyntheticLambda4;

    invoke-direct {v1, p0, p1, p2, v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor$$ExternalSyntheticLambda4;-><init>(Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;Landroid/os/Handler;)V

    invoke-virtual {v0, v1}, Landroid/os/Handler;->post(Ljava/lang/Runnable;)Z

    :cond_1
    :goto_0
    return-void
.end method

.method public getCameraSurface()Landroid/view/Surface;
    .locals 0

    .line 216
    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->cameraSurface:Landroid/view/Surface;

    if-eqz p0, :cond_0

    return-object p0

    .line 217
    :cond_0
    const-string p0, "GL processor is not started"

    invoke-static {p0}, Lokio/Segment$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    const/4 p0, 0x0

    return-object p0
.end method

.method public onCameraSessionConfigured()V
    .locals 2

    .line 261
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->handler:Landroid/os/Handler;

    .line 262
    iget-boolean v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->started:Z

    if-eqz v1, :cond_1

    if-nez v0, :cond_0

    goto :goto_0

    .line 263
    :cond_0
    new-instance v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor$$ExternalSyntheticLambda6;

    invoke-direct {v1, p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor$$ExternalSyntheticLambda6;-><init>(Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;)V

    invoke-virtual {v0, v1}, Landroid/os/Handler;->post(Ljava/lang/Runnable;)Z

    :cond_1
    :goto_0
    return-void
.end method

.method public setFirstFrameListener(Ljava/lang/Runnable;)V
    .locals 0

    .line 257
    iput-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->firstFrameListener:Ljava/lang/Runnable;

    return-void
.end method

.method public start()Landroid/view/Surface;
    .locals 4

    .line 142
    iget-boolean v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->started:Z

    if-eqz v0, :cond_0

    .line 143
    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->cameraSurface:Landroid/view/Surface;

    return-object p0

    .line 146
    :cond_0
    new-instance v0, Ljava/util/concurrent/CountDownLatch;

    const/4 v1, 0x1

    invoke-direct {v0, v1}, Ljava/util/concurrent/CountDownLatch;-><init>(I)V

    .line 147
    new-instance v1, Landroid/os/HandlerThread;

    const-string v2, "RoundVideoGlProcessor"

    invoke-direct {v1, v2}, Landroid/os/HandlerThread;-><init>(Ljava/lang/String;)V

    iput-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->thread:Landroid/os/HandlerThread;

    .line 148
    invoke-virtual {v1}, Ljava/lang/Thread;->start()V

    .line 149
    invoke-static {}, Ljava/lang/System;->nanoTime()J

    move-result-wide v1

    iput-wide v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->glStartedNs:J

    .line 150
    iget-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->diagnostics:Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;

    new-instance v2, Ljava/lang/StringBuilder;

    const-string v3, "GL processor start requested: input="

    invoke-direct {v2, v3}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    iget-object v3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->inputSize:Landroid/util/Size;

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    const-string v3, ", crop="

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget v3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->sourceCropSize:I

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string v3, ", output="

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget v3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->outputSize:I

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string v3, "x"

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget v3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->outputSize:I

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string v3, ", filter="

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 153
    invoke-direct {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->inputFilterName()Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v3, ", composition="

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-boolean v3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->compositionEnabled:Z

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Z)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    .line 150
    invoke-virtual {v1, v2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;->log(Ljava/lang/String;)V

    .line 155
    new-instance v1, Landroid/os/Handler;

    iget-object v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->thread:Landroid/os/HandlerThread;

    invoke-virtual {v2}, Landroid/os/HandlerThread;->getLooper()Landroid/os/Looper;

    move-result-object v2

    invoke-direct {v1, v2}, Landroid/os/Handler;-><init>(Landroid/os/Looper;)V

    iput-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->handler:Landroid/os/Handler;

    .line 156
    new-instance v2, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor$$ExternalSyntheticLambda2;

    invoke-direct {v2, p0, v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor$$ExternalSyntheticLambda2;-><init>(Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;Ljava/util/concurrent/CountDownLatch;)V

    invoke-virtual {v1, v2}, Landroid/os/Handler;->post(Ljava/lang/Runnable;)Z

    const/4 v1, 0x0

    .line 169
    :try_start_0
    invoke-virtual {v0}, Ljava/util/concurrent/CountDownLatch;->await()V
    :try_end_0
    .catch Ljava/lang/InterruptedException; {:try_start_0 .. :try_end_0} :catch_0

    .line 176
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->initializationError:Ljava/lang/RuntimeException;

    if-nez v0, :cond_1

    .line 182
    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->cameraSurface:Landroid/view/Surface;

    return-object p0

    .line 177
    :cond_1
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->initializationError:Ljava/lang/RuntimeException;

    .line 178
    iput-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->initializationError:Ljava/lang/RuntimeException;

    .line 179
    invoke-virtual {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->stop()V

    .line 180
    throw v0

    :catch_0
    move-exception v0

    .line 171
    invoke-static {}, Ljava/lang/Thread;->currentThread()Ljava/lang/Thread;

    move-result-object v2

    invoke-virtual {v2}, Ljava/lang/Thread;->interrupt()V

    .line 172
    invoke-virtual {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->stop()V

    .line 173
    const-string p0, "GL initialization was interrupted"

    invoke-static {p0, v0}, Lorg/mvel2/asm/Constants$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;Ljava/lang/Throwable;)V

    return-object v1
.end method

.method public startRecording(J)V
    .locals 3

    const-wide/16 v0, 0x0

    cmp-long v2, p1, v0

    if-lez v2, :cond_0

    .line 274
    iput-wide p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->presentationTimeOriginNs:J

    const-wide/16 p1, -0x1

    .line 275
    iput-wide p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->firstCameraTimestamp:J

    .line 276
    iput-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->firstPresentationTime:J

    .line 277
    iput-wide p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->lastPresentationTime:J

    const-wide p1, 0x7fffffffffffffffL

    .line 278
    iput-wide p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->presentationTimeLimitNs:J

    const/4 p1, 0x1

    .line 279
    iput-boolean p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->recordingStarted:Z

    return-void

    .line 272
    :cond_0
    const-string p0, "Invalid recording time origin"

    invoke-static {p0}, Lc/f$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-void
.end method

.method public stop()V
    .locals 4

    .line 186
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->handler:Landroid/os/Handler;

    .line 187
    iget-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->thread:Landroid/os/HandlerThread;

    if-eqz v0, :cond_0

    .line 189
    iget-object v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->switchFrameRunnable:Ljava/lang/Runnable;

    invoke-virtual {v0, v2}, Landroid/os/Handler;->removeCallbacks(Ljava/lang/Runnable;)V

    :cond_0
    const/4 v2, 0x0

    .line 191
    iput-object v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->handler:Landroid/os/Handler;

    .line 192
    iput-object v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->thread:Landroid/os/HandlerThread;

    const/4 v2, 0x0

    .line 193
    iput-boolean v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->started:Z

    if-eqz v0, :cond_2

    if-nez v1, :cond_1

    goto :goto_0

    .line 198
    :cond_1
    new-instance v2, Ljava/util/concurrent/CountDownLatch;

    const/4 v3, 0x1

    invoke-direct {v2, v3}, Ljava/util/concurrent/CountDownLatch;-><init>(I)V

    .line 199
    new-instance v3, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor$$ExternalSyntheticLambda3;

    invoke-direct {v3, p0, v1, v2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor$$ExternalSyntheticLambda3;-><init>(Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;Landroid/os/HandlerThread;Ljava/util/concurrent/CountDownLatch;)V

    invoke-virtual {v0, v3}, Landroid/os/Handler;->post(Ljava/lang/Runnable;)Z

    .line 208
    :try_start_0
    invoke-virtual {v2}, Ljava/util/concurrent/CountDownLatch;->await()V
    :try_end_0
    .catch Ljava/lang/InterruptedException; {:try_start_0 .. :try_end_0} :catch_0

    return-void

    .line 210
    :catch_0
    invoke-static {}, Ljava/lang/Thread;->currentThread()Ljava/lang/Thread;

    move-result-object p0

    invoke-virtual {p0}, Ljava/lang/Thread;->interrupt()V

    :cond_2
    :goto_0
    return-void
.end method

.method public stopRecordingAt(J)V
    .locals 2

    const-wide/16 v0, 0x0

    .line 283
    invoke-static {v0, v1, p1, p2}, Ljava/lang/Math;->max(JJ)J

    move-result-wide p1

    const-wide/16 v0, 0x3e8

    mul-long/2addr p1, v0

    iput-wide p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->presentationTimeLimitNs:J

    .line 284
    iget-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->handler:Landroid/os/Handler;

    if-eqz p1, :cond_0

    .line 286
    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->switchFrameRunnable:Ljava/lang/Runnable;

    invoke-virtual {p1, p0}, Landroid/os/Handler;->removeCallbacks(Ljava/lang/Runnable;)V

    :cond_0
    return-void
.end method

.method public updateInputConfiguration(Landroid/util/Size;IZ)V
    .locals 9

    .line 295
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->handler:Landroid/os/Handler;

    .line 296
    iget-boolean v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;->started:Z

    if-eqz v1, :cond_2

    if-nez v0, :cond_0

    goto :goto_0

    .line 299
    :cond_0
    new-instance v8, Ljava/util/concurrent/CountDownLatch;

    const/4 v1, 0x1

    invoke-direct {v8, v1}, Ljava/util/concurrent/CountDownLatch;-><init>(I)V

    .line 300
    new-array v7, v1, [Ljava/lang/RuntimeException;

    .line 301
    new-instance v2, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor$$ExternalSyntheticLambda5;

    move-object v3, p0

    move-object v4, p1

    move v5, p2

    move v6, p3

    invoke-direct/range {v2 .. v8}, Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor$$ExternalSyntheticLambda5;-><init>(Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor;Landroid/util/Size;IZ[Ljava/lang/RuntimeException;Ljava/util/concurrent/CountDownLatch;)V

    invoke-virtual {v0, v2}, Landroid/os/Handler;->post(Ljava/lang/Runnable;)Z

    .line 323
    :try_start_0
    invoke-virtual {v8}, Ljava/util/concurrent/CountDownLatch;->await()V
    :try_end_0
    .catch Ljava/lang/InterruptedException; {:try_start_0 .. :try_end_0} :catch_0

    const/4 p0, 0x0

    .line 328
    aget-object p0, v7, p0

    if-nez p0, :cond_1

    goto :goto_0

    .line 329
    :cond_1
    throw p0

    :catch_0
    move-exception v0

    move-object p0, v0

    .line 325
    invoke-static {}, Ljava/lang/Thread;->currentThread()Ljava/lang/Thread;

    move-result-object p1

    invoke-virtual {p1}, Ljava/lang/Thread;->interrupt()V

    .line 326
    const-string p1, "Input size update was interrupted"

    invoke-static {p1, p0}, Lorg/mvel2/asm/Constants$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;Ljava/lang/Throwable;)V

    :cond_2
    :goto_0
    return-void
.end method
