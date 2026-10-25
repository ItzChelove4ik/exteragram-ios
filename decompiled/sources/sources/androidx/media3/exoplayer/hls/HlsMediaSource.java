package androidx.media3.exoplayer.hls;

import android.os.Looper;
import androidx.media3.common.MediaItem;
import androidx.media3.common.MediaLibraryInfo;
import androidx.media3.common.StreamKey;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.DataSource;
import androidx.media3.datasource.TransferListener;
import androidx.media3.exoplayer.drm.DefaultDrmSessionManagerProvider;
import androidx.media3.exoplayer.drm.DrmSessionEventListener;
import androidx.media3.exoplayer.drm.DrmSessionManager;
import androidx.media3.exoplayer.drm.DrmSessionManagerProvider;
import androidx.media3.exoplayer.hls.playlist.DefaultHlsPlaylistParserFactory;
import androidx.media3.exoplayer.hls.playlist.DefaultHlsPlaylistTracker;
import androidx.media3.exoplayer.hls.playlist.FilteringHlsPlaylistParserFactory;
import androidx.media3.exoplayer.hls.playlist.HlsMediaPlaylist;
import androidx.media3.exoplayer.hls.playlist.HlsMultivariantPlaylist;
import androidx.media3.exoplayer.hls.playlist.HlsPlaylistParserFactory;
import androidx.media3.exoplayer.hls.playlist.HlsPlaylistTracker;
import androidx.media3.exoplayer.source.BaseMediaSource;
import androidx.media3.exoplayer.source.CompositeSequenceableLoaderFactory;
import androidx.media3.exoplayer.source.DefaultCompositeSequenceableLoaderFactory;
import androidx.media3.exoplayer.source.MediaPeriod;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.source.MediaSourceEventListener;
import androidx.media3.exoplayer.source.MediaSourceFactory;
import androidx.media3.exoplayer.source.SinglePeriodTimeline;
import androidx.media3.exoplayer.upstream.Allocator;
import androidx.media3.exoplayer.upstream.CmcdConfiguration;
import androidx.media3.exoplayer.upstream.DefaultLoadErrorHandlingPolicy;
import androidx.media3.exoplayer.upstream.LoadErrorHandlingPolicy;
import androidx.media3.extractor.text.SubtitleParser;
import java.util.List;
import java.util.Objects;

/* loaded from: classes4.dex */
public final class HlsMediaSource extends BaseMediaSource implements HlsPlaylistTracker.PrimaryPlaylistListener {
    private final boolean allowChunklessPreparation;
    private final CompositeSequenceableLoaderFactory compositeSequenceableLoaderFactory;
    private final HlsDataSourceFactory dataSourceFactory;
    private final DrmSessionManager drmSessionManager;
    private final long elapsedRealTimeOffsetMs;
    private final HlsExtractorFactory extractorFactory;
    private MediaItem.LiveConfiguration liveConfiguration;
    private final LoadErrorHandlingPolicy loadErrorHandlingPolicy;
    private MediaItem mediaItem;
    private TransferListener mediaTransferListener;
    private final int metadataType;
    private final HlsPlaylistTracker playlistTracker;
    private final long timestampAdjusterInitializationTimeoutMs;
    private final boolean useSessionKeys;

    /* renamed from: androidx.media3.exoplayer.hls.HlsMediaSource$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static final class Factory implements MediaSourceFactory {
        public static final /* synthetic */ int $r8$clinit = 0;
        private boolean allowChunklessPreparation;
        private int codecsToParseWithinGopSampleDependencies;
        private CompositeSequenceableLoaderFactory compositeSequenceableLoaderFactory;
        private DrmSessionManagerProvider drmSessionManagerProvider;
        private long elapsedRealTimeOffsetMs;
        private HlsExtractorFactory extractorFactory;
        private final HlsDataSourceFactory hlsDataSourceFactory;
        private LoadErrorHandlingPolicy loadErrorHandlingPolicy;
        private int metadataType;
        private boolean parseSubtitlesDuringExtraction;
        private HlsPlaylistParserFactory playlistParserFactory;
        private HlsPlaylistTracker.Factory playlistTrackerFactory;
        private SubtitleParser.Factory subtitleParserFactoryOverride;
        private long timestampAdjusterInitializationTimeoutMs;
        private boolean useSessionKeys;

