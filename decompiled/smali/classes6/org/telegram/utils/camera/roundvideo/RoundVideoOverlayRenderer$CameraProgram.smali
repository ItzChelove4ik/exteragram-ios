.class Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$CameraProgram;
.super Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$Program;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x9
    name = "CameraProgram"
.end annotation


# instance fields
.field final textureMatrixHandle:I


# direct methods
.method public constructor <init>(Ljava/lang/String;)V
    .locals 1

    .line 1257
    const-string v0, "uniform mat4 uTextureMatrix;\nattribute vec4 aPosition;\nattribute vec4 aTextureCoord;\nvarying vec2 vTextureCoord;\nvarying vec2 vScreenTextureCoord;\nvoid main() {\n    gl_Position = aPosition;\n    vTextureCoord = (uTextureMatrix * vec4(aTextureCoord.xy, 0.0, 1.0)).xy;\n    vScreenTextureCoord = aPosition.xy * 0.5 + 0.5;\n}\n"

    invoke-direct {p0, v0, p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$Program;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    .line 1258
    iget p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$Program;->program:I

    const-string v0, "uTextureMatrix"

    invoke-static {p1, v0}, Landroid/opengl/GLES20;->glGetUniformLocation(ILjava/lang/String;)I

    move-result p1

    iput p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$CameraProgram;->textureMatrixHandle:I

    return-void
.end method
