package androidx.media3.extractor.jpeg;

import androidx.media3.common.Format;
import androidx.media3.common.Metadata;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.extractor.Extractor;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.PositionHolder;
import androidx.media3.extractor.SeekMap;
import androidx.media3.extractor.metadata.mp4.MotionPhotoMetadata;
import androidx.media3.extractor.mp4.Mp4Extractor;
import androidx.media3.extractor.text.SubtitleParser;
import org.mvel2.asm.MethodWriter$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
final class JpegMotionPhotoExtractor implements Extractor {
    private ExtractorOutput extractorOutput;
    private ExtractorInput lastExtractorInput;
    private int marker;
    private MotionPhotoMetadata motionPhotoMetadata;
    private Mp4Extractor mp4Extractor;
    private StartOffsetExtractorInput mp4ExtractorStartOffsetExtractorInput;
    private long mp4StartPosition;
    private final ParsableByteArray scratch;
    private int segmentLength;
    private int state;

    public JpegMotionPhotoExtractor() {
        this.scratch = new ParsableByteArray(2);
        this.mp4StartPosition = -1;
    }

    private void advancePeekPositionToNextSegment(ExtractorInput r4) {
        this.scratch.reset(2);
        r4.peekFully(this.scratch.getData(), 0, 2);
        r4.advancePeekPosition(this.scratch.readUnsignedShort() - 2);
    }

    private void endReading() {
        ((ExtractorOutput) Assertions.checkNotNull(this.extractorOutput)).endTracks();
        this.extractorOutput.seekMap(new SeekMap.Unseekable(-9223372036854775807L));
        this.state = 6;
    }

    private static MotionPhotoMetadata getMotionPhotoMetadata(String r2, long r3) {
        if (r3 != (-1)) goto L5;
        return null;
    L5:
        MotionPhotoDescription r22 = XmpMotionPhotoDescriptionParser.parse(r2);
        if (r22 != null) goto L9;
        return null;
    L9:
        return r22.getMotionPhotoMetadata(r3);
    }

    private void outputImageTrack(MotionPhotoMetadata r5) {
        ((ExtractorOutput) Assertions.checkNotNull(this.extractorOutput)).track(1024, 4).format(new Format.Builder().setContainerMimeType("image/jpeg").setMetadata(new Metadata(new Metadata.Entry[]{r5})).build());
    }

    private int peekMarker(ExtractorInput r4) {
        this.scratch.reset(2);
        r4.peekFully(this.scratch.getData(), 0, 2);
        return this.scratch.readUnsignedShort();
    }

    private void readMarker(ExtractorInput r5) {
        this.scratch.reset(2);
        r5.readFully(this.scratch.getData(), 0, 2);
        int r52 = this.scratch.readUnsignedShort();
        this.marker = r52;
        if (r52 != 65498) goto L11;
        if (this.mp4StartPosition == (-1)) goto L8;
        this.state = 4;
        return;
    L8:
        endReading();
        return;
    L11:
        if (r52 < 65488) goto L15;
        if (r52 > 65497) goto L15;
        return;
    L15:
        if (r52 == 65281) goto L19;
        this.state = 1;
        return;
    }

    private void readSegment(ExtractorInput r6) {
        if (this.marker != 65505) goto L13;
        ParsableByteArray r0 = new ParsableByteArray(this.segmentLength);
        r6.readFully(r0.getData(), 0, this.segmentLength);
        if (this.motionPhotoMetadata == null) goto L7;
    L14:
        this.state = 0;
        return;
    L7:
        if ("http://ns.adobe.com/xap/1.0/".equals(r0.readNullTerminatedString()) == false) goto L14;
        String r02 = r0.readNullTerminatedString();
        if (r02 == null) goto L14;
        MotionPhotoMetadata r62 = getMotionPhotoMetadata(r02, r6.getLength());
        this.motionPhotoMetadata = r62;
        if (r62 == null) goto L14;
        this.mp4StartPosition = r62.videoStartPosition;
        goto L14
    L13:
        r6.skipFully(this.segmentLength);
        goto L14
    }

