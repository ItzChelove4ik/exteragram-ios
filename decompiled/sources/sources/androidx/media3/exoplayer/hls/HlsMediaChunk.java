package androidx.media3.exoplayer.hls;

import android.net.Uri;
import androidx.media3.common.DrmInitData;
import androidx.media3.common.Format;
import androidx.media3.common.Metadata;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.TimestampAdjuster;
import androidx.media3.common.util.UriUtil;
import androidx.media3.datasource.DataSource;
import androidx.media3.datasource.DataSourceUtil;
import androidx.media3.datasource.DataSpec;
import androidx.media3.exoplayer.analytics.PlayerId;
import androidx.media3.exoplayer.hls.HlsChunkSource;
import androidx.media3.exoplayer.hls.playlist.HlsMediaPlaylist;
import androidx.media3.exoplayer.source.chunk.MediaChunk;
import androidx.media3.exoplayer.upstream.CmcdData;
import androidx.media3.extractor.DefaultExtractorInput;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.metadata.id3.Id3Decoder;
import androidx.media3.extractor.metadata.id3.PrivFrame;
import com.google.common.base.Ascii;
import com.google.common.collect.ImmutableList;
import java.io.EOFException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.math.BigInteger;
import java.util.List;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

/* loaded from: classes4.dex */
final class HlsMediaChunk extends MediaChunk {
    private static final AtomicInteger uidSource = null;
    public final int discontinuitySequenceNumber;
    private final DrmInitData drmInitData;
    private HlsMediaChunkExtractor extractor;
    private final HlsExtractorFactory extractorFactory;
    private boolean extractorInvalidated;
    private final boolean hasGapTag;
    private final Id3Decoder id3Decoder;
    private boolean initDataLoadRequired;
    private final DataSource initDataSource;
    private final DataSpec initDataSpec;
    private final boolean initSegmentEncrypted;
    public final boolean isIndependent;
    private final boolean isPrimaryTimestampSource;
    private volatile boolean loadCanceled;
    private boolean loadCompleted;
    private final boolean mediaSegmentEncrypted;
    private final List<Format> muxedCaptionFormats;
    private int nextLoadPosition;
    private HlsSampleStreamWrapper output;
    public final int partIndex;
    private final PlayerId playerId;
    public final Uri playlistUrl;
    private final HlsMediaChunkExtractor previousExtractor;
    private long publishedDurationUs;
    private ImmutableList<Integer> sampleQueueFirstSampleIndices;
    private final ParsableByteArray scratchId3Data;
    private boolean shouldSpliceIn;
    private final TimestampAdjuster timestampAdjuster;
    private final long timestampAdjusterInitializationTimeoutMs;
    public final int uid;

    static {
        uidSource = new AtomicInteger();
    }

    private HlsMediaChunk(HlsExtractorFactory r14, DataSource r15, DataSpec r16, Format r17, boolean r18, DataSource r19, DataSpec r20, boolean r21, Uri r22, List<Format> r23, int r24, Object r25, long r26, long r28, long r30, int r32, boolean r33, int r34, boolean r35, boolean r36, TimestampAdjuster r37, long r38, DrmInitData r40, HlsMediaChunkExtractor r41, Id3Decoder r42, ParsableByteArray r43, boolean r44, boolean r45, PlayerId r46) {
        super(r15, r16, r17, r24, r25, r26, r28, r30);
        this.mediaSegmentEncrypted = r18;
        this.partIndex = r32;
        if (r33 == false) goto L5;
        long r2 = r28 - r26;
    L6:
        this.publishedDurationUs = r2;
        this.discontinuitySequenceNumber = r34;
        this.initDataSpec = r20;
        this.initDataSource = r19;
        if (r20 == null) goto L9;
        boolean r152 = true;
    L10:
        this.initDataLoadRequired = r152;
        this.initSegmentEncrypted = r21;
        this.playlistUrl = r22;
        this.isPrimaryTimestampSource = r36;
        this.timestampAdjuster = r37;
        this.timestampAdjusterInitializationTimeoutMs = r38;
        this.hasGapTag = r35;
        this.extractorFactory = r14;
        this.muxedCaptionFormats = r23;
        this.drmInitData = r40;
        this.previousExtractor = r41;
        this.id3Decoder = r42;
        this.scratchId3Data = r43;
        this.shouldSpliceIn = r44;
        this.isIndependent = r45;
        this.playerId = r46;
        this.sampleQueueFirstSampleIndices = ImmutableList.of();
        this.uid = uidSource.getAndIncrement();
        return;
    L9:
        r152 = false;
        goto L10
    L5:
        r2 = -9223372036854775807L;
        goto L6
    }

