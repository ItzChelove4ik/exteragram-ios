package androidx.media3.session;

import android.os.Looper;
import android.os.SystemClock;
import android.view.Surface;
import androidx.media3.common.AudioAttributes;
import androidx.media3.common.DeviceInfo;
import androidx.media3.common.ForwardingPlayer;
import androidx.media3.common.MediaItem;
import androidx.media3.common.MediaMetadata;
import androidx.media3.common.PlaybackException;
import androidx.media3.common.PlaybackParameters;
import androidx.media3.common.Player;
import androidx.media3.common.Timeline;
import androidx.media3.common.TrackSelectionParameters;
import androidx.media3.common.Tracks;
import androidx.media3.common.VideoSize;
import androidx.media3.common.text.CueGroup;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import java.util.List;

/* loaded from: classes4.dex */
final class PlayerWrapper extends ForwardingPlayer {

    public static final class CurrentMediaItemOnlyTimeline extends Timeline {
        private static final Object UID = null;
        private final long durationUs;
        private final boolean isDynamic;
        private final boolean isPlaceholder;
        private final boolean isSeekable;
        private final MediaItem.LiveConfiguration liveConfiguration;
        private final MediaItem mediaItem;

        static {
            UID = new Object();
        }

        public CurrentMediaItemOnlyTimeline(PlayerWrapper r4) {
            this.mediaItem = r4.getCurrentMediaItem();
            this.isSeekable = r4.isCurrentMediaItemSeekable();
            this.isDynamic = r4.isCurrentMediaItemDynamic();
            if (r4.getCurrentTimeline().isEmpty() == false) goto L5;
        L7:
            boolean r0 = false;
        L8:
            this.isPlaceholder = r0;
            if (r4.isCurrentMediaItemLive() == false) goto L11;
            MediaItem.LiveConfiguration r02 = MediaItem.LiveConfiguration.UNSET;
        L12:
            this.liveConfiguration = r02;
            this.durationUs = Util.msToUs(r4.getContentDuration());
            return;
        L11:
            r02 = null;
            goto L12
        L5:
            if (r4.getCurrentTimeline().getWindow(r4.getCurrentMediaItemIndex(), new Timeline.Window()).isPlaceholder == false) goto L7;
            r0 = true;
            goto L8
        }

        @Override // androidx.media3.common.Timeline
        public int getIndexOfPeriod(Object r1) {
            if (UID.equals(r1) == false) goto L6;
            return 0;
        L6:
            return -1;
        }

        @Override // androidx.media3.common.Timeline
        public Timeline.Period getPeriod(int r9, Timeline.Period r10, boolean r11) {
            Object r1 = UID;
            r10.set(r1, r1, 0, this.durationUs, 0);
            r10.isPlaceholder = this.isPlaceholder;
            return r10;
        }

        @Override // androidx.media3.common.Timeline
        public int getPeriodCount() {
            return 1;
        }

        @Override // androidx.media3.common.Timeline
        public Object getUidOfPeriod(int r1) {
            return UID;
        }

        @Override // androidx.media3.common.Timeline
        public Timeline.Window getWindow(int r23, Timeline.Window r24, long r25) {
            r24.set(UID, this.mediaItem, null, -9223372036854775807L, -9223372036854775807L, -9223372036854775807L, this.isSeekable, this.isDynamic, this.liveConfiguration, 0, this.durationUs, 0, 0, 0);
            r24.isPlaceholder = this.isPlaceholder;
            return r24;
        }

        @Override // androidx.media3.common.Timeline
        public int getWindowCount() {
            return 1;
        }
    }

    public PlayerWrapper(Player r1) {
        super(r1);
    }

    private void verifyApplicationThread() {
        if (Looper.myLooper() != getApplicationLooper()) goto L5;
        boolean r1 = true;
    L6:
        Assertions.checkState(r1);
        return;
    L5:
        r1 = false;
        goto L6
    }

    @Override // androidx.media3.common.ForwardingPlayer, androidx.media3.common.Player
    public void addListener(Player.Listener r1) {
        verifyApplicationThread();
        super.addListener(r1);
    }

    @Override // androidx.media3.common.ForwardingPlayer, androidx.media3.common.Player
    public void addMediaItems(int r1, List<MediaItem> r2) {
        verifyApplicationThread();
        super.addMediaItems(r1, r2);
    }

