.class public final Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraCapabilities;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = "CameraCapabilities"
.end annotation


# instance fields
.field private final activeFacing:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;

.field private final activeFrameRate:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;

.field private final activeResolution:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraResolution;

.field private final flashType:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FlashType;

.field private final maximumZoomRatio:F

.field private final requestedFacing:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;

.field private final requestedFrameRate:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;

.field private final requestedResolution:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraResolution;


# direct methods
.method private constructor <init>(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraResolution;Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraResolution;Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FlashType;F)V
    .locals 0

    .line 417
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 418
    iput-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraCapabilities;->requestedFacing:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;

    .line 419
    iput-object p2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraCapabilities;->activeFacing:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;

    .line 420
    iput-object p3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraCapabilities;->requestedResolution:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraResolution;

    .line 421
    iput-object p4, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraCapabilities;->activeResolution:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraResolution;

    .line 422
    iput-object p5, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraCapabilities;->requestedFrameRate:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;

    .line 423
    iput-object p6, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraCapabilities;->activeFrameRate:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;

    .line 424
    iput-object p7, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraCapabilities;->flashType:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FlashType;

    .line 425
    iput p8, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraCapabilities;->maximumZoomRatio:F

    return-void
.end method

.method public synthetic constructor <init>(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraResolution;Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraResolution;Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FlashType;FLorg/telegram/utils/camera/roundvideo/RoundVideoSession-IA;)V
    .locals 0

    .line 0
    invoke-direct/range {p0 .. p8}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraCapabilities;-><init>(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraResolution;Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraResolution;Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FrameRate;Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FlashType;F)V

    return-void
.end method


# virtual methods
.method public getActiveFacing()Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;
    .locals 0

    .line 432
    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraCapabilities;->activeFacing:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;

    return-object p0
.end method

.method public getFlashType()Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FlashType;
    .locals 0

    .line 447
    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraCapabilities;->flashType:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$FlashType;

    return-object p0
.end method

.method public getMaximumZoomRatio()F
    .locals 0

    .line 450
    iget p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraCapabilities;->maximumZoomRatio:F

    return p0
.end method

.method public getRequestedFacing()Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;
    .locals 0

    .line 429
    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraCapabilities;->requestedFacing:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$CameraFacing;

    return-object p0
.end method
