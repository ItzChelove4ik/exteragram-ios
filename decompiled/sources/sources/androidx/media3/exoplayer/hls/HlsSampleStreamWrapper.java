package androidx.media3.exoplayer.hls;

import android.net.Uri;
import android.os.Handler;
import android.util.SparseIntArray;
import androidx.media3.common.DataReader;
import androidx.media3.common.DrmInitData;
import androidx.media3.common.Format;
import androidx.media3.common.Metadata;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.ParserException;
import androidx.media3.common.TrackGroup;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.HttpDataSource$InvalidResponseCodeException;
import androidx.media3.decoder.DecoderInputBuffer;
import androidx.media3.exoplayer.FormatHolder;
import androidx.media3.exoplayer.LoadingInfo;
import androidx.media3.exoplayer.SeekParameters;
import androidx.media3.exoplayer.drm.DrmSessionEventListener;
import androidx.media3.exoplayer.drm.DrmSessionManager;
import androidx.media3.exoplayer.hls.HlsChunkSource;
import androidx.media3.exoplayer.source.LoadEventInfo;
import androidx.media3.exoplayer.source.MediaLoadData;
import androidx.media3.exoplayer.source.MediaSourceEventListener;
import androidx.media3.exoplayer.source.SampleQueue;
import androidx.media3.exoplayer.source.SampleStream;
import androidx.media3.exoplayer.source.SequenceableLoader;
import androidx.media3.exoplayer.source.TrackGroupArray;
import androidx.media3.exoplayer.source.chunk.Chunk;
import androidx.media3.exoplayer.trackselection.ExoTrackSelection;
import androidx.media3.exoplayer.trackselection.TrackSelectionUtil;
import androidx.media3.exoplayer.upstream.Allocator;
import androidx.media3.exoplayer.upstream.LoadErrorHandlingPolicy;
import androidx.media3.exoplayer.upstream.Loader;
import androidx.media3.extractor.DiscardingTrackOutput;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.SeekMap;
import androidx.media3.extractor.TrackOutput;
import androidx.media3.extractor.metadata.emsg.EventMessage;
import androidx.media3.extractor.metadata.emsg.EventMessageDecoder;
import androidx.media3.extractor.metadata.id3.PrivFrame;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.google.common.primitives.Ints;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import kotlin.CharCodeKt$$ExternalSyntheticBUOutline0;
import okio.Buffer$$ExternalSyntheticBUOutline1;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

/* loaded from: classes4.dex */
final class HlsSampleStreamWrapper implements Loader.Callback<Chunk>, Loader.ReleaseCallback, SequenceableLoader, ExtractorOutput, SampleQueue.UpstreamFormatChangedListener {
    private static final Set<Integer> MAPPABLE_TYPES = null;
    private final Allocator allocator;
    private final Callback callback;
    private final HlsChunkSource chunkSource;
    private Format downstreamTrackFormat;
    private final DrmSessionEventListener.EventDispatcher drmEventDispatcher;
    private DrmInitData drmInitData;
    private final DrmSessionManager drmSessionManager;
    private TrackOutput emsgUnwrappingTrackOutput;
    private int enabledTrackGroupCount;
    private final Handler handler;
    private boolean haveAudioVideoSampleQueues;
    private final ArrayList<HlsSampleStream> hlsSampleStreams;
    private long lastSeekPositionUs;
    private final LoadErrorHandlingPolicy loadErrorHandlingPolicy;
    private final Loader loader;
    private Chunk loadingChunk;
    private boolean loadingFinished;
    private final Runnable maybeFinishPrepareRunnable;
    private final ArrayList<HlsMediaChunk> mediaChunks;
    private final MediaSourceEventListener.EventDispatcher mediaSourceEventDispatcher;
    private final int metadataType;
    private final Format muxedAudioFormat;
    private final HlsChunkSource.HlsChunkHolder nextChunkHolder;
    private final Runnable onTracksEndedRunnable;
    private Set<TrackGroup> optionalTrackGroups;
    private final Map<String, DrmInitData> overridingDrmInitData;
    private long pendingResetPositionUs;
    private boolean pendingResetUpstreamFormats;
    private boolean prepared;
    private int primarySampleQueueIndex;
    private int primarySampleQueueType;
    private int primaryTrackGroupIndex;
    private final List<HlsMediaChunk> readOnlyMediaChunks;
    private boolean released;
    private long sampleOffsetUs;
    private SparseIntArray sampleQueueIndicesByType;
    private boolean[] sampleQueueIsAudioVideoFlags;
    private Set<Integer> sampleQueueMappingDoneByType;
    private int[] sampleQueueTrackIds;
    private HlsSampleQueue[] sampleQueues;
    private boolean sampleQueuesBuilt;
    private boolean[] sampleQueuesEnabledStates;
    private boolean seenFirstTrackSelection;
    private HlsMediaChunk sourceChunk;
    private int[] trackGroupToSampleQueueIndex;
    private TrackGroupArray trackGroups;
    private final int trackType;
    private boolean tracksEnded;
    private final String uid;
    private Format upstreamTrackFormat;

    /* renamed from: androidx.media3.exoplayer.hls.HlsSampleStreamWrapper$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public interface Callback extends SequenceableLoader.Callback<HlsSampleStreamWrapper> {
        void onPlaylistRefreshRequired(Uri r1);

        void onPrepared();
    }

    public static class EmsgUnwrappingTrackOutput implements TrackOutput {
        private static final Format EMSG_FORMAT = null;
        private static final Format ID3_FORMAT = null;
        private byte[] buffer;
        private int bufferPosition;
        private final TrackOutput delegate;
        private final Format delegateFormat;
        private final EventMessageDecoder emsgDecoder;
        private Format format;

        static {
            ID3_FORMAT = new Format.Builder().setSampleMimeType("application/id3").build();
            EMSG_FORMAT = new Format.Builder().setSampleMimeType("application/x-emsg").build();
        }

        public EmsgUnwrappingTrackOutput(TrackOutput r2, int r3) {
            this.emsgDecoder = new EventMessageDecoder();
            this.delegate = r2;
            if (r3 != 1) goto L5;
            this.delegateFormat = ID3_FORMAT;
        L10:
            this.buffer = new byte[0];
            this.bufferPosition = 0;
            return;
        L5:
            if (r3 != 3) goto L7;
            this.delegateFormat = EMSG_FORMAT;
            goto L10
        L7:
            CharCodeKt$$ExternalSyntheticBUOutline0.m("Unknown metadataType: ", r3);
            throw null;
        }

        private boolean emsgContainsExpectedWrappedFormat(EventMessage r1) {
            Format r12 = r1.getWrappedMetadataFormat();
            if (r12 != null) goto L5;
            return false;
        L5:
            if (Objects.equals(this.delegateFormat.sampleMimeType, r12.sampleMimeType) == false) goto L10;
            return true;
        L10:
            return false;
        }

        private void ensureBufferCapacity(int r3) {
            byte[] r0 = this.buffer;
            if (r0.length >= r3) goto L6;
            this.buffer = Arrays.copyOf(r0, r3 + (r3 / 2));
            return;
        }

        private ParsableByteArray getSampleAndTrimBuffer(int r4, int r5) {
            int r0 = this.bufferPosition - r5;
            byte[] r1 = this.buffer;
            ParsableByteArray r12 = new ParsableByteArray(Arrays.copyOfRange(r1, r0 - r4, r0));
            byte[] r42 = this.buffer;
            System.arraycopy(r42, r0, r42, 0, r5);
            this.bufferPosition = r5;
            return r12;
        }

        @Override // androidx.media3.extractor.TrackOutput
        public void format(Format r1) {
            this.format = r1;
            this.delegate.format(this.delegateFormat);
        }

        @Override // androidx.media3.extractor.TrackOutput
        public int sampleData(DataReader r2, int r3, boolean r4, int r5) {
            ensureBufferCapacity(this.bufferPosition + r3);
            int r22 = r2.read(this.buffer, this.bufferPosition, r3);
            if (r22 != (-1)) goto L8;
            if (r4 == false) goto L6;
            return -1;
        L6:
            Buffer$$ExternalSyntheticBUOutline1.m();
            return 0;
        L8:
            this.bufferPosition += r22;
            return r22;
        }

        @Override // androidx.media3.extractor.TrackOutput
        public void sampleData(ParsableByteArray r2, int r3, int r4) {
            ensureBufferCapacity(this.bufferPosition + r3);
            r2.readBytes(this.buffer, this.bufferPosition, r3);
            this.bufferPosition += r3;
        }

        @Override // androidx.media3.extractor.TrackOutput
        public void sampleMetadata(long r8, int r10, int r11, int r12, TrackOutput.CryptoData r13) {
            Assertions.checkNotNull(this.format);
            ParsableByteArray r112 = getSampleAndTrimBuffer(r11, r12);
            if (Objects.equals(this.format.sampleMimeType, this.delegateFormat.sampleMimeType) == false) goto L6;
        L12:
            int r4 = r112.bytesLeft();
            this.delegate.sampleData(r112, r4);
            this.delegate.sampleMetadata(r8, r10, r4, 0, r13);
            return;
        L6:
            if ("application/x-emsg".equals(this.format.sampleMimeType) == false) goto L14;
            EventMessage r113 = this.emsgDecoder.decode(r112);
            if (emsgContainsExpectedWrappedFormat(r113) == true) goto L11;
            Log.w("HlsSampleStreamWrapper", String.format("Ignoring EMSG. Expected it to contain wrapped %s but actual wrapped format: %s", new Object[]{this.delegateFormat.sampleMimeType, r113.getWrappedMetadataFormat()}));
            return;
        L11:
            r112 = new ParsableByteArray((byte[]) Assertions.checkNotNull(r113.getWrappedMetadataBytes()));
            goto L12
        L14:
            Log.w("HlsSampleStreamWrapper", "Ignoring sample for unsupported format: " + this.format.sampleMimeType);
        }
    }

    public static final class HlsSampleQueue extends SampleQueue {
        private DrmInitData drmInitData;
        private final Map<String, DrmInitData> overridingDrmInitData;

        private HlsSampleQueue(Allocator r1, DrmSessionManager r2, DrmSessionEventListener.EventDispatcher r3, Map<String, DrmInitData> r4) {
            super(r1, r2, r3);
            this.overridingDrmInitData = r4;
        }

        public /* synthetic */ HlsSampleQueue(Allocator r1, DrmSessionManager r2, DrmSessionEventListener.EventDispatcher r3, Map r4, AnonymousClass1 r5) {
            this(r1, r2, r3, r4);
        }

