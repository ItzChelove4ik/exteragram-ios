package androidx.interpolator.view.animation;

import android.view.animation.Interpolator;

/* loaded from: classes3.dex */
abstract class LookupTableInterpolator implements Interpolator {
    private final float mStepSize;
    private final float[] mValues;

    public LookupTableInterpolator(float[] r2) {
        this.mValues = r2;
        this.mStepSize = 1.0f / (r2.length - 1);
    }

    @Override // android.animation.TimeInterpolator
    public float getInterpolation(float r4) {
        if (r4 < 1.0f) goto L6;
        return 1.0f;
    L6:
        if (r4 > 0.0f) goto L8;
        return 0.0f;
    L8:
        float[] r0 = this.mValues;
        int r02 = Math.min((int) ((r0.length - 1) * r4), r0.length - 2);
        float r2 = this.mStepSize;
        float r42 = (r4 - (r02 * r2)) / r2;
        float[] r3 = this.mValues;
        float r1 = r3[r02];
        return r1 + (r42 * (r3[r02 + 1] - r1));
    }
}
