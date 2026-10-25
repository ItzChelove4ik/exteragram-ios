package com.google.android.gms.internal.vision;

import kotlin.UByte;
import kotlin.jvm.internal.ByteCompanionObject;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes5.dex */
abstract class zzhl {
    public static int zza(int r2, byte[] r3, int r4, int r5, zzjl<?> r6, zzhn r7) {
        zzjd r62 = (zzjd) r6;
        int r42 = zza(r3, r4, r7);
        r62.zzc(r7.zza);
    L3:
        if (r42 >= r5) goto L7;
        int r0 = zza(r3, r42, r7);
        if (r2 != r7.zza) goto L7;
        r42 = zza(r3, r0, r7);
        r62.zzc(r7.zza);
    L7:
        return r42;
    }

    public static int zza(int r7, byte[] r8, int r9, int r10, zzlx r11, zzhn r12) {
        if ((r7 >>> 3) == 0) goto L49;
        int r0 = r7 & 7;
        if (r0 != 0) goto L7;
        int r82 = zzb(r8, r9, r12);
        r11.zza(r7, Long.valueOf(r12.zzb));
        return r82;
    L7:
        if (r0 != 1) goto L9;
        r11.zza(r7, Long.valueOf(zzb(r8, r9)));
        return r9 + 8;
    L9:
        if (r0 != 2) goto L11;
        int r83 = zza(r8, r9, r12);
        int r92 = r12.zza;
        if (r92 < 0) goto L43;
        if (r92 > (r8.length - r83)) goto L41;
        if (r92 != 0) goto L37;
        r11.zza(r7, zzht.zza);
    L39:
        return r83 + r92;
    L37:
        r11.zza(r7, zzht.zza(r8, r83, r92));
        goto L39
    L41:
        throw zzjk.zza();
    L43:
        throw zzjk.zzb();
    L11:
        if (r0 != 3) goto L13;
        zzlx r4 = zzlx.zzb();
        int r6 = (r7 & (-8)) | 4;
        int r02 = 0;
    L19:
        if (r9 >= r10) goto L25;
        int r2 = zza(r8, r9, r12);
        r02 = r12.zza;
        if (r02 == r6) goto L23;
        r9 = zza(r02, r8, r2, r10, r4, r12);
        goto L19
    L23:
        r9 = r2;
    L25:
        if (r9 > r10) goto L30;
        if (r02 != r6) goto L30;
        r11.zza(r7, r4);
        return r9;
    L30:
        throw zzjk.zzg();
    L13:
        if (r0 != 5) goto L17;
        r11.zza(r7, Integer.valueOf(zza(r8, r9)));
        return r9 + 4;
    L17:
        throw zzjk.zzd();
    L49:
        throw zzjk.zzd();
    }

    public static int zza(int r2, byte[] r3, int r4, zzhn r5) {
        int r22 = r2 & 127;
        int r0 = r4 + 1;
        byte r1 = r3[r4];
        if (r1 < 0) goto L6;
        r5.zza = r22 | (r1 << 7);
        return r0;
    L6:
        int r23 = r22 | ((r1 & ByteCompanionObject.MAX_VALUE) << 7);
        int r12 = r4 + 2;
        byte r02 = r3[r0];
        if (r02 < 0) goto L10;
        r5.zza = r23 | (r02 << 14);
        return r12;
    L10:
        int r24 = r23 | ((r02 & ByteCompanionObject.MAX_VALUE) << 14);
        int r03 = r4 + 3;
        byte r13 = r3[r12];
        if (r13 < 0) goto L14;
        r5.zza = r24 | (r13 << 21);
        return r03;
    L14:
        int r25 = r24 | ((r13 & ByteCompanionObject.MAX_VALUE) << 21);
        int r42 = r4 + 4;
        byte r04 = r3[r03];
        if (r04 < 0) goto L18;
        r5.zza = r25 | (r04 << 28);
        return r42;
    L18:
        int r26 = r25 | ((r04 & ByteCompanionObject.MAX_VALUE) << 28);
    L19:
        int r05 = r42 + 1;
        if (r3[r42] >= 0) goto L21;
        r42 = r05;
        goto L19
    L21:
        r5.zza = r26;
        return r05;
    }

    public static int zza(zzlc<?> r2, int r3, byte[] r4, int r5, int r6, zzjl<?> r7, zzhn r8) {
        int r52 = zza(r2, r4, r5, r6, r8);
        r7.add(r8.zzc);
    L3:
        if (r52 >= r6) goto L7;
        int r0 = zza(r4, r52, r8);
        if (r3 != r8.zza) goto L7;
        r52 = zza(r2, r4, r0, r6, r8);
        r7.add(r8.zzc);
    L7:
        return r52;
    }

    public static int zza(zzlc r7, byte[] r8, int r9, int r10, int r11, zzhn r12) {
        zzko r0 = (zzko) r7;
        Object r1 = r0.zza();
        int r72 = r0.zza(r1, r8, r9, r10, r11, r12);
        r0.zzc(r1);
        r12.zzc = r1;
        return r72;
    }