        private Metadata getAdjustedMetadata(Metadata r7) {
            if (r7 != null) goto L5;
            return null;
        L5:
            int r0 = r7.length();
            int r1 = 0;
            int r2 = 0;
        L7:
            if (r2 >= r0) goto L14;
            Metadata.Entry r4 = r7.get(r2);
            if ((r4 instanceof PrivFrame) == false) goto L13;
            if ("com.apple.streaming.transportStreamTimestamp".equals(((PrivFrame) r4).owner) == false) goto L13;
        L15:
            if (r2 != (-1)) goto L18;
            return r7;
        L18:
            if (r0 != 1) goto L20;
            return null;
        L20:
            Metadata.Entry[] r6 = new Metadata.Entry[r0 - 1];
        L21:
            if (r1 >= r0) goto L29;
            if (r1 == r2) goto L27;
            if (r1 >= r2) goto L25;
            int r3 = r1;
        L26:
            r6[r3] = r7.get(r1);
            goto L27
        L25:
            r3 = r1 - 1;
        L27:
            r1 = r1 + 1;
            goto L21
        L29:
            return new Metadata(r6);
        L13:
            r2 = r2 + 1;
            goto L7
        L14:
            r2 = -1;
            goto L15
        }

        @Override // androidx.media3.exoplayer.source.SampleQueue
        public Format getAdjustedUpstreamFormat(Format r4) {
            DrmInitData r0 = this.drmInitData;
            if (r0 != null) goto L6;
            r0 = r4.drmInitData;
        L6:
            if (r0 == null) goto L10;
            DrmInitData r1 = this.overridingDrmInitData.get(r0.schemeType);
            if (r1 == null) goto L10;
            r0 = r1;
        L10:
            Metadata r12 = getAdjustedMetadata(r4.metadata);
            if (r0 == r4.drmInitData) goto L13;
        L14:
            r4 = r4.buildUpon().setDrmInitData(r0).setMetadata(r12).build();
        L16:
            return super.getAdjustedUpstreamFormat(r4);
        L13:
            if (r12 == r4.metadata) goto L16;
            goto L14
        }

        @Override // androidx.media3.exoplayer.source.SampleQueue, androidx.media3.extractor.TrackOutput
        public void sampleMetadata(long r1, int r3, int r4, int r5, TrackOutput.CryptoData r6) {
            super.sampleMetadata(r1, r3, r4, r5, r6);
        }

        public void setDrmInitData(DrmInitData r1) {
            this.drmInitData = r1;
            invalidateUpstreamFormatAdjustment();
        }

