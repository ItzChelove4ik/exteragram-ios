package androidx.media3.exoplayer.analytics;

import android.os.Looper;
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
import androidx.media3.common.util.Clock;
import androidx.media3.common.util.HandlerWrapper;
import androidx.media3.common.util.ListenerSet;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.DecoderCounters;
import androidx.media3.exoplayer.DecoderReuseEvaluation;
import androidx.media3.exoplayer.ExoPlaybackException;
import androidx.media3.exoplayer.analytics.AnalyticsListener;
import androidx.media3.exoplayer.audio.AudioSink;
import androidx.media3.exoplayer.source.LoadEventInfo;
import androidx.media3.exoplayer.source.MediaLoadData;
import androidx.media3.exoplayer.source.MediaSource;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Iterables;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import okhttp3.internal.ws.WebSocketProtocol;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

/* loaded from: classes4.dex */
public class DefaultAnalyticsCollector implements AnalyticsCollector {
    private final Clock clock;
    private final SparseArray<AnalyticsListener.EventTime> eventTimes;
    private HandlerWrapper handler;
    private boolean isSeeking;
    private ListenerSet<AnalyticsListener> listeners;
    private final MediaPeriodQueueTracker mediaPeriodQueueTracker;
    private final Timeline.Period period;
    private Player player;
    private final Timeline.Window window;

    public static final class MediaPeriodQueueTracker {
        private MediaSource.MediaPeriodId currentPlayerMediaPeriod;
        private ImmutableList<MediaSource.MediaPeriodId> mediaPeriodQueue;
        private ImmutableMap<MediaSource.MediaPeriodId, Timeline> mediaPeriodTimelines;
        private final Timeline.Period period;
        private MediaSource.MediaPeriodId playingMediaPeriod;
        private MediaSource.MediaPeriodId readingMediaPeriod;

        public MediaPeriodQueueTracker(Timeline.Period r1) {
            this.period = r1;
            this.mediaPeriodQueue = ImmutableList.of();
            this.mediaPeriodTimelines = ImmutableMap.of();
        }

        public static /* synthetic */ ImmutableList access$000(MediaPeriodQueueTracker r0) {
            return r0.mediaPeriodQueue;
        }

        private void addTimelineForMediaPeriodId(ImmutableMap.Builder<MediaSource.MediaPeriodId, Timeline> r3, MediaSource.MediaPeriodId r4, Timeline r5) {
            if (r4 != null) goto L5;
            return;
        L5:
            if (r5.getIndexOfPeriod(r4.periodUid) == (-1)) goto L8;
            r3.put(r4, r5);
            return;
        L8:
            Timeline r2 = this.mediaPeriodTimelines.get(r4);
            if (r2 == null) goto L12;
            r3.put(r4, r2);
            return;
        }

        private static MediaSource.MediaPeriodId findCurrentPlayerMediaPeriodInQueue(Player r10, ImmutableList<MediaSource.MediaPeriodId> r11, MediaSource.MediaPeriodId r12, Timeline.Period r13) {
            Timeline r0 = r10.getCurrentTimeline();
            int r1 = r10.getCurrentPeriodIndex();
            if (r0.isEmpty() == false) goto L5;
            Object r5 = null;
        L7:
            if (r10.isPlayingAd() == false) goto L9;
        L13:
            int r132 = -1;
        L12:
            int r9 = r132;
            int r133 = 0;
        L16:
            if (r133 >= r11.size()) goto L22;
            MediaSource.MediaPeriodId r4 = r11.get(r133);
            if (isMatchingMediaPeriod(r4, r5, r10.isPlayingAd(), r10.getCurrentAdGroupIndex(), r10.getCurrentAdIndexInAdGroup(), r9) == true) goto L19;
            r133 = r133 + 1;
            goto L16
        L19:
            return r4;
        L22:
            if (r11.isEmpty() == false) goto L27;
            if (r12 == null) goto L27;
            if (isMatchingMediaPeriod(r12, r5, r10.isPlayingAd(), r10.getCurrentAdGroupIndex(), r10.getCurrentAdIndexInAdGroup(), r9) == false) goto L27;
            return r12;
        L27:
            return null;
        L9:
            if (r0.isEmpty() == true) goto L13;
            r132 = r0.getPeriod(r1, r13).getAdGroupIndexAfterPositionUs(Util.msToUs(r10.getCurrentPosition()) - r13.getPositionInWindowUs());
            goto L12
        L5:
            r5 = r0.getUidOfPeriod(r1);
            goto L7
        }

        private static boolean isMatchingMediaPeriod(MediaSource.MediaPeriodId r1, Object r2, boolean r3, int r4, int r5, int r6) {
            if (r1.periodUid.equals(r2) == true) goto L5;
            return false;
        L5:
            if (r3 == true) goto L7;
        L10:
            if (r3 == false) goto L12;
        L17:
            return false;
        L12:
            if (r1.adGroupIndex != (-1)) goto L17;
            if (r1.nextAdGroupIndex != r6) goto L17;
            return true;
        L7:
            if (r1.adGroupIndex != r4) goto L10;
            if (r1.adIndexInAdGroup != r5) goto L10;
            return true;
        }

        private void updateMediaPeriodTimelines(Timeline r5) {
            ImmutableMap.Builder<MediaSource.MediaPeriodId, Timeline> r0 = ImmutableMap.builder();
            if (this.mediaPeriodQueue.isEmpty() == false) goto L12;
            addTimelineForMediaPeriodId(r0, this.playingMediaPeriod, r5);
            if (Objects.equals(this.readingMediaPeriod, this.playingMediaPeriod) == true) goto L8;
            addTimelineForMediaPeriodId(r0, this.readingMediaPeriod, r5);
        L8:
            if (Objects.equals(this.currentPlayerMediaPeriod, this.playingMediaPeriod) == false) goto L10;
        L19:
            this.mediaPeriodTimelines = r0.buildOrThrow();
            return;
        L10:
            if (Objects.equals(this.currentPlayerMediaPeriod, this.readingMediaPeriod) == true) goto L19;
            addTimelineForMediaPeriodId(r0, this.currentPlayerMediaPeriod, r5);
            goto L19
        L12:
            int r1 = 0;
        L13:
            int r2 = this.mediaPeriodQueue.size();
            ImmutableList<MediaSource.MediaPeriodId> r3 = this.mediaPeriodQueue;
            if (r1 >= r2) goto L17;
            addTimelineForMediaPeriodId(r0, r3.get(r1), r5);
            r1 = r1 + 1;
            goto L13
        L17:
            if (r3.contains(this.currentPlayerMediaPeriod) == true) goto L19;
            addTimelineForMediaPeriodId(r0, this.currentPlayerMediaPeriod, r5);
            goto L19
        }

        public MediaSource.MediaPeriodId getCurrentPlayerMediaPeriod() {
            return this.currentPlayerMediaPeriod;
        }

        public MediaSource.MediaPeriodId getLoadingMediaPeriod() {
            if (this.mediaPeriodQueue.isEmpty() == false) goto L7;
            return null;
        L7:
            return (MediaSource.MediaPeriodId) Iterables.getLast(this.mediaPeriodQueue);
        }

        public Timeline getMediaPeriodIdTimeline(MediaSource.MediaPeriodId r1) {
            return this.mediaPeriodTimelines.get(r1);
        }

        public MediaSource.MediaPeriodId getPlayingMediaPeriod() {
            return this.playingMediaPeriod;
        }

        public MediaSource.MediaPeriodId getReadingMediaPeriod() {
            return this.readingMediaPeriod;
        }

