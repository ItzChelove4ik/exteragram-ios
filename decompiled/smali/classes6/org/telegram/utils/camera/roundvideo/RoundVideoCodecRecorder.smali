.class final Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Lorg/telegram/utils/camera/roundvideo/RoundVideoGlProcessor$FrameTimingListener;


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder$ErrorListener;,
        Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder$PendingAudioSample;
    }
.end annotation


# instance fields
.field private alignedAudioStartDeltaUs:J

.field private audioBuffers:I

.field private audioBytes:J

.field private audioCodec:Landroid/media/MediaCodec;

.field private audioReadErrors:I

.field private audioReadyListener:Ljava/lang/Runnable;

.field private audioRecord:Landroid/media/AudioRecord;

.field private audioStartAligned:Z

.field private audioThread:Ljava/lang/Thread;

.field private final audioTimestamp:Landroid/media/AudioTimestamp;

.field private final diagnostics:Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;

.field private droppedAudioAfterStop:I

.field private droppedAudioBuffers:I

.field private droppedVideoAfterStop:I

.field private emptyAudioReads:I

.field private final errorListener:Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder$ErrorListener;

.field private final errorReported:Ljava/util/concurrent/atomic/AtomicBoolean;

.field private firstAudioPtsUs:J

.field private volatile firstVideoPtsUs:J

.field private lastAudioPtsUs:J

.field private lastKeyframePtsUs:J

.field private lastVideoPtsUs:J

.field private latencyMaxNs:J

.field private latencySamples:I

.field private latencyTotalNs:J

.field private maximumKeyframeIntervalUs:J

.field private nonMonotonicAudioPts:I

.field private nonMonotonicVideoPts:I

.field private final outputSize:I

.field private final pendingAudioSamples:Ljava/util/ArrayList;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/ArrayList<",
            "Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder$PendingAudioSample;",
            ">;"
        }
    .end annotation
.end field

.field private prepared:Z

.field private prependHeaderSize:I

.field private started:Z

.field private stopFinalizationStarted:Z

.field private volatile stopPresentationTimeUs:J

.field private volatile stopping:Z

