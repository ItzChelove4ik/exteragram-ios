package androidx.media3.exoplayer.dash;

import android.os.SystemClock;
import android.util.Pair;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.UriUtil;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.DataSource;
import androidx.media3.datasource.DataSpec;
import androidx.media3.datasource.HttpDataSource$InvalidResponseCodeException;
import androidx.media3.datasource.TransferListener;
import androidx.media3.exoplayer.LoadingInfo;
import androidx.media3.exoplayer.SeekParameters;
import androidx.media3.exoplayer.analytics.PlayerId;
import androidx.media3.exoplayer.dash.DashChunkSource;
import androidx.media3.exoplayer.dash.PlayerEmsgHandler;
import androidx.media3.exoplayer.dash.manifest.AdaptationSet;
import androidx.media3.exoplayer.dash.manifest.BaseUrl;
import androidx.media3.exoplayer.dash.manifest.DashManifest;
import androidx.media3.exoplayer.dash.manifest.RangedUri;
import androidx.media3.exoplayer.dash.manifest.Representation;
import androidx.media3.exoplayer.source.BehindLiveWindowException;
import androidx.media3.exoplayer.source.chunk.BaseMediaChunkIterator;
import androidx.media3.exoplayer.source.chunk.BundledChunkExtractor;
import androidx.media3.exoplayer.source.chunk.Chunk;
import androidx.media3.exoplayer.source.chunk.ChunkExtractor;
import androidx.media3.exoplayer.source.chunk.ChunkHolder;
import androidx.media3.exoplayer.source.chunk.ContainerMediaChunk;
import androidx.media3.exoplayer.source.chunk.InitializationChunk;
import androidx.media3.exoplayer.source.chunk.MediaChunk;
import androidx.media3.exoplayer.source.chunk.MediaChunkIterator;
import androidx.media3.exoplayer.source.chunk.SingleSampleMediaChunk;
import androidx.media3.exoplayer.trackselection.ExoTrackSelection;
import androidx.media3.exoplayer.upstream.CmcdConfiguration;
import androidx.media3.exoplayer.upstream.CmcdData;
import androidx.media3.exoplayer.upstream.LoadErrorHandlingPolicy;
import androidx.media3.exoplayer.upstream.LoaderErrorThrower;
import androidx.media3.extractor.ChunkIndex;
import androidx.media3.extractor.text.SubtitleParser;
import com.google.common.collect.ImmutableMap;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

/* loaded from: classes4.dex */
public class DefaultDashChunkSource implements DashChunkSource {
    private final int[] adaptationSetIndices;
    private final BaseUrlExclusionList baseUrlExclusionList;
    private final DataSource dataSource;
    private final long elapsedRealtimeOffsetMs;
    private IOException fatalError;
    private long lastChunkRequestRealtimeMs;
    private DashManifest manifest;
    private final LoaderErrorThrower manifestLoaderErrorThrower;
    private final int maxSegmentsPerLoad;
    private boolean missingLastSegment;
    private int periodIndex;
    private final PlayerEmsgHandler.PlayerTrackEmsgHandler playerTrackEmsgHandler;
    protected final RepresentationHolder[] representationHolders;
    private ExoTrackSelection trackSelection;
    private final int trackType;

    public static final class Factory implements DashChunkSource.Factory {
        private final ChunkExtractor.Factory chunkExtractorFactory;
        private final DataSource.Factory dataSourceFactory;
        private final int maxSegmentsPerLoad;

        public Factory(DataSource.Factory r2) {
            this(r2, 1);
        }

        public Factory(DataSource.Factory r2, int r3) {
            this(new BundledChunkExtractor.Factory(), r2, r3);
        }

        public Factory(ChunkExtractor.Factory r1, DataSource.Factory r2, int r3) {
            this.chunkExtractorFactory = r1;
            this.dataSourceFactory = r2;
            this.maxSegmentsPerLoad = r3;
        }

        @Override // androidx.media3.exoplayer.dash.DashChunkSource.Factory
        public DashChunkSource createDashChunkSource(LoaderErrorThrower r22, DashManifest r23, BaseUrlExclusionList r24, int r25, int[] r26, ExoTrackSelection r27, int r28, long r29, boolean r31, List<Format> r32, PlayerEmsgHandler.PlayerTrackEmsgHandler r33, TransferListener r34, PlayerId r35, CmcdConfiguration r36) {
            DataSource r12 = this.dataSourceFactory.createDataSource();
            if (r34 == null) goto L6;
            r12.addTransferListener(r34);
        L6:
            return new DefaultDashChunkSource(this.chunkExtractorFactory, r22, r23, r24, r25, r26, r27, r28, r12, r29, this.maxSegmentsPerLoad, r31, r32, r33, r35, r36);
        }

