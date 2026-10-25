.class final Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer$TrackTimeline;,
        Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer$Listener;,
        Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer$PendingSample;
    }
.end annotation


# instance fields
.field private audioFormat:Landroid/media/MediaFormat;

.field private audioSamples:J

.field private final audioTimeline:Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer$TrackTimeline;

.field private audioTrack:I

.field private availableSize:J

.field private builder:Lorg/telegram/messenger/video/MP4Builder;

.field private final convertedVideoInfo:Landroid/media/MediaCodec$BufferInfo;

.field private final diagnostics:Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;

.field private final file:Ljava/io/File;

.field private finished:Z

.field private final includeAudio:Z

.field private final listener:Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer$Listener;

.field private liveSegmentBaseUs:J

.field private liveSegmentOffsetUs:J

.field private final outputSize:I

.field private final pendingSamples:Ljava/util/ArrayList;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/ArrayList<",
            "Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer$PendingSample;",
            ">;"
        }
    .end annotation
.end field

.field private suppressAvailableNotifications:Z

.field private videoConversionBuffer:Ljava/nio/ByteBuffer;

.field private videoFormat:Landroid/media/MediaFormat;

.field private videoSamples:J

.field private final videoTimeline:Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer$TrackTimeline;

.field private videoTrack:I


# direct methods
.method public constructor <init>(Ljava/io/File;IZLorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer$Listener;)V
    .locals 3

    .line 53
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 28
    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    iput-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->pendingSamples:Ljava/util/ArrayList;

    .line 29
    new-instance v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer$TrackTimeline;

    const-wide/32 v1, 0x8235

    invoke-direct {v0, v1, v2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer$TrackTimeline;-><init>(J)V

    iput-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->videoTimeline:Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer$TrackTimeline;

    .line 30
    new-instance v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer$TrackTimeline;

    const-wide/16 v1, 0x5355

    invoke-direct {v0, v1, v2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer$TrackTimeline;-><init>(J)V

    iput-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->audioTimeline:Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer$TrackTimeline;

    const/4 v0, -0x1

    .line 35
    iput v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->videoTrack:I

    .line 36
    iput v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->audioTrack:I

    const-wide/high16 v0, -0x8000000000000000L

    .line 40
    iput-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->liveSegmentOffsetUs:J

    .line 43
    new-instance v0, Landroid/media/MediaCodec$BufferInfo;

    invoke-direct {v0}, Landroid/media/MediaCodec$BufferInfo;-><init>()V

    iput-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->convertedVideoInfo:Landroid/media/MediaCodec$BufferInfo;

    .line 54
    iput-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->file:Ljava/io/File;

    .line 55
    iput p2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->outputSize:I

    .line 56
    iput-boolean p3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->includeAudio:Z

    .line 57
    iput-object p4, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->diagnostics:Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;

    .line 58
    iput-object p5, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->listener:Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer$Listener;

    return-void
.end method

.method private static asIOException(Ljava/lang/String;Ljava/lang/Exception;)Ljava/io/IOException;
    .locals 1

    .line 390
    instance-of v0, p1, Ljava/io/IOException;

    if-eqz v0, :cond_0

    .line 391
    check-cast p1, Ljava/io/IOException;

    return-object p1

    .line 392
    :cond_0
    new-instance v0, Ljava/io/IOException;

    invoke-direct {v0, p0, p1}, Ljava/io/IOException;-><init>(Ljava/lang/String;Ljava/lang/Throwable;)V

    return-object v0
.end method

.method private static codecSpecificDataOffset(Ljava/nio/ByteBuffer;)I
    .locals 6

    .line 372
    invoke-virtual {p0}, Ljava/nio/Buffer;->limit()I

    move-result v0

    const/4 v1, 0x2

    const/4 v2, 0x3

    const/4 v3, 0x0

    const/4 v4, 0x1

    const/4 v5, 0x4

    if-lt v0, v5, :cond_0

    .line 373
    invoke-virtual {p0, v3}, Ljava/nio/ByteBuffer;->get(I)B

    move-result v0

    if-nez v0, :cond_0

    .line 374
    invoke-virtual {p0, v4}, Ljava/nio/ByteBuffer;->get(I)B

    move-result v0

    if-nez v0, :cond_0

    .line 375
    invoke-virtual {p0, v1}, Ljava/nio/ByteBuffer;->get(I)B

    move-result v0

    if-nez v0, :cond_0

    .line 376
    invoke-virtual {p0, v2}, Ljava/nio/ByteBuffer;->get(I)B

    move-result v0

    if-ne v0, v4, :cond_0

    return v5

    .line 379
    :cond_0
    invoke-virtual {p0}, Ljava/nio/Buffer;->limit()I

    move-result v0

    if-lt v0, v2, :cond_1

    .line 380
    invoke-virtual {p0, v3}, Ljava/nio/ByteBuffer;->get(I)B

    move-result v0

    if-nez v0, :cond_1

    .line 381
    invoke-virtual {p0, v4}, Ljava/nio/ByteBuffer;->get(I)B

    move-result v0

    if-nez v0, :cond_1

    .line 382
    invoke-virtual {p0, v1}, Ljava/nio/ByteBuffer;->get(I)B

    move-result p0

    if-ne p0, v4, :cond_1

    return v2

    :cond_1
    return v3
.end method

.method private copyInfo(ZLandroid/media/MediaCodec$BufferInfo;J)Landroid/media/MediaCodec$BufferInfo;
    .locals 6

    if-eqz p1, :cond_0

    .line 224
    iget-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->videoTimeline:Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer$TrackTimeline;

    goto :goto_0

    :cond_0
    iget-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->audioTimeline:Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer$TrackTimeline;

    :goto_0
    const-wide/16 v0, 0x0

    cmp-long v2, p3, v0

    if-gez v2, :cond_1

    .line 226
    iget-wide v2, p2, Landroid/media/MediaCodec$BufferInfo;->presentationTimeUs:J

    add-long/2addr p3, v2

    invoke-static {v0, v1, p3, p4}, Ljava/lang/Math;->max(JJ)J

    move-result-wide p3

    :goto_1
    move-wide v3, p3

    goto :goto_2

    .line 228
    :cond_1
    iget-wide v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->liveSegmentOffsetUs:J

    cmp-long v2, v2, p3

    if-eqz v2, :cond_2

    .line 229
    iput-wide p3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->liveSegmentOffsetUs:J

    .line 230
    iget-object v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->videoTimeline:Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer$TrackTimeline;

    .line 232
    invoke-virtual {v2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer$TrackTimeline;->nextPresentationTimeUs()J

    move-result-wide v2

    iget-object v4, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->audioTimeline:Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer$TrackTimeline;

    .line 233
    invoke-virtual {v4}, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer$TrackTimeline;->nextPresentationTimeUs()J

    move-result-wide v4

    .line 232
    invoke-static {v2, v3, v4, v5}, Ljava/lang/Math;->max(JJ)J

    move-result-wide v2

    .line 230
    invoke-static {p3, p4, v2, v3}, Ljava/lang/Math;->max(JJ)J

    move-result-wide p3

    iput-wide p3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->liveSegmentBaseUs:J

    .line 235
    iget-object p3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->videoTimeline:Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer$TrackTimeline;

    invoke-virtual {p3}, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer$TrackTimeline;->startSegment()V

    .line 236
    iget-object p3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->audioTimeline:Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer$TrackTimeline;

    invoke-virtual {p3}, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer$TrackTimeline;->startSegment()V

    .line 238
    :cond_2
    iget-wide p3, p1, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer$TrackTimeline;->firstSourcePresentationTimeUs:J

    const-wide/high16 v2, -0x8000000000000000L

    cmp-long p3, p3, v2

    if-nez p3, :cond_3

    .line 239
    iget-wide p3, p2, Landroid/media/MediaCodec$BufferInfo;->presentationTimeUs:J

    iput-wide p3, p1, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer$TrackTimeline;->firstSourcePresentationTimeUs:J

    .line 241
    :cond_3
    iget-wide p3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->liveSegmentBaseUs:J

    iget-wide v2, p2, Landroid/media/MediaCodec$BufferInfo;->presentationTimeUs:J

    iget-wide v4, p1, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer$TrackTimeline;->firstSourcePresentationTimeUs:J

    sub-long/2addr v2, v4

    .line 242
    invoke-static {v0, v1, v2, v3}, Ljava/lang/Math;->max(JJ)J

    move-result-wide v0

    add-long/2addr p3, v0

    goto :goto_1

    .line 245
    :goto_2
    iget-wide p3, p2, Landroid/media/MediaCodec$BufferInfo;->presentationTimeUs:J

    invoke-virtual {p1, p3, p4, v3, v4}, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer$TrackTimeline;->add(JJ)V

    .line 246
    new-instance v0, Landroid/media/MediaCodec$BufferInfo;

    invoke-direct {v0}, Landroid/media/MediaCodec$BufferInfo;-><init>()V

    .line 247
    iget v1, p2, Landroid/media/MediaCodec$BufferInfo;->offset:I

    iget v2, p2, Landroid/media/MediaCodec$BufferInfo;->size:I

    iget v5, p2, Landroid/media/MediaCodec$BufferInfo;->flags:I

    invoke-virtual/range {v0 .. v5}, Landroid/media/MediaCodec$BufferInfo;->set(IIJI)V

    return-object v0
.end method

.method private static elapsedMs(J)J
    .locals 2

    .line 214
    invoke-static {}, Ljava/lang/System;->nanoTime()J

    move-result-wide v0

    sub-long/2addr v0, p0

    const-wide/32 p0, 0xf4240

    div-long/2addr v0, p0

    return-wide v0
.end method

.method private ensureVideoConversionCapacity(I)V
    .locals 1

    .line 327
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->videoConversionBuffer:Ljava/nio/ByteBuffer;

    if-eqz v0, :cond_0

    invoke-virtual {v0}, Ljava/nio/Buffer;->capacity()I

    move-result v0

    if-lt v0, p1, :cond_0

    return-void

    .line 328
    :cond_0
    invoke-static {p1}, Ljava/nio/ByteBuffer;->allocateDirect(I)Ljava/nio/ByteBuffer;

    move-result-object p1

    iput-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->videoConversionBuffer:Ljava/nio/ByteBuffer;

    return-void
.end method

.method private static findStartCode(Ljava/nio/ByteBuffer;II)I
    .locals 3

    :goto_0
    add-int/lit8 v0, p1, 0x2

    if-ge v0, p2, :cond_3

    .line 333
    invoke-virtual {p0, p1}, Ljava/nio/ByteBuffer;->get(I)B

    move-result v1

    if-nez v1, :cond_2

    add-int/lit8 v1, p1, 0x1

    invoke-virtual {p0, v1}, Ljava/nio/ByteBuffer;->get(I)B

    move-result v1

    if-eqz v1, :cond_0

    goto :goto_2

    .line 334
    :cond_0
    invoke-virtual {p0, v0}, Ljava/nio/ByteBuffer;->get(I)B

    move-result v1

    const/4 v2, 0x1

    if-ne v1, v2, :cond_1

    goto :goto_1

    :cond_1
    add-int/lit8 v1, p1, 0x3

    if-ge v1, p2, :cond_2

    .line 335
    invoke-virtual {p0, v0}, Ljava/nio/ByteBuffer;->get(I)B

    move-result v0

    if-nez v0, :cond_2

    invoke-virtual {p0, v1}, Ljava/nio/ByteBuffer;->get(I)B

    move-result v0

    if-ne v0, v2, :cond_2

    :goto_1
    return p1

    :cond_2
    :goto_2
    add-int/lit8 p1, p1, 0x1

    goto :goto_0

    :cond_3
    const/4 p0, -0x1

    return p0
.end method

.method private initializeIfReady()V
    .locals 5

    .line 147
    const-string v0, "MP4 initialized: file="

    iget-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->videoFormat:Landroid/media/MediaFormat;

    if-eqz v1, :cond_3

    iget-boolean v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->includeAudio:Z

    if-eqz v1, :cond_0

    iget-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->audioFormat:Landroid/media/MediaFormat;

    if-nez v1, :cond_0

    goto/16 :goto_1

    .line 148
    :cond_0
    new-instance v1, Lorg/telegram/messenger/video/Mp4Movie;

    invoke-direct {v1}, Lorg/telegram/messenger/video/Mp4Movie;-><init>()V

    .line 149
    iget-object v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->file:Ljava/io/File;

    invoke-virtual {v1, v2}, Lorg/telegram/messenger/video/Mp4Movie;->setCacheFile(Ljava/io/File;)V

    .line 150
    iget v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->outputSize:I

    invoke-virtual {v1, v2, v2}, Lorg/telegram/messenger/video/Mp4Movie;->setSize(II)V

    .line 152
    :try_start_0
    new-instance v2, Lorg/telegram/messenger/video/MP4Builder;

    invoke-direct {v2}, Lorg/telegram/messenger/video/MP4Builder;-><init>()V

    const/4 v3, 0x0

    const/4 v4, 0x1

    invoke-virtual {v2, v1, v4, v3}, Lorg/telegram/messenger/video/MP4Builder;->createMovie(Lorg/telegram/messenger/video/Mp4Movie;ZZ)Lorg/telegram/messenger/video/MP4Builder;

    move-result-object v1

    iput-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->builder:Lorg/telegram/messenger/video/MP4Builder;

    .line 153
    iget-object v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->videoFormat:Landroid/media/MediaFormat;

    invoke-virtual {v1, v2, v3}, Lorg/telegram/messenger/video/MP4Builder;->addTrack(Landroid/media/MediaFormat;Z)I

    move-result v1

    iput v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->videoTrack:I

    .line 154
    iget-boolean v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->includeAudio:Z

    if-eqz v1, :cond_1

    iget-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->builder:Lorg/telegram/messenger/video/MP4Builder;

    iget-object v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->audioFormat:Landroid/media/MediaFormat;

    invoke-virtual {v1, v2, v4}, Lorg/telegram/messenger/video/MP4Builder;->addTrack(Landroid/media/MediaFormat;Z)I

    move-result v1

    iput v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->audioTrack:I

    .line 155
    :cond_1
    iget-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->diagnostics:Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2, v0}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->file:Ljava/io/File;

    invoke-virtual {v0}, Ljava/io/File;->getName()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v2, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v0, ", output="

    invoke-virtual {v2, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->outputSize:I

    invoke-virtual {v2, v0}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string v0, "x"

    invoke-virtual {v2, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->outputSize:I

    invoke-virtual {v2, v0}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string v0, ", includeAudio="

    invoke-virtual {v2, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-boolean v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->includeAudio:Z

    invoke-virtual {v2, v0}, Ljava/lang/StringBuilder;->append(Z)Ljava/lang/StringBuilder;

    const-string v0, ", pendingSamples="

    invoke-virtual {v2, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->pendingSamples:Ljava/util/ArrayList;

    .line 158
    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v0

    invoke-virtual {v2, v0}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    .line 155
    invoke-virtual {v1, v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;->log(Ljava/lang/String;)V

    .line 159
    :goto_0
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->pendingSamples:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v0
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    .line 163
    iget-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->pendingSamples:Ljava/util/ArrayList;

    if-ge v3, v0, :cond_2

    .line 160
    :try_start_1
    invoke-virtual {v1, v3}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer$PendingSample;

    .line 161
    iget-boolean v1, v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer$PendingSample;->video:Z

    iget-object v2, v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer$PendingSample;->data:Ljava/nio/ByteBuffer;

    iget-object v0, v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer$PendingSample;->info:Landroid/media/MediaCodec$BufferInfo;

    invoke-direct {p0, v1, v2, v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->writeSampleInternal(ZLjava/nio/ByteBuffer;Landroid/media/MediaCodec$BufferInfo;)V

    add-int/lit8 v3, v3, 0x1

    goto :goto_0

    .line 163
    :cond_2
    invoke-virtual {v1}, Ljava/util/ArrayList;->clear()V
    :try_end_1
    .catch Ljava/lang/Exception; {:try_start_1 .. :try_end_1} :catch_0

    return-void

    :catch_0
    move-exception p0

    .line 165
    const-string v0, "Unable to initialize MP4"

    invoke-static {v0, p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->asIOException(Ljava/lang/String;Ljava/lang/Exception;)Ljava/io/IOException;

    move-result-object p0

    throw p0

    :cond_3
    :goto_1
    return-void
.end method

.method private normalizeVideoSample(Ljava/nio/ByteBuffer;Landroid/media/MediaCodec$BufferInfo;)I
    .locals 8

    .line 264
    iget v0, p2, Landroid/media/MediaCodec$BufferInfo;->offset:I

    iget v1, p2, Landroid/media/MediaCodec$BufferInfo;->size:I

    add-int/2addr v1, v0

    .line 265
    invoke-static {p1, v0, v1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->findStartCode(Ljava/nio/ByteBuffer;II)I

    move-result v0

    .line 266
    iget v2, p2, Landroid/media/MediaCodec$BufferInfo;->offset:I

    const/4 v3, 0x0

    if-eq v0, v2, :cond_0

    return v3

    .line 269
    :cond_0
    invoke-static {p1, v0, v1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->startCodeLength(Ljava/nio/ByteBuffer;II)I

    move-result v2

    add-int v4, v0, v2

    .line 270
    invoke-static {p1, v4, v1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->findStartCode(Ljava/nio/ByteBuffer;II)I

    move-result v4

    const/4 v5, 0x4

    if-ne v2, v5, :cond_1

    if-gez v4, :cond_1

    const/4 p0, 0x1

    return p0

    :cond_1
    move v2, v0

    move v4, v3

    move v5, v4

    :goto_0
    if-ltz v2, :cond_4

    .line 279
    invoke-static {p1, v2, v1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->startCodeLength(Ljava/nio/ByteBuffer;II)I

    move-result v6

    add-int/2addr v2, v6

    .line 281
    invoke-static {p1, v2, v1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->findStartCode(Ljava/nio/ByteBuffer;II)I

    move-result v6

    if-gez v6, :cond_2

    move v7, v1

    goto :goto_1

    :cond_2
    move v7, v6

    :goto_1
    if-le v7, v2, :cond_3

    add-int/lit8 v4, v4, 0x1

    add-int/lit8 v7, v7, 0x4

    sub-int/2addr v7, v2

    add-int/2addr v5, v7

    :cond_3
    move v2, v6

    goto :goto_0

    :cond_4
    if-nez v4, :cond_5

    return v3

    .line 293
    :cond_5
    invoke-direct {p0, v5}, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->ensureVideoConversionCapacity(I)V

    .line 294
    iget-object v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->videoConversionBuffer:Ljava/nio/ByteBuffer;

    invoke-virtual {v2}, Ljava/nio/ByteBuffer;->clear()Ljava/nio/Buffer;

    .line 296
    invoke-virtual {p1}, Ljava/nio/Buffer;->position()I

    move-result v2

    .line 297
    invoke-virtual {p1}, Ljava/nio/Buffer;->limit()I

    move-result v3

    :goto_2
    if-ltz v0, :cond_8

    .line 300
    :try_start_0
    invoke-static {p1, v0, v1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->startCodeLength(Ljava/nio/ByteBuffer;II)I

    move-result v4

    add-int/2addr v0, v4

    .line 302
    invoke-static {p1, v0, v1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->findStartCode(Ljava/nio/ByteBuffer;II)I

    move-result v4

    if-gez v4, :cond_6

    move v5, v1

    goto :goto_3

    :cond_6
    move v5, v4

    :goto_3
    if-le v5, v0, :cond_7

    .line 305
    iget-object v6, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->videoConversionBuffer:Ljava/nio/ByteBuffer;

    sub-int v7, v5, v0

    invoke-virtual {v6, v7}, Ljava/nio/ByteBuffer;->putInt(I)Ljava/nio/ByteBuffer;

    .line 306
    invoke-virtual {p1, v0}, Ljava/nio/ByteBuffer;->position(I)Ljava/nio/Buffer;

    .line 307
    invoke-virtual {p1, v5}, Ljava/nio/ByteBuffer;->limit(I)Ljava/nio/Buffer;

    .line 308
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->videoConversionBuffer:Ljava/nio/ByteBuffer;

    invoke-virtual {v0, p1}, Ljava/nio/ByteBuffer;->put(Ljava/nio/ByteBuffer;)Ljava/nio/ByteBuffer;
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    goto :goto_4

    :catchall_0
    move-exception v0

    move-object p0, v0

    goto :goto_5

    :cond_7
    :goto_4
    move v0, v4

    goto :goto_2

    .line 313
    :goto_5
    invoke-virtual {p1, v3}, Ljava/nio/ByteBuffer;->limit(I)Ljava/nio/Buffer;

    .line 314
    invoke-virtual {p1, v2}, Ljava/nio/ByteBuffer;->position(I)Ljava/nio/Buffer;

    .line 315
    throw p0

    .line 313
    :cond_8
    invoke-virtual {p1, v3}, Ljava/nio/ByteBuffer;->limit(I)Ljava/nio/Buffer;

    .line 314
    invoke-virtual {p1, v2}, Ljava/nio/ByteBuffer;->position(I)Ljava/nio/Buffer;

    .line 316
    iget-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->videoConversionBuffer:Ljava/nio/ByteBuffer;

    invoke-virtual {p1}, Ljava/nio/ByteBuffer;->flip()Ljava/nio/Buffer;

    .line 317
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->convertedVideoInfo:Landroid/media/MediaCodec$BufferInfo;

    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->videoConversionBuffer:Ljava/nio/ByteBuffer;

    .line 319
    invoke-virtual {p0}, Ljava/nio/Buffer;->remaining()I

    move-result v2

    iget-wide v3, p2, Landroid/media/MediaCodec$BufferInfo;->presentationTimeUs:J

    iget v5, p2, Landroid/media/MediaCodec$BufferInfo;->flags:I

    const/4 v1, 0x0

    .line 317
    invoke-virtual/range {v0 .. v5}, Landroid/media/MediaCodec$BufferInfo;->set(IIJI)V

    const/4 p0, 0x2

    return p0
.end method

.method private notifyAvailable(J)V
    .locals 2

    .line 203
    iget-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->availableSize:J

    cmp-long v0, p1, v0

    if-gtz v0, :cond_0

    goto :goto_0

    .line 204
    :cond_0
    iput-wide p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->availableSize:J

    .line 205
    iget-boolean v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->suppressAvailableNotifications:Z

    if-eqz v0, :cond_1

    :goto_0
    return-void

    .line 206
    :cond_1
    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->listener:Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer$Listener;

    invoke-interface {p0, p1, p2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer$Listener;->onBytesAvailable(J)V

    return-void
.end method

.method private requireBuilder()V
    .locals 0

    .line 210
    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->builder:Lorg/telegram/messenger/video/MP4Builder;

    if-eqz p0, :cond_0

    return-void

    :cond_0
    const-string p0, "MP4 tracks are not initialized"

    invoke-static {p0}, Lorg/vosk/Model$$ExternalSyntheticBUOutline0;->m(Ljava/lang/String;)V

    return-void
.end method

.method private static sameBytes(Ljava/nio/ByteBuffer;Ljava/nio/ByteBuffer;)Z
    .locals 8

    const/4 v0, 0x1

    const/4 v1, 0x0

    if-eqz p0, :cond_4

    if-nez p1, :cond_0

    goto :goto_1

    .line 360
    :cond_0
    invoke-static {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->codecSpecificDataOffset(Ljava/nio/ByteBuffer;)I

    move-result v2

    .line 361
    invoke-static {p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->codecSpecificDataOffset(Ljava/nio/ByteBuffer;)I

    move-result v3

    .line 362
    invoke-virtual {p0}, Ljava/nio/Buffer;->limit()I

    move-result v4

    sub-int/2addr v4, v2

    .line 363
    invoke-virtual {p1}, Ljava/nio/Buffer;->limit()I

    move-result v5

    sub-int/2addr v5, v3

    if-eq v4, v5, :cond_1

    return v1

    :cond_1
    move v5, v1

    :goto_0
    if-ge v5, v4, :cond_3

    add-int v6, v2, v5

    .line 366
    invoke-virtual {p0, v6}, Ljava/nio/ByteBuffer;->get(I)B

    move-result v6

    add-int v7, v3, v5

    invoke-virtual {p1, v7}, Ljava/nio/ByteBuffer;->get(I)B

    move-result v7

    if-eq v6, v7, :cond_2

    return v1

    :cond_2
    add-int/lit8 v5, v5, 0x1

    goto :goto_0

    :cond_3
    return v0

    :cond_4
    :goto_1
    if-ne p0, p1, :cond_5

    return v0

    :cond_5
    return v1
.end method

.method private static varargs sameCodecSpecificData(Landroid/media/MediaFormat;Landroid/media/MediaFormat;[Ljava/lang/String;)Z
    .locals 5

    const/4 v0, 0x0

    if-nez p0, :cond_0

    return v0

    .line 350
    :cond_0
    array-length v1, p2

    move v2, v0

    :goto_0
    if-ge v2, v1, :cond_2

    aget-object v3, p2, v2

    .line 351
    invoke-virtual {p0, v3}, Landroid/media/MediaFormat;->getByteBuffer(Ljava/lang/String;)Ljava/nio/ByteBuffer;

    move-result-object v4

    .line 352
    invoke-virtual {p1, v3}, Landroid/media/MediaFormat;->getByteBuffer(Ljava/lang/String;)Ljava/nio/ByteBuffer;

    move-result-object v3

    .line 353
    invoke-static {v4, v3}, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->sameBytes(Ljava/nio/ByteBuffer;Ljava/nio/ByteBuffer;)Z

    move-result v3

    if-nez v3, :cond_1

    return v0

    :cond_1
    add-int/lit8 v2, v2, 0x1

    goto :goto_0

    :cond_2
    const/4 p0, 0x1

    return p0
.end method

.method private static startCodeLength(Ljava/nio/ByteBuffer;II)I
    .locals 1

    add-int/lit8 v0, p1, 0x3

    if-ge v0, p2, :cond_0

    add-int/lit8 p1, p1, 0x2

    .line 341
    invoke-virtual {p0, p1}, Ljava/nio/ByteBuffer;->get(I)B

    move-result p0

    if-nez p0, :cond_0

    const/4 p0, 0x4

    return p0

    :cond_0
    const/4 p0, 0x3

    return p0
.end method

.method private writeSampleInternal(ZLjava/nio/ByteBuffer;Landroid/media/MediaCodec$BufferInfo;)V
    .locals 4

    if-eqz p1, :cond_1

    .line 179
    :try_start_0
    invoke-direct {p0, p2, p3}, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->normalizeVideoSample(Ljava/nio/ByteBuffer;Landroid/media/MediaCodec$BufferInfo;)I

    move-result v0

    const/4 v1, 0x2

    const/4 v2, 0x0

    if-ne v0, v1, :cond_0

    .line 181
    iget-object p2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->videoConversionBuffer:Ljava/nio/ByteBuffer;

    .line 182
    iget-object p3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->convertedVideoInfo:Landroid/media/MediaCodec$BufferInfo;

    goto :goto_0

    :cond_0
    if-nez v0, :cond_1

    goto :goto_0

    :cond_1
    move v2, p1

    .line 188
    :goto_0
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->builder:Lorg/telegram/messenger/video/MP4Builder;

    if-eqz p1, :cond_2

    .line 189
    iget v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->videoTrack:I

    goto :goto_1

    :cond_2
    iget v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->audioTrack:I

    .line 188
    :goto_1
    invoke-virtual {v0, v1, p2, p3, v2}, Lorg/telegram/messenger/video/MP4Builder;->writeSampleData(ILjava/nio/ByteBuffer;Landroid/media/MediaCodec$BufferInfo;Z)J

    move-result-wide p2

    const-wide/16 v0, 0x1

    if-eqz p1, :cond_3

    .line 194
    iget-wide v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->videoSamples:J

    add-long/2addr v2, v0

    iput-wide v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->videoSamples:J

    goto :goto_2

    .line 195
    :cond_3
    iget-wide v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->audioSamples:J

    add-long/2addr v2, v0

    iput-wide v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->audioSamples:J

    :goto_2
    const-wide/16 v0, 0x0

    cmp-long p1, p2, v0

    if-lez p1, :cond_4

    .line 196
    invoke-direct {p0, p2, p3}, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->notifyAvailable(J)V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    :cond_4
    return-void

    :catch_0
    move-exception p0

    .line 198
    const-string p1, "Unable to write MP4 sample"

    invoke-static {p1, p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->asIOException(Ljava/lang/String;Ljava/lang/Exception;)Ljava/io/IOException;

    move-result-object p0

    throw p0
.end method


# virtual methods
.method public declared-synchronized closeForReplacement()V
    .locals 2

    monitor-enter p0

    const/4 v0, 0x1

    .line 138
    :try_start_0
    iput-boolean v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->suppressAvailableNotifications:Z
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    const/4 v0, 0x0

    .line 140
    :try_start_1
    invoke-virtual {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->finish()V
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_1

    .line 142
    :try_start_2
    iput-boolean v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->suppressAvailableNotifications:Z
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_0

    .line 144
    monitor-exit p0

    return-void

    :catchall_0
    move-exception v0

    goto :goto_0

    :catchall_1
    move-exception v1

    .line 142
    :try_start_3
    iput-boolean v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->suppressAvailableNotifications:Z

    .line 143
    throw v1

    :goto_0
    monitor-exit p0
    :try_end_3
    .catchall {:try_start_3 .. :try_end_3} :catchall_0

    throw v0
.end method

.method public declared-synchronized createPreview(Ljava/io/File;)V
    .locals 7

    const-string v0, "MP4 preview written: file="

    monitor-enter p0

    .line 107
    :try_start_0
    invoke-direct {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->requireBuilder()V

    .line 108
    invoke-static {}, Ljava/lang/System;->nanoTime()J

    move-result-wide v1
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 110
    :try_start_1
    iget-object v3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->builder:Lorg/telegram/messenger/video/MP4Builder;

    invoke-virtual {v3, p1}, Lorg/telegram/messenger/video/MP4Builder;->finishMovie(Ljava/io/File;)V

    .line 111
    iget-object v3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->diagnostics:Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;

    new-instance v4, Ljava/lang/StringBuilder;

    invoke-direct {v4, v0}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {p1}, Ljava/io/File;->getName()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v4, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v0, ", size="

    invoke-virtual {v4, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 112
    invoke-virtual {p1}, Ljava/io/File;->length()J

    move-result-wide v5

    invoke-virtual {v4, v5, v6}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    const-string p1, ", elapsedMs="

    invoke-virtual {v4, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 113
    invoke-static {v1, v2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->elapsedMs(J)J

    move-result-wide v0

    invoke-virtual {v4, v0, v1}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    invoke-virtual {v4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    .line 111
    invoke-virtual {v3, p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;->log(Ljava/lang/String;)V
    :try_end_1
    .catch Ljava/lang/Exception; {:try_start_1 .. :try_end_1} :catch_0
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    .line 117
    monitor-exit p0

    return-void

    :catchall_0
    move-exception p1

    goto :goto_0

    :catch_0
    move-exception p1

    .line 115
    :try_start_2
    const-string v0, "Unable to create preview MP4"

    invoke-static {v0, p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->asIOException(Ljava/lang/String;Ljava/lang/Exception;)Ljava/io/IOException;

    move-result-object p1

    throw p1

    :goto_0
    monitor-exit p0
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_0

    throw p1
.end method

.method public declared-synchronized finish()V
    .locals 7

    const-string v0, "MP4 finalized: file="

    monitor-enter p0

    .line 120
    :try_start_0
    iget-boolean v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->finished:Z
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    if-eqz v1, :cond_0

    monitor-exit p0

    return-void

    .line 121
    :cond_0
    :try_start_1
    invoke-direct {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->requireBuilder()V

    .line 122
    invoke-static {}, Ljava/lang/System;->nanoTime()J

    move-result-wide v1
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    .line 124
    :try_start_2
    iget-object v3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->builder:Lorg/telegram/messenger/video/MP4Builder;

    invoke-virtual {v3}, Lorg/telegram/messenger/video/MP4Builder;->finishMovie()V

    const/4 v3, 0x1

    .line 125
    iput-boolean v3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->finished:Z

    .line 126
    iget-object v3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->file:Ljava/io/File;

    invoke-virtual {v3}, Ljava/io/File;->length()J

    move-result-wide v3

    invoke-direct {p0, v3, v4}, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->notifyAvailable(J)V

    .line 127
    iget-object v3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->diagnostics:Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;

    new-instance v4, Ljava/lang/StringBuilder;

    invoke-direct {v4, v0}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->file:Ljava/io/File;

    invoke-virtual {v0}, Ljava/io/File;->getName()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v4, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v0, ", size="

    invoke-virtual {v4, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->file:Ljava/io/File;

    .line 128
    invoke-virtual {v0}, Ljava/io/File;->length()J

    move-result-wide v5

    invoke-virtual {v4, v5, v6}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    const-string v0, ", videoSamples="

    invoke-virtual {v4, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-wide v5, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->videoSamples:J

    invoke-virtual {v4, v5, v6}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    const-string v0, ", audioSamples="

    invoke-virtual {v4, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-wide v5, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->audioSamples:J

    invoke-virtual {v4, v5, v6}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    const-string v0, ", elapsedMs="

    invoke-virtual {v4, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 131
    invoke-static {v1, v2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->elapsedMs(J)J

    move-result-wide v0

    invoke-virtual {v4, v0, v1}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    invoke-virtual {v4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    .line 127
    invoke-virtual {v3, v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;->log(Ljava/lang/String;)V
    :try_end_2
    .catch Ljava/lang/Exception; {:try_start_2 .. :try_end_2} :catch_0
    .catchall {:try_start_2 .. :try_end_2} :catchall_0

    .line 135
    monitor-exit p0

    return-void

    :catchall_0
    move-exception v0

    goto :goto_0

    :catch_0
    move-exception v0

    .line 133
    :try_start_3
    const-string v1, "Unable to finish MP4"

    invoke-static {v1, v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->asIOException(Ljava/lang/String;Ljava/lang/Exception;)Ljava/io/IOException;

    move-result-object v0

    throw v0

    :goto_0
    monitor-exit p0
    :try_end_3
    .catchall {:try_start_3 .. :try_end_3} :catchall_0

    throw v0
.end method

.method public getFile()Ljava/io/File;
    .locals 0

    .line 63
    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->file:Ljava/io/File;

    return-object p0
.end method

.method public declared-synchronized setTrackFormat(ZLandroid/media/MediaFormat;)V
    .locals 4

    const-string v0, " codec configuration changed between segments"

    monitor-enter p0

    .line 67
    :try_start_0
    iget-boolean v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->finished:Z
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    if-eqz v1, :cond_0

    monitor-exit p0

    return-void

    .line 68
    :cond_0
    :try_start_1
    iget-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->builder:Lorg/telegram/messenger/video/MP4Builder;

    if-eqz v1, :cond_6

    if-eqz p1, :cond_1

    .line 69
    iget-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->videoFormat:Landroid/media/MediaFormat;

    goto :goto_0

    :catchall_0
    move-exception p1

    goto :goto_3

    :cond_1
    iget-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->audioFormat:Landroid/media/MediaFormat;

    :goto_0
    if-eqz p1, :cond_2

    .line 70
    const-string v2, "csd-0"

    const-string v3, "csd-1"

    filled-new-array {v2, v3}, [Ljava/lang/String;

    move-result-object v2

    invoke-static {v1, p2, v2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->sameCodecSpecificData(Landroid/media/MediaFormat;Landroid/media/MediaFormat;[Ljava/lang/String;)Z

    move-result v2

    if-eqz v2, :cond_3

    :cond_2
    if-nez p1, :cond_5

    iget-boolean v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->includeAudio:Z

    if-eqz v2, :cond_5

    const-string v2, "csd-0"

    filled-new-array {v2}, [Ljava/lang/String;

    move-result-object v2

    .line 72
    invoke-static {v1, p2, v2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->sameCodecSpecificData(Landroid/media/MediaFormat;Landroid/media/MediaFormat;[Ljava/lang/String;)Z

    move-result p2

    if-nez p2, :cond_5

    .line 73
    :cond_3
    new-instance p2, Ljava/io/IOException;

    if-eqz p1, :cond_4

    const-string p1, "Video"

    goto :goto_1

    :cond_4
    const-string p1, "Audio"

    :goto_1
    invoke-virtual {p1, v0}, Ljava/lang/String;->concat(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p1

    invoke-direct {p2, p1}, Ljava/io/IOException;-><init>(Ljava/lang/String;)V

    throw p2
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    .line 76
    :cond_5
    monitor-exit p0

    return-void

    :cond_6
    if-eqz p1, :cond_7

    .line 79
    :try_start_2
    iput-object p2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->videoFormat:Landroid/media/MediaFormat;

    goto :goto_2

    .line 80
    :cond_7
    iget-boolean p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->includeAudio:Z

    if-eqz p1, :cond_8

    .line 81
    iput-object p2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->audioFormat:Landroid/media/MediaFormat;

    .line 83
    :cond_8
    :goto_2
    invoke-direct {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->initializeIfReady()V
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_0

    .line 84
    monitor-exit p0

    return-void

    :goto_3
    :try_start_3
    monitor-exit p0
    :try_end_3
    .catchall {:try_start_3 .. :try_end_3} :catchall_0

    throw p1
.end method

.method public declared-synchronized writeSample(ZLjava/nio/ByteBuffer;Landroid/media/MediaCodec$BufferInfo;J)V
    .locals 1

    monitor-enter p0

    .line 92
    :try_start_0
    iget-boolean v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->finished:Z

    if-nez v0, :cond_3

    if-nez p1, :cond_0

    iget-boolean v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->includeAudio:Z

    if-eqz v0, :cond_3

    goto :goto_0

    :catchall_0
    move-exception p1

    goto :goto_2

    :cond_0
    :goto_0
    iget v0, p3, Landroid/media/MediaCodec$BufferInfo;->size:I

    if-gtz v0, :cond_1

    goto :goto_1

    .line 93
    :cond_1
    invoke-direct {p0, p1, p3, p4, p5}, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->copyInfo(ZLandroid/media/MediaCodec$BufferInfo;J)Landroid/media/MediaCodec$BufferInfo;

    move-result-object p4

    .line 94
    iget-object p5, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->builder:Lorg/telegram/messenger/video/MP4Builder;

    if-nez p5, :cond_2

    .line 95
    iget p5, p3, Landroid/media/MediaCodec$BufferInfo;->size:I

    invoke-static {p5}, Ljava/nio/ByteBuffer;->allocateDirect(I)Ljava/nio/ByteBuffer;

    move-result-object p5

    .line 96
    iget v0, p3, Landroid/media/MediaCodec$BufferInfo;->offset:I

    invoke-virtual {p2, v0}, Ljava/nio/ByteBuffer;->position(I)Ljava/nio/Buffer;

    .line 97
    iget v0, p3, Landroid/media/MediaCodec$BufferInfo;->offset:I

    iget p3, p3, Landroid/media/MediaCodec$BufferInfo;->size:I

    add-int/2addr v0, p3

    invoke-virtual {p2, v0}, Ljava/nio/ByteBuffer;->limit(I)Ljava/nio/Buffer;

    .line 98
    invoke-virtual {p5, p2}, Ljava/nio/ByteBuffer;->put(Ljava/nio/ByteBuffer;)Ljava/nio/ByteBuffer;

    move-result-object p2

    invoke-virtual {p2}, Ljava/nio/ByteBuffer;->flip()Ljava/nio/Buffer;

    const/4 p2, 0x0

    .line 99
    iput p2, p4, Landroid/media/MediaCodec$BufferInfo;->offset:I

    .line 100
    iget-object p2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->pendingSamples:Ljava/util/ArrayList;

    new-instance p3, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer$PendingSample;

    invoke-direct {p3, p1, p5, p4}, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer$PendingSample;-><init>(ZLjava/nio/ByteBuffer;Landroid/media/MediaCodec$BufferInfo;)V

    invoke-virtual {p2, p3}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 101
    monitor-exit p0

    return-void

    .line 103
    :cond_2
    :try_start_1
    invoke-direct {p0, p1, p2, p4}, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->writeSampleInternal(ZLjava/nio/ByteBuffer;Landroid/media/MediaCodec$BufferInfo;)V
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    .line 104
    monitor-exit p0

    return-void

    .line 92
    :cond_3
    :goto_1
    monitor-exit p0

    return-void

    :goto_2
    :try_start_2
    monitor-exit p0
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_0

    throw p1
.end method
