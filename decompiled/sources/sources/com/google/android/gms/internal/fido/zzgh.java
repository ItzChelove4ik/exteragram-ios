package com.google.android.gms.internal.fido;

import c.f$$ExternalSyntheticBUOutline1;
import java.math.RoundingMode;
import okio.AsyncTimeout$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
public abstract class zzgh {
    public static int zza(int r5, int r6, RoundingMode r7) {
        r7.getClass();
        if (r6 == 0) goto L35;
        int r0 = r5 / r6;
        int r1 = r5 - (r6 * r0);
        if (r1 == 0) goto L38;
        int r3 = 1;
        int r52 = ((r5 ^ r6) >> 31) | 1;
        switch(zzgg.zza[r7.ordinal()]) {
            case 1: goto L32;
            case 2: goto L36;
            case 3: goto L29;
            case 4: goto L31;
            case 5: goto L26;
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
        if (r13 > 0) goto L31;
        return r0;
    L14:
        if (r7 == RoundingMode.HALF_UP) goto L31;
        if (r7 == RoundingMode.HALF_EVEN) goto L20;
        r3 = 0;
    L20:
        if (((r0 & 1) & r3) != 0) goto L31;
        return r0;
    L26:
        if (r52 > 0) goto L31;
        return r0;
    L29:
        if (r52 < 0) goto L31;
        return r0;
    L32:
        zzgi.zza(false);
        return r0;
    L36:
        return r0;
    L31:
        return r0 + r52;
    L38:
        return r0;
    L35:
        throw new ArithmeticException("/ by zero");
    }

    public static int zzb(int r1, RoundingMode r2) {
        if (r1 > 0) goto L4;
        f$$ExternalSyntheticBUOutline1.m("x (0) must be > 0");
        return 0;
    L4:
        switch(zzgg.zza[r2.ordinal()]) {
            case 1: goto L12;
            case 2: goto L17;
            case 3: goto L17;
            case 4: goto L10;
            case 5: goto L10;
            case 6: goto L7;
            case 7: goto L7;
            case 8: goto L7;
            default: goto L5;
        };
    L5:
        AsyncTimeout$$ExternalSyntheticBUOutline0.m();
        return 0;
    L7:
        int r22 = Integer.numberOfLeadingZeros(r1);
        return (31 - r22) + ((((-1257966797) >>> r22) - r1) >>> 31);
    L10:
        return 32 - Integer.numberOfLeadingZeros(r1 - 1);
    L12:
        if (((r1 - 1) & r1) != 0) goto L14;
        boolean r23 = true;
    L15:
        zzgi.zza(r23);
        goto L17
    L14:
        r23 = false;
    L17:
        return 31 - Integer.numberOfLeadingZeros(r1);
    }
}
