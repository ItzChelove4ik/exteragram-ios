package androidx.media3.session;

import android.os.Bundle;
import androidx.media3.common.AudioAttributes;
import androidx.media3.common.DeviceInfo;
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
import java.util.Objects;

/* loaded from: classes4.dex */
class PlayerInfo {
    public static final PlayerInfo DEFAULT = null;
    private static final String FIELD_AUDIO_ATTRIBUTES = null;
    private static final String FIELD_CUE_GROUP = null;
    private static final String FIELD_CURRENT_TRACKS = null;
    private static final String FIELD_DEVICE_INFO = null;
    private static final String FIELD_DEVICE_MUTED = null;
    private static final String FIELD_DEVICE_VOLUME = null;
    private static final String FIELD_DISCONTINUITY_REASON = null;
    private static final String FIELD_IN_PROCESS_BINDER = null;
    private static final String FIELD_IS_LOADING = null;
    private static final String FIELD_IS_PLAYING = null;
    static final String FIELD_MAX_SEEK_TO_PREVIOUS_POSITION_MS = null;
    private static final String FIELD_MEDIA_ITEM_TRANSITION_REASON = null;
    private static final String FIELD_MEDIA_METADATA = null;
    static final String FIELD_NEW_POSITION_INFO = null;
    static final String FIELD_OLD_POSITION_INFO = null;
    private static final String FIELD_PLAYBACK_ERROR = null;
    private static final String FIELD_PLAYBACK_PARAMETERS = null;
    private static final String FIELD_PLAYBACK_STATE = null;
    private static final String FIELD_PLAYBACK_SUPPRESSION_REASON = null;
    private static final String FIELD_PLAYLIST_METADATA = null;
    private static final String FIELD_PLAY_WHEN_READY = null;
    private static final String FIELD_PLAY_WHEN_READY_CHANGE_REASON = null;
    private static final String FIELD_REPEAT_MODE = null;
    static final String FIELD_SEEK_BACK_INCREMENT_MS = null;
    static final String FIELD_SEEK_FORWARD_INCREMENT_MS = null;
    static final String FIELD_SESSION_POSITION_INFO = null;
    private static final String FIELD_SHUFFLE_MODE_ENABLED = null;
    private static final String FIELD_TIMELINE = null;
    private static final String FIELD_TIMELINE_CHANGE_REASON = null;
    private static final String FIELD_TRACK_SELECTION_PARAMETERS = null;
    private static final String FIELD_VIDEO_SIZE = null;
    private static final String FIELD_VOLUME = null;
    public final AudioAttributes audioAttributes;
    public final CueGroup cueGroup;
    public final Tracks currentTracks;
    public final DeviceInfo deviceInfo;
    public final boolean deviceMuted;
    public final int deviceVolume;
    public final int discontinuityReason;
    public final boolean isLoading;
    public final boolean isPlaying;
    public final long maxSeekToPreviousPositionMs;
    public final int mediaItemTransitionReason;
    public final MediaMetadata mediaMetadata;
    public final Player.PositionInfo newPositionInfo;
    public final Player.PositionInfo oldPositionInfo;
    public final boolean playWhenReady;
    public final int playWhenReadyChangeReason;
    public final PlaybackParameters playbackParameters;
    public final int playbackState;
    public final int playbackSuppressionReason;
    public final PlaybackException playerError;
    public final MediaMetadata playlistMetadata;
    public final int repeatMode;
    public final long seekBackIncrementMs;
    public final long seekForwardIncrementMs;
    public final SessionPositionInfo sessionPositionInfo;
    public final boolean shuffleModeEnabled;
    public final Timeline timeline;
    public final int timelineChangeReason;
    public final TrackSelectionParameters trackSelectionParameters;
    public final VideoSize videoSize;
    public final float volume;

