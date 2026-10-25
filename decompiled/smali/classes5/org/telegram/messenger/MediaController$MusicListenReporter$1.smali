.class Lorg/telegram/messenger/MediaController$MusicListenReporter$1;
.super Ljava/lang/Object;
.source "SourceFile"

# interfaces
.implements Landroidx/media3/common/Player$Listener;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lorg/telegram/messenger/MediaController$MusicListenReporter;->getPlayerListener(Landroidx/media3/exoplayer/ExoPlayer;)Landroidx/media3/common/Player$Listener;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x1
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lorg/telegram/messenger/MediaController$MusicListenReporter;

.field final synthetic val$player:Landroidx/media3/exoplayer/ExoPlayer;


# direct methods
.method public constructor <init>(Lorg/telegram/messenger/MediaController$MusicListenReporter;Landroidx/media3/exoplayer/ExoPlayer;)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()V"
        }
    .end annotation

    .line 7011
    iput-object p1, p0, Lorg/telegram/messenger/MediaController$MusicListenReporter$1;->this$0:Lorg/telegram/messenger/MediaController$MusicListenReporter;

    iput-object p2, p0, Lorg/telegram/messenger/MediaController$MusicListenReporter$1;->val$player:Landroidx/media3/exoplayer/ExoPlayer;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method private closeRange()V
    .locals 8

    .line 7013
    iget-object v0, p0, Lorg/telegram/messenger/MediaController$MusicListenReporter$1;->val$player:Landroidx/media3/exoplayer/ExoPlayer;

    invoke-interface {v0}, Landroidx/media3/common/Player;->getCurrentPosition()J

    move-result-wide v0

    .line 7014
    iget-object v2, p0, Lorg/telegram/messenger/MediaController$MusicListenReporter$1;->this$0:Lorg/telegram/messenger/MediaController$MusicListenReporter;

    invoke-static {v2}, Lorg/telegram/messenger/MediaController$MusicListenReporter;->-$$Nest$fgetrangeStart(Lorg/telegram/messenger/MediaController$MusicListenReporter;)J

    move-result-wide v2

    const-wide v4, -0x7fffffffffffffffL    # -4.9E-324

    cmp-long v2, v2, v4

    if-eqz v2, :cond_0

    iget-object v2, p0, Lorg/telegram/messenger/MediaController$MusicListenReporter$1;->this$0:Lorg/telegram/messenger/MediaController$MusicListenReporter;

    invoke-static {v2}, Lorg/telegram/messenger/MediaController$MusicListenReporter;->-$$Nest$fgetrangeStart(Lorg/telegram/messenger/MediaController$MusicListenReporter;)J

    move-result-wide v2

    cmp-long v2, v0, v2

    if-lez v2, :cond_0

    .line 7015
    iget-object v2, p0, Lorg/telegram/messenger/MediaController$MusicListenReporter$1;->this$0:Lorg/telegram/messenger/MediaController$MusicListenReporter;

    invoke-static {v2}, Lorg/telegram/messenger/MediaController$MusicListenReporter;->-$$Nest$fgetrangeStart(Lorg/telegram/messenger/MediaController$MusicListenReporter;)J

    move-result-wide v6

    invoke-static {v2, v6, v7, v0, v1}, Lorg/telegram/messenger/MediaController$MusicListenReporter;->-$$Nest$mlistenedRange(Lorg/telegram/messenger/MediaController$MusicListenReporter;JJ)V

    .line 7017
    :cond_0
    iget-object p0, p0, Lorg/telegram/messenger/MediaController$MusicListenReporter$1;->this$0:Lorg/telegram/messenger/MediaController$MusicListenReporter;

    invoke-static {p0, v4, v5}, Lorg/telegram/messenger/MediaController$MusicListenReporter;->-$$Nest$fputrangeStart(Lorg/telegram/messenger/MediaController$MusicListenReporter;J)V

    return-void
.end method


