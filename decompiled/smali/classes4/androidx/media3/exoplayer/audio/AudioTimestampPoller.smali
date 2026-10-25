.class final Landroidx/media3/exoplayer/audio/AudioTimestampPoller;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Landroidx/media3/exoplayer/audio/AudioTimestampPoller$AudioTimestampWrapper;
    }
.end annotation


# instance fields
.field private final audioTimestamp:Landroidx/media3/exoplayer/audio/AudioTimestampPoller$AudioTimestampWrapper;

.field private final errorListener:Landroidx/media3/exoplayer/audio/AudioTrackPositionTracker$Listener;

.field private initialTimestampPositionFrames:J

.field private initialTimestampSystemTimeUs:J

.field private initializeSystemTimeUs:J

.field private lastTimestampSampleTimeUs:J

.field private sampleIntervalUs:J

.field private final sampleRate:I

.field private state:I


# direct methods
.method public constructor <init>(Landroid/media/AudioTrack;Landroidx/media3/exoplayer/audio/AudioTrackPositionTracker$Listener;)V
    .locals 1

    .line 128
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 129
    new-instance v0, Landroidx/media3/exoplayer/audio/AudioTimestampPoller$AudioTimestampWrapper;

    invoke-direct {v0, p1}, Landroidx/media3/exoplayer/audio/AudioTimestampPoller$AudioTimestampWrapper;-><init>(Landroid/media/AudioTrack;)V

    iput-object v0, p0, Landroidx/media3/exoplayer/audio/AudioTimestampPoller;->audioTimestamp:Landroidx/media3/exoplayer/audio/AudioTimestampPoller$AudioTimestampWrapper;

    .line 130
    invoke-virtual {p1}, Landroid/media/AudioTrack;->getSampleRate()I

    move-result p1

    iput p1, p0, Landroidx/media3/exoplayer/audio/AudioTimestampPoller;->sampleRate:I

    .line 131
    iput-object p2, p0, Landroidx/media3/exoplayer/audio/AudioTimestampPoller;->errorListener:Landroidx/media3/exoplayer/audio/AudioTrackPositionTracker$Listener;

    .line 132
    invoke-virtual {p0}, Landroidx/media3/exoplayer/audio/AudioTimestampPoller;->reset()V

    return-void
.end method

.method private checkTimestampIsPlausibleAndUpdateErrorState(JFJ)V
    .locals 11

    .line 318
    iget-object v0, p0, Landroidx/media3/exoplayer/audio/AudioTimestampPoller;->audioTimestamp:Landroidx/media3/exoplayer/audio/AudioTimestampPoller$AudioTimestampWrapper;

    invoke-virtual {v0}, Landroidx/media3/exoplayer/audio/AudioTimestampPoller$AudioTimestampWrapper;->getTimestampSystemTimeUs()J

    move-result-wide v4

    .line 319
    invoke-direct/range {p0 .. p3}, Landroidx/media3/exoplayer/audio/AudioTimestampPoller;->computeTimestampPositionUs(JF)J

    move-result-wide v0

    sub-long v2, v4, p1

    .line 320
    invoke-static {v2, v3}, Ljava/lang/Math;->abs(J)J

    move-result-wide v2

    const-wide/32 v6, 0x4c4b40

    cmp-long p3, v2, v6

    const/4 v10, 0x4

    if-lez p3, :cond_0

    .line 321
    iget-object v1, p0, Landroidx/media3/exoplayer/audio/AudioTimestampPoller;->errorListener:Landroidx/media3/exoplayer/audio/AudioTrackPositionTracker$Listener;

    iget-object p3, p0, Landroidx/media3/exoplayer/audio/AudioTimestampPoller;->audioTimestamp:Landroidx/media3/exoplayer/audio/AudioTimestampPoller$AudioTimestampWrapper;

    .line 322
    invoke-virtual {p3}, Landroidx/media3/exoplayer/audio/AudioTimestampPoller$AudioTimestampWrapper;->getTimestampPositionFrames()J

    move-result-wide v2

    move-wide v6, p1

    move-wide v8, p4

    .line 321
    invoke-interface/range {v1 .. v9}, Landroidx/media3/exoplayer/audio/AudioTrackPositionTracker$Listener;->onSystemTimeUsMismatch(JJJJ)V

    .line 326
    invoke-direct {p0, v10}, Landroidx/media3/exoplayer/audio/AudioTimestampPoller;->updateState(I)V

    return-void

    :cond_0
    sub-long/2addr v0, p4

    .line 327
    invoke-static {v0, v1}, Ljava/lang/Math;->abs(J)J

    move-result-wide v0

    cmp-long p3, v0, v6

    if-lez p3, :cond_1

    .line 329
    iget-object v1, p0, Landroidx/media3/exoplayer/audio/AudioTimestampPoller;->errorListener:Landroidx/media3/exoplayer/audio/AudioTrackPositionTracker$Listener;

    iget-object p3, p0, Landroidx/media3/exoplayer/audio/AudioTimestampPoller;->audioTimestamp:Landroidx/media3/exoplayer/audio/AudioTimestampPoller$AudioTimestampWrapper;

    .line 330
    invoke-virtual {p3}, Landroidx/media3/exoplayer/audio/AudioTimestampPoller$AudioTimestampWrapper;->getTimestampPositionFrames()J

    move-result-wide v2

    move-wide v6, p1

    move-wide v8, p4

    .line 329
    invoke-interface/range {v1 .. v9}, Landroidx/media3/exoplayer/audio/AudioTrackPositionTracker$Listener;->onPositionFramesMismatch(JJJJ)V

    .line 334
    invoke-direct {p0, v10}, Landroidx/media3/exoplayer/audio/AudioTimestampPoller;->updateState(I)V

    return-void

    .line 335
    :cond_1
    iget p1, p0, Landroidx/media3/exoplayer/audio/AudioTimestampPoller;->state:I

    if-ne p1, v10, :cond_2

    .line 336
    invoke-virtual {p0}, Landroidx/media3/exoplayer/audio/AudioTimestampPoller;->reset()V

    :cond_2
    return-void
