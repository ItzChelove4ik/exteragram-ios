package androidx.media3.extractor;

import androidx.media3.extractor.SeekMap;

/* loaded from: classes4.dex */
public final class SingleSampleSeekMap implements SeekMap {
    private final long durationUs;
    private final long startPosition;

    public SingleSampleSeekMap(long r3) {
        this(r3, 0);
    }

    public SingleSampleSeekMap(long r1, long r3) {
        this.durationUs = r1;
        this.startPosition = r3;
    }

    @Override // androidx.media3.extractor.SeekMap
    public long getDurationUs() {
        return this.durationUs;
    }

    @Override // androidx.media3.extractor.SeekMap
    public SeekMap.SeekPoints getSeekPoints(long r5) {
        return new SeekMap.SeekPoints(new SeekPoint(r5, this.startPosition));
    }

    @Override // androidx.media3.extractor.SeekMap
    public boolean isSeekable() {
        return true;
    }
}