    @Override // androidx.media3.common.ForwardingPlayer, androidx.media3.common.Player
    public void addMediaItems(List<MediaItem> r1) {
        verifyApplicationThread();
        super.addMediaItems(r1);
    }

    @Override // androidx.media3.common.ForwardingPlayer, androidx.media3.common.Player
    public void clearMediaItems() {
        verifyApplicationThread();
        super.clearMediaItems();
    }

    public PlayerInfo createInitialPlayerInfo() {
        return new PlayerInfo(getPlayerError(), 0, createSessionPositionInfo(), createPositionInfo(), createPositionInfo(), 0, getPlaybackParameters(), getRepeatMode(), getShuffleModeEnabled(), getVideoSize(), getCurrentTimelineWithCommandCheck(), 0, getPlaylistMetadataWithCommandCheck(), getVolumeWithCommandCheck(), getAudioAttributesWithCommandCheck(), getCurrentCuesWithCommandCheck(), getDeviceInfo(), getDeviceVolumeWithCommandCheck(), isDeviceMutedWithCommandCheck(), getPlayWhenReady(), 1, getPlaybackSuppressionReason(), getPlaybackState(), isPlaying(), isLoading(), getMediaMetadataWithCommandCheck(), getSeekBackIncrement(), getSeekForwardIncrement(), getMaxSeekToPreviousPosition(), getCurrentTracksWithCommandCheck(), getTrackSelectionParameters());
    }

    public Player.PositionInfo createPositionInfo() {
        boolean r1 = isCommandAvailable(16);
        boolean r2 = isCommandAvailable(17);
        int r4 = 0;
        if (r2 == false) goto L5;
        int r5 = getCurrentMediaItemIndex();
    L6:
        if (r1 == false) goto L8;
        MediaItem r6 = getCurrentMediaItem();
    L9:
        if (r2 == false) goto L11;
        r4 = getCurrentPeriodIndex();
    L11:
        int r8 = r4;
        long r9 = 0;
        if (r1 == false) goto L14;
        long r11 = getCurrentPosition();
    L15:
        if (r1 == false) goto L17;
        r9 = getContentPosition();
    L17:
        int r22 = -1;
        if (r1 == false) goto L20;
        int r13 = getCurrentAdGroupIndex();
    L21:
        if (r1 == false) goto L24;
        r22 = getCurrentAdIndexInAdGroup();
    L24:
        return new Player.PositionInfo(null, r5, r6, null, r8, r11, r9, r13, r22);
    L20:
        r13 = -1;
        goto L21
    L14:
        r11 = 0;
        goto L15
    L8:
        r6 = null;
        goto L9
    L5:
        r5 = 0;
        goto L6
    }

    public SessionPositionInfo createSessionPositionInfo() {
        boolean r0 = isCommandAvailable(16);
        Player.PositionInfo r2 = createPositionInfo();
        int r3 = 0;
        if (r0 == true) goto L5;
    L7:
        boolean r4 = false;
    L8:
        long r5 = SystemClock.elapsedRealtime();
        long r7 = -9223372036854775807L;
        if (r0 == false) goto L11;
        long r9 = getDuration();
    L12:
        long r11 = 0;
        if (r0 == false) goto L15;
        long r13 = getBufferedPosition();
    L16:
        if (r0 == false) goto L18;
        r3 = getBufferedPercentage();
    L18:
        if (r0 == false) goto L20;
        long r15 = getTotalBufferedDuration();
    L21:
        if (r0 == false) goto L23;
        long r17 = getCurrentLiveOffset();
    L24:
        if (r0 == false) goto L26;
        r7 = getContentDuration();
    L26:
        if (r0 == false) goto L28;
        r11 = getContentBufferedPosition();
    L28:
        int r10 = r3;
        return new SessionPositionInfo(r2, r4, r5, r9, r13, r10, r15, r17, r7, r11);
    L23:
        r17 = -9223372036854775807L;
        goto L24
    L20:
        r15 = 0;
        goto L21
    L15:
        r13 = 0;
        goto L16
    L11:
        r9 = -9223372036854775807L;
        goto L12
    L5:
        if (isPlayingAd() == false) goto L7;
        r4 = true;
        goto L8
    }

    @Override // androidx.media3.common.ForwardingPlayer, androidx.media3.common.Player
    @Deprecated
    public void decreaseDeviceVolume() {
        verifyApplicationThread();
        super.decreaseDeviceVolume();
    }

