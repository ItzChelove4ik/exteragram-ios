.class public final synthetic Landroidx/media3/decoder/ffmpeg/ExperimentalFfmpegVideoDecoder$$ExternalSyntheticLambda0;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Landroidx/media3/decoder/DecoderOutputBuffer$Owner;


# instance fields
.field public final synthetic f$0:Landroidx/media3/decoder/ffmpeg/ExperimentalFfmpegVideoDecoder;


# direct methods
.method public synthetic constructor <init>(Landroidx/media3/decoder/ffmpeg/ExperimentalFfmpegVideoDecoder;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Landroidx/media3/decoder/ffmpeg/ExperimentalFfmpegVideoDecoder$$ExternalSyntheticLambda0;->f$0:Landroidx/media3/decoder/ffmpeg/ExperimentalFfmpegVideoDecoder;

    return-void
.end method


# virtual methods
.method public final releaseOutputBuffer(Landroidx/media3/decoder/DecoderOutputBuffer;)V
    .locals 0

    .line 0
    iget-object p0, p0, Landroidx/media3/decoder/ffmpeg/ExperimentalFfmpegVideoDecoder$$ExternalSyntheticLambda0;->f$0:Landroidx/media3/decoder/ffmpeg/ExperimentalFfmpegVideoDecoder;

    check-cast p1, Landroidx/media3/decoder/VideoDecoderOutputBuffer;

    invoke-static {p0, p1}, Landroidx/media3/decoder/ffmpeg/ExperimentalFfmpegVideoDecoder;->$r8$lambda$KsPZ8n3VnwUduE9ssA7RhBITdHg(Landroidx/media3/decoder/ffmpeg/ExperimentalFfmpegVideoDecoder;Landroidx/media3/decoder/DecoderOutputBuffer;)V

    return-void
.end method