# virtual methods
.method public bridge synthetic onAudioAttributesChanged(Landroidx/media3/common/AudioAttributes;)V
    .locals 0

    .line 0
    invoke-super {p0, p1}, Landroidx/media3/common/Player$Listener;->onAudioAttributesChanged(Landroidx/media3/common/AudioAttributes;)V

    return-void
.end method

.method public bridge synthetic onAudioSessionIdChanged(I)V
    .locals 0

    .line 0
    invoke-super {p0, p1}, Landroidx/media3/common/Player$Listener;->onAudioSessionIdChanged(I)V

    return-void
.end method

.method public bridge synthetic onAvailableCommandsChanged(Landroidx/media3/common/Player$Commands;)V
    .locals 0

    .line 0
    invoke-super {p0, p1}, Landroidx/media3/common/Player$Listener;->onAvailableCommandsChanged(Landroidx/media3/common/Player$Commands;)V

    return-void
.end method

.method public bridge synthetic onCues(Landroidx/media3/common/text/CueGroup;)V
    .locals 0

    .line 0
    invoke-super {p0, p1}, Landroidx/media3/common/Player$Listener;->onCues(Landroidx/media3/common/text/CueGroup;)V

    return-void
.end method

.method public bridge synthetic onCues(Ljava/util/List;)V
    .locals 0
    .annotation runtime Ljava/lang/Deprecated;
    .end annotation

    .line 0
    invoke-super {p0, p1}, Landroidx/media3/common/Player$Listener;->onCues(Ljava/util/List;)V

    return-void
.end method

.method public bridge synthetic onDeviceInfoChanged(Landroidx/media3/common/DeviceInfo;)V
    .locals 0

    .line 0
    invoke-super {p0, p1}, Landroidx/media3/common/Player$Listener;->onDeviceInfoChanged(Landroidx/media3/common/DeviceInfo;)V

    return-void
.end method

.method public bridge synthetic onDeviceVolumeChanged(IZ)V
    .locals 0

    .line 0
    invoke-super {p0, p1, p2}, Landroidx/media3/common/Player$Listener;->onDeviceVolumeChanged(IZ)V

    return-void
.end method

.method public bridge synthetic onEvents(Landroidx/media3/common/Player;Landroidx/media3/common/Player$Events;)V
    .locals 0

    .line 0
    invoke-super {p0, p1, p2}, Landroidx/media3/common/Player$Listener;->onEvents(Landroidx/media3/common/Player;Landroidx/media3/common/Player$Events;)V

    return-void
.end method

.method public bridge synthetic onIsLoadingChanged(Z)V
    .locals 0

    .line 0
    invoke-super {p0, p1}, Landroidx/media3/common/Player$Listener;->onIsLoadingChanged(Z)V

    return-void
.end method

.method public onIsPlayingChanged(Z)V
    .locals 3

    if-eqz p1, :cond_0

    .line 7023
    iget-object v0, p0, Lorg/telegram/messenger/MediaController$MusicListenReporter$1;->this$0:Lorg/telegram/messenger/MediaController$MusicListenReporter;

    iget-object v1, p0, Lorg/telegram/messenger/MediaController$MusicListenReporter$1;->val$player:Landroidx/media3/exoplayer/ExoPlayer;

    invoke-interface {v1}, Landroidx/media3/common/Player;->getCurrentPosition()J

    move-result-wide v1

    invoke-static {v0, v1, v2}, Lorg/telegram/messenger/MediaController$MusicListenReporter;->-$$Nest$fputrangeStart(Lorg/telegram/messenger/MediaController$MusicListenReporter;J)V

    goto :goto_0

    .line 7025
    :cond_0
    invoke-direct {p0}, Lorg/telegram/messenger/MediaController$MusicListenReporter$1;->closeRange()V

    .line 7028
    :goto_0
    iget-object v0, p0, Lorg/telegram/messenger/MediaController$MusicListenReporter$1;->this$0:Lorg/telegram/messenger/MediaController$MusicListenReporter;

    invoke-static {v0}, Lorg/telegram/messenger/MediaController$MusicListenReporter;->-$$Nest$fgetreportRunnable(Lorg/telegram/messenger/MediaController$MusicListenReporter;)Ljava/lang/Runnable;

    move-result-object v0

    invoke-static {v0}, Lorg/telegram/messenger/AndroidUtilities;->cancelRunOnUIThread(Ljava/lang/Runnable;)V

    if-nez p1, :cond_1

    .line 7030
    iget-object p0, p0, Lorg/telegram/messenger/MediaController$MusicListenReporter$1;->this$0:Lorg/telegram/messenger/MediaController$MusicListenReporter;

    invoke-static {p0}, Lorg/telegram/messenger/MediaController$MusicListenReporter;->-$$Nest$fgetreportRunnable(Lorg/telegram/messenger/MediaController$MusicListenReporter;)Ljava/lang/Runnable;

    move-result-object p0

    const-wide/32 v0, 0xea60

    invoke-static {p0, v0, v1}, Lorg/telegram/messenger/AndroidUtilities;->runOnUIThread(Ljava/lang/Runnable;J)V

    :cond_1
    return-void