    private static DataSource buildDataSource(DataSource r1, byte[] r2, byte[] r3) {
        if (r2 == null) goto L5;
        Assertions.checkNotNull(r3);
        return new Aes128DataSource(r1, r2, r3);
    L5:
        return r1;
    }

    public static HlsMediaChunk createInstance(HlsExtractorFactory r44, DataSource r45, Format r46, long r47, HlsMediaPlaylist r49, HlsChunkSource.SegmentBaseHolder r50, Uri r51, List<Format> r52, int r53, Object r54, boolean r55, TimestampAdjusterProvider r56, long r57, HlsMediaChunk r59, byte[] r60, byte[] r61, boolean r62, boolean r63, PlayerId r64, CmcdData.Factory r65) {
        HlsMediaPlaylist.SegmentBase r6 = r50.segmentBase;
        DataSpec.Builder r7 = new DataSpec.Builder().setUri(UriUtil.resolveToUri(r49.baseUri, r6.url)).setPosition(r6.byteRangeOffset).setLength(r6.byteRangeLength);
        if (r50.isPreload == false) goto L5;
        int r8 = 8;
    L6:
        DataSpec r13 = r7.setFlags(r8).build();
        if (r60 == null) goto L9;
        boolean r15 = true;
    L10:
        if (r15 == false) goto L12;
        byte[] r10 = getEncryptionIvArray((String) Assertions.checkNotNull(r6.encryptionIV));
    L13:
        DataSource r12 = buildDataSource(r45, r60, r10);
        HlsMediaPlaylist.Segment r4 = r6.initializationSegment;
        if (r4 == null) goto L22;
        if (r61 == null) goto L17;
        boolean r102 = true;
    L18:
        if (r102 == false) goto L20;
        byte[] r11 = getEncryptionIvArray((String) Assertions.checkNotNull(r4.encryptionIV));
    L21:
        boolean r652 = true;
        DataSpec r42 = new DataSpec.Builder().setUri(UriUtil.resolveToUri(r49.baseUri, r4.url)).setPosition(r4.byteRangeOffset).setLength(r4.byteRangeLength).build();
        DataSource r0 = buildDataSource(r45, r61, r11);
        boolean r18 = r102;
    L23:
        long r23 = r47 + r6.relativeStartTimeUs;
        long r25 = r23 + r6.durationUs;
        int r1 = r49.discontinuitySequence + r6.relativeDiscontinuitySequence;
        if (r59 == null) goto L53;
        DataSpec r5 = r59.initDataSpec;
        if (r42 == r5) goto L35;
        if (r42 == null) goto L34;
        if (r5 == null) goto L34;
        if (r42.uri.equals(r5.uri) == false) goto L34;
        if (r42.position == r59.initDataSpec.position) goto L35;
    L34:
        boolean r58 = false;
    L36:
        Uri r82 = r51;
        if (r82.equals(r59.playlistUrl) == true) goto L39;
    L41:
        boolean r9 = false;
    L42:
        Id3Decoder r72 = r59.id3Decoder;
        ParsableByteArray r103 = r59.scratchId3Data;
        if (r58 == false) goto L50;
        if (r9 == false) goto L50;
        if (r59.extractorInvalidated == true) goto L50;
        if (r59.discontinuitySequenceNumber != r1) goto L50;
        HlsMediaChunkExtractor r16 = r59.extractor;
    L51:
        HlsMediaChunkExtractor r38 = r16;
    L55:
        return new HlsMediaChunk(r44, r12, r13, r46, r15, r0, r42, r18, r82, r52, r53, r54, r23, r25, r50.mediaSequence, r50.partIndex, !r50.isPreload, r1, r6.hasGapTag, r55, r56.getAdjuster(r1), r57, r6.drmInitData, r38, r72, r103, r62, r63, r64);
    L50:
        r16 = null;
        goto L51
    L39:
        if (r59.loadCompleted == false) goto L41;
        r9 = r652;
    L35:
        r58 = r652;
        goto L36
    L53:
        r82 = r51;
        r72 = new Id3Decoder();
        r103 = new ParsableByteArray(10);
        r38 = null;
        goto L55
    L20:
        r11 = null;
        goto L21
    L17:
        r102 = false;
        goto L18
    L22:
        r652 = true;
        r0 = null;
        r42 = null;
        r18 = false;
        goto L23
    L12:
        r10 = null;
        goto L13
    L9:
        r15 = false;
        goto L10
    L5:
        r8 = 0;
        goto L6
    }