        public void onPositionDiscontinuity(Player r4) {
            this.currentPlayerMediaPeriod = findCurrentPlayerMediaPeriodInQueue(r4, this.mediaPeriodQueue, this.playingMediaPeriod, this.period);
        }

        public void onQueueUpdated(List<MediaSource.MediaPeriodId> r2, MediaSource.MediaPeriodId r3, Player r4) {
            this.mediaPeriodQueue = ImmutableList.copyOf(r2);
            if (r2.isEmpty() == true) goto L6;
            this.playingMediaPeriod = r2.get(0);
            this.readingMediaPeriod = (MediaSource.MediaPeriodId) Assertions.checkNotNull(r3);
        L6:
            if (this.currentPlayerMediaPeriod != null) goto L8;
            this.currentPlayerMediaPeriod = findCurrentPlayerMediaPeriodInQueue(r4, this.mediaPeriodQueue, this.playingMediaPeriod, this.period);
        L8:
            updateMediaPeriodTimelines(r4.getCurrentTimeline());
        }

        public void onTimelineChanged(Player r4) {
            this.currentPlayerMediaPeriod = findCurrentPlayerMediaPeriodInQueue(r4, this.mediaPeriodQueue, this.playingMediaPeriod, this.period);
            updateMediaPeriodTimelines(r4.getCurrentTimeline());
        }
    }

    /* renamed from: $r8$lambda$0UGfiRTPX5BzU--qpDTpJUG65MI, reason: not valid java name */
    public static /* synthetic */ void m796$r8$lambda$0UGfiRTPX5BzUqpDTpJUG65MI(AnalyticsListener.EventTime r0, TrackSelectionParameters r1, AnalyticsListener r2) {
        r2.onTrackSelectionParametersChanged(r0, r1);
    }

    /* renamed from: $r8$lambda$1Iz-7t0FD6TY8XaZKNq1J31Mvrk, reason: not valid java name */
    public static /* synthetic */ void m797$r8$lambda$1Iz7t0FD6TY8XaZKNq1J31Mvrk(AnalyticsListener.EventTime r0, AnalyticsListener r1) {
        r1.onSeekStarted(r0);
    }

    /* renamed from: $r8$lambda$1vYjxRJ2chw1jS8CCU2-2gyC5WQ, reason: not valid java name */
    public static /* synthetic */ void m798$r8$lambda$1vYjxRJ2chw1jS8CCU22gyC5WQ(AnalyticsListener.EventTime r1, int r2, long r3, long r5, AnalyticsListener r7) {
        r7.onBandwidthEstimate(r1, r2, r3, r5);
    }

    public static /* synthetic */ void $r8$lambda$2YBORLGkfEw0uhrON1DKMz095ns(AnalyticsListener.EventTime r0, int r1, AnalyticsListener r2) {
        r2.onDrmSessionAcquired(r0);
        r2.onDrmSessionAcquired(r0, r1);
    }

