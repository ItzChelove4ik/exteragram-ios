package com.google.android.recaptcha.internal;

import com.google.android.gms.cast.internal.zzar$$ExternalSyntheticBUOutline1;
import java.util.Objects;
import kotlin.UByte;

/* loaded from: classes5.dex */
abstract class zzpk {
    public static Object zza(int r2) {
        if (r2 >= 2) goto L5;
    L18:
        zzar$$ExternalSyntheticBUOutline1.m(String.valueOf(r2).length() + 41, "must be power of 2 between 2^1 and 2^30: ", r2);
        return null;
    L5:
        if (r2 > 1073741824) goto L18;
        if (Integer.highestOneBit(r2) != r2) goto L18;
        if (r2 > 256) goto L13;
        return new byte[r2];
    L13:
        if (r2 > 65536) goto L17;
        return new short[r2];
    L17:
        return new int[r2];
    }

    public static int zzb(Object r1, int r2) {
        if ((r1 instanceof byte[]) == false) goto L7;
        return ((byte[]) r1)[r2] & UByte.MAX_VALUE;
    L7:
        if ((r1 instanceof short[]) == false) goto L11;
        return (char) ((short[]) r1)[r2];
    L11:
        return ((int[]) r1)[r2];
    }

    public static void zzc(Object r1, int r2, int r3) {
        if ((r1 instanceof byte[]) == false) goto L7;
        ((byte[]) r1)[r2] = (byte) r3;
        return;
    L7:
        if ((r1 instanceof short[]) == false) goto L10;
        ((short[]) r1)[r2] = (short) r3;
        return;
    L10:
        ((int[]) r1)[r2] = r3;
    }

    public static int zzd(int r1) {
        if (r1 >= 32) goto L5;
        int r0 = 4;
    L7:
        return r0 * (r1 + 1);
    L5:
        r0 = 2;
        goto L7
    }

    public static int zze(Object r8, Object r9, int r10, Object r11, int[] r12, Object[] r13, Object[] r14) {
        int r0 = zzpn.zzb(r8);
        int r1 = r0 & r10;
        int r2 = zzb(r11, r1);
        if (r2 == 0) goto L19;
        int r4 = ~r10;
        int r02 = r0 & r4;
        int r5 = -1;
    L5:
        int r22 = r2 - 1;
        int r6 = r12[r22];
        int r7 = r6 & r10;
        if ((r6 & r4) == r02) goto L8;
    L17:
        if (r7 == 0) goto L19;
        r5 = r22;
        r2 = r7;
        goto L5
    L8:
        if (Objects.equals(r8, r13[r22]) == false) goto L17;
        if (r14 == null) goto L12;
        if (Objects.equals(r9, r14[r22]) == false) goto L17;
    L12:
        if (r5 != (-1)) goto L15;
        zzc(r11, r1, r7);
        return r22;
    L15:
        r12[r5] = (r12[r5] & r4) | (r7 & r10);
        return r22;
    L19:
        return -1;
    }
}
