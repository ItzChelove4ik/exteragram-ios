package androidx.media3.exoplayer;

import android.content.Context;
import android.media.MediaFormat;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Pair;
import androidx.media3.common.AudioAttributes;
import androidx.media3.common.Format;
import androidx.media3.common.MediaItem;
import androidx.media3.common.Metadata;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.ParserException;
import androidx.media3.common.PlaybackParameters;
import androidx.media3.common.Timeline;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Clock;
import androidx.media3.common.util.ConditionVariable;
import androidx.media3.common.util.HandlerWrapper;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.TraceUtil;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.DataSourceException;
import androidx.media3.exoplayer.AudioFocusManager;
import androidx.media3.exoplayer.DefaultMediaClock;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.exoplayer.LoadControl;
import androidx.media3.exoplayer.LoadingInfo;
import androidx.media3.exoplayer.MediaSourceList;
import androidx.media3.exoplayer.PlayerMessage;
import androidx.media3.exoplayer.Renderer;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.media3.exoplayer.analytics.AnalyticsCollector;
import androidx.media3.exoplayer.analytics.PlayerId;
import androidx.media3.exoplayer.drm.DrmSession;
import androidx.media3.exoplayer.source.BehindLiveWindowException;
import androidx.media3.exoplayer.source.MediaPeriod;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.source.SequenceableLoader;
import androidx.media3.exoplayer.source.ShuffleOrder;
import androidx.media3.exoplayer.source.TrackGroupArray;
import androidx.media3.exoplayer.trackselection.ExoTrackSelection;
import androidx.media3.exoplayer.trackselection.TrackSelector;
import androidx.media3.exoplayer.trackselection.TrackSelectorResult;
import androidx.media3.exoplayer.upstream.BandwidthMeter;
import androidx.media3.exoplayer.video.VideoFrameMetadataListener;
import com.google.android.gms.cast.CastStatusCodes;
import com.google.android.gms.cast.framework.media.NotificationOptions;
import com.google.common.collect.ImmutableList;
import com.google.common.math.DoubleMath;
import java.io.IOException;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import kotlin.jvm.internal.LongCompanionObject;
import okhttp3.HttpUrl$$ExternalSyntheticBUOutline0;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes4.dex */
final class ExoPlayerImplInternal implements Handler.Callback, MediaPeriod.Callback, TrackSelector.InvalidationListener, MediaSourceList.MediaSourceListInfoRefreshListener, DefaultMediaClock.PlaybackParametersListener, PlayerMessage.Sender, AudioFocusManager.PlayerControl, VideoFrameMetadataListener {
    private static final long BUFFERING_MAXIMUM_INTERVAL_MS = 0;
    private final AnalyticsCollector analyticsCollector;
    private final HandlerWrapper applicationLooperHandler;
    private final AudioFocusManager audioFocusManager;
    private final long backBufferDurationUs;
    private final BandwidthMeter bandwidthMeter;
    private final Clock clock;
    private boolean deliverPendingMessageAtStartPositionRequired;
    private final boolean dynamicSchedulingEnabled;
    private final TrackSelectorResult emptyTrackSelectorResult;
    private int enabledRendererCount;
    private boolean foregroundMode;
    private final HandlerWrapper handler;
    private final boolean hasSecondaryRenderers;
    private boolean isPrewarmingDisabledUntilNextTransition;
    private boolean isRebuffering;
    private Timeline lastPreloadPoolInvalidationTimeline;
    private long lastRebufferRealtimeMs;
    private final LivePlaybackSpeedControl livePlaybackSpeedControl;
    private final LoadControl loadControl;
    private final DefaultMediaClock mediaClock;
    private final MediaSourceList mediaSourceList;
    private int nextPendingMessageIndexHint;
    private boolean offloadSchedulingEnabled;
    private boolean pauseAtEndOfWindow;
    private SeekPosition pendingInitialSeekPosition;
    private final ArrayList<PendingMessageInfo> pendingMessages;
    private boolean pendingPauseAtEndOfPeriod;
    private ExoPlaybackException pendingRecoverableRendererError;
    private final Timeline.Period period;
    private PlaybackInfo playbackInfo;
    private PlaybackInfoUpdate playbackInfoUpdate;
    private final PlaybackInfoUpdateListener playbackInfoUpdateListener;
    private final Looper playbackLooper;
    private final PlaybackLooperProvider playbackLooperProvider;
    private long playbackMaybeBecameStuckAtMs;
    private final PlayerId playerId;
    private ExoPlayer.PreloadConfiguration preloadConfiguration;
    private long prewarmingMediaPeriodDiscontinuity;
    private final MediaPeriodQueue queue;
    private SeekPosition queuedSeekWhileScrubbing;
    private final long releaseTimeoutMs;
    private boolean releasedOnApplicationThread;
    private final RendererCapabilities[] rendererCapabilities;
    private long rendererPositionElapsedRealtimeUs;
    private long rendererPositionUs;
    private final boolean[] rendererReportedReady;
    private final RendererHolder[] renderers;
    private int repeatMode;
    private boolean requestForRendererSleep;
    private final boolean retainBackBufferFromKeyframe;
    private boolean scrubbingModeEnabled;
    private ScrubbingModeParameters scrubbingModeParameters;
    private SeekParameters scrubbingModeSeekParameters;
    private boolean seekIsPendingWhileScrubbing;
    private SeekParameters seekParameters;
    private long setForegroundModeTimeoutMs;
    private boolean shouldContinueLoading;
    private boolean shuffleModeEnabled;
    private final TrackSelector trackSelector;
    private float volume;
    private final Timeline.Window window;

    /* renamed from: androidx.media3.exoplayer.ExoPlayerImplInternal$1, reason: invalid class name */
    public class AnonymousClass1 implements Renderer.WakeupListener {
        final /* synthetic */ ExoPlayerImplInternal this$0;

        public AnonymousClass1(ExoPlayerImplInternal r1) {
            this.this$0 = r1;
        }

        @Override // androidx.media3.exoplayer.Renderer.WakeupListener
        public void onSleep() {
            ExoPlayerImplInternal.access$602(this.this$0, true);
        }

        @Override // androidx.media3.exoplayer.Renderer.WakeupListener
        public void onWakeup() {
            if (ExoPlayerImplInternal.access$700(this.this$0) == false) goto L5;
        L8:
            ExoPlayerImplInternal.access$900(this.this$0).sendEmptyMessage(2);
            return;
        L5:
            if (ExoPlayerImplInternal.access$800(this.this$0) == true) goto L8;
        }
    }

    public static final class MediaSourceListUpdateMessage {
        private final List<MediaSourceList.MediaSourceHolder> mediaSourceHolders;
        private final long positionUs;
        private final ShuffleOrder shuffleOrder;
        private final int windowIndex;

        private MediaSourceListUpdateMessage(List<MediaSourceList.MediaSourceHolder> r1, ShuffleOrder r2, int r3, long r4) {
            this.mediaSourceHolders = r1;
            this.shuffleOrder = r2;
            this.windowIndex = r3;
            this.positionUs = r4;
        }

        public /* synthetic */ MediaSourceListUpdateMessage(List r1, ShuffleOrder r2, int r3, long r4, AnonymousClass1 r6) {
            this(r1, r2, r3, r4);
        }

        public static /* synthetic */ int access$200(MediaSourceListUpdateMessage r0) {
            return r0.windowIndex;
        }

        public static /* synthetic */ List access$300(MediaSourceListUpdateMessage r0) {
            return r0.mediaSourceHolders;
        }

        public static /* synthetic */ ShuffleOrder access$400(MediaSourceListUpdateMessage r0) {
            return r0.shuffleOrder;
        }

        public static /* synthetic */ long access$500(MediaSourceListUpdateMessage r2) {
            return r2.positionUs;
        }
    }

    public static class MoveMediaItemsMessage {
        public final int fromIndex;
        public final int newFromIndex;
        public final ShuffleOrder shuffleOrder;
        public final int toIndex;

        public MoveMediaItemsMessage(int r1, int r2, int r3, ShuffleOrder r4) {
            this.fromIndex = r1;
            this.toIndex = r2;
            this.newFromIndex = r3;
            this.shuffleOrder = r4;
        }
    }

    public static final class PendingMessageInfo implements Comparable<PendingMessageInfo> {
        public final PlayerMessage message;
        public int resolvedPeriodIndex;
        public long resolvedPeriodTimeUs;
        public Object resolvedPeriodUid;

        public PendingMessageInfo(PlayerMessage r1) {
            this.message = r1;
        }

        /* renamed from: compareTo, reason: avoid collision after fix types in other method */
        public int compareTo2(PendingMessageInfo r6) {
            Object r0 = this.resolvedPeriodUid;
            if (r0 != null) goto L5;
            boolean r3 = true;
        L7:
            if (r6.resolvedPeriodUid != null) goto L9;
            boolean r4 = true;
        L10:
            if (r3 == r4) goto L15;
            if (r0 == null) goto L14;
            return -1;
        L14:
            return 1;
        L15:
            if (r0 != null) goto L17;
            return 0;
        L17:
            int r02 = this.resolvedPeriodIndex - r6.resolvedPeriodIndex;
            if (r02 == 0) goto L21;
            return r02;
        L21:
            return Long.compare(this.resolvedPeriodTimeUs, r6.resolvedPeriodTimeUs);
        L9:
            r4 = false;
            goto L10
        L5:
            r3 = false;
            goto L7
        }

        @Override // java.lang.Comparable
        public /* bridge */ /* synthetic */ int compareTo(PendingMessageInfo r1) {
            return compareTo2(r1);
        }

        public void setResolvedPosition(int r1, long r2, Object r4) {
            this.resolvedPeriodIndex = r1;
            this.resolvedPeriodTimeUs = r2;
            this.resolvedPeriodUid = r4;
        }
    }

    public static final class PlaybackInfoUpdate {
        public int discontinuityReason;
        private boolean hasPendingChange;
        public int operationAcks;
        public PlaybackInfo playbackInfo;
        public boolean positionDiscontinuity;

        public PlaybackInfoUpdate(PlaybackInfo r1) {
            this.playbackInfo = r1;
        }

        public static /* synthetic */ boolean access$100(PlaybackInfoUpdate r0) {
            return r0.hasPendingChange;
        }

        public void incrementPendingOperationAcks(int r3) {
            boolean r0 = this.hasPendingChange;
            if (r3 <= 0) goto L5;
            boolean r1 = true;
        L6:
            this.hasPendingChange = r0 | r1;
            this.operationAcks += r3;
            return;
        L5:
            r1 = false;
            goto L6
        }

        public void setPlaybackInfo(PlaybackInfo r3) {
            boolean r0 = this.hasPendingChange;
            if (this.playbackInfo == r3) goto L5;
            boolean r1 = true;
        L6:
            this.hasPendingChange = r0 | r1;
            this.playbackInfo = r3;
            return;
        L5:
            r1 = false;
            goto L6
        }

        public void setPositionDiscontinuity(int r4) {
            boolean r1 = true;
            if (this.positionDiscontinuity == true) goto L5;
        L11:
            this.hasPendingChange = true;
            this.positionDiscontinuity = true;
            this.discontinuityReason = r4;
            return;
        L5:
            if (this.discontinuityReason == 5) goto L11;
            if (r4 == 5) goto L9;
            r1 = false;
        L9:
            Assertions.checkArgument(r1);
        }
    }

    public interface PlaybackInfoUpdateListener {
        void onPlaybackInfoUpdate(PlaybackInfoUpdate r1);
    }

    public static final class PositionUpdateForPlaylistChange {
        public final boolean endPlayback;
        public final boolean forceBufferingState;
        public final MediaSource.MediaPeriodId periodId;
        public final long periodPositionUs;
        public final long requestedContentPositionUs;
        public final boolean setTargetLiveOffset;

        public PositionUpdateForPlaylistChange(MediaSource.MediaPeriodId r1, long r2, long r4, boolean r6, boolean r7, boolean r8) {
            this.periodId = r1;
            this.periodPositionUs = r2;
            this.requestedContentPositionUs = r4;
            this.forceBufferingState = r6;
            this.endPlayback = r7;
            this.setTargetLiveOffset = r8;
        }
    }

    public static final class SeekPosition {
        public final Timeline timeline;
        public final int windowIndex;
        public final long windowPositionUs;

        public SeekPosition(Timeline r1, int r2, long r3) {
            this.timeline = r1;
            this.windowIndex = r2;
            this.windowPositionUs = r3;
        }
    }

