.class Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$Program;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x9
    name = "Program"
.end annotation


# instance fields
.field final fragmentShader:I

.field final positionHandle:I

.field final program:I

.field final textureCoordinateHandle:I

.field final textureHandle:I

.field final vertexShader:I


# direct methods
.method public constructor <init>(Ljava/lang/String;Ljava/lang/String;)V
    .locals 1

    .line 1342
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    const v0, 0x8b31

    .line 1343
    invoke-static {v0, p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->-$$Nest$smcreateShader(ILjava/lang/String;)I

    move-result p1

    iput p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$Program;->vertexShader:I

    const v0, 0x8b30

    .line 1344
    invoke-static {v0, p2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->-$$Nest$smcreateShader(ILjava/lang/String;)I

    move-result p2

    iput p2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$Program;->fragmentShader:I

    .line 1345
    invoke-static {p1, p2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer;->-$$Nest$smcreateProgram(II)I

    move-result p1

    iput p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$Program;->program:I

    const/4 p2, 0x0

    .line 1346
    iput p2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$Program;->positionHandle:I

    const/4 v0, 0x1

    .line 1347
    iput v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$Program;->textureCoordinateHandle:I

    .line 1348
    const-string v0, "sTexture"

    invoke-static {p1, v0}, Landroid/opengl/GLES20;->glGetUniformLocation(ILjava/lang/String;)I

    move-result v0

    iput v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$Program;->textureHandle:I

    .line 1349
    invoke-static {p1}, Landroid/opengl/GLES20;->glUseProgram(I)V

    .line 1350
    invoke-static {v0, p2}, Landroid/opengl/GLES20;->glUniform1i(II)V

    return-void
.end method


# virtual methods
.method public release()V
    .locals 1

    .line 1354
    iget v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$Program;->program:I

    invoke-static {v0}, Landroid/opengl/GLES20;->glDeleteProgram(I)V

    .line 1355
    iget v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$Program;->vertexShader:I

    invoke-static {v0}, Landroid/opengl/GLES20;->glDeleteShader(I)V

    .line 1356
    iget p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoOverlayRenderer$Program;->fragmentShader:I

    invoke-static {p0}, Landroid/opengl/GLES20;->glDeleteShader(I)V

    return-void
.end method
