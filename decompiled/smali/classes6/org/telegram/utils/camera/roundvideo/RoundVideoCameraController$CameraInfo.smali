.class final Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$CameraInfo;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = "CameraInfo"
.end annotation


# instance fields
.field final cameraResolution:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraResolution;

.field final facing:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;

.field final frameRate:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;

.field final maximumZoomRatio:F

.field final previewSize:Landroid/util/Size;

.field final recordingSize:Landroid/util/Size;

.field final torchAvailable:Z


# direct methods
.method public constructor <init>(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraResolution;Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;Landroid/util/Size;Landroid/util/Size;FZ)V
    .locals 0

    .line 70
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 71
    iput-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$CameraInfo;->facing:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;

    .line 72
    iput-object p2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$CameraInfo;->cameraResolution:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraResolution;

    .line 73
    iput-object p3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$CameraInfo;->frameRate:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;

    .line 74
    iput-object p4, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$CameraInfo;->previewSize:Landroid/util/Size;

    .line 75
    iput-object p5, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$CameraInfo;->recordingSize:Landroid/util/Size;

    .line 76
    iput p6, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$CameraInfo;->maximumZoomRatio:F

    .line 77
    iput-boolean p7, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$CameraInfo;->torchAvailable:Z

    return-void
.end method
