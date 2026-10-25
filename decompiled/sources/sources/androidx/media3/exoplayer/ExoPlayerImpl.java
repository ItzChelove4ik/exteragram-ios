package androidx.media3.exoplayer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.SurfaceTexture;
import android.media.MediaFormat;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import android.util.Pair;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.TextureView;
import androidx.media3.common.AudioAttributes;
import androidx.media3.common.BasePlayer;
import androidx.media3.common.DeviceInfo;
import androidx.media3.common.FlagSet;
import androidx.media3.common.Format;
import androidx.media3.common.IllegalSeekPositionException;
import androidx.media3.common.MediaItem;
import androidx.media3.common.MediaLibraryInfo;
import androidx.media3.common.MediaMetadata;
import androidx.media3.common.Metadata;
import androidx.media3.common.PlaybackException;
import androidx.media3.common.PlaybackParameters;
import androidx.media3.common.Player;
import androidx.media3.common.Timeline;
import androidx.media3.common.TrackSelectionParameters;
import androidx.media3.common.Tracks;
import androidx.media3.common.VideoListener;
import androidx.media3.common.VideoSize;
import androidx.media3.common.text.Cue;
import androidx.media3.common.text.CueGroup;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.BackgroundThreadStateHandler;
import androidx.media3.common.util.Clock;
import androidx.media3.common.util.ConditionVariable;
import androidx.media3.common.util.HandlerWrapper;
import androidx.media3.common.util.ListenerSet;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Size;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.AudioBecomingNoisyManager;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.exoplayer.ExoPlayerImplInternal;
import androidx.media3.exoplayer.MediaSourceList;
import androidx.media3.exoplayer.PlayerMessage;
import androidx.media3.exoplayer.StreamVolumeManager;
import androidx.media3.exoplayer.analytics.AnalyticsCollector;
import androidx.media3.exoplayer.analytics.AnalyticsListener;
import androidx.media3.exoplayer.analytics.MediaMetricsListener;
import androidx.media3.exoplayer.analytics.PlayerId;
import androidx.media3.exoplayer.audio.AudioRendererEventListener;
import androidx.media3.exoplayer.audio.AudioSink;
import androidx.media3.exoplayer.metadata.MetadataOutput;
import androidx.media3.exoplayer.source.MaskingMediaSource;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.source.ShuffleOrder;
import androidx.media3.exoplayer.source.TimelineWithUpdatedMediaItem;
import androidx.media3.exoplayer.source.TrackGroupArray;
import androidx.media3.exoplayer.text.TextOutput;
import androidx.media3.exoplayer.trackselection.ExoTrackSelection;
import androidx.media3.exoplayer.trackselection.TrackSelector;
import androidx.media3.exoplayer.trackselection.TrackSelectorResult;
import androidx.media3.exoplayer.upstream.BandwidthMeter;
import androidx.media3.exoplayer.video.VideoFrameMetadataListener;
import androidx.media3.exoplayer.video.VideoRendererEventListener;
import androidx.media3.exoplayer.video.spherical.CameraMotionListener;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.UnmodifiableIterator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.Executor;
import okio.Segment$$ExternalSyntheticBUOutline1;
import org.mvel2.asm.MethodWriter$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
final class ExoPlayerImpl extends BasePlayer implements ExoPlayer {
    private final AnalyticsCollector analyticsCollector;
    private final Context applicationContext;
    private final Looper applicationLooper;
    private AudioAttributes audioAttributes;
    private final AudioBecomingNoisyManager audioBecomingNoisyManager;
    private DecoderCounters audioDecoderCounters;
    private Format audioFormat;
    private final CopyOnWriteArraySet<ExoPlayer.AudioOffloadListener> audioOffloadListeners;
    private final BackgroundThreadStateHandler<Integer> audioSessionIdState;
    private Player.Commands availableCommands;
    private final BandwidthMeter bandwidthMeter;
    private final Clock clock;
    private final ComponentListener componentListener;
    private final ConditionVariable constructorFinished;
    private CueGroup currentCueGroup;
    private final long detachSurfaceTimeoutMs;
    private DeviceInfo deviceInfo;
    private ImmutableSet<Integer> disabledTrackTypesWithoutScrubbingMode;
    final TrackSelectorResult emptyTrackSelectorResult;
    private final FrameMetadataListener frameMetadataListener;
    private boolean hasNotifiedFullWrongThreadWarning;
    private final ExoPlayerImplInternal internalPlayer;
    private boolean isPriorityTaskManagerRegistered;
    private final ListenerSet<Player.Listener> listeners;
    private int maskingPeriodIndex;
    private int maskingWindowIndex;
    private long maskingWindowPositionMs;
    private final long maxSeekToPreviousPositionMs;
    private MediaMetadata mediaMetadata;
    private final MediaSource.Factory mediaSourceFactory;
    private final List<MediaSourceHolderSnapshot> mediaSourceHolderSnapshots;
    private Surface ownedSurface;
    private boolean pauseAtEndOfMediaItems;
    private boolean pendingDiscontinuity;
    private int pendingDiscontinuityReason;
    private int pendingOperationAcks;
    private final Timeline.Period period;
    final Player.Commands permanentAvailableCommands;
    private PlaybackInfo playbackInfo;
    private final HandlerWrapper playbackInfoUpdateHandler;
    private final ExoPlayerImplInternal.PlaybackInfoUpdateListener playbackInfoUpdateListener;
    private boolean playerReleased;
    private MediaMetadata playlistMetadata;
    private ExoPlayer.PreloadConfiguration preloadConfiguration;
    private int priority;
    private final Renderer[] renderers;
    private int repeatMode;
    private boolean scrubbingModeEnabled;
    private ScrubbingModeParameters scrubbingModeParameters;
    private final Renderer[] secondaryRenderers;
    private final long seekBackIncrementMs;
    private final long seekForwardIncrementMs;
    private SeekParameters seekParameters;
    private boolean shuffleModeEnabled;
    private ShuffleOrder shuffleOrder;
    private boolean skipSilenceEnabled;
    private MediaMetadata staticAndDynamicMediaMetadata;
    private final StreamVolumeManager streamVolumeManager;
    private final SuitableOutputChecker suitableOutputChecker;
    private SurfaceHolder surfaceHolder;
    private boolean surfaceHolderSurfaceIsVideoOutput;
    private Size surfaceSize;
    private TextureView textureView;
    private boolean throwsWhenUsingWrongThread;
    private final TrackSelector trackSelector;
    private final boolean useLazyPreparation;
    private int videoChangeFrameRateStrategy;
    private DecoderCounters videoDecoderCounters;
    private Format videoFormat;
    private VideoFrameMetadataListener videoFrameMetadataListener;
    private final ArrayList<VideoListener> videoListeners;
    private Object videoOutput;
    private int videoScalingMode;
    private VideoSize videoSize;
    private float volume;
    private final WakeLockManager wakeLockManager;
    private final WifiLockManager wifiLockManager;
    private Executor workerQueue;
    private final Player wrappingPlayer;

    /* renamed from: androidx.media3.exoplayer.ExoPlayerImpl$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static final class Api31 {
        public static /* synthetic */ void $r8$lambda$GdNmwbem2h_CgD6K9P4adVimWuQ(Context r0, boolean r1, ExoPlayerImpl r2, PlayerId r3) {
            MediaMetricsListener r02 = MediaMetricsListener.create(r0);
            if (r02 != null) goto L6;
            Log.w("ExoPlayerImpl", "MediaMetricsService unavailable.");
            return;
        L6:
            if (r1 == false) goto L8;
            r2.addAnalyticsListener(r02);
        L8:
            r3.setLogSessionId(r02.getLogSessionId());
        }

