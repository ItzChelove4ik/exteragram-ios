package androidx.media3.extractor.flac;

import androidx.media3.common.Format;
import androidx.media3.common.Metadata;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.Extractor;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.ExtractorsFactory;
import androidx.media3.extractor.FlacFrameReader;
import androidx.media3.extractor.FlacMetadataReader;
import androidx.media3.extractor.FlacSeekTableSeekMap;
import androidx.media3.extractor.FlacStreamMetadata;
import androidx.media3.extractor.PositionHolder;
import androidx.media3.extractor.SeekMap;
import androidx.media3.extractor.TrackOutput;
import org.mvel2.asm.MethodWriter$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
public final class FlacExtractor implements Extractor {
    public static final ExtractorsFactory FACTORY = null;
    private FlacBinarySearchSeeker binarySearchSeeker;
    private final ParsableByteArray buffer;
    private int currentFrameBytesWritten;
    private long currentFrameFirstSampleNumber;
    private ExtractorOutput extractorOutput;
    private FlacStreamMetadata flacStreamMetadata;
    private int frameStartMarker;
    private Metadata id3Metadata;
    private final boolean id3MetadataDisabled;
    private int minFrameSize;
    private final FlacFrameReader.SampleNumberHolder sampleNumberHolder;
    private int state;
    private final byte[] streamMarkerAndInfoBlock;
    private TrackOutput trackOutput;

    public static /* synthetic */ Extractor[] $r8$lambda$WFdhFS2w9xcH8MtglVnuJ0Dti4M() {
        return new Extractor[]{new FlacExtractor()};
    }

    static {
        FACTORY = new FlacExtractor$$ExternalSyntheticLambda0();
    }

    public FlacExtractor() {
        this(0);
    }

    public FlacExtractor(int r4) {
        this.streamMarkerAndInfoBlock = new byte[42];
        this.buffer = new ParsableByteArray(new byte[32768], 0);
        boolean r0 = true;
        if ((r4 & 1) != 0) goto L6;
        r0 = false;
    L6:
        this.id3MetadataDisabled = r0;
        this.sampleNumberHolder = new FlacFrameReader.SampleNumberHolder();
        this.state = 0;
    }

    private long findFrame(ParsableByteArray r5, boolean r6) {
        Assertions.checkNotNull(this.flacStreamMetadata);
        int r0 = r5.getPosition();
    L4:
        if (r0 > (r5.limit() - 16)) goto L10;
        r5.setPosition(r0);
        if (FlacFrameReader.checkAndReadFrameHeader(r5, this.flacStreamMetadata, this.frameStartMarker, this.sampleNumberHolder) == true) goto L7;
        r0 = r0 + 1;
        goto L4
    L7:
        r5.setPosition(r0);
        return this.sampleNumberHolder.sampleNumber;
    L10:
        if (r6 == true) goto L12;
        r5.setPosition(r0);
        return -1;
    L12:
        if (r0 > (r5.limit() - this.minFrameSize)) goto L25;
        r5.setPosition(r0);
        boolean r62 = false;
        boolean r1 = FlacFrameReader.checkAndReadFrameHeader(r5, this.flacStreamMetadata, this.frameStartMarker, this.sampleNumberHolder);     // Catch: IndexOutOfBoundsException -> L16
    L18:
        if (r5.getPosition() > r5.limit()) goto L21;
        r62 = r1;
    L21:
        if (r62 == true) goto L22;
        r0 = r0 + 1;
        goto L12
    L22:
        r5.setPosition(r0);
        return this.sampleNumberHolder.sampleNumber;
    L16:
        r1 = false;
        goto L18
    L25:
        r5.setPosition(r5.limit());
        return -1;
    }

    private void getFrameStartMarker(ExtractorInput r6) {
        this.frameStartMarker = FlacMetadataReader.getFrameStartMarker(r6);
        ((ExtractorOutput) Util.castNonNull(this.extractorOutput)).seekMap(getSeekMap(r6.getPosition(), r6.getLength()));
        this.state = 5;
    }

    private SeekMap getSeekMap(long r9, long r11) {
        Assertions.checkNotNull(this.flacStreamMetadata);
        FlacStreamMetadata r2 = this.flacStreamMetadata;
        FlacStreamMetadata.SeekTable r0 = r2.seekTable;
        if (r0 == null) goto L9;
        if (r0.pointSampleNumbers.length <= 0) goto L9;
        return new FlacSeekTableSeekMap(r2, r9);
    L9:
        if (r11 == (-1)) goto L15;
        if (r2.totalSamples <= 0) goto L15;
        FlacBinarySearchSeeker r1 = new FlacBinarySearchSeeker(r2, this.frameStartMarker, r9, r11);
        this.binarySearchSeeker = r1;
        return r1.getSeekMap();
    L15:
        return new SeekMap.Unseekable(r2.getDurationUs());
    }

    private void getStreamMarkerAndInfoBlockBytes(ExtractorInput r4) {
        byte[] r0 = this.streamMarkerAndInfoBlock;
        r4.peekFully(r0, 0, r0.length);
        r4.resetPeekPosition();
        this.state = 2;
    }

    private void outputSampleMetadata() {
        ((TrackOutput) Util.castNonNull(this.trackOutput)).sampleMetadata((this.currentFrameFirstSampleNumber * 1000000) / ((FlacStreamMetadata) Util.castNonNull(this.flacStreamMetadata)).sampleRate, 1, this.currentFrameBytesWritten, 0, null);
    }

