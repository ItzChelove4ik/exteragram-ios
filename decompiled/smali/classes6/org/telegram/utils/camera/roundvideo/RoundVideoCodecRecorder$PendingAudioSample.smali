.class final Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder$PendingAudioSample;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = "PendingAudioSample"
.end annotation


# instance fields
.field final data:Ljava/nio/ByteBuffer;

.field final info:Landroid/media/MediaCodec$BufferInfo;


# direct methods
.method public constructor <init>(Ljava/nio/ByteBuffer;Landroid/media/MediaCodec$BufferInfo;)V
    .locals 0

    .line 726
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 727
    iput-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder$PendingAudioSample;->data:Ljava/nio/ByteBuffer;

    .line 728
    iput-object p2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoCodecRecorder$PendingAudioSample;->info:Landroid/media/MediaCodec$BufferInfo;

    return-void
.end method
