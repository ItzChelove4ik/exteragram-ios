package com.google.android.gms.internal.mlkit_vision_subject_segmentation;

import kotlin.CharCodeKt$$ExternalSyntheticBUOutline0;
import kotlin.UByte;

/* loaded from: classes4.dex */
abstract class zzam {
    public static int zza(int r1) {
        if (r1 >= 32) goto L5;
        int r0 = 4;
    L7:
        return r0 * (r1 + 1);
    L5:
        r0 = 2;
        goto L7
    }

    public static int zzb(Object r8, Object r9, int r10, Object r11, int[] r12, Object[] r13, Object[] r14) {
        int r0 = zzan.zza(r8);
        int r1 = r0 & r10;
        int r2 = zzc(r11, r1);
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
        if (zze.zza(r8, r13[r22]) == false) goto L17;
        if (r14 == null) goto L12;
        if (zze.zza(r9, r14[r22]) == false) goto L17;
    L12:
        if (r5 != (-1)) goto L15;
        zze(r11, r1, r7);
        return r22;
    L15:
        r12[r5] = (r12[r5] & r4) | (r7 & r10);
        return r22;
    L19:
        return -1;
    }

    public static int zzc(Object r1, int r2) {
        if ((r1 instanceof byte[]) == false) goto L7;
        return ((byte[]) r1)[r2] & UByte.MAX_VALUE;
    L7:
        if ((r1 instanceof short[]) == false) goto L11;
        return (char) ((short[]) r1)[r2];
    L11:
        return ((int[]) r1)[r2];
    }

    public static Object zzd(int r1) {
        if (r1 >= 2) goto L5;
    L18:
        CharCodeKt$$ExternalSyntheticBUOutline0.m("must be power of 2 between 2^1 and 2^30: ", r1);
        return null;
    L5:
        if (r1 > 1073741824) goto L18;
        if (Integer.highestOneBit(r1) != r1) goto L18;
        if (r1 > 256) goto L13;
        return new byte[r1];
    L13:
        if (r1 > 65536) goto L17;
        return new short[r1];
    L17:
        return new int[r1];
    }

    public static void zze(Object r1, int r2, int r3) {
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
}
