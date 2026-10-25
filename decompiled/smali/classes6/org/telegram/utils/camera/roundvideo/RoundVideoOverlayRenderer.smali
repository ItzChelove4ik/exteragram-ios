.class final Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$CameraProgram;,
        Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$Program;,
        Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$TemporalProgram;,
        Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$MixProgram;,
        Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$TransitionProgram;,
        Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$GaussianBlurProgram;,
        Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$WatermarkAtlas;
    }
.end annotation


# static fields
.field private static final GAUSSIAN_BLUR_FRAGMENT_SHADER:Ljava/lang/String;

.field private static final SWITCH_GAUSSIAN_BLUR_FRAGMENT_SHADER:Ljava/lang/String;

.field private static cachedWatermark360:Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$WatermarkAtlas;

.field private static cachedWatermark480:Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$WatermarkAtlas;


# instance fields
.field private final cameraProgram:Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$CameraProgram;

.field private cameraTextureBuffer:Ljava/nio/FloatBuffer;

.field private final compositionEnabled:Z

.field private final copyProgram:Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$Program;

.field private final explicitTextureLod:Z

.field private final frameBuffers:[I

.field private final gaussianBlurProgram:Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$GaussianBlurProgram;

.field private historyReadTexture:I

.field private historyValid:Z

.field private historyWriteTexture:I

.field private inputSize:Landroid/util/Size;

.field private lastHistoryFrameNs:J

.field private logoFrame:I

.field private final mixProgram:Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$MixProgram;

.field private newSwitchBlurRadius:F

.field private final outputSize:I

.field private rotatedCameraTextureBuffer:Ljava/nio/FloatBuffer;

.field private sourceCropSize:I

.field private final switchGaussianBlurProgram:Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$GaussianBlurProgram;

.field private switchNewBackgroundTexture:I

.field private final switchNewBlurSize:I

.field private switchNewFrameCaptured:Z

.field private switchOldBackgroundTexture:I

.field private final temporalProgram:Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$TemporalProgram;

.field private final textureBuffer:Ljava/nio/FloatBuffer;

.field private final textures:[I

.field private final transitionProgram:Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$TransitionProgram;

.field private final vertexBuffer:Ljava/nio/FloatBuffer;

.field private final watermarkProgram:Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$Program;


# direct methods
.method public static bridge synthetic -$$Nest$sfgetGAUSSIAN_BLUR_FRAGMENT_SHADER()Ljava/lang/String;
    .locals 1

    .line 0
    sget-object v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->GAUSSIAN_BLUR_FRAGMENT_SHADER:Ljava/lang/String;

    return-object v0
.end method

.method public static bridge synthetic -$$Nest$smcreateProgram(II)I
    .locals 0

    .line 0
    invoke-static {p0, p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->createProgram(II)I

    move-result p0

    return p0
.end method

.method public static bridge synthetic -$$Nest$smcreateShader(ILjava/lang/String;)I
    .locals 0

    .line 0
    invoke-static {p0, p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->createShader(ILjava/lang/String;)I

    move-result p0

    return p0
.end method

.method public static bridge synthetic -$$Nest$smcreateTransitionFragmentShader(ZZ)Ljava/lang/String;
    .locals 0

    .line 0
    invoke-static {p0, p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->createTransitionFragmentShader(ZZ)Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method static constructor <clinit>()V
    .locals 2

    const/4 v0, 0x4

    const/high16 v1, 0x3fe00000    # 1.75f

    .line 232
    invoke-static {v0, v1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->createGaussianBlurFragmentShader(IF)Ljava/lang/String;

    move-result-object v0

    sput-object v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->GAUSSIAN_BLUR_FRAGMENT_SHADER:Ljava/lang/String;

    const/16 v0, 0x9

    const/high16 v1, 0x40800000    # 4.0f

    .line 237
    invoke-static {v0, v1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->createGaussianBlurFragmentShader(IF)Ljava/lang/String;

    move-result-object v0

    sput-object v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->SWITCH_GAUSSIAN_BLUR_FRAGMENT_SHADER:Ljava/lang/String;

    return-void
.end method

.method public constructor <init>(ILandroid/util/Size;IZ)V
    .locals 19

    move-object/from16 v0, p0

    move/from16 v1, p1

    move-object/from16 v2, p2

    move/from16 v3, p3

    move/from16 v4, p4

    .line 278
    invoke-direct {v0}, Ljava/lang/Object;-><init>()V

    const/16 v5, 0x8

    .line 260
    new-array v6, v5, [I

    iput-object v6, v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->frameBuffers:[I

    const/16 v7, 0xa

    .line 261
    new-array v7, v7, [I

    iput-object v7, v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->textures:[I

    const/4 v8, 0x0

    .line 264
    iput v8, v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->historyReadTexture:I

    const/4 v9, 0x1

    .line 265
    iput v9, v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->historyWriteTexture:I

    .line 266
    iput v8, v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->switchOldBackgroundTexture:I

    .line 267
    iput v9, v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->switchNewBackgroundTexture:I

    const/high16 v10, -0x40800000    # -1.0f

    .line 271
    iput v10, v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->newSwitchBlurRadius:F

    .line 279
    iput v1, v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->outputSize:I

    .line 280
    div-int/lit8 v10, v1, 0x2

    invoke-static {v9, v10}, Ljava/lang/Math;->max(II)I

    move-result v10

    iput v10, v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->switchNewBlurSize:I

    .line 281
    iput-object v2, v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->inputSize:Landroid/util/Size;

    .line 282
    iput v3, v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->sourceCropSize:I

    .line 283
    iput-boolean v4, v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->compositionEnabled:Z

    .line 284
    invoke-static {}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->supportsExplicitTextureLod()Z

    move-result v11

    iput-boolean v11, v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->explicitTextureLod:Z

    .line 285
    new-instance v12, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$CameraProgram;

    const-string v13, "#extension GL_OES_EGL_image_external : require\nprecision mediump float;\nvarying vec2 vTextureCoord;\nuniform samplerExternalOES sTexture;\nvoid main() {\n    gl_FragColor = texture2D(sTexture, vTextureCoord);\n}\n"

    invoke-direct {v12, v13}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$CameraProgram;-><init>(Ljava/lang/String;)V

    iput-object v12, v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->cameraProgram:Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$CameraProgram;

    .line 286
    new-instance v12, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$Program;

    const-string v13, "precision mediump float;\nvarying vec2 vTextureCoord;\nuniform sampler2D sTexture;\nvoid main() {\n    gl_FragColor = texture2D(sTexture, vTextureCoord);\n}\n"

    const-string v14, "attribute vec4 aPosition;\nattribute vec4 aTextureCoord;\nvarying vec2 vTextureCoord;\nvoid main() {\n    gl_Position = aPosition;\n    vTextureCoord = aTextureCoord.xy;\n}\n"

    invoke-direct {v12, v14, v13}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$Program;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    iput-object v12, v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->copyProgram:Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$Program;

    const/4 v12, 0x0

    if-eqz v4, :cond_0

    .line 290
    new-instance v13, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$TemporalProgram;

    invoke-direct {v13}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$TemporalProgram;-><init>()V

    goto :goto_0

    :cond_0
    move-object v13, v12

    :goto_0
    iput-object v13, v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->temporalProgram:Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$TemporalProgram;

    if-eqz v4, :cond_1

    .line 291
    new-instance v13, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$MixProgram;

    invoke-direct {v13}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$MixProgram;-><init>()V

    goto :goto_1

    :cond_1
    move-object v13, v12

    :goto_1
    iput-object v13, v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->mixProgram:Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$MixProgram;

    .line 292
    new-instance v13, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$TransitionProgram;

    invoke-direct {v13, v4, v11}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$TransitionProgram;-><init>(ZZ)V

    iput-object v13, v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->transitionProgram:Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$TransitionProgram;

    .line 296
    new-instance v11, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$GaussianBlurProgram;

    invoke-direct {v11}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$GaussianBlurProgram;-><init>()V

    iput-object v11, v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->gaussianBlurProgram:Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$GaussianBlurProgram;

    .line 297
    new-instance v11, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$GaussianBlurProgram;

    sget-object v13, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->SWITCH_GAUSSIAN_BLUR_FRAGMENT_SHADER:Ljava/lang/String;

    invoke-direct {v11, v13}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$GaussianBlurProgram;-><init>(Ljava/lang/String;)V

    iput-object v11, v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->switchGaussianBlurProgram:Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$GaussianBlurProgram;

    if-eqz v4, :cond_2

    .line 301
    new-instance v12, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$Program;

    const-string v11, "precision mediump float;\nvarying vec2 vTextureCoord;\nuniform sampler2D sTexture;\nvoid main() {\n    gl_FragColor = vec4(1.0, 1.0, 1.0, texture2D(sTexture, vTextureCoord).a);\n}\n"

    invoke-direct {v12, v14, v11}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$Program;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    .line 302
    :cond_2
    iput-object v12, v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->watermarkProgram:Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$Program;

    const/16 v11, 0x290

    .line 304
    new-array v12, v11, [F

    const/high16 v16, 0x3f800000    # 1.0f

    const/16 v17, 0x0

    const/4 v13, 0x0

    const/4 v14, 0x0

    const/high16 v15, 0x3f800000    # 1.0f

    .line 305
    invoke-static/range {v12 .. v17}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->setTextureCoordinates([FIFFFF)V

    const/16 v11, 0x30

    .line 307
    new-array v13, v11, [F

    const/high16 v17, 0x3f800000    # 1.0f

    const/high16 v18, -0x40800000    # -1.0f

    const/4 v14, 0x0

    const/high16 v15, -0x40800000    # -1.0f

    .line 308
    invoke-static/range {v13 .. v18}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->setVertexCoordinates([FIFFFF)V

    .line 309
    invoke-static {v2, v3, v8}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->createCameraTextureBuffer(Landroid/util/Size;IZ)Ljava/nio/FloatBuffer;

    move-result-object v14

    iput-object v14, v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->cameraTextureBuffer:Ljava/nio/FloatBuffer;

    .line 310
    invoke-static {v2, v3, v9}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->createCameraTextureBuffer(Landroid/util/Size;IZ)Ljava/nio/FloatBuffer;

    move-result-object v2

    iput-object v2, v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->rotatedCameraTextureBuffer:Ljava/nio/FloatBuffer;

    .line 312
    array-length v2, v7

    invoke-static {v2, v7, v8}, Landroid/opengl/GLES20;->glGenTextures(I[II)V

    const/16 v2, 0x2601

    if-eqz v4, :cond_3

    .line 314
    invoke-direct {v0, v8, v11, v11, v2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->createColorTexture(IIII)V

    .line 315
    invoke-direct {v0, v9, v11, v11, v2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->createColorTexture(IIII)V

    .line 316
    invoke-direct {v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->createMaskTexture()V

    .line 317
    invoke-direct {v0, v13, v12}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->createWatermarkAtlas([F[F)V

    .line 318
    invoke-direct {v0, v5, v11, v11, v2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->createColorTexture(IIII)V

    :cond_3
    const/4 v3, 0x4

    .line 320
    invoke-direct {v0, v3, v1, v1, v2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->createColorTexture(IIII)V

    const/4 v7, 0x5

    const/16 v11, 0x200

    .line 326
    invoke-direct {v0, v7, v11, v11, v2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->createColorTexture(IIII)V

    const/4 v11, 0x6

    .line 332
    invoke-direct {v0, v11, v1, v1, v2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->createColorTexture(IIII)V

    const/4 v1, 0x7

    .line 333
    invoke-direct {v0, v1, v10, v10, v2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->createColorTexture(IIII)V

    const/16 v14, 0x9

    .line 339
    invoke-direct {v0, v14, v10, v10, v2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->createColorTexture(IIII)V

    const/16 v2, 0xde1

    .line 345
    invoke-static {v2, v8}, Landroid/opengl/GLES20;->glBindTexture(II)V

    .line 347
    array-length v2, v6

    invoke-static {v2, v6, v8}, Landroid/opengl/GLES20;->glGenFramebuffers(I[II)V

    if-eqz v4, :cond_4

    .line 349
    invoke-direct {v0, v8, v8}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->attachFrameBuffer(II)V

    .line 350
    invoke-direct {v0, v9, v9}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->attachFrameBuffer(II)V

    .line 351
    invoke-direct {v0, v11, v5}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->attachFrameBuffer(II)V

    :cond_4
    const/4 v2, 0x2

    .line 353
    invoke-direct {v0, v2, v3}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->attachFrameBuffer(II)V

    const/4 v2, 0x3

    .line 354
    invoke-direct {v0, v2, v7}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->attachFrameBuffer(II)V

    .line 355
    invoke-direct {v0, v3, v11}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->attachFrameBuffer(II)V

    .line 356
    invoke-direct {v0, v7, v1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->attachFrameBuffer(II)V

    .line 357
    invoke-direct {v0, v1, v14}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->attachFrameBuffer(II)V

    const v1, 0x8d40

    .line 361
    invoke-static {v1, v8}, Landroid/opengl/GLES20;->glBindFramebuffer(II)V

    .line 362
    invoke-static {v13}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->createBuffer([F)Ljava/nio/FloatBuffer;

    move-result-object v1

    iput-object v1, v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->vertexBuffer:Ljava/nio/FloatBuffer;

    .line 363
    invoke-static {v12}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->createBuffer([F)Ljava/nio/FloatBuffer;

    move-result-object v1

    iput-object v1, v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->textureBuffer:Ljava/nio/FloatBuffer;

    .line 364
    invoke-static {v8}, Landroid/opengl/GLES20;->glEnableVertexAttribArray(I)V

    .line 365
    invoke-static {v9}, Landroid/opengl/GLES20;->glEnableVertexAttribArray(I)V

    return-void
.end method

.method private attach(I)V
    .locals 3

    .line 846
    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->textures:[I

    aget p0, p0, p1

    const/4 p1, 0x0

    const v0, 0x8d40

    const v1, 0x8ce0

    const/16 v2, 0xde1

    invoke-static {v0, v1, v2, p0, p1}, Landroid/opengl/GLES20;->glFramebufferTexture2D(IIIII)V

    return-void
.end method

.method private attachFrameBuffer(II)V
    .locals 1

    .line 856
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->frameBuffers:[I

    aget p1, v0, p1

    const v0, 0x8d40

    invoke-static {v0, p1}, Landroid/opengl/GLES20;->glBindFramebuffer(II)V

    .line 857
    invoke-direct {p0, p2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->attach(I)V

    .line 858
    invoke-static {v0}, Landroid/opengl/GLES20;->glCheckFramebufferStatus(I)I

    move-result p0

    const p1, 0x8cd5

    if-ne p0, p1, :cond_0

    const/high16 p0, 0x3f800000    # 1.0f

    const/4 p1, 0x0

    .line 863
    invoke-static {p1, p1, p1, p0}, Landroid/opengl/GLES20;->glClearColor(FFFF)V

    const/16 p0, 0x4000

    .line 864
    invoke-static {p0}, Landroid/opengl/GLES20;->glClear(I)V

    return-void

    .line 860
    :cond_0
    const-string p1, "Incomplete framebuffer: 0x"

    .line 861
    invoke-static {p0}, Ljava/lang/Integer;->toHexString(I)Ljava/lang/String;

    move-result-object p0

    invoke-static {p1, p0}, Lcom/android/dx/dex/code/CstInsn$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;Ljava/lang/Object;)V

    return-void
.end method

.method private bindCameraProgram(Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$CameraProgram;[F)V
    .locals 8

    .line 785
    iget v0, p1, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$Program;->program:I

    invoke-static {v0}, Landroid/opengl/GLES20;->glUseProgram(I)V

    .line 786
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->vertexBuffer:Ljava/nio/FloatBuffer;

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Ljava/nio/FloatBuffer;->position(I)Ljava/nio/Buffer;

    .line 787
    iget v2, p1, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$Program;->positionHandle:I

    const/16 v6, 0xc

    iget-object v7, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->vertexBuffer:Ljava/nio/FloatBuffer;

    const/4 v3, 0x3

    const/16 v4, 0x1406

    const/4 v5, 0x0

    invoke-static/range {v2 .. v7}, Landroid/opengl/GLES20;->glVertexAttribPointer(IIIZILjava/nio/Buffer;)V

    .line 795
    invoke-static {p2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->swapsTextureAxes([F)Z

    move-result v0

    if-eqz v0, :cond_0

    .line 796
    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->rotatedCameraTextureBuffer:Ljava/nio/FloatBuffer;

    :goto_0
    move-object v7, p0

    goto :goto_1

    .line 797
    :cond_0
    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->cameraTextureBuffer:Ljava/nio/FloatBuffer;

    goto :goto_0

    .line 798
    :goto_1
    invoke-virtual {v7, v1}, Ljava/nio/FloatBuffer;->position(I)Ljava/nio/Buffer;

    .line 799
    iget v2, p1, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$Program;->textureCoordinateHandle:I

    const/4 v5, 0x0

    const/16 v6, 0x8

    const/4 v3, 0x2

    const/16 v4, 0x1406

    invoke-static/range {v2 .. v7}, Landroid/opengl/GLES20;->glVertexAttribPointer(IIIZILjava/nio/Buffer;)V

    .line 807
    iget p0, p1, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$CameraProgram;->textureMatrixHandle:I

    const/4 p1, 0x1

    invoke-static {p0, p1, v1, p2, v1}, Landroid/opengl/GLES20;->glUniformMatrix4fv(IIZ[FI)V

    return-void
.end method

.method private static bindExternalTexture(III)V
    .locals 0

    const p2, 0x84c0

    add-int/2addr p0, p2

    .line 841
    invoke-static {p0}, Landroid/opengl/GLES20;->glActiveTexture(I)V

    const p0, 0x8d65

    .line 842
    invoke-static {p0, p1}, Landroid/opengl/GLES20;->glBindTexture(II)V

    return-void
.end method

.method private bindProgram(Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$Program;I)V
    .locals 1

    const/4 v0, 0x0

    .line 781
    invoke-direct {p0, p1, p2, v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->bindProgram(Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$Program;II)V

    return-void
.end method

.method private bindProgram(Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$Program;II)V
    .locals 7

    .line 811
    iget v0, p1, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$Program;->program:I

    invoke-static {v0}, Landroid/opengl/GLES20;->glUseProgram(I)V

    .line 812
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->vertexBuffer:Ljava/nio/FloatBuffer;

    invoke-virtual {v0, p3}, Ljava/nio/FloatBuffer;->position(I)Ljava/nio/Buffer;

    .line 813
    iget v1, p1, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$Program;->positionHandle:I

    const/16 v5, 0xc

    iget-object v6, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->vertexBuffer:Ljava/nio/FloatBuffer;

    const/4 v2, 0x3

    const/16 v3, 0x1406

    const/4 v4, 0x0

    invoke-static/range {v1 .. v6}, Landroid/opengl/GLES20;->glVertexAttribPointer(IIIZILjava/nio/Buffer;)V

    .line 821
    iget-object p3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->textureBuffer:Ljava/nio/FloatBuffer;

    invoke-virtual {p3, p2}, Ljava/nio/FloatBuffer;->position(I)Ljava/nio/Buffer;

    .line 822
    iget v0, p1, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$Program;->textureCoordinateHandle:I

    const/16 v4, 0x8

    iget-object v5, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->textureBuffer:Ljava/nio/FloatBuffer;

    const/4 v1, 0x2

    const/16 v2, 0x1406

    const/4 v3, 0x0

    invoke-static/range {v0 .. v5}, Landroid/opengl/GLES20;->glVertexAttribPointer(IIIZILjava/nio/Buffer;)V

    return-void
.end method

.method private bindTexture(III)V
    .locals 0

    const p3, 0x84c0

    add-int/2addr p1, p3

    .line 836
    invoke-static {p1}, Landroid/opengl/GLES20;->glActiveTexture(I)V

    .line 837
    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->textures:[I

    aget p0, p0, p2

    const/16 p1, 0xde1

    invoke-static {p1, p0}, Landroid/opengl/GLES20;->glBindTexture(II)V

    return-void
.end method

.method private static clamp01(F)F
    .locals 1

    const/high16 v0, 0x3f800000    # 1.0f

    .line 777
    invoke-static {v0, p0}, Ljava/lang/Math;->min(FF)F

    move-result p0

    const/4 v0, 0x0

    invoke-static {v0, p0}, Ljava/lang/Math;->max(FF)F

    move-result p0

    return p0
.end method

.method private configureTexture(II)V
    .locals 0

    .line 1036
    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->textures:[I

    aget p0, p0, p1

    const/16 p1, 0xde1

    invoke-static {p1, p0}, Landroid/opengl/GLES20;->glBindTexture(II)V

    const/16 p0, 0x2801

    .line 1037
    invoke-static {p1, p0, p2}, Landroid/opengl/GLES20;->glTexParameteri(III)V

    const/16 p0, 0x2800

    .line 1038
    invoke-static {p1, p0, p2}, Landroid/opengl/GLES20;->glTexParameteri(III)V

    const/16 p0, 0x2802

    const p2, 0x812f

    .line 1039
    invoke-static {p1, p0, p2}, Landroid/opengl/GLES20;->glTexParameteri(III)V

    const/16 p0, 0x2803

    .line 1044
    invoke-static {p1, p0, p2}, Landroid/opengl/GLES20;->glTexParameteri(III)V

    return-void
.end method

.method private static createBuffer([F)Ljava/nio/FloatBuffer;
    .locals 2

    .line 1052
    array-length v0, p0

    mul-int/lit8 v0, v0, 0x4

    invoke-static {v0}, Ljava/nio/ByteBuffer;->allocateDirect(I)Ljava/nio/ByteBuffer;

    move-result-object v0

    .line 1053
    invoke-static {}, Ljava/nio/ByteOrder;->nativeOrder()Ljava/nio/ByteOrder;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/nio/ByteBuffer;->order(Ljava/nio/ByteOrder;)Ljava/nio/ByteBuffer;

    move-result-object v0

    .line 1054
    invoke-virtual {v0}, Ljava/nio/ByteBuffer;->asFloatBuffer()Ljava/nio/FloatBuffer;

    move-result-object v0

    .line 1055
    invoke-virtual {v0, p0}, Ljava/nio/FloatBuffer;->put([F)Ljava/nio/FloatBuffer;

    move-result-object p0

    const/4 v1, 0x0

    invoke-virtual {p0, v1}, Ljava/nio/FloatBuffer;->position(I)Ljava/nio/Buffer;

    return-object v0
.end method

.method private static createCameraTextureBuffer(Landroid/util/Size;IZ)Ljava/nio/FloatBuffer;
    .locals 3

    .line 1066
    invoke-virtual {p0}, Landroid/util/Size;->getWidth()I

    move-result v0

    invoke-virtual {p0}, Landroid/util/Size;->getHeight()I

    move-result v1

    invoke-static {v0, v1}, Ljava/lang/Math;->min(II)I

    move-result v0

    .line 1064
    invoke-static {p1, v0}, Ljava/lang/Math;->min(II)I

    move-result p1

    if-eqz p2, :cond_0

    .line 1068
    invoke-virtual {p0}, Landroid/util/Size;->getHeight()I

    move-result v0

    goto :goto_0

    :cond_0
    invoke-virtual {p0}, Landroid/util/Size;->getWidth()I

    move-result v0

    :goto_0
    if-eqz p2, :cond_1

    .line 1069
    invoke-virtual {p0}, Landroid/util/Size;->getWidth()I

    move-result p0

    goto :goto_1

    :cond_1
    invoke-virtual {p0}, Landroid/util/Size;->getHeight()I

    move-result p0

    :goto_1
    int-to-float p1, p1

    int-to-float p2, v0

    const/high16 v0, 0x40000000    # 2.0f

    mul-float/2addr p2, v0

    div-float p2, p1, p2

    int-to-float p0, p0

    mul-float/2addr p0, v0

    div-float/2addr p1, p0

    const/high16 p0, 0x3f000000    # 0.5f

    sub-float v0, p0, p2

    sub-float v1, p0, p1

    add-float/2addr p2, p0

    add-float/2addr p1, p0

    const/16 p0, 0x8

    .line 1072
    new-array p0, p0, [F

    const/4 v2, 0x0

    aput v0, p0, v2

    const/4 v2, 0x1

    aput v1, p0, v2

    const/4 v2, 0x2

    aput p2, p0, v2

    const/4 v2, 0x3

    aput v1, p0, v2

    const/4 v1, 0x4

    aput v0, p0, v1

    const/4 v0, 0x5

    aput p1, p0, v0

    const/4 v0, 0x6

    aput p2, p0, v0

    const/4 p2, 0x7

    aput p1, p0, p2

    invoke-static {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->createBuffer([F)Ljava/nio/FloatBuffer;

    move-result-object p0

    return-object p0
.end method

.method private createColorTexture(IIII)V
    .locals 9

    .line 868
    invoke-direct {p0, p1, p4}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->configureTexture(II)V

    const/16 v7, 0x1401

    const/4 v8, 0x0

    const/16 v0, 0xde1

    const/4 v1, 0x0

    const/16 v2, 0x1908

    const/4 v5, 0x0

    const/16 v6, 0x1908

    move v3, p2

    move v4, p3

    .line 869
    invoke-static/range {v0 .. v8}, Landroid/opengl/GLES20;->glTexImage2D(IIIIIIIILjava/nio/Buffer;)V

    return-void
.end method

.method private static createGaussianBlurFragmentShader(IF)Ljava/lang/String;
    .locals 15

    move/from16 v1, p1

    neg-int v2, p0

    const-wide/16 v3, 0x0

    :goto_0
    const-wide/high16 v5, 0x4000000000000000L    # 2.0

    if-gt v2, p0, :cond_0

    mul-int v7, v2, v2

    neg-int v7, v7

    int-to-double v7, v7

    float-to-double v9, v1

    mul-double/2addr v5, v9

    mul-double/2addr v5, v9

    div-double/2addr v7, v5

    .line 1177
    invoke-static {v7, v8}, Ljava/lang/Math;->exp(D)D

    move-result-wide v5

    add-double/2addr v3, v5

    add-int/lit8 v2, v2, 0x1

    goto :goto_0

    .line 1179
    :cond_0
    new-instance v2, Ljava/lang/StringBuilder;

    const/16 v7, 0x1000

    invoke-direct {v2, v7}, Ljava/lang/StringBuilder;-><init>(I)V

    .line 1180
    const-string v7, "precision mediump float;\nvarying vec2 vTextureCoord;\nuniform sampler2D sTexture;\nuniform vec2 texOffset;\nvoid main() {\n    vec3 color = texture2D(sTexture, vTextureCoord).rgb * "

    invoke-virtual {v2, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-wide/high16 v7, 0x3ff0000000000000L    # 1.0

    div-double/2addr v7, v3

    double-to-float v7, v7

    .line 1187
    invoke-static {v7}, Ljava/lang/Float;->toString(F)Ljava/lang/String;

    move-result-object v7

    invoke-virtual {v2, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 1188
    const-string v7, ";\n"

    invoke-virtual {v2, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const/4 v8, 0x1

    :goto_1
    if-gt v8, p0, :cond_3

    mul-int v9, v8, v8

    neg-int v9, v9

    int-to-double v9, v9

    float-to-double v11, v1

    mul-double v13, v11, v5

    mul-double/2addr v13, v11

    div-double/2addr v9, v13

    .line 1191
    invoke-static {v9, v10}, Ljava/lang/Math;->exp(D)D

    move-result-wide v9

    div-double/2addr v9, v3

    double-to-float v9, v9

    add-int/lit8 v10, v8, 0x1

    const/4 v11, 0x0

    if-gt v10, p0, :cond_1

    mul-int v12, v10, v10

    neg-int v12, v12

    int-to-double v5, v12

    div-double/2addr v5, v13

    .line 1195
    invoke-static {v5, v6}, Ljava/lang/Math;->exp(D)D

    move-result-wide v5

    div-double/2addr v5, v3

    double-to-float v5, v5

    goto :goto_2

    :cond_1
    move v5, v11

    :goto_2
    add-float v6, v9, v5

    cmpl-float v11, v5, v11

    if-nez v11, :cond_2

    int-to-float v5, v8

    goto :goto_3

    :cond_2
    int-to-float v11, v8

    mul-float/2addr v11, v9

    int-to-float v9, v10

    mul-float/2addr v9, v5

    add-float/2addr v11, v9

    div-float v5, v11, v6

    .line 1201
    :goto_3
    const-string v9, "    color += (texture2D(sTexture, vTextureCoord + texOffset * "

    invoke-virtual {v2, v9}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 1202
    invoke-static {v5}, Ljava/lang/Float;->toString(F)Ljava/lang/String;

    move-result-object v9

    invoke-virtual {v2, v9}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v9, ").rgb + texture2D(sTexture, vTextureCoord - texOffset * "

    .line 1203
    invoke-virtual {v2, v9}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 1204
    invoke-static {v5}, Ljava/lang/Float;->toString(F)Ljava/lang/String;

    move-result-object v5

    invoke-virtual {v2, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v5, ").rgb) * "

    .line 1205
    invoke-virtual {v2, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 1206
    invoke-static {v6}, Ljava/lang/Float;->toString(F)Ljava/lang/String;

    move-result-object v5

    invoke-virtual {v2, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 1207
    invoke-virtual {v2, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    add-int/lit8 v8, v8, 0x2

    const-wide/high16 v5, 0x4000000000000000L    # 2.0

    goto :goto_1

    .line 1209
    :cond_3
    const-string v0, "    gl_FragColor = vec4(color, 1.0);\n}\n"

    invoke-virtual {v2, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 1211
    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    return-object v0
.end method

.method private createMaskTexture()V
    .locals 12

    const/4 v0, 0x2

    const/16 v1, 0x2601

    .line 883
    invoke-direct {p0, v0, v1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->configureTexture(II)V

    .line 884
    iget v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->outputSize:I

    mul-int/2addr v0, v0

    invoke-static {v0}, Ljava/nio/ByteBuffer;->allocateDirect(I)Ljava/nio/ByteBuffer;

    move-result-object v9

    .line 885
    iget v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->outputSize:I

    int-to-float v0, v0

    const/high16 v1, 0x3f000000    # 0.5f

    mul-float/2addr v0, v1

    const/high16 v2, 0x40000000    # 2.0f

    add-float/2addr v2, v0

    const/4 v3, 0x0

    move v4, v3

    .line 887
    :goto_0
    iget v5, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->outputSize:I

    if-ge v4, v5, :cond_1

    int-to-float v5, v4

    add-float/2addr v5, v1

    sub-float/2addr v5, v0

    move v6, v3

    .line 889
    :goto_1
    iget v7, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->outputSize:I

    if-ge v6, v7, :cond_0

    int-to-float v7, v6

    add-float/2addr v7, v1

    sub-float/2addr v7, v0

    add-float v8, v2, v1

    mul-float/2addr v7, v7

    mul-float v10, v5, v5

    add-float/2addr v7, v10

    float-to-double v10, v7

    .line 891
    invoke-static {v10, v11}, Ljava/lang/Math;->sqrt(D)D

    move-result-wide v10

    double-to-float v7, v10

    sub-float/2addr v8, v7

    const/high16 v7, 0x3f800000    # 1.0f

    .line 892
    invoke-static {v7, v8}, Ljava/lang/Math;->min(FF)F

    move-result v7

    const/4 v8, 0x0

    invoke-static {v8, v7}, Ljava/lang/Math;->max(FF)F

    move-result v7

    const/high16 v8, 0x437f0000    # 255.0f

    mul-float/2addr v7, v8

    invoke-static {v7}, Ljava/lang/Math;->round(F)I

    move-result v7

    int-to-byte v7, v7

    invoke-virtual {v9, v7}, Ljava/nio/ByteBuffer;->put(B)Ljava/nio/ByteBuffer;

    add-int/lit8 v6, v6, 0x1

    goto :goto_1

    :cond_0
    add-int/lit8 v4, v4, 0x1

    goto :goto_0

    .line 895
    :cond_1
    invoke-virtual {v9, v3}, Ljava/nio/ByteBuffer;->position(I)Ljava/nio/Buffer;

    const/16 v0, 0xcf5

    const/4 v1, 0x1

    .line 896
    invoke-static {v0, v1}, Landroid/opengl/GLES20;->glPixelStorei(II)V

    .line 897
    iget v4, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->outputSize:I

    const/16 v7, 0x1906

    const/16 v8, 0x1401

    const/16 v1, 0xde1

    const/4 v2, 0x0

    const/16 v3, 0x1906

    const/4 v6, 0x0

    move v5, v4

    invoke-static/range {v1 .. v9}, Landroid/opengl/GLES20;->glTexImage2D(IIIIIIIILjava/nio/Buffer;)V

    const/4 p0, 0x4

    .line 908
    invoke-static {v0, p0}, Landroid/opengl/GLES20;->glPixelStorei(II)V

    return-void
.end method

.method private static createProgram(II)I
    .locals 2

    .line 1375
    invoke-static {}, Landroid/opengl/GLES20;->glCreateProgram()I

    move-result v0

    .line 1376
    invoke-static {v0, p0}, Landroid/opengl/GLES20;->glAttachShader(II)V

    .line 1377
    invoke-static {v0, p1}, Landroid/opengl/GLES20;->glAttachShader(II)V

    .line 1378
    const-string p0, "aPosition"

    const/4 p1, 0x0

    invoke-static {v0, p1, p0}, Landroid/opengl/GLES20;->glBindAttribLocation(IILjava/lang/String;)V

    .line 1379
    const-string p0, "aTextureCoord"

    const/4 v1, 0x1

    invoke-static {v0, v1, p0}, Landroid/opengl/GLES20;->glBindAttribLocation(IILjava/lang/String;)V

    .line 1380
    invoke-static {v0}, Landroid/opengl/GLES20;->glLinkProgram(I)V

    .line 1381
    new-array p0, v1, [I

    const v1, 0x8b82

    .line 1382
    invoke-static {v0, v1, p0, p1}, Landroid/opengl/GLES20;->glGetProgramiv(II[II)V

    .line 1383
    aget p0, p0, p1

    if-eqz p0, :cond_0

    return v0

    .line 1384
    :cond_0
    invoke-static {v0}, Landroid/opengl/GLES20;->glGetProgramInfoLog(I)Ljava/lang/String;

    move-result-object p0

    .line 1385
    invoke-static {v0}, Landroid/opengl/GLES20;->glDeleteProgram(I)V

    .line 1386
    const-string p1, "Unable to link program: "

    invoke-static {p1, p0}, Lcom/android/dx/DexMaker$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;Ljava/lang/Object;)V

    const/4 p0, 0x0

    return p0
.end method

.method private static createShader(ILjava/lang/String;)I
    .locals 2

    .line 1361
    invoke-static {p0}, Landroid/opengl/GLES20;->glCreateShader(I)I

    move-result p0

    .line 1362
    invoke-static {p0, p1}, Landroid/opengl/GLES20;->glShaderSource(ILjava/lang/String;)V

    .line 1363
    invoke-static {p0}, Landroid/opengl/GLES20;->glCompileShader(I)V

    const/4 p1, 0x1

    .line 1364
    new-array p1, p1, [I

    const v0, 0x8b81

    const/4 v1, 0x0

    .line 1365
    invoke-static {p0, v0, p1, v1}, Landroid/opengl/GLES20;->glGetShaderiv(II[II)V

    .line 1366
    aget p1, p1, v1

    if-eqz p1, :cond_0

    return p0

    .line 1367
    :cond_0
    invoke-static {p0}, Landroid/opengl/GLES20;->glGetShaderInfoLog(I)Ljava/lang/String;

    move-result-object p1

    .line 1368
    invoke-static {p0}, Landroid/opengl/GLES20;->glDeleteShader(I)V

    .line 1369
    const-string p0, "Unable to compile shader: "

    invoke-static {p0, p1}, Lcom/android/dx/DexMaker$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;Ljava/lang/Object;)V

    const/4 p0, 0x0

    return p0
.end method

.method private static createTransitionFragmentShader(ZZ)Ljava/lang/String;
    .locals 2

    if-eqz p0, :cond_0

    .line 767
    const-string p0, "#ifdef GL_FRAGMENT_PRECISION_HIGH\nprecision highp float;\n#else\nprecision mediump float;\n#endif\nvarying vec2 vTextureCoord;\nuniform sampler2D sTexture;\nuniform sampler2D omTexture;\nuniform sampler2D nTexture;\nuniform sampler2D nbTexture;\nuniform sampler2D oldBackground;\nuniform sampler2D newBackground;\nuniform sampler2D maskTexture;\nuniform vec2 oldBlurStep;\nuniform float oldMipBias;\nuniform float oldBlurMix;\nuniform float newBlur;\nuniform float mixValue;\nvec4 sampleOldFrame() {\n    vec2 x = vec2(oldBlurStep.x, 0.0);\n    vec2 diagonalUp = vec2(oldBlurStep.x * 0.5, oldBlurStep.y * 0.8660254);\n    vec2 diagonalDown = vec2(oldBlurStep.x * 0.5, -oldBlurStep.y * 0.8660254);\n    vec4 color = sampleOldMip(omTexture, vTextureCoord, oldMipBias) * 0.25;\n    color += (sampleOldMip(omTexture, vTextureCoord + x, oldMipBias)\n            + sampleOldMip(omTexture, vTextureCoord - x, oldMipBias)\n            + sampleOldMip(omTexture, vTextureCoord + diagonalUp, oldMipBias)\n            + sampleOldMip(omTexture, vTextureCoord - diagonalUp, oldMipBias)\n            + sampleOldMip(omTexture, vTextureCoord + diagonalDown, oldMipBias)\n            + sampleOldMip(omTexture, vTextureCoord - diagonalDown, oldMipBias)) * 0.125;\n    return mix(texture2D(sTexture, vTextureCoord), color, oldBlurMix);\n}\nvoid main() {\n    vec4 oldFrame = sampleOldFrame();\n    vec4 newFrame = mix(texture2D(nTexture, vTextureCoord),\n            texture2D(nbTexture, vTextureCoord), newBlur);\n    vec3 camera = mix(oldFrame.rgb, newFrame.rgb, mixValue);\n    vec3 background = mix(texture2D(oldBackground, vTextureCoord).rgb,\n            texture2D(newBackground, vTextureCoord).rgb, mixValue) * 0.25;\n    float mask = texture2D(maskTexture, vTextureCoord).a;\n    gl_FragColor = vec4(mix(background, camera, mask), 1.0);\n}\n"

    goto :goto_0

    .line 768
    :cond_0
    const-string p0, "#ifdef GL_FRAGMENT_PRECISION_HIGH\nprecision highp float;\n#else\nprecision mediump float;\n#endif\nvarying vec2 vTextureCoord;\nuniform sampler2D sTexture;\nuniform sampler2D omTexture;\nuniform sampler2D nTexture;\nuniform sampler2D nbTexture;\nuniform vec2 oldBlurStep;\nuniform float oldMipBias;\nuniform float oldBlurMix;\nuniform float newBlur;\nuniform float mixValue;\nvec4 sampleOldFrame() {\n    vec2 x = vec2(oldBlurStep.x, 0.0);\n    vec2 diagonalUp = vec2(oldBlurStep.x * 0.5, oldBlurStep.y * 0.8660254);\n    vec2 diagonalDown = vec2(oldBlurStep.x * 0.5, -oldBlurStep.y * 0.8660254);\n    vec4 color = sampleOldMip(omTexture, vTextureCoord, oldMipBias) * 0.25;\n    color += (sampleOldMip(omTexture, vTextureCoord + x, oldMipBias)\n            + sampleOldMip(omTexture, vTextureCoord - x, oldMipBias)\n            + sampleOldMip(omTexture, vTextureCoord + diagonalUp, oldMipBias)\n            + sampleOldMip(omTexture, vTextureCoord - diagonalUp, oldMipBias)\n            + sampleOldMip(omTexture, vTextureCoord + diagonalDown, oldMipBias)\n            + sampleOldMip(omTexture, vTextureCoord - diagonalDown, oldMipBias)) * 0.125;\n    return mix(texture2D(sTexture, vTextureCoord), color, oldBlurMix);\n}\nvoid main() {\n    vec4 oldFrame = sampleOldFrame();\n    vec4 newFrame = mix(texture2D(nTexture, vTextureCoord),\n            texture2D(nbTexture, vTextureCoord), newBlur);\n    gl_FragColor = mix(oldFrame, newFrame, mixValue);\n}\n"

    .line 769
    :goto_0
    const-string v0, "sampleOldMip"

    if-eqz p1, :cond_1

    .line 770
    new-instance p1, Ljava/lang/StringBuilder;

    const-string v1, "#extension GL_EXT_shader_texture_lod : require\n"

    invoke-direct {p1, v1}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    const-string v1, "texture2DLodEXT"

    .line 771
    invoke-virtual {p0, v0, v1}, Ljava/lang/String;->replace(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;

    move-result-object p0

    invoke-virtual {p1, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    return-object p0

    .line 773
    :cond_1
    const-string p1, "texture2D"

    invoke-virtual {p0, v0, p1}, Ljava/lang/String;->replace(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method private createWatermarkAtlas([F[F)V
    .locals 20

    move-object/from16 v0, p0

    const/4 v1, 0x3

    const/16 v2, 0x2601

    .line 912
    invoke-direct {v0, v1, v2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->configureTexture(II)V

    .line 913
    iget v1, v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->outputSize:I

    invoke-static {v1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->getWatermarkAtlas(I)Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$WatermarkAtlas;

    move-result-object v1

    const/4 v2, 0x0

    move v3, v2

    :goto_0
    const/16 v4, 0x1b

    const/4 v5, 0x1

    if-ge v3, v4, :cond_0

    .line 915
    rem-int/lit8 v4, v3, 0x8

    .line 916
    div-int/lit8 v6, v3, 0x8

    mul-int/lit8 v7, v3, 0x18

    add-int/lit8 v9, v7, 0x8

    .line 918
    iget v8, v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$WatermarkAtlas;->logoAtlasHeight:I

    int-to-float v8, v8

    iget v10, v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$WatermarkAtlas;->height:I

    int-to-float v10, v10

    div-float v11, v8, v10

    iget v8, v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$WatermarkAtlas;->textSize:I

    int-to-float v8, v8

    iget v10, v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$WatermarkAtlas;->width:I

    int-to-float v10, v10

    div-float v12, v8, v10

    const/high16 v13, 0x3f800000    # 1.0f

    const/4 v10, 0x0

    move-object/from16 v8, p2

    invoke-static/range {v8 .. v13}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->setTriangleTextureCoordinates([FIFFFF)V

    add-int/lit8 v15, v7, 0x14

    .line 926
    iget v7, v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$WatermarkAtlas;->frameSize:I

    mul-int v8, v7, v4

    int-to-float v8, v8

    iget v9, v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$WatermarkAtlas;->width:I

    int-to-float v10, v9

    div-float v16, v8, v10

    mul-int v8, v7, v6

    int-to-float v8, v8

    iget v10, v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$WatermarkAtlas;->height:I

    int-to-float v11, v10

    div-float v17, v8, v11

    add-int/2addr v4, v5

    mul-int/2addr v4, v7

    int-to-float v4, v4

    int-to-float v8, v9

    div-float v18, v4, v8

    add-int/2addr v6, v5

    mul-int/2addr v7, v6

    int-to-float v4, v7

    int-to-float v5, v10

    div-float v19, v4, v5

    move-object/from16 v14, p2

    invoke-static/range {v14 .. v19}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->setTriangleTextureCoordinates([FIFFFF)V

    add-int/lit8 v3, v3, 0x1

    goto :goto_0

    .line 936
    :cond_0
    iget v3, v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$WatermarkAtlas;->textSize:I

    int-to-float v3, v3

    iget v4, v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->outputSize:I

    int-to-float v4, v4

    div-float/2addr v3, v4

    const/high16 v4, 0x40000000    # 2.0f

    mul-float/2addr v3, v4

    const/high16 v6, 0x3f800000    # 1.0f

    sub-float v9, v6, v3

    const/high16 v11, 0x3f800000    # 1.0f

    const/high16 v6, -0x40800000    # -1.0f

    add-float v12, v3, v6

    const/16 v8, 0xc

    const/high16 v10, -0x40800000    # -1.0f

    move-object/from16 v7, p1

    .line 937
    invoke-static/range {v7 .. v12}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->setTriangleVertexCoordinates([FIFFFF)V

    .line 945
    iget v3, v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$WatermarkAtlas;->frameSize:I

    int-to-float v3, v3

    iget v0, v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->outputSize:I

    int-to-float v0, v0

    div-float/2addr v3, v0

    mul-float/2addr v3, v4

    add-float v17, v3, v6

    const/16 v14, 0x1e

    const/high16 v15, -0x40800000    # -1.0f

    const/high16 v16, -0x40800000    # -1.0f

    move/from16 v18, v17

    move-object/from16 v13, p1

    .line 946
    invoke-static/range {v13 .. v18}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->setTriangleVertexCoordinates([FIFFFF)V

    .line 954
    iget-object v0, v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$WatermarkAtlas;->pixels:Ljava/nio/ByteBuffer;

    invoke-virtual {v0}, Ljava/nio/ByteBuffer;->duplicate()Ljava/nio/ByteBuffer;

    move-result-object v14

    .line 955
    invoke-virtual {v14, v2}, Ljava/nio/ByteBuffer;->position(I)Ljava/nio/Buffer;

    const/16 v0, 0xcf5

    .line 956
    invoke-static {v0, v5}, Landroid/opengl/GLES20;->glPixelStorei(II)V

    .line 957
    iget v9, v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$WatermarkAtlas;->width:I

    iget v10, v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$WatermarkAtlas;->height:I

    const/16 v12, 0x1906

    const/16 v13, 0x1401

    const/16 v6, 0xde1

    const/4 v7, 0x0

    const/16 v8, 0x1906

    const/4 v11, 0x0

    invoke-static/range {v6 .. v14}, Landroid/opengl/GLES20;->glTexImage2D(IIIIIIIILjava/nio/Buffer;)V

    const/4 v1, 0x4

    .line 968
    invoke-static {v0, v1}, Landroid/opengl/GLES20;->glPixelStorei(II)V

    return-void
.end method

.method private generateOldSwitchMipmaps()V
    .locals 2

    const v0, 0x8d40

    const/4 v1, 0x0

    .line 727
    invoke-static {v0, v1}, Landroid/opengl/GLES20;->glBindFramebuffer(II)V

    .line 728
    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->textures:[I

    const/4 v0, 0x5

    aget p0, p0, v0

    const/16 v0, 0xde1

    invoke-static {v0, p0}, Landroid/opengl/GLES20;->glBindTexture(II)V

    .line 729
    invoke-static {v0}, Landroid/opengl/GLES20;->glGenerateMipmap(I)V

    const/16 p0, 0x2801

    const/16 v1, 0x2703

    .line 730
    invoke-static {v0, p0, v1}, Landroid/opengl/GLES20;->glTexParameteri(III)V

    return-void
.end method

.method private getCropSize()I
    .locals 2

    .line 1081
    iget v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->sourceCropSize:I

    iget-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->inputSize:Landroid/util/Size;

    .line 1083
    invoke-virtual {v1}, Landroid/util/Size;->getWidth()I

    move-result v1

    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->inputSize:Landroid/util/Size;

    invoke-virtual {p0}, Landroid/util/Size;->getHeight()I

    move-result p0

    invoke-static {v1, p0}, Ljava/lang/Math;->min(II)I

    move-result p0

    .line 1081
    invoke-static {v0, p0}, Ljava/lang/Math;->min(II)I

    move-result p0

    return p0
.end method

.method private getHistoryWeight(J)F
    .locals 6

    .line 716
    iget-boolean v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->historyValid:Z

    const/4 v1, 0x0

    if-eqz v0, :cond_2

    iget-wide v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->lastHistoryFrameNs:J

    const-wide/16 v4, 0x0

    cmp-long p0, v2, v4

    if-nez p0, :cond_0

    goto :goto_0

    :cond_0
    sub-long/2addr p1, v2

    cmp-long p0, p1, v4

    if-lez p0, :cond_2

    const-wide/32 v2, 0x5f5e100

    cmp-long p0, p1, v2

    if-lez p0, :cond_1

    goto :goto_0

    :cond_1
    neg-long p0, p1

    long-to-double p0, p0

    const-wide v0, 0x41b0b07600000000L    # 2.8E8

    div-double/2addr p0, v0

    .line 723
    invoke-static {p0, p1}, Ljava/lang/Math;->exp(D)D

    move-result-wide p0

    double-to-float p0, p0

    return p0

    :cond_2
    :goto_0
    return v1
.end method

.method private getSwitchNewMaximumBlurRadiusPx()F
    .locals 0

    const/high16 p0, 0x41100000    # 9.0f

    return p0
.end method

.method private getSwitchOldMipParameter(F)F
    .locals 8

    const/high16 v0, 0x3f800000    # 1.0f

    .line 743
    invoke-static {v0, p1}, Ljava/lang/Math;->max(FF)F

    move-result p1

    const/high16 v0, 0x3f200000    # 0.625f

    mul-float/2addr p1, v0

    const/high16 v0, 0x44000000    # 512.0f

    mul-float/2addr p1, v0

    iget v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->outputSize:I

    int-to-float v0, v0

    div-float/2addr p1, v0

    float-to-double v0, p1

    .line 748
    invoke-static {v0, v1}, Ljava/lang/Math;->log(D)D

    move-result-wide v0

    const-wide/high16 v2, 0x4000000000000000L    # 2.0

    invoke-static {v2, v3}, Ljava/lang/Math;->log(D)D

    move-result-wide v4

    div-double/2addr v0, v4

    double-to-float p1, v0

    const/high16 v0, 0x41000000    # 8.0f

    .line 746
    invoke-static {v0, p1}, Ljava/lang/Math;->min(FF)F

    move-result p1

    const/high16 v0, 0x3fa00000    # 1.25f

    add-float/2addr p1, v0

    .line 750
    iget-boolean v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->explicitTextureLod:Z

    const/4 v1, 0x0

    if-eqz v0, :cond_0

    invoke-static {v1, p1}, Ljava/lang/Math;->max(FF)F

    move-result p0

    return p0

    .line 751
    :cond_0
    iget p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->outputSize:I

    int-to-double v4, p0

    const-wide/high16 v6, 0x4080000000000000L    # 512.0

    div-double/2addr v6, v4

    .line 752
    invoke-static {v6, v7}, Ljava/lang/Math;->log(D)D

    move-result-wide v4

    invoke-static {v2, v3}, Ljava/lang/Math;->log(D)D

    move-result-wide v2

    div-double/2addr v4, v2

    double-to-float p0, v4

    sub-float/2addr p1, p0

    .line 754
    invoke-static {v1, p1}, Ljava/lang/Math;->max(FF)F

    move-result p0

    return p0
.end method

.method private static declared-synchronized getWatermarkAtlas(I)Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$WatermarkAtlas;
    .locals 17

    move/from16 v0, p0

    const-class v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;

    monitor-enter v1

    const/16 v2, 0x1e0

    const/16 v4, 0x168

    if-ne v0, v4, :cond_0

    .line 973
    :try_start_0
    sget-object v5, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->cachedWatermark360:Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$WatermarkAtlas;

    goto :goto_0

    :catchall_0
    move-exception v0

    goto/16 :goto_3

    :cond_0
    if-ne v0, v2, :cond_1

    .line 974
    sget-object v5, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->cachedWatermark480:Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$WatermarkAtlas;
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    goto :goto_0

    :cond_1
    const/4 v5, 0x0

    :goto_0
    if-eqz v5, :cond_2

    .line 975
    monitor-exit v1

    return-object v5

    :cond_2
    int-to-float v5, v0

    const/high16 v6, 0x43ba0000    # 372.0f

    mul-float/2addr v6, v5

    const/high16 v7, 0x44c00000    # 1536.0f

    div-float/2addr v6, v7

    .line 977
    :try_start_1
    invoke-static {v6}, Ljava/lang/Math;->round(F)I

    move-result v11

    const v6, 0x3e4ccccd    # 0.2f

    mul-float/2addr v6, v5

    .line 978
    invoke-static {v6}, Ljava/lang/Math;->round(F)I

    move-result v6

    const/high16 v8, 0x41e00000    # 28.0f

    mul-float/2addr v5, v8

    div-float/2addr v5, v7

    .line 979
    invoke-static {v5}, Ljava/lang/Math;->round(F)I

    move-result v5

    mul-int/lit8 v7, v5, 0x2

    sub-int v12, v6, v7

    mul-int/lit8 v7, v12, 0x8

    mul-int/lit8 v13, v12, 0x4

    .line 983
    invoke-static {v7, v11}, Ljava/lang/Math;->max(II)I

    move-result v9

    add-int v10, v13, v11

    .line 986
    sget v7, Lorg/telegram/messenger/R$raw;->plane_logo_plain:I

    .line 987
    invoke-static {v7}, Lorg/telegram/messenger/AndroidUtilities;->readRes(I)Ljava/lang/String;

    move-result-object v7

    .line 986
    invoke-static {v7}, Lorg/telegram/ui/Components/RLottieNative;->createFromRawJson(Ljava/lang/String;)Lorg/telegram/ui/Components/RLottieNative;

    move-result-object v7

    if-eqz v7, :cond_8

    .line 990
    sget-object v8, Landroid/graphics/Bitmap$Config;->ARGB_8888:Landroid/graphics/Bitmap$Config;

    invoke-static {v6, v6, v8}, Landroid/graphics/Bitmap;->createBitmap(IILandroid/graphics/Bitmap$Config;)Landroid/graphics/Bitmap;

    move-result-object v6

    .line 991
    sget-object v8, Landroid/graphics/Bitmap$Config;->ALPHA_8:Landroid/graphics/Bitmap$Config;

    invoke-static {v9, v10, v8}, Landroid/graphics/Bitmap;->createBitmap(IILandroid/graphics/Bitmap$Config;)Landroid/graphics/Bitmap;

    move-result-object v15

    .line 992
    new-instance v8, Landroid/graphics/Canvas;

    invoke-direct {v8, v15}, Landroid/graphics/Canvas;-><init>(Landroid/graphics/Bitmap;)V

    const/4 v2, 0x0

    :goto_1
    const/16 v4, 0x1b

    const/4 v14, 0x1

    if-ge v2, v4, :cond_3

    .line 994
    rem-int/lit8 v4, v2, 0x8

    .line 995
    div-int/lit8 v16, v2, 0x8

    mul-int/lit8 v3, v2, 0x2

    .line 996
    invoke-virtual {v7, v3, v6, v14}, Lorg/telegram/ui/Components/RLottieNative;->getFrame(ILandroid/graphics/Bitmap;Z)I

    mul-int/2addr v4, v12

    sub-int/2addr v4, v5

    int-to-float v3, v4

    mul-int v16, v16, v12

    sub-int v4, v16, v5

    int-to-float v4, v4

    const/4 v14, 0x0

    .line 997
    invoke-virtual {v8, v6, v3, v4, v14}, Landroid/graphics/Canvas;->drawBitmap(Landroid/graphics/Bitmap;FFLandroid/graphics/Paint;)V

    add-int/lit8 v2, v2, 0x1

    goto :goto_1

    .line 1000
    :cond_3
    sget v2, Lorg/telegram/messenger/R$raw;->round_blur_overlay_text:I

    invoke-static {v2}, Lorg/telegram/messenger/AndroidUtilities;->getBitmapFromRaw(I)Landroid/graphics/Bitmap;

    move-result-object v2

    if-eqz v2, :cond_4

    .line 1002
    invoke-static {v2, v11, v11, v14}, Landroid/graphics/Bitmap;->createScaledBitmap(Landroid/graphics/Bitmap;IIZ)Landroid/graphics/Bitmap;

    move-result-object v3

    .line 1003
    invoke-virtual {v3}, Landroid/graphics/Bitmap;->extractAlpha()Landroid/graphics/Bitmap;

    move-result-object v4

    const/4 v5, 0x0

    int-to-float v14, v13

    move-object/from16 v16, v2

    const/4 v2, 0x0

    .line 1004
    invoke-virtual {v8, v4, v5, v14, v2}, Landroid/graphics/Canvas;->drawBitmap(Landroid/graphics/Bitmap;FFLandroid/graphics/Paint;)V

    .line 1005
    invoke-virtual {v4}, Landroid/graphics/Bitmap;->recycle()V

    .line 1006
    invoke-virtual {v3}, Landroid/graphics/Bitmap;->recycle()V

    .line 1007
    invoke-virtual/range {v16 .. v16}, Landroid/graphics/Bitmap;->recycle()V

    .line 1010
    :cond_4
    invoke-virtual {v15}, Landroid/graphics/Bitmap;->getRowBytes()I

    move-result v2

    if-ne v2, v9, :cond_7

    mul-int v2, v9, v10

    .line 1016
    invoke-static {v2}, Ljava/nio/ByteBuffer;->allocateDirect(I)Ljava/nio/ByteBuffer;

    move-result-object v14

    .line 1017
    invoke-virtual {v15, v14}, Landroid/graphics/Bitmap;->copyPixelsToBuffer(Ljava/nio/Buffer;)V

    const/4 v2, 0x0

    .line 1018
    invoke-virtual {v14, v2}, Ljava/nio/ByteBuffer;->position(I)Ljava/nio/Buffer;

    .line 1019
    new-instance v8, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$WatermarkAtlas;

    invoke-direct/range {v8 .. v14}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$WatermarkAtlas;-><init>(IIIIILjava/nio/ByteBuffer;)V

    .line 1027
    invoke-virtual {v15}, Landroid/graphics/Bitmap;->recycle()V

    .line 1028
    invoke-virtual {v6}, Landroid/graphics/Bitmap;->recycle()V

    .line 1029
    invoke-virtual {v7}, Lorg/telegram/ui/Components/RLottieNative;->recycle()V

    const/16 v2, 0x168

    if-ne v0, v2, :cond_5

    .line 1030
    sput-object v8, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->cachedWatermark360:Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$WatermarkAtlas;

    goto :goto_2

    :cond_5
    const/16 v2, 0x1e0

    if-ne v0, v2, :cond_6

    .line 1031
    sput-object v8, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->cachedWatermark480:Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$WatermarkAtlas;
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    .line 1032
    :cond_6
    :goto_2
    monitor-exit v1

    return-object v8

    .line 1011
    :cond_7
    :try_start_2
    invoke-virtual {v15}, Landroid/graphics/Bitmap;->recycle()V

    .line 1012
    invoke-virtual {v6}, Landroid/graphics/Bitmap;->recycle()V

    .line 1013
    invoke-virtual {v7}, Lorg/telegram/ui/Components/RLottieNative;->recycle()V

    .line 1014
    new-instance v0, Ljava/lang/IllegalStateException;

    const-string v2, "Unexpected watermark atlas stride"

    invoke-direct {v0, v2}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 989
    :cond_8
    new-instance v0, Ljava/lang/IllegalStateException;

    const-string v2, "Unable to load watermark animation"

    invoke-direct {v0, v2}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 1032
    :goto_3
    monitor-exit v1
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_0

    throw v0
.end method

.method private renderBackgroundBlur(I)V
    .locals 14

    const/16 v4, 0x30

    const/4 v6, 0x0

    const/4 v2, 0x6

    const/16 v3, 0x30

    const v5, 0x3caaaaab

    move-object v0, p0

    move v1, p1

    .line 663
    invoke-direct/range {v0 .. v6}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->renderGaussianBlurPass(IIIIFF)V

    const/16 v11, 0x30

    const/4 v12, 0x0

    const/16 v8, 0x8

    const/16 v10, 0x30

    move-object v7, v0

    move v9, v1

    move v13, v5

    .line 671
    invoke-direct/range {v7 .. v13}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->renderGaussianBlurPass(IIIIFF)V

    return-void
.end method

.method private renderGaussianBlurPass(IIIIFF)V
    .locals 1

    .line 689
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->frameBuffers:[I

    aget p2, v0, p2

    const v0, 0x8d40

    invoke-static {v0, p2}, Landroid/opengl/GLES20;->glBindFramebuffer(II)V

    const/4 p2, 0x0

    .line 690
    invoke-static {p2, p2, p3, p4}, Landroid/opengl/GLES20;->glViewport(IIII)V

    .line 691
    iget-object p3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->gaussianBlurProgram:Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$GaussianBlurProgram;

    invoke-direct {p0, p3, p2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->bindProgram(Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$Program;I)V

    .line 692
    iget-object p3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->gaussianBlurProgram:Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$GaussianBlurProgram;

    iget p3, p3, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$Program;->textureHandle:I

    invoke-direct {p0, p2, p1, p3}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->bindTexture(III)V

    .line 693
    iget-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->gaussianBlurProgram:Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$GaussianBlurProgram;

    iget p1, p1, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$GaussianBlurProgram;->offsetHandle:I

    invoke-static {p1, p5, p6}, Landroid/opengl/GLES20;->glUniform2f(IFF)V

    const/4 p1, 0x5

    const/4 p3, 0x4

    .line 694
    invoke-static {p1, p2, p3}, Landroid/opengl/GLES20;->glDrawArrays(III)V

    .line 695
    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->gaussianBlurProgram:Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$GaussianBlurProgram;

    invoke-static {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->unbindProgram(Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$Program;)V

    return-void
.end method

.method private renderMix(I[FIII)V
    .locals 2

    const v0, 0x8d40

    const/4 v1, 0x0

    .line 564
    invoke-static {v0, v1}, Landroid/opengl/GLES20;->glBindFramebuffer(II)V

    .line 565
    invoke-static {v1, v1, p4, p5}, Landroid/opengl/GLES20;->glViewport(IIII)V

    .line 566
    iget-object p4, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->mixProgram:Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$MixProgram;

    invoke-direct {p0, p4, p2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->bindCameraProgram(Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$CameraProgram;[F)V

    .line 567
    iget-object p2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->mixProgram:Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$MixProgram;

    iget p2, p2, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$Program;->textureHandle:I

    invoke-static {v1, p1, p2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->bindExternalTexture(III)V

    .line 568
    iget-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->mixProgram:Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$MixProgram;

    iget p1, p1, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$MixProgram;->blurredTextureHandle:I

    const/4 p2, 0x1

    invoke-direct {p0, p2, p3, p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->bindTexture(III)V

    .line 569
    iget-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->mixProgram:Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$MixProgram;

    iget p1, p1, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$MixProgram;->maskTextureHandle:I

    const/4 p2, 0x2

    invoke-direct {p0, p2, p2, p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->bindTexture(III)V

    const/4 p1, 0x5

    const/4 p2, 0x4

    .line 570
    invoke-static {p1, v1, p2}, Landroid/opengl/GLES20;->glDrawArrays(III)V

    .line 571
    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->mixProgram:Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$MixProgram;

    invoke-static {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->unbindProgram(Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$Program;)V

    return-void
.end method

.method private renderNewSwitchBlur(F)V
    .locals 8

    .line 636
    iget v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->newSwitchBlurRadius:F

    sub-float/2addr v0, p1

    invoke-static {v0}, Ljava/lang/Math;->abs(F)F

    move-result v0

    const v1, 0x3e4ccccd    # 0.2f

    cmpg-float v0, v0, v1

    if-gez v0, :cond_0

    return-void

    .line 639
    :cond_0
    iget v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->outputSize:I

    mul-int/lit8 v0, v0, 0x9

    int-to-float v0, v0

    div-float v6, p1, v0

    .line 641
    iget v4, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->switchNewBlurSize:I

    const/4 v7, 0x0

    const/4 v2, 0x6

    const/4 v3, 0x7

    move v5, v4

    move-object v1, p0

    invoke-direct/range {v1 .. v7}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->renderSwitchGaussianBlurPass(IIIIFF)V

    .line 649
    iget v4, v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->switchNewBlurSize:I

    move v7, v6

    const/4 v6, 0x0

    const/16 v2, 0x9

    const/4 v3, 0x5

    move v5, v4

    invoke-direct/range {v1 .. v7}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->renderSwitchGaussianBlurPass(IIIIFF)V

    .line 657
    iput p1, v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->newSwitchBlurRadius:F

    return-void
.end method

.method private renderRawCamera(I[FIII)V
    .locals 1

    .line 599
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->frameBuffers:[I

    aget p3, v0, p3

    const v0, 0x8d40

    invoke-static {v0, p3}, Landroid/opengl/GLES20;->glBindFramebuffer(II)V

    const/4 p3, 0x0

    .line 600
    invoke-static {p3, p3, p4, p5}, Landroid/opengl/GLES20;->glViewport(IIII)V

    .line 601
    iget-object p4, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->cameraProgram:Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$CameraProgram;

    invoke-direct {p0, p4, p2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->bindCameraProgram(Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$CameraProgram;[F)V

    .line 602
    iget-object p2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->cameraProgram:Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$CameraProgram;

    iget p2, p2, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$Program;->textureHandle:I

    invoke-static {p3, p1, p2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->bindExternalTexture(III)V

    const/4 p1, 0x5

    const/4 p2, 0x4

    .line 603
    invoke-static {p1, p3, p2}, Landroid/opengl/GLES20;->glDrawArrays(III)V

    .line 604
    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->cameraProgram:Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$CameraProgram;

    invoke-static {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->unbindProgram(Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$Program;)V

    return-void
.end method

.method private renderRawCameraToOutput(I[FII)V
    .locals 2

    const v0, 0x8d40

    const/4 v1, 0x0

    .line 613
    invoke-static {v0, v1}, Landroid/opengl/GLES20;->glBindFramebuffer(II)V

    .line 614
    invoke-static {v1, v1, p3, p4}, Landroid/opengl/GLES20;->glViewport(IIII)V

    .line 615
    iget-object p3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->cameraProgram:Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$CameraProgram;

    invoke-direct {p0, p3, p2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->bindCameraProgram(Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$CameraProgram;[F)V

    .line 616
    iget-object p2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->cameraProgram:Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$CameraProgram;

    iget p2, p2, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$Program;->textureHandle:I

    invoke-static {v1, p1, p2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->bindExternalTexture(III)V

    const/4 p1, 0x5

    const/4 p2, 0x4

    .line 617
    invoke-static {p1, v1, p2}, Landroid/opengl/GLES20;->glDrawArrays(III)V

    .line 618
    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->cameraProgram:Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$CameraProgram;

    invoke-static {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->unbindProgram(Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$Program;)V

    return-void
.end method

.method private renderSwitchGaussianBlurPass(IIIIFF)V
    .locals 1

    .line 706
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->frameBuffers:[I

    aget p2, v0, p2

    const v0, 0x8d40

    invoke-static {v0, p2}, Landroid/opengl/GLES20;->glBindFramebuffer(II)V

    const/4 p2, 0x0

    .line 707
    invoke-static {p2, p2, p3, p4}, Landroid/opengl/GLES20;->glViewport(IIII)V

    .line 708
    iget-object p3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->switchGaussianBlurProgram:Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$GaussianBlurProgram;

    invoke-direct {p0, p3, p2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->bindProgram(Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$Program;I)V

    .line 709
    iget-object p3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->switchGaussianBlurProgram:Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$GaussianBlurProgram;

    iget p3, p3, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$Program;->textureHandle:I

    invoke-direct {p0, p2, p1, p3}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->bindTexture(III)V

    .line 710
    iget-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->switchGaussianBlurProgram:Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$GaussianBlurProgram;

    iget p1, p1, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$GaussianBlurProgram;->offsetHandle:I

    invoke-static {p1, p5, p6}, Landroid/opengl/GLES20;->glUniform2f(IFF)V

    const/4 p1, 0x5

    const/4 p3, 0x4

    .line 711
    invoke-static {p1, p2, p3}, Landroid/opengl/GLES20;->glDrawArrays(III)V

    .line 712
    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->switchGaussianBlurProgram:Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$GaussianBlurProgram;

    invoke-static {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->unbindProgram(Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$Program;)V

    return-void
.end method

.method private renderTemporalBackground(I[FIF)V
    .locals 5

    .line 530
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->frameBuffers:[I

    aget p3, v0, p3

    const v0, 0x8d40

    invoke-static {v0, p3}, Landroid/opengl/GLES20;->glBindFramebuffer(II)V

    const/16 p3, 0x30

    const/4 v0, 0x0

    .line 531
    invoke-static {v0, v0, p3, p3}, Landroid/opengl/GLES20;->glViewport(IIII)V

    .line 532
    iget-object p3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->temporalProgram:Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$TemporalProgram;

    invoke-direct {p0, p3, p2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->bindCameraProgram(Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$CameraProgram;[F)V

    .line 533
    iget-object p3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->temporalProgram:Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$TemporalProgram;

    iget p3, p3, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$Program;->textureHandle:I

    invoke-static {v0, p1, p3}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->bindExternalTexture(III)V

    .line 534
    iget p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->historyReadTexture:I

    iget-object p3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->temporalProgram:Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$TemporalProgram;

    iget p3, p3, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$TemporalProgram;->historyTextureHandle:I

    const/4 v1, 0x1

    invoke-direct {p0, v1, p1, p3}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->bindTexture(III)V

    .line 536
    invoke-static {p2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->swapsTextureAxes([F)Z

    move-result p1

    .line 537
    invoke-direct {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->getCropSize()I

    move-result p3

    .line 538
    iget-object v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->inputSize:Landroid/util/Size;

    if-eqz p1, :cond_0

    invoke-virtual {v2}, Landroid/util/Size;->getHeight()I

    move-result v2

    :goto_0
    int-to-float v2, v2

    goto :goto_1

    :cond_0
    invoke-virtual {v2}, Landroid/util/Size;->getWidth()I

    move-result v2

    goto :goto_0

    .line 539
    :goto_1
    iget-object v3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->inputSize:Landroid/util/Size;

    if-eqz p1, :cond_1

    invoke-virtual {v3}, Landroid/util/Size;->getWidth()I

    move-result p1

    :goto_2
    int-to-float p1, p1

    goto :goto_3

    :cond_1
    invoke-virtual {v3}, Landroid/util/Size;->getHeight()I

    move-result p1

    goto :goto_2

    :goto_3
    int-to-float p3, p3

    div-float v2, p3, v2

    const/high16 v3, 0x42400000    # 48.0f

    div-float/2addr v2, v3

    const v4, 0x3ee66666    # 0.45f

    mul-float/2addr v2, v4

    div-float/2addr p3, p1

    div-float/2addr p3, v3

    mul-float/2addr p3, v4

    .line 542
    iget-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->temporalProgram:Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$TemporalProgram;

    iget p1, p1, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$TemporalProgram;->sampleStepXHandle:I

    aget v3, p2, v0

    mul-float/2addr v3, v2

    aget v1, p2, v1

    mul-float/2addr v1, v2

    invoke-static {p1, v3, v1}, Landroid/opengl/GLES20;->glUniform2f(IFF)V

    .line 547
    iget-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->temporalProgram:Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$TemporalProgram;

    iget p1, p1, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$TemporalProgram;->sampleStepYHandle:I

    const/4 v1, 0x4

    aget v2, p2, v1

    mul-float/2addr v2, p3

    const/4 v3, 0x5

    aget p2, p2, v3

    mul-float/2addr p2, p3

    invoke-static {p1, v2, p2}, Landroid/opengl/GLES20;->glUniform2f(IFF)V

    .line 552
    iget-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->temporalProgram:Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$TemporalProgram;

    iget p1, p1, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$TemporalProgram;->historyWeightHandle:I

    invoke-static {p1, p4}, Landroid/opengl/GLES20;->glUniform1f(IF)V

    .line 553
    invoke-static {v3, v0, v1}, Landroid/opengl/GLES20;->glDrawArrays(III)V

    .line 554
    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->temporalProgram:Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$TemporalProgram;

    invoke-static {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->unbindProgram(Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$Program;)V

    return-void
.end method

.method private renderTextureCopy(IIII)V
    .locals 1

    .line 627
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->frameBuffers:[I

    aget p2, v0, p2

    const v0, 0x8d40

    invoke-static {v0, p2}, Landroid/opengl/GLES20;->glBindFramebuffer(II)V

    const/4 p2, 0x0

    .line 628
    invoke-static {p2, p2, p3, p4}, Landroid/opengl/GLES20;->glViewport(IIII)V

    .line 629
    iget-object p3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->copyProgram:Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$Program;

    invoke-direct {p0, p3, p2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->bindProgram(Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$Program;I)V

    .line 630
    iget-object p3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->copyProgram:Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$Program;

    iget p3, p3, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$Program;->textureHandle:I

    invoke-direct {p0, p2, p1, p3}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->bindTexture(III)V

    const/4 p1, 0x5

    const/4 p3, 0x4

    .line 631
    invoke-static {p1, p2, p3}, Landroid/opengl/GLES20;->glDrawArrays(III)V

    .line 632
    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->copyProgram:Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$Program;

    invoke-static {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->unbindProgram(Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$Program;)V

    return-void
.end method

.method private renderWatermarks()V
    .locals 5

    .line 575
    iget-boolean v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->compositionEnabled:Z

    if-eqz v0, :cond_1

    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->watermarkProgram:Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$Program;

    if-nez v0, :cond_0

    goto :goto_0

    :cond_0
    const/16 v0, 0xbe2

    .line 576
    invoke-static {v0}, Landroid/opengl/GLES20;->glEnable(I)V

    const/16 v1, 0x302

    const/16 v2, 0x303

    .line 577
    invoke-static {v1, v2}, Landroid/opengl/GLES20;->glBlendFunc(II)V

    .line 579
    iget v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->logoFrame:I

    add-int/lit8 v2, v1, 0x1

    iput v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->logoFrame:I

    rem-int/lit8 v1, v1, 0x1b

    .line 580
    iget-object v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->watermarkProgram:Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$Program;

    mul-int/lit8 v1, v1, 0x18

    add-int/lit8 v1, v1, 0x8

    const/16 v3, 0xc

    invoke-direct {p0, v2, v1, v3}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->bindProgram(Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$Program;II)V

    .line 585
    iget-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->watermarkProgram:Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$Program;

    iget v1, v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$Program;->textureHandle:I

    const/4 v2, 0x0

    const/4 v4, 0x3

    invoke-direct {p0, v2, v4, v1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->bindTexture(III)V

    const/4 v1, 0x4

    .line 586
    invoke-static {v1, v2, v3}, Landroid/opengl/GLES20;->glDrawArrays(III)V

    .line 587
    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->watermarkProgram:Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$Program;

    invoke-static {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->unbindProgram(Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$Program;)V

    .line 589
    invoke-static {v0}, Landroid/opengl/GLES20;->glDisable(I)V

    :cond_1
    :goto_0
    return-void
.end method

.method private static setTextureCoordinate([FIFF)V
    .locals 0

    .line 1170
    aput p2, p0, p1

    add-int/lit8 p1, p1, 0x1

    .line 1171
    aput p3, p0, p1

    return-void
.end method

.method private static setTextureCoordinates([FIFFFF)V
    .locals 1

    .line 1121
    aput p2, p0, p1

    add-int/lit8 v0, p1, 0x1

    .line 1122
    aput p5, p0, v0

    add-int/lit8 v0, p1, 0x2

    .line 1123
    aput p4, p0, v0

    add-int/lit8 v0, p1, 0x3

    .line 1124
    aput p5, p0, v0

    add-int/lit8 p5, p1, 0x4

    .line 1125
    aput p2, p0, p5

    add-int/lit8 p2, p1, 0x5

    .line 1126
    aput p3, p0, p2

    add-int/lit8 p2, p1, 0x6

    .line 1127
    aput p4, p0, p2

    add-int/lit8 p1, p1, 0x7

    .line 1128
    aput p3, p0, p1

    return-void
.end method

.method private setTextureMinFilter(II)V
    .locals 0

    .line 738
    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->textures:[I

    aget p0, p0, p1

    const/16 p1, 0xde1

    invoke-static {p1, p0}, Landroid/opengl/GLES20;->glBindTexture(II)V

    const/16 p0, 0x2801

    .line 739
    invoke-static {p1, p0, p2}, Landroid/opengl/GLES20;->glTexParameteri(III)V

    return-void
.end method

.method private static setTriangleTextureCoordinates([FIFFFF)V
    .locals 1

    .line 1161
    invoke-static {p0, p1, p2, p5}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->setTextureCoordinate([FIFF)V

    add-int/lit8 v0, p1, 0x2

    .line 1162
    invoke-static {p0, v0, p4, p5}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->setTextureCoordinate([FIFF)V

    add-int/lit8 v0, p1, 0x4

    .line 1163
    invoke-static {p0, v0, p2, p3}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->setTextureCoordinate([FIFF)V

    add-int/lit8 v0, p1, 0x6

    .line 1164
    invoke-static {p0, v0, p2, p3}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->setTextureCoordinate([FIFF)V

    add-int/lit8 p2, p1, 0x8

    .line 1165
    invoke-static {p0, p2, p4, p5}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->setTextureCoordinate([FIFF)V

    add-int/lit8 p1, p1, 0xa

    .line 1166
    invoke-static {p0, p1, p4, p3}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->setTextureCoordinate([FIFF)V

    return-void
.end method

.method private static setTriangleVertexCoordinates([FIFFFF)V
    .locals 1

    .line 1139
    invoke-static {p0, p1, p2, p3}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->setVertex([FIFF)V

    add-int/lit8 v0, p1, 0x3

    .line 1140
    invoke-static {p0, v0, p4, p3}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->setVertex([FIFF)V

    add-int/lit8 v0, p1, 0x6

    .line 1141
    invoke-static {p0, v0, p2, p5}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->setVertex([FIFF)V

    add-int/lit8 v0, p1, 0x9

    .line 1142
    invoke-static {p0, v0, p2, p5}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->setVertex([FIFF)V

    add-int/lit8 p2, p1, 0xc

    .line 1143
    invoke-static {p0, p2, p4, p3}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->setVertex([FIFF)V

    add-int/lit8 p1, p1, 0xf

    .line 1144
    invoke-static {p0, p1, p4, p5}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->setVertex([FIFF)V

    return-void
.end method

.method private static setVertex([FIFF)V
    .locals 0

    .line 1148
    aput p2, p0, p1

    add-int/lit8 p2, p1, 0x1

    .line 1149
    aput p3, p0, p2

    add-int/lit8 p1, p1, 0x2

    const/4 p2, 0x0

    .line 1150
    aput p2, p0, p1

    return-void
.end method

.method private static setVertexCoordinates([FIFFFF)V
    .locals 2

    .line 1099
    aput p2, p0, p1

    add-int/lit8 v0, p1, 0x1

    .line 1100
    aput p5, p0, v0

    add-int/lit8 v0, p1, 0x2

    const/4 v1, 0x0

    .line 1101
    aput v1, p0, v0

    add-int/lit8 v0, p1, 0x3

    .line 1102
    aput p4, p0, v0

    add-int/lit8 v0, p1, 0x4

    .line 1103
    aput p5, p0, v0

    add-int/lit8 p5, p1, 0x5

    .line 1104
    aput v1, p0, p5

    add-int/lit8 p5, p1, 0x6

    .line 1105
    aput p2, p0, p5

    add-int/lit8 p2, p1, 0x7

    .line 1106
    aput p3, p0, p2

    add-int/lit8 p2, p1, 0x8

    .line 1107
    aput v1, p0, p2

    add-int/lit8 p2, p1, 0x9

    .line 1108
    aput p4, p0, p2

    add-int/lit8 p2, p1, 0xa

    .line 1109
    aput p3, p0, p2

    add-int/lit8 p1, p1, 0xb

    .line 1110
    aput v1, p0, p1

    return-void
.end method

.method private static supportsExplicitTextureLod()Z
    .locals 2

    const/16 v0, 0x1f03

    .line 758
    invoke-static {v0}, Landroid/opengl/GLES20;->glGetString(I)Ljava/lang/String;

    move-result-object v0

    if-eqz v0, :cond_0

    .line 759
    const-string v1, "GL_EXT_shader_texture_lod"

    invoke-virtual {v0, v1}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result v0

    if-eqz v0, :cond_0

    const/4 v0, 0x1

    return v0

    :cond_0
    const/4 v0, 0x0

    return v0
.end method

.method private static swapsTextureAxes([F)Z
    .locals 3

    const/4 v0, 0x1

    .line 1088
    aget v1, p0, v0

    invoke-static {v1}, Ljava/lang/Math;->abs(F)F

    move-result v1

    const/4 v2, 0x0

    aget p0, p0, v2

    invoke-static {p0}, Ljava/lang/Math;->abs(F)F

    move-result p0

    cmpl-float p0, v1, p0

    if-lez p0, :cond_0

    return v0

    :cond_0
    return v2
.end method

.method private static unbindProgram(Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$Program;)V
    .locals 0

    return-void
.end method


# virtual methods
.method public captureSwitchFrame(I[FZ)V
    .locals 8

    const/16 v0, 0xbe2

    .line 410
    invoke-static {v0}, Landroid/opengl/GLES20;->glDisable(I)V

    if-nez p3, :cond_0

    const/4 v0, 0x5

    const/16 v1, 0x2601

    .line 412
    invoke-direct {p0, v0, v1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->setTextureMinFilter(II)V

    :cond_0
    const/4 v0, 0x4

    if-eqz p3, :cond_1

    move v5, v0

    goto :goto_0

    :cond_1
    const/4 v1, 0x2

    move v5, v1

    .line 417
    :goto_0
    iget v6, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->outputSize:I

    move v7, v6

    move-object v2, p0

    move v3, p1

    move-object v4, p2

    .line 414
    invoke-direct/range {v2 .. v7}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->renderRawCamera(I[FIII)V

    const/high16 p0, -0x40800000    # -1.0f

    if-eqz p3, :cond_3

    .line 422
    iget p1, v2, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->historyWriteTexture:I

    iput p1, v2, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->switchNewBackgroundTexture:I

    .line 423
    iget-boolean p2, v2, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->compositionEnabled:Z

    if-eqz p2, :cond_2

    const/4 p2, 0x0

    .line 424
    invoke-direct {v2, v3, v4, p1, p2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->renderTemporalBackground(I[FIF)V

    .line 425
    iget p1, v2, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->switchNewBackgroundTexture:I

    invoke-direct {v2, p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->renderBackgroundBlur(I)V

    .line 427
    :cond_2
    iput p0, v2, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->newSwitchBlurRadius:F

    const/4 p0, 0x1

    .line 428
    iput-boolean p0, v2, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->switchNewFrameCaptured:Z

    return-void

    :cond_3
    const/4 p1, 0x3

    const/16 p2, 0x200

    .line 430
    invoke-direct {v2, v0, p1, p2, p2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->renderTextureCopy(IIII)V

    .line 436
    invoke-direct {v2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->generateOldSwitchMipmaps()V

    .line 437
    iget p1, v2, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->historyReadTexture:I

    iput p1, v2, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->switchOldBackgroundTexture:I

    .line 438
    iget p1, v2, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->historyWriteTexture:I

    iput p1, v2, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->switchNewBackgroundTexture:I

    const/4 p1, 0x0

    .line 439
    iput-boolean p1, v2, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->switchNewFrameCaptured:Z

    .line 440
    iput p0, v2, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->newSwitchBlurRadius:F

    return-void
.end method

.method public finishCameraSwitch(J)V
    .locals 1

    .line 499
    iget-boolean v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->switchNewFrameCaptured:Z

    if-nez v0, :cond_0

    return-void

    .line 502
    :cond_0
    iget-boolean v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->compositionEnabled:Z

    if-eqz v0, :cond_1

    .line 503
    iget v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->switchNewBackgroundTexture:I

    iput v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->historyReadTexture:I

    .line 504
    iget v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->switchOldBackgroundTexture:I

    iput v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->historyWriteTexture:I

    const/4 v0, 0x1

    .line 505
    iput-boolean v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->historyValid:Z

    .line 506
    iput-wide p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->lastHistoryFrameNs:J

    :cond_1
    const/4 p1, 0x0

    .line 508
    iput-boolean p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->switchNewFrameCaptured:Z

    return-void
.end method

.method public getSwitchTargetBlurRadiusPx()F
    .locals 1

    .line 445
    iget p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->outputSize:I

    int-to-float p0, p0

    const/high16 v0, 0x40800000    # 4.0f

    mul-float/2addr p0, v0

    const/high16 v0, 0x42400000    # 48.0f

    div-float/2addr p0, v0

    const v0, 0x3f933333    # 1.15f

    mul-float/2addr p0, v0

    return p0
.end method

.method public release()V
    .locals 3

    .line 512
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->cameraProgram:Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$CameraProgram;

    invoke-virtual {v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$Program;->release()V

    .line 513
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->copyProgram:Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$Program;

    invoke-virtual {v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$Program;->release()V

    .line 514
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->temporalProgram:Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$TemporalProgram;

    if-eqz v0, :cond_0

    invoke-virtual {v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$Program;->release()V

    .line 515
    :cond_0
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->mixProgram:Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$MixProgram;

    if-eqz v0, :cond_1

    invoke-virtual {v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$Program;->release()V

    .line 516
    :cond_1
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->transitionProgram:Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$TransitionProgram;

    invoke-virtual {v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$Program;->release()V

    .line 517
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->gaussianBlurProgram:Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$GaussianBlurProgram;

    invoke-virtual {v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$Program;->release()V

    .line 518
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->switchGaussianBlurProgram:Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$GaussianBlurProgram;

    invoke-virtual {v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$Program;->release()V

    .line 519
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->watermarkProgram:Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$Program;

    if-eqz v0, :cond_2

    invoke-virtual {v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$Program;->release()V

    .line 520
    :cond_2
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->textures:[I

    array-length v1, v0

    const/4 v2, 0x0

    invoke-static {v1, v0, v2}, Landroid/opengl/GLES20;->glDeleteTextures(I[II)V

    .line 521
    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->frameBuffers:[I

    array-length v0, p0

    invoke-static {v0, p0, v2}, Landroid/opengl/GLES20;->glDeleteFramebuffers(I[II)V

    return-void
.end method

.method public render(I[FJII)V
    .locals 3

    const/16 v0, 0xbe2

    .line 375
    invoke-static {v0}, Landroid/opengl/GLES20;->glDisable(I)V

    .line 376
    iget-boolean v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->compositionEnabled:Z

    if-eqz v0, :cond_0

    .line 377
    iget v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->historyWriteTexture:I

    .line 381
    invoke-direct {p0, p3, p4}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->getHistoryWeight(J)F

    move-result v1

    .line 377
    invoke-direct {p0, p1, p2, v0, v1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->renderTemporalBackground(I[FIF)V

    .line 383
    iget v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->historyWriteTexture:I

    invoke-direct {p0, v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->renderBackgroundBlur(I)V

    .line 384
    iget v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->historyReadTexture:I

    move-wide v1, p3

    .line 385
    iget p3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->historyWriteTexture:I

    iput p3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->historyReadTexture:I

    .line 386
    iput v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->historyWriteTexture:I

    const/4 p4, 0x1

    .line 387
    iput-boolean p4, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->historyValid:Z

    .line 388
    iput-wide v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->lastHistoryFrameNs:J

    move p4, p5

    move p5, p6

    .line 389
    invoke-direct/range {p0 .. p5}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->renderMix(I[FIII)V

    goto :goto_0

    :cond_0
    move p4, p5

    move p5, p6

    .line 391
    invoke-direct {p0, p1, p2, p4, p5}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->renderRawCameraToOutput(I[FII)V

    .line 393
    :goto_0
    invoke-direct {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->renderWatermarks()V

    return-void
.end method

.method public renderSwitch(FFFII)V
    .locals 6

    .line 460
    iget-boolean v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->switchNewFrameCaptured:Z

    if-eqz v0, :cond_0

    .line 461
    invoke-direct {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->getSwitchNewMaximumBlurRadiusPx()F

    move-result v0

    invoke-static {p1, v0}, Ljava/lang/Math;->min(FF)F

    move-result v0

    invoke-direct {p0, v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->renderNewSwitchBlur(F)V

    :cond_0
    const v0, 0x8d40

    const/4 v1, 0x0

    .line 463
    invoke-static {v0, v1}, Landroid/opengl/GLES20;->glBindFramebuffer(II)V

    .line 464
    invoke-static {v1, v1, p4, p5}, Landroid/opengl/GLES20;->glViewport(IIII)V

    const/16 p4, 0xbe2

    .line 465
    invoke-static {p4}, Landroid/opengl/GLES20;->glDisable(I)V

    .line 466
    iget-object p4, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->transitionProgram:Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$TransitionProgram;

    invoke-direct {p0, p4, v1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->bindProgram(Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$Program;I)V

    .line 467
    iget-object p4, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->transitionProgram:Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$TransitionProgram;

    iget p4, p4, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$Program;->textureHandle:I

    const/4 p5, 0x4

    invoke-direct {p0, v1, p5, p4}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->bindTexture(III)V

    .line 468
    iget-object p4, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->transitionProgram:Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$TransitionProgram;

    iget p4, p4, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$TransitionProgram;->oldMipTextureHandle:I

    const/4 v0, 0x1

    const/4 v2, 0x5

    invoke-direct {p0, v0, v2, p4}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->bindTexture(III)V

    .line 469
    iget-object p4, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->transitionProgram:Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$TransitionProgram;

    iget p4, p4, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$TransitionProgram;->newTextureHandle:I

    const/4 v0, 0x2

    const/4 v3, 0x6

    invoke-direct {p0, v0, v3, p4}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->bindTexture(III)V

    .line 470
    iget-object p4, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->transitionProgram:Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$TransitionProgram;

    iget p4, p4, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$TransitionProgram;->newBlurTextureHandle:I

    const/4 v4, 0x3

    const/4 v5, 0x7

    invoke-direct {p0, v4, v5, p4}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->bindTexture(III)V

    .line 471
    iget-boolean p4, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->compositionEnabled:Z

    if-eqz p4, :cond_1

    .line 472
    iget p4, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->switchOldBackgroundTexture:I

    iget-object v4, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->transitionProgram:Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$TransitionProgram;

    iget v4, v4, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$TransitionProgram;->oldBackgroundHandle:I

    invoke-direct {p0, p5, p4, v4}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->bindTexture(III)V

    .line 473
    iget p4, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->switchNewBackgroundTexture:I

    iget-object v4, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->transitionProgram:Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$TransitionProgram;

    iget v4, v4, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$TransitionProgram;->newBackgroundHandle:I

    invoke-direct {p0, v2, p4, v4}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->bindTexture(III)V

    .line 474
    iget-object p4, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->transitionProgram:Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$TransitionProgram;

    iget p4, p4, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$TransitionProgram;->maskTextureHandle:I

    invoke-direct {p0, v3, v0, p4}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->bindTexture(III)V

    :cond_1
    const/4 p4, 0x0

    .line 476
    invoke-static {p4, p1}, Ljava/lang/Math;->max(FF)F

    move-result p4

    const/high16 v0, 0x3f200000    # 0.625f

    mul-float/2addr p4, v0

    iget v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->outputSize:I

    int-to-float v0, v0

    div-float/2addr p4, v0

    .line 478
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->transitionProgram:Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$TransitionProgram;

    iget v0, v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$TransitionProgram;->oldBlurStepHandle:I

    invoke-static {v0, p4, p4}, Landroid/opengl/GLES20;->glUniform2f(IFF)V

    .line 483
    iget-object p4, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->transitionProgram:Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$TransitionProgram;

    iget p4, p4, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$TransitionProgram;->oldMipBiasHandle:I

    .line 485
    invoke-direct {p0, p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->getSwitchOldMipParameter(F)F

    move-result v0

    .line 483
    invoke-static {p4, v0}, Landroid/opengl/GLES20;->glUniform1f(IF)V

    .line 487
    iget-object p4, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->transitionProgram:Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$TransitionProgram;

    iget p4, p4, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$TransitionProgram;->oldBlurMixHandle:I

    const/high16 v0, 0x40000000    # 2.0f

    div-float/2addr p1, v0

    .line 489
    invoke-static {p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->clamp01(F)F

    move-result p1

    .line 487
    invoke-static {p4, p1}, Landroid/opengl/GLES20;->glUniform1f(IF)V

    .line 491
    iget-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->transitionProgram:Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$TransitionProgram;

    iget p1, p1, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$TransitionProgram;->newBlurHandle:I

    invoke-static {p1, p2}, Landroid/opengl/GLES20;->glUniform1f(IF)V

    .line 492
    iget-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->transitionProgram:Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$TransitionProgram;

    iget p1, p1, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$TransitionProgram;->mixHandle:I

    invoke-static {p1, p3}, Landroid/opengl/GLES20;->glUniform1f(IF)V

    .line 493
    invoke-static {v2, v1, p5}, Landroid/opengl/GLES20;->glDrawArrays(III)V

    .line 494
    iget-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->transitionProgram:Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$TransitionProgram;

    invoke-static {p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->unbindProgram(Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$Program;)V

    .line 495
    invoke-direct {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->renderWatermarks()V

    return-void
.end method

.method public updateInputConfiguration(Landroid/util/Size;I)V
    .locals 2

    .line 397
    iput-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->inputSize:Landroid/util/Size;

    .line 398
    iput p2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->sourceCropSize:I

    const/4 v0, 0x0

    .line 399
    invoke-static {p1, p2, v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->createCameraTextureBuffer(Landroid/util/Size;IZ)Ljava/nio/FloatBuffer;

    move-result-object v1

    iput-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->cameraTextureBuffer:Ljava/nio/FloatBuffer;

    const/4 v1, 0x1

    .line 400
    invoke-static {p1, p2, v1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->createCameraTextureBuffer(Landroid/util/Size;IZ)Ljava/nio/FloatBuffer;

    move-result-object p1

    iput-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->rotatedCameraTextureBuffer:Ljava/nio/FloatBuffer;

    .line 401
    iput-boolean v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->historyValid:Z

    const-wide/16 p1, 0x0

    .line 402
    iput-wide p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->lastHistoryFrameNs:J

    return-void
.end method