    @RequiresNonNull({"output"})
    private void feedDataToExtractor(DataSource r5, DataSpec r6, boolean r7, boolean r8) {
        int r0 = this.nextLoadPosition;
        boolean r1 = false;
        if (r7 == false) goto L7;
        if (r0 == 0) goto L6;
        r1 = true;
    L6:
        DataSpec r72 = r6;
    L41:
        DefaultExtractorInput r73 = prepareExtraction(r5, r72, r8);     // Catch: Throwable -> L12 Exception -> L38
        if (r1 == false) goto L39;
        r73.skipFully(this.nextLoadPosition);     // Catch: Throwable -> L12 Exception -> L38
    L39:
        if (this.loadCanceled == true) goto L23;
        if (this.extractor.read(r73) == true) goto L39;
    L23:
        long r74 = r73.getPosition();     // Catch: Throwable -> L12 Exception -> L38
    L24:
        this.nextLoadPosition = (int) (r74 - r6.position);     // Catch: Throwable -> L12 Exception -> L38
        goto L31
    L21:
        e = move-exception;
        if ((this.trackFormat.roleFlags & 16384) == 0) goto L32;
        this.extractor.onTruncatedSegmentParsed();     // Catch: Throwable -> L19
        r74 = r73.getPosition();     // Catch: Throwable -> L12 Exception -> L38
        goto L24
    L32:
        throw e;     // Catch: Throwable -> L19
    L19:
        th = move-exception;
        this.nextLoadPosition = (int) (r73.getPosition() - r6.position);     // Catch: Throwable -> L12 Exception -> L38
        throw th;     // Catch: Throwable -> L12 Exception -> L38
    L12:
        th = move-exception;
        DataSourceUtil.closeQuietly(r5);
        throw th;
    L31:
        DataSourceUtil.closeQuietly(r5);
        return;
    L7:
        r72 = r6.subrange(r0);
        goto L41
    }

    private static byte[] getEncryptionIvArray(String r4) {
        if (Ascii.toLowerCase(r4).startsWith("0x") == false) goto L5;
        r4 = r4.substring(2);
    L5:
        byte[] r42 = new BigInteger(r4, 16).toByteArray();
        byte[] r0 = new byte[16];
        if (r42.length <= 16) goto L8;
        int r2 = r42.length - 16;
    L9:
        System.arraycopy(r42, r2, r0, (16 - r42.length) + r2, r42.length - r2);
        return r0;
    L8:
        r2 = 0;
        goto L9
    }

    @RequiresNonNull({"output"})
    private void loadMedia() {
        feedDataToExtractor(this.dataSource, this.dataSpec, this.mediaSegmentEncrypted, true);
    }

    @RequiresNonNull({"output"})
    private void maybeLoadInitData() {
        if (this.initDataLoadRequired == true) goto L5;
        return;
    L5:
        Assertions.checkNotNull(this.initDataSource);
        Assertions.checkNotNull(this.initDataSpec);
        feedDataToExtractor(this.initDataSource, this.initDataSpec, this.initSegmentEncrypted, true);
        this.nextLoadPosition = 0;
        this.initDataLoadRequired = false;
    }

    private long peekId3PrivTimestamp(ExtractorInput r9) {
        r9.resetPeekPosition();
        this.scratchId3Data.reset(10);     // Catch: EOFException -> L23
        r9.peekFully(this.scratchId3Data.getData(), 0, 10);     // Catch: EOFException -> L23
        if (this.scratchId3Data.readUnsignedInt24() == 4801587) goto L7;
        return -9223372036854775807L;
    L7:
        this.scratchId3Data.skipBytes(3);
        int r2 = this.scratchId3Data.readSynchSafeInt();
        int r5 = r2 + 10;
        if (r5 <= this.scratchId3Data.capacity()) goto L10;
        byte[] r6 = this.scratchId3Data.getData();
        this.scratchId3Data.reset(r5);
        System.arraycopy(r6, 0, this.scratchId3Data.getData(), 0, 10);
    L10:
        r9.peekFully(this.scratchId3Data.getData(), 10, r2);
        Metadata r92 = this.id3Decoder.decode(this.scratchId3Data.getData(), r2);
        if (r92 != null) goto L13;
        return -9223372036854775807L;
    L13:
        int r22 = r92.length();
        int r3 = 0;
    L14:
        if (r3 >= r22) goto L22;
        Metadata.Entry r52 = r92.get(r3);
        if ((r52 instanceof PrivFrame) == false) goto L21;
        PrivFrame r53 = (PrivFrame) r52;
        if ("com.apple.streaming.transportStreamTimestamp".equals(r53.owner) == false) goto L21;
        System.arraycopy(r53.privateData, 0, this.scratchId3Data.getData(), 0, 8);
        this.scratchId3Data.setPosition(0);
        this.scratchId3Data.setLimit(8);
        return this.scratchId3Data.readLong() & 8589934591L;
    L21:
        r3 = r3 + 1;
    L22:
        return -9223372036854775807L;
    }

