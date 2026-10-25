package com.google.android.gms.internal.play_billing;

import com.google.protobuf.Utf8$Processor$$ExternalSyntheticBUOutline0;
import kotlin.UByte;
import kotlin.jvm.internal.ByteCompanionObject;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes5.dex */
abstract class zzdx {
    public static final /* synthetic */ int $r8$clinit = 0;
    private static volatile int zzb = 100;

    static {
    }

    public static int zza(byte[] r3, int r4, zzdw r5) {
        int r42 = zzi(r3, r4, r5);
        int r0 = r5.zza;
        if (r0 >= 0) goto L5;
        zzdx$$ExternalSyntheticBUOutline0.m("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
        return 0;
    L5:
        if (r0 > (r3.length - r42)) goto L11;
        if (r0 != 0) goto L9;
        r5.zzc = zzei.zzb;
        return r42;
    L9:
        r5.zzc = zzei.zzj(r3, r42, r0);
        return r42 + r0;
    L11:
        zzdx$$ExternalSyntheticBUOutline0.m("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        return 0;
    }

    public static int zzb(byte[] r3, int r4) {
        int r0 = r3[r4] & UByte.MAX_VALUE;
        int r1 = r3[r4 + 1] & UByte.MAX_VALUE;
        int r2 = r3[r4 + 2] & UByte.MAX_VALUE;
        int r32 = r3[r4 + 3] & UByte.MAX_VALUE;
        int r33 = r32 << 24;
        return r33 | (((r1 << 8) | r0) | (r2 << 16));
    }

    public static int zzc(zzgv r7, byte[] r8, int r9, int r10, int r11, zzdw r12) {
        Object r0 = r7.zze();
        int r72 = zzm(r0, r7, r8, r9, r10, r11, r12);
        r7.zzf(r0);
        r12.zzc = r0;
        return r72;
    }

    public static int zzd(zzgv r6, byte[] r7, int r8, int r9, zzdw r10) {
        Object r0 = r6.zze();
        int r62 = zzn(r0, r6, r7, r8, r9, r10);
        r6.zzf(r0);
        r10.zzc = r0;
        return r62;
    }

    public static int zze(zzgv r2, int r3, byte[] r4, int r5, int r6, zzfn r7, zzdw r8) {
        int r52 = zzd(r2, r4, r5, r6, r8);
        r7.add(r8.zzc);
    L3:
        if (r52 >= r6) goto L8;
        int r0 = zzi(r4, r52, r8);
        if (r3 != r8.zza) goto L8;
        r52 = zzd(r2, r4, r0, r6, r8);
        r7.add(r8.zzc);
    L8:
        return r52;
    }

    public static int zzf(byte[] r2, int r3, zzfn r4, zzdw r5) {
        zzfj r42 = (zzfj) r4;
        int r32 = zzi(r2, r3, r5);
        int r0 = r5.zza + r32;
    L3:
        if (r32 >= r0) goto L5;
        r32 = zzi(r2, r32, r5);
        r42.zzg(r5.zza);
        goto L3
    L5:
        if (r32 != r0) goto L7;
        return r32;
    L7:
        zzdx$$ExternalSyntheticBUOutline0.m("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        return 0;
    }

    public static int zzg(byte[] r9, int r10, zzdw r11) {
        int r102 = zzi(r9, r10, r11);
        int r0 = r11.zza;
        if (r0 < 0) goto L46;
        if (r0 != 0) goto L7;
        r11.zzc = _UrlKt.FRAGMENT_ENCODE_SET;
        return r102;
    L7:
        int r2 = zzhr.$r8$clinit;
        int r22 = r9.length;
        if ((((r22 - r102) - r0) | (r102 | r0)) < 0) goto L44;
        int r23 = r102 + r0;
        char[] r7 = new char[r0];
        int r02 = 0;
    L10:
        if (r102 >= r23) goto L14;
        byte r3 = r9[r102];
        if (zzhp.zzd(r3) == false) goto L14;
        r102 = r102 + 1;
        r7[r02] = (char) r3;
        r02 = r02 + 1;
    L14:
        int r8 = r02;
    L15:
        if (r102 >= r23) goto L42;
        int r03 = r102 + 1;
        byte r32 = r9[r102];
        if (zzhp.zzd(r32) == true) goto L18;
        if (r32 < (-32)) goto L25;
        if (r32 < (-16)) goto L33;
        if (r03 >= (r23 - 2)) goto L40;
        byte r04 = r9[r03];
        int r5 = r102 + 3;
        byte r4 = r9[r102 + 2];
        r102 = r102 + 4;
        zzhp.zza(r32, r04, r4, r9[r5], r7, r8);
        r8 = r8 + 2;
        goto L15
    L40:
        zzdx$$ExternalSyntheticBUOutline0.m("Protocol message had invalid UTF-8.");
        return 0;
    L33:
        if (r03 >= (r23 - 1)) goto L35;
        int r42 = r8 + 1;
        int r52 = r102 + 2;
        r102 = r102 + 3;
        zzhp.zzb(r32, r9[r03], r9[r52], r7, r8);
    L27:
        r8 = r42;
        goto L15
    L35:
        zzdx$$ExternalSyntheticBUOutline0.m("Protocol message had invalid UTF-8.");
        return 0;
    L25:
        if (r03 >= r23) goto L28;
        r42 = r8 + 1;
        r102 = r102 + 2;
        zzhp.zzc(r32, r9[r03], r7, r8);
        goto L27
    L28:
        zzdx$$ExternalSyntheticBUOutline0.m("Protocol message had invalid UTF-8.");
        return 0;
    L18:
        r7[r8] = (char) r32;
        r8 = r8 + 1;
        r102 = r03;
    L19:
        if (r102 >= r23) goto L15;
        byte r05 = r9[r102];
        if (zzhp.zzd(r05) == false) goto L15;
        r102 = r102 + 1;
        r7[r8] = (char) r05;
        r8 = r8 + 1;
        goto L19
    L42:
        r11.zzc = new String(r7, 0, r8);
        return r23;
    L44:
        Utf8$Processor$$ExternalSyntheticBUOutline0.m("buffer length=%d, index=%d, size=%d", new Object[]{Integer.valueOf(r22), Integer.valueOf(r102), Integer.valueOf(r0)});
        return 0;
    L46:
        zzdx$$ExternalSyntheticBUOutline0.m("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
        return 0;
    }

    public static int zzh(int r10, byte[] r11, int r12, int r13, zzhi r14, zzdw r15) {
        if ((r10 >>> 3) == 0) goto L49;
        int r0 = r10 & 7;
        if (r0 != 0) goto L7;
        int r112 = zzl(r11, r12, r15);
        r14.zzj(r10, Long.valueOf(r15.zzb));
        return r112;
    L7:
        if (r0 != 1) goto L9;
        r14.zzj(r10, Long.valueOf(zzo(r11, r12)));
        return r12 + 8;
    L9:
        if (r0 != 2) goto L11;
        int r113 = zzi(r11, r12, r15);
        int r122 = r15.zza;
        if (r122 >= 0) goto L35;
        zzdx$$ExternalSyntheticBUOutline0.m("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
        return 0;
    L35:
        if (r122 > (r11.length - r113)) goto L41;
        if (r122 != 0) goto L38;
        r14.zzj(r10, zzei.zzb);
    L40:
        return r113 + r122;
    L38:
        r14.zzj(r10, zzei.zzj(r11, r113, r122));
        goto L40
    L41:
        zzdx$$ExternalSyntheticBUOutline0.m("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        return 0;
    L11:
        if (r0 != 3) goto L13;
        int r02 = (r10 & (-8)) | 4;
        zzhi r8 = zzhi.zzf();
        int r2 = r15.zze + 1;
        r15.zze = r2;
        zzp(r2);
        int r22 = 0;
    L19:
        if (r12 >= r13) goto L25;
        int r6 = zzi(r11, r12, r15);
        int r4 = r15.zza;
        if (r4 == r02) goto L22;
        r12 = zzh(r4, r11, r6, r13, r8, r15);
        r22 = r4;
        goto L19
    L22:
        r22 = r4;
        r12 = r6;
    L25:
        r15.zze--;
        if (r12 > r13) goto L30;
        if (r22 != r02) goto L30;
        r14.zzj(r10, r8);
        return r12;
    L30:
        zzdx$$ExternalSyntheticBUOutline0.m("Failed to parse the message.");
        return 0;
    L13:
        if (r0 != 5) goto L16;
        r14.zzj(r10, Integer.valueOf(zzb(r11, r12)));
        return r12 + 4;
    L16:
        zzdx$$ExternalSyntheticBUOutline0.m("Protocol message contained an invalid tag (zero).");
        return 0;
    L49:
        zzdx$$ExternalSyntheticBUOutline0.m("Protocol message contained an invalid tag (zero).");
        return 0;
    }

    public static int zzi(byte[] r1, int r2, zzdw r3) {
        int r0 = r2 + 1;
        byte r22 = r1[r2];
        if (r22 < 0) goto L7;
        r3.zza = r22;
        return r0;
    L7:
        return zzj(r22, r1, r0, r3);
    }

    public static int zzj(int r2, byte[] r3, int r4, zzdw r5) {
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

    public static int zzk(int r2, byte[] r3, int r4, int r5, zzfn r6, zzdw r7) {
        zzfj r62 = (zzfj) r6;
        int r42 = zzi(r3, r4, r7);
        r62.zzg(r7.zza);
    L3:
        if (r42 >= r5) goto L8;
        int r0 = zzi(r3, r42, r7);
        if (r2 != r7.zza) goto L8;
        r42 = zzi(r3, r0, r7);
        r62.zzg(r7.zza);
    L8:
        return r42;
    }

    public static int zzl(byte[] r9, int r10, zzdw r11) {
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

    public static int zzm(Object r2, zzgv r3, byte[] r4, int r5, int r6, int r7, zzdw r8) {
        int r0 = r8.zze + 1;
        r8.zze = r0;
        zzp(r0);
        int r22 = ((zzgo) r3).zzc(r2, r4, r5, r6, r7, r8);
        r8.zze--;
        r8.zzc = r2;
        return r22;
    }

    public static int zzn(Object r6, zzgv r7, byte[] r8, int r9, int r10, zzdw r11) {
        int r0 = r9 + 1;
        int r92 = r8[r9];
        if (r92 >= 0) goto L5;
        r0 = zzj(r92, r8, r0, r11);
        r92 = r11.zza;
    L5:
        int r3 = r0;
        if (r92 >= 0) goto L8;
    L11:
        zzdx$$ExternalSyntheticBUOutline0.m("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
        return 0;
    L8:
        if (r92 > (r10 - r3)) goto L11;
        int r102 = r11.zze + 1;
        r11.zze = r102;
        zzp(r102);
        int r4 = r3 + r92;
        r7.zzh(r6, r8, r3, r4, r11);
        r11.zze--;
        r11.zzc = r6;
        return r4;
    }

    public static long zzo(byte[] r18, int r19) {
        return (((((((r18[r19] & 255) | ((r18[r19 + 1] & 255) << 8)) | ((r18[r19 + 2] & 255) << 16)) | ((r18[r19 + 3] & 255) << 24)) | ((r18[r19 + 4] & 255) << 32)) | ((r18[r19 + 5] & 255) << 40)) | ((r18[r19 + 6] & 255) << 48)) | ((r18[r19 + 7] & 255) << 56);
    }

    private static void zzp(int r1) {
        if (r1 >= zzb) goto L5;
        return;
    L5:
        zzdx$$ExternalSyntheticBUOutline0.m("Protocol message had too many levels of nesting.  May be malicious.  Use setRecursionLimit() to increase the recursion depth limit.");
    }
}
