.class final Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$FrameRatePlan;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = "FrameRatePlan"
.end annotation


# instance fields
.field final fpsRange:Landroid/util/Range;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Landroid/util/Range<",
            "Ljava/lang/Integer;",
            ">;"
        }
    .end annotation
.end field

.field final frameRate:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;

.field final outputPair:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$OutputPair;


# direct methods
.method public constructor <init>(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;Landroid/util/Range;Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$OutputPair;)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;",
            "Landroid/util/Range<",
            "Ljava/lang/Integer;",
            ">;",
            "Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$OutputPair;",
            ")V"
        }
    .end annotation

    .line 1787
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 1788
    iput-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$FrameRatePlan;->frameRate:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;

    .line 1789
    iput-object p2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$FrameRatePlan;->fpsRange:Landroid/util/Range;

    .line 1790
    iput-object p3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$FrameRatePlan;->outputPair:Lorg/telegram/utils/camera/roundvideo/RoundVideoCameraController$OutputPair;

    return-void
.end method