.end method

.method private computeTimestampPositionUs(JF)J
    .locals 9

    .line 297
    iget-object v0, p0, Landroidx/media3/exoplayer/audio/AudioTimestampPoller;->audioTimestamp:Landroidx/media3/exoplayer/audio/AudioTimestampPoller$AudioTimestampWrapper;

    .line 298
    invoke-virtual {v0}, Landroidx/media3/exoplayer/audio/AudioTimestampPoller$AudioTimestampWrapper;->getTimestampPositionFrames()J

    move-result-wide v2

    iget-object v0, p0, Landroidx/media3/exoplayer/audio/AudioTimestampPoller;->audioTimestamp:Landroidx/media3/exoplayer/audio/AudioTimestampPoller$AudioTimestampWrapper;

    .line 299
    invoke-virtual {v0}, Landroidx/media3/exoplayer/audio/AudioTimestampPoller$AudioTimestampWrapper;->getTimestampSystemTimeUs()J

    move-result-wide v4

    move-object v1, p0

    move-wide v6, p1

    move v8, p3

    .line 297
    invoke-direct/range {v1 .. v8}, Landroidx/media3/exoplayer/audio/AudioTimestampPoller;->computeTimestampPositionUs(JJJF)J

    move-result-wide p0

    return-wide p0
.end method

.method private computeTimestampPositionUs(JJJF)J
    .locals 0

    .line 309
    iget p0, p0, Landroidx/media3/exoplayer/audio/AudioTimestampPoller;->sampleRate:I

    invoke-static {p1, p2, p0}, Landroidx/media3/common/util/Util;->sampleCountToDurationUs(JI)J

    move-result-wide p0

    sub-long/2addr p5, p3

    .line 312
    invoke-static {p5, p6, p7}, Landroidx/media3/common/util/Util;->getMediaDurationForPlayoutDuration(JF)J

    move-result-wide p2

    add-long/2addr p0, p2

    return-wide p0
.end method

