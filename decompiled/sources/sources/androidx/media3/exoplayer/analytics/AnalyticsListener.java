package androidx.media3.exoplayer.analytics;

import android.util.SparseArray;
import androidx.media3.common.AudioAttributes;
import androidx.media3.common.DeviceInfo;
import androidx.media3.common.FlagSet;
import androidx.media3.common.Format;
import androidx.media3.common.MediaItem;
import androidx.media3.common.MediaMetadata;
import androidx.media3.common.Metadata;
import androidx.media3.common.PlaybackException;
import androidx.media3.common.PlaybackParameters;
import androidx.media3.common.Player;
import androidx.media3.common.Timeline;
import androidx.media3.common.TrackSelectionParameters;
import androidx.media3.common.Tracks;
import androidx.media3.common.VideoSize;
import androidx.media3.common.text.Cue;
import androidx.media3.common.text.CueGroup;
import androidx.media3.common.util.Assertions;
import androidx.media3.exoplayer.DecoderCounters;
import androidx.media3.exoplayer.DecoderReuseEvaluation;
import androidx.media3.exoplayer.audio.AudioSink;
import androidx.media3.exoplayer.source.LoadEventInfo;
import androidx.media3.exoplayer.source.MediaLoadData;
import androidx.media3.exoplayer.source.MediaSource;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

/* loaded from: classes4.dex */
public interface AnalyticsListener {

    public static final class EventTime {
        public final MediaSource.MediaPeriodId currentMediaPeriodId;
        public final long currentPlaybackPositionMs;
        public final Timeline currentTimeline;
        public final int currentWindowIndex;
        public final long eventPlaybackPositionMs;
        public final MediaSource.MediaPeriodId mediaPeriodId;
        public final long realtimeMs;
        public final Timeline timeline;
        public final long totalBufferedDurationMs;
        public final int windowIndex;

        public EventTime(long r1, Timeline r3, int r4, MediaSource.MediaPeriodId r5, long r6, Timeline r8, int r9, MediaSource.MediaPeriodId r10, long r11, long r13) {
            this.realtimeMs = r1;
            this.timeline = r3;
            this.windowIndex = r4;
            this.mediaPeriodId = r5;
            this.eventPlaybackPositionMs = r6;
            this.currentTimeline = r8;
            this.currentWindowIndex = r9;
            this.currentMediaPeriodId = r10;
            this.currentPlaybackPositionMs = r11;
            this.totalBufferedDurationMs = r13;
        }

        public boolean equals(Object r7) {
            if (this != r7) goto L6;
            return true;
        L6:
            if (r7 != null) goto L8;
        L31:
            return false;
        L8:
            if (EventTime.class != r7.getClass()) goto L31;
            EventTime r72 = (EventTime) r7;
            if (this.realtimeMs != r72.realtimeMs) goto L31;
            if (this.windowIndex != r72.windowIndex) goto L31;
            if (this.eventPlaybackPositionMs != r72.eventPlaybackPositionMs) goto L31;
            if (this.currentWindowIndex != r72.currentWindowIndex) goto L31;
            if (this.currentPlaybackPositionMs != r72.currentPlaybackPositionMs) goto L31;
            if (this.totalBufferedDurationMs != r72.totalBufferedDurationMs) goto L31;
            if (Objects.equals(this.timeline, r72.timeline) == false) goto L31;
            if (Objects.equals(this.mediaPeriodId, r72.mediaPeriodId) == false) goto L31;
            if (Objects.equals(this.currentTimeline, r72.currentTimeline) == false) goto L31;
            if (Objects.equals(this.currentMediaPeriodId, r72.currentMediaPeriodId) == false) goto L31;
            return true;
        }

        public int hashCode() {
            return Objects.hash(new Object[]{Long.valueOf(this.realtimeMs), this.timeline, Integer.valueOf(this.windowIndex), this.mediaPeriodId, Long.valueOf(this.eventPlaybackPositionMs), this.currentTimeline, Integer.valueOf(this.currentWindowIndex), this.currentMediaPeriodId, Long.valueOf(this.currentPlaybackPositionMs), Long.valueOf(this.totalBufferedDurationMs)});
        }
    }

