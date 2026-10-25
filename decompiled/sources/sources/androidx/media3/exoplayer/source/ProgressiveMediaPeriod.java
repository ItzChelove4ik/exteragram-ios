package androidx.media3.exoplayer.source;

import android.net.Uri;
import android.os.Handler;
import androidx.media3.common.DataReader;
import androidx.media3.common.Format;
import androidx.media3.common.Metadata;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.ParserException;
import androidx.media3.common.TrackGroup;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.ConditionVariable;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.DataSource;
import androidx.media3.datasource.DataSourceUtil;
import androidx.media3.datasource.DataSpec;
import androidx.media3.datasource.StatsDataSource;
import androidx.media3.decoder.DecoderInputBuffer;
import androidx.media3.exoplayer.FormatHolder;
import androidx.media3.exoplayer.LoadingInfo;
import androidx.media3.exoplayer.SeekParameters;
import androidx.media3.exoplayer.drm.DrmSessionEventListener;
import androidx.media3.exoplayer.drm.DrmSessionManager;
import androidx.media3.exoplayer.source.IcyDataSource;
import androidx.media3.exoplayer.source.MediaPeriod;
import androidx.media3.exoplayer.source.MediaSourceEventListener;
import androidx.media3.exoplayer.source.SampleQueue;
import androidx.media3.exoplayer.trackselection.ExoTrackSelection;
import androidx.media3.exoplayer.upstream.Allocator;
import androidx.media3.exoplayer.upstream.LoadErrorHandlingPolicy;
import androidx.media3.exoplayer.upstream.Loader;
import androidx.media3.exoplayer.util.ReleasableExecutor;
import androidx.media3.extractor.DiscardingTrackOutput;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.ForwardingSeekMap;
import androidx.media3.extractor.IndexSeekMap;
import androidx.media3.extractor.PositionHolder;
import androidx.media3.extractor.SeekMap;
import androidx.media3.extractor.TrackOutput;
import androidx.media3.extractor.metadata.icy.IcyHeaders;
import com.google.android.gms.cast.framework.media.NotificationOptions;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import kotlin.jvm.internal.LongCompanionObject;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;

/* loaded from: classes4.dex */
final class ProgressiveMediaPeriod implements MediaPeriod, ExtractorOutput, Loader.Callback<ExtractingLoadable>, Loader.ReleaseCallback, SampleQueue.UpstreamFormatChangedListener {
    private static final Format ICY_FORMAT = null;
    private static final Map<String, String> ICY_METADATA_HEADERS = null;
    private final Allocator allocator;
    private MediaPeriod.Callback callback;
    private final long continueLoadingCheckIntervalBytes;
    private final String customCacheKey;
    private final DataSource dataSource;
    private int dataType;
    private final DrmSessionEventListener.EventDispatcher drmEventDispatcher;
    private final DrmSessionManager drmSessionManager;
    private long durationUs;
    private int enabledTrackCount;
    private int extractedSamplesCountAtStartOfLoad;
    private final Handler handler;
    private boolean haveAudioVideoTracks;
    private IcyHeaders icyHeaders;
    private boolean isLengthKnown;
    private boolean isLive;
    private boolean isSingleSample;
    private long lastSeekPositionUs;
    private final Listener listener;
    private final ConditionVariable loadCondition;
    private final LoadErrorHandlingPolicy loadErrorHandlingPolicy;
    private final Loader loader;
    private boolean loadingFinished;
    private final Runnable maybeFinishPrepareRunnable;
    private final MediaSourceEventListener.EventDispatcher mediaSourceEventDispatcher;
    private boolean notifyDiscontinuity;
    private final Runnable onContinueLoadingRequestedRunnable;
    private boolean pendingDeferredRetry;
    private boolean pendingInitialDiscontinuity;
    private long pendingResetPositionUs;
    private boolean prepared;
    private final ProgressiveMediaExtractor progressiveMediaExtractor;
    private boolean released;
    private TrackId[] sampleQueueTrackIds;
    private SampleQueue[] sampleQueues;
    private boolean sampleQueuesBuilt;
    private SeekMap seekMap;
    private boolean seenFirstTrackSelection;
    private final long singleSampleDurationUs;
    private final Format singleTrackFormat;
    private final int singleTrackId;
    private TrackState trackState;
    private final Uri uri;

    /* renamed from: androidx.media3.exoplayer.source.ProgressiveMediaPeriod$1, reason: invalid class name */
    public class AnonymousClass1 extends ForwardingSeekMap {
        final /* synthetic */ ProgressiveMediaPeriod this$0;

        public AnonymousClass1(ProgressiveMediaPeriod r1, SeekMap r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // androidx.media3.extractor.ForwardingSeekMap, androidx.media3.extractor.SeekMap
        public long getDurationUs() {
            return ProgressiveMediaPeriod.access$500(this.this$0);
        }
    }

    public final class ExtractingLoadable implements Loader.Loadable, IcyDataSource.Listener {
        private final StatsDataSource dataSource;
        private DataSpec dataSpec;
        private final ExtractorOutput extractorOutput;
        private TrackOutput icyTrackOutput;
        private volatile boolean loadCanceled;
        private final ConditionVariable loadCondition;
        private final long loadTaskId;
        private boolean pendingExtractorSeek;
        private final PositionHolder positionHolder;
        private final ProgressiveMediaExtractor progressiveMediaExtractor;
        private long seekTimeUs;
        private boolean seenIcyMetadata;
        final /* synthetic */ ProgressiveMediaPeriod this$0;
        private final Uri uri;

        public ExtractingLoadable(ProgressiveMediaPeriod r1, Uri r2, DataSource r3, ProgressiveMediaExtractor r4, ExtractorOutput r5, ConditionVariable r6) {
            this.this$0 = r1;
            this.uri = r2;
            this.dataSource = new StatsDataSource(r3);
            this.progressiveMediaExtractor = r4;
            this.extractorOutput = r5;
            this.loadCondition = r6;
            this.positionHolder = new PositionHolder();
            this.pendingExtractorSeek = true;
            this.loadTaskId = LoadEventInfo.getNewId();
            this.dataSpec = buildDataSpec(0);
        }

        public static /* synthetic */ StatsDataSource access$100(ExtractingLoadable r0) {
            return r0.dataSource;
        }

        public static /* synthetic */ long access$200(ExtractingLoadable r2) {
            return r2.loadTaskId;
        }

        public static /* synthetic */ DataSpec access$300(ExtractingLoadable r0) {
            return r0.dataSpec;
        }

        public static /* synthetic */ long access$400(ExtractingLoadable r2) {
            return r2.seekTimeUs;
        }

