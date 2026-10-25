package androidx.media3.common;

import android.os.Looper;
import android.view.Surface;
import androidx.media3.common.Player;
import androidx.media3.common.text.Cue;
import androidx.media3.common.text.CueGroup;
import java.util.List;

/* loaded from: classes3.dex */
public abstract class ForwardingPlayer implements Player {
    private final Player player;

    public static final class ForwardingListener implements Player.Listener {
        private final ForwardingPlayer forwardingPlayer;
        private final Player.Listener listener;

        public ForwardingListener(ForwardingPlayer r1, Player.Listener r2) {
            this.forwardingPlayer = r1;
            this.listener = r2;
        }

        public boolean equals(Object r4) {
            if (this != r4) goto L6;
            return true;
        L6:
            if ((r4 instanceof ForwardingListener) == true) goto L8;
            return false;
        L8:
            ForwardingListener r42 = (ForwardingListener) r4;
            if (this.forwardingPlayer.equals(r42.forwardingPlayer) == true) goto L12;
            return false;
        L12:
            return this.listener.equals(r42.listener);
        }

        public int hashCode() {
            return (this.forwardingPlayer.hashCode() * 31) + this.listener.hashCode();
        }

        @Override // androidx.media3.common.Player.Listener
        public void onAudioAttributesChanged(AudioAttributes r1) {
            this.listener.onAudioAttributesChanged(r1);
        }

        @Override // androidx.media3.common.Player.Listener
        public void onAudioSessionIdChanged(int r1) {
            this.listener.onAudioSessionIdChanged(r1);
        }

        @Override // androidx.media3.common.Player.Listener
        public void onAvailableCommandsChanged(Player.Commands r1) {
            this.listener.onAvailableCommandsChanged(r1);
        }

        @Override // androidx.media3.common.Player.Listener
        public void onCues(CueGroup r1) {
            this.listener.onCues(r1);
        }

        @Override // androidx.media3.common.Player.Listener
        public void onCues(List<Cue> r1) {
            this.listener.onCues(r1);
        }

        @Override // androidx.media3.common.Player.Listener
        public void onDeviceInfoChanged(DeviceInfo r1) {
            this.listener.onDeviceInfoChanged(r1);
        }

        @Override // androidx.media3.common.Player.Listener
        public void onDeviceVolumeChanged(int r1, boolean r2) {
            this.listener.onDeviceVolumeChanged(r1, r2);
        }

        @Override // androidx.media3.common.Player.Listener
        public void onEvents(Player r1, Player.Events r2) {
            this.listener.onEvents(this.forwardingPlayer, r2);
        }

        @Override // androidx.media3.common.Player.Listener
        public void onIsLoadingChanged(boolean r1) {
            this.listener.onIsLoadingChanged(r1);
        }

        @Override // androidx.media3.common.Player.Listener
        public void onIsPlayingChanged(boolean r1) {
            this.listener.onIsPlayingChanged(r1);
        }

        @Override // androidx.media3.common.Player.Listener
        public void onLoadingChanged(boolean r1) {
            this.listener.onIsLoadingChanged(r1);
        }

        @Override // androidx.media3.common.Player.Listener
        public void onMediaItemTransition(MediaItem r1, int r2) {
            this.listener.onMediaItemTransition(r1, r2);
        }

        @Override // androidx.media3.common.Player.Listener
        public void onMediaMetadataChanged(MediaMetadata r1) {
            this.listener.onMediaMetadataChanged(r1);
        }

        @Override // androidx.media3.common.Player.Listener
        public void onMetadata(Metadata r1) {
            this.listener.onMetadata(r1);
        }

        @Override // androidx.media3.common.Player.Listener
        public void onPlayWhenReadyChanged(boolean r1, int r2) {
            this.listener.onPlayWhenReadyChanged(r1, r2);
        }

        @Override // androidx.media3.common.Player.Listener
        public void onPlaybackParametersChanged(PlaybackParameters r1) {
            this.listener.onPlaybackParametersChanged(r1);
        }

        @Override // androidx.media3.common.Player.Listener
        public void onPlaybackStateChanged(int r1) {
            this.listener.onPlaybackStateChanged(r1);
        }

