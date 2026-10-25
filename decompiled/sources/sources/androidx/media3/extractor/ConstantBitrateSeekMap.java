package androidx.media3.extractor;

import androidx.media3.extractor.SeekMap;

/* loaded from: classes4.dex */
public class ConstantBitrateSeekMap implements SeekMap {
    private final boolean allowSeeksIfLengthUnknown;
    private final int bitrate;
    private final long dataSize;
    private final long durationUs;
    private final long firstFrameBytePosition;
    private final int frameSize;
    private final long inputLength;

    public ConstantBitrateSeekMap(long r2, long r4, int r6, int r7, boolean r8) {
        this.inputLength = r2;
        this.firstFrameBytePosition = r4;
        if (r7 != (-1)) goto L5;
        r7 = 1;
    L5:
        this.frameSize = r7;
        this.bitrate = r6;
        this.allowSeeksIfLengthUnknown = r8;
        if (r2 != (-1)) goto L9;
        this.dataSize = -1;
        this.durationUs = -9223372036854775807L;
        return;
    L9:
        this.dataSize = r2 - r4;
        this.durationUs = getTimeUsAtPosition(r2, r4, r6);
    }

    private long getFramePositionForTimeUs(long r6) {
        int r0 = this.frameSize;
        long r62 = (((r6 * this.bitrate) / 8000000) / r0) * r0;
        long r1 = this.dataSize;
        if (r1 == (-1)) goto L6;
        r62 = Math.min(r62, r1 - r0);
    L6:
        return this.firstFrameBytePosition + Math.max(r62, 0);
    }

    private static long getTimeUsAtPosition(long r2, long r4, int r6) {
        return (Math.max(0, r2 - r4) * 8000000) / r6;
    }

    @Override // androidx.media3.extractor.SeekMap
    public long getDurationUs() {
        return this.durationUs;
    }

    @Override // androidx.media3.extractor.SeekMap
    public SeekMap.SeekPoints getSeekPoints(long r10) {
        if (this.dataSize == (-1)) goto L5;
    L8:
        long r0 = getFramePositionForTimeUs(r10);
        long r4 = getTimeUsAtPosition(r0);
        SeekPoint r6 = new SeekPoint(r4, r0);
        if (this.dataSize == (-1)) goto L18;
        if (r4 >= r10) goto L18;
        int r102 = this.frameSize;
        if ((r102 + r0) >= this.inputLength) goto L18;
        long r02 = r0 + r102;
        return new SeekMap.SeekPoints(r6, new SeekPoint(getTimeUsAtPosition(r02), r02));
    L18:
        return new SeekMap.SeekPoints(r6);
    L5:
        if (this.allowSeeksIfLengthUnknown == true) goto L8;
        return new SeekMap.SeekPoints(new SeekPoint(0, this.firstFrameBytePosition));
    }

    public long getTimeUsAtPosition(long r3) {
        return getTimeUsAtPosition(r3, this.firstFrameBytePosition, this.bitrate);
    }

    @Override // androidx.media3.extractor.SeekMap
    public boolean isSeekable() {
        if (this.dataSize == (-1)) goto L5;
        return true;
    L5:
        if (this.allowSeeksIfLengthUnknown == true) goto L11;
        return false;
    L11:
        return true;
    }
}