        @Override // androidx.media3.exoplayer.dash.DashChunkSource.Factory
        public /* bridge */ /* synthetic */ DashChunkSource.Factory experimentalParseSubtitlesDuringExtraction(boolean r1) {
            return experimentalParseSubtitlesDuringExtraction(r1);
        }

        @Override // androidx.media3.exoplayer.dash.DashChunkSource.Factory
        public Factory experimentalParseSubtitlesDuringExtraction(boolean r2) {
            this.chunkExtractorFactory.experimentalParseSubtitlesDuringExtraction(r2);
            return this;
        }

        @Override // androidx.media3.exoplayer.dash.DashChunkSource.Factory
        public /* bridge */ /* synthetic */ DashChunkSource.Factory experimentalSetCodecsToParseWithinGopSampleDependencies(int r1) {
            return experimentalSetCodecsToParseWithinGopSampleDependencies(r1);
        }

        @Override // androidx.media3.exoplayer.dash.DashChunkSource.Factory
        public Factory experimentalSetCodecsToParseWithinGopSampleDependencies(int r2) {
            this.chunkExtractorFactory.experimentalSetCodecsToParseWithinGopSampleDependencies(r2);
            return this;
        }

        @Override // androidx.media3.exoplayer.dash.DashChunkSource.Factory
        public Format getOutputTextFormat(Format r1) {
            return this.chunkExtractorFactory.getOutputTextFormat(r1);
        }

        @Override // androidx.media3.exoplayer.dash.DashChunkSource.Factory
        public /* bridge */ /* synthetic */ DashChunkSource.Factory setSubtitleParserFactory(SubtitleParser.Factory r1) {
            return setSubtitleParserFactory(r1);
        }

        @Override // androidx.media3.exoplayer.dash.DashChunkSource.Factory
        public Factory setSubtitleParserFactory(SubtitleParser.Factory r2) {
            this.chunkExtractorFactory.setSubtitleParserFactory(r2);
            return this;
        }
    }

    public static final class RepresentationHolder {
        final ChunkExtractor chunkExtractor;
        private final long periodDurationUs;
        public final Representation representation;
        public final DashSegmentIndex segmentIndex;
        private final long segmentNumShift;
        public final BaseUrl selectedBaseUrl;

        public RepresentationHolder(long r1, Representation r3, BaseUrl r4, ChunkExtractor r5, long r6, DashSegmentIndex r8) {
            this.periodDurationUs = r1;
            this.representation = r3;
            this.selectedBaseUrl = r4;
            this.segmentNumShift = r6;
            this.chunkExtractor = r5;
            this.segmentIndex = r8;
        }

        public static /* synthetic */ long access$000(RepresentationHolder r2) {
            return r2.periodDurationUs;
        }

        public RepresentationHolder copyWithNewRepresentation(long r20, Representation r22) {
            DashSegmentIndex r8 = this.representation.getIndex();
            DashSegmentIndex r82 = r22.getIndex();
            if (r8 != null) goto L7;
            return new RepresentationHolder(r20, r22, this.selectedBaseUrl, this.chunkExtractor, this.segmentNumShift, r8);
        L7:
            if (r8.isExplicit() == false) goto L9;
            long r4 = r8.getSegmentCount(r20);
            if (r4 == 0) goto L13;
            Assertions.checkStateNotNull(r82);
            long r6 = r8.getFirstSegmentNum();
            long r9 = r8.getTimeUs(r6);
            long r42 = r4 + r6;
            long r11 = r42 - 1;
            long r13 = r8.getTimeUs(r11) + r8.getDurationUs(r11, r20);
            long r112 = r82.getFirstSegmentNum();
            long r43 = r82.getTimeUs(r112);
            long r62 = this.segmentNumShift;
            if (r13 != r43) goto L18;
            long r44 = r42 - r112;
        L17:
            long r63 = r62 + r44;
        L24:
            return new RepresentationHolder(r20, r22, this.selectedBaseUrl, this.chunkExtractor, r63, r82);
        L18:
            if (r13 < r43) goto L26;
            if (r43 >= r9) goto L22;
            r63 = r62 - (r82.getSegmentNum(r9, r20) - r6);
            goto L24
        L22:
            r44 = r8.getSegmentNum(r43, r20) - r112;
            goto L17
        L26:
            throw new BehindLiveWindowException();
        L13:
            return new RepresentationHolder(r20, r22, this.selectedBaseUrl, this.chunkExtractor, this.segmentNumShift, r82);
        L9:
            return new RepresentationHolder(r20, r22, this.selectedBaseUrl, this.chunkExtractor, this.segmentNumShift, r82);
        }