        public Factory(DataSource.Factory r2) {
            this(new DefaultHlsDataSourceFactory(r2));
        }

        public Factory(HlsDataSourceFactory r3) {
            this.hlsDataSourceFactory = (HlsDataSourceFactory) Assertions.checkNotNull(r3);
            this.drmSessionManagerProvider = new DefaultDrmSessionManagerProvider();
            this.playlistParserFactory = new DefaultHlsPlaylistParserFactory();
            this.playlistTrackerFactory = DefaultHlsPlaylistTracker.FACTORY;
            this.loadErrorHandlingPolicy = new DefaultLoadErrorHandlingPolicy();
            this.compositeSequenceableLoaderFactory = new DefaultCompositeSequenceableLoaderFactory();
            this.metadataType = 1;
            this.elapsedRealTimeOffsetMs = -9223372036854775807L;
            this.allowChunklessPreparation = true;
            experimentalParseSubtitlesDuringExtraction(true);
        }

        @Override // androidx.media3.exoplayer.source.MediaSource.Factory
        public HlsMediaSource createMediaSource(MediaItem r20) {
            Assertions.checkNotNull(r20.localConfiguration);
            if (this.extractorFactory != null) goto L5;
            this.extractorFactory = new DefaultHlsExtractorFactory();
        L5:
            SubtitleParser.Factory r2 = this.subtitleParserFactoryOverride;
            if (r2 == null) goto L8;
            this.extractorFactory.setSubtitleParserFactory(r2);
        L8:
            this.extractorFactory.experimentalParseSubtitlesDuringExtraction(this.parseSubtitlesDuringExtraction);
            this.extractorFactory.experimentalSetCodecsToParseWithinGopSampleDependencies(this.codecsToParseWithinGopSampleDependencies);
            HlsExtractorFactory r3 = this.extractorFactory;
            HlsPlaylistParserFactory r22 = this.playlistParserFactory;
            List<StreamKey> r4 = r20.localConfiguration.streamKeys;
            if (r4.isEmpty() == true) goto L11;
            r22 = new FilteringHlsPlaylistParserFactory(r22, r4);
        L11:
            HlsDataSourceFactory r5 = this.hlsDataSourceFactory;
            CompositeSequenceableLoaderFactory r42 = this.compositeSequenceableLoaderFactory;
            DrmSessionManager r7 = this.drmSessionManagerProvider.get(r20);
            LoadErrorHandlingPolicy r72 = this.loadErrorHandlingPolicy;
            return new HlsMediaSource(r20, r5, r3, r42, null, r7, r72, this.playlistTrackerFactory.createTracker(this.hlsDataSourceFactory, r72, r22, null), this.elapsedRealTimeOffsetMs, this.allowChunklessPreparation, this.metadataType, this.useSessionKeys, this.timestampAdjusterInitializationTimeoutMs, null);
        }

        @Override // androidx.media3.exoplayer.source.MediaSource.Factory
        public /* bridge */ /* synthetic */ MediaSource createMediaSource(MediaItem r1) {
            return createMediaSource(r1);
        }

        @Override // androidx.media3.exoplayer.source.MediaSource.Factory
        @Deprecated
        public Factory experimentalParseSubtitlesDuringExtraction(boolean r1) {
            this.parseSubtitlesDuringExtraction = r1;
            return this;
        }

        @Override // androidx.media3.exoplayer.source.MediaSource.Factory
        @Deprecated
        public /* bridge */ /* synthetic */ MediaSource.Factory experimentalParseSubtitlesDuringExtraction(boolean r1) {
            return experimentalParseSubtitlesDuringExtraction(r1);
        }

