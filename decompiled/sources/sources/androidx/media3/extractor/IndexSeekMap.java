package androidx.media3.extractor;

import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.LongArray;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.SeekMap;

/* loaded from: classes4.dex */
public final class IndexSeekMap implements SeekMap {
    private long durationUs;
    private final LongArray positions;
    private final LongArray timesUs;

    public IndexSeekMap(long[] r7, long[] r8, long r9) {
        if (r7.length != r8.length) goto L5;
        boolean r0 = true;
    L6:
        Assertions.checkArgument(r0);
        int r02 = r8.length;
        if (r02 > 0) goto L9;
    L11:
        this.positions = new LongArray(r02);
        this.timesUs = new LongArray(r02);
    L12:
        this.positions.addAll(r7);
        this.timesUs.addAll(r8);
        this.durationUs = r9;
        return;
    L9:
        if (r8[0] <= 0) goto L11;
        int r03 = r02 + 1;
        LongArray r1 = new LongArray(r03);
        this.positions = r1;
        LongArray r2 = new LongArray(r03);
        this.timesUs = r2;
        r1.add(0);
        r2.add(0);
        goto L12
    L5:
        r0 = false;
        goto L6
    }

    public void addSeekPoint(long r4, long r6) {
        if (this.timesUs.size() == 0) goto L5;
    L7:
        this.positions.add(r6);
        this.timesUs.add(r4);
        return;
    L5:
        if (r4 <= 0) goto L7;
        this.positions.add(0);
        this.timesUs.add(0);
        goto L7
    }

    @Override // androidx.media3.extractor.SeekMap
    public long getDurationUs() {
        return this.durationUs;
    }

    @Override // androidx.media3.extractor.SeekMap
    public SeekMap.SeekPoints getSeekPoints(long r8) {
        if (this.timesUs.size() == 0) goto L5;
        int r0 = Util.binarySearchFloor(this.timesUs, r8, true, true);
        SeekPoint r2 = new SeekPoint(this.timesUs.get(r0), this.positions.get(r0));
        if (r2.timeUs == r8) goto L14;
        if (r0 == (this.timesUs.size() - 1)) goto L14;
        int r02 = r0 + 1;
        return new SeekMap.SeekPoints(r2, new SeekPoint(this.timesUs.get(r02), this.positions.get(r02)));
    L14:
        return new SeekMap.SeekPoints(r2);
    L5:
        return new SeekMap.SeekPoints(SeekPoint.START);
    }

    public long getTimeUs(long r3) {
        if (this.timesUs.size() != 0) goto L6;
        return -9223372036854775807L;
    L6:
        int r32 = Util.binarySearchFloor(this.positions, r3, true, true);
        return this.timesUs.get(r32);
    }

    @Override // androidx.media3.extractor.SeekMap
    public boolean isSeekable() {
        if (this.timesUs.size() <= 0) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean isTimeUsInIndex(long r6, long r8) {
        if (this.timesUs.size() != 0) goto L5;
        return false;
    L5:
        LongArray r5 = this.timesUs;
        if ((r6 - r5.get(r5.size() - 1)) >= r8) goto L8;
        return true;
    L8:
        return false;
    }

    public void setDurationUs(long r1) {
        this.durationUs = r1;
    }
}