        public RepresentationHolder copyWithNewSegmentIndex(DashSegmentIndex r10) {
            return new RepresentationHolder(this.periodDurationUs, this.representation, this.selectedBaseUrl, this.chunkExtractor, this.segmentNumShift, r10);
        }

        public RepresentationHolder copyWithNewSelectedBaseUrl(BaseUrl r10) {
            return new RepresentationHolder(this.periodDurationUs, this.representation, r10, this.chunkExtractor, this.segmentNumShift, this.segmentIndex);
        }

        public long getFirstAvailableSegmentNum(long r4) {
            return ((DashSegmentIndex) Assertions.checkStateNotNull(this.segmentIndex)).getFirstAvailableSegmentNum(this.periodDurationUs, r4) + this.segmentNumShift;
        }

        public long getFirstSegmentNum() {
            return ((DashSegmentIndex) Assertions.checkStateNotNull(this.segmentIndex)).getFirstSegmentNum() + this.segmentNumShift;
        }

        public long getLastAvailableSegmentNum(long r6) {
            return (getFirstAvailableSegmentNum(r6) + ((DashSegmentIndex) Assertions.checkStateNotNull(this.segmentIndex)).getAvailableSegmentCount(this.periodDurationUs, r6)) - 1;
        }

        public long getSegmentCount() {
            return ((DashSegmentIndex) Assertions.checkStateNotNull(this.segmentIndex)).getSegmentCount(this.periodDurationUs);
        }

        public long getSegmentEndTimeUs(long r6) {
            return getSegmentStartTimeUs(r6) + ((DashSegmentIndex) Assertions.checkStateNotNull(this.segmentIndex)).getDurationUs(r6 - this.segmentNumShift, this.periodDurationUs);
        }

        public long getSegmentNum(long r4) {
            return ((DashSegmentIndex) Assertions.checkStateNotNull(this.segmentIndex)).getSegmentNum(r4, this.periodDurationUs) + this.segmentNumShift;
        }

        public long getSegmentStartTimeUs(long r4) {
            return ((DashSegmentIndex) Assertions.checkStateNotNull(this.segmentIndex)).getTimeUs(r4 - this.segmentNumShift);
        }

        public RangedUri getSegmentUrl(long r4) {
            return ((DashSegmentIndex) Assertions.checkStateNotNull(this.segmentIndex)).getSegmentUrl(r4 - this.segmentNumShift);
        }

        public boolean isSegmentAvailableAtFullNetworkSpeed(long r5, long r7) {
            if (((DashSegmentIndex) Assertions.checkStateNotNull(this.segmentIndex)).isExplicit() == false) goto L6;
            return true;
        L6:
            if (r7 != (-9223372036854775807L)) goto L8;
        L12:
            return true;
        L8:
            if (getSegmentEndTimeUs(r5) <= r7) goto L12;
            return false;
        }
    }

    public static final class RepresentationSegmentIterator extends BaseMediaChunkIterator {
        private final long nowPeriodTimeUs;
        private final RepresentationHolder representationHolder;

        public RepresentationSegmentIterator(RepresentationHolder r1, long r2, long r4, long r6) {
            super(r2, r4);
            this.representationHolder = r1;
            this.nowPeriodTimeUs = r6;
        }

        @Override // androidx.media3.exoplayer.source.chunk.MediaChunkIterator
        public long getChunkEndTimeUs() {
            checkInBounds();
            return this.representationHolder.getSegmentEndTimeUs(getCurrentIndex());
        }

        @Override // androidx.media3.exoplayer.source.chunk.MediaChunkIterator
        public long getChunkStartTimeUs() {
            checkInBounds();
            return this.representationHolder.getSegmentStartTimeUs(getCurrentIndex());
        }
    }