    @EnsuresNonNull({"extractor"})
    @RequiresNonNull({"output"})
    private DefaultExtractorInput prepareExtraction(DataSource r13, DataSpec r14, boolean r15) {
        long r4 = r13.open(r14);
        if (r15 == true) goto L28;
    L11:
        DefaultExtractorInput r0 = new DefaultExtractorInput(r13, r14.position, r4);
        if (this.extractor != null) goto L26;
        long r8 = peekId3PrivTimestamp(r0);
        r0.resetPeekPosition();
        HlsMediaChunkExtractor r132 = this.previousExtractor;
        if (r132 == null) goto L16;
        HlsMediaChunkExtractor r133 = r132.recreate();
    L17:
        this.extractor = r133;
        boolean r134 = r133.isPackedAudioExtractor();
        HlsSampleStreamWrapper r142 = this.output;
        if (r134 == true) goto L20;
        r142.setSampleOffsetUs(0);
    L25:
        this.output.onNewExtractor();
        this.extractor.init(this.output);
        goto L26
    L20:
        if (r8 == (-9223372036854775807L)) goto L22;
        long r1 = this.timestampAdjuster.adjustTsTimestamp(r8);
    L23:
        r142.setSampleOffsetUs(r1);
        goto L25
    L22:
        r1 = this.startTimeUs;
        goto L23
    L16:
        r133 = this.extractorFactory.createExtractor(r14.uri, this.trackFormat, this.muxedCaptionFormats, this.timestampAdjuster, r13.getResponseHeaders(), r0, this.playerId);
        r0 = r0;
    L26:
        this.output.setDrmInitData(this.drmInitData);
        return r0;
    L28:
        this.timestampAdjuster.sharedInitializeOrWait(this.isPrimaryTimestampSource, this.startTimeUs, this.timestampAdjusterInitializationTimeoutMs);     // Catch: TimeoutException -> L6 InterruptedException -> L9
    L6:
        e = move-exception;
        throw new IOException(e);
    L10:
        throw new InterruptedIOException();
    }

    public static boolean shouldSpliceIn(HlsMediaChunk r3, long r4, Uri r6, boolean r7, HlsChunkSource.SegmentBaseHolder r8, long r9) {
        if (r3 != null) goto L6;
        return false;
    L6:
        if (r6.equals(r3.playlistUrl) == true) goto L8;
    L10:
        long r92 = r9 + r8.segmentBase.relativeStartTimeUs;
        if (r7 == true) goto L13;
        return true;
    L13:
        if (r92 < r4) goto L18;
        return false;
    L18:
        return true;
    L8:
        if (r3.loadCompleted == false) goto L10;
        return false;
    }

    @Override // androidx.media3.exoplayer.upstream.Loader.Loadable
    public void cancelLoad() {
        this.loadCanceled = true;
    }

    public void clearShouldSpliceIn() {
        this.shouldSpliceIn = false;
    }

    public int getFirstSampleIndex(int r2) {
        Assertions.checkState(!this.shouldSpliceIn);
        if (r2 < this.sampleQueueFirstSampleIndices.size()) goto L7;
        return 0;
    L7:
        return this.sampleQueueFirstSampleIndices.get(r2).intValue();
    }

    public long getPublishedEndTimeUs() {
        long r0 = this.publishedDurationUs;
        if (r0 != (-9223372036854775807L)) goto L5;
        return -9223372036854775807L;
    L5:
        return this.startTimeUs + r0;
    }

    public void init(HlsSampleStreamWrapper r1, ImmutableList<Integer> r2) {
        this.output = r1;
        this.sampleQueueFirstSampleIndices = r2;
    }

    public void invalidateExtractor() {
        this.extractorInvalidated = true;
    }

    @Override // androidx.media3.exoplayer.source.chunk.MediaChunk
    public boolean isLoadCompleted() {
        return this.loadCompleted;
    }

    public boolean isPublished() {
        if (this.publishedDurationUs == (-9223372036854775807L)) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // androidx.media3.exoplayer.upstream.Loader.Loadable
    public void load() {
        Assertions.checkNotNull(this.output);
        if (this.extractor != null) goto L9;
        HlsMediaChunkExtractor r0 = this.previousExtractor;
        if (r0 == null) goto L9;
        if (r0.isReusable() == false) goto L9;
        this.extractor = this.previousExtractor;
        this.initDataLoadRequired = false;
    L9:
        maybeLoadInitData();
        if (this.loadCanceled == false) goto L12;
        return;
    L12:
        if (this.hasGapTag == true) goto L14;
        loadMedia();
    L14:
        this.loadCompleted = !this.loadCanceled;
    }

    public void publish(long r1) {
        this.publishedDurationUs = r1;
    }

    public boolean shouldSpliceIn() {
        return this.shouldSpliceIn;
    }
}