.end method

.method public bridge synthetic onLoadingChanged(Z)V
    .locals 0
    .annotation runtime Ljava/lang/Deprecated;
    .end annotation

    .line 0
    invoke-super {p0, p1}, Landroidx/media3/common/Player$Listener;->onLoadingChanged(Z)V

    return-void
.end method

.method public bridge synthetic onMaxSeekToPreviousPositionChanged(J)V
    .locals 0

    .line 0
    invoke-super {p0, p1, p2}, Landroidx/media3/common/Player$Listener;->onMaxSeekToPreviousPositionChanged(J)V

    return-void
.end method

.method public bridge synthetic onMediaItemTransition(Landroidx/media3/common/MediaItem;I)V
    .locals 0

    .line 0
    invoke-super {p0, p1, p2}, Landroidx/media3/common/Player$Listener;->onMediaItemTransition(Landroidx/media3/common/MediaItem;I)V

    return-void
.end method

.method public bridge synthetic onMediaMetadataChanged(Landroidx/media3/common/MediaMetadata;)V
    .locals 0

    .line 0
    invoke-super {p0, p1}, Landroidx/media3/common/Player$Listener;->onMediaMetadataChanged(Landroidx/media3/common/MediaMetadata;)V

    return-void
.end method

.method public bridge synthetic onMetadata(Landroidx/media3/common/Metadata;)V
    .locals 0

    .line 0
    invoke-super {p0, p1}, Landroidx/media3/common/Player$Listener;->onMetadata(Landroidx/media3/common/Metadata;)V

    return-void
.end method

.method public bridge synthetic onPlayWhenReadyChanged(ZI)V
    .locals 0

    .line 0
    invoke-super {p0, p1, p2}, Landroidx/media3/common/Player$Listener;->onPlayWhenReadyChanged(ZI)V

    return-void
.end method

.method public bridge synthetic onPlaybackParametersChanged(Landroidx/media3/common/PlaybackParameters;)V
    .locals 0

    .line 0
    invoke-super {p0, p1}, Landroidx/media3/common/Player$Listener;->onPlaybackParametersChanged(Landroidx/media3/common/PlaybackParameters;)V

    return-void
.end method

.method public bridge synthetic onPlaybackStateChanged(I)V
    .locals 0

    .line 0
    invoke-super {p0, p1}, Landroidx/media3/common/Player$Listener;->onPlaybackStateChanged(I)V

    return-void
.end method

.method public bridge synthetic onPlaybackSuppressionReasonChanged(I)V
    .locals 0

    .line 0
    invoke-super {p0, p1}, Landroidx/media3/common/Player$Listener;->onPlaybackSuppressionReasonChanged(I)V

    return-void
.end method

.method public bridge synthetic onPlayerError(Landroidx/media3/common/PlaybackException;)V
    .locals 0

    .line 0
    invoke-super {p0, p1}, Landroidx/media3/common/Player$Listener;->onPlayerError(Landroidx/media3/common/PlaybackException;)V

    return-void
