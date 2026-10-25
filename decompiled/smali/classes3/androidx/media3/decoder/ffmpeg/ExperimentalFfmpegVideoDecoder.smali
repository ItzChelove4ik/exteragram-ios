.class final Landroidx/media3/decoder/ffmpeg/ExperimentalFfmpegVideoDecoder;
.super Landroidx/media3/decoder/SimpleDecoder;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Landroidx/media3/decoder/ffmpeg/ExperimentalFfmpegVideoDecoder$PendingInput;
    }
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Landroidx/media3/decoder/SimpleDecoder<",
        "Landroidx/media3/decoder/DecoderInputBuffer;",
        "Landroidx/media3/decoder/VideoDecoderOutputBuffer;",
        "Landroidx/media3/decoder/ffmpeg/FfmpegDecoderException;",
        ">;"
    }
.end annotation


# instance fields
.field private final codecName:Ljava/lang/String;

.field private degree:I

.field private final extraData:[B

.field private nativeContext:J

.field private volatile outputMode:I

.field private final pendingInputs:Ljava/util/ArrayDeque;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/ArrayDeque<",
            "Landroidx/media3/decoder/ffmpeg/ExperimentalFfmpegVideoDecoder$PendingInput;",
            ">;"
        }
    .end annotation
.end field


# direct methods
.method public static synthetic $r8$lambda$KsPZ8n3VnwUduE9ssA7RhBITdHg(Landroidx/media3/decoder/ffmpeg/ExperimentalFfmpegVideoDecoder;Landroidx/media3/decoder/DecoderOutputBuffer;)V
    .locals 0

    .line 159
    invoke-virtual {p0, p1}, Landroidx/media3/decoder/SimpleDecoder;->releaseOutputBuffer(Landroidx/media3/decoder/DecoderOutputBuffer;)V

    return-void
.end method