        @Override // androidx.media3.exoplayer.source.MediaSource.Factory
        public Factory experimentalSetCodecsToParseWithinGopSampleDependencies(int r1) {
            this.codecsToParseWithinGopSampleDependencies = r1;
            return this;
        }

        @Override // androidx.media3.exoplayer.source.MediaSource.Factory
        public /* bridge */ /* synthetic */ MediaSource.Factory experimentalSetCodecsToParseWithinGopSampleDependencies(int r1) {
            return experimentalSetCodecsToParseWithinGopSampleDependencies(r1);
        }

        @Override // androidx.media3.exoplayer.source.MediaSource.Factory
        public Factory setDrmSessionManagerProvider(DrmSessionManagerProvider r2) {
            this.drmSessionManagerProvider = (DrmSessionManagerProvider) Assertions.checkNotNull(r2, "MediaSource.Factory#setDrmSessionManagerProvider no longer handles null by instantiating a new DefaultDrmSessionManagerProvider. Explicitly construct and pass an instance in order to retain the old behavior.");
            return this;
        }

        @Override // androidx.media3.exoplayer.source.MediaSource.Factory
        public /* bridge */ /* synthetic */ MediaSource.Factory setDrmSessionManagerProvider(DrmSessionManagerProvider r1) {
            return setDrmSessionManagerProvider(r1);
        }

        @Override // androidx.media3.exoplayer.source.MediaSource.Factory
        public Factory setLoadErrorHandlingPolicy(LoadErrorHandlingPolicy r2) {
            this.loadErrorHandlingPolicy = (LoadErrorHandlingPolicy) Assertions.checkNotNull(r2, "MediaSource.Factory#setLoadErrorHandlingPolicy no longer handles null by instantiating a new DefaultLoadErrorHandlingPolicy. Explicitly construct and pass an instance in order to retain the old behavior.");
            return this;
        }

        @Override // androidx.media3.exoplayer.source.MediaSource.Factory
        public /* bridge */ /* synthetic */ MediaSource.Factory setLoadErrorHandlingPolicy(LoadErrorHandlingPolicy r1) {
            return setLoadErrorHandlingPolicy(r1);
        }

        @Override // androidx.media3.exoplayer.source.MediaSource.Factory
        public Factory setSubtitleParserFactory(SubtitleParser.Factory r1) {
            this.subtitleParserFactoryOverride = r1;
            return this;
        }

