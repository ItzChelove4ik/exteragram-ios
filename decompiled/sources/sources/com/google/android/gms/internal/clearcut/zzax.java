package com.google.android.gms.internal.clearcut;

import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import kotlin.UByte;
import kotlin.jvm.internal.ByteCompanionObject;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
abstract class zzax {
    public static int zza(int r2, byte[] r3, int r4, int r5, zzay r6) {
        if ((r2 >>> 3) == 0) goto L35;
        int r0 = r2 & 7;
        if (r0 == 0) goto L33;
        if (r0 == 1) goto L31;
        if (r0 == 2) goto L29;
        if (r0 != 3) goto L13;
        int r22 = (r2 & (-8)) | 4;
        int r02 = 0;
    L19:
        if (r4 >= r5) goto L23;
        r4 = zza(r3, r4, r6);
        r02 = r6.zzfd;
        if (r02 == r22) goto L23;
        r4 = zza(r02, r3, r4, r5, r6);
    L23:
        if (r4 > r5) goto L27;
        if (r02 != r22) goto L27;
        return r4;
    L27:
        throw zzco.zzbo();
    L13:
        if (r0 != 5) goto L17;
        return r4 + 4;
    L17:
        throw zzco.zzbm();
    L29:
        return zza(r3, r4, r6) + r6.zzfd;
    L31:
        return r4 + 8;
    L33:
        return zzb(r3, r4, r6);
    L35:
        throw zzco.zzbm();
    }