.method public constructor <init>(IIIILandroidx/media3/common/Format;)V
    .locals 7

    .line 97
    new-array p1, p1, [Landroidx/media3/decoder/DecoderInputBuffer;

    new-array p2, p2, [Landroidx/media3/decoder/VideoDecoderOutputBuffer;

    invoke-direct {p0, p1, p2}, Landroidx/media3/decoder/SimpleDecoder;-><init>([Landroidx/media3/decoder/DecoderInputBuffer;[Landroidx/media3/decoder/DecoderOutputBuffer;)V

    const/4 p1, 0x0

    .line 62
    iput p1, p0, Landroidx/media3/decoder/ffmpeg/ExperimentalFfmpegVideoDecoder;->degree:I

    .line 71
    new-instance p1, Ljava/util/ArrayDeque;

    invoke-direct {p1}, Ljava/util/ArrayDeque;-><init>()V

    iput-object p1, p0, Landroidx/media3/decoder/ffmpeg/ExperimentalFfmpegVideoDecoder;->pendingInputs:Ljava/util/ArrayDeque;

    .line 100
    invoke-static {}, Landroidx/media3/decoder/ffmpeg/FfmpegLibrary;->isAvailable()Z

    move-result p1

    if-eqz p1, :cond_1

    .line 103
    iget-object p1, p5, Landroidx/media3/common/Format;->sampleMimeType:Ljava/lang/String;

    invoke-static {p1}, Landroidx/media3/decoder/ffmpeg/FfmpegLibrary;->getCodecName(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p1

    invoke-static {p1}, Landroidx/media3/common/util/Assertions;->checkNotNull(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p1

    move-object v1, p1

    check-cast v1, Ljava/lang/String;

    iput-object v1, p0, Landroidx/media3/decoder/ffmpeg/ExperimentalFfmpegVideoDecoder;->codecName:Ljava/lang/String;

    .line 104
    iget-object p1, p5, Landroidx/media3/common/Format;->sampleMimeType:Ljava/lang/String;

    iget-object p2, p5, Landroidx/media3/common/Format;->initializationData:Ljava/util/List;

    invoke-static {p1, p2}, Landroidx/media3/decoder/ffmpeg/ExperimentalFfmpegVideoDecoder;->getExtraData(Ljava/lang/String;Ljava/util/List;)[B

    move-result-object v2

    iput-object v2, p0, Landroidx/media3/decoder/ffmpeg/ExperimentalFfmpegVideoDecoder;->extraData:[B

    .line 105
    iget v4, p5, Landroidx/media3/common/Format;->rotationDegrees:I

    iput v4, p0, Landroidx/media3/decoder/ffmpeg/ExperimentalFfmpegVideoDecoder;->degree:I

    .line 106
    iget v5, p5, Landroidx/media3/common/Format;->width:I

    iget v6, p5, Landroidx/media3/common/Format;->height:I

    move-object v0, p0

    move v3, p4

    invoke-direct/range {v0 .. v6}, Landroidx/media3/decoder/ffmpeg/ExperimentalFfmpegVideoDecoder;->ffmpegInitialize(Ljava/lang/String;[BIIII)J

    move-result-wide p0

    iput-wide p0, v0, Landroidx/media3/decoder/ffmpeg/ExperimentalFfmpegVideoDecoder;->nativeContext:J

    const-wide/16 p4, 0x0

    cmp-long p0, p0, p4

    if-eqz p0, :cond_0

    .line 110
    invoke-virtual {v0, p3}, Landroidx/media3/decoder/SimpleDecoder;->setInitialInputBufferSize(I)V

    return-void

    .line 108
    :cond_0
    new-instance p0, Landroidx/media3/decoder/ffmpeg/FfmpegDecoderException;

    const-string p1, "Failed to initialize decoder."

    invoke-direct {p0, p1}, Landroidx/media3/decoder/ffmpeg/FfmpegDecoderException;-><init>(Ljava/lang/String;)V

    throw p0

    .line 101
    :cond_1
    new-instance p0, Landroidx/media3/decoder/ffmpeg/FfmpegDecoderException;

    const-string p1, "Failed to load decoder native library."

    invoke-direct {p0, p1}, Landroidx/media3/decoder/ffmpeg/FfmpegDecoderException;-><init>(Ljava/lang/String;)V

    throw p0
.end method

.method private static copyPendingInput(Ljava/nio/ByteBuffer;J)Landroidx/media3/decoder/ffmpeg/ExperimentalFfmpegVideoDecoder$PendingInput;
    .locals 1

    const/4 v0, 0x0

    .line 251
    invoke-virtual {p0, v0}, Ljava/nio/ByteBuffer;->position(I)Ljava/nio/Buffer;

    .line 252
    invoke-virtual {p0}, Ljava/nio/Buffer;->limit()I

    move-result v0

    invoke-static {v0}, Ljava/nio/ByteBuffer;->allocateDirect(I)Ljava/nio/ByteBuffer;

    move-result-object v0

    .line 253
    invoke-virtual {v0, p0}, Ljava/nio/ByteBuffer;->put(Ljava/nio/ByteBuffer;)Ljava/nio/ByteBuffer;

    .line 254
    invoke-virtual {v0}, Ljava/nio/ByteBuffer;->flip()Ljava/nio/Buffer;

    .line 255
    new-instance p0, Landroidx/media3/decoder/ffmpeg/ExperimentalFfmpegVideoDecoder$PendingInput;

    invoke-direct {p0, v0, p1, p2}, Landroidx/media3/decoder/ffmpeg/ExperimentalFfmpegVideoDecoder$PendingInput;-><init>(Ljava/nio/ByteBuffer;J)V

    return-object p0
.end method

.method private native ffmpegInitialize(Ljava/lang/String;[BIIII)J
.end method

.method private native ffmpegReceiveFrame(JILandroidx/media3/decoder/VideoDecoderOutputBuffer;Z)I
.end method

.method private native ffmpegRelease(J)V
.end method

.method private native ffmpegRenderFrame(JLandroid/view/Surface;Landroidx/media3/decoder/VideoDecoderOutputBuffer;II)I
.end method

.method private native ffmpegReset(J)J
.end method

.method private native ffmpegSendPacket(JLjava/nio/ByteBuffer;IJ)I
.end method

.method private static getExtraData(Ljava/lang/String;Ljava/util/List;)[B
    .locals 3
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/String;",
            "Ljava/util/List<",
            "[B>;)[B"
        }
    .end annotation

    const/4 p0, 0x0

    move v0, p0

    move v1, v0

    .line 120
    :goto_0
    invoke-interface {p1}, Ljava/util/List;->size()I

    move-result v2

    if-ge v0, v2, :cond_0

    .line 121
    invoke-interface {p1, v0}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v2

    check-cast v2, [B

    array-length v2, v2

    add-int/2addr v1, v2

    add-int/lit8 v0, v0, 0x1

    goto :goto_0

    :cond_0
    if-lez v1, :cond_2

    .line 124
    new-array v0, v1, [B

    .line 125
    invoke-static {v0}, Ljava/nio/ByteBuffer;->wrap([B)Ljava/nio/ByteBuffer;

    move-result-object v1

    .line 126
    :goto_1
    invoke-interface {p1}, Ljava/util/List;->size()I

    move-result v2

    if-ge p0, v2, :cond_1

    .line 127
    invoke-interface {p1, p0}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v2

    check-cast v2, [B

    invoke-virtual {v1, v2}, Ljava/nio/ByteBuffer;->put([B)Ljava/nio/ByteBuffer;

    add-int/lit8 p0, p0, 0x1

    goto :goto_1

    :cond_1
    return-object v0

    :cond_2
    const/4 p0, 0x0

    return-object p0
.end method


# virtual methods
.method public createInputBuffer()Landroidx/media3/decoder/DecoderInputBuffer;
    .locals 2

    .line 152
    new-instance p0, Landroidx/media3/decoder/DecoderInputBuffer;

    const/4 v0, 0x2

    .line 154
    invoke-static {}, Landroidx/media3/decoder/ffmpeg/FfmpegLibrary;->getInputBufferPaddingSize()I

    move-result v1

    invoke-direct {p0, v0, v1}, Landroidx/media3/decoder/DecoderInputBuffer;-><init>(II)V

    return-object p0
.end method

.method public bridge synthetic createOutputBuffer()Landroidx/media3/decoder/DecoderOutputBuffer;
    .locals 0

    .line 40
    invoke-virtual {p0}, Landroidx/media3/decoder/ffmpeg/ExperimentalFfmpegVideoDecoder;->createOutputBuffer()Landroidx/media3/decoder/VideoDecoderOutputBuffer;

    move-result-object p0

    return-object p0
.end method

.method public createOutputBuffer()Landroidx/media3/decoder/VideoDecoderOutputBuffer;
    .locals 2

    .line 159
    new-instance v0, Landroidx/media3/decoder/VideoDecoderOutputBuffer;

    new-instance v1, Landroidx/media3/decoder/ffmpeg/ExperimentalFfmpegVideoDecoder$$ExternalSyntheticLambda0;

    invoke-direct {v1, p0}, Landroidx/media3/decoder/ffmpeg/ExperimentalFfmpegVideoDecoder$$ExternalSyntheticLambda0;-><init>(Landroidx/media3/decoder/ffmpeg/ExperimentalFfmpegVideoDecoder;)V

    invoke-direct {v0, v1}, Landroidx/media3/decoder/VideoDecoderOutputBuffer;-><init>(Landroidx/media3/decoder/DecoderOutputBuffer$Owner;)V

    return-object v0
.end method

.method public bridge synthetic createUnexpectedDecodeException(Ljava/lang/Throwable;)Landroidx/media3/decoder/DecoderException;
    .locals 0

    .line 40
    invoke-virtual {p0, p1}, Landroidx/media3/decoder/ffmpeg/ExperimentalFfmpegVideoDecoder;->createUnexpectedDecodeException(Ljava/lang/Throwable;)Landroidx/media3/decoder/ffmpeg/FfmpegDecoderException;

    move-result-object p0

    return-object p0
.end method

.method public createUnexpectedDecodeException(Ljava/lang/Throwable;)Landroidx/media3/decoder/ffmpeg/FfmpegDecoderException;
    .locals 1

    .line 260
    new-instance p0, Landroidx/media3/decoder/ffmpeg/FfmpegDecoderException;

    const-string v0, "Unexpected decode error"

    invoke-direct {p0, v0, p1}, Landroidx/media3/decoder/ffmpeg/FfmpegDecoderException;-><init>(Ljava/lang/String;Ljava/lang/Throwable;)V

    return-object p0
.end method

.method public bridge synthetic decode(Landroidx/media3/decoder/DecoderInputBuffer;Landroidx/media3/decoder/DecoderOutputBuffer;Z)Landroidx/media3/decoder/DecoderException;
    .locals 0

    .line 40
    check-cast p2, Landroidx/media3/decoder/VideoDecoderOutputBuffer;

    invoke-virtual {p0, p1, p2, p3}, Landroidx/media3/decoder/ffmpeg/ExperimentalFfmpegVideoDecoder;->decode(Landroidx/media3/decoder/DecoderInputBuffer;Landroidx/media3/decoder/VideoDecoderOutputBuffer;Z)Landroidx/media3/decoder/ffmpeg/FfmpegDecoderException;

    move-result-object p0

    return-object p0
.end method

.method public decode(Landroidx/media3/decoder/DecoderInputBuffer;Landroidx/media3/decoder/VideoDecoderOutputBuffer;Z)Landroidx/media3/decoder/ffmpeg/FfmpegDecoderException;
    .locals 18

    move-object/from16 v0, p0

    move-object/from16 v7, p1

    move-object/from16 v8, p2

    if-eqz p3, :cond_1

    .line 167
    iget-wide v1, v0, Landroidx/media3/decoder/ffmpeg/ExperimentalFfmpegVideoDecoder;->nativeContext:J

    invoke-direct {v0, v1, v2}, Landroidx/media3/decoder/ffmpeg/ExperimentalFfmpegVideoDecoder;->ffmpegReset(J)J

    move-result-wide v1

    iput-wide v1, v0, Landroidx/media3/decoder/ffmpeg/ExperimentalFfmpegVideoDecoder;->nativeContext:J

    const-wide/16 v3, 0x0

    cmp-long v1, v1, v3

    if-nez v1, :cond_0

    .line 169
    new-instance v0, Landroidx/media3/decoder/ffmpeg/FfmpegDecoderException;

    const-string v1, "Error resetting (see logcat)."

    invoke-direct {v0, v1}, Landroidx/media3/decoder/ffmpeg/FfmpegDecoderException;-><init>(Ljava/lang/String;)V

    return-object v0

    .line 171
    :cond_0
    iget-object v1, v0, Landroidx/media3/decoder/ffmpeg/ExperimentalFfmpegVideoDecoder;->pendingInputs:Ljava/util/ArrayDeque;

    invoke-virtual {v1}, Ljava/util/ArrayDeque;->clear()V

    .line 174
    :cond_1
    iget-object v1, v7, Landroidx/media3/decoder/DecoderInputBuffer;->data:Ljava/nio/ByteBuffer;

    invoke-static {v1}, Landroidx/media3/common/util/Util;->castNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v1

    move-object v9, v1

    check-cast v9, Ljava/nio/ByteBuffer;

    .line 175
    iget-wide v1, v7, Landroidx/media3/decoder/DecoderInputBuffer;->timeUs:J

    invoke-virtual {v0, v1, v2}, Landroidx/media3/decoder/SimpleDecoder;->isAtLeastOutputStartTimeUs(J)Z

    move-result v10

    xor-int/lit8 v11, v10, 0x1

    .line 179
    :cond_2
    iget-object v1, v0, Landroidx/media3/decoder/ffmpeg/ExperimentalFfmpegVideoDecoder;->pendingInputs:Ljava/util/ArrayDeque;

    invoke-virtual {v1}, Ljava/util/ArrayDeque;->isEmpty()Z

    move-result v1

    const/4 v12, -0x3

    const-string v13, "ffmpegDecode error: (see logcat)"

    const/4 v14, -0x2

    if-nez v1, :cond_4

    .line 180
    iget-object v1, v0, Landroidx/media3/decoder/ffmpeg/ExperimentalFfmpegVideoDecoder;->pendingInputs:Ljava/util/ArrayDeque;

    invoke-virtual {v1}, Ljava/util/ArrayDeque;->peek()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Landroidx/media3/decoder/ffmpeg/ExperimentalFfmpegVideoDecoder$PendingInput;

    .line 181
    iget-wide v2, v0, Landroidx/media3/decoder/ffmpeg/ExperimentalFfmpegVideoDecoder;->nativeContext:J

    move-wide v4, v2

    iget-object v3, v1, Landroidx/media3/decoder/ffmpeg/ExperimentalFfmpegVideoDecoder$PendingInput;->data:Ljava/nio/ByteBuffer;

    move-wide v5, v4

    .line 182
    invoke-virtual {v3}, Ljava/nio/Buffer;->limit()I

    move-result v4

    iget-wide v1, v1, Landroidx/media3/decoder/ffmpeg/ExperimentalFfmpegVideoDecoder$PendingInput;->timeUs:J

    move-wide/from16 v16, v5

    move-wide v5, v1

    move-wide/from16 v1, v16

    invoke-direct/range {v0 .. v6}, Landroidx/media3/decoder/ffmpeg/ExperimentalFfmpegVideoDecoder;->ffmpegSendPacket(JLjava/nio/ByteBuffer;IJ)I

    move-result v1

    if-ne v1, v12, :cond_3

    goto :goto_0

    .line 186
    :cond_3
    iget-object v2, v0, Landroidx/media3/decoder/ffmpeg/ExperimentalFfmpegVideoDecoder;->pendingInputs:Ljava/util/ArrayDeque;

    invoke-virtual {v2}, Ljava/util/ArrayDeque;->removeFirst()Ljava/lang/Object;

    if-ne v1, v14, :cond_2

    .line 188
    new-instance v0, Landroidx/media3/decoder/ffmpeg/FfmpegDecoderException;

    invoke-direct {v0, v13}, Landroidx/media3/decoder/ffmpeg/FfmpegDecoderException;-><init>(Ljava/lang/String;)V

    return-object v0

    .line 196
    :cond_4
    :goto_0
    iget-wide v1, v0, Landroidx/media3/decoder/ffmpeg/ExperimentalFfmpegVideoDecoder;->nativeContext:J

    .line 197
    invoke-virtual {v9}, Ljava/nio/Buffer;->limit()I

    move-result v4

    iget-wide v5, v7, Landroidx/media3/decoder/DecoderInputBuffer;->timeUs:J

    move-object v3, v9

    invoke-direct/range {v0 .. v6}, Landroidx/media3/decoder/ffmpeg/ExperimentalFfmpegVideoDecoder;->ffmpegSendPacket(JLjava/nio/ByteBuffer;IJ)I

    move-result v1

    const/4 v6, -0x1

    const/4 v9, 0x1

    const/4 v15, 0x0

    if-ne v1, v12, :cond_9

    .line 200
    iget-object v1, v0, Landroidx/media3/decoder/ffmpeg/ExperimentalFfmpegVideoDecoder;->pendingInputs:Ljava/util/ArrayDeque;

    iget-wide v4, v7, Landroidx/media3/decoder/DecoderInputBuffer;->timeUs:J

    invoke-static {v3, v4, v5}, Landroidx/media3/decoder/ffmpeg/ExperimentalFfmpegVideoDecoder;->copyPendingInput(Ljava/nio/ByteBuffer;J)Landroidx/media3/decoder/ffmpeg/ExperimentalFfmpegVideoDecoder$PendingInput;

    move-result-object v2

    invoke-virtual {v1, v2}, Ljava/util/ArrayDeque;->addLast(Ljava/lang/Object;)V

    if-eqz v10, :cond_5

    .line 202
    iget-wide v1, v7, Landroidx/media3/decoder/DecoderInputBuffer;->timeUs:J

    iget v3, v0, Landroidx/media3/decoder/ffmpeg/ExperimentalFfmpegVideoDecoder;->outputMode:I

    invoke-virtual {v8, v1, v2, v3, v15}, Landroidx/media3/decoder/VideoDecoderOutputBuffer;->init(JILjava/nio/ByteBuffer;)V

    .line 204
    :cond_5
    iget-wide v1, v0, Landroidx/media3/decoder/ffmpeg/ExperimentalFfmpegVideoDecoder;->nativeContext:J

    iget v3, v0, Landroidx/media3/decoder/ffmpeg/ExperimentalFfmpegVideoDecoder;->outputMode:I

    move-object v4, v8

    move v5, v11

    invoke-direct/range {v0 .. v5}, Landroidx/media3/decoder/ffmpeg/ExperimentalFfmpegVideoDecoder;->ffmpegReceiveFrame(JILandroidx/media3/decoder/VideoDecoderOutputBuffer;Z)I

    move-result v0

    if-ne v0, v14, :cond_6

    .line 206
    new-instance v0, Landroidx/media3/decoder/ffmpeg/FfmpegDecoderException;

    invoke-direct {v0, v13}, Landroidx/media3/decoder/ffmpeg/FfmpegDecoderException;-><init>(Ljava/lang/String;)V

    return-object v0

    :cond_6
    if-ne v0, v6, :cond_7

    .line 209
    iput-boolean v9, v4, Landroidx/media3/decoder/DecoderOutputBuffer;->shouldBeSkipped:Z

    goto :goto_1

    :cond_7
    if-eqz v10, :cond_8

    .line 212
    iget-object v0, v7, Landroidx/media3/decoder/DecoderInputBuffer;->format:Landroidx/media3/common/Format;

    iput-object v0, v4, Landroidx/media3/decoder/VideoDecoderOutputBuffer;->format:Landroidx/media3/common/Format;

    :cond_8
    :goto_1
    return-object v15

    :cond_9
    move-object v4, v8

    move v5, v11

    if-ne v1, v6, :cond_a

    .line 218
    iput-boolean v9, v4, Landroidx/media3/decoder/DecoderOutputBuffer;->shouldBeSkipped:Z

    return-object v15

    :cond_a
    if-ne v1, v14, :cond_b

    .line 221
    new-instance v0, Landroidx/media3/decoder/ffmpeg/FfmpegDecoderException;

    invoke-direct {v0, v13}, Landroidx/media3/decoder/ffmpeg/FfmpegDecoderException;-><init>(Ljava/lang/String;)V

    return-object v0

    :cond_b
    if-eqz v10, :cond_c

    .line 228
    iget-wide v1, v7, Landroidx/media3/decoder/DecoderInputBuffer;->timeUs:J

    iget v3, v0, Landroidx/media3/decoder/ffmpeg/ExperimentalFfmpegVideoDecoder;->outputMode:I

    invoke-virtual {v4, v1, v2, v3, v15}, Landroidx/media3/decoder/VideoDecoderOutputBuffer;->init(JILjava/nio/ByteBuffer;)V

    .line 230
    :cond_c
    iget-wide v1, v0, Landroidx/media3/decoder/ffmpeg/ExperimentalFfmpegVideoDecoder;->nativeContext:J

    iget v3, v0, Landroidx/media3/decoder/ffmpeg/ExperimentalFfmpegVideoDecoder;->outputMode:I

    invoke-direct/range {v0 .. v5}, Landroidx/media3/decoder/ffmpeg/ExperimentalFfmpegVideoDecoder;->ffmpegReceiveFrame(JILandroidx/media3/decoder/VideoDecoderOutputBuffer;Z)I

    move-result v0

    if-ne v0, v14, :cond_d

    .line 232
    new-instance v0, Landroidx/media3/decoder/ffmpeg/FfmpegDecoderException;

    invoke-direct {v0, v13}, Landroidx/media3/decoder/ffmpeg/FfmpegDecoderException;-><init>(Ljava/lang/String;)V

    return-object v0

    :cond_d
    if-ne v0, v6, :cond_e

    .line 236
    iput-boolean v9, v4, Landroidx/media3/decoder/DecoderOutputBuffer;->shouldBeSkipped:Z

    :cond_e
    if-eqz v10, :cond_f

    .line 240
    iget-object v0, v7, Landroidx/media3/decoder/DecoderInputBuffer;->format:Landroidx/media3/common/Format;

    iput-object v0, v4, Landroidx/media3/decoder/VideoDecoderOutputBuffer;->format:Landroidx/media3/common/Format;

    :cond_f
    return-object v15
.end method

.method public getName()Ljava/lang/String;
    .locals 2

    .line 136
    new-instance v0, Ljava/lang/StringBuilder;

    const-string v1, "ffmpeg"

    invoke-direct {v0, v1}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-static {}, Landroidx/media3/decoder/ffmpeg/FfmpegLibrary;->getVersion()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v1, "-"

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    iget-object p0, p0, Landroidx/media3/decoder/ffmpeg/ExperimentalFfmpegVideoDecoder;->codecName:Ljava/lang/String;

    invoke-virtual {v0, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method public release()V
    .locals 2

    .line 265
    invoke-super {p0}, Landroidx/media3/decoder/SimpleDecoder;->release()V

    .line 266
    iget-wide v0, p0, Landroidx/media3/decoder/ffmpeg/ExperimentalFfmpegVideoDecoder;->nativeContext:J

    invoke-direct {p0, v0, v1}, Landroidx/media3/decoder/ffmpeg/ExperimentalFfmpegVideoDecoder;->ffmpegRelease(J)V

    const-wide/16 v0, 0x0

    .line 267
    iput-wide v0, p0, Landroidx/media3/decoder/ffmpeg/ExperimentalFfmpegVideoDecoder;->nativeContext:J

    return-void
.end method

.method public renderToSurface(Landroidx/media3/decoder/VideoDecoderOutputBuffer;Landroid/view/Surface;)V
    .locals 9

    .line 281
    iget v0, p1, Landroidx/media3/decoder/VideoDecoderOutputBuffer;->mode:I

    const/4 v1, 0x1

    if-ne v0, v1, :cond_1

    .line 284
    iget-wide v3, p0, Landroidx/media3/decoder/ffmpeg/ExperimentalFfmpegVideoDecoder;->nativeContext:J

    iget v7, p1, Landroidx/media3/decoder/VideoDecoderOutputBuffer;->width:I

    iget v8, p1, Landroidx/media3/decoder/VideoDecoderOutputBuffer;->height:I

    move-object v2, p0

    move-object v6, p1

    move-object v5, p2

    invoke-direct/range {v2 .. v8}, Landroidx/media3/decoder/ffmpeg/ExperimentalFfmpegVideoDecoder;->ffmpegRenderFrame(JLandroid/view/Surface;Landroidx/media3/decoder/VideoDecoderOutputBuffer;II)I

    move-result p0

    const/4 p1, -0x2

    if-eq p0, p1, :cond_0

    const/4 p1, -0x4

    if-eq p0, p1, :cond_0

    return-void

    .line 287
    :cond_0
    new-instance p1, Landroidx/media3/decoder/ffmpeg/FfmpegDecoderException;

    new-instance p2, Ljava/lang/StringBuilder;

    const-string v0, "Buffer render error: "

    invoke-direct {p2, v0}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {p2, p0}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {p2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    invoke-direct {p1, p0}, Landroidx/media3/decoder/ffmpeg/FfmpegDecoderException;-><init>(Ljava/lang/String;)V

    throw p1

    .line 282
    :cond_1
    new-instance p0, Landroidx/media3/decoder/ffmpeg/FfmpegDecoderException;

    const-string p1, "Invalid output mode."

    invoke-direct {p0, p1}, Landroidx/media3/decoder/ffmpeg/FfmpegDecoderException;-><init>(Ljava/lang/String;)V

    throw p0
.end method

.method public setOutputMode(I)V
    .locals 0

    .line 145
    iput p1, p0, Landroidx/media3/decoder/ffmpeg/ExperimentalFfmpegVideoDecoder;->outputMode:I

    return-void
.end method
