.class public final synthetic Landroidx/media3/decoder/ffmpeg/FfmpegAudioDecoder$$ExternalSyntheticLambda0;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Landroidx/media3/decoder/DecoderOutputBuffer$Owner;


# instance fields
.field public final synthetic f$0:Landroidx/media3/decoder/ffmpeg/FfmpegAudioDecoder;


# direct methods
.method public synthetic constructor <init>(Landroidx/media3/decoder/ffmpeg/FfmpegAudioDecoder;)V
    .locals 0

    .line 0
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Landroidx/media3/decoder/ffmpeg/FfmpegAudioDecoder$$ExternalSyntheticLambda0;->f$0:Landroidx/media3/decoder/ffmpeg/FfmpegAudioDecoder;

    return-void
.end method


# virtual methods
.method public final releaseOutputBuffer(Landroidx/media3/decoder/DecoderOutputBuffer;)V
    .locals 0

    .line 0
    iget-object p0, p0, Landroidx/media3/decoder/ffmpeg/FfmpegAudioDecoder$$ExternalSyntheticLambda0;->f$0:Landroidx/media3/decoder/ffmpeg/FfmpegAudioDecoder;

    check-cast p1, Landroidx/media3/decoder/SimpleDecoderOutputBuffer;

    invoke-static {p0, p1}, Landroidx/media3/decoder/ffmpeg/FfmpegAudioDecoder;->$r8$lambda$gv1CCAcyN_eE2cDrXqQpFXTVmX8(Landroidx/media3/decoder/ffmpeg/FfmpegAudioDecoder;Landroidx/media3/decoder/DecoderOutputBuffer;)V

    return-void
.end method
