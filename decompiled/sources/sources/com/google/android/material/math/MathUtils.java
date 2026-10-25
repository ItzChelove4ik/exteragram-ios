package com.google.android.material.math;

/* loaded from: classes5.dex */
public final class MathUtils {
    public static final float DEFAULT_EPSILON = 1.0E-4f;

    private MathUtils() {
    }

    public static boolean areAllElementsEqual(float[] r5) {
        if (r5.length > 1) goto L5;
        return true;
    L5:
        float r2 = r5[0];
        int r3 = 1;
    L7:
        if (r3 >= r5.length) goto L12;
        if (r5[r3] != r2) goto L10;
        r3 = r3 + 1;
        goto L7
    L10:
        return false;
    L12:
        return true;
    }

    public static float dist(float r0, float r1, float r2, float r3) {
        return (float) Math.hypot(r2 - r0, r3 - r1);
    }

    public static float distanceToFurthestCorner(float r1, float r2, float r3, float r4, float r5, float r6) {
        return max(dist(r1, r2, r3, r4), dist(r1, r2, r5, r4), dist(r1, r2, r5, r6), dist(r1, r2, r3, r6));
    }

    public static float floorMod(float r3, int r4) {
        float r0 = r4;
        int r1 = (int) (r3 / r0);
        if ((Math.signum(r3) * r0) >= 0.0f) goto L8;
        if ((r1 * r4) == r3) goto L8;
        r1 = r1 - 1;
    L8:
        return r3 - (r1 * r4);
    }

    public static int floorMod(int r2, int r3) {
        int r0 = r2 / r3;
        if ((r2 ^ r3) >= 0) goto L8;
        if ((r0 * r3) == r2) goto L8;
        r0 = r0 - 1;
    L8:
        return r2 - (r0 * r3);
    }

    public static boolean geq(float r0, float r1, float r2) {
        if ((r0 + r2) < r1) goto L6;
        return true;
    L6:
        return false;
    }

    public static float lerp(float r1, float r2, float r3) {
        return ((1.0f - r3) * r1) + (r3 * r2);
    }

    private static float max(float r1, float r2, float r3, float r4) {
        if (r1 <= r2) goto L10;
        if (r1 <= r3) goto L10;
        if (r1 <= r4) goto L10;
        return r1;
    L10:
        if (r2 <= r3) goto L15;
        if (r2 <= r4) goto L15;
        return r2;
    L15:
        if (r3 <= r4) goto L17;
        return r3;
    L17:
        return r4;
    }
}