    private int readFrames(ExtractorInput r7, PositionHolder r8) {
        Assertions.checkNotNull(this.trackOutput);
        Assertions.checkNotNull(this.flacStreamMetadata);
        FlacBinarySearchSeeker r0 = this.binarySearchSeeker;
        if (r0 == null) goto L9;
        if (r0.isSeeking() == false) goto L9;
        return this.binarySearchSeeker.handlePendingSeek(r7, r8);
    L9:
        if (this.currentFrameFirstSampleNumber != (-1)) goto L12;
        this.currentFrameFirstSampleNumber = FlacFrameReader.getFirstSampleNumber(r7, this.flacStreamMetadata);
        return 0;
    L12:
        int r82 = this.buffer.limit();
        if (r82 >= 32768) goto L25;
        int r72 = r7.read(this.buffer.getData(), r82, 32768 - r82);
        if (r72 != (-1)) goto L17;
        boolean r4 = true;
    L18:
        ParsableByteArray r5 = this.buffer;
        if (r4 == true) goto L22;
        r5.setLimit(r82 + r72);
    L26:
        int r73 = this.buffer.getPosition();
        int r83 = this.currentFrameBytesWritten;
        int r1 = this.minFrameSize;
        if (r83 >= r1) goto L29;
        ParsableByteArray r52 = this.buffer;
        r52.skipBytes(Math.min(r1 - r83, r52.bytesLeft()));
    L29:
        long r42 = findFrame(this.buffer, r4);
        int r84 = this.buffer.getPosition() - r73;
        this.buffer.setPosition(r73);
        this.trackOutput.sampleData(this.buffer, r84);
        this.currentFrameBytesWritten += r84;
        if (r42 == (-1)) goto L32;
        outputSampleMetadata();
        this.currentFrameBytesWritten = 0;
        this.currentFrameFirstSampleNumber = r42;
    L32:
        int r74 = this.buffer.getData().length - this.buffer.limit();
        if (this.buffer.bytesLeft() >= 16) goto L36;
        if (r74 >= 16) goto L36;
        int r75 = this.buffer.bytesLeft();
        System.arraycopy(this.buffer.getData(), this.buffer.getPosition(), this.buffer.getData(), 0, r75);
        this.buffer.setPosition(0);
        this.buffer.setLimit(r75);
    L36:
        return 0;
    L22:
        if (r5.bytesLeft() != 0) goto L26;
        outputSampleMetadata();
        return -1;
    L17:
        r4 = false;
        goto L18
    L25:
        r4 = false;
        goto L26
    }

    private void readId3Metadata(ExtractorInput r3) {
        this.id3Metadata = FlacMetadataReader.readId3Metadata(r3, !this.id3MetadataDisabled);
        this.state = 1;
    }

    private void readMetadataBlocks(ExtractorInput r4) {
        FlacMetadataReader.FlacStreamMetadataHolder r0 = new FlacMetadataReader.FlacStreamMetadataHolder(this.flacStreamMetadata);
        boolean r1 = false;
    L3:
        if (r1 == true) goto L5;
        r1 = FlacMetadataReader.readMetadataBlock(r4, r0);
        this.flacStreamMetadata = (FlacStreamMetadata) Util.castNonNull(r0.flacStreamMetadata);
        goto L3
    L5:
        Assertions.checkNotNull(this.flacStreamMetadata);
        this.minFrameSize = Math.max(this.flacStreamMetadata.minFrameSize, 6);
        Format r42 = this.flacStreamMetadata.getFormat(this.streamMarkerAndInfoBlock, this.id3Metadata);
        ((TrackOutput) Util.castNonNull(this.trackOutput)).format(r42.buildUpon().setContainerMimeType("audio/flac").build());
        ((TrackOutput) Util.castNonNull(this.trackOutput)).durationUs(this.flacStreamMetadata.getDurationUs());
        this.state = 4;
    }

    private void readStreamMarker(ExtractorInput r1) {
        FlacMetadataReader.readStreamMarker(r1);
        this.state = 3;
    }

    @Override // androidx.media3.extractor.Extractor
    public void init(ExtractorOutput r3) {
        this.extractorOutput = r3;
        this.trackOutput = r3.track(0, 1);
        r3.endTracks();
    }

    @Override // androidx.media3.extractor.Extractor
    public int read(ExtractorInput r4, PositionHolder r5) {
        int r0 = this.state;
        if (r0 != 0) goto L5;
        readId3Metadata(r4);
        return 0;
    L5:
        if (r0 != 1) goto L7;
        getStreamMarkerAndInfoBlockBytes(r4);
        return 0;
    L7:
        if (r0 != 2) goto L9;
        readStreamMarker(r4);
        return 0;
    L9:
        if (r0 != 3) goto L11;
        readMetadataBlocks(r4);
        return 0;
    L11:
        if (r0 != 4) goto L13;
        getFrameStartMarker(r4);
        return 0;
    L13:
        if (r0 == 5) goto L15;
        MethodWriter$$ExternalSyntheticBUOutline0.m();
        return 0;
    L15:
        return readFrames(r4, r5);
    }

    @Override // androidx.media3.extractor.Extractor
    public void release() {
    }

    @Override // androidx.media3.extractor.Extractor
    public void seek(long r3, long r5) {
        long r0 = 0;
        if (r3 != 0) goto L5;
        this.state = 0;
    L9:
        if (r5 == 0) goto L12;
        r0 = -1;
    L12:
        this.currentFrameFirstSampleNumber = r0;
        this.currentFrameBytesWritten = 0;
        this.buffer.reset(0);
        return;
    L5:
        FlacBinarySearchSeeker r32 = this.binarySearchSeeker;
        if (r32 == null) goto L9;
        r32.setSeekTargetUs(r5);
        goto L9
    }

    @Override // androidx.media3.extractor.Extractor
    public boolean sniff(ExtractorInput r1) {
        FlacMetadataReader.peekId3Metadata(r1, false);
        return FlacMetadataReader.checkAndPeekStreamMarker(r1);
    }
}