        public static /* synthetic */ void access$600(ExtractingLoadable r0, long r1, long r3) {
            r0.setLoadPosition(r1, r3);
        }

        private DataSpec buildDataSpec(long r3) {
            return new DataSpec.Builder().setUri(this.uri).setPosition(r3).setKey(ProgressiveMediaPeriod.access$1500(this.this$0)).setFlags(6).setHttpRequestHeaders(ProgressiveMediaPeriod.access$1400()).build();
        }

        private void setLoadPosition(long r2, long r4) {
            this.positionHolder.position = r2;
            this.seekTimeUs = r4;
            this.pendingExtractorSeek = true;
            this.seenIcyMetadata = false;
        }

        @Override // androidx.media3.exoplayer.upstream.Loader.Loadable
        public void cancelLoad() {
            this.loadCanceled = true;
        }

        @Override // androidx.media3.exoplayer.upstream.Loader.Loadable
        public void load() {
            int r1 = 0;
        L3:
            if (r1 != 0) goto L55;
            if (this.loadCanceled == true) goto L73;
            long r10 = this.positionHolder.position;     // Catch: Throwable -> L21
            DataSpec r5 = buildDataSpec(r10);     // Catch: Throwable -> L21
            this.dataSpec = r5;     // Catch: Throwable -> L21
            long r52 = this.dataSource.open(r5);     // Catch: Throwable -> L21
            if (this.loadCanceled == true) goto L9;
            if (r52 == (-1)) goto L20;
            r52 = r52 + r10;
            ProgressiveMediaPeriod.access$700(this.this$0);     // Catch: Throwable -> L21
        L20:
            long r12 = r52;
            ProgressiveMediaPeriod.access$802(this.this$0, IcyHeaders.parse(this.dataSource.getResponseHeaders()));     // Catch: Throwable -> L21
            DataReader r53 = this.dataSource;     // Catch: Throwable -> L21
            if (ProgressiveMediaPeriod.access$800(this.this$0) != null) goto L26;
        L28:
            this.progressiveMediaExtractor.init(r53, this.uri, this.dataSource.getResponseHeaders(), r10, r12, this.extractorOutput);     // Catch: Throwable -> L21
            if (ProgressiveMediaPeriod.access$800(this.this$0) == null) goto L32;
            this.progressiveMediaExtractor.disableSeekingOnMp3Streams();     // Catch: Throwable -> L21
        L32:
            if (this.pendingExtractorSeek == false) goto L34;
            this.progressiveMediaExtractor.seek(r10, this.seekTimeUs);     // Catch: Throwable -> L21
            this.pendingExtractorSeek = false;     // Catch: Throwable -> L21
        L34:
            if (r1 != 0) goto L43;
            if (this.loadCanceled == true) goto L43;
            this.loadCondition.block();     // Catch: Throwable -> L21 InterruptedException -> L41
            r1 = this.progressiveMediaExtractor.read(this.positionHolder);     // Catch: Throwable -> L21
            long r54 = this.progressiveMediaExtractor.getCurrentInputPosition();     // Catch: Throwable -> L21
            if (r54 <= (ProgressiveMediaPeriod.access$1000(this.this$0) + r10)) goto L34;
            this.loadCondition.close();     // Catch: Throwable -> L21
            ProgressiveMediaPeriod.access$1200(this.this$0).post(ProgressiveMediaPeriod.access$1100(this.this$0));     // Catch: Throwable -> L21
            r10 = r54;
            goto L34
        L42:
            throw new InterruptedIOException();     // Catch: Throwable -> L21
        L43:
            if (r1 != 1) goto L46;
            r1 = 0;
        L48:
            DataSourceUtil.closeQuietly(this.dataSource);
            goto L3
        L46:
            if (this.progressiveMediaExtractor.getCurrentInputPosition() == (-1)) goto L48;
            this.positionHolder.position = this.progressiveMediaExtractor.getCurrentInputPosition();
            goto L48
        L26:
            if (ProgressiveMediaPeriod.access$800(this.this$0).metadataInterval == (-1)) goto L28;
            r53 = new IcyDataSource(this.dataSource, ProgressiveMediaPeriod.access$800(this.this$0).metadataInterval, this);     // Catch: Throwable -> L21
            TrackOutput r6 = this.this$0.icyTrack();     // Catch: Throwable -> L21
            this.icyTrackOutput = r6;     // Catch: Throwable -> L21
            r6.format(ProgressiveMediaPeriod.access$900());     // Catch: Throwable -> L21
            goto L28
        L9:
            if (r1 != 1) goto L12;
        L14:
            DataSourceUtil.closeQuietly(this.dataSource);
            return;
        L12:
            if (this.progressiveMediaExtractor.getCurrentInputPosition() == (-1)) goto L14;
            this.positionHolder.position = this.progressiveMediaExtractor.getCurrentInputPosition();
        L21:
            th = move-exception;
            if (r1 != 1) goto L51;
        L53:
            DataSourceUtil.closeQuietly(this.dataSource);
            throw th;
        L51:
            if (this.progressiveMediaExtractor.getCurrentInputPosition() == (-1)) goto L53;
            this.positionHolder.position = this.progressiveMediaExtractor.getCurrentInputPosition();
            goto L53
        L73:
            return;
        }

        @Override // androidx.media3.exoplayer.source.IcyDataSource.Listener
        public void onIcyMetadata(ParsableByteArray r12) {
            if (this.seenIcyMetadata == true) goto L6;
            long r2 = this.seekTimeUs;
        L5:
            long r5 = r2;
            int r8 = r12.bytesLeft();
            TrackOutput r4 = (TrackOutput) Assertions.checkNotNull(this.icyTrackOutput);
            r4.sampleData(r12, r8);
            r4.sampleMetadata(r5, 1, r8, 0, null);
            this.seenIcyMetadata = true;
            return;
        L6:
            r2 = Math.max(ProgressiveMediaPeriod.access$1300(this.this$0, true), this.seekTimeUs);
            goto L5
        }
    }

    public interface Listener {
        void onSourceInfoRefreshed(long r1, SeekMap r3, boolean r4);
    }

    public final class SampleStreamImpl implements SampleStream {
        final /* synthetic */ ProgressiveMediaPeriod this$0;
        private final int track;

        public SampleStreamImpl(ProgressiveMediaPeriod r1, int r2) {
            this.this$0 = r1;
            this.track = r2;
        }

        public static /* synthetic */ int access$000(SampleStreamImpl r0) {
            return r0.track;
        }