    @Override // androidx.media3.common.ForwardingPlayer, androidx.media3.common.Player
    public void decreaseDeviceVolume(int r1) {
        verifyApplicationThread();
        super.decreaseDeviceVolume(r1);
    }

    @Override // androidx.media3.common.ForwardingPlayer, androidx.media3.common.Player
    public AudioAttributes getAudioAttributes() {
        verifyApplicationThread();
        return super.getAudioAttributes();
    }

    public AudioAttributes getAudioAttributesWithCommandCheck() {
        if (isCommandAvailable(21) == false) goto L7;
        return getAudioAttributes();
    L7:
        return AudioAttributes.DEFAULT;
    }

    @Override // androidx.media3.common.ForwardingPlayer, androidx.media3.common.Player
    public Player.Commands getAvailableCommands() {
        verifyApplicationThread();
        return super.getAvailableCommands();
    }

    @Override // androidx.media3.common.ForwardingPlayer, androidx.media3.common.Player
    public int getBufferedPercentage() {
        verifyApplicationThread();
        return super.getBufferedPercentage();
    }

    @Override // androidx.media3.common.ForwardingPlayer, androidx.media3.common.Player
    public long getBufferedPosition() {
        verifyApplicationThread();
        return super.getBufferedPosition();
    }

    @Override // androidx.media3.common.ForwardingPlayer, androidx.media3.common.Player
    public long getContentBufferedPosition() {
        verifyApplicationThread();
        return super.getContentBufferedPosition();
    }

    @Override // androidx.media3.common.ForwardingPlayer, androidx.media3.common.Player
    public long getContentDuration() {
        verifyApplicationThread();
        return super.getContentDuration();
    }

    @Override // androidx.media3.common.ForwardingPlayer, androidx.media3.common.Player
    public long getContentPosition() {
        verifyApplicationThread();
        return super.getContentPosition();
    }

    @Override // androidx.media3.common.ForwardingPlayer, androidx.media3.common.Player
    public int getCurrentAdGroupIndex() {
        verifyApplicationThread();
        return super.getCurrentAdGroupIndex();
    }

    @Override // androidx.media3.common.ForwardingPlayer, androidx.media3.common.Player
    public int getCurrentAdIndexInAdGroup() {
        verifyApplicationThread();
        return super.getCurrentAdIndexInAdGroup();
    }

    @Override // androidx.media3.common.ForwardingPlayer, androidx.media3.common.Player
    public CueGroup getCurrentCues() {
        verifyApplicationThread();
        return super.getCurrentCues();
    }

    public CueGroup getCurrentCuesWithCommandCheck() {
        if (isCommandAvailable(28) == false) goto L7;
        return getCurrentCues();
    L7:
        return CueGroup.EMPTY_TIME_ZERO;
    }

    @Override // androidx.media3.common.ForwardingPlayer, androidx.media3.common.Player
    public long getCurrentLiveOffset() {
        verifyApplicationThread();
        return super.getCurrentLiveOffset();
    }

    @Override // androidx.media3.common.ForwardingPlayer, androidx.media3.common.Player
    public MediaItem getCurrentMediaItem() {
        verifyApplicationThread();
        return super.getCurrentMediaItem();
    }

    @Override // androidx.media3.common.ForwardingPlayer, androidx.media3.common.Player
    public int getCurrentMediaItemIndex() {
        verifyApplicationThread();
        return super.getCurrentMediaItemIndex();
    }

    public MediaItem getCurrentMediaItemWithCommandCheck() {
        if (isCommandAvailable(16) == true) goto L5;
        return null;
    L5:
        return getCurrentMediaItem();
    }

    @Override // androidx.media3.common.ForwardingPlayer, androidx.media3.common.Player
    public int getCurrentPeriodIndex() {
        verifyApplicationThread();
        return super.getCurrentPeriodIndex();
    }

    @Override // androidx.media3.common.ForwardingPlayer, androidx.media3.common.Player
    public long getCurrentPosition() {
        verifyApplicationThread();
        return super.getCurrentPosition();
    }

    @Override // androidx.media3.common.ForwardingPlayer, androidx.media3.common.Player
    public Timeline getCurrentTimeline() {
        verifyApplicationThread();
        return super.getCurrentTimeline();
    }