.method private isTimestampAdvancingFromInitialTimestamp(JF)Z
    .locals 10

    .line 279
    iget-object v0, p0, Landroidx/media3/exoplayer/audio/AudioTimestampPoller;->audioTimestamp:Landroidx/media3/exoplayer/audio/AudioTimestampPoller$AudioTimestampWrapper;

    invoke-virtual {v0}, Landroidx/media3/exoplayer/audio/AudioTimestampPoller$AudioTimestampWrapper;->getTimestampPositionFrames()J

    move-result-wide v0

    iget-wide v3, p0, Landroidx/media3/exoplayer/audio/AudioTimestampPoller;->initialTimestampPositionFrames:J

    cmp-long v0, v0, v3

    const/4 v1, 0x0

    if-gtz v0, :cond_0

    return v1

    .line 283
    :cond_0
    iget-wide v5, p0, Landroidx/media3/exoplayer/audio/AudioTimestampPoller;->initialTimestampSystemTimeUs:J

    move-object v2, p0

    move-wide v7, p1

    move v9, p3

    .line 284
    invoke-direct/range {v2 .. v9}, Landroidx/media3/exoplayer/audio/AudioTimestampPoller;->computeTimestampPositionUs(JJJF)J

    move-result-wide p0

    .line 290
    invoke-direct {v2, v7, v8, v9}, Landroidx/media3/exoplayer/audio/AudioTimestampPoller;->computeTimestampPositionUs(JF)J

    move-result-wide p2

    sub-long/2addr p2, p0

    .line 292
    invoke-static {p2, p3}, Ljava/lang/Math;->abs(J)J

    move-result-wide p0

    const-wide/16 p2, 0x3e8

    cmp-long p0, p0, p2

    if-gez p0, :cond_1

    const/4 p0, 0x1

    return p0

    :cond_1
    return v1
.end method

.method private updateState(I)V
    .locals 6

    .line 252
    iput p1, p0, Landroidx/media3/exoplayer/audio/AudioTimestampPoller;->state:I

    const-wide/16 v0, 0x2710

    if-eqz p1, :cond_3

    const/4 v2, 0x1

    if-eq p1, v2, :cond_2

    const/4 v0, 0x2

    if-eq p1, v0, :cond_1

    const/4 v0, 0x3

    if-eq p1, v0, :cond_1

    const/4 v0, 0x4

    if-ne p1, v0, :cond_0

    const-wide/32 v0, 0x7a120

    .line 270
    iput-wide v0, p0, Landroidx/media3/exoplayer/audio/AudioTimestampPoller;->sampleIntervalUs:J

    return-void

    .line 273
    :cond_0
    invoke-static {}, Lorg/mvel2/asm/MethodWriter$$ExternalSyntheticBUOutline0;->m()V

    return-void

    :cond_1
    const-wide/32 v0, 0x989680

    .line 267
    iput-wide v0, p0, Landroidx/media3/exoplayer/audio/AudioTimestampPoller;->sampleIntervalUs:J

    return-void

    .line 263
    :cond_2
    iput-wide v0, p0, Landroidx/media3/exoplayer/audio/AudioTimestampPoller;->sampleIntervalUs:J

    return-void

    :cond_3
    const-wide/16 v2, 0x0

    .line 256
    iput-wide v2, p0, Landroidx/media3/exoplayer/audio/AudioTimestampPoller;->lastTimestampSampleTimeUs:J

    const-wide/16 v2, -0x1

    .line 257
    iput-wide v2, p0, Landroidx/media3/exoplayer/audio/AudioTimestampPoller;->initialTimestampPositionFrames:J

    const-wide v2, -0x7fffffffffffffffL    # -4.9E-324

    .line 258
    iput-wide v2, p0, Landroidx/media3/exoplayer/audio/AudioTimestampPoller;->initialTimestampSystemTimeUs:J

    .line 259
    invoke-static {}, Ljava/lang/System;->nanoTime()J

    move-result-wide v2

    const-wide/16 v4, 0x3e8

    div-long/2addr v2, v4

    iput-wide v2, p0, Landroidx/media3/exoplayer/audio/AudioTimestampPoller;->initializeSystemTimeUs:J

    .line 260
    iput-wide v0, p0, Landroidx/media3/exoplayer/audio/AudioTimestampPoller;->sampleIntervalUs:J

    return-void
.end method


# virtual methods
.method public expectTimestampFramePositionReset()V
    .locals 0

    .line 248
    iget-object p0, p0, Landroidx/media3/exoplayer/audio/AudioTimestampPoller;->audioTimestamp:Landroidx/media3/exoplayer/audio/AudioTimestampPoller$AudioTimestampWrapper;

    invoke-virtual {p0}, Landroidx/media3/exoplayer/audio/AudioTimestampPoller$AudioTimestampWrapper;->expectTimestampFramePositionReset()V

    return-void
.end method

