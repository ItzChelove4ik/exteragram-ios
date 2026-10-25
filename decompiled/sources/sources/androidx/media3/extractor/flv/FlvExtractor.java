package androidx.media3.extractor.flv;

import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.extractor.Extractor;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.ExtractorsFactory;
import androidx.media3.extractor.IndexSeekMap;
import androidx.media3.extractor.PositionHolder;
import androidx.media3.extractor.SeekMap;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;
import org.mvel2.asm.MethodWriter$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
public final class FlvExtractor implements Extractor {
    public static final ExtractorsFactory FACTORY = null;
    private AudioTagPayloadReader audioReader;
    private int bytesToNextTagHeader;
    private ExtractorOutput extractorOutput;
    private final ParsableByteArray headerBuffer;
    private long mediaTagTimestampOffsetUs;
    private final ScriptTagPayloadReader metadataReader;
    private boolean outputFirstSample;
    private boolean outputSeekMap;
    private final ParsableByteArray scratch;
    private int state;
    private final ParsableByteArray tagData;
    private int tagDataSize;
    private final ParsableByteArray tagHeaderBuffer;
    private long tagTimestampUs;
    private int tagType;
    private VideoTagPayloadReader videoReader;

    public static /* synthetic */ Extractor[] $r8$lambda$MrsZrKA4DwdxUxuYChsEUeDE69Q() {
        return new Extractor[]{new FlvExtractor()};
    }

    static {
        FACTORY = new FlvExtractor$$ExternalSyntheticLambda0();
    }

    public FlvExtractor() {
        this.scratch = new ParsableByteArray(4);
        this.headerBuffer = new ParsableByteArray(9);
        this.tagHeaderBuffer = new ParsableByteArray(11);
        this.tagData = new ParsableByteArray();
        this.metadataReader = new ScriptTagPayloadReader();
        this.state = 1;
    }

    @RequiresNonNull({"extractorOutput"})
    private void ensureReadyForMediaOutput() {
        if (this.outputSeekMap == true) goto L6;
        this.extractorOutput.seekMap(new SeekMap.Unseekable(-9223372036854775807L));
        this.outputSeekMap = true;
        return;
    }

    private long getCurrentTimestampUs() {
        if (this.outputFirstSample == false) goto L7;
        return this.mediaTagTimestampOffsetUs + this.tagTimestampUs;
    L7:
        if (this.metadataReader.getDurationUs() != (-9223372036854775807L)) goto L11;
        return 0;
    L11:
        return this.tagTimestampUs;
    }

    private ParsableByteArray prepareTagData(ExtractorInput r5) {
        int r0 = this.tagDataSize;
        int r1 = this.tagData.capacity();
        ParsableByteArray r2 = this.tagData;
        if (r0 <= r1) goto L5;
        r2.reset(new byte[Math.max(r2.capacity() * 2, this.tagDataSize)], 0);
    L6:
        this.tagData.setLimit(this.tagDataSize);
        r5.readFully(this.tagData.getData(), 0, this.tagDataSize);
        return this.tagData;
    L5:
        r2.setPosition(0);
        goto L6
    }

    @RequiresNonNull({"extractorOutput"})
    private boolean readFlvHeader(ExtractorInput r6) {
        boolean r1 = false;
        if (r6.readFully(this.headerBuffer.getData(), 0, 9, true) == true) goto L5;
        return false;
    L5:
        this.headerBuffer.setPosition(0);
        this.headerBuffer.skipBytes(4);
        int r62 = this.headerBuffer.readUnsignedByte();
        if ((r62 & 4) == 0) goto L8;
        boolean r0 = true;
    L10:
        if ((r62 & 1) == 0) goto L12;
        r1 = true;
    L12:
        if (r0 == false) goto L17;
        if (this.audioReader != null) goto L17;
        this.audioReader = new AudioTagPayloadReader(this.extractorOutput.track(8, 1));
    L17:
        if (r1 == true) goto L19;
    L21:
        this.extractorOutput.endTracks();
        this.bytesToNextTagHeader = this.headerBuffer.readInt() - 5;
        this.state = 2;
        return true;
    L19:
        if (this.videoReader != null) goto L21;
        this.videoReader = new VideoTagPayloadReader(this.extractorOutput.track(9, 2));
        goto L21
    L8:
        r0 = false;
        goto L10
    }

