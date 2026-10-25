package androidx.media3.extractor;

import androidx.media3.common.util.Util;
import androidx.media3.extractor.SeekMap;
import java.util.Arrays;

/* loaded from: classes4.dex */
public final class ChunkIndex implements SeekMap {
    private final long durationUs;
    public final long[] durationsUs;
    public final int length;
    public final long[] offsets;
    public final int[] sizes;
    public final long[] timesUs;

    public ChunkIndex(int[] r3, long[] r4, long[] r5, long[] r6) {
        this.sizes = r3;
        this.offsets = r4;
        this.durationsUs = r5;
        this.timesUs = r6;
        int r32 = r3.length;
        this.length = r32;
        if (r32 <= 0) goto L6;
        this.durationUs = r5[r32 - 1] + r6[r32 - 1];
        return;
    L6:
        this.durationUs = 0;
    }

    public int getChunkIndex(long r2) {
        return Util.binarySearchFloor(this.timesUs, r2, true, true);
    }

    @Override // androidx.media3.extractor.SeekMap
    public long getDurationUs() {
        return this.durationUs;
    }

    @Override // androidx.media3.extractor.SeekMap
    public SeekMap.SeekPoints getSeekPoints(long r8) {
        int r0 = getChunkIndex(r8);
        SeekPoint r1 = new SeekPoint(this.timesUs[r0], this.offsets[r0]);
        if (r1.timeUs >= r8) goto L10;
        if (r0 == (this.length - 1)) goto L10;
        int r02 = r0 + 1;
        return new SeekMap.SeekPoints(r1, new SeekPoint(this.timesUs[r02], this.offsets[r02]));
    L10:
        return new SeekMap.SeekPoints(r1);
    }

    @Override // androidx.media3.extractor.SeekMap
    public boolean isSeekable() {
        return true;
    }

    public String toString() {
        return "ChunkIndex(length=" + this.length + ", sizes=" + Arrays.toString(this.sizes) + ", offsets=" + Arrays.toString(this.offsets) + ", timeUs=" + Arrays.toString(this.timesUs) + ", durationsUs=" + Arrays.toString(this.durationsUs) + ")";
    }
}
