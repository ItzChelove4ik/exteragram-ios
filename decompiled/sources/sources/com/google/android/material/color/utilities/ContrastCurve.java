package com.google.android.material.color.utilities;

/* loaded from: classes5.dex */
public final class ContrastCurve {
    private final double high;
    private final double low;
    private final double medium;
    private final double normal;

    public ContrastCurve(double r1, double r3, double r5, double r7) {
        this.low = r1;
        this.normal = r3;
        this.medium = r5;
        this.high = r7;
    }

    public double get(double r14) {
        if (r14 > (-1.0d)) goto L7;
        return this.low;
    L7:
        if (r14 >= 0.0d) goto L11;
        return MathUtils.lerp(this.low, this.normal, (r14 - (-1.0d)) / 1.0d);
    L11:
        if (r14 >= 0.5d) goto L15;
        return MathUtils.lerp(this.normal, this.medium, (r14 - 0.0d) / 0.5d);
    L15:
        if (r14 >= 1.0d) goto L19;
        return MathUtils.lerp(this.medium, this.high, (r14 - 0.5d) / 0.5d);
    L19:
        return this.high;
    }
}
