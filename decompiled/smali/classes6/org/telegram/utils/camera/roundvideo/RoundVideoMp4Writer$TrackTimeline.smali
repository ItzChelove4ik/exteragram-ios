.class final Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer$TrackTimeline;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = "TrackTimeline"
.end annotation


# instance fields
.field final defaultDurationUs:J

.field firstSourcePresentationTimeUs:J

.field lastPresentationTimeUs:J

.field previousSourcePresentationTimeUs:J

.field sampleDurationUs:J


# direct methods
.method public constructor <init>(J)V
    .locals 2

    .line 414
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    const-wide/high16 v0, -0x8000000000000000L

    .line 409
    iput-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer$TrackTimeline;->firstSourcePresentationTimeUs:J

    .line 410
    iput-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer$TrackTimeline;->previousSourcePresentationTimeUs:J

    .line 411
    iput-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer$TrackTimeline;->lastPresentationTimeUs:J

    .line 415
    iput-wide p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer$TrackTimeline;->defaultDurationUs:J

    .line 416
    iput-wide p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer$TrackTimeline;->sampleDurationUs:J

    return-void
.end method


# virtual methods
.method public add(JJ)V
    .locals 4

    .line 425
    iget-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer$TrackTimeline;->previousSourcePresentationTimeUs:J

    const-wide/high16 v2, -0x8000000000000000L

    cmp-long v2, v0, v2

    if-eqz v2, :cond_0

    sub-long v0, p1, v0

    const-wide/16 v2, 0x0

    cmp-long v2, v0, v2

    if-lez v2, :cond_0

    const-wide/32 v2, 0xf4240

    cmp-long v2, v0, v2

    if-gez v2, :cond_0

    .line 427
    iput-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer$TrackTimeline;->sampleDurationUs:J

    .line 429
    :cond_0
    iput-wide p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer$TrackTimeline;->previousSourcePresentationTimeUs:J

    .line 430
    iget-wide p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer$TrackTimeline;->lastPresentationTimeUs:J

    invoke-static {p1, p2, p3, p4}, Ljava/lang/Math;->max(JJ)J

    move-result-wide p1

    iput-wide p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer$TrackTimeline;->lastPresentationTimeUs:J

    return-void
.end method

.method public nextPresentationTimeUs()J
    .locals 6

    .line 434
    iget-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer$TrackTimeline;->lastPresentationTimeUs:J

    const-wide/high16 v2, -0x8000000000000000L

    cmp-long v2, v0, v2

    if-nez v2, :cond_0

    const-wide/16 v0, 0x0

    return-wide v0

    :cond_0
    const-wide/16 v2, 0x1

    .line 436
    iget-wide v4, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer$TrackTimeline;->sampleDurationUs:J

    invoke-static {v2, v3, v4, v5}, Ljava/lang/Math;->max(JJ)J

    move-result-wide v2

    add-long/2addr v0, v2

    return-wide v0
.end method

.method public startSegment()V
    .locals 2

    const-wide/high16 v0, -0x8000000000000000L

    .line 420
    iput-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer$TrackTimeline;->firstSourcePresentationTimeUs:J

    .line 421
    iput-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer$TrackTimeline;->previousSourcePresentationTimeUs:J

    return-void
.end method