    public static class Builder {
        private AudioAttributes audioAttributes;
        private CueGroup cueGroup;
        private Tracks currentTracks;
        private DeviceInfo deviceInfo;
        private boolean deviceMuted;
        private int deviceVolume;
        private int discontinuityReason;
        private boolean isLoading;
        private boolean isPlaying;
        private long maxSeekToPreviousPositionMs;
        private int mediaItemTransitionReason;
        private MediaMetadata mediaMetadata;
        private Player.PositionInfo newPositionInfo;
        private Player.PositionInfo oldPositionInfo;
        private boolean playWhenReady;
        private int playWhenReadyChangeReason;
        private PlaybackParameters playbackParameters;
        private int playbackState;
        private int playbackSuppressionReason;
        private PlaybackException playerError;
        private MediaMetadata playlistMetadata;
        private int repeatMode;
        private long seekBackIncrementMs;
        private long seekForwardIncrementMs;
        private SessionPositionInfo sessionPositionInfo;
        private boolean shuffleModeEnabled;
        private Timeline timeline;
        private int timelineChangeReason;
        private TrackSelectionParameters trackSelectionParameters;
        private VideoSize videoSize;
        private float volume;

        public Builder(PlayerInfo r3) {
            this.playerError = r3.playerError;
            this.mediaItemTransitionReason = r3.mediaItemTransitionReason;
            this.sessionPositionInfo = r3.sessionPositionInfo;
            this.oldPositionInfo = r3.oldPositionInfo;
            this.newPositionInfo = r3.newPositionInfo;
            this.discontinuityReason = r3.discontinuityReason;
            this.playbackParameters = r3.playbackParameters;
            this.repeatMode = r3.repeatMode;
            this.shuffleModeEnabled = r3.shuffleModeEnabled;
            this.timeline = r3.timeline;
            this.timelineChangeReason = r3.timelineChangeReason;
            this.videoSize = r3.videoSize;
            this.playlistMetadata = r3.playlistMetadata;
            this.volume = r3.volume;
            this.audioAttributes = r3.audioAttributes;
            this.cueGroup = r3.cueGroup;
            this.deviceInfo = r3.deviceInfo;
            this.deviceVolume = r3.deviceVolume;
            this.deviceMuted = r3.deviceMuted;
            this.playWhenReady = r3.playWhenReady;
            this.playWhenReadyChangeReason = r3.playWhenReadyChangeReason;
            this.isPlaying = r3.isPlaying;
            this.isLoading = r3.isLoading;
            this.playbackSuppressionReason = r3.playbackSuppressionReason;
            this.playbackState = r3.playbackState;
            this.mediaMetadata = r3.mediaMetadata;
            this.seekBackIncrementMs = r3.seekBackIncrementMs;
            this.seekForwardIncrementMs = r3.seekForwardIncrementMs;
            this.maxSeekToPreviousPositionMs = r3.maxSeekToPreviousPositionMs;
            this.currentTracks = r3.currentTracks;
            this.trackSelectionParameters = r3.trackSelectionParameters;
        }

        public PlayerInfo build() {
            if (this.timeline.isEmpty() == false) goto L5;
        L8:
            boolean r1 = true;
        L9:
            Assertions.checkState(r1);
            return new PlayerInfo(this.playerError, this.mediaItemTransitionReason, this.sessionPositionInfo, this.oldPositionInfo, this.newPositionInfo, this.discontinuityReason, this.playbackParameters, this.repeatMode, this.shuffleModeEnabled, this.videoSize, this.timeline, this.timelineChangeReason, this.playlistMetadata, this.volume, this.audioAttributes, this.cueGroup, this.deviceInfo, this.deviceVolume, this.deviceMuted, this.playWhenReady, this.playWhenReadyChangeReason, this.playbackSuppressionReason, this.playbackState, this.isPlaying, this.isLoading, this.mediaMetadata, this.seekBackIncrementMs, this.seekForwardIncrementMs, this.maxSeekToPreviousPositionMs, this.currentTracks, this.trackSelectionParameters);
        L5:
            if (this.sessionPositionInfo.positionInfo.mediaItemIndex < this.timeline.getWindowCount()) goto L8;
            r1 = false;
            goto L9
        }

