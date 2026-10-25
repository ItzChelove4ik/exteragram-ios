package androidx.media3.exoplayer.source.chunk;

import retrofit2.Utils$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
public abstract class BaseMediaChunkIterator implements MediaChunkIterator {
    private long currentIndex;
    private final long fromIndex;
    private final long toIndex;

    public BaseMediaChunkIterator(long r1, long r3) {
        this.fromIndex = r1;
        this.toIndex = r3;
        reset();
    }

    public final void checkInBounds() {
        long r0 = this.currentIndex;
        if (r0 >= this.fromIndex) goto L5;
    L7:
        Utils$$ExternalSyntheticBUOutline0.m();
        return;
    L5:
        if (r0 > this.toIndex) goto L7;
    }

    public final long getCurrentIndex() {
        return this.currentIndex;
    }

    public boolean isEnded() {
        if (this.currentIndex <= this.toIndex) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // androidx.media3.exoplayer.source.chunk.MediaChunkIterator
    public boolean next() {
        this.currentIndex++;
        return !isEnded();
    }

    public void reset() {
        this.currentIndex = this.fromIndex - 1;
    }
}
