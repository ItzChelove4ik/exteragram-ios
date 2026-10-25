package androidx.media3.extractor;

import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.FlacStreamMetadata;
import androidx.media3.extractor.SeekMap;

/* loaded from: classes4.dex */
public final class FlacSeekTableSeekMap implements SeekMap {
    private final long firstFrameOffset;
    private final FlacStreamMetadata flacStreamMetadata;

    public FlacSeekTableSeekMap(FlacStreamMetadata r1, long r2) {
        this.flacStreamMetadata = r1;
        this.firstFrameOffset = r2;
    }

    private SeekPoint getSeekPoint(long r3, long r5) {
        return new SeekPoint((r3 * 1000000) / this.flacStreamMetadata.sampleRate, this.firstFrameOffset + r5);
    }

    @Override // androidx.media3.extractor.SeekMap
    public long getDurationUs() {
        return this.flacStreamMetadata.getDurationUs();
    }

    @Override // androidx.media3.extractor.SeekMap
    public SeekMap.SeekPoints getSeekPoints(long r10) {
        Assertions.checkStateNotNull(this.flacStreamMetadata.seekTable);
        FlacStreamMetadata r0 = this.flacStreamMetadata;
        FlacStreamMetadata.SeekTable r1 = r0.seekTable;
        long[] r2 = r1.pointSampleNumbers;
        long[] r12 = r1.pointOffsets;
        int r02 = Util.binarySearchFloor(r2, r0.getSampleNumber(r10), true, false);
        long r3 = 0;
        if (r02 != (-1)) goto L5;
        long r7 = 0;
    L6:
        if (r02 == (-1)) goto L9;
        r3 = r12[r02];
    L9:
        SeekPoint r32 = getSeekPoint(r7, r3);
        if (r32.timeUs == r10) goto L17;
        if (r02 == (r2.length - 1)) goto L17;
        int r03 = r02 + 1;
        return new SeekMap.SeekPoints(r32, getSeekPoint(r2[r03], r12[r03]));
    L17:
        return new SeekMap.SeekPoints(r32);
    L5:
        r7 = r2[r02];
        goto L6
    }

    @Override // androidx.media3.extractor.SeekMap
    public boolean isSeekable() {
        return true;
    }
}