        public Builder setAudioAttributes(AudioAttributes r1) {
            this.audioAttributes = r1;
            return this;
        }

        public Builder setCues(CueGroup r1) {
            this.cueGroup = r1;
            return this;
        }

        public Builder setCurrentTracks(Tracks r1) {
            this.currentTracks = r1;
            return this;
        }

        public Builder setDeviceInfo(DeviceInfo r1) {
            this.deviceInfo = r1;
            return this;
        }

        public Builder setDeviceMuted(boolean r1) {
            this.deviceMuted = r1;
            return this;
        }

        public Builder setDeviceVolume(int r1) {
            this.deviceVolume = r1;
            return this;
        }

        public Builder setDiscontinuityReason(int r1) {
            this.discontinuityReason = r1;
            return this;
        }

        public Builder setIsLoading(boolean r1) {
            this.isLoading = r1;
            return this;
        }

        public Builder setIsPlaying(boolean r1) {
            this.isPlaying = r1;
            return this;
        }

        public Builder setMediaItemTransitionReason(int r1) {
            this.mediaItemTransitionReason = r1;
            return this;
        }

        public Builder setMediaMetadata(MediaMetadata r1) {
            this.mediaMetadata = r1;
            return this;
        }

        public Builder setNewPositionInfo(Player.PositionInfo r1) {
            this.newPositionInfo = r1;
            return this;
        }

        public Builder setOldPositionInfo(Player.PositionInfo r1) {
            this.oldPositionInfo = r1;
            return this;
        }

        public Builder setPlayWhenReady(boolean r1) {
            this.playWhenReady = r1;
            return this;
        }

        public Builder setPlayWhenReadyChangeReason(int r1) {
            this.playWhenReadyChangeReason = r1;
            return this;
        }

        public Builder setPlaybackParameters(PlaybackParameters r1) {
            this.playbackParameters = r1;
            return this;
        }

        public Builder setPlaybackState(int r1) {
            this.playbackState = r1;
            return this;
        }

        public Builder setPlaybackSuppressionReason(int r1) {
            this.playbackSuppressionReason = r1;
            return this;
        }

        public Builder setPlayerError(PlaybackException r1) {
            this.playerError = r1;
            return this;
        }

        public Builder setPlaylistMetadata(MediaMetadata r1) {
            this.playlistMetadata = r1;
            return this;
        }

        public Builder setRepeatMode(int r1) {
            this.repeatMode = r1;
            return this;
        }

        public Builder setSessionPositionInfo(SessionPositionInfo r1) {
            this.sessionPositionInfo = r1;
            return this;
        }

        public Builder setShuffleModeEnabled(boolean r1) {
            this.shuffleModeEnabled = r1;
            return this;
        }

        public Builder setTimeline(Timeline r1) {
            this.timeline = r1;
            return this;
        }

        public Builder setTimelineChangeReason(int r1) {
            this.timelineChangeReason = r1;
            return this;
        }

        public Builder setTrackSelectionParameters(TrackSelectionParameters r1) {
            this.trackSelectionParameters = r1;
            return this;
        }

        public Builder setVideoSize(VideoSize r1) {
            this.videoSize = r1;
            return this;
        }

        public Builder setVolume(float r1) {
            this.volume = r1;
            return this;
        }
    }

    public static class BundlingExclusions {
        private static final String FIELD_ARE_CURRENT_TRACKS_EXCLUDED = null;
        private static final String FIELD_IS_TIMELINE_EXCLUDED = null;
        public static final BundlingExclusions NONE = null;
        public final boolean areCurrentTracksExcluded;
        public final boolean isTimelineExcluded;

        static {
            NONE = new BundlingExclusions(false, false);
            FIELD_IS_TIMELINE_EXCLUDED = Util.intToStringMaxRadix(0);
            FIELD_ARE_CURRENT_TRACKS_EXCLUDED = Util.intToStringMaxRadix(1);
        }