    public static final class Events {
        private final SparseArray<EventTime> eventTimes;
        private final FlagSet flags;

        public Events(FlagSet r5, SparseArray<EventTime> r6) {
            this.flags = r5;
            SparseArray<EventTime> r0 = new SparseArray(r5.size());
            int r1 = 0;
        L4:
            if (r1 >= r5.size()) goto L6;
            int r2 = r5.get(r1);
            r0.append(r2, (EventTime) Assertions.checkNotNull(r6.get(r2)));
            r1 = r1 + 1;
            goto L4
        L6:
            this.eventTimes = r0;
        }

        public boolean contains(int r1) {
            return this.flags.contains(r1);
        }

        public int get(int r1) {
            return this.flags.get(r1);
        }

        public EventTime getEventTime(int r1) {
            return (EventTime) Assertions.checkNotNull(this.eventTimes.get(r1));
        }

        public int size() {
            return this.flags.size();
        }
    }

    default void onAudioAttributesChanged(EventTime r1, AudioAttributes r2) {
    }

    default void onAudioCodecError(EventTime r1, Exception r2) {
    }

    @Deprecated
    default void onAudioDecoderInitialized(EventTime r1, String r2, long r3) {
    }

    default void onAudioDecoderInitialized(EventTime r1, String r2, long r3, long r5) {
    }

    default void onAudioDecoderReleased(EventTime r1, String r2) {
    }

    default void onAudioDisabled(EventTime r1, DecoderCounters r2) {
    }

    default void onAudioEnabled(EventTime r1, DecoderCounters r2) {
    }

    default void onAudioInputFormatChanged(EventTime r1, Format r2, DecoderReuseEvaluation r3) {
    }

    default void onAudioPositionAdvancing(EventTime r1, long r2) {
    }

    default void onAudioSessionIdChanged(EventTime r1, int r2) {
    }

    default void onAudioSinkError(EventTime r1, Exception r2) {
    }

    default void onAudioTrackInitialized(EventTime r1, AudioSink.AudioTrackConfig r2) {
    }

    default void onAudioTrackReleased(EventTime r1, AudioSink.AudioTrackConfig r2) {
    }

    default void onAudioUnderrun(EventTime r1, int r2, long r3, long r5) {
    }

    default void onAvailableCommandsChanged(EventTime r1, Player.Commands r2) {
    }

    default void onBandwidthEstimate(EventTime r1, int r2, long r3, long r5) {
    }

    default void onCues(EventTime r1, CueGroup r2) {
    }

    @Deprecated
    default void onCues(EventTime r1, List<Cue> r2) {
    }

    default void onDeviceInfoChanged(EventTime r1, DeviceInfo r2) {
    }

    default void onDeviceVolumeChanged(EventTime r1, int r2, boolean r3) {
    }

    default void onDownstreamFormatChanged(EventTime r1, MediaLoadData r2) {
    }

    default void onDrmKeysLoaded(EventTime r1) {
    }

    default void onDrmKeysRemoved(EventTime r1) {
    }

    default void onDrmKeysRestored(EventTime r1) {
    }

    @Deprecated
    default void onDrmSessionAcquired(EventTime r1) {
    }

    default void onDrmSessionAcquired(EventTime r1, int r2) {
    }

    default void onDrmSessionManagerError(EventTime r1, Exception r2) {
    }

    default void onDrmSessionReleased(EventTime r1) {
    }

    default void onDroppedVideoFrames(EventTime r1, int r2, long r3) {
    }

    default void onEvents(Player r1, Events r2) {
    }

    default void onIsLoadingChanged(EventTime r1, boolean r2) {
    }

    default void onIsPlayingChanged(EventTime r1, boolean r2) {
    }