        @Override // androidx.media3.exoplayer.source.SampleStream
        public boolean isReady() {
            return this.this$0.isReady(this.track);
        }

        @Override // androidx.media3.exoplayer.source.SampleStream
        public void maybeThrowError() {
            this.this$0.maybeThrowError(this.track);
        }

        @Override // androidx.media3.exoplayer.source.SampleStream
        public int readData(FormatHolder r2, DecoderInputBuffer r3, int r4) {
            return this.this$0.readData(this.track, r2, r3, r4);
        }

        @Override // androidx.media3.exoplayer.source.SampleStream
        public int skipData(long r2) {
            return this.this$0.skipData(this.track, r2);
        }
    }

    public static final class TrackId {
        public final int id;
        public final boolean isIcyTrack;

        public TrackId(int r1, boolean r2) {
            this.id = r1;
            this.isIcyTrack = r2;
        }

        public boolean equals(Object r5) {
            if (this != r5) goto L6;
            return true;
        L6:
            if (r5 != null) goto L8;
        L15:
            return false;
        L8:
            if (TrackId.class != r5.getClass()) goto L15;
            TrackId r52 = (TrackId) r5;
            if (this.id != r52.id) goto L15;
            if (this.isIcyTrack != r52.isIcyTrack) goto L15;
            return true;
        }

        public int hashCode() {
            return (this.id * 31) + (this.isIcyTrack ? 1 : 0);
        }
    }

    public static final class TrackState {
        public final boolean[] trackEnabledStates;
        public final boolean[] trackIsAudioVideoFlags;
        public final boolean[] trackNotifiedDownstreamFormats;
        public final TrackGroupArray tracks;

        public TrackState(TrackGroupArray r1, boolean[] r2) {
            this.tracks = r1;
            this.trackIsAudioVideoFlags = r2;
            int r12 = r1.length;
            this.trackEnabledStates = new boolean[r12];
            this.trackNotifiedDownstreamFormats = new boolean[r12];
        }
    }

    /* renamed from: $r8$lambda$4-N8ezzihe8QMK8V_lPhNaahKos, reason: not valid java name */
    public static /* synthetic */ void m840$r8$lambda$4N8ezzihe8QMK8V_lPhNaahKos(ProgressiveMediaPeriod r0, SeekMap r1) {
        r0.setSeekMap(r1);
    }

    /* renamed from: $r8$lambda$S--pTorCq-DuXYVXYm-nCp1JewM, reason: not valid java name */
    public static /* synthetic */ void m841$r8$lambda$SpTorCqDuXYVXYmnCp1JewM(ProgressiveMediaPeriod r0) {
        r0.maybeFinishPrepare();
    }

    public static /* synthetic */ void $r8$lambda$dqSMcOVucEUbHoOoHEY__SOT3rM(ProgressiveMediaPeriod r1) {
        if (r1.released == true) goto L6;
        ((MediaPeriod.Callback) Assertions.checkNotNull(r1.callback)).onContinueLoadingRequested(r1);
        return;
    }

    public static /* synthetic */ void $r8$lambda$hTMoUy8yRfFrcB7i0Y7cO3WKbus(ProgressiveMediaPeriod r1) {
        r1.isLengthKnown = true;
    }

    static {
        ICY_METADATA_HEADERS = createIcyMetadataHeaders();
        ICY_FORMAT = new Format.Builder().setId("icy").setSampleMimeType("application/x-icy").build();
    }

    public ProgressiveMediaPeriod(Uri r2, DataSource r3, ProgressiveMediaExtractor r4, DrmSessionManager r5, DrmSessionEventListener.EventDispatcher r6, LoadErrorHandlingPolicy r7, MediaSourceEventListener.EventDispatcher r8, Listener r9, Allocator r10, String r11, int r12, int r13, Format r14, long r15, ReleasableExecutor r17) {
        this.uri = r2;
        this.dataSource = r3;
        this.drmSessionManager = r5;
        this.drmEventDispatcher = r6;
        this.loadErrorHandlingPolicy = r7;
        this.mediaSourceEventDispatcher = r8;
        this.listener = r9;
        this.allocator = r10;
        this.customCacheKey = r11;
        this.continueLoadingCheckIntervalBytes = r12;
        this.singleTrackId = r13;
        this.singleTrackFormat = r14;
        if (r17 == null) goto L5;
        Loader r22 = new Loader(r17);
    L6:
        this.loader = r22;
        this.progressiveMediaExtractor = r4;
        this.singleSampleDurationUs = r15;
        this.loadCondition = new ConditionVariable();
        this.maybeFinishPrepareRunnable = new ProgressiveMediaPeriod$$ExternalSyntheticLambda1(this);
        this.onContinueLoadingRequestedRunnable = new ProgressiveMediaPeriod$$ExternalSyntheticLambda2(this);
        this.handler = Util.createHandlerForCurrentLooper();
        this.sampleQueueTrackIds = new TrackId[0];
        this.sampleQueues = new SampleQueue[0];
        this.pendingResetPositionUs = -9223372036854775807L;
        this.dataType = 1;
        return;
    L5:
        r22 = new Loader("ProgressiveMediaPeriod");
        goto L6
    }

    public static /* synthetic */ long access$1000(ProgressiveMediaPeriod r2) {
        return r2.continueLoadingCheckIntervalBytes;
    }

    public static /* synthetic */ Runnable access$1100(ProgressiveMediaPeriod r0) {
        return r0.onContinueLoadingRequestedRunnable;
    }

    public static /* synthetic */ Handler access$1200(ProgressiveMediaPeriod r0) {
        return r0.handler;
    }

    public static /* synthetic */ long access$1300(ProgressiveMediaPeriod r0, boolean r1) {
        return r0.getLargestQueuedTimestampUs(r1);
    }

    public static /* synthetic */ Map access$1400() {
        return ICY_METADATA_HEADERS;
    }

    public static /* synthetic */ String access$1500(ProgressiveMediaPeriod r0) {
        return r0.customCacheKey;
    }

    public static /* synthetic */ long access$500(ProgressiveMediaPeriod r2) {
        return r2.durationUs;
    }

    public static /* synthetic */ void access$700(ProgressiveMediaPeriod r0) {
        r0.onLengthKnown();
    }

    public static /* synthetic */ IcyHeaders access$800(ProgressiveMediaPeriod r0) {
        return r0.icyHeaders;
    }

    public static /* synthetic */ IcyHeaders access$802(ProgressiveMediaPeriod r0, IcyHeaders r1) {
        r0.icyHeaders = r1;
        return r1;
    }

    public static /* synthetic */ Format access$900() {
        return ICY_FORMAT;
    }

