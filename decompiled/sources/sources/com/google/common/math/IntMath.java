package com.google.common.math;

import com.android.dx.AppDataDirGuesser;
import com.google.android.gms.cast.MediaError;
import com.google.common.base.Preconditions;
import com.google.common.primitives.Ints;
import java.math.RoundingMode;
import kotlin.time.DurationKt;
import okio.AsyncTimeout$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
public abstract class IntMath {
    static final int[] biggestBinomials = null;
    private static final int[] factorials = null;
    static final int[] halfPowersOf10 = null;
    static final byte[] maxLog10ForLeadingZeros = null;
    static final int[] powersOf10 = null;

    /* renamed from: com.google.common.math.IntMath$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$java$math$RoundingMode = null;

        static {
            int[] r0 = new int[RoundingMode.values().length];
            $SwitchMap$java$math$RoundingMode = r0;
            r0[RoundingMode.UNNECESSARY.ordinal()] = 1;     // Catch: NoSuchFieldError -> L12
        L20:
            $SwitchMap$java$math$RoundingMode[RoundingMode.DOWN.ordinal()] = 2;     // Catch: NoSuchFieldError -> L13
        L24:
            $SwitchMap$java$math$RoundingMode[RoundingMode.FLOOR.ordinal()] = 3;     // Catch: NoSuchFieldError -> L14
        L34:
            $SwitchMap$java$math$RoundingMode[RoundingMode.UP.ordinal()] = 4;     // Catch: NoSuchFieldError -> L15
        L22:
            $SwitchMap$java$math$RoundingMode[RoundingMode.CEILING.ordinal()] = 5;     // Catch: NoSuchFieldError -> L16
        L26:
            $SwitchMap$java$math$RoundingMode[RoundingMode.HALF_DOWN.ordinal()] = 6;     // Catch: NoSuchFieldError -> L17
        L28:
            $SwitchMap$java$math$RoundingMode[RoundingMode.HALF_UP.ordinal()] = 7;     // Catch: NoSuchFieldError -> L18
        L30:
            $SwitchMap$java$math$RoundingMode[RoundingMode.HALF_EVEN.ordinal()] = 8;     // Catch: NoSuchFieldError -> L19
            return;
        }
    }

    static {
        maxLog10ForLeadingZeros = new byte[]{9, 9, 9, 8, 8, 8, 7, 7, 7, 6, 6, 6, 6, 5, 5, 5, 4, 4, 4, 3, 3, 3, 3, 2, 2, 2, 1, 1, 1, 0, 0, 0, 0};
        powersOf10 = new int[]{1, 10, 100, 1000, 10000, AppDataDirGuesser.PER_USER_RANGE, DurationKt.NANOS_IN_MILLIS, 10000000, 100000000, 1000000000};
        halfPowersOf10 = new int[]{3, 31, MediaError.DetailedErrorCode.HLS_SEGMENT_PARSING, 3162, 31622, 316227, 3162277, 31622776, 316227766, Integer.MAX_VALUE};
        factorials = new int[]{1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880, 3628800, 39916800, 479001600};
        biggestBinomials = new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE, 65536, 2345, 477, 193, 110, 75, 58, 49, 43, 39, 37, 35, 34, 34, 33};
    }

    public static int checkedAdd(int r0, int r1) {
        return Math.addExact(r0, r1);
    }

    public static int divide(int r5, int r6, RoundingMode r7) {
        Preconditions.checkNotNull(r7);
        if (r6 == 0) goto L42;
        int r0 = r5 / r6;
        int r1 = r5 - (r6 * r0);
        if (r1 != 0) goto L7;
        return r0;
    L7:
        boolean r2 = true;
        int r52 = ((r5 ^ r6) >> 31) | 1;
        switch(AnonymousClass1.$SwitchMap$java$math$RoundingMode[r7.ordinal()]) {
            case 1: goto L36;
            case 2: goto L40;
            case 3: goto L33;
            case 4: goto L35;
            case 5: goto L30;
            case 6: goto L11;
            case 7: goto L11;
            case 8: goto L11;
            default: goto L9;
        };
    L9:
        AsyncTimeout$$ExternalSyntheticBUOutline0.m();
        return 0;
    L11:
        int r12 = Math.abs(r1);
        int r13 = r12 - (Math.abs(r6) - r12);
        if (r13 == 0) goto L14;
        if (r13 > 0) goto L35;
        return r0;
    L14:
        if (r7 == RoundingMode.HALF_UP) goto L35;
        if (r7 != RoundingMode.HALF_EVEN) goto L18;
        boolean r62 = true;
    L20:
        if ((r0 & 1) != 0) goto L24;
        r2 = false;
    L24:
        if ((r62 & r2) == true) goto L35;
        return r0;
    L18:
        r62 = false;
        goto L20
    L30:
        if (r52 > 0) goto L35;
        return r0;
    L33:
        if (r52 < 0) goto L35;
        return r0;
    L36:
        if (r1 == 0) goto L39;
        r2 = false;
    L39:
        MathPreconditions.checkRoundingUnnecessary(r2);
    L40:
        return r0;
    L35:
        return r0 + r52;
    L42:
        throw new ArithmeticException("/ by zero");
    }

    public static boolean isPowerOfTwo(int r4) {
        boolean r0 = false;
        if (r4 <= 0) goto L5;
        boolean r2 = true;
    L7:
        if ((r4 & (r4 - 1)) != 0) goto L10;
        r0 = true;
    L10:
        return r2 & r0;
    L5:
        r2 = false;
        goto L7
    }

    public static int lessThanBranchFree(int r0, int r1) {
        return (~(~(r0 - r1))) >>> 31;
    }

    public static int log2(int r1, RoundingMode r2) {
        MathPreconditions.checkPositive("x", r1);
        switch(AnonymousClass1.$SwitchMap$java$math$RoundingMode[r2.ordinal()]) {
            case 1: goto L10;
            case 2: goto L12;
            case 3: goto L12;
            case 4: goto L9;
            case 5: goto L9;
            case 6: goto L6;
            case 7: goto L6;
            case 8: goto L6;
            default: goto L4;
        };
    L4:
        AsyncTimeout$$ExternalSyntheticBUOutline0.m();
        return 0;
    L6:
        int r22 = Integer.numberOfLeadingZeros(r1);
        return (31 - r22) + lessThanBranchFree((-1257966797) >>> r22, r1);
    L10:
        MathPreconditions.checkRoundingUnnecessary(isPowerOfTwo(r1));
        goto L12
    L9:
        return 32 - Integer.numberOfLeadingZeros(r1 - 1);
    L12:
        return 31 - Integer.numberOfLeadingZeros(r1);
    }

    public static int saturatedMultiply(int r2, int r3) {
        return Ints.saturatedCast(r2 * r3);
    }
}