.end method

.method public bridge synthetic onPlayerErrorChanged(Landroidx/media3/common/PlaybackException;)V
    .locals 0

    .line 0
    invoke-super {p0, p1}, Landroidx/media3/common/Player$Listener;->onPlayerErrorChanged(Landroidx/media3/common/PlaybackException;)V

    return-void
.end method

.method public bridge synthetic onPlayerStateChanged(ZI)V
    .locals 0
    .annotation runtime Ljava/lang/Deprecated;
    .end annotation

    .line 0
    invoke-super {p0, p1, p2}, Landroidx/media3/common/Player$Listener;->onPlayerStateChanged(ZI)V

    return-void
.end method

.method public bridge synthetic onPlaylistMetadataChanged(Landroidx/media3/common/MediaMetadata;)V
    .locals 0

    .line 0
    invoke-super {p0, p1}, Landroidx/media3/common/Player$Listener;->onPlaylistMetadataChanged(Landroidx/media3/common/MediaMetadata;)V

    return-void
.end method

.method public bridge synthetic onPositionDiscontinuity(I)V
    .locals 0
    .annotation runtime Ljava/lang/Deprecated;
    .end annotation

    .line 0
    invoke-super {p0, p1}, Landroidx/media3/common/Player$Listener;->onPositionDiscontinuity(I)V

    return-void
.end method

.method public onPositionDiscontinuity(Landroidx/media3/common/Player$PositionInfo;Landroidx/media3/common/Player$PositionInfo;I)V
    .locals 6

    const/4 v0, 0x1

    if-ne p3, v0, :cond_2

    .line 7041
    iget-object p3, p0, Lorg/telegram/messenger/MediaController$MusicListenReporter$1;->this$0:Lorg/telegram/messenger/MediaController$MusicListenReporter;

    invoke-static {p3}, Lorg/telegram/messenger/MediaController$MusicListenReporter;->-$$Nest$fgetrangeStart(Lorg/telegram/messenger/MediaController$MusicListenReporter;)J

    move-result-wide v0

    const-wide v2, -0x7fffffffffffffffL    # -4.9E-324

    cmp-long p3, v0, v2

    if-eqz p3, :cond_0

    .line 7042
    iget-object p3, p0, Lorg/telegram/messenger/MediaController$MusicListenReporter$1;->this$0:Lorg/telegram/messenger/MediaController$MusicListenReporter;

    invoke-static {p3}, Lorg/telegram/messenger/MediaController$MusicListenReporter;->-$$Nest$fgetrangeStart(Lorg/telegram/messenger/MediaController$MusicListenReporter;)J

    move-result-wide v0

    iget-wide v4, p1, Landroidx/media3/common/Player$PositionInfo;->positionMs:J

    invoke-static {p3, v0, v1, v4, v5}, Lorg/telegram/messenger/MediaController$MusicListenReporter;->-$$Nest$mlistenedRange(Lorg/telegram/messenger/MediaController$MusicListenReporter;JJ)V

    .line 7044
    :cond_0
    iget-object p1, p0, Lorg/telegram/messenger/MediaController$MusicListenReporter$1;->this$0:Lorg/telegram/messenger/MediaController$MusicListenReporter;

    iget-object p3, p0, Lorg/telegram/messenger/MediaController$MusicListenReporter$1;->val$player:Landroidx/media3/exoplayer/ExoPlayer;

    invoke-interface {p3}, Landroidx/media3/common/Player;->isPlaying()Z

    move-result p3

    if-eqz p3, :cond_1

    iget-wide v2, p2, Landroidx/media3/common/Player$PositionInfo;->positionMs:J

    :cond_1
    invoke-static {p1, v2, v3}, Lorg/telegram/messenger/MediaController$MusicListenReporter;->-$$Nest$fputrangeStart(Lorg/telegram/messenger/MediaController$MusicListenReporter;J)V

    .line 7046
    iget-object p0, p0, Lorg/telegram/messenger/MediaController$MusicListenReporter$1;->this$0:Lorg/telegram/messenger/MediaController$MusicListenReporter;

    invoke-static {p0}, Lorg/telegram/messenger/MediaController$MusicListenReporter;->-$$Nest$fgetreportRunnable(Lorg/telegram/messenger/MediaController$MusicListenReporter;)Ljava/lang/Runnable;

    move-result-object p0

    invoke-static {p0}, Lorg/telegram/messenger/AndroidUtilities;->cancelRunOnUIThread(Ljava/lang/Runnable;)V

    :cond_2
    return-void