        @Override // androidx.media3.common.Player.Listener
        public void onPlaybackSuppressionReasonChanged(int r1) {
            this.listener.onPlaybackSuppressionReasonChanged(r1);
        }

        @Override // androidx.media3.common.Player.Listener
        public void onPlayerError(PlaybackException r1) {
            this.listener.onPlayerError(r1);
        }

        @Override // androidx.media3.common.Player.Listener
        public void onPlayerErrorChanged(PlaybackException r1) {
            this.listener.onPlayerErrorChanged(r1);
        }

        @Override // androidx.media3.common.Player.Listener
        public void onPlayerStateChanged(boolean r1, int r2) {
            this.listener.onPlayerStateChanged(r1, r2);
        }

        @Override // androidx.media3.common.Player.Listener
        public void onPlaylistMetadataChanged(MediaMetadata r1) {
            this.listener.onPlaylistMetadataChanged(r1);
        }

        @Override // androidx.media3.common.Player.Listener
        public void onPositionDiscontinuity(int r1) {
            this.listener.onPositionDiscontinuity(r1);
        }

        @Override // androidx.media3.common.Player.Listener
        public void onPositionDiscontinuity(Player.PositionInfo r1, Player.PositionInfo r2, int r3) {
            this.listener.onPositionDiscontinuity(r1, r2, r3);
        }

        @Override // androidx.media3.common.Player.Listener
        public void onRenderedFirstFrame() {
            this.listener.onRenderedFirstFrame();
        }

        @Override // androidx.media3.common.Player.Listener
        public void onRepeatModeChanged(int r1) {
            this.listener.onRepeatModeChanged(r1);
        }

        @Override // androidx.media3.common.Player.Listener
        public void onShuffleModeEnabledChanged(boolean r1) {
            this.listener.onShuffleModeEnabledChanged(r1);
        }

        @Override // androidx.media3.common.Player.Listener
        public void onSkipSilenceEnabledChanged(boolean r1) {
            this.listener.onSkipSilenceEnabledChanged(r1);
        }

        @Override // androidx.media3.common.Player.Listener
        public void onSurfaceSizeChanged(int r1, int r2) {
            this.listener.onSurfaceSizeChanged(r1, r2);
        }

        @Override // androidx.media3.common.Player.Listener
        public void onTimelineChanged(Timeline r1, int r2) {
            this.listener.onTimelineChanged(r1, r2);
        }

        @Override // androidx.media3.common.Player.Listener
        public void onTrackSelectionParametersChanged(TrackSelectionParameters r1) {
            this.listener.onTrackSelectionParametersChanged(r1);
        }

        @Override // androidx.media3.common.Player.Listener
        public void onTracksChanged(Tracks r1) {
            this.listener.onTracksChanged(r1);
        }

        @Override // androidx.media3.common.Player.Listener
        public void onVideoSizeChanged(VideoSize r1) {
            this.listener.onVideoSizeChanged(r1);
        }

        @Override // androidx.media3.common.Player.Listener
        public void onVolumeChanged(float r1) {
            this.listener.onVolumeChanged(r1);
        }
    }

    public ForwardingPlayer(Player r1) {
        this.player = r1;
    }

    @Override // androidx.media3.common.Player
    public void addListener(Player.Listener r3) {
        this.player.addListener(new ForwardingListener(this, r3));
    }

    @Override // androidx.media3.common.Player
    public void addMediaItems(int r1, List<MediaItem> r2) {
        this.player.addMediaItems(r1, r2);
    }

    @Override // androidx.media3.common.Player
    public void addMediaItems(List<MediaItem> r1) {
        this.player.addMediaItems(r1);
    }

    @Override // androidx.media3.common.Player
    public boolean canAdvertiseSession() {
        return this.player.canAdvertiseSession();
    }

    @Override // androidx.media3.common.Player
    public void clearMediaItems() {
        this.player.clearMediaItems();
    }

    @Override // androidx.media3.common.Player
    @Deprecated
    public void decreaseDeviceVolume() {
        this.player.decreaseDeviceVolume();
    }

    @Override // androidx.media3.common.Player
    public void decreaseDeviceVolume(int r1) {
        this.player.decreaseDeviceVolume(r1);
    }