    public static /* synthetic */ MediaPeriodHolder $r8$lambda$4ByVomVrDZGMrfXELlhTrFd0naQ(ExoPlayerImplInternal r0, MediaPeriodInfo r1, long r2) {
        return r0.createMediaPeriodHolder(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$8XbA_SN6j8GvqbkI7njgXAWg47Q(ExoPlayerImplInternal r1, int r2, boolean r3) {
        r1.analyticsCollector.onRendererReadyChanged(r2, r1.renderers[r2].getTrackType(), r3);
    }

    public static /* synthetic */ void $r8$lambda$PHPl0lerVHRNmb61QccGegOGlG0(ExoPlayerImplInternal r0, VideoFrameMetadataListener r1, long r2, long r4, Format r6, MediaFormat r7) {
        r0.getClass();
        r1.onVideoFrameAboutToBeRendered(r2, r4, r6, r7);
        r0.onVideoFrameAboutToBeRendered(r2, r4, r6, r7);
    }

    public static /* synthetic */ void $r8$lambda$nZ_iCIp1I4p988uPUOGUgStg6Ek(ExoPlayerImplInternal r1, PlayerMessage r2) {
        r1.getClass();
        r1.deliverMessage(r2);     // Catch: ExoPlaybackException -> L5
        return;
    L5:
        e = move-exception;
        Log.e("ExoPlayerImplInternal", "Unexpected error delivering message on external thread.", e);
        HttpUrl$$ExternalSyntheticBUOutline0.m(e);
    }

    static {
        BUFFERING_MAXIMUM_INTERVAL_MS = Util.usToMs(NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS);
    }

    public ExoPlayerImplInternal(Context r15, Renderer[] r16, Renderer[] r17, TrackSelector r18, TrackSelectorResult r19, LoadControl r20, BandwidthMeter r21, int r22, boolean r23, AnalyticsCollector r24, SeekParameters r25, LivePlaybackSpeedControl r26, long r27, boolean r29, boolean r30, Looper r31, Clock r32, PlaybackInfoUpdateListener r33, PlayerId r34, PlaybackLooperProvider r35, ExoPlayer.PreloadConfiguration r36, VideoFrameMetadataListener r37) {
        this.prewarmingMediaPeriodDiscontinuity = -9223372036854775807L;
        this.playbackInfoUpdateListener = r33;
        this.trackSelector = r18;
        this.emptyTrackSelectorResult = r19;
        this.loadControl = r20;
        this.bandwidthMeter = r21;
        this.repeatMode = r22;
        this.shuffleModeEnabled = r23;
        this.seekParameters = r25;
        this.livePlaybackSpeedControl = r26;
        this.releaseTimeoutMs = r27;
        this.setForegroundModeTimeoutMs = r27;
        this.pauseAtEndOfWindow = r29;
        this.dynamicSchedulingEnabled = r30;
        this.clock = r32;
        this.playerId = r34;
        this.preloadConfiguration = r36;
        this.analyticsCollector = r24;
        this.volume = 1.0f;
        this.scrubbingModeParameters = ScrubbingModeParameters.DEFAULT;
        this.playbackMaybeBecameStuckAtMs = -9223372036854775807L;
        this.lastRebufferRealtimeMs = -9223372036854775807L;
        this.backBufferDurationUs = r20.getBackBufferDurationUs(r34);
        this.retainBackBufferFromKeyframe = r20.retainBackBufferFromKeyframe(r34);
        this.lastPreloadPoolInvalidationTimeline = Timeline.EMPTY;
        PlaybackInfo r2 = PlaybackInfo.createDummy(r19);
        this.playbackInfo = r2;
        this.playbackInfoUpdate = new PlaybackInfoUpdate(r2);
        this.rendererCapabilities = new RendererCapabilities[r16.length];
        this.rendererReportedReady = new boolean[r16.length];
        RendererCapabilities.Listener r28 = r18.getRendererCapabilitiesListener();
        this.renderers = new RendererHolder[r16.length];
        int r5 = 0;
        boolean r6 = false;
    L4:
        if (r5 >= r16.length) goto L12;
        r16[r5].init(r5, r34, r32);
        this.rendererCapabilities[r5] = r16[r5].getCapabilities();
        if (r28 == null) goto L8;
        this.rendererCapabilities[r5].setListener(r28);
    L8:
        Renderer r10 = r17[r5];
        if (r10 == null) goto L11;
        r10.init(r5, r34, r32);
        r6 = true;
    L11:
        this.renderers[r5] = new RendererHolder(r16[r5], r17[r5], r5);
        r5 = r5 + 1;
        goto L4
    L12:
        this.hasSecondaryRenderers = r6;
        this.mediaClock = new DefaultMediaClock(this, r32);
        this.pendingMessages = new ArrayList();
        this.window = new Timeline.Window();
        this.period = new Timeline.Period();
        r18.init(this, r21);
        this.deliverPendingMessageAtStartPositionRequired = true;
        HandlerWrapper r0 = r32.createHandler(r31, null);
        this.applicationLooperHandler = r0;
        this.queue = new MediaPeriodQueue(r24, r0, new ExoPlayerImplInternal$$ExternalSyntheticLambda2(this), r36);
        this.mediaSourceList = new MediaSourceList(this, r24, r0, r34);
        if (r35 != null) goto L15;
        PlaybackLooperProvider r02 = new PlaybackLooperProvider();
    L16:
        this.playbackLooperProvider = r02;
        Looper r03 = r02.obtainLooper();
        this.playbackLooper = r03;
        HandlerWrapper r1 = r32.createHandler(r03, this);
        this.handler = r1;
        this.audioFocusManager = new AudioFocusManager(r15, r03, this);
        r1.obtainMessage(35, new ExoPlayerImplInternal$$ExternalSyntheticLambda3(this, r37)).sendToTarget();
        return;
    L15:
        r02 = r35;
        goto L16
    }

    public static /* synthetic */ boolean access$602(ExoPlayerImplInternal r0, boolean r1) {
        r0.requestForRendererSleep = r1;
        return r1;
    }

    public static /* synthetic */ boolean access$700(ExoPlayerImplInternal r0) {
        return r0.isDynamicSchedulingEnabled();
    }

    public static /* synthetic */ boolean access$800(ExoPlayerImplInternal r0) {
        return r0.offloadSchedulingEnabled;
    }

    public static /* synthetic */ HandlerWrapper access$900(ExoPlayerImplInternal r0) {
        return r0.handler;
    }

    private void addMediaItemsInternal(MediaSourceListUpdateMessage r3, int r4) {
        this.playbackInfoUpdate.incrementPendingOperationAcks(1);
        MediaSourceList r0 = this.mediaSourceList;
        if (r4 != (-1)) goto L5;
        r4 = r0.getSize();
    L5:
        handleMediaSourceListInfoRefreshed(r0.addMediaSources(r4, MediaSourceListUpdateMessage.access$300(r3), MediaSourceListUpdateMessage.access$400(r3)), false);
    }

    private void allowRenderersToRenderStartOfStreams() {
        TrackSelectorResult r0 = this.queue.getPlayingPeriod().getTrackSelectorResult();
        int r1 = 0;
    L4:
        if (r1 >= this.renderers.length) goto L10;
        if (r0.isRendererEnabled(r1) == false) goto L9;
        this.renderers[r1].enableMayRenderStartOfStream();
    L9:
        r1 = r1 + 1;
        goto L4
    }

    private void applyScrubbingModeParameters() {
        RendererHolder[] r0 = this.renderers;
        int r1 = r0.length;
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L9;
        RendererHolder r3 = r0[r2];
        if (this.scrubbingModeEnabled == false) goto L7;
        ScrubbingModeParameters r4 = this.scrubbingModeParameters;
    L8:
        r3.setScrubbingMode(r4);
        r2 = r2 + 1;
        goto L3
    L7:
        r4 = null;
        goto L8
    }

    private boolean areRenderersPrewarming() {
        if (this.hasSecondaryRenderers == true) goto L5;
        return false;
    L5:
        RendererHolder[] r4 = this.renderers;
        int r0 = r4.length;
        int r2 = 0;
    L6:
        if (r2 >= r0) goto L12;
        if (r4[r2].isPrewarming() == true) goto L9;
        r2 = r2 + 1;
        goto L6
    L9:
        return true;
    L12:
        return false;
    }

    private void attemptRendererErrorRecovery() {
        reselectTracksInternalAndSeek();
    }

    private MediaPeriodHolder createMediaPeriodHolder(MediaPeriodInfo r12, long r13) {
        return new MediaPeriodHolder(this.rendererCapabilities, r13, this.trackSelector, this.loadControl.getAllocator(), this.mediaSourceList, r12, this.emptyTrackSelectorResult, this.preloadConfiguration.targetPreloadDurationUs);
    }

    private void deliverMessage(PlayerMessage r4) {
        if (r4.isCanceled() == false) goto L12;
        return;
    L12:
        r4.getTarget().handleMessage(r4.getType(), r4.getPayload());     // Catch: Throwable -> L9
        r4.markAsProcessed(true);
        return;
    L9:
        th = move-exception;
        r4.markAsProcessed(true);
        throw th;
    }

    private void disableAndResetPrewarmingRenderers() {
        if (this.hasSecondaryRenderers == true) goto L5;
        return;
    L5:
        if (areRenderersPrewarming() == false) goto L14;
        RendererHolder[] r0 = this.renderers;
        int r1 = r0.length;
        int r2 = 0;
    L8:
        if (r2 >= r1) goto L10;
        RendererHolder r3 = r0[r2];
        int r4 = r3.getEnabledRendererCount();
        r3.disablePrewarming(this.mediaClock);
        this.enabledRendererCount -= r4 - r3.getEnabledRendererCount();
        r2 = r2 + 1;
        goto L8
    L10:
        this.prewarmingMediaPeriodDiscontinuity = -9223372036854775807L;
        return;
    }

    private void disableRenderer(int r4) {
        int r0 = this.renderers[r4].getEnabledRendererCount();
        this.renderers[r4].disable(this.mediaClock);
        maybeTriggerOnRendererReadyChanged(r4, false);
        this.enabledRendererCount -= r0;
    }

    private void disableRenderers() {
        int r0 = 0;
    L4:
        if (r0 >= this.renderers.length) goto L6;
        disableRenderer(r0);
        r0 = r0 + 1;
        goto L4
    L6:
        this.prewarmingMediaPeriodDiscontinuity = -9223372036854775807L;
    }

    private void doSomeWork() {
        long r1 = this.clock.uptimeMillis();
        this.handler.removeMessages(2);
        updatePeriods();
        int r3 = this.playbackInfo.playbackState;
        boolean r5 = true;
        if (r3 != 1) goto L5;
        return;
    L5:
        if (r3 == 4) goto L138;
        MediaPeriodHolder r32 = this.queue.getPlayingPeriod();
        if (r32 != null) goto L11;
        scheduleNextWork(r1);
        return;
    L11:
        TraceUtil.beginSection("doSomeWork");
        updatePlaybackPositions();
        if (r32.prepared == false) goto L33;
        this.rendererPositionElapsedRealtimeUs = Util.msToUs(this.clock.elapsedRealtime());
        r32.mediaPeriod.discardBuffer(this.playbackInfo.positionUs - this.backBufferDurationUs, this.retainBackBufferFromKeyframe);
        boolean r9 = true;
        boolean r10 = true;
        int r7 = 0;
    L14:
        RendererHolder[] r11 = this.renderers;
        if (r7 >= r11.length) goto L34;
        RendererHolder r112 = r11[r7];
        if (r112.getEnabledRendererCount() != 0) goto L19;
        maybeTriggerOnRendererReadyChanged(r7, false);
    L32:
        r7 = r7 + 1;
        goto L14
    L19:
        r112.render(this.rendererPositionUs, this.rendererPositionElapsedRealtimeUs);
        if (r9 == true) goto L22;
    L24:
        r9 = false;
    L25:
        boolean r113 = r112.allowsPlayback(r32);
        maybeTriggerOnRendererReadyChanged(r7, r113);
        if (r10 == false) goto L29;
        if (r113 == false) goto L29;
        r10 = true;
    L30:
        if (r113 == true) goto L32;
        maybeThrowRendererStreamError(r7);
    L29:
        r10 = false;
        goto L30
    L22:
        if (r112.isEnded() == false) goto L24;
        r9 = true;
    L34:
        long r114 = r32.info.durationUs;
        if (r9 == true) goto L37;
    L45:
        long r15 = -9223372036854775807L;
    L46:
        boolean r72 = false;
    L47:
        if (r72 == false) goto L52;
        if (this.pendingPauseAtEndOfPeriod == false) goto L52;
        this.pendingPauseAtEndOfPeriod = false;
        setPlayWhenReadyInternal(false, this.playbackInfo.playbackSuppressionReason, false, 5);
    L52:
        if (r72 == false) goto L57;
        if (r32.info.isFinal == false) goto L57;
        setState(4);
        stopRenderers();
    L76:
        if (this.playbackInfo.playbackState != 2) goto L100;
        int r73 = 0;
    L78:
        RendererHolder[] r102 = this.renderers;
        if (r73 >= r102.length) goto L84;
        if (r102[r73].isReadingFromPeriod(r32) == false) goto L83;
        maybeThrowRendererStreamError(r73);
    L83:
        r73 = r73 + 1;
        goto L78
    L84:
        PlaybackInfo r33 = this.playbackInfo;
        if (r33.isLoading == true) goto L100;
        if (r33.totalBufferedDurationUs >= 500000) goto L100;
        if (isLoadingPossible(this.queue.getLoadingPeriod()) == false) goto L100;
        if (shouldPlayWhenReady() == false) goto L100;
        long r103 = this.playbackMaybeBecameStuckAtMs;
        Clock r74 = this.clock;
        if (r103 != r15) goto L96;
        this.playbackMaybeBecameStuckAtMs = r74.elapsedRealtime();
    L102:
        if (shouldPlayWhenReady() == true) goto L104;
    L106:
        boolean r34 = false;
    L108:
        if (this.offloadSchedulingEnabled == true) goto L110;
    L113:
        r5 = false;
    L114:
        PlaybackInfo r75 = this.playbackInfo;
        if (r75.sleepingForOffload == r5) goto L117;
        this.playbackInfo = r75.copyWithSleepingForOffload(r5);
    L117:
        this.requestForRendererSleep = false;
        if (r5 == true) goto L128;
        int r52 = this.playbackInfo.playbackState;
        if (r52 == 4) goto L128;
        if (r34 == true) goto L127;
        if (r52 == 2) goto L127;
        if (r52 != 3) goto L128;
        if (this.enabledRendererCount == 0) goto L128;
    L127:
        scheduleNextWork(r1);
    L128:
        TraceUtil.endSection();
        return;
    L110:
        if (this.requestForRendererSleep == false) goto L113;
        if (r34 == false) goto L113;
    L104:
        if (this.playbackInfo.playbackState != 3) goto L106;
        r34 = true;
        goto L108
    L96:
        if ((r74.elapsedRealtime() - this.playbackMaybeBecameStuckAtMs) < 4000) goto L102;
        Segment$$ExternalSyntheticBUOutline1.m("Playback stuck buffering and not loading");
        return;
    L100:
        this.playbackMaybeBecameStuckAtMs = r15;
    L57:
        if (this.playbackInfo.playbackState != 2) goto L64;
        if (shouldTransitionToReadyState(r10) == false) goto L64;
        setState(3);
        this.pendingRecoverableRendererError = null;
        if (shouldPlayWhenReady() == false) goto L76;
        updateRebufferingState(false, false);
        this.mediaClock.start();
        startRenderers();
    L64:
        if (this.playbackInfo.playbackState != 3) goto L76;
        if (this.enabledRendererCount == 0) goto L68;
        if (r10 == true) goto L76;
    L71:
        updateRebufferingState(shouldPlayWhenReady(), false);
        setState(2);
        if (this.isRebuffering == false) goto L74;
        notifyTrackSelectionRebuffer();
        this.livePlaybackSpeedControl.notifyRebuffer();
    L74:
        stopRenderers();
        goto L76
    L68:
        if (isTimelineReady() == false) goto L71;
    L37:
        if (r32.prepared == false) goto L45;
        if (r114 == (-9223372036854775807L)) goto L43;
        r15 = -9223372036854775807L;
        if (r114 > this.playbackInfo.positionUs) goto L46;
    L44:
        r72 = true;
        goto L47
    L43:
        r15 = -9223372036854775807L;
        goto L44
    L33:
        r32.mediaPeriod.maybeThrowPrepareError();
        r9 = true;
        r10 = true;
        goto L34
    }

    private void enableRenderer(MediaPeriodHolder r19, int r20, boolean r21, long r22) {
        RendererHolder r3 = this.renderers[r20];
        if (r3.isRendererEnabled() == false) goto L6;
        return;
    L6:
        if (r19 != this.queue.getPlayingPeriod()) goto L8;
        boolean r10 = true;
    L9:
        TrackSelectorResult r2 = r19.getTrackSelectorResult();
        RendererConfiguration r6 = r2.rendererConfigurations[r20];
        ExoTrackSelection r23 = r2.selections[r20];
        if (shouldPlayWhenReady() == true) goto L12;
    L14:
        boolean r17 = false;
    L15:
        if (r21 == true) goto L18;
        if (r17 == false) goto L18;
        boolean r9 = true;
    L19:
        this.enabledRendererCount++;
        r3.enable(r6, r23, r19.sampleStreams[r20], this.rendererPositionUs, r9, r10, r22, r19.getRendererOffset(), r19.info.id, this.mediaClock);
        r3.handleMessage(11, new AnonymousClass1(this), r19);
        if (r17 == false) goto L24;
        if (r10 == false) goto L25;
        r3.start();
        return;
    L25:
        return;
    L24:
        return;
    L18:
        r9 = false;
        goto L19
    L12:
        if (this.playbackInfo.playbackState != 3) goto L14;
        r17 = true;
        goto L15
    L8:
        r10 = false;
        goto L9
    }

    private void enableRenderers() {
        enableRenderers(new boolean[this.renderers.length], this.queue.getReadingPeriod().getStartPositionRendererTime());
    }

    private void enableRenderers(boolean[] r8, long r9) {
        MediaPeriodHolder r2 = this.queue.getReadingPeriod();
        TrackSelectorResult r0 = r2.getTrackSelectorResult();
        int r3 = 0;
    L4:
        if (r3 >= this.renderers.length) goto L9;
        if (r0.isRendererEnabled(r3) == true) goto L8;
        this.renderers[r3].reset();
    L8:
        r3 = r3 + 1;
        goto L4
    L9:
        int r32 = 0;
    L11:
        if (r32 >= this.renderers.length) goto L19;
        if (r0.isRendererEnabled(r32) == true) goto L15;
    L17:
        ExoPlayerImplInternal r1 = this;
        long r5 = r9;
    L18:
        r32 = r32 + 1;
        ExoPlayerImplInternal this = r1;
        r9 = r5;
        goto L11
    L15:
        if (this.renderers[r32].isReadingFromPeriod(r2) == true) goto L17;
        r1 = this;
        r5 = r9;
        r1.enableRenderer(r2, r32, r8[r32], r5);
        goto L18
    }

    private ImmutableList<Metadata> extractMetadataFromTrackSelectionArray(ExoTrackSelection[] r7) {
        ImmutableList.Builder r6 = new ImmutableList.Builder();
        int r0 = r7.length;
        int r2 = 0;
        boolean r3 = false;
    L3:
        if (r2 >= r0) goto L11;
        ExoTrackSelection r4 = r7[r2];
        if (r4 == null) goto L10;
        Metadata r42 = r4.getFormat(0).metadata;
        if (r42 != null) goto L9;
        r6.add(new Metadata(new Metadata.Entry[0]));
        goto L10
    L9:
        r6.add(r42);
        r3 = true;
    L10:
        r2 = r2 + 1;
        goto L3
    L11:
        if (r3 == false) goto L15;
        return r6.build();
    L15:
        return ImmutableList.of();
    }

    private long getCurrentLiveOffsetUs() {
        PlaybackInfo r0 = this.playbackInfo;
        return getLiveOffsetUs(r0.timeline, r0.periodId.periodUid, r0.positionUs);
    }

    private long getDynamicSchedulingWakeUpIntervalMs() {
        if (this.playbackInfo.playbackState != 3) goto L5;
        long r0 = 1000;
    L6:
        RendererHolder[] r2 = this.renderers;
        int r3 = r2.length;
        int r4 = 0;
    L7:
        if (r4 >= r3) goto L10;
        r0 = Math.min(r0, Util.usToMs(r2[r4].getMinDurationToProgressUs(this.rendererPositionUs, this.rendererPositionElapsedRealtimeUs)));
        r4 = r4 + 1;
        goto L7
    L10:
        if (this.playbackInfo.isPlaying() == true) goto L12;
        return r0;
    L12:
        if (this.queue.getPlayingPeriod() == null) goto L14;
        MediaPeriodHolder r22 = this.queue.getPlayingPeriod().getNext();
    L15:
        if (r22 != null) goto L17;
        return r0;
    L17:
        if ((this.rendererPositionUs + (Util.msToUs(r0) * this.playbackInfo.playbackParameters.speed)) >= r22.getStartPositionRendererTime()) goto L19;
        return r0;
    L19:
        return Math.min(r0, BUFFERING_MAXIMUM_INTERVAL_MS);
    L14:
        r22 = null;
        goto L15
    L5:
        r0 = BUFFERING_MAXIMUM_INTERVAL_MS;
        goto L6
    }

    private long getLiveOffsetUs(Timeline r5, Object r6, long r7) {
        r5.getWindow(r5.getPeriodByUid(r6, this.period).windowIndex, this.window);
        Timeline.Window r52 = this.window;
        if (r52.windowStartTimeMs != (-9223372036854775807L)) goto L5;
    L11:
        return -9223372036854775807L;
    L5:
        if (r52.isLive() == false) goto L11;
        Timeline.Window r53 = this.window;
        if (r53.isDynamic == false) goto L11;
        return Util.msToUs(r53.getCurrentUnixTimeMs() - this.window.windowStartTimeMs) - (r7 + this.period.getPositionInWindowUs());
    }

    private long getMaxRendererReadPositionUs(MediaPeriodHolder r9) {
        if (r9 != null) goto L5;
        return 0;
    L5:
        long r0 = r9.getRendererOffset();
        if (r9.prepared == true) goto L8;
        return r0;
    L8:
        int r2 = 0;
    L9:
        RendererHolder[] r3 = this.renderers;
        if (r2 >= r3.length) goto L19;
        if (r3[r2].isReadingFromPeriod(r9) == false) goto L18;
        long r32 = this.renderers[r2].getReadingPositionUs(r9);
        if (r32 == Long.MIN_VALUE) goto L16;
        r0 = Math.max(r32, r0);
        goto L18
    L16:
        return Long.MIN_VALUE;
    L18:
        r2 = r2 + 1;
        goto L9
    L19:
        return r0;
    }

    private Pair<MediaSource.MediaPeriodId, Long> getPlaceholderFirstMediaPeriodPositionUs(Timeline r10) {
        long r1 = 0;
        if (r10.isEmpty() == true) goto L5;
        int r6 = r10.getFirstWindowIndex(this.shuffleModeEnabled);
        Pair<Object, Long> r102 = r10.getPeriodPositionUs(this.window, this.period, r6, -9223372036854775807L);
        MediaSource.MediaPeriodId r0 = this.queue.resolveMediaPeriodIdForAdsAfterPeriodPositionChange(r10, r102.first, 0);
        long r4 = ((Long) r102.second).longValue();
        if (r0.isAd() == false) goto L13;
        r10.getPeriodByUid(r0.periodUid, this.period);
        if (r0.adIndexInAdGroup != this.period.getFirstAdIndexToPlay(r0.adGroupIndex)) goto L11;
        r1 = this.period.getAdResumePositionUs();
    L11:
        r4 = r1;
    L13:
        return Pair.create(r0, Long.valueOf(r4));
    L5:
        return Pair.create(PlaybackInfo.getDummyPeriodForEmptyTimeline(), 0L);
    }

    private SeekParameters getSeekParameters(long r6) {
        if (this.scrubbingModeEnabled == false) goto L21;
        if (r6 == (-9223372036854775807L)) goto L21;
        ScrubbingModeParameters r0 = this.scrubbingModeParameters;
        Double r1 = r0.fractionalSeekToleranceBefore;
        if (r1 == null) goto L21;
        if (r0.fractionalSeekToleranceAfter == null) goto L21;
        double r62 = r6;
        double r02 = r1.doubleValue() * r62;
        RoundingMode r2 = RoundingMode.FLOOR;
        long r03 = DoubleMath.roundToLong(r02, r2);
        long r63 = DoubleMath.roundToLong(this.scrubbingModeParameters.fractionalSeekToleranceAfter.doubleValue() * r62, r2);
        SeekParameters r22 = this.scrubbingModeSeekParameters;
        if (r22 != null) goto L14;
    L17:
        this.scrubbingModeSeekParameters = new SeekParameters(r03, r63);
    L19:
        return this.scrubbingModeSeekParameters;
    L14:
        if (r22.toleranceBeforeUs != r03) goto L17;
        if (r22.toleranceAfterUs == r63) goto L19;
    L21:
        return this.seekParameters;
    }

    private long getStaticSchedulingWakeUpIntervalMs() {
        if (this.playbackInfo.playbackState != 3) goto L9;
        if (shouldPlayWhenReady() == true) goto L9;
        return 1000;
    L9:
        return BUFFERING_MAXIMUM_INTERVAL_MS;
    }

    private long getTotalBufferedDurationUs() {
        return getTotalBufferedDurationUs(this.playbackInfo.bufferedPositionUs);
    }

    private long getTotalBufferedDurationUs(long r6) {
        MediaPeriodHolder r0 = this.queue.getLoadingPeriod();
        if (r0 != null) goto L6;
        return 0;
    L6:
        return Math.max(0, r6 - r0.toPeriodTime(this.rendererPositionUs));
    }

    private void handleAudioFocusPlayerCommandInternal(int r4) {
        PlaybackInfo r0 = this.playbackInfo;
        updatePlayWhenReadyWithAudioFocus(r0.playWhenReady, r4, r0.playbackSuppressionReason, r0.playWhenReadyChangeReason);
    }

    private void handleAudioFocusVolumeMultiplierChange() {
        setVolumeInternal(this.volume);
    }

    private void handleContinueLoadingRequested(MediaPeriod r5) {
        boolean r0 = this.queue.isLoading(r5);
        MediaPeriodQueue r1 = this.queue;
        if (r0 == false) goto L7;
        r1.reevaluateBuffer(this.rendererPositionUs);
        maybeContinueLoading();
        return;
    L7:
        if (r1.isPreloading(r5) == false) goto L10;
        maybeContinuePreloading();
        return;
    }

    private void handleIoException(IOException r2, int r3) {
        ExoPlaybackException r22 = ExoPlaybackException.createForSource(r2, r3);
        MediaPeriodHolder r32 = this.queue.getPlayingPeriod();
        if (r32 == null) goto L5;
        r22 = r22.copyWithMediaPeriodId(r32.info.id);
    L5:
        Log.e("ExoPlayerImplInternal", "Playback error", r22);
        stopInternal(false, false);
        this.playbackInfo = this.playbackInfo.copyWithPlaybackError(r22);
    }

    private void handleLoadingMediaPeriodChanged(boolean r6) {
        MediaPeriodHolder r0 = this.queue.getLoadingPeriod();
        if (r0 != null) goto L5;
        MediaSource.MediaPeriodId r1 = this.playbackInfo.periodId;
    L6:
        boolean r2 = this.playbackInfo.loadingMediaPeriodId.equals(r1);
        if (r2 == true) goto L9;
        this.playbackInfo = this.playbackInfo.copyWithLoadingMediaPeriodId(r1);
    L9:
        PlaybackInfo r12 = this.playbackInfo;
        if (r0 != null) goto L12;
        long r3 = r12.positionUs;
    L13:
        r12.bufferedPositionUs = r3;
        PlaybackInfo r13 = this.playbackInfo;
        r13.totalBufferedDurationUs = getTotalBufferedDurationUs();
        if (r2 == false) goto L16;
        if (r6 == true) goto L16;
        return;
    L16:
        if (r0 != null) goto L18;
        return;
    L18:
        if (r0.prepared == false) goto L23;
        updateLoadControlTrackSelection(r0.info.id, r0.getTrackGroups(), r0.getTrackSelectorResult());
        return;
    L23:
        return;
    L12:
        r3 = r0.getBufferedPositionUs();
        goto L13
    L5:
        r1 = r0.info.id;
        goto L6
    }

    private void handleLoadingPeriodPrepared(MediaPeriodHolder r12) {
        if (r12.prepared == true) goto L5;
        float r0 = this.mediaClock.getPlaybackParameters().speed;
        PlaybackInfo r1 = this.playbackInfo;
        r12.handlePrepared(r0, r1.timeline, r1.playWhenReady);
    L5:
        updateLoadControlTrackSelection(r12.info.id, r12.getTrackGroups(), r12.getTrackSelectorResult());
        if (r12 != this.queue.getPlayingPeriod()) goto L8;
        resetRendererPosition(r12.info.startPositionUs);
        enableRenderers();
        r12.allRenderersInCorrectState = true;
        PlaybackInfo r02 = this.playbackInfo;
        MediaSource.MediaPeriodId r2 = r02.periodId;
        long r3 = r12.info.startPositionUs;
        ExoPlayerImplInternal r13 = this;
        r13.playbackInfo = r13.handlePositionDiscontinuity(r2, r3, r02.requestedContentPositionUs, r3, false, 5);
    L9:
        r13.maybeContinueLoading();
        return;
    L8:
        r13 = this;
        goto L9
    }

    /* JADX WARN: Type inference failed for: r12v0, types: [long] */
    /* JADX WARN: Type inference failed for: r12v4 */
    /* JADX WARN: Type inference failed for: r12v5 */
    /* JADX WARN: Type inference failed for: r12v6 */
    private void handleMediaSourceListInfoRefreshed(Timeline r29, boolean r30) {
        Timeline r2 = r29;
        PositionUpdateForPlaylistChange r10 = resolvePositionForPlaylistChange(r2, this.playbackInfo, this.pendingInitialSeekPosition, this.queue, this.repeatMode, this.shuffleModeEnabled, this.window, this.period);
        MediaSource.MediaPeriodId r11 = r10.periodId;
        ?? r12 = r10.requestedContentPositionUs;
        boolean r0 = r10.forceBufferingState;
        long r14 = r10.periodPositionUs;
        if (this.playbackInfo.periodId.equals(r11) == true) goto L5;
    L8:
        boolean r16 = true;
    L125:
    L15:
        th = th;
        Timeline r112 = r2;
        MediaSource.MediaPeriodId r22 = r11;
        boolean r20 = true;
        int r23 = 4;
        long r21 = r12;
        boolean r122 = false;
    L17:
        int r13 = -1;
        boolean r123 = r122;
    L97:
        PlaybackInfo r3 = this.playbackInfo;
        Timeline r4 = r3.timeline;
        MediaSource.MediaPeriodId r5 = r3.periodId;
        if (r10.setTargetLiveOffset == false) goto L100;
        long r6 = r14;
    L101:
        MediaSource.MediaPeriodId r32 = r22;
        updatePlaybackSpeedSettingsForNewPeriod(r112, r32, r4, r5, r6, false);
        if (r16 == false) goto L104;
    L105:
        PlaybackInfo r33 = this.playbackInfo;
        Object r42 = r33.periodId.periodUid;
        Timeline r34 = r33.timeline;
        if (r16 == false) goto L113;
        if (r30 == false) goto L113;
        if (r34.isEmpty() == true) goto L113;
        if (r34.getPeriodByUid(r42, this.period).isPlaceholder == true) goto L113;
        boolean r9 = r20;
    L114:
        long r7 = this.playbackInfo.discontinuityStartPositionUs;
        if (r112.getIndexOfPeriod(r42) != r13) goto L118;
        int r102 = r23;
    L119:
        this.playbackInfo = handlePositionDiscontinuity(r32, r14, r21, r7, r9, r102);
    L120:
        resetPendingPauseAtEndOfPeriod();
        resolvePendingMessagePositions(r112, this.playbackInfo.timeline);
        this.playbackInfo = this.playbackInfo.copyWithTimeline(r112);
        if (r112.isEmpty() == true) goto L123;
        this.pendingInitialSeekPosition = null;
    L123:
        handleLoadingMediaPeriodChanged(r123);
        this.handler.sendEmptyMessage(2);
        throw th;
    L118:
        r102 = 3;
    L113:
        r9 = r123;
        goto L114
    L104:
        if (r21 == this.playbackInfo.requestedContentPositionUs) goto L120;
    L100:
        r6 = -9223372036854775807L;
        goto L101
    L11:
        if (r10.endPlayback == true) goto L13;
    L19:
        RendererHolder[] r92 = this.renderers;     // Catch: Throwable -> L15
        int r35 = r92.length;     // Catch: Throwable -> L15
        int r43 = 0;
    L21:
        if (r43 >= r35) goto L131;
        r92[r43].setTimeline(r2);     // Catch: Throwable -> L24
        r43 = r43 + 1;
    L24:
        th = th;
        r112 = r2;
        r22 = r11;
        r23 = 4;
        r21 = r12;
        r122 = false;
        r20 = true;
        goto L17
    L131:
        if (r16 == false) goto L129;
        int r24 = 2;
        r23 = 4;
        r21 = r12;
        boolean r124 = false;
        r124 = false;
        r20 = true;
        r13 = -1;
        if (r2.isEmpty() == true) goto L69;
        MediaPeriodHolder r36 = this.queue.getPlayingPeriod();     // Catch: Throwable -> L45
    L63:
        if (r36 == null) goto L68;
        if (r36.info.id.equals(r11) == false) goto L67;
        r36.info = this.queue.getUpdatedMediaPeriodInfo(r2, r36.info);     // Catch: Throwable -> L45
        r36.updateClipping();     // Catch: Throwable -> L45
    L67:
        r36 = r36.getNext();     // Catch: Throwable -> L45
        goto L63
    L68:
        r14 = seekToPeriodPosition(r11, r14, r0);     // Catch: Throwable -> L45
    L69:
        PlaybackInfo r02 = this.playbackInfo;
        Timeline r44 = r02.timeline;
        MediaSource.MediaPeriodId r52 = r02.periodId;
        if (r10.setTargetLiveOffset == false) goto L72;
        long r62 = r14;
    L73:
        updatePlaybackSpeedSettingsForNewPeriod(r2, r11, r44, r52, r62, false);
        Timeline r113 = r2;
        if (r16 == false) goto L76;
    L77:
        PlaybackInfo r03 = this.playbackInfo;
        Object r37 = r03.periodId.periodUid;
        Timeline r04 = r03.timeline;
        if (r16 == false) goto L85;
        if (r30 == false) goto L85;
        if (r04.isEmpty() == true) goto L85;
        if (r04.getPeriodByUid(r37, this.period).isPlaceholder == true) goto L85;
        boolean r93 = r20;
    L86:
        long r72 = this.playbackInfo.discontinuityStartPositionUs;
        if (r113.getIndexOfPeriod(r37) != r13) goto L90;
        int r103 = r23;
    L91:
        this.playbackInfo = handlePositionDiscontinuity(r11, r14, r21, r72, r93, r103);
    L92:
        resetPendingPauseAtEndOfPeriod();
        resolvePendingMessagePositions(r113, this.playbackInfo.timeline);
        this.playbackInfo = this.playbackInfo.copyWithTimeline(r113);
        if (r113.isEmpty() == true) goto L95;
        this.pendingInitialSeekPosition = null;
    L95:
        handleLoadingMediaPeriodChanged(r124);
        this.handler.sendEmptyMessage(r24);
        return;
    L90:
        r103 = 3;
    L85:
        r93 = r124;
        goto L86
    L76:
        if (r21 == this.playbackInfo.requestedContentPositionUs) goto L92;
    L72:
        r62 = -9223372036854775807L;
        goto L73
    L129:
        long r38 = 0;
        if (this.queue.getReadingPeriod() != null) goto L30;
        long r222 = 0;
    L31:
        if (areRenderersPrewarming() == true) goto L34;
    L134:
        r24 = 2;
        r20 = true;
        r23 = 4;
        r21 = r12;
        r12 = 0;
        r124 = false;
        r124 = false;
        r124 = false;
        r13 = -1;
        int r05 = this.queue.updateQueuedPeriods(r29, this.rendererPositionUs, r222, r38);     // Catch: Throwable -> L50
        r2 = r29;
        if ((r05 & 1) == 0) goto L48;
        seekToCurrentPosition(false);     // Catch: Throwable -> L45
        goto L69
    L48:
        if ((r05 & 2) == 0) goto L69;
        disableAndResetPrewarmingRenderers();     // Catch: Throwable -> L45
    L50:
        th = th;
        r2 = r29;
    L46:
        r112 = r2;
        r22 = r11;
        r123 = r12;
    L52:
        th = th;
        r2 = r29;
        r23 = 4;
    L54:
        r21 = r12;
        r12 = 0;
        r20 = true;
        r13 = -1;
    L55:
        th = th;
        r2 = r29;
    L57:
        r23 = 4;
        goto L54
    L34:
        if (this.queue.getPrewarmingPeriod() == null) goto L134;
        r38 = getMaxRendererReadPositionUs(this.queue.getPrewarmingPeriod());     // Catch: Throwable -> L24
        goto L134
    L30:
        r222 = getMaxRendererReadPositionUs(this.queue.getReadingPeriod());     // Catch: Throwable -> L58
    L58:
        th = th;
    L45:
        th = th;
        goto L46
    L13:
        if (this.playbackInfo.playbackState == 1) goto L18;
        setState(4);     // Catch: Throwable -> L15
    L18:
        resetInternal(false, false, false, true);     // Catch: Throwable -> L15
        goto L19
    L5:
        if (r14 != this.playbackInfo.positionUs) goto L8;
        r16 = false;
        goto L125
    }

    private void handlePeriodPrepared(MediaPeriod r5) {
        boolean r0 = this.queue.isLoading(r5);
        MediaPeriodQueue r1 = this.queue;
        if (r0 == false) goto L6;
        handleLoadingPeriodPrepared((MediaPeriodHolder) Assertions.checkNotNull(r1.getLoadingPeriod()));
        return;
    L6:
        MediaPeriodHolder r02 = r1.getPreloadHolderByMediaPeriod(r5);
        if (r02 == null) goto L12;
        Assertions.checkState(!r02.prepared);
        float r12 = this.mediaClock.getPlaybackParameters().speed;
        PlaybackInfo r2 = this.playbackInfo;
        r02.handlePrepared(r12, r2.timeline, r2.playWhenReady);
        if (this.queue.isPreloading(r5) == false) goto L13;
        maybeContinuePreloading();
        return;
    L13:
        return;
    }

    private void handlePlaybackParameters(PlaybackParameters r3, float r4, boolean r5, boolean r6) {
        if (r5 == false) goto L6;
        if (r6 == false) goto L5;
        this.playbackInfoUpdate.incrementPendingOperationAcks(1);
    L5:
        this.playbackInfo = this.playbackInfo.copyWithPlaybackParameters(r3);
    L6:
        updateTrackSelectionPlaybackSpeed(r3.speed);
        RendererHolder[] r2 = this.renderers;
        int r52 = r2.length;
        int r62 = 0;
    L7:
        if (r62 >= r52) goto L9;
        r2[r62].setPlaybackSpeed(r4, r3.speed);
        r62 = r62 + 1;
        goto L7
    }

    private void handlePlaybackParameters(PlaybackParameters r3, boolean r4) {
        handlePlaybackParameters(r3, r3.speed, true, r4);
    }

    private PlaybackInfo handlePositionDiscontinuity(MediaSource.MediaPeriodId r14, long r15, long r17, long r19, boolean r21, int r22) {
        if (this.deliverPendingMessageAtStartPositionRequired == false) goto L5;
    L10:
        boolean r0 = true;
    L11:
        this.deliverPendingMessageAtStartPositionRequired = r0;
        resetPendingPauseAtEndOfPeriod();
        PlaybackInfo r02 = this.playbackInfo;
        TrackGroupArray r1 = r02.trackGroups;
        TrackSelectorResult r2 = r02.trackSelectorResult;
        List r03 = r02.staticMetadata;
        if (this.mediaSourceList.isPrepared() == false) goto L27;
        MediaPeriodHolder r04 = this.queue.getPlayingPeriod();
        if (r04 != null) goto L16;
        TrackGroupArray r12 = TrackGroupArray.EMPTY;
    L17:
        if (r04 != null) goto L19;
        TrackSelectorResult r23 = this.emptyTrackSelectorResult;
    L20:
        List r3 = extractMetadataFromTrackSelectionArray(r23.selections);
        if (r04 == null) goto L25;
        MediaPeriodInfo r6 = r04.info;
        if (r6.requestedContentPositionUs == r17) goto L25;
        r04.info = r6.copyWithRequestedContentPositionUs(r17);
    L25:
        maybeUpdateOffloadScheduling();
        TrackGroupArray r10 = r12;
        TrackSelectorResult r11 = r23;
        List r122 = r3;
    L30:
        if (r21 == false) goto L33;
        this.playbackInfoUpdate.setPositionDiscontinuity(r22);
    L33:
        return this.playbackInfo.copyWithNewPosition(r14, r15, r17, r19, getTotalBufferedDurationUs(), r10, r11, r122);
    L19:
        r23 = r04.getTrackSelectorResult();
        goto L20
    L16:
        r12 = r04.getTrackGroups();
        goto L17
    L27:
        if (r14.equals(this.playbackInfo.periodId) == true) goto L29;
        r1 = TrackGroupArray.EMPTY;
        r2 = this.emptyTrackSelectorResult;
        r03 = ImmutableList.of();
    L29:
        r122 = r03;
        r10 = r1;
        r11 = r2;
        goto L30
    L5:
        if (r15 != this.playbackInfo.positionUs) goto L10;
        if (r14.equals(this.playbackInfo.periodId) == false) goto L10;
        r0 = false;
        goto L11
    }

    private boolean hasReadingPeriodFinishedReading() {
        MediaPeriodHolder r0 = this.queue.getReadingPeriod();
        if (r0.prepared == true) goto L5;
        return false;
    L5:
        int r1 = 0;
    L6:
        RendererHolder[] r3 = this.renderers;
        if (r1 >= r3.length) goto L12;
        if (r3[r1].hasFinishedReadingFromPeriod(r0) == false) goto L10;
        r1 = r1 + 1;
        goto L6
    L10:
        return false;
    L12:
        return true;
    }

    private boolean isDynamicSchedulingEnabled() {
        if (this.dynamicSchedulingEnabled == false) goto L5;
        return true;
    L5:
        if (this.scrubbingModeEnabled == true) goto L7;
        return false;
    L7:
        if (this.scrubbingModeParameters.shouldEnableDynamicScheduling == true) goto L14;
        return false;
    L14:
        return true;
    }

    private static boolean isIgnorableServerSideAdInsertionPeriodChange(boolean r1, MediaSource.MediaPeriodId r2, long r3, MediaSource.MediaPeriodId r5, Timeline.Period r6, long r7) {
        if (r1 == false) goto L5;
    L24:
        return false;
    L5:
        if (r3 != r7) goto L24;
        if (r2.periodUid.equals(r5.periodUid) == false) goto L24;
        if (r2.isAd() == false) goto L20;
        if (r6.isServerSideInsertedAdGroup(r2.adGroupIndex) == false) goto L20;
        if (r6.getAdState(r2.adGroupIndex, r2.adIndexInAdGroup) != 4) goto L16;
    L18:
        return false;
    L16:
        if (r6.getAdState(r2.adGroupIndex, r2.adIndexInAdGroup) == 2) goto L18;
        return true;
    L20:
        if (r5.isAd() == false) goto L24;
        if (r6.isServerSideInsertedAdGroup(r5.adGroupIndex) == false) goto L24;
        return true;
    }

    private boolean isLoadingPossible(MediaPeriodHolder r3) {
        if (r3 != null) goto L4;
        return false;
    L4:
        if (r3.hasLoadingError() == false) goto L6;
        return false;
    L6:
        if (r3.getNextLoadPositionUs() == Long.MIN_VALUE) goto L12;
        return true;
    L12:
        return false;
    }

    private boolean isRendererPrewarmingMediaPeriod(int r2, MediaSource.MediaPeriodId r3) {
        if (this.queue.getPrewarmingPeriod() != null) goto L5;
        return false;
    L5:
        if (this.queue.getPrewarmingPeriod().info.id.equals(r3) == true) goto L8;
        return false;
    L8:
        return this.renderers[r2].isPrewarmingPeriod(this.queue.getPrewarmingPeriod());
    }

    private boolean isTimelineReady() {
        MediaPeriodHolder r0 = this.queue.getPlayingPeriod();
        long r1 = r0.info.durationUs;
        if (r0.prepared == true) goto L5;
        return false;
    L5:
        if (r1 != (-9223372036854775807L)) goto L7;
        return true;
    L7:
        if (this.playbackInfo.positionUs >= r1) goto L9;
        return true;
    L9:
        if (shouldPlayWhenReady() == true) goto L16;
        return true;
    L16:
        return false;
    }

    private static boolean isUsingPlaceholderPeriod(PlaybackInfo r2, Timeline.Period r3) {
        MediaSource.MediaPeriodId r0 = r2.periodId;
        Timeline r22 = r2.timeline;
        if (r22.isEmpty() == false) goto L5;
        return true;
    L5:
        if (r22.getPeriodByUid(r0.periodUid, r3).isPlaceholder == true) goto L11;
        return false;
    L11:
        return true;
    }

    private void maybeContinueLoading() {
        boolean r0 = shouldContinueLoading();
        this.shouldContinueLoading = r0;
        if (r0 == false) goto L5;
        MediaPeriodHolder r02 = (MediaPeriodHolder) Assertions.checkNotNull(this.queue.getLoadingPeriod());
        r02.continueLoading(new LoadingInfo.Builder().setPlaybackPositionUs(r02.toPeriodTime(this.rendererPositionUs)).setPlaybackSpeed(this.mediaClock.getPlaybackParameters().speed).setLastRebufferRealtimeMs(this.lastRebufferRealtimeMs).build());
    L5:
        updateIsLoading();
    }

    private void maybeContinuePreloading() {
        this.queue.maybeUpdatePreloadMediaPeriodHolder();
        MediaPeriodHolder r0 = this.queue.getPreloadingPeriod();
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.prepareCalled == false) goto L9;
        if (r0.prepared == true) goto L9;
        return;
    L9:
        if (r0.mediaPeriod.isLoading() == true) goto L25;
        LoadControl r1 = this.loadControl;
        Timeline r2 = this.playbackInfo.timeline;
        MediaSource.MediaPeriodId r3 = r0.info.id;
        if (r0.prepared == false) goto L13;
        long r4 = r0.mediaPeriod.getBufferedPositionUs();
    L15:
        if (r1.shouldContinuePreloading(r2, r3, r4) == true) goto L18;
        return;
    L18:
        if (r0.prepareCalled == true) goto L21;
        r0.prepare(this, r0.info.startPositionUs);
        return;
    L21:
        r0.continueLoading(new LoadingInfo.Builder().setPlaybackPositionUs(r0.toPeriodTime(this.rendererPositionUs)).setPlaybackSpeed(this.mediaClock.getPlaybackParameters().speed).setLastRebufferRealtimeMs(this.lastRebufferRealtimeMs).build());
        return;
    L13:
        r4 = 0;
        goto L15
    }

    private void maybeHandlePrewarmingTransition() {
        RendererHolder[] r3 = this.renderers;
        int r0 = r3.length;
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        r3[r1].maybeHandlePrewarmingTransition();
        r1 = r1 + 1;
        goto L3
    }

    private void maybeNotifyPlaybackInfoChanged() {
        this.playbackInfoUpdate.setPlaybackInfo(this.playbackInfo);
        if (PlaybackInfoUpdate.access$100(this.playbackInfoUpdate) == false) goto L6;
        this.playbackInfoUpdateListener.onPlaybackInfoUpdate(this.playbackInfoUpdate);
        this.playbackInfoUpdate = new PlaybackInfoUpdate(this.playbackInfo);
        return;
    }

    private void maybePrewarmRenderers() {
        MediaPeriodHolder r2 = this.queue.getPrewarmingPeriod();
        if (r2 == null) goto L28;
        TrackSelectorResult r0 = r2.getTrackSelectorResult();
        int r3 = 0;
    L7:
        if (r3 >= this.renderers.length) goto L17;
        if (r0.isRendererEnabled(r3) == true) goto L11;
    L15:
        ExoPlayerImplInternal r1 = this;
    L16:
        r3 = r3 + 1;
        ExoPlayerImplInternal this = r1;
        goto L7
    L11:
        if (this.renderers[r3].hasSecondary() == false) goto L15;
        if (this.renderers[r3].isPrewarming() == true) goto L15;
        this.renderers[r3].startPrewarming();
        r1 = this;
        r1.enableRenderer(r2, r3, false, r2.getStartPositionRendererTime());
        goto L16
    L17:
        ExoPlayerImplInternal r12 = this;
        if (r12.areRenderersPrewarming() == false) goto L26;
        r12.prewarmingMediaPeriodDiscontinuity = r2.mediaPeriod.readDiscontinuity();
        if (r2.isFullyBuffered() == true) goto L27;
        r12.queue.removeAfter(r2);
        r12.handleLoadingMediaPeriodChanged(false);
        r12.maybeContinueLoading();
        return;
    L27:
        return;
    L26:
        return;
    }

    private void maybeThrowRendererStreamError(int r6) {
        RendererHolder r0 = this.renderers[r6];
        r0.maybeThrowStreamError((MediaPeriodHolder) Assertions.checkNotNull(this.queue.getPlayingPeriod()));     // Catch: RuntimeException -> L5 Throwable -> L7
        return;
    L7:
        e = move-exception;
        int r02 = r0.getTrackType();
        if (r02 != 3) goto L11;
    L14:
        TrackSelectorResult r03 = this.queue.getPlayingPeriod().getTrackSelectorResult();
        Log.e("ExoPlayerImplInternal", "Disabling track due to error: " + Format.toLogString(r03.selections[r6].getSelectedFormat()), e);
        TrackSelectorResult r1 = new TrackSelectorResult((RendererConfiguration[]) r03.rendererConfigurations.clone(), (ExoTrackSelection[]) r03.selections.clone(), r03.tracks, r03.info);
        r1.rendererConfigurations[r6] = null;
        r1.selections[r6] = null;
        disableRenderer(r6);
        this.queue.getPlayingPeriod().applyTrackSelection(r1, this.playbackInfo.positionUs, false);
        return;
    L11:
        if (r02 == 5) goto L14;
        throw e;
    }

    private void maybeTriggerOnRendererReadyChanged(int r3, boolean r4) {
        boolean[] r0 = this.rendererReportedReady;
        if (r0[r3] == r4) goto L6;
        r0[r3] = r4;
        this.applicationLooperHandler.post(new ExoPlayerImplInternal$$ExternalSyntheticLambda0(this, r3, r4));
        return;
    }

    private void maybeTriggerPendingMessages(long r9, long r11) {
        if (this.pendingMessages.isEmpty() == false) goto L5;
        return;
    L5:
        if (this.playbackInfo.periodId.isAd() == false) goto L8;
        return;
    L8:
        if (this.deliverPendingMessageAtStartPositionRequired == false) goto L10;
        r9 = r9 - 1;
        this.deliverPendingMessageAtStartPositionRequired = false;
    L10:
        PlaybackInfo r0 = this.playbackInfo;
        int r02 = r0.timeline.getIndexOfPeriod(r0.periodId.periodUid);
        int r1 = Math.min(this.nextPendingMessageIndexHint, this.pendingMessages.size());
        if (r1 <= 0) goto L13;
        PendingMessageInfo r3 = this.pendingMessages.get(r1 - 1);
    L14:
        if (r3 == null) goto L26;
        int r4 = r3.resolvedPeriodIndex;
        if (r4 > r02) goto L20;
        if (r4 != r02) goto L26;
        if (r3.resolvedPeriodTimeUs <= r9) goto L26;
    L20:
        int r32 = r1 - 1;
        if (r32 <= 0) goto L23;
        PendingMessageInfo r12 = this.pendingMessages.get(r1 - 2);
    L24:
        r3 = r12;
        r1 = r32;
        goto L14
    L23:
        r12 = null;
    L26:
        if (r1 >= this.pendingMessages.size()) goto L28;
        PendingMessageInfo r33 = this.pendingMessages.get(r1);
    L29:
        if (r33 == null) goto L40;
        if (r33.resolvedPeriodUid == null) goto L40;
        int r42 = r33.resolvedPeriodIndex;
        if (r42 < r02) goto L37;
        if (r42 != r02) goto L40;
        if (r33.resolvedPeriodTimeUs > r9) goto L40;
    L37:
        r1 = r1 + 1;
        if (r1 >= this.pendingMessages.size()) goto L28;
        r33 = this.pendingMessages.get(r1);
        goto L29
    L28:
        r33 = null;
    L40:
        if (r33 == null) goto L68;
        if (r33.resolvedPeriodUid == null) goto L68;
        if (r33.resolvedPeriodIndex != r02) goto L68;
        long r43 = r33.resolvedPeriodTimeUs;
        if (r43 <= r9) goto L68;
        if (r43 > r11) goto L68;
        sendMessageToTarget(r33.message);     // Catch: Throwable -> L61
        if (r33.message.getDeleteAfterDelivery() == false) goto L53;
    L56:
        this.pendingMessages.remove(r1);
    L58:
        if (r1 < this.pendingMessages.size()) goto L59;
        r33 = null;
        goto L40
    L59:
        r33 = this.pendingMessages.get(r1);
        goto L40
    L53:
        if (r33.message.isCanceled() == true) goto L56;
        r1 = r1 + 1;
    L61:
        th = move-exception;
        if (r33.message.getDeleteAfterDelivery() == false) goto L65;
    L66:
        this.pendingMessages.remove(r1);
    L67:
        throw th;
    L65:
        if (r33.message.isCanceled() == false) goto L67;
    L68:
        this.nextPendingMessageIndexHint = r1;
        return;
    L72:
        r33 = null;
        goto L29
    L13:
        r3 = null;
        goto L14
    }

    private boolean maybeUpdateLoadingPeriod() {
        this.queue.reevaluateBuffer(this.rendererPositionUs);
        boolean r1 = false;
        if (this.queue.shouldLoadNextMediaPeriod() == false) goto L17;
        MediaPeriodInfo r0 = this.queue.getNextMediaPeriodInfo(this.rendererPositionUs, this.playbackInfo);
        if (r0 == null) goto L17;
        MediaPeriodHolder r2 = this.queue.enqueueNextMediaPeriodHolder(r0);
        if (r2.prepareCalled == true) goto L10;
        r2.prepare(this, r0.startPositionUs);
    L13:
        if (this.queue.getPlayingPeriod() != r2) goto L15;
        resetRendererPosition(r0.startPositionUs);
    L15:
        handleLoadingMediaPeriodChanged(false);
        r1 = true;
        goto L17
    L10:
        if (r2.prepared == false) goto L13;
        this.handler.obtainMessage(8, r2.mediaPeriod).sendToTarget();
    L17:
        if (this.shouldContinueLoading == false) goto L20;
        this.shouldContinueLoading = isLoadingPossible(this.queue.getLoadingPeriod());
        updateIsLoading();
        return r1;
    L20:
        maybeContinueLoading();
        return r1;
    }

    private void maybeUpdateOffloadScheduling() {
        if (this.queue.getPlayingPeriod() != this.queue.getReadingPeriod()) goto L31;
        MediaPeriodHolder r0 = this.queue.getPlayingPeriod();
        if (r0 == null) goto L30;
        TrackSelectorResult r02 = r0.getTrackSelectorResult();
        boolean r1 = false;
        int r2 = 0;
        boolean r3 = false;
    L9:
        if (r2 >= this.renderers.length) goto L19;
        if (r02.isRendererEnabled(r2) == false) goto L18;
        if (this.renderers[r2].getTrackType() != 1) goto L14;
        if (r02.rendererConfigurations[r2].offloadModePreferred == 0) goto L18;
        r3 = true;
        goto L18
    L14:
        boolean r03 = false;
    L20:
        if (r3 == false) goto L23;
        if (r03 == false) goto L23;
        r1 = true;
    L23:
        setOffloadSchedulingEnabled(r1);
        return;
    L18:
        r2 = r2 + 1;
        goto L9
    L19:
        r03 = true;
        goto L20
    L30:
        return;
    }

    private void maybeUpdatePlayingPeriod() {
        boolean r1 = false;
    L4:
        if (this.shouldAdvancePlayingPeriod() == false) goto L26;
        if (r1 == false) goto L7;
        this.maybeNotifyPlaybackInfoChanged();
    L7:
        this.isPrewarmingDisabledUntilNextTransition = false;
        MediaPeriodHolder r12 = (MediaPeriodHolder) Assertions.checkNotNull(this.queue.advancePlayingPeriod());
        if (this.playbackInfo.periodId.periodUid.equals(r12.info.id.periodUid) == false) goto L16;
        MediaSource.MediaPeriodId r2 = this.playbackInfo.periodId;
        if (r2.adGroupIndex != (-1)) goto L16;
        MediaSource.MediaPeriodId r4 = r12.info.id;
        if (r4.adGroupIndex != (-1)) goto L16;
        if (r2.nextAdGroupIndex == r4.nextAdGroupIndex) goto L16;
        boolean r22 = true;
    L17:
        MediaPeriodInfo r42 = r12.info;
        MediaSource.MediaPeriodId r6 = r42.id;
        long r7 = r42.startPositionUs;
        ExoPlayerImplInternal r5 = this;
        r5.playbackInfo = r5.handlePositionDiscontinuity(r6, r7, r42.requestedContentPositionUs, r7, !r22, 0);
        r5.resetPendingPauseAtEndOfPeriod();
        r5.updatePlaybackPositions();
        if (r5.areRenderersPrewarming() == false) goto L23;
        if (r12 != r5.queue.getPrewarmingPeriod()) goto L23;
        r5.maybeHandlePrewarmingTransition();
    L23:
        if (r5.playbackInfo.playbackState != 3) goto L25;
        r5.startRenderers();
    L25:
        r5.allowRenderersToRenderStartOfStreams();
        r1 = true;
        ExoPlayerImplInternal this = r5;
    L16:
        r22 = false;
        goto L17
    }

    private void maybeUpdatePreloadPeriods(boolean r5) {
        if (this.preloadConfiguration.targetPreloadDurationUs != (-9223372036854775807L)) goto L5;
        return;
    L5:
        if (r5 == false) goto L7;
    L8:
        Timeline r52 = this.playbackInfo.timeline;
        this.lastPreloadPoolInvalidationTimeline = r52;
        this.queue.invalidatePreloadPool(r52);
    L9:
        maybeContinuePreloading();
        return;
    L7:
        if (this.playbackInfo.timeline.equals(this.lastPreloadPoolInvalidationTimeline) == true) goto L9;
        goto L8
    }

    private void maybeUpdatePrewarmingPeriod() {
        if (this.pendingPauseAtEndOfPeriod == false) goto L5;
        return;
    L5:
        if (this.hasSecondaryRenderers == true) goto L7;
        return;
    L7:
        if (this.isPrewarmingDisabledUntilNextTransition == false) goto L9;
        return;
    L9:
        if (areRenderersPrewarming() == true) goto L28;
        MediaPeriodHolder r0 = this.queue.getPrewarmingPeriod();
        if (r0 != null) goto L14;
        return;
    L14:
        if (r0 == this.queue.getReadingPeriod()) goto L16;
        return;
    L16:
        if (r0.getNext() != null) goto L18;
        return;
    L18:
        if (r0.getNext().prepared == false) goto L29;
        this.queue.advancePrewarmingPeriod();
        maybePrewarmRenderers();
        return;
    L29:
        return;
    }

    private void maybeUpdateReadingPeriod() {
        MediaPeriodHolder r1 = this.queue.getReadingPeriod();
        if (r1 == null) goto L92;
        int r10 = 0;
        if (r1.getNext() == null) goto L61;
        if (this.pendingPauseAtEndOfPeriod == true) goto L61;
        if (hasReadingPeriodFinishedReading() == true) goto L14;
        return;
    L14:
        if (areRenderersPrewarming() == false) goto L19;
        if (this.queue.getPrewarmingPeriod() != this.queue.getReadingPeriod()) goto L19;
        return;
    L19:
        if (r1.getNext().prepared == false) goto L21;
    L23:
        TrackSelectorResult r11 = r1.getTrackSelectorResult();
        MediaPeriodHolder r12 = this.queue.advanceReadingPeriod();
        TrackSelectorResult r13 = r12.getTrackSelectorResult();
        Timeline r2 = this.playbackInfo.timeline;
        updatePlaybackSpeedSettingsForNewPeriod(r2, r12.info.id, r2, r1.info.id, -9223372036854775807L, false);
        if (r12.prepared == true) goto L26;
    L57:
        RendererHolder[] r0 = this.renderers;
        int r14 = r0.length;
    L58:
        if (r10 >= r14) goto L96;
        r0[r10].maybeSetOldStreamToFinal(r11, r13, r12.getStartPositionRendererTime());
        r10 = r10 + 1;
        goto L58
    L96:
        return;
    L26:
        if (this.hasSecondaryRenderers == false) goto L30;
        if (this.prewarmingMediaPeriodDiscontinuity == (-9223372036854775807L)) goto L30;
    L31:
        this.prewarmingMediaPeriodDiscontinuity = -9223372036854775807L;
        if (this.hasSecondaryRenderers == true) goto L34;
    L36:
        boolean r15 = false;
    L37:
        if (r15 == false) goto L52;
        int r22 = 0;
    L40:
        if (r22 >= this.renderers.length) goto L52;
        if (r13.isRendererEnabled(r22) == false) goto L51;
        if (this.renderers[r22].getTrackType() == (-2)) goto L51;
        if (MimeTypes.allSamplesAreSyncSamples(r13.selections[r22].getSelectedFormat().sampleMimeType, r13.selections[r22].getSelectedFormat().codecs) == true) goto L51;
        if (this.renderers[r22].isPrewarming() == true) goto L51;
        r15 = false;
    L51:
        r22 = r22 + 1;
    L52:
        if (r15 == true) goto L57;
        setAllNonPrewarmingRendererStreamsFinal(r12.getStartPositionRendererTime());
        if (r12.isFullyBuffered() == true) goto L79;
        this.queue.removeAfter(r12);
        handleLoadingMediaPeriodChanged(false);
        maybeContinueLoading();
        return;
    L79:
        return;
    L34:
        if (this.isPrewarmingDisabledUntilNextTransition == true) goto L36;
        r15 = true;
    L30:
        if (r12.mediaPeriod.readDiscontinuity() == (-9223372036854775807L)) goto L57;
    L21:
        if (this.rendererPositionUs >= r1.getNext().getStartPositionRendererTime()) goto L23;
        return;
    L61:
        if (r1.info.isFinal == false) goto L63;
    L64:
        RendererHolder[] r02 = this.renderers;
        int r23 = r02.length;
    L65:
        if (r10 >= r23) goto L97;
        RendererHolder r3 = r02[r10];
        if (r3.isReadingFromPeriod(r1) == false) goto L78;
        if (r3.hasReadPeriodToEnd(r1) == false) goto L78;
        long r4 = r1.info.durationUs;
        if (r4 != (-9223372036854775807L)) goto L74;
    L76:
        long r42 = -9223372036854775807L;
    L77:
        r3.setCurrentStreamFinal(r1, r42);
        goto L78
    L74:
        if (r4 == Long.MIN_VALUE) goto L76;
        r42 = r1.getRendererOffset() + r1.info.durationUs;
    L78:
        r10 = r10 + 1;
        goto L65
    L97:
        return;
    L63:
        if (this.pendingPauseAtEndOfPeriod == true) goto L64;
        return;
    }

    private void maybeUpdateReadingRenderers() {
        MediaPeriodHolder r0 = this.queue.getReadingPeriod();
        if (r0 != null) goto L5;
        return;
    L5:
        if (this.queue.getPlayingPeriod() != r0) goto L7;
        return;
    L7:
        if (r0.allRenderersInCorrectState == false) goto L10;
        return;
    L10:
        if (updateRenderersForTransition() == false) goto L15;
        this.queue.getReadingPeriod().allRenderersInCorrectState = true;
        return;
    }

    private void mediaSourceListUpdateRequestedInternal() {
        handleMediaSourceListInfoRefreshed(this.mediaSourceList.createTimeline(), true);
    }

    private void moveMediaItemsInternal(MoveMediaItemsMessage r5) {
        this.playbackInfoUpdate.incrementPendingOperationAcks(1);
        handleMediaSourceListInfoRefreshed(this.mediaSourceList.moveMediaSourceRange(r5.fromIndex, r5.toIndex, r5.newFromIndex, r5.shuffleOrder), false);
    }

    private void notifyTrackSelectionDiscontinuity() {
        MediaPeriodHolder r4 = this.queue.getPlayingPeriod();
    L3:
        if (r4 == null) goto L11;
        ExoTrackSelection[] r0 = r4.getTrackSelectorResult().selections;
        int r1 = r0.length;
        int r2 = 0;
    L5:
        if (r2 >= r1) goto L10;
        ExoTrackSelection r3 = r0[r2];
        if (r3 == null) goto L9;
        r3.onDiscontinuity();
    L9:
        r2 = r2 + 1;
        goto L5
    L10:
        r4 = r4.getNext();
        goto L3
    }

    private void notifyTrackSelectionPlayWhenReadyChanged(boolean r5) {
        MediaPeriodHolder r4 = this.queue.getPlayingPeriod();
    L3:
        if (r4 == null) goto L11;
        ExoTrackSelection[] r0 = r4.getTrackSelectorResult().selections;
        int r1 = r0.length;
        int r2 = 0;
    L5:
        if (r2 >= r1) goto L10;
        ExoTrackSelection r3 = r0[r2];
        if (r3 == null) goto L9;
        r3.onPlayWhenReadyChanged(r5);
    L9:
        r2 = r2 + 1;
        goto L5
    L10:
        r4 = r4.getNext();
        goto L3
    }

    private void notifyTrackSelectionRebuffer() {
        MediaPeriodHolder r4 = this.queue.getPlayingPeriod();
    L3:
        if (r4 == null) goto L11;
        ExoTrackSelection[] r0 = r4.getTrackSelectorResult().selections;
        int r1 = r0.length;
        int r2 = 0;
    L5:
        if (r2 >= r1) goto L10;
        ExoTrackSelection r3 = r0[r2];
        if (r3 == null) goto L9;
        r3.onRebuffer();
    L9:
        r2 = r2 + 1;
        goto L5
    L10:
        r4 = r4.getNext();
        goto L3
    }

    private void prepareInternal() {
        this.playbackInfoUpdate.incrementPendingOperationAcks(1);
        resetInternal(false, false, false, true);
        this.loadControl.onPrepared(this.playerId);
        if (this.playbackInfo.timeline.isEmpty() == false) goto L5;
        int r0 = 4;
    L6:
        setState(r0);
        updatePlayWhenReadyWithAudioFocus();
        this.mediaSourceList.prepare(this.bandwidthMeter.getTransferListener());
        this.handler.sendEmptyMessage(2);
        return;
    L5:
        r0 = 2;
        goto L6
    }

    private void releaseInternal(ConditionVariable r5) {
        resetInternal(true, false, true, false);     // Catch: Throwable -> L6
        releaseRenderers();     // Catch: Throwable -> L6
        this.loadControl.onReleased(this.playerId);     // Catch: Throwable -> L6
        this.audioFocusManager.release();     // Catch: Throwable -> L6
        this.trackSelector.release();     // Catch: Throwable -> L6
        setState(1);     // Catch: Throwable -> L6
        this.handler.removeCallbacksAndMessages(null);
        this.playbackLooperProvider.releaseLooper();
        r5.open();
        return;
    L6:
        th = move-exception;
        this.handler.removeCallbacksAndMessages(null);
        this.playbackLooperProvider.releaseLooper();
        r5.open();
        throw th;
    }

    private void releaseRenderers() {
        int r0 = 0;
    L4:
        if (r0 >= this.renderers.length) goto L6;
        this.rendererCapabilities[r0].clearListener();
        this.renderers[r0].release();
        r0 = r0 + 1;
        goto L4
    }

    private void removeMediaItemsInternal(int r3, int r4, ShuffleOrder r5) {
        this.playbackInfoUpdate.incrementPendingOperationAcks(1);
        handleMediaSourceListInfoRefreshed(this.mediaSourceList.removeMediaSourceRange(r3, r4, r5), false);
    }

    private void reselectTracksInternal() {
        float r1 = this.mediaClock.getPlaybackParameters().speed;
        MediaPeriodHolder r2 = this.queue.getPlayingPeriod();
        MediaPeriodHolder r3 = this.queue.getReadingPeriod();
        TrackSelectorResult r4 = null;
        boolean r5 = true;
    L3:
        if (r2 == null) goto L61;
        if (r2.prepared == false) goto L62;
        PlaybackInfo r6 = this.playbackInfo;
        TrackSelectorResult r62 = r2.selectTracks(r1, r6.timeline, r6.playWhenReady);
        if (r2 != this.queue.getPlayingPeriod()) goto L11;
        r4 = r62;
    L11:
        if (r62.isEquivalent(r2.getTrackSelectorResult()) == false) goto L12;
        if (r2 != r3) goto L51;
        r5 = false;
    L51:
        r2 = r2.getNext();
        goto L3
    L12:
        MediaPeriodQueue r12 = this.queue;
        if (r5 == false) goto L35;
        MediaPeriodHolder r13 = r12.getPlayingPeriod();
        if ((this.queue.removeAfter(r13) & 1) == 0) goto L17;
        boolean r17 = true;
    L18:
        boolean[] r14 = new boolean[this.renderers.length];
        long r22 = r13.applyTrackSelection((TrackSelectorResult) Assertions.checkNotNull(r4), this.playbackInfo.positionUs, r17, r14);
        PlaybackInfo r15 = this.playbackInfo;
        if (r15.playbackState != 4) goto L21;
    L23:
        boolean r8 = false;
    L24:
        PlaybackInfo r16 = this.playbackInfo;
        this.playbackInfo = handlePositionDiscontinuity(r16.periodId, r22, r16.requestedContentPositionUs, r16.discontinuityStartPositionUs, r8, 5);
        if (r8 == false) goto L27;
        resetRendererPosition(r22);
    L27:
        disableAndResetPrewarmingRenderers();
        boolean[] r18 = new boolean[this.renderers.length];
        int r23 = 0;
    L28:
        RendererHolder[] r32 = this.renderers;
        if (r23 >= r32.length) goto L34;
        int r33 = r32[r23].getEnabledRendererCount();
        r18[r23] = this.renderers[r23].isRendererEnabled();
        this.renderers[r23].maybeDisableOrResetPosition(r13.sampleStreams[r23], this.mediaClock, this.rendererPositionUs, r14[r23]);
        if ((r33 - this.renderers[r23].getEnabledRendererCount()) <= 0) goto L33;
        maybeTriggerOnRendererReadyChanged(r23, false);
    L33:
        this.enabledRendererCount -= r33 - this.renderers[r23].getEnabledRendererCount();
        r23 = r23 + 1;
        goto L28
    L34:
        enableRenderers(r18, this.rendererPositionUs);
        r13.allRenderersInCorrectState = true;
    L45:
        handleLoadingMediaPeriodChanged(true);
        if (this.playbackInfo.playbackState == 4) goto L52;
        maybeContinueLoading();
        updatePlaybackPositions();
        this.handler.sendEmptyMessage(2);
        return;
    L52:
        return;
    L21:
        if (r22 == r15.positionUs) goto L23;
        r8 = true;
        goto L24
    L17:
        r17 = false;
        goto L18
    L35:
        r12.removeAfter(r2);
        if (r2.prepared == false) goto L45;
        long r34 = Math.max(r2.info.startPositionUs, r2.toPeriodTime(this.rendererPositionUs));
        if (this.hasSecondaryRenderers == true) goto L40;
    L44:
        r2.applyTrackSelection(r62, r34, false);
        goto L45
    L40:
        if (areRenderersPrewarming() == false) goto L44;
        if (this.queue.getPrewarmingPeriod() != r2) goto L44;
        disableAndResetPrewarmingRenderers();
        goto L44
    L62:
        return;
    }

    private void reselectTracksInternalAndSeek() {
        reselectTracksInternal();
        seekToCurrentPosition(true);
    }

    private void resetInternal(boolean r34, boolean r35, boolean r36, boolean r37) {
        this.handler.removeMessages(2);
        this.seekIsPendingWhileScrubbing = false;
        ExoPlaybackException r4 = null;
        this.queuedSeekWhileScrubbing = null;
        this.pendingRecoverableRendererError = null;
        boolean r5 = true;
        updateRebufferingState(false, true);
        this.mediaClock.stop();
        this.rendererPositionUs = 1000000000000L;
        disableRenderers();     // Catch: ExoPlaybackException -> L5 Throwable -> L7
    L9:
        if (r34 == false) goto L18;
        RendererHolder[] r6 = this.renderers;
        int r7 = r6.length;
        int r8 = 0;
    L11:
        if (r8 >= r7) goto L18;
        r6[r8].reset();     // Catch: RuntimeException -> L15
    L17:
        r8 = r8 + 1;
    L15:
        e = move-exception;
        Log.e("ExoPlayerImplInternal", "Reset failed.", e);
    L18:
        this.enabledRendererCount = 0;
        PlaybackInfo r0 = this.playbackInfo;
        MediaSource.MediaPeriodId r2 = r0.periodId;
        long r62 = r0.positionUs;
        if (this.playbackInfo.periodId.isAd() == false) goto L21;
    L24:
        long r82 = this.playbackInfo.requestedContentPositionUs;
    L25:
        if (r35 == false) goto L29;
        this.pendingInitialSeekPosition = null;
        Pair<MediaSource.MediaPeriodId, Long> r02 = getPlaceholderFirstMediaPeriodPositionUs(this.playbackInfo.timeline);
        r2 = (MediaSource.MediaPeriodId) r02.first;
        r62 = ((Long) r02.second).longValue();
        r82 = -9223372036854775807L;
        if (r2.equals(this.playbackInfo.periodId) == true) goto L29;
    L28:
        long r11 = r62;
        long r9 = r82;
        this.queue.clear();
        this.shouldContinueLoading = false;
        Timeline r03 = this.playbackInfo.timeline;
        if (r36 == true) goto L33;
    L39:
        Timeline r72 = r03;
        MediaSource.MediaPeriodId r83 = r2;
    L40:
        PlaybackInfo r04 = this.playbackInfo;
        int r13 = r04.playbackState;
        if (r37 == false) goto L43;
    L42:
        ExoPlaybackException r14 = r4;
        if (r5 == false) goto L47;
        TrackGroupArray r22 = TrackGroupArray.EMPTY;
    L46:
        TrackGroupArray r16 = r22;
        if (r5 == false) goto L51;
        TrackSelectorResult r23 = this.emptyTrackSelectorResult;
    L50:
        TrackSelectorResult r17 = r23;
        if (r5 == false) goto L55;
        List r05 = ImmutableList.of();
    L54:
        List r18 = r05;
        PlaybackInfo r06 = this.playbackInfo;
        this.playbackInfo = new PlaybackInfo(r72, r83, r9, r11, r13, r14, false, r16, r17, r18, r83, r06.playWhenReady, r06.playWhenReadyChangeReason, r06.playbackSuppressionReason, r06.playbackParameters, r11, 0, r11, 0, false);
        if (r36 == false) goto L67;
        this.queue.releasePreloadPool();
        this.mediaSourceList.release();
        return;
    L67:
        return;
    L55:
        r05 = r04.staticMetadata;
        goto L54
    L51:
        r23 = r04.trackSelectorResult;
        goto L50
    L47:
        r22 = r04.trackGroups;
        goto L46
    L43:
        r4 = r04.playbackError;
        goto L42
    L33:
        if ((r03 instanceof PlaylistTimeline) == false) goto L39;
        r03 = ((PlaylistTimeline) r03).copyWithPlaceholderTimeline(this.mediaSourceList.getShuffleOrder());
        if (r2.adGroupIndex == (-1)) goto L39;
        r03.getPeriodByUid(r2.periodUid, this.period);
        if (r03.getWindow(this.period.windowIndex, this.window).isLive() == false) goto L39;
        r72 = r03;
        r83 = new MediaSource.MediaPeriodId(r2.periodUid, r2.windowSequenceNumber);
    L29:
        r5 = false;
        goto L28
    L21:
        if (isUsingPlaceholderPeriod(this.playbackInfo, this.period) == true) goto L24;
        r82 = this.playbackInfo.positionUs;
    L7:
        e = move-exception;
        Log.e("ExoPlayerImplInternal", "Disable failed.", e);
        goto L9
    }

    private void resetPendingPauseAtEndOfPeriod() {
        MediaPeriodHolder r0 = this.queue.getPlayingPeriod();
        if (r0 != null) goto L5;
    L9:
        boolean r02 = false;
    L10:
        this.pendingPauseAtEndOfPeriod = r02;
        return;
    L5:
        if (r0.info.isLastInTimelineWindow == false) goto L9;
        if (this.pauseAtEndOfWindow == false) goto L9;
        r02 = true;
        goto L10
    }

    private void resetRendererPosition(long r6) {
        MediaPeriodHolder r0 = this.queue.getPlayingPeriod();
        if (r0 != null) goto L5;
        long r62 = r6 + 1000000000000L;
    L6:
        this.rendererPositionUs = r62;
        this.mediaClock.resetPosition(r62);
        RendererHolder[] r63 = this.renderers;
        int r7 = r63.length;
        int r1 = 0;
    L7:
        if (r1 >= r7) goto L9;
        r63[r1].resetPosition(r0, this.rendererPositionUs);
        r1 = r1 + 1;
        goto L7
    L9:
        notifyTrackSelectionDiscontinuity();
        return;
    L5:
        r62 = r0.toRendererTime(r6);
        goto L6
    }

    private static void resolvePendingMessageEndOfStreamPosition(Timeline r4, PendingMessageInfo r5, Timeline.Window r6, Timeline.Period r7) {
        int r62 = r4.getWindow(r4.getPeriodByUid(r5.resolvedPeriodUid, r7).windowIndex, r6).lastPeriodIndex;
        Object r42 = r4.getPeriod(r62, r7, true).uid;
        long r0 = r7.durationUs;
        if (r0 == (-9223372036854775807L)) goto L5;
        long r02 = r0 - 1;
    L6:
        r5.setResolvedPosition(r62, r02, r42);
        return;
    L5:
        r02 = LongCompanionObject.MAX_VALUE;
        goto L6
    }

    private static boolean resolvePendingMessagePosition(PendingMessageInfo r11, Timeline r12, Timeline r13, int r14, boolean r15, Timeline.Window r16, Timeline.Period r17) {
        Object r0 = r11.resolvedPeriodUid;
        if (r0 == null) goto L5;
        int r02 = r12.getIndexOfPeriod(r0);
        if (r02 != (-1)) goto L19;
        return false;
    L19:
        if (r11.message.getPositionMs() != Long.MIN_VALUE) goto L22;
        resolvePendingMessageEndOfStreamPosition(r12, r11, r16, r17);
        return true;
    L22:
        r11.resolvedPeriodIndex = r02;
        r13.getPeriodByUid(r11.resolvedPeriodUid, r17);
        if (r17.isPlaceholder == true) goto L25;
    L27:
        return true;
    L25:
        if (r13.getWindow(r17.windowIndex, r16).firstPeriodIndex != r13.getIndexOfPeriod(r11.resolvedPeriodUid)) goto L27;
        long r3 = r11.resolvedPeriodTimeUs + r17.getPositionInWindowUs();
        Pair<Object, Long> r132 = r12.getPeriodPositionUs(r16, r17, r12.getPeriodByUid(r11.resolvedPeriodUid, r17).windowIndex, r3);
        r11.setResolvedPosition(r12.getIndexOfPeriod(r132.first), ((Long) r132.second).longValue(), r132.first);
        goto L27
    L5:
        if (r11.message.getPositionMs() != Long.MIN_VALUE) goto L7;
        long r03 = -9223372036854775807L;
    L8:
        Pair<Object, Long> r133 = resolveSeekPositionUs(r12, new SeekPosition(r11.message.getTimeline(), r11.message.getMediaItemIndex(), r03), false, r14, r15, r16, r17);
        if (r133 != null) goto L11;
        return false;
    L11:
        r11.setResolvedPosition(r12.getIndexOfPeriod(r133.first), ((Long) r133.second).longValue(), r133.first);
        if (r11.message.getPositionMs() != Long.MIN_VALUE) goto L14;
        resolvePendingMessageEndOfStreamPosition(r12, r11, r16, r17);
    L14:
        return true;
    L7:
        r03 = Util.msToUs(r11.message.getPositionMs());
        goto L8
    }

    private void resolvePendingMessagePositions(Timeline r10, Timeline r11) {
        if (r10.isEmpty() == true) goto L5;
    L7:
        int r0 = this.pendingMessages.size() - 1;
    L8:
        ArrayList<PendingMessageInfo> r1 = this.pendingMessages;
        if (r0 < 0) goto L14;
        Timeline r3 = r10;
        Timeline r4 = r11;
        if (resolvePendingMessagePosition(r1.get(r0), r3, r4, this.repeatMode, this.shuffleModeEnabled, this.window, this.period) == true) goto L13;
        this.pendingMessages.get(r0).message.markAsProcessed(false);
        this.pendingMessages.remove(r0);
    L13:
        r0 = r0 - 1;
        r10 = r3;
        r11 = r4;
        goto L8
    L14:
        Collections.sort(r1);
        return;
    L5:
        if (r11.isEmpty() == false) goto L7;
    }

    private static PositionUpdateForPlaylistChange resolvePositionForPlaylistChange(Timeline r32, PlaybackInfo r33, SeekPosition r34, MediaPeriodQueue r35, int r36, boolean r37, Timeline.Window r38, Timeline.Period r39) {
        if (r32.isEmpty() == true) goto L5;
        MediaSource.MediaPeriodId r9 = r33.periodId;
        Object r8 = r9.periodUid;
        boolean r10 = isUsingPlaceholderPeriod(r33, r39);
        if (r33.periodId.isAd() == true) goto L12;
        if (r10 == true) goto L12;
        long r0 = r33.positionUs;
    L11:
        long r11 = r0;
        boolean r19 = false;
        if (r34 == null) goto L28;
        Timeline r02 = r32;
        Pair<Object, Long> r2 = resolveSeekPositionUs(r02, r34, true, r36, r37, r38, r39);
        if (r2 != null) goto L18;
        int r3 = r02.getFirstWindowIndex(r37);
        long r1 = r11;
        boolean r4 = false;
        boolean r5 = false;
        boolean r14 = true;
    L27:
        boolean r29 = r4;
        boolean r27 = r5;
        boolean r28 = r14;
        Timeline.Period r22 = r39;
        int r6 = -1;
        long r13 = r1;
    L53:
        if (r3 == r6) goto L56;
        Pair<Object, Long> r12 = r02.getPeriodPositionUs(r38, r22, r3, -9223372036854775807L);
        r8 = r12.first;
        long r310 = ((Long) r12.second).longValue();
        long r25 = -9223372036854775807L;
    L57:
        MediaSource.MediaPeriodId r15 = r35.resolveMediaPeriodIdForAdsAfterPeriodPositionChange(r02, r8, r310);
        int r52 = r15.nextAdGroupIndex;
        if (r52 == r6) goto L64;
        int r132 = r9.nextAdGroupIndex;
        if (r132 == r6) goto L63;
        if (r52 >= r132) goto L64;
    L63:
        boolean r53 = false;
    L66:
        if (r9.periodUid.equals(r8) == true) goto L68;
    L73:
        MediaSource.MediaPeriodId r122 = r15;
        boolean r16 = isIgnorableServerSideAdInsertionPeriodChange(r10, r9, r11, r122, r02.getPeriodByUid(r8, r22), r25);
        if (r19 == true) goto L76;
        if (r16 == true) goto L76;
    L78:
        if (r122.isAd() == false) goto L89;
        if (r122.equals(r9) == false) goto L83;
        r310 = r33.positionUs;
        goto L89
    L83:
        r02.getPeriodByUid(r122.periodUid, r22);
        if (r122.adIndexInAdGroup != r22.getFirstAdIndexToPlay(r122.adGroupIndex)) goto L87;
        long r03 = r22.getAdResumePositionUs();
    L86:
        r310 = r03;
        goto L89
    L87:
        r03 = 0;
    L89:
        return new PositionUpdateForPlaylistChange(r122, r310, r25, r27, r28, r29);
    L76:
        r122 = r9;
        goto L78
    L68:
        if (r9.isAd() == true) goto L73;
        if (r15.isAd() == true) goto L73;
        if (r53 == false) goto L73;
        r19 = true;
    L64:
        r53 = true;
        goto L66
    L56:
        r310 = r13;
        r25 = r310;
        goto L57
    L18:
        long r311 = r34.windowPositionUs;
        Object r312 = r2.first;
        if (r311 != (-9223372036854775807L)) goto L21;
        r3 = r02.getPeriodByUid(r312, r39).windowIndex;
        r1 = r11;
        r4 = false;
    L23:
        if (r33.playbackState != 4) goto L25;
        r5 = true;
    L26:
        r14 = false;
        goto L27
    L25:
        r5 = false;
        goto L26
    L21:
        r1 = ((Long) r2.second).longValue();
        r8 = r312;
        r3 = -1;
        r4 = true;
        goto L23
    L28:
        r02 = r32;
        if (r33.timeline.isEmpty() == false) goto L34;
        r3 = r02.getFirstWindowIndex(r37);
    L31:
        r22 = r39;
        r6 = -1;
        r27 = false;
        r28 = false;
        r29 = false;
    L32:
        r13 = r11;
        goto L53
    L34:
        if (r02.getIndexOfPeriod(r8) != (-1)) goto L41;
        int r23 = resolveSubsequentPeriod(r38, r39, r36, r37, r8, r33.timeline, r02);
        r02 = r02;
        if (r23 != (-1)) goto L38;
        r23 = r02.getFirstWindowIndex(r37);
        boolean r142 = true;
    L39:
        r3 = r23;
        r8 = r8;
        r22 = r39;
        r6 = -1;
        r28 = r142;
        r27 = false;
        r29 = false;
        goto L32
    L38:
        r142 = false;
        goto L39
    L41:
        if (r11 != (-9223372036854775807L)) goto L43;
        r3 = r02.getPeriodByUid(r8, r39).windowIndex;
        r8 = r8;
        goto L31
    L43:
        if (r10 == false) goto L52;
        r33.timeline.getPeriodByUid(r9.periodUid, r39);
        if (r33.timeline.getWindow(r39.windowIndex, r38).firstPeriodIndex != r33.timeline.getIndexOfPeriod(r9.periodUid)) goto L47;
        r22 = r39;
        Pair<Object, Long> r313 = r02.getPeriodPositionUs(r38, r22, r02.getPeriodByUid(r8, r39).windowIndex, r39.getPositionInWindowUs() + r11);
        r8 = r313.first;
        r6 = -1;
        r13 = ((Long) r313.second).longValue();
    L51:
        r3 = r6;
        r27 = false;
        r28 = false;
        r29 = true;
        goto L53
    L47:
        r22 = r39;
        if (r02.getPeriodByUid(r8, r22).durationUs == (-9223372036854775807L)) goto L50;
        r6 = -1;
        r13 = Util.constrainValue(r11, 0, r22.durationUs - 1);
        r8 = r8;
        goto L51
    L50:
        r6 = -1;
        r8 = r8;
        r13 = r11;
        goto L51
    L52:
        r22 = r39;
        r6 = -1;
        r8 = r8;
        r3 = -1;
        r13 = r11;
        r27 = false;
        r28 = false;
        r29 = false;
    L12:
        r0 = r33.requestedContentPositionUs;
        goto L11
    L5:
        return new PositionUpdateForPlaylistChange(PlaybackInfo.getDummyPeriodForEmptyTimeline(), 0, -9223372036854775807L, false, true, false);
    }

    private static Pair<Object, Long> resolveSeekPositionUs(Timeline r9, SeekPosition r10, boolean r11, int r12, boolean r13, Timeline.Window r14, Timeline.Period r15) {
        Timeline r2 = r10.timeline;
        if (r9.isEmpty() == false) goto L6;
        return null;
    L6:
        if (r2.isEmpty() == false) goto L28;
        r2 = r9;
    L28:
        Pair<Object, Long> r5 = r2.getPeriodPositionUs(r14, r15, r10.windowIndex, r10.windowPositionUs);     // Catch: IndexOutOfBoundsException -> L27
        Timeline r3 = r2;
        if (r9.equals(r3) == false) goto L13;
        return r5;
    L13:
        if (r9.getIndexOfPeriod(r5.first) != (-1)) goto L15;
        if (r11 == false) goto L26;
        int r32 = resolveSubsequentPeriod(r14, r15, r12, r13, r5.first, r3, r9);
        if (r32 == (-1)) goto L26;
        return r9.getPeriodPositionUs(r14, r15, r32, -9223372036854775807L);
    L15:
        if (r3.getPeriodByUid(r5.first, r15).isPlaceholder == true) goto L17;
    L20:
        return r5;
    L17:
        if (r3.getWindow(r15.windowIndex, r14).firstPeriodIndex != r3.getIndexOfPeriod(r5.first)) goto L20;
        return r9.getPeriodPositionUs(r14, r15, r9.getPeriodByUid(r5.first, r15).windowIndex, r10.windowPositionUs);
    L26:
        return null;
    }

    public static int resolveSubsequentPeriod(Timeline.Window r9, Timeline.Period r10, int r11, boolean r12, Object r13, Timeline r14, Timeline r15) {
        Object r0 = r14.getWindow(r14.getPeriodByUid(r13, r10).windowIndex, r9).uid;
        int r1 = 0;
        int r2 = 0;
    L4:
        if (r2 >= r15.getWindowCount()) goto L9;
        if (r15.getWindow(r2, r9).uid.equals(r0) == true) goto L7;
        r2 = r2 + 1;
        goto L4
    L7:
        return r2;
    L9:
        int r132 = r14.getIndexOfPeriod(r13);
        int r02 = r14.getPeriodCount();
        int r4 = r132;
        int r133 = -1;
    L10:
        if (r1 >= r02) goto L16;
        if (r133 != (-1)) goto L16;
        Timeline.Window r6 = r9;
        Timeline.Period r5 = r10;
        int r7 = r11;
        boolean r8 = r12;
        Timeline r3 = r14;
        r4 = r3.getNextPeriodIndex(r4, r5, r6, r7, r8);
        if (r4 == (-1)) goto L17;
        r133 = r15.getIndexOfPeriod(r3.getUidOfPeriod(r4));
        r1 = r1 + 1;
        r14 = r3;
        r10 = r5;
        r9 = r6;
        r11 = r7;
        r12 = r8;
    L17:
        if (r133 != (-1)) goto L20;
        return -1;
    L20:
        return r15.getPeriod(r133, r5).windowIndex;
    L16:
        r5 = r10;
        goto L17
    }

    private void scheduleNextWork(long r4) {
        if (isDynamicSchedulingEnabled() == false) goto L5;
        long r0 = getDynamicSchedulingWakeUpIntervalMs();
    L6:
        this.handler.sendEmptyMessageAtTime(2, r4 + r0);
        return;
    L5:
        r0 = getStaticSchedulingWakeUpIntervalMs();
        goto L6
    }

    private void seekToCurrentPosition(boolean r12) {
        MediaSource.MediaPeriodId r2 = this.queue.getPlayingPeriod().info.id;
        long r3 = seekToPeriodPosition(r2, this.playbackInfo.positionUs, true, false);
        if (r3 == this.playbackInfo.positionUs) goto L6;
        PlaybackInfo r11 = this.playbackInfo;
        this.playbackInfo = handlePositionDiscontinuity(r2, r3, r11.requestedContentPositionUs, r11.discontinuityStartPositionUs, r12, 5);
        return;
    }

    private void seekToInternal(SeekPosition r18, boolean r19) {
        ExoPlayerImplInternal r1 = this;
        r1.playbackInfoUpdate.incrementPendingOperationAcks(r19 ? 1 : 0);
        if (r1.seekIsPendingWhileScrubbing == false) goto L6;
        r1.queuedSeekWhileScrubbing = r18;
        return;
    L6:
        Pair<Object, Long> r0 = resolveSeekPositionUs(r1.playbackInfo.timeline, r18, true, r1.repeatMode, r1.shuffleModeEnabled, r1.window, r1.period);
        boolean r8 = true;
        if (r0 != null) goto L10;
        Pair<MediaSource.MediaPeriodId, Long> r9 = r1.getPlaceholderFirstMediaPeriodPositionUs(r1.playbackInfo.timeline);
        MediaSource.MediaPeriodId r10 = (MediaSource.MediaPeriodId) r9.first;
        long r11 = ((Long) r9.second).longValue();
        boolean r92 = !r1.playbackInfo.timeline.isEmpty();
        long r5 = -9223372036854775807L;
    L9:
        long r15 = 0;
    L97:
    L30:
        th = th;
        MediaSource.MediaPeriodId r2 = r10;
    L32:
        long r3 = r11;
    L89:
        r1.playbackInfo = r1.handlePositionDiscontinuity(r2, r3, r5, r3, r92, 2);
        throw th;
    L27:
        if (r1.playbackInfo.timeline.isEmpty() == false) goto L33;
        r1.pendingInitialSeekPosition = r18;     // Catch: Throwable -> L30
    L39:
        MediaSource.MediaPeriodId r22 = r10;
        long r32 = r11;
    L79:
        int r102 = 2;
        long r7 = r32;
        r1 = this;
    L60:
        r1.playbackInfo = r1.handlePositionDiscontinuity(r22, r32, r5, r7, r92, r102);
        return;
    L33:
        PlaybackInfo r33 = r1.playbackInfo;
        if (r0 != null) goto L41;
        if (r33.playbackState == 1) goto L38;
        r1.setState(4);     // Catch: Throwable -> L30
    L38:
        r1.resetInternal(false, true, false, true);     // Catch: Throwable -> L30
        goto L39
    L41:
        if (r10.equals(r33.periodId) == false) goto L62;
        MediaPeriodHolder r02 = r1.queue.getPlayingPeriod();     // Catch: Throwable -> L30
        if (r02 != null) goto L45;
    L49:
        long r13 = r11;
    L51:
        if (Util.usToMs(r13) != Util.usToMs(r1.playbackInfo.positionUs)) goto L57;
        PlaybackInfo r03 = r1.playbackInfo;     // Catch: Throwable -> L30
        int r23 = r03.playbackState;     // Catch: Throwable -> L30
        if (r23 != 2) goto L55;
    L58:
        r32 = r03.positionUs;     // Catch: Throwable -> L30
        r22 = r10;
        r102 = 2;
        r7 = r32;
        goto L60
    L55:
        if (r23 == 3) goto L58;
    L57:
        r2 = r10;
    L91:
        r1.seekIsPendingWhileScrubbing = r1.scrubbingModeEnabled;     // Catch: Throwable -> L87
        if (r1.playbackInfo.playbackState != 4) goto L66;
        boolean r04 = true;
    L67:
        long r132 = r1.seekToPeriodPosition(r2, r13, r04);     // Catch: Throwable -> L87
        if (r11 != r132) goto L72;
        r8 = false;
    L72:
        r92 = r92 | r8;
        PlaybackInfo r05 = r1.playbackInfo;     // Catch: Throwable -> L85
        MediaSource.MediaPeriodId r34 = r2;
        Timeline r24 = r05.timeline;     // Catch: Throwable -> L83
        long r6 = r5;
        r1.updatePlaybackSpeedSettingsForNewPeriod(r24, r34, r24, r05.periodId, r6, true);     // Catch: Throwable -> L80
        r22 = r34;
        r5 = r6;
        r32 = r132;
    L80:
        th = th;
        r2 = r34;
        r5 = r6;
    L82:
        r3 = r132;
    L83:
        th = th;
        r2 = r34;
    L85:
        th = th;
        goto L82
    L66:
        r04 = false;
    L87:
        th = th;
        goto L32
    L45:
        if (r02.prepared == false) goto L49;
        if (r11 == r15) goto L49;
        r13 = r02.mediaPeriod.getAdjustedSeekPositionUs(r11, r1.getSeekParameters(r1.window.durationUs));     // Catch: Throwable -> L30
        goto L51
    L62:
        r2 = r10;
        r13 = r11;
        goto L91
    L10:
        Object r93 = r0.first;
        r11 = ((Long) r0.second).longValue();
        if (r18.windowPositionUs != (-9223372036854775807L)) goto L13;
        long r133 = -9223372036854775807L;
    L14:
        r10 = r1.queue.resolveMediaPeriodIdForAdsAfterPeriodPositionChange(r1.playbackInfo.timeline, r93, r11);
        if (r10.isAd() == false) goto L21;
        r1.playbackInfo.timeline.getPeriodByUid(r10.periodUid, r1.period);
        if (r1.period.getFirstAdIndexToPlay(r10.adGroupIndex) != r10.adIndexInAdGroup) goto L19;
        r11 = r1.period.getAdResumePositionUs();
    L20:
        r92 = true;
        r5 = r133;
        goto L9
    L19:
        r11 = 0;
        goto L20
    L21:
        r15 = 0;
        if (r18.windowPositionUs != (-9223372036854775807L)) goto L24;
        r92 = true;
    L25:
        r5 = r133;
        goto L97
    L24:
        r92 = false;
        goto L25
    L13:
        r133 = r11;
        goto L14
    }

    private long seekToPeriodPosition(MediaSource.MediaPeriodId r8, long r9, boolean r11) {
        if (this.queue.getPlayingPeriod() == this.queue.getReadingPeriod()) goto L6;
        boolean r0 = true;
    L8:
        return seekToPeriodPosition(r8, r9, r0, r11);
    L6:
        r0 = false;
        goto L8
    }

    private long seekToPeriodPosition(MediaSource.MediaPeriodId r7, long r8, boolean r10, boolean r11) {
        stopRenderers();
        updateRebufferingState(false, true);
        if (r11 == false) goto L5;
    L6:
        setState(2);
    L7:
        MediaPeriodHolder r112 = this.queue.getPlayingPeriod();
        MediaPeriodHolder r3 = r112;
    L8:
        if (r3 == null) goto L13;
        if (r7.equals(r3.info.id) == true) goto L13;
        r3 = r3.getNext();
    L13:
        if (r10 == true) goto L18;
        if (r112 != r3) goto L18;
        if (r3 != null) goto L17;
    L24:
        disableAndResetPrewarmingRenderers();
        MediaPeriodQueue r72 = this.queue;
        if (r3 == null) goto L33;
        r72.removeAfter(r3);
        if (r3.prepared == true) goto L30;
        r3.info = r3.info.copyWithStartPositionUs(r8);
    L32:
        resetRendererPosition(r8);
        maybeContinueLoading();
    L34:
        handleLoadingMediaPeriodChanged(false);
        this.handler.sendEmptyMessage(2);
        return r8;
    L30:
        if (r3.hasEnabledTracks == false) goto L32;
        r8 = r3.mediaPeriod.seekToUs(r8);
        r3.mediaPeriod.discardBuffer(r8 - this.backBufferDurationUs, this.retainBackBufferFromKeyframe);
        goto L32
    L33:
        r72.clear();
        resetRendererPosition(r8);
        goto L34
    L17:
        if (r3.toRendererTime(r8) >= 0) goto L24;
    L18:
        disableRenderers();
        if (r3 == null) goto L24;
    L20:
        MediaPeriodHolder r73 = this.queue.getPlayingPeriod();
        MediaPeriodQueue r102 = this.queue;
        if (r73 == r3) goto L23;
        r102.advancePlayingPeriod();
        goto L20
    L23:
        r102.removeAfter(r3);
        r3.setRendererOffset(1000000000000L);
        enableRenderers();
        r3.allRenderersInCorrectState = true;
        goto L24
    L5:
        if (this.playbackInfo.playbackState != 3) goto L7;
        goto L6
    }

    private void sendMessageInternal(PlayerMessage r9) {
        if (r9.getPositionMs() != (-9223372036854775807L)) goto L7;
        sendMessageToTarget(r9);
        return;
    L7:
        if (this.playbackInfo.timeline.isEmpty() == false) goto L10;
        this.pendingMessages.add(new PendingMessageInfo(r9));
        return;
    L10:
        PendingMessageInfo r1 = new PendingMessageInfo(r9);
        Timeline r2 = this.playbackInfo.timeline;
        if (resolvePendingMessagePosition(r1, r2, r2, this.repeatMode, this.shuffleModeEnabled, this.window, this.period) == false) goto L14;
        this.pendingMessages.add(r1);
        Collections.sort(this.pendingMessages);
        return;
    L14:
        r9.markAsProcessed(false);
    }

    private void sendMessageToTarget(PlayerMessage r3) {
        if (r3.getLooper() != this.playbackLooper) goto L11;
        deliverMessage(r3);
        int r32 = this.playbackInfo.playbackState;
        if (r32 == 3) goto L9;
        if (r32 == 2) goto L9;
        return;
    L9:
        this.handler.sendEmptyMessage(2);
        return;
    L11:
        this.handler.obtainMessage(15, r3).sendToTarget();
    }

    private void sendMessageToTargetThread(PlayerMessage r4) {
        Looper r0 = r4.getLooper();
        if (r0.getThread().isAlive() == true) goto L6;
        Log.w("TAG", "Trying to send message on a dead thread.");
        r4.markAsProcessed(false);
        return;
    L6:
        this.clock.createHandler(r0, null).post(new ExoPlayerImplInternal$$ExternalSyntheticLambda1(this, r4));
    }

    private void setAllNonPrewarmingRendererStreamsFinal(long r4) {
        RendererHolder[] r3 = this.renderers;
        int r0 = r3.length;
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        r3[r1].setAllNonPrewarmingRendererStreamsFinal(r4);
        r1 = r1 + 1;
        goto L3
    }

    private void setAudioAttributesInternal(AudioAttributes r2, boolean r3) {
        this.trackSelector.setAudioAttributes(r2);
        AudioFocusManager r0 = this.audioFocusManager;
        if (r3 == true) goto L6;
        r2 = null;
    L6:
        r0.setAudioAttributes(r2);
        updatePlayWhenReadyWithAudioFocus();
    }

    private void setForegroundModeInternal(boolean r3, ConditionVariable r4) {
        if (this.foregroundMode == r3) goto L9;
        this.foregroundMode = r3;
        if (r3 == true) goto L9;
        RendererHolder[] r2 = this.renderers;
        int r32 = r2.length;
        int r0 = 0;
    L7:
        if (r0 >= r32) goto L9;
        r2[r0].reset();
        r0 = r0 + 1;
    L9:
        if (r4 == null) goto L13;
        r4.open();
        return;
    }

    private void setMediaClockPlaybackParameters(PlaybackParameters r3) {
        this.handler.removeMessages(16);
        this.mediaClock.setPlaybackParameters(r3);
    }

    private void setMediaItemsInternal(MediaSourceListUpdateMessage r6) {
        this.playbackInfoUpdate.incrementPendingOperationAcks(1);
        if (MediaSourceListUpdateMessage.access$200(r6) == (-1)) goto L5;
        this.pendingInitialSeekPosition = new SeekPosition(new PlaylistTimeline(MediaSourceListUpdateMessage.access$300(r6), MediaSourceListUpdateMessage.access$400(r6)), MediaSourceListUpdateMessage.access$200(r6), MediaSourceListUpdateMessage.access$500(r6));
    L5:
        handleMediaSourceListInfoRefreshed(this.mediaSourceList.setMediaSources(MediaSourceListUpdateMessage.access$300(r6), MediaSourceListUpdateMessage.access$400(r6)), false);
    }

    private void setOffloadSchedulingEnabled(boolean r2) {
        if (r2 == this.offloadSchedulingEnabled) goto L13;
        this.offloadSchedulingEnabled = r2;
        if (r2 == false) goto L8;
        return;
    L8:
        if (this.playbackInfo.sleepingForOffload == false) goto L12;
        this.handler.sendEmptyMessage(2);
        return;
    L12:
        return;
    }

    private void setPauseAtEndOfWindowInternal(boolean r2) {
        this.pauseAtEndOfWindow = r2;
        resetPendingPauseAtEndOfPeriod();
        if (this.pendingPauseAtEndOfPeriod == true) goto L5;
        return;
    L5:
        if (this.queue.getReadingPeriod() == this.queue.getPlayingPeriod()) goto L9;
        seekToCurrentPosition(true);
        handleLoadingMediaPeriodChanged(false);
        return;
    }

    private void setPlayWhenReadyInternal(boolean r2, int r3, boolean r4, int r5) {
        this.playbackInfoUpdate.incrementPendingOperationAcks(r4 ? 1 : 0);
        updatePlayWhenReadyWithAudioFocus(r2, r3, r5);
    }

    private void setPlaybackParametersInternal(PlaybackParameters r2) {
        setMediaClockPlaybackParameters(r2);
        handlePlaybackParameters(this.mediaClock.getPlaybackParameters(), true);
    }

    private void setPreloadConfigurationInternal(ExoPlayer.PreloadConfiguration r2) {
        this.preloadConfiguration = r2;
        this.queue.updatePreloadConfiguration(this.playbackInfo.timeline, r2);
    }

    private void setRepeatModeInternal(int r3) {
        this.repeatMode = r3;
        int r32 = this.queue.updateRepeatMode(this.playbackInfo.timeline, r3);
        if ((r32 & 1) == 0) goto L6;
        seekToCurrentPosition(true);
    L8:
        handleLoadingMediaPeriodChanged(false);
        return;
    L6:
        if ((r32 & 2) == 0) goto L8;
        disableAndResetPrewarmingRenderers();
        goto L8
    }

    private void setScrubbingModeEnabledInternal(boolean r4) {
        if (r4 == true) goto L6;
        this.seekIsPendingWhileScrubbing = false;
        this.handler.removeMessages(37);
        SeekPosition r1 = this.queuedSeekWhileScrubbing;
        if (r1 == null) goto L6;
        seekToInternal(r1, false);
        this.queuedSeekWhileScrubbing = null;
    L6:
        this.scrubbingModeEnabled = r4;
        applyScrubbingModeParameters();
    }

    private void setScrubbingModeParametersInternal(ScrubbingModeParameters r1) {
        this.scrubbingModeParameters = r1;
        applyScrubbingModeParameters();
    }

    private void setSeekParametersInternal(SeekParameters r1) {
        this.seekParameters = r1;
    }

    private void setShuffleModeEnabledInternal(boolean r3) {
        this.shuffleModeEnabled = r3;
        int r32 = this.queue.updateShuffleModeEnabled(this.playbackInfo.timeline, r3);
        if ((r32 & 1) == 0) goto L6;
        seekToCurrentPosition(true);
    L8:
        handleLoadingMediaPeriodChanged(false);
        return;
    L6:
        if ((r32 & 2) == 0) goto L8;
        disableAndResetPrewarmingRenderers();
        goto L8
    }

    private void setShuffleOrderInternal(ShuffleOrder r3) {
        this.playbackInfoUpdate.incrementPendingOperationAcks(1);
        handleMediaSourceListInfoRefreshed(this.mediaSourceList.setShuffleOrder(r3), false);
    }

    private void setState(int r4) {
        PlaybackInfo r0 = this.playbackInfo;
        if (r0.playbackState != r4) goto L5;
        return;
    L5:
        if (r4 == 2) goto L8;
        this.playbackMaybeBecameStuckAtMs = -9223372036854775807L;
    L8:
        if (r4 != 3) goto L10;
    L12:
        this.playbackInfo = this.playbackInfo.copyWithPlaybackState(r4);
        return;
    L10:
        if (r0.sleepingForOffload == false) goto L12;
        this.playbackInfo = r0.copyWithSleepingForOffload(false);
        goto L12
    }

    private void setVideoFrameMetadataListenerInternal(VideoFrameMetadataListener r4) {
        RendererHolder[] r3 = this.renderers;
        int r0 = r3.length;
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        r3[r1].setVideoFrameMetadataListener(r4);
        r1 = r1 + 1;
        goto L3
    }

    private void setVideoOutputInternal(Object r5, ConditionVariable r6) {
        RendererHolder[] r0 = this.renderers;
        int r1 = r0.length;
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L5;
        r0[r2].setVideoOutput(r5);
        r2 = r2 + 1;
        goto L3
    L5:
        int r52 = this.playbackInfo.playbackState;
        if (r52 == 3) goto L8;
        if (r52 == 2) goto L8;
    L9:
        if (r6 == null) goto L13;
        r6.open();
        return;
    L13:
        return;
    L8:
        this.handler.sendEmptyMessage(2);
        goto L9
    }

    private void setVolumeInternal(float r4) {
        this.volume = r4;
        float r42 = r4 * this.audioFocusManager.getVolumeMultiplier();
        RendererHolder[] r3 = this.renderers;
        int r0 = r3.length;
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        r3[r1].setVolume(r42);
        r1 = r1 + 1;
        goto L3
    }

    private boolean shouldAdvancePlayingPeriod() {
        if (shouldPlayWhenReady() == true) goto L6;
        return false;
    L6:
        if (this.pendingPauseAtEndOfPeriod == false) goto L8;
        return false;
    L8:
        MediaPeriodHolder r0 = this.queue.getPlayingPeriod();
        if (r0 != null) goto L11;
        return false;
    L11:
        MediaPeriodHolder r02 = r0.getNext();
        if (r02 != null) goto L14;
    L19:
        return false;
    L14:
        if (this.rendererPositionUs < r02.getStartPositionRendererTime()) goto L19;
        if (r02.allRenderersInCorrectState == false) goto L19;
        return true;
    }

    private boolean shouldContinueLoading() {
        if (isLoadingPossible(this.queue.getLoadingPeriod()) == true) goto L5;
        return false;
    L5:
        MediaPeriodHolder r1 = this.queue.getLoadingPeriod();
        long r11 = getTotalBufferedDurationUs(r1.getNextLoadPositionUs());
        MediaPeriodHolder r3 = this.queue.getPlayingPeriod();
        long r4 = this.rendererPositionUs;
        if (r1 != r3) goto L9;
        long r32 = r1.toPeriodTime(r4);
    L8:
        long r9 = r32;
        if (shouldUseLivePlaybackSpeedControl(this.playbackInfo.timeline, r1.info.id) == false) goto L14;
        long r33 = this.livePlaybackSpeedControl.getTargetLiveOffsetUs();
    L13:
        long r16 = r33;
        LoadControl.Parameters r5 = new LoadControl.Parameters(this.playerId, this.playbackInfo.timeline, r1.info.id, r9, r11, this.mediaClock.getPlaybackParameters().speed, this.playbackInfo.playWhenReady, this.isRebuffering, r16, this.lastRebufferRealtimeMs);
        boolean r12 = this.loadControl.shouldContinueLoading(r5);
        MediaPeriodHolder r34 = this.queue.getPlayingPeriod();
        if (r12 == false) goto L18;
    L27:
        return r12;
    L18:
        if (r34.prepared == false) goto L27;
        if (r11 >= 500000) goto L27;
        if (this.backBufferDurationUs <= 0) goto L24;
    L25:
        r34.mediaPeriod.discardBuffer(this.playbackInfo.positionUs, false);
        return this.loadControl.shouldContinueLoading(r5);
    L24:
        if (this.retainBackBufferFromKeyframe == false) goto L27;
    L14:
        r33 = -9223372036854775807L;
        goto L13
    L9:
        r32 = r1.toPeriodTime(r4) - r1.info.startPositionUs;
        goto L8
    }

    private boolean shouldPlayWhenReady() {
        PlaybackInfo r1 = this.playbackInfo;
        if (r1.playWhenReady == true) goto L5;
        return false;
    L5:
        if (r1.playbackSuppressionReason != 0) goto L10;
        return true;
    L10:
        return false;
    }

    private boolean shouldTransitionToReadyState(boolean r22) {
        if (this.enabledRendererCount == 0) goto L5;
        boolean r1 = false;
        if (r22 == true) goto L10;
        return false;
    L10:
        if (this.playbackInfo.isLoading == true) goto L12;
        return true;
    L12:
        MediaPeriodHolder r2 = this.queue.getPlayingPeriod();
        if (shouldUseLivePlaybackSpeedControl(this.playbackInfo.timeline, r2.info.id) == false) goto L16;
        long r4 = this.livePlaybackSpeedControl.getTargetLiveOffsetUs();
    L15:
        long r17 = r4;
        MediaPeriodHolder r42 = this.queue.getLoadingPeriod();
        if (r42.isFullyBuffered() == true) goto L20;
    L22:
        boolean r5 = false;
    L24:
        if (r42.info.id.isAd() == true) goto L26;
    L28:
        if (r5 == true) goto L33;
        if (r1 == true) goto L33;
        return this.loadControl.shouldStartPlayback(new LoadControl.Parameters(this.playerId, this.playbackInfo.timeline, r2.info.id, r2.toPeriodTime(this.rendererPositionUs), getTotalBufferedDurationUs(r42.getBufferedPositionUs()), this.mediaClock.getPlaybackParameters().speed, this.playbackInfo.playWhenReady, this.isRebuffering, r17, this.lastRebufferRealtimeMs));
    L33:
        return true;
    L26:
        if (r42.prepared == true) goto L28;
        r1 = true;
        goto L28
    L20:
        if (r42.info.isFinal == false) goto L22;
        r5 = true;
        goto L24
    L16:
        r4 = -9223372036854775807L;
        goto L15
    L5:
        return isTimelineReady();
    }

    private boolean shouldUseLivePlaybackSpeedControl(Timeline r5, MediaSource.MediaPeriodId r6) {
        if (r6.isAd() == false) goto L5;
    L15:
        return false;
    L5:
        if (r5.isEmpty() == true) goto L15;
        r5.getWindow(r5.getPeriodByUid(r6.periodUid, this.period).windowIndex, this.window);
        if (this.window.isLive() == false) goto L15;
        Timeline.Window r4 = this.window;
        if (r4.isDynamic == false) goto L15;
        if (r4.windowStartTimeMs == (-9223372036854775807L)) goto L15;
        return true;
    }

    private void startRenderers() {
        MediaPeriodHolder r0 = this.queue.getPlayingPeriod();
        if (r0 == null) goto L13;
        TrackSelectorResult r02 = r0.getTrackSelectorResult();
        int r1 = 0;
    L7:
        if (r1 >= this.renderers.length) goto L17;
        if (r02.isRendererEnabled(r1) == false) goto L12;
        this.renderers[r1].start();
    L12:
        r1 = r1 + 1;
        goto L7
    L17:
        return;
    }

    private void stopInternal(boolean r3, boolean r4) {
        if (r3 == false) goto L5;
    L8:
        boolean r32 = true;
    L9:
        resetInternal(r32, false, true, false);
        this.playbackInfoUpdate.incrementPendingOperationAcks(r4 ? 1 : 0);
        this.loadControl.onStopped(this.playerId);
        this.audioFocusManager.updateAudioFocus(this.playbackInfo.playWhenReady, 1);
        setState(1);
        return;
    L5:
        if (this.foregroundMode == false) goto L8;
        r32 = false;
        goto L9
    }

    private void stopRenderers() {
        this.mediaClock.stop();
        RendererHolder[] r3 = this.renderers;
        int r0 = r3.length;
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        r3[r1].stop();
        r1 = r1 + 1;
        goto L3
    }

    private void updateIsLoading() {
        MediaPeriodHolder r0 = this.queue.getLoadingPeriod();
        if (this.shouldContinueLoading == true) goto L9;
        if (r0 != null) goto L6;
    L8:
        boolean r02 = false;
    L10:
        PlaybackInfo r1 = this.playbackInfo;
        if (r02 == r1.isLoading) goto L14;
        this.playbackInfo = r1.copyWithIsLoading(r02);
        return;
    L14:
        return;
    L6:
        if (r0.mediaPeriod.isLoading() == false) goto L8;
    L9:
        r02 = true;
        goto L10
    }

    private void updateLoadControlTrackSelection(MediaSource.MediaPeriodId r20, TrackGroupArray r21, TrackSelectorResult r22) {
        MediaPeriodHolder r1 = (MediaPeriodHolder) Assertions.checkNotNull(this.queue.getLoadingPeriod());
        MediaPeriodHolder r2 = this.queue.getPlayingPeriod();
        long r3 = this.rendererPositionUs;
        if (r1 != r2) goto L6;
        long r23 = r1.toPeriodTime(r3);
    L5:
        long r8 = r23;
        long r10 = getTotalBufferedDurationUs(r1.getBufferedPositionUs());
        if (shouldUseLivePlaybackSpeedControl(this.playbackInfo.timeline, r1.info.id) == false) goto L11;
        long r12 = this.livePlaybackSpeedControl.getTargetLiveOffsetUs();
    L10:
        long r15 = r12;
        this.loadControl.onTracksSelected(new LoadControl.Parameters(this.playerId, this.playbackInfo.timeline, r20, r8, r10, this.mediaClock.getPlaybackParameters().speed, this.playbackInfo.playWhenReady, this.isRebuffering, r15, this.lastRebufferRealtimeMs), r21, r22.selections);
        return;
    L11:
        r12 = -9223372036854775807L;
        goto L10
    L6:
        r23 = r1.toPeriodTime(r3) - r1.info.startPositionUs;
        goto L5
    }

    private void updateMediaSourcesWithMediaItemsInternal(int r3, int r4, List<MediaItem> r5) {
        this.playbackInfoUpdate.incrementPendingOperationAcks(1);
        handleMediaSourceListInfoRefreshed(this.mediaSourceList.updateMediaSourcesWithMediaItems(r3, r4, r5), false);
    }

    private void updatePeriods() {
        if (this.playbackInfo.timeline.isEmpty() == false) goto L5;
        return;
    L5:
        if (this.mediaSourceList.isPrepared() == false) goto L10;
        boolean r0 = maybeUpdateLoadingPeriod();
        maybeUpdatePrewarmingPeriod();
        maybeUpdateReadingPeriod();
        maybeUpdateReadingRenderers();
        maybeUpdatePlayingPeriod();
        maybeUpdatePreloadPeriods(r0);
        return;
    }

    private static int updatePlayWhenReadyChangeReason(int r2, int r3) {
        if (r2 != (-1)) goto L5;
        return 2;
    L5:
        if (r3 != 2) goto L8;
        return 1;
    L8:
        return r3;
    }

    private void updatePlayWhenReadyWithAudioFocus() {
        PlaybackInfo r0 = this.playbackInfo;
        updatePlayWhenReadyWithAudioFocus(r0.playWhenReady, r0.playbackSuppressionReason, r0.playWhenReadyChangeReason);
    }

    private void updatePlayWhenReadyWithAudioFocus(boolean r3, int r4, int r5) {
        updatePlayWhenReadyWithAudioFocus(r3, this.audioFocusManager.updateAudioFocus(r3, this.playbackInfo.playbackState), r4, r5);
    }

    private void updatePlayWhenReadyWithAudioFocus(boolean r3, int r4, int r5, int r6) {
        if (r3 == true) goto L5;
    L7:
        boolean r32 = false;
    L8:
        int r62 = updatePlayWhenReadyChangeReason(r4, r6);
        int r42 = updatePlaybackSuppressionReason(r4, r5);
        PlaybackInfo r52 = this.playbackInfo;
        if (r52.playWhenReady == r32) goto L11;
    L15:
        this.playbackInfo = r52.copyWithPlayWhenReady(r32, r62, r42);
        updateRebufferingState(false, false);
        notifyTrackSelectionPlayWhenReadyChanged(r32);
        if (shouldPlayWhenReady() == true) goto L22;
        stopRenderers();
        updatePlaybackPositions();
        PlaybackInfo r33 = this.playbackInfo;
        if (r33.sleepingForOffload == false) goto L20;
        this.playbackInfo = r33.copyWithSleepingForOffload(false);
    L20:
        this.queue.reevaluateBuffer(this.rendererPositionUs);
        return;
    L22:
        int r34 = this.playbackInfo.playbackState;
        if (r34 != 3) goto L26;
        this.mediaClock.start();
        startRenderers();
        this.handler.sendEmptyMessage(2);
        return;
    L26:
        if (r34 != 2) goto L29;
        this.handler.sendEmptyMessage(2);
        return;
    L29:
        return;
    L11:
        if (r52.playbackSuppressionReason != r42) goto L15;
        if (r52.playWhenReadyChangeReason != r62) goto L15;
        return;
    L5:
        if (r4 == (-1)) goto L7;
        r32 = true;
        goto L8
    }

    private void updatePlaybackPositions() {
        MediaPeriodHolder r1 = this.queue.getPlayingPeriod();
        if (r1 != null) goto L6;
        return;
    L6:
        if (r1.prepared == false) goto L8;
        long r5 = r1.mediaPeriod.readDiscontinuity();
    L10:
        if (r5 != (-9223372036854775807L)) goto L12;
        DefaultMediaClock r2 = this.mediaClock;
        if (r1 == this.queue.getReadingPeriod()) goto L20;
        boolean r3 = true;
    L21:
        long r22 = r2.syncAndGetPositionUs(r3);
        this.rendererPositionUs = r22;
        long r23 = r1.toPeriodTime(r22);
        maybeTriggerPendingMessages(this.playbackInfo.positionUs, r23);
        if (this.mediaClock.hasSkippedSilenceSinceLastCall() == false) goto L24;
        boolean r8 = !this.playbackInfoUpdate.positionDiscontinuity;
        PlaybackInfo r12 = this.playbackInfo;
        this.playbackInfo = handlePositionDiscontinuity(r12.periodId, r23, r12.requestedContentPositionUs, r23, r8, 6);
    L25:
        MediaPeriodHolder r13 = this.queue.getLoadingPeriod();
        this.playbackInfo.bufferedPositionUs = r13.getBufferedPositionUs();
        this.playbackInfo.totalBufferedDurationUs = getTotalBufferedDurationUs();
        PlaybackInfo r14 = this.playbackInfo;
        if (r14.playWhenReady == true) goto L28;
        return;
    L28:
        if (r14.playbackState == 3) goto L30;
        return;
    L30:
        if (shouldUseLivePlaybackSpeedControl(r14.timeline, r14.periodId) == true) goto L32;
        return;
    L32:
        if (this.playbackInfo.playbackParameters.speed != 1.0f) goto L40;
        float r15 = this.livePlaybackSpeedControl.getAdjustedPlaybackSpeed(getCurrentLiveOffsetUs(), this.playbackInfo.totalBufferedDurationUs);
        if (this.mediaClock.getPlaybackParameters().speed == r15) goto L41;
        setMediaClockPlaybackParameters(this.playbackInfo.playbackParameters.withSpeed(r15));
        handlePlaybackParameters(this.playbackInfo.playbackParameters, this.mediaClock.getPlaybackParameters().speed, false, false);
        return;
    L41:
        return;
    L40:
        return;
    L24:
        this.playbackInfo.updatePositionUs(r23);
        goto L25
    L20:
        r3 = false;
        goto L21
    L12:
        if (r1.isFullyBuffered() == true) goto L14;
        this.queue.removeAfter(r1);
        handleLoadingMediaPeriodChanged(false);
        maybeContinueLoading();
    L14:
        resetRendererPosition(r5);
        if (r5 == this.playbackInfo.positionUs) goto L25;
        PlaybackInfo r16 = this.playbackInfo;
        long r11 = r5;
        this.playbackInfo = handlePositionDiscontinuity(r16.periodId, r11, r16.requestedContentPositionUs, r11, true, 5);
        goto L25
    L8:
        r5 = -9223372036854775807L;
        goto L10
    }

    private void updatePlaybackSpeedSettingsForNewPeriod(Timeline r4, MediaSource.MediaPeriodId r5, Timeline r6, MediaSource.MediaPeriodId r7, long r8, boolean r10) {
        if (shouldUseLivePlaybackSpeedControl(r4, r5) == false) goto L5;
        r4.getWindow(r4.getPeriodByUid(r5.periodUid, this.period).windowIndex, this.window);
        this.livePlaybackSpeedControl.setLiveConfiguration((MediaItem.LiveConfiguration) Util.castNonNull(this.window.liveConfiguration));
        if (r8 == (-9223372036854775807L)) goto L16;
        this.livePlaybackSpeedControl.setTargetLiveOffsetOverrideUs(getLiveOffsetUs(r4, r5.periodUid, r8));
        return;
    L16:
        Object r42 = this.window.uid;
        if (r6.isEmpty() == true) goto L19;
        Object r52 = r6.getWindow(r6.getPeriodByUid(r7.periodUid, this.period).windowIndex, this.window).uid;
    L21:
        if (Objects.equals(r52, r42) == false) goto L25;
        if (r10 == true) goto L25;
        return;
    L25:
        this.livePlaybackSpeedControl.setTargetLiveOffsetOverrideUs(-9223372036854775807L);
        return;
    L19:
        r52 = null;
        goto L21
    L5:
        if (r5.isAd() == false) goto L7;
        PlaybackParameters r43 = PlaybackParameters.DEFAULT;
    L9:
        if (this.mediaClock.getPlaybackParameters().equals(r43) == true) goto L24;
        setMediaClockPlaybackParameters(r43);
        handlePlaybackParameters(this.playbackInfo.playbackParameters, r43.speed, false, false);
        return;
    L24:
        return;
    L7:
        r43 = this.playbackInfo.playbackParameters;
        goto L9
    }

    private static int updatePlaybackSuppressionReason(int r1, int r2) {
        if (r1 != 0) goto L5;
        return 1;
    L5:
        if (r2 != 1) goto L8;
        return 0;
    L8:
        return r2;
    }

    private void updateRebufferingState(boolean r1, boolean r2) {
        this.isRebuffering = r1;
        if (r1 == false) goto L6;
        if (r2 == true) goto L6;
        long r12 = this.clock.elapsedRealtime();
    L7:
        this.lastRebufferRealtimeMs = r12;
        return;
    L6:
        r12 = -9223372036854775807L;
        goto L7
    }

    private boolean updateRenderersForTransition() {
        MediaPeriodHolder r2 = this.queue.getReadingPeriod();
        TrackSelectorResult r0 = r2.getTrackSelectorResult();
        int r3 = 0;
        boolean r7 = true;
        int r4 = 0;
    L3:
        RendererHolder[] r5 = this.renderers;
        if (r4 >= r5.length) goto L15;
        int r52 = r5[r4].getEnabledRendererCount();
        int r6 = this.renderers[r4].replaceStreamsOrDisableRendererForTransition(r2, r0, this.mediaClock);
        if ((r6 & 2) != 0) goto L8;
    L10:
        this.enabledRendererCount -= r52 - this.renderers[r4].getEnabledRendererCount();
        if ((r6 & 1) == 0) goto L13;
        boolean r53 = true;
    L14:
        r7 = r7 & r53;
        r4 = r4 + 1;
        goto L3
    L13:
        r53 = false;
        goto L14
    L8:
        if (this.offloadSchedulingEnabled == false) goto L10;
        setOffloadSchedulingEnabled(false);
        goto L10
    L15:
        if (r7 == true) goto L17;
    L25:
        return r7;
    L17:
        if (r3 >= this.renderers.length) goto L25;
        if (r0.isRendererEnabled(r3) == true) goto L21;
    L23:
        ExoPlayerImplInternal r1 = this;
    L24:
        r3 = r3 + 1;
        ExoPlayerImplInternal this = r1;
        goto L17
    L21:
        if (this.renderers[r3].isReadingFromPeriod(r2) == true) goto L23;
        r1 = this;
        r1.enableRenderer(r2, r3, false, r2.getStartPositionRendererTime());
        goto L24
    }

    private void updateTrackSelectionPlaybackSpeed(float r5) {
        MediaPeriodHolder r4 = this.queue.getPlayingPeriod();
    L3:
        if (r4 == null) goto L11;
        ExoTrackSelection[] r0 = r4.getTrackSelectorResult().selections;
        int r1 = r0.length;
        int r2 = 0;
    L5:
        if (r2 >= r1) goto L10;
        ExoTrackSelection r3 = r0[r2];
        if (r3 == null) goto L9;
        r3.onPlaybackSpeed(r5);
    L9:
        r2 = r2 + 1;
        goto L5
    L10:
        r4 = r4.getNext();
        goto L3
    }

    public void addMediaSources(int r8, List<MediaSourceList.MediaSourceHolder> r9, ShuffleOrder r10) {
        this.handler.obtainMessage(18, r8, 0, new MediaSourceListUpdateMessage(r9, r10, -1, -9223372036854775807L, null)).sendToTarget();
    }

    @Override // androidx.media3.exoplayer.AudioFocusManager.PlayerControl
    public void executePlayerCommand(int r3) {
        this.handler.obtainMessage(33, r3, 0).sendToTarget();
    }

    public void experimentalSetForegroundModeTimeoutMs(long r1) {
        this.setForegroundModeTimeoutMs = r1;
    }

    public Looper getPlaybackLooper() {
        return this.playbackLooper;
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message r16) {
        int r2 = 1000;
    L15:
        e = move-exception;
        int r4 = e.dataType;
        if (r4 == 1) goto L93;
        if (r4 == 4) goto L98;
    L101:
        handleIoException(e, r2);
    L149:
        maybeNotifyPlaybackInfoChanged();
        return true;
    L98:
        if (e.contentIsMalformed == false) goto L100;
        r2 = 3002;
        goto L101
    L100:
        r2 = 3004;
        goto L101
    L93:
        if (e.contentIsMalformed == false) goto L95;
        r2 = 3001;
        goto L101
    L95:
        r2 = 3003;
    L13:
        e = move-exception;
        handleIoException(e, e.reason);
    L19:
        e = e;
        if (e.type != 1) goto L111;
        MediaPeriodHolder r22 = this.queue.getReadingPeriod();
        if (r22 == null) goto L111;
        if (e.mediaPeriodId != null) goto L111;
        e = e.copyWithMediaPeriodId(r22.info.id);
    L111:
        if (e.type != 1) goto L125;
        MediaSource.MediaPeriodId r23 = e.mediaPeriodId;
        if (r23 == null) goto L125;
        if (isRendererPrewarmingMediaPeriod(e.rendererIndex, r23) == false) goto L125;
        this.isPrewarmingDisabledUntilNextTransition = true;
        disableAndResetPrewarmingRenderers();
        MediaPeriodHolder r0 = this.queue.getPrewarmingPeriod();
        MediaPeriodHolder r24 = this.queue.getPlayingPeriod();
        if (this.queue.getPlayingPeriod() == r0) goto L122;
    L118:
        if (r24 == null) goto L122;
        if (r24.getNext() == r0) goto L122;
        r24 = r24.getNext();
    L122:
        this.queue.removeAfter(r24);
        if (this.playbackInfo.playbackState == 4) goto L149;
        maybeContinueLoading();
        this.handler.sendEmptyMessage(2);
    L125:
        ExoPlaybackException r25 = this.pendingRecoverableRendererError;
        if (r25 == null) goto L129;
        r25.addSuppressed(e);
        e = this.pendingRecoverableRendererError;
    L129:
        if (e.type != 1) goto L137;
        if (this.queue.getPlayingPeriod() == this.queue.getReadingPeriod()) goto L137;
    L132:
        MediaPeriodHolder r26 = this.queue.getPlayingPeriod();
        MediaPeriodHolder r3 = this.queue.getReadingPeriod();
        MediaPeriodQueue r42 = this.queue;
        if (r26 == r3) goto L135;
        r42.advancePlayingPeriod();
        goto L132
    L135:
        MediaPeriodHolder r27 = (MediaPeriodHolder) Assertions.checkNotNull(r42.getPlayingPeriod());
        maybeNotifyPlaybackInfoChanged();
        MediaPeriodInfo r28 = r27.info;
        MediaSource.MediaPeriodId r32 = r28.id;
        long r33 = r28.startPositionUs;
        this.playbackInfo = handlePositionDiscontinuity(r32, r33, r28.requestedContentPositionUs, r33, true, 0);
    L137:
        if (e.isRecoverable == true) goto L139;
    L148:
        Log.e("ExoPlayerImplInternal", "Playback error", e);
        stopInternal(true, false);
        this.playbackInfo = this.playbackInfo.copyWithPlaybackError(e);
        goto L149
    L139:
        if (this.pendingRecoverableRendererError == null) goto L144;
        int r29 = e.errorCode;
        if (r29 == 5004) goto L144;
        if (r29 != 5003) goto L148;
    L144:
        Log.w("ExoPlayerImplInternal", "Recoverable renderer error", e);
        if (this.pendingRecoverableRendererError != null) goto L147;
        this.pendingRecoverableRendererError = e;
    L147:
        HandlerWrapper r210 = this.handler;
        r210.sendMessageAtFrontOfQueue(r210.obtainMessage(25, e));
    L17:
        e = move-exception;
        handleIoException(e, e.errorCode);
    L11:
        e = move-exception;
        handleIoException(e, 1002);
    L9:
        e = move-exception;
        handleIoException(e, CastStatusCodes.AUTHENTICATION_FAILED);
    L7:
        e = move-exception;
        if ((e instanceof IllegalStateException) == false) goto L84;
    L85:
        r2 = 1004;
    L86:
        ExoPlaybackException r02 = ExoPlaybackException.createForUnexpected(e, r2);
        Log.e("ExoPlayerImplInternal", "Playback error", r02);
        stopInternal(true, false);
        this.playbackInfo = this.playbackInfo.copyWithPlaybackError(r02);
        goto L149
    L84:
        if ((e instanceof IllegalArgumentException) == false) goto L86;
    L4:
        switch(r16.what) {
            case 1: goto L76;
            case 2: goto L74;
            case 3: goto L73;
            case 4: goto L72;
            case 5: goto L71;
            case 6: goto L70;
            case 7: goto L68;
            case 8: goto L67;
            case 9: goto L66;
            case 10: goto L65;
            case 11: goto L64;
            case 12: goto L60;
            case 13: goto L55;
            case 14: goto L53;
            case 15: goto L52;
            case 16: goto L51;
            case 17: goto L50;
            case 18: goto L49;
            case 19: goto L48;
            case 20: goto L47;
            case 21: goto L46;
            case 22: goto L45;
            case 23: goto L41;
            case 24: goto L5;
            case 25: goto L39;
            case 26: goto L38;
            case 27: goto L37;
            case 28: goto L36;
            case 29: goto L35;
            case 30: goto L34;
            case 31: goto L29;
            case 32: goto L28;
            case 33: goto L27;
            case 34: goto L26;
            case 35: goto L25;
            case 36: goto L24;
            case 37: goto L21;
            case 38: goto L6;
            default: goto L5;
        };     // Catch: RuntimeException -> L7 IOException -> L9 BehindLiveWindowException -> L11 DataSourceException -> L13 ParserException -> L15 DrmSession.DrmSessionException -> L17 ExoPlaybackException -> L19
    L5:
        return false;
    L6:
        setScrubbingModeParametersInternal((ScrubbingModeParameters) r16.obj);     // Catch: RuntimeException -> L7 IOException -> L9 BehindLiveWindowException -> L11 DataSourceException -> L13 ParserException -> L15 DrmSession.DrmSessionException -> L17 ExoPlaybackException -> L19
        goto L149
    L21:
        this.seekIsPendingWhileScrubbing = false;     // Catch: RuntimeException -> L7 IOException -> L9 BehindLiveWindowException -> L11 DataSourceException -> L13 ParserException -> L15 DrmSession.DrmSessionException -> L17 ExoPlaybackException -> L19
        SeekPosition r03 = this.queuedSeekWhileScrubbing;     // Catch: RuntimeException -> L7 IOException -> L9 BehindLiveWindowException -> L11 DataSourceException -> L13 ParserException -> L15 DrmSession.DrmSessionException -> L17 ExoPlaybackException -> L19
        if (r03 == null) goto L149;
        seekToInternal(r03, false);     // Catch: RuntimeException -> L7 IOException -> L9 BehindLiveWindowException -> L11 DataSourceException -> L13 ParserException -> L15 DrmSession.DrmSessionException -> L17 ExoPlaybackException -> L19
        this.queuedSeekWhileScrubbing = null;     // Catch: RuntimeException -> L7 IOException -> L9 BehindLiveWindowException -> L11 DataSourceException -> L13 ParserException -> L15 DrmSession.DrmSessionException -> L17 ExoPlaybackException -> L19
        goto L149
    L24:
        setScrubbingModeEnabledInternal(((Boolean) r16.obj).booleanValue());     // Catch: RuntimeException -> L7 IOException -> L9 BehindLiveWindowException -> L11 DataSourceException -> L13 ParserException -> L15 DrmSession.DrmSessionException -> L17 ExoPlaybackException -> L19
        goto L149
    L25:
        setVideoFrameMetadataListenerInternal((VideoFrameMetadataListener) r16.obj);     // Catch: RuntimeException -> L7 IOException -> L9 BehindLiveWindowException -> L11 DataSourceException -> L13 ParserException -> L15 DrmSession.DrmSessionException -> L17 ExoPlaybackException -> L19
        goto L149
    L26:
        handleAudioFocusVolumeMultiplierChange();     // Catch: RuntimeException -> L7 IOException -> L9 BehindLiveWindowException -> L11 DataSourceException -> L13 ParserException -> L15 DrmSession.DrmSessionException -> L17 ExoPlaybackException -> L19
        goto L149
    L27:
        handleAudioFocusPlayerCommandInternal(r16.arg1);     // Catch: RuntimeException -> L7 IOException -> L9 BehindLiveWindowException -> L11 DataSourceException -> L13 ParserException -> L15 DrmSession.DrmSessionException -> L17 ExoPlaybackException -> L19
        goto L149
    L28:
        setVolumeInternal(((Float) r16.obj).floatValue());     // Catch: RuntimeException -> L7 IOException -> L9 BehindLiveWindowException -> L11 DataSourceException -> L13 ParserException -> L15 DrmSession.DrmSessionException -> L17 ExoPlaybackException -> L19
        goto L149
    L29:
        AudioAttributes r43 = (AudioAttributes) r16.obj;     // Catch: RuntimeException -> L7 IOException -> L9 BehindLiveWindowException -> L11 DataSourceException -> L13 ParserException -> L15 DrmSession.DrmSessionException -> L17 ExoPlaybackException -> L19
        if (r16.arg1 == 0) goto L32;
        boolean r04 = true;
    L33:
        setAudioAttributesInternal(r43, r04);     // Catch: RuntimeException -> L7 IOException -> L9 BehindLiveWindowException -> L11 DataSourceException -> L13 ParserException -> L15 DrmSession.DrmSessionException -> L17 ExoPlaybackException -> L19
        goto L149
    L32:
        r04 = false;
        goto L33
    L34:
        Pair r05 = (Pair) r16.obj;     // Catch: RuntimeException -> L7 IOException -> L9 BehindLiveWindowException -> L11 DataSourceException -> L13 ParserException -> L15 DrmSession.DrmSessionException -> L17 ExoPlaybackException -> L19
        setVideoOutputInternal(r05.first, (ConditionVariable) r05.second);     // Catch: RuntimeException -> L7 IOException -> L9 BehindLiveWindowException -> L11 DataSourceException -> L13 ParserException -> L15 DrmSession.DrmSessionException -> L17 ExoPlaybackException -> L19
        goto L149
    L35:
        prepareInternal();     // Catch: RuntimeException -> L7 IOException -> L9 BehindLiveWindowException -> L11 DataSourceException -> L13 ParserException -> L15 DrmSession.DrmSessionException -> L17 ExoPlaybackException -> L19
        goto L149
    L36:
        setPreloadConfigurationInternal((ExoPlayer.PreloadConfiguration) r16.obj);     // Catch: RuntimeException -> L7 IOException -> L9 BehindLiveWindowException -> L11 DataSourceException -> L13 ParserException -> L15 DrmSession.DrmSessionException -> L17 ExoPlaybackException -> L19
        goto L149
    L37:
        updateMediaSourcesWithMediaItemsInternal(r16.arg1, r16.arg2, (List) r16.obj);     // Catch: RuntimeException -> L7 IOException -> L9 BehindLiveWindowException -> L11 DataSourceException -> L13 ParserException -> L15 DrmSession.DrmSessionException -> L17 ExoPlaybackException -> L19
        goto L149
    L38:
        reselectTracksInternalAndSeek();     // Catch: RuntimeException -> L7 IOException -> L9 BehindLiveWindowException -> L11 DataSourceException -> L13 ParserException -> L15 DrmSession.DrmSessionException -> L17 ExoPlaybackException -> L19
        goto L149
    L39:
        attemptRendererErrorRecovery();     // Catch: RuntimeException -> L7 IOException -> L9 BehindLiveWindowException -> L11 DataSourceException -> L13 ParserException -> L15 DrmSession.DrmSessionException -> L17 ExoPlaybackException -> L19
        goto L149
    L45:
        mediaSourceListUpdateRequestedInternal();     // Catch: RuntimeException -> L7 IOException -> L9 BehindLiveWindowException -> L11 DataSourceException -> L13 ParserException -> L15 DrmSession.DrmSessionException -> L17 ExoPlaybackException -> L19
        goto L149
    L46:
        setShuffleOrderInternal((ShuffleOrder) r16.obj);     // Catch: RuntimeException -> L7 IOException -> L9 BehindLiveWindowException -> L11 DataSourceException -> L13 ParserException -> L15 DrmSession.DrmSessionException -> L17 ExoPlaybackException -> L19
        goto L149
    L47:
        removeMediaItemsInternal(r16.arg1, r16.arg2, (ShuffleOrder) r16.obj);     // Catch: RuntimeException -> L7 IOException -> L9 BehindLiveWindowException -> L11 DataSourceException -> L13 ParserException -> L15 DrmSession.DrmSessionException -> L17 ExoPlaybackException -> L19
        goto L149
    L48:
        moveMediaItemsInternal((MoveMediaItemsMessage) r16.obj);     // Catch: RuntimeException -> L7 IOException -> L9 BehindLiveWindowException -> L11 DataSourceException -> L13 ParserException -> L15 DrmSession.DrmSessionException -> L17 ExoPlaybackException -> L19
        goto L149
    L49:
        addMediaItemsInternal((MediaSourceListUpdateMessage) r16.obj, r16.arg1);     // Catch: RuntimeException -> L7 IOException -> L9 BehindLiveWindowException -> L11 DataSourceException -> L13 ParserException -> L15 DrmSession.DrmSessionException -> L17 ExoPlaybackException -> L19
        goto L149
    L50:
        setMediaItemsInternal((MediaSourceListUpdateMessage) r16.obj);     // Catch: RuntimeException -> L7 IOException -> L9 BehindLiveWindowException -> L11 DataSourceException -> L13 ParserException -> L15 DrmSession.DrmSessionException -> L17 ExoPlaybackException -> L19
        goto L149
    L51:
        handlePlaybackParameters((PlaybackParameters) r16.obj, false);     // Catch: RuntimeException -> L7 IOException -> L9 BehindLiveWindowException -> L11 DataSourceException -> L13 ParserException -> L15 DrmSession.DrmSessionException -> L17 ExoPlaybackException -> L19
        goto L149
    L52:
        sendMessageToTargetThread((PlayerMessage) r16.obj);     // Catch: RuntimeException -> L7 IOException -> L9 BehindLiveWindowException -> L11 DataSourceException -> L13 ParserException -> L15 DrmSession.DrmSessionException -> L17 ExoPlaybackException -> L19
        goto L149
    L53:
        sendMessageInternal((PlayerMessage) r16.obj);     // Catch: RuntimeException -> L7 IOException -> L9 BehindLiveWindowException -> L11 DataSourceException -> L13 ParserException -> L15 DrmSession.DrmSessionException -> L17 ExoPlaybackException -> L19
        goto L149
    L64:
        setRepeatModeInternal(r16.arg1);     // Catch: RuntimeException -> L7 IOException -> L9 BehindLiveWindowException -> L11 DataSourceException -> L13 ParserException -> L15 DrmSession.DrmSessionException -> L17 ExoPlaybackException -> L19
        goto L149
    L65:
        reselectTracksInternal();     // Catch: RuntimeException -> L7 IOException -> L9 BehindLiveWindowException -> L11 DataSourceException -> L13 ParserException -> L15 DrmSession.DrmSessionException -> L17 ExoPlaybackException -> L19
        goto L149
    L66:
        handleContinueLoadingRequested((MediaPeriod) r16.obj);     // Catch: RuntimeException -> L7 IOException -> L9 BehindLiveWindowException -> L11 DataSourceException -> L13 ParserException -> L15 DrmSession.DrmSessionException -> L17 ExoPlaybackException -> L19
        goto L149
    L67:
        handlePeriodPrepared((MediaPeriod) r16.obj);     // Catch: RuntimeException -> L7 IOException -> L9 BehindLiveWindowException -> L11 DataSourceException -> L13 ParserException -> L15 DrmSession.DrmSessionException -> L17 ExoPlaybackException -> L19
        goto L149
    L68:
        releaseInternal((ConditionVariable) r16.obj);     // Catch: RuntimeException -> L7 IOException -> L9 BehindLiveWindowException -> L11 DataSourceException -> L13 ParserException -> L15 DrmSession.DrmSessionException -> L17 ExoPlaybackException -> L19
        return true;
    L70:
        stopInternal(false, true);     // Catch: RuntimeException -> L7 IOException -> L9 BehindLiveWindowException -> L11 DataSourceException -> L13 ParserException -> L15 DrmSession.DrmSessionException -> L17 ExoPlaybackException -> L19
        goto L149
    L71:
        setSeekParametersInternal((SeekParameters) r16.obj);     // Catch: RuntimeException -> L7 IOException -> L9 BehindLiveWindowException -> L11 DataSourceException -> L13 ParserException -> L15 DrmSession.DrmSessionException -> L17 ExoPlaybackException -> L19
        goto L149
    L72:
        setPlaybackParametersInternal((PlaybackParameters) r16.obj);     // Catch: RuntimeException -> L7 IOException -> L9 BehindLiveWindowException -> L11 DataSourceException -> L13 ParserException -> L15 DrmSession.DrmSessionException -> L17 ExoPlaybackException -> L19
        goto L149
    L73:
        seekToInternal((SeekPosition) r16.obj, true);     // Catch: RuntimeException -> L7 IOException -> L9 BehindLiveWindowException -> L11 DataSourceException -> L13 ParserException -> L15 DrmSession.DrmSessionException -> L17 ExoPlaybackException -> L19
        goto L149
    L74:
        doSomeWork();     // Catch: RuntimeException -> L7 IOException -> L9 BehindLiveWindowException -> L11 DataSourceException -> L13 ParserException -> L15 DrmSession.DrmSessionException -> L17 ExoPlaybackException -> L19
        goto L149
    L41:
        if (r16.arg1 == 0) goto L43;
        boolean r06 = true;
    L44:
        setPauseAtEndOfWindowInternal(r06);     // Catch: RuntimeException -> L7 IOException -> L9 BehindLiveWindowException -> L11 DataSourceException -> L13 ParserException -> L15 DrmSession.DrmSessionException -> L17 ExoPlaybackException -> L19
        goto L149
    L43:
        r06 = false;
        goto L44
    L55:
        if (r16.arg1 == 0) goto L57;
        boolean r44 = true;
    L58:
        setForegroundModeInternal(r44, (ConditionVariable) r16.obj);     // Catch: RuntimeException -> L7 IOException -> L9 BehindLiveWindowException -> L11 DataSourceException -> L13 ParserException -> L15 DrmSession.DrmSessionException -> L17 ExoPlaybackException -> L19
        goto L149
    L57:
        r44 = false;
        goto L58
    L60:
        if (r16.arg1 == 0) goto L62;
        boolean r07 = true;
    L63:
        setShuffleModeEnabledInternal(r07);     // Catch: RuntimeException -> L7 IOException -> L9 BehindLiveWindowException -> L11 DataSourceException -> L13 ParserException -> L15 DrmSession.DrmSessionException -> L17 ExoPlaybackException -> L19
        goto L149
    L62:
        r07 = false;
        goto L63
    L76:
        if (r16.arg1 == 0) goto L78;
        boolean r45 = true;
    L79:
        int r08 = r16.arg2;     // Catch: RuntimeException -> L7 IOException -> L9 BehindLiveWindowException -> L11 DataSourceException -> L13 ParserException -> L15 DrmSession.DrmSessionException -> L17 ExoPlaybackException -> L19
        setPlayWhenReadyInternal(r45, r08 >> 4, true, r08 & 15);     // Catch: RuntimeException -> L7 IOException -> L9 BehindLiveWindowException -> L11 DataSourceException -> L13 ParserException -> L15 DrmSession.DrmSessionException -> L17 ExoPlaybackException -> L19
        goto L149
    L78:
        r45 = false;
        goto L79
    }

    public void moveMediaSources(int r2, int r3, int r4, ShuffleOrder r5) {
        MoveMediaItemsMessage r0 = new MoveMediaItemsMessage(r2, r3, r4, r5);
        this.handler.obtainMessage(19, r0).sendToTarget();
    }

    public void onContinueLoadingRequested(MediaPeriod r2) {
        this.handler.obtainMessage(9, r2).sendToTarget();
    }

    @Override // androidx.media3.exoplayer.source.SequenceableLoader.Callback
    public /* bridge */ /* synthetic */ void onContinueLoadingRequested(SequenceableLoader r1) {
        onContinueLoadingRequested((MediaPeriod) r1);
    }

    @Override // androidx.media3.exoplayer.DefaultMediaClock.PlaybackParametersListener
    public void onPlaybackParametersChanged(PlaybackParameters r2) {
        this.handler.obtainMessage(16, r2).sendToTarget();
    }

    @Override // androidx.media3.exoplayer.MediaSourceList.MediaSourceListInfoRefreshListener
    public void onPlaylistUpdateRequested() {
        this.handler.removeMessages(2);
        this.handler.sendEmptyMessage(22);
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod.Callback
    public void onPrepared(MediaPeriod r2) {
        this.handler.obtainMessage(8, r2).sendToTarget();
    }

    @Override // androidx.media3.exoplayer.trackselection.TrackSelector.InvalidationListener
    public void onRendererCapabilitiesChanged(Renderer r1) {
        this.handler.sendEmptyMessage(26);
    }

    @Override // androidx.media3.exoplayer.trackselection.TrackSelector.InvalidationListener
    public void onTrackSelectionsInvalidated() {
        this.handler.sendEmptyMessage(10);
    }

    @Override // androidx.media3.exoplayer.video.VideoFrameMetadataListener
    public void onVideoFrameAboutToBeRendered(long r1, long r3, Format r5, MediaFormat r6) {
        if (this.seekIsPendingWhileScrubbing == false) goto L6;
        this.handler.obtainMessage(37).sendToTarget();
        return;
    }

    public void prepare() {
        this.handler.obtainMessage(29).sendToTarget();
    }

    public boolean release() {
        if (this.releasedOnApplicationThread == false) goto L5;
    L9:
        return true;
    L5:
        if (this.playbackLooper.getThread().isAlive() == false) goto L9;
        this.releasedOnApplicationThread = true;
        ConditionVariable r0 = new ConditionVariable(this.clock);
        this.handler.obtainMessage(7, r0).sendToTarget();
        return r0.blockUninterruptible(this.releaseTimeoutMs);
    }

    public void removeMediaSources(int r2, int r3, ShuffleOrder r4) {
        this.handler.obtainMessage(20, r2, r3, r4).sendToTarget();
    }

    public void seekTo(Timeline r2, int r3, long r4) {
        this.handler.obtainMessage(3, new SeekPosition(r2, r3, r4)).sendToTarget();
    }

    @Override // androidx.media3.exoplayer.PlayerMessage.Sender
    public void sendMessage(PlayerMessage r2) {
        if (this.releasedOnApplicationThread == false) goto L5;
    L9:
        Log.w("ExoPlayerImplInternal", "Ignoring messages sent after release.");
        r2.markAsProcessed(false);
        return;
    L5:
        if (this.playbackLooper.getThread().isAlive() == false) goto L9;
        this.handler.obtainMessage(14, r2).sendToTarget();
    }

    public void setAudioAttributes(AudioAttributes r3, boolean r4) {
        this.handler.obtainMessage(31, r4 ? 1 : 0, 0, r3).sendToTarget();
    }

    public void setMediaSources(List<MediaSourceList.MediaSourceHolder> r8, int r9, long r10, ShuffleOrder r12) {
        this.handler.obtainMessage(17, new MediaSourceListUpdateMessage(r8, r12, r9, r10, null)).sendToTarget();
    }

    public void setPlayWhenReady(boolean r1, int r2, int r3) {
        HandlerWrapper r0 = this.handler;
        int r12 = r1 ? 1 : 0;
        r0.obtainMessage(1, r12, r2 | (r3 << 4)).sendToTarget();
    }

    public void setPlaybackParameters(PlaybackParameters r2) {
        this.handler.obtainMessage(4, r2).sendToTarget();
    }

    public void setRepeatMode(int r3) {
        this.handler.obtainMessage(11, r3, 0).sendToTarget();
    }

    public void setScrubbingModeParameters(ScrubbingModeParameters r2) {
        this.handler.obtainMessage(38, r2).sendToTarget();
    }

    public void setSeekParameters(SeekParameters r2) {
        this.handler.obtainMessage(5, r2).sendToTarget();
    }

    public void setShuffleModeEnabled(boolean r3) {
        this.handler.obtainMessage(12, r3 ? 1 : 0, 0).sendToTarget();
    }

    public boolean setVideoOutput(Object r4, long r5) {
        if (this.releasedOnApplicationThread == false) goto L5;
    L11:
        return true;
    L5:
        if (this.playbackLooper.getThread().isAlive() == false) goto L11;
        ConditionVariable r0 = new ConditionVariable(this.clock);
        this.handler.obtainMessage(30, new Pair(r4, r0)).sendToTarget();
        if (r5 == (-9223372036854775807L)) goto L11;
        return r0.blockUninterruptible(r5);
    }

    public void setVolume(float r2) {
        this.handler.obtainMessage(32, Float.valueOf(r2)).sendToTarget();
    }

    @Override // androidx.media3.exoplayer.AudioFocusManager.PlayerControl
    public void setVolumeMultiplier(float r1) {
        this.handler.sendEmptyMessage(34);
    }

    public void stop() {
        this.handler.obtainMessage(6).sendToTarget();
    }

    public void updateMediaSourcesWithMediaItems(int r2, int r3, List<MediaItem> r4) {
        this.handler.obtainMessage(27, r2, r3, r4).sendToTarget();
    }
}