    public DefaultDashChunkSource(ChunkExtractor.Factory r20, LoaderErrorThrower r21, DashManifest r22, BaseUrlExclusionList r23, int r24, int[] r25, ExoTrackSelection r26, int r27, DataSource r28, long r29, int r31, boolean r32, List<Format> r33, PlayerEmsgHandler.PlayerTrackEmsgHandler r34, PlayerId r35, CmcdConfiguration r36) {
        this.manifestLoaderErrorThrower = r21;
        this.manifest = r22;
        this.baseUrlExclusionList = r23;
        this.adaptationSetIndices = r25;
        this.trackSelection = r26;
        int r6 = r27;
        this.trackType = r6;
        this.dataSource = r28;
        this.periodIndex = r24;
        this.elapsedRealtimeOffsetMs = r29;
        this.maxSegmentsPerLoad = r31;
        PlayerEmsgHandler.PlayerTrackEmsgHandler r10 = r34;
        this.playerTrackEmsgHandler = r10;
        this.lastChunkRequestRealtimeMs = -9223372036854775807L;
        long r12 = r22.getPeriodDurationUs(r24);
        ArrayList<Representation> r1 = getRepresentations();
        this.representationHolders = new RepresentationHolder[r26.length()];
        int r14 = 0;
    L4:
        if (r14 >= this.representationHolders.length) goto L10;
        Representation r15 = r1.get(r26.getIndexInTrackGroup(r14));
        BaseUrl r5 = r23.selectBaseUrl(r15.baseUrls);
        RepresentationHolder[] r7 = this.representationHolders;
        if (r5 == null) goto L8;
    L7:
        BaseUrl r17 = r5;
        ChunkExtractor r72 = r20.createProgressiveMediaExtractor(r6, r15.format, r32, r33, r10, r35);
        long r102 = r12;
        int r52 = r14;
        r7[r52] = new RepresentationHolder(r102, r15, r17, r72, 0, r15.getIndex());
        r14 = r52 + 1;
        r6 = r27;
        r12 = r102;
        r10 = r34;
        goto L4
    L8:
        r5 = r15.baseUrls.get(0);
        goto L7
    }

    private LoadErrorHandlingPolicy.FallbackOptions createFallbackOptions(ExoTrackSelection r7, List<BaseUrl> r8) {
        long r0 = SystemClock.elapsedRealtime();
        int r2 = r7.length();
        int r3 = 0;
        int r4 = 0;
    L3:
        if (r3 >= r2) goto L8;
        if (r7.isTrackExcluded(r3, r0) == false) goto L7;
        r4 = r4 + 1;
    L7:
        r3 = r3 + 1;
        goto L3
    L8:
        int r72 = BaseUrlExclusionList.getPriorityCount(r8);
        return new LoadErrorHandlingPolicy.FallbackOptions(r72, r72 - this.baseUrlExclusionList.getPriorityCountAfterExclusion(r8), r2, r4);
    }

    private long getAvailableLiveDurationUs(long r7, long r9) {
        if (this.manifest.dynamic == true) goto L5;
        return -9223372036854775807L;
    L5:
        if (this.representationHolders[0].getSegmentCount() == 0) goto L11;
        long r2 = this.representationHolders[0].getLastAvailableSegmentNum(r7);
        long r0 = this.representationHolders[0].getSegmentEndTimeUs(r2);
        return Math.max(0, Math.min(getNowPeriodTimeUs(r7), r0) - r9);
    L11:
        return -9223372036854775807L;
    }

    private Pair<String, String> getNextObjectAndRangeRequest(long r5, RangedUri r7, RepresentationHolder r8) {
        long r52 = r5 + 1;
        if (r52 < r8.getSegmentCount()) goto L6;
        return null;
    L6:
        RangedUri r4 = r8.getSegmentUrl(r52);
        String r53 = UriUtil.getRelativePath(r7.resolveUri(r8.selectedBaseUrl.url), r4.resolveUri(r8.selectedBaseUrl.url));
        String r6 = r4.start + "-";
        if (r4.length == (-1)) goto L10;
        r6 = r6 + (r4.start + r4.length);
    L10:
        return new Pair(r53, r6);
    }

    private long getNowPeriodTimeUs(long r7) {
        DashManifest r0 = this.manifest;
        long r1 = r0.availabilityStartTimeMs;
        if (r1 != (-9223372036854775807L)) goto L6;
        return -9223372036854775807L;
    L6:
        return r7 - Util.msToUs(r1 + r0.getPeriod(this.periodIndex).startMs);
    }

    @RequiresNonNull({"manifest", "adaptationSetIndices"})
    private ArrayList<Representation> getRepresentations() {
        List<AdaptationSet> r0 = this.manifest.getPeriod(this.periodIndex).adaptationSets;
        ArrayList<Representation> r1 = new ArrayList();
        int[] r5 = this.adaptationSetIndices;
        int r2 = r5.length;
        int r3 = 0;
    L3:
        if (r3 >= r2) goto L5;
        r1.addAll(r0.get(r5[r3]).representations);
        r3 = r3 + 1;
        goto L3
    L5:
        return r1;
    }

    private long getSegmentNum(RepresentationHolder r1, MediaChunk r2, long r3, long r5, long r7) {
        if (r2 == null) goto L6;
        return r2.getNextChunkIndex();
    L6:
        return Util.constrainValue(r1.getSegmentNum(r3), r5, r7);
    }