    public Timeline getCurrentTimelineWithCommandCheck() {
        if (isCommandAvailable(17) == false) goto L7;
        return getCurrentTimeline();
    L7:
        if (isCommandAvailable(16) == false) goto L15;
        if (getCurrentTimeline().isEmpty() == false) goto L13;
        return Timeline.EMPTY;
    L13:
        return new CurrentMediaItemOnlyTimeline(this);
    L15:
        return Timeline.EMPTY;
    }

    @Override // androidx.media3.common.ForwardingPlayer, androidx.media3.common.Player
    public Tracks getCurrentTracks() {
        verifyApplicationThread();
        return super.getCurrentTracks();
    }

    public Tracks getCurrentTracksWithCommandCheck() {
        if (isCommandAvailable(30) == false) goto L7;
        return getCurrentTracks();
    L7:
        return Tracks.EMPTY;
    }

    @Override // androidx.media3.common.ForwardingPlayer, androidx.media3.common.Player
    public DeviceInfo getDeviceInfo() {
        verifyApplicationThread();
        return super.getDeviceInfo();
    }

    @Override // androidx.media3.common.ForwardingPlayer, androidx.media3.common.Player
    public int getDeviceVolume() {
        verifyApplicationThread();
        return super.getDeviceVolume();
    }

    public int getDeviceVolumeWithCommandCheck() {
        if (isCommandAvailable(23) == true) goto L5;
        return 0;
    L5:
        return getDeviceVolume();
    }

    @Override // androidx.media3.common.ForwardingPlayer, androidx.media3.common.Player
    public long getDuration() {
        verifyApplicationThread();
        return super.getDuration();
    }

    public long getDurationWithCommandCheck() {
        if (isCommandAvailable(16) == true) goto L5;
        return -9223372036854775807L;
    L5:
        return getDuration();
    }

    @Override // androidx.media3.common.ForwardingPlayer, androidx.media3.common.Player
    public long getMaxSeekToPreviousPosition() {
        verifyApplicationThread();
        return super.getMaxSeekToPreviousPosition();
    }

    @Override // androidx.media3.common.ForwardingPlayer, androidx.media3.common.Player
    public MediaMetadata getMediaMetadata() {
        verifyApplicationThread();
        return super.getMediaMetadata();
    }

    public MediaMetadata getMediaMetadataWithCommandCheck() {
        if (isCommandAvailable(18) == false) goto L7;
        return getMediaMetadata();
    L7:
        return MediaMetadata.EMPTY;
    }

    @Override // androidx.media3.common.ForwardingPlayer, androidx.media3.common.Player
    public boolean getPlayWhenReady() {
        verifyApplicationThread();
        return super.getPlayWhenReady();
    }

    @Override // androidx.media3.common.ForwardingPlayer, androidx.media3.common.Player
    public PlaybackParameters getPlaybackParameters() {
        verifyApplicationThread();
        return super.getPlaybackParameters();
    }

    @Override // androidx.media3.common.ForwardingPlayer, androidx.media3.common.Player
    public int getPlaybackState() {
        verifyApplicationThread();
        return super.getPlaybackState();
    }

    @Override // androidx.media3.common.ForwardingPlayer, androidx.media3.common.Player
    public int getPlaybackSuppressionReason() {
        verifyApplicationThread();
        return super.getPlaybackSuppressionReason();
    }

    @Override // androidx.media3.common.ForwardingPlayer, androidx.media3.common.Player
    public PlaybackException getPlayerError() {
        verifyApplicationThread();
        return super.getPlayerError();
    }

    @Override // androidx.media3.common.ForwardingPlayer, androidx.media3.common.Player
    public MediaMetadata getPlaylistMetadata() {
        verifyApplicationThread();
        return super.getPlaylistMetadata();
    }

    public MediaMetadata getPlaylistMetadataWithCommandCheck() {
        if (isCommandAvailable(18) == false) goto L7;
        return getPlaylistMetadata();
    L7:
        return MediaMetadata.EMPTY;
    }

    @Override // androidx.media3.common.ForwardingPlayer, androidx.media3.common.Player
    public int getRepeatMode() {
        verifyApplicationThread();
        return super.getRepeatMode();
    }

    @Override // androidx.media3.common.ForwardingPlayer, androidx.media3.common.Player
    public long getSeekBackIncrement() {
        verifyApplicationThread();
        return super.getSeekBackIncrement();
    }

