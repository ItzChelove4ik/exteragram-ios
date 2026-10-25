package androidx.transition;

import java.util.Arrays;

/* loaded from: classes4.dex */
class VelocityTracker1D {
    private float[] mDataSamples;
    private int mIndex;
    private long[] mTimeSamples;

    public VelocityTracker1D() {
        long[] r1 = new long[20];
        this.mTimeSamples = r1;
        this.mDataSamples = new float[20];
        this.mIndex = 0;
        Arrays.fill(r1, Long.MIN_VALUE);
    }
}