        public void setSourceChunk(HlsMediaChunk r3) {
            sourceId(r3.uid);
        }
    }

    public static /* synthetic */ void $r8$lambda$4T0XYfGSx3i_ZqboGl7iI4j9qEU(HlsSampleStreamWrapper r0) {
        r0.onTracksEnded();
    }

    public static /* synthetic */ void $r8$lambda$UK4TNmzu4rF7l558xzg8C7fYdDI(HlsSampleStreamWrapper r0) {
        r0.maybeFinishPrepare();
    }

    public static /* synthetic */ void $r8$lambda$Yr5WPKSIS9DpuMVl3pLdQ20QiaM(HlsSampleStreamWrapper r0, HlsMediaChunk r1) {
        r0.callback.onPlaylistRefreshRequired(r1.playlistUrl);
    }

    static {
        MAPPABLE_TYPES = Collections.unmodifiableSet(new HashSet(Arrays.asList(new Integer[]{1, 2, 5})));
    }

    public HlsSampleStreamWrapper(String r1, int r2, Callback r3, HlsChunkSource r4, Map<String, DrmInitData> r5, Allocator r6, long r7, Format r9, DrmSessionManager r10, DrmSessionEventListener.EventDispatcher r11, LoadErrorHandlingPolicy r12, MediaSourceEventListener.EventDispatcher r13, int r14) {
        this.uid = r1;
        this.trackType = r2;
        this.callback = r3;
        this.chunkSource = r4;
        this.overridingDrmInitData = r5;
        this.allocator = r6;
        this.muxedAudioFormat = r9;
        this.drmSessionManager = r10;
        this.drmEventDispatcher = r11;
        this.loadErrorHandlingPolicy = r12;
        this.mediaSourceEventDispatcher = r13;
        this.metadataType = r14;
        this.loader = new Loader("Loader:HlsSampleStreamWrapper");
        this.nextChunkHolder = new HlsChunkSource.HlsChunkHolder();
        this.sampleQueueTrackIds = new int[0];
        Set<Integer> r32 = MAPPABLE_TYPES;
        this.sampleQueueMappingDoneByType = new HashSet(r32.size());
        this.sampleQueueIndicesByType = new SparseIntArray(r32.size());
        this.sampleQueues = new HlsSampleQueue[0];
        this.sampleQueueIsAudioVideoFlags = new boolean[0];
        this.sampleQueuesEnabledStates = new boolean[0];
        ArrayList<HlsMediaChunk> r15 = new ArrayList();
        this.mediaChunks = r15;
        this.readOnlyMediaChunks = Collections.unmodifiableList(r15);
        this.hlsSampleStreams = new ArrayList();
        this.maybeFinishPrepareRunnable = new HlsSampleStreamWrapper$$ExternalSyntheticLambda1(this);
        this.onTracksEndedRunnable = new HlsSampleStreamWrapper$$ExternalSyntheticLambda2(this);
        this.handler = Util.createHandlerForCurrentLooper();
        this.lastSeekPositionUs = r7;
        this.pendingResetPositionUs = r7;
    }

    @EnsuresNonNull({"trackGroups", "optionalTrackGroups"})
    private void assertIsPrepared() {
        Assertions.checkState(this.prepared);
        Assertions.checkNotNull(this.trackGroups);
        Assertions.checkNotNull(this.optionalTrackGroups);
    }

    @EnsuresNonNull({"trackGroups", "optionalTrackGroups", "trackGroupToSampleQueueIndex"})
    private void buildTracksFromSampleStreams() {
        int r0 = this.sampleQueues.length;
        boolean r3 = false;
        int r5 = -2;
        int r6 = -1;
        int r4 = 0;
    L3:
        int r7 = 2;
        if (r4 >= r0) goto L22;
        String r9 = ((Format) Assertions.checkStateNotNull(this.sampleQueues[r4].getUpstreamFormat())).sampleMimeType;
        if (MimeTypes.isVideo(r9) == true) goto L16;
        if (MimeTypes.isAudio(r9) == false) goto L12;
        r7 = 1;
        goto L16
    L12:
        if (MimeTypes.isText(r9) == false) goto L14;
        r7 = 3;
        goto L16
    L14:
        r7 = -2;
    L16:
        if (getTrackTypeScore(r7) <= getTrackTypeScore(r5)) goto L18;
        r6 = r4;
        r5 = r7;
    L21:
        r4 = r4 + 1;
        goto L3
    L18:
        if (r7 != r5) goto L21;
        if (r6 == (-1)) goto L21;
        r6 = -1;
        goto L21
    L22:
        TrackGroup r1 = this.chunkSource.getTrackGroup();
        int r42 = r1.length;
        this.primaryTrackGroupIndex = -1;
        this.trackGroupToSampleQueueIndex = new int[r0];
        int r2 = 0;
    L23:
        if (r2 >= r0) goto L25;
        this.trackGroupToSampleQueueIndex[r2] = r2;
        r2 = r2 + 1;
        goto L23
    L25:
        TrackGroup[] r22 = new TrackGroup[r0];
        int r92 = 0;
    L26:
        if (r92 >= r0) goto L52;
        Format r10 = (Format) Assertions.checkStateNotNull(this.sampleQueues[r92].getUpstreamFormat());
        if (r92 != r6) goto L41;
        Format[] r11 = new Format[r42];
        int r12 = 0;
    L30:
        if (r12 >= r42) goto L40;
        Format r13 = r1.getFormat(r12);
        if (r5 != 1) goto L36;
        Format r14 = this.muxedAudioFormat;
        if (r14 == null) goto L36;
        r13 = r13.withManifestFormatInfo(r14);
    L36:
        if (r42 != 1) goto L38;
        Format r132 = r10.withManifestFormatInfo(r13);
    L39:
        r11[r12] = r132;
        r12 = r12 + 1;
        goto L30
    L38:
        r132 = deriveFormat(r13, r10, true);
        goto L39
    L40:
        r22[r92] = new TrackGroup(this.uid, r11);
        this.primaryTrackGroupIndex = r92;
    L51:
        r92 = r92 + 1;
        goto L26
    L41:
        if (r5 == 2) goto L43;
    L45:
        Format r112 = null;
    L46:
        StringBuilder r122 = new StringBuilder();
        r122.append(this.uid);
        r122.append(":muxed:");
        if (r92 >= r6) goto L49;
        int r133 = r92;
    L50:
        r122.append(r133);
        r22[r92] = new TrackGroup(r122.toString(), new Format[]{deriveFormat(r112, r10, false)});
        goto L51
    L49:
        r133 = r92 - 1;
        goto L50
    L43:
        if (MimeTypes.isAudio(r10.sampleMimeType) == false) goto L45;
        r112 = this.muxedAudioFormat;
        goto L46
    L52:
        this.trackGroups = createTrackGroupArrayWithDrmInfo(r22);
        if (this.optionalTrackGroups != null) goto L55;
        r3 = true;
    L55:
        Assertions.checkState(r3);
        this.optionalTrackGroups = Collections.EMPTY_SET;
    }

    private boolean canDiscardUpstreamMediaChunksFromIndex(int r5) {
        int r0 = r5;
    L3:
        int r1 = this.mediaChunks.size();
        ArrayList<HlsMediaChunk> r2 = this.mediaChunks;
        if (r0 >= r1) goto L9;
        if (r2.get(r0).shouldSpliceIn() == true) goto L7;
        r0 = r0 + 1;
        goto L3
    L7:
        return false;
    L9:
        HlsMediaChunk r52 = r2.get(r5);
        int r02 = 0;
    L11:
        if (r02 >= this.sampleQueues.length) goto L16;
        if (this.sampleQueues[r02].getReadIndex() > r52.getFirstSampleIndex(r02)) goto L14;
        r02 = r02 + 1;
        goto L11
    L14:
        return false;
    L16:
        return true;
    }

    private static DiscardingTrackOutput createDiscardingTrackOutput(int r2, int r3) {
        Log.w("HlsSampleStreamWrapper", "Unmapped track with id " + r2 + " of type " + r3);
        return new DiscardingTrackOutput();
    }

    private SampleQueue createSampleQueue(int r9, int r10) {
        int r0 = this.sampleQueues.length;
        boolean r1 = true;
        if (r10 != 1) goto L5;
    L8:
        HlsSampleQueue r2 = new HlsSampleQueue(this.allocator, this.drmSessionManager, this.drmEventDispatcher, this.overridingDrmInitData, null);
        r2.setStartTimeUs(this.lastSeekPositionUs);
        if (r1 == false) goto L11;
        r2.setDrmInitData(this.drmInitData);
    L11:
        r2.setSampleOffsetUs(this.sampleOffsetUs);
        HlsMediaChunk r3 = this.sourceChunk;
        if (r3 == null) goto L14;
        r2.setSourceChunk(r3);
    L14:
        r2.setUpstreamFormatChangeListener(this);
        int r4 = r0 + 1;
        int[] r32 = Arrays.copyOf(this.sampleQueueTrackIds, r4);
        this.sampleQueueTrackIds = r32;
        r32[r0] = r9;
        this.sampleQueues = (HlsSampleQueue[]) Util.nullSafeArrayAppend(this.sampleQueues, r2);
        boolean[] r92 = Arrays.copyOf(this.sampleQueueIsAudioVideoFlags, r4);
        this.sampleQueueIsAudioVideoFlags = r92;
        r92[r0] = r1;
        this.haveAudioVideoSampleQueues |= r1;
        this.sampleQueueMappingDoneByType.add(Integer.valueOf(r10));
        this.sampleQueueIndicesByType.append(r10, r0);
        if (getTrackTypeScore(r10) <= getTrackTypeScore(this.primarySampleQueueType)) goto L17;
        this.primarySampleQueueIndex = r0;
        this.primarySampleQueueType = r10;
    L17:
        this.sampleQueuesEnabledStates = Arrays.copyOf(this.sampleQueuesEnabledStates, r4);
        return r2;
    L5:
        if (r10 == 2) goto L8;
        r1 = false;
        goto L8
    }

    private TrackGroupArray createTrackGroupArrayWithDrmInfo(TrackGroup[] r8) {
        int r1 = 0;
    L4:
        if (r1 >= r8.length) goto L11;
        TrackGroup r2 = r8[r1];
        Format[] r3 = new Format[r2.length];
        int r4 = 0;
    L7:
        if (r4 >= r2.length) goto L9;
        Format r5 = r2.getFormat(r4);
        r3[r4] = r5.copyWithCryptoType(this.drmSessionManager.getCryptoType(r5));
        r4 = r4 + 1;
        goto L7
    L9:
        r8[r1] = new TrackGroup(r2.id, r3);
        r1 = r1 + 1;
        goto L4
    L11:
        return new TrackGroupArray(r8);
    }

    private static Format deriveFormat(Format r8, Format r9, boolean r10) {
        if (r8 != null) goto L4;
        return r9;
    L4:
        int r0 = MimeTypes.getTrackType(r9.sampleMimeType);
        int r1 = Util.getCodecCountOfType(r8.codecs, r0);
        String r2 = r8.codecs;
        if (r1 != 1) goto L7;
        String r12 = Util.getCodecsOfType(r2, r0);
        String r22 = MimeTypes.getMediaMimeType(r12);
    L8:
        r9.cached = r8.cached;
        r9.documentId = r8.documentId;
        r9.currentAccount = r8.currentAccount;
        r9.documentFilename = r8.documentFilename;
        Format.Builder r4 = r9.buildUpon().setId(r8.id).setLabel(r8.label).setLabels(r8.labels).setLanguage(r8.language).setSelectionFlags(r8.selectionFlags).setRoleFlags(r8.roleFlags);
        if (r10 == false) goto L11;
        int r6 = r8.averageBitrate;
    L12:
        Format.Builder r42 = r4.setAverageBitrate(r6);
        if (r10 == false) goto L15;
        int r102 = r8.peakBitrate;
    L16:
        Format.Builder r103 = r42.setPeakBitrate(r102).setCodecs(r12).setCurrentAccount(r8.currentAccount).setDocumentId(r8.documentId).setCached(r8.cached).setDocumentFilename(r8.documentFilename);
        if (r0 != 2) goto L19;
        r103.setWidth(r8.width).setHeight(r8.height).setFrameRate(r8.frameRate);
    L19:
        if (r22 == null) goto L21;
        r103.setSampleMimeType(r22);
    L21:
        int r13 = r8.channelCount;
        if (r13 == (-1)) goto L25;
        if (r0 != 1) goto L25;
        r103.setChannelCount(r13);
    L25:
        Metadata r82 = r8.metadata;
        if (r82 == null) goto L32;
        Metadata r92 = r9.metadata;
        if (r92 == null) goto L30;
        r82 = r92.copyWithAppendedEntriesFrom(r82);
    L30:
        r103.setMetadata(r82);
    L32:
        return r103.build();
    L15:
        r102 = -1;
        goto L16
    L11:
        r6 = -1;
        goto L12
    L7:
        r12 = MimeTypes.getCodecsCorrespondingToMimeType(r2, r9.sampleMimeType);
        r22 = r9.sampleMimeType;
        goto L8
    }

    private void discardUpstream(int r8) {
        Assertions.checkState(!this.loader.isLoading());
    L4:
        if (r8 >= this.mediaChunks.size()) goto L9;
        if (canDiscardUpstreamMediaChunksFromIndex(r8) == true) goto L10;
        r8 = r8 + 1;
    L10:
        if (r8 != (-1)) goto L12;
        return;
    L12:
        long r5 = getLastMediaChunk().endTimeUs;
        HlsMediaChunk r82 = discardUpstreamMediaChunksFromIndex(r8);
        if (this.mediaChunks.isEmpty() == false) goto L15;
        this.pendingResetPositionUs = this.lastSeekPositionUs;
    L16:
        this.loadingFinished = false;
        this.mediaSourceEventDispatcher.upstreamDiscarded(this.primarySampleQueueType, r82.startTimeUs, r5);
        return;
    L15:
        ((HlsMediaChunk) Iterables.getLast(this.mediaChunks)).invalidateExtractor();
        goto L16
    L9:
        r8 = -1;
        goto L10
    }

    private HlsMediaChunk discardUpstreamMediaChunksFromIndex(int r4) {
        HlsMediaChunk r0 = this.mediaChunks.get(r4);
        ArrayList<HlsMediaChunk> r1 = this.mediaChunks;
        Util.removeRange(r1, r4, r1.size());
        int r42 = 0;
    L4:
        if (r42 >= this.sampleQueues.length) goto L6;
        int r12 = r0.getFirstSampleIndex(r42);
        this.sampleQueues[r42].discardUpstreamSamples(r12);
        r42 = r42 + 1;
        goto L4
    L6:
        return r0;
    }

    private boolean finishedReadingChunk(HlsMediaChunk r8) {
        int r82 = r8.uid;
        int r0 = this.sampleQueues.length;
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L10;
        if (this.sampleQueuesEnabledStates[r2] == false) goto L9;
        if (this.sampleQueues[r2].peekSourceId() != r82) goto L9;
        return false;
    L9:
        r2 = r2 + 1;
        goto L3
    L10:
        return true;
    }

    private static boolean formatsMatch(Format r6, Format r7) {
        String r0 = r6.sampleMimeType;
        String r1 = r7.sampleMimeType;
        int r2 = MimeTypes.getTrackType(r0);
        if (r2 == 3) goto L9;
        if (r2 != MimeTypes.getTrackType(r1)) goto L7;
        return true;
    L7:
        return false;
    L9:
        if (Objects.equals(r0, r1) == true) goto L12;
        return false;
    L12:
        if ("application/cea-608".equals(r0) == true) goto L18;
        if ("application/cea-708".equals(r0) == true) goto L18;
        return true;
    L18:
        if (r6.accessibilityChannel != r7.accessibilityChannel) goto L20;
        return true;
    L20:
        return false;
    }

    private HlsMediaChunk getLastMediaChunk() {
        return this.mediaChunks.get(r1.size() - 1);
    }

    private TrackOutput getMappedTrackOutput(int r4, int r5) {
        Assertions.checkArgument(MAPPABLE_TYPES.contains(Integer.valueOf(r5)));
        int r0 = this.sampleQueueIndicesByType.get(r5, -1);
        if (r0 != (-1)) goto L7;
        return null;
    L7:
        if (this.sampleQueueMappingDoneByType.add(Integer.valueOf(r5)) == false) goto L10;
        this.sampleQueueTrackIds[r0] = r4;
    L10:
        if (this.sampleQueueTrackIds[r0] != r4) goto L14;
        return this.sampleQueues[r0];
    L14:
        return createDiscardingTrackOutput(r4, r5);
    }

    private static int getTrackTypeScore(int r3) {
        if (r3 != 1) goto L5;
        return 2;
    L5:
        if (r3 == 2) goto L10;
        if (r3 == 3) goto L9;
        return 0;
    L9:
        return 1;
    L10:
        return 3;
    }

    private void initMediaChunkLoad(HlsMediaChunk r7) {
        this.sourceChunk = r7;
        this.upstreamTrackFormat = r7.trackFormat;
        this.pendingResetPositionUs = -9223372036854775807L;
        this.mediaChunks.add(r7);
        ImmutableList.Builder r0 = ImmutableList.builder();
        HlsSampleQueue[] r1 = this.sampleQueues;
        int r2 = r1.length;
        int r3 = 0;
        int r4 = 0;
    L3:
        if (r4 >= r2) goto L5;
        r0.add(Integer.valueOf(r1[r4].getWriteIndex()));
        r4 = r4 + 1;
        goto L3
    L5:
        r7.init(this, r0.build());
        HlsSampleQueue[] r6 = this.sampleQueues;
        int r02 = r6.length;
    L6:
        if (r3 >= r02) goto L11;
        HlsSampleQueue r12 = r6[r3];
        r12.setSourceChunk(r7);
        if (r7.shouldSpliceIn() == false) goto L10;
        r12.splice();
    L10:
        r3 = r3 + 1;
        goto L6
    }

    private static boolean isMediaChunk(Chunk r0) {
        return r0 instanceof HlsMediaChunk;
    }

    private boolean isPendingReset() {
        if (this.pendingResetPositionUs == (-9223372036854775807L)) goto L6;
        return true;
    L6:
        return false;
    }

    @EnsuresNonNull({"trackGroupToSampleQueueIndex"})
    @RequiresNonNull({"trackGroups"})
    private void mapSampleQueuesToMatchTrackGroups() {
        int r0 = this.trackGroups.length;
        int[] r1 = new int[r0];
        this.trackGroupToSampleQueueIndex = r1;
        Arrays.fill(r1, -1);
        int r12 = 0;
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L12;
        int r3 = 0;
    L5:
        HlsSampleQueue[] r4 = this.sampleQueues;
        if (r3 >= r4.length) goto L11;
        if (formatsMatch((Format) Assertions.checkStateNotNull(r4[r3].getUpstreamFormat()), this.trackGroups.get(r2).getFormat(0)) == true) goto L9;
        r3 = r3 + 1;
        goto L5
    L9:
        this.trackGroupToSampleQueueIndex[r2] = r3;
    L11:
        r2 = r2 + 1;
        goto L3
    L12:
        ArrayList<HlsSampleStream> r6 = this.hlsSampleStreams;
        int r02 = r6.size();
    L13:
        if (r12 >= r02) goto L15;
        HlsSampleStream r22 = r6.get(r12);
        r12 = r12 + 1;
        r22.bindSampleQueue();
        goto L13
    }

    private void maybeDiscardUpstreamForNewMediaChunk(HlsMediaChunk r7) {
        if (this.mediaChunks.isEmpty() == false) goto L6;
        return;
    L6:
        if (getLastMediaChunk().isPublished() == true) goto L9;
        discardUpstream(this.mediaChunks.size() - 1);
    L9:
        if (r7.isIndependent == true) goto L11;
        return;
    L11:
        if (r7.shouldSpliceIn() == false) goto L30;
        int r0 = this.mediaChunks.size() - 1;
    L13:
        if (r0 < 0) goto L32;
        long r1 = this.mediaChunks.get(r0).startTimeUs;
        long r3 = r7.startTimeUs;
        if (r1 < r3) goto L33;
        if (r1 != r3) goto L23;
        if (canDiscardUpstreamMediaChunksFromIndex(r0) == false) goto L23;
        discardUpstream(r0);
        r7.clearShouldSpliceIn();
        return;
    L23:
        r0 = r0 - 1;
        goto L13
    L33:
        return;
    L32:
        return;
    }

    private void maybeFinishPrepare() {
        if (this.released == false) goto L5;
        return;
    L5:
        if (this.trackGroupToSampleQueueIndex == null) goto L7;
        return;
    L7:
        if (this.sampleQueuesBuilt == false) goto L25;
        HlsSampleQueue[] r0 = this.sampleQueues;
        int r1 = r0.length;
        int r2 = 0;
    L10:
        if (r2 >= r1) goto L16;
        if (r0[r2].getUpstreamFormat() == null) goto L26;
        r2 = r2 + 1;
        goto L10
    L26:
        return;
    L16:
        if (this.trackGroups == null) goto L19;
        mapSampleQueuesToMatchTrackGroups();
        return;
    L19:
        buildTracksFromSampleStreams();
        setIsPrepared();
        this.callback.onPrepared();
        return;
    }

    private void onTracksEnded() {
        this.sampleQueuesBuilt = true;
        maybeFinishPrepare();
    }

    private void resetSampleQueues() {
        HlsSampleQueue[] r0 = this.sampleQueues;
        int r1 = r0.length;
        int r3 = 0;
    L3:
        if (r3 >= r1) goto L5;
        r0[r3].reset(this.pendingResetUpstreamFormats);
        r3 = r3 + 1;
        goto L3
    L5:
        this.pendingResetUpstreamFormats = false;
    }

    private boolean seekInsideBufferUs(long r10, HlsMediaChunk r12) {
        int r0 = this.sampleQueues.length;
        int r2 = 0;
    L3:
        boolean r3 = true;
        if (r2 >= r0) goto L22;
        HlsSampleQueue r4 = this.sampleQueues[r2];
        if (r12 == null) goto L8;
        boolean r32 = r4.seekTo(r12.getFirstSampleIndex(r2));
    L15:
        if (r32 == true) goto L21;
        if (this.sampleQueueIsAudioVideoFlags[r2] == true) goto L20;
        if (this.haveAudioVideoSampleQueues == true) goto L21;
    L20:
        return false;
    L21:
        r2 = r2 + 1;
        goto L3
    L8:
        long r5 = getNextLoadPositionUs();
        if (r5 != Long.MIN_VALUE) goto L11;
    L14:
        r32 = r4.seekTo(r10, r3);
        goto L15
    L11:
        if (r10 < r5) goto L14;
        r3 = false;
        goto L14
    L22:
        return true;
    }

    @RequiresNonNull({"trackGroups", "optionalTrackGroups"})
    private void setIsPrepared() {
        this.prepared = true;
    }

    private void updateSampleStreams(SampleStream[] r5) {
        this.hlsSampleStreams.clear();
        int r0 = r5.length;
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L8;
        SampleStream r2 = r5[r1];
        if (r2 == null) goto L7;
        this.hlsSampleStreams.add((HlsSampleStream) r2);
    L7:
        r1 = r1 + 1;
        goto L3
    }

    public int bindSampleQueueToSampleStream(int r4) {
        assertIsPrepared();
        Assertions.checkNotNull(this.trackGroupToSampleQueueIndex);
        int r0 = this.trackGroupToSampleQueueIndex[r4];
        if (r0 == (-1)) goto L5;
        boolean[] r3 = this.sampleQueuesEnabledStates;
        if (r3[r0] == false) goto L12;
        return -2;
    L12:
        r3[r0] = true;
        return r0;
    L5:
        if (this.optionalTrackGroups.contains(this.trackGroups.get(r4)) == false) goto L8;
        return -3;
    L8:
        return -2;
    }

    @Override // androidx.media3.exoplayer.source.SequenceableLoader
    public boolean continueLoading(LoadingInfo r20) {
        if (this.loadingFinished == false) goto L5;
    L48:
        return false;
    L5:
        if (this.loader.isLoading() == true) goto L48;
        if (this.loader.hasFatalError() == true) goto L48;
        if (isPendingReset() == false) goto L15;
        List<HlsMediaChunk> r1 = Collections.EMPTY_LIST;
        long r3 = this.pendingResetPositionUs;
        HlsSampleQueue[] r5 = this.sampleQueues;
        int r6 = r5.length;
        int r7 = 0;
    L12:
        if (r7 >= r6) goto L14;
        r5[r7].setStartTimeUs(this.pendingResetPositionUs);
        r7 = r7 + 1;
        goto L12
    L14:
        List<HlsMediaChunk> r16 = r1;
        long r12 = r3;
        long r14 = r12;
    L28:
        this.nextChunkHolder.clear();
        HlsChunkSource r10 = this.chunkSource;
        if (this.prepared == false) goto L31;
    L34:
        boolean r17 = true;
    L35:
        r10.getNextChunk(r20, r12, r14, r16, r17, this.nextChunkHolder);
        HlsChunkSource.HlsChunkHolder r13 = this.nextChunkHolder;
        boolean r4 = r13.endOfStream;
        Chunk r52 = r13.chunk;
        Uri r15 = r13.playlistUrl;
        if (r4 == false) goto L39;
        this.pendingResetPositionUs = -9223372036854775807L;
        this.loadingFinished = true;
        return true;
    L39:
        if (r52 != null) goto L44;
        if (r15 == null) goto L42;
        this.callback.onPlaylistRefreshRequired(r15);
    L42:
        return false;
    L44:
        if (isMediaChunk(r52) == false) goto L46;
        HlsMediaChunk r18 = (HlsMediaChunk) r52;
        maybeDiscardUpstreamForNewMediaChunk(r18);
        initMediaChunkLoad(r18);
    L46:
        this.loadingChunk = r52;
        this.loader.startLoading(r52, this, this.loadErrorHandlingPolicy.getMinimumLoadableRetryCount(r52.type));
        return true;
    L31:
        if (r16.isEmpty() == false) goto L34;
        r17 = false;
        goto L35
    L15:
        List<HlsMediaChunk> r19 = this.readOnlyMediaChunks;
        HlsMediaChunk r32 = getLastMediaChunk();
        if (r32.isLoadCompleted() == true) goto L18;
    L21:
        long r33 = Math.max(this.lastSeekPositionUs, r32.startTimeUs);
    L22:
        long r53 = this.lastSeekPositionUs;
        if (this.sampleQueuesBuilt == false) goto L27;
        HlsSampleQueue[] r72 = this.sampleQueues;
        int r8 = r72.length;
        int r9 = 0;
    L25:
        if (r9 >= r8) goto L27;
        r53 = Math.max(r53, r72[r9].getLargestReadTimestampUs());
        r9 = r9 + 1;
    L27:
        r16 = r19;
        r12 = r33;
        r14 = r53;
        goto L28
    L18:
        if (r32.isPublished() == false) goto L21;
        r33 = r32.getPublishedEndTimeUs();
        goto L22
    }

    public void continuePreparing() {
        if (this.prepared == true) goto L6;
        continueLoading(new LoadingInfo.Builder().setPlaybackPositionUs(this.lastSeekPositionUs).build());
        return;
    }

    public void discardBuffer(long r5, boolean r7) {
        if (this.sampleQueuesBuilt == true) goto L5;
        return;
    L5:
        if (isPendingReset() == true) goto L12;
        int r0 = this.sampleQueues.length;
        int r1 = 0;
    L8:
        if (r1 >= r0) goto L13;
        this.sampleQueues[r1].discardTo(r5, r7, this.sampleQueuesEnabledStates[r1]);
        r1 = r1 + 1;
        goto L8
    L13:
        return;
    }

    @Override // androidx.media3.extractor.ExtractorOutput
    public void endTracks() {
        this.tracksEnded = true;
        this.handler.post(this.onTracksEndedRunnable);
    }

    public long getAdjustedSeekPositionUs(long r1, SeekParameters r3) {
        return this.chunkSource.getAdjustedSeekPositionUs(r1, r3);
    }

    @Override // androidx.media3.exoplayer.source.SequenceableLoader
    public long getBufferedPositionUs() {
        if (this.loadingFinished == false) goto L7;
        return Long.MIN_VALUE;
    L7:
        if (isPendingReset() == true) goto L9;
        long r0 = this.lastSeekPositionUs;
        HlsMediaChunk r2 = getLastMediaChunk();
        if (r2.isLoadCompleted() == false) goto L14;
    L17:
        if (r2 == null) goto L20;
        r0 = Math.max(r0, r2.endTimeUs);
    L20:
        if (this.sampleQueuesBuilt == false) goto L24;
        HlsSampleQueue[] r6 = this.sampleQueues;
        int r22 = r6.length;
        int r3 = 0;
    L22:
        if (r3 >= r22) goto L24;
        r0 = Math.max(r0, r6[r3].getLargestQueuedTimestampUs());
        r3 = r3 + 1;
    L24:
        return r0;
    L14:
        if (this.mediaChunks.size() <= 1) goto L16;
        r2 = this.mediaChunks.get(r2.size() - 2);
        goto L17
    L16:
        r2 = null;
        goto L17
    L9:
        return this.pendingResetPositionUs;
    }

    @Override // androidx.media3.exoplayer.source.SequenceableLoader
    public long getNextLoadPositionUs() {
        if (isPendingReset() == false) goto L7;
        return this.pendingResetPositionUs;
    L7:
        if (this.loadingFinished == false) goto L11;
        return Long.MIN_VALUE;
    L11:
        return getLastMediaChunk().endTimeUs;
    }

    public TrackGroupArray getTrackGroups() {
        assertIsPrepared();
        return this.trackGroups;
    }

    @Override // androidx.media3.exoplayer.source.SequenceableLoader
    public boolean isLoading() {
        return this.loader.isLoading();
    }

    public boolean isReady(int r2) {
        if (isPendingReset() == false) goto L5;
        return false;
    L5:
        if (this.sampleQueues[r2].isReady(this.loadingFinished) == false) goto L10;
        return true;
    L10:
        return false;
    }

    public boolean isVideoSampleStream() {
        if (this.primarySampleQueueType != 2) goto L6;
        return true;
    L6:
        return false;
    }

    public void maybeThrowError() {
        this.loader.maybeThrowError();
        this.chunkSource.maybeThrowError();
    }

    public void maybeThrowError(int r1) {
        maybeThrowError();
        this.sampleQueues[r1].maybeThrowError();
    }

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

    public void onLoadCanceled(Chunk r14, long r15, long r17, boolean r19) {
        this.loadingChunk = null;
        LoadEventInfo r1 = new LoadEventInfo(r14.loadTaskId, r14.dataSpec, r14.getUri(), r14.getResponseHeaders(), r15, r17, r14.bytesLoaded());
        this.loadErrorHandlingPolicy.onLoadTaskConcluded(r14.loadTaskId);
        this.mediaSourceEventDispatcher.loadCanceled(r1, r14.type, this.trackType, r14.trackFormat, r14.trackSelectionReason, r14.trackSelectionData, r14.startTimeUs, r14.endTimeUs);
        if (r19 == false) goto L5;
        return;
    L5:
        if (isPendingReset() == false) goto L7;
    L8:
        resetSampleQueues();
    L10:
        if (this.enabledTrackGroupCount <= 0) goto L14;
        this.callback.onContinueLoadingRequested(this);
        return;
    L14:
        return;
    L7:
        if (this.enabledTrackGroupCount != 0) goto L10;
        goto L8
    }

    @Override // androidx.media3.exoplayer.upstream.Loader.Callback
    public /* bridge */ /* synthetic */ void onLoadCanceled(Loader.Loadable r1, long r2, long r4, boolean r6) {
        onLoadCanceled((Chunk) r1, r2, r4, r6);
    }

    public void onLoadCompleted(Chunk r14, long r15, long r17) {
        this.loadingChunk = null;
        this.chunkSource.onChunkLoadCompleted(r14);
        LoadEventInfo r1 = new LoadEventInfo(r14.loadTaskId, r14.dataSpec, r14.getUri(), r14.getResponseHeaders(), r15, r17, r14.bytesLoaded());
        this.loadErrorHandlingPolicy.onLoadTaskConcluded(r14.loadTaskId);
        this.mediaSourceEventDispatcher.loadCompleted(r1, r14.type, this.trackType, r14.trackFormat, r14.trackSelectionReason, r14.trackSelectionData, r14.startTimeUs, r14.endTimeUs);
        if (this.prepared == true) goto L6;
        continueLoading(new LoadingInfo.Builder().setPlaybackPositionUs(this.lastSeekPositionUs).build());
        return;
    L6:
        this.callback.onContinueLoadingRequested(this);
    }

    @Override // androidx.media3.exoplayer.upstream.Loader.Callback
    public /* bridge */ /* synthetic */ void onLoadCompleted(Loader.Loadable r1, long r2, long r4) {
        onLoadCompleted((Chunk) r1, r2, r4);
    }

    public Loader.LoadErrorAction onLoadError(Chunk r27, long r28, long r30, IOException r32, int r33) {
        boolean r2 = isMediaChunk(r27);
        if (r2 == true) goto L5;
    L14:
        long r24 = r27.bytesLoaded();
        LoadEventInfo r14 = new LoadEventInfo(r27.loadTaskId, r27.dataSpec, r27.getUri(), r27.getResponseHeaders(), r28, r30, r24);
        LoadErrorHandlingPolicy.LoadErrorInfo r4 = new LoadErrorHandlingPolicy.LoadErrorInfo(r14, new MediaLoadData(r27.type, this.trackType, r27.trackFormat, r27.trackSelectionReason, r27.trackSelectionData, Util.usToMs(r27.startTimeUs), Util.usToMs(r27.endTimeUs)), r32, r33);
        LoadErrorHandlingPolicy.FallbackSelection r3 = this.loadErrorHandlingPolicy.getFallbackSelectionFor(TrackSelectionUtil.createFallbackOptions(this.chunkSource.getTrackSelection()), r4);
        boolean r5 = false;
        if (r3 != null) goto L17;
    L19:
        boolean r15 = false;
    L20:
        if (r15 == false) goto L33;
        if (r2 == true) goto L23;
    L31:
        Loader.LoadErrorAction r22 = Loader.DONT_RETRY;
    L32:
        Loader.LoadErrorAction r16 = r22;
        boolean r17 = r16.isRetry();
        this.mediaSourceEventDispatcher.loadError(r14, r27.type, this.trackType, r27.trackFormat, r27.trackSelectionReason, r27.trackSelectionData, r27.startTimeUs, r27.endTimeUs, r32, !r17);
        if (r17 == true) goto L40;
        this.loadingChunk = null;
        this.loadErrorHandlingPolicy.onLoadTaskConcluded(r27.loadTaskId);
    L40:
        if (r15 == true) goto L42;
    L46:
        return r16;
    L42:
        if (this.prepared == true) goto L45;
        continueLoading(new LoadingInfo.Builder().setPlaybackPositionUs(this.lastSeekPositionUs).build());
        return r16;
    L45:
        this.callback.onContinueLoadingRequested(this);
        goto L46
    L23:
        if (r24 != 0) goto L31;
        ArrayList<HlsMediaChunk> r23 = this.mediaChunks;
        if (r23.remove(r23.size() - 1) != r27) goto L27;
        r5 = true;
    L27:
        Assertions.checkState(r5);
        if (this.mediaChunks.isEmpty() == false) goto L30;
        this.pendingResetPositionUs = this.lastSeekPositionUs;
        goto L31
    L30:
        ((HlsMediaChunk) Iterables.getLast(this.mediaChunks)).invalidateExtractor();
        goto L31
    L33:
        long r25 = this.loadErrorHandlingPolicy.getRetryDelayMsFor(r4);
        if (r25 == (-9223372036854775807L)) goto L36;
        r22 = Loader.createRetryAction(false, r25);
        goto L32
    L36:
        r22 = Loader.DONT_RETRY_FATAL;
        goto L32
    L17:
        if (r3.type != 2) goto L19;
        r15 = this.chunkSource.maybeExcludeTrack(r27, r3.exclusionDurationMs);
        goto L20
    L5:
        if (((HlsMediaChunk) r27).isPublished() == true) goto L14;
        if ((r32 instanceof HttpDataSource$InvalidResponseCodeException) == false) goto L14;
        int r34 = ((HttpDataSource$InvalidResponseCodeException) r32).responseCode;
        if (r34 == 410) goto L13;
        if (r34 != 404) goto L14;
    L13:
        return Loader.RETRY;
    }

    @Override // androidx.media3.exoplayer.upstream.Loader.Callback
    public /* bridge */ /* synthetic */ Loader.LoadErrorAction onLoadError(Loader.Loadable r1, long r2, long r4, IOException r6, int r7) {
        return onLoadError((Chunk) r1, r2, r4, r6, r7);
    }

    public void onLoadStarted(Chunk r17, long r18, long r20, int r22) {
        if (r22 != 0) goto L5;
        LoadEventInfo r5 = new LoadEventInfo(r17.loadTaskId, r17.dataSpec, r18);
    L6:
        this.mediaSourceEventDispatcher.loadStarted(r5, r17.type, this.trackType, r17.trackFormat, r17.trackSelectionReason, r17.trackSelectionData, r17.startTimeUs, r17.endTimeUs, r22);
        return;
    L5:
        r5 = new LoadEventInfo(r17.loadTaskId, r17.dataSpec, r17.getUri(), r17.getResponseHeaders(), r18, r20, r17.bytesLoaded());
        goto L6
    }

    @Override // androidx.media3.exoplayer.upstream.Loader.Callback
    public /* bridge */ /* synthetic */ void onLoadStarted(Loader.Loadable r1, long r2, long r4, int r6) {
        onLoadStarted((Chunk) r1, r2, r4, r6);
    }

    @Override // androidx.media3.exoplayer.upstream.Loader.ReleaseCallback
    public void onLoaderReleased() {
        HlsSampleQueue[] r3 = this.sampleQueues;
        int r0 = r3.length;
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L5;
        r3[r1].release();
        r1 = r1 + 1;
        goto L3
    }

    public void onNewExtractor() {
        this.sampleQueueMappingDoneByType.clear();
    }

    public boolean onPlaylistError(Uri r2, LoadErrorHandlingPolicy.LoadErrorInfo r3, boolean r4) {
        if (this.chunkSource.obtainsChunksForPlaylist(r2) == true) goto L6;
        return true;
    L6:
        if (r4 == true) goto L12;
        LoadErrorHandlingPolicy.FallbackSelection r32 = this.loadErrorHandlingPolicy.getFallbackSelectionFor(TrackSelectionUtil.createFallbackOptions(this.chunkSource.getTrackSelection()), r3);
        if (r32 == null) goto L12;
        if (r32.type != 2) goto L12;
        long r33 = r32.exclusionDurationMs;
    L14:
        return this.chunkSource.onPlaylistError(r2, r33);
    L12:
        r33 = -9223372036854775807L;
        goto L14
    }

    public void onPlaylistUpdated() {
        if (this.mediaChunks.isEmpty() == true) goto L26;
        HlsMediaChunk r0 = (HlsMediaChunk) Iterables.getLast(this.mediaChunks);
        int r1 = this.chunkSource.getChunkPublicationState(r0);
        if (r1 == 1) goto L8;
        if (r1 != 0) goto L15;
        this.handler.post(new HlsSampleStreamWrapper$$ExternalSyntheticLambda0(this, r0));
        return;
    L15:
        if (r1 == 2) goto L17;
        return;
    L17:
        if (this.loadingFinished == false) goto L19;
        return;
    L19:
        if (this.loader.isLoading() == false) goto L25;
        this.loader.cancelLoading();
        return;
    L25:
        return;
    L8:
        if (r0.isPublished() == true) goto L22;
        r0.publish(this.chunkSource.getPublishedPartDurationUs(r0));
        return;
    L22:
        return;
    }

    @Override // androidx.media3.exoplayer.source.SampleQueue.UpstreamFormatChangedListener
    public void onUpstreamFormatChanged(Format r1) {
        this.handler.post(this.maybeFinishPrepareRunnable);
    }

    public void prepareWithMultivariantPlaylistInfo(TrackGroup[] r5, int r6, int... r7) {
        this.trackGroups = createTrackGroupArrayWithDrmInfo(r5);
        this.optionalTrackGroups = new HashSet();
        int r52 = r7.length;
        int r0 = 0;
    L3:
        if (r0 >= r52) goto L5;
        int r1 = r7[r0];
        this.optionalTrackGroups.add(this.trackGroups.get(r1));
        r0 = r0 + 1;
        goto L3
    L5:
        this.primaryTrackGroupIndex = r6;
        Handler r53 = this.handler;
        Callback r62 = this.callback;
        Objects.requireNonNull(r62);
        r53.post(new HlsSampleStreamWrapper$$ExternalSyntheticLambda3(r62));
        setIsPrepared();
    }

    public int readData(int r11, FormatHolder r12, DecoderInputBuffer r13, int r14) {
        if (isPendingReset() == false) goto L5;
        return -3;
    L5:
        int r2 = 0;
        if (this.mediaChunks.isEmpty() == true) goto L18;
        int r0 = 0;
    L9:
        if (r0 >= (this.mediaChunks.size() - 1)) goto L13;
        if (finishedReadingChunk(this.mediaChunks.get(r0)) == false) goto L13;
        r0 = r0 + 1;
    L13:
        Util.removeRange(this.mediaChunks, 0, r0);
        HlsMediaChunk r02 = this.mediaChunks.get(0);
        Format r5 = r02.trackFormat;
        if (r5.equals(this.downstreamTrackFormat) == true) goto L16;
        this.mediaSourceEventDispatcher.downstreamFormatChanged(this.trackType, r5, r02.trackSelectionReason, r02.trackSelectionData, r02.startTimeUs);
    L16:
        this.downstreamTrackFormat = r5;
    L18:
        if (this.mediaChunks.isEmpty() == false) goto L20;
    L22:
        int r132 = this.sampleQueues[r11].read(r12, r13, r14, this.loadingFinished);
        if (r132 != (-5)) goto L38;
        Format r142 = (Format) Assertions.checkNotNull(r12.format);
        if (r11 != this.primarySampleQueueIndex) goto L37;
        int r112 = Ints.checkedCast(this.sampleQueues[r11].peekSourceId());
    L28:
        if (r2 >= this.mediaChunks.size()) goto L33;
        if (this.mediaChunks.get(r2).uid == r112) goto L33;
        r2 = r2 + 1;
    L33:
        if (r2 >= this.mediaChunks.size()) goto L35;
        Format r10 = this.mediaChunks.get(r2).trackFormat;
    L36:
        r142 = r142.withManifestFormatInfo(r10);
        goto L37
    L35:
        r10 = (Format) Assertions.checkNotNull(this.upstreamTrackFormat);
    L37:
        r12.format = r142;
    L38:
        return r132;
    L20:
        if (this.mediaChunks.get(0).isPublished() == true) goto L22;
        return -3;
    }

    @Override // androidx.media3.exoplayer.source.SequenceableLoader
    public void reevaluateBuffer(long r5) {
        if (this.loader.hasFatalError() == false) goto L5;
        return;
    L5:
        if (isPendingReset() == false) goto L8;
        return;
    L8:
        if (this.loader.isLoading() == false) goto L13;
        Assertions.checkNotNull(this.loadingChunk);
        if (this.chunkSource.shouldCancelLoad(r5, this.loadingChunk, this.readOnlyMediaChunks) == false) goto L28;
        this.loader.cancelLoading();
        return;
    L28:
        return;
    L13:
        int r0 = this.readOnlyMediaChunks.size();
    L14:
        if (r0 <= 0) goto L19;
        if (this.chunkSource.getChunkPublicationState(this.readOnlyMediaChunks.get(r0 - 1)) != 2) goto L19;
        r0 = r0 - 1;
    L19:
        if (r0 >= this.readOnlyMediaChunks.size()) goto L21;
        discardUpstream(r0);
    L21:
        int r52 = this.chunkSource.getPreferredQueueSize(r5, this.readOnlyMediaChunks);
        if (r52 >= this.mediaChunks.size()) goto L29;
        discardUpstream(r52);
        return;
    }

    public void release() {
        if (this.prepared == false) goto L7;
        HlsSampleQueue[] r0 = this.sampleQueues;
        int r1 = r0.length;
        int r2 = 0;
    L5:
        if (r2 >= r1) goto L7;
        r0[r2].preRelease();
        r2 = r2 + 1;
    L7:
        this.chunkSource.reset();
        this.loader.release(this);
        this.handler.removeCallbacksAndMessages(null);
        this.released = true;
        this.hlsSampleStreams.clear();
    }

    @Override // androidx.media3.extractor.ExtractorOutput
    public void seekMap(SeekMap r1) {
    }

    public boolean seekToUs(long r7, boolean r9) {
        this.lastSeekPositionUs = r7;
        if (isPendingReset() == false) goto L6;
        this.pendingResetPositionUs = r7;
        return true;
    L6:
        int r2 = 0;
        if (this.chunkSource.hasIndependentSegments() == false) goto L15;
        int r0 = 0;
    L10:
        if (r0 >= this.mediaChunks.size()) goto L15;
        HlsMediaChunk r3 = this.mediaChunks.get(r0);
        if (r3.startTimeUs == r7) goto L17;
        r0 = r0 + 1;
    L17:
        if (this.sampleQueuesBuilt == false) goto L24;
        if (r9 == true) goto L24;
        if (this.mediaChunks.isEmpty() == true) goto L24;
        if (seekInsideBufferUs(r7, r3) == false) goto L24;
        return false;
    L24:
        this.pendingResetPositionUs = r7;
        this.loadingFinished = false;
        this.mediaChunks.clear();
        if (this.loader.isLoading() == true) goto L27;
        this.loader.clearFatalError();
        resetSampleQueues();
    L33:
        return true;
    L27:
        if (this.sampleQueuesBuilt == false) goto L31;
        HlsSampleQueue[] r72 = this.sampleQueues;
        int r8 = r72.length;
    L29:
        if (r2 >= r8) goto L31;
        r72[r2].discardToEnd();
        r2 = r2 + 1;
    L31:
        this.loader.cancelLoading();
    L15:
        r3 = null;
        goto L17
    }

    public boolean selectTracks(ExoTrackSelection[] r14, boolean[] r15, SampleStream[] r16, boolean[] r17, long r18, boolean r20) {
        assertIsPrepared();
        int r1 = this.enabledTrackGroupCount;
        int r10 = 0;
        int r4 = 0;
    L4:
        if (r4 >= r14.length) goto L13;
        HlsSampleStream r5 = (HlsSampleStream) r16[r4];
        if (r5 == null) goto L12;
        if (r14[r4] != null) goto L10;
    L11:
        this.enabledTrackGroupCount--;
        r5.unbindSampleQueue();
        r16[r4] = null;
        goto L12
    L10:
        if (r15[r4] == false) goto L11;
    L12:
        r4 = r4 + 1;
        goto L4
    L13:
        if (r20 == false) goto L15;
    L22:
        boolean r12 = true;
    L23:
        ExoTrackSelection r42 = this.chunkSource.getTrackSelection();
        boolean r122 = r12;
        ExoTrackSelection r52 = r42;
        int r13 = 0;
    L25:
        if (r13 >= r14.length) goto L47;
        ExoTrackSelection r7 = r14[r13];
        if (r7 == null) goto L45;
        int r8 = this.trackGroups.indexOf(r7.getTrackGroup());
        if (r8 != this.primaryTrackGroupIndex) goto L33;
        this.chunkSource.setTrackSelection(r7);
        r52 = r7;
    L33:
        if (r16[r13] != null) goto L45;
        this.enabledTrackGroupCount++;
        HlsSampleStream r72 = new HlsSampleStream(this, r8);
        r16[r13] = r72;
        r17[r13] = true;
        if (this.trackGroupToSampleQueueIndex == null) goto L45;
        r72.bindSampleQueue();
        if (r122 == true) goto L45;
        HlsSampleQueue r73 = this.sampleQueues[this.trackGroupToSampleQueueIndex[r8]];
        if (r73.getReadIndex() != 0) goto L41;
    L43:
        boolean r74 = false;
    L44:
        r122 = r74;
        goto L45
    L41:
        if (r73.seekTo(r18, true) == true) goto L43;
        r74 = true;
    L45:
        r13 = r13 + 1;
        goto L25
    L47:
        if (this.enabledTrackGroupCount != 0) goto L58;
        this.chunkSource.reset();
        this.downstreamTrackFormat = null;
        this.pendingResetUpstreamFormats = true;
        this.mediaChunks.clear();
        if (this.loader.isLoading() == true) goto L51;
        resetSampleQueues();
    L78:
        updateSampleStreams(r16);
        this.seenFirstTrackSelection = true;
        return r122;
    L51:
        if (this.sampleQueuesBuilt == false) goto L55;
        HlsSampleQueue[] r142 = this.sampleQueues;
        int r19 = r142.length;
    L53:
        if (r10 >= r19) goto L55;
        r142[r10].discardToEnd();
        r10 = r10 + 1;
    L55:
        this.loader.cancelLoading();
        goto L78
    L58:
        if (this.mediaChunks.isEmpty() == false) goto L60;
    L69:
        boolean r143 = r20;
    L70:
        if (r122 == false) goto L78;
        seekToUs(r18, r143);
    L73:
        if (r10 >= r16.length) goto L78;
        if (r16[r10] == null) goto L77;
        r17[r10] = true;
    L77:
        r10 = r10 + 1;
        goto L73
    L60:
        if (Objects.equals(r52, r42) == true) goto L69;
        if (this.seenFirstTrackSelection == true) goto L68;
        long r6 = 0;
        if (r18 >= 0) goto L66;
        r6 = -r18;
    L66:
        HlsMediaChunk r144 = getLastMediaChunk();
        ExoTrackSelection r110 = r52;
        r110.updateSelectedTrack(r18, r6, -9223372036854775807L, this.readOnlyMediaChunks, this.chunkSource.createMediaChunkIterators(r144, r18));
        if (r110.getSelectedIndexInTrackGroup() == this.chunkSource.getTrackGroup().indexOf(r144.trackFormat)) goto L69;
    L68:
        this.pendingResetUpstreamFormats = true;
        r143 = true;
        r122 = true;
        goto L70
    L15:
        if (this.seenFirstTrackSelection == false) goto L19;
        if (r1 == 0) goto L22;
    L21:
        r12 = false;
        goto L23
    L19:
        if (r18 == this.lastSeekPositionUs) goto L21;
        goto L21
    }

    public void setDrmInitData(DrmInitData r4) {
        if (Objects.equals(this.drmInitData, r4) == true) goto L11;
        this.drmInitData = r4;
        int r0 = 0;
    L5:
        HlsSampleQueue[] r1 = this.sampleQueues;
        if (r0 >= r1.length) goto L15;
        if (this.sampleQueueIsAudioVideoFlags[r0] == false) goto L10;
        r1[r0].setDrmInitData(r4);
    L10:
        r0 = r0 + 1;
        goto L5
    L15:
        return;
    }

    public void setIsPrimaryTimestampSource(boolean r1) {
        this.chunkSource.setIsPrimaryTimestampSource(r1);
    }

    public void setSampleOffsetUs(long r4) {
        if (this.sampleOffsetUs == r4) goto L7;
        this.sampleOffsetUs = r4;
        HlsSampleQueue[] r3 = this.sampleQueues;
        int r0 = r3.length;
        int r1 = 0;
    L5:
        if (r1 >= r0) goto L9;
        r3[r1].setSampleOffsetUs(r4);
        r1 = r1 + 1;
        goto L5
    L9:
        return;
    }

    public int skipData(int r3, long r4) {
        if (isPendingReset() == false) goto L6;
        return 0;
    L6:
        HlsSampleQueue r0 = this.sampleQueues[r3];
        int r42 = r0.getSkipCount(r4, this.loadingFinished);
        HlsMediaChunk r2 = (HlsMediaChunk) Iterables.getLast(this.mediaChunks, null);
        if (r2 != null) goto L9;
    L11:
        r0.skip(r42);
        return r42;
    L9:
        if (r2.isPublished() == true) goto L11;
        int r5 = r0.getReadIndex();
        r42 = Math.min(r42, r2.getFirstSampleIndex(r3) - r5);
        goto L11
    }

    @Override // androidx.media3.extractor.ExtractorOutput
    public TrackOutput track(int r4, int r5) {
        if (MAPPABLE_TYPES.contains(Integer.valueOf(r5)) == false) goto L5;
        TrackOutput r0 = getMappedTrackOutput(r4, r5);
    L13:
        if (r0 != null) goto L20;
        if (this.tracksEnded == true) goto L17;
        r0 = createSampleQueue(r4, r5);
        goto L20
    L17:
        return createDiscardingTrackOutput(r4, r5);
    L20:
        if (r5 == 5) goto L22;
        return r0;
    L22:
        if (this.emsgUnwrappingTrackOutput != null) goto L25;
        this.emsgUnwrappingTrackOutput = new EmsgUnwrappingTrackOutput(r0, this.metadataType);
    L25:
        return this.emsgUnwrappingTrackOutput;
    L5:
        int r02 = 0;
    L6:
        TrackOutput[] r1 = this.sampleQueues;
        if (r02 >= r1.length) goto L12;
        if (this.sampleQueueTrackIds[r02] == r4) goto L10;
        r02 = r02 + 1;
        goto L6
    L10:
        r0 = r1[r02];
        goto L13
    L12:
        r0 = null;
        goto L13
    }

    public void unbindSampleQueue(int r2) {
        assertIsPrepared();
        Assertions.checkNotNull(this.trackGroupToSampleQueueIndex);
        int r22 = this.trackGroupToSampleQueueIndex[r2];
        Assertions.checkState(this.sampleQueuesEnabledStates[r22]);
        this.sampleQueuesEnabledStates[r22] = false;
    }
}