        public BundlingExclusions(boolean r1, boolean r2) {
            this.isTimelineExcluded = r1;
            this.areCurrentTracksExcluded = r2;
        }

        public boolean equals(Object r5) {
            if (this != r5) goto L6;
            return true;
        L6:
            if ((r5 instanceof BundlingExclusions) == true) goto L8;
            return false;
        L8:
            BundlingExclusions r52 = (BundlingExclusions) r5;
            if (this.isTimelineExcluded == r52.isTimelineExcluded) goto L11;
        L13:
            return false;
        L11:
            if (this.areCurrentTracksExcluded != r52.areCurrentTracksExcluded) goto L13;
            return true;
        }

        public int hashCode() {
            return Objects.hash(new Object[]{Boolean.valueOf(this.isTimelineExcluded), Boolean.valueOf(this.areCurrentTracksExcluded)});
        }

        public Bundle toBundle() {
            Bundle r0 = new Bundle();
            r0.putBoolean(FIELD_IS_TIMELINE_EXCLUDED, this.isTimelineExcluded);
            r0.putBoolean(FIELD_ARE_CURRENT_TRACKS_EXCLUDED, this.areCurrentTracksExcluded);
            return r0;
        }
    }

    static {
        SessionPositionInfo r3 = SessionPositionInfo.DEFAULT;
        Player.PositionInfo r4 = SessionPositionInfo.DEFAULT_POSITION_INFO;
        PlaybackParameters r7 = PlaybackParameters.DEFAULT;
        VideoSize r10 = VideoSize.UNKNOWN;
        Timeline r11 = Timeline.EMPTY;
        MediaMetadata r13 = MediaMetadata.EMPTY;
        DEFAULT = new PlayerInfo(null, 0, r3, r4, r4, 0, r7, 0, false, r10, r11, 0, r13, 1.0f, AudioAttributes.DEFAULT, CueGroup.EMPTY_TIME_ZERO, DeviceInfo.UNKNOWN, 0, false, false, 1, 0, 1, false, false, r13, 5000, 15000, 3000, Tracks.EMPTY, TrackSelectionParameters.DEFAULT);
        FIELD_PLAYBACK_PARAMETERS = Util.intToStringMaxRadix(1);
        FIELD_REPEAT_MODE = Util.intToStringMaxRadix(2);
        FIELD_SHUFFLE_MODE_ENABLED = Util.intToStringMaxRadix(3);
        FIELD_TIMELINE = Util.intToStringMaxRadix(4);
        FIELD_VIDEO_SIZE = Util.intToStringMaxRadix(5);
        FIELD_PLAYLIST_METADATA = Util.intToStringMaxRadix(6);
        FIELD_VOLUME = Util.intToStringMaxRadix(7);
        FIELD_AUDIO_ATTRIBUTES = Util.intToStringMaxRadix(8);
        FIELD_DEVICE_INFO = Util.intToStringMaxRadix(9);
        FIELD_DEVICE_VOLUME = Util.intToStringMaxRadix(10);
        FIELD_DEVICE_MUTED = Util.intToStringMaxRadix(11);
        FIELD_PLAY_WHEN_READY = Util.intToStringMaxRadix(12);
        FIELD_PLAY_WHEN_READY_CHANGE_REASON = Util.intToStringMaxRadix(13);
        FIELD_PLAYBACK_SUPPRESSION_REASON = Util.intToStringMaxRadix(14);
        FIELD_PLAYBACK_STATE = Util.intToStringMaxRadix(15);
        FIELD_IS_PLAYING = Util.intToStringMaxRadix(16);
        FIELD_IS_LOADING = Util.intToStringMaxRadix(17);
        FIELD_PLAYBACK_ERROR = Util.intToStringMaxRadix(18);
        FIELD_SESSION_POSITION_INFO = Util.intToStringMaxRadix(19);
        FIELD_MEDIA_ITEM_TRANSITION_REASON = Util.intToStringMaxRadix(20);
        FIELD_OLD_POSITION_INFO = Util.intToStringMaxRadix(21);
        FIELD_NEW_POSITION_INFO = Util.intToStringMaxRadix(22);
        FIELD_DISCONTINUITY_REASON = Util.intToStringMaxRadix(23);
        FIELD_CUE_GROUP = Util.intToStringMaxRadix(24);
        FIELD_MEDIA_METADATA = Util.intToStringMaxRadix(25);
        FIELD_SEEK_BACK_INCREMENT_MS = Util.intToStringMaxRadix(26);
        FIELD_SEEK_FORWARD_INCREMENT_MS = Util.intToStringMaxRadix(27);
        FIELD_MAX_SEEK_TO_PREVIOUS_POSITION_MS = Util.intToStringMaxRadix(28);
        FIELD_TRACK_SELECTION_PARAMETERS = Util.intToStringMaxRadix(29);
        FIELD_CURRENT_TRACKS = Util.intToStringMaxRadix(30);
        FIELD_TIMELINE_CHANGE_REASON = Util.intToStringMaxRadix(31);
        FIELD_IN_PROCESS_BINDER = Util.intToStringMaxRadix(32);
    }

