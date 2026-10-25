package androidx.media3.extractor.mp3;

import androidx.media3.common.util.Util;
import androidx.media3.extractor.IndexSeekMap;
import androidx.media3.extractor.SeekMap;
import java.math.RoundingMode;

/* loaded from: classes4.dex */
final class IndexSeeker implements Seeker {
    private final int averageBitrate;
    private final long dataEndPosition;
    private final long dataStartPosition;
    private final IndexSeekMap indexSeekMap;

    public IndexSeeker(long r13, long r15, long r17) {
        this.indexSeekMap = new IndexSeekMap(new long[]{r15}, new long[]{0}, r13);
        this.dataStartPosition = r15;
        this.dataEndPosition = r17;
        int r7 = -2147483647;
        if (r13 == (-9223372036854775807L)) goto L11;
        long r0 = Util.scaleLargeValue(r15 - r17, 8, r13, RoundingMode.HALF_UP);
        if (r0 > 0) goto L7;
    L9:
        this.averageBitrate = r7;
        return;
    L7:
        if (r0 > 2147483647L) goto L9;
        r7 = (int) r0;
        goto L9
    L11:
        this.averageBitrate = -2147483647;
    }

    @Override // androidx.media3.extractor.mp3.Seeker
    public int getAverageBitrate() {
        return this.averageBitrate;
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
        return this.indexSeekMap.getDurationUs();
    }

    @Override // androidx.media3.extractor.SeekMap
    public SeekMap.SeekPoints getSeekPoints(long r1) {
        return this.indexSeekMap.getSeekPoints(r1);
    }

    @Override // androidx.media3.extractor.mp3.Seeker
    public long getTimeUs(long r1) {
        return this.indexSeekMap.getTimeUs(r1);
    }

    @Override // androidx.media3.extractor.SeekMap
    public boolean isSeekable() {
        return this.indexSeekMap.isSeekable();
    }

    public boolean isTimeUsInIndex(long r3) {
        return this.indexSeekMap.isTimeUsInIndex(r3, 100000);
    }

    public void maybeAddSeekPoint(long r2, long r4) {
        if (isTimeUsInIndex(r2) == false) goto L5;
        return;
    L5:
        this.indexSeekMap.addSeekPoint(r2, r4);
    }

    public void setDurationUs(long r1) {
        this.indexSeekMap.setDurationUs(r1);
    }
}
