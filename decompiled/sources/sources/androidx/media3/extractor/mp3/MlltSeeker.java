package androidx.media3.extractor.mp3;

import android.util.Pair;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.SeekMap;
import androidx.media3.extractor.SeekPoint;
import androidx.media3.extractor.metadata.id3.MlltFrame;

/* loaded from: classes4.dex */
final class MlltSeeker implements Seeker {
    private final long durationUs;
    private final long[] referencePositions;
    private final long[] referenceTimesMs;

    private MlltSeeker(long[] r3, long[] r4, long r5) {
        this.referencePositions = r3;
        this.referenceTimesMs = r4;
        if (r5 != (-9223372036854775807L)) goto L6;
        r5 = Util.msToUs(r4[r4.length - 1]);
    L6:
        this.durationUs = r5;
    }

    public static MlltSeeker create(long r9, MlltFrame r11, long r12) {
        int r0 = r11.bytesDeviations.length;
        int r1 = r0 + 1;
        long[] r2 = new long[r1];
        long[] r13 = new long[r1];
        r2[0] = r9;
        long r4 = 0;
        r13[0] = 0;
        int r3 = 1;
    L3:
        if (r3 > r0) goto L6;
        int r8 = r3 - 1;
        r9 = r9 + (r11.bytesBetweenReference + r11.bytesDeviations[r8]);
        r4 = r4 + (r11.millisecondsBetweenReference + r11.millisecondsDeviations[r8]);
        r2[r3] = r9;
        r13[r3] = r4;
        r3 = r3 + 1;
        goto L3
    L6:
        return new MlltSeeker(r2, r13, r12);
    }

    private static Pair<Long, Long> linearlyInterpolate(long r10, long[] r12, long[] r13) {
        int r1 = Util.binarySearchFloor(r12, r10, true, true);
        long r2 = r12[r1];
        long r4 = r13[r1];
        int r14 = r1 + 1;
        if (r14 == r12.length) goto L5;
        long r6 = r12[r14];
        long r122 = r13[r14];
        if (r6 != r2) goto L9;
        double r0 = 0.0d;
    L11:
        return Pair.create(Long.valueOf(r10), Long.valueOf(((long) (r0 * (r122 - r4))) + r4));
    L9:
        r0 = (r10 - r2) / (r6 - r2);
        goto L11
    L5:
        return Pair.create(Long.valueOf(r2), Long.valueOf(r4));
    }

    @Override // androidx.media3.extractor.mp3.Seeker
    public int getAverageBitrate() {
        return -2147483647;
    }

    @Override // androidx.media3.extractor.mp3.Seeker
    public long getDataEndPosition() {
        return -1;
    }

    @Override // androidx.media3.extractor.mp3.Seeker
    public long getDataStartPosition() {
        return 0;
    }

    @Override // androidx.media3.extractor.SeekMap
    public long getDurationUs() {
        return this.durationUs;
    }

    @Override // androidx.media3.extractor.SeekMap
    public SeekMap.SeekPoints getSeekPoints(long r7) {
        Pair<Long, Long> r6 = linearlyInterpolate(Util.usToMs(Util.constrainValue(r7, 0, this.durationUs)), this.referenceTimesMs, this.referencePositions);
        return new SeekMap.SeekPoints(new SeekPoint(Util.msToUs(((Long) r6.first).longValue()), ((Long) r6.second).longValue()));
    }

    @Override // androidx.media3.extractor.mp3.Seeker
    public long getTimeUs(long r2) {
        return Util.msToUs(((Long) linearlyInterpolate(r2, this.referencePositions, this.referenceTimesMs).second).longValue());
    }

    @Override // androidx.media3.extractor.SeekMap
    public boolean isSeekable() {
        return true;
    }
}