    public PlayerInfo(PlaybackException r1, int r2, SessionPositionInfo r3, Player.PositionInfo r4, Player.PositionInfo r5, int r6, PlaybackParameters r7, int r8, boolean r9, VideoSize r10, Timeline r11, int r12, MediaMetadata r13, float r14, AudioAttributes r15, CueGroup r16, DeviceInfo r17, int r18, boolean r19, boolean r20, int r21, int r22, int r23, boolean r24, boolean r25, MediaMetadata r26, long r27, long r29, long r31, Tracks r33, TrackSelectionParameters r34) {
        this.playerError = r1;
        this.mediaItemTransitionReason = r2;
        this.sessionPositionInfo = r3;
        this.oldPositionInfo = r4;
        this.newPositionInfo = r5;
        this.discontinuityReason = r6;
        this.playbackParameters = r7;
        this.repeatMode = r8;
        this.shuffleModeEnabled = r9;
        this.videoSize = r10;
        this.timeline = r11;
        this.timelineChangeReason = r12;
        this.playlistMetadata = r13;
        this.volume = r14;
        this.audioAttributes = r15;
        this.cueGroup = r16;
        this.deviceInfo = r17;
        this.deviceVolume = r18;
        this.deviceMuted = r19;
        this.playWhenReady = r20;
        this.playWhenReadyChangeReason = r21;
        this.playbackSuppressionReason = r22;
        this.playbackState = r23;
        this.isPlaying = r24;
        this.isLoading = r25;
        this.mediaMetadata = r26;
        this.seekBackIncrementMs = r27;
        this.seekForwardIncrementMs = r29;
        this.maxSeekToPreviousPositionMs = r31;
        this.currentTracks = r33;
        this.trackSelectionParameters = r34;
    }

    private boolean isPlaying(int r1, boolean r2, int r3) {
        if (r1 != 3) goto L8;
        if (r2 == false) goto L10;
        if (r3 != 0) goto L11;
        return true;
    L11:
        return false;
    L10:
        return false;
    L8:
        return false;
    }

    public PlayerInfo copyWithAudioAttributes(AudioAttributes r2) {
        return new Builder(this).setAudioAttributes(r2).build();
    }

    public PlayerInfo copyWithCurrentTracks(Tracks r2) {
        return new Builder(this).setCurrentTracks(r2).build();
    }

    public PlayerInfo copyWithDeviceInfo(DeviceInfo r2) {
        return new Builder(this).setDeviceInfo(r2).build();
    }

    public PlayerInfo copyWithDeviceVolume(int r2, boolean r3) {
        return new Builder(this).setDeviceVolume(r2).setDeviceMuted(r3).build();
    }

    public PlayerInfo copyWithIsLoading(boolean r2) {
        return new Builder(this).setIsLoading(r2).build();
    }

