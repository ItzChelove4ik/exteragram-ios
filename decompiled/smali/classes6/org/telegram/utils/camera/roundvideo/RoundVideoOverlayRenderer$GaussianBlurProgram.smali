.class Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$GaussianBlurProgram;
.super Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$Program;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x9
    name = "GaussianBlurProgram"
.end annotation


# instance fields
.field final offsetHandle:I


# direct methods
.method public constructor <init>()V
    .locals 2

    .line 1218
    const-string v0, "attribute vec4 aPosition;\nattribute vec4 aTextureCoord;\nvarying vec2 vTextureCoord;\nvoid main() {\n    gl_Position = aPosition;\n    vTextureCoord = aTextureCoord.xy;\n}\n"

    invoke-static {}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->-$$Nest$sfgetGAUSSIAN_BLUR_FRAGMENT_SHADER()Ljava/lang/String;

    move-result-object v1

    invoke-direct {p0, v0, v1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$Program;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    .line 1219
    iget v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$Program;->program:I

    const-string v1, "texOffset"

    invoke-static {v0, v1}, Landroid/opengl/GLES20;->glGetUniformLocation(ILjava/lang/String;)I

    move-result v0

    iput v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$GaussianBlurProgram;->offsetHandle:I

    return-void
.end method

.method public constructor <init>(Ljava/lang/String;)V
    .locals 1

    .line 1223
    const-string v0, "attribute vec4 aPosition;\nattribute vec4 aTextureCoord;\nvarying vec2 vTextureCoord;\nvoid main() {\n    gl_Position = aPosition;\n    vTextureCoord = aTextureCoord.xy;\n}\n"

    invoke-direct {p0, v0, p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$Program;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    .line 1224
    iget p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$Program;->program:I

    const-string v0, "texOffset"

    invoke-static {p1, v0}, Landroid/opengl/GLES20;->glGetUniformLocation(ILjava/lang/String;)I

    move-result p1

    iput p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$GaussianBlurProgram;->offsetHandle:I

    return-void
.end method
