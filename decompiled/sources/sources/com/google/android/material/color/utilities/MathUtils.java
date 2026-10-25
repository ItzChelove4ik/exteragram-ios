package com.google.android.material.color.utilities;

/* loaded from: classes5.dex */
public class MathUtils {
    private MathUtils() {
    }

    public static double clampDouble(double r1, double r3, double r5) {
        if (r5 >= r1) goto L6;
        return r1;
    L6:
        if (r5 <= r3) goto L8;
        return r3;
    L8:
        return r5;
    }

    public static int clampInt(int r0, int r1, int r2) {
        if (r2 >= r0) goto L4;
        return r0;
    L4:
        if (r2 <= r1) goto L6;
        return r1;
    L6:
        return r2;
    }

    public static double differenceDegrees(double r0, double r2) {
        return 180.0d - Math.abs(Math.abs(r0 - r2) - 180.0d);
    }

    public static double lerp(double r2, double r4, double r6) {
        return ((1.0d - r6) * r2) + (r6 * r4);
    }

    public static double[] matrixMultiply(double[] r16, double[][] r17) {
        double r1 = r16[0];
        double[] r3 = r17[0];
        double r4 = r3[0] * r1;
        double r7 = r16[1];
        double r42 = r4 + (r3[1] * r7);
        double r10 = r16[2];
        double r43 = r42 + (r3[2] * r10);
        double[] r32 = r17[1];
        double r12 = ((r32[0] * r1) + (r32[1] * r7)) + (r32[2] * r10);
        double[] r33 = r17[2];
        return new double[]{r43, r12, ((r1 * r33[0]) + (r7 * r33[1])) + (r10 * r33[2])};
    }

    public static double rotationDirection(double r0, double r2) {
        if (sanitizeDegreesDouble(r2 - r0) > 180.0d) goto L6;
        return 1.0d;
    L6:
        return -1.0d;
    }

    public static double sanitizeDegreesDouble(double r4) {
        double r42 = r4 % 360.0d;
        if (r42 < 0.0d) goto L5;
        return r42;
    L5:
        return r42 + 360.0d;
    }

    public static int sanitizeDegreesInt(int r0) {
        int r02 = r0 % 360;
        if (r02 < 0) goto L5;
        return r02;
    L5:
        return r02 + 360;
    }

    public static int signum(double r3) {
        if (r3 >= 0.0d) goto L7;
        return -1;
    L7:
        if (r3 != 0.0d) goto L10;
        return 0;
    L10:
        return 1;
    }
}
