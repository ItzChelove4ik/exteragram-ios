package com.google.common.math;

import com.google.common.base.Preconditions;
import java.math.RoundingMode;
import okio.AsyncTimeout$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
public abstract class DoubleMath {
    private static final double LN_2 = 0.0d;
    static final double[] everySixteenthFactorial = null;

    /* renamed from: com.google.common.math.DoubleMath$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$java$math$RoundingMode = null;

        static {
            int[] r0 = new int[RoundingMode.values().length];
            $SwitchMap$java$math$RoundingMode = r0;
            r0[RoundingMode.UNNECESSARY.ordinal()] = 1;     // Catch: NoSuchFieldError -> L12
        L20:
            $SwitchMap$java$math$RoundingMode[RoundingMode.FLOOR.ordinal()] = 2;     // Catch: NoSuchFieldError -> L13
        L24:
            $SwitchMap$java$math$RoundingMode[RoundingMode.CEILING.ordinal()] = 3;     // Catch: NoSuchFieldError -> L14
        L34:
            $SwitchMap$java$math$RoundingMode[RoundingMode.DOWN.ordinal()] = 4;     // Catch: NoSuchFieldError -> L15
        L22:
            $SwitchMap$java$math$RoundingMode[RoundingMode.UP.ordinal()] = 5;     // Catch: NoSuchFieldError -> L16
        L26:
            $SwitchMap$java$math$RoundingMode[RoundingMode.HALF_EVEN.ordinal()] = 6;     // Catch: NoSuchFieldError -> L17
        L28:
            $SwitchMap$java$math$RoundingMode[RoundingMode.HALF_UP.ordinal()] = 7;     // Catch: NoSuchFieldError -> L18
        L30:
            $SwitchMap$java$math$RoundingMode[RoundingMode.HALF_DOWN.ordinal()] = 8;     // Catch: NoSuchFieldError -> L19
            return;
        }
    }

    static {
        LN_2 = Math.log(2.0d);
        everySixteenthFactorial = new double[]{1.0d, 2.0922789888E13d, 2.631308369336935E35d, 1.2413915592536073E61d, 1.2688693218588417E89d, 7.156945704626381E118d, 9.916779348709496E149d, 1.974506857221074E182d, 3.856204823625804E215d, 5.5502938327393044E249d, 4.7147236359920616E284d};
    }

    public static boolean fuzzyEquals(double r4, double r6, double r8) {
        MathPreconditions.checkNonNegative("tolerance", r8);
        if (Math.copySign(r4 - r6, 1.0d) > r8) goto L5;
        return true;
    L5:
        if (r4 != r6) goto L7;
        return true;
    L7:
        if (Double.isNaN(r4) == true) goto L9;
        return false;
    L9:
        if (Double.isNaN(r6) == true) goto L17;
        return false;
    L17:
        return true;
    }

    public static boolean isMathematicalInteger(double r2) {
        if (DoubleUtils.isFinite(r2) == true) goto L5;
        return false;
    L5:
        if (r2 != 0.0d) goto L7;
        return true;
    L7:
        if ((52 - Long.numberOfTrailingZeros(DoubleUtils.getSignificand(r2))) > Math.getExponent(r2)) goto L13;
        return true;
    L13:
        return false;
    }

    public static boolean isPowerOfTwo(double r4) {
        if (r4 > 0.0d) goto L5;
    L10:
        return false;
    L5:
        if (DoubleUtils.isFinite(r4) == false) goto L10;
        long r42 = DoubleUtils.getSignificand(r4);
        if ((r42 & (r42 - 1)) != 0) goto L10;
        return true;
    }

    public static int log2(double r5, RoundingMode r7) {
        boolean r1 = false;
        if (r5 > 0.0d) goto L5;
    L7:
        boolean r0 = false;
    L8:
        Preconditions.checkArgument(r0, "x must be positive and finite");
        int r02 = Math.getExponent(r5);
        if (DoubleUtils.isNormal(r5) == true) goto L13;
        return log2(r5 * 4.503599627370496E15d, r7) - 52;
    L13:
        switch(AnonymousClass1.$SwitchMap$java$math$RoundingMode[r7.ordinal()]) {
            case 1: goto L27;
            case 2: goto L28;
            case 3: goto L26;
            case 4: goto L23;
            case 5: goto L19;
            case 6: goto L16;
            case 7: goto L16;
            case 8: goto L16;
            default: goto L14;
        };
    L14:
        AsyncTimeout$$ExternalSyntheticBUOutline0.m();
        return 0;
    L16:
        double r52 = DoubleUtils.scaleNormalize(r5);
        if ((r52 * r52) <= 2.0d) goto L28;
        r1 = true;
        goto L28
    L19:
        if (r02 < 0) goto L21;
        r1 = true;
    L21:
        boolean r53 = isPowerOfTwo(r5);
    L22:
        r1 = r1 & (!r53);
        goto L28
    L23:
        if (r02 >= 0) goto L25;
        r1 = true;
    L25:
        r53 = isPowerOfTwo(r5);
        goto L22
    L26:
        r1 = !isPowerOfTwo(r5);
        goto L28
    L27:
        MathPreconditions.checkRoundingUnnecessary(isPowerOfTwo(r5));
    L28:
        if (r1 == true) goto L30;
        return r02;
    L30:
        return r02 + 1;
    L5:
        if (DoubleUtils.isFinite(r5) == false) goto L7;
        r0 = true;
        goto L8
    }

    public static double roundIntermediate(double r6, RoundingMode r8) {
        if (DoubleUtils.isFinite(r6) == false) goto L45;
        switch(AnonymousClass1.$SwitchMap$java$math$RoundingMode[r8.ordinal()]) {
            case 1: goto L42;
            case 2: goto L36;
            case 3: goto L29;
            case 4: goto L46;
            case 5: goto L20;
            case 6: goto L18;
            case 7: goto L12;
            case 8: goto L8;
            default: goto L6;
        };
    L6:
        AsyncTimeout$$ExternalSyntheticBUOutline0.m();
        return 0.0d;
    L8:
        double r0 = Math.rint(r6);
        if (Math.abs(r6 - r0) == 0.5d) goto L49;
        return r0;
    L49:
        return r6;
    L12:
        double r02 = Math.rint(r6);
        if (Math.abs(r6 - r02) == 0.5d) goto L15;
        return r02;
    L15:
        return r6 + Math.copySign(0.5d, r6);
    L42:
        MathPreconditions.checkRoundingUnnecessary(isMathematicalInteger(r6));
        return r6;
    L46:
        return r6;
    L18:
        return Math.rint(r6);
    L20:
        if (isMathematicalInteger(r6) == true) goto L50;
        long r03 = (long) r6;
        if (r6 <= 0.0d) goto L25;
        int r62 = 1;
    L27:
        return r03 + r62;
    L25:
        r62 = -1;
        goto L27
    L50:
        return r6;
    L29:
        if (r6 > 0.0d) goto L31;
        return r6;
    L31:
        if (isMathematicalInteger(r6) == false) goto L34;
        return r6;
    L34:
        return ((long) r6) + 1;
    L36:
        if (r6 < 0.0d) goto L38;
        return r6;
    L38:
        if (isMathematicalInteger(r6) == false) goto L41;
        return r6;
    L41:
        return ((long) r6) - 1;
    L45:
        throw new ArithmeticException("input is infinite or NaN");
    }

    public static long roundToLong(double r7, RoundingMode r9) {
        double r0 = roundIntermediate(r7, r9);
        boolean r3 = false;
        if (((-9.223372036854776E18d) - r0) >= 1.0d) goto L5;
        boolean r2 = true;
    L7:
        if (r0 >= 9.223372036854776E18d) goto L9;
        r3 = true;
    L9:
        MathPreconditions.checkInRangeForRoundingInputs(r2 & r3, r7, r9);
        return (long) r0;
    L5:
        r2 = false;
        goto L7
    }
}