    private RepresentationHolder updateSelectedBaseUrl(int r4) {
        RepresentationHolder r0 = this.representationHolders[r4];
        BaseUrl r1 = this.baseUrlExclusionList.selectBaseUrl(r0.representation.baseUrls);
        if (r1 != null) goto L5;
        return r0;
    L5:
        if (r1.equals(r0.selectedBaseUrl) == true) goto L9;
        RepresentationHolder r02 = r0.copyWithNewSelectedBaseUrl(r1);
        this.representationHolders[r4] = r02;
        return r02;
    L9:
        return r0;
    }

    @Override // androidx.media3.exoplayer.source.chunk.ChunkSource
    public long getAdjustedSeekPositionUs(long r13, SeekParameters r15) {
        RepresentationHolder[] r12 = this.representationHolders;
        int r0 = r12.length;
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L22;
        RepresentationHolder r2 = r12[r1];
        if (r2.segmentIndex == null) goto L20;
        long r3 = r2.getSegmentCount();
        if (r3 == 0) goto L20;
        long r02 = r2.getSegmentNum(r13);
        long r8 = r2.getSegmentStartTimeUs(r02);
        if (r8 < r13) goto L12;
    L17:
        long r10 = r8;
    L19:
        return r15.resolveSeekPositionUs(r13, r8, r10);
    L12:
        if (r3 != (-1)) goto L14;
    L15:
        r10 = r2.getSegmentStartTimeUs(r02 + 1);
        goto L19
    L14:
        if (r02 >= ((r2.getFirstSegmentNum() + r3) - 1)) goto L17;
    L20:
        r1 = r1 + 1;
        r15 = r15;
        r13 = r13;
        goto L3
    L22:
        return r13;
    }