.field private final submittedPtsUs:[J

.field private volatile submittedSequence:J

.field private final submittedTimeNs:[J

.field private timeOriginNs:J

.field private final timelineOffsetUs:J

.field private final videoBitrate:I

.field private videoBuffers:I

.field private videoBytes:J

.field private videoCodec:Landroid/media/MediaCodec;

.field private videoDrainThread:Ljava/lang/Thread;

.field private final videoFrameRate:I

.field private videoInputSurface:Landroid/view/Surface;

.field private videoKeyframes:I

.field private videoReadyListener:Ljava/lang/Runnable;

.field private final writer:Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;


# direct methods
.method public static synthetic $r8$lambda$rslDXXFtcnYJZUc3Hibo3G7lRuA(Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->drainVideo()V

    return-void
.end method

.method public static synthetic $r8$lambda$uRkrlwnpT4OKrW6qd3hFLO8-yuA(Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->captureAndEncodeAudio()V

    return-void
.end method

.method public constructor <init>(Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;JIIILorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder$ErrorListener;)V
    .locals 2

    .line 97
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 44
    new-instance v0, Ljava/util/concurrent/atomic/AtomicBoolean;

    invoke-direct {v0}, Ljava/util/concurrent/atomic/AtomicBoolean;-><init>()V

    iput-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->errorReported:Ljava/util/concurrent/atomic/AtomicBoolean;

    .line 45
    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    iput-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->pendingAudioSamples:Ljava/util/ArrayList;

    .line 46
    new-instance v0, Landroid/media/AudioTimestamp;

    invoke-direct {v0}, Landroid/media/AudioTimestamp;-><init>()V

    iput-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->audioTimestamp:Landroid/media/AudioTimestamp;

    const/16 v0, 0x100

    .line 47
    new-array v1, v0, [J

    iput-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->submittedPtsUs:[J

    .line 48
    new-array v0, v0, [J

    iput-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->submittedTimeNs:[J

    const-wide v0, 0x7fffffffffffffffL

    .line 59
    iput-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->stopPresentationTimeUs:J

    .line 98
    iput-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->writer:Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;

    .line 99
    iput-wide p2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->timelineOffsetUs:J

    .line 100
    iput p4, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->outputSize:I

    .line 101
    iput p5, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->videoBitrate:I

    .line 102
    iput p6, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->videoFrameRate:I

    .line 103
    iput-object p7, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->diagnostics:Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;

    .line 104
    iput-object p8, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->errorListener:Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder$ErrorListener;

    return-void
.end method

.method private static bitrate(JJ)Ljava/lang/String;
    .locals 2

    const-wide/16 v0, 0x0

    cmp-long v0, p2, v0

    if-gtz v0, :cond_0

    .line 674
    const-string p0, "n/a"

    return-object p0

    :cond_0
    const-wide/32 v0, 0x7a1200

    mul-long/2addr p0, v0

    div-long/2addr p0, p2

    invoke-static {p0, p1}, Ljava/lang/String;->valueOf(J)Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method private bufferAudioSample(Ljava/nio/ByteBuffer;Landroid/media/MediaCodec$BufferInfo;)V
    .locals 10

    .line 472
    iget v0, p2, Landroid/media/MediaCodec$BufferInfo;->size:I

    invoke-static {v0}, Ljava/nio/ByteBuffer;->allocateDirect(I)Ljava/nio/ByteBuffer;

    move-result-object v0

    .line 473
    invoke-virtual {p1}, Ljava/nio/Buffer;->position()I

    move-result v1

    .line 474
    invoke-virtual {p1}, Ljava/nio/Buffer;->limit()I

    move-result v2

    .line 476
    :try_start_0
    iget v3, p2, Landroid/media/MediaCodec$BufferInfo;->offset:I

    invoke-virtual {p1, v3}, Ljava/nio/ByteBuffer;->position(I)Ljava/nio/Buffer;

    .line 477
    iget v3, p2, Landroid/media/MediaCodec$BufferInfo;->offset:I

    iget v4, p2, Landroid/media/MediaCodec$BufferInfo;->size:I

    add-int/2addr v3, v4

    invoke-virtual {p1, v3}, Ljava/nio/ByteBuffer;->limit(I)Ljava/nio/Buffer;

    .line 478
    invoke-virtual {v0, p1}, Ljava/nio/ByteBuffer;->put(Ljava/nio/ByteBuffer;)Ljava/nio/ByteBuffer;

    move-result-object v3

    invoke-virtual {v3}, Ljava/nio/ByteBuffer;->flip()Ljava/nio/Buffer;
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 480
    invoke-virtual {p1, v2}, Ljava/nio/ByteBuffer;->limit(I)Ljava/nio/Buffer;

    .line 481
    invoke-virtual {p1, v1}, Ljava/nio/ByteBuffer;->position(I)Ljava/nio/Buffer;

    .line 483
    new-instance v4, Landroid/media/MediaCodec$BufferInfo;

    invoke-direct {v4}, Landroid/media/MediaCodec$BufferInfo;-><init>()V

    .line 484
    iget v6, p2, Landroid/media/MediaCodec$BufferInfo;->size:I

    iget-wide v7, p2, Landroid/media/MediaCodec$BufferInfo;->presentationTimeUs:J

    iget v9, p2, Landroid/media/MediaCodec$BufferInfo;->flags:I

    const/4 v5, 0x0

    invoke-virtual/range {v4 .. v9}, Landroid/media/MediaCodec$BufferInfo;->set(IIJI)V

    .line 485
    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->pendingAudioSamples:Ljava/util/ArrayList;

    new-instance p1, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder$PendingAudioSample;

    invoke-direct {p1, v0, v4}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder$PendingAudioSample;-><init>(Ljava/nio/ByteBuffer;Landroid/media/MediaCodec$BufferInfo;)V

    invoke-virtual {p0, p1}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    return-void

    :catchall_0
    move-exception v0

    move-object p0, v0

    .line 480
    invoke-virtual {p1, v2}, Ljava/nio/ByteBuffer;->limit(I)Ljava/nio/Buffer;

    .line 481
    invoke-virtual {p1, v1}, Ljava/nio/ByteBuffer;->position(I)Ljava/nio/Buffer;

    .line 482
    throw p0
.end method

.method private captureAndEncodeAudio()V
    .locals 22

    move-object/from16 v1, p0

    const/16 v0, -0x10

    .line 321
    invoke-static {v0}, Landroid/os/Process;->setThreadPriority(I)V

    .line 322
    new-instance v0, Landroid/media/MediaCodec$BufferInfo;

    invoke-direct {v0}, Landroid/media/MediaCodec$BufferInfo;-><init>()V

    const-wide/high16 v4, -0x8000000000000000L

    const/4 v6, 0x0

    move-wide v8, v4

    move v7, v6

    const-wide/16 v10, 0x0

    :goto_0
    if-nez v6, :cond_c

    const-wide/16 v12, 0x2710

    if-eqz v7, :cond_0

    move-wide v14, v12

    goto :goto_1

    :cond_0
    const-wide/16 v14, 0x0

    .line 329
    :goto_1
    :try_start_0
    invoke-direct {v1, v0, v14, v15}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->drainAudioOutput(Landroid/media/MediaCodec$BufferInfo;J)Z

    move-result v6

    if-eqz v6, :cond_1

    goto/16 :goto_6

    .line 331
    :cond_1
    iget-object v14, v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->audioCodec:Landroid/media/MediaCodec;

    invoke-virtual {v14, v12, v13}, Landroid/media/MediaCodec;->dequeueInputBuffer(J)I

    move-result v16

    if-gez v16, :cond_2

    goto :goto_0

    .line 333
    :cond_2
    iget-boolean v12, v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->stopping:Z

    const/4 v13, 0x1

    if-eqz v12, :cond_4

    cmp-long v7, v8, v4

    if-nez v7, :cond_3

    const-wide/16 v19, 0x0

    goto :goto_2

    .line 334
    :cond_3
    invoke-static {v10, v11}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->framesToDurationUs(J)J

    move-result-wide v14

    add-long/2addr v14, v8

    move-wide/from16 v19, v14

    .line 335
    :goto_2
    iget-object v15, v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->audioCodec:Landroid/media/MediaCodec;

    const/16 v18, 0x0

    const/16 v21, 0x4

    const/16 v17, 0x0

    invoke-virtual/range {v15 .. v21}, Landroid/media/MediaCodec;->queueInputBuffer(IIIJI)V

    move v7, v13

    goto :goto_0

    :catch_0
    move-exception v0

    goto/16 :goto_5

    :cond_4
    move/from16 v12, v16

    .line 339
    iget-object v14, v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->audioCodec:Landroid/media/MediaCodec;

    invoke-virtual {v14, v12}, Landroid/media/MediaCodec;->getInputBuffer(I)Ljava/nio/ByteBuffer;

    move-result-object v14

    if-nez v14, :cond_5

    .line 341
    iget-object v15, v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->audioCodec:Landroid/media/MediaCodec;

    const-wide/16 v19, 0x0

    const/16 v21, 0x0

    const/16 v17, 0x0

    const/16 v18, 0x0

    move/from16 v16, v12

    invoke-virtual/range {v15 .. v21}, Landroid/media/MediaCodec;->queueInputBuffer(IIIJI)V

    goto :goto_0

    :cond_5
    move/from16 v16, v12

    .line 344
    invoke-virtual {v14}, Ljava/nio/ByteBuffer;->clear()Ljava/nio/Buffer;

    .line 345
    iget-object v12, v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->audioRecord:Landroid/media/AudioRecord;

    .line 347
    invoke-virtual {v14}, Ljava/nio/Buffer;->remaining()I

    move-result v15

    const/16 v2, 0x800

    invoke-static {v15, v2}, Ljava/lang/Math;->min(II)I

    move-result v2

    .line 345
    invoke-virtual {v12, v14, v2}, Landroid/media/AudioRecord;->read(Ljava/nio/ByteBuffer;I)I

    move-result v2

    if-gtz v2, :cond_9

    if-gez v2, :cond_7

    .line 351
    iget v3, v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->audioReadErrors:I

    add-int/2addr v3, v13

    iput v3, v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->audioReadErrors:I

    const/4 v12, -0x6

    if-eq v2, v12, :cond_6

    const/4 v12, 0x3

    if-ge v3, v12, :cond_6

    goto :goto_3

    .line 353
    :cond_6
    new-instance v0, Ljava/lang/IllegalStateException;

    new-instance v3, Ljava/lang/StringBuilder;

    invoke-direct {v3}, Ljava/lang/StringBuilder;-><init>()V

    const-string v4, "AudioRecord read failed: "

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3, v2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    invoke-direct {v0, v2}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 356
    :cond_7
    iget v2, v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->emptyAudioReads:I

    add-int/2addr v2, v13

    iput v2, v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->emptyAudioReads:I

    :goto_3
    cmp-long v2, v8, v4

    if-nez v2, :cond_8

    const-wide/16 v19, 0x0

    goto :goto_4

    .line 360
    :cond_8
    invoke-static {v10, v11}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->framesToDurationUs(J)J

    move-result-wide v2

    add-long/2addr v2, v8

    move-wide/from16 v19, v2

    .line 361
    :goto_4
    iget-object v15, v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->audioCodec:Landroid/media/MediaCodec;

    const/16 v18, 0x0

    const/16 v21, 0x0

    const/16 v17, 0x0

    invoke-virtual/range {v15 .. v21}, Landroid/media/MediaCodec;->queueInputBuffer(IIIJI)V

    goto/16 :goto_0

    .line 364
    :cond_9
    div-int/lit8 v2, v2, 0x2

    cmp-long v3, v8, v4

    if-nez v3, :cond_a

    .line 366
    invoke-direct {v1, v2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->resolveAudioBasePtsUs(I)J

    move-result-wide v8

    .line 367
    iget-object v3, v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->diagnostics:Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;

    new-instance v12, Ljava/lang/StringBuilder;

    invoke-direct {v12}, Ljava/lang/StringBuilder;-><init>()V

    const-string v13, "first audio input: basePtsUs="

    invoke-virtual {v12, v13}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v12, v8, v9}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    const-string v13, ", frames="

    invoke-virtual {v12, v13}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v12, v2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string v13, ", timestampSource="

    invoke-virtual {v12, v13}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 371
    const-string v13, "AudioTimestamp"

    invoke-virtual {v12, v13}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v12}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v12

    .line 367
    invoke-virtual {v3, v12}, Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;->log(Ljava/lang/String;)V

    .line 373
    :cond_a
    invoke-static {v10, v11}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->framesToDurationUs(J)J

    move-result-wide v12

    add-long v19, v8, v12

    .line 374
    iget-object v15, v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->audioCodec:Landroid/media/MediaCodec;

    mul-int/lit8 v18, v2, 0x2

    const/16 v21, 0x0

    const/16 v17, 0x0

    invoke-virtual/range {v15 .. v21}, Landroid/media/MediaCodec;->queueInputBuffer(IIIJI)V

    int-to-long v2, v2

    add-long/2addr v10, v2

    .line 376
    invoke-direct {v1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->notifyAudioReady()V
    :try_end_0
    .catch Ljava/lang/RuntimeException; {:try_start_0 .. :try_end_0} :catch_0

    goto/16 :goto_0

    .line 379
    :goto_5
    iget-boolean v2, v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->stopping:Z

    if-nez v2, :cond_b

    invoke-direct {v1, v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->reportError(Ljava/lang/Exception;)V

    goto :goto_6

    .line 380
    :cond_b
    iget-object v1, v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->diagnostics:Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;

    const-string v2, "audio capture failed while stopping"

    invoke-virtual {v1, v2, v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;->error(Ljava/lang/String;Ljava/lang/Throwable;)V

    :cond_c
    :goto_6
    return-void
.end method

.method private static codecDiagnostic(Ljava/lang/Exception;)Ljava/lang/String;
    .locals 2

    .line 679
    instance-of v0, p0, Landroid/media/MediaCodec$CodecException;

    if-eqz v0, :cond_0

    .line 680
    check-cast p0, Landroid/media/MediaCodec$CodecException;

    .line 681
    new-instance v0, Ljava/lang/StringBuilder;

    const-string v1, ": diagnostic="

    invoke-direct {v0, v1}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {p0}, Landroid/media/MediaCodec$CodecException;->getDiagnosticInfo()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v1, ", recoverable="

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 682
    invoke-virtual {p0}, Landroid/media/MediaCodec$CodecException;->isRecoverable()Z

    move-result v1

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Z)Ljava/lang/StringBuilder;

    const-string v1, ", transient="

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 683
    invoke-virtual {p0}, Landroid/media/MediaCodec$CodecException;->isTransient()Z

    move-result p0

    invoke-virtual {v0, p0}, Ljava/lang/StringBuilder;->append(Z)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    return-object p0

    .line 685
    :cond_0
    const-string p0, ""

    return-object p0
.end method

.method private createAudioEncoder()V
    .locals 11

    const v0, 0xbb80

    const/16 v1, 0x10

    const/4 v2, 0x2

    .line 272
    invoke-static {v0, v1, v2}, Landroid/media/AudioRecord;->getMinBufferSize(III)I

    move-result v1

    if-lez v1, :cond_1

    mul-int/lit8 v3, v1, 0x4

    const/16 v4, 0x4000

    .line 274
    invoke-static {v3, v4}, Ljava/lang/Math;->max(II)I

    move-result v10

    .line 275
    const-string v3, "audio/mp4a-latm"

    const/4 v4, 0x1

    invoke-static {v3, v0, v4}, Landroid/media/MediaFormat;->createAudioFormat(Ljava/lang/String;II)Landroid/media/MediaFormat;

    move-result-object v0

    .line 276
    const-string v5, "aac-profile"

    invoke-virtual {v0, v5, v2}, Landroid/media/MediaFormat;->setInteger(Ljava/lang/String;I)V

    .line 277
    const-string v2, "bitrate"

    const v5, 0xfa00

    invoke-virtual {v0, v2, v5}, Landroid/media/MediaFormat;->setInteger(Ljava/lang/String;I)V

    .line 278
    const-string v2, "max-input-size"

    invoke-virtual {v0, v2, v10}, Landroid/media/MediaFormat;->setInteger(Ljava/lang/String;I)V

    .line 279
    invoke-static {v3}, Landroid/media/MediaCodec;->createEncoderByType(Ljava/lang/String;)Landroid/media/MediaCodec;

    move-result-object v2

    iput-object v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->audioCodec:Landroid/media/MediaCodec;

    .line 280
    iget-object v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->diagnostics:Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;

    new-instance v3, Ljava/lang/StringBuilder;

    const-string v5, "audio encoder configure: codec="

    invoke-direct {v3, v5}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    iget-object v5, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->audioCodec:Landroid/media/MediaCodec;

    invoke-virtual {v5}, Landroid/media/MediaCodec;->getName()Ljava/lang/String;

    move-result-object v5

    invoke-virtual {v3, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v5, ", format="

    invoke-virtual {v3, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    const-string v5, ", minBufferSize="

    invoke-virtual {v3, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3, v1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string v1, ", audioBufferSize="

    invoke-virtual {v3, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3, v10}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v2, v1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;->log(Ljava/lang/String;)V

    .line 284
    iget-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->audioCodec:Landroid/media/MediaCodec;

    const/4 v2, 0x0

    invoke-virtual {v1, v0, v2, v2, v4}, Landroid/media/MediaCodec;->configure(Landroid/media/MediaFormat;Landroid/view/Surface;Landroid/media/MediaCrypto;I)V

    .line 285
    new-instance v5, Landroid/media/AudioRecord;

    const/16 v8, 0x10

    const/4 v9, 0x2

    const/4 v6, 0x5

    const v7, 0xbb80

    invoke-direct/range {v5 .. v10}, Landroid/media/AudioRecord;-><init>(IIIII)V

    iput-object v5, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->audioRecord:Landroid/media/AudioRecord;

    .line 287
    invoke-virtual {v5}, Landroid/media/AudioRecord;->getState()I

    move-result p0

    if-ne p0, v4, :cond_0

    return-void

    :cond_0
    const-string p0, "Unable to initialize AudioRecord"

    invoke-static {p0}, Lokio/Segment$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-void

    .line 273
    :cond_1
    const-string p0, "Unsupported audio recording configuration"

    invoke-static {p0}, Lokio/Segment$$ExternalSyntheticBUOutline1;->m(Ljava/lang/String;)V

    return-void
.end method

.method private createVideoEncoder()V
    .locals 6

    .line 243
    iget v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->outputSize:I

    const-string v1, "video/avc"

    invoke-static {v1, v0, v0}, Landroid/media/MediaFormat;->createVideoFormat(Ljava/lang/String;II)Landroid/media/MediaFormat;

    move-result-object v0

    .line 248
    const-string v2, "color-format"

    const v3, 0x7f000789

    invoke-virtual {v0, v2, v3}, Landroid/media/MediaFormat;->setInteger(Ljava/lang/String;I)V

    .line 249
    const-string v2, "bitrate"

    iget v3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->videoBitrate:I

    invoke-virtual {v0, v2, v3}, Landroid/media/MediaFormat;->setInteger(Ljava/lang/String;I)V

    .line 250
    const-string v2, "frame-rate"

    iget v3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->videoFrameRate:I

    invoke-virtual {v0, v2, v3}, Landroid/media/MediaFormat;->setInteger(Ljava/lang/String;I)V

    .line 251
    const-string v2, "i-frame-interval"

    const/4 v3, 0x1

    invoke-virtual {v0, v2, v3}, Landroid/media/MediaFormat;->setInteger(Ljava/lang/String;I)V

    .line 252
    invoke-static {v1}, Landroid/media/MediaCodec;->createEncoderByType(Ljava/lang/String;)Landroid/media/MediaCodec;

    move-result-object v2

    iput-object v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->videoCodec:Landroid/media/MediaCodec;

    .line 253
    invoke-virtual {v2}, Landroid/media/MediaCodec;->getCodecInfo()Landroid/media/MediaCodecInfo;

    move-result-object v2

    .line 254
    invoke-virtual {v2, v1}, Landroid/media/MediaCodecInfo;->getCapabilitiesForType(Ljava/lang/String;)Landroid/media/MediaCodecInfo$CodecCapabilities;

    move-result-object v1

    .line 255
    invoke-virtual {v1}, Landroid/media/MediaCodecInfo$CodecCapabilities;->getVideoCapabilities()Landroid/media/MediaCodecInfo$VideoCapabilities;

    move-result-object v1

    .line 256
    iget v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->outputSize:I

    invoke-virtual {v1, v2, v2}, Landroid/media/MediaCodecInfo$VideoCapabilities;->isSizeSupported(II)Z

    move-result v2

    if-eqz v2, :cond_0

    iget v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->outputSize:I

    iget v4, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->videoFrameRate:I

    int-to-double v4, v4

    .line 257
    invoke-virtual {v1, v2, v2, v4, v5}, Landroid/media/MediaCodecInfo$VideoCapabilities;->areSizeAndRateSupported(IID)Z

    move-result v1

    if-eqz v1, :cond_0

    .line 265
    iget-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->diagnostics:Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;

    new-instance v2, Ljava/lang/StringBuilder;

    const-string v4, "video encoder configure: codec="

    invoke-direct {v2, v4}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    iget-object v4, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->videoCodec:Landroid/media/MediaCodec;

    invoke-virtual {v4}, Landroid/media/MediaCodec;->getName()Ljava/lang/String;

    move-result-object v4

    invoke-virtual {v2, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v4, ", format="

    invoke-virtual {v2, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v1, v2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;->log(Ljava/lang/String;)V

    .line 267
    iget-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->videoCodec:Landroid/media/MediaCodec;

    const/4 v2, 0x0

    invoke-virtual {v1, v0, v2, v2, v3}, Landroid/media/MediaCodec;->configure(Landroid/media/MediaFormat;Landroid/view/Surface;Landroid/media/MediaCrypto;I)V

    .line 268
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->videoCodec:Landroid/media/MediaCodec;

    invoke-virtual {v0}, Landroid/media/MediaCodec;->createInputSurface()Landroid/view/Surface;

    move-result-object v0

    iput-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->videoInputSurface:Landroid/view/Surface;

    return-void

    .line 262
    :cond_0
    new-instance v0, Ljava/io/IOException;

    iget v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->outputSize:I

    iget v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->outputSize:I

    iget p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->videoFrameRate:I

    new-instance v3, Ljava/lang/StringBuilder;

    const-string v4, "Video encoder does not support "

    invoke-direct {v3, v4}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v3, v1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string v1, "x"

    invoke-virtual {v3, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3, v2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string v1, " at "

    invoke-virtual {v3, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3, p0}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string p0, " fps"

    invoke-virtual {v3, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    invoke-direct {v0, p0}, Ljava/io/IOException;-><init>(Ljava/lang/String;)V

    throw v0
.end method

.method private drainAudioOutput(Landroid/media/MediaCodec$BufferInfo;J)Z
    .locals 4

    .line 386
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->audioCodec:Landroid/media/MediaCodec;

    invoke-virtual {v0, p1, p2, p3}, Landroid/media/MediaCodec;->dequeueOutputBuffer(Landroid/media/MediaCodec$BufferInfo;J)I

    move-result p2

    const/4 p3, 0x0

    move v0, p3

    :goto_0
    const/4 v1, 0x1

    if-ltz p2, :cond_2

    .line 388
    invoke-static {p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->isMediaBuffer(Landroid/media/MediaCodec$BufferInfo;)Z

    move-result v2

    if-eqz v2, :cond_0

    .line 389
    iget-object v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->audioCodec:Landroid/media/MediaCodec;

    invoke-virtual {v2, p2}, Landroid/media/MediaCodec;->getOutputBuffer(I)Ljava/nio/ByteBuffer;

    move-result-object v2

    invoke-direct {p0, v2, p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->writeOrBufferAudioSample(Ljava/nio/ByteBuffer;Landroid/media/MediaCodec$BufferInfo;)V

    .line 391
    :cond_0
    iget v2, p1, Landroid/media/MediaCodec$BufferInfo;->flags:I

    and-int/lit8 v2, v2, 0x4

    if-eqz v2, :cond_1

    goto :goto_1

    :cond_1
    move v1, p3

    :goto_1
    or-int/2addr v0, v1

    .line 392
    iget-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->audioCodec:Landroid/media/MediaCodec;

    invoke-virtual {v1, p2, p3}, Landroid/media/MediaCodec;->releaseOutputBuffer(IZ)V

    .line 393
    iget-object p2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->audioCodec:Landroid/media/MediaCodec;

    const-wide/16 v1, 0x0

    invoke-virtual {p2, p1, v1, v2}, Landroid/media/MediaCodec;->dequeueOutputBuffer(Landroid/media/MediaCodec$BufferInfo;J)I

    move-result p2

    goto :goto_0

    :cond_2
    const/4 p1, -0x2

    if-ne p2, p1, :cond_3

    .line 395
    iget-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->audioCodec:Landroid/media/MediaCodec;

    invoke-virtual {p1}, Landroid/media/MediaCodec;->getOutputFormat()Landroid/media/MediaFormat;

    move-result-object p1

    invoke-direct {p0, p3, p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->setTrackFormat(ZLandroid/media/MediaFormat;)V

    :cond_3
    if-eqz v0, :cond_4

    .line 396
    iget-boolean p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->audioStartAligned:Z

    if-nez p1, :cond_4

    iget-wide p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->firstVideoPtsUs:J

    const-wide/high16 v2, -0x8000000000000000L

    cmp-long p1, p1, v2

    if-eqz p1, :cond_4

    .line 397
    iget-wide p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->firstVideoPtsUs:J

    invoke-direct {p0, p1, p2, v1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->flushPendingAudioSamples(JZ)V

    :cond_4
    return v0
.end method

.method private drainVideo()V
    .locals 10

    .line 291
    new-instance v0, Landroid/media/MediaCodec$BufferInfo;

    invoke-direct {v0}, Landroid/media/MediaCodec$BufferInfo;-><init>()V

    const/4 v1, 0x0

    move v2, v1

    :cond_0
    :goto_0
    if-nez v2, :cond_6

    .line 295
    :try_start_0
    iget-object v3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->videoCodec:Landroid/media/MediaCodec;

    const-wide/16 v4, 0x2710

    invoke-virtual {v3, v0, v4, v5}, Landroid/media/MediaCodec;->dequeueOutputBuffer(Landroid/media/MediaCodec$BufferInfo;J)I

    move-result v3

    const/4 v4, -0x2

    const/4 v5, 0x1

    if-ne v3, v4, :cond_1

    .line 297
    iget-object v3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->videoCodec:Landroid/media/MediaCodec;

    invoke-virtual {v3}, Landroid/media/MediaCodec;->getOutputFormat()Landroid/media/MediaFormat;

    move-result-object v3

    invoke-direct {p0, v5, v3}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->setTrackFormat(ZLandroid/media/MediaFormat;)V

    goto :goto_0

    :catch_0
    move-exception v0

    goto :goto_3

    :cond_1
    if-ltz v3, :cond_0

    .line 299
    invoke-static {v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->isMediaBuffer(Landroid/media/MediaCodec$BufferInfo;)Z

    move-result v2

    if-eqz v2, :cond_3

    .line 300
    iget-wide v6, v0, Landroid/media/MediaCodec$BufferInfo;->presentationTimeUs:J

    iget-wide v8, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->stopPresentationTimeUs:J

    cmp-long v2, v6, v8

    if-gez v2, :cond_2

    .line 301
    iget v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->videoBuffers:I

    add-int/2addr v2, v5

    iput v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->videoBuffers:I

    .line 302
    iget-wide v6, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->videoBytes:J

    iget v2, v0, Landroid/media/MediaCodec$BufferInfo;->size:I

    int-to-long v8, v2

    add-long/2addr v6, v8

    iput-wide v6, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->videoBytes:J

    .line 303
    invoke-direct {p0, v5, v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->recordSampleStats(ZLandroid/media/MediaCodec$BufferInfo;)V

    .line 304
    iget-wide v6, v0, Landroid/media/MediaCodec$BufferInfo;->presentationTimeUs:J

    invoke-direct {p0, v6, v7}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->recordCodecLatency(J)V

    .line 305
    iget-object v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->videoCodec:Landroid/media/MediaCodec;

    invoke-virtual {v2, v3}, Landroid/media/MediaCodec;->getOutputBuffer(I)Ljava/nio/ByteBuffer;

    move-result-object v2

    invoke-direct {p0, v5, v2, v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->writeSample(ZLjava/nio/ByteBuffer;Landroid/media/MediaCodec$BufferInfo;)V

    goto :goto_1

    .line 307
    :cond_2
    iget v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->droppedVideoAfterStop:I

    add-int/2addr v2, v5

    iput v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->droppedVideoAfterStop:I

    .line 310
    :cond_3
    :goto_1
    iget v2, v0, Landroid/media/MediaCodec$BufferInfo;->flags:I

    and-int/lit8 v2, v2, 0x4

    if-eqz v2, :cond_4

    move v2, v5

    goto :goto_2

    :cond_4
    move v2, v1

    .line 311
    :goto_2
    iget-object v4, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->videoCodec:Landroid/media/MediaCodec;

    invoke-virtual {v4, v3, v1}, Landroid/media/MediaCodec;->releaseOutputBuffer(IZ)V
    :try_end_0
    .catch Ljava/lang/RuntimeException; {:try_start_0 .. :try_end_0} :catch_0

    goto :goto_0

    .line 315
    :goto_3
    iget-boolean v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->stopping:Z

    if-nez v1, :cond_5

    invoke-direct {p0, v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->reportError(Ljava/lang/Exception;)V

    goto :goto_4

    .line 316
    :cond_5
    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->diagnostics:Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;

    const-string v1, "video drain failed while stopping"

    invoke-virtual {p0, v1, v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;->error(Ljava/lang/String;Ljava/lang/Throwable;)V

    :cond_6
    :goto_4
    return-void
.end method

.method private static durationUs(JJ)J
    .locals 2

    const-wide/high16 v0, -0x8000000000000000L

    cmp-long v0, p0, v0

    if-eqz v0, :cond_1

    cmp-long v0, p2, p0

    if-gtz v0, :cond_0

    goto :goto_0

    :cond_0
    sub-long/2addr p2, p0

    return-wide p2

    :cond_1
    :goto_0
    const-wide/16 p0, 0x0

    return-wide p0
.end method

.method private static elapsedMs(J)J
    .locals 2

    .line 689
    invoke-static {}, Ljava/lang/System;->nanoTime()J

    move-result-wide v0

    sub-long/2addr v0, p0

    const-wide/32 p0, 0xf4240

    div-long/2addr v0, p0

    return-wide v0
.end method

.method private flushPendingAudioSamples(JZ)V
    .locals 7

    .line 489
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->pendingAudioSamples:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->isEmpty()Z

    move-result v0

    if-eqz v0, :cond_0

    goto :goto_2

    :cond_0
    const/4 v0, 0x0

    move v1, v0

    .line 491
    :goto_0
    iget-object v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->pendingAudioSamples:Ljava/util/ArrayList;

    invoke-virtual {v2}, Ljava/util/ArrayList;->size()I

    move-result v2

    if-ge v1, v2, :cond_2

    .line 492
    iget-object v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->pendingAudioSamples:Ljava/util/ArrayList;

    invoke-virtual {v2, v1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder$PendingAudioSample;

    iget-object v2, v2, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder$PendingAudioSample;->info:Landroid/media/MediaCodec$BufferInfo;

    iget-wide v2, v2, Landroid/media/MediaCodec$BufferInfo;->presentationTimeUs:J

    cmp-long v2, v2, p1

    if-ltz v2, :cond_1

    goto :goto_1

    :cond_1
    add-int/lit8 v1, v1, 0x1

    goto :goto_0

    :cond_2
    const/4 v1, -0x1

    :goto_1
    const/4 v2, 0x1

    if-gez v1, :cond_4

    if-nez p3, :cond_3

    :goto_2
    return-void

    .line 499
    :cond_3
    iget-object p3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->pendingAudioSamples:Ljava/util/ArrayList;

    invoke-virtual {p3}, Ljava/util/ArrayList;->size()I

    move-result p3

    add-int/lit8 v1, p3, -0x1

    goto :goto_3

    :cond_4
    if-lez v1, :cond_5

    .line 501
    iget-object p3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->pendingAudioSamples:Ljava/util/ArrayList;

    add-int/lit8 v3, v1, -0x1

    .line 502
    invoke-virtual {p3, v3}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object p3

    check-cast p3, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder$PendingAudioSample;

    iget-object p3, p3, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder$PendingAudioSample;->info:Landroid/media/MediaCodec$BufferInfo;

    iget-wide v3, p3, Landroid/media/MediaCodec$BufferInfo;->presentationTimeUs:J

    sub-long v3, p1, v3

    .line 503
    iget-object p3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->pendingAudioSamples:Ljava/util/ArrayList;

    invoke-virtual {p3, v1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object p3

    check-cast p3, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder$PendingAudioSample;

    iget-object p3, p3, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder$PendingAudioSample;->info:Landroid/media/MediaCodec$BufferInfo;

    iget-wide v5, p3, Landroid/media/MediaCodec$BufferInfo;->presentationTimeUs:J

    sub-long/2addr v5, p1

    cmp-long p3, v3, v5

    if-gtz p3, :cond_5

    add-int/lit8 v1, v1, -0x1

    :cond_5
    :goto_3
    if-ge v0, v1, :cond_6

    .line 508
    iget p3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->droppedAudioBuffers:I

    add-int/2addr p3, v2

    iput p3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->droppedAudioBuffers:I

    add-int/lit8 v0, v0, 0x1

    goto :goto_3

    .line 510
    :cond_6
    iget-object p3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->pendingAudioSamples:Ljava/util/ArrayList;

    invoke-virtual {p3, v1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object p3

    check-cast p3, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder$PendingAudioSample;

    .line 511
    iget-object v0, p3, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder$PendingAudioSample;->info:Landroid/media/MediaCodec$BufferInfo;

    iget-wide v3, v0, Landroid/media/MediaCodec$BufferInfo;->presentationTimeUs:J

    sub-long/2addr v3, p1

    iput-wide v3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->alignedAudioStartDeltaUs:J

    .line 512
    :goto_4
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->pendingAudioSamples:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v0

    .line 516
    iget-object v3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->pendingAudioSamples:Ljava/util/ArrayList;

    if-ge v1, v0, :cond_7

    .line 513
    invoke-virtual {v3, v1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder$PendingAudioSample;

    .line 514
    iget-object v3, v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder$PendingAudioSample;->data:Ljava/nio/ByteBuffer;

    iget-object v0, v0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder$PendingAudioSample;->info:Landroid/media/MediaCodec$BufferInfo;

    invoke-direct {p0, v3, v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->writeAudioSample(Ljava/nio/ByteBuffer;Landroid/media/MediaCodec$BufferInfo;)V

    add-int/lit8 v1, v1, 0x1

    goto :goto_4

    .line 516
    :cond_7
    invoke-virtual {v3}, Ljava/util/ArrayList;->clear()V

    .line 517
    iput-boolean v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->audioStartAligned:Z

    .line 518
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->diagnostics:Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;

    new-instance v1, Ljava/lang/StringBuilder;

    const-string v2, "A/V start aligned: videoPtsUs="

    invoke-direct {v1, v2}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v1, p1, p2}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    const-string p1, ", audioPtsUs="

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-object p1, p3, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder$PendingAudioSample;->info:Landroid/media/MediaCodec$BufferInfo;

    iget-wide p1, p1, Landroid/media/MediaCodec$BufferInfo;->presentationTimeUs:J

    invoke-virtual {v1, p1, p2}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    const-string p1, ", deltaUs="

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-wide p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->alignedAudioStartDeltaUs:J

    invoke-virtual {v1, p0, p1}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    invoke-virtual {v0, p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;->log(Ljava/lang/String;)V

    return-void
.end method

.method private static framesToDurationUs(J)J
    .locals 2

    const-wide/32 v0, 0xf4240

    mul-long/2addr p0, v0

    const-wide/32 v0, 0xbb80

    .line 705
    div-long/2addr p0, v0

    return-wide p0
.end method

.method private hasAudioVideoPts()Z
    .locals 4

    .line 650
    iget-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->firstVideoPtsUs:J

    const-wide/high16 v2, -0x8000000000000000L

    cmp-long v0, v0, v2

    if-eqz v0, :cond_0

    iget-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->lastVideoPtsUs:J

    cmp-long v0, v0, v2

    if-eqz v0, :cond_0

    iget-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->firstAudioPtsUs:J

    cmp-long v0, v0, v2

    if-eqz v0, :cond_0

    iget-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->lastAudioPtsUs:J

    cmp-long p0, v0, v2

    if-eqz p0, :cond_0

    const/4 p0, 0x1

    return p0

    :cond_0
    const/4 p0, 0x0

    return p0
.end method

.method private static isMediaBuffer(Landroid/media/MediaCodec$BufferInfo;)Z
    .locals 1

    .line 706
    iget v0, p0, Landroid/media/MediaCodec$BufferInfo;->size:I

    if-lez v0, :cond_0

    iget p0, p0, Landroid/media/MediaCodec$BufferInfo;->flags:I

    and-int/lit8 p0, p0, 0x2

    if-nez p0, :cond_0

    const/4 p0, 0x1

    return p0

    :cond_0
    const/4 p0, 0x0

    return p0
.end method

.method private join(Ljava/lang/Thread;)V
    .locals 3

    .line 715
    const-string v0, "encoder thread did not stop: "

    if-nez p1, :cond_0

    goto :goto_0

    :cond_0
    const-wide/16 v1, 0x1388

    .line 717
    :try_start_0
    invoke-virtual {p1, v1, v2}, Ljava/lang/Thread;->join(J)V

    .line 718
    invoke-virtual {p1}, Ljava/lang/Thread;->isAlive()Z

    move-result v1

    if-eqz v1, :cond_1

    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->diagnostics:Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1, v0}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {p1}, Ljava/lang/Thread;->getName()Ljava/lang/String;

    move-result-object p1

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p0, p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;->log(Ljava/lang/String;)V
    :try_end_0
    .catch Ljava/lang/InterruptedException; {:try_start_0 .. :try_end_0} :catch_0

    :cond_1
    :goto_0
    return-void

    .line 719
    :catch_0
    invoke-static {}, Ljava/lang/Thread;->currentThread()Ljava/lang/Thread;

    move-result-object p0

    invoke-virtual {p0}, Ljava/lang/Thread;->interrupt()V

    return-void
.end method

.method private logSummary()V
    .locals 14

    .line 615
    invoke-direct {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->hasAudioVideoPts()Z

    move-result v0

    const-wide/high16 v1, -0x8000000000000000L

    if-eqz v0, :cond_0

    iget-wide v3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->firstAudioPtsUs:J

    iget-wide v5, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->firstVideoPtsUs:J

    sub-long/2addr v3, v5

    goto :goto_0

    :cond_0
    move-wide v3, v1

    .line 616
    :goto_0
    invoke-direct {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->hasAudioVideoPts()Z

    move-result v0

    if-eqz v0, :cond_1

    iget-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->lastAudioPtsUs:J

    iget-wide v5, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->lastVideoPtsUs:J

    sub-long v1, v0, v5

    .line 617
    :cond_1
    iget-wide v5, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->firstVideoPtsUs:J

    iget-wide v7, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->lastVideoPtsUs:J

    invoke-static {v5, v6, v7, v8}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->durationUs(JJ)J

    move-result-wide v5

    .line 618
    iget-wide v7, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->firstAudioPtsUs:J

    iget-wide v9, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->lastAudioPtsUs:J

    invoke-static {v7, v8, v9, v10}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->durationUs(JJ)J

    move-result-wide v7

    .line 619
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->diagnostics:Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;

    new-instance v9, Ljava/lang/StringBuilder;

    const-string v10, "codec summary: videoBuffers="

    invoke-direct {v9, v10}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    iget v10, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->videoBuffers:I

    invoke-virtual {v9, v10}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string v10, ", videoBytes="

    invoke-virtual {v9, v10}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-wide v10, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->videoBytes:J

    invoke-virtual {v9, v10, v11}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    const-string v10, ", videoPts="

    invoke-virtual {v9, v10}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-wide v10, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->firstVideoPtsUs:J

    iget-wide v12, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->lastVideoPtsUs:J

    .line 621
    invoke-static {v10, v11, v12, v13}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->ptsRange(JJ)Ljava/lang/String;

    move-result-object v10

    invoke-virtual {v9, v10}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v10, ", actualVideoBitrate="

    invoke-virtual {v9, v10}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-wide v10, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->videoBytes:J

    .line 622
    invoke-static {v10, v11, v5, v6}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->bitrate(JJ)Ljava/lang/String;

    move-result-object v5

    invoke-virtual {v9, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v5, ", keyframes="

    invoke-virtual {v9, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget v5, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->videoKeyframes:I

    invoke-virtual {v9, v5}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string v5, ", maxKeyframeIntervalUs="

    invoke-virtual {v9, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-wide v5, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->maximumKeyframeIntervalUs:J

    invoke-virtual {v9, v5, v6}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    const-string v5, ", nonMonotonicVideoPts="

    invoke-virtual {v9, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget v5, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->nonMonotonicVideoPts:I

    invoke-virtual {v9, v5}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string v5, ", audioBuffers="

    invoke-virtual {v9, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget v5, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->audioBuffers:I

    invoke-virtual {v9, v5}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string v5, ", audioBytes="

    invoke-virtual {v9, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-wide v5, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->audioBytes:J

    invoke-virtual {v9, v5, v6}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    const-string v5, ", audioPts="

    invoke-virtual {v9, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-wide v5, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->firstAudioPtsUs:J

    iget-wide v10, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->lastAudioPtsUs:J

    .line 628
    invoke-static {v5, v6, v10, v11}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->ptsRange(JJ)Ljava/lang/String;

    move-result-object v5

    invoke-virtual {v9, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v5, ", actualAudioBitrate="

    invoke-virtual {v9, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-wide v5, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->audioBytes:J

    .line 629
    invoke-static {v5, v6, v7, v8}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->bitrate(JJ)Ljava/lang/String;

    move-result-object v5

    invoke-virtual {v9, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v5, ", nonMonotonicAudioPts="

    invoke-virtual {v9, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget v5, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->nonMonotonicAudioPts:I

    invoke-virtual {v9, v5}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string v5, ", avStartDeltaUs="

    invoke-virtual {v9, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 631
    invoke-static {v3, v4}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->optionalValue(J)Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v9, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v3, ", avEndDeltaUs="

    invoke-virtual {v9, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 632
    invoke-static {v1, v2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->optionalValue(J)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v9, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v1, ", audioReadErrors="

    invoke-virtual {v9, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->audioReadErrors:I

    invoke-virtual {v9, v1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string v1, ", emptyAudioReads="

    invoke-virtual {v9, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->emptyAudioReads:I

    invoke-virtual {v9, v1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string v1, ", droppedAudioBuffers="

    invoke-virtual {v9, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->droppedAudioBuffers:I

    invoke-virtual {v9, v1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string v1, ", droppedAudioAfterStop="

    invoke-virtual {v9, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->droppedAudioAfterStop:I

    invoke-virtual {v9, v1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string v1, ", droppedVideoAfterStop="

    invoke-virtual {v9, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->droppedVideoAfterStop:I

    invoke-virtual {v9, v1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string v1, ", stopPresentationTimeUs="

    invoke-virtual {v9, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 639
    iget-wide v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->stopPresentationTimeUs:J

    const-wide v3, 0x7fffffffffffffffL

    cmp-long v1, v1, v3

    const-string v2, "n/a"

    if-nez v1, :cond_2

    move-object v1, v2

    goto :goto_1

    .line 640
    :cond_2
    iget-wide v3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->stopPresentationTimeUs:J

    invoke-static {v3, v4}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v1

    :goto_1
    invoke-virtual {v9, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    const-string v1, ", alignedAudioStartDeltaUs="

    invoke-virtual {v9, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-wide v3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->alignedAudioStartDeltaUs:J

    .line 642
    invoke-static {v3, v4}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->optionalValue(J)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v9, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v1, ", codecLatencyAvgMs="

    invoke-virtual {v9, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 644
    iget v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->latencySamples:I

    const v3, 0x49742400    # 1000000.0f

    if-nez v1, :cond_3

    goto :goto_2

    .line 645
    :cond_3
    iget-wide v4, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->latencyTotalNs:J

    long-to-float v2, v4

    int-to-float v1, v1

    div-float/2addr v2, v1

    div-float/2addr v2, v3

    invoke-static {v2}, Ljava/lang/Float;->valueOf(F)Ljava/lang/Float;

    move-result-object v2

    :goto_2
    invoke-virtual {v9, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    const-string v1, ", codecLatencyMaxMs="

    invoke-virtual {v9, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-wide v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->latencyMaxNs:J

    long-to-float p0, v1

    div-float/2addr p0, v3

    invoke-virtual {v9, p0}, Ljava/lang/StringBuilder;->append(F)Ljava/lang/StringBuilder;

    invoke-virtual {v9}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    .line 619
    invoke-virtual {v0, p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;->log(Ljava/lang/String;)V

    return-void
.end method

.method private notifyAudioReady()V
    .locals 2

    .line 404
    monitor-enter p0

    .line 405
    :try_start_0
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->audioReadyListener:Ljava/lang/Runnable;

    if-eqz v0, :cond_1

    iget-boolean v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->stopping:Z

    if-eqz v0, :cond_0

    goto :goto_0

    .line 406
    :cond_0
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->audioReadyListener:Ljava/lang/Runnable;

    const/4 v1, 0x0

    .line 407
    iput-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->audioReadyListener:Ljava/lang/Runnable;

    .line 408
    monitor-exit p0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 409
    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->diagnostics:Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;

    const-string v1, "audio capture ready; waiting for common A/V start frame"

    invoke-virtual {p0, v1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;->log(Ljava/lang/String;)V

    .line 410
    invoke-interface {v0}, Ljava/lang/Runnable;->run()V

    return-void

    :catchall_0
    move-exception v0

    goto :goto_1

    .line 405
    :cond_1
    :goto_0
    :try_start_1
    monitor-exit p0

    return-void

    .line 408
    :goto_1
    monitor-exit p0
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    throw v0
.end method

.method private notifyVideoReady()V
    .locals 2

    .line 415
    monitor-enter p0

    .line 416
    :try_start_0
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->videoReadyListener:Ljava/lang/Runnable;

    if-eqz v0, :cond_1

    iget-boolean v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->stopping:Z

    if-eqz v0, :cond_0

    goto :goto_0

    .line 417
    :cond_0
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->videoReadyListener:Ljava/lang/Runnable;

    const/4 v1, 0x0

    .line 418
    iput-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->videoReadyListener:Ljava/lang/Runnable;

    .line 419
    monitor-exit p0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 420
    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->diagnostics:Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;

    const-string v1, "first synchronized video frame submitted"

    invoke-virtual {p0, v1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;->log(Ljava/lang/String;)V

    .line 421
    invoke-interface {v0}, Ljava/lang/Runnable;->run()V

    return-void

    :catchall_0
    move-exception v0

    goto :goto_1

    .line 416
    :cond_1
    :goto_0
    :try_start_1
    monitor-exit p0

    return-void

    .line 419
    :goto_1
    monitor-exit p0
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    throw v0
.end method

.method private static optionalValue(J)Ljava/lang/String;
    .locals 2

    const-wide/high16 v0, -0x8000000000000000L

    cmp-long v0, p0, v0

    if-nez v0, :cond_0

    .line 663
    const-string p0, "n/a"

    return-object p0

    :cond_0
    invoke-static {p0, p1}, Ljava/lang/String;->valueOf(J)Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method private static ptsRange(JJ)Ljava/lang/String;
    .locals 2

    const-wide/high16 v0, -0x8000000000000000L

    cmp-long v0, p0, v0

    if-nez v0, :cond_0

    .line 658
    const-string p0, "n/a"

    return-object p0

    :cond_0
    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v0, p0, p1}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    const-string p0, ".."

    invoke-virtual {v0, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, p2, p3}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method private recordCodecLatency(J)V
    .locals 12

    .line 569
    iget-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->submittedSequence:J

    const-wide/16 v2, 0x100

    sub-long v4, v0, v2

    const-wide/16 v6, 0x0

    .line 570
    invoke-static {v6, v7, v4, v5}, Ljava/lang/Math;->max(JJ)J

    move-result-wide v4

    const-wide/16 v6, 0x1

    :goto_0
    sub-long/2addr v0, v6

    cmp-long v8, v0, v4

    if-ltz v8, :cond_1

    .line 572
    rem-long v8, v0, v2

    long-to-int v8, v8

    .line 573
    iget-object v9, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->submittedPtsUs:[J

    aget-wide v10, v9, v8

    cmp-long v9, v10, p1

    if-eqz v9, :cond_0

    goto :goto_0

    .line 574
    :cond_0
    invoke-static {}, Ljava/lang/System;->nanoTime()J

    move-result-wide p1

    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->submittedTimeNs:[J

    aget-wide v1, v0, v8

    sub-long/2addr p1, v1

    .line 575
    iget v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->latencySamples:I

    add-int/lit8 v0, v0, 0x1

    iput v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->latencySamples:I

    .line 576
    iget-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->latencyTotalNs:J

    add-long/2addr v0, p1

    iput-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->latencyTotalNs:J

    .line 577
    iget-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->latencyMaxNs:J

    invoke-static {v0, v1, p1, p2}, Ljava/lang/Math;->max(JJ)J

    move-result-wide p1

    iput-wide p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->latencyMaxNs:J

    .line 578
    iget p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->latencySamples:I

    rem-int/lit8 p1, p1, 0x1e

    if-nez p1, :cond_1

    .line 579
    iget-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->diagnostics:Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;

    new-instance p2, Ljava/lang/StringBuilder;

    const-string v0, "codec latency: average="

    invoke-direct {p2, v0}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    iget-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->latencyTotalNs:J

    long-to-float v0, v0

    iget v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->latencySamples:I

    int-to-float v1, v1

    div-float/2addr v0, v1

    const v1, 0x49742400    # 1000000.0f

    div-float/2addr v0, v1

    invoke-virtual {p2, v0}, Ljava/lang/StringBuilder;->append(F)Ljava/lang/StringBuilder;

    const-string v0, " ms, max="

    invoke-virtual {p2, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-wide v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->latencyMaxNs:J

    long-to-float p0, v2

    div-float/2addr p0, v1

    invoke-virtual {p2, p0}, Ljava/lang/StringBuilder;->append(F)Ljava/lang/StringBuilder;

    const-string p0, " ms"

    invoke-virtual {p2, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {p2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    invoke-virtual {p1, p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;->log(Ljava/lang/String;)V

    :cond_1
    return-void
.end method

.method private recordSampleStats(ZLandroid/media/MediaCodec$BufferInfo;)V
    .locals 6

    .line 588
    iget-wide v0, p2, Landroid/media/MediaCodec$BufferInfo;->presentationTimeUs:J

    const-wide/high16 v2, -0x8000000000000000L

    if-eqz p1, :cond_4

    .line 590
    iget-wide v4, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->firstVideoPtsUs:J

    cmp-long p1, v4, v2

    if-nez p1, :cond_0

    iput-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->firstVideoPtsUs:J

    .line 591
    :cond_0
    iget-wide v4, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->lastVideoPtsUs:J

    cmp-long p1, v4, v2

    if-eqz p1, :cond_1

    cmp-long p1, v0, v4

    if-gez p1, :cond_1

    .line 592
    iget p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->nonMonotonicVideoPts:I

    add-int/lit8 p1, p1, 0x1

    iput p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->nonMonotonicVideoPts:I

    .line 594
    :cond_1
    invoke-static {v4, v5, v0, v1}, Ljava/lang/Math;->max(JJ)J

    move-result-wide v4

    iput-wide v4, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->lastVideoPtsUs:J

    .line 595
    iget p1, p2, Landroid/media/MediaCodec$BufferInfo;->flags:I

    and-int/lit8 p1, p1, 0x1

    if-eqz p1, :cond_3

    .line 596
    iget p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->videoKeyframes:I

    add-int/lit8 p1, p1, 0x1

    iput p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->videoKeyframes:I

    .line 597
    iget-wide p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->lastKeyframePtsUs:J

    cmp-long v2, p1, v2

    if-eqz v2, :cond_2

    .line 598
    iget-wide v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->maximumKeyframeIntervalUs:J

    sub-long p1, v0, p1

    invoke-static {v2, v3, p1, p2}, Ljava/lang/Math;->max(JJ)J

    move-result-wide p1

    iput-wide p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->maximumKeyframeIntervalUs:J

    .line 603
    :cond_2
    iput-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->lastKeyframePtsUs:J

    :cond_3
    return-void

    .line 606
    :cond_4
    iget-wide p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->firstAudioPtsUs:J

    cmp-long p1, p1, v2

    if-nez p1, :cond_5

    iput-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->firstAudioPtsUs:J

    .line 607
    :cond_5
    iget-wide p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->lastAudioPtsUs:J

    cmp-long v2, p1, v2

    if-eqz v2, :cond_6

    cmp-long v2, v0, p1

    if-gez v2, :cond_6

    .line 608
    iget v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->nonMonotonicAudioPts:I

    add-int/lit8 v2, v2, 0x1

    iput v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->nonMonotonicAudioPts:I

    .line 610
    :cond_6
    invoke-static {p1, p2, v0, v1}, Ljava/lang/Math;->max(JJ)J

    move-result-wide p1

    iput-wide p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->lastAudioPtsUs:J

    return-void
.end method

.method private release()V
    .locals 2

    const/4 v0, 0x0

    .line 693
    iput-boolean v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->prepared:Z

    .line 694
    iput-boolean v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->started:Z

    const/4 v0, 0x0

    .line 695
    iput-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->audioReadyListener:Ljava/lang/Runnable;

    .line 696
    iput-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->videoReadyListener:Ljava/lang/Runnable;

    .line 697
    iget-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->audioRecord:Landroid/media/AudioRecord;

    if-eqz v1, :cond_0

    invoke-virtual {v1}, Landroid/media/AudioRecord;->release()V

    iput-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->audioRecord:Landroid/media/AudioRecord;

    .line 698
    :cond_0
    iget-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->videoInputSurface:Landroid/view/Surface;

    if-eqz v1, :cond_1

    invoke-virtual {v1}, Landroid/view/Surface;->release()V

    iput-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->videoInputSurface:Landroid/view/Surface;

    .line 699
    :cond_1
    iget-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->videoCodec:Landroid/media/MediaCodec;

    invoke-static {v1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->releaseCodec(Landroid/media/MediaCodec;)V

    .line 700
    iget-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->audioCodec:Landroid/media/MediaCodec;

    invoke-static {v1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->releaseCodec(Landroid/media/MediaCodec;)V

    .line 701
    iput-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->audioCodec:Landroid/media/MediaCodec;

    iput-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->videoCodec:Landroid/media/MediaCodec;

    .line 702
    iput-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->audioThread:Ljava/lang/Thread;

    iput-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->videoDrainThread:Ljava/lang/Thread;

    return-void
.end method

.method private static releaseCodec(Landroid/media/MediaCodec;)V
    .locals 0

    if-nez p0, :cond_0

    return-void

    .line 710
    :cond_0
    :try_start_0
    invoke-virtual {p0}, Landroid/media/MediaCodec;->stop()V
    :try_end_0
    .catch Ljava/lang/IllegalStateException; {:try_start_0 .. :try_end_0} :catch_0

    .line 711
    :catch_0
    invoke-virtual {p0}, Landroid/media/MediaCodec;->release()V

    return-void
.end method

.method private reportError(Ljava/lang/Exception;)V
    .locals 3

    .line 564
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->diagnostics:Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;

    new-instance v1, Ljava/lang/StringBuilder;

    const-string v2, "codec error"

    invoke-direct {v1, v2}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-static {p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->codecDiagnostic(Ljava/lang/Exception;)Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1, p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;->error(Ljava/lang/String;Ljava/lang/Throwable;)V

    .line 565
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->errorReported:Ljava/util/concurrent/atomic/AtomicBoolean;

    const/4 v1, 0x0

    const/4 v2, 0x1

    invoke-virtual {v0, v1, v2}, Ljava/util/concurrent/atomic/AtomicBoolean;->compareAndSet(ZZ)Z

    move-result v0

    if-eqz v0, :cond_0

    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->errorListener:Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder$ErrorListener;

    invoke-interface {p0, p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder$ErrorListener;->onError(Ljava/lang/Exception;)V

    :cond_0
    return-void
.end method

.method private resetStats()V
    .locals 5

    const/4 v0, 0x0

    .line 216
    iput-boolean v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->stopping:Z

    .line 217
    iput-boolean v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->stopFinalizationStarted:Z

    const-wide v1, 0x7fffffffffffffffL

    .line 218
    iput-wide v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->stopPresentationTimeUs:J

    const-wide/16 v1, 0x0

    .line 219
    iput-wide v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->timeOriginNs:J

    .line 220
    iput-wide v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->audioBytes:J

    iput-wide v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->videoBytes:J

    .line 221
    iput v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->audioBuffers:I

    iput v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->videoBuffers:I

    .line 222
    iput-wide v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->submittedSequence:J

    .line 223
    iput v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->latencySamples:I

    .line 224
    iput-wide v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->latencyMaxNs:J

    iput-wide v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->latencyTotalNs:J

    const-wide/high16 v3, -0x8000000000000000L

    .line 225
    iput-wide v3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->lastVideoPtsUs:J

    iput-wide v3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->firstVideoPtsUs:J

    .line 226
    iput-wide v3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->lastAudioPtsUs:J

    iput-wide v3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->firstAudioPtsUs:J

    .line 227
    iput-wide v3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->lastKeyframePtsUs:J

    .line 228
    iput-wide v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->maximumKeyframeIntervalUs:J

    .line 229
    iput v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->videoKeyframes:I

    .line 230
    iput v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->nonMonotonicAudioPts:I

    iput v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->nonMonotonicVideoPts:I

    .line 231
    iput v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->emptyAudioReads:I

    iput v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->audioReadErrors:I

    .line 232
    iput v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->droppedAudioBuffers:I

    .line 233
    iput v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->droppedAudioAfterStop:I

    .line 234
    iput v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->droppedVideoAfterStop:I

    .line 235
    iput-boolean v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->audioStartAligned:Z

    .line 236
    iput-wide v3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->alignedAudioStartDeltaUs:J

    const/4 v0, 0x0

    .line 237
    iput-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->audioReadyListener:Ljava/lang/Runnable;

    .line 238
    iput-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->videoReadyListener:Ljava/lang/Runnable;

    .line 239
    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->pendingAudioSamples:Ljava/util/ArrayList;

    invoke-virtual {p0}, Ljava/util/ArrayList;->clear()V

    return-void
.end method

.method private resolveAudioBasePtsUs(I)J
    .locals 11

    .line 427
    invoke-static {}, Landroid/os/SystemClock;->elapsedRealtimeNanos()J

    move-result-wide v0

    iget-wide v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->timeOriginNs:J

    sub-long/2addr v0, v2

    const-wide/16 v2, 0x3e8

    div-long/2addr v0, v2

    int-to-long v4, p1

    .line 428
    invoke-static {v4, v5}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->framesToDurationUs(J)J

    move-result-wide v4

    sub-long/2addr v0, v4

    const-wide/16 v4, 0x0

    .line 425
    invoke-static {v4, v5, v0, v1}, Ljava/lang/Math;->max(JJ)J

    move-result-wide v0

    .line 430
    iget-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->audioRecord:Landroid/media/AudioRecord;

    iget-object v6, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->audioTimestamp:Landroid/media/AudioTimestamp;

    const/4 v7, 0x1

    .line 431
    invoke-virtual {p1, v6, v7}, Landroid/media/AudioRecord;->getTimestamp(Landroid/media/AudioTimestamp;I)I

    move-result p1

    if-nez p1, :cond_0

    .line 435
    iget-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->audioTimestamp:Landroid/media/AudioTimestamp;

    iget-wide v6, p1, Landroid/media/AudioTimestamp;->nanoTime:J

    iget-wide v8, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->timeOriginNs:J

    sub-long/2addr v6, v8

    div-long/2addr v6, v2

    iget-wide v8, p1, Landroid/media/AudioTimestamp;->framePosition:J

    .line 438
    invoke-static {v8, v9}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->framesToDurationUs(J)J

    move-result-wide v8

    sub-long/2addr v6, v8

    .line 435
    invoke-static {v4, v5, v6, v7}, Ljava/lang/Math;->max(JJ)J

    move-result-wide v4

    .line 440
    iget-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->diagnostics:Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;

    new-instance v6, Ljava/lang/StringBuilder;

    const-string v7, "audio timestamp alignment: framePosition="

    invoke-direct {v6, v7}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    iget-object v7, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->audioTimestamp:Landroid/media/AudioTimestamp;

    iget-wide v7, v7, Landroid/media/AudioTimestamp;->framePosition:J

    invoke-virtual {v6, v7, v8}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    const-string v7, ", timestampDeltaUs="

    invoke-virtual {v6, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-object v7, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->audioTimestamp:Landroid/media/AudioTimestamp;

    iget-wide v7, v7, Landroid/media/AudioTimestamp;->nanoTime:J

    iget-wide v9, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->timeOriginNs:J

    sub-long/2addr v7, v9

    div-long/2addr v7, v2

    invoke-virtual {v6, v7, v8}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    const-string p0, ", timestampBaseUs="

    invoke-virtual {v6, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v6, v4, v5}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    const-string p0, ", readCompletionBaseUs="

    invoke-virtual {v6, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v6, v0, v1}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    invoke-virtual {v6}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    invoke-virtual {p1, p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;->log(Ljava/lang/String;)V

    return-wide v4

    .line 448
    :cond_0
    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->diagnostics:Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;

    new-instance p1, Ljava/lang/StringBuilder;

    const-string v2, "audio timestamp unavailable: readCompletionBaseUs="

    invoke-direct {p1, v2}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {p1, v0, v1}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    invoke-virtual {p1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p0, p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;->log(Ljava/lang/String;)V

    return-wide v0
.end method

.method private setTrackFormat(ZLandroid/media/MediaFormat;)V
    .locals 3

    .line 532
    const-string v0, "prepend-sps-pps-to-idr-frames"

    if-eqz p1, :cond_2

    .line 533
    :try_start_0
    invoke-virtual {p2, v0}, Landroid/media/MediaFormat;->containsKey(Ljava/lang/String;)Z

    move-result v1

    if-eqz v1, :cond_2

    .line 534
    invoke-virtual {p2, v0}, Landroid/media/MediaFormat;->getInteger(Ljava/lang/String;)I

    move-result v0

    const/4 v1, 0x1

    if-ne v0, v1, :cond_2

    .line 535
    const-string v0, "csd-0"

    invoke-virtual {p2, v0}, Landroid/media/MediaFormat;->getByteBuffer(Ljava/lang/String;)Ljava/nio/ByteBuffer;

    move-result-object v0

    .line 536
    const-string v1, "csd-1"

    invoke-virtual {p2, v1}, Landroid/media/MediaFormat;->getByteBuffer(Ljava/lang/String;)Ljava/nio/ByteBuffer;

    move-result-object v1

    const/4 v2, 0x0

    if-nez v0, :cond_0

    move v0, v2

    goto :goto_0

    .line 537
    :cond_0
    invoke-virtual {v0}, Ljava/nio/Buffer;->remaining()I

    move-result v0

    :goto_0
    if-nez v1, :cond_1

    goto :goto_1

    .line 538
    :cond_1
    invoke-virtual {v1}, Ljava/nio/Buffer;->remaining()I

    move-result v2

    :goto_1
    add-int/2addr v0, v2

    iput v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->prependHeaderSize:I

    .line 540
    :cond_2
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->writer:Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;

    invoke-virtual {v0, p1, p2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->setTrackFormat(ZLandroid/media/MediaFormat;)V

    .line 541
    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->diagnostics:Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;

    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    if-eqz p1, :cond_3

    const-string p1, "video"

    goto :goto_2

    :cond_3
    const-string p1, "audio"

    :goto_2
    invoke-virtual {v0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p1, " output format: "

    invoke-virtual {v0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    invoke-virtual {p0, p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;->log(Ljava/lang/String;)V
    :try_end_0
    .catch Ljava/io/IOException; {:try_start_0 .. :try_end_0} :catch_0

    return-void

    :catch_0
    move-exception p0

    .line 543
    invoke-static {p0}, Lcom/google/protobuf/UnsafeUtil$$ExternalSyntheticBUOutline0;->m(Ljava/lang/Throwable;)V

    return-void
.end method

.method private writeAudioSample(Ljava/nio/ByteBuffer;Landroid/media/MediaCodec$BufferInfo;)V
    .locals 4

    .line 524
    iget v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->audioBuffers:I

    add-int/lit8 v0, v0, 0x1

    iput v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->audioBuffers:I

    .line 525
    iget-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->audioBytes:J

    iget v2, p2, Landroid/media/MediaCodec$BufferInfo;->size:I

    int-to-long v2, v2

    add-long/2addr v0, v2

    iput-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->audioBytes:J

    const/4 v0, 0x0

    .line 526
    invoke-direct {p0, v0, p2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->recordSampleStats(ZLandroid/media/MediaCodec$BufferInfo;)V

    .line 527
    invoke-direct {p0, v0, p1, p2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->writeSample(ZLjava/nio/ByteBuffer;Landroid/media/MediaCodec$BufferInfo;)V

    return-void
.end method

.method private writeOrBufferAudioSample(Ljava/nio/ByteBuffer;Landroid/media/MediaCodec$BufferInfo;)V
    .locals 4

    if-nez p1, :cond_0

    goto :goto_0

    .line 455
    :cond_0
    iget-wide v0, p2, Landroid/media/MediaCodec$BufferInfo;->presentationTimeUs:J

    iget-wide v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->stopPresentationTimeUs:J

    cmp-long v0, v0, v2

    if-ltz v0, :cond_1

    .line 456
    iget p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->droppedAudioBuffers:I

    add-int/lit8 p1, p1, 0x1

    iput p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->droppedAudioBuffers:I

    .line 457
    iget p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->droppedAudioAfterStop:I

    add-int/lit8 p1, p1, 0x1

    iput p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->droppedAudioAfterStop:I

    return-void

    .line 460
    :cond_1
    iget-boolean v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->audioStartAligned:Z

    if-eqz v0, :cond_2

    .line 461
    invoke-direct {p0, p1, p2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->writeAudioSample(Ljava/nio/ByteBuffer;Landroid/media/MediaCodec$BufferInfo;)V

    return-void

    .line 464
    :cond_2
    invoke-direct {p0, p1, p2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->bufferAudioSample(Ljava/nio/ByteBuffer;Landroid/media/MediaCodec$BufferInfo;)V

    .line 465
    iget-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->firstVideoPtsUs:J

    const-wide/high16 v2, -0x8000000000000000L

    cmp-long p1, v0, v2

    if-eqz p1, :cond_3

    .line 466
    iget-wide p1, p2, Landroid/media/MediaCodec$BufferInfo;->presentationTimeUs:J

    cmp-long p1, p1, v0

    if-ltz p1, :cond_3

    const/4 p1, 0x0

    .line 467
    invoke-direct {p0, v0, v1, p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->flushPendingAudioSamples(JZ)V

    :cond_3
    :goto_0
    return-void
.end method

.method private writeSample(ZLjava/nio/ByteBuffer;Landroid/media/MediaCodec$BufferInfo;)V
    .locals 6

    if-nez p2, :cond_0

    return-void

    :cond_0
    if-eqz p1, :cond_1

    .line 550
    :try_start_0
    iget v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->prependHeaderSize:I

    if-lez v0, :cond_1

    iget v1, p3, Landroid/media/MediaCodec$BufferInfo;->flags:I

    and-int/lit8 v1, v1, 0x1

    if-eqz v1, :cond_1

    iget v1, p3, Landroid/media/MediaCodec$BufferInfo;->size:I

    if-le v1, v0, :cond_1

    .line 554
    iget v2, p3, Landroid/media/MediaCodec$BufferInfo;->offset:I

    add-int/2addr v2, v0

    iput v2, p3, Landroid/media/MediaCodec$BufferInfo;->offset:I

    sub-int/2addr v1, v0

    .line 555
    iput v1, p3, Landroid/media/MediaCodec$BufferInfo;->size:I

    .line 557
    :cond_1
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->writer:Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;

    iget-wide v4, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->timelineOffsetUs:J

    move v1, p1

    move-object v2, p2

    move-object v3, p3

    invoke-virtual/range {v0 .. v5}, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;->writeSample(ZLjava/nio/ByteBuffer;Landroid/media/MediaCodec$BufferInfo;J)V
    :try_end_0
    .catch Ljava/io/IOException; {:try_start_0 .. :try_end_0} :catch_0

    return-void

    :catch_0
    move-exception v0

    move-object p0, v0

    .line 559
    invoke-static {p0}, Lcom/google/protobuf/UnsafeUtil$$ExternalSyntheticBUOutline0;->m(Ljava/lang/Throwable;)V

    return-void
.end method


# virtual methods
.method public getTimeOriginNs()J
    .locals 2

    .line 158
    iget-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->timeOriginNs:J

    return-wide v0
.end method

.method public declared-synchronized isStarted()Z
    .locals 1

    monitor-enter p0

    .line 156
    :try_start_0
    iget-boolean v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->started:Z
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    monitor-exit p0

    return v0

    :catchall_0
    move-exception v0

    :try_start_1
    monitor-exit p0
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    throw v0
.end method

.method public onFrameSubmitted(JJ)V
    .locals 4

    .line 162
    iget-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->submittedSequence:J

    const-wide/16 v2, 0x100

    .line 163
    rem-long v2, v0, v2

    long-to-int v2, v2

    .line 164
    iget-object v3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->submittedPtsUs:[J

    aput-wide p1, v3, v2

    .line 165
    iget-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->submittedTimeNs:[J

    aput-wide p3, p1, v2

    const-wide/16 p1, 0x1

    add-long/2addr v0, p1

    .line 166
    iput-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->submittedSequence:J

    .line 167
    invoke-direct {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->notifyVideoReady()V

    return-void
.end method

.method public declared-synchronized prepare()Landroid/view/Surface;
    .locals 5

    const-string v0, "codecs prepared: video="

    monitor-enter p0

    .line 109
    :try_start_0
    iget-boolean v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->prepared:Z

    if-eqz v1, :cond_0

    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->videoInputSurface:Landroid/view/Surface;
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    monitor-exit p0

    return-object v0

    :catchall_0
    move-exception v0

    goto :goto_1

    .line 110
    :cond_0
    :try_start_1
    invoke-direct {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->resetStats()V

    .line 111
    invoke-static {}, Ljava/lang/System;->nanoTime()J

    move-result-wide v1
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    .line 113
    :try_start_2
    invoke-direct {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->createVideoEncoder()V

    .line 114
    invoke-direct {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->createAudioEncoder()V

    .line 115
    iget-object v3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->videoCodec:Landroid/media/MediaCodec;

    invoke-virtual {v3}, Landroid/media/MediaCodec;->start()V

    const/4 v3, 0x1

    .line 116
    iput-boolean v3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->prepared:Z

    .line 117
    iget-object v3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->diagnostics:Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;

    new-instance v4, Ljava/lang/StringBuilder;

    invoke-direct {v4, v0}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->videoCodec:Landroid/media/MediaCodec;

    invoke-virtual {v0}, Landroid/media/MediaCodec;->getName()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v4, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v0, ", audio="

    invoke-virtual {v4, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->audioCodec:Landroid/media/MediaCodec;

    .line 118
    invoke-virtual {v0}, Landroid/media/MediaCodec;->getName()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v4, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v0, ", elapsedMs="

    invoke-virtual {v4, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 119
    invoke-static {v1, v2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->elapsedMs(J)J

    move-result-wide v0

    invoke-virtual {v4, v0, v1}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    invoke-virtual {v4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    .line 117
    invoke-virtual {v3, v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;->log(Ljava/lang/String;)V

    .line 120
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->videoInputSurface:Landroid/view/Surface;
    :try_end_2
    .catch Ljava/io/IOException; {:try_start_2 .. :try_end_2} :catch_1
    .catch Ljava/lang/RuntimeException; {:try_start_2 .. :try_end_2} :catch_0
    .catchall {:try_start_2 .. :try_end_2} :catchall_0

    monitor-exit p0

    return-object v0

    :catch_0
    move-exception v0

    goto :goto_0

    :catch_1
    move-exception v0

    .line 122
    :goto_0
    :try_start_3
    invoke-direct {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->release()V

    .line 123
    throw v0

    :goto_1
    monitor-exit p0
    :try_end_3
    .catchall {:try_start_3 .. :try_end_3} :catchall_0

    throw v0
.end method

.method public requestStop()J
    .locals 5

    .line 194
    monitor-enter p0

    .line 195
    :try_start_0
    iget-boolean v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->prepared:Z

    if-eqz v0, :cond_3

    iget-boolean v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->started:Z

    if-nez v0, :cond_0

    goto :goto_1

    .line 196
    :cond_0
    iget-boolean v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->stopping:Z

    if-eqz v0, :cond_1

    iget-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->stopPresentationTimeUs:J

    monitor-exit p0

    return-wide v0

    :catchall_0
    move-exception v0

    goto :goto_2

    :cond_1
    const/4 v0, 0x1

    .line 197
    iput-boolean v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->stopping:Z

    .line 199
    invoke-static {}, Landroid/os/SystemClock;->elapsedRealtimeNanos()J

    move-result-wide v0

    iget-wide v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->timeOriginNs:J

    sub-long/2addr v0, v2

    const-wide/16 v2, 0x3e8

    div-long/2addr v0, v2

    const-wide/16 v2, 0x0

    .line 198
    invoke-static {v2, v3, v0, v1}, Ljava/lang/Math;->max(JJ)J

    move-result-wide v0

    .line 200
    iput-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->stopPresentationTimeUs:J

    .line 201
    iget-object v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->audioRecord:Landroid/media/AudioRecord;

    .line 202
    monitor-exit p0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    if-eqz v2, :cond_2

    .line 205
    :try_start_1
    invoke-virtual {v2}, Landroid/media/AudioRecord;->getRecordingState()I

    move-result v3

    const/4 v4, 0x3

    if-ne v3, v4, :cond_2

    .line 206
    invoke-virtual {v2}, Landroid/media/AudioRecord;->stop()V
    :try_end_1
    .catch Ljava/lang/IllegalStateException; {:try_start_1 .. :try_end_1} :catch_0

    goto :goto_0

    :catch_0
    move-exception v2

    .line 209
    iget-object v3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->diagnostics:Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;

    const-string v4, "AudioRecord stop failed"

    invoke-virtual {v3, v4, v2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;->error(Ljava/lang/String;Ljava/lang/Throwable;)V

    .line 211
    :cond_2
    :goto_0
    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->diagnostics:Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;

    new-instance v2, Ljava/lang/StringBuilder;

    const-string v3, "recording stop boundary: presentationTimeUs="

    invoke-direct {v2, v3}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v2, v0, v1}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {p0, v2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;->log(Ljava/lang/String;)V

    return-wide v0

    :cond_3
    :goto_1
    const-wide v0, 0x7fffffffffffffffL

    .line 195
    :try_start_2
    monitor-exit p0

    return-wide v0

    .line 202
    :goto_2
    monitor-exit p0
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_0

    throw v0
.end method

.method public declared-synchronized startRecording(Ljava/lang/Runnable;Ljava/lang/Runnable;)V
    .locals 3

    const-string v0, "audio and video recording started: timeOriginNs="

    monitor-enter p0

    .line 131
    :try_start_0
    iget-boolean v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->started:Z
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    if-eqz v1, :cond_0

    monitor-exit p0

    return-void

    .line 132
    :cond_0
    :try_start_1
    iget-boolean v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->prepared:Z
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    if-eqz v1, :cond_2

    .line 134
    :try_start_2
    iget-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->audioCodec:Landroid/media/MediaCodec;

    invoke-virtual {v1}, Landroid/media/MediaCodec;->start()V

    .line 135
    iget-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->audioRecord:Landroid/media/AudioRecord;

    invoke-virtual {v1}, Landroid/media/AudioRecord;->startRecording()V

    .line 136
    iget-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->audioRecord:Landroid/media/AudioRecord;

    invoke-virtual {v1}, Landroid/media/AudioRecord;->getRecordingState()I

    move-result v1

    const/4 v2, 0x3

    if-ne v1, v2, :cond_1

    .line 139
    invoke-static {}, Landroid/os/SystemClock;->elapsedRealtimeNanos()J

    move-result-wide v1

    iput-wide v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->timeOriginNs:J

    .line 140
    iput-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->audioReadyListener:Ljava/lang/Runnable;

    .line 141
    iput-object p2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->videoReadyListener:Ljava/lang/Runnable;

    const/4 p1, 0x1

    .line 142
    iput-boolean p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->started:Z

    .line 143
    iget-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->diagnostics:Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;

    new-instance p2, Ljava/lang/StringBuilder;

    invoke-direct {p2, v0}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    iget-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->timeOriginNs:J

    invoke-virtual {p2, v0, v1}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    const-string v0, ", timelineOffsetUs="

    invoke-virtual {p2, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-wide v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->timelineOffsetUs:J

    invoke-virtual {p2, v0, v1}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    const-string v0, ", audioSessionId="

    invoke-virtual {p2, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->audioRecord:Landroid/media/AudioRecord;

    .line 145
    invoke-virtual {v0}, Landroid/media/AudioRecord;->getAudioSessionId()I

    move-result v0

    invoke-virtual {p2, v0}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {p2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p2

    .line 143
    invoke-virtual {p1, p2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;->log(Ljava/lang/String;)V

    .line 146
    new-instance p1, Ljava/lang/Thread;

    new-instance p2, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder$$ExternalSyntheticLambda0;

    invoke-direct {p2, p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder$$ExternalSyntheticLambda0;-><init>(Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;)V

    const-string v0, "RoundVideoVideoEncoder"

    invoke-direct {p1, p2, v0}, Ljava/lang/Thread;-><init>(Ljava/lang/Runnable;Ljava/lang/String;)V

    iput-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->videoDrainThread:Ljava/lang/Thread;

    .line 147
    new-instance p1, Ljava/lang/Thread;

    new-instance p2, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder$$ExternalSyntheticLambda1;

    invoke-direct {p2, p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder$$ExternalSyntheticLambda1;-><init>(Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;)V

    const-string v0, "RoundVideoAudioEncoder"

    invoke-direct {p1, p2, v0}, Ljava/lang/Thread;-><init>(Ljava/lang/Runnable;Ljava/lang/String;)V

    iput-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->audioThread:Ljava/lang/Thread;

    .line 148
    iget-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->videoDrainThread:Ljava/lang/Thread;

    invoke-virtual {p1}, Ljava/lang/Thread;->start()V

    .line 149
    iget-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->audioThread:Ljava/lang/Thread;

    invoke-virtual {p1}, Ljava/lang/Thread;->start()V
    :try_end_2
    .catch Ljava/lang/RuntimeException; {:try_start_2 .. :try_end_2} :catch_0
    .catchall {:try_start_2 .. :try_end_2} :catchall_0

    .line 154
    monitor-exit p0

    return-void

    :catchall_0
    move-exception p1

    goto :goto_1

    :catch_0
    move-exception p1

    goto :goto_0

    .line 137
    :cond_1
    :try_start_3
    new-instance p1, Ljava/lang/IllegalStateException;

    const-string p2, "Unable to start AudioRecord"

    invoke-direct {p1, p2}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw p1
    :try_end_3
    .catch Ljava/lang/RuntimeException; {:try_start_3 .. :try_end_3} :catch_0
    .catchall {:try_start_3 .. :try_end_3} :catchall_0

    .line 151
    :goto_0
    :try_start_4
    invoke-direct {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->release()V

    .line 152
    throw p1

    .line 132
    :cond_2
    new-instance p1, Ljava/lang/IllegalStateException;

    const-string p2, "Recorder is not prepared"

    invoke-direct {p1, p2}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw p1

    .line 152
    :goto_1
    monitor-exit p0
    :try_end_4
    .catchall {:try_start_4 .. :try_end_4} :catchall_0

    throw p1
.end method

.method public stop()V
    .locals 3

    .line 171
    invoke-virtual {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->requestStop()J

    .line 172
    monitor-enter p0

    .line 173
    :try_start_0
    iget-boolean v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->prepared:Z

    if-eqz v0, :cond_2

    iget-boolean v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->stopFinalizationStarted:Z

    if-eqz v0, :cond_0

    goto :goto_1

    .line 174
    :cond_0
    iget-boolean v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->started:Z

    if-nez v0, :cond_1

    .line 175
    invoke-direct {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->release()V

    .line 176
    monitor-exit p0

    return-void

    :catchall_0
    move-exception v0

    goto :goto_2

    :cond_1
    const/4 v0, 0x1

    .line 178
    iput-boolean v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->stopFinalizationStarted:Z

    .line 179
    monitor-exit p0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 181
    :try_start_1
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->videoCodec:Landroid/media/MediaCodec;

    invoke-virtual {v0}, Landroid/media/MediaCodec;->signalEndOfInputStream()V
    :try_end_1
    .catch Ljava/lang/IllegalStateException; {:try_start_1 .. :try_end_1} :catch_0

    goto :goto_0

    :catch_0
    move-exception v0

    .line 183
    iget-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->diagnostics:Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;

    const-string v2, "video encoder EOS failed"

    invoke-virtual {v1, v2, v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;->error(Ljava/lang/String;Ljava/lang/Throwable;)V

    .line 185
    :goto_0
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->videoDrainThread:Ljava/lang/Thread;

    invoke-direct {p0, v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->join(Ljava/lang/Thread;)V

    .line 186
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->audioThread:Ljava/lang/Thread;

    invoke-direct {p0, v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->join(Ljava/lang/Thread;)V

    .line 187
    invoke-direct {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->logSummary()V

    .line 188
    invoke-direct {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;->release()V

    return-void

    .line 173
    :cond_2
    :goto_1
    :try_start_2
    monitor-exit p0

    return-void

    .line 179
    :goto_2
    monitor-exit p0
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_0

    throw v0
.end method
