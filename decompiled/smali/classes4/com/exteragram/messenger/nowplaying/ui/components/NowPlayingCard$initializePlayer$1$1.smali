.class public final Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard$initializePlayer$1$1;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Landroidx/media3/common/Player$Listener;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard;->initializePlayer()V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = null
.end annotation

.annotation runtime Lkotlin/Metadata;
    d1 = {
        "\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\u0008\u0002\n\u0002\u0010\u0008\n\u0000*\u0001\u0000\u0008\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u0008H\u0016\u00a8\u0006\t"
    }
    d2 = {
        "com/exteragram/messenger/nowplaying/ui/components/NowPlayingCard$initializePlayer$1$1",
        "Landroidx/media3/common/Player$Listener;",
        "onIsPlayingChanged",
        "",
        "isPlaying",
        "",
        "onPlaybackStateChanged",
        "playbackState",
        "",
        "TMessagesProj"
    }
    k = 0x1
    mv = {
        0x2,
        0x2,
        0x0
    }
    xi = 0x30
.end annotation


# instance fields
.field final synthetic this$0:Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard;


# direct methods
.method public constructor <init>(Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard;)V
    .locals 0

    iput-object p1, p0, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard$initializePlayer$1$1;->this$0:Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard;

    .line 373
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onIsPlayingChanged(Z)V
    .locals 1

    .line 375
    iget-object v0, p0, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard$initializePlayer$1$1;->this$0:Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard;

    invoke-static {v0, p1}, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard;->access$setPlaying$p(Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard;Z)V

    .line 376
    iget-object v0, p0, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard$initializePlayer$1$1;->this$0:Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard;

    invoke-static {v0}, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard;->access$updatePlayPauseButton(Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard;)V

    if-nez p1, :cond_0

    .line 378
    iget-object p0, p0, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard$initializePlayer$1$1;->this$0:Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard;

    invoke-static {p0}, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard;->access$abandonAudioFocus(Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard;)V

    :cond_0
    return-void
.end method

.method public onPlaybackStateChanged(I)V
    .locals 1

    const/4 v0, 0x4

    if-ne p1, v0, :cond_0

    .line 384
    iget-object p0, p0, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard$initializePlayer$1$1;->this$0:Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard;

    invoke-static {p0}, Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard;->access$abandonAudioFocus(Lcom/exteragram/messenger/nowplaying/ui/components/NowPlayingCard;)V

    :cond_0
    return-void
.end method