    @Override // androidx.media3.common.ForwardingPlayer, androidx.media3.common.Player
    public long getSeekForwardIncrement() {
        verifyApplicationThread();
        return super.getSeekForwardIncrement();
    }

    @Override // androidx.media3.common.ForwardingPlayer, androidx.media3.common.Player
    public boolean getShuffleModeEnabled() {
        verifyApplicationThread();
        return super.getShuffleModeEnabled();
    }

    @Override // androidx.media3.common.ForwardingPlayer, androidx.media3.common.Player
    public long getTotalBufferedDuration() {
        verifyApplicationThread();
        return super.getTotalBufferedDuration();
    }

    @Override // androidx.media3.common.ForwardingPlayer, androidx.media3.common.Player
    public TrackSelectionParameters getTrackSelectionParameters() {
        verifyApplicationThread();
        return super.getTrackSelectionParameters();
    }

    @Override // androidx.media3.common.ForwardingPlayer, androidx.media3.common.Player
    public VideoSize getVideoSize() {
        verifyApplicationThread();
        return super.getVideoSize();
    }

    @Override // androidx.media3.common.ForwardingPlayer, androidx.media3.common.Player
    public float getVolume() {
        verifyApplicationThread();
        return super.getVolume();
    }

    public float getVolumeWithCommandCheck() {
        if (isCommandAvailable(22) == true) goto L5;
        return 1.0f;
    L5:
        return getVolume();
    }

    @Override // androidx.media3.common.ForwardingPlayer, androidx.media3.common.Player
    public boolean hasNextMediaItem() {
        verifyApplicationThread();
        return super.hasNextMediaItem();
    }

    @Override // androidx.media3.common.ForwardingPlayer, androidx.media3.common.Player
    public boolean hasPreviousMediaItem() {
        verifyApplicationThread();
        return super.hasPreviousMediaItem();
    }

    @Override // androidx.media3.common.ForwardingPlayer, androidx.media3.common.Player
    @Deprecated
    public void increaseDeviceVolume() {
        verifyApplicationThread();
        super.increaseDeviceVolume();
    }

    @Override // androidx.media3.common.ForwardingPlayer, androidx.media3.common.Player
    public void increaseDeviceVolume(int r1) {
        verifyApplicationThread();
        super.increaseDeviceVolume(r1);
    }

    @Override // androidx.media3.common.ForwardingPlayer, androidx.media3.common.Player
    public boolean isCommandAvailable(int r1) {
        verifyApplicationThread();
        return super.isCommandAvailable(r1);
    }

    @Override // androidx.media3.common.ForwardingPlayer, androidx.media3.common.Player
    public boolean isCurrentMediaItemDynamic() {
        verifyApplicationThread();
        return super.isCurrentMediaItemDynamic();
    }

    @Override // androidx.media3.common.ForwardingPlayer, androidx.media3.common.Player
    public boolean isCurrentMediaItemLive() {
        verifyApplicationThread();
        return super.isCurrentMediaItemLive();
    }

    public boolean isCurrentMediaItemLiveWithCommandCheck() {
        if (isCommandAvailable(16) == true) goto L5;
        return false;
    L5:
        if (isCurrentMediaItemLive() == false) goto L10;
        return true;
    L10:
        return false;
    }

    @Override // androidx.media3.common.ForwardingPlayer, androidx.media3.common.Player
    public boolean isCurrentMediaItemSeekable() {
        verifyApplicationThread();
        return super.isCurrentMediaItemSeekable();
    }

    @Override // androidx.media3.common.ForwardingPlayer, androidx.media3.common.Player
    public boolean isDeviceMuted() {
        verifyApplicationThread();
        return super.isDeviceMuted();
    }

    public boolean isDeviceMutedWithCommandCheck() {
        if (isCommandAvailable(23) == true) goto L5;
        return false;
    L5:
        if (isDeviceMuted() == false) goto L10;
        return true;
    L10:
        return false;
    }

    @Override // androidx.media3.common.ForwardingPlayer, androidx.media3.common.Player
    public boolean isLoading() {
        verifyApplicationThread();
        return super.isLoading();
    }

    @Override // androidx.media3.common.ForwardingPlayer, androidx.media3.common.Player
    public boolean isPlaying() {
        verifyApplicationThread();
        return super.isPlaying();
    }

    @Override // androidx.media3.common.ForwardingPlayer, androidx.media3.common.Player
    public boolean isPlayingAd() {
        verifyApplicationThread();
        return super.isPlayingAd();
    }

