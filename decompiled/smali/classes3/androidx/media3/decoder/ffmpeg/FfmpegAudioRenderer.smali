.class public final Landroidx/media3/decoder/ffmpeg/FfmpegAudioRenderer;
.super Landroidx/media3/exoplayer/audio/DecoderAudioRenderer;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/Signature;
    value = {
        "Landroidx/media3/exoplayer/audio/DecoderAudioRenderer<",
        "Landroidx/media3/decoder/ffmpeg/FfmpegAudioDecoder;",
        ">;"
    }
.end annotation


# direct methods
.method public constructor <init>(Landroid/os/Handler;Landroidx/media3/exoplayer/audio/AudioRendererEventListener;Landroidx/media3/exoplayer/audio/AudioSink;)V
    .locals 0

    .line 124
    invoke-direct {p0, p1, p2, p3}, Landroidx/media3/exoplayer/audio/DecoderAudioRenderer;-><init>(Landroid/os/Handler;Landroidx/media3/exoplayer/audio/AudioRendererEventListener;Landroidx/media3/exoplayer/audio/AudioSink;)V

    return-void
.end method

.method private shouldOutputFloat(Landroidx/media3/common/Format;)Z
    .locals 5

    const/4 v0, 0x2

    .line 187
    invoke-direct {p0, p1, v0}, Landroidx/media3/decoder/ffmpeg/FfmpegAudioRenderer;->sinkSupportsFormat(Landroidx/media3/common/Format;I)Z

    move-result v1

    const/4 v2, 0x1

    if-nez v1, :cond_0

    return v2

    .line 193
    :cond_0
    iget v1, p1, Landroidx/media3/common/Format;->channelCount:I

    iget v3, p1, Landroidx/media3/common/Format;->sampleRate:I

    const/4 v4, 0x4

    .line 195
    invoke-static {v4, v1, v3}, Landroidx/media3/common/util/Util;->getPcmFormat(III)Landroidx/media3/common/Format;

    move-result-object v1

    .line 194
    invoke-virtual {p0, v1}, Landroidx/media3/exoplayer/audio/DecoderAudioRenderer;->getSinkFormatSupport(Landroidx/media3/common/Format;)I

    move-result p0

    if-eq p0, v0, :cond_1

    const/4 p0, 0x0

    return p0

    .line 201
    :cond_1
    const-string p0, "audio/ac3"

    iget-object p1, p1, Landroidx/media3/common/Format;->sampleMimeType:Ljava/lang/String;

    invoke-virtual {p0, p1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result p0

    xor-int/2addr p0, v2

    return p0
.end method

.method private sinkSupportsFormat(Landroidx/media3/common/Format;I)Z
    .locals 1

    .line 182
    iget v0, p1, Landroidx/media3/common/Format;->channelCount:I

    iget p1, p1, Landroidx/media3/common/Format;->sampleRate:I

    .line 183
    invoke-static {p2, v0, p1}, Landroidx/media3/common/util/Util;->getPcmFormat(III)Landroidx/media3/common/Format;

    move-result-object p1

    .line 182
    invoke-virtual {p0, p1}, Landroidx/media3/exoplayer/audio/DecoderAudioRenderer;->sinkSupportsFormat(Landroidx/media3/common/Format;)Z

    move-result p0

    return p0
.end method


# virtual methods
.method public bridge synthetic createDecoder(Landroidx/media3/common/Format;Landroidx/media3/decoder/CryptoConfig;)Landroidx/media3/decoder/Decoder;
    .locals 0

    .line 41
    invoke-virtual {p0, p1, p2}, Landroidx/media3/decoder/ffmpeg/FfmpegAudioRenderer;->createDecoder(Landroidx/media3/common/Format;Landroidx/media3/decoder/CryptoConfig;)Landroidx/media3/decoder/ffmpeg/FfmpegAudioDecoder;

    move-result-object p0

    return-object p0
.end method

.method public createDecoder(Landroidx/media3/common/Format;Landroidx/media3/decoder/CryptoConfig;)Landroidx/media3/decoder/ffmpeg/FfmpegAudioDecoder;
    .locals 6

    .line 156
    const-string p2, "createFfmpegAudioDecoder"

    invoke-static {p2}, Landroidx/media3/common/util/TraceUtil;->beginSection(Ljava/lang/String;)V

    .line 158
    iget p2, p1, Landroidx/media3/common/Format;->maxInputSize:I

    const/4 v0, -0x1

    if-eq p2, v0, :cond_0

    :goto_0
    move v4, p2

    goto :goto_1

    :cond_0
    const/16 p2, 0x1680

    goto :goto_0

    .line 159
    :goto_1
    new-instance v0, Landroidx/media3/decoder/ffmpeg/FfmpegAudioDecoder;

    const/16 v3, 0x10

    .line 161
    invoke-direct {p0, p1}, Landroidx/media3/decoder/ffmpeg/FfmpegAudioRenderer;->shouldOutputFloat(Landroidx/media3/common/Format;)Z

    move-result v5

    const/16 v2, 0x10

    move-object v1, p1

    invoke-direct/range {v0 .. v5}, Landroidx/media3/decoder/ffmpeg/FfmpegAudioDecoder;-><init>(Landroidx/media3/common/Format;IIIZ)V

    .line 162
    invoke-static {}, Landroidx/media3/common/util/TraceUtil;->endSection()V

    return-object v0
.end method

.method public getName()Ljava/lang/String;
    .locals 0

    .line 129
    const-string p0, "FfmpegAudioRenderer"

    return-object p0
.end method

.method public bridge synthetic getOutputFormat(Landroidx/media3/decoder/Decoder;)Landroidx/media3/common/Format;
    .locals 0

    .line 41
    check-cast p1, Landroidx/media3/decoder/ffmpeg/FfmpegAudioDecoder;

    invoke-virtual {p0, p1}, Landroidx/media3/decoder/ffmpeg/FfmpegAudioRenderer;->getOutputFormat(Landroidx/media3/decoder/ffmpeg/FfmpegAudioDecoder;)Landroidx/media3/common/Format;

    move-result-object p0

    return-object p0
.end method

.method public getOutputFormat(Landroidx/media3/decoder/ffmpeg/FfmpegAudioDecoder;)Landroidx/media3/common/Format;
    .locals 1

    .line 168
    invoke-static {p1}, Lcom/google/common/base/Preconditions;->checkNotNull(Ljava/lang/Object;)Ljava/lang/Object;

    .line 169
    new-instance p0, Landroidx/media3/common/Format$Builder;

    invoke-direct {p0}, Landroidx/media3/common/Format$Builder;-><init>()V

    const-string v0, "audio/raw"

    .line 170
    invoke-virtual {p0, v0}, Landroidx/media3/common/Format$Builder;->setSampleMimeType(Ljava/lang/String;)Landroidx/media3/common/Format$Builder;

    move-result-object p0

    .line 171
    invoke-virtual {p1}, Landroidx/media3/decoder/ffmpeg/FfmpegAudioDecoder;->getChannelCount()I

    move-result v0

    invoke-virtual {p0, v0}, Landroidx/media3/common/Format$Builder;->setChannelCount(I)Landroidx/media3/common/Format$Builder;

    move-result-object p0

    .line 172
    invoke-virtual {p1}, Landroidx/media3/decoder/ffmpeg/FfmpegAudioDecoder;->getSampleRate()I

    move-result v0

    invoke-virtual {p0, v0}, Landroidx/media3/common/Format$Builder;->setSampleRate(I)Landroidx/media3/common/Format$Builder;

    move-result-object p0

    .line 173
    invoke-virtual {p1}, Landroidx/media3/decoder/ffmpeg/FfmpegAudioDecoder;->getEncoding()I

    move-result p1

    invoke-virtual {p0, p1}, Landroidx/media3/common/Format$Builder;->setPcmEncoding(I)Landroidx/media3/common/Format$Builder;

    move-result-object p0

    .line 174
    invoke-virtual {p0}, Landroidx/media3/common/Format$Builder;->build()Landroidx/media3/common/Format;

    move-result-object p0

    return-object p0
.end method

.method public supportsFormatInternal(Landroidx/media3/common/Format;)I
    .locals 3

    .line 134
    iget-object v0, p1, Landroidx/media3/common/Format;->sampleMimeType:Ljava/lang/String;

    invoke-static {v0}, Lcom/google/common/base/Preconditions;->checkNotNull(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/String;

    .line 135
    invoke-static {}, Landroidx/media3/decoder/ffmpeg/FfmpegLibrary;->isAvailable()Z

    move-result v1

    if-eqz v1, :cond_4

    invoke-static {v0}, Landroidx/media3/common/MimeTypes;->isAudio(Ljava/lang/String;)Z

    move-result v1

    if-nez v1, :cond_0

    goto :goto_1

    .line 137
    :cond_0
    invoke-static {v0}, Landroidx/media3/decoder/ffmpeg/FfmpegLibrary;->supportsFormat(Ljava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_3

    const/4 v0, 0x2

    .line 138
    invoke-direct {p0, p1, v0}, Landroidx/media3/decoder/ffmpeg/FfmpegAudioRenderer;->sinkSupportsFormat(Landroidx/media3/common/Format;I)Z

    move-result v1

    const/4 v2, 0x4

    if-nez v1, :cond_1

    .line 139
    invoke-direct {p0, p1, v2}, Landroidx/media3/decoder/ffmpeg/FfmpegAudioRenderer;->sinkSupportsFormat(Landroidx/media3/common/Format;I)Z

    move-result p0

    if-nez p0, :cond_1

    goto :goto_0

    .line 141
    :cond_1
    iget p0, p1, Landroidx/media3/common/Format;->cryptoType:I

    if-eqz p0, :cond_2

    return v0

    :cond_2
    return v2

    :cond_3
    :goto_0
    const/4 p0, 0x1

    return p0

    :cond_4
    :goto_1
    const/4 p0, 0x0

    return p0
.end method

.method public supportsMixedMimeTypeAdaptation()I
    .locals 0

    const/16 p0, 0x8

    return p0
.end method
