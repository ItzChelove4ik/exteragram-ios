package androidx.media3.extractor.mp4;

import androidx.media3.extractor.SniffFailure;

/* loaded from: classes4.dex */
public final class AtomSizeTooSmallSniffFailure implements SniffFailure {
    public final long atomSize;
    public final int atomType;
    public final int minimumHeaderSize;

    public AtomSizeTooSmallSniffFailure(int r1, long r2, int r4) {
        this.atomType = r1;
        this.atomSize = r2;
        this.minimumHeaderSize = r4;
    }
}