    public PlayerInfo copyWithIsPlaying(boolean r2) {
        return new Builder(this).setIsPlaying(r2).build();
    }

    public PlayerInfo copyWithMediaItemTransitionReason(int r2) {
        return new Builder(this).setMediaItemTransitionReason(r2).build();
    }

    public PlayerInfo copyWithMediaMetadata(MediaMetadata r2) {
        return new Builder(this).setMediaMetadata(r2).build();
    }

    public PlayerInfo copyWithPlayWhenReady(boolean r2, int r3, int r4) {
        return new Builder(this).setPlayWhenReady(r2).setPlayWhenReadyChangeReason(r3).setPlaybackSuppressionReason(r4).setIsPlaying(isPlaying(this.playbackState, r2, r4)).build();
    }

    public PlayerInfo copyWithPlaybackParameters(PlaybackParameters r2) {
        return new Builder(this).setPlaybackParameters(r2).build();
    }

    public PlayerInfo copyWithPlaybackState(int r3, PlaybackException r4) {
        return new Builder(this).setPlayerError(r4).setPlaybackState(r3).setIsPlaying(isPlaying(r3, this.playWhenReady, this.playbackSuppressionReason)).build();
    }

    public PlayerInfo copyWithPlayerError(PlaybackException r2) {
        return new Builder(this).setPlayerError(r2).build();
    }

    public PlayerInfo copyWithPlaylistMetadata(MediaMetadata r2) {
        return new Builder(this).setPlaylistMetadata(r2).build();
    }

    public PlayerInfo copyWithPositionInfos(Player.PositionInfo r2, Player.PositionInfo r3, int r4) {
        return new Builder(this).setOldPositionInfo(r2).setNewPositionInfo(r3).setDiscontinuityReason(r4).build();
    }

    public PlayerInfo copyWithRepeatMode(int r2) {
        return new Builder(this).setRepeatMode(r2).build();
    }

    public PlayerInfo copyWithSessionPositionInfo(SessionPositionInfo r2) {
        return new Builder(this).setSessionPositionInfo(r2).build();
    }

    public PlayerInfo copyWithShuffleModeEnabled(boolean r2) {
        return new Builder(this).setShuffleModeEnabled(r2).build();
    }

    public PlayerInfo copyWithTimelineAndSessionPositionInfo(Timeline r2, SessionPositionInfo r3, int r4) {
        return new Builder(this).setTimeline(r2).setSessionPositionInfo(r3).setTimelineChangeReason(r4).build();
    }

    public PlayerInfo copyWithTrackSelectionParameters(TrackSelectionParameters r2) {
        return new Builder(this).setTrackSelectionParameters(r2).build();
    }

    public PlayerInfo copyWithVideoSize(VideoSize r2) {
        return new Builder(this).setVideoSize(r2).build();
    }

    public PlayerInfo copyWithVolume(float r2) {
        return new Builder(this).setVolume(r2).build();
    }

    public PlayerInfo filterByAvailableCommands(Player.Commands r5, boolean r6, boolean r7) {
        Builder r0 = new Builder(this);
        boolean r1 = r5.contains(16);
        boolean r2 = r5.contains(17);
        r0.setSessionPositionInfo(this.sessionPositionInfo.filterByAvailableCommands(r1, r2));
        r0.setOldPositionInfo(this.oldPositionInfo.filterByAvailableCommands(r1, r2));
        r0.setNewPositionInfo(this.newPositionInfo.filterByAvailableCommands(r1, r2));
        if (r2 == true) goto L8;
        if (r1 == false) goto L8;
        if (this.timeline.isEmpty() == true) goto L8;
        r0.setTimeline(this.timeline.copyWithSingleWindow(this.sessionPositionInfo.positionInfo.mediaItemIndex));
    L12:
        if (r5.contains(18) == true) goto L15;
        r0.setPlaylistMetadata(MediaMetadata.EMPTY);
    L15:
        if (r5.contains(22) == true) goto L18;
        r0.setVolume(1.0f);
    L18:
        if (r5.contains(21) == true) goto L21;
        r0.setAudioAttributes(AudioAttributes.DEFAULT);
    L21:
        if (r5.contains(28) == true) goto L24;
        r0.setCues(CueGroup.EMPTY_TIME_ZERO);
    L24:
        if (r5.contains(23) == true) goto L27;
        r0.setDeviceVolume(0).setDeviceMuted(false);
    L27:
        if (r5.contains(18) == true) goto L29;
        r0.setMediaMetadata(MediaMetadata.EMPTY);
    L29:
        if (r7 == false) goto L31;
    L32:
        r0.setCurrentTracks(Tracks.EMPTY);
    L34:
        return r0.build();
    L31:
        if (r5.contains(30) == true) goto L34;
    L8:
        if (r6 == true) goto L10;
        if (r2 == true) goto L12;
    L10:
        r0.setTimeline(Timeline.EMPTY);
        goto L12
    }

