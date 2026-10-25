package com.google.common.math;

import java.math.RoundingMode;

/* loaded from: classes5.dex */
abstract class MathPreconditions {
    public static void checkInRangeForRoundingInputs(boolean r2, double r3, RoundingMode r5) {
        if (r2 == false) goto L5;
        return;
    L5:
        throw new ArithmeticException("rounded value is out of range for input " + r3 + " and rounding mode " + r5);
    }

    public static double checkNonNegative(String r2, double r3) {
        if (r3 < 0.0d) goto L6;
        return r3;
    L6:
        throw new IllegalArgumentException(r2 + " (" + r3 + ") must be >= 0");
    }

    public static long checkNonNegative(String r2, long r3) {
        if (r3 < 0) goto L6;
        return r3;
    L6:
        throw new IllegalArgumentException(r2 + " (" + r3 + ") must be >= 0");
    }

    public static int checkPositive(String r2, int r3) {
        if (r3 <= 0) goto L5;
        return r3;
    L5:
        throw new IllegalArgumentException(r2 + " (" + r3 + ") must be > 0");
    }

    public static void checkRoundingUnnecessary(boolean r1) {
        if (r1 == false) goto L5;
        return;
    L5:
        throw new ArithmeticException("mode was UNNECESSARY, but rounding was necessary");
    }
}