    @EnsuresNonNull({"trackState", "seekMap"})
    private void assertPrepared() {
        Assertions.checkState(this.prepared);
        Assertions.checkNotNull(this.trackState);
        Assertions.checkNotNull(this.seekMap);
    }

    private boolean configureRetry(ExtractingLoadable r7, int r8) {
        if (this.isLengthKnown == true) goto L20;
        SeekMap r0 = this.seekMap;
        if (r0 != null) goto L7;
    L9:
        int r02 = 0;
        if (this.prepared == true) goto L12;
    L15:
        this.notifyDiscontinuity = this.prepared;
        this.lastSeekPositionUs = 0;
        this.extractedSamplesCountAtStartOfLoad = 0;
        SampleQueue[] r6 = this.sampleQueues;
        int r82 = r6.length;
    L16:
        if (r02 >= r82) goto L18;
        r6[r02].reset();
        r02 = r02 + 1;
        goto L16
    L18:
        ExtractingLoadable.access$600(r7, 0, 0);
        return true;
    L12:
        if (suppressRead() == true) goto L15;
        this.pendingDeferredRetry = true;
        return false;
    L7:
        if (r0.getDurationUs() == (-9223372036854775807L)) goto L9;
    L20:
        this.extractedSamplesCountAtStartOfLoad = r8;
        return true;
    }

    private static Map<String, String> createIcyMetadataHeaders() {
        HashMap r0 = new HashMap();
        r0.put("Icy-MetaData", "1");
        return Collections.unmodifiableMap(r0);
    }

    private int getExtractedSamplesCount() {
        SampleQueue[] r4 = this.sampleQueues;
        int r0 = r4.length;
        int r1 = 0;
        int r2 = 0;
    L3:
        if (r1 >= r0) goto L5;
        r2 = r2 + r4[r1].getWriteIndex();
        r1 = r1 + 1;
        goto L3
    L5:
        return r2;
    }

    private long getLargestQueuedTimestampUs(boolean r6) {
        long r0 = Long.MIN_VALUE;
        int r2 = 0;
    L4:
        if (r2 >= this.sampleQueues.length) goto L10;
        if (r6 == false) goto L7;
    L8:
        r0 = Math.max(r0, this.sampleQueues[r2].getLargestQueuedTimestampUs());
    L9:
        r2 = r2 + 1;
        goto L4
    L7:
        if (((TrackState) Assertions.checkNotNull(this.trackState)).trackEnabledStates[r2] == false) goto L9;
    L10:
        return r0;
    }

    private boolean isPendingReset() {
        if (this.pendingResetPositionUs == (-9223372036854775807L)) goto L6;
        return true;
    L6:
        return false;
    }

    private void maybeFinishPrepare() {
        if (this.released == false) goto L5;
        return;
    L5:
        if (this.prepared == false) goto L7;
        return;
    L7:
        if (this.sampleQueuesBuilt == true) goto L9;
        return;
    L9:
        if (this.seekMap == null) goto L71;
        SampleQueue[] r0 = this.sampleQueues;
        int r1 = r0.length;
        int r3 = 0;
    L12:
        if (r3 >= r1) goto L17;
        if (r0[r3].getUpstreamFormat() == null) goto L72;
        r3 = r3 + 1;
        goto L12
    L72:
        return;
    L17:
        this.loadCondition.close();
        int r02 = this.sampleQueues.length;
        TrackGroup[] r12 = new TrackGroup[r02];
        boolean[] r32 = new boolean[r02];
        int r4 = 0;
    L19:
        if (r4 >= r02) goto L52;
        Format r8 = (Format) Assertions.checkNotNull(this.sampleQueues[r4].getUpstreamFormat());
        String r9 = r8.sampleMimeType;
        boolean r10 = MimeTypes.isAudio(r9);
        if (r10 == false) goto L23;
    L26:
        boolean r11 = true;
    L27:
        r32[r4] = r11;
        this.haveAudioVideoTracks = r11 | this.haveAudioVideoTracks;
        boolean r92 = MimeTypes.isImage(r9);
        if (this.singleSampleDurationUs == (-9223372036854775807L)) goto L32;
        if (r02 != 1) goto L32;
        if (r92 == false) goto L32;
        boolean r5 = true;
    L33:
        this.isSingleSample = r5;
        IcyHeaders r52 = this.icyHeaders;
        if (r52 == null) goto L51;
        if (r10 == false) goto L37;
    L38:
        Metadata r6 = r8.metadata;
        if (r6 != null) goto L41;
        Metadata r62 = new Metadata(new Metadata.Entry[]{r52});
    L42:
        r8 = r8.buildUpon().setMetadata(r62).build();
    L43:
        if (r10 == false) goto L51;
        if (r8.averageBitrate != (-1)) goto L51;
        if (r8.peakBitrate != (-1)) goto L51;
        if (r52.bitrate == (-1)) goto L51;
        r8 = r8.buildUpon().setAverageBitrate(r52.bitrate).build();
        goto L51
    L41:
        r62 = r6.copyWithAppendedEntries(new Metadata.Entry[]{r52});
        goto L42
    L37:
        if (this.sampleQueueTrackIds[r4].isIcyTrack == false) goto L43;
    L51:
        Format r53 = r8.copyWithCryptoType(this.drmSessionManager.getCryptoType(r8));
        r12[r4] = new TrackGroup(Integer.toString(r4), new Format[]{r53});
        this.pendingInitialDiscontinuity = r53.hasPrerollSamples | this.pendingInitialDiscontinuity;
        r4 = r4 + 1;
    L32:
        r5 = false;
        goto L33
    L23:
        if (MimeTypes.isVideo(r9) == true) goto L26;
        r11 = false;
        goto L27
    L52:
        this.trackState = new TrackState(new TrackGroupArray(r12), r32);
        if (this.isSingleSample == true) goto L55;
    L57:
        this.listener.onSourceInfoRefreshed(this.durationUs, this.seekMap, this.isLive);
        this.prepared = true;
        ((MediaPeriod.Callback) Assertions.checkNotNull(this.callback)).onPrepared(this);
        return;
    L55:
        if (this.durationUs != (-9223372036854775807L)) goto L57;
        this.durationUs = this.singleSampleDurationUs;
        this.seekMap = new AnonymousClass1(this, this.seekMap);
        goto L57
    }

    private void maybeNotifyDownstreamFormat(int r11) {
        assertPrepared();
        TrackState r0 = this.trackState;
        boolean[] r1 = r0.trackNotifiedDownstreamFormats;
        if (r1[r11] == true) goto L6;
        Format r5 = r0.tracks.get(r11).getFormat(0);
        this.mediaSourceEventDispatcher.downstreamFormatChanged(MimeTypes.getTrackType(r5.sampleMimeType), r5, 0, null, this.lastSeekPositionUs);
        r1[r11] = true;
        return;
    }

