.class final Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$OutputPair;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = "OutputPair"
.end annotation


# instance fields
.field final cameraResolution:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraResolution;

.field final cropSize:I

.field final preview:Landroid/util/Size;

.field final recording:Landroid/util/Size;


# direct methods
.method public constructor <init>(Landroid/util/Size;Landroid/util/Size;Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraResolution;I)V
    .locals 0

    .line 1827
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 1828
    iput-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$OutputPair;->preview:Landroid/util/Size;

    .line 1829
    iput-object p2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$OutputPair;->recording:Landroid/util/Size;

    .line 1830
    iput-object p3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$OutputPair;->cameraResolution:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraResolution;

    .line 1831
    iput p4, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$OutputPair;->cropSize:I

    return-void
.end method
