package androidx.media3.extractor.wav;

import androidx.media3.common.util.Util;
import androidx.media3.extractor.SeekMap;
import androidx.media3.extractor.SeekPoint;

/* loaded from: classes4.dex */
final class WavSeekMap implements SeekMap {
    private final long blockCount;
    private final long durationUs;
    private final long firstBlockPosition;
    private final int framesPerBlock;
    private final WavFormat wavFormat;

    public WavSeekMap(WavFormat r1, int r2, long r3, long r5) {
        this.wavFormat = r1;
        this.framesPerBlock = r2;
        this.firstBlockPosition = r3;
        long r52 = (r5 - r3) / r1.blockSize;
        this.blockCount = r52;
        this.durationUs = blockIndexToTimeUs(r52);
    }

    private long blockIndexToTimeUs(long r9) {
        return Util.scaleLargeTimestamp(r9 * this.framesPerBlock, 1000000, this.wavFormat.frameRateHz);
    }

    @Override // androidx.media3.extractor.SeekMap
    public long getDurationUs() {
        return this.durationUs;
    }

    @Override // androidx.media3.extractor.SeekMap
    public SeekMap.SeekPoints getSeekPoints(long r11) {
        long r0 = Util.constrainValue((this.wavFormat.frameRateHz * r11) / (this.framesPerBlock * 1000000), 0, this.blockCount - 1);
        long r4 = this.firstBlockPosition + (this.wavFormat.blockSize * r0);
        long r6 = blockIndexToTimeUs(r0);
        SeekPoint r8 = new SeekPoint(r6, r4);
        if (r6 >= r11) goto L10;
        if (r0 == (this.blockCount - 1)) goto L10;
        long r02 = r0 + 1;
        return new SeekMap.SeekPoints(r8, new SeekPoint(blockIndexToTimeUs(r02), this.firstBlockPosition + (this.wavFormat.blockSize * r02)));
    L10:
        return new SeekMap.SeekPoints(r8);
    }

    @Override // androidx.media3.extractor.SeekMap
    public boolean isSeekable() {
        return true;
    }
}
