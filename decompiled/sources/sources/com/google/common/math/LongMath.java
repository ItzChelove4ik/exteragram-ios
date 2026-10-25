package com.google.common.math;

import androidx.mediarouter.media.GlobalMediaRouter;
import com.android.dx.io.Opcodes;
import com.google.android.gms.cast.framework.media.NotificationOptions;
import com.google.common.base.Preconditions;
import java.math.RoundingMode;
import kotlin.jvm.internal.LongCompanionObject;
import okhttp3.internal.connection.RealConnection;
import okio.AsyncTimeout$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
public abstract class LongMath {
    static final int[] biggestBinomials = null;
    static final int[] biggestSimpleBinomials = null;
    static final long[] factorials = null;
    static final long[] halfPowersOf10 = null;
    static final byte[] maxLog10ForLeadingZeros = null;
    private static final long[][] millerRabinBaseSets = null;
    static final long[] powersOf10 = null;

    /* renamed from: com.google.common.math.LongMath$1, reason: invalid class name */
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
        maxLog10ForLeadingZeros = new byte[]{19, 18, 18, 18, 18, 17, 17, 17, 16, 16, 16, 15, 15, 15, 15, 14, 14, 14, 13, 13, 13, 12, 12, 12, 12, 11, 11, 11, 10, 10, 10, 9, 9, 9, 9, 8, 8, 8, 7, 7, 7, 6, 6, 6, 6, 5, 5, 5, 4, 4, 4, 3, 3, 3, 3, 2, 2, 2, 1, 1, 1, 0, 0, 0};
        powersOf10 = new long[]{1, 10, 100, 1000, NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS, 100000, 1000000, 10000000, 100000000, 1000000000, RealConnection.IDLE_CONNECTION_HEALTHY_NS, 100000000000L, 1000000000000L, 10000000000000L, 100000000000000L, 1000000000000000L, 10000000000000000L, 100000000000000000L, 1000000000000000000L};
        halfPowersOf10 = new long[]{3, 31, 316, 3162, 31622, 316227, 3162277, 31622776, 316227766, 3162277660L, 31622776601L, 316227766016L, 3162277660168L, 31622776601683L, 316227766016837L, 3162277660168379L, 31622776601683793L, 316227766016837933L, 3162277660168379331L};
        factorials = new long[]{1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880, 3628800, 39916800, 479001600, 6227020800L, 87178291200L, 1307674368000L, 20922789888000L, 355687428096000L, 6402373705728000L, 121645100408832000L, 2432902008176640000L};
        biggestBinomials = new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 3810779, 121977, 16175, 4337, 1733, 887, 534, 361, GlobalMediaRouter.CallbackHandler.MSG_ROUTE_CONNECTED, Opcodes.DIV_DOUBLE_2ADDR, 169, 143, 125, 111, 101, 94, 88, 83, 79, 76, 74, 72, 70, 69, 68, 67, 67, 66, 66, 66, 66};
        biggestSimpleBinomials = new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 2642246, 86251, 11724, 3218, 1313, 684, 419, 287, Opcodes.OR_INT_LIT16, 169, 139, 119, 105, 95, 87, 81, 76, 73, 70, 68, 66, 64, 63, 62, 62, 61, 61, 61};
        millerRabinBaseSets = new long[][]{new long[]{291830, 126401071349994536L}, new long[]{885594168, 725270293939359937L, 3569819667048198375L}, new long[]{273919523040L, 15, 7363882082L, 992620450144556L}, new long[]{47636622961200L, 2, 2570940, 211991001, 3749873356L}, new long[]{7999252175582850L, 2, 4130806001517L, 149795463772692060L, 186635894390467037L, 3967304179347715805L}, new long[]{585226005592931976L, 2, 123635709730000L, 9233062284813009L, 43835965440333360L, 761179012939631437L, 1263739024124850375L}, new long[]{LongCompanionObject.MAX_VALUE, 2, 325, 9375, 28178, 450775, 9780504, 1795265022}};
    }

    public static long checkedAdd(long r0, long r2) {
        return Math.addExact(r0, r2);
    }

    public static long divide(long r9, long r11, RoundingMode r13) {
        Preconditions.checkNotNull(r13);
        long r0 = r9 / r11;
        long r2 = r9 - (r11 * r0);
        if (r2 != 0) goto L5;
        return r0;
    L5:
        boolean r10 = true;
        int r92 = ((int) ((r9 ^ r11) >> 63)) | 1;
        switch(AnonymousClass1.$SwitchMap$java$math$RoundingMode[r13.ordinal()]) {
            case 1: goto L28;
            case 2: goto L32;
            case 3: goto L25;
            case 4: goto L27;
            case 5: goto L22;
            case 6: goto L9;
            case 7: goto L9;
            case 8: goto L9;
            default: goto L7;
        };
    L7:
        AsyncTimeout$$ExternalSyntheticBUOutline0.m();
        return 0;
    L9:
        long r22 = Math.abs(r2);
        long r23 = r22 - (Math.abs(r11) - r22);
        if (r23 == 0) goto L12;
        if (r23 > 0) goto L27;
        return r0;
    L12:
        if (r13 == RoundingMode.HALF_UP) goto L27;
        if (r13 == RoundingMode.HALF_EVEN) goto L16;
    L18:
        return r0;
    L16:
        if ((1 & r0) == 0) goto L18;
    L22:
        if (r92 > 0) goto L27;
        return r0;
    L25:
        if (r92 < 0) goto L27;
        return r0;
    L28:
        if (r2 == 0) goto L31;
        r10 = false;
    L31:
        MathPreconditions.checkRoundingUnnecessary(r10);
    L32:
        return r0;
    L27:
        return r0 + r92;
    }

    public static long gcd(long r4, long r6) {
        MathPreconditions.checkNonNegative("a", r4);
        MathPreconditions.checkNonNegative("b", r6);
        if (r4 != 0) goto L6;
        return r6;
    L6:
        if (r6 != 0) goto L8;
        return r4;
    L8:
        int r0 = Long.numberOfTrailingZeros(r4);
        long r42 = r4 >> r0;
        int r1 = Long.numberOfTrailingZeros(r6);
        long r62 = r6 >> r1;
    L10:
        if (r42 == r62) goto L13;
        long r43 = r42 - r62;
        long r2 = (r43 >> 63) & r43;
        long r44 = (r43 - r2) - r2;
        r62 = r62 + r2;
        r42 = r44 >> Long.numberOfTrailingZeros(r44);
        goto L10
    L13:
        return r42 << Math.min(r0, r1);
    }

    public static long saturatedMultiply(long r9, long r11) {
        int r0 = ((Long.numberOfLeadingZeros(r9) + Long.numberOfLeadingZeros(~r9)) + Long.numberOfLeadingZeros(r11)) + Long.numberOfLeadingZeros(~r11);
        if (r0 > 65) goto L5;
        long r1 = ((r9 ^ r11) >>> 63) + LongCompanionObject.MAX_VALUE;
        boolean r4 = false;
        if (r0 >= 64) goto L9;
        boolean r02 = true;
    L11:
        if (r9 >= 0) goto L13;
        boolean r6 = true;
    L15:
        if (r11 != Long.MIN_VALUE) goto L18;
        r4 = true;
    L18:
        if ((r02 | (r4 & r6)) == true) goto L25;
        long r42 = r9 * r11;
        if (r9 != 0) goto L23;
    L26:
        return r42;
    L23:
        if ((r42 / r9) == r11) goto L26;
    L25:
        return r1;
    L13:
        r6 = false;
        goto L15
    L9:
        r02 = false;
        goto L11
    L5:
        return r9 * r11;
    }
}