        public static void registerMediaMetricsListener(Context r3, ExoPlayerImpl r4, boolean r5, PlayerId r6) {
            r4.getClock().createHandler(r4.getPlaybackLooper(), null).post(new ExoPlayerImpl$Api31$$ExternalSyntheticLambda0(r3, r5, r4, r6));
        }
    }

    public final class ComponentListener implements VideoRendererEventListener, AudioRendererEventListener, TextOutput, MetadataOutput, SurfaceHolder.Callback, TextureView.SurfaceTextureListener, AudioBecomingNoisyManager.EventListener, StreamVolumeManager.Listener, ExoPlayer.AudioOffloadListener {
        final /* synthetic */ ExoPlayerImpl this$0;

        public static /* synthetic */ void $r8$lambda$1Fibjz2MXDaMzWi6GoBVfjr9nvM(ComponentListener r0, SurfaceTexture r1) {
            r0.onSurfaceTextureUpdatedInternal(r1);
        }

        public static /* synthetic */ void $r8$lambda$5bKSpLOBVovtxWFwv9J8xn5mLMc(int r0, boolean r1, Player.Listener r2) {
            r2.onDeviceVolumeChanged(r0, r1);
        }

        public static /* synthetic */ void $r8$lambda$An1y54xKv8glxsKQzZ8HIb5LFlY(ComponentListener r0, SurfaceTexture r1, int r2, int r3) {
            r0.onSurfaceTextureAvailableInternal(r1, r2, r3);
        }

        /* renamed from: $r8$lambda$L1HgkZUg6U2EbN7Wdz9Is-jwzC8, reason: not valid java name */
        public static /* synthetic */ Integer m775$r8$lambda$L1HgkZUg6U2EbN7Wdz9IsjwzC8(int r0, Integer r1) {
            return Integer.valueOf(r0);
        }

        /* renamed from: $r8$lambda$O8Jt3-I_fy8KMT7aGrtn8Tn36mo, reason: not valid java name */
        public static /* synthetic */ void m776$r8$lambda$O8Jt3I_fy8KMT7aGrtn8Tn36mo(Metadata r0, Player.Listener r1) {
            r1.onMetadata(r0);
        }

        /* renamed from: $r8$lambda$VYq7qrU-d8IW0Tgcj2t7txIoREo, reason: not valid java name */
        public static /* synthetic */ void m777$r8$lambda$VYq7qrUd8IW0Tgcj2t7txIoREo(VideoSize r0, Player.Listener r1) {
            r1.onVideoSizeChanged(r0);
        }

        /* renamed from: $r8$lambda$g2hEAIgaNPntZm-MuBYuzppuZXo, reason: not valid java name */
        public static /* synthetic */ Integer m778$r8$lambda$g2hEAIgaNPntZmMuBYuzppuZXo(int r0, Integer r1) {
            return Integer.valueOf(r0);
        }

        /* renamed from: $r8$lambda$jcen_CSgUYJ-QA0WF0xKI7beFqo, reason: not valid java name */
        public static /* synthetic */ void m779$r8$lambda$jcen_CSgUYJQA0WF0xKI7beFqo(ComponentListener r0, Player.Listener r1) {
            r1.onMediaMetadataChanged(ExoPlayerImpl.access$1600(r0.this$0));
        }

        /* renamed from: $r8$lambda$nlI4je_u-107LYLH60j4Wu21u58, reason: not valid java name */
        public static /* synthetic */ void m780$r8$lambda$nlI4je_u107LYLH60j4Wu21u58(CueGroup r0, Player.Listener r1) {
            r1.onCues(r0);
        }

        public static /* synthetic */ void $r8$lambda$nzkgynw8rmOjf1y4qnunAwzs0VQ(ComponentListener r0, SurfaceTexture r1, int r2, int r3) {
            r0.onSurfaceTextureSizeChangedInternal(r1, r2, r3);
        }

        public static /* synthetic */ void $r8$lambda$rrFwmDhvbvDQKroQT61AjQpXY_8(DeviceInfo r0, Player.Listener r1) {
            r1.onDeviceInfoChanged(r0);
        }

        /* renamed from: $r8$lambda$tF8k-q5mATF3LWJGfO6mWRuDev4, reason: not valid java name */
        public static /* synthetic */ void m781$r8$lambda$tF8kq5mATF3LWJGfO6mWRuDev4(boolean r0, Player.Listener r1) {
            r1.onSkipSilenceEnabledChanged(r0);
        }

        /* renamed from: $r8$lambda$uvIWFrD5-qsU-D4NeR4fxhQYVwc, reason: not valid java name */
        public static /* synthetic */ void m782$r8$lambda$uvIWFrD5qsUD4NeR4fxhQYVwc(List r0, Player.Listener r1) {
            r1.onCues(r0);
        }

        /* renamed from: $r8$lambda$z7d-RDJ_MF1l0DN8Bciz6GkZjHM, reason: not valid java name */
        public static /* synthetic */ void m783$r8$lambda$z7dRDJ_MF1l0DN8Bciz6GkZjHM(ComponentListener r0, SurfaceTexture r1) {
            r0.onSurfaceTextureDestroyedInternal(r1);
        }

        private ComponentListener(ExoPlayerImpl r1) {
            this.this$0 = r1;
        }

        public /* synthetic */ ComponentListener(ExoPlayerImpl r1, AnonymousClass1 r2) {
            this(r1);
        }

        @Override // androidx.media3.exoplayer.AudioBecomingNoisyManager.EventListener
        public void onAudioBecomingNoisy() {
            ExoPlayerImpl.access$2300(this.this$0, false, 3);
        }

        @Override // androidx.media3.exoplayer.audio.AudioRendererEventListener
        public void onAudioCodecError(Exception r1) {
            ExoPlayerImpl.access$400(this.this$0).onAudioCodecError(r1);
        }

        @Override // androidx.media3.exoplayer.audio.AudioRendererEventListener
        public void onAudioDecoderInitialized(String r7, long r8, long r10) {
            ExoPlayerImpl.access$400(this.this$0).onAudioDecoderInitialized(r7, r8, r10);
        }

        @Override // androidx.media3.exoplayer.audio.AudioRendererEventListener
        public void onAudioDecoderReleased(String r1) {
            ExoPlayerImpl.access$400(this.this$0).onAudioDecoderReleased(r1);
        }

        @Override // androidx.media3.exoplayer.audio.AudioRendererEventListener
        public void onAudioDisabled(DecoderCounters r2) {
            ExoPlayerImpl.access$400(this.this$0).onAudioDisabled(r2);
            ExoPlayerImpl.access$1002(this.this$0, null);
            ExoPlayerImpl.access$902(this.this$0, null);
        }

        @Override // androidx.media3.exoplayer.audio.AudioRendererEventListener
        public void onAudioEnabled(DecoderCounters r2) {
            ExoPlayerImpl.access$902(this.this$0, r2);
            ExoPlayerImpl.access$400(this.this$0).onAudioEnabled(r2);
        }

        @Override // androidx.media3.exoplayer.audio.AudioRendererEventListener
        public void onAudioInputFormatChanged(Format r2, DecoderReuseEvaluation r3) {
            ExoPlayerImpl.access$1002(this.this$0, r2);
            ExoPlayerImpl.access$400(this.this$0).onAudioInputFormatChanged(r2, r3);
        }

        @Override // androidx.media3.exoplayer.audio.AudioRendererEventListener
        public void onAudioPositionAdvancing(long r1) {
            ExoPlayerImpl.access$400(this.this$0).onAudioPositionAdvancing(r1);
        }

        @Override // androidx.media3.exoplayer.audio.AudioRendererEventListener
        public void onAudioSessionIdChanged(int r3) {
            ExoPlayerImpl.access$1200(this.this$0).updateStateAsync(new ExoPlayerImpl$ComponentListener$$ExternalSyntheticLambda12(r3), new ExoPlayerImpl$ComponentListener$$ExternalSyntheticLambda13(r3));
        }

        @Override // androidx.media3.exoplayer.audio.AudioRendererEventListener
        public void onAudioSinkError(Exception r1) {
            ExoPlayerImpl.access$400(this.this$0).onAudioSinkError(r1);
        }

        @Override // androidx.media3.exoplayer.audio.AudioRendererEventListener
        public void onAudioTrackInitialized(AudioSink.AudioTrackConfig r1) {
            ExoPlayerImpl.access$400(this.this$0).onAudioTrackInitialized(r1);
        }

        @Override // androidx.media3.exoplayer.audio.AudioRendererEventListener
        public void onAudioTrackReleased(AudioSink.AudioTrackConfig r1) {
            ExoPlayerImpl.access$400(this.this$0).onAudioTrackReleased(r1);
        }

        @Override // androidx.media3.exoplayer.audio.AudioRendererEventListener
        public void onAudioUnderrun(int r7, long r8, long r10) {
            ExoPlayerImpl.access$400(this.this$0).onAudioUnderrun(r7, r8, r10);
        }

        @Override // androidx.media3.exoplayer.text.TextOutput
        public void onCues(CueGroup r2) {
            ExoPlayerImpl.access$1302(this.this$0, r2);
            ExoPlayerImpl.access$700(this.this$0).sendEvent(27, new ExoPlayerImpl$ComponentListener$$ExternalSyntheticLambda1(r2));
        }

        @Override // androidx.media3.exoplayer.text.TextOutput
        public void onCues(List<Cue> r2) {
            ExoPlayerImpl.access$700(this.this$0).sendEvent(27, new ExoPlayerImpl$ComponentListener$$ExternalSyntheticLambda5(r2));
        }

        @Override // androidx.media3.exoplayer.video.VideoRendererEventListener
        public void onDroppedFrames(int r1, long r2) {
            ExoPlayerImpl.access$400(this.this$0).onDroppedFrames(r1, r2);
        }

        @Override // androidx.media3.exoplayer.metadata.MetadataOutput
        public void onMetadata(Metadata r4) {
            ExoPlayerImpl r0 = this.this$0;
            ExoPlayerImpl.access$1402(r0, ExoPlayerImpl.access$1400(r0).buildUpon().populateFromMetadata(r4).build());
            MediaMetadata r02 = ExoPlayerImpl.access$1500(this.this$0);
            if (r02.equals(ExoPlayerImpl.access$1600(this.this$0)) == true) goto L5;
            ExoPlayerImpl.access$1602(this.this$0, r02);
            ExoPlayerImpl.access$700(this.this$0).queueEvent(14, new ExoPlayerImpl$ComponentListener$$ExternalSyntheticLambda2(this));
        L5:
            ExoPlayerImpl.access$700(this.this$0).queueEvent(28, new ExoPlayerImpl$ComponentListener$$ExternalSyntheticLambda3(r4));
            ExoPlayerImpl.access$700(this.this$0).flushEvents();
        }

        @Override // androidx.media3.exoplayer.video.VideoRendererEventListener
        public void onRenderedFirstFrame(Object r2, long r3) {
            ExoPlayerImpl.access$400(this.this$0).onRenderedFirstFrame(r2, r3);
            if (ExoPlayerImpl.access$800(this.this$0) != r2) goto L6;
            ExoPlayerImpl.access$700(this.this$0).sendEvent(26, new ExoPlayerImpl$ComponentListener$$ExternalSyntheticLambda11());
            return;
        }

        @Override // androidx.media3.exoplayer.audio.AudioRendererEventListener
        public void onSkipSilenceEnabledChanged(boolean r2) {
            if (ExoPlayerImpl.access$1100(this.this$0) != r2) goto L5;
            return;
        L5:
            ExoPlayerImpl.access$1102(this.this$0, r2);
            ExoPlayerImpl.access$700(this.this$0).sendEvent(23, new ExoPlayerImpl$ComponentListener$$ExternalSyntheticLambda14(r2));
        }

        @Override // androidx.media3.exoplayer.ExoPlayer.AudioOffloadListener
        public void onSleepingForOffloadChanged(boolean r1) {
            ExoPlayerImpl.access$2700(this.this$0);
        }

        @Override // androidx.media3.exoplayer.StreamVolumeManager.Listener
        public void onStreamTypeChanged(int r2) {
            DeviceInfo r22 = ExoPlayerImpl.access$2500(ExoPlayerImpl.access$2400(this.this$0));
            if (r22.equals(ExoPlayerImpl.access$2600(this.this$0)) == true) goto L6;
            ExoPlayerImpl.access$2602(this.this$0, r22);
            ExoPlayerImpl.access$700(this.this$0).sendEvent(29, new ExoPlayerImpl$ComponentListener$$ExternalSyntheticLambda9(r22));
            return;
        }

        @Override // androidx.media3.exoplayer.StreamVolumeManager.Listener
        public void onStreamVolumeChanged(int r2, boolean r3) {
            ExoPlayerImpl.access$700(this.this$0).sendEvent(30, new ExoPlayerImpl$ComponentListener$$ExternalSyntheticLambda10(r2, r3));
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public void onSurfaceTextureAvailable(SurfaceTexture r3, int r4, int r5) {
            if (ExoPlayerImpl.access$2000(this.this$0) == null) goto L6;
            ExoPlayerImpl.access$2000(this.this$0).execute(new ExoPlayerImpl$ComponentListener$$ExternalSyntheticLambda6(this, r3, r4, r5));
            return;
        L6:
            onSurfaceTextureAvailableInternal(r3, r4, r5);
        }

        public void onSurfaceTextureAvailableInternal(SurfaceTexture r2, int r3, int r4) {
            ExoPlayerImpl.access$2100(this.this$0, r2);
            ExoPlayerImpl.access$1900(this.this$0, r3, r4);
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public boolean onSurfaceTextureDestroyed(SurfaceTexture r6) {
            ArrayList r0 = ExoPlayerImpl.access$2200(this.this$0);
            int r1 = r0.size();
            int r3 = 0;
        L3:
            if (r3 >= r1) goto L8;
            Object r4 = r0.get(r3);
            r3 = r3 + 1;
            if (((VideoListener) r4).onSurfaceDestroyed(r6) == false) goto L3;
            return false;
        L8:
            if (ExoPlayerImpl.access$2000(this.this$0) == null) goto L10;
            ExoPlayerImpl.access$2000(this.this$0).execute(new ExoPlayerImpl$ComponentListener$$ExternalSyntheticLambda7(this, r6));
            return true;
        L10:
            onSurfaceTextureDestroyedInternal(r6);
            return true;
        }

        public void onSurfaceTextureDestroyedInternal(SurfaceTexture r2) {
            ExoPlayerImpl.access$1800(this.this$0, null);
            ExoPlayerImpl.access$1900(this.this$0, 0, 0);
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public void onSurfaceTextureSizeChanged(SurfaceTexture r3, int r4, int r5) {
            if (ExoPlayerImpl.access$2000(this.this$0) == null) goto L6;
            ExoPlayerImpl.access$2000(this.this$0).execute(new ExoPlayerImpl$ComponentListener$$ExternalSyntheticLambda4(this, r3, r4, r5));
            return;
        L6:
            onSurfaceTextureSizeChangedInternal(r3, r4, r5);
        }

        public void onSurfaceTextureSizeChangedInternal(SurfaceTexture r1, int r2, int r3) {
            ExoPlayerImpl.access$1900(this.this$0, r2, r3);
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public void onSurfaceTextureUpdated(SurfaceTexture r3) {
            if (ExoPlayerImpl.access$2000(this.this$0) == null) goto L6;
            ExoPlayerImpl.access$2000(this.this$0).execute(new ExoPlayerImpl$ComponentListener$$ExternalSyntheticLambda0(this, r3));
            return;
        L6:
            onSurfaceTextureUpdatedInternal(r3);
        }

        public void onSurfaceTextureUpdatedInternal(SurfaceTexture r4) {
            ArrayList r3 = ExoPlayerImpl.access$2200(this.this$0);
            int r0 = r3.size();
            int r1 = 0;
        L3:
            if (r1 >= r0) goto L5;
            Object r2 = r3.get(r1);
            r1 = r1 + 1;
            ((VideoListener) r2).onSurfaceTextureUpdated(r4);
            goto L3
        }

        @Override // androidx.media3.exoplayer.video.VideoRendererEventListener
        public void onVideoCodecError(Exception r1) {
            ExoPlayerImpl.access$400(this.this$0).onVideoCodecError(r1);
        }

        @Override // androidx.media3.exoplayer.video.VideoRendererEventListener
        public void onVideoDecoderInitialized(String r7, long r8, long r10) {
            ExoPlayerImpl.access$400(this.this$0).onVideoDecoderInitialized(r7, r8, r10);
        }

        @Override // androidx.media3.exoplayer.video.VideoRendererEventListener
        public void onVideoDecoderReleased(String r1) {
            ExoPlayerImpl.access$400(this.this$0).onVideoDecoderReleased(r1);
        }

        @Override // androidx.media3.exoplayer.video.VideoRendererEventListener
        public void onVideoDisabled(DecoderCounters r2) {
            ExoPlayerImpl.access$400(this.this$0).onVideoDisabled(r2);
            ExoPlayerImpl.access$502(this.this$0, null);
            ExoPlayerImpl.access$302(this.this$0, null);
        }

        @Override // androidx.media3.exoplayer.video.VideoRendererEventListener
        public void onVideoEnabled(DecoderCounters r2) {
            ExoPlayerImpl.access$302(this.this$0, r2);
            ExoPlayerImpl.access$400(this.this$0).onVideoEnabled(r2);
        }

        @Override // androidx.media3.exoplayer.video.VideoRendererEventListener
        public void onVideoFrameProcessingOffset(long r1, int r3) {
            ExoPlayerImpl.access$400(this.this$0).onVideoFrameProcessingOffset(r1, r3);
        }

        @Override // androidx.media3.exoplayer.video.VideoRendererEventListener
        public void onVideoInputFormatChanged(Format r2, DecoderReuseEvaluation r3) {
            ExoPlayerImpl.access$502(this.this$0, r2);
            ExoPlayerImpl.access$400(this.this$0).onVideoInputFormatChanged(r2, r3);
        }

        @Override // androidx.media3.exoplayer.video.VideoRendererEventListener
        public void onVideoSizeChanged(VideoSize r2) {
            ExoPlayerImpl.access$602(this.this$0, r2);
            ExoPlayerImpl.access$700(this.this$0).sendEvent(25, new ExoPlayerImpl$ComponentListener$$ExternalSyntheticLambda8(r2));
        }

        @Override // android.view.SurfaceHolder.Callback
        public void surfaceChanged(SurfaceHolder r1, int r2, int r3, int r4) {
            ExoPlayerImpl.access$1900(this.this$0, r3, r4);
        }

        @Override // android.view.SurfaceHolder.Callback
        public void surfaceCreated(SurfaceHolder r2) {
            if (ExoPlayerImpl.access$1700(this.this$0) == false) goto L6;
            ExoPlayerImpl.access$1800(this.this$0, r2.getSurface());
            return;
        }

        @Override // android.view.SurfaceHolder.Callback
        public void surfaceDestroyed(SurfaceHolder r2) {
            if (ExoPlayerImpl.access$1700(this.this$0) == false) goto L5;
            ExoPlayerImpl.access$1800(this.this$0, null);
        L5:
            ExoPlayerImpl.access$1900(this.this$0, 0, 0);
        }
    }

    public static final class FrameMetadataListener implements VideoFrameMetadataListener, CameraMotionListener, PlayerMessage.Target {
        private CameraMotionListener cameraMotionListener;
        private CameraMotionListener internalCameraMotionListener;
        private VideoFrameMetadataListener internalVideoFrameMetadataListener;
        private VideoFrameMetadataListener videoFrameMetadataListener;

        private FrameMetadataListener() {
        }

        public /* synthetic */ FrameMetadataListener(AnonymousClass1 r1) {
            this();
        }

        @Override // androidx.media3.exoplayer.PlayerMessage.Target
        public void handleMessage(int r2, Object r3) {
            if (r2 != 7) goto L5;
            this.videoFrameMetadataListener = (VideoFrameMetadataListener) r3;
            return;
        L5:
            if (r2 != 8) goto L7;
            this.cameraMotionListener = (CameraMotionListener) r3;
            return;
        L7:
            if (r2 == 10000) goto L9;
            return;
        L9:
            MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r3);
            this.internalVideoFrameMetadataListener = null;
            this.internalCameraMotionListener = null;
        }

        @Override // androidx.media3.exoplayer.video.spherical.CameraMotionListener
        public void onCameraMotion(long r2, float[] r4) {
            CameraMotionListener r0 = this.internalCameraMotionListener;
            if (r0 == null) goto L5;
            r0.onCameraMotion(r2, r4);
        L5:
            CameraMotionListener r1 = this.cameraMotionListener;
            if (r1 == null) goto L9;
            r1.onCameraMotion(r2, r4);
            return;
        }

        @Override // androidx.media3.exoplayer.video.spherical.CameraMotionListener
        public void onCameraMotionReset() {
            CameraMotionListener r0 = this.internalCameraMotionListener;
            if (r0 == null) goto L5;
            r0.onCameraMotionReset();
        L5:
            CameraMotionListener r1 = this.cameraMotionListener;
            if (r1 == null) goto L9;
            r1.onCameraMotionReset();
            return;
        }

        @Override // androidx.media3.exoplayer.video.VideoFrameMetadataListener
        public void onVideoFrameAboutToBeRendered(long r8, long r10, Format r12, MediaFormat r13) {
            VideoFrameMetadataListener r0 = this.internalVideoFrameMetadataListener;
            if (r0 == null) goto L5;
            r0.onVideoFrameAboutToBeRendered(r8, r10, r12, r13);
        L5:
            VideoFrameMetadataListener r7 = this.videoFrameMetadataListener;
            if (r7 == null) goto L9;
            r7.onVideoFrameAboutToBeRendered(r8, r10, r12, r13);
            return;
        }
    }

    public static final class MediaSourceHolderSnapshot implements MediaSourceInfoHolder {
        private final MediaSource mediaSource;
        private Timeline timeline;
        private final Object uid;

        public MediaSourceHolderSnapshot(Object r1, MaskingMediaSource r2) {
            this.uid = r1;
            this.mediaSource = r2;
            this.timeline = r2.getTimeline();
        }

        public static /* synthetic */ MediaSource access$200(MediaSourceHolderSnapshot r0) {
            return r0.mediaSource;
        }

        @Override // androidx.media3.exoplayer.MediaSourceInfoHolder
        public Timeline getTimeline() {
            return this.timeline;
        }

        @Override // androidx.media3.exoplayer.MediaSourceInfoHolder
        public Object getUid() {
            return this.uid;
        }

        public void updateTimeline(Timeline r1) {
            this.timeline = r1;
        }
    }

    public static /* synthetic */ void $r8$lambda$0bZwQtiy8yk4MDxSHN8zBrZAm9k(PlaybackInfo r0, Player.Listener r1) {
        r1.onPlaybackParametersChanged(r0.playbackParameters);
    }

    /* renamed from: $r8$lambda$1gEdoxBmSmAW5P-QTTCG87Sm2Qw, reason: not valid java name */
    public static /* synthetic */ void m769$r8$lambda$1gEdoxBmSmAW5PQTTCG87Sm2Qw(PlaybackInfo r1, Player.Listener r2) {
        r2.onLoadingChanged(r1.isLoading);
        r2.onIsLoadingChanged(r1.isLoading);
    }

    public static /* synthetic */ void $r8$lambda$6U1cQDmyxa0xoMTCwUaYLUOFkf8(ExoPlayerImpl r3, int r4, int r5) {
        r3.listeners.sendEvent(24, new ExoPlayerImpl$$ExternalSyntheticLambda26(r4, r5));
        r3.sendRendererMessage(2, 14, new Size(r4, r5));
    }

    public static /* synthetic */ void $r8$lambda$6p_tLpVi3EtoSGIWeLjE6Rswo1I(MediaMetadata r0, Player.Listener r1) {
        r1.onMediaMetadataChanged(r0);
    }

    public static /* synthetic */ void $r8$lambda$8RcTlOi8VIAcb2Jj6Kb1NfiqLvU(boolean r0, Player.Listener r1) {
        r1.onShuffleModeEnabledChanged(r0);
    }

    public static /* synthetic */ void $r8$lambda$Ajx_Rj5NS7PTfDNs9ha2rvDQBig(PlaybackInfo r0, Player.Listener r1) {
        r1.onPlaybackSuppressionReasonChanged(r0.playbackSuppressionReason);
    }

    public static /* synthetic */ void $r8$lambda$E4n58CkvitW_uqvjUINIEQVgh4g(PlaybackInfo r0, Player.Listener r1) {
        r1.onPlaybackStateChanged(r0.playbackState);
    }

    /* renamed from: $r8$lambda$Gc-Xz3EpjmpoPTXFZ5wYflU9ZFQ, reason: not valid java name */
    public static /* synthetic */ void m770$r8$lambda$GcXz3EpjmpoPTXFZ5wYflU9ZFQ(int r0, Player.Listener r1) {
        r1.onRepeatModeChanged(r0);
    }

    public static /* synthetic */ void $r8$lambda$J8sSek0csTCyq99Am8l_et3EKt8(int r0, int r1, Player.Listener r2) {
        r2.onSurfaceSizeChanged(r0, r1);
    }

    /* renamed from: $r8$lambda$O34gV5Cdy-LOCCm_m9cyEsjkVBc, reason: not valid java name */
    public static /* synthetic */ void m771$r8$lambda$O34gV5CdyLOCCm_m9cyEsjkVBc(ExoPlayerImpl r1) {
        r1.audioSessionIdState.setStateInBackground(Integer.valueOf(Util.generateAudioSessionIdV21(r1.applicationContext)));
    }

    public static /* synthetic */ void $r8$lambda$OWJGeulauWTiP5TmgRFLAI8hY4w(MediaItem r0, int r1, Player.Listener r2) {
        r2.onMediaItemTransition(r0, r1);
    }

    public static /* synthetic */ void $r8$lambda$SA7gbpEMwf_Ft8S0LNQ1m_I4CcI(ExoPlayerImpl r1, Player.Listener r2, FlagSet r3) {
        r2.onEvents(r1.wrappingPlayer, new Player.Events(r3));
    }

    public static /* synthetic */ void $r8$lambda$Vd8bpRwTrMedPvln7St8ixx4MaQ(ExoPlayerImpl r0, Player.Listener r1) {
        r1.onAvailableCommandsChanged(r0.availableCommands);
    }

    public static /* synthetic */ void $r8$lambda$Yt4ixPU41P9lIHJETVnAcCtGyJA(ExoPlayerImpl r0, Player.Listener r1) {
        r1.onPlaylistMetadataChanged(r0.playlistMetadata);
    }

    public static /* synthetic */ void $r8$lambda$choO4TRKBCklMb4U8yq28eL8Yio(TrackSelectionParameters r0, Player.Listener r1) {
        r1.onTrackSelectionParametersChanged(r0);
    }

    /* renamed from: $r8$lambda$d6_sVBXTAtSwyW1Ev-WSK99VrZ0, reason: not valid java name */
    public static /* synthetic */ void m772$r8$lambda$d6_sVBXTAtSwyW1EvWSK99VrZ0(int r0, int r1, Player.Listener r2) {
        r2.onSurfaceSizeChanged(r0, r1);
    }

    public static /* synthetic */ void $r8$lambda$dM6MzW20444NC795J4AFrMPPDQc(PlaybackInfo r0, Player.Listener r1) {
        r1.onPlayerError(r0.playbackError);
    }

    public static /* synthetic */ void $r8$lambda$dcv3_plMRw3eMKSaAkcF4LfoFPo(AudioAttributes r0, Player.Listener r1) {
        r1.onAudioAttributesChanged(r0);
    }

    public static /* synthetic */ void $r8$lambda$fUJLApYuUgztnCwBruBeTqYzkcM(PlaybackInfo r1, Player.Listener r2) {
        r2.onPlayerStateChanged(r1.playWhenReady, r1.playbackState);
    }

    public static /* synthetic */ void $r8$lambda$fztnzcl4YpTH_4jKAea0fIRYfLI(PlaybackInfo r0, Player.Listener r1) {
        r1.onTracksChanged(r0.trackSelectorResult.tracks);
    }

    public static /* synthetic */ void $r8$lambda$gsU4DewlULShodFJ5aDEBvO25Co(int r0, Player.Listener r1) {
        r1.onAudioSessionIdChanged(r0);
    }

    public static /* synthetic */ void $r8$lambda$jdJC3SAdaMPplfMVIQbhu1ehRBo(PlaybackInfo r0, Player.Listener r1) {
        r1.onIsPlayingChanged(r0.isPlaying());
    }

    public static /* synthetic */ void $r8$lambda$kYzGcMcungTnaITqB_kFytLJzb4(PlaybackInfo r1, Player.Listener r2) {
        r2.onPlayWhenReadyChanged(r1.playWhenReady, r1.playWhenReadyChangeReason);
    }

    public static /* synthetic */ void $r8$lambda$kuBgAkV8YlLt9T_pxBD5vBMCsCg(ExoPlayerImpl r0, boolean r1) {
        r0.onSelectedOutputSuitabilityChanged(r1);
    }

    public static /* synthetic */ void $r8$lambda$lw6lhDmtFnjAs6VzxoaBDEG3RGo(ExoPlayerImpl r0, ExoPlayerImplInternal.PlaybackInfoUpdate r1) {
        r0.handlePlaybackInfo(r1);
    }

    public static /* synthetic */ void $r8$lambda$qjFXF1Utq4esG9BDL7XKZtYQDpY(int r0, Player.PositionInfo r1, Player.PositionInfo r2, Player.Listener r3) {
        r3.onPositionDiscontinuity(r0);
        r3.onPositionDiscontinuity(r1, r2, r0);
    }

    /* renamed from: $r8$lambda$rRIx3ALIOu-37br0YocGyrqs2Y4, reason: not valid java name */
    public static /* synthetic */ void m773$r8$lambda$rRIx3ALIOu37br0YocGyrqs2Y4(float r0, Player.Listener r1) {
        r1.onVolumeChanged(r0);
    }

    /* renamed from: $r8$lambda$rhuHtK-xyuDBg43oOpN6ZRzStxw, reason: not valid java name */
    public static /* synthetic */ void m774$r8$lambda$rhuHtKxyuDBg43oOpN6ZRzStxw(Player.Listener r2) {
        r2.onPlayerError(ExoPlaybackException.createForUnexpected(new ExoTimeoutException(1), 1003));
    }

    public static /* synthetic */ void $r8$lambda$sWe1niOEMHErJZ3kCbbvlTrB_Yw(ExoPlayerImpl r2, ExoPlayerImplInternal.PlaybackInfoUpdate r3) {
        r2.playbackInfoUpdateHandler.post(new ExoPlayerImpl$$ExternalSyntheticLambda25(r2, r3));
    }

    public static /* synthetic */ void $r8$lambda$wwX5uklx0ubPHQal6205ie1nrh8(ExoPlayerImpl r0, int r1, int r2) {
        r0.onAudioSessionIdChanged(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$x2Akcs2JZprO8quv1jEpAw8gEBY(PlaybackInfo r0, int r1, Player.Listener r2) {
        r2.onTimelineChanged(r0.timeline, r1);
    }

    public static /* synthetic */ void $r8$lambda$zfFqs5AoRxGJOib99KjgF3T0HpE(PlaybackInfo r0, Player.Listener r1) {
        r1.onPlayerErrorChanged(r0.playbackError);
    }

    static {
        MediaLibraryInfo.registerModule("media3.exoplayer");
    }

    @SuppressLint({"HandlerLeak"})
    public ExoPlayerImpl(ExoPlayer.Builder r42, Player r43) {
        this.videoListeners = new ArrayList();
        this.constructorFinished = new ConditionVariable();
        Log.i("ExoPlayerImpl", "Init " + Integer.toHexString(System.identityHashCode(this)) + " [AndroidXMedia3/1.8.1] [" + Util.DEVICE_DEBUG_INFO + "]");     // Catch: Throwable -> L11
        this.applicationContext = r42.context.getApplicationContext();     // Catch: Throwable -> L11
        this.analyticsCollector = r42.analyticsCollectorFunction.apply(r42.clock);     // Catch: Throwable -> L11
        this.priority = r42.priority;     // Catch: Throwable -> L11
        this.audioAttributes = r42.audioAttributes;     // Catch: Throwable -> L11
        this.videoScalingMode = r42.videoScalingMode;     // Catch: Throwable -> L11
        this.videoChangeFrameRateStrategy = r42.videoChangeFrameRateStrategy;     // Catch: Throwable -> L11
        this.skipSilenceEnabled = r42.skipSilenceEnabled;     // Catch: Throwable -> L11
        this.detachSurfaceTimeoutMs = r42.detachSurfaceTimeoutMs;     // Catch: Throwable -> L11
        ComponentListener r6 = new ComponentListener(this, null);     // Catch: Throwable -> L11
        this.componentListener = r6;     // Catch: Throwable -> L11
        this.frameMetadataListener = new FrameMetadataListener(null);     // Catch: Throwable -> L11
        Handler r5 = new Handler(r42.looper);     // Catch: Throwable -> L11
        RenderersFactory r4 = r42.renderersFactorySupplier.get();     // Catch: Throwable -> L11
        Renderer[] r3 = r4.createRenderers(r5, r6, r6, r6, r6);     // Catch: Throwable -> L11
        this.renderers = r3;     // Catch: Throwable -> L11
        if (r3.length <= 0) goto L6;
        boolean r62 = true;
    L7:
        Assertions.checkState(r62);     // Catch: Throwable -> L11
        this.secondaryRenderers = new Renderer[r3.length];     // Catch: Throwable -> L11
        int r32 = 0;
    L8:
        Renderer[] r63 = this.secondaryRenderers;     // Catch: Throwable -> L11
        if (r32 >= r63.length) goto L13;
        Renderer r8 = this.renderers[r32];     // Catch: Throwable -> L11
        ComponentListener r10 = this.componentListener;     // Catch: Throwable -> L11
        RenderersFactory r7 = r4;
        r63[r32] = r7.createSecondaryRenderer(r8, r5, r10, r10, r10, r10);     // Catch: Throwable -> L11
        r32 = r32 + 1;     // Catch: Throwable -> L11
        r4 = r7;
        goto L8
    L13:
        TrackSelector r33 = r42.trackSelectorSupplier.get();     // Catch: Throwable -> L11
        this.trackSelector = r33;     // Catch: Throwable -> L11
        this.mediaSourceFactory = r42.mediaSourceFactorySupplier.get();     // Catch: Throwable -> L11
        BandwidthMeter r44 = r42.bandwidthMeterSupplier.get();     // Catch: Throwable -> L11
        this.bandwidthMeter = r44;     // Catch: Throwable -> L11
        this.useLazyPreparation = r42.useLazyPreparation;     // Catch: Throwable -> L11
        this.seekParameters = r42.seekParameters;     // Catch: Throwable -> L11
        this.seekBackIncrementMs = r42.seekBackIncrementMs;     // Catch: Throwable -> L11
        this.seekForwardIncrementMs = r42.seekForwardIncrementMs;     // Catch: Throwable -> L11
        this.maxSeekToPreviousPositionMs = r42.maxSeekToPreviousPositionMs;     // Catch: Throwable -> L11
        this.scrubbingModeParameters = r42.scrubbingModeParameters;     // Catch: Throwable -> L11
        this.pauseAtEndOfMediaItems = r42.pauseAtEndOfMediaItems;     // Catch: Throwable -> L11
        Looper r9 = r42.looper;     // Catch: Throwable -> L11
        this.applicationLooper = r9;     // Catch: Throwable -> L11
        Clock r102 = r42.clock;     // Catch: Throwable -> L11
        this.clock = r102;     // Catch: Throwable -> L11
        if (r43 != null) goto L16;
        Player r52 = this;
    L17:
        this.wrappingPlayer = r52;     // Catch: Throwable -> L11
        this.listeners = new ListenerSet(r9, r102, new ExoPlayerImpl$$ExternalSyntheticLambda19(this));     // Catch: Throwable -> L11
        this.audioOffloadListeners = new CopyOnWriteArraySet();     // Catch: Throwable -> L11
        this.mediaSourceHolderSnapshots = new ArrayList();     // Catch: Throwable -> L11
        this.shuffleOrder = new ShuffleOrder.DefaultShuffleOrder(0);     // Catch: Throwable -> L11
        this.preloadConfiguration = ExoPlayer.PreloadConfiguration.DEFAULT;     // Catch: Throwable -> L11
        Renderer[] r72 = this.renderers;     // Catch: Throwable -> L11
        TrackSelectorResult r64 = new TrackSelectorResult(new RendererConfiguration[r72.length], new ExoTrackSelection[r72.length], Tracks.EMPTY, null);     // Catch: Throwable -> L11
        this.emptyTrackSelectorResult = r64;     // Catch: Throwable -> L11
        this.period = new Timeline.Period();     // Catch: Throwable -> L11
        Player.Commands r73 = new Player.Commands.Builder().addAll(new int[]{1, 2, 3, 13, 14, 15, 16, 17, 18, 19, 31, 20, 30, 21, 35, 22, 24, 27, 28, 32}).addIf(29, r33.isSetParametersSupported()).addIf(23, r42.deviceVolumeControlEnabled).addIf(25, r42.deviceVolumeControlEnabled).addIf(33, r42.deviceVolumeControlEnabled).addIf(26, r42.deviceVolumeControlEnabled).addIf(34, r42.deviceVolumeControlEnabled).build();     // Catch: Throwable -> L11
        this.permanentAvailableCommands = r73;     // Catch: Throwable -> L11
        this.availableCommands = new Player.Commands.Builder().addAll(r73).add(4).add(10).build();     // Catch: Throwable -> L11
        this.playbackInfoUpdateHandler = r102.createHandler(r9, null);     // Catch: Throwable -> L11
        ExoPlayerImpl$$ExternalSyntheticLambda20 r74 = new ExoPlayerImpl$$ExternalSyntheticLambda20(this);     // Catch: Throwable -> L11
        this.playbackInfoUpdateListener = r74;     // Catch: Throwable -> L11
        this.playbackInfo = PlaybackInfo.createDummy(r64);     // Catch: Throwable -> L11
        this.analyticsCollector.setPlayer(r52, r9);     // Catch: Throwable -> L11
        PlayerId r53 = new PlayerId(r42.playerName);     // Catch: Throwable -> L11
        ExoPlayerImplInternal r16 = new ExoPlayerImplInternal(this.applicationContext, this.renderers, this.secondaryRenderers, r33, r64, r42.loadControlSupplier.get(), r44, this.repeatMode, this.shuffleModeEnabled, this.analyticsCollector, this.seekParameters, r42.livePlaybackSpeedControl, r42.releaseTimeoutMs, this.pauseAtEndOfMediaItems, r42.dynamicSchedulingEnabled, r9, r102, r74, r53, r42.playbackLooperProvider, this.preloadConfiguration, this.frameMetadataListener);     // Catch: Throwable -> L11
        this.internalPlayer = r16;     // Catch: Throwable -> L11
        Looper r103 = r16.getPlaybackLooper();     // Catch: Throwable -> L11
        this.volume = 1.0f;     // Catch: Throwable -> L11
        this.repeatMode = 0;     // Catch: Throwable -> L11
        MediaMetadata r54 = MediaMetadata.EMPTY;     // Catch: Throwable -> L11
        this.mediaMetadata = r54;     // Catch: Throwable -> L11
        this.playlistMetadata = r54;     // Catch: Throwable -> L11
        this.staticAndDynamicMediaMetadata = r54;     // Catch: Throwable -> L11
        this.maskingWindowIndex = -1;     // Catch: Throwable -> L11
        this.currentCueGroup = CueGroup.EMPTY_TIME_ZERO;     // Catch: Throwable -> L11
        this.throwsWhenUsingWrongThread = true;     // Catch: Throwable -> L11
        addListener(this.analyticsCollector);     // Catch: Throwable -> L11
        r44.addEventListener(new Handler(r9), this.analyticsCollector);     // Catch: Throwable -> L11
        addAudioOffloadListener(this.componentListener);     // Catch: Throwable -> L11
        long r45 = r42.foregroundModeTimeoutMs;     // Catch: Throwable -> L11
        if (r45 <= 0) goto L21;
        r16.experimentalSetForegroundModeTimeoutMs(r45);     // Catch: Throwable -> L11
    L21:
        if (Build.VERSION.SDK_INT < 31) goto L23;
        Api31.registerMediaMetricsListener(this.applicationContext, this, r42.usePlatformDiagnostics, r53);     // Catch: Throwable -> L11
    L23:
        BackgroundThreadStateHandler<Integer> r65 = new BackgroundThreadStateHandler(0, r103, r9, r102, new ExoPlayerImpl$$ExternalSyntheticLambda21(this));     // Catch: Throwable -> L11
        this.audioSessionIdState = r65;     // Catch: Throwable -> L11
        r65.runInBackground(new ExoPlayerImpl$$ExternalSyntheticLambda22(this));     // Catch: Throwable -> L11
        AudioBecomingNoisyManager r162 = new AudioBecomingNoisyManager(r42.context, r103, r42.looper, this.componentListener, r102);     // Catch: Throwable -> L11
        Clock r34 = r102;
        this.audioBecomingNoisyManager = r162;     // Catch: Throwable -> L11
        r162.setEnabled(r42.handleAudioBecomingNoisy);     // Catch: Throwable -> L11
        if (r42.suppressPlaybackOnUnsuitableOutput == false) goto L26;
        SuitableOutputChecker r66 = r42.suitableOutputChecker;     // Catch: Throwable -> L11
        this.suitableOutputChecker = r66;     // Catch: Throwable -> L11
        r66.enable(new ExoPlayerImpl$$ExternalSyntheticLambda23(this), this.applicationContext, r9, r103, r34);     // Catch: Throwable -> L11
        r34 = r34;
    L28:
        if (r42.deviceVolumeControlEnabled == false) goto L30;
        Clock r12 = r34;
        StreamVolumeManager r67 = new StreamVolumeManager(r42.context, this.componentListener, this.audioAttributes.getStreamType(), r103, r9, r12);     // Catch: Throwable -> L11
        Looper r82 = r103;
        Clock r104 = r12;
        this.streamVolumeManager = r67;     // Catch: Throwable -> L11
    L31:
        WakeLockManager r2 = new WakeLockManager(r42.context, r82, r104);     // Catch: Throwable -> L11
        this.wakeLockManager = r2;     // Catch: Throwable -> L11
        if (r42.wakeMode == 0) goto L34;
        boolean r46 = true;
    L35:
        r2.setEnabled(r46);     // Catch: Throwable -> L11
        WifiLockManager r22 = new WifiLockManager(r42.context, r82, r104);     // Catch: Throwable -> L11
        this.wifiLockManager = r22;     // Catch: Throwable -> L11
        if (r42.wakeMode != 2) goto L38;
        boolean r14 = true;
    L39:
        r22.setEnabled(r14);     // Catch: Throwable -> L11
        this.deviceInfo = DeviceInfo.UNKNOWN;     // Catch: Throwable -> L11
        this.videoSize = VideoSize.UNKNOWN;     // Catch: Throwable -> L11
        this.surfaceSize = Size.UNKNOWN;     // Catch: Throwable -> L11
        r16.setScrubbingModeParameters(this.scrubbingModeParameters);     // Catch: Throwable -> L11
        r16.setAudioAttributes(this.audioAttributes, r42.handleAudioFocus);     // Catch: Throwable -> L11
        sendRendererMessage(1, 3, this.audioAttributes);     // Catch: Throwable -> L11
        sendRendererMessage(2, 4, Integer.valueOf(this.videoScalingMode));     // Catch: Throwable -> L11
        sendRendererMessage(2, 5, Integer.valueOf(this.videoChangeFrameRateStrategy));     // Catch: Throwable -> L11
        sendRendererMessage(1, 9, Boolean.valueOf(this.skipSilenceEnabled));     // Catch: Throwable -> L11
        sendRendererMessage(6, 8, this.frameMetadataListener);     // Catch: Throwable -> L11
        sendRendererMessage(16, Integer.valueOf(this.priority));     // Catch: Throwable -> L11
        this.constructorFinished.open();
        return;
    L38:
        r14 = false;
        goto L39
    L34:
        r46 = false;
        goto L35
    L30:
        r82 = r103;
        r104 = r34;
        this.streamVolumeManager = null;     // Catch: Throwable -> L11
        goto L31
    L26:
        this.suitableOutputChecker = null;     // Catch: Throwable -> L11
        goto L28
    L16:
        r52 = r43;
        goto L17
    L6:
        r62 = false;
    L11:
        th = move-exception;
        this.constructorFinished.open();
        throw th;
    }

    public static /* synthetic */ Format access$1002(ExoPlayerImpl r0, Format r1) {
        r0.audioFormat = r1;
        return r1;
    }

    public static /* synthetic */ boolean access$1100(ExoPlayerImpl r0) {
        return r0.skipSilenceEnabled;
    }

    public static /* synthetic */ boolean access$1102(ExoPlayerImpl r0, boolean r1) {
        r0.skipSilenceEnabled = r1;
        return r1;
    }

    public static /* synthetic */ BackgroundThreadStateHandler access$1200(ExoPlayerImpl r0) {
        return r0.audioSessionIdState;
    }

    public static /* synthetic */ CueGroup access$1302(ExoPlayerImpl r0, CueGroup r1) {
        r0.currentCueGroup = r1;
        return r1;
    }

    public static /* synthetic */ MediaMetadata access$1400(ExoPlayerImpl r0) {
        return r0.staticAndDynamicMediaMetadata;
    }

    public static /* synthetic */ MediaMetadata access$1402(ExoPlayerImpl r0, MediaMetadata r1) {
        r0.staticAndDynamicMediaMetadata = r1;
        return r1;
    }

    public static /* synthetic */ MediaMetadata access$1500(ExoPlayerImpl r0) {
        return r0.buildUpdatedMediaMetadata();
    }

    public static /* synthetic */ MediaMetadata access$1600(ExoPlayerImpl r0) {
        return r0.mediaMetadata;
    }

    public static /* synthetic */ MediaMetadata access$1602(ExoPlayerImpl r0, MediaMetadata r1) {
        r0.mediaMetadata = r1;
        return r1;
    }

    public static /* synthetic */ boolean access$1700(ExoPlayerImpl r0) {
        return r0.surfaceHolderSurfaceIsVideoOutput;
    }

    public static /* synthetic */ void access$1800(ExoPlayerImpl r0, Object r1) {
        r0.setVideoOutputInternal(r1);
    }

    public static /* synthetic */ void access$1900(ExoPlayerImpl r0, int r1, int r2) {
        r0.maybeNotifySurfaceSizeChanged(r1, r2);
    }

    public static /* synthetic */ Executor access$2000(ExoPlayerImpl r0) {
        return r0.workerQueue;
    }

    public static /* synthetic */ void access$2100(ExoPlayerImpl r0, SurfaceTexture r1) {
        r0.setSurfaceTextureInternal(r1);
    }

    public static /* synthetic */ ArrayList access$2200(ExoPlayerImpl r0) {
        return r0.videoListeners;
    }

    public static /* synthetic */ void access$2300(ExoPlayerImpl r0, boolean r1, int r2) {
        r0.updatePlayWhenReady(r1, r2);
    }

    public static /* synthetic */ StreamVolumeManager access$2400(ExoPlayerImpl r0) {
        return r0.streamVolumeManager;
    }

    public static /* synthetic */ DeviceInfo access$2500(StreamVolumeManager r0) {
        return createDeviceInfo(r0);
    }

    public static /* synthetic */ DeviceInfo access$2600(ExoPlayerImpl r0) {
        return r0.deviceInfo;
    }

    public static /* synthetic */ DeviceInfo access$2602(ExoPlayerImpl r0, DeviceInfo r1) {
        r0.deviceInfo = r1;
        return r1;
    }

    public static /* synthetic */ void access$2700(ExoPlayerImpl r0) {
        r0.updateWakeAndWifiLock();
    }

    public static /* synthetic */ DecoderCounters access$302(ExoPlayerImpl r0, DecoderCounters r1) {
        r0.videoDecoderCounters = r1;
        return r1;
    }

    public static /* synthetic */ AnalyticsCollector access$400(ExoPlayerImpl r0) {
        return r0.analyticsCollector;
    }

    public static /* synthetic */ Format access$502(ExoPlayerImpl r0, Format r1) {
        r0.videoFormat = r1;
        return r1;
    }

    public static /* synthetic */ VideoSize access$602(ExoPlayerImpl r0, VideoSize r1) {
        r0.videoSize = r1;
        return r1;
    }

    public static /* synthetic */ ListenerSet access$700(ExoPlayerImpl r0) {
        return r0.listeners;
    }

    public static /* synthetic */ Object access$800(ExoPlayerImpl r0) {
        return r0.videoOutput;
    }

    public static /* synthetic */ DecoderCounters access$902(ExoPlayerImpl r0, DecoderCounters r1) {
        r0.audioDecoderCounters = r1;
        return r1;
    }

    private static TrackSelectionParameters addDisabledTrackTypes(TrackSelectionParameters r2, ImmutableSet<Integer> r3) {
        TrackSelectionParameters.Builder r22 = r2.buildUpon();
        UnmodifiableIterator<Integer> r32 = r3.iterator();
    L4:
        if (r32.hasNext() == false) goto L7;
        r22.setTrackTypeDisabled(r32.next().intValue(), true);
        goto L4
    L7:
        return r22.build();
    }

    private List<MediaSourceList.MediaSourceHolder> addMediaSourceHolders(int r8, List<MediaSource> r9) {
        ArrayList r0 = new ArrayList();
        int r1 = 0;
    L4:
        if (r1 >= r9.size()) goto L6;
        MediaSourceList.MediaSourceHolder r2 = new MediaSourceList.MediaSourceHolder(r9.get(r1), this.useLazyPreparation);
        r0.add(r2);
        this.mediaSourceHolderSnapshots.add(r1 + r8, new MediaSourceHolderSnapshot(r2.uid, r2.mediaSource));
        r1 = r1 + 1;
        goto L4
    L6:
        this.shuffleOrder = this.shuffleOrder.cloneAndInsert(r8, r0.size());
        return r0;
    }

    private PlaybackInfo addMediaSourcesInternal(PlaybackInfo r7, int r8, List<MediaSource> r9) {
        Timeline r1 = r7.timeline;
        this.pendingOperationAcks++;
        List<MediaSourceList.MediaSourceHolder> r92 = addMediaSourceHolders(r8, r9);
        Timeline r2 = createMaskingTimeline();
        PlaybackInfo r6 = maskTimelineAndPosition(r7, r2, getPeriodPositionUsAfterTimelineChanged(r1, r2, getCurrentWindowIndexInternal(r7), getContentPositionInternal(r7)));
        this.internalPlayer.addMediaSources(r8, r92, this.shuffleOrder);
        return r6;
    }

    private MediaMetadata buildUpdatedMediaMetadata() {
        Timeline r0 = getCurrentTimeline();
        if (r0.isEmpty() == true) goto L5;
        MediaItem r02 = r0.getWindow(getCurrentMediaItemIndex(), this.window).mediaItem;
        return this.staticAndDynamicMediaMetadata.buildUpon().populate(r02.mediaMetadata).build();
    L5:
        return this.staticAndDynamicMediaMetadata;
    }

    private boolean canUpdateMediaSourcesWithMediaItems(int r5, int r6, List<MediaItem> r7) {
        if ((r6 - r5) == r7.size()) goto L5;
        return false;
    L5:
        int r0 = r5;
    L6:
        if (r0 >= r6) goto L11;
        if (MediaSourceHolderSnapshot.access$200(this.mediaSourceHolderSnapshots.get(r0)).canUpdateMediaItem(r7.get(r0 - r5)) == false) goto L9;
        r0 = r0 + 1;
        goto L6
    L9:
        return false;
    L11:
        return true;
    }

    private int computePlaybackSuppressionReason(boolean r2) {
        if (this.scrubbingModeEnabled == false) goto L6;
        return 4;
    L6:
        SuitableOutputChecker r0 = this.suitableOutputChecker;
        if (r0 == null) goto L13;
        if (r0.isSelectedOutputSuitableForPlayback() == true) goto L13;
        return 3;
    L13:
        if (this.playbackInfo.playbackSuppressionReason != 1) goto L16;
        if (r2 == true) goto L18;
        return 1;
    L18:
        return 0;
    L16:
        return 0;
    }

    private static DeviceInfo createDeviceInfo(StreamVolumeManager r3) {
        int r1 = 0;
        DeviceInfo.Builder r0 = new DeviceInfo.Builder(0);
        if (r3 == null) goto L5;
        int r2 = r3.getMinVolume();
    L6:
        DeviceInfo.Builder r02 = r0.setMinVolume(r2);
        if (r3 == null) goto L10;
        r1 = r3.getMaxVolume();
    L10:
        return r02.setMaxVolume(r1).build();
    L5:
        r2 = 0;
        goto L6
    }

    private Timeline createMaskingTimeline() {
        return new PlaylistTimeline(this.mediaSourceHolderSnapshots, this.shuffleOrder);
    }

    private List<MediaSource> createMediaSources(List<MediaItem> r5) {
        ArrayList r0 = new ArrayList();
        int r1 = 0;
    L4:
        if (r1 >= r5.size()) goto L6;
        r0.add(this.mediaSourceFactory.createMediaSource(r5.get(r1)));
        r1 = r1 + 1;
        goto L4
    L6:
        return r0;
    }

    private PlayerMessage createMessageInternal(PlayerMessage.Target r9) {
        int r0 = getCurrentWindowIndexInternal(this.playbackInfo);
        ExoPlayerImplInternal r2 = this.internalPlayer;
        Timeline r4 = this.playbackInfo.timeline;
        if (r0 != (-1)) goto L6;
        r0 = 0;
    L6:
        return new PlayerMessage(r2, r9, r4, r0, this.clock, r2.getPlaybackLooper());
    }

    private Pair<Boolean, Integer> evaluateMediaItemTransitionReason(PlaybackInfo r7, PlaybackInfo r8, boolean r9, int r10, boolean r11, boolean r12) {
        Timeline r1 = r8.timeline;
        Timeline r2 = r7.timeline;
        if (r2.isEmpty() == true) goto L5;
    L8:
        int r5 = 3;
        if (r2.isEmpty() == r1.isEmpty()) goto L13;
        return new Pair(Boolean.TRUE, 3);
    L13:
        if (r1.getWindow(r1.getPeriodByUid(r8.periodId.periodUid, this.period).windowIndex, this.window).uid.equals(r2.getWindow(r2.getPeriodByUid(r7.periodId.periodUid, this.period).windowIndex, this.window).uid) == true) goto L25;
        if (r9 == false) goto L17;
        if (r10 != 0) goto L17;
        r5 = 1;
    L22:
        return new Pair(Boolean.TRUE, Integer.valueOf(r5));
    L17:
        if (r9 == false) goto L20;
        if (r10 != 1) goto L20;
        r5 = 2;
    L20:
        if (r11 == true) goto L22;
        MethodWriter$$ExternalSyntheticBUOutline0.m();
        return null;
    L25:
        if (r9 == false) goto L31;
        if (r10 != 0) goto L31;
        if (r8.periodId.windowSequenceNumber >= r7.periodId.windowSequenceNumber) goto L31;
        return new Pair(Boolean.TRUE, 0);
    L31:
        if (r9 == false) goto L37;
        if (r10 != 1) goto L37;
        if (r12 == false) goto L37;
        return new Pair(Boolean.TRUE, 2);
    L37:
        return new Pair(Boolean.FALSE, -1);
    L5:
        if (r1.isEmpty() == false) goto L8;
        return new Pair(Boolean.FALSE, -1);
    }

    private long getContentPositionInternal(PlaybackInfo r5) {
        if (r5.periodId.isAd() == false) goto L11;
        r5.timeline.getPeriodByUid(r5.periodId.periodUid, this.period);
        if (r5.requestedContentPositionUs != (-9223372036854775807L)) goto L9;
        return r5.timeline.getWindow(getCurrentWindowIndexInternal(r5), this.window).getDefaultPositionMs();
    L9:
        return this.period.getPositionInWindowMs() + Util.usToMs(r5.requestedContentPositionUs);
    L11:
        return Util.usToMs(getCurrentPositionUsInternal(r5));
    }

    private long getCurrentPositionUsInternal(PlaybackInfo r4) {
        if (r4.timeline.isEmpty() == false) goto L7;
        return Util.msToUs(this.maskingWindowPositionMs);
    L7:
        if (r4.sleepingForOffload == false) goto L9;
        long r0 = r4.getEstimatedPositionUs();
    L11:
        if (r4.periodId.isAd() == false) goto L14;
        return r0;
    L14:
        return periodPositionUsToWindowPositionUs(r4.timeline, r4.periodId, r0);
    L9:
        r0 = r4.positionUs;
        goto L11
    }

    private int getCurrentWindowIndexInternal(PlaybackInfo r2) {
        if (r2.timeline.isEmpty() == false) goto L7;
        return this.maskingWindowIndex;
    L7:
        return r2.timeline.getPeriodByUid(r2.periodId.periodUid, this.period).windowIndex;
    }

    private Pair<Object, Long> getPeriodPositionUsAfterTimelineChanged(Timeline r18, Timeline r19, int r20, long r21) {
        long r8 = -9223372036854775807L;
        int r10 = -1;
        if (r18.isEmpty() == true) goto L17;
        if (r19.isEmpty() == true) goto L17;
        Pair<Object, Long> r1 = r18.getPeriodPositionUs(this.window, this.period, r20, Util.msToUs(r21));
        Object r5 = ((Pair) Util.castNonNull(r1)).first;
        if (r19.getIndexOfPeriod(r5) == (-1)) goto L10;
        return r1;
    L10:
        int r12 = ExoPlayerImplInternal.resolveSubsequentPeriod(this.window, this.period, this.repeatMode, this.shuffleModeEnabled, r5, r18, r19);
        if (r12 == (-1)) goto L15;
        return maskWindowPositionMsOrGetPeriodPositionUs(r19, r12, r19.getWindow(r12, this.window).getDefaultPositionMs());
    L15:
        return maskWindowPositionMsOrGetPeriodPositionUs(r19, -1, -9223372036854775807L);
    L17:
        if (r18.isEmpty() == false) goto L19;
    L21:
        boolean r13 = false;
    L22:
        if (r13 == true) goto L25;
        r10 = r20;
    L25:
        if (r13 == true) goto L29;
        r8 = r21;
    L29:
        return maskWindowPositionMsOrGetPeriodPositionUs(r19, r10, r8);
    L19:
        if (r19.isEmpty() == false) goto L21;
        r13 = true;
        goto L22
    }

    private Player.PositionInfo getPositionInfo(long r13) {
        int r2 = getCurrentMediaItemIndex();
        if (this.playbackInfo.timeline.isEmpty() == true) goto L6;
        PlaybackInfo r0 = this.playbackInfo;
        Object r1 = r0.periodId.periodUid;
        r0.timeline.getPeriodByUid(r1, this.period);
        int r02 = this.playbackInfo.timeline.getIndexOfPeriod(r1);
        Object r3 = this.playbackInfo.timeline.getWindow(r2, this.window).uid;
        Object r4 = r1;
        Object r12 = r3;
        MediaItem r32 = this.window.mediaItem;
    L5:
        int r5 = r02;
        long r6 = Util.usToMs(r13);
        if (this.playbackInfo.periodId.isAd() == false) goto L10;
        long r8 = Util.usToMs(getRequestedContentPositionUs(this.playbackInfo));
    L11:
        MediaSource.MediaPeriodId r122 = this.playbackInfo.periodId;
        return new Player.PositionInfo(r12, r2, r32, r4, r5, r6, r8, r122.adGroupIndex, r122.adIndexInAdGroup);
    L10:
        r8 = r6;
        goto L11
    L6:
        r12 = null;
        r02 = -1;
        r32 = null;
        r4 = null;
        goto L5
    }

    private Player.PositionInfo getPreviousPositionInfo(int r18, PlaybackInfo r19, int r20) {
        Timeline.Period r2 = new Timeline.Period();
        if (r19.timeline.isEmpty() == true) goto L5;
        Object r3 = r19.periodId.periodUid;
        r19.timeline.getPeriodByUid(r3, r2);
        int r5 = r2.windowIndex;
        int r6 = r19.timeline.getIndexOfPeriod(r3);
        Object r7 = r19.timeline.getWindow(r5, this.window).uid;
        MediaItem r8 = this.window.mediaItem;
        Object r9 = r3;
        int r10 = r6;
        Object r62 = r7;
        int r72 = r5;
    L6:
        MediaSource.MediaPeriodId r32 = r19.periodId;
        if (r18 != 0) goto L17;
        boolean r33 = r32.isAd();
        MediaSource.MediaPeriodId r52 = r19.periodId;
        if (r33 == false) goto L12;
        long r22 = r2.getAdDurationUs(r52.adGroupIndex, r52.adIndexInAdGroup);
        long r4 = getRequestedContentPositionUs(r19);
    L20:
        long r11 = Util.usToMs(r22);
        long r13 = Util.usToMs(r4);
        MediaSource.MediaPeriodId r1 = r19.periodId;
        return new Player.PositionInfo(r62, r72, r8, r9, r10, r11, r13, r1.adGroupIndex, r1.adIndexInAdGroup);
    L12:
        if (r52.nextAdGroupIndex == (-1)) goto L15;
        r22 = getRequestedContentPositionUs(this.playbackInfo);
    L14:
        r4 = r22;
        goto L20
    L15:
        r22 = r2.positionInWindowUs + r2.durationUs;
        goto L14
    L17:
        if (r32.isAd() == false) goto L19;
        r22 = r19.positionUs;
        r4 = getRequestedContentPositionUs(r19);
        goto L20
    L19:
        r22 = r2.positionInWindowUs + r19.positionUs;
        goto L14
    L5:
        r72 = r20;
        r62 = null;
        r8 = null;
        r9 = null;
        r10 = -1;
        goto L6
    }

    private static long getRequestedContentPositionUs(PlaybackInfo r6) {
        Timeline.Window r0 = new Timeline.Window();
        Timeline.Period r1 = new Timeline.Period();
        r6.timeline.getPeriodByUid(r6.periodId.periodUid, r1);
        if (r6.requestedContentPositionUs != (-9223372036854775807L)) goto L7;
        return r6.timeline.getWindow(r1.windowIndex, r0).getDefaultPositionUs();
    L7:
        return r1.getPositionInWindowUs() + r6.requestedContentPositionUs;
    }

    private void handlePlaybackInfo(ExoPlayerImplInternal.PlaybackInfoUpdate r13) {
        int r2 = this.pendingOperationAcks - r13.operationAcks;
        this.pendingOperationAcks = r2;
        boolean r4 = true;
        if (r13.positionDiscontinuity == false) goto L5;
        this.pendingDiscontinuityReason = r13.discontinuityReason;
        this.pendingDiscontinuity = true;
    L5:
        if (r2 != 0) goto L43;
        Timeline r22 = r13.playbackInfo.timeline;
        if (this.playbackInfo.timeline.isEmpty() == true) goto L12;
        if (r22.isEmpty() == false) goto L12;
        this.maskingWindowIndex = -1;
        this.maskingWindowPositionMs = 0;
        this.maskingPeriodIndex = 0;
    L12:
        if (r22.isEmpty() == true) goto L21;
        List<Timeline> r3 = ((PlaylistTimeline) r22).getChildTimelines();
        if (r3.size() != this.mediaSourceHolderSnapshots.size()) goto L16;
        boolean r6 = true;
    L17:
        Assertions.checkState(r6);
        int r62 = 0;
    L19:
        if (r62 >= r3.size()) goto L21;
        this.mediaSourceHolderSnapshots.get(r62).updateTimeline(r3.get(r62));
        r62 = r62 + 1;
        goto L19
    L16:
        r6 = false;
    L21:
        long r63 = -9223372036854775807L;
        if (this.pendingDiscontinuity == true) goto L24;
        boolean r32 = false;
    L40:
        this.pendingDiscontinuity = false;
        updatePlaybackInfo(r13.playbackInfo, 1, r32, this.pendingDiscontinuityReason, r63, -1, false);
        return;
    L24:
        if (r13.playbackInfo.periodId.equals(this.playbackInfo.periodId) == true) goto L26;
    L29:
        if (r4 == true) goto L31;
    L38:
        r32 = r4;
        goto L40
    L31:
        if (r22.isEmpty() == false) goto L33;
    L37:
        long r23 = r13.playbackInfo.discontinuityStartPositionUs;
    L36:
        r63 = r23;
        goto L38
    L33:
        if (r13.playbackInfo.periodId.isAd() == true) goto L37;
        PlaybackInfo r33 = r13.playbackInfo;
        r23 = periodPositionUsToWindowPositionUs(r22, r33.periodId, r33.discontinuityStartPositionUs);
        goto L36
    L26:
        if (r13.playbackInfo.discontinuityStartPositionUs != this.playbackInfo.positionUs) goto L29;
        r4 = false;
        goto L29
    }

    private static PlaybackInfo maskPlaybackState(PlaybackInfo r1, int r2) {
        PlaybackInfo r12 = r1.copyWithPlaybackState(r2);
        if (r2 == 1) goto L9;
        if (r2 == 4) goto L9;
        return r12;
    L9:
        return r12.copyWithIsLoading(false);
    }

    private PlaybackInfo maskTimelineAndPosition(PlaybackInfo r22, Timeline r23, Pair<Object, Long> r24) {
        if (r23.isEmpty() == true) goto L7;
        if (r24 != null) goto L7;
        boolean r3 = false;
    L8:
        Assertions.checkArgument(r3);
        Timeline r5 = r22.timeline;
        long r6 = getContentPositionInternal(r22);
        PlaybackInfo r8 = r22.copyWithTimeline(r23);
        if (r23.isEmpty() == false) goto L12;
        MediaSource.MediaPeriodId r9 = PlaybackInfo.getDummyPeriodForEmptyTimeline();
        long r10 = Util.msToUs(this.maskingWindowPositionMs);
        PlaybackInfo r0 = r8.copyWithNewPosition(r9, r10, r10, r10, 0, TrackGroupArray.EMPTY, this.emptyTrackSelectorResult, ImmutableList.of()).copyWithLoadingMediaPeriodId(r9);
        r0.bufferedPositionUs = r0.positionUs;
        return r0;
    L12:
        Object r32 = r8.periodId.periodUid;
        boolean r92 = r32.equals(((Pair) Util.castNonNull(r24)).first);
        if (r92 == true) goto L15;
        MediaSource.MediaPeriodId r102 = new MediaSource.MediaPeriodId(r24.first);
    L16:
        long r11 = ((Long) r24.second).longValue();
        long r62 = Util.msToUs(r6);
        if (r5.isEmpty() == true) goto L19;
        r62 = r62 - r5.getPeriodByUid(r32, this.period).getPositionInWindowUs();
    L19:
        if (r92 == true) goto L21;
    L22:
        MediaSource.MediaPeriodId r93 = r102;
        Assertions.checkState(!r93.isAd());
        if (r92 == true) goto L46;
        TrackGroupArray r2 = TrackGroupArray.EMPTY;
    L45:
        TrackGroupArray r18 = r2;
        if (r92 == true) goto L50;
        TrackSelectorResult r02 = this.emptyTrackSelectorResult;
    L49:
        TrackSelectorResult r19 = r02;
        if (r92 == true) goto L54;
        List<Metadata> r03 = ImmutableList.of();
    L55:
        PlaybackInfo r04 = r8.copyWithNewPosition(r93, r11, r11, r11, 0, r18, r19, r03).copyWithLoadingMediaPeriodId(r93);
        r04.bufferedPositionUs = r11;
        return r04;
    L54:
        r03 = r8.staticMetadata;
        goto L55
    L50:
        r02 = r8.trackSelectorResult;
        goto L49
    L46:
        r2 = r8.trackGroups;
        goto L45
    L21:
        if (r11 < r62) goto L22;
        if (r11 != r62) goto L37;
        int r25 = r23.getIndexOfPeriod(r8.loadingMediaPeriodId.periodUid);
        if (r25 != (-1)) goto L27;
    L30:
        r23.getPeriodByUid(r102.periodUid, this.period);
        boolean r1 = r102.isAd();
        Timeline.Period r05 = this.period;
        if (r1 == false) goto L34;
        long r06 = r05.getAdDurationUs(r102.adGroupIndex, r102.adIndexInAdGroup);
    L33:
        MediaSource.MediaPeriodId r94 = r102;
        PlaybackInfo r26 = r8.copyWithNewPosition(r94, r8.positionUs, r8.positionUs, r8.discontinuityStartPositionUs, r06 - r8.positionUs, r8.trackGroups, r8.trackSelectorResult, r8.staticMetadata).copyWithLoadingMediaPeriodId(r94);
        r26.bufferedPositionUs = r06;
        return r26;
    L34:
        r06 = r05.durationUs;
        goto L33
    L27:
        if (r23.getPeriod(r25, this.period).windowIndex != r23.getPeriodByUid(r102.periodUid, this.period).windowIndex) goto L30;
        return r8;
    L37:
        MediaSource.MediaPeriodId r95 = r102;
        Assertions.checkState(!r95.isAd());
        long r16 = Math.max(0, r8.totalBufferedDurationUs - (r11 - r62));
        long r07 = r8.bufferedPositionUs;
        if (r8.loadingMediaPeriodId.equals(r8.periodId) == false) goto L40;
        r07 = r11 + r16;
    L40:
        PlaybackInfo r27 = r8.copyWithNewPosition(r95, r11, r11, r11, r16, r8.trackGroups, r8.trackSelectorResult, r8.staticMetadata);
        r27.bufferedPositionUs = r07;
        return r27;
    L15:
        r102 = r8.periodId;
    L7:
        r3 = true;
        goto L8
    }

    private Pair<Object, Long> maskWindowPositionMsOrGetPeriodPositionUs(Timeline r7, int r8, long r9) {
        if (r7.isEmpty() == false) goto L10;
        this.maskingWindowIndex = r8;
        if (r9 != (-9223372036854775807L)) goto L7;
        r9 = 0;
    L7:
        this.maskingWindowPositionMs = r9;
        this.maskingPeriodIndex = 0;
        return null;
    L10:
        if (r8 != (-1)) goto L12;
    L15:
        r8 = r7.getFirstWindowIndex(this.shuffleModeEnabled);
        r9 = r7.getWindow(r8, this.window).getDefaultPositionMs();
    L14:
        int r3 = r8;
        return r7.getPeriodPositionUs(this.window, this.period, r3, Util.msToUs(r9));
    L12:
        if (r8 < r7.getWindowCount()) goto L14;
        goto L14
    }

    private void maybeNotifySurfaceSizeChanged(int r4, int r5) {
        if (r4 == this.surfaceSize.getWidth()) goto L5;
    L8:
        this.surfaceSize = new Size(r4, r5);
        Executor r0 = this.workerQueue;
        if (r0 == null) goto L12;
        r0.execute(new ExoPlayerImpl$$ExternalSyntheticLambda15(this, r4, r5));
        return;
    L12:
        this.listeners.sendEvent(24, new ExoPlayerImpl$$ExternalSyntheticLambda16(r4, r5));
        sendRendererMessage(2, 14, new Size(r4, r5));
        return;
    L5:
        if (r5 != this.surfaceSize.getHeight()) goto L8;
    }

    private void maybeUpdatePlaybackSuppressionReason() {
        PlaybackInfo r0 = this.playbackInfo;
        updatePlayWhenReady(r0.playWhenReady, r0.playWhenReadyChangeReason);
    }

    private void onAudioSessionIdChanged(int r3, int r4) {
        verifyApplicationThread();
        sendRendererMessage(1, 10, Integer.valueOf(r4));
        sendRendererMessage(2, 10, Integer.valueOf(r4));
        this.listeners.sendEvent(21, new ExoPlayerImpl$$ExternalSyntheticLambda28(r4));
    }

    private void onSelectedOutputSuitabilityChanged(boolean r2) {
        if (this.playerReleased == true) goto L13;
        if (r2 == true) goto L7;
        maybeUpdatePlaybackSuppressionReason();
        return;
    L7:
        if (this.playbackInfo.playbackSuppressionReason != 3) goto L12;
        maybeUpdatePlaybackSuppressionReason();
        return;
    L12:
        return;
    }

    private long periodPositionUsToWindowPositionUs(Timeline r2, MediaSource.MediaPeriodId r3, long r4) {
        r2.getPeriodByUid(r3.periodUid, this.period);
        return r4 + this.period.getPositionInWindowUs();
    }

    private PlaybackInfo removeMediaItemsInternal(PlaybackInfo r9, int r10, int r11) {
        int r3 = getCurrentWindowIndexInternal(r9);
        long r4 = getContentPositionInternal(r9);
        Timeline r1 = r9.timeline;
        int r6 = this.mediaSourceHolderSnapshots.size();
        this.pendingOperationAcks++;
        removeMediaSourceHolders(r10, r11);
        Timeline r2 = createMaskingTimeline();
        PlaybackInfo r8 = maskTimelineAndPosition(r9, r2, getPeriodPositionUsAfterTimelineChanged(r1, r2, r3, r4));
        int r92 = r8.playbackState;
        if (r92 != 1) goto L5;
    L11:
        this.internalPlayer.removeMediaSources(r10, r11, this.shuffleOrder);
        return r8;
    L5:
        if (r92 == 4) goto L11;
        if (r10 >= r11) goto L11;
        if (r11 != r6) goto L11;
        if (r3 < r8.timeline.getWindowCount()) goto L11;
        r8 = maskPlaybackState(r8, 4);
        goto L11
    }

    private void removeMediaSourceHolders(int r3, int r4) {
        int r0 = r4 - 1;
    L3:
        if (r0 < r3) goto L5;
        this.mediaSourceHolderSnapshots.remove(r0);
        r0 = r0 - 1;
        goto L3
    L5:
        this.shuffleOrder = this.shuffleOrder.cloneAndRemove(r3, r4);
    }

    private void removeSurfaceCallbacks() {
        TextureView r0 = this.textureView;
        if (r0 != null) goto L5;
    L9:
        SurfaceHolder r02 = this.surfaceHolder;
        if (r02 == null) goto L13;
        r02.removeCallback(this.componentListener);
        this.surfaceHolder = null;
        return;
    L13:
        return;
    L5:
        if (r0.getSurfaceTextureListener() == this.componentListener) goto L7;
        Log.w("ExoPlayerImpl", "SurfaceTextureListener already unset or replaced.");
    L8:
        this.textureView = null;
        goto L9
    L7:
        this.textureView.setSurfaceTextureListener(null);
        goto L8
    }

    private void sendRendererMessage(int r7, int r8, Object r9) {
        Renderer[] r0 = this.renderers;
        int r1 = r0.length;
        int r2 = 0;
        int r3 = 0;
    L4:
        if (r3 >= r1) goto L11;
        Renderer r5 = r0[r3];
        if (r7 != (-1)) goto L8;
    L9:
        createMessageInternal(r5).setType(r8).setPayload(r9).send();
    L10:
        r3 = r3 + 1;
        goto L4
    L8:
        if (r5.getTrackType() != r7) goto L10;
    L11:
        Renderer[] r02 = this.secondaryRenderers;
        int r12 = r02.length;
    L12:
        if (r2 >= r12) goto L20;
        Renderer r32 = r02[r2];
        if (r32 == null) goto L19;
        if (r7 != (-1)) goto L17;
    L18:
        createMessageInternal(r32).setType(r8).setPayload(r9).send();
        goto L19
    L17:
        if (r32.getTrackType() == r7) goto L18;
    L19:
        r2 = r2 + 1;
        goto L12
    }

    private void sendRendererMessage(int r2, Object r3) {
        sendRendererMessage(-1, r2, r3);
    }

    private void setMediaSourcesInternal(List<MediaSource> r15, int r16, long r17, boolean r19) {
        int r1 = r16;
        int r2 = getCurrentWindowIndexInternal(this.playbackInfo);
        long r3 = getCurrentPosition();
        this.pendingOperationAcks++;
        if (this.mediaSourceHolderSnapshots.isEmpty() == true) goto L5;
        removeMediaSourceHolders(0, this.mediaSourceHolderSnapshots.size());
    L5:
        List<MediaSourceList.MediaSourceHolder> r9 = addMediaSourceHolders(0, r15);
        Timeline r5 = createMaskingTimeline();
        if (r5.isEmpty() == true) goto L13;
        if (r1 < r5.getWindowCount()) goto L13;
        throw new IllegalSeekPositionException(r5, r1, r17);
    L13:
        if (r19 == false) goto L16;
        r1 = r5.getFirstWindowIndex(this.shuffleModeEnabled);
        long r22 = -9223372036854775807L;
    L15:
        int r10 = r1;
    L19:
        PlaybackInfo r12 = maskTimelineAndPosition(this.playbackInfo, r5, maskWindowPositionMsOrGetPeriodPositionUs(r5, r10, r22));
        int r4 = r12.playbackState;
        if (r10 == (-1)) goto L29;
        if (r4 == 1) goto L29;
        if (r5.isEmpty() == false) goto L25;
    L28:
        r4 = 4;
        goto L29
    L25:
        if (r10 >= r5.getWindowCount()) goto L28;
        r4 = 2;
    L29:
        PlaybackInfo r13 = maskPlaybackState(r12, r4);
        this.internalPlayer.setMediaSources(r9, r10, Util.msToUs(r22), this.shuffleOrder);
        if (this.playbackInfo.periodId.periodUid.equals(r13.periodId.periodUid) == false) goto L32;
    L34:
        boolean r32 = false;
    L35:
        updatePlaybackInfo(r13, 0, r32, 4, getCurrentPositionUsInternal(r13), -1, false);
        return;
    L32:
        if (this.playbackInfo.timeline.isEmpty() == true) goto L34;
        r32 = true;
        goto L35
    L16:
        if (r1 != (-1)) goto L18;
        r10 = r2;
        r22 = r3;
        goto L19
    L18:
        r22 = r17;
        goto L15
    }

    private void setSurfaceTextureInternal(SurfaceTexture r2) {
        Surface r0 = new Surface(r2);
        setVideoOutputInternal(r0);
        this.ownedSurface = r0;
    }

    private void setVideoOutputInternal(Object r5) {
        Object r0 = this.videoOutput;
        if (r0 == null) goto L6;
        if (r0 == r5) goto L6;
        boolean r02 = true;
    L7:
        if (r02 == false) goto L9;
        long r1 = this.detachSurfaceTimeoutMs;
    L10:
        boolean r12 = this.internalPlayer.setVideoOutput(r5, r1);
        if (r02 == false) goto L16;
        Object r03 = this.videoOutput;
        Surface r2 = this.ownedSurface;
        if (r03 != r2) goto L16;
        r2.release();     // Catch: Throwable -> L20
    L15:
        this.ownedSurface = null;
    L16:
        this.videoOutput = r5;
        if (r12 == true) goto L23;
        stopInternal(ExoPlaybackException.createForUnexpected(new ExoTimeoutException(3), 1003));
        return;
    L23:
        return;
    L9:
        r1 = -9223372036854775807L;
    L6:
        r02 = false;
        goto L7
    }

    private void stopInternal(ExoPlaybackException r12) {
        PlaybackInfo r0 = this.playbackInfo;
        PlaybackInfo r02 = r0.copyWithLoadingMediaPeriodId(r0.periodId);
        r02.bufferedPositionUs = r02.positionUs;
        r02.totalBufferedDurationUs = 0;
        PlaybackInfo r03 = maskPlaybackState(r02, 1);
        if (r12 == null) goto L5;
        r03 = r03.copyWithPlaybackError(r12);
    L5:
        this.pendingOperationAcks++;
        this.internalPlayer.stop();
        updatePlaybackInfo(r03, 0, false, 5, -9223372036854775807L, -1, false);
    }

    private void updateAvailableCommands() {
        Player.Commands r0 = this.availableCommands;
        Player.Commands r1 = Util.getAvailableCommands(this.wrappingPlayer, this.permanentAvailableCommands);
        this.availableCommands = r1;
        if (r1.equals(r0) == true) goto L6;
        this.listeners.queueEvent(13, new ExoPlayerImpl$$ExternalSyntheticLambda27(this));
        return;
    }

    private void updateMediaSourcesWithMediaItems(int r10, int r11, List<MediaItem> r12) {
        this.pendingOperationAcks++;
        this.internalPlayer.updateMediaSourcesWithMediaItems(r10, r11, r12);
        int r0 = r10;
    L3:
        if (r0 >= r11) goto L5;
        MediaSourceHolderSnapshot r1 = this.mediaSourceHolderSnapshots.get(r0);
        r1.updateTimeline(new TimelineWithUpdatedMediaItem(r1.getTimeline(), r12.get(r0 - r10)));
        r0 = r0 + 1;
        goto L3
    L5:
        updatePlaybackInfo(this.playbackInfo.copyWithTimeline(createMaskingTimeline()), 0, false, 4, -9223372036854775807L, -1, false);
    }

    private void updatePlayWhenReady(boolean r12, int r13) {
        int r0 = computePlaybackSuppressionReason(r12);
        PlaybackInfo r1 = this.playbackInfo;
        if (r1.playWhenReady == r12) goto L5;
    L9:
        this.pendingOperationAcks++;
        if (r1.sleepingForOffload == false) goto L12;
        r1 = r1.copyWithEstimatedPosition();
    L12:
        PlaybackInfo r3 = r1.copyWithPlayWhenReady(r12, r13, r0);
        this.internalPlayer.setPlayWhenReady(r12, r13, r0);
        updatePlaybackInfo(r3, 0, false, 5, -9223372036854775807L, -1, false);
        return;
    L5:
        if (r1.playbackSuppressionReason != r0) goto L9;
        if (r1.playWhenReadyChangeReason != r13) goto L9;
    }

    private void updatePlaybackInfo(PlaybackInfo r17, int r18, boolean r19, int r20, long r21, int r23, boolean r24) {
        PlaybackInfo r2 = this.playbackInfo;
        this.playbackInfo = r17;
        boolean r7 = r2.timeline.equals(r17.timeline);
        Pair<Boolean, Integer> r5 = evaluateMediaItemTransitionReason(r17, r2, r19, r20, !r7, r24);
        boolean r3 = ((Boolean) r5.first).booleanValue();
        int r52 = ((Integer) r5.second).intValue();
        MediaItem r6 = null;
        if (r3 == true) goto L5;
    L8:
        if (r3 == false) goto L10;
    L11:
        this.staticAndDynamicMediaMetadata = this.staticAndDynamicMediaMetadata.buildUpon().populateFromMetadata(r17.staticMetadata).build();
    L12:
        MediaMetadata r8 = buildUpdatedMediaMetadata();
        boolean r9 = r8.equals(this.mediaMetadata);
        this.mediaMetadata = r8;
        if (r2.playWhenReady == r17.playWhenReady) goto L15;
        boolean r82 = true;
    L17:
        if (r2.playbackState == r17.playbackState) goto L19;
        boolean r10 = true;
    L20:
        if (r10 == true) goto L22;
        if (r82 == true) goto L22;
    L23:
        boolean r13 = r2.isLoading;
        boolean r14 = r17.isLoading;
        if (r13 == r14) goto L26;
        boolean r132 = true;
    L27:
        if (r132 == false) goto L29;
        updatePriorityTaskManagerForIsLoadingChange(r14);
    L29:
        if (r7 == true) goto L31;
        this.listeners.queueEvent(0, new ExoPlayerImpl$$ExternalSyntheticLambda0(r17, r18));
    L31:
        if (r19 == false) goto L33;
        this.listeners.queueEvent(11, new ExoPlayerImpl$$ExternalSyntheticLambda5(r20, getPreviousPositionInfo(r20, r2, r23), getPositionInfo(r21)));
    L33:
        if (r3 == false) goto L36;
        this.listeners.queueEvent(1, new ExoPlayerImpl$$ExternalSyntheticLambda6(r6, r52));
    L36:
        if (r2.playbackError == r17.playbackError) goto L40;
        this.listeners.queueEvent(10, new ExoPlayerImpl$$ExternalSyntheticLambda7(r17));
        if (r17.playbackError == null) goto L40;
        this.listeners.queueEvent(10, new ExoPlayerImpl$$ExternalSyntheticLambda8(r17));
    L40:
        TrackSelectorResult r32 = r2.trackSelectorResult;
        TrackSelectorResult r4 = r17.trackSelectorResult;
        if (r32 == r4) goto L43;
        this.trackSelector.onSelectionActivated(r4.info);
        this.listeners.queueEvent(2, new ExoPlayerImpl$$ExternalSyntheticLambda9(r17));
    L43:
        if (r9 == true) goto L45;
        this.listeners.queueEvent(14, new ExoPlayerImpl$$ExternalSyntheticLambda10(this.mediaMetadata));
    L45:
        if (r132 == false) goto L47;
        this.listeners.queueEvent(3, new ExoPlayerImpl$$ExternalSyntheticLambda11(r17));
    L47:
        if (r10 == true) goto L49;
        if (r82 == true) goto L49;
    L50:
        if (r10 == false) goto L52;
        this.listeners.queueEvent(4, new ExoPlayerImpl$$ExternalSyntheticLambda13(r17));
    L52:
        if (r82 == false) goto L54;
    L55:
        this.listeners.queueEvent(5, new ExoPlayerImpl$$ExternalSyntheticLambda1(r17));
    L57:
        if (r2.playbackSuppressionReason == r17.playbackSuppressionReason) goto L60;
        this.listeners.queueEvent(6, new ExoPlayerImpl$$ExternalSyntheticLambda2(r17));
    L60:
        if (r2.isPlaying() == r17.isPlaying()) goto L63;
        this.listeners.queueEvent(7, new ExoPlayerImpl$$ExternalSyntheticLambda3(r17));
    L63:
        if (r2.playbackParameters.equals(r17.playbackParameters) == true) goto L65;
        this.listeners.queueEvent(12, new ExoPlayerImpl$$ExternalSyntheticLambda4(r17));
    L65:
        updateAvailableCommands();
        this.listeners.flushEvents();
        if (r2.sleepingForOffload == r17.sleepingForOffload) goto L71;
        Iterator<ExoPlayer.AudioOffloadListener> r0 = this.audioOffloadListeners.iterator();
    L69:
        if (r0.hasNext() == false) goto L73;
        r0.next().onSleepingForOffloadChanged(r17.sleepingForOffload);
        goto L69
    L73:
        return;
    L71:
        return;
    L54:
        if (r2.playWhenReadyChangeReason == r17.playWhenReadyChangeReason) goto L57;
    L49:
        this.listeners.queueEvent(-1, new ExoPlayerImpl$$ExternalSyntheticLambda12(r17));
        goto L50
    L26:
        r132 = false;
    L22:
        updateWakeAndWifiLock();
        goto L23
    L19:
        r10 = false;
        goto L20
    L15:
        r82 = false;
        goto L17
    L10:
        if (r2.staticMetadata.equals(r17.staticMetadata) == true) goto L12;
    L5:
        if (r17.timeline.isEmpty() == true) goto L7;
        r6 = r17.timeline.getWindow(r17.timeline.getPeriodByUid(r17.periodId.periodUid, this.period).windowIndex, this.window).mediaItem;
    L7:
        this.staticAndDynamicMediaMetadata = MediaMetadata.EMPTY;
        goto L8
    }

    private void updatePriorityTaskManagerForIsLoadingChange(boolean r1) {
    }

    private void updateWakeAndWifiLock() {
        int r0 = getPlaybackState();
        boolean r1 = false;
        if (r0 != 1) goto L5;
    L19:
        this.wakeLockManager.setStayAwake(false);
        this.wifiLockManager.setStayAwake(false);
        return;
    L5:
        if (r0 != 2) goto L7;
    L13:
        boolean r02 = isSleepingForOffload();
        WakeLockManager r3 = this.wakeLockManager;
        if (getPlayWhenReady() == false) goto L17;
        if (r02 == true) goto L17;
        r1 = true;
    L17:
        r3.setStayAwake(r1);
        this.wifiLockManager.setStayAwake(getPlayWhenReady());
        return;
    L7:
        if (r0 == 3) goto L13;
        if (r0 == 4) goto L19;
        MethodWriter$$ExternalSyntheticBUOutline0.m();
    }

    private void verifyApplicationThread() {
        this.constructorFinished.blockUninterruptible();
        if (Thread.currentThread() == getApplicationLooper().getThread()) goto L14;
        String r0 = Util.formatInvariant("Player is accessed on the wrong thread.\nCurrent thread: '%s'\nExpected thread: '%s'\nSee https://developer.android.com/guide/topics/media/issues/player-accessed-on-wrong-thread", new Object[]{Thread.currentThread().getName(), getApplicationLooper().getThread().getName()});
        if (this.throwsWhenUsingWrongThread == false) goto L7;
        Segment$$ExternalSyntheticBUOutline1.m(r0);
        return;
    L7:
        if (this.hasNotifiedFullWrongThreadWarning == false) goto L9;
        IllegalStateException r1 = null;
    L10:
        Log.w("ExoPlayerImpl", r0, r1);
        this.hasNotifiedFullWrongThreadWarning = true;
        return;
    L9:
        r1 = new IllegalStateException();
        goto L10
    }

    @Override // androidx.media3.exoplayer.ExoPlayer
    public void addAnalyticsListener(AnalyticsListener r1) {
        this.analyticsCollector.addListener((AnalyticsListener) Assertions.checkNotNull(r1));
    }

    public void addAudioOffloadListener(ExoPlayer.AudioOffloadListener r1) {
        this.audioOffloadListeners.add(r1);
    }

    @Override // androidx.media3.common.Player
    public void addListener(Player.Listener r1) {
        this.listeners.add((Player.Listener) Assertions.checkNotNull(r1));
    }

    @Override // androidx.media3.common.Player
    public void addMediaItems(int r1, List<MediaItem> r2) {
        verifyApplicationThread();
        addMediaSources(r1, createMediaSources(r2));
    }

    public void addMediaSources(int r11, List<MediaSource> r12) {
        verifyApplicationThread();
        boolean r0 = false;
        if (r11 < 0) goto L5;
        boolean r2 = true;
    L6:
        Assertions.checkArgument(r2);
        int r112 = Math.min(r11, this.mediaSourceHolderSnapshots.size());
        if (this.mediaSourceHolderSnapshots.isEmpty() == true) goto L9;
        updatePlaybackInfo(addMediaSourcesInternal(this.playbackInfo, r112, r12), 0, false, 5, -9223372036854775807L, -1, false);
        return;
    L9:
        if (this.maskingWindowIndex != (-1)) goto L11;
        r0 = true;
    L11:
        setMediaSources(r12, r0);
        return;
    L5:
        r2 = false;
        goto L6
    }

    @Override // androidx.media3.exoplayer.ExoPlayer
    public void addVideoListener(VideoListener r1) {
        this.videoListeners.add(r1);
    }

    public void clearVideoSurface() {
        verifyApplicationThread();
        removeSurfaceCallbacks();
        setVideoOutputInternal(null);
        maybeNotifySurfaceSizeChanged(0, 0);
    }

    @Override // androidx.media3.common.Player
    public void clearVideoTextureView(TextureView r2) {
        verifyApplicationThread();
        if (r2 != null) goto L5;
        return;
    L5:
        if (r2 != this.textureView) goto L9;
        clearVideoSurface();
        return;
    }

    @Override // androidx.media3.common.Player
    @Deprecated
    public void decreaseDeviceVolume() {
        verifyApplicationThread();
        StreamVolumeManager r1 = this.streamVolumeManager;
        if (r1 == null) goto L6;
        r1.decreaseVolume(1);
        return;
    }

    @Override // androidx.media3.common.Player
    public void decreaseDeviceVolume(int r1) {
        verifyApplicationThread();
        StreamVolumeManager r0 = this.streamVolumeManager;
        if (r0 == null) goto L6;
        r0.decreaseVolume(r1);
        return;
    }

    @Override // androidx.media3.common.Player
    public Looper getApplicationLooper() {
        return this.applicationLooper;
    }

    @Override // androidx.media3.common.Player
    public AudioAttributes getAudioAttributes() {
        verifyApplicationThread();
        return this.audioAttributes;
    }

    @Override // androidx.media3.common.Player
    public Player.Commands getAvailableCommands() {
        verifyApplicationThread();
        return this.availableCommands;
    }

    @Override // androidx.media3.common.Player
    public long getBufferedPosition() {
        verifyApplicationThread();
        if (isPlayingAd() == false) goto L11;
        PlaybackInfo r0 = this.playbackInfo;
        if (r0.loadingMediaPeriodId.equals(r0.periodId) == false) goto L9;
        return Util.usToMs(this.playbackInfo.bufferedPositionUs);
    L9:
        return getDuration();
    L11:
        return getContentBufferedPosition();
    }

    public Clock getClock() {
        return this.clock;
    }

    @Override // androidx.media3.common.Player
    public long getContentBufferedPosition() {
        verifyApplicationThread();
        if (this.playbackInfo.timeline.isEmpty() == true) goto L5;
        PlaybackInfo r0 = this.playbackInfo;
        if (r0.loadingMediaPeriodId.windowSequenceNumber != r0.periodId.windowSequenceNumber) goto L9;
        long r02 = r0.bufferedPositionUs;
        if (this.playbackInfo.loadingMediaPeriodId.isAd() == false) goto L16;
        PlaybackInfo r03 = this.playbackInfo;
        Timeline.Period r04 = r03.timeline.getPeriodByUid(r03.loadingMediaPeriodId.periodUid, this.period);
        long r1 = r04.getAdGroupTimeUs(this.playbackInfo.loadingMediaPeriodId.adGroupIndex);
        if (r1 != Long.MIN_VALUE) goto L15;
        r02 = r04.durationUs;
        goto L16
    L15:
        r02 = r1;
    L16:
        PlaybackInfo r2 = this.playbackInfo;
        return Util.usToMs(periodPositionUsToWindowPositionUs(r2.timeline, r2.loadingMediaPeriodId, r02));
    L9:
        return r0.timeline.getWindow(getCurrentMediaItemIndex(), this.window).getDurationMs();
    L5:
        return this.maskingWindowPositionMs;
    }

    @Override // androidx.media3.common.Player
    public long getContentPosition() {
        verifyApplicationThread();
        return getContentPositionInternal(this.playbackInfo);
    }

    @Override // androidx.media3.common.Player
    public int getCurrentAdGroupIndex() {
        verifyApplicationThread();
        if (isPlayingAd() == true) goto L5;
        return -1;
    L5:
        return this.playbackInfo.periodId.adGroupIndex;
    }

    @Override // androidx.media3.common.Player
    public int getCurrentAdIndexInAdGroup() {
        verifyApplicationThread();
        if (isPlayingAd() == true) goto L5;
        return -1;
    L5:
        return this.playbackInfo.periodId.adIndexInAdGroup;
    }

    @Override // androidx.media3.common.Player
    public CueGroup getCurrentCues() {
        verifyApplicationThread();
        return this.currentCueGroup;
    }

    @Override // androidx.media3.common.Player
    public int getCurrentMediaItemIndex() {
        verifyApplicationThread();
        int r1 = getCurrentWindowIndexInternal(this.playbackInfo);
        if (r1 != (-1)) goto L6;
        return 0;
    L6:
        return r1;
    }

    @Override // androidx.media3.common.Player
    public int getCurrentPeriodIndex() {
        verifyApplicationThread();
        if (this.playbackInfo.timeline.isEmpty() == true) goto L5;
        PlaybackInfo r1 = this.playbackInfo;
        return r1.timeline.getIndexOfPeriod(r1.periodId.periodUid);
    L5:
        return this.maskingPeriodIndex;
    }

    @Override // androidx.media3.common.Player
    public long getCurrentPosition() {
        verifyApplicationThread();
        return Util.usToMs(getCurrentPositionUsInternal(this.playbackInfo));
    }

    @Override // androidx.media3.common.Player
    public Timeline getCurrentTimeline() {
        verifyApplicationThread();
        return this.playbackInfo.timeline;
    }

    @Override // androidx.media3.common.Player
    public Tracks getCurrentTracks() {
        verifyApplicationThread();
        return this.playbackInfo.trackSelectorResult.tracks;
    }

    @Override // androidx.media3.common.Player
    public DeviceInfo getDeviceInfo() {
        verifyApplicationThread();
        return this.deviceInfo;
    }

    @Override // androidx.media3.common.Player
    public int getDeviceVolume() {
        verifyApplicationThread();
        StreamVolumeManager r0 = this.streamVolumeManager;
        if (r0 != null) goto L5;
        return 0;
    L5:
        return r0.getVolume();
    }

    @Override // androidx.media3.common.Player
    public long getDuration() {
        verifyApplicationThread();
        if (isPlayingAd() == false) goto L7;
        PlaybackInfo r0 = this.playbackInfo;
        MediaSource.MediaPeriodId r1 = r0.periodId;
        r0.timeline.getPeriodByUid(r1.periodUid, this.period);
        return Util.usToMs(this.period.getAdDurationUs(r1.adGroupIndex, r1.adIndexInAdGroup));
    L7:
        return getContentDuration();
    }

    @Override // androidx.media3.common.Player
    public long getMaxSeekToPreviousPosition() {
        verifyApplicationThread();
        return this.maxSeekToPreviousPositionMs;
    }

    @Override // androidx.media3.common.Player
    public MediaMetadata getMediaMetadata() {
        verifyApplicationThread();
        return this.mediaMetadata;
    }

    @Override // androidx.media3.common.Player
    public boolean getPlayWhenReady() {
        verifyApplicationThread();
        return this.playbackInfo.playWhenReady;
    }

    public Looper getPlaybackLooper() {
        return this.internalPlayer.getPlaybackLooper();
    }

    @Override // androidx.media3.common.Player
    public PlaybackParameters getPlaybackParameters() {
        verifyApplicationThread();
        return this.playbackInfo.playbackParameters;
    }

    @Override // androidx.media3.common.Player
    public int getPlaybackState() {
        verifyApplicationThread();
        return this.playbackInfo.playbackState;
    }

    @Override // androidx.media3.common.Player
    public int getPlaybackSuppressionReason() {
        verifyApplicationThread();
        return this.playbackInfo.playbackSuppressionReason;
    }

    @Override // androidx.media3.common.Player
    public /* bridge */ /* synthetic */ PlaybackException getPlayerError() {
        return getPlayerError();
    }

    @Override // androidx.media3.common.Player
    public ExoPlaybackException getPlayerError() {
        verifyApplicationThread();
        return this.playbackInfo.playbackError;
    }

    @Override // androidx.media3.common.Player
    public MediaMetadata getPlaylistMetadata() {
        verifyApplicationThread();
        return this.playlistMetadata;
    }

    @Override // androidx.media3.exoplayer.ExoPlayer
    public Renderer getRenderer(int r1) {
        verifyApplicationThread();
        return this.renderers[r1];
    }

    @Override // androidx.media3.common.Player
    public int getRepeatMode() {
        verifyApplicationThread();
        return this.repeatMode;
    }

    @Override // androidx.media3.common.Player
    public long getSeekBackIncrement() {
        verifyApplicationThread();
        return this.seekBackIncrementMs;
    }

    @Override // androidx.media3.common.Player
    public long getSeekForwardIncrement() {
        verifyApplicationThread();
        return this.seekForwardIncrementMs;
    }

    @Override // androidx.media3.common.Player
    public boolean getShuffleModeEnabled() {
        verifyApplicationThread();
        return this.shuffleModeEnabled;
    }

    @Override // androidx.media3.common.Player
    public long getTotalBufferedDuration() {
        verifyApplicationThread();
        return Util.usToMs(this.playbackInfo.totalBufferedDurationUs);
    }

    @Override // androidx.media3.common.Player
    public TrackSelectionParameters getTrackSelectionParameters() {
        verifyApplicationThread();
        TrackSelectionParameters r0 = this.trackSelector.getParameters();
        if (this.scrubbingModeEnabled == true) goto L5;
        return r0;
    L5:
        return r0.buildUpon().setDisabledTrackTypes(this.disabledTrackTypesWithoutScrubbingMode).build();
    }

    @Override // androidx.media3.exoplayer.ExoPlayer
    public Format getVideoFormat() {
        verifyApplicationThread();
        return this.videoFormat;
    }

    @Override // androidx.media3.common.Player
    public VideoSize getVideoSize() {
        verifyApplicationThread();
        return this.videoSize;
    }

    @Override // androidx.media3.common.Player
    public float getVolume() {
        verifyApplicationThread();
        return this.volume;
    }

    @Override // androidx.media3.common.Player
    @Deprecated
    public void increaseDeviceVolume() {
        verifyApplicationThread();
        StreamVolumeManager r1 = this.streamVolumeManager;
        if (r1 == null) goto L6;
        r1.increaseVolume(1);
        return;
    }

    @Override // androidx.media3.common.Player
    public void increaseDeviceVolume(int r1) {
        verifyApplicationThread();
        StreamVolumeManager r0 = this.streamVolumeManager;
        if (r0 == null) goto L6;
        r0.increaseVolume(r1);
        return;
    }

    @Override // androidx.media3.common.Player
    public boolean isDeviceMuted() {
        verifyApplicationThread();
        StreamVolumeManager r0 = this.streamVolumeManager;
        if (r0 != null) goto L5;
        return false;
    L5:
        return r0.isMuted();
    }

    @Override // androidx.media3.common.Player
    public boolean isLoading() {
        verifyApplicationThread();
        return this.playbackInfo.isLoading;
    }

    @Override // androidx.media3.common.Player
    public boolean isPlayingAd() {
        verifyApplicationThread();
        return this.playbackInfo.periodId.isAd();
    }

    public boolean isSleepingForOffload() {
        verifyApplicationThread();
        return this.playbackInfo.sleepingForOffload;
    }

    @Override // androidx.media3.common.Player
    public void moveMediaItems(int r11, int r12, int r13) {
        verifyApplicationThread();
        if (r11 < 0) goto L7;
        if (r11 > r12) goto L7;
        if (r13 < 0) goto L7;
        boolean r4 = true;
    L8:
        Assertions.checkArgument(r4);
        int r42 = this.mediaSourceHolderSnapshots.size();
        int r7 = Math.min(r12, r42);
        int r8 = Math.min(r13, r42 - (r7 - r11));
        if (r11 >= r42) goto L15;
        if (r11 == r7) goto L16;
        if (r11 == r8) goto L17;
        Timeline r1 = getCurrentTimeline();
        this.pendingOperationAcks++;
        Util.moveItems(this.mediaSourceHolderSnapshots, r11, r7, r8);
        this.shuffleOrder = this.shuffleOrder.cloneAndMove(r11, r7, r8);
        Timeline r2 = createMaskingTimeline();
        PlaybackInfo r9 = this.playbackInfo;
        PlaybackInfo r14 = maskTimelineAndPosition(r9, r2, getPeriodPositionUsAfterTimelineChanged(r1, r2, getCurrentWindowIndexInternal(r9), getContentPositionInternal(this.playbackInfo)));
        this.internalPlayer.moveMediaSources(r11, r7, r8, this.shuffleOrder);
        updatePlaybackInfo(r14, 0, false, 5, -9223372036854775807L, -1, false);
        return;
    L17:
        return;
    L16:
        return;
    L15:
        return;
    L7:
        r4 = false;
        goto L8
    }

    @Override // androidx.media3.common.Player
    public void prepare() {
        verifyApplicationThread();
        PlaybackInfo r0 = this.playbackInfo;
        if (r0.playbackState == 1) goto L5;
        return;
    L5:
        PlaybackInfo r02 = r0.copyWithPlaybackError(null);
        if (r02.timeline.isEmpty() == false) goto L8;
        int r1 = 4;
    L9:
        PlaybackInfo r4 = maskPlaybackState(r02, r1);
        this.pendingOperationAcks++;
        this.internalPlayer.prepare();
        updatePlaybackInfo(r4, 1, false, 5, -9223372036854775807L, -1, false);
        return;
    L8:
        r1 = 2;
        goto L9
    }

    @Override // androidx.media3.exoplayer.ExoPlayer
    public void release() {
        Log.i("ExoPlayerImpl", "Release " + Integer.toHexString(System.identityHashCode(this)) + " [AndroidXMedia3/1.8.1] [" + Util.DEVICE_DEBUG_INFO + "] [" + MediaLibraryInfo.registeredModules() + "]");
        verifyApplicationThread();
        this.audioBecomingNoisyManager.setEnabled(false);
        StreamVolumeManager r0 = this.streamVolumeManager;
        if (r0 == null) goto L5;
        r0.release();
    L5:
        this.wakeLockManager.setStayAwake(false);
        this.wifiLockManager.setStayAwake(false);
        SuitableOutputChecker r02 = this.suitableOutputChecker;
        if (r02 == null) goto L9;
        r02.disable();
    L9:
        if (this.internalPlayer.release() == true) goto L11;
        this.listeners.sendEvent(10, new ExoPlayerImpl$$ExternalSyntheticLambda17());
    L11:
        this.listeners.release();
        this.playbackInfoUpdateHandler.removeCallbacksAndMessages(null);
        this.bandwidthMeter.removeEventListener(this.analyticsCollector);
        PlaybackInfo r03 = this.playbackInfo;
        if (r03.sleepingForOffload == false) goto L14;
        this.playbackInfo = r03.copyWithEstimatedPosition();
    L14:
        PlaybackInfo r04 = maskPlaybackState(this.playbackInfo, 1);
        this.playbackInfo = r04;
        PlaybackInfo r05 = r04.copyWithLoadingMediaPeriodId(r04.periodId);
        this.playbackInfo = r05;
        r05.bufferedPositionUs = r05.positionUs;
        this.playbackInfo.totalBufferedDurationUs = 0;
        this.analyticsCollector.release();
        removeSurfaceCallbacks();
        Surface r06 = this.ownedSurface;
        if (r06 == null) goto L18;
        r06.release();
        this.ownedSurface = null;
    L18:
        if (this.isPriorityTaskManagerRegistered == true) goto L21;
        this.currentCueGroup = CueGroup.EMPTY_TIME_ZERO;
        this.playerReleased = true;
        return;
    L21:
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(Assertions.checkNotNull(null));
        throw null;
    }

    @Override // androidx.media3.exoplayer.ExoPlayer
    public void removeAnalyticsListener(AnalyticsListener r1) {
        verifyApplicationThread();
        this.analyticsCollector.removeListener((AnalyticsListener) Assertions.checkNotNull(r1));
    }

    @Override // androidx.media3.common.Player
    public void removeListener(Player.Listener r1) {
        verifyApplicationThread();
        this.listeners.remove((Player.Listener) Assertions.checkNotNull(r1));
    }

    @Override // androidx.media3.common.Player
    public void removeMediaItems(int r12, int r13) {
        verifyApplicationThread();
        if (r12 < 0) goto L6;
        if (r13 < r12) goto L6;
        boolean r1 = true;
    L7:
        Assertions.checkArgument(r1);
        int r14 = this.mediaSourceHolderSnapshots.size();
        int r132 = Math.min(r13, r14);
        if (r12 >= r14) goto L13;
        if (r12 == r132) goto L14;
        PlaybackInfo r3 = removeMediaItemsInternal(this.playbackInfo, r12, r132);
        updatePlaybackInfo(r3, 0, !r3.periodId.periodUid.equals(this.playbackInfo.periodId.periodUid), 4, getCurrentPositionUsInternal(r3), -1, false);
        return;
    L14:
        return;
    L13:
        return;
    L6:
        r1 = false;
        goto L7
    }

    @Override // androidx.media3.exoplayer.ExoPlayer
    public void removeVideoListener(VideoListener r1) {
        this.videoListeners.remove(r1);
    }

    @Override // androidx.media3.common.Player
    public void replaceMediaItems(int r12, int r13, List<MediaItem> r14) {
        verifyApplicationThread();
        boolean r0 = false;
        if (r12 < 0) goto L6;
        if (r13 < r12) goto L6;
        boolean r2 = true;
    L7:
        Assertions.checkArgument(r2);
        int r22 = this.mediaSourceHolderSnapshots.size();
        if (r12 <= r22) goto L10;
        return;
    L10:
        int r132 = Math.min(r13, r22);
        if (canUpdateMediaSourcesWithMediaItems(r12, r132, r14) == false) goto L14;
        updateMediaSourcesWithMediaItems(r12, r132, r14);
        return;
    L14:
        List<MediaSource> r142 = createMediaSources(r14);
        if (this.mediaSourceHolderSnapshots.isEmpty() == true) goto L17;
        PlaybackInfo r3 = removeMediaItemsInternal(addMediaSourcesInternal(this.playbackInfo, r132, r142), r12, r132);
        updatePlaybackInfo(r3, 0, !r3.periodId.periodUid.equals(this.playbackInfo.periodId.periodUid), 4, getCurrentPositionUsInternal(r3), -1, false);
        return;
    L17:
        if (this.maskingWindowIndex != (-1)) goto L19;
        r0 = true;
    L19:
        setMediaSources(r142, r0);
        return;
    L6:
        r2 = false;
        goto L7
    }

    @Override // androidx.media3.common.BasePlayer
    public void seekTo(int r11, long r12, int r14, boolean r15) {
        verifyApplicationThread();
        if (r11 != (-1)) goto L6;
        return;
    L6:
        if (r11 < 0) goto L8;
        boolean r0 = true;
    L9:
        Assertions.checkArgument(r0);
        Timeline r02 = this.playbackInfo.timeline;
        if (r02.isEmpty() == false) goto L12;
    L14:
        this.analyticsCollector.notifySeekStarted();
        this.pendingOperationAcks++;
        if (isPlayingAd() == false) goto L18;
        Log.w("ExoPlayerImpl", "seekTo ignored because an ad is playing");
        ExoPlayerImplInternal.PlaybackInfoUpdate r112 = new ExoPlayerImplInternal.PlaybackInfoUpdate(this.playbackInfo);
        r112.incrementPendingOperationAcks(1);
        this.playbackInfoUpdateListener.onPlaybackInfoUpdate(r112);
        return;
    L18:
        PlaybackInfo r142 = this.playbackInfo;
        int r1 = r142.playbackState;
        if (r1 != 3) goto L21;
    L24:
        r142 = maskPlaybackState(this.playbackInfo, 2);
    L25:
        int r8 = getCurrentMediaItemIndex();
        PlaybackInfo r2 = maskTimelineAndPosition(r142, r02, maskWindowPositionMsOrGetPeriodPositionUs(r02, r11, r12));
        this.internalPlayer.seekTo(r02, r11, Util.msToUs(r12));
        updatePlaybackInfo(r2, 0, true, 1, getCurrentPositionUsInternal(r2), r8, r15);
        return;
    L21:
        if (r1 != 4) goto L25;
        if (r02.isEmpty() == true) goto L25;
    L12:
        if (r11 < r02.getWindowCount()) goto L14;
        return;
    L8:
        r0 = false;
        goto L9
    }

    @Override // androidx.media3.common.Player
    public void setAudioAttributes(AudioAttributes r3, boolean r4) {
        verifyApplicationThread();
        if (this.playerReleased == false) goto L6;
        return;
    L6:
        if (Objects.equals(this.audioAttributes, r3) == true) goto L11;
        this.audioAttributes = r3;
        sendRendererMessage(1, 3, r3);
        StreamVolumeManager r0 = this.streamVolumeManager;
        if (r0 == null) goto L10;
        r0.setStreamType(r3.getStreamType());
    L10:
        this.listeners.queueEvent(20, new ExoPlayerImpl$$ExternalSyntheticLambda24(r3));
    L11:
        this.internalPlayer.setAudioAttributes(this.audioAttributes, r4);
        this.listeners.flushEvents();
    }

    @Override // androidx.media3.common.Player
    @Deprecated
    public void setDeviceMuted(boolean r2) {
        verifyApplicationThread();
        StreamVolumeManager r1 = this.streamVolumeManager;
        if (r1 == null) goto L6;
        r1.setMuted(r2, 1);
        return;
    }

    @Override // androidx.media3.common.Player
    public void setDeviceMuted(boolean r1, int r2) {
        verifyApplicationThread();
        StreamVolumeManager r0 = this.streamVolumeManager;
        if (r0 == null) goto L6;
        r0.setMuted(r1, r2);
        return;
    }

    @Override // androidx.media3.common.Player
    @Deprecated
    public void setDeviceVolume(int r2) {
        verifyApplicationThread();
        StreamVolumeManager r1 = this.streamVolumeManager;
        if (r1 == null) goto L6;
        r1.setVolume(r2, 1);
        return;
    }

    @Override // androidx.media3.common.Player
    public void setDeviceVolume(int r1, int r2) {
        verifyApplicationThread();
        StreamVolumeManager r0 = this.streamVolumeManager;
        if (r0 == null) goto L6;
        r0.setVolume(r1, r2);
        return;
    }

    @Override // androidx.media3.common.Player
    public void setMediaItems(List<MediaItem> r1, int r2, long r3) {
        verifyApplicationThread();
        setMediaSources(createMediaSources(r1), r2, r3);
    }

    @Override // androidx.media3.common.Player
    public void setMediaItems(List<MediaItem> r1, boolean r2) {
        verifyApplicationThread();
        setMediaSources(createMediaSources(r1), r2);
    }

    @Override // androidx.media3.exoplayer.ExoPlayer
    public void setMediaSource(MediaSource r1) {
        verifyApplicationThread();
        setMediaSources(Collections.singletonList(r1));
    }

    @Override // androidx.media3.exoplayer.ExoPlayer
    public void setMediaSource(MediaSource r1, boolean r2) {
        verifyApplicationThread();
        setMediaSources(Collections.singletonList(r1), r2);
    }

    public void setMediaSources(List<MediaSource> r2) {
        verifyApplicationThread();
        setMediaSources(r2, true);
    }

    public void setMediaSources(List<MediaSource> r7, int r8, long r9) {
        verifyApplicationThread();
        setMediaSourcesInternal(r7, r8, r9, false);
    }

    public void setMediaSources(List<MediaSource> r7, boolean r8) {
        verifyApplicationThread();
        setMediaSourcesInternal(r7, -1, -9223372036854775807L, r8);
    }

    @Override // androidx.media3.common.Player
    public void setPlayWhenReady(boolean r2) {
        verifyApplicationThread();
        updatePlayWhenReady(r2, 1);
    }

    @Override // androidx.media3.common.Player
    public void setPlaybackParameters(PlaybackParameters r11) {
        verifyApplicationThread();
        if (r11 != null) goto L6;
        r11 = PlaybackParameters.DEFAULT;
    L6:
        if (this.playbackInfo.playbackParameters.equals(r11) == false) goto L8;
        return;
    L8:
        PlaybackInfo r2 = this.playbackInfo.copyWithPlaybackParameters(r11);
        this.pendingOperationAcks++;
        this.internalPlayer.setPlaybackParameters(r11);
        updatePlaybackInfo(r2, 0, false, 5, -9223372036854775807L, -1, false);
    }

    @Override // androidx.media3.common.Player
    public void setPlaylistMetadata(MediaMetadata r2) {
        verifyApplicationThread();
        Assertions.checkNotNull(r2);
        if (r2.equals(this.playlistMetadata) == false) goto L5;
        return;
    L5:
        this.playlistMetadata = r2;
        this.listeners.sendEvent(15, new ExoPlayerImpl$$ExternalSyntheticLambda29(this));
    }

    @Override // androidx.media3.common.Player
    public void setRepeatMode(int r3) {
        verifyApplicationThread();
        if (this.repeatMode == r3) goto L6;
        this.repeatMode = r3;
        this.internalPlayer.setRepeatMode(r3);
        this.listeners.queueEvent(8, new ExoPlayerImpl$$ExternalSyntheticLambda18(r3));
        updateAvailableCommands();
        this.listeners.flushEvents();
        return;
    }

    @Override // androidx.media3.exoplayer.ExoPlayer
    public void setSeekParameters(SeekParameters r2) {
        verifyApplicationThread();
        if (r2 != null) goto L6;
        r2 = SeekParameters.DEFAULT;
    L6:
        if (this.seekParameters.equals(r2) == true) goto L9;
        this.seekParameters = r2;
        this.internalPlayer.setSeekParameters(r2);
        return;
    }

    @Override // androidx.media3.common.Player
    public void setShuffleModeEnabled(boolean r3) {
        verifyApplicationThread();
        if (this.shuffleModeEnabled == r3) goto L6;
        this.shuffleModeEnabled = r3;
        this.internalPlayer.setShuffleModeEnabled(r3);
        this.listeners.queueEvent(9, new ExoPlayerImpl$$ExternalSyntheticLambda30(r3));
        updateAvailableCommands();
        this.listeners.flushEvents();
        return;
    }

    @Override // androidx.media3.common.Player
    public void setTrackSelectionParameters(TrackSelectionParameters r4) {
        verifyApplicationThread();
        if (this.trackSelector.isSetParametersSupported() == false) goto L17;
        TrackSelectionParameters r0 = getTrackSelectionParameters();
        if (this.scrubbingModeEnabled == false) goto L8;
        this.disabledTrackTypesWithoutScrubbingMode = r4.disabledTrackTypes;
        TrackSelectionParameters r1 = addDisabledTrackTypes(r4, this.scrubbingModeParameters.disabledTrackTypes);
    L10:
        if (r1.equals(this.trackSelector.getParameters()) == true) goto L13;
        this.trackSelector.setParameters(r1);
    L13:
        if (r0.equals(r4) == true) goto L16;
        this.listeners.sendEvent(19, new ExoPlayerImpl$$ExternalSyntheticLambda31(r4));
        return;
    L16:
        return;
    L8:
        r1 = r4;
        goto L10
    }

    @Override // androidx.media3.exoplayer.ExoPlayer
    public void setVideoFrameMetadataListener(VideoFrameMetadataListener r2) {
        verifyApplicationThread();
        this.videoFrameMetadataListener = r2;
        createMessageInternal(this.frameMetadataListener).setType(7).setPayload(r2).send();
    }

    @Override // androidx.media3.common.Player
    public void setVideoSurface(Surface r1) {
        verifyApplicationThread();
        removeSurfaceCallbacks();
        setVideoOutputInternal(r1);
        if (r1 != null) goto L5;
        int r12 = 0;
    L6:
        maybeNotifySurfaceSizeChanged(r12, r12);
        return;
    L5:
        r12 = -1;
        goto L6
    }

    public void setVideoSurfaceHolder(SurfaceHolder r3) {
        verifyApplicationThread();
        if (r3 != null) goto L6;
        clearVideoSurface();
        return;
    L6:
        removeSurfaceCallbacks();
        this.surfaceHolderSurfaceIsVideoOutput = true;
        this.surfaceHolder = r3;
        r3.addCallback(this.componentListener);
        Surface r0 = r3.getSurface();
        if (r0 != null) goto L9;
    L12:
        setVideoOutputInternal(null);
        maybeNotifySurfaceSizeChanged(0, 0);
        return;
    L9:
        if (r0.isValid() == false) goto L12;
        setVideoOutputInternal(r0);
        Rect r32 = r3.getSurfaceFrame();
        maybeNotifySurfaceSizeChanged(r32.width(), r32.height());
    }

    @Override // androidx.media3.common.Player
    public void setVideoSurfaceView(SurfaceView r1) {
        verifyApplicationThread();
        if (r1 != null) goto L5;
        SurfaceHolder r12 = null;
    L6:
        setVideoSurfaceHolder(r12);
        return;
    L5:
        r12 = r1.getHolder();
        goto L6
    }

    @Override // androidx.media3.common.Player
    public void setVideoTextureView(TextureView r3) {
        verifyApplicationThread();
        if (r3 != null) goto L6;
        clearVideoSurface();
        return;
    L6:
        removeSurfaceCallbacks();
        this.textureView = r3;
        if (r3.getSurfaceTextureListener() == null) goto L9;
        Log.w("ExoPlayerImpl", "Replacing existing SurfaceTextureListener.");
    L9:
        r3.setSurfaceTextureListener(this.componentListener);
        if (r3.isAvailable() == false) goto L12;
        SurfaceTexture r0 = r3.getSurfaceTexture();
    L13:
        if (r0 != null) goto L16;
        setVideoOutputInternal(null);
        maybeNotifySurfaceSizeChanged(0, 0);
        return;
    L16:
        setSurfaceTextureInternal(r0);
        maybeNotifySurfaceSizeChanged(r3.getWidth(), r3.getHeight());
        return;
    L12:
        r0 = null;
        goto L13
    }

    @Override // androidx.media3.common.Player
    public void setVolume(float r3) {
        verifyApplicationThread();
        float r32 = Util.constrainValue(r3, 0.0f, 1.0f);
        if (this.volume != r32) goto L5;
        return;
    L5:
        this.volume = r32;
        this.internalPlayer.setVolume(r32);
        this.listeners.sendEvent(22, new ExoPlayerImpl$$ExternalSyntheticLambda14(r32));
    }

    @Override // androidx.media3.exoplayer.ExoPlayer
    public void setWorkerQueue(Executor r1) {
        this.workerQueue = r1;
    }

    @Override // androidx.media3.common.Player
    public void stop() {
        verifyApplicationThread();
        stopInternal(null);
        this.currentCueGroup = new CueGroup(ImmutableList.of(), this.playbackInfo.positionUs);
    }
}