    private void maybeStartDeferredRetry(int r4) {
        assertPrepared();
        if (this.pendingDeferredRetry == true) goto L5;
        return;
    L5:
        if (this.haveAudioVideoTracks == true) goto L7;
    L8:
        SampleQueue r42 = this.sampleQueues[r4];
        int r0 = 0;
        if (r42.isReady(false) == true) goto L19;
        this.pendingResetPositionUs = 0;
        this.pendingDeferredRetry = false;
        this.notifyDiscontinuity = true;
        this.lastSeekPositionUs = 0;
        this.extractedSamplesCountAtStartOfLoad = 0;
        SampleQueue[] r43 = this.sampleQueues;
        int r1 = r43.length;
    L12:
        if (r0 >= r1) goto L14;
        r43[r0].reset();
        r0 = r0 + 1;
        goto L12
    L14:
        ((MediaPeriod.Callback) Assertions.checkNotNull(this.callback)).onContinueLoadingRequested(this);
        return;
    L19:
        return;
    L7:
        if (this.trackState.trackIsAudioVideoFlags[r4] == true) goto L8;
    }

    private void onLengthKnown() {
        this.handler.post(new ProgressiveMediaPeriod$$ExternalSyntheticLambda0(this));
    }

    private TrackOutput prepareTrackOutput(TrackId r5) {
        int r0 = this.sampleQueues.length;
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L10;
        if (r5.equals(this.sampleQueueTrackIds[r1]) == true) goto L7;
        r1 = r1 + 1;
        goto L3
    L7:
        return this.sampleQueues[r1];
    L10:
        if (this.sampleQueuesBuilt == false) goto L13;
        Log.w("ProgressiveMediaPeriod", "Extractor added new track (id=" + r5.id + ") after finishing tracks.");
        return new DiscardingTrackOutput();
    L13:
        SampleQueue r12 = SampleQueue.createWithDrm(this.allocator, this.drmSessionManager, this.drmEventDispatcher);
        r12.setUpstreamFormatChangeListener(this);
        int r3 = r0 + 1;
        TrackId[] r2 = (TrackId[]) Arrays.copyOf(this.sampleQueueTrackIds, r3);
        r2[r0] = r5;
        this.sampleQueueTrackIds = (TrackId[]) Util.castNonNullTypeArray(r2);
        SampleQueue[] r52 = (SampleQueue[]) Arrays.copyOf(this.sampleQueues, r3);
        r52[r0] = r12;
        this.sampleQueues = (SampleQueue[]) Util.castNonNullTypeArray(r52);
        return r12;
    }

    private boolean seekInsideBufferUs(boolean[] r6, long r7, boolean r9) {
        int r0 = this.sampleQueues.length;
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L19;
        SampleQueue r3 = this.sampleQueues[r2];
        if (r3.getReadIndex() != 0) goto L9;
        if (r9 == false) goto L9;
    L18:
        r2 = r2 + 1;
    L9:
        if (this.isSingleSample == false) goto L11;
        boolean r32 = r3.seekTo(r3.getFirstIndex());
    L12:
        if (r32 == true) goto L18;
        if (r6[r2] == true) goto L17;
        if (this.haveAudioVideoTracks == true) goto L18;
    L17:
        return false;
    L11:
        r32 = r3.seekTo(r7, this.loadingFinished);
        goto L12
    L19:
        return true;
    }

    private void setSeekMap(SeekMap r7) {
        if (this.icyHeaders != null) goto L5;
        SeekMap r0 = r7;
    L6:
        this.seekMap = r0;
        this.durationUs = r7.getDurationUs();
        int r3 = 1;
        if (this.isLengthKnown == false) goto L9;
    L11:
        boolean r02 = false;
    L12:
        this.isLive = r02;
        if (r02 == false) goto L15;
        r3 = 7;
    L15:
        this.dataType = r3;
        if (this.prepared == false) goto L19;
        this.listener.onSourceInfoRefreshed(this.durationUs, r7, r02);
        return;
    L19:
        maybeFinishPrepare();
        return;
    L9:
        if (r7.getDurationUs() != (-9223372036854775807L)) goto L11;
        r02 = true;
        goto L12
    L5:
        r0 = new SeekMap.Unseekable(-9223372036854775807L);
        goto L6
    }

    private void startLoading() {
        ExtractingLoadable r0 = new ExtractingLoadable(this, this.uri, this.dataSource, this.progressiveMediaExtractor, this, this.loadCondition);
        if (this.prepared == false) goto L14;
        Assertions.checkState(isPendingReset());
        long r2 = this.durationUs;
        if (r2 != (-9223372036854775807L)) goto L7;
    L10:
        ExtractingLoadable.access$600(r0, ((SeekMap) Assertions.checkNotNull(this.seekMap)).getSeekPoints(this.pendingResetPositionUs).first.position, this.pendingResetPositionUs);
        SampleQueue[] r9 = this.sampleQueues;
        int r22 = r9.length;
        int r3 = 0;
    L11:
        if (r3 >= r22) goto L13;
        r9[r3].setStartTimeUs(this.pendingResetPositionUs);
        r3 = r3 + 1;
        goto L11
    L13:
        this.pendingResetPositionUs = -9223372036854775807L;
        goto L14
    L7:
        if (this.pendingResetPositionUs <= r2) goto L10;
        this.loadingFinished = true;
        this.pendingResetPositionUs = -9223372036854775807L;
        return;
    L14:
        this.extractedSamplesCountAtStartOfLoad = getExtractedSamplesCount();
        this.loader.startLoading(r0, this, this.loadErrorHandlingPolicy.getMinimumLoadableRetryCount(this.dataType));
    }

