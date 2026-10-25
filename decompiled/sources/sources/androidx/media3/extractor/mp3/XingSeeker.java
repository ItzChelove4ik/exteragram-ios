package androidx.media3.extractor.mp3;

import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.MpegAudioUtil;
import androidx.media3.extractor.SeekMap;
import androidx.media3.extractor.SeekPoint;

/* loaded from: classes4.dex */
final class XingSeeker implements Seeker {
    private final int bitrate;
    private final long dataEndPosition;
    private final long dataSize;
    private final long dataStartPosition;
    private final long durationUs;
    private final long[] tableOfContents;
    private final int xingFrameSize;

    private XingSeeker(long r1, int r3, long r4, int r6, long r7, long[] r9) {
        this.dataStartPosition = r1;
        this.xingFrameSize = r3;
        this.durationUs = r4;
        this.bitrate = r6;
        this.dataSize = r7;
        this.tableOfContents = r9;
        long r32 = -1;
        if (r7 == (-1)) goto L6;
        r32 = r1 + r7;
    L6:
        this.dataEndPosition = r32;
    }

    public static XingSeeker create(XingFrame r10, long r11) {
        long r4 = r10.computeDurationUs();
        if (r4 != (-9223372036854775807L)) goto L6;
        return null;
    L6:
        MpegAudioUtil.Header r1 = r10.header;
        return new XingSeeker(r11, r1.frameSize, r4, r1.bitrate, r10.dataSize, r10.tableOfContents);
    }

    private long getTimeUsForTableIndex(int r3) {
        return (this.durationUs * r3) / 100;
    }

    @Override // androidx.media3.extractor.mp3.Seeker
    public int getAverageBitrate() {
        return this.bitrate;
    }

    @Override // androidx.media3.extractor.mp3.Seeker
    public long getDataEndPosition() {
        return this.dataEndPosition;
    }

    @Override // androidx.media3.extractor.mp3.Seeker
    public long getDataStartPosition() {
        return this.dataStartPosition + this.xingFrameSize;
    }

    @Override // androidx.media3.extractor.SeekMap
    public long getDurationUs() {
        return this.durationUs;
    }

    @Override // androidx.media3.extractor.SeekMap
    public SeekMap.SeekPoints getSeekPoints(long r13) {
        if (isSeekable() == false) goto L5;
        long r132 = Util.constrainValue(r13, 0, this.durationUs);
        double r0 = (r132 * 100.0d) / this.durationUs;
        double r4 = 0.0d;
        if (r0 <= 0.0d) goto L18;
        if (r0 < 100.0d) goto L12;
        r4 = 256.0d;
        goto L18
    L12:
        int r2 = (int) r0;
        long[] r3 = (long[]) Assertions.checkStateNotNull(this.tableOfContents);
        double r42 = r3[r2];
        if (r2 != 99) goto L15;
        double r9 = 256.0d;
    L16:
        r4 = r42 + ((r0 - r2) * (r9 - r42));
        goto L18
    L15:
        r9 = r3[r2 + 1];
    L18:
        return new SeekMap.SeekPoints(new SeekPoint(r132, this.dataStartPosition + Util.constrainValue(Math.round((r4 / 256.0d) * this.dataSize), this.xingFrameSize, this.dataSize - 1)));
    L5:
        return new SeekMap.SeekPoints(new SeekPoint(0, this.dataStartPosition + this.xingFrameSize));
    }

    @Override // androidx.media3.extractor.mp3.Seeker
    public long getTimeUs(long r12) {
        long r122 = r12 - this.dataStartPosition;
        if (isSeekable() == true) goto L5;
        return 0;
    L5:
        if (r122 <= this.xingFrameSize) goto L19;
        long[] r0 = (long[]) Assertions.checkStateNotNull(this.tableOfContents);
        double r123 = (r122 * 256.0d) / this.dataSize;
        int r1 = Util.binarySearchFloor(r0, (long) r123, true, true);
        long r2 = getTimeUsForTableIndex(r1);
        long r4 = r0[r1];
        int r6 = r1 + 1;
        long r7 = getTimeUsForTableIndex(r6);
        if (r1 != 99) goto L10;
        long r02 = 256;
    L12:
        if (r4 != r02) goto L14;
        double r11 = 0.0d;
    L16:
        return r2 + Math.round(r11 * (r7 - r2));
    L14:
        r11 = (r123 - r4) / (r02 - r4);
        goto L16
    L10:
        r02 = r0[r6];
        goto L12
    L19:
        return 0;
    }

    @Override // androidx.media3.extractor.SeekMap
    public boolean isSeekable() {
        if (this.tableOfContents == null) goto L6;
        return true;
    L6:
        return false;
    }
}