    default void onLoadCanceled(EventTime r1, LoadEventInfo r2, MediaLoadData r3) {
    }

    default void onLoadCompleted(EventTime r1, LoadEventInfo r2, MediaLoadData r3) {
    }

    default void onLoadError(EventTime r1, LoadEventInfo r2, MediaLoadData r3, IOException r4, boolean r5) {
    }

    @Deprecated
    default void onLoadStarted(EventTime r1, LoadEventInfo r2, MediaLoadData r3) {
    }

    default void onLoadStarted(EventTime r1, LoadEventInfo r2, MediaLoadData r3, int r4) {
    }

    @Deprecated
    default void onLoadingChanged(EventTime r1, boolean r2) {
    }

    default void onMediaItemTransition(EventTime r1, MediaItem r2, int r3) {
    }

    default void onMediaMetadataChanged(EventTime r1, MediaMetadata r2) {
    }

    default void onMetadata(EventTime r1, Metadata r2) {
    }

    default void onPlayWhenReadyChanged(EventTime r1, boolean r2, int r3) {
    }

    default void onPlaybackParametersChanged(EventTime r1, PlaybackParameters r2) {
    }

    default void onPlaybackStateChanged(EventTime r1, int r2) {
    }

    default void onPlaybackSuppressionReasonChanged(EventTime r1, int r2) {
    }

    default void onPlayerError(EventTime r1, PlaybackException r2) {
    }

    default void onPlayerErrorChanged(EventTime r1, PlaybackException r2) {
    }

    default void onPlayerReleased(EventTime r1) {
    }

    @Deprecated
    default void onPlayerStateChanged(EventTime r1, boolean r2, int r3) {
    }

    default void onPlaylistMetadataChanged(EventTime r1, MediaMetadata r2) {
    }

    @Deprecated
    default void onPositionDiscontinuity(EventTime r1, int r2) {
    }

    void onPositionDiscontinuity(EventTime r1, Player.PositionInfo r2, Player.PositionInfo r3, int r4);

    default void onRenderedFirstFrame(EventTime r1, Object r2, long r3) {
    }

    default void onRendererReadyChanged(EventTime r1, int r2, int r3, boolean r4) {
    }

    default void onRepeatModeChanged(EventTime r1, int r2) {
    }

    @Deprecated
    default void onSeekStarted(EventTime r1) {
    }

    default void onShuffleModeChanged(EventTime r1, boolean r2) {
    }

    default void onSkipSilenceEnabledChanged(EventTime r1, boolean r2) {
    }

    default void onSurfaceSizeChanged(EventTime r1, int r2, int r3) {
    }

    default void onTimelineChanged(EventTime r1, int r2) {
    }

    default void onTrackSelectionParametersChanged(EventTime r1, TrackSelectionParameters r2) {
    }

    default void onTracksChanged(EventTime r1, Tracks r2) {
    }

    default void onUpstreamDiscarded(EventTime r1, MediaLoadData r2) {
    }

    default void onVideoCodecError(EventTime r1, Exception r2) {
    }

    @Deprecated
    default void onVideoDecoderInitialized(EventTime r1, String r2, long r3) {
    }

    default void onVideoDecoderInitialized(EventTime r1, String r2, long r3, long r5) {
    }

    default void onVideoDecoderReleased(EventTime r1, String r2) {
    }

    default void onVideoDisabled(EventTime r1, DecoderCounters r2) {
    }

    default void onVideoEnabled(EventTime r1, DecoderCounters r2) {
    }

    default void onVideoFrameProcessingOffset(EventTime r1, long r2, int r4) {
    }

    default void onVideoInputFormatChanged(EventTime r1, Format r2, DecoderReuseEvaluation r3) {
    }

    @Deprecated
    default void onVideoSizeChanged(EventTime r1, int r2, int r3, int r4, float r5) {
    }

    default void onVideoSizeChanged(EventTime r1, VideoSize r2) {
    }

    default void onVolumeChanged(EventTime r1, float r2) {
    }
}