    @Override // androidx.media3.exoplayer.source.chunk.ChunkSource
    public void getNextChunk(LoadingInfo r30, long r31, List<? extends MediaChunk> r33, ChunkHolder r34) {
        DefaultDashChunkSource r0 = this;
        if (r0.fatalError != null) goto L101;
        long r9 = r30.playbackPositionUs;
        long r11 = r31 - r9;
        long r1 = (Util.msToUs(r0.manifest.availabilityStartTimeMs) + Util.msToUs(r0.manifest.getPeriod(r0.periodIndex).startMs)) + r31;
        PlayerEmsgHandler.PlayerTrackEmsgHandler r3 = r0.playerTrackEmsgHandler;
        if (r3 != null) goto L8;
    L10:
        long r13 = Util.msToUs(Util.getNowUnixTimeMs(r0.elapsedRealtimeOffsetMs));
        long r16 = r0.getNowPeriodTimeUs(r13);
        RangedUri r18 = null;
        boolean r2 = true;
        if (r33.isEmpty() == false) goto L13;
        MediaChunk r12 = null;
    L14:
        int r4 = r0.trackSelection.length();
        MediaChunkIterator[] r5 = new MediaChunkIterator[r4];
        int r6 = 0;
    L15:
        if (r6 >= r4) goto L24;
        RepresentationHolder r7 = r0.representationHolders[r6];
        if (r7.segmentIndex != null) goto L19;
        r5[r6] = MediaChunkIterator.EMPTY;
        DefaultDashChunkSource r8 = r0;
        MediaChunk r20 = r12;
        boolean r22 = r2;
        MediaChunkIterator[] r21 = r5;
        long r23 = r11;
        int r122 = r4;
        int r112 = r6;
    L23:
        r6 = r112 + 1;
        r0 = r8;
        r4 = r122;
        r12 = r20;
        r5 = r21;
        r2 = r22;
        r11 = r23;
        goto L15
    L19:
        MediaChunkIterator[] r82 = r5;
        long r52 = r7.getFirstAvailableSegmentNum(r13);
        r22 = r2;
        r21 = r82;
        MediaChunk r24 = r12;
        long r72 = r7.getLastAvailableSegmentNum(r13);
        r23 = r11;
        r112 = r6;
        r122 = r4;
        long r25 = r0.getSegmentNum(r7, r24, r31, r52, r72);
        r8 = r0;
        r20 = r24;
        if (r25 >= r52) goto L22;
        r21[r112] = MediaChunkIterator.EMPTY;
        goto L23
    L22:
        r21[r112] = new RepresentationSegmentIterator(r8.updateSelectedBaseUrl(r112), r25, r72, r16);
        goto L23
    L24:
        DefaultDashChunkSource r83 = r0;
        MediaChunk r202 = r12;
        boolean r222 = r2;
        MediaChunkIterator[] r212 = r5;
        long r53 = r83.getAvailableLiveDurationUs(r13, r9);
        r83.trackSelection.updateSelectedTrack(r9, r11, r53, r33, r212);
        int r02 = r83.trackSelection.getSelectedIndex();
        r83.lastChunkRequestRealtimeMs = SystemClock.elapsedRealtime();
        RepresentationHolder r14 = r83.updateSelectedBaseUrl(r02);
        ChunkExtractor r03 = r14.chunkExtractor;
        if (r03 == null) goto L40;
        Representation r26 = r14.representation;
        if (r03.getSampleFormats() != null) goto L29;
        RangedUri r62 = r26.getInitializationUri();
    L31:
        if (r14.segmentIndex != null) goto L33;
        r18 = r26.getIndexUri();
    L33:
        RangedUri r73 = r18;
        if (r62 != null) goto L38;
        if (r73 == null) goto L40;
    L38:
        r34.chunk = r83.newInitializationChunk(r14, r83.dataSource, r83.trackSelection.getSelectedFormat(), r83.trackSelection.getSelectionReason(), r83.trackSelection.getSelectionData(), r62, r73, null);
        return;
    L29:
        r62 = null;
    L40:
        long r10 = RepresentationHolder.access$000(r14);
        if (r83.manifest.dynamic == true) goto L43;
    L45:
        boolean r27 = false;
    L47:
        if (r27 == true) goto L49;
    L52:
        boolean r32 = r222;
    L54:
        if (r14.getSegmentCount() != 0) goto L57;
        r34.endOfStream = r32;
        return;
    L57:
        long r54 = r14.getFirstAvailableSegmentNum(r13);
        long r74 = r14.getLastAvailableSegmentNum(r13);
        if (r27 == false) goto L64;
        long r123 = r14.getSegmentEndTimeUs(r74);
        if ((r123 + (r123 - r14.getSegmentStartTimeUs(r74))) < r10) goto L62;
        boolean r28 = r222;
    L63:
        r32 = r32 & r28;
        goto L64
    L62:
        r28 = false;
    L64:
        boolean r124 = r32;
        long r132 = r83.getSegmentNum(r14, r202, r31, r54, r74);
        if (r132 >= r54) goto L69;
        r83.fatalError = new BehindLiveWindowException();
        return;
    L69:
        if (r132 <= r74) goto L71;
    L93:
        r34.endOfStream = r124;
        return;
    L71:
        if (r83.missingLastSegment == false) goto L74;
        if (r132 >= r74) goto L93;
    L74:
        if (r124 == true) goto L76;
    L79:
        int r29 = (int) Math.min(r83.maxSegmentsPerLoad, (r74 - r132) + 1);
        if (r10 == (-9223372036854775807L)) goto L87;
    L82:
        if (r29 <= 1) goto L87;
        if (r14.getSegmentStartTimeUs((r29 + r132) - 1) < r10) goto L87;
        r29 = r29 - 1;
    L87:
        if (r33.isEmpty() == false) goto L90;
        long r102 = r31;
    L91:
        r34.chunk = r83.newMediaChunk(r14, r83.dataSource, r83.trackType, r83.trackSelection.getSelectedFormat(), r83.trackSelection.getSelectionReason(), r83.trackSelection.getSelectionData(), r132, r29, r102, r16, null);
        return;
    L90:
        r102 = -9223372036854775807L;
        goto L91
    L76:
        if (r14.getSegmentStartTimeUs(r132) < r10) goto L79;
        r34.endOfStream = r222;
        return;
    L49:
        if (r10 != (-9223372036854775807L)) goto L52;
        r32 = false;
        goto L54
    L43:
        if (r83.periodIndex != (r2.getPeriodCount() - 1)) goto L45;
        r27 = r222;
        goto L47
    L13:
        r12 = r33.get(r33.size() - 1);
        goto L14
    L8:
        if (r3.maybeRefreshManifestBeforeLoadingNextChunk(r1) == false) goto L10;
        return;
    }

    @Override // androidx.media3.exoplayer.source.chunk.ChunkSource
    public int getPreferredQueueSize(long r3, List<? extends MediaChunk> r5) {
        if (this.fatalError != null) goto L10;
        if (this.trackSelection.length() < 2) goto L10;
        return this.trackSelection.evaluateQueueSize(r3, r5);
    L10:
        return r5.size();
    }

    @Override // androidx.media3.exoplayer.source.chunk.ChunkSource
    public void maybeThrowError() {
        IOException r0 = this.fatalError;
        if (r0 != null) goto L6;
        this.manifestLoaderErrorThrower.maybeThrowError();
        return;
    L6:
        throw r0;
    }