    private boolean suppressRead() {
        if (this.notifyDiscontinuity == false) goto L5;
        return true;
    L5:
        if (isPendingReset() == true) goto L11;
        return false;
    L11:
        return true;
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod, androidx.media3.exoplayer.source.SequenceableLoader
    public boolean continueLoading(LoadingInfo r2) {
        if (this.loadingFinished == false) goto L5;
        return false;
    L5:
        if (this.loader.hasFatalError() == false) goto L7;
        return false;
    L7:
        if (this.pendingDeferredRetry == false) goto L9;
        return false;
    L9:
        if (this.prepared == true) goto L13;
        if (this.singleTrackFormat != null) goto L13;
    L15:
        boolean r22 = this.loadCondition.open();
        if (this.loader.isLoading() == true) goto L19;
        startLoading();
        return true;
    L19:
        return r22;
    L13:
        if (this.enabledTrackCount != 0) goto L15;
        return false;
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public void discardBuffer(long r6, boolean r8) {
        if (this.isSingleSample == true) goto L11;
        assertPrepared();
        if (isPendingReset() == true) goto L13;
        boolean[] r0 = this.trackState.trackEnabledStates;
        int r1 = this.sampleQueues.length;
        int r2 = 0;
    L9:
        if (r2 >= r1) goto L14;
        this.sampleQueues[r2].discardTo(r6, r8, r0[r2]);
        r2 = r2 + 1;
        goto L9
    L14:
        return;
    L13:
        return;
    }

    @Override // androidx.media3.extractor.ExtractorOutput
    public void endTracks() {
        this.sampleQueuesBuilt = true;
        this.handler.post(this.maybeFinishPrepareRunnable);
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public long getAdjustedSeekPositionUs(long r9, SeekParameters r11) {
        assertPrepared();
        if (this.seekMap.isSeekable() == true) goto L6;
        return 0;
    L6:
        SeekMap.SeekPoints r8 = this.seekMap.getSeekPoints(r9);
        return r11.resolveSeekPositionUs(r9, r8.first.timeUs, r8.second.timeUs);
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod, androidx.media3.exoplayer.source.SequenceableLoader
    public long getBufferedPositionUs() {
        assertPrepared();
        if (this.loadingFinished == false) goto L5;
    L32:
        return Long.MIN_VALUE;
    L5:
        if (this.enabledTrackCount == 0) goto L32;
        if (isPendingReset() == false) goto L12;
        return this.pendingResetPositionUs;
    L12:
        if (this.haveAudioVideoTracks == false) goto L23;
        int r0 = this.sampleQueues.length;
        int r6 = 0;
        long r7 = Long.MAX_VALUE;
    L14:
        if (r6 >= r0) goto L25;
        TrackState r9 = this.trackState;
        if (r9.trackIsAudioVideoFlags[r6] == false) goto L22;
        if (r9.trackEnabledStates[r6] == false) goto L22;
        if (this.sampleQueues[r6].isLastSampleQueued() == true) goto L22;
        r7 = Math.min(r7, this.sampleQueues[r6].getLargestQueuedTimestampUs());
    L22:
        r6 = r6 + 1;
    L25:
        if (r7 != LongCompanionObject.MAX_VALUE) goto L28;
        r7 = getLargestQueuedTimestampUs(false);
    L28:
        if (r7 == Long.MIN_VALUE) goto L30;
        return r7;
    L30:
        return this.lastSeekPositionUs;
    L23:
        r7 = Long.MAX_VALUE;
        goto L25
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod, androidx.media3.exoplayer.source.SequenceableLoader
    public long getNextLoadPositionUs() {
        return getBufferedPositionUs();
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public TrackGroupArray getTrackGroups() {
        assertPrepared();
        return this.trackState.tracks;
    }

    public TrackOutput icyTrack() {
        return prepareTrackOutput(new TrackId(0, true));
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod, androidx.media3.exoplayer.source.SequenceableLoader
    public boolean isLoading() {
        if (this.loader.isLoading() == true) goto L5;
        return false;
    L5:
        if (this.loadCondition.isOpen() == false) goto L10;
        return true;
    L10:
        return false;
    }

    public boolean isReady(int r2) {
        if (suppressRead() == false) goto L5;
        return false;
    L5:
        if (this.sampleQueues[r2].isReady(this.loadingFinished) == false) goto L10;
        return true;
    L10:
        return false;
    }

    public void maybeThrowError() {
        this.loader.maybeThrowError(this.loadErrorHandlingPolicy.getMinimumLoadableRetryCount(this.dataType));
    }

    public void maybeThrowError(int r2) {
        this.sampleQueues[r2].maybeThrowError();
        maybeThrowError();
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public void maybeThrowPrepareError() {
        maybeThrowError();
        if (this.loadingFinished == true) goto L5;
        return;
    L5:
        if (this.prepared == false) goto L8;
        return;
    L8:
        throw ParserException.createForMalformedContainer("Loading finished before preparation is complete.", null);
    }

    public void onLoadCanceled(ExtractingLoadable r14, long r15, long r17, boolean r19) {
        StatsDataSource r0 = ExtractingLoadable.access$100(r14);
        LoadEventInfo r1 = new LoadEventInfo(ExtractingLoadable.access$200(r14), ExtractingLoadable.access$300(r14), r0.getLastOpenedUri(), r0.getLastResponseHeaders(), r15, r17, r0.getBytesRead());
        this.loadErrorHandlingPolicy.onLoadTaskConcluded(ExtractingLoadable.access$200(r14));
        this.mediaSourceEventDispatcher.loadCanceled(r1, 1, -1, null, 0, null, ExtractingLoadable.access$400(r14), this.durationUs);
        if (r19 == true) goto L12;
        SampleQueue[] r142 = this.sampleQueues;
        int r02 = r142.length;
        int r12 = 0;
    L5:
        if (r12 >= r02) goto L8;
        r142[r12].reset();
        r12 = r12 + 1;
        goto L5
    L8:
        if (this.enabledTrackCount <= 0) goto L13;
        ((MediaPeriod.Callback) Assertions.checkNotNull(this.callback)).onContinueLoadingRequested(this);
        return;
    L13:
        return;
    }

    @Override // androidx.media3.exoplayer.upstream.Loader.Callback
    public /* bridge */ /* synthetic */ void onLoadCanceled(Loader.Loadable r1, long r2, long r4, boolean r6) {
        onLoadCanceled((ExtractingLoadable) r1, r2, r4, r6);
    }

    public void onLoadCompleted(ExtractingLoadable r15, long r16, long r18) {
        if (this.durationUs == (-9223372036854775807L)) goto L5;
    L11:
        StatsDataSource r0 = ExtractingLoadable.access$100(r15);
        LoadEventInfo r2 = new LoadEventInfo(ExtractingLoadable.access$200(r15), ExtractingLoadable.access$300(r15), r0.getLastOpenedUri(), r0.getLastResponseHeaders(), r16, r18, r0.getBytesRead());
        this.loadErrorHandlingPolicy.onLoadTaskConcluded(ExtractingLoadable.access$200(r15));
        this.mediaSourceEventDispatcher.loadCompleted(r2, 1, -1, null, 0, null, ExtractingLoadable.access$400(r15), this.durationUs);
        this.loadingFinished = true;
        ((MediaPeriod.Callback) Assertions.checkNotNull(this.callback)).onContinueLoadingRequested(this);
        return;
    L5:
        if (this.seekMap == null) goto L11;
        long r22 = getLargestQueuedTimestampUs(true);
        if (r22 != Long.MIN_VALUE) goto L9;
        long r23 = 0;
    L10:
        this.durationUs = r23;
        this.listener.onSourceInfoRefreshed(r23, this.seekMap, this.isLive);
        goto L11
    L9:
        r23 = r22 + NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS;
        goto L10
    }

    @Override // androidx.media3.exoplayer.upstream.Loader.Callback
    public /* bridge */ /* synthetic */ void onLoadCompleted(Loader.Loadable r1, long r2, long r4) {
        onLoadCompleted((ExtractingLoadable) r1, r2, r4);
    }

    public Loader.LoadErrorAction onLoadError(ExtractingLoadable r18, long r19, long r21, IOException r23, int r24) {
        StatsDataSource r1 = ExtractingLoadable.access$100(r18);
        LoadEventInfo r2 = new LoadEventInfo(ExtractingLoadable.access$200(r18), ExtractingLoadable.access$300(r18), r1.getLastOpenedUri(), r1.getLastResponseHeaders(), r19, r21, r1.getBytesRead());
        long r3 = this.loadErrorHandlingPolicy.getRetryDelayMsFor(new LoadErrorHandlingPolicy.LoadErrorInfo(r2, new MediaLoadData(1, -1, null, 0, null, Util.usToMs(ExtractingLoadable.access$400(r18)), Util.usToMs(this.durationUs)), r23, r24));
        if (r3 != (-9223372036854775807L)) goto L5;
        Loader.LoadErrorAction r12 = Loader.DONT_RETRY_FATAL;
        ExtractingLoadable r15 = r18;
    L14:
        boolean r16 = r12.isRetry();
        this.mediaSourceEventDispatcher.loadError(r2, 1, -1, null, 0, null, ExtractingLoadable.access$400(r15), this.durationUs, r23, !r16);
        if (r16 == true) goto L17;
        this.loadErrorHandlingPolicy.onLoadTaskConcluded(ExtractingLoadable.access$200(r15));
    L17:
        return r12;
    L5:
        int r13 = getExtractedSamplesCount();
        if (r13 <= this.extractedSamplesCountAtStartOfLoad) goto L9;
        boolean r5 = true;
    L8:
        r15 = r18;
        if (configureRetry(r15, r13) == false) goto L13;
        r12 = Loader.createRetryAction(r5, r3);
        goto L14
    L13:
        r12 = Loader.DONT_RETRY;
        goto L14
    L9:
        r5 = false;
        goto L8
    }

    @Override // androidx.media3.exoplayer.upstream.Loader.Callback
    public /* bridge */ /* synthetic */ Loader.LoadErrorAction onLoadError(Loader.Loadable r1, long r2, long r4, IOException r6, int r7) {
        return onLoadError((ExtractingLoadable) r1, r2, r4, r6, r7);
    }

    public void onLoadStarted(ExtractingLoadable r17, long r18, long r20, int r22) {
        StatsDataSource r1 = ExtractingLoadable.access$100(r17);
        if (r22 != 0) goto L5;
        LoadEventInfo r5 = new LoadEventInfo(ExtractingLoadable.access$200(r17), ExtractingLoadable.access$300(r17), r18);
    L6:
        this.mediaSourceEventDispatcher.loadStarted(r5, 1, -1, null, 0, null, ExtractingLoadable.access$400(r17), this.durationUs, r22);
        return;
    L5:
        r5 = new LoadEventInfo(ExtractingLoadable.access$200(r17), ExtractingLoadable.access$300(r17), r1.getLastOpenedUri(), r1.getLastResponseHeaders(), r18, r20, r1.getBytesRead());
        goto L6
    }

    @Override // androidx.media3.exoplayer.upstream.Loader.Callback
    public /* bridge */ /* synthetic */ void onLoadStarted(Loader.Loadable r1, long r2, long r4, int r6) {
        onLoadStarted((ExtractingLoadable) r1, r2, r4, r6);
    }

    @Override // androidx.media3.exoplayer.upstream.Loader.ReleaseCallback
    public void onLoaderReleased() {
        SampleQueue[] r0 = this.sampleQueues;
        int r1 = r0.length;
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L5;
        r0[r2].release();
        r2 = r2 + 1;
        goto L3
    L5:
        this.progressiveMediaExtractor.release();
    }

    @Override // androidx.media3.exoplayer.source.SampleQueue.UpstreamFormatChangedListener
    public void onUpstreamFormatChanged(Format r1) {
        this.handler.post(this.maybeFinishPrepareRunnable);
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public void prepare(MediaPeriod.Callback r6, long r7) {
        this.callback = r6;
        if (this.singleTrackFormat == null) goto L6;
        track(this.singleTrackId, 3).format(this.singleTrackFormat);
        setSeekMap(new IndexSeekMap(new long[]{0}, new long[]{0}, -9223372036854775807L));
        endTracks();
        this.pendingResetPositionUs = r7;
        return;
    L6:
        this.loadCondition.open();
        startLoading();
    }

    public int readData(int r4, FormatHolder r5, DecoderInputBuffer r6, int r7) {
        if (suppressRead() == false) goto L5;
        return -3;
    L5:
        maybeNotifyDownstreamFormat(r4);
        int r52 = this.sampleQueues[r4].read(r5, r6, r7, this.loadingFinished);
        if (r52 != (-3)) goto L8;
        maybeStartDeferredRetry(r4);
    L8:
        return r52;
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public long readDiscontinuity() {
        if (this.pendingInitialDiscontinuity == false) goto L7;
        this.pendingInitialDiscontinuity = false;
        return this.lastSeekPositionUs;
    L7:
        if (this.notifyDiscontinuity == true) goto L9;
        return -9223372036854775807L;
    L9:
        if (this.loadingFinished == false) goto L11;
    L12:
        this.notifyDiscontinuity = false;
        return this.lastSeekPositionUs;
    L11:
        if (getExtractedSamplesCount() > this.extractedSamplesCountAtStartOfLoad) goto L12;
        return -9223372036854775807L;
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod, androidx.media3.exoplayer.source.SequenceableLoader
    public void reevaluateBuffer(long r1) {
    }

    public void release() {
        if (this.prepared == false) goto L7;
        SampleQueue[] r0 = this.sampleQueues;
        int r1 = r0.length;
        int r2 = 0;
    L5:
        if (r2 >= r1) goto L7;
        r0[r2].preRelease();
        r2 = r2 + 1;
    L7:
        this.loader.release(this);
        this.handler.removeCallbacksAndMessages(null);
        this.callback = null;
        this.released = true;
    }

    @Override // androidx.media3.extractor.ExtractorOutput
    public void seekMap(SeekMap r3) {
        this.handler.post(new ProgressiveMediaPeriod$$ExternalSyntheticLambda3(this, r3));
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public long seekToUs(long r6) {
        assertPrepared();
        boolean[] r0 = this.trackState.trackIsAudioVideoFlags;
        if (this.seekMap.isSeekable() == true) goto L6;
        r6 = 0;
    L6:
        int r1 = 0;
        this.notifyDiscontinuity = false;
        if (this.lastSeekPositionUs != r6) goto L9;
        boolean r2 = true;
    L10:
        this.lastSeekPositionUs = r6;
        if (isPendingReset() == false) goto L15;
        this.pendingResetPositionUs = r6;
        return r6;
    L15:
        if (this.dataType != 7) goto L17;
    L23:
        this.pendingDeferredRetry = false;
        this.pendingResetPositionUs = r6;
        this.loadingFinished = false;
        this.pendingInitialDiscontinuity = false;
        if (this.loader.isLoading() == false) goto L30;
        SampleQueue[] r02 = this.sampleQueues;
        int r22 = r02.length;
    L26:
        if (r1 >= r22) goto L28;
        r02[r1].discardToEnd();
        r1 = r1 + 1;
        goto L26
    L28:
        this.loader.cancelLoading();
        return r6;
    L30:
        this.loader.clearFatalError();
        SampleQueue[] r5 = this.sampleQueues;
        int r03 = r5.length;
    L31:
        if (r1 >= r03) goto L33;
        r5[r1].reset();
        r1 = r1 + 1;
    L33:
        return r6;
    L17:
        if (this.loadingFinished == true) goto L21;
        if (this.loader.isLoading() == false) goto L23;
    L21:
        if (seekInsideBufferUs(r0, r6, r2) == false) goto L23;
    L9:
        r2 = false;
        goto L10
    }

    @Override // androidx.media3.exoplayer.source.MediaPeriod
    public long selectTracks(ExoTrackSelection[] r9, boolean[] r10, SampleStream[] r11, boolean[] r12, long r13) {
        assertPrepared();
        TrackState r0 = this.trackState;
        TrackGroupArray r1 = r0.tracks;
        boolean[] r02 = r0.trackEnabledStates;
        int r2 = this.enabledTrackCount;
        int r3 = 0;
        int r4 = 0;
    L4:
        if (r4 >= r9.length) goto L14;
        SampleStream r5 = r11[r4];
        if (r5 == null) goto L12;
        if (r9[r4] != null) goto L10;
    L11:
        int r52 = SampleStreamImpl.access$000((SampleStreamImpl) r5);
        Assertions.checkState(r02[r52]);
        this.enabledTrackCount--;
        r02[r52] = false;
        r11[r4] = null;
        goto L12
    L10:
        if (r10[r4] == false) goto L11;
    L12:
        r4 = r4 + 1;
        goto L4
    L14:
        if (this.seenFirstTrackSelection == false) goto L19;
        if (r2 != 0) goto L17;
    L16:
        boolean r102 = true;
    L23:
        int r22 = 0;
    L25:
        if (r22 >= r9.length) goto L48;
        if (r11[r22] != null) goto L46;
        ExoTrackSelection r42 = r9[r22];
        if (r42 == null) goto L46;
        if (r42.length() != 1) goto L33;
        boolean r53 = true;
    L34:
        Assertions.checkState(r53);
        if (r42.getIndexInTrackGroup(0) != 0) goto L37;
        boolean r54 = true;
    L38:
        Assertions.checkState(r54);
        int r55 = r1.indexOf(r42.getTrackGroup());
        Assertions.checkState(!r02[r55]);
        this.enabledTrackCount++;
        r02[r55] = true;
        this.pendingInitialDiscontinuity = r42.getSelectedFormat().hasPrerollSamples | this.pendingInitialDiscontinuity;
        r11[r22] = new SampleStreamImpl(this, r55);
        r12[r22] = true;
        if (r102 == true) goto L46;
        SampleQueue r103 = this.sampleQueues[r55];
        if (r103.getReadIndex() != 0) goto L43;
    L45:
        r102 = false;
        goto L46
    L43:
        if (r103.seekTo(r13, true) == true) goto L45;
        r102 = true;
        goto L46
    L37:
        r54 = false;
        goto L38
    L33:
        r53 = false;
    L46:
        r22 = r22 + 1;
        goto L25
    L48:
        if (this.enabledTrackCount != 0) goto L58;
        this.pendingDeferredRetry = false;
        this.notifyDiscontinuity = false;
        this.pendingInitialDiscontinuity = false;
        if (this.loader.isLoading() == false) goto L55;
        SampleQueue[] r92 = this.sampleQueues;
        int r104 = r92.length;
    L52:
        if (r3 >= r104) goto L54;
        r92[r3].discardToEnd();
        r3 = r3 + 1;
        goto L52
    L54:
        this.loader.cancelLoading();
    L66:
        this.seenFirstTrackSelection = true;
        return r13;
    L55:
        this.loadingFinished = false;
        SampleQueue[] r93 = this.sampleQueues;
        int r105 = r93.length;
    L56:
        if (r3 >= r105) goto L66;
        r93[r3].reset();
        r3 = r3 + 1;
        goto L56
    L58:
        if (r102 == false) goto L66;
        r13 = seekToUs(r13);
    L61:
        if (r3 >= r11.length) goto L66;
        if (r11[r3] == null) goto L65;
        r12[r3] = true;
    L65:
        r3 = r3 + 1;
    L17:
        r102 = false;
        goto L23
    L19:
        if (r13 == 0) goto L17;
        if (this.isSingleSample == true) goto L17;
        goto L17
    }

    public int skipData(int r3, long r4) {
        if (suppressRead() == false) goto L6;
        return 0;
    L6:
        maybeNotifyDownstreamFormat(r3);
        SampleQueue r0 = this.sampleQueues[r3];
        int r42 = r0.getSkipCount(r4, this.loadingFinished);
        r0.skip(r42);
        if (r42 != 0) goto L9;
        maybeStartDeferredRetry(r3);
    L9:
        return r42;
    }

    @Override // androidx.media3.extractor.ExtractorOutput
    public TrackOutput track(int r2, int r3) {
        return prepareTrackOutput(new TrackId(r2, false));
    }
}
