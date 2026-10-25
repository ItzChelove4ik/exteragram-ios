.class Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$TransitionProgram;
.super Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$Program;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x9
    name = "TransitionProgram"
.end annotation


# instance fields
.field final maskTextureHandle:I

.field final mixHandle:I

.field final newBackgroundHandle:I

.field final newBlurHandle:I

.field final newBlurTextureHandle:I

.field final newTextureHandle:I

.field final oldBackgroundHandle:I

.field final oldBlurMixHandle:I

.field final oldBlurStepHandle:I

.field final oldMipBiasHandle:I

.field final oldMipTextureHandle:I


# direct methods
.method public constructor <init>(ZZ)V
    .locals 7

    .line 1307
    const-string v0, "attribute vec4 aPosition;\nattribute vec4 aTextureCoord;\nvarying vec2 vTextureCoord;\nvoid main() {\n    gl_Position = aPosition;\n    vTextureCoord = aTextureCoord.xy;\n}\n"

    .line 1309
    invoke-static {p1, p2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->-$$Nest$smcreateTransitionFragmentShader(ZZ)Ljava/lang/String;

    move-result-object p2

    .line 1307
    invoke-direct {p0, v0, p2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$Program;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    .line 1311
    iget p2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$Program;->program:I

    const-string v0, "omTexture"

    invoke-static {p2, v0}, Landroid/opengl/GLES20;->glGetUniformLocation(ILjava/lang/String;)I

    move-result p2

    iput p2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$TransitionProgram;->oldMipTextureHandle:I

    .line 1312
    iget v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$Program;->program:I

    const-string v1, "nTexture"

    invoke-static {v0, v1}, Landroid/opengl/GLES20;->glGetUniformLocation(ILjava/lang/String;)I

    move-result v0

    iput v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$TransitionProgram;->newTextureHandle:I

    .line 1313
    iget v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$Program;->program:I

    const-string v2, "nbTexture"

    invoke-static {v1, v2}, Landroid/opengl/GLES20;->glGetUniformLocation(ILjava/lang/String;)I

    move-result v1

    iput v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$TransitionProgram;->newBlurTextureHandle:I

    .line 1314
    iget v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$Program;->program:I

    const-string v3, "oldBackground"

    invoke-static {v2, v3}, Landroid/opengl/GLES20;->glGetUniformLocation(ILjava/lang/String;)I

    move-result v2

    iput v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$TransitionProgram;->oldBackgroundHandle:I

    .line 1315
    iget v3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$Program;->program:I

    const-string v4, "newBackground"

    invoke-static {v3, v4}, Landroid/opengl/GLES20;->glGetUniformLocation(ILjava/lang/String;)I

    move-result v3

    iput v3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$TransitionProgram;->newBackgroundHandle:I

    .line 1316
    iget v4, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$Program;->program:I

    const-string v5, "maskTexture"

    invoke-static {v4, v5}, Landroid/opengl/GLES20;->glGetUniformLocation(ILjava/lang/String;)I

    move-result v4

    iput v4, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$TransitionProgram;->maskTextureHandle:I

    .line 1317
    iget v5, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$Program;->program:I

    const-string v6, "oldBlurStep"

    invoke-static {v5, v6}, Landroid/opengl/GLES20;->glGetUniformLocation(ILjava/lang/String;)I

    move-result v5

    iput v5, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$TransitionProgram;->oldBlurStepHandle:I

    .line 1318
    iget v5, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$Program;->program:I

    const-string v6, "oldMipBias"

    invoke-static {v5, v6}, Landroid/opengl/GLES20;->glGetUniformLocation(ILjava/lang/String;)I

    move-result v5

    iput v5, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$TransitionProgram;->oldMipBiasHandle:I

    .line 1319
    iget v5, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$Program;->program:I

    const-string v6, "oldBlurMix"

    invoke-static {v5, v6}, Landroid/opengl/GLES20;->glGetUniformLocation(ILjava/lang/String;)I

    move-result v5

    iput v5, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$TransitionProgram;->oldBlurMixHandle:I

    .line 1320
    iget v5, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$Program;->program:I

    const-string v6, "newBlur"

    invoke-static {v5, v6}, Landroid/opengl/GLES20;->glGetUniformLocation(ILjava/lang/String;)I

    move-result v5

    iput v5, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$TransitionProgram;->newBlurHandle:I

    .line 1321
    iget v5, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$Program;->program:I

    const-string v6, "mixValue"

    invoke-static {v5, v6}, Landroid/opengl/GLES20;->glGetUniformLocation(ILjava/lang/String;)I

    move-result v5

    iput v5, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$TransitionProgram;->mixHandle:I

    .line 1322
    iget p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$Program;->program:I

    invoke-static {p0}, Landroid/opengl/GLES20;->glUseProgram(I)V

    const/4 p0, 0x1

    .line 1323
    invoke-static {p2, p0}, Landroid/opengl/GLES20;->glUniform1i(II)V

    const/4 p0, 0x2

    .line 1324
    invoke-static {v0, p0}, Landroid/opengl/GLES20;->glUniform1i(II)V

    const/4 p0, 0x3

    .line 1325
    invoke-static {v1, p0}, Landroid/opengl/GLES20;->glUniform1i(II)V

    if-eqz p1, :cond_0

    const/4 p0, 0x4

    .line 1327
    invoke-static {v2, p0}, Landroid/opengl/GLES20;->glUniform1i(II)V

    const/4 p0, 0x5

    .line 1328
    invoke-static {v3, p0}, Landroid/opengl/GLES20;->glUniform1i(II)V

    const/4 p0, 0x6

    .line 1329
    invoke-static {v4, p0}, Landroid/opengl/GLES20;->glUniform1i(II)V

    :cond_0
    return-void
.end method
