package com.google.common.math;

import com.google.common.base.Preconditions;

/* loaded from: classes5.dex */
abstract class DoubleUtils {
    public static long getSignificand(double r3) {
        Preconditions.checkArgument(isFinite(r3), "not a normal value");
        int r0 = Math.getExponent(r3);
        long r32 = Double.doubleToRawLongBits(r3) & 4503599627370495L;
        if (r0 != (-1023)) goto L7;
        return r32 << 1;
    L7:
        return r32 | 4503599627370496L;
    }

    public static boolean isFinite(double r0) {
        if (Math.getExponent(r0) > 1023) goto L6;
        return true;
    L6:
        return false;
    }

    public static boolean isNormal(double r0) {
        if (Math.getExponent(r0) < (-1022)) goto L6;
        return true;
    L6:
        return false;
    }

    public static double scaleNormalize(double r2) {
        return Double.longBitsToDouble((Double.doubleToRawLongBits(r2) & 4503599627370495L) | 4607182418800017408L);
    }
}
