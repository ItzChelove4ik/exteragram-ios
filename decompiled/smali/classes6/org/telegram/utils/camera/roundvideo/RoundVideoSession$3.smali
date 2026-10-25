.class Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$3;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Landroidx/media3/common/Player$Listener;


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

    .line 1027
    iput-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$3;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onIsPlayingChanged(Z)V
    .locals 2

    .line 1041
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$3;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;

    invoke-static {v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->-$$Nest$fgetstate(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;)Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

    move-result-object v0

    sget-object v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;->PREVIEWING:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

    if-ne v0, v1, :cond_0

    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$3;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;

    invoke-static {p0, p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->-$$Nest$mupdatePreviewPlaying(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;Z)V

    :cond_0
    return-void
.end method

.method public onPlaybackStateChanged(I)V
    .locals 3

    .line 1046
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$3;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;

    invoke-static {v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->-$$Nest$fgetdiagnostics(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;)Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;

    move-result-object v0

    new-instance v1, Ljava/lang/StringBuilder;

    const-string v2, "preview playback state="

    invoke-direct {v1, v2}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;->log(Ljava/lang/String;)V

    .line 1047
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$3;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;

    invoke-static {v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->-$$Nest$fgetstate(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;)Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

    move-result-object v0

    sget-object v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;->PREVIEWING:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

    if-ne v0, v1, :cond_0

    const/4 v0, 0x4

    if-ne p1, v0, :cond_0

    iget-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$3;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;

    invoke-static {p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->-$$Nest$fgetplayer(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;)Landroidx/media3/exoplayer/ExoPlayer;

    move-result-object p1

    if-eqz p1, :cond_0

    .line 1050
    iget-object p1, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$3;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;

    invoke-static {p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->-$$Nest$fgetplayer(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;)Landroidx/media3/exoplayer/ExoPlayer;

    move-result-object p1

    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$3;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;

    invoke-static {v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->-$$Nest$fgettrimStartMs(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;)J

    move-result-wide v0

    invoke-interface {p1, v0, v1}, Landroidx/media3/common/Player;->seekTo(J)V

    .line 1051
    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$3;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;

    invoke-static {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->-$$Nest$fgetplayer(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;)Landroidx/media3/exoplayer/ExoPlayer;

    move-result-object p0

    invoke-interface {p0}, Landroidx/media3/common/Player;->play()V

    :cond_0
    return-void
.end method

.method public onPlayerError(Landroidx/media3/common/PlaybackException;)V
    .locals 3

    .line 1035
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$3;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;

    invoke-static {v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->-$$Nest$fgetdiagnostics(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;)Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;

    move-result-object v0

    new-instance v1, Ljava/lang/StringBuilder;

    const-string v2, "preview player error: code="

    invoke-direct {v1, v2}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    iget v2, p1, Landroidx/media3/common/PlaybackException;->errorCode:I

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoDiagnostics;->log(Ljava/lang/String;)V

    .line 1036
    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$3;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;

    invoke-static {p0, p1}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->-$$Nest$mfail(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;Ljava/lang/Exception;)V

    return-void
.end method

.method public onRenderedFirstFrame()V
    .locals 2

    .line 1030
    iget-object v0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$3;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;

    invoke-static {v0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->-$$Nest$fgetstate(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;)Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

    move-result-object v0

    sget-object v1, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;->PREVIEWING:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$State;

    if-ne v0, v1, :cond_0

    iget-object p0, p0, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$3;->this$0:Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;

    invoke-static {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;->-$$Nest$fgetlistener(Lorg/telegram/utils/camera/roundvideo/RoundVideoSession;)Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Listener;

    move-result-object p0

    invoke-interface {p0}, Lorg/telegram/utils/camera/roundvideo/RoundVideoSession$Listener;->onPreviewFirstFrame()V

    :cond_0
    return-void
.end method