.end method

.method public bridge synthetic onRenderedFirstFrame()V
    .locals 0

    .line 0
    invoke-super {p0}, Landroidx/media3/common/Player$Listener;->onRenderedFirstFrame()V

    return-void
.end method

.method public bridge synthetic onRepeatModeChanged(I)V
    .locals 0

    .line 0
    invoke-super {p0, p1}, Landroidx/media3/common/Player$Listener;->onRepeatModeChanged(I)V

    return-void
.end method

.method public bridge synthetic onSeekBackIncrementChanged(J)V
    .locals 0

    .line 0
    invoke-super {p0, p1, p2}, Landroidx/media3/common/Player$Listener;->onSeekBackIncrementChanged(J)V

    return-void
.end method

.method public bridge synthetic onSeekForwardIncrementChanged(J)V
    .locals 0

    .line 0
    invoke-super {p0, p1, p2}, Landroidx/media3/common/Player$Listener;->onSeekForwardIncrementChanged(J)V

    return-void
.end method

.method public bridge synthetic onShuffleModeEnabledChanged(Z)V
    .locals 0

    .line 0
    invoke-super {p0, p1}, Landroidx/media3/common/Player$Listener;->onShuffleModeEnabledChanged(Z)V

    return-void
.end method

.method public bridge synthetic onSkipSilenceEnabledChanged(Z)V
    .locals 0

    .line 0
    invoke-super {p0, p1}, Landroidx/media3/common/Player$Listener;->onSkipSilenceEnabledChanged(Z)V

    return-void
.end method

.method public bridge synthetic onSurfaceSizeChanged(II)V
    .locals 0

    .line 0
    invoke-super {p0, p1, p2}, Landroidx/media3/common/Player$Listener;->onSurfaceSizeChanged(II)V

    return-void
.end method

.method public bridge synthetic onTimelineChanged(Landroidx/media3/common/Timeline;I)V
    .locals 0

    .line 0
    invoke-super {p0, p1, p2}, Landroidx/media3/common/Player$Listener;->onTimelineChanged(Landroidx/media3/common/Timeline;I)V

    return-void
.end method

.method public bridge synthetic onTrackSelectionParametersChanged(Landroidx/media3/common/TrackSelectionParameters;)V
    .locals 0

    .line 0
    invoke-super {p0, p1}, Landroidx/media3/common/Player$Listener;->onTrackSelectionParametersChanged(Landroidx/media3/common/TrackSelectionParameters;)V

    return-void
.end method

.method public bridge synthetic onTracksChanged(Landroidx/media3/common/Tracks;)V
    .locals 0

    .line 0
    invoke-super {p0, p1}, Landroidx/media3/common/Player$Listener;->onTracksChanged(Landroidx/media3/common/Tracks;)V

    return-void
.end method

.method public bridge synthetic onVideoSizeChanged(Landroidx/media3/common/VideoSize;)V
    .locals 0

    .line 0
    invoke-super {p0, p1}, Landroidx/media3/common/Player$Listener;->onVideoSizeChanged(Landroidx/media3/common/VideoSize;)V

    return-void
.end method

.method public bridge synthetic onVolumeChanged(F)V
    .locals 0

    .line 0
    invoke-super {p0, p1}, Landroidx/media3/common/Player$Listener;->onVolumeChanged(F)V

    return-void
.end method
