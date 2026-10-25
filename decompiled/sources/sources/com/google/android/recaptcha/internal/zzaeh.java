package com.google.android.recaptcha.internal;

import kotlin.UByte;
import kotlin.jvm.internal.ByteCompanionObject;

/* loaded from: classes5.dex */
abstract class zzaeh {
    public static final /* synthetic */ int $r8$clinit = 0;
    private static volatile int zzb = 100;

    static {
    }

    public static int zza(byte[] r1, int r2, zzaeg r3) {
        int r0 = r2 + 1;
        byte r22 = r1[r2];
        if (r22 < 0) goto L7;
        r3.zza = r22;
        return r0;
    L7:
        return zzb(r22, r1, r0, r3);
    }

    public static int zzb(int r2, byte[] r3, int r4, zzaeg r5) {
        byte r0 = r3[r4];
        int r1 = r4 + 1;
        int r22 = r2 & 127;
        if (r0 < 0) goto L6;
        r5.zza = r22 | (r0 << 7);
        return r1;
    L6:
        int r23 = r22 | ((r0 & ByteCompanionObject.MAX_VALUE) << 7);
        int r02 = r4 + 2;
        byte r12 = r3[r1];
        if (r12 < 0) goto L10;
        r5.zza = r23 | (r12 << 14);
        return r02;
    L10:
        int r24 = r23 | ((r12 & ByteCompanionObject.MAX_VALUE) << 14);
        int r13 = r4 + 3;
        byte r03 = r3[r02];
        if (r03 < 0) goto L14;
        r5.zza = r24 | (r03 << 21);
        return r13;
    L14:
        int r25 = r24 | ((r03 & ByteCompanionObject.MAX_VALUE) << 21);
        int r42 = r4 + 4;
        byte r04 = r3[r13];
        if (r04 < 0) goto L18;
        r5.zza = r25 | (r04 << 28);
        return r42;
    L18:
        int r26 = r25 | ((r04 & ByteCompanionObject.MAX_VALUE) << 28);
    L19:
        int r05 = r42 + 1;
        if (r3[r42] >= 0) goto L22;
        r42 = r05;
        goto L19
    L22:
        r5.zza = r26;
        return r05;
    }

    public static int zzc(byte[] r9, int r10, zzaeg r11) {
        long r0 = r9[r10];
        int r3 = r10 + 1;
        if (r0 < 0) goto L6;
        r11.zzb = r0;
        return r3;
    L6:
        int r102 = r10 + 2;
        byte r2 = r9[r3];
        long r02 = (r0 & 127) | ((r2 & ByteCompanionObject.MAX_VALUE) << 7);
        int r32 = 7;
    L7:
        if (r2 >= 0) goto L9;
        int r22 = r102 + 1;
        r32 = r32 + 7;
        r02 = r02 | ((r10 & ByteCompanionObject.MAX_VALUE) << r32);
        r2 = r9[r102];
        r102 = r22;
        goto L7
    L9:
        r11.zzb = r02;
        return r102;
    }

    public static int zzd(byte[] r3, int r4) {
        int r0 = r3[r4] & UByte.MAX_VALUE;
        int r1 = r3[r4 + 1] & UByte.MAX_VALUE;
        int r2 = r3[r4 + 2] & UByte.MAX_VALUE;
        int r32 = r3[r4 + 3] & UByte.MAX_VALUE;
        int r33 = r32 << 24;
        return r33 | (((r1 << 8) | r0) | (r2 << 16));
    }

    public static long zze(byte[] r18, int r19) {
        return (((((((r18[r19] & 255) | ((r18[r19 + 1] & 255) << 8)) | ((r18[r19 + 2] & 255) << 16)) | ((r18[r19 + 3] & 255) << 24)) | ((r18[r19 + 4] & 255) << 32)) | ((r18[r19 + 5] & 255) << 40)) | ((r18[r19 + 6] & 255) << 48)) | ((r18[r19 + 7] & 255) << 56);
    }