    private void readSegmentLength(ExtractorInput r4) {
        this.scratch.reset(2);
        r4.readFully(this.scratch.getData(), 0, 2);
        this.segmentLength = this.scratch.readUnsignedShort() - 2;
        this.state = 2;
    }

    private void sniffMotionPhotoVideo(ExtractorInput r5) {
        if (r5.peekFully(this.scratch.getData(), 0, 1, true) == true) goto L6;
        endReading();
        return;
    L6:
        r5.resetPeekPosition();
        if (this.mp4Extractor != null) goto L9;
        this.mp4Extractor = new Mp4Extractor(SubtitleParser.Factory.UNSUPPORTED, 8);
    L9:
        StartOffsetExtractorInput r0 = new StartOffsetExtractorInput(r5, this.mp4StartPosition);
        this.mp4ExtractorStartOffsetExtractorInput = r0;
        if (this.mp4Extractor.sniff(r0) == false) goto L13;
        this.mp4Extractor.init(new StartOffsetExtractorOutput(this.mp4StartPosition, (ExtractorOutput) Assertions.checkNotNull(this.extractorOutput)));
        startReadingMotionPhoto();
        return;
    L13:
        endReading();
    }

    private void startReadingMotionPhoto() {
        outputImageTrack((MotionPhotoMetadata) Assertions.checkNotNull(this.motionPhotoMetadata));
        this.state = 5;
    }

    @Override // androidx.media3.extractor.Extractor
    public void init(ExtractorOutput r1) {
        this.extractorOutput = r1;
    }

    @Override // androidx.media3.extractor.Extractor
    public int read(ExtractorInput r8, PositionHolder r9) {
        int r0 = this.state;
        if (r0 != 0) goto L5;
        readMarker(r8);
        return 0;
    L5:
        if (r0 != 1) goto L7;
        readSegmentLength(r8);
        return 0;
    L7:
        if (r0 != 2) goto L9;
        readSegment(r8);
        return 0;
    L9:
        if (r0 != 4) goto L11;
        long r3 = r8.getPosition();
        long r5 = this.mp4StartPosition;
        if (r3 == r5) goto L31;
        r9.position = r5;
        return 1;
    L31:
        sniffMotionPhotoVideo(r8);
        return 0;
    L11:
        if (r0 == 5) goto L19;
        if (r0 != 6) goto L16;
        return -1;
    L16:
        MethodWriter$$ExternalSyntheticBUOutline0.m();
        return 0;
    L19:
        if (this.mp4ExtractorStartOffsetExtractorInput != null) goto L21;
    L22:
        this.lastExtractorInput = r8;
        this.mp4ExtractorStartOffsetExtractorInput = new StartOffsetExtractorInput(r8, this.mp4StartPosition);
    L23:
        int r82 = ((Mp4Extractor) Assertions.checkNotNull(this.mp4Extractor)).read(this.mp4ExtractorStartOffsetExtractorInput, r9);
        if (r82 != 1) goto L26;
        r9.position += this.mp4StartPosition;
    L26:
        return r82;
    L21:
        if (r8 == this.lastExtractorInput) goto L23;
        goto L22
    }

    @Override // androidx.media3.extractor.Extractor
    public void release() {
        Mp4Extractor r0 = this.mp4Extractor;
        if (r0 == null) goto L6;
        r0.release();
        return;
    }

    @Override // androidx.media3.extractor.Extractor
    public void seek(long r3, long r5) {
        if (r3 != 0) goto L7;
        this.state = 0;
        this.mp4Extractor = null;
        return;
    L7:
        if (this.state != 5) goto L10;
        ((Mp4Extractor) Assertions.checkNotNull(this.mp4Extractor)).seek(r3, r5);
        return;
    }

    @Override // androidx.media3.extractor.Extractor
    public boolean sniff(ExtractorInput r4) {
        if (peekMarker(r4) == 65496) goto L5;
        return false;
    L5:
        int r0 = peekMarker(r4);
        this.marker = r0;
        if (r0 != 65504) goto L9;
        advancePeekPositionToNextSegment(r4);
        this.marker = peekMarker(r4);
    L9:
        if (this.marker != 65505) goto L12;
        return true;
    L12:
        return false;
    }
}