    public static int zza(int r0, byte[] r1, int r2, int r3, zzcn<?> r4, zzay r5) {
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r4);
        zza(r1, r2, r5);
        int r02 = r5.zzfd;
        throw null;
    }

    public static int zza(int r7, byte[] r8, int r9, int r10, zzey r11, zzay r12) {
        if ((r7 >>> 3) == 0) goto L43;
        int r0 = r7 & 7;
        if (r0 != 0) goto L7;
        int r82 = zzb(r8, r9, r12);
        r11.zzb(r7, Long.valueOf(r12.zzfe));
        return r82;
    L7:
        if (r0 != 1) goto L9;
        r11.zzb(r7, Long.valueOf(zzd(r8, r9)));
        return r9 + 8;
    L9:
        if (r0 != 2) goto L11;
        int r83 = zza(r8, r9, r12);
        int r92 = r12.zzfd;
        if (r92 != 0) goto L35;
        Object r102 = zzbb.zzfi;
    L34:
        r11.zzb(r7, r102);
        return r83 + r92;
    L35:
        r102 = zzbb.zzb(r8, r83, r92);
        goto L34
    L11:
        if (r0 != 3) goto L13;
        zzey r4 = zzey.zzeb();
        int r6 = (r7 & (-8)) | 4;
        int r02 = 0;
    L19:
        if (r9 >= r10) goto L25;
        int r2 = zza(r8, r9, r12);
        r02 = r12.zzfd;
        if (r02 == r6) goto L23;
        r9 = zza(r02, r8, r2, r10, r4, r12);
        goto L19
    L23:
        r9 = r2;
    L25:
        if (r9 > r10) goto L30;
        if (r02 != r6) goto L30;
        r11.zzb(r7, r4);
        return r9;
    L30:
        throw zzco.zzbo();
    L13:
        if (r0 != 5) goto L17;
        r11.zzb(r7, Integer.valueOf(zzc(r8, r9)));
        return r9 + 4;
    L17:
        throw zzco.zzbm();
    L43:
        throw zzco.zzbm();
    }

    public static int zza(int r2, byte[] r3, int r4, zzay r5) {
        int r22 = r2 & 127;
        int r0 = r4 + 1;
        byte r1 = r3[r4];
        if (r1 < 0) goto L7;
        int r32 = r1 << 7;
    L5:
        r5.zzfd = r22 | r32;
        return r0;
    L7:
        int r23 = r22 | ((r1 & ByteCompanionObject.MAX_VALUE) << 7);
        int r12 = r4 + 2;
        byte r02 = r3[r0];
        if (r02 < 0) goto L11;
        r5.zzfd = r23 | (r02 << 14);
        return r12;
    L11:
        r22 = r23 | ((r02 & ByteCompanionObject.MAX_VALUE) << 14);
        r0 = r4 + 3;
        byte r13 = r3[r12];
        if (r13 < 0) goto L14;
        r32 = r13 << 21;
        goto L5
    L14:
        int r24 = r22 | ((r13 & ByteCompanionObject.MAX_VALUE) << 21);
        int r42 = r4 + 4;
        byte r03 = r3[r0];
        if (r03 < 0) goto L18;
        r5.zzfd = r24 | (r03 << 28);
        return r42;
    L18:
        int r25 = r24 | ((r03 & ByteCompanionObject.MAX_VALUE) << 28);
    L19:
        int r04 = r42 + 1;
        if (r3[r42] >= 0) goto L21;
        r42 = r04;
        goto L19
    L21:
        r5.zzfd = r25;
        return r04;
    }

    public static int zza(byte[] r1, int r2, zzay r3) {
        int r0 = r2 + 1;
        byte r22 = r1[r2];
        if (r22 < 0) goto L7;
        r3.zzfd = r22;
        return r0;
    L7:
        return zza(r22, r1, r0, r3);
    }

    public static int zza(byte[] r0, int r1, zzcn<?> r2, zzay r3) {
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r2);
        int r12 = zza(r0, r1, r3);
        int r22 = r3.zzfd + r12;
        if (r12 < r22) goto L8;
        if (r12 != r22) goto L7;
        return r12;
    L7:
        throw zzco.zzbl();
    L8:
        zza(r0, r12, r3);
        throw null;
    }

    public static int zzb(byte[] r9, int r10, zzay r11) {
        int r0 = r10 + 1;
        long r1 = r9[r10];
        if (r1 < 0) goto L6;
        r11.zzfe = r1;
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
        r11.zzfe = r12;
        return r102;
    }

    public static int zzc(byte[] r2, int r3) {
        int r0 = ((r2[r3] & UByte.MAX_VALUE) | ((r2[r3 + 1] & UByte.MAX_VALUE) << 8)) | ((r2[r3 + 2] & UByte.MAX_VALUE) << 16);
        return ((r2[r3 + 3] & UByte.MAX_VALUE) << 24) | r0;
    }

    public static int zzc(byte[] r3, int r4, zzay r5) {
        int r42 = zza(r3, r4, r5);
        int r0 = r5.zzfd;
        if (r0 != 0) goto L6;
        r5.zzff = _UrlKt.FRAGMENT_ENCODE_SET;
        return r42;
    L6:
        r5.zzff = new String(r3, r42, r0, zzci.UTF_8);
        return r42 + r0;
    }

    public static int zzd(byte[] r4, int r5, zzay r6) {
        int r52 = zza(r4, r5, r6);
        int r0 = r6.zzfd;
        if (r0 != 0) goto L6;
        r6.zzff = _UrlKt.FRAGMENT_ENCODE_SET;
        return r52;
    L6:
        int r1 = r52 + r0;
        if (zzff.zze(r4, r52, r1) == false) goto L11;
        r6.zzff = new String(r4, r52, r0, zzci.UTF_8);
        return r1;
    L11:
        throw zzco.zzbp();
    }

    public static long zzd(byte[] r7, int r8) {
        return ((r7[r8 + 7] & 255) << 56) | (((((((r7[r8] & 255) | ((r7[r8 + 1] & 255) << 8)) | ((r7[r8 + 2] & 255) << 16)) | ((r7[r8 + 3] & 255) << 24)) | ((r7[r8 + 4] & 255) << 32)) | ((r7[r8 + 5] & 255) << 40)) | ((r7[r8 + 6] & 255) << 48));
    }

    public static double zze(byte[] r0, int r1) {
        return Double.longBitsToDouble(zzd(r0, r1));
    }

    public static int zze(byte[] r1, int r2, zzay r3) {
        int r22 = zza(r1, r2, r3);
        int r0 = r3.zzfd;
        if (r0 != 0) goto L6;
        r3.zzff = zzbb.zzfi;
        return r22;
    L6:
        r3.zzff = zzbb.zzb(r1, r22, r0);
        return r22 + r0;
    }

    public static float zzf(byte[] r0, int r1) {
        return Float.intBitsToFloat(zzc(r0, r1));
    }
}