    public static int zzf(byte[] r3, int r4, zzaeg r5) {
        int r42 = zza(r3, r4, r5);
        int r0 = r5.zza;
        if (r0 >= 0) goto L5;
        zzaeh$$ExternalSyntheticBUOutline0.m("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
        return 0;
    L5:
        if (r0 > (r3.length - r42)) goto L11;
        if (r0 != 0) goto L9;
        r5.zzc = zzaer.zza;
        return r42;
    L9:
        r5.zzc = zzaer.zzs(r3, r42, r0);
        return r42 + r0;
    L11:
        zzaeh$$ExternalSyntheticBUOutline0.m("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        return 0;
    }

    public static int zzg(zzahn r6, byte[] r7, int r8, int r9, zzaeg r10) {
        Object r0 = r6.zza();
        int r62 = zzi(r0, r6, r7, r8, r9, r10);
        r6.zzk(r0);
        r10.zzc = r0;
        return r62;
    }

    public static int zzh(zzahn r7, byte[] r8, int r9, int r10, int r11, zzaeg r12) {
        Object r0 = r7.zza();
        int r72 = zzj(r0, r7, r8, r9, r10, r11, r12);
        r7.zzk(r0);
        r12.zzc = r0;
        return r72;
    }

    public static int zzi(Object r6, zzahn r7, byte[] r8, int r9, int r10, zzaeg r11) {
        int r0 = r9 + 1;
        int r92 = r8[r9];
        if (r92 >= 0) goto L5;
        r0 = zzb(r92, r8, r0, r11);
        r92 = r11.zza;
    L5:
        int r3 = r0;
        if (r92 >= 0) goto L8;
    L11:
        zzaeh$$ExternalSyntheticBUOutline0.m("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        return 0;
    L8:
        if (r92 > (r10 - r3)) goto L11;
        int r102 = r11.zze + 1;
        r11.zze = r102;
        zzo(r102);
        int r4 = r3 + r92;
        r7.zzj(r6, r8, r3, r4, r11);
        r11.zze--;
        r11.zzc = r6;
        return r4;
    }

    public static int zzj(Object r2, zzahn r3, byte[] r4, int r5, int r6, int r7, zzaeg r8) {
        int r0 = r8.zze + 1;
        r8.zze = r0;
        zzo(r0);
        int r22 = ((zzaha) r3).zzi(r2, r4, r5, r6, r7, r8);
        r8.zze--;
        r8.zzc = r2;
        return r22;
    }

    public static int zzk(int r2, byte[] r3, int r4, int r5, zzage r6, zzaeg r7) {
        zzafw r62 = (zzafw) r6;
        int r42 = zza(r3, r4, r7);
        r62.zzh(r7.zza);
    L3:
        if (r42 >= r5) goto L8;
        int r0 = zza(r3, r42, r7);
        if (r2 != r7.zza) goto L8;
        r42 = zza(r3, r0, r7);
        r62.zzh(r7.zza);
    L8:
        return r42;
    }

    public static int zzl(byte[] r4, int r5, zzage r6, zzaeg r7) {
        zzafw r62 = (zzafw) r6;
        int r52 = zza(r4, r5, r7);
        int r0 = r7.zza;
        if (r0 >= 0) goto L5;
        zzaeh$$ExternalSyntheticBUOutline0.m("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
        return 0;
    L5:
        if (r0 > (r4.length - r52)) goto L13;
        int r02 = r0 + r52;
    L7:
        if (r52 >= r02) goto L9;
        r52 = zza(r4, r52, r7);
        r62.zzh(r7.zza);
        goto L7
    L9:
        if (r52 != r02) goto L11;
        return r52;
    L11:
        zzaeh$$ExternalSyntheticBUOutline0.m("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        return 0;
    L13:
        zzaeh$$ExternalSyntheticBUOutline0.m("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        return 0;
    }

    public static int zzm(zzahn r2, int r3, byte[] r4, int r5, int r6, zzage r7, zzaeg r8) {
        int r52 = zzg(r2, r4, r5, r6, r8);
        r7.add(r8.zzc);
    L3:
        if (r52 >= r6) goto L8;
        int r0 = zza(r4, r52, r8);
        if (r3 != r8.zza) goto L8;
        r52 = zzg(r2, r4, r0, r6, r8);
        r7.add(r8.zzc);
    L8:
        return r52;
    }

    public static int zzn(int r10, byte[] r11, int r12, int r13, zzaia r14, zzaeg r15) {
        if ((r10 >>> 3) == 0) goto L49;
        int r0 = r10 & 7;
        if (r0 != 0) goto L7;
        int r112 = zzc(r11, r12, r15);
        r14.zzk(r10, Long.valueOf(r15.zzb));
        return r112;
    L7:
        if (r0 != 1) goto L9;
        r14.zzk(r10, Long.valueOf(zze(r11, r12)));
        return r12 + 8;
    L9:
        if (r0 != 2) goto L11;
        int r113 = zza(r11, r12, r15);
        int r122 = r15.zza;
        if (r122 >= 0) goto L35;
        zzaeh$$ExternalSyntheticBUOutline0.m("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
        return 0;
    L35:
        if (r122 > (r11.length - r113)) goto L41;
        if (r122 != 0) goto L38;
        r14.zzk(r10, zzaer.zza);
    L40:
        return r113 + r122;
    L38:
        r14.zzk(r10, zzaer.zzs(r11, r113, r122));
        goto L40
    L41:
        zzaeh$$ExternalSyntheticBUOutline0.m("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        return 0;
    L11:
        if (r0 != 3) goto L13;
        int r02 = (r10 & (-8)) | 4;
        zzaia r8 = zzaia.zzb();
        int r2 = r15.zze + 1;
        r15.zze = r2;
        zzo(r2);
        int r22 = 0;
    L19:
        if (r12 >= r13) goto L25;
        int r6 = zza(r11, r12, r15);
        int r4 = r15.zza;
        if (r4 == r02) goto L22;
        r12 = zzn(r4, r11, r6, r13, r8, r15);
        r22 = r4;
        goto L19
    L22:
        r22 = r4;
        r12 = r6;
    L25:
        r15.zze--;
        if (r12 > r13) goto L30;
        if (r22 != r02) goto L30;
        r14.zzk(r10, r8);
        return r12;
    L30:
        zzaeh$$ExternalSyntheticBUOutline0.m("Failed to parse the message.");
        return 0;
    L13:
        if (r0 != 5) goto L16;
        r14.zzk(r10, Integer.valueOf(zzd(r11, r12)));
        return r12 + 4;
    L16:
        zzaeh$$ExternalSyntheticBUOutline0.m("Protocol message contained an invalid tag (zero).");
        return 0;
    L49:
        zzaeh$$ExternalSyntheticBUOutline0.m("Protocol message contained an invalid tag (zero).");
        return 0;
    }

    private static void zzo(int r1) {
        if (r1 >= zzb) goto L5;
        return;
    L5:
        zzaeh$$ExternalSyntheticBUOutline0.m("Protocol message had too many levels of nesting.  May be malicious.  Use setRecursionLimit() to increase the recursion depth limit.");
    }
}
