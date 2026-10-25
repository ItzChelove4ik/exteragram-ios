.class final Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$CameraCandidate;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = "CameraCandidate"
.end annotation


# instance fields
.field final characteristics:Landroid/hardware/camera2/CameraCharacteristics;

.field final frameRatePlan:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$FrameRatePlan;

.field final id:Ljava/lang/String;

.field final regularPair:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$OutputPair;

.field final sizes:[Landroid/util/Size;


# direct methods
.method public constructor <init>(Ljava/lang/String;Landroid/hardware/camera2/CameraCharacteristics;[Landroid/util/Size;Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$OutputPair;Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$FrameRatePlan;)V
    .locals 0

    .line 1807
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 1808
    iput-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$CameraCandidate;->id:Ljava/lang/String;

    .line 1809
    iput-object p2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$CameraCandidate;->characteristics:Landroid/hardware/camera2/CameraCharacteristics;

    .line 1810
    iput-object p3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$CameraCandidate;->sizes:[Landroid/util/Size;

    .line 1811
    iput-object p4, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$CameraCandidate;->regularPair:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$OutputPair;

    .line 1812
    iput-object p5, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$CameraCandidate;->frameRatePlan:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$FrameRatePlan;

    return-void
.end method