    @Override // androidx.media3.common.ForwardingPlayer, androidx.media3.common.Player
    public void moveMediaItem(int r1, int r2) {
        verifyApplicationThread();
        super.moveMediaItem(r1, r2);
    }

    @Override // androidx.media3.common.ForwardingPlayer, androidx.media3.common.Player
    public void moveMediaItems(int r1, int r2, int r3) {
        verifyApplicationThread();
        super.moveMediaItems(r1, r2, r3);
    }

    @Override // androidx.media3.common.ForwardingPlayer, androidx.media3.common.Player
    public void pause() {
        verifyApplicationThread();
        super.pause();
    }

    @Override // androidx.media3.common.ForwardingPlayer, androidx.media3.common.Player
    public void play() {
        verifyApplicationThread();
        super.play();
    }

    public void playIfCommandAvailable() {
        if (isCommandAvailable(1) == false) goto L6;
        play();
        return;
    }

    @Override // androidx.media3.common.ForwardingPlayer, androidx.media3.common.Player
    public void prepare() {
        verifyApplicationThread();
        super.prepare();
    }

    public void prepareIfCommandAvailable() {
        if (isCommandAvailable(2) == false) goto L6;
        prepare();
        return;
    }

    @Override // androidx.media3.common.ForwardingPlayer, androidx.media3.common.Player
    public void removeListener(Player.Listener r1) {
        verifyApplicationThread();
        super.removeListener(r1);
    }

    @Override // androidx.media3.common.ForwardingPlayer, androidx.media3.common.Player
    public void removeMediaItem(int r1) {
        verifyApplicationThread();
        super.removeMediaItem(r1);
    }

    @Override // androidx.media3.common.ForwardingPlayer, androidx.media3.common.Player
    public void removeMediaItems(int r1, int r2) {
        verifyApplicationThread();
        super.removeMediaItems(r1, r2);
    }

    @Override // androidx.media3.common.ForwardingPlayer, androidx.media3.common.Player
    public void replaceMediaItem(int r1, MediaItem r2) {
        verifyApplicationThread();
        super.replaceMediaItem(r1, r2);
    }

    @Override // androidx.media3.common.ForwardingPlayer, androidx.media3.common.Player
    public void replaceMediaItems(int r1, int r2, List<MediaItem> r3) {
        verifyApplicationThread();
        super.replaceMediaItems(r1, r2, r3);
    }

    @Override // androidx.media3.common.ForwardingPlayer, androidx.media3.common.Player
    public void seekBack() {
        verifyApplicationThread();
        super.seekBack();
    }

    @Override // androidx.media3.common.ForwardingPlayer, androidx.media3.common.Player
    public void seekForward() {
        verifyApplicationThread();
        super.seekForward();
    }

    @Override // androidx.media3.common.ForwardingPlayer, androidx.media3.common.Player
    public void seekTo(int r1, long r2) {
        verifyApplicationThread();
        super.seekTo(r1, r2);
    }

    @Override // androidx.media3.common.ForwardingPlayer, androidx.media3.common.Player
    public void seekTo(long r1) {
        verifyApplicationThread();
        super.seekTo(r1);
    }

    @Override // androidx.media3.common.ForwardingPlayer, androidx.media3.common.Player
    public void seekToDefaultPosition() {
        verifyApplicationThread();
        super.seekToDefaultPosition();
    }

    @Override // androidx.media3.common.ForwardingPlayer, androidx.media3.common.Player
    public void seekToDefaultPosition(int r1) {
        verifyApplicationThread();
        super.seekToDefaultPosition(r1);
    }

    public void seekToDefaultPositionIfCommandAvailable() {
        if (isCommandAvailable(4) == false) goto L6;
        seekToDefaultPosition();
        return;
    }

    @Override // androidx.media3.common.ForwardingPlayer, androidx.media3.common.Player
    public void seekToNext() {
        verifyApplicationThread();
        super.seekToNext();
    }

    @Override // androidx.media3.common.ForwardingPlayer, androidx.media3.common.Player
    public void seekToNextMediaItem() {
        verifyApplicationThread();
        super.seekToNextMediaItem();
    }

    @Override // androidx.media3.common.ForwardingPlayer, androidx.media3.common.Player
    public void seekToPrevious() {
        verifyApplicationThread();
        super.seekToPrevious();
    }