    @RequiresNonNull({"extractorOutput"})
    private boolean readTagData(ExtractorInput r10) {
        long r0 = getCurrentTimestampUs();
        int r2 = this.tagType;
        if (r2 != 8) goto L9;
        if (this.audioReader == null) goto L9;
        ensureReadyForMediaOutput();
        boolean r102 = this.audioReader.consume(prepareTagData(r10), r0);
    L7:
        boolean r02 = true;
    L22:
        if (this.outputFirstSample == true) goto L29;
        if (r102 == false) goto L29;
        this.outputFirstSample = true;
        if (this.metadataReader.getDurationUs() != (-9223372036854775807L)) goto L27;
        long r1 = -this.tagTimestampUs;
    L28:
        this.mediaTagTimestampOffsetUs = r1;
        goto L29
    L27:
        r1 = 0;
    L29:
        this.bytesToNextTagHeader = 4;
        this.state = 2;
        return r02;
    L9:
        if (r2 != 9) goto L14;
        if (this.videoReader == null) goto L14;
        ensureReadyForMediaOutput();
        r102 = this.videoReader.consume(prepareTagData(r10), r0);
    L14:
        if (r2 == 18) goto L16;
    L20:
        r10.skipFully(this.tagDataSize);
        r102 = false;
        r02 = false;
        goto L22
    L16:
        if (this.outputSeekMap == true) goto L20;
        r102 = this.metadataReader.consume(prepareTagData(r10), r0);
        long r03 = this.metadataReader.getDurationUs();
        if (r03 == (-9223372036854775807L)) goto L7;
        this.extractorOutput.seekMap(new IndexSeekMap(this.metadataReader.getKeyFrameTagPositions(), this.metadataReader.getKeyFrameTimesUs(), r03));
        this.outputSeekMap = true;
        goto L7
    }

    private boolean readTagHeader(ExtractorInput r7) {
        if (r7.readFully(this.tagHeaderBuffer.getData(), 0, 11, true) == true) goto L5;
        return false;
    L5:
        this.tagHeaderBuffer.setPosition(0);
        this.tagType = this.tagHeaderBuffer.readUnsignedByte();
        this.tagDataSize = this.tagHeaderBuffer.readUnsignedInt24();
        this.tagTimestampUs = this.tagHeaderBuffer.readUnsignedInt24();
        this.tagTimestampUs = ((this.tagHeaderBuffer.readUnsignedByte() << 24) | this.tagTimestampUs) * 1000;
        this.tagHeaderBuffer.skipBytes(3);
        this.state = 4;
        return true;
    }

    private void skipToTagHeader(ExtractorInput r2) {
        r2.skipFully(this.bytesToNextTagHeader);
        this.bytesToNextTagHeader = 0;
        this.state = 3;
    }

    @Override // androidx.media3.extractor.Extractor
    public void init(ExtractorOutput r1) {
        this.extractorOutput = r1;
    }

    @Override // androidx.media3.extractor.Extractor
    public int read(ExtractorInput r3, PositionHolder r4) {
        Assertions.checkStateNotNull(this.extractorOutput);
    L3:
        int r42 = this.state;
        if (r42 != 1) goto L6;
        if (readFlvHeader(r3) == true) goto L3;
        return -1;
    L6:
        if (r42 != 2) goto L8;
        skipToTagHeader(r3);
        goto L3
    L8:
        if (r42 != 3) goto L10;
        if (readTagHeader(r3) == true) goto L3;
        return -1;
    L10:
        if (r42 != 4) goto L15;
        if (readTagData(r3) == false) goto L3;
        return 0;
    L15:
        MethodWriter$$ExternalSyntheticBUOutline0.m();
        return 0;
    }

    @Override // androidx.media3.extractor.Extractor
    public void release() {
    }

    @Override // androidx.media3.extractor.Extractor
    public void seek(long r1, long r3) {
        if (r1 != 0) goto L5;
        this.state = 1;
        this.outputFirstSample = false;
    L6:
        this.bytesToNextTagHeader = 0;
        return;
    L5:
        this.state = 3;
        goto L6
    }

    @Override // androidx.media3.extractor.Extractor
    public boolean sniff(ExtractorInput r4) {
        r4.peekFully(this.scratch.getData(), 0, 3);
        this.scratch.setPosition(0);
        if (this.scratch.readUnsignedInt24() == 4607062) goto L5;
        return false;
    L5:
        r4.peekFully(this.scratch.getData(), 0, 2);
        this.scratch.setPosition(0);
        if ((this.scratch.readUnsignedShort() & 250) == 0) goto L8;
        return false;
    L8:
        r4.peekFully(this.scratch.getData(), 0, 4);
        this.scratch.setPosition(0);
        int r0 = this.scratch.readInt();
        r4.resetPeekPosition();
        r4.advancePeekPosition(r0);
        r4.peekFully(this.scratch.getData(), 0, 4);
        this.scratch.setPosition(0);
        if (this.scratch.readInt() != 0) goto L12;
        return true;
    L12:
        return false;
    }
}
