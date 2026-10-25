package androidx.media3.common;

import android.os.Bundle;
import android.os.Looper;
import android.view.Surface;
import android.view.SurfaceView;
import android.view.TextureView;
import androidx.media3.common.FlagSet;
import androidx.media3.common.text.Cue;
import androidx.media3.common.text.CueGroup;
import androidx.media3.common.util.Util;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/* loaded from: classes3.dex */
public interface Player {

    /* renamed from: androidx.media3.common.Player$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static final class Commands {
        public static final Commands EMPTY = null;
        private static final String FIELD_COMMANDS = null;
        private final FlagSet flags;

        public static final class Builder {
            private static final int[] SUPPORTED_COMMANDS = null;
            private final FlagSet.Builder flagsBuilder;

            static {
                SUPPORTED_COMMANDS = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 31, 20, 21, 22, 23, 24, 25, 33, 26, 34, 35, 27, 28, 29, 30, 32};
            }

            public Builder() {
                this.flagsBuilder = new FlagSet.Builder();
            }

            private Builder(Commands r2) {
                FlagSet.Builder r0 = new FlagSet.Builder();
                this.flagsBuilder = r0;
                r0.addAll(Commands.access$000(r2));
            }

            public /* synthetic */ Builder(Commands r1, AnonymousClass1 r2) {
                this(r1);
            }

            public Builder add(int r2) {
                this.flagsBuilder.add(r2);
                return this;
            }

            public Builder addAll(Commands r2) {
                this.flagsBuilder.addAll(Commands.access$000(r2));
                return this;
            }

            public Builder addAll(int... r2) {
                this.flagsBuilder.addAll(r2);
                return this;
            }

            public Builder addAllCommands() {
                this.flagsBuilder.addAll(SUPPORTED_COMMANDS);
                return this;
            }

            public Builder addIf(int r2, boolean r3) {
                this.flagsBuilder.addIf(r2, r3);
                return this;
            }