    @RequiresNonNull({"#1.chunkExtractor"})
    public Chunk newInitializationChunk(RepresentationHolder r12, DataSource r13, Format r14, int r15, Object r16, RangedUri r17, RangedUri r18, CmcdData.Factory r19) {
        RangedUri r11 = r17;
        Representation r0 = r12.representation;
        if (r11 == null) goto L8;
        RangedUri r1 = r11.attemptMerge(r18, r12.selectedBaseUrl.url);
        if (r1 == null) goto L10;
        r11 = r1;
    L10:
        return new InitializationChunk(r13, DashUtil.buildDataSpec(r0, r12.selectedBaseUrl.url, r11, 0, ImmutableMap.of()), r14, r15, r16, r12.chunkExtractor);
    L8:
        r11 = (RangedUri) Assertions.checkNotNull(r18);
        goto L10
    }

    public Chunk newMediaChunk(RepresentationHolder r21, DataSource r22, int r23, Format r24, int r25, Object r26, long r27, int r29, long r30, long r32, CmcdData.Factory r34) {
        Representation r3 = r21.representation;
        long r6 = r21.getSegmentStartTimeUs(r27);
        RangedUri r4 = r21.getSegmentUrl(r27);
        if (r21.chunkExtractor != null) goto L9;
        int r5 = 8;
        long r8 = r21.getSegmentEndTimeUs(r27);
        if (r21.isSegmentAvailableAtFullNetworkSpeed(r27, r32) == false) goto L8;
        r5 = 0;
    L8:
        return new SingleSampleMediaChunk(r22, DashUtil.buildDataSpec(r3, r21.selectedBaseUrl.url, r4, r5, ImmutableMap.of()), r24, r25, r26, r6, r8, r27, r23, r24);
    L9:
        int r52 = 8;
        int r82 = 1;
        int r9 = 1;
    L10:
        if (r82 >= r29) goto L15;
        RangedUri r11 = r4.attemptMerge(r21.getSegmentUrl(r27 + r82), r21.selectedBaseUrl.url);
        if (r11 == null) goto L15;
        r9 = r9 + 1;
        r82 = r82 + 1;
        r4 = r11;
    L15:
        long r10 = (r27 + r9) - 1;
        int r16 = r9;
        long r83 = r21.getSegmentEndTimeUs(r10);
        long r13 = RepresentationHolder.access$000(r21);
        if (r13 != (-9223372036854775807L)) goto L18;
    L20:
        r13 = -9223372036854775807L;
    L22:
        if (r21.isSegmentAvailableAtFullNetworkSpeed(r10, r32) == false) goto L24;
        r52 = 0;
    L24:
        DataSpec r2 = DashUtil.buildDataSpec(r3, r21.selectedBaseUrl.url, r4, r52, ImmutableMap.of());
        long r33 = -r3.presentationTimeOffsetUs;
        if (MimeTypes.isImage(r24.sampleMimeType) == false) goto L28;
        r33 = r33 + r6;
    L28:
        return new ContainerMediaChunk(r22, r2, r24, r25, r26, r6, r83, r30, r13, r27, r16, r33, r21.chunkExtractor);
    L18:
        if (r13 > r83) goto L20;
        goto L20
    }

    @Override // androidx.media3.exoplayer.source.chunk.ChunkSource
    public void onChunkLoadCompleted(Chunk r8) {
        if ((r8 instanceof InitializationChunk) == false) goto L9;
        int r0 = this.trackSelection.indexOf(((InitializationChunk) r8).trackFormat);
        RepresentationHolder r1 = this.representationHolders[r0];
        if (r1.segmentIndex != null) goto L9;
        ChunkIndex r2 = ((ChunkExtractor) Assertions.checkStateNotNull(r1.chunkExtractor)).getChunkIndex();
        if (r2 == null) goto L9;
        this.representationHolders[r0] = r1.copyWithNewSegmentIndex(new DashWrappingSegmentIndex(r2, r1.representation.presentationTimeOffsetUs));
    L9:
        PlayerEmsgHandler.PlayerTrackEmsgHandler r7 = this.playerTrackEmsgHandler;
        if (r7 == null) goto L13;
        r7.onChunkLoadCompleted(r8);
        return;
    }

