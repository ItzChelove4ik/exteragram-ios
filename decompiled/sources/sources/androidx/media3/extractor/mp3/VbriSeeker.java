package androidx.media3.extractor.mp3;

import androidx.media3.common.util.Log;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.MpegAudioUtil;
import androidx.media3.extractor.SeekMap;
import androidx.media3.extractor.SeekPoint;

/* loaded from: classes4.dex */
final class VbriSeeker implements Seeker {
    private final int bitrate;
    private final long dataEndPosition;
    private final long dataStartPosition;
    private final long durationUs;
    private final long[] positions;
    private final long[] timesUs;

    private VbriSeeker(long[] r1, long[] r2, long r3, long r5, long r7, int r9) {
        this.timesUs = r1;
        this.positions = r2;
        this.durationUs = r3;
        this.dataStartPosition = r5;
        this.dataEndPosition = r7;
        this.bitrate = r9;
    }

    public static VbriSeeker create(long r22, long r24, MpegAudioUtil.Header r26, ParsableByteArray r27) {
        r27.skipBytes(6);
        long r12 = r24 + r26.frameSize;
        long r4 = r27.readInt() + r12;
        int r6 = r27.readInt();
        if (r6 > 0) goto L5;
        return null;
    L5:
        long r10 = Util.sampleCountToDurationUs((r6 * r26.samplesPerFrame) - 1, r26.sampleRate);
        int r62 = r27.readUnsignedShort();
        int r8 = r27.readUnsignedShort();
        int r9 = r27.readUnsignedShort();
        r27.skipBytes(2);
        int r17 = r8;
        long[] r82 = new long[r62];
        long[] r7 = new long[r62];
        int r3 = 0;
        long r14 = r24 + r26.frameSize;
    L6:
        if (r3 >= r62) goto L21;
        long[] r19 = r7;
        long[] r18 = r82;
        r18[r3] = (r3 * r10) / r62;
        r19[r3] = r14;
        if (r9 != 1) goto L10;
        int r83 = r27.readUnsignedByte();
    L20:
        int r242 = r3;
        int r32 = r17;
        r14 = r14 + (r83 * r32);
        r17 = r32;
        r3 = r242 + 1;
        r62 = r62;
        r82 = r18;
        r7 = r19;
        goto L6
    L10:
        if (r9 != 2) goto L12;
        r83 = r27.readUnsignedShort();
        goto L20
    L12:
        if (r9 != 3) goto L14;
        r83 = r27.readUnsignedInt24();
        goto L20
    L14:
        if (r9 != 4) goto L15;
        r83 = r27.readUnsignedIntToInt();
        goto L20
    L15:
        return null;
    L21:
        long[] r192 = r7;
        long[] r182 = r82;
        if (r22 == (-1)) goto L27;
        if (r22 == r4) goto L27;
        Log.w("VbriSeeker", "VBRI data size mismatch: " + r22 + ", " + r4);
    L27:
        if (r4 == r14) goto L30;
        Log.w("VbriSeeker", "VBRI bytes and ToC mismatch (using max): " + r4 + ", " + r14 + "\nSeeking will be inaccurate.");
        r4 = Math.max(r4, r14);
    L30:
        return new VbriSeeker(r182, r192, r10, r12, r4, r26.bitrate);
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
        return this.dataStartPosition;
    }

    @Override // androidx.media3.extractor.SeekMap
    public long getDurationUs() {
        return this.durationUs;
    }

    @Override // androidx.media3.extractor.SeekMap
    public SeekMap.SeekPoints getSeekPoints(long r9) {
        int r0 = Util.binarySearchFloor(this.timesUs, r9, true, true);
        SeekPoint r2 = new SeekPoint(this.timesUs[r0], this.positions[r0]);
        if (r2.timeUs >= r9) goto L10;
        if (r0 == (this.timesUs.length - 1)) goto L10;
        int r02 = r0 + 1;
        return new SeekMap.SeekPoints(r2, new SeekPoint(this.timesUs[r02], this.positions[r02]));
    L10:
        return new SeekMap.SeekPoints(r2);
    }

    @Override // androidx.media3.extractor.mp3.Seeker
    public long getTimeUs(long r3) {
        return this.timesUs[Util.binarySearchFloor(this.positions, r3, true, true)];
    }

    @Override // androidx.media3.extractor.SeekMap
    public boolean isSeekable() {
        return true;
    }
}