    public static int zza(zzlc r6, byte[] r7, int r8, int r9, zzhn r10) {
        int r0 = r8 + 1;
        int r82 = r7[r8];
        if (r82 >= 0) goto L5;
        r0 = zza(r82, r7, r0, r10);
        r82 = r10.zza;
    L5:
        int r3 = r0;
        if (r82 < 0) goto L12;
        if (r82 > (r9 - r3)) goto L12;
        Object r1 = r6.zza();
        int r4 = r3 + r82;
        r6.zza(r1, r7, r3, r4, r10);
        r6.zzc(r1);
        r10.zzc = r1;
        return r4;
    L12:
        throw zzjk.zza();
    }

    public static int zza(byte[] r2, int r3) {
        int r0 = ((r2[r3] & UByte.MAX_VALUE) | ((r2[r3 + 1] & UByte.MAX_VALUE) << 8)) | ((r2[r3 + 2] & UByte.MAX_VALUE) << 16);
        return ((r2[r3 + 3] & UByte.MAX_VALUE) << 24) | r0;
    }

    public static int zza(byte[] r1, int r2, zzhn r3) {
        int r0 = r2 + 1;
        byte r22 = r1[r2];
        if (r22 < 0) goto L7;
        r3.zza = r22;
        return r0;
    L7:
        return zza(r22, r1, r0, r3);
    }

    public static int zza(byte[] r2, int r3, zzjl<?> r4, zzhn r5) {
        zzjd r42 = (zzjd) r4;
        int r32 = zza(r2, r3, r5);
        int r0 = r5.zza + r32;
    L3:
        if (r32 >= r0) goto L5;
        r32 = zza(r2, r32, r5);
        r42.zzc(r5.zza);
        goto L3
    L5:
        if (r32 != r0) goto L8;
        return r32;
    L8:
        throw zzjk.zza();
    }

    public static int zzb(byte[] r9, int r10, zzhn r11) {
        int r0 = r10 + 1;
        long r1 = r9[r10];
        if (r1 < 0) goto L6;
        r11.zzb = r1;
        return r0;
    L6:
        int r102 = r10 + 2;
        byte r02 = r9[r0];
        long r12 = (r1 & 127) | ((r02 & ByteCompanionObject.MAX_VALUE) << 7);
        int r3 = 7;
    L7:
        if (r02 >= 0) goto L9;
        int r03 = r102 + 1;
        r3 = r3 + 7;
        r12 = r12 | ((r10 & ByteCompanionObject.MAX_VALUE) << r3);
        r02 = r9[r102];
        r102 = r03;
        goto L7
    L9:
        r11.zzb = r12;
        return r102;
    }

    public static long zzb(byte[] r7, int r8) {
        return ((r7[r8 + 7] & 255) << 56) | (((((((r7[r8] & 255) | ((r7[r8 + 1] & 255) << 8)) | ((r7[r8 + 2] & 255) << 16)) | ((r7[r8 + 3] & 255) << 24)) | ((r7[r8 + 4] & 255) << 32)) | ((r7[r8 + 5] & 255) << 40)) | ((r7[r8 + 6] & 255) << 48));
    }

    public static double zzc(byte[] r0, int r1) {
        return Double.longBitsToDouble(zzb(r0, r1));
    }

    public static int zzc(byte[] r3, int r4, zzhn r5) {
        int r42 = zza(r3, r4, r5);
        int r0 = r5.zza;
        if (r0 < 0) goto L10;
        if (r0 != 0) goto L7;
        r5.zzc = _UrlKt.FRAGMENT_ENCODE_SET;
        return r42;
    L7:
        r5.zzc = new String(r3, r42, r0, zzjf.zza);
        return r42 + r0;
    L10:
        throw zzjk.zzb();
    }

    public static float zzd(byte[] r0, int r1) {
        return Float.intBitsToFloat(zza(r0, r1));
    }

    public static int zzd(byte[] r1, int r2, zzhn r3) {
        int r22 = zza(r1, r2, r3);
        int r0 = r3.zza;
        if (r0 < 0) goto L10;
        if (r0 != 0) goto L7;
        r3.zzc = _UrlKt.FRAGMENT_ENCODE_SET;
        return r22;
    L7:
        r3.zzc = zzmd.zzb(r1, r22, r0);
        return r22 + r0;
    L10:
        throw zzjk.zzb();
    }

    public static int zze(byte[] r2, int r3, zzhn r4) {
        int r32 = zza(r2, r3, r4);
        int r0 = r4.zza;
        if (r0 < 0) goto L14;
        if (r0 > (r2.length - r32)) goto L12;
        if (r0 != 0) goto L9;
        r4.zzc = zzht.zza;
        return r32;
    L9:
        r4.zzc = zzht.zza(r2, r32, r0);
        return r32 + r0;
    L12:
        throw zzjk.zza();
    L14:
        throw zzjk.zzb();
    }
}