    @Override // androidx.media3.common.Player
    public Looper getApplicationLooper() {
        return this.player.getApplicationLooper();
    }

    @Override // androidx.media3.common.Player
    public AudioAttributes getAudioAttributes() {
        return this.player.getAudioAttributes();
    }

    @Override // androidx.media3.common.Player
    public Player.Commands getAvailableCommands() {
        return this.player.getAvailableCommands();
    }

    @Override // androidx.media3.common.Player
    public int getBufferedPercentage() {
        return this.player.getBufferedPercentage();
    }

    @Override // androidx.media3.common.Player
    public long getBufferedPosition() {
        return this.player.getBufferedPosition();
    }

    @Override // androidx.media3.common.Player
    public long getContentBufferedPosition() {
        return this.player.getContentBufferedPosition();
    }

    @Override // androidx.media3.common.Player
    public long getContentDuration() {
        return this.player.getContentDuration();
    }

    @Override // androidx.media3.common.Player
    public long getContentPosition() {
        return this.player.getContentPosition();
    }

    @Override // androidx.media3.common.Player
    public int getCurrentAdGroupIndex() {
        return this.player.getCurrentAdGroupIndex();
    }

    @Override // androidx.media3.common.Player
    public int getCurrentAdIndexInAdGroup() {
        return this.player.getCurrentAdIndexInAdGroup();
    }

    @Override // androidx.media3.common.Player
    public CueGroup getCurrentCues() {
        return this.player.getCurrentCues();
    }

    @Override // androidx.media3.common.Player
    public long getCurrentLiveOffset() {
        return this.player.getCurrentLiveOffset();
    }

    @Override // androidx.media3.common.Player
    public MediaItem getCurrentMediaItem() {
        return this.player.getCurrentMediaItem();
    }

    @Override // androidx.media3.common.Player
    public int getCurrentMediaItemIndex() {
        return this.player.getCurrentMediaItemIndex();
    }

    @Override // androidx.media3.common.Player
    public int getCurrentPeriodIndex() {
        return this.player.getCurrentPeriodIndex();
    }

    @Override // androidx.media3.common.Player
    public long getCurrentPosition() {
        return this.player.getCurrentPosition();
    }

    @Override // androidx.media3.common.Player
    public Timeline getCurrentTimeline() {
        return this.player.getCurrentTimeline();
    }

    @Override // androidx.media3.common.Player
    public Tracks getCurrentTracks() {
        return this.player.getCurrentTracks();
    }

    @Override // androidx.media3.common.Player
    public DeviceInfo getDeviceInfo() {
        return this.player.getDeviceInfo();
    }

    @Override // androidx.media3.common.Player
    public int getDeviceVolume() {
        return this.player.getDeviceVolume();
    }

    @Override // androidx.media3.common.Player
    public long getDuration() {
        return this.player.getDuration();
    }

    @Override // androidx.media3.common.Player
    public long getMaxSeekToPreviousPosition() {
        return this.player.getMaxSeekToPreviousPosition();
    }

    @Override // androidx.media3.common.Player
    public MediaMetadata getMediaMetadata() {
        return this.player.getMediaMetadata();
    }

    @Override // androidx.media3.common.Player
    public boolean getPlayWhenReady() {
        return this.player.getPlayWhenReady();
    }

    @Override // androidx.media3.common.Player
    public PlaybackParameters getPlaybackParameters() {
        return this.player.getPlaybackParameters();
    }

    @Override // androidx.media3.common.Player
    public int getPlaybackState() {
        return this.player.getPlaybackState();
    }

    @Override // androidx.media3.common.Player
    public int getPlaybackSuppressionReason() {
        return this.player.getPlaybackSuppressionReason();
    }

    @Override // androidx.media3.common.Player
    public PlaybackException getPlayerError() {
        return this.player.getPlayerError();
    }

    @Override // androidx.media3.common.Player
    public MediaMetadata getPlaylistMetadata() {
        return this.player.getPlaylistMetadata();
    }

    @Override // androidx.media3.common.Player
    public int getRepeatMode() {
        return this.player.getRepeatMode();
    }

