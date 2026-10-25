package androidx.media3.exoplayer.audio;

import android.content.Context;
import android.media.AudioDeviceInfo;
import android.media.AudioRouting;
import android.media.AudioTrack;
import android.media.AudioTrack$StreamEventCallback;
import android.media.PlaybackParams;
import android.media.metrics.LogSessionId;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.util.Pair;
import androidx.media3.common.AudioAttributes;
import androidx.media3.common.AuxEffectInfo;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.PlaybackParameters;
import androidx.media3.common.audio.AudioProcessingPipeline;
import androidx.media3.common.audio.AudioProcessor;
import androidx.media3.common.audio.AudioProcessorChain;
import androidx.media3.common.audio.SonicAudioProcessor;
import androidx.media3.common.audio.ToInt16PcmAudioProcessor;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Clock;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.exoplayer.analytics.PlayerId;
import androidx.media3.exoplayer.analytics.PlayerId$LogSessionIdApi31$$ExternalSyntheticApiModelOutline0;
import androidx.media3.exoplayer.audio.AudioSink;
import androidx.media3.exoplayer.audio.AudioTrackPositionTracker;
import androidx.media3.exoplayer.audio.DefaultAudioTrackBufferSizeProvider;
import androidx.media3.extractor.Ac3Util;
import androidx.media3.extractor.Ac4Util;
import androidx.media3.extractor.DtsUtil;
import androidx.media3.extractor.ExtractorUtil;
import androidx.media3.extractor.MpegAudioUtil;
import androidx.media3.extractor.OpusUtil;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;
import com.google.gson.JsonArray$$ExternalSyntheticBUOutline0;
import java.math.RoundingMode;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayDeque;
import java.util.Objects;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import kotlin.time.DurationKt;
import okio.Segment$$ExternalSyntheticBUOutline0;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

/* loaded from: classes4.dex */
public final class DefaultAudioSink implements AudioSink {
    public static boolean failOnSpuriousAudioTimestamp = false;
    private static int pendingReleaseCount;
    private static ScheduledExecutorService releaseExecutor;
    private static final Object releaseExecutorLock = null;
    private long accumulatedSkippedSilenceDurationUs;
    private MediaPositionParameters afterDrainParameters;
    private AudioAttributes audioAttributes;
    private AudioCapabilities audioCapabilities;
    private AudioCapabilitiesReceiver audioCapabilitiesReceiver;
    private final ExoPlayer.AudioOffloadListener audioOffloadListener;
    private final AudioOffloadSupportProvider audioOffloadSupportProvider;
    private AudioProcessingPipeline audioProcessingPipeline;
    private final AudioProcessorChain audioProcessorChain;
    private int audioSessionId;
    private AudioTrack audioTrack;
    private final AudioTrackBufferSizeProvider audioTrackBufferSizeProvider;
    private final AudioTrackPositionTracker audioTrackPositionTracker;
    private final AudioTrackProvider audioTrackProvider;
    private AuxEffectInfo auxEffectInfo;
    private ByteBuffer avSyncHeader;
    private final ImmutableList<AudioProcessor> availableAudioProcessors;
    private int bytesUntilNextAvSync;
    private final ChannelMappingAudioProcessor channelMappingAudioProcessor;
    private Configuration configuration;
    private final Context context;
    private final int contextDeviceId;
    private Context contextWithDeviceId;
    private final boolean enableFloatOutput;
    private boolean enableOnAudioPositionAdvancingFix;
    private boolean externalAudioSessionIdProvided;
    private int framesPerEncodedSample;
    private boolean handledEndOfStream;
    private boolean handledOffloadOnPresentationEnded;
    private final PendingExceptionHolder<AudioSink.InitializationException> initializationExceptionPendingExceptionHolder;
    private ByteBuffer inputBuffer;
    private int inputBufferAccessUnitCount;
    private boolean isWaitingForOffloadEndOfStreamHandled;
    private long lastFeedElapsedRealtimeMs;
    private long lastTunnelingAvSyncPresentationTimeUs;
    private AudioSink.Listener listener;
    private MediaPositionParameters mediaPositionParameters;
    private final ArrayDeque<MediaPositionParameters> mediaPositionParametersCheckpoints;
    private boolean offloadDisabledUntilNextConfiguration;
    private int offloadMode;
    private StreamEventCallbackV29 offloadStreamEventCallbackV29;
    private OnRoutingChangedListenerApi24 onRoutingChangedListener;
    private ByteBuffer outputBuffer;
    private boolean pendingAudioSessionIdChangeConfirmation;
    private Configuration pendingConfiguration;
    private Looper playbackLooper;
    private PlaybackParameters playbackParameters;
    private PlayerId playerId;
    private boolean playing;
    private final boolean preferAudioTrackPlaybackParams;
    private AudioDeviceInfoApi23 preferredDevice;
    private Handler reportSkippedSilenceHandler;
    private boolean skipSilenceEnabled;
    private long skippedOutputFrameCountAtLastPosition;
    private long startMediaTimeUs;
    private boolean startMediaTimeUsNeedsInit;
    private boolean startMediaTimeUsNeedsSync;
    private boolean stoppedAudioTrack;
    private long submittedEncodedFrames;
    private long submittedPcmBytes;
    private final ToFloatPcmAudioProcessor toFloatPcmAudioProcessor;
    private final ToInt16PcmAudioProcessor toInt16PcmAudioProcessor;
    private final TrimmingAudioProcessor trimmingAudioProcessor;
    private boolean tunneling;
    private float volume;
    private final PendingExceptionHolder<AudioSink.WriteException> writeExceptionPendingExceptionHolder;
    private long writtenEncodedFrames;
    private long writtenPcmBytes;

    /* renamed from: androidx.media3.exoplayer.audio.DefaultAudioSink$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static final class Api23 {
        public static long getAudioTrackBufferSizeUs(AudioTrack r7, Configuration r8) {
            if (r8.outputMode != 0) goto L7;
            return r8.framesToDurationUs(r7.getBufferSizeInFrames());
        L7:
            return Util.scaleLargeValue(r7.getBufferSizeInFrames(), 1000000, DefaultAudioSink.access$2200(r8.outputEncoding), RoundingMode.DOWN);
        }

        public static void setPreferredDeviceOnAudioTrack(AudioTrack r0, AudioDeviceInfoApi23 r1) {
            if (r1 != null) goto L4;
            AudioDeviceInfo r12 = null;
        L5:
            r0.setPreferredDevice(r12);
            return;
        L4:
            r12 = r1.audioDeviceInfo;
            goto L5
        }
    }

    public static final class Api31 {
        public static void setLogSessionIdOnAudioTrack(AudioTrack r1, PlayerId r2) {
            LogSessionId r22 = r2.getLogSessionId();
            if (r22.equals(PlayerId$LogSessionIdApi31$$ExternalSyntheticApiModelOutline0.m()) == true) goto L6;
            r1.setLogSessionId(r22);
            return;
        }
    }

    public interface AudioOffloadSupportProvider {
        AudioOffloadSupport getAudioOffloadSupport(Format r1, AudioAttributes r2);
    }

    public interface AudioTrackBufferSizeProvider {
        public static final AudioTrackBufferSizeProvider DEFAULT = null;

        static {
            DEFAULT = new DefaultAudioTrackBufferSizeProvider.Builder().build();
        }

        int getBufferSizeInBytes(int r1, int r2, int r3, int r4, int r5, int r6, double r7);
    }

    public interface AudioTrackProvider {
        public static final AudioTrackProvider DEFAULT = null;

        static {
            DEFAULT = new DefaultAudioTrackProvider();
        }

        AudioTrack getAudioTrack(AudioSink.AudioTrackConfig r1, AudioAttributes r2, int r3, Context r4);

        default int getAudioTrackChannelConfig(int r1) {
            return Util.getAudioTrackChannelConfig(r1);
        }
    }

    public static final class Builder {
        private AudioCapabilities audioCapabilities;
        private ExoPlayer.AudioOffloadListener audioOffloadListener;
        private AudioOffloadSupportProvider audioOffloadSupportProvider;
        private AudioProcessorChain audioProcessorChain;
        private AudioTrackBufferSizeProvider audioTrackBufferSizeProvider;
        private AudioTrackProvider audioTrackProvider;
        private boolean buildCalled;
        private final Context context;
        private boolean enableAudioTrackPlaybackParams;
        private boolean enableFloatOutput;
        private boolean enableOnAudioPositionAdvancingFix;

        public Builder(Context r2) {
            this.enableOnAudioPositionAdvancingFix = true;
            this.context = r2;
            this.audioCapabilities = AudioCapabilities.DEFAULT_AUDIO_CAPABILITIES;
            this.audioTrackBufferSizeProvider = AudioTrackBufferSizeProvider.DEFAULT;
            this.audioTrackProvider = AudioTrackProvider.DEFAULT;
        }

        public static /* synthetic */ Context access$100(Builder r0) {
            return r0.context;
        }

        public static /* synthetic */ ExoPlayer.AudioOffloadListener access$1000(Builder r0) {
            return r0.audioOffloadListener;
        }

        public static /* synthetic */ AudioTrackProvider access$1100(Builder r0) {
            return r0.audioTrackProvider;
        }

        public static /* synthetic */ boolean access$1200(Builder r0) {
            return r0.enableOnAudioPositionAdvancingFix;
        }

        public static /* synthetic */ AudioCapabilities access$200(Builder r0) {
            return r0.audioCapabilities;
        }

        public static /* synthetic */ AudioProcessorChain access$300(Builder r0) {
            return r0.audioProcessorChain;
        }

        public static /* synthetic */ boolean access$400(Builder r0) {
            return r0.enableFloatOutput;
        }

        public static /* synthetic */ boolean access$500(Builder r0) {
            return r0.enableAudioTrackPlaybackParams;
        }

        public static /* synthetic */ AudioTrackBufferSizeProvider access$600(Builder r0) {
            return r0.audioTrackBufferSizeProvider;
        }

        public static /* synthetic */ AudioOffloadSupportProvider access$700(Builder r0) {
            return r0.audioOffloadSupportProvider;
        }

        public DefaultAudioSink build() {
            Assertions.checkState(!this.buildCalled);
            this.buildCalled = true;
            if (this.audioProcessorChain != null) goto L6;
            this.audioProcessorChain = new DefaultAudioProcessorChain(new AudioProcessor[0]);
        L6:
            if (this.audioOffloadSupportProvider != null) goto L9;
            this.audioOffloadSupportProvider = new DefaultAudioOffloadSupportProvider(this.context);
        L9:
            return new DefaultAudioSink(this, null);
        }