.method public getTimestampPositionUs(JF)J
    .locals 0

    .line 240
    invoke-direct {p0, p1, p2, p3}, Landroidx/media3/exoplayer/audio/AudioTimestampPoller;->computeTimestampPositionUs(JF)J

    move-result-wide p0

    return-wide p0
.end method

.method public hasAdvancingTimestamp()Z
    .locals 1

    .line 215
    iget p0, p0, Landroidx/media3/exoplayer/audio/AudioTimestampPoller;->state:I

    const/4 v0, 0x2

    if-ne p0, v0, :cond_0

    const/4 p0, 0x1

    return p0

    :cond_0
    const/4 p0, 0x0

    return p0
.end method

.method public isWaitingForAdvancingTimestamp()Z
    .locals 1

    .line 224
    iget p0, p0, Landroidx/media3/exoplayer/audio/AudioTimestampPoller;->state:I

    const/4 v0, 0x1

    if-eqz p0, :cond_1

    if-ne p0, v0, :cond_0

    goto :goto_0

    :cond_0
    const/4 p0, 0x0

    return p0

    :cond_1
    :goto_0
    return v0
.end method

.method public maybePollTimestamp(JFJ)V
    .locals 4

    .line 147
    iget-wide v0, p0, Landroidx/media3/exoplayer/audio/AudioTimestampPoller;->lastTimestampSampleTimeUs:J

    sub-long v0, p1, v0

    iget-wide v2, p0, Landroidx/media3/exoplayer/audio/AudioTimestampPoller;->sampleIntervalUs:J

    cmp-long v0, v0, v2

    if-gez v0, :cond_0

    goto/16 :goto_0

    .line 150
    :cond_0
    iput-wide p1, p0, Landroidx/media3/exoplayer/audio/AudioTimestampPoller;->lastTimestampSampleTimeUs:J

    .line 151
    iget-object v0, p0, Landroidx/media3/exoplayer/audio/AudioTimestampPoller;->audioTimestamp:Landroidx/media3/exoplayer/audio/AudioTimestampPoller$AudioTimestampWrapper;

    invoke-virtual {v0}, Landroidx/media3/exoplayer/audio/AudioTimestampPoller$AudioTimestampWrapper;->maybeUpdateTimestamp()Z

    move-result v0

    if-eqz v0, :cond_1

    .line 153
    invoke-direct/range {p0 .. p5}, Landroidx/media3/exoplayer/audio/AudioTimestampPoller;->checkTimestampIsPlausibleAndUpdateErrorState(JFJ)V

    .line 156
    :cond_1
    iget p4, p0, Landroidx/media3/exoplayer/audio/AudioTimestampPoller;->state:I

    const/4 p5, 0x1

    const/4 v1, 0x3

    if-eqz p4, :cond_9

    const/4 v2, 0x2

    if-eq p4, p5, :cond_5

    if-eq p4, v2, :cond_4

    if-eq p4, v1, :cond_3

    const/4 p0, 0x4

    if-ne p4, p0, :cond_2

    goto :goto_0

    .line 206
    :cond_2
    invoke-static {}, Lorg/mvel2/asm/MethodWriter$$ExternalSyntheticBUOutline0;->m()V

    return-void

    :cond_3
    if-eqz v0, :cond_b

    .line 199
    invoke-virtual {p0}, Landroidx/media3/exoplayer/audio/AudioTimestampPoller;->reset()V

    return-void

    :cond_4
    if-nez v0, :cond_b

    .line 193
    invoke-virtual {p0}, Landroidx/media3/exoplayer/audio/AudioTimestampPoller;->reset()V

    return-void

    :cond_5
    if-eqz v0, :cond_8

    .line 175
    invoke-direct {p0, p1, p2, p3}, Landroidx/media3/exoplayer/audio/AudioTimestampPoller;->isTimestampAdvancingFromInitialTimestamp(JF)Z

    move-result p3

    if-eqz p3, :cond_6

    .line 176
    invoke-direct {p0, v2}, Landroidx/media3/exoplayer/audio/AudioTimestampPoller;->updateState(I)V

    return-void

    .line 177
    :cond_6
    iget-wide p3, p0, Landroidx/media3/exoplayer/audio/AudioTimestampPoller;->initializeSystemTimeUs:J

    sub-long/2addr p1, p3

    const-wide/32 p3, 0x1e8480

    cmp-long p1, p1, p3

    if-lez p1, :cond_7

    .line 180
    invoke-direct {p0, v1}, Landroidx/media3/exoplayer/audio/AudioTimestampPoller;->updateState(I)V

    return-void

    .line 183
    :cond_7
    iget-object p1, p0, Landroidx/media3/exoplayer/audio/AudioTimestampPoller;->audioTimestamp:Landroidx/media3/exoplayer/audio/AudioTimestampPoller$AudioTimestampWrapper;

    invoke-virtual {p1}, Landroidx/media3/exoplayer/audio/AudioTimestampPoller$AudioTimestampWrapper;->getTimestampPositionFrames()J

    move-result-wide p1

    iput-wide p1, p0, Landroidx/media3/exoplayer/audio/AudioTimestampPoller;->initialTimestampPositionFrames:J

    .line 184
    iget-object p1, p0, Landroidx/media3/exoplayer/audio/AudioTimestampPoller;->audioTimestamp:Landroidx/media3/exoplayer/audio/AudioTimestampPoller$AudioTimestampWrapper;

    invoke-virtual {p1}, Landroidx/media3/exoplayer/audio/AudioTimestampPoller$AudioTimestampWrapper;->getTimestampSystemTimeUs()J

    move-result-wide p1

    iput-wide p1, p0, Landroidx/media3/exoplayer/audio/AudioTimestampPoller;->initialTimestampSystemTimeUs:J

    return-void

    .line 187
    :cond_8
    invoke-virtual {p0}, Landroidx/media3/exoplayer/audio/AudioTimestampPoller;->reset()V

    return-void

    :cond_9
    if-eqz v0, :cond_a

    .line 159
    iget-object p1, p0, Landroidx/media3/exoplayer/audio/AudioTimestampPoller;->audioTimestamp:Landroidx/media3/exoplayer/audio/AudioTimestampPoller$AudioTimestampWrapper;

    invoke-virtual {p1}, Landroidx/media3/exoplayer/audio/AudioTimestampPoller$AudioTimestampWrapper;->getTimestampSystemTimeUs()J

    move-result-wide p1

    iget-wide p3, p0, Landroidx/media3/exoplayer/audio/AudioTimestampPoller;->initializeSystemTimeUs:J

    cmp-long p1, p1, p3

    if-ltz p1, :cond_b

    .line 161
    iget-object p1, p0, Landroidx/media3/exoplayer/audio/AudioTimestampPoller;->audioTimestamp:Landroidx/media3/exoplayer/audio/AudioTimestampPoller$AudioTimestampWrapper;

    invoke-virtual {p1}, Landroidx/media3/exoplayer/audio/AudioTimestampPoller$AudioTimestampWrapper;->getTimestampPositionFrames()J

    move-result-wide p1

    iput-wide p1, p0, Landroidx/media3/exoplayer/audio/AudioTimestampPoller;->initialTimestampPositionFrames:J

    .line 162
    iget-object p1, p0, Landroidx/media3/exoplayer/audio/AudioTimestampPoller;->audioTimestamp:Landroidx/media3/exoplayer/audio/AudioTimestampPoller$AudioTimestampWrapper;

    invoke-virtual {p1}, Landroidx/media3/exoplayer/audio/AudioTimestampPoller$AudioTimestampWrapper;->getTimestampSystemTimeUs()J

    move-result-wide p1

    iput-wide p1, p0, Landroidx/media3/exoplayer/audio/AudioTimestampPoller;->initialTimestampSystemTimeUs:J

    .line 163
    invoke-direct {p0, p5}, Landroidx/media3/exoplayer/audio/AudioTimestampPoller;->updateState(I)V

    return-void

    .line 165
    :cond_a
    iget-wide p3, p0, Landroidx/media3/exoplayer/audio/AudioTimestampPoller;->initializeSystemTimeUs:J

    sub-long/2addr p1, p3

    const-wide/32 p3, 0x7a120

    cmp-long p1, p1, p3

    if-lez p1, :cond_b

    .line 170
    invoke-direct {p0, v1}, Landroidx/media3/exoplayer/audio/AudioTimestampPoller;->updateState(I)V

    :cond_b
    :goto_0
    return-void
.end method

.method public reset()V
    .locals 1

    const/4 v0, 0x0

    .line 229
    invoke-direct {p0, v0}, Landroidx/media3/exoplayer/audio/AudioTimestampPoller;->updateState(I)V

    return-void
.end method