    public static /* synthetic */ void $r8$lambda$2aq2fQxyhmeLD1L1RC1By0Hn3OQ(AnalyticsListener.EventTime r0, int r1, long r2, AnalyticsListener r4) {
        r4.onDroppedVideoFrames(r0, r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$3CDSWPM8oOl8YfJfJkG2Ayw56RU(AnalyticsListener.EventTime r3, String r4, long r5, long r7, AnalyticsListener r9) {
        r9.onAudioDecoderInitialized(r3, r4, r5);
        r9.onAudioDecoderInitialized(r3, r4, r7, r5);
    }

    public static /* synthetic */ void $r8$lambda$4DW3jeNkzyZgr_m8cgmZr1nCmUc(AnalyticsListener.EventTime r0, PlaybackException r1, AnalyticsListener r2) {
        r2.onPlayerError(r0, r1);
    }

    /* renamed from: $r8$lambda$4qT9YXi-U1WsyrWmg6L6byZ-ndc, reason: not valid java name */
    public static /* synthetic */ void m799$r8$lambda$4qT9YXiU1WsyrWmg6L6byZndc(AnalyticsListener.EventTime r0, AudioAttributes r1, AnalyticsListener r2) {
        r2.onAudioAttributesChanged(r0, r1);
    }

    public static /* synthetic */ void $r8$lambda$9MiDfvK3DNDtJjaT5T_zNlV0MUo(AnalyticsListener.EventTime r0, Exception r1, AnalyticsListener r2) {
        r2.onVideoCodecError(r0, r1);
    }

    public static /* synthetic */ void $r8$lambda$BJbK9foTDOLytGYx0qdKUrWcUTo(AnalyticsListener.EventTime r0, int r1, int r2, boolean r3, AnalyticsListener r4) {
        r4.onRendererReadyChanged(r0, r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$CmxIeUZuhK0Dae5_pV_EzE5oeU0(AnalyticsListener.EventTime r0, AnalyticsListener r1) {
        r1.onDrmKeysRemoved(r0);
    }

    public static /* synthetic */ void $r8$lambda$CrnmoGGPTegxQ8RsXxdz_jlbZuQ(AnalyticsListener.EventTime r0, MediaMetadata r1, AnalyticsListener r2) {
        r2.onMediaMetadataChanged(r0, r1);
    }

    public static /* synthetic */ void $r8$lambda$DAA5Nzi95KHet6dyVQ9nY4fi9YE(AnalyticsListener.EventTime r6, VideoSize r7, AnalyticsListener r8) {
        r8.onVideoSizeChanged(r6, r7);
        r8.onVideoSizeChanged(r6, r7.width, r7.height, 0, r7.pixelWidthHeightRatio);
    }

    public static /* synthetic */ void $r8$lambda$DIdc623UX2VV8N7tNg4F2MQjb7w(AnalyticsListener.EventTime r0, LoadEventInfo r1, MediaLoadData r2, int r3, AnalyticsListener r4) {
        r4.onLoadStarted(r0, r1, r2);
        r4.onLoadStarted(r0, r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$E6FnGoeGujPpxNy5W28KkBvboS4(AnalyticsListener.EventTime r0, AudioSink.AudioTrackConfig r1, AnalyticsListener r2) {
        r2.onAudioTrackInitialized(r0, r1);
    }

    public static /* synthetic */ void $r8$lambda$FMvB0v5PqUUGvcXZYTf1USt_Pm4(AnalyticsListener.EventTime r1, int r2, long r3, long r5, AnalyticsListener r7) {
        r7.onAudioUnderrun(r1, r2, r3, r5);
    }

    /* renamed from: $r8$lambda$GMHRJYG6on_1G-lKr4LJVfdwt7Q, reason: not valid java name */
    public static /* synthetic */ void m800$r8$lambda$GMHRJYG6on_1GlKr4LJVfdwt7Q(AnalyticsListener.EventTime r0, int r1, AnalyticsListener r2) {
        r2.onPlaybackStateChanged(r0, r1);
    }

    public static /* synthetic */ void $r8$lambda$GifmCBIzU3uJmJ8Rh8UUBJPC2rg(AnalyticsListener.EventTime r0, LoadEventInfo r1, MediaLoadData r2, AnalyticsListener r3) {
        r3.onLoadCanceled(r0, r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$IFCtC3S7TL0L5sldQjis7zdEjQY(AnalyticsListener.EventTime r0, AnalyticsListener r1) {
        r1.onDrmKeysLoaded(r0);
    }

    /* renamed from: $r8$lambda$ImxjYQDJtateDyy2INIcsxUZo-A, reason: not valid java name */
    public static /* synthetic */ void m801$r8$lambda$ImxjYQDJtateDyy2INIcsxUZoA(AnalyticsListener.EventTime r0, Tracks r1, AnalyticsListener r2) {
        r2.onTracksChanged(r0, r1);
    }

    /* renamed from: $r8$lambda$KHXoVfoBSibjJ5jRYG-4xZxqhJE, reason: not valid java name */
    public static /* synthetic */ void m802$r8$lambda$KHXoVfoBSibjJ5jRYG4xZxqhJE(AnalyticsListener.EventTime r0, DeviceInfo r1, AnalyticsListener r2) {
        r2.onDeviceInfoChanged(r0, r1);
    }

    public static /* synthetic */ void $r8$lambda$KQF4nej0VrsMFDOdQzXqSpdOXTs(AnalyticsListener.EventTime r0, Format r1, DecoderReuseEvaluation r2, AnalyticsListener r3) {
        r3.onAudioInputFormatChanged(r0, r1, r2);
    }

    /* renamed from: $r8$lambda$LnvdHnpk1HjiFwgKnOq3V3Qc-gs, reason: not valid java name */
    public static /* synthetic */ void m803$r8$lambda$LnvdHnpk1HjiFwgKnOq3V3Qcgs(AnalyticsListener.EventTime r0, long r1, AnalyticsListener r3) {
        r3.onAudioPositionAdvancing(r0, r1);
    }

    /* renamed from: $r8$lambda$MLMny-JRvDz48IfWZ4UVooJxor4, reason: not valid java name */
    public static /* synthetic */ void m804$r8$lambda$MLMnyJRvDz48IfWZ4UVooJxor4(AnalyticsListener.EventTime r3, String r4, long r5, long r7, AnalyticsListener r9) {
        r9.onVideoDecoderInitialized(r3, r4, r5);
        r9.onVideoDecoderInitialized(r3, r4, r7, r5);
    }

    public static /* synthetic */ void $r8$lambda$NazTkydGfX6jUSUhgOk3tFa3GFY(DefaultAnalyticsCollector r1, Player r2, AnalyticsListener r3, FlagSet r4) {
        r3.onEvents(r2, new AnalyticsListener.Events(r4, r1.eventTimes));
    }

    public static /* synthetic */ void $r8$lambda$PS84D8nyDr5W795jjs8Z40bZjWs(AnalyticsListener.EventTime r0, MediaMetadata r1, AnalyticsListener r2) {
        r2.onPlaylistMetadataChanged(r0, r1);
    }

    public static /* synthetic */ void $r8$lambda$ROeNGVQunBbdMKF_9R9Jo5pxxFk(AnalyticsListener.EventTime r0, DecoderCounters r1, AnalyticsListener r2) {
        r2.onVideoEnabled(r0, r1);
    }

    public static /* synthetic */ void $r8$lambda$RrIW4STToMHKtb5L8S1MxaAswkw(AnalyticsListener.EventTime r0, DecoderCounters r1, AnalyticsListener r2) {
        r2.onAudioDisabled(r0, r1);
    }

    public static /* synthetic */ void $r8$lambda$S4lQQxj2nAf0u1bXhMcCSywBUSw(AnalyticsListener.EventTime r0, Metadata r1, AnalyticsListener r2) {
        r2.onMetadata(r0, r1);
    }

    public static /* synthetic */ void $r8$lambda$T3i71MJXDw1V6MH3QfhqDeS_f3U(AnalyticsListener.EventTime r0, int r1, AnalyticsListener r2) {
        r2.onTimelineChanged(r0, r1);
    }

    /* renamed from: $r8$lambda$TFvaHWPxY51-9S2AW9S8VxTk55I, reason: not valid java name */
    public static /* synthetic */ void m805$r8$lambda$TFvaHWPxY519S2AW9S8VxTk55I(AnalyticsListener.EventTime r0, boolean r1, AnalyticsListener r2) {
        r2.onLoadingChanged(r0, r1);
        r2.onIsLoadingChanged(r0, r1);
    }

    public static /* synthetic */ void $r8$lambda$TlrYZMQTf3veSivtBYC8GlN4dG4(AnalyticsListener.EventTime r0, boolean r1, int r2, AnalyticsListener r3) {
        r3.onPlayWhenReadyChanged(r0, r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$UsOrpmdv_joUNNXbSfcXWRVVcLA(AnalyticsListener.EventTime r0, PlaybackParameters r1, AnalyticsListener r2) {
        r2.onPlaybackParametersChanged(r0, r1);
    }

    /* renamed from: $r8$lambda$V44vuFe-INZn-zu5EblmCxMf018, reason: not valid java name */
    public static /* synthetic */ void m806$r8$lambda$V44vuFeINZnzu5EblmCxMf018(AnalyticsListener.EventTime r0, MediaLoadData r1, AnalyticsListener r2) {
        r2.onDownstreamFormatChanged(r0, r1);
    }

    /* renamed from: $r8$lambda$VZrPDtOVzR2O10Xoj9V-7Uj7KBE, reason: not valid java name */
    public static /* synthetic */ void m807$r8$lambda$VZrPDtOVzR2O10Xoj9V7Uj7KBE(AnalyticsListener.EventTime r0, DecoderCounters r1, AnalyticsListener r2) {
        r2.onAudioEnabled(r0, r1);
    }

    /* renamed from: $r8$lambda$Wq-Qx85Q-7qQjalo8BfMKRNhYxs, reason: not valid java name */
    public static /* synthetic */ void m808$r8$lambda$WqQx85Q7qQjalo8BfMKRNhYxs(AnalyticsListener.EventTime r0, int r1, AnalyticsListener r2) {
        r2.onPlaybackSuppressionReasonChanged(r0, r1);
    }

    public static /* synthetic */ void $r8$lambda$Y8DQqUgI7UBECd_3BYAapcRHq9c(AnalyticsListener.EventTime r0, List r1, AnalyticsListener r2) {
        r2.onCues(r0, r1);
    }

    /* renamed from: $r8$lambda$YHmps8375b6QilreldgtZfD1D-Y, reason: not valid java name */
    public static /* synthetic */ void m809$r8$lambda$YHmps8375b6QilreldgtZfD1DY(AnalyticsListener.EventTime r0, boolean r1, AnalyticsListener r2) {
        r2.onSkipSilenceEnabledChanged(r0, r1);
    }

    public static /* synthetic */ void $r8$lambda$_77neWu5Vg8AE2X_5nMs7Wdw498(AnalyticsListener.EventTime r1, LoadEventInfo r2, MediaLoadData r3, IOException r4, boolean r5, AnalyticsListener r6) {
        r6.onLoadError(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$_PoTDKFsO54zbf7w82zCgY1yIbI(AnalyticsListener.EventTime r0, int r1, Player.PositionInfo r2, Player.PositionInfo r3, AnalyticsListener r4) {
        r4.onPositionDiscontinuity(r0, r1);
        r4.onPositionDiscontinuity(r0, r2, r3, r1);
    }

    public static /* synthetic */ void $r8$lambda$_Qb7vR15x29dkP3rcpmGIsSBYkE(AnalyticsListener.EventTime r0, int r1, int r2, AnalyticsListener r3) {
        r3.onSurfaceSizeChanged(r0, r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$beemuUlrcRdYmQiMx95bEndNv9Q(DefaultAnalyticsCollector r0) {
        r0.releaseInternal();
    }

    /* renamed from: $r8$lambda$cAjaJckv3dQsO1-9Zqx9IUaBTnA, reason: not valid java name */
    public static /* synthetic */ void m810$r8$lambda$cAjaJckv3dQsO19Zqx9IUaBTnA(AnalyticsListener.EventTime r0, PlaybackException r1, AnalyticsListener r2) {
        r2.onPlayerErrorChanged(r0, r1);
    }

    public static /* synthetic */ void $r8$lambda$cBAS1WUPopsKODYyrhXwL3P22Do(AnalyticsListener.EventTime r0, DecoderCounters r1, AnalyticsListener r2) {
        r2.onVideoDisabled(r0, r1);
    }

    public static /* synthetic */ void $r8$lambda$eEbbyrUfNvrSnGzc3eEBjfKzIaY(AnalyticsListener.EventTime r0, int r1, AnalyticsListener r2) {
        r2.onAudioSessionIdChanged(r0, r1);
    }

    public static /* synthetic */ void $r8$lambda$edyYzcoYh5EZrYMMEmOcR8tQxSs(AnalyticsListener.EventTime r0, boolean r1, AnalyticsListener r2) {
        r2.onShuffleModeChanged(r0, r1);
    }

    public static /* synthetic */ void $r8$lambda$ejFllaZzAt3wTCgytsnA5KrWEMk(AnalyticsListener.EventTime r0, Format r1, DecoderReuseEvaluation r2, AnalyticsListener r3) {
        r3.onVideoInputFormatChanged(r0, r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$hSuP48vdUmBzOVsihGBbeoQAa_E(AnalyticsListener.EventTime r0, AnalyticsListener r1) {
        r1.onDrmSessionReleased(r0);
    }

    /* renamed from: $r8$lambda$iIDMmOFRhIJoiaVhHtoq-BOi9l0, reason: not valid java name */
    public static /* synthetic */ void m811$r8$lambda$iIDMmOFRhIJoiaVhHtoqBOi9l0(AnalyticsListener.EventTime r0, Exception r1, AnalyticsListener r2) {
        r2.onAudioCodecError(r0, r1);
    }

    public static /* synthetic */ void $r8$lambda$k84oLa_HJ_diQZBgGzOa8deagb8(AnalyticsListener.EventTime r0, LoadEventInfo r1, MediaLoadData r2, AnalyticsListener r3) {
        r3.onLoadCompleted(r0, r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$kmGavx8Pgn_nN_ahC3jiF2xm5e0(AnalyticsListener.EventTime r0, long r1, int r3, AnalyticsListener r4) {
        r4.onVideoFrameProcessingOffset(r0, r1, r3);
    }

    public static /* synthetic */ void $r8$lambda$lE7nzn8wkV1KgN6S24UQuDnyLlw(AnalyticsListener.EventTime r0, AnalyticsListener r1) {
        r1.onPlayerReleased(r0);
    }

    public static /* synthetic */ void $r8$lambda$lkObtjZ1XQlZPiT3AD_j36q7eec(AnalyticsListener.EventTime r0, Player.Commands r1, AnalyticsListener r2) {
        r2.onAvailableCommandsChanged(r0, r1);
    }

    public static /* synthetic */ void $r8$lambda$lyO01WHVxY8BxDhu5BHFpLZOQqk(AnalyticsListener.EventTime r0, Exception r1, AnalyticsListener r2) {
        r2.onDrmSessionManagerError(r0, r1);
    }

    public static /* synthetic */ void $r8$lambda$mhRXvE541SUL435o9bPvy5airYA(AnalyticsListener.EventTime r0, String r1, AnalyticsListener r2) {
        r2.onVideoDecoderReleased(r0, r1);
    }

    public static /* synthetic */ void $r8$lambda$nXITBqglhelrCdCDLukzemni674(AnalyticsListener.EventTime r0, float r1, AnalyticsListener r2) {
        r2.onVolumeChanged(r0, r1);
    }

    public static /* synthetic */ void $r8$lambda$nvmm4uCUE2kYoiEJXkurq3gqTqQ(AnalyticsListener.EventTime r0, Object r1, long r2, AnalyticsListener r4) {
        r4.onRenderedFirstFrame(r0, r1, r2);
    }

    /* renamed from: $r8$lambda$o7G3P-s1s5Lt0zU2-CXTgAglN8M, reason: not valid java name */
    public static /* synthetic */ void m812$r8$lambda$o7G3Ps1s5Lt0zU2CXTgAglN8M(AnalyticsListener.EventTime r0, AudioSink.AudioTrackConfig r1, AnalyticsListener r2) {
        r2.onAudioTrackReleased(r0, r1);
    }

    /* renamed from: $r8$lambda$oI648d2qVbacQ1C2-4Tb2Zd6EBI, reason: not valid java name */
    public static /* synthetic */ void m813$r8$lambda$oI648d2qVbacQ1C24Tb2Zd6EBI(AnalyticsListener.EventTime r0, AnalyticsListener r1) {
        r1.onDrmKeysRestored(r0);
    }

    /* renamed from: $r8$lambda$pir8c8ieeVu1q4_iQR-5T09ZTps, reason: not valid java name */
    public static /* synthetic */ void m814$r8$lambda$pir8c8ieeVu1q4_iQR5T09ZTps(AnalyticsListener.EventTime r0, int r1, boolean r2, AnalyticsListener r3) {
        r3.onDeviceVolumeChanged(r0, r1, r2);
    }

    /* renamed from: $r8$lambda$qMgIV-GOEUUGlBWSFGqcq12dUPU, reason: not valid java name */
    public static /* synthetic */ void m815$r8$lambda$qMgIVGOEUUGlBWSFGqcq12dUPU(AnalyticsListener.EventTime r0, CueGroup r1, AnalyticsListener r2) {
        r2.onCues(r0, r1);
    }

    public static /* synthetic */ void $r8$lambda$sDhmnLMTKE8G7LNYyKdIS1HfHdE(AnalyticsListener.EventTime r0, Exception r1, AnalyticsListener r2) {
        r2.onAudioSinkError(r0, r1);
    }

    public static /* synthetic */ void $r8$lambda$u3fKm9hQibRvOBSb4nFm35RzLt0(AnalyticsListener.EventTime r0, MediaItem r1, int r2, AnalyticsListener r3) {
        r3.onMediaItemTransition(r0, r1, r2);
    }

    /* renamed from: $r8$lambda$ubIvhRttt-7WUyFo1QdJkzqTaV4, reason: not valid java name */
    public static /* synthetic */ void m816$r8$lambda$ubIvhRttt7WUyFo1QdJkzqTaV4(AnalyticsListener.EventTime r0, boolean r1, int r2, AnalyticsListener r3) {
        r3.onPlayerStateChanged(r0, r1, r2);
    }

    /* renamed from: $r8$lambda$ufv-2XVr2AaLRht14ioTUNjfa8M, reason: not valid java name */
    public static /* synthetic */ void m817$r8$lambda$ufv2XVr2AaLRht14ioTUNjfa8M(AnalyticsListener r0, FlagSet r1) {
    }

    public static /* synthetic */ void $r8$lambda$xt9cDRygzIXoy5y6qRp3tTzm2G0(AnalyticsListener.EventTime r0, String r1, AnalyticsListener r2) {
        r2.onAudioDecoderReleased(r0, r1);
    }

    public static /* synthetic */ void $r8$lambda$y9hk9Y8FxQNdmQ3uewaHWI67PBc(AnalyticsListener.EventTime r0, MediaLoadData r1, AnalyticsListener r2) {
        r2.onUpstreamDiscarded(r0, r1);
    }

    /* renamed from: $r8$lambda$yMciVl9CxG_-QsBaHiRBvm2Tj6w, reason: not valid java name */
    public static /* synthetic */ void m818$r8$lambda$yMciVl9CxG_QsBaHiRBvm2Tj6w(AnalyticsListener.EventTime r0, int r1, AnalyticsListener r2) {
        r2.onRepeatModeChanged(r0, r1);
    }

    public static /* synthetic */ void $r8$lambda$zvw79qTGIt1GnIDpkUD8LXB_pYo(AnalyticsListener.EventTime r0, boolean r1, AnalyticsListener r2) {
        r2.onIsPlayingChanged(r0, r1);
    }

    public DefaultAnalyticsCollector(Clock r4) {
        this.clock = (Clock) Assertions.checkNotNull(r4);
        this.listeners = new ListenerSet(Util.getCurrentOrMainLooper(), r4, new DefaultAnalyticsCollector$$ExternalSyntheticLambda23());
        Timeline.Period r42 = new Timeline.Period();
        this.period = r42;
        this.window = new Timeline.Window();
        this.mediaPeriodQueueTracker = new MediaPeriodQueueTracker(r42);
        this.eventTimes = new SparseArray();
    }

    private AnalyticsListener.EventTime generateEventTime(MediaSource.MediaPeriodId r4) {
        Assertions.checkNotNull(this.player);
        if (r4 != null) goto L5;
        Timeline r1 = null;
    L6:
        if (r4 == null) goto L11;
        if (r1 == null) goto L11;
        return generateEventTime(r1, r1.getPeriodByUid(r4.periodUid, this.period).windowIndex, r4);
    L11:
        int r42 = this.player.getCurrentMediaItemIndex();
        Timeline r12 = this.player.getCurrentTimeline();
        if (r42 < r12.getWindowCount()) goto L16;
        r12 = Timeline.EMPTY;
    L16:
        return generateEventTime(r12, r42, null);
    L5:
        r1 = this.mediaPeriodQueueTracker.getMediaPeriodIdTimeline(r4);
        goto L6
    }

    private AnalyticsListener.EventTime generateLoadingMediaPeriodEventTime() {
        return generateEventTime(this.mediaPeriodQueueTracker.getLoadingMediaPeriod());
    }

    private AnalyticsListener.EventTime generateMediaPeriodEventTime(int r2, MediaSource.MediaPeriodId r3) {
        Assertions.checkNotNull(this.player);
        if (r3 != null) goto L5;
        Timeline r32 = this.player.getCurrentTimeline();
        if (r2 < r32.getWindowCount()) goto L15;
        r32 = Timeline.EMPTY;
    L15:
        return generateEventTime(r32, r2, null);
    L5:
        if (this.mediaPeriodQueueTracker.getMediaPeriodIdTimeline(r3) == null) goto L9;
        return generateEventTime(r3);
    L9:
        return generateEventTime(Timeline.EMPTY, r2, r3);
    }

    private AnalyticsListener.EventTime generatePlayingMediaPeriodEventTime() {
        return generateEventTime(this.mediaPeriodQueueTracker.getPlayingMediaPeriod());
    }

    private AnalyticsListener.EventTime generateReadingMediaPeriodEventTime() {
        return generateEventTime(this.mediaPeriodQueueTracker.getReadingMediaPeriod());
    }

    private AnalyticsListener.EventTime getEventTimeForErrorEvent(PlaybackException r2) {
        if ((r2 instanceof ExoPlaybackException) == false) goto L9;
        MediaSource.MediaPeriodId r22 = ((ExoPlaybackException) r2).mediaPeriodId;
        if (r22 == null) goto L9;
        return generateEventTime(r22);
    L9:
        return generateCurrentPlayerMediaPeriodEventTime();
    }

    private void releaseInternal() {
        AnalyticsListener.EventTime r0 = generateCurrentPlayerMediaPeriodEventTime();
        sendEvent(r0, 1028, new DefaultAnalyticsCollector$$ExternalSyntheticLambda37(r0));
        this.listeners.release();
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsCollector
    public void addListener(AnalyticsListener r1) {
        Assertions.checkNotNull(r1);
        this.listeners.add(r1);
    }

    public final AnalyticsListener.EventTime generateCurrentPlayerMediaPeriodEventTime() {
        return generateEventTime(this.mediaPeriodQueueTracker.getCurrentPlayerMediaPeriod());
    }

    @RequiresNonNull({"player"})
    public final AnalyticsListener.EventTime generateEventTime(Timeline r17, int r18, MediaSource.MediaPeriodId r19) {
        if (r17.isEmpty() == false) goto L5;
        MediaSource.MediaPeriodId r5 = null;
    L6:
        long r1 = this.clock.elapsedRealtime();
        if (r17.equals(this.player.getCurrentTimeline()) == true) goto L9;
    L11:
        boolean r6 = false;
    L12:
        long r7 = 0;
        if (r5 != null) goto L15;
    L23:
        if (r6 == false) goto L26;
        r7 = this.player.getContentPosition();
    L30:
        return new AnalyticsListener.EventTime(r1, r17, r18, r5, r7, this.player.getCurrentTimeline(), this.player.getCurrentMediaItemIndex(), this.mediaPeriodQueueTracker.getCurrentPlayerMediaPeriod(), this.player.getCurrentPosition(), this.player.getTotalBufferedDuration());
    L26:
        if (r17.isEmpty() == true) goto L30;
        r7 = r17.getWindow(r18, this.window).getDefaultPositionMs();
        goto L30
    L15:
        if (r5.isAd() == false) goto L23;
        if (r6 == false) goto L30;
        if (this.player.getCurrentAdGroupIndex() != r5.adGroupIndex) goto L30;
        if (this.player.getCurrentAdIndexInAdGroup() != r5.adIndexInAdGroup) goto L30;
        r7 = this.player.getCurrentPosition();
        goto L30
    L9:
        if (r18 != this.player.getCurrentMediaItemIndex()) goto L11;
        r6 = true;
        goto L12
    L5:
        r5 = r19;
        goto L6
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsCollector
    public final void notifySeekStarted() {
        if (this.isSeeking == true) goto L6;
        AnalyticsListener.EventTime r0 = generateCurrentPlayerMediaPeriodEventTime();
        this.isSeeking = true;
        sendEvent(r0, -1, new DefaultAnalyticsCollector$$ExternalSyntheticLambda26(r0));
        return;
    }

    @Override // androidx.media3.common.Player.Listener
    public final void onAudioAttributesChanged(AudioAttributes r3) {
        AnalyticsListener.EventTime r0 = generateReadingMediaPeriodEventTime();
        sendEvent(r0, 20, new DefaultAnalyticsCollector$$ExternalSyntheticLambda13(r0, r3));
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsCollector
    public final void onAudioCodecError(Exception r3) {
        AnalyticsListener.EventTime r0 = generateReadingMediaPeriodEventTime();
        sendEvent(r0, 1029, new DefaultAnalyticsCollector$$ExternalSyntheticLambda30(r0, r3));
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsCollector
    public final void onAudioDecoderInitialized(String r8, long r9, long r11) {
        AnalyticsListener.EventTime r1 = generateReadingMediaPeriodEventTime();
        sendEvent(r1, 1008, new DefaultAnalyticsCollector$$ExternalSyntheticLambda16(r1, r8, r11, r9));
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsCollector
    public final void onAudioDecoderReleased(String r3) {
        AnalyticsListener.EventTime r0 = generateReadingMediaPeriodEventTime();
        sendEvent(r0, 1012, new DefaultAnalyticsCollector$$ExternalSyntheticLambda5(r0, r3));
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsCollector
    public final void onAudioDisabled(DecoderCounters r3) {
        AnalyticsListener.EventTime r0 = generatePlayingMediaPeriodEventTime();
        sendEvent(r0, 1013, new DefaultAnalyticsCollector$$ExternalSyntheticLambda46(r0, r3));
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsCollector
    public final void onAudioEnabled(DecoderCounters r3) {
        AnalyticsListener.EventTime r0 = generateReadingMediaPeriodEventTime();
        sendEvent(r0, 1007, new DefaultAnalyticsCollector$$ExternalSyntheticLambda38(r0, r3));
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsCollector
    public final void onAudioInputFormatChanged(Format r3, DecoderReuseEvaluation r4) {
        AnalyticsListener.EventTime r0 = generateReadingMediaPeriodEventTime();
        sendEvent(r0, 1009, new DefaultAnalyticsCollector$$ExternalSyntheticLambda55(r0, r3, r4));
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsCollector
    public final void onAudioPositionAdvancing(long r3) {
        AnalyticsListener.EventTime r0 = generateReadingMediaPeriodEventTime();
        sendEvent(r0, 1010, new DefaultAnalyticsCollector$$ExternalSyntheticLambda41(r0, r3));
    }

    @Override // androidx.media3.common.Player.Listener
    public final void onAudioSessionIdChanged(int r3) {
        AnalyticsListener.EventTime r0 = generateReadingMediaPeriodEventTime();
        sendEvent(r0, 21, new DefaultAnalyticsCollector$$ExternalSyntheticLambda53(r0, r3));
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsCollector
    public final void onAudioSinkError(Exception r3) {
        AnalyticsListener.EventTime r0 = generateReadingMediaPeriodEventTime();
        sendEvent(r0, 1014, new DefaultAnalyticsCollector$$ExternalSyntheticLambda60(r0, r3));
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsCollector
    public void onAudioTrackInitialized(AudioSink.AudioTrackConfig r3) {
        AnalyticsListener.EventTime r0 = generateReadingMediaPeriodEventTime();
        sendEvent(r0, 1031, new DefaultAnalyticsCollector$$ExternalSyntheticLambda52(r0, r3));
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsCollector
    public void onAudioTrackReleased(AudioSink.AudioTrackConfig r3) {
        AnalyticsListener.EventTime r0 = generateReadingMediaPeriodEventTime();
        sendEvent(r0, 1032, new DefaultAnalyticsCollector$$ExternalSyntheticLambda65(r0, r3));
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsCollector
    public final void onAudioUnderrun(int r8, long r9, long r11) {
        AnalyticsListener.EventTime r1 = generateReadingMediaPeriodEventTime();
        sendEvent(r1, 1011, new DefaultAnalyticsCollector$$ExternalSyntheticLambda39(r1, r8, r9, r11));
    }

    @Override // androidx.media3.common.Player.Listener
    public void onAvailableCommandsChanged(Player.Commands r3) {
        AnalyticsListener.EventTime r0 = generateCurrentPlayerMediaPeriodEventTime();
        sendEvent(r0, 13, new DefaultAnalyticsCollector$$ExternalSyntheticLambda6(r0, r3));
    }

    @Override // androidx.media3.exoplayer.upstream.BandwidthMeter.EventListener
    public final void onBandwidthSample(int r8, long r9, long r11) {
        AnalyticsListener.EventTime r1 = generateLoadingMediaPeriodEventTime();
        sendEvent(r1, 1006, new DefaultAnalyticsCollector$$ExternalSyntheticLambda33(r1, r8, r9, r11));
    }

    @Override // androidx.media3.common.Player.Listener
    public void onCues(CueGroup r3) {
        AnalyticsListener.EventTime r0 = generateCurrentPlayerMediaPeriodEventTime();
        sendEvent(r0, 27, new DefaultAnalyticsCollector$$ExternalSyntheticLambda2(r0, r3));
    }

    @Override // androidx.media3.common.Player.Listener
    public void onCues(List<Cue> r3) {
        AnalyticsListener.EventTime r0 = generateCurrentPlayerMediaPeriodEventTime();
        sendEvent(r0, 27, new DefaultAnalyticsCollector$$ExternalSyntheticLambda22(r0, r3));
    }

    @Override // androidx.media3.common.Player.Listener
    public void onDeviceInfoChanged(DeviceInfo r3) {
        AnalyticsListener.EventTime r0 = generateCurrentPlayerMediaPeriodEventTime();
        sendEvent(r0, 29, new DefaultAnalyticsCollector$$ExternalSyntheticLambda48(r0, r3));
    }

    @Override // androidx.media3.common.Player.Listener
    public void onDeviceVolumeChanged(int r3, boolean r4) {
        AnalyticsListener.EventTime r0 = generateCurrentPlayerMediaPeriodEventTime();
        sendEvent(r0, 30, new DefaultAnalyticsCollector$$ExternalSyntheticLambda43(r0, r3, r4));
    }

    @Override // androidx.media3.exoplayer.source.MediaSourceEventListener
    public final void onDownstreamFormatChanged(int r1, MediaSource.MediaPeriodId r2, MediaLoadData r3) {
        AnalyticsListener.EventTime r12 = generateMediaPeriodEventTime(r1, r2);
        sendEvent(r12, 1004, new DefaultAnalyticsCollector$$ExternalSyntheticLambda34(r12, r3));
    }

    @Override // androidx.media3.exoplayer.drm.DrmSessionEventListener
    public final void onDrmKeysLoaded(int r2, MediaSource.MediaPeriodId r3) {
        AnalyticsListener.EventTime r22 = generateMediaPeriodEventTime(r2, r3);
        sendEvent(r22, 1023, new DefaultAnalyticsCollector$$ExternalSyntheticLambda64(r22));
    }

    @Override // androidx.media3.exoplayer.drm.DrmSessionEventListener
    public final void onDrmKeysRemoved(int r2, MediaSource.MediaPeriodId r3) {
        AnalyticsListener.EventTime r22 = generateMediaPeriodEventTime(r2, r3);
        sendEvent(r22, 1026, new DefaultAnalyticsCollector$$ExternalSyntheticLambda62(r22));
    }

    @Override // androidx.media3.exoplayer.drm.DrmSessionEventListener
    public final void onDrmKeysRestored(int r2, MediaSource.MediaPeriodId r3) {
        AnalyticsListener.EventTime r22 = generateMediaPeriodEventTime(r2, r3);
        sendEvent(r22, 1025, new DefaultAnalyticsCollector$$ExternalSyntheticLambda63(r22));
    }

    @Override // androidx.media3.exoplayer.drm.DrmSessionEventListener
    public final void onDrmSessionAcquired(int r1, MediaSource.MediaPeriodId r2, int r3) {
        AnalyticsListener.EventTime r12 = generateMediaPeriodEventTime(r1, r2);
        sendEvent(r12, 1022, new DefaultAnalyticsCollector$$ExternalSyntheticLambda47(r12, r3));
    }

    @Override // androidx.media3.exoplayer.drm.DrmSessionEventListener
    public final void onDrmSessionManagerError(int r1, MediaSource.MediaPeriodId r2, Exception r3) {
        AnalyticsListener.EventTime r12 = generateMediaPeriodEventTime(r1, r2);
        sendEvent(r12, 1024, new DefaultAnalyticsCollector$$ExternalSyntheticLambda49(r12, r3));
    }

    @Override // androidx.media3.exoplayer.drm.DrmSessionEventListener
    public final void onDrmSessionReleased(int r2, MediaSource.MediaPeriodId r3) {
        AnalyticsListener.EventTime r22 = generateMediaPeriodEventTime(r2, r3);
        sendEvent(r22, 1027, new DefaultAnalyticsCollector$$ExternalSyntheticLambda59(r22));
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsCollector
    public final void onDroppedFrames(int r3, long r4) {
        AnalyticsListener.EventTime r0 = generatePlayingMediaPeriodEventTime();
        sendEvent(r0, 1018, new DefaultAnalyticsCollector$$ExternalSyntheticLambda42(r0, r3, r4));
    }

    @Override // androidx.media3.common.Player.Listener
    public void onEvents(Player r1, Player.Events r2) {
    }

    @Override // androidx.media3.common.Player.Listener
    public final void onIsLoadingChanged(boolean r3) {
        AnalyticsListener.EventTime r0 = generateCurrentPlayerMediaPeriodEventTime();
        sendEvent(r0, 3, new DefaultAnalyticsCollector$$ExternalSyntheticLambda4(r0, r3));
    }

    @Override // androidx.media3.common.Player.Listener
    public void onIsPlayingChanged(boolean r3) {
        AnalyticsListener.EventTime r0 = generateCurrentPlayerMediaPeriodEventTime();
        sendEvent(r0, 7, new DefaultAnalyticsCollector$$ExternalSyntheticLambda15(r0, r3));
    }

    @Override // androidx.media3.exoplayer.source.MediaSourceEventListener
    public final void onLoadCanceled(int r1, MediaSource.MediaPeriodId r2, LoadEventInfo r3, MediaLoadData r4) {
        AnalyticsListener.EventTime r12 = generateMediaPeriodEventTime(r1, r2);
        sendEvent(r12, 1002, new DefaultAnalyticsCollector$$ExternalSyntheticLambda45(r12, r3, r4));
    }

    @Override // androidx.media3.exoplayer.source.MediaSourceEventListener
    public final void onLoadCompleted(int r1, MediaSource.MediaPeriodId r2, LoadEventInfo r3, MediaLoadData r4) {
        AnalyticsListener.EventTime r12 = generateMediaPeriodEventTime(r1, r2);
        sendEvent(r12, WebSocketProtocol.CLOSE_CLIENT_GOING_AWAY, new DefaultAnalyticsCollector$$ExternalSyntheticLambda50(r12, r3, r4));
    }

    @Override // androidx.media3.exoplayer.source.MediaSourceEventListener
    public final void onLoadError(int r1, MediaSource.MediaPeriodId r2, LoadEventInfo r3, MediaLoadData r4, IOException r5, boolean r6) {
        AnalyticsListener.EventTime r22 = generateMediaPeriodEventTime(r1, r2);
        sendEvent(r22, 1003, new DefaultAnalyticsCollector$$ExternalSyntheticLambda40(r22, r3, r4, r5, r6));
    }

    @Override // androidx.media3.exoplayer.source.MediaSourceEventListener
    public final void onLoadStarted(int r1, MediaSource.MediaPeriodId r2, LoadEventInfo r3, MediaLoadData r4, int r5) {
        AnalyticsListener.EventTime r12 = generateMediaPeriodEventTime(r1, r2);
        sendEvent(r12, 1000, new DefaultAnalyticsCollector$$ExternalSyntheticLambda36(r12, r3, r4, r5));
    }

    @Override // androidx.media3.common.Player.Listener
    public void onLoadingChanged(boolean r1) {
    }

    @Override // androidx.media3.common.Player.Listener
    public final void onMediaItemTransition(MediaItem r3, int r4) {
        AnalyticsListener.EventTime r0 = generateCurrentPlayerMediaPeriodEventTime();
        sendEvent(r0, 1, new DefaultAnalyticsCollector$$ExternalSyntheticLambda8(r0, r3, r4));
    }

    @Override // androidx.media3.common.Player.Listener
    public void onMediaMetadataChanged(MediaMetadata r3) {
        AnalyticsListener.EventTime r0 = generateCurrentPlayerMediaPeriodEventTime();
        sendEvent(r0, 14, new DefaultAnalyticsCollector$$ExternalSyntheticLambda3(r0, r3));
    }

    @Override // androidx.media3.common.Player.Listener
    public final void onMetadata(Metadata r3) {
        AnalyticsListener.EventTime r0 = generateCurrentPlayerMediaPeriodEventTime();
        sendEvent(r0, 28, new DefaultAnalyticsCollector$$ExternalSyntheticLambda14(r0, r3));
    }

    @Override // androidx.media3.common.Player.Listener
    public final void onPlayWhenReadyChanged(boolean r3, int r4) {
        AnalyticsListener.EventTime r0 = generateCurrentPlayerMediaPeriodEventTime();
        sendEvent(r0, 5, new DefaultAnalyticsCollector$$ExternalSyntheticLambda20(r0, r3, r4));
    }

    @Override // androidx.media3.common.Player.Listener
    public final void onPlaybackParametersChanged(PlaybackParameters r3) {
        AnalyticsListener.EventTime r0 = generateCurrentPlayerMediaPeriodEventTime();
        sendEvent(r0, 12, new DefaultAnalyticsCollector$$ExternalSyntheticLambda0(r0, r3));
    }

    @Override // androidx.media3.common.Player.Listener
    public final void onPlaybackStateChanged(int r3) {
        AnalyticsListener.EventTime r0 = generateCurrentPlayerMediaPeriodEventTime();
        sendEvent(r0, 4, new DefaultAnalyticsCollector$$ExternalSyntheticLambda25(r0, r3));
    }

    @Override // androidx.media3.common.Player.Listener
    public final void onPlaybackSuppressionReasonChanged(int r3) {
        AnalyticsListener.EventTime r0 = generateCurrentPlayerMediaPeriodEventTime();
        sendEvent(r0, 6, new DefaultAnalyticsCollector$$ExternalSyntheticLambda17(r0, r3));
    }

    @Override // androidx.media3.common.Player.Listener
    public final void onPlayerError(PlaybackException r3) {
        AnalyticsListener.EventTime r0 = getEventTimeForErrorEvent(r3);
        sendEvent(r0, 10, new DefaultAnalyticsCollector$$ExternalSyntheticLambda24(r0, r3));
    }

    @Override // androidx.media3.common.Player.Listener
    public void onPlayerErrorChanged(PlaybackException r3) {
        AnalyticsListener.EventTime r0 = getEventTimeForErrorEvent(r3);
        sendEvent(r0, 10, new DefaultAnalyticsCollector$$ExternalSyntheticLambda19(r0, r3));
    }

    @Override // androidx.media3.common.Player.Listener
    public final void onPlayerStateChanged(boolean r3, int r4) {
        AnalyticsListener.EventTime r0 = generateCurrentPlayerMediaPeriodEventTime();
        sendEvent(r0, -1, new DefaultAnalyticsCollector$$ExternalSyntheticLambda12(r0, r3, r4));
    }

    @Override // androidx.media3.common.Player.Listener
    public void onPlaylistMetadataChanged(MediaMetadata r3) {
        AnalyticsListener.EventTime r0 = generateCurrentPlayerMediaPeriodEventTime();
        sendEvent(r0, 15, new DefaultAnalyticsCollector$$ExternalSyntheticLambda67(r0, r3));
    }

    @Override // androidx.media3.common.Player.Listener
    public void onPositionDiscontinuity(int r1) {
    }

    @Override // androidx.media3.common.Player.Listener
    public final void onPositionDiscontinuity(Player.PositionInfo r3, Player.PositionInfo r4, int r5) {
        if (r5 != 1) goto L5;
        this.isSeeking = false;
    L5:
        this.mediaPeriodQueueTracker.onPositionDiscontinuity((Player) Assertions.checkNotNull(this.player));
        AnalyticsListener.EventTime r0 = generateCurrentPlayerMediaPeriodEventTime();
        sendEvent(r0, 11, new DefaultAnalyticsCollector$$ExternalSyntheticLambda27(r0, r5, r3, r4));
    }

    @Override // androidx.media3.common.Player.Listener
    public void onRenderedFirstFrame() {
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsCollector
    public final void onRenderedFirstFrame(Object r3, long r4) {
        AnalyticsListener.EventTime r0 = generateReadingMediaPeriodEventTime();
        sendEvent(r0, 26, new DefaultAnalyticsCollector$$ExternalSyntheticLambda57(r0, r3, r4));
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsCollector
    public void onRendererReadyChanged(int r3, int r4, boolean r5) {
        AnalyticsListener.EventTime r0 = generateReadingMediaPeriodEventTime();
        sendEvent(r0, 1033, new DefaultAnalyticsCollector$$ExternalSyntheticLambda21(r0, r3, r4, r5));
    }

    @Override // androidx.media3.common.Player.Listener
    public final void onRepeatModeChanged(int r3) {
        AnalyticsListener.EventTime r0 = generateCurrentPlayerMediaPeriodEventTime();
        sendEvent(r0, 8, new DefaultAnalyticsCollector$$ExternalSyntheticLambda29(r0, r3));
    }

    @Override // androidx.media3.common.Player.Listener
    public final void onShuffleModeEnabledChanged(boolean r3) {
        AnalyticsListener.EventTime r0 = generateCurrentPlayerMediaPeriodEventTime();
        sendEvent(r0, 9, new DefaultAnalyticsCollector$$ExternalSyntheticLambda68(r0, r3));
    }

    @Override // androidx.media3.common.Player.Listener
    public final void onSkipSilenceEnabledChanged(boolean r3) {
        AnalyticsListener.EventTime r0 = generateReadingMediaPeriodEventTime();
        sendEvent(r0, 23, new DefaultAnalyticsCollector$$ExternalSyntheticLambda61(r0, r3));
    }

    @Override // androidx.media3.common.Player.Listener
    public final void onSurfaceSizeChanged(int r3, int r4) {
        AnalyticsListener.EventTime r0 = generateReadingMediaPeriodEventTime();
        sendEvent(r0, 24, new DefaultAnalyticsCollector$$ExternalSyntheticLambda32(r0, r3, r4));
    }

    @Override // androidx.media3.common.Player.Listener
    public final void onTimelineChanged(Timeline r2, int r3) {
        this.mediaPeriodQueueTracker.onTimelineChanged((Player) Assertions.checkNotNull(this.player));
        AnalyticsListener.EventTime r22 = generateCurrentPlayerMediaPeriodEventTime();
        sendEvent(r22, 0, new DefaultAnalyticsCollector$$ExternalSyntheticLambda7(r22, r3));
    }

    @Override // androidx.media3.common.Player.Listener
    public void onTrackSelectionParametersChanged(TrackSelectionParameters r3) {
        AnalyticsListener.EventTime r0 = generateCurrentPlayerMediaPeriodEventTime();
        sendEvent(r0, 19, new DefaultAnalyticsCollector$$ExternalSyntheticLambda66(r0, r3));
    }

    @Override // androidx.media3.common.Player.Listener
    public void onTracksChanged(Tracks r3) {
        AnalyticsListener.EventTime r0 = generateCurrentPlayerMediaPeriodEventTime();
        sendEvent(r0, 2, new DefaultAnalyticsCollector$$ExternalSyntheticLambda1(r0, r3));
    }

    @Override // androidx.media3.exoplayer.source.MediaSourceEventListener
    public final void onUpstreamDiscarded(int r1, MediaSource.MediaPeriodId r2, MediaLoadData r3) {
        AnalyticsListener.EventTime r12 = generateMediaPeriodEventTime(r1, r2);
        sendEvent(r12, WebSocketProtocol.CLOSE_NO_STATUS_CODE, new DefaultAnalyticsCollector$$ExternalSyntheticLambda58(r12, r3));
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsCollector
    public final void onVideoCodecError(Exception r3) {
        AnalyticsListener.EventTime r0 = generateReadingMediaPeriodEventTime();
        sendEvent(r0, 1030, new DefaultAnalyticsCollector$$ExternalSyntheticLambda10(r0, r3));
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsCollector
    public final void onVideoDecoderInitialized(String r8, long r9, long r11) {
        AnalyticsListener.EventTime r1 = generateReadingMediaPeriodEventTime();
        sendEvent(r1, 1016, new DefaultAnalyticsCollector$$ExternalSyntheticLambda31(r1, r8, r11, r9));
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsCollector
    public final void onVideoDecoderReleased(String r3) {
        AnalyticsListener.EventTime r0 = generateReadingMediaPeriodEventTime();
        sendEvent(r0, 1019, new DefaultAnalyticsCollector$$ExternalSyntheticLambda18(r0, r3));
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsCollector
    public final void onVideoDisabled(DecoderCounters r3) {
        AnalyticsListener.EventTime r0 = generatePlayingMediaPeriodEventTime();
        sendEvent(r0, 1020, new DefaultAnalyticsCollector$$ExternalSyntheticLambda35(r0, r3));
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsCollector
    public final void onVideoEnabled(DecoderCounters r3) {
        AnalyticsListener.EventTime r0 = generateReadingMediaPeriodEventTime();
        sendEvent(r0, 1015, new DefaultAnalyticsCollector$$ExternalSyntheticLambda56(r0, r3));
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsCollector
    public final void onVideoFrameProcessingOffset(long r3, int r5) {
        AnalyticsListener.EventTime r0 = generatePlayingMediaPeriodEventTime();
        sendEvent(r0, 1021, new DefaultAnalyticsCollector$$ExternalSyntheticLambda44(r0, r3, r5));
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsCollector
    public final void onVideoInputFormatChanged(Format r3, DecoderReuseEvaluation r4) {
        AnalyticsListener.EventTime r0 = generateReadingMediaPeriodEventTime();
        sendEvent(r0, 1017, new DefaultAnalyticsCollector$$ExternalSyntheticLambda51(r0, r3, r4));
    }

    @Override // androidx.media3.common.Player.Listener
    public final void onVideoSizeChanged(VideoSize r3) {
        AnalyticsListener.EventTime r0 = generateReadingMediaPeriodEventTime();
        sendEvent(r0, 25, new DefaultAnalyticsCollector$$ExternalSyntheticLambda54(r0, r3));
    }

    @Override // androidx.media3.common.Player.Listener
    public final void onVolumeChanged(float r3) {
        AnalyticsListener.EventTime r0 = generateReadingMediaPeriodEventTime();
        sendEvent(r0, 22, new DefaultAnalyticsCollector$$ExternalSyntheticLambda9(r0, r3));
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsCollector
    public void release() {
        ((HandlerWrapper) Assertions.checkStateNotNull(this.handler)).post(new DefaultAnalyticsCollector$$ExternalSyntheticLambda28(this));
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsCollector
    public void removeListener(AnalyticsListener r1) {
        this.listeners.remove(r1);
    }

    public final void sendEvent(AnalyticsListener.EventTime r2, int r3, ListenerSet.Event<AnalyticsListener> r4) {
        this.eventTimes.put(r3, r2);
        this.listeners.sendEvent(r3, r4);
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsCollector
    public void setPlayer(Player r3, Looper r4) {
        if (this.player != null) goto L5;
    L8:
        boolean r0 = true;
    L9:
        Assertions.checkState(r0);
        this.player = (Player) Assertions.checkNotNull(r3);
        this.handler = this.clock.createHandler(r4, null);
        this.listeners = this.listeners.copy(r4, new DefaultAnalyticsCollector$$ExternalSyntheticLambda11(this, r3));
        return;
    L5:
        if (MediaPeriodQueueTracker.access$000(this.mediaPeriodQueueTracker).isEmpty() == true) goto L8;
        r0 = false;
        goto L9
    }

    @Override // androidx.media3.exoplayer.analytics.AnalyticsCollector
    public final void updateMediaPeriodQueueInfo(List<MediaSource.MediaPeriodId> r2, MediaSource.MediaPeriodId r3) {
        this.mediaPeriodQueueTracker.onQueueUpdated(r2, r3, (Player) Assertions.checkNotNull(this.player));
    }
}
