package androidx.media3.exoplayer;

import android.content.Context;
import android.os.Looper;
import androidx.media3.common.AudioAttributes;
import androidx.media3.common.Format;
import androidx.media3.common.Player;
import androidx.media3.common.VideoListener;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Clock;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.DefaultLivePlaybackSpeedControl;
import androidx.media3.exoplayer.analytics.AnalyticsCollector;
import androidx.media3.exoplayer.analytics.AnalyticsListener;
import androidx.media3.exoplayer.source.DefaultMediaSourceFactory;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.trackselection.DefaultTrackSelector;
import androidx.media3.exoplayer.trackselection.TrackSelector;
import androidx.media3.exoplayer.upstream.BandwidthMeter;
import androidx.media3.exoplayer.upstream.DefaultBandwidthMeter;
import androidx.media3.exoplayer.video.VideoFrameMetadataListener;
import androidx.media3.extractor.DefaultExtractorsFactory;
import com.google.common.base.Function;
import com.google.common.base.Supplier;
import java.util.concurrent.Executor;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
public interface ExoPlayer extends Player {

    public interface AudioOffloadListener {
        default void onOffloadedPlayback(boolean r1) {
        }

        void onSleepingForOffloadChanged(boolean r1);
    }

    public static final class Builder {
        Function<Clock, AnalyticsCollector> analyticsCollectorFunction;
        AudioAttributes audioAttributes;
        Supplier<BandwidthMeter> bandwidthMeterSupplier;
        boolean buildCalled;
        Clock clock;
        final Context context;
        long detachSurfaceTimeoutMs;
        boolean deviceVolumeControlEnabled;
        boolean dynamicSchedulingEnabled;
        long foregroundModeTimeoutMs;
        boolean handleAudioBecomingNoisy;
        boolean handleAudioFocus;
        LivePlaybackSpeedControl livePlaybackSpeedControl;
        Supplier<LoadControl> loadControlSupplier;
        Looper looper;
        long maxSeekToPreviousPositionMs;
        Supplier<MediaSource.Factory> mediaSourceFactorySupplier;
        boolean pauseAtEndOfMediaItems;
        PlaybackLooperProvider playbackLooperProvider;
        String playerName;
        int priority;
        long releaseTimeoutMs;
        Supplier<RenderersFactory> renderersFactorySupplier;
        ScrubbingModeParameters scrubbingModeParameters;
        long seekBackIncrementMs;
        long seekForwardIncrementMs;
        SeekParameters seekParameters;
        boolean skipSilenceEnabled;
        SuitableOutputChecker suitableOutputChecker;
        boolean suppressPlaybackOnUnsuitableOutput;
        Supplier<TrackSelector> trackSelectorSupplier;
        boolean useLazyPreparation;
        boolean usePlatformDiagnostics;
        int videoChangeFrameRateStrategy;
        int videoScalingMode;
        int wakeMode;

        /* renamed from: $r8$lambda$1S4JTYE6v7ASEpYX-zQPTquCFzM, reason: not valid java name */
        public static /* synthetic */ RenderersFactory m766$r8$lambda$1S4JTYE6v7ASEpYXzQPTquCFzM(RenderersFactory r0) {
            return r0;
        }

        /* renamed from: $r8$lambda$6RcAC-_BqYMBagvVbrZlEBfEca8, reason: not valid java name */
        public static /* synthetic */ RenderersFactory m767$r8$lambda$6RcAC_BqYMBagvVbrZlEBfEca8(Context r1) {
            return new DefaultRenderersFactory(r1);
        }

        public static /* synthetic */ MediaSource.Factory $r8$lambda$EUAzPSpf_tY17bpaOOdpyKtURb0(Context r2) {
            return new DefaultMediaSourceFactory(r2, new DefaultExtractorsFactory());
        }

        public static /* synthetic */ LoadControl $r8$lambda$HekyiBTTjarrX819XM5j8sFRos8(LoadControl r0) {
            return r0;
        }

        public static /* synthetic */ BandwidthMeter $r8$lambda$WPXsxQz0xLwRE7OHWiDerxu51BU(Context r0) {
            return DefaultBandwidthMeter.getSingletonInstance(r0);
        }

        /* renamed from: $r8$lambda$YdD1-a4RqBfR_B5CWrkD4jlJiGk, reason: not valid java name */
        public static /* synthetic */ TrackSelector m768$r8$lambda$YdD1a4RqBfR_B5CWrkD4jlJiGk(TrackSelector r0) {
            return r0;
        }

        public static /* synthetic */ TrackSelector $r8$lambda$xUvto8h1vMLVIth0_snkoRcg_Fw(Context r1) {
            return new DefaultTrackSelector(r1);
        }

