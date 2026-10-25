package androidx.media3.extractor;

import androidx.media3.common.Format;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.ParsableByteArray;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;
import org.mvel2.asm.MethodWriter$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
public final class SingleSampleExtractor implements Extractor {
    private ExtractorOutput extractorOutput;
    private final int fileSignature;
    private final int fileSignatureLength;
    private final String sampleMimeType;
    private int size;
    private int state;
    private TrackOutput trackOutput;

    public SingleSampleExtractor(int r1, int r2, String r3) {
        this.fileSignature = r1;
        this.fileSignatureLength = r2;
        this.sampleMimeType = r3;
    }

    @RequiresNonNull({"this.extractorOutput"})
    private void outputImageTrackAndSeekMap(String r4) {
        TrackOutput r0 = this.extractorOutput.track(1024, 4);
        this.trackOutput = r0;
        r0.format(new Format.Builder().setContainerMimeType(r4).setSampleMimeType(r4).build());
        this.extractorOutput.endTracks();
        this.extractorOutput.seekMap(new SingleSampleSeekMap(-9223372036854775807L));
        this.state = 1;
    }

    private void readSegment(ExtractorInput r8) {
        int r82 = ((TrackOutput) Assertions.checkNotNull(this.trackOutput)).sampleData(r8, 1024, true);
        if (r82 != (-1)) goto L6;
        this.state = 2;
        this.trackOutput.sampleMetadata(0, 1, this.size, 0, null);
        this.size = 0;
        return;
    L6:
        this.size += r82;
    }

    @Override // androidx.media3.extractor.Extractor
    public void init(ExtractorOutput r1) {
        this.extractorOutput = r1;
        outputImageTrackAndSeekMap(this.sampleMimeType);
    }

    @Override // androidx.media3.extractor.Extractor
    public int read(ExtractorInput r2, PositionHolder r3) {
        int r32 = this.state;
        if (r32 != 1) goto L5;
        readSegment(r2);
        return 0;
    L5:
        if (r32 != 2) goto L8;
        return -1;
    L8:
        MethodWriter$$ExternalSyntheticBUOutline0.m();
        return 0;
    }

    @Override // androidx.media3.extractor.Extractor
    public void release() {
    }

    @Override // androidx.media3.extractor.Extractor
    public void seek(long r1, long r3) {
        if (r1 != 0) goto L5;
    L8:
        this.state = 1;
        this.size = 0;
        return;
    L5:
        if (this.state == 1) goto L8;
    }

    @Override // androidx.media3.extractor.Extractor
    public boolean sniff(ExtractorInput r6) {
        if (this.fileSignature != (-1)) goto L5;
    L7:
        boolean r0 = false;
    L8:
        Assertions.checkState(r0);
        ParsableByteArray r02 = new ParsableByteArray(this.fileSignatureLength);
        r6.peekFully(r02.getData(), 0, this.fileSignatureLength);
        if (r02.readUnsignedShort() != this.fileSignature) goto L11;
        return true;
    L11:
        return false;
    L5:
        if (this.fileSignatureLength == (-1)) goto L7;
        r0 = true;
        goto L8
    }
}
