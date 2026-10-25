.class final Landroidx/media3/decoder/ffmpeg/ExperimentalFfmpegVideoDecoder$PendingInput;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Landroidx/media3/decoder/ffmpeg/ExperimentalFfmpegVideoDecoder;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = "PendingInput"
.end annotation


# instance fields
.field public final data:Ljava/nio/ByteBuffer;

.field public final timeUs:J


# direct methods
.method public constructor <init>(Ljava/nio/ByteBuffer;J)V
    .locals 0

    .line 78
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 79
    iput-object p1, p0, Landroidx/media3/decoder/ffmpeg/ExperimentalFfmpegVideoDecoder$PendingInput;->data:Ljava/nio/ByteBuffer;

    .line 80
    iput-wide p2, p0, Landroidx/media3/decoder/ffmpeg/ExperimentalFfmpegVideoDecoder$PendingInput;->timeUs:J

    return-void
.end method
