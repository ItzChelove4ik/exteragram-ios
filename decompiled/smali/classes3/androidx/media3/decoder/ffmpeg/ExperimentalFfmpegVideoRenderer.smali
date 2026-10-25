.class public final Landroidx/media3/decoder/ffmpeg/ExperimentalFfmpegVideoRenderer;
.super Landroidx/media3/exoplayer/video/DecoderVideoRenderer;
.source "SourceFile"


# static fields
.field public static final synthetic $r8$clinit:I

.field private static final DEFAULT_INPUT_BUFFER_SIZE:I


# instance fields
.field private decoder:Landroidx/media3/decoder/ffmpeg/ExperimentalFfmpegVideoDecoder;

.field private final numInputBuffers:I

.field private final numOutputBuffers:I

.field private final threads:I


# direct methods
.method static constructor <clinit>()V
    .locals 3

    const/16 v0, 0x500

    const/16 v1, 0x40

    .line 57
    invoke-static {v0, v1}, Landroidx/media3/common/util/Util;->ceilDivide(II)I

    move-result v0

    const/16 v2, 0x2d0

    invoke-static {v2, v1}, Landroidx/media3/common/util/Util;->ceilDivide(II)I

    move-result v1

    mul-int/2addr v0, v1

    mul-int/lit16 v0, v0, 0x1800

    div-int/lit8 v0, v0, 0x2

    sput v0, Landroidx/media3/decoder/ffmpeg/ExperimentalFfmpegVideoRenderer;->DEFAULT_INPUT_BUFFER_SIZE:I

    return-void
.end method

.method public constructor <init>(JLandroid/os/Handler;Landroidx/media3/exoplayer/video/VideoRendererEventListener;I)V
    .locals 10

    .line 96
    invoke-static {}, Ljava/lang/Runtime;->getRuntime()Ljava/lang/Runtime;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/Runtime;->availableProcessors()I

    move-result v7

    const/4 v8, 0x4

    const/4 v9, 0x4

    move-object v1, p0

    move-wide v2, p1

    move-object v4, p3

    move-object v5, p4

    move v6, p5

    .line 91
    invoke-direct/range {v1 .. v9}, Landroidx/media3/decoder/ffmpeg/ExperimentalFfmpegVideoRenderer;-><init>(JLandroid/os/Handler;Landroidx/media3/exoplayer/video/VideoRendererEventListener;IIII)V

    return-void
.end method

.method public constructor <init>(JLandroid/os/Handler;Landroidx/media3/exoplayer/video/VideoRendererEventListener;IIII)V
    .locals 0

    .line 120
    invoke-direct/range {p0 .. p5}, Landroidx/media3/exoplayer/video/DecoderVideoRenderer;-><init>(JLandroid/os/Handler;Landroidx/media3/exoplayer/video/VideoRendererEventListener;I)V

    .line 121
    iput p6, p0, Landroidx/media3/decoder/ffmpeg/ExperimentalFfmpegVideoRenderer;->threads:I

    .line 122
    iput p7, p0, Landroidx/media3/decoder/ffmpeg/ExperimentalFfmpegVideoRenderer;->numInputBuffers:I

    .line 123
    iput p8, p0, Landroidx/media3/decoder/ffmpeg/ExperimentalFfmpegVideoRenderer;->numOutputBuffers:I

    return-void
.end method


# virtual methods
.method public canReuseDecoder(Ljava/lang/String;Landroidx/media3/common/Format;Landroidx/media3/common/Format;)Landroidx/media3/exoplayer/DecoderReuseEvaluation;
    .locals 6

    .line 194
    new-instance v0, Landroidx/media3/exoplayer/DecoderReuseEvaluation;

    const/4 v4, 0x0

    const/16 v5, 0x8

    move-object v1, p1

    move-object v2, p2

    move-object v3, p3

    invoke-direct/range {v0 .. v5}, Landroidx/media3/exoplayer/DecoderReuseEvaluation;-><init>(Ljava/lang/String;Landroidx/media3/common/Format;Landroidx/media3/common/Format;II)V

    return-object v0
.end method

.method public createDecoder(Landroidx/media3/common/Format;Landroidx/media3/decoder/CryptoConfig;)Landroidx/media3/decoder/Decoder;
    .locals 6
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Landroidx/media3/common/Format;",
            "Landroidx/media3/decoder/CryptoConfig;",
            ")",
            "Landroidx/media3/decoder/Decoder<",
            "Landroidx/media3/decoder/DecoderInputBuffer;",
            "Landroidx/media3/decoder/VideoDecoderOutputBuffer;",
            "Landroidx/media3/decoder/ffmpeg/FfmpegDecoderException;",
            ">;"
        }
    .end annotation

    .line 154
    const-string p2, "createFfmpegVideoDecoder"

    invoke-static {p2}, Landroidx/media3/common/util/TraceUtil;->beginSection(Ljava/lang/String;)V

    .line 156
    iget p2, p1, Landroidx/media3/common/Format;->maxInputSize:I

    const/4 v0, -0x1

    if-eq p2, v0, :cond_0

    :goto_0
    move v3, p2

    goto :goto_1

    :cond_0
    sget p2, Landroidx/media3/decoder/ffmpeg/ExperimentalFfmpegVideoRenderer;->DEFAULT_INPUT_BUFFER_SIZE:I

    goto :goto_0

    .line 157
    :goto_1
    iget p2, p0, Landroidx/media3/decoder/ffmpeg/ExperimentalFfmpegVideoRenderer;->threads:I

    const/4 v0, 0x1

    invoke-static {p2, v0}, Ljava/lang/Math;->max(II)I

    move-result v4

    .line 158
    new-instance v0, Landroidx/media3/decoder/ffmpeg/ExperimentalFfmpegVideoDecoder;

    iget v1, p0, Landroidx/media3/decoder/ffmpeg/ExperimentalFfmpegVideoRenderer;->numInputBuffers:I

    iget v2, p0, Landroidx/media3/decoder/ffmpeg/ExperimentalFfmpegVideoRenderer;->numOutputBuffers:I

    move-object v5, p1

    invoke-direct/range {v0 .. v5}, Landroidx/media3/decoder/ffmpeg/ExperimentalFfmpegVideoDecoder;-><init>(IIIILandroidx/media3/common/Format;)V

    .line 162
    iput-object v0, p0, Landroidx/media3/decoder/ffmpeg/ExperimentalFfmpegVideoRenderer;->decoder:Landroidx/media3/decoder/ffmpeg/ExperimentalFfmpegVideoDecoder;

    .line 163
    invoke-static {}, Landroidx/media3/common/util/TraceUtil;->endSection()V

    return-object v0
