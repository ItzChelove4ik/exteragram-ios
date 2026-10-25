.class final Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer$PendingSample;
.super Ljava/lang/Object;
.source "SourceFile"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = "PendingSample"
.end annotation


# instance fields
.field final data:Ljava/nio/ByteBuffer;

.field final info:Landroid/media/MediaCodec$BufferInfo;

.field final video:Z


# direct methods
.method public constructor <init>(ZLjava/nio/ByteBuffer;Landroid/media/MediaCodec$BufferInfo;)V
    .locals 0

    .line 400
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 401
    iput-boolean p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer$PendingSample;->video:Z

    .line 402
    iput-object p2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer$PendingSample;->data:Ljava/nio/ByteBuffer;

    .line 403
    iput-object p3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoMp4Writer$PendingSample;->info:Landroid/media/MediaCodec$BufferInfo;

    return-void
.end method