    @Override // androidx.media3.exoplayer.source.chunk.ChunkSource
    public boolean onChunkLoadError(Chunk r7, boolean r8, LoadErrorHandlingPolicy.LoadErrorInfo r9, LoadErrorHandlingPolicy r10) {
        if (r8 == true) goto L5;
        return false;
    L5:
        PlayerEmsgHandler.PlayerTrackEmsgHandler r82 = this.playerTrackEmsgHandler;
        if (r82 == null) goto L11;
        if (r82.onChunkLoadError(r7) == false) goto L11;
        return true;
    L11:
        if (this.manifest.dynamic == false) goto L13;
    L26:
        int r83 = this.trackSelection.indexOf(r7.trackFormat);
        RepresentationHolder r84 = this.representationHolders[r83];
        BaseUrl r2 = this.baseUrlExclusionList.selectBaseUrl(r84.representation.baseUrls);
        if (r2 != null) goto L29;
    L31:
        LoadErrorHandlingPolicy.FallbackOptions r22 = createFallbackOptions(this.trackSelection, r84.representation.baseUrls);
        if (r22.isFallbackAvailable(2) == false) goto L34;
    L36:
        LoadErrorHandlingPolicy.FallbackSelection r92 = r10.getFallbackSelectionFor(r22, r9);
        if (r92 != null) goto L39;
    L48:
        return false;
    L39:
        if (r22.isFallbackAvailable(r92.type) == false) goto L48;
        int r102 = r92.type;
        if (r102 != 2) goto L45;
        ExoTrackSelection r6 = this.trackSelection;
        return r6.excludeTrack(r6.indexOf(r7.trackFormat), r92.exclusionDurationMs);
    L45:
        if (r102 != 1) goto L48;
        this.baseUrlExclusionList.exclude(r84.selectedBaseUrl, r92.exclusionDurationMs);
        return true;
    L34:
        if (r22.isFallbackAvailable(1) == true) goto L36;
        return false;
    L29:
        if (r84.selectedBaseUrl.equals(r2) == true) goto L31;
        return true;
    L13:
        if ((r7 instanceof MediaChunk) == false) goto L26;
        IOException r85 = r9.exception;
        if ((r85 instanceof HttpDataSource$InvalidResponseCodeException) == false) goto L26;
        if (((HttpDataSource$InvalidResponseCodeException) r85).responseCode != 404) goto L26;
        RepresentationHolder r86 = this.representationHolders[this.trackSelection.indexOf(r7.trackFormat)];
        long r23 = r86.getSegmentCount();
        if (r23 == (-1)) goto L26;
        if (r23 == 0) goto L26;
        long r4 = (r86.getFirstSegmentNum() + r23) - 1;
        if (((MediaChunk) r7).getNextChunkIndex() <= r4) goto L26;
        this.missingLastSegment = true;
        return true;
    }

    @Override // androidx.media3.exoplayer.source.chunk.ChunkSource
    public void release() {
        RepresentationHolder[] r3 = this.representationHolders;
        int r0 = r3.length;
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L8;
        ChunkExtractor r2 = r3[r1].chunkExtractor;
        if (r2 == null) goto L7;
        r2.release();
    L7:
        r1 = r1 + 1;
        goto L3
    }

    @Override // androidx.media3.exoplayer.source.chunk.ChunkSource
    public boolean shouldCancelLoad(long r2, Chunk r4, List<? extends MediaChunk> r5) {
        if (this.fatalError == null) goto L7;
        return false;
    L7:
        return this.trackSelection.shouldCancelChunkLoad(r2, r4, r5);
    }

    @Override // androidx.media3.exoplayer.dash.DashChunkSource
    public void updateManifest(DashManifest r6, int r7) {
        this.manifest = r6;     // Catch: BehindLiveWindowException -> L7
        this.periodIndex = r7;     // Catch: BehindLiveWindowException -> L7
        long r62 = r6.getPeriodDurationUs(r7);     // Catch: BehindLiveWindowException -> L7
        ArrayList<Representation> r0 = getRepresentations();     // Catch: BehindLiveWindowException -> L7
        int r1 = 0;
    L3:
        if (r1 >= this.representationHolders.length) goto L9;
        Representation r2 = r0.get(this.trackSelection.getIndexInTrackGroup(r1));     // Catch: BehindLiveWindowException -> L7
        RepresentationHolder[] r3 = this.representationHolders;     // Catch: BehindLiveWindowException -> L7
        r3[r1] = r3[r1].copyWithNewRepresentation(r62, r2);     // Catch: BehindLiveWindowException -> L7
        r1 = r1 + 1;
        goto L3
    L9:
        return;
    L7:
        e = move-exception;
        this.fatalError = e;
    }

    @Override // androidx.media3.exoplayer.dash.DashChunkSource
    public void updateTrackSelection(ExoTrackSelection r1) {
        this.trackSelection = r1;
    }
}