            public Commands build() {
                return new Commands(this.flagsBuilder.build(), null);
            }
        }

        static {
            EMPTY = new Builder().build();
            FIELD_COMMANDS = Util.intToStringMaxRadix(0);
        }

        private Commands(FlagSet r1) {
            this.flags = r1;
        }

        public /* synthetic */ Commands(FlagSet r1, AnonymousClass1 r2) {
            this(r1);
        }

        public static /* synthetic */ FlagSet access$000(Commands r0) {
            return r0.flags;
        }

        public Builder buildUpon() {
            return new Builder(this, null);
        }

        public boolean contains(int r1) {
            return this.flags.contains(r1);
        }

        public boolean containsAny(int... r1) {
            return this.flags.containsAny(r1);
        }

        public boolean equals(Object r2) {
            if (this != r2) goto L6;
            return true;
        L6:
            if ((r2 instanceof Commands) == true) goto L10;
            return false;
        L10:
            return this.flags.equals(((Commands) r2).flags);
        }

        public int get(int r1) {
            return this.flags.get(r1);
        }

        public int hashCode() {
            return this.flags.hashCode();
        }

        public int size() {
            return this.flags.size();
        }

        public Bundle toBundle() {
            Bundle r0 = new Bundle();
            ArrayList<Integer> r1 = new ArrayList();
            int r2 = 0;
        L4:
            if (r2 >= this.flags.size()) goto L6;
            r1.add(Integer.valueOf(this.flags.get(r2)));
            r2 = r2 + 1;
            goto L4
        L6:
            r0.putIntegerArrayList(FIELD_COMMANDS, r1);
            return r0;
        }
    }

    public static final class Events {
        private final FlagSet flags;

        public Events(FlagSet r1) {
            this.flags = r1;
        }

        public boolean equals(Object r2) {
            if (this != r2) goto L6;
            return true;
        L6:
            if ((r2 instanceof Events) == true) goto L10;
            return false;
        L10:
            return this.flags.equals(((Events) r2).flags);
        }

        public int hashCode() {
            return this.flags.hashCode();
        }
    }

    public interface Listener {
        default void onAudioAttributesChanged(AudioAttributes r1) {
        }

        default void onAudioSessionIdChanged(int r1) {
        }

        default void onAvailableCommandsChanged(Commands r1) {
        }

        default void onCues(CueGroup r1) {
        }

        @Deprecated
        default void onCues(List<Cue> r1) {
        }

        default void onDeviceInfoChanged(DeviceInfo r1) {
        }

        default void onDeviceVolumeChanged(int r1, boolean r2) {
        }

        default void onEvents(Player r1, Events r2) {
        }

        default void onIsLoadingChanged(boolean r1) {
        }

        default void onIsPlayingChanged(boolean r1) {
        }

        @Deprecated
        default void onLoadingChanged(boolean r1) {
        }

        default void onMaxSeekToPreviousPositionChanged(long r1) {
        }

        default void onMediaItemTransition(MediaItem r1, int r2) {
        }

        default void onMediaMetadataChanged(MediaMetadata r1) {
        }

        default void onMetadata(Metadata r1) {
        }

        default void onPlayWhenReadyChanged(boolean r1, int r2) {
        }

        default void onPlaybackParametersChanged(PlaybackParameters r1) {
        }

        default void onPlaybackStateChanged(int r1) {
        }

        default void onPlaybackSuppressionReasonChanged(int r1) {
        }

        default void onPlayerError(PlaybackException r1) {
        }

        default void onPlayerErrorChanged(PlaybackException r1) {
        }

        @Deprecated
        default void onPlayerStateChanged(boolean r1, int r2) {
        }

        default void onPlaylistMetadataChanged(MediaMetadata r1) {
        }

        @Deprecated
        default void onPositionDiscontinuity(int r1) {
        }

        default void onPositionDiscontinuity(PositionInfo r1, PositionInfo r2, int r3) {
        }

        default void onRenderedFirstFrame() {
        }

        default void onRepeatModeChanged(int r1) {
        }

        default void onSeekBackIncrementChanged(long r1) {
        }

        default void onSeekForwardIncrementChanged(long r1) {
        }

        default void onShuffleModeEnabledChanged(boolean r1) {
        }

        default void onSkipSilenceEnabledChanged(boolean r1) {
        }

        default void onSurfaceSizeChanged(int r1, int r2) {
        }

        default void onTimelineChanged(Timeline r1, int r2) {
        }

        default void onTrackSelectionParametersChanged(TrackSelectionParameters r1) {
        }

        default void onTracksChanged(Tracks r1) {
        }

        default void onVideoSizeChanged(VideoSize r1) {
        }

        default void onVolumeChanged(float r1) {
        }
    }

    public static final class PositionInfo {
        private static final String FIELD_AD_GROUP_INDEX = null;
        private static final String FIELD_AD_INDEX_IN_AD_GROUP = null;
        static final String FIELD_CONTENT_POSITION_MS = null;
        private static final String FIELD_MEDIA_ITEM = null;
        static final String FIELD_MEDIA_ITEM_INDEX = null;
        static final String FIELD_PERIOD_INDEX = null;
        static final String FIELD_POSITION_MS = null;
        public final int adGroupIndex;
        public final int adIndexInAdGroup;
        public final long contentPositionMs;
        public final MediaItem mediaItem;
        public final int mediaItemIndex;
        public final int periodIndex;
        public final Object periodUid;
        public final long positionMs;

        @Deprecated
        public final int windowIndex;
        public final Object windowUid;

        static {
            FIELD_MEDIA_ITEM_INDEX = Util.intToStringMaxRadix(0);
            FIELD_MEDIA_ITEM = Util.intToStringMaxRadix(1);
            FIELD_PERIOD_INDEX = Util.intToStringMaxRadix(2);
            FIELD_POSITION_MS = Util.intToStringMaxRadix(3);
            FIELD_CONTENT_POSITION_MS = Util.intToStringMaxRadix(4);
            FIELD_AD_GROUP_INDEX = Util.intToStringMaxRadix(5);
            FIELD_AD_INDEX_IN_AD_GROUP = Util.intToStringMaxRadix(6);
        }

        public PositionInfo(Object r1, int r2, MediaItem r3, Object r4, int r5, long r6, long r8, int r10, int r11) {
            this.windowUid = r1;
            this.windowIndex = r2;
            this.mediaItemIndex = r2;
            this.mediaItem = r3;
            this.periodUid = r4;
            this.periodIndex = r5;
            this.positionMs = r6;
            this.contentPositionMs = r8;
            this.adGroupIndex = r10;
            this.adIndexInAdGroup = r11;
        }

        public boolean equals(Object r5) {
            if (this != r5) goto L6;
            return true;
        L6:
            if (r5 != null) goto L8;
        L17:
            return false;
        L8:
            if (PositionInfo.class != r5.getClass()) goto L17;
            PositionInfo r52 = (PositionInfo) r5;
            if (equalsForBundling(r52) == false) goto L17;
            if (Objects.equals(this.windowUid, r52.windowUid) == false) goto L17;
            if (Objects.equals(this.periodUid, r52.periodUid) == false) goto L17;
            return true;
        }

        public boolean equalsForBundling(PositionInfo r5) {
            if (this.mediaItemIndex == r5.mediaItemIndex) goto L5;
            return false;
        L5:
            if (this.periodIndex == r5.periodIndex) goto L7;
            return false;
        L7:
            if (this.positionMs == r5.positionMs) goto L9;
            return false;
        L9:
            if (this.contentPositionMs == r5.contentPositionMs) goto L11;
            return false;
        L11:
            if (this.adGroupIndex == r5.adGroupIndex) goto L13;
            return false;
        L13:
            if (this.adIndexInAdGroup == r5.adIndexInAdGroup) goto L15;
            return false;
        L15:
            if (Objects.equals(this.mediaItem, r5.mediaItem) == false) goto L25;
            return true;
        L25:
            return false;
        }

        public PositionInfo filterByAvailableCommands(boolean r15, boolean r16) {
            if (r15 == false) goto L5;
            if (r16 == false) goto L5;
            return this;
        L5:
            Object r1 = this.windowUid;
            int r2 = 0;
            if (r16 == false) goto L8;
            int r3 = this.mediaItemIndex;
        L9:
            if (r15 == false) goto L11;
            MediaItem r4 = this.mediaItem;
        L12:
            Object r5 = this.periodUid;
            if (r16 == false) goto L15;
            r2 = this.periodIndex;
        L15:
            long r6 = 0;
            if (r15 == false) goto L18;
            long r8 = this.positionMs;
        L19:
            if (r15 == false) goto L21;
            r6 = this.contentPositionMs;
        L21:
            int r10 = -1;
            if (r15 == false) goto L24;
            int r11 = this.adGroupIndex;
        L25:
            if (r15 == false) goto L28;
            r10 = this.adIndexInAdGroup;
        L28:
            return new PositionInfo(r1, r3, r4, r5, r2, r8, r6, r11, r10);
        L24:
            r11 = -1;
            goto L25
        L18:
            r8 = 0;
            goto L19
        L11:
            r4 = null;
            goto L12
        L8:
            r3 = 0;
            goto L9
        }

        public int hashCode() {
            return Objects.hash(new Object[]{this.windowUid, Integer.valueOf(this.mediaItemIndex), this.mediaItem, this.periodUid, Integer.valueOf(this.periodIndex), Long.valueOf(this.positionMs), Long.valueOf(this.contentPositionMs), Integer.valueOf(this.adGroupIndex), Integer.valueOf(this.adIndexInAdGroup)});
        }

        public Bundle toBundle(int r8) {
            Bundle r0 = new Bundle();
            if (r8 >= 3) goto L5;
        L6:
            r0.putInt(FIELD_MEDIA_ITEM_INDEX, this.mediaItemIndex);
        L7:
            MediaItem r2 = this.mediaItem;
            if (r2 == null) goto L10;
            r0.putBundle(FIELD_MEDIA_ITEM, r2.toBundle());
        L10:
            if (r8 >= 3) goto L12;
        L13:
            r0.putInt(FIELD_PERIOD_INDEX, this.periodIndex);
        L15:
            if (r8 >= 3) goto L17;
        L18:
            r0.putLong(FIELD_POSITION_MS, this.positionMs);
        L19:
            if (r8 >= 3) goto L21;
        L22:
            r0.putLong(FIELD_CONTENT_POSITION_MS, this.contentPositionMs);
        L23:
            int r82 = this.adGroupIndex;
            if (r82 == (-1)) goto L26;
            r0.putInt(FIELD_AD_GROUP_INDEX, r82);
        L26:
            int r7 = this.adIndexInAdGroup;
            if (r7 == (-1)) goto L29;
            r0.putInt(FIELD_AD_INDEX_IN_AD_GROUP, r7);
        L29:
            return r0;
        L21:
            if (this.contentPositionMs == 0) goto L23;
        L17:
            if (this.positionMs == 0) goto L19;
        L12:
            if (this.periodIndex == 0) goto L15;
        L5:
            if (this.mediaItemIndex == 0) goto L7;
            goto L6
        }

        public String toString() {
            String r0 = "mediaItem=" + this.mediaItemIndex + ", period=" + this.periodIndex + ", pos=" + this.positionMs;
            if (this.adGroupIndex != (-1)) goto L6;
            return r0;
        L6:
            return r0 + ", contentPos=" + this.contentPositionMs + ", adGroup=" + this.adGroupIndex + ", ad=" + this.adIndexInAdGroup;
        }
    }

    void addListener(Listener r1);

    void addMediaItems(int r1, List<MediaItem> r2);

    void addMediaItems(List<MediaItem> r1);

    boolean canAdvertiseSession();

    void clearMediaItems();

    void clearVideoTextureView(TextureView r1);

    @Deprecated
    void decreaseDeviceVolume();

    void decreaseDeviceVolume(int r1);

    Looper getApplicationLooper();

    AudioAttributes getAudioAttributes();

    Commands getAvailableCommands();

    int getBufferedPercentage();

    long getBufferedPosition();

    long getContentBufferedPosition();

    long getContentDuration();

    long getContentPosition();

    int getCurrentAdGroupIndex();

    int getCurrentAdIndexInAdGroup();

    CueGroup getCurrentCues();

    long getCurrentLiveOffset();

    MediaItem getCurrentMediaItem();

    int getCurrentMediaItemIndex();

    int getCurrentPeriodIndex();

    long getCurrentPosition();

    Timeline getCurrentTimeline();

    Tracks getCurrentTracks();

    DeviceInfo getDeviceInfo();

    int getDeviceVolume();

    long getDuration();

    long getMaxSeekToPreviousPosition();

    MediaMetadata getMediaMetadata();

    boolean getPlayWhenReady();

    PlaybackParameters getPlaybackParameters();

    int getPlaybackState();

    int getPlaybackSuppressionReason();

    PlaybackException getPlayerError();

    MediaMetadata getPlaylistMetadata();

    int getRepeatMode();

    long getSeekBackIncrement();

    long getSeekForwardIncrement();

    boolean getShuffleModeEnabled();

    long getTotalBufferedDuration();

    TrackSelectionParameters getTrackSelectionParameters();

    VideoSize getVideoSize();

    float getVolume();

    boolean hasNextMediaItem();

    boolean hasPreviousMediaItem();

    @Deprecated
    void increaseDeviceVolume();

    void increaseDeviceVolume(int r1);

    boolean isCommandAvailable(int r1);

    boolean isCurrentMediaItemDynamic();

    boolean isCurrentMediaItemLive();

    boolean isCurrentMediaItemSeekable();

    boolean isDeviceMuted();

    boolean isLoading();

    boolean isPlaying();

    boolean isPlayingAd();

    void moveMediaItem(int r1, int r2);

    void moveMediaItems(int r1, int r2, int r3);

    void pause();

    void play();

    void prepare();

    void removeListener(Listener r1);

    void removeMediaItem(int r1);

    void removeMediaItems(int r1, int r2);

    void replaceMediaItem(int r1, MediaItem r2);

    void replaceMediaItems(int r1, int r2, List<MediaItem> r3);

    void seekBack();

    void seekForward();

    void seekTo(int r1, long r2);

    void seekTo(long r1);

    void seekToDefaultPosition();

    void seekToDefaultPosition(int r1);

    void seekToNext();

    void seekToNextMediaItem();

    void seekToPrevious();

    void seekToPreviousMediaItem();

    void setAudioAttributes(AudioAttributes r1, boolean r2);

    @Deprecated
    void setDeviceMuted(boolean r1);

    void setDeviceMuted(boolean r1, int r2);

    @Deprecated
    void setDeviceVolume(int r1);

    void setDeviceVolume(int r1, int r2);

    void setMediaItem(MediaItem r1);

    void setMediaItem(MediaItem r1, long r2);

    void setMediaItem(MediaItem r1, boolean r2);

    void setMediaItems(List<MediaItem> r1, int r2, long r3);

    void setMediaItems(List<MediaItem> r1, boolean r2);

    void setPlayWhenReady(boolean r1);

    void setPlaybackParameters(PlaybackParameters r1);

    void setPlaybackSpeed(float r1);

    void setPlaylistMetadata(MediaMetadata r1);

    void setRepeatMode(int r1);

    void setShuffleModeEnabled(boolean r1);

    void setTrackSelectionParameters(TrackSelectionParameters r1);

    void setVideoSurface(Surface r1);

    void setVideoSurfaceView(SurfaceView r1);

    void setVideoTextureView(TextureView r1);

    void setVolume(float r1);

    void stop();
}