    public Bundle toBundleForRemoteProcess(int r9) {
        Bundle r0 = new Bundle();
        PlaybackException r1 = this.playerError;
        if (r1 == null) goto L5;
        r0.putBundle(FIELD_PLAYBACK_ERROR, r1.toBundle());
    L5:
        int r12 = this.mediaItemTransitionReason;
        if (r12 == 0) goto L9;
        r0.putInt(FIELD_MEDIA_ITEM_TRANSITION_REASON, r12);
    L9:
        if (r9 >= 3) goto L11;
    L12:
        r0.putBundle(FIELD_SESSION_POSITION_INFO, this.sessionPositionInfo.toBundle(r9));
    L13:
        if (r9 >= 3) goto L15;
    L16:
        r0.putBundle(FIELD_OLD_POSITION_INFO, this.oldPositionInfo.toBundle(r9));
    L17:
        if (r9 >= 3) goto L19;
    L20:
        r0.putBundle(FIELD_NEW_POSITION_INFO, this.newPositionInfo.toBundle(r9));
    L21:
        int r13 = this.discontinuityReason;
        if (r13 == 0) goto L25;
        r0.putInt(FIELD_DISCONTINUITY_REASON, r13);
    L25:
        if (this.playbackParameters.equals(PlaybackParameters.DEFAULT) == true) goto L27;
        r0.putBundle(FIELD_PLAYBACK_PARAMETERS, this.playbackParameters.toBundle());
    L27:
        int r14 = this.repeatMode;
        if (r14 == 0) goto L30;
        r0.putInt(FIELD_REPEAT_MODE, r14);
    L30:
        boolean r15 = this.shuffleModeEnabled;
        if (r15 == false) goto L34;
        r0.putBoolean(FIELD_SHUFFLE_MODE_ENABLED, r15);
    L34:
        if (this.timeline.equals(Timeline.EMPTY) == true) goto L36;
        r0.putBundle(FIELD_TIMELINE, this.timeline.toBundle());
    L36:
        int r16 = this.timelineChangeReason;
        if (r16 == 0) goto L40;
        r0.putInt(FIELD_TIMELINE_CHANGE_REASON, r16);
    L40:
        if (this.videoSize.equals(VideoSize.UNKNOWN) == true) goto L42;
        r0.putBundle(FIELD_VIDEO_SIZE, this.videoSize.toBundle());
    L42:
        MediaMetadata r17 = this.playlistMetadata;
        MediaMetadata r2 = MediaMetadata.EMPTY;
        if (r17.equals(r2) == true) goto L45;
        r0.putBundle(FIELD_PLAYLIST_METADATA, this.playlistMetadata.toBundle());
    L45:
        float r18 = this.volume;
        if (r18 == 1.0f) goto L49;
        r0.putFloat(FIELD_VOLUME, r18);
    L49:
        if (this.audioAttributes.equals(AudioAttributes.DEFAULT) == true) goto L52;
        r0.putBundle(FIELD_AUDIO_ATTRIBUTES, this.audioAttributes.toBundle());
    L52:
        if (this.cueGroup.equals(CueGroup.EMPTY_TIME_ZERO) == true) goto L55;
        r0.putBundle(FIELD_CUE_GROUP, this.cueGroup.toBundle());
    L55:
        if (this.deviceInfo.equals(DeviceInfo.UNKNOWN) == true) goto L57;
        r0.putBundle(FIELD_DEVICE_INFO, this.deviceInfo.toBundle());
    L57:
        int r19 = this.deviceVolume;
        if (r19 == 0) goto L60;
        r0.putInt(FIELD_DEVICE_VOLUME, r19);
    L60:
        boolean r110 = this.deviceMuted;
        if (r110 == false) goto L63;
        r0.putBoolean(FIELD_DEVICE_MUTED, r110);
    L63:
        boolean r111 = this.playWhenReady;
        if (r111 == false) goto L66;
        r0.putBoolean(FIELD_PLAY_WHEN_READY, r111);
    L66:
        int r112 = this.playWhenReadyChangeReason;
        if (r112 == 1) goto L69;
        r0.putInt(FIELD_PLAY_WHEN_READY_CHANGE_REASON, r112);
    L69:
        int r113 = this.playbackSuppressionReason;
        if (r113 == 0) goto L72;
        r0.putInt(FIELD_PLAYBACK_SUPPRESSION_REASON, r113);
    L72:
        int r114 = this.playbackState;
        if (r114 == 1) goto L75;
        r0.putInt(FIELD_PLAYBACK_STATE, r114);
    L75:
        boolean r115 = this.isPlaying;
        if (r115 == false) goto L78;
        r0.putBoolean(FIELD_IS_PLAYING, r115);
    L78:
        boolean r116 = this.isLoading;
        if (r116 == false) goto L82;
        r0.putBoolean(FIELD_IS_LOADING, r116);
    L82:
        if (this.mediaMetadata.equals(r2) == true) goto L84;
        r0.putBundle(FIELD_MEDIA_METADATA, this.mediaMetadata.toBundle());
    L84:
        long r117 = 0;
        if (r9 >= 6) goto L87;
        long r4 = 0;
    L88:
        long r6 = this.seekBackIncrementMs;
        if (r6 == r4) goto L91;
        r0.putLong(FIELD_SEEK_BACK_INCREMENT_MS, r6);
    L91:
        if (r9 >= 6) goto L93;
        long r42 = 0;
    L94:
        long r62 = this.seekForwardIncrementMs;
        if (r62 == r42) goto L97;
        r0.putLong(FIELD_SEEK_FORWARD_INCREMENT_MS, r62);
    L97:
        if (r9 < 6) goto L100;
        r117 = 3000;
    L100:
        long r3 = this.maxSeekToPreviousPositionMs;
        if (r3 == r117) goto L104;
        r0.putLong(FIELD_MAX_SEEK_TO_PREVIOUS_POSITION_MS, r3);
    L104:
        if (this.currentTracks.equals(Tracks.EMPTY) == true) goto L107;
        r0.putBundle(FIELD_CURRENT_TRACKS, this.currentTracks.toBundle());
    L107:
        if (this.trackSelectionParameters.equals(TrackSelectionParameters.DEFAULT) == true) goto L109;
        r0.putBundle(FIELD_TRACK_SELECTION_PARAMETERS, this.trackSelectionParameters.toBundle());
    L109:
        return r0;
    L93:
        r42 = 15000;
        goto L94
    L87:
        r4 = 5000;
        goto L88
    L19:
        if (SessionPositionInfo.DEFAULT_POSITION_INFO.equalsForBundling(this.newPositionInfo) == true) goto L21;
    L15:
        if (SessionPositionInfo.DEFAULT_POSITION_INFO.equalsForBundling(this.oldPositionInfo) == true) goto L17;
    L11:
        if (this.sessionPositionInfo.equals(SessionPositionInfo.DEFAULT) == true) goto L13;
        goto L12
    }
}
