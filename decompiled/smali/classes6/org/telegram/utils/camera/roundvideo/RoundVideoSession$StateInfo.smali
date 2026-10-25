.class public final Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$StateInfo;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = "StateInfo"
.end annotation


# instance fields
.field private final cameraSwitching:Z

.field private final flashEnabled:Z

.field private final maximumDurationMs:J

.field private final previewPlaying:Z

.field private final recordedDurationMs:J

.field private final recordingStartedAtRealtimeMs:J

.field private final state:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

.field private final trimEndMs:J

.field private final trimStartMs:J

.field private final zoomProgress:F


# direct methods
.method private constructor <init>(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;JJJJJZZZF)V
    .locals 0

    .line 336
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 337
    iput-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$StateInfo;->state:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

    .line 338
    iput-wide p2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$StateInfo;->recordedDurationMs:J

    .line 339
    iput-wide p4, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$StateInfo;->recordingStartedAtRealtimeMs:J

    .line 340
    iput-wide p6, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$StateInfo;->maximumDurationMs:J

    .line 341
    iput-wide p8, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$StateInfo;->trimStartMs:J

    .line 342
    iput-wide p10, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$StateInfo;->trimEndMs:J

    .line 343
    iput-boolean p12, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$StateInfo;->previewPlaying:Z

    .line 344
    iput-boolean p13, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$StateInfo;->cameraSwitching:Z

    .line 345
    iput-boolean p14, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$StateInfo;->flashEnabled:Z

    .line 346
    iput p15, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$StateInfo;->zoomProgress:F

    return-void
.end method

.method public synthetic constructor <init>(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;JJJJJZZZFLorg/telegram/utils/camera/roundvideo/RoundVideoSession-IA;)V
    .locals 0

    .line 0
    invoke-direct/range {p0 .. p15}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$StateInfo;-><init>(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;JJJJJZZZF)V

    return-void
.end method


# virtual methods
.method public canControlCamera()Z
    .locals 2

    .line 391
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$StateInfo;->state:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

    sget-object v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;->RECORDING:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

    if-ne v0, v1, :cond_0

    iget-boolean p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$StateInfo;->cameraSwitching:Z

    if-nez p0, :cond_0

    const/4 p0, 0x1

    return p0

    :cond_0
    const/4 p0, 0x0

    return p0
.end method

.method public getMaximumDurationMs()J
    .locals 2

    .line 359
    iget-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$StateInfo;->maximumDurationMs:J

    return-wide v0
.end method

.method public getRecordedDurationMs()J
    .locals 2

    .line 353
    iget-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$StateInfo;->recordedDurationMs:J

    return-wide v0
.end method

.method public getRecordingStartedAtRealtimeMs()J
    .locals 2

    .line 356
    iget-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$StateInfo;->recordingStartedAtRealtimeMs:J

    return-wide v0
.end method

.method public getState()Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;
    .locals 0

    .line 350
    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$StateInfo;->state:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

    return-object p0
.end method

.method public isFlashEnabled()Z
    .locals 0

    .line 374
    iget-boolean p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$StateInfo;->flashEnabled:Z

    return p0
.end method
