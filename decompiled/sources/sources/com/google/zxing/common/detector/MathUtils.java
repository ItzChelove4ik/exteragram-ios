package com.google.zxing.common.detector;

/* loaded from: classes5.dex */
public abstract class MathUtils {
    public static float distance(float r2, float r3, float r4, float r5) {
        double r0 = r2 - r4;
        double r22 = r3 - r5;
        return (float) Math.sqrt((r0 * r0) + (r22 * r22));
    }

    public static float distance(int r2, int r3, int r4, int r5) {
        double r0 = r2 - r4;
        double r22 = r3 - r5;
        return (float) Math.sqrt((r0 * r0) + (r22 * r22));
    }

    public static int round(float r1) {
        if (r1 >= 0.0f) goto L5;
        float r0 = -0.5f;
    L7:
        return (int) (r1 + r0);
    L5:
        r0 = 0.5f;
        goto L7
    }
}