.end method

.method public getName()Ljava/lang/String;
    .locals 0

    .line 128
    const-string p0, "ExperimentalFfmpegVideoRenderer"

    return-object p0
.end method

.method public renderOutputBufferToSurface(Landroidx/media3/decoder/VideoDecoderOutputBuffer;Landroid/view/Surface;)V
    .locals 0

    .line 171
    :try_start_0
    iget-object p0, p0, Landroidx/media3/decoder/ffmpeg/ExperimentalFfmpegVideoRenderer;->decoder:Landroidx/media3/decoder/ffmpeg/ExperimentalFfmpegVideoDecoder;

    if-eqz p0, :cond_0

    .line 175
    invoke-virtual {p0, p1, p2}, Landroidx/media3/decoder/ffmpeg/ExperimentalFfmpegVideoDecoder;->renderToSurface(Landroidx/media3/decoder/VideoDecoderOutputBuffer;Landroid/view/Surface;)V
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 179
    invoke-virtual {p1}, Landroidx/media3/decoder/VideoDecoderOutputBuffer;->release()V

    return-void

    :catchall_0
    move-exception p0

    goto :goto_0

    .line 172
    :cond_0
    :try_start_1
    new-instance p0, Landroidx/media3/decoder/ffmpeg/FfmpegDecoderException;

    const-string p2, "Failed to render output buffer to surface: decoder is not initialized."

    invoke-direct {p0, p2}, Landroidx/media3/decoder/ffmpeg/FfmpegDecoderException;-><init>(Ljava/lang/String;)V

    throw p0
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    .line 179
    :goto_0
    invoke-virtual {p1}, Landroidx/media3/decoder/VideoDecoderOutputBuffer;->release()V

    .line 180
    throw p0
.end method

.method public setDecoderOutputMode(I)V
    .locals 0

    .line 185
    iget-object p0, p0, Landroidx/media3/decoder/ffmpeg/ExperimentalFfmpegVideoRenderer;->decoder:Landroidx/media3/decoder/ffmpeg/ExperimentalFfmpegVideoDecoder;

    if-eqz p0, :cond_0

    .line 186
    invoke-virtual {p0, p1}, Landroidx/media3/decoder/ffmpeg/ExperimentalFfmpegVideoDecoder;->setOutputMode(I)V

    :cond_0
    return-void
.end method

.method public final supportsFormat(Landroidx/media3/common/Format;)I
    .locals 2

    .line 133
    iget-object p0, p1, Landroidx/media3/common/Format;->sampleMimeType:Ljava/lang/String;

    .line 134
    invoke-static {}, Landroidx/media3/decoder/ffmpeg/FfmpegLibrary;->isAvailable()Z

    move-result v0

    const/4 v1, 0x0

    if-eqz v0, :cond_3

    invoke-static {p0}, Landroidx/media3/common/MimeTypes;->isVideo(Ljava/lang/String;)Z

    move-result v0

    if-nez v0, :cond_0

    goto :goto_0

    .line 136
    :cond_0
    invoke-static {p0}, Landroidx/media3/decoder/ffmpeg/FfmpegLibrary;->supportsFormat(Ljava/lang/String;)Z

    move-result p0

    if-nez p0, :cond_1

    const/4 p0, 0x1

    .line 137
    invoke-static {p0}, Landroidx/media3/exoplayer/RendererCapabilities;->create(I)I

    move-result p0

    return p0

    .line 138
    :cond_1
    iget p0, p1, Landroidx/media3/common/Format;->cryptoType:I

    if-eqz p0, :cond_2

    const/4 p0, 0x2

    .line 139
    invoke-static {p0}, Landroidx/media3/exoplayer/RendererCapabilities;->create(I)I

    move-result p0

    return p0

    :cond_2
    const/4 p0, 0x4

    const/16 p1, 0x8

    .line 143
    invoke-static {p0, p1, v1}, Landroidx/media3/exoplayer/RendererCapabilities;->create(III)I

    move-result p0

    return p0

    .line 135
    :cond_3
    :goto_0
    invoke-static {v1}, Landroidx/media3/exoplayer/RendererCapabilities;->create(I)I

    move-result p0

    return p0
.end method