    @Override // androidx.media3.common.ForwardingPlayer, androidx.media3.common.Player
    public void seekToPreviousMediaItem() {
        verifyApplicationThread();
        super.seekToPreviousMediaItem();
    }

    @Override // androidx.media3.common.ForwardingPlayer, androidx.media3.common.Player
    @Deprecated
    public void setDeviceMuted(boolean r1) {
        verifyApplicationThread();
        super.setDeviceMuted(r1);
    }

    @Override // androidx.media3.common.ForwardingPlayer, androidx.media3.common.Player
    public void setDeviceMuted(boolean r1, int r2) {
        verifyApplicationThread();
        super.setDeviceMuted(r1, r2);
    }

    @Override // androidx.media3.common.ForwardingPlayer, androidx.media3.common.Player
    @Deprecated
    public void setDeviceVolume(int r1) {
        verifyApplicationThread();
        super.setDeviceVolume(r1);
    }

    @Override // androidx.media3.common.ForwardingPlayer, androidx.media3.common.Player
    public void setDeviceVolume(int r1, int r2) {
        verifyApplicationThread();
        super.setDeviceVolume(r1, r2);
    }

    @Override // androidx.media3.common.ForwardingPlayer, androidx.media3.common.Player
    public void setMediaItem(MediaItem r1, long r2) {
        verifyApplicationThread();
        super.setMediaItem(r1, r2);
    }

    @Override // androidx.media3.common.ForwardingPlayer, androidx.media3.common.Player
    public void setMediaItem(MediaItem r1, boolean r2) {
        verifyApplicationThread();
        super.setMediaItem(r1, r2);
    }

    @Override // androidx.media3.common.ForwardingPlayer, androidx.media3.common.Player
    public void setMediaItems(List<MediaItem> r1, int r2, long r3) {
        verifyApplicationThread();
        super.setMediaItems(r1, r2, r3);
    }

    @Override // androidx.media3.common.ForwardingPlayer, androidx.media3.common.Player
    public void setMediaItems(List<MediaItem> r1, boolean r2) {
        verifyApplicationThread();
        super.setMediaItems(r1, r2);
    }

    @Override // androidx.media3.common.ForwardingPlayer, androidx.media3.common.Player
    public void setPlayWhenReady(boolean r1) {
        verifyApplicationThread();
        super.setPlayWhenReady(r1);
    }

    @Override // androidx.media3.common.ForwardingPlayer, androidx.media3.common.Player
    public void setPlaybackParameters(PlaybackParameters r1) {
        verifyApplicationThread();
        super.setPlaybackParameters(r1);
    }

    @Override // androidx.media3.common.ForwardingPlayer, androidx.media3.common.Player
    public void setPlaybackSpeed(float r1) {
        verifyApplicationThread();
        super.setPlaybackSpeed(r1);
    }

    @Override // androidx.media3.common.ForwardingPlayer, androidx.media3.common.Player
    public void setPlaylistMetadata(MediaMetadata r1) {
        verifyApplicationThread();
        super.setPlaylistMetadata(r1);
    }

    @Override // androidx.media3.common.ForwardingPlayer, androidx.media3.common.Player
    public void setRepeatMode(int r1) {
        verifyApplicationThread();
        super.setRepeatMode(r1);
    }

    @Override // androidx.media3.common.ForwardingPlayer, androidx.media3.common.Player
    public void setShuffleModeEnabled(boolean r1) {
        verifyApplicationThread();
        super.setShuffleModeEnabled(r1);
    }

    @Override // androidx.media3.common.ForwardingPlayer, androidx.media3.common.Player
    public void setTrackSelectionParameters(TrackSelectionParameters r1) {
        verifyApplicationThread();
        super.setTrackSelectionParameters(r1);
    }

    @Override // androidx.media3.common.ForwardingPlayer, androidx.media3.common.Player
    public void setVideoSurface(Surface r1) {
        verifyApplicationThread();
        super.setVideoSurface(r1);
    }

    @Override // androidx.media3.common.ForwardingPlayer, androidx.media3.common.Player
    public void setVolume(float r1) {
        verifyApplicationThread();
        super.setVolume(r1);
    }

    @Override // androidx.media3.common.ForwardingPlayer, androidx.media3.common.Player
    public void stop() {
        verifyApplicationThread();
        super.stop();
    }
}