    @Override // androidx.media3.common.Player
    public long getSeekBackIncrement() {
        return this.player.getSeekBackIncrement();
    }

    @Override // androidx.media3.common.Player
    public long getSeekForwardIncrement() {
        return this.player.getSeekForwardIncrement();
    }

    @Override // androidx.media3.common.Player
    public boolean getShuffleModeEnabled() {
        return this.player.getShuffleModeEnabled();
    }

    @Override // androidx.media3.common.Player
    public long getTotalBufferedDuration() {
        return this.player.getTotalBufferedDuration();
    }

    @Override // androidx.media3.common.Player
    public TrackSelectionParameters getTrackSelectionParameters() {
        return this.player.getTrackSelectionParameters();
    }

    @Override // androidx.media3.common.Player
    public VideoSize getVideoSize() {
        return this.player.getVideoSize();
    }

    @Override // androidx.media3.common.Player
    public float getVolume() {
        return this.player.getVolume();
    }

    public Player getWrappedPlayer() {
        return this.player;
    }

    @Override // androidx.media3.common.Player
    public boolean hasNextMediaItem() {
        return this.player.hasNextMediaItem();
    }

    @Override // androidx.media3.common.Player
    public boolean hasPreviousMediaItem() {
        return this.player.hasPreviousMediaItem();
    }

    @Override // androidx.media3.common.Player
    @Deprecated
    public void increaseDeviceVolume() {
        this.player.increaseDeviceVolume();
    }

    @Override // androidx.media3.common.Player
    public void increaseDeviceVolume(int r1) {
        this.player.increaseDeviceVolume(r1);
    }

    @Override // androidx.media3.common.Player
    public boolean isCommandAvailable(int r1) {
        return this.player.isCommandAvailable(r1);
    }

    @Override // androidx.media3.common.Player
    public boolean isCurrentMediaItemDynamic() {
        return this.player.isCurrentMediaItemDynamic();
    }

    @Override // androidx.media3.common.Player
    public boolean isCurrentMediaItemLive() {
        return this.player.isCurrentMediaItemLive();
    }

    @Override // androidx.media3.common.Player
    public boolean isCurrentMediaItemSeekable() {
        return this.player.isCurrentMediaItemSeekable();
    }

    @Override // androidx.media3.common.Player
    public boolean isDeviceMuted() {
        return this.player.isDeviceMuted();
    }

    @Override // androidx.media3.common.Player
    public boolean isLoading() {
        return this.player.isLoading();
    }

    @Override // androidx.media3.common.Player
    public boolean isPlaying() {
        return this.player.isPlaying();
    }

    @Override // androidx.media3.common.Player
    public boolean isPlayingAd() {
        return this.player.isPlayingAd();
    }

    @Override // androidx.media3.common.Player
    public void moveMediaItem(int r1, int r2) {
        this.player.moveMediaItem(r1, r2);
    }

    @Override // androidx.media3.common.Player
    public void moveMediaItems(int r1, int r2, int r3) {
        this.player.moveMediaItems(r1, r2, r3);
    }

    @Override // androidx.media3.common.Player
    public void pause() {
        this.player.pause();
    }

    @Override // androidx.media3.common.Player
    public void play() {
        this.player.play();
    }

    @Override // androidx.media3.common.Player
    public void prepare() {
        this.player.prepare();
    }

    @Override // androidx.media3.common.Player
    public void removeListener(Player.Listener r3) {
        this.player.removeListener(new ForwardingListener(this, r3));
    }

    @Override // androidx.media3.common.Player
    public void removeMediaItem(int r1) {
        this.player.removeMediaItem(r1);
    }

    @Override // androidx.media3.common.Player
    public void removeMediaItems(int r1, int r2) {
        this.player.removeMediaItems(r1, r2);
    }

    @Override // androidx.media3.common.Player
    public void replaceMediaItem(int r1, MediaItem r2) {
        this.player.replaceMediaItem(r1, r2);
    }

    @Override // androidx.media3.common.Player
    public void replaceMediaItems(int r1, int r2, List<MediaItem> r3) {
        this.player.replaceMediaItems(r1, r2, r3);
    }

    @Override // androidx.media3.common.Player
    public void seekBack() {
        this.player.seekBack();
    }