        @Override // androidx.media3.exoplayer.source.MediaSource.Factory
        public /* bridge */ /* synthetic */ MediaSource.Factory setSubtitleParserFactory(SubtitleParser.Factory r1) {
            return setSubtitleParserFactory(r1);
        }
    }

    static {
        MediaLibraryInfo.registerModule("media3.exoplayer.hls");
    }

    private HlsMediaSource(MediaItem r1, HlsDataSourceFactory r2, HlsExtractorFactory r3, CompositeSequenceableLoaderFactory r4, CmcdConfiguration r5, DrmSessionManager r6, LoadErrorHandlingPolicy r7, HlsPlaylistTracker r8, long r9, boolean r11, int r12, boolean r13, long r14) {
        this.mediaItem = r1;
        this.liveConfiguration = r1.liveConfiguration;
        this.dataSourceFactory = r2;
        this.extractorFactory = r3;
        this.compositeSequenceableLoaderFactory = r4;
        this.drmSessionManager = r6;
        this.loadErrorHandlingPolicy = r7;
        this.playlistTracker = r8;
        this.elapsedRealTimeOffsetMs = r9;
        this.allowChunklessPreparation = r11;
        this.metadataType = r12;
        this.useSessionKeys = r13;
        this.timestampAdjusterInitializationTimeoutMs = r14;
    }

    public /* synthetic */ HlsMediaSource(MediaItem r1, HlsDataSourceFactory r2, HlsExtractorFactory r3, CompositeSequenceableLoaderFactory r4, CmcdConfiguration r5, DrmSessionManager r6, LoadErrorHandlingPolicy r7, HlsPlaylistTracker r8, long r9, boolean r11, int r12, boolean r13, long r14, AnonymousClass1 r16) {
        this(r1, r2, r3, r4, r5, r6, r7, r8, r9, r11, r12, r13, r14);
    }

    private SinglePeriodTimeline createTimelineForLive(HlsMediaPlaylist r28, long r29, long r31, HlsManifest r33) {
        long r17 = r28.startTimeUs - this.playlistTracker.getInitialStartTimeUs();
        if (r28.hasEndTag == false) goto L5;
        long r13 = r17 + r28.durationUs;
    L6:
        long r7 = getLiveEdgeOffsetUs(r28);
        long r5 = this.liveConfiguration.targetOffsetMs;
        if (r5 == (-9223372036854775807L)) goto L10;
        long r2 = Util.msToUs(r5);
    L11:
        updateLiveConfiguration(r28, Util.constrainValue(r2, r7, r28.durationUs + r7));
        long r19 = getLiveWindowDefaultStartPositionUs(r28, r7);
        if (r28.playlistType == 2) goto L14;
    L16:
        boolean r23 = false;
    L18:
        return new SinglePeriodTimeline(r29, r31, -9223372036854775807L, r13, r28.durationUs, r17, r19, true, !r28.hasEndTag, r23, r33, getMediaItem(), this.liveConfiguration);
    L14:
        if (r28.hasPositiveStartOffset == false) goto L16;
        r23 = true;
        goto L18
    L10:
        r2 = getTargetLiveOffsetUs(r28, r7);
        goto L11
    L5:
        r13 = -9223372036854775807L;
        goto L6
    }

    private SinglePeriodTimeline createTimelineForOnDemand(HlsMediaPlaylist r25, long r26, long r28, HlsManifest r30) {
        if (r25.startOffsetUs != (-9223372036854775807L)) goto L5;
    L15:
        long r1 = 0;
    L13:
        long r16 = r1;
        long r10 = r25.durationUs;
        return new SinglePeriodTimeline(r26, r28, -9223372036854775807L, r10, r10, 0, r16, true, false, true, r30, getMediaItem(), null);
    L5:
        if (r25.segments.isEmpty() == true) goto L15;
        if (r25.preciseStart == true) goto L14;
        long r12 = r25.startOffsetUs;
        if (r12 == r25.durationUs) goto L14;
        r1 = findClosestPrecedingSegment(r25.segments, r12).relativeStartTimeUs;
    L14:
        r1 = r25.startOffsetUs;
        goto L13
    }

    private static HlsMediaPlaylist.Part findClosestPrecedingIndependentPart(List<HlsMediaPlaylist.Part> r6, long r7) {
        HlsMediaPlaylist.Part r0 = null;
        int r1 = 0;
    L4:
        if (r1 >= r6.size()) goto L14;
        HlsMediaPlaylist.Part r2 = r6.get(r1);
        long r3 = r2.relativeStartTimeUs;
        if (r3 > r7) goto L11;
        if (r2.isIndependent == false) goto L11;
        r0 = r2;
    L13:
        r1 = r1 + 1;
    L11:
        if (r3 <= r7) goto L13;
    L14:
        return r0;
    }

    private static HlsMediaPlaylist.Segment findClosestPrecedingSegment(List<HlsMediaPlaylist.Segment> r0, long r1) {
        return r0.get(Util.binarySearchFloor(r0, Long.valueOf(r1), true, true));
    }

    private long getLiveEdgeOffsetUs(HlsMediaPlaylist r3) {
        if (r3.hasProgramDateTime == true) goto L5;
        return 0;
    L5:
        return Util.msToUs(Util.getNowUnixTimeMs(this.elapsedRealTimeOffsetMs)) - r3.getEndTimeUs();
    }

    private long getLiveWindowDefaultStartPositionUs(HlsMediaPlaylist r5, long r6) {
        long r0 = r5.startOffsetUs;
        if (r0 != (-9223372036854775807L)) goto L7;
        r0 = (r5.durationUs + r6) - Util.msToUs(this.liveConfiguration.targetOffsetMs);
    L7:
        if (r5.preciseStart == false) goto L9;
        return r0;
    L9:
        HlsMediaPlaylist.Part r4 = findClosestPrecedingIndependentPart(r5.trailingParts, r0);
        if (r4 == null) goto L14;
        return r4.relativeStartTimeUs;
    L14:
        if (r5.segments.isEmpty() == false) goto L17;
        return 0;
    L17:
        HlsMediaPlaylist.Segment r42 = findClosestPrecedingSegment(r5.segments, r0);
        HlsMediaPlaylist.Part r52 = findClosestPrecedingIndependentPart(r42.parts, r0);
        if (r52 == null) goto L22;
        return r52.relativeStartTimeUs;
    L22:
        return r42.relativeStartTimeUs;
    }

    private static long getTargetLiveOffsetUs(HlsMediaPlaylist r7, long r8) {
        HlsMediaPlaylist.ServerControl r0 = r7.serverControl;
        long r1 = r7.startOffsetUs;
        if (r1 == (-9223372036854775807L)) goto L5;
        long r3 = r7.durationUs - r1;
    L15:
        return r3 + r8;
    L5:
        long r12 = r0.partHoldBackUs;
        if (r12 != (-9223372036854775807L)) goto L8;
    L10:
        long r02 = r0.holdBackUs;
        if (r02 == (-9223372036854775807L)) goto L13;
        r3 = r02;
        goto L15
    L13:
        r3 = r7.targetDurationUs * 3;
        goto L15
    L8:
        if (r7.partTargetDurationUs == (-9223372036854775807L)) goto L10;
        r3 = r12;
        goto L15
    }

    private void updateLiveConfiguration(HlsMediaPlaylist r5, long r6) {
        MediaItem.LiveConfiguration r0 = getMediaItem().liveConfiguration;
        if (r0.minPlaybackSpeed == (-3.4028235E38f)) goto L5;
    L11:
        boolean r52 = false;
    L12:
        MediaItem.LiveConfiguration.Builder r62 = new MediaItem.LiveConfiguration.Builder().setTargetOffsetMs(Util.usToMs(r6));
        float r7 = 1.0f;
        if (r52 == false) goto L15;
        float r02 = 1.0f;
    L16:
        MediaItem.LiveConfiguration.Builder r63 = r62.setMinPlaybackSpeed(r02);
        if (r52 == true) goto L20;
        r7 = this.liveConfiguration.maxPlaybackSpeed;
    L20:
        this.liveConfiguration = r63.setMaxPlaybackSpeed(r7).build();
        return;
    L15:
        r02 = this.liveConfiguration.minPlaybackSpeed;
        goto L16
    L5:
        if (r0.maxPlaybackSpeed != (-3.4028235E38f)) goto L11;
        HlsMediaPlaylist.ServerControl r53 = r5.serverControl;
        if (r53.holdBackUs != (-9223372036854775807L)) goto L11;
        if (r53.partHoldBackUs != (-9223372036854775807L)) goto L11;
        r52 = true;
        goto L12
    }

    @Override // androidx.media3.exoplayer.source.MediaSource
    public boolean canUpdateMediaItem(MediaItem r5) {
        MediaItem r4 = getMediaItem();
        MediaItem.LocalConfiguration r0 = (MediaItem.LocalConfiguration) Assertions.checkNotNull(r4.localConfiguration);
        MediaItem.LocalConfiguration r1 = r5.localConfiguration;
        if (r1 != null) goto L5;
        return false;
    L5:
        if (r1.uri.equals(r0.uri) == true) goto L7;
        return false;
    L7:
        if (r1.streamKeys.equals(r0.streamKeys) == true) goto L9;
        return false;
    L9:
        if (Objects.equals(r1.drmConfiguration, r0.drmConfiguration) == true) goto L11;
        return false;
    L11:
        if (r4.liveConfiguration.equals(r5.liveConfiguration) == false) goto L19;
        return true;
    L19:
        return false;
    }

    @Override // androidx.media3.exoplayer.source.MediaSource
    public MediaPeriod createPeriod(MediaSource.MediaPeriodId r19, Allocator r20, long r21) {
        MediaSourceEventListener.EventDispatcher r9 = createEventDispatcher(r19);
        DrmSessionEventListener.EventDispatcher r7 = createDrmEventDispatcher(r19);
        return new HlsMediaPeriod(this.extractorFactory, this.playlistTracker, this.dataSourceFactory, this.mediaTransferListener, null, this.drmSessionManager, r7, this.loadErrorHandlingPolicy, r9, r20, this.compositeSequenceableLoaderFactory, this.allowChunklessPreparation, this.metadataType, this.useSessionKeys, getPlayerId(), this.timestampAdjusterInitializationTimeoutMs);
    }

    @Override // androidx.media3.exoplayer.source.MediaSource
    public synchronized MediaItem getMediaItem() {
        monitor-enter(this);
        MediaItem r0 = this.mediaItem;     // Catch: Throwable -> L6
        monitor-exit(this);
        return r0;
    L6:
        th = move-exception;
        throw th;
    }

    @Override // androidx.media3.exoplayer.source.MediaSource
    public void maybeThrowSourceInfoRefreshError() {
        this.playlistTracker.maybeThrowPrimaryPlaylistRefreshError();
    }

    @Override // androidx.media3.exoplayer.hls.playlist.HlsPlaylistTracker.PrimaryPlaylistListener
    public void onPrimaryPlaylistRefreshed(HlsMediaPlaylist r13) {
        if (r13.hasProgramDateTime == false) goto L5;
        long r9 = Util.usToMs(r13.startTimeUs);
    L6:
        int r0 = r13.playlistType;
        if (r0 != 2) goto L9;
    L12:
        long r7 = r9;
    L13:
        HlsManifest r11 = new HlsManifest((HlsMultivariantPlaylist) Assertions.checkNotNull(this.playlistTracker.getMultivariantPlaylist()), r13);
        if (this.playlistTracker.isLive() == false) goto L16;
        HlsMediaSource r5 = this;
        SinglePeriodTimeline r12 = r5.createTimelineForLive(r13, r7, r9, r11);
    L17:
        r5.refreshSourceInfo(r12);
        return;
    L16:
        r5 = this;
        r12 = r5.createTimelineForOnDemand(r13, r7, r9, r11);
        goto L17
    L9:
        if (r0 == 1) goto L12;
        r7 = -9223372036854775807L;
        goto L13
    L5:
        r9 = -9223372036854775807L;
        goto L6
    }

    @Override // androidx.media3.exoplayer.source.BaseMediaSource
    public void prepareSourceInternal(TransferListener r3) {
        this.mediaTransferListener = r3;
        this.drmSessionManager.setPlayer((Looper) Assertions.checkNotNull(Looper.myLooper()), getPlayerId());
        this.drmSessionManager.prepare();
        MediaSourceEventListener.EventDispatcher r32 = createEventDispatcher(null);
        this.playlistTracker.start(((MediaItem.LocalConfiguration) Assertions.checkNotNull(getMediaItem().localConfiguration)).uri, r32, this);
    }

    @Override // androidx.media3.exoplayer.source.MediaSource
    public void releasePeriod(MediaPeriod r1) {
        ((HlsMediaPeriod) r1).release();
    }

    @Override // androidx.media3.exoplayer.source.BaseMediaSource
    public void releaseSourceInternal() {
        this.playlistTracker.stop();
        this.drmSessionManager.release();
    }

    @Override // androidx.media3.exoplayer.source.MediaSource
    public synchronized void updateMediaItem(MediaItem r1) {
        monitor-enter(this);
        this.mediaItem = r1;     // Catch: Throwable -> L6
        monitor-exit(this);
        return;
    L6:
        th = move-exception;
        throw th;
    }
}
