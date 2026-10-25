.class Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$1;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Ljava/lang/Runnable;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x1
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;


# direct methods
.method public constructor <init>(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;)V
    .locals 0

    .line 560
    iput-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$1;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public run()V
    .locals 5

    .line 563
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$1;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;

    invoke-static {v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->-$$Nest$fgetstate(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;)Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

    move-result-object v0

    sget-object v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;->PREVIEWING:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

    if-ne v0, v1, :cond_3

    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$1;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;

    invoke-static {v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->-$$Nest$fgetplayer(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;)Landroidx/media3/exoplayer/ExoPlayer;

    move-result-object v0

    if-eqz v0, :cond_3

    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$1;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;

    invoke-static {v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->-$$Nest$fgetpreviewPlaying(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;)Z

    move-result v0

    if-nez v0, :cond_0

    goto :goto_0

    .line 564
    :cond_0
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$1;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;

    invoke-static {v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->-$$Nest$fgetplayer(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;)Landroidx/media3/exoplayer/ExoPlayer;

    move-result-object v0

    invoke-interface {v0}, Landroidx/media3/common/Player;->getCurrentPosition()J

    move-result-wide v0

    .line 565
    iget-object v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$1;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;

    invoke-static {v2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->-$$Nest$fgettrimStartMs(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;)J

    move-result-wide v2

    cmp-long v2, v0, v2

    if-ltz v2, :cond_1

    iget-object v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$1;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;

    invoke-static {v2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->-$$Nest$fgettrimEndMs(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;)J

    move-result-wide v2

    cmp-long v2, v0, v2

    if-ltz v2, :cond_2

    .line 566
    :cond_1
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$1;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;

    invoke-static {v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->-$$Nest$fgetplayer(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;)Landroidx/media3/exoplayer/ExoPlayer;

    move-result-object v0

    iget-object v1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$1;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;

    invoke-static {v1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->-$$Nest$fgettrimStartMs(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;)J

    move-result-wide v1

    invoke-interface {v0, v1, v2}, Landroidx/media3/common/Player;->seekTo(J)V

    .line 567
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$1;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;

    invoke-static {v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->-$$Nest$fgettrimStartMs(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;)J

    move-result-wide v0

    .line 569
    :cond_2
    iget-object v2, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$1;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;

    invoke-static {v2}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->-$$Nest$fgetlistener(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;)Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Listener;

    move-result-object v2

    iget-object v3, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$1;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;

    invoke-static {v3}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->-$$Nest$fgetrecordedDurationMs(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;)J

    move-result-wide v3

    invoke-interface {v2, v0, v1, v3, v4}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Listener;->onPreviewProgress(JJ)V

    .line 570
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$1;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;

    invoke-static {v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->-$$Nest$fgetmainHandler(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;)Landroid/os/Handler;

    move-result-object v0

    const-wide/16 v1, 0x21

    invoke-virtual {v0, p0, v1, v2}, Landroid/os/Handler;->postDelayed(Ljava/lang/Runnable;J)Z

    :cond_3
    :goto_0
    return-void
.end method
