package androidx.media3.common;

/* loaded from: classes3.dex */
public final class IllegalSeekPositionException extends IllegalStateException {
    public final long positionMs;
    public final Timeline timeline;
    public final int windowIndex;

    public IllegalSeekPositionException(Timeline r1, int r2, long r3) {
        this.timeline = r1;
        this.windowIndex = r2;
        this.positionMs = r3;
    }
}
