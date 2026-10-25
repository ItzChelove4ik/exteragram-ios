package androidx.media3.exoplayer.hls;

import android.util.SparseArray;
import androidx.media3.common.util.TimestampAdjuster;

/* loaded from: classes4.dex */
public final class TimestampAdjusterProvider {
    private final SparseArray<TimestampAdjuster> timestampAdjusters;

    public TimestampAdjusterProvider() {
        this.timestampAdjusters = new SparseArray();
    }

    public TimestampAdjuster getAdjuster(int r4) {
        TimestampAdjuster r0 = this.timestampAdjusters.get(r4);
        if (r0 != null) goto L6;
        TimestampAdjuster r02 = new TimestampAdjuster(9223372036854775806L);
        this.timestampAdjusters.put(r4, r02);
        return r02;
    L6:
        return r0;
    }

    public void reset() {
        this.timestampAdjusters.clear();
    }
}