        public Builder(Context r3) {
            this(r3, new ExoPlayer$Builder$$ExternalSyntheticLambda2(r3), new ExoPlayer$Builder$$ExternalSyntheticLambda3(r3));
        }

        private Builder(Context r9, Supplier<RenderersFactory> r10, Supplier<MediaSource.Factory> r11) {
            this(r9, r10, r11, new ExoPlayer$Builder$$ExternalSyntheticLambda5(r9), new ExoPlayer$Builder$$ExternalSyntheticLambda6(), new ExoPlayer$Builder$$ExternalSyntheticLambda7(r9), new ExoPlayer$Builder$$ExternalSyntheticLambda8());
        }

        private Builder(Context r1, Supplier<RenderersFactory> r2, Supplier<MediaSource.Factory> r3, Supplier<TrackSelector> r4, Supplier<LoadControl> r5, Supplier<BandwidthMeter> r6, Function<Clock, AnalyticsCollector> r7) {
            this.context = (Context) Assertions.checkNotNull(r1);
            this.renderersFactorySupplier = r2;
            this.mediaSourceFactorySupplier = r3;
            this.trackSelectorSupplier = r4;
            this.loadControlSupplier = r5;
            this.bandwidthMeterSupplier = r6;
            this.analyticsCollectorFunction = r7;
            this.looper = Util.getCurrentOrMainLooper();
            this.audioAttributes = AudioAttributes.DEFAULT;
            this.wakeMode = 0;
            this.videoScalingMode = 1;
            this.videoChangeFrameRateStrategy = 0;
            this.useLazyPreparation = true;
            this.seekParameters = SeekParameters.DEFAULT;
            this.seekBackIncrementMs = 5000;
            this.seekForwardIncrementMs = 15000;
            this.maxSeekToPreviousPositionMs = 3000;
            this.scrubbingModeParameters = ScrubbingModeParameters.DEFAULT;
            this.livePlaybackSpeedControl = new DefaultLivePlaybackSpeedControl.Builder().build();
            this.clock = Clock.DEFAULT;
            this.releaseTimeoutMs = 500;
            this.detachSurfaceTimeoutMs = 2000;
            this.usePlatformDiagnostics = true;
            this.playerName = _UrlKt.FRAGMENT_ENCODE_SET;
            this.priority = -1000;
            this.suitableOutputChecker = new DefaultSuitableOutputChecker();
        }

        public ExoPlayer build() {
            Assertions.checkState(!this.buildCalled);
            this.buildCalled = true;
            return new ExoPlayerImpl(this, null);
        }

        public Builder setLoadControl(LoadControl r2) {
            Assertions.checkState(!this.buildCalled);
            Assertions.checkNotNull(r2);
            this.loadControlSupplier = new ExoPlayer$Builder$$ExternalSyntheticLambda0(r2);
            return this;
        }

        public Builder setLooper(Looper r2) {
            Assertions.checkState(!this.buildCalled);
            Assertions.checkNotNull(r2);
            this.looper = r2;
            return this;
        }

        public Builder setRenderersFactory(RenderersFactory r2) {
            Assertions.checkState(!this.buildCalled);
            Assertions.checkNotNull(r2);
            this.renderersFactorySupplier = new ExoPlayer$Builder$$ExternalSyntheticLambda4(r2);
            return this;
        }

        public Builder setTrackSelector(TrackSelector r2) {
            Assertions.checkState(!this.buildCalled);
            Assertions.checkNotNull(r2);
            this.trackSelectorSupplier = new ExoPlayer$Builder$$ExternalSyntheticLambda1(r2);
            return this;
        }
    }

    public static class PreloadConfiguration {
        public static final PreloadConfiguration DEFAULT = null;
        public final long targetPreloadDurationUs;

        static {
            DEFAULT = new PreloadConfiguration(-9223372036854775807L);
        }

        public PreloadConfiguration(long r1) {
            this.targetPreloadDurationUs = r1;
        }
    }

    void addAnalyticsListener(AnalyticsListener r1);

    void addVideoListener(VideoListener r1);

    Renderer getRenderer(int r1);

    Format getVideoFormat();

    void release();

    void removeAnalyticsListener(AnalyticsListener r1);

    void removeVideoListener(VideoListener r1);

    void setMediaSource(MediaSource r1);

    void setMediaSource(MediaSource r1, boolean r2);

    void setSeekParameters(SeekParameters r1);

    void setVideoFrameMetadataListener(VideoFrameMetadataListener r1);

    void setWorkerQueue(Executor r1);
}
