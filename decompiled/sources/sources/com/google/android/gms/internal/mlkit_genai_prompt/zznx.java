package com.google.android.gms.internal.mlkit_genai_prompt;

import java.math.RoundingMode;
import okio.AsyncTimeout$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
public abstract class zznx {
    static {
        Math.log(2.0d);
    }

    public static long zza(double r8, RoundingMode r10) {
        if (zzny.zza(r8) == false) goto L56;
        boolean r1 = true;
        switch(zznw.zza[r10.ordinal()]) {
            case 1: goto L37;
            case 2: goto L31;
            case 3: goto L24;
            case 4: goto L38;
            case 5: goto L16;
            case 6: goto L14;
            case 7: goto L11;
            case 8: goto L8;
            default: goto L6;
        };
    L6:
        AsyncTimeout$$ExternalSyntheticBUOutline0.m();
        return 0;
    L8:
        double r2 = Math.rint(r8);
        if (Math.abs(r8 - r2) == 0.5d) goto L38;
    L40:
        if (((-9.223372036854776E18d) - r2) >= 1.0d) goto L42;
        boolean r0 = true;
    L44:
        if (r2 < 9.223372036854776E18d) goto L48;
        r1 = false;
    L48:
        if ((r0 & r1) == true) goto L50;
        String r102 = String.valueOf(r10);
        StringBuilder r22 = new StringBuilder((String.valueOf(r8).length() + 59) + r102.length());
        r22.append("rounded value is out of range for input ");
        r22.append(r8);
        r22.append(" and rounding mode ");
        r22.append(r102);
        throw new ArithmeticException(r22.toString());
    L50:
        return (long) r2;
    L42:
        r0 = false;
        goto L44
    L11:
        r2 = Math.rint(r8);
        if (Math.abs(r8 - r2) != 0.5d) goto L40;
        r2 = Math.copySign(0.5d, r8) + r8;
        goto L40
    L14:
        r2 = Math.rint(r8);
    L38:
        r2 = r8;
        goto L40
    L16:
        if (zzb(r8) == true) goto L38;
        long r4 = (long) r8;
        if (r8 <= 0.0d) goto L21;
        int r02 = 1;
    L22:
        r2 = r4 + r02;
        goto L40
    L21:
        r02 = -1;
        goto L22
    L24:
        if (r8 <= 0.0d) goto L38;
        if (zzb(r8) == true) goto L38;
        long r23 = (long) r8;
        long r42 = 1;
    L29:
        r2 = r23 + r42;
        goto L40
    L31:
        if (r8 >= 0.0d) goto L38;
        if (zzb(r8) == true) goto L38;
        r23 = (long) r8;
        r42 = -1;
        goto L29
    L37:
        if (zzb(r8) == true) goto L38;
        throw new ArithmeticException("mode was UNNECESSARY, but rounding was necessary");
    L56:
        throw new ArithmeticException("input is infinite or NaN");
    }

    public static boolean zzb(double r7) {
        if (zzny.zza(r7) == true) goto L5;
        return false;
    L5:
        if (r7 == 0.0d) goto L13;
        zzla.zza(zzny.zza(r7), "not a normal value");
        int r0 = Math.getExponent(r7);
        long r3 = Double.doubleToRawLongBits(r7) & 4503599627370495L;
        if (r0 != (-1023)) goto L9;
        long r32 = r3 + r3;
    L11:
        if ((52 - Long.numberOfTrailingZeros(r32)) <= Math.getExponent(r7)) goto L13;
        return false;
    L9:
        r32 = r3 | 4503599627370496L;
    L13:
        return true;
    }
}