        public Builder setAudioProcessorChain(AudioProcessorChain r1) {
            Assertions.checkNotNull(r1);
            this.audioProcessorChain = r1;
            return this;
        }

        public Builder setAudioProcessors(AudioProcessor[] r2) {
            Assertions.checkNotNull(r2);
            return setAudioProcessorChain(new DefaultAudioProcessorChain(r2));
        }

        public Builder setEnableAudioTrackPlaybackParams(boolean r1) {
            this.enableAudioTrackPlaybackParams = r1;
            return this;
        }

        public Builder setEnableFloatOutput(boolean r1) {
            this.enableFloatOutput = r1;
            return this;
        }
    }

    public static final class Configuration {
        public final AudioProcessingPipeline audioProcessingPipeline;
        public final int bufferSize;
        public final boolean enableAudioTrackPlaybackParams;
        public final boolean enableOffloadGapless;
        public final Format inputFormat;
        public final int inputPcmFrameSize;
        public final int outputChannelConfig;
        public final int outputEncoding;
        public final int outputMode;
        public final int outputPcmFrameSize;
        public final int outputSampleRate;
        public final boolean tunneling;

        public Configuration(Format r1, int r2, int r3, int r4, int r5, int r6, int r7, int r8, AudioProcessingPipeline r9, boolean r10, boolean r11, boolean r12) {
            this.inputFormat = r1;
            this.inputPcmFrameSize = r2;
            this.outputMode = r3;
            this.outputPcmFrameSize = r4;
            this.outputSampleRate = r5;
            this.outputChannelConfig = r6;
            this.outputEncoding = r7;
            this.bufferSize = r8;
            this.audioProcessingPipeline = r9;
            this.enableAudioTrackPlaybackParams = r10;
            this.enableOffloadGapless = r11;
            this.tunneling = r12;
        }

        public AudioSink.AudioTrackConfig buildAudioTrackConfig() {
            int r1 = this.outputEncoding;
            int r2 = this.outputSampleRate;
            int r3 = this.outputChannelConfig;
            boolean r4 = this.tunneling;
            boolean r6 = true;
            if (this.outputMode != 1) goto L5;
        L4:
            boolean r5 = r6;
            return new AudioSink.AudioTrackConfig(r1, r2, r3, r4, r5, this.bufferSize);
        L5:
            r6 = false;
            goto L4
        }

        public boolean canReuseAudioTrack(Configuration r3) {
            if (r3.outputMode == this.outputMode) goto L5;
            return false;
        L5:
            if (r3.outputEncoding == this.outputEncoding) goto L7;
            return false;
        L7:
            if (r3.outputSampleRate == this.outputSampleRate) goto L9;
            return false;
        L9:
            if (r3.outputChannelConfig == this.outputChannelConfig) goto L11;
            return false;
        L11:
            if (r3.outputPcmFrameSize == this.outputPcmFrameSize) goto L13;
            return false;
        L13:
            if (r3.enableAudioTrackPlaybackParams == this.enableAudioTrackPlaybackParams) goto L15;
            return false;
        L15:
            if (r3.enableOffloadGapless != this.enableOffloadGapless) goto L25;
            return true;
        L25:
            return false;
        }

        public Configuration copyWithBufferSize(int r14) {
            return new Configuration(this.inputFormat, this.inputPcmFrameSize, this.outputMode, this.outputPcmFrameSize, this.outputSampleRate, this.outputChannelConfig, this.outputEncoding, r14, this.audioProcessingPipeline, this.enableAudioTrackPlaybackParams, this.enableOffloadGapless, this.tunneling);
        }

        public long framesToDurationUs(long r1) {
            return Util.sampleCountToDurationUs(r1, this.outputSampleRate);
        }

        public long inputFramesToDurationUs(long r1) {
            return Util.sampleCountToDurationUs(r1, this.inputFormat.sampleRate);
        }

        public boolean outputModeIsOffload() {
            if (this.outputMode != 1) goto L5;
            return true;
        L5:
            return false;
        }
    }

    public static class DefaultAudioProcessorChain implements AudioProcessorChain {
        private final AudioProcessor[] audioProcessors;
        private final SilenceSkippingAudioProcessor silenceSkippingAudioProcessor;
        private final SonicAudioProcessor sonicAudioProcessor;

        public DefaultAudioProcessorChain(AudioProcessor... r3) {
            this(r3, new SilenceSkippingAudioProcessor(), new SonicAudioProcessor());
        }

        public DefaultAudioProcessorChain(AudioProcessor[] r4, SilenceSkippingAudioProcessor r5, SonicAudioProcessor r6) {
            AudioProcessor[] r0 = new AudioProcessor[r4.length + 2];
            this.audioProcessors = r0;
            System.arraycopy(r4, 0, r0, 0, r4.length);
            this.silenceSkippingAudioProcessor = r5;
            this.sonicAudioProcessor = r6;
            r0[r4.length] = r5;
            r0[r4.length + 1] = r6;
        }

        @Override // androidx.media3.common.audio.AudioProcessorChain
        public PlaybackParameters applyPlaybackParameters(PlaybackParameters r3) {
            this.sonicAudioProcessor.setSpeed(r3.speed);
            this.sonicAudioProcessor.setPitch(r3.pitch);
            return r3;
        }

        @Override // androidx.media3.common.audio.AudioProcessorChain
        public boolean applySkipSilenceEnabled(boolean r1) {
            this.silenceSkippingAudioProcessor.setEnabled(r1);
            return r1;
        }

        @Override // androidx.media3.common.audio.AudioProcessorChain
        public AudioProcessor[] getAudioProcessors() {
            return this.audioProcessors;
        }

        @Override // androidx.media3.common.audio.AudioProcessorChain
        public long getMediaDuration(long r2) {
            if (this.sonicAudioProcessor.isActive() == true) goto L5;
            return r2;
        L5:
            return this.sonicAudioProcessor.getMediaDuration(r2);
        }

        @Override // androidx.media3.common.audio.AudioProcessorChain
        public long getSkippedOutputFrameCount() {
            return this.silenceSkippingAudioProcessor.getSkippedFrames();
        }
    }

    public static final class InvalidAudioTrackTimestampException extends RuntimeException {
        private InvalidAudioTrackTimestampException(String r1) {
            super(r1);
        }

        public /* synthetic */ InvalidAudioTrackTimestampException(String r1, AnonymousClass1 r2) {
            this(r1);
        }
    }

    public static final class MediaPositionParameters {
        public final long audioTrackPositionUs;
        public long mediaPositionDriftUs;
        public final long mediaTimeUs;
        public final PlaybackParameters playbackParameters;

        private MediaPositionParameters(PlaybackParameters r1, long r2, long r4) {
            this.playbackParameters = r1;
            this.mediaTimeUs = r2;
            this.audioTrackPositionUs = r4;
        }

        public /* synthetic */ MediaPositionParameters(PlaybackParameters r1, long r2, long r4, AnonymousClass1 r6) {
            this(r1, r2, r4);
        }
    }

    public static final class OnRoutingChangedListenerApi24 {
        private final AudioTrack audioTrack;
        private final AudioCapabilitiesReceiver capabilitiesReceiver;
        private AudioRouting.OnRoutingChangedListener listener;

        /* renamed from: $r8$lambda$iF2hPfQ_z_eejaC3PvZcN-i9wBM, reason: not valid java name */
        public static /* synthetic */ void m826$r8$lambda$iF2hPfQ_z_eejaC3PvZcNi9wBM(OnRoutingChangedListenerApi24 r0, AudioRouting r1) {
            r0.onRoutingChanged(r1);
        }

        public OnRoutingChangedListenerApi24(AudioTrack r2, AudioCapabilitiesReceiver r3) {
            this.audioTrack = r2;
            this.capabilitiesReceiver = r3;
            this.listener = new DefaultAudioSink$OnRoutingChangedListenerApi24$$ExternalSyntheticLambda0(this);
            Handler r32 = new Handler(Looper.myLooper());
            r2.addOnRoutingChangedListener(this.listener, r32);
        }

        private void onRoutingChanged(AudioRouting r2) {
            if (this.listener == null) goto L10;
            AudioDeviceInfo r22 = r2.getRoutedDevice();
            if (r22 == null) goto L9;
            this.capabilitiesReceiver.setRoutedDevice(r22);
            return;
        L9:
            return;
        }

        public void release() {
            this.audioTrack.removeOnRoutingChangedListener((AudioRouting.OnRoutingChangedListener) Assertions.checkNotNull(this.listener));
            this.listener = null;
        }
    }

    public static final class PendingExceptionHolder<T extends Exception> {
        private long earliestNextRetryTimeMs;
        private T pendingException;
        private long throwDeadlineMs;

        public PendingExceptionHolder() {
            this.throwDeadlineMs = -9223372036854775807L;
            this.earliestNextRetryTimeMs = -9223372036854775807L;
        }

        public void clear() {
            this.pendingException = null;
            this.throwDeadlineMs = -9223372036854775807L;
            this.earliestNextRetryTimeMs = -9223372036854775807L;
        }

        public boolean shouldWaitBeforeRetry() {
            if (this.pendingException != null) goto L6;
            return false;
        L6:
            if (DefaultAudioSink.access$2100() == false) goto L9;
            return true;
        L9:
            if (SystemClock.elapsedRealtime() >= this.earliestNextRetryTimeMs) goto L11;
            return true;
        L11:
            return false;
        }

        public void throwExceptionIfDeadlineIsReached(T r7) {
            long r0 = SystemClock.elapsedRealtime();
            if (this.pendingException != null) goto L6;
            this.pendingException = r7;
        L6:
            if (this.throwDeadlineMs == (-9223372036854775807L)) goto L8;
        L10:
            long r2 = this.throwDeadlineMs;
            if (r2 != (-9223372036854775807L)) goto L13;
        L19:
            this.earliestNextRetryTimeMs = r0 + 50;
            return;
        L13:
            if (r0 < r2) goto L19;
            T r02 = this.pendingException;
            if (r02 == r7) goto L17;
            r02.addSuppressed(r7);
        L17:
            T r72 = this.pendingException;
            clear();
            throw r72;
        L8:
            if (DefaultAudioSink.access$2100() == true) goto L10;
            this.throwDeadlineMs = 200 + r0;
            goto L10
        }
    }

    public final class PositionTrackerListener implements AudioTrackPositionTracker.Listener {
        final /* synthetic */ DefaultAudioSink this$0;

        private PositionTrackerListener(DefaultAudioSink r1) {
            this.this$0 = r1;
        }

        public /* synthetic */ PositionTrackerListener(DefaultAudioSink r1, AnonymousClass1 r2) {
            this(r1);
        }

        @Override // androidx.media3.exoplayer.audio.AudioTrackPositionTracker.Listener
        public void onInvalidLatency(long r2) {
            Log.w("DefaultAudioSink", "Ignoring impossibly large audio latency: " + r2);
        }

        @Override // androidx.media3.exoplayer.audio.AudioTrackPositionTracker.Listener
        public void onPositionAdvancing(long r2) {
            if (DefaultAudioSink.access$1400(this.this$0) == null) goto L6;
            DefaultAudioSink.access$1400(this.this$0).onPositionAdvancing(r2);
            return;
        }

        @Override // androidx.media3.exoplayer.audio.AudioTrackPositionTracker.Listener
        public void onPositionFramesMismatch(long r3, long r5, long r7, long r9) {
            String r2 = "Spurious audio timestamp (frame position mismatch): " + r3 + ", " + r5 + ", " + r7 + ", " + r9 + ", " + DefaultAudioSink.access$1700(this.this$0) + ", " + DefaultAudioSink.access$1800(this.this$0);
            if (DefaultAudioSink.failOnSpuriousAudioTimestamp == true) goto L7;
            Log.w("DefaultAudioSink", r2);
            return;
        L7:
            throw new InvalidAudioTrackTimestampException(r2, null);
        }

        @Override // androidx.media3.exoplayer.audio.AudioTrackPositionTracker.Listener
        public void onSystemTimeUsMismatch(long r3, long r5, long r7, long r9) {
            String r2 = "Spurious audio timestamp (system clock mismatch): " + r3 + ", " + r5 + ", " + r7 + ", " + r9 + ", " + DefaultAudioSink.access$1700(this.this$0) + ", " + DefaultAudioSink.access$1800(this.this$0);
            if (DefaultAudioSink.failOnSpuriousAudioTimestamp == true) goto L7;
            Log.w("DefaultAudioSink", r2);
            return;
        L7:
            throw new InvalidAudioTrackTimestampException(r2, null);
        }

        @Override // androidx.media3.exoplayer.audio.AudioTrackPositionTracker.Listener
        public void onUnderrun(int r11, long r12) {
            if (DefaultAudioSink.access$1400(this.this$0) == null) goto L6;
            long r8 = SystemClock.elapsedRealtime() - DefaultAudioSink.access$2000(this.this$0);
            DefaultAudioSink.access$1400(this.this$0).onUnderrun(r11, r12, r8);
            return;
        }
    }

    public final class StreamEventCallbackV29 {
        private final AudioTrack$StreamEventCallback callback;
        private final Handler handler;
        final /* synthetic */ DefaultAudioSink this$0;

        /* renamed from: androidx.media3.exoplayer.audio.DefaultAudioSink$StreamEventCallbackV29$1, reason: invalid class name */
        public class AnonymousClass1 extends AudioTrack$StreamEventCallback {
            final /* synthetic */ StreamEventCallbackV29 this$1;
            final /* synthetic */ DefaultAudioSink val$this$0;

            public AnonymousClass1(StreamEventCallbackV29 r1, DefaultAudioSink r2) {
                this.this$1 = r1;
                this.val$this$0 = r2;
            }

            public void onDataRequest(AudioTrack r1, int r2) {
                if (r1.equals(DefaultAudioSink.access$1300(this.this$1.this$0)) == true) goto L6;
                return;
            L6:
                if (DefaultAudioSink.access$1400(this.this$1.this$0) != null) goto L8;
                return;
            L8:
                if (DefaultAudioSink.access$1500(this.this$1.this$0) == false) goto L12;
                DefaultAudioSink.access$1400(this.this$1.this$0).onOffloadBufferEmptying();
                return;
            }

            public void onPresentationEnded(AudioTrack r2) {
                if (r2.equals(DefaultAudioSink.access$1300(this.this$1.this$0)) == true) goto L5;
                return;
            L5:
                DefaultAudioSink.access$1602(this.this$1.this$0, true);
            }

            public void onTearDown(AudioTrack r2) {
                if (r2.equals(DefaultAudioSink.access$1300(this.this$1.this$0)) == true) goto L6;
                return;
            L6:
                if (DefaultAudioSink.access$1400(this.this$1.this$0) != null) goto L8;
                return;
            L8:
                if (DefaultAudioSink.access$1500(this.this$1.this$0) == false) goto L12;
                DefaultAudioSink.access$1400(this.this$1.this$0).onOffloadBufferEmptying();
                return;
            }
        }

        public StreamEventCallbackV29(DefaultAudioSink r3) {
            this.this$0 = r3;
            this.handler = new Handler(Looper.myLooper());
            this.callback = new AnonymousClass1(this, r3);
        }

        public void register(AudioTrack r3) {
            Handler r0 = this.handler;
            Objects.requireNonNull(r0);
            r3.registerStreamEventCallback(new DefaultAudioSink$StreamEventCallbackV29$$ExternalSyntheticLambda0(r0), this.callback);
        }

        public void unregister(AudioTrack r2) {
            r2.unregisterStreamEventCallback(this.callback);
            this.handler.removeCallbacksAndMessages(null);
        }
    }

    public static /* synthetic */ void $r8$lambda$HSqvmOmbsjCp8OxUgX7XYweSoS0(AudioSink.Listener r0, AudioSink.AudioTrackConfig r1) {
        r0.onAudioTrackReleased(r1);
    }

    public static /* synthetic */ void $r8$lambda$eQpBSvvM7qMitmX5MdwoTJRm8SQ(DefaultAudioSink r0) {
        r0.maybeReportSkippedSilence();
    }

    /* renamed from: $r8$lambda$idAuhXPZKkc9ZJ9aYo_-jnodQOk, reason: not valid java name */
    public static /* synthetic */ void m825$r8$lambda$idAuhXPZKkc9ZJ9aYo_jnodQOk(AudioTrack r2, AudioSink.Listener r3, Handler r4, AudioSink.AudioTrackConfig r5) {
        r2.flush();     // Catch: Throwable -> L19
        r2.release();     // Catch: Throwable -> L19
        if (r3 != null) goto L6;
    L8:
        Object r22 = releaseExecutorLock;
        monitor-enter(r22);
        int r32 = pendingReleaseCount - 1;
        pendingReleaseCount = r32;     // Catch: Throwable -> L13
        if (r32 != 0) goto L15;
        releaseExecutor.shutdown();     // Catch: Throwable -> L13
        releaseExecutor = null;     // Catch: Throwable -> L13
    L15:
        monitor-exit(r22);     // Catch: Throwable -> L13
        return;
    L13:
        th = move-exception;
        throw th;
    L6:
        if (r4.getLooper().getThread().isAlive() == false) goto L8;
        r4.post(new DefaultAudioSink$$ExternalSyntheticLambda3(r3, r5));
    L19:
        th = move-exception;
        if (r3 == null) goto L25;
        if (r4.getLooper().getThread().isAlive() == false) goto L25;
        r4.post(new DefaultAudioSink$$ExternalSyntheticLambda3(r3, r5));
    L25:
        monitor-enter(releaseExecutorLock);
        int r42 = pendingReleaseCount - 1;
        pendingReleaseCount = r42;     // Catch: Throwable -> L29
        if (r42 != 0) goto L31;
        releaseExecutor.shutdown();     // Catch: Throwable -> L29
        releaseExecutor = null;     // Catch: Throwable -> L29
    L31:
        throw th;
    L29:
        th = move-exception;
        throw th;
    }

    static {
        releaseExecutorLock = new Object();
    }

    @RequiresNonNull({"#1.audioProcessorChain"})
    private DefaultAudioSink(Builder r11) {
        if (Builder.access$100(r11) != null) goto L5;
        Context r0 = null;
    L6:
        this.context = r0;
        this.audioAttributes = AudioAttributes.DEFAULT;
        if (r0 == null) goto L9;
        AudioCapabilities r02 = null;
    L10:
        this.audioCapabilities = r02;
        this.audioProcessorChain = Builder.access$300(r11);
        this.enableFloatOutput = Builder.access$400(r11);
        this.preferAudioTrackPlaybackParams = Builder.access$500(r11);
        this.offloadMode = 0;
        this.audioTrackBufferSizeProvider = Builder.access$600(r11);
        this.audioOffloadSupportProvider = (AudioOffloadSupportProvider) Assertions.checkNotNull(Builder.access$700(r11));
        this.audioTrackPositionTracker = new AudioTrackPositionTracker(new PositionTrackerListener(this, null));
        ChannelMappingAudioProcessor r1 = new ChannelMappingAudioProcessor();
        this.channelMappingAudioProcessor = r1;
        TrimmingAudioProcessor r2 = new TrimmingAudioProcessor();
        this.trimmingAudioProcessor = r2;
        this.toInt16PcmAudioProcessor = new ToInt16PcmAudioProcessor();
        this.toFloatPcmAudioProcessor = new ToFloatPcmAudioProcessor();
        this.availableAudioProcessors = ImmutableList.of(r2, r1);
        this.volume = 1.0f;
        this.audioSessionId = 0;
        this.auxEffectInfo = new AuxEffectInfo(0, 0.0f);
        PlaybackParameters r4 = PlaybackParameters.DEFAULT;
        this.mediaPositionParameters = new MediaPositionParameters(r4, 0, 0, null);
        this.playbackParameters = r4;
        this.skipSilenceEnabled = false;
        this.mediaPositionParametersCheckpoints = new ArrayDeque();
        this.initializationExceptionPendingExceptionHolder = new PendingExceptionHolder();
        this.writeExceptionPendingExceptionHolder = new PendingExceptionHolder();
        this.audioOffloadListener = Builder.access$1000(r11);
        this.audioTrackProvider = Builder.access$1100(r11);
        if (Build.VERSION.SDK_INT >= 34) goto L13;
    L16:
        int r03 = -1;
    L17:
        this.contextDeviceId = r03;
        this.enableOnAudioPositionAdvancingFix = Builder.access$1200(r11);
        return;
    L13:
        if (Builder.access$100(r11) == null) goto L16;
        r03 = getDeviceIdFromContext(Builder.access$100(r11));
        goto L17
    L9:
        r02 = Builder.access$200(r11);
        goto L10
    L5:
        r0 = Builder.access$100(r11).getApplicationContext();
        goto L6
    }

    public /* synthetic */ DefaultAudioSink(Builder r1, AnonymousClass1 r2) {
        this(r1);
    }

    public static /* synthetic */ AudioTrack access$1300(DefaultAudioSink r0) {
        return r0.audioTrack;
    }

    public static /* synthetic */ AudioSink.Listener access$1400(DefaultAudioSink r0) {
        return r0.listener;
    }

    public static /* synthetic */ boolean access$1500(DefaultAudioSink r0) {
        return r0.playing;
    }

    public static /* synthetic */ boolean access$1602(DefaultAudioSink r0, boolean r1) {
        r0.handledOffloadOnPresentationEnded = r1;
        return r1;
    }

    public static /* synthetic */ long access$1700(DefaultAudioSink r2) {
        return r2.getSubmittedFrames();
    }

    public static /* synthetic */ long access$1800(DefaultAudioSink r2) {
        return r2.getWrittenFrames();
    }

    public static /* synthetic */ long access$2000(DefaultAudioSink r2) {
        return r2.lastFeedElapsedRealtimeMs;
    }

    public static /* synthetic */ boolean access$2100() {
        return hasPendingAudioTrackReleases();
    }

    public static /* synthetic */ int access$2200(int r0) {
        return getNonPcmMaximumEncodedRateBytesPerSecond(r0);
    }

    private void applyAudioProcessorPlaybackParametersAndSkipSilence(long r9) {
        if (useAudioTrackPlaybackParams() == false) goto L5;
        PlaybackParameters r0 = PlaybackParameters.DEFAULT;
    L9:
        PlaybackParameters r2 = r0;
        if (shouldApplyAudioProcessorPlaybackParameters() == false) goto L14;
        boolean r02 = this.audioProcessorChain.applySkipSilenceEnabled(this.skipSilenceEnabled);
    L15:
        this.skipSilenceEnabled = r02;
        this.mediaPositionParametersCheckpoints.add(new MediaPositionParameters(r2, Math.max(0, r9), this.configuration.framesToDurationUs(getWrittenFrames()), null));
        setupAudioProcessors();
        AudioSink.Listener r92 = this.listener;
        if (r92 == null) goto L19;
        r92.onSkipSilenceEnabledChanged(this.skipSilenceEnabled);
        return;
    L19:
        return;
    L14:
        r02 = false;
        goto L15
    L5:
        if (shouldApplyAudioProcessorPlaybackParameters() == false) goto L7;
        r0 = this.audioProcessorChain.applyPlaybackParameters(this.playbackParameters);
    L8:
        this.playbackParameters = r0;
        goto L9
    L7:
        r0 = PlaybackParameters.DEFAULT;
        goto L8
    }

    private long applyMediaPositionParameters(long r5) {
    L3:
        if (this.mediaPositionParametersCheckpoints.isEmpty() == true) goto L7;
        if (r5 < this.mediaPositionParametersCheckpoints.getFirst().audioTrackPositionUs) goto L7;
        this.mediaPositionParameters = this.mediaPositionParametersCheckpoints.remove();
    L7:
        MediaPositionParameters r0 = this.mediaPositionParameters;
        long r52 = r5 - r0.audioTrackPositionUs;
        long r02 = Util.getMediaDurationForPlayoutDuration(r52, r0.playbackParameters.speed);
        if (this.mediaPositionParametersCheckpoints.isEmpty() == false) goto L11;
        long r53 = this.audioProcessorChain.getMediaDuration(r52);
        MediaPositionParameters r4 = this.mediaPositionParameters;
        long r2 = r4.mediaTimeUs + r53;
        r4.mediaPositionDriftUs = r53 - r02;
        return r2;
    L11:
        MediaPositionParameters r42 = this.mediaPositionParameters;
        return (r42.mediaTimeUs + r02) + r42.mediaPositionDriftUs;
    }

    private long applySkipping(long r6) {
        long r0 = this.audioProcessorChain.getSkippedOutputFrameCount();
        long r62 = r6 + this.configuration.framesToDurationUs(r0);
        long r2 = this.skippedOutputFrameCountAtLastPosition;
        if (r0 <= r2) goto L5;
        long r22 = this.configuration.framesToDurationUs(r0 - r2);
        this.skippedOutputFrameCountAtLastPosition = r0;
        handleSkippedSilence(r22);
    L5:
        return r62;
    }

    private AudioTrack buildAudioTrack(AudioSink.AudioTrackConfig r11, AudioAttributes r12, int r13, Format r14, Context r15) {
        AudioTrack r10 = this.audioTrackProvider.getAudioTrack(r11, r12, r13, r15);     // Catch: Throwable -> L9 UnsupportedOperationException -> L11
        int r1 = r10.getState();
        if (r1 != 1) goto L16;
        return r10;
    L16:
        r10.release();     // Catch: Exception -> L15
    L8:
        throw new AudioSink.InitializationException(r1, r11.sampleRate, r11.channelConfig, r11.encoding, r11.bufferSize, r14, r11.offload, null);
    L9:
        e = move-exception;
        throw new AudioSink.InitializationException(0, r11.sampleRate, r11.channelConfig, r11.encoding, r11.bufferSize, r14, r11.offload, e);
    }

    private AudioTrack buildAudioTrack(Configuration r9) {
        int r0 = this.audioSessionId;     // Catch: AudioSink.InitializationException -> L26
        int r1 = this.contextDeviceId;     // Catch: AudioSink.InitializationException -> L26
        if (r1 != (-1)) goto L36;
    L16:
        int r5 = r0;
        Context r7 = null;
    L17:
        DefaultAudioSink r2 = this;
        AudioTrack r8 = r2.buildAudioTrack(r9.buildAudioTrackConfig(), this.audioAttributes, r5, r9.inputFormat, r7);     // Catch: AudioSink.InitializationException -> L23
        ExoPlayer.AudioOffloadListener r92 = r2.audioOffloadListener;     // Catch: AudioSink.InitializationException -> L23
        if (r92 == null) goto L25;
        r92.onOffloadedPlayback(isOffloadedPlayback(r8));     // Catch: AudioSink.InitializationException -> L23
        return r8;
    L25:
        return r8;
    L23:
        e = e;
    L24:
        AudioSink.InitializationException r93 = e;
    L28:
        AudioSink.Listener r82 = r2.listener;
        if (r82 == null) goto L38;
        r82.onAudioSinkError(r93);
        throw r93;
    L38:
        throw r93;
    L36:
        Context r22 = this.context;     // Catch: AudioSink.InitializationException -> L12
        if (r22 == null) goto L16;
        if (Build.VERSION.SDK_INT < 34) goto L16;
        if (this.contextWithDeviceId != null) goto L14;
        this.contextWithDeviceId = r22.createDeviceContext(r1);     // Catch: AudioSink.InitializationException -> L12
    L14:
        r7 = this.contextWithDeviceId;     // Catch: AudioSink.InitializationException -> L12
        r5 = 0;
    L12:
        e = move-exception;
        r93 = e;
        r2 = this;
    L26:
        e = e;
        r2 = this;
        goto L24
    }

    private AudioTrack buildAudioTrackWithRetry() {
        return buildAudioTrack((Configuration) Assertions.checkNotNull(this.configuration));
    L4:
        e = move-exception;
        Configuration r1 = this.configuration;
        if (r1.bufferSize <= 1000000) goto L12;
        Configuration r12 = r1.copyWithBufferSize(DurationKt.NANOS_IN_MILLIS);
        AudioTrack r2 = this.buildAudioTrack(r12);     // Catch: AudioSink.InitializationException -> L10
        this.configuration = r12;     // Catch: AudioSink.InitializationException -> L10
        return r2;
    L10:
        e = move-exception;
        e.addSuppressed(e);
    L12:
        this.maybeDisableOffload();
        throw e;
    }

    private void drainOutputBuffer(long r10) {
        if (this.outputBuffer != null) goto L6;
        return;
    L6:
        if (this.writeExceptionPendingExceptionHolder.shouldWaitBeforeRetry() == true) goto L69;
        int r4 = this.outputBuffer.remaining();
        boolean r7 = false;
        if (this.tunneling == true) goto L11;
        DefaultAudioSink r1 = this;
        int r9 = writeNonBlocking(r1.audioTrack, r1.outputBuffer, r4);
    L21:
        r1.lastFeedElapsedRealtimeMs = SystemClock.elapsedRealtime();
        if (r9 < 0) goto L24;
        r1.writeExceptionPendingExceptionHolder.clear();
        if (isOffloadedPlayback(r1.audioTrack) == true) goto L46;
    L56:
        int r102 = r1.configuration.outputMode;
        if (r102 != 0) goto L59;
        r1.writtenPcmBytes += r9;
    L59:
        if (r9 != r4) goto L67;
        if (r102 != 0) goto L62;
    L65:
        r1.outputBuffer = null;
        return;
    L62:
        if (r1.outputBuffer != r1.inputBuffer) goto L64;
        r7 = true;
    L64:
        Assertions.checkState(r7);
        r1.writtenEncodedFrames += r1.framesPerEncodedSample * r1.inputBufferAccessUnitCount;
        goto L65
    L67:
        return;
    L46:
        if (r1.writtenEncodedFrames <= 0) goto L49;
        r1.isWaitingForOffloadEndOfStreamHandled = false;
    L49:
        if (r1.playing == false) goto L56;
        AudioSink.Listener r103 = r1.listener;
        if (r103 == null) goto L56;
        if (r9 >= r4) goto L56;
        if (r1.isWaitingForOffloadEndOfStreamHandled == true) goto L56;
        r103.onOffloadBufferFull();
        goto L56
    L24:
        if (isAudioTrackDeadObject(r9) == true) goto L26;
    L31:
        AudioSink.WriteException r104 = new AudioSink.WriteException(r9, r1.configuration.inputFormat, r7);
        AudioSink.Listener r92 = r1.listener;
        if (r92 == null) goto L35;
        r92.onAudioSinkError(r104);
    L35:
        if (r104.isRecoverable == true) goto L37;
    L41:
        r1.writeExceptionPendingExceptionHolder.throwExceptionIfDeadlineIsReached(r104);
        return;
    L37:
        if (r1.context == null) goto L41;
        AudioCapabilities r93 = AudioCapabilities.DEFAULT_AUDIO_CAPABILITIES;
        r1.audioCapabilities = r93;
        r1.audioCapabilitiesReceiver.overrideCapabilities(r93);
        throw r104;
    L26:
        if (r1.getWrittenFrames() <= 0) goto L29;
    L27:
        r7 = true;
        goto L31
    L29:
        if (isOffloadedPlayback(r1.audioTrack) == false) goto L31;
        r1.maybeDisableOffload();
        goto L27
    L11:
        if (r10 == (-9223372036854775807L)) goto L13;
        boolean r0 = true;
    L14:
        Assertions.checkState(r0);
        if (r10 != Long.MIN_VALUE) goto L18;
        r10 = this.lastTunnelingAvSyncPresentationTimeUs;
    L19:
        r1 = this;
        r9 = r1.writeNonBlockingWithAvSync(this.audioTrack, this.outputBuffer, r4, r10);
        goto L21
    L18:
        this.lastTunnelingAvSyncPresentationTimeUs = r10;
        goto L19
    L13:
        r0 = false;
        goto L14
    }

    private boolean drainToEndOfStream() {
        if (this.audioProcessingPipeline.isOperational() == true) goto L8;
        drainOutputBuffer(Long.MIN_VALUE);
        if (this.outputBuffer != null) goto L7;
        return true;
    L7:
        return false;
    L8:
        this.audioProcessingPipeline.queueEndOfStream();
        processBuffers(Long.MIN_VALUE);
        if (this.audioProcessingPipeline.isEnded() == false) goto L15;
        ByteBuffer r5 = this.outputBuffer;
        if (r5 != null) goto L13;
    L14:
        return true;
    L13:
        if (r5.hasRemaining() == false) goto L14;
    L15:
        return false;
    }

    private static int getAudioTrackMinBufferSize(int r0, int r1, int r2) {
        int r02 = AudioTrack.getMinBufferSize(r0, r1, r2);
        if (r02 == (-2)) goto L5;
        boolean r12 = true;
    L6:
        Assertions.checkState(r12);
        return r02;
    L5:
        r12 = false;
        goto L6
    }

    private static int getDeviceIdFromContext(Context r1) {
        int r12 = r1.getDeviceId();
        if (r12 == 0) goto L6;
        if (r12 == (-1)) goto L6;
        return r12;
    L6:
        return -1;
    }

    private static int getFramesPerEncodedSample(int r3, ByteBuffer r4) {
        if (r3 == 20) goto L34;
        if (r3 == 30) goto L32;
        switch(r3) {
            case 5: goto L30;
            case 6: goto L30;
            case 7: goto L32;
            case 8: goto L32;
            case 9: goto L24;
            case 10: goto L23;
            case 11: goto L21;
            case 12: goto L21;
            default: goto L8;
        };
    L8:
        switch(r3) {
            case 14: goto L16;
            case 15: goto L14;
            case 16: goto L13;
            case 17: goto L12;
            case 18: goto L30;
            default: goto L9;
        };
    L9:
        JsonArray$$ExternalSyntheticBUOutline0.m("Unexpected audio encoding: ", r3);
        return 0;
    L13:
        return 1024;
    L14:
        return 512;
    L16:
        int r32 = Ac3Util.findTrueHdSyncframeOffset(r4);
        if (r32 != (-1)) goto L20;
        return 0;
    L20:
        return Ac3Util.parseTrueHdSyncframeAudioSampleCount(r4, r32) * 16;
    L12:
        return Ac4Util.parseAc4SyncframeAudioSampleCount(r4);
    L21:
        return 2048;
    L23:
        return 1024;
    L24:
        int r33 = MpegAudioUtil.parseMpegAudioFrameSampleCount(Util.getBigEndianInt(r4, r4.position()));
        if (r33 == (-1)) goto L27;
        return r33;
    L27:
        Segment$$ExternalSyntheticBUOutline0.m();
        return 0;
    L30:
        return Ac3Util.parseAc3SyncframeAudioSampleCount(r4);
    L32:
        return DtsUtil.parseDtsAudioSampleCount(r4);
    L34:
        return OpusUtil.parseOggPacketAudioSampleCount(r4);
    }

    private static String getLooperThreadName(Looper r0) {
        if (r0 != null) goto L6;
        return "null";
    L6:
        return r0.getThread().getName();
    }

    private static int getNonPcmMaximumEncodedRateBytesPerSecond(int r1) {
        int r12 = ExtractorUtil.getMaximumEncodedRateBytesPerSecond(r1);
        if (r12 == (-2147483647)) goto L5;
        boolean r0 = true;
    L6:
        Assertions.checkState(r0);
        return r12;
    L5:
        r0 = false;
        goto L6
    }

    private long getSubmittedFrames() {
        if (this.configuration.outputMode != 0) goto L7;
        return this.submittedPcmBytes / r0.inputPcmFrameSize;
    L7:
        return this.submittedEncodedFrames;
    }

    private long getWrittenFrames() {
        if (this.configuration.outputMode != 0) goto L7;
        return Util.ceilDivide(this.writtenPcmBytes, r0.outputPcmFrameSize);
    L7:
        return this.writtenEncodedFrames;
    }

    private void handleSkippedSilence(long r3) {
        this.accumulatedSkippedSilenceDurationUs += r3;
        if (this.reportSkippedSilenceHandler != null) goto L5;
        this.reportSkippedSilenceHandler = new Handler(Looper.myLooper());
    L5:
        this.reportSkippedSilenceHandler.removeCallbacksAndMessages(null);
        this.reportSkippedSilenceHandler.postDelayed(new DefaultAudioSink$$ExternalSyntheticLambda2(this), 100);
    }

    private static boolean hasPendingAudioTrackReleases() {
        Object r0 = releaseExecutorLock;
        monitor-enter(r0);
    L10:
        th = move-exception;
        throw th;
    L5:
        if (pendingReleaseCount <= 0) goto L7;
        boolean r1 = true;
    L8:
        monitor-exit(r0);     // Catch: Throwable -> L10
        return r1;
    L7:
        r1 = false;
        goto L8
    }

    private boolean initializeAudioTrack() {
        boolean r1 = false;
        if (this.initializationExceptionPendingExceptionHolder.shouldWaitBeforeRetry() == false) goto L5;
        return false;
    L5:
        AudioTrack r0 = buildAudioTrackWithRetry();
        this.audioTrack = r0;
        if (isOffloadedPlayback(r0) == false) goto L11;
        registerStreamEventCallbackV29(this.audioTrack);
        Configuration r02 = this.configuration;
        if (r02.enableOffloadGapless == false) goto L11;
        AudioTrack r2 = this.audioTrack;
        Format r03 = r02.inputFormat;
        r2.setOffloadDelayPadding(r03.encoderDelay, r03.encoderPadding);
    L11:
        if (Build.VERSION.SDK_INT < 31) goto L15;
        PlayerId r04 = this.playerId;
        if (r04 == null) goto L15;
        Api31.setLogSessionIdOnAudioTrack(this.audioTrack, r04);
    L15:
        AudioTrackPositionTracker r3 = this.audioTrackPositionTracker;
        AudioTrack r4 = this.audioTrack;
        Configuration r05 = this.configuration;
        if (r05.outputMode != 2) goto L18;
        boolean r5 = true;
    L19:
        r3.setAudioTrack(r4, r5, r05.outputEncoding, r05.outputPcmFrameSize, r05.bufferSize, this.enableOnAudioPositionAdvancingFix);
        setVolumeInternal();
        int r06 = this.auxEffectInfo.effectId;
        if (r06 == 0) goto L22;
        this.audioTrack.attachAuxEffect(r06);
        this.audioTrack.setAuxEffectSendLevel(this.auxEffectInfo.sendLevel);
    L22:
        AudioDeviceInfoApi23 r07 = this.preferredDevice;
        if (r07 == null) goto L27;
        Api23.setPreferredDeviceOnAudioTrack(this.audioTrack, r07);
        AudioCapabilitiesReceiver r08 = this.audioCapabilitiesReceiver;
        if (r08 == null) goto L27;
        r08.setRoutedDevice(this.preferredDevice.audioDeviceInfo);
    L27:
        AudioCapabilitiesReceiver r09 = this.audioCapabilitiesReceiver;
        if (r09 == null) goto L30;
        this.onRoutingChangedListener = new OnRoutingChangedListenerApi24(this.audioTrack, r09);
    L30:
        this.startMediaTimeUsNeedsInit = true;
        int r010 = this.audioTrack.getAudioSessionId();
        if (r010 == this.audioSessionId) goto L33;
        r1 = true;
    L33:
        this.audioSessionId = r010;
        AudioSink.Listener r011 = this.listener;
        if (r011 == null) goto L38;
        r011.onAudioTrackInitialized(this.configuration.buildAudioTrackConfig());
        if (r1 == false) goto L38;
        this.pendingAudioSessionIdChangeConfirmation = true;
        this.listener.onAudioSessionIdChanged(this.audioSessionId);
    L38:
        return true;
    L18:
        r5 = false;
        goto L19
    }

    private static boolean isAudioTrackDeadObject(int r1) {
        if (r1 != (-6)) goto L5;
        return true;
    L5:
        if (r1 == (-32)) goto L11;
        return false;
    L11:
        return true;
    }

    private boolean isAudioTrackInitialized() {
        if (this.audioTrack == null) goto L6;
        return true;
    L6:
        return false;
    }

    private static boolean isOffloadedPlayback(AudioTrack r2) {
        if (Build.VERSION.SDK_INT >= 29) goto L5;
        return false;
    L5:
        if (r2.isOffloadedPlayback() == false) goto L10;
        return true;
    L10:
        return false;
    }

    private void maybeDisableOffload() {
        if (this.configuration.outputModeIsOffload() == true) goto L5;
        return;
    L5:
        this.offloadDisabledUntilNextConfiguration = true;
    }

    private ByteBuffer maybeRampUpVolume(ByteBuffer r6) {
        if (this.configuration.outputMode != 0) goto L7;
        int r0 = (int) Util.durationUsToSampleCount(Util.msToUs(20), this.configuration.outputSampleRate);
        long r1 = getWrittenFrames();
        if (r1 >= r0) goto L7;
        Configuration r5 = this.configuration;
        return PcmAudioUtil.rampUpVolume(r6, r5.outputEncoding, r5.outputPcmFrameSize, (int) r1, r0);
    L7:
        return r6;
    }

    private void maybeReportSkippedSilence() {
        if (this.accumulatedSkippedSilenceDurationUs < 300000) goto L6;
        this.listener.onSilenceSkipped();
        this.accumulatedSkippedSilenceDurationUs = 0;
        return;
    }

    @EnsuresNonNull({"audioCapabilities"})
    private void maybeStartAudioCapabilitiesReceiver() {
        Looper r0 = Looper.myLooper();
        if (this.audioCapabilitiesReceiver != null) goto L5;
    L8:
        boolean r1 = true;
    L9:
        Assertions.checkState(r1, "DefaultAudioSink accessed on multiple threads: " + getLooperThreadName(this.playbackLooper) + " and " + getLooperThreadName(r0));
        if (this.audioCapabilitiesReceiver != null) goto L14;
        Context r12 = this.context;
        if (r12 == null) goto L14;
        this.playbackLooper = r0;
        AudioCapabilitiesReceiver r02 = new AudioCapabilitiesReceiver(r12, new DefaultAudioSink$$ExternalSyntheticLambda1(this), this.audioAttributes, this.preferredDevice);
        this.audioCapabilitiesReceiver = r02;
        this.audioCapabilities = r02.register();
    L14:
        Assertions.checkNotNull(this.audioCapabilities);
        return;
    L5:
        if (this.playbackLooper == r0) goto L8;
        r1 = false;
        goto L9
    }

    private void playPendingData() {
        if (this.stoppedAudioTrack == true) goto L9;
        this.stoppedAudioTrack = true;
        this.audioTrackPositionTracker.handleEndOfStream(getWrittenFrames());
        if (isOffloadedPlayback(this.audioTrack) == false) goto L7;
        this.handledOffloadOnPresentationEnded = false;
    L7:
        this.audioTrack.stop();
        this.bytesUntilNextAvSync = 0;
        return;
    }

    private void processBuffers(long r3) {
        drainOutputBuffer(r3);
        if (this.outputBuffer == null) goto L6;
        return;
    L6:
        if (this.audioProcessingPipeline.isOperational() == true) goto L12;
        ByteBuffer r0 = this.inputBuffer;
        if (r0 == null) goto L24;
        setOutputBuffer(r0);
        drainOutputBuffer(r3);
        return;
    L24:
        return;
    L12:
        if (this.audioProcessingPipeline.isEnded() == true) goto L35;
    L13:
        ByteBuffer r02 = this.audioProcessingPipeline.getOutput();
        if (r02.hasRemaining() == false) goto L18;
        setOutputBuffer(r02);
        drainOutputBuffer(r3);
        if (this.outputBuffer == null) goto L13;
        return;
    L18:
        ByteBuffer r03 = this.inputBuffer;
        if (r03 == null) goto L33;
        if (r03.hasRemaining() == false) goto L34;
        this.audioProcessingPipeline.queueInput(this.inputBuffer);
        goto L12
    L34:
        return;
    L33:
        return;
    }

    private void registerStreamEventCallbackV29(AudioTrack r2) {
        if (this.offloadStreamEventCallbackV29 != null) goto L5;
        this.offloadStreamEventCallbackV29 = new StreamEventCallbackV29(this);
    L5:
        this.offloadStreamEventCallbackV29.register(r2);
    }

    private static void releaseAudioTrackAsync(AudioTrack r4, AudioSink.Listener r5, AudioSink.AudioTrackConfig r6) {
        Handler r0 = new Handler(Looper.myLooper());
        Object r1 = releaseExecutorLock;
        monitor-enter(r1);
    L7:
        th = move-exception;
        throw th;
    L5:
        if (releaseExecutor != null) goto L9;
        releaseExecutor = Util.newSingleThreadScheduledExecutor("ExoPlayer:AudioTrackReleaseThread");     // Catch: Throwable -> L7
    L9:
        pendingReleaseCount++;
        releaseExecutor.schedule(new DefaultAudioSink$$ExternalSyntheticLambda0(r4, r5, r0, r6), 20, TimeUnit.MILLISECONDS);     // Catch: Throwable -> L7
        monitor-exit(r1);     // Catch: Throwable -> L7
    }

    private void resetSinkStateForFlush() {
        this.submittedPcmBytes = 0;
        this.submittedEncodedFrames = 0;
        this.writtenPcmBytes = 0;
        this.writtenEncodedFrames = 0;
        this.isWaitingForOffloadEndOfStreamHandled = false;
        this.framesPerEncodedSample = 0;
        this.mediaPositionParameters = new MediaPositionParameters(this.playbackParameters, 0, 0, null);
        this.startMediaTimeUs = 0;
        this.afterDrainParameters = null;
        this.mediaPositionParametersCheckpoints.clear();
        this.inputBuffer = null;
        this.inputBufferAccessUnitCount = 0;
        this.outputBuffer = null;
        this.stoppedAudioTrack = false;
        this.handledEndOfStream = false;
        this.handledOffloadOnPresentationEnded = false;
        this.avSyncHeader = null;
        this.bytesUntilNextAvSync = 0;
        this.trimmingAudioProcessor.resetTrimmedFrameCount();
        setupAudioProcessors();
    }

    private void setAudioProcessorPlaybackParameters(PlaybackParameters r8) {
        MediaPositionParameters r0 = new MediaPositionParameters(r8, -9223372036854775807L, -9223372036854775807L, null);
        if (isAudioTrackInitialized() == false) goto L6;
        this.afterDrainParameters = r0;
        return;
    L6:
        this.mediaPositionParameters = r0;
    }

    private void setAudioTrackPlaybackParametersV23() {
        if (isAudioTrackInitialized() == false) goto L13;
        PlaybackParams r0 = new PlaybackParams().allowDefaults().setSpeed(this.playbackParameters.speed).setPitch(this.playbackParameters.pitch).setAudioFallbackMode(2);
        this.audioTrack.setPlaybackParams(r0);     // Catch: IllegalArgumentException -> L7
    L9:
        PlaybackParameters r02 = new PlaybackParameters(this.audioTrack.getPlaybackParams().getSpeed(), this.audioTrack.getPlaybackParams().getPitch());
        this.playbackParameters = r02;
        this.audioTrackPositionTracker.setAudioTrackPlaybackSpeed(r02.speed);
        return;
    L7:
        e = move-exception;
        Log.w("DefaultAudioSink", "Failed to set playback params", e);
        goto L9
    }

    private void setOutputBuffer(ByteBuffer r2) {
        if (this.outputBuffer != null) goto L5;
        boolean r0 = true;
    L6:
        Assertions.checkState(r0);
        if (r2.hasRemaining() == true) goto L9;
        return;
    L9:
        this.outputBuffer = maybeRampUpVolume(r2);
        return;
    L5:
        r0 = false;
        goto L6
    }

    private void setVolumeInternal() {
        if (isAudioTrackInitialized() == false) goto L6;
        this.audioTrack.setVolume(this.volume);
        return;
    }

    private void setupAudioProcessors() {
        AudioProcessingPipeline r0 = this.configuration.audioProcessingPipeline;
        this.audioProcessingPipeline = r0;
        r0.flush();
    }

    private boolean shouldApplyAudioProcessorPlaybackParameters() {
        if (this.tunneling == true) goto L10;
        Configuration r0 = this.configuration;
        if (r0.outputMode == 0) goto L7;
        return false;
    L7:
        if (shouldUseFloatOutput(r0.inputFormat.pcmEncoding) == true) goto L13;
        return true;
    L13:
        return false;
    L10:
        return false;
    }

    private boolean shouldUseFloatOutput(int r1) {
        if (this.enableFloatOutput == true) goto L5;
        return false;
    L5:
        if (Util.isEncodingHighResolutionPcm(r1) == false) goto L10;
        return true;
    L10:
        return false;
    }

    private boolean useAudioTrackPlaybackParams() {
        Configuration r0 = this.configuration;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.enableAudioTrackPlaybackParams == false) goto L10;
        return true;
    L10:
        return false;
    }

    private static int writeNonBlocking(AudioTrack r1, ByteBuffer r2, int r3) {
        return r1.write(r2, r3, 1);
    }

    private int writeNonBlockingWithAvSync(AudioTrack r7, ByteBuffer r8, int r9, long r10) {
        if (Build.VERSION.SDK_INT < 26) goto L7;
        return r7.write(r8, r9, 1, r10 * 1000);
    L7:
        if (this.avSyncHeader != null) goto L10;
        ByteBuffer r0 = ByteBuffer.allocate(16);
        this.avSyncHeader = r0;
        r0.order(ByteOrder.BIG_ENDIAN);
        this.avSyncHeader.putInt(1431633921);
    L10:
        if (this.bytesUntilNextAvSync != 0) goto L12;
        this.avSyncHeader.putInt(4, r9);
        this.avSyncHeader.putLong(8, r10 * 1000);
        this.avSyncHeader.position(0);
        this.bytesUntilNextAvSync = r9;
    L12:
        int r102 = this.avSyncHeader.remaining();
        if (r102 <= 0) goto L20;
        int r11 = r7.write(this.avSyncHeader, r102, 1);
        if (r11 >= 0) goto L18;
        this.bytesUntilNextAvSync = 0;
        return r11;
    L18:
        if (r11 >= r102) goto L20;
        return 0;
    L20:
        int r6 = writeNonBlocking(r7, r8, r9);
        if (r6 >= 0) goto L24;
        this.bytesUntilNextAvSync = 0;
        return r6;
    L24:
        this.bytesUntilNextAvSync -= r6;
        return r6;
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public void configure(Format r22, int r23, int[] r24) {
        maybeStartAudioCapabilitiesReceiver();
        if ("audio/raw".equals(r22.sampleMimeType) == false) goto L17;
        Assertions.checkArgument(Util.isEncodingLinearPcm(r22.pcmEncoding));
        int r1 = Util.getPcmFrameSize(r22.pcmEncoding, r22.channelCount);
        ImmutableList.Builder r6 = new ImmutableList.Builder();
        r6.addAll(this.availableAudioProcessors);
        if (shouldUseFloatOutput(r22.pcmEncoding) == false) goto L7;
        r6.add(this.toFloatPcmAudioProcessor);
    L8:
        AudioProcessingPipeline r7 = new AudioProcessingPipeline(r6.build());
        if (r7.equals(this.audioProcessingPipeline) == false) goto L11;
        r7 = this.audioProcessingPipeline;
    L11:
        this.trimmingAudioProcessor.setTrimFrameCount(r22.encoderDelay, r22.encoderPadding);
        this.channelMappingAudioProcessor.setChannelMap(r24);
        AudioProcessor.AudioFormat r62 = r7.configure(new AudioProcessor.AudioFormat(r22));     // Catch: AudioProcessor.UnhandledAudioFormatException -> L14
        int r8 = r62.encoding;
        int r9 = r62.sampleRate;
        int r10 = this.audioTrackProvider.getAudioTrackChannelConfig(r62.channelCount);
        int r63 = Util.getPcmFrameSize(r8, r62.channelCount);
        boolean r11 = this.preferAudioTrackPlaybackParams;
        int r15 = 0;
        int r14 = r8;
        int r82 = r10;
        AudioProcessingPipeline r102 = r7;
        int r72 = r63;
        boolean r64 = false;
    L31:
        if (r14 == 0) goto L58;
        if (r82 == 0) goto L56;
        int r12 = r22.bitrate;
        if ("audio/vnd.dts.hd;profile=lbr".equals(r22.sampleMimeType) == false) goto L37;
        if (r12 != (-1)) goto L37;
        r12 = 768000;
    L37:
        int r18 = r12;
        if (r23 == 0) goto L40;
        int r17 = r9;
        int r92 = r23;
    L49:
        this.offloadDisabledUntilNextConfiguration = false;
        boolean r122 = r64;
        int r4 = r15;
        int r65 = r17;
        Configuration r13 = new Configuration(r22, r1, r4, r72, r65, r82, r14, r92, r102, r11, r122, this.tunneling);
        if (isAudioTrackInitialized() == false) goto L53;
        this.pendingConfiguration = r13;
        return;
    L53:
        this.configuration = r13;
        return;
    L40:
        AudioTrackBufferSizeProvider r123 = this.audioTrackBufferSizeProvider;
        int r132 = getAudioTrackMinBufferSize(r9, r82, r14);
        if (r72 == (-1)) goto L43;
        int r16 = r72;
    L44:
        if (r11 == false) goto L47;
        double r3 = 8.0d;
    L46:
        r17 = r9;
        r92 = r123.getBufferSizeInBytes(r132, r14, r15, r16, r17, r18, r3);
        goto L49
    L47:
        r3 = 1.0d;
        goto L46
    L43:
        r16 = 1;
        goto L44
    L56:
        throw new AudioSink.ConfigurationException("Invalid output channel config (mode=" + r15 + ") for: " + r22, r22);
    L58:
        throw new AudioSink.ConfigurationException("Invalid output encoding (mode=" + r15 + ") for: " + r22, r22);
    L14:
        e = move-exception;
        throw new AudioSink.ConfigurationException(e, r22);
    L7:
        r6.add(this.toInt16PcmAudioProcessor);
        r6.add(this.audioProcessorChain.getAudioProcessors());
        goto L8
    L17:
        AudioProcessingPipeline r73 = new AudioProcessingPipeline(ImmutableList.of());
        r9 = r22.sampleRate;
        if (this.offloadMode == 0) goto L20;
        AudioOffloadSupport r19 = getFormatOffloadSupport(r22);
    L22:
        if (this.offloadMode != 0) goto L24;
    L27:
        Pair<Integer, Integer> r110 = this.audioCapabilities.getEncodingAndChannelConfigForPassthrough(r22, this.audioAttributes);
        if (r110 == null) goto L60;
        int r83 = ((Integer) r110.first).intValue();
        int r103 = ((Integer) r110.second).intValue();
        r11 = this.preferAudioTrackPlaybackParams;
        r15 = 2;
        r1 = -1;
        r64 = false;
        r14 = r83;
        r82 = r103;
    L26:
        r102 = r73;
        r72 = r1;
        goto L31
    L60:
        throw new AudioSink.ConfigurationException("Unable to configure passthrough for: " + r22, r22);
    L24:
        if (r19.isFormatSupported == false) goto L27;
        int r84 = MimeTypes.getEncoding((String) Assertions.checkNotNull(r22.sampleMimeType), r22.codecs);
        int r104 = this.audioTrackProvider.getAudioTrackChannelConfig(r22.channelCount);
        r64 = r19.isGaplessSupported;
        r1 = -1;
        r14 = r84;
        r82 = r104;
        r11 = true;
        r15 = 1;
        goto L26
    L20:
        r19 = AudioOffloadSupport.DEFAULT_UNSUPPORTED;
        goto L22
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public void disableTunneling() {
        if (this.tunneling == false) goto L6;
        this.tunneling = false;
        flush();
        return;
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public void enableTunnelingV21() {
        Assertions.checkState(this.externalAudioSessionIdProvided);
        if (this.tunneling == true) goto L6;
        this.tunneling = true;
        flush();
        return;
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public void flush() {
        if (isAudioTrackInitialized() == false) goto L17;
        resetSinkStateForFlush();
        if (this.audioTrackPositionTracker.isPlaying() == false) goto L8;
        this.audioTrack.pause();
    L8:
        if (isOffloadedPlayback(this.audioTrack) == false) goto L10;
        ((StreamEventCallbackV29) Assertions.checkNotNull(this.offloadStreamEventCallbackV29)).unregister(this.audioTrack);
    L10:
        AudioSink.AudioTrackConfig r0 = this.configuration.buildAudioTrackConfig();
        Configuration r2 = this.pendingConfiguration;
        if (r2 == null) goto L13;
        this.configuration = r2;
        this.pendingConfiguration = null;
    L13:
        this.audioTrackPositionTracker.reset();
        OnRoutingChangedListenerApi24 r22 = this.onRoutingChangedListener;
        if (r22 == null) goto L16;
        r22.release();
        this.onRoutingChangedListener = null;
    L16:
        releaseAudioTrackAsync(this.audioTrack, this.listener, r0);
        this.audioTrack = null;
    L17:
        this.writeExceptionPendingExceptionHolder.clear();
        this.initializationExceptionPendingExceptionHolder.clear();
        this.skippedOutputFrameCountAtLastPosition = 0;
        this.accumulatedSkippedSilenceDurationUs = 0;
        Handler r4 = this.reportSkippedSilenceHandler;
        if (r4 == null) goto L21;
        ((Handler) Assertions.checkNotNull(r4)).removeCallbacksAndMessages(null);
        return;
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public long getAudioTrackBufferSizeUs() {
        if (isAudioTrackInitialized() == true) goto L7;
        return -9223372036854775807L;
    L7:
        return Api23.getAudioTrackBufferSizeUs(this.audioTrack, this.configuration);
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public long getCurrentPositionUs(boolean r5) {
        if (isAudioTrackInitialized() == true) goto L5;
        return Long.MIN_VALUE;
    L5:
        if (this.startMediaTimeUsNeedsInit == false) goto L8;
        return Long.MIN_VALUE;
    L8:
        return applySkipping(applyMediaPositionParameters(Math.min(this.audioTrackPositionTracker.getCurrentPositionUs(), this.configuration.framesToDurationUs(getWrittenFrames()))));
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public AudioOffloadSupport getFormatOffloadSupport(Format r2) {
        if (this.offloadDisabledUntilNextConfiguration == false) goto L7;
        return AudioOffloadSupport.DEFAULT_UNSUPPORTED;
    L7:
        return this.audioOffloadSupportProvider.getAudioOffloadSupport(r2, this.audioAttributes);
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public int getFormatSupport(Format r4) {
        maybeStartAudioCapabilitiesReceiver();
        if ("audio/raw".equals(r4.sampleMimeType) == false) goto L18;
        boolean r0 = Util.isEncodingLinearPcm(r4.pcmEncoding);
        int r42 = r4.pcmEncoding;
        if (r0 == true) goto L8;
        Log.w("DefaultAudioSink", "Invalid PCM encoding: " + r42);
        return 0;
    L8:
        if (r42 != 2) goto L10;
    L16:
        return 2;
    L10:
        if (this.enableFloatOutput == true) goto L12;
        return 1;
    L12:
        if (r42 == 4) goto L16;
        return 1;
    L18:
        if (this.audioCapabilities.isPassthroughPlaybackSupported(r4, this.audioAttributes) == false) goto L20;
        return 2;
    L20:
        return 0;
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public PlaybackParameters getPlaybackParameters() {
        return this.playbackParameters;
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public boolean handleBuffer(ByteBuffer r18, long r19, int r21) {
        ByteBuffer r5 = this.inputBuffer;
        if (r5 == null) goto L7;
        if (r18 == r5) goto L7;
        boolean r52 = false;
    L8:
        Assertions.checkArgument(r52);
        if (this.pendingConfiguration == null) goto L31;
        if (drainToEndOfStream() == true) goto L14;
        return false;
    L14:
        if (this.pendingConfiguration.canReuseAudioTrack(this.configuration) == true) goto L19;
        playPendingData();
        if (hasPendingData() == false) goto L18;
        return false;
    L18:
        flush();
    L29:
        applyAudioProcessorPlaybackParametersAndSkipSilence(r19);
        goto L31
    L19:
        this.configuration = this.pendingConfiguration;
        this.pendingConfiguration = null;
        AudioTrack r53 = this.audioTrack;
        if (r53 == null) goto L29;
        if (isOffloadedPlayback(r53) == false) goto L29;
        if (this.configuration.enableOffloadGapless == false) goto L29;
        if (this.audioTrack.getPlayState() != 3) goto L28;
        this.audioTrack.setOffloadEndOfStream();
        this.audioTrackPositionTracker.expectRawPlaybackHeadReset();
    L28:
        AudioTrack r54 = this.audioTrack;
        Format r9 = this.configuration.inputFormat;
        r54.setOffloadDelayPadding(r9.encoderDelay, r9.encoderPadding);
        this.isWaitingForOffloadEndOfStreamHandled = true;
    L31:
        if (isAudioTrackInitialized() == false) goto L106;
    L41:
        this.initializationExceptionPendingExceptionHolder.clear();
        if (this.startMediaTimeUsNeedsInit == false) goto L50;
        this.startMediaTimeUs = Math.max(0, r19);
        this.startMediaTimeUsNeedsSync = false;
        this.startMediaTimeUsNeedsInit = false;
        if (useAudioTrackPlaybackParams() == false) goto L46;
        setAudioTrackPlaybackParametersV23();
    L46:
        applyAudioProcessorPlaybackParametersAndSkipSilence(r19);
        if (this.playing == false) goto L50;
        play();
    L50:
        if (this.audioTrackPositionTracker.mayHandleBuffer(getWrittenFrames()) == true) goto L53;
        return false;
    L53:
        if (this.inputBuffer == null) goto L55;
    L97:
        processBuffers(r19);
        if (this.inputBuffer.hasRemaining() == true) goto L102;
        this.inputBuffer = null;
        this.inputBufferAccessUnitCount = 0;
        return true;
    L102:
        if (this.audioTrackPositionTracker.isStalled(getWrittenFrames()) == false) goto L105;
        Log.w("DefaultAudioSink", "Resetting stalled audio track");
        flush();
        return true;
    L105:
        return false;
    L55:
        if (r18.order() != ByteOrder.LITTLE_ENDIAN) goto L57;
        boolean r55 = true;
    L58:
        Assertions.checkArgument(r55);
        if (r18.hasRemaining() == true) goto L61;
        return true;
    L61:
        Configuration r56 = this.configuration;
        if (r56.outputMode == 0) goto L69;
        if (this.framesPerEncodedSample != 0) goto L69;
        int r57 = getFramesPerEncodedSample(r56.outputEncoding, r18);
        this.framesPerEncodedSample = r57;
        if (r57 != 0) goto L69;
        return true;
    L69:
        if (this.afterDrainParameters != null) goto L71;
    L74:
        long r11 = this.startMediaTimeUs + this.configuration.inputFramesToDurationUs(getSubmittedFrames() - this.trimmingAudioProcessor.getTrimmedFrameCount());
        if (this.startMediaTimeUsNeedsSync == true) goto L83;
        if (Math.abs(r11 - r19) <= 200000) goto L83;
        AudioSink.Listener r58 = this.listener;
        if (r58 == null) goto L81;
        r58.onAudioSinkError(new AudioSink.UnexpectedDiscontinuityException(r19, r11));
    L81:
        this.startMediaTimeUsNeedsSync = true;
    L83:
        if (this.startMediaTimeUsNeedsSync == false) goto L93;
        if (drainToEndOfStream() == true) goto L87;
        return false;
    L87:
        long r112 = r19 - r11;
        this.startMediaTimeUs += r112;
        this.startMediaTimeUsNeedsSync = false;
        applyAudioProcessorPlaybackParametersAndSkipSilence(r19);
        AudioSink.Listener r59 = this.listener;
        if (r59 == null) goto L93;
        if (r112 == 0) goto L93;
        r59.onPositionDiscontinuity();
    L93:
        if (this.configuration.outputMode != 0) goto L95;
        this.submittedPcmBytes += r18.remaining();
    L96:
        this.inputBuffer = r18;
        this.inputBufferAccessUnitCount = r21;
        goto L97
    L95:
        this.submittedEncodedFrames += this.framesPerEncodedSample * r21;
        goto L96
    L71:
        if (drainToEndOfStream() == true) goto L73;
        return false;
    L73:
        applyAudioProcessorPlaybackParametersAndSkipSilence(r19);
        this.afterDrainParameters = null;
        goto L74
    L57:
        r55 = false;
        goto L58
    L106:
        if (initializeAudioTrack() == true) goto L41;
        return false;
    L35:
        e = move-exception;
        if (e.isRecoverable == true) goto L40;
        this.initializationExceptionPendingExceptionHolder.throwExceptionIfDeadlineIsReached(e);
        return false;
    L40:
        throw e;
    L7:
        r52 = true;
        goto L8
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public void handleDiscontinuity() {
        this.startMediaTimeUsNeedsSync = true;
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public boolean hasPendingData() {
        if (isAudioTrackInitialized() == true) goto L5;
        return false;
    L5:
        if (Build.VERSION.SDK_INT < 29) goto L11;
        if (this.audioTrack.isOffloadedPlayback() == false) goto L11;
        if (this.handledOffloadOnPresentationEnded == false) goto L11;
        return false;
    L11:
        if (this.audioTrackPositionTracker.hasPendingData(getWrittenFrames()) == false) goto L17;
        return true;
    L17:
        return false;
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public boolean isEnded() {
        if (isAudioTrackInitialized() == true) goto L5;
        return true;
    L5:
        if (this.handledEndOfStream == true) goto L7;
        return false;
    L7:
        if (hasPendingData() == false) goto L14;
        return false;
    L14:
        return true;
    }

    public void onAudioCapabilitiesChanged(AudioCapabilities r5) {
        Looper r0 = Looper.myLooper();
        if (this.playbackLooper != r0) goto L5;
        boolean r1 = true;
    L6:
        Assertions.checkState(r1, "Current looper (" + getLooperThreadName(r0) + ") is not the playback looper (" + getLooperThreadName(this.playbackLooper) + ")");
        AudioCapabilities r02 = this.audioCapabilities;
        if (r02 != null) goto L9;
        return;
    L9:
        if (r5.equals(r02) == true) goto L15;
        this.audioCapabilities = r5;
        AudioSink.Listener r4 = this.listener;
        if (r4 == null) goto L16;
        r4.onAudioCapabilitiesChanged();
        return;
    L16:
        return;
    L15:
        return;
    L5:
        r1 = false;
        goto L6
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public void pause() {
        this.playing = false;
        if (isAudioTrackInitialized() == false) goto L10;
        this.audioTrackPositionTracker.pause();
        if (this.stoppedAudioTrack == true) goto L7;
    L8:
        this.audioTrack.pause();
        return;
    L7:
        if (isOffloadedPlayback(this.audioTrack) == true) goto L8;
        return;
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public void play() {
        this.playing = true;
        if (isAudioTrackInitialized() == false) goto L10;
        this.audioTrackPositionTracker.start();
        if (this.stoppedAudioTrack == true) goto L7;
    L8:
        this.audioTrack.play();
        return;
    L7:
        if (isOffloadedPlayback(this.audioTrack) == true) goto L8;
        return;
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public void playToEndOfStream() {
        if (this.handledEndOfStream == false) goto L5;
        return;
    L5:
        if (isAudioTrackInitialized() == true) goto L7;
        return;
    L7:
        if (drainToEndOfStream() == false) goto L12;
        playPendingData();
        this.handledEndOfStream = true;
        return;
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public void release() {
        AudioCapabilitiesReceiver r0 = this.audioCapabilitiesReceiver;
        if (r0 == null) goto L6;
        r0.unregister();
        return;
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public void reset() {
        flush();
        UnmodifiableIterator<AudioProcessor> r0 = this.availableAudioProcessors.iterator();
    L4:
        if (r0.hasNext() == false) goto L6;
        r0.next().reset();
        goto L4
    L6:
        this.toInt16PcmAudioProcessor.reset();
        this.toFloatPcmAudioProcessor.reset();
        AudioProcessingPipeline r02 = this.audioProcessingPipeline;
        if (r02 == null) goto L9;
        r02.reset();
    L9:
        this.playing = false;
        this.offloadDisabledUntilNextConfiguration = false;
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public void setAudioAttributes(AudioAttributes r2) {
        if (this.audioAttributes.equals(r2) == true) goto L13;
        this.audioAttributes = r2;
        if (this.tunneling == false) goto L8;
        return;
    L8:
        AudioCapabilitiesReceiver r0 = this.audioCapabilitiesReceiver;
        if (r0 == null) goto L11;
        r0.setAudioAttributes(r2);
    L11:
        flush();
        return;
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public void setAudioSessionId(int r3) {
        boolean r1 = false;
        if (this.pendingAudioSessionIdChangeConfirmation == false) goto L8;
        if (this.audioSessionId != r3) goto L14;
        this.pendingAudioSessionIdChangeConfirmation = false;
        goto L8
    L14:
        return;
    L8:
        if (this.audioSessionId == r3) goto L15;
        this.audioSessionId = r3;
        if (r3 == 0) goto L12;
        r1 = true;
    L12:
        this.externalAudioSessionIdProvided = r1;
        flush();
        return;
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public void setAuxEffectInfo(AuxEffectInfo r5) {
        if (this.auxEffectInfo.equals(r5) == false) goto L5;
        return;
    L5:
        int r0 = r5.effectId;
        float r1 = r5.sendLevel;
        AudioTrack r2 = this.audioTrack;
        if (r2 != null) goto L8;
    L12:
        this.auxEffectInfo = r5;
        return;
    L8:
        if (this.auxEffectInfo.effectId == r0) goto L10;
        r2.attachAuxEffect(r0);
    L10:
        if (r0 == 0) goto L12;
        this.audioTrack.setAuxEffectSendLevel(r1);
        goto L12
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public void setClock(Clock r1) {
        this.audioTrackPositionTracker.setClock(r1);
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public void setListener(AudioSink.Listener r1) {
        this.listener = r1;
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public void setOffloadDelayPadding(int r2, int r3) {
        AudioTrack r0 = this.audioTrack;
        if (r0 != null) goto L5;
        return;
    L5:
        if (isOffloadedPlayback(r0) == false) goto L13;
        Configuration r02 = this.configuration;
        if (r02 != null) goto L9;
        return;
    L9:
        if (r02.enableOffloadGapless == false) goto L15;
        this.audioTrack.setOffloadDelayPadding(r2, r3);
        return;
    L15:
        return;
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public void setOffloadMode(int r3) {
        if (Build.VERSION.SDK_INT < 29) goto L5;
        boolean r0 = true;
    L6:
        Assertions.checkState(r0);
        this.offloadMode = r3;
        return;
    L5:
        r0 = false;
        goto L6
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public void setPlaybackParameters(PlaybackParameters r6) {
        this.playbackParameters = new PlaybackParameters(Util.constrainValue(r6.speed, 0.1f, 8.0f), Util.constrainValue(r6.pitch, 0.1f, 8.0f));
        if (useAudioTrackPlaybackParams() == false) goto L6;
        setAudioTrackPlaybackParametersV23();
        return;
    L6:
        setAudioProcessorPlaybackParameters(r6);
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public void setPlayerId(PlayerId r1) {
        this.playerId = r1;
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public void setPreferredDevice(AudioDeviceInfo r2) {
        if (r2 != null) goto L4;
        AudioDeviceInfoApi23 r0 = null;
    L5:
        this.preferredDevice = r0;
        AudioCapabilitiesReceiver r02 = this.audioCapabilitiesReceiver;
        if (r02 == null) goto L8;
        r02.setRoutedDevice(r2);
    L8:
        AudioTrack r22 = this.audioTrack;
        if (r22 == null) goto L12;
        Api23.setPreferredDeviceOnAudioTrack(r22, this.preferredDevice);
        return;
    L12:
        return;
    L4:
        r0 = new AudioDeviceInfoApi23(r2);
        goto L5
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public void setSkipSilenceEnabled(boolean r1) {
        this.skipSilenceEnabled = r1;
        if (useAudioTrackPlaybackParams() == false) goto L5;
        PlaybackParameters r12 = PlaybackParameters.DEFAULT;
    L6:
        setAudioProcessorPlaybackParameters(r12);
        return;
    L5:
        r12 = this.playbackParameters;
        goto L6
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public void setVolume(float r2) {
        if (this.volume == r2) goto L6;
        this.volume = r2;
        setVolumeInternal();
        return;
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public boolean supportsFormat(Format r1) {
        if (getFormatSupport(r1) == 0) goto L6;
        return true;
    L6:
        return false;
    }
}
