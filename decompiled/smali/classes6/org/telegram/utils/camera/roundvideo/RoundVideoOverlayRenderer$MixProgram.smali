.class Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$MixProgram;
.super Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$CameraProgram;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x9
    name = "MixProgram"
.end annotation


# instance fields
.field final blurredTextureHandle:I

.field final maskTextureHandle:I


# direct methods
.method public constructor <init>()V
    .locals 3

    .line 1284
    const-string v0, "#extension GL_OES_EGL_image_external : require\nprecision mediump float;\nvarying vec2 vTextureCoord;\nvarying vec2 vScreenTextureCoord;\nuniform samplerExternalOES sTexture;\nuniform sampler2D bTexture;\nuniform sampler2D mTexture;\nvoid main() {\n    vec3 sharp = texture2D(sTexture, vTextureCoord).rgb;\n    vec3 blurred = texture2D(bTexture, vScreenTextureCoord).rgb * 0.25;\n    float mask = texture2D(mTexture, vScreenTextureCoord).a;\n    gl_FragColor = vec4(mix(blurred, sharp, mask), 1.0);\n}\n"

    invoke-direct {p0, v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$CameraProgram;-><init>(Ljava/lang/String;)V

    .line 1285
    iget v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$Program;->program:I

    const-string v1, "bTexture"

    invoke-static {v0, v1}, Landroid/opengl/GLES20;->glGetUniformLocation(ILjava/lang/String;)I

    move-result v0

    iput v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$MixProgram;->blurredTextureHandle:I

    .line 1286
    iget v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$Program;->program:I

    const-string v2, "mTexture"

    invoke-static {v1, v2}, Landroid/opengl/GLES20;->glGetUniformLocation(ILjava/lang/String;)I

    move-result v1

    iput v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$MixProgram;->maskTextureHandle:I

    .line 1287
    iget p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$Program;->program:I

    invoke-static {p0}, Landroid/opengl/GLES20;->glUseProgram(I)V

    const/4 p0, 0x1

    .line 1288
    invoke-static {v0, p0}, Landroid/opengl/GLES20;->glUniform1i(II)V

    const/4 p0, 0x2

    .line 1289
    invoke-static {v1, p0}, Landroid/opengl/GLES20;->glUniform1i(II)V

    return-void
.end method