    @Override // androidx.media3.common.Player
    public void seekForward() {
        this.player.seekForward();
    }

    @Override // androidx.media3.common.Player
    public void seekTo(int r1, long r2) {
        this.player.seekTo(r1, r2);
    }

    @Override // androidx.media3.common.Player
    public void seekTo(long r1) {
        this.player.seekTo(r1);
    }

    @Override // androidx.media3.common.Player
    public void seekToDefaultPosition() {
        this.player.seekToDefaultPosition();
    }

    @Override // androidx.media3.common.Player
    public void seekToDefaultPosition(int r1) {
        this.player.seekToDefaultPosition(r1);
    }

    @Override // androidx.media3.common.Player
    public void seekToNext() {
        this.player.seekToNext();
    }

    @Override // androidx.media3.common.Player
    public void seekToNextMediaItem() {
        this.player.seekToNextMediaItem();
    }

    @Override // androidx.media3.common.Player
    public void seekToPrevious() {
        this.player.seekToPrevious();
    }

    @Override // androidx.media3.common.Player
    public void seekToPreviousMediaItem() {
        this.player.seekToPreviousMediaItem();
    }

    @Override // androidx.media3.common.Player
    public void setAudioAttributes(AudioAttributes r1, boolean r2) {
        this.player.setAudioAttributes(r1, r2);
    }

    @Override // androidx.media3.common.Player
    @Deprecated
    public void setDeviceMuted(boolean r1) {
        this.player.setDeviceMuted(r1);
    }

    @Override // androidx.media3.common.Player
    public void setDeviceMuted(boolean r1, int r2) {
        this.player.setDeviceMuted(r1, r2);
    }

    @Override // androidx.media3.common.Player
    @Deprecated
    public void setDeviceVolume(int r1) {
        this.player.setDeviceVolume(r1);
    }

    @Override // androidx.media3.common.Player
    public void setDeviceVolume(int r1, int r2) {
        this.player.setDeviceVolume(r1, r2);
    }

    @Override // androidx.media3.common.Player
    public void setMediaItem(MediaItem r1, long r2) {
        this.player.setMediaItem(r1, r2);
    }

    @Override // androidx.media3.common.Player
    public void setMediaItem(MediaItem r1, boolean r2) {
        this.player.setMediaItem(r1, r2);
    }

    @Override // androidx.media3.common.Player
    public void setMediaItems(List<MediaItem> r1, int r2, long r3) {
        this.player.setMediaItems(r1, r2, r3);
    }

    @Override // androidx.media3.common.Player
    public void setMediaItems(List<MediaItem> r1, boolean r2) {
        this.player.setMediaItems(r1, r2);
    }

    @Override // androidx.media3.common.Player
    public void setPlayWhenReady(boolean r1) {
        this.player.setPlayWhenReady(r1);
    }

    @Override // androidx.media3.common.Player
    public void setPlaybackParameters(PlaybackParameters r1) {
        this.player.setPlaybackParameters(r1);
    }

    @Override // androidx.media3.common.Player
    public void setPlaybackSpeed(float r1) {
        this.player.setPlaybackSpeed(r1);
    }

    @Override // androidx.media3.common.Player
    public void setPlaylistMetadata(MediaMetadata r1) {
        this.player.setPlaylistMetadata(r1);
    }

    @Override // androidx.media3.common.Player
    public void setRepeatMode(int r1) {
        this.player.setRepeatMode(r1);
    }

    @Override // androidx.media3.common.Player
    public void setShuffleModeEnabled(boolean r1) {
        this.player.setShuffleModeEnabled(r1);
    }

    @Override // androidx.media3.common.Player
    public void setTrackSelectionParameters(TrackSelectionParameters r1) {
        this.player.setTrackSelectionParameters(r1);
    }

    @Override // androidx.media3.common.Player
    public void setVideoSurface(Surface r1) {
        this.player.setVideoSurface(r1);
    }

    @Override // androidx.media3.common.Player
    public void setVolume(float r1) {
        this.player.setVolume(r1);
    }

    @Override // androidx.media3.common.Player
    public void stop() {
        this.player.stop();
    }
}
