package com.google.zxing;

import com.google.zxing.common.detector.MathUtils;

/* loaded from: classes5.dex */
public abstract class ResultPoint {
    private final float x;
    private final float y;

    public ResultPoint(float r1, float r2) {
        this.x = r1;
        this.y = r2;
    }

    private static float crossProductZ(ResultPoint r3, ResultPoint r4, ResultPoint r5) {
        float r0 = r4.x;
        float r42 = r4.y;
        return ((r5.x - r0) * (r3.y - r42)) - ((r5.y - r42) * (r3.x - r0));
    }

    public static float distance(ResultPoint r2, ResultPoint r3) {
        return MathUtils.distance(r2.x, r2.y, r3.x, r3.y);
    }

    public static void orderBestPatterns(ResultPoint[] r9) {
        float r1 = distance(r9[0], r9[1]);
        float r3 = distance(r9[1], r9[2]);
        float r5 = distance(r9[0], r9[2]);
        if (r3 < r1) goto L8;
        if (r3 < r5) goto L8;
        ResultPoint r12 = r9[0];
        ResultPoint r32 = r9[1];
        ResultPoint r52 = r9[2];
    L14:
        if (crossProductZ(r32, r12, r52) >= 0.0f) goto L16;
        ResultPoint r8 = r52;
        r52 = r32;
        r32 = r8;
    L16:
        r9[0] = r32;
        r9[1] = r12;
        r9[2] = r52;
        return;
    L8:
        if (r5 >= r3) goto L10;
    L12:
        r12 = r9[2];
        r32 = r9[0];
        r52 = r9[1];
        goto L14
    L10:
        if (r5 < r1) goto L12;
        r12 = r9[1];
        r32 = r9[0];
        r52 = r9[2];
        goto L14
    }

    public final boolean equals(Object r4) {
        if ((r4 instanceof ResultPoint) == false) goto L10;
        ResultPoint r42 = (ResultPoint) r4;
        if (this.x != r42.x) goto L10;
        if (this.y != r42.y) goto L10;
        return true;
    L10:
        return false;
    }

    public final float getX() {
        return this.x;
    }

    public final float getY() {
        return this.y;
    }

    public final int hashCode() {
        return (Float.floatToIntBits(this.x) * 31) + Float.floatToIntBits(this.y);
    }

    public final String toString() {
        return "(" + this.x + ',' + this.y + ')';
    }
}
