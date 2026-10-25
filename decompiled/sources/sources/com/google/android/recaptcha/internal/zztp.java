package com.google.android.recaptcha.internal;

import java.security.MessageDigest;
import java.util.Arrays;
import kotlin.UByte;
import kotlin.jvm.internal.ByteCompanionObject;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes5.dex */
public abstract class zztp {
    static final byte[] zza = null;
    private static final zztk zzb = null;
    private static final zztm zzc = null;

    static {
        zzb = new zztk(new long[]{1, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new long[]{1, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new long[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
        zzc = new zztm(new zztn(new long[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new long[]{1, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new long[]{1, 0, 0, 0, 0, 0, 0, 0, 0, 0}), new long[]{1, 0, 0, 0, 0, 0, 0, 0, 0, 0});
        zza = new byte[]{-19, -45, -11, 92, 26, 99, 18, 88, -42, -100, -9, -94, -34, -7, -34, 20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 16};
    }

    public static byte[] zza(byte[] r8) {
        byte[] r1 = new byte[64];
        int r2 = 0;
        int r3 = 0;
    L3:
        int r5 = 1;
        if (r3 >= 32) goto L6;
        int r6 = r3 + r3;
        r1[r6] = (byte) (r8[r3] & 15);
        r1[r6 + 1] = (byte) ((r8[r3] & UByte.MAX_VALUE) >> 4);
        r3 = r3 + 1;
        goto L3
    L6:
        int r82 = 0;
        int r32 = 0;
    L8:
        if (r82 >= 63) goto L10;
        byte r33 = (byte) (r1[r82] + r32);
        r1[r82] = r33;
        int r4 = (r33 + 8) >> 4;
        r1[r82] = (byte) (r33 - (r4 << 4));
        r82 = r82 + 1;
        r32 = r4;
        goto L8
    L10:
        r1[63] = (byte) (r1[63] + r32);
        zztm r83 = new zztm(zzc);
        zzto r34 = new zzto();
    L11:
        if (r5 >= 64) goto L13;
        zztk r42 = new zztk(zzb);
        zzk(r42, r5 / 2, r1[r5]);
        zzto.zza(r34, r83);
        zzg(r83, r34, r42);
        r5 = r5 + 2;
        goto L11
    L13:
        zztn r43 = new zztn();
        zztn.zza(r43, r83);
        zzi(r83, r43);
        zztn.zza(r43, r83);
        zzi(r83, r43);
        zztn.zza(r43, r83);
        zzi(r83, r43);
        zztn.zza(r43, r83);
        zzi(r83, r43);
    L14:
        if (r2 >= 64) goto L16;
        zztk r44 = new zztk(zzb);
        zzk(r44, r2 / 2, r1[r2]);
        zzto.zza(r34, r83);
        zzg(r83, r34, r44);
        r2 = r2 + 2;
        goto L14
    L16:
        zztn r0 = new zztn(r83);
        long[] r22 = new long[10];
        zztw.zzf(r22, r0.zza);
        long[] r35 = new long[10];
        zztw.zzf(r35, r0.zzb);
        long[] r45 = new long[10];
        zztw.zzf(r45, r0.zzc);
        long[] r84 = new long[10];
        zztw.zzf(r84, r45);
        long[] r52 = new long[10];
        zztw.zzb(r52, r35, r22);
        zztw.zze(r52, r52, r45);
        long[] r12 = new long[10];
        zztw.zze(r12, r22, r35);
        zztw.zze(r12, r12, zztr.zza);
        zztw.zza(r12, r12, r84);
        zztw.zzc(r12, r12);
        if (MessageDigest.isEqual(zztw.zzh(r52), zztw.zzh(r12)) == true) goto L19;
        Segment$$ExternalSyntheticBUOutline1.m("arithmetic error in scalar multiplication");
        return null;
    L19:
        return r0.zzb();
    }

    public static byte[] zzb(byte[] r3) {
        MessageDigest r0 = (MessageDigest) zzadf.zzb.zzb("SHA-512");
        r0.update(r3, 0, 32);
        byte[] r32 = r0.digest();
        r32[0] = (byte) (r32[0] & 248);
        int r1 = r32[31] & ByteCompanionObject.MAX_VALUE;
        r32[31] = (byte) r1;
        r32[31] = (byte) (r1 | 64);
        return r32;
    }

    public static boolean zzc(byte[] r115, byte[] r116, byte[] r117) {
        byte[] r0 = r116;
        if (r0.length == 64) goto L5;
        return false;
    L5:
        byte[] r4 = Arrays.copyOfRange(r0, 32, 64);
        int r6 = 31;
    L6:
        if (r6 < 0) goto L85;
        int r7 = r4[r6] & UByte.MAX_VALUE;
        int r9 = zza[r6] & UByte.MAX_VALUE;
        if (r7 != r9) goto L9;
        r6 = r6 - 1;
        r0 = r116;
        goto L6
    L9:
        if (r7 >= r9) goto L85;
        MessageDigest r62 = (MessageDigest) zzadf.zzb.zzb("SHA-512");
        r62.update(r0, 0, 32);
        r62.update(r117);
        r62.update(r115);
        byte[] r63 = r62.digest();
        long r92 = zzn(r63, 0) & 2097151;
        long r13 = zzo(r63, 2) >> 5;
        long r16 = zzn(r63, 5) >> 2;
        long r19 = zzo(r63, 7) >> 7;
        long r21 = zzo(r63, 10) >> 4;
        long r25 = zzn(r63, 13) >> 1;
        long r29 = zzo(r63, 15) >> 6;
        long r33 = zzn(r63, 18) >> 3;
        long r37 = zzn(r63, 21) & 2097151;
        long r40 = zzo(r63, 23) >> 5;
        long r43 = zzn(r63, 26) >> 2;
        long r46 = zzo(r63, 28) >> 7;
        long r48 = zzo(r63, 31) >> 4;
        long r51 = zzn(r63, 34) >> 1;
        long r53 = zzo(r63, 36) >> 6;
        long r55 = zzn(r63, 39) >> 3;
        long r57 = zzn(r63, 42) & 2097151;
        long r59 = zzo(r63, 44) >> 5;
        long r61 = (zzn(r63, 47) >> 2) & 2097151;
        long r632 = (zzo(r63, 49) >> 7) & 2097151;
        long r65 = (zzo(r63, 52) >> 4) & 2097151;
        long r67 = (zzn(r63, 55) >> 1) & 2097151;
        long r11 = (zzo(r63, 57) >> 6) & 2097151;
        long r69 = zzo(r63, 60) >> 3;
        long r292 = (r29 & 2097151) + (r61 * 666643);
        long r75 = (r292 + 1048576) >> 21;
        long r77 = r75 << 21;
        long r372 = ((r37 + (r65 * 666643)) + (r632 * 470296)) + (r61 * 654183);
        long r83 = (r372 + 1048576) >> 21;
        long r85 = r83 << 21;
        long r432 = (((((r43 & 2097151) + (r11 * 666643)) + (r67 * 470296)) + (r65 * 654183)) - (r632 * 997805)) + (r61 * 136657);
        long r91 = (r432 + 1048576) >> 21;
        long r93 = r91 << 21;
        long r482 = (((((r48 & 2097151) + (r69 * 470296)) + (r11 * 654183)) - (r67 * 997805)) + (r65 * 136657)) - (r632 * 683901);
        long r97 = (r482 + 1048576) >> 21;
        long r532 = (((r53 & 2097151) - (r69 * 997805)) + (r11 * 136657)) - (r67 * 683901);
        long r101 = (r532 + 1048576) >> 21;
        long r572 = r57 - (r69 * 683901);
        long r105 = (r572 + 1048576) >> 21;
        long r332 = ((r33 + (r632 * 666643)) + (r61 * 470296)) + r75;
        long r752 = (r332 + 1048576) >> 21;
        long r109 = r752 << 21;
        long r402 = (((((r40 & 2097151) + (r67 * 666643)) + (r65 * 470296)) + (r632 * 654183)) - (r61 * 997805)) + r83;
        long r832 = (r402 + 1048576) >> 21;
        long r111 = r832 << 21;
        long r462 = (((((((r46 & 2097151) + (r69 * 666643)) + (r11 * 470296)) + (r67 * 654183)) - (r65 * 997805)) + (r632 * 136657)) - (r61 * 683901)) + r91;
        long r612 = (r462 + 1048576) >> 21;
        long r633 = r612 << 21;
        long r512 = (((((r51 & 2097151) + (r69 * 654183)) - (r11 * 997805)) + (r67 * 136657)) - (r65 * 683901)) + r97;
        long r652 = (r512 + 1048576) >> 21;
        long r552 = ((r55 + (r69 * 136657)) - (r11 * 683901)) + r101;
        long r112 = (r552 + 1048576) >> 21;
        long r483 = (r482 - (r97 << 21)) + r612;
        long r94 = r92 + (r483 * 666643);
        long r613 = (r94 + 1048576) >> 21;
        long r912 = r613 << 21;
        long r533 = (r532 - (r101 << 21)) + r652;
        long r513 = r512 - (r652 << 21);
        long r162 = (((r16 & 2097151) + (r533 * 666643)) + (r513 * 470296)) + (r483 * 654183);
        long r653 = (r162 + 1048576) >> 21;
        long r672 = r653 << 21;
        long r573 = (r572 - (r105 << 21)) + r112;
        long r553 = r552 - (r112 << 21);
        long r212 = (((((r21 & 2097151) + (r573 * 666643)) + (r553 * 470296)) + (r533 * 654183)) - (r513 * 997805)) + (r483 * 136657);
        long r113 = (r212 + 1048576) >> 21;
        long r692 = r113 << 21;
        long r592 = (r59 & 2097151) + r105;
        long r293 = (((((r292 - r77) + (r592 * 470296)) + (r573 * 654183)) - (r553 * 997805)) + (r533 * 136657)) - (r513 * 683901);
        long r772 = (r293 + 1048576) >> 21;
        long r972 = r772 << 21;
        long r373 = ((((r372 - r85) + r752) - (r592 * 997805)) + (r573 * 136657)) - (r553 * 683901);
        long r753 = (r373 + 1048576) >> 21;
        long r852 = r753 << 21;
        long r433 = ((r432 - r93) + r832) - (r592 * 683901);
        long r833 = (r433 + 1048576) >> 21;
        long r132 = (((r13 & 2097151) + (r513 * 666643)) + (r483 * 470296)) + r613;
        long r614 = (r132 + 1048576) >> 21;
        long r192 = (((((r19 & 2097151) + (r553 * 666643)) + (r533 * 470296)) + (r513 * 654183)) - (r483 * 997805)) + r653;
        long r654 = (r192 + 1048576) >> 21;
        long r252 = (((((((r25 & 2097151) + (r592 * 666643)) + (r573 * 470296)) + (r553 * 654183)) - (r533 * 997805)) + (r513 * 136657)) - (r483 * 683901)) + r113;
        long r114 = (r252 + 1048576) >> 21;
        long r333 = (((((r332 - r109) + (r592 * 654183)) - (r573 * 997805)) + (r553 * 136657)) - (r533 * 683901)) + r772;
        long r514 = (r333 + 1048576) >> 21;
        long r403 = (((r402 - r111) + (r592 * 136657)) - (r573 * 683901)) + r753;
        long r554 = (r403 + 1048576) >> 21;
        long r463 = (r462 - r633) + r833;
        long r593 = (r463 + 1048576) >> 21;
        long r634 = r593 << 21;
        long r95 = (r94 - r912) + (r593 * 666643);
        long r73 = r95 >> 21;
        long r754 = r73 << 21;
        long r133 = ((r132 - (r614 << 21)) + (r593 * 470296)) + r73;
        long r732 = r133 >> 21;
        long r773 = r732 << 21;
        long r163 = (((r162 - r672) + r614) + (r593 * 654183)) + r732;
        long r615 = r163 >> 21;
        long r673 = r615 << 21;
        long r193 = ((r192 - (r654 << 21)) - (r593 * 997805)) + r615;
        long r616 = r193 >> 21;
        long r733 = r616 << 21;
        long r213 = (((r212 - r692) + r654) + (r593 * 136657)) + r616;
        long r617 = r213 >> 21;
        long r655 = r617 << 21;
        long r253 = ((r252 - (r114 << 21)) - (r593 * 683901)) + r617;
        long r484 = r253 >> 21;
        long r594 = r484 << 21;
        long r294 = ((r293 - r972) + r114) + r484;
        long r118 = r294 >> 21;
        long r485 = r118 << 21;
        long r334 = (r333 - (r514 << 21)) + r118;
        long r119 = r334 >> 21;
        long r534 = r119 << 21;
        long r374 = ((r373 - r852) + r514) + r119;
        long r1110 = r374 >> 21;
        long r515 = r1110 << 21;
        long r404 = (r403 - (r554 << 21)) + r1110;
        long r1111 = r404 >> 21;
        long r574 = r1111 << 21;
        long r434 = ((r433 - (r833 << 21)) + r554) + r1111;
        long r1112 = r434 >> 21;
        long r555 = r1112 << 21;
        long r464 = (r463 - r634) + r1112;
        long r1113 = r464 >> 21;
        long r618 = r1113 << 21;
        long r96 = (r95 - r754) + (666643 * r1113);
        long r635 = r96 >> 21;
        long r693 = r635 << 21;
        long r134 = ((r133 - r773) + (470296 * r1113)) + r635;
        long r636 = r134 >> 21;
        long r71 = r636 << 21;
        long r164 = ((r163 - r673) + (654183 * r1113)) + r636;
        long r637 = r164 >> 21;
        long r674 = r637 << 21;
        long r194 = ((r193 - r733) - (997805 * r1113)) + r637;
        long r638 = r194 >> 21;
        long r734 = r638 << 21;
        long r214 = ((r213 - r655) + (136657 * r1113)) + r638;
        long r639 = r214 >> 21;
        long r254 = ((r253 - r594) - (r1113 * 683901)) + r639;
        long r1114 = r254 >> 21;
        long r595 = r1114 << 21;
        long r295 = (r294 - r485) + r1114;
        long r1115 = r295 >> 21;
        long r486 = r1115 << 21;
        long r335 = (r334 - r534) + r1115;
        long r1116 = r335 >> 21;
        long r535 = r1116 << 21;
        long r375 = (r374 - r515) + r1116;
        long r1117 = r375 >> 21;
        long r516 = r1117 << 21;
        long r405 = (r404 - r574) + r1117;
        long r1118 = r405 >> 21;
        long r575 = r1118 << 21;
        long r435 = (r434 - r555) + r1118;
        long r1119 = r435 >> 21;
        r63[0] = (byte) (r96 - r693);
        long r336 = r335 - r535;
        long r296 = r295 - r486;
        long r255 = r254 - r595;
        long r215 = r214 - (r639 << 21);
        long r195 = r194 - r734;
        long r165 = r164 - r674;
        long r135 = r134 - r71;
        r63[1] = (byte) (r9 >> 8);
        r63[2] = (byte) ((r9 >> 16) | (r135 << 5));
        r63[3] = (byte) (r135 >> 3);
        r63[4] = (byte) (r135 >> 11);
        r63[5] = (byte) ((r135 >> 19) | (r165 << 2));
        r63[6] = (byte) (r165 >> 6);
        r63[7] = (byte) ((r165 >> 14) | (r195 << 7));
        r63[8] = (byte) (r195 >> 1);
        r63[9] = (byte) (r195 >> 9);
        r63[10] = (byte) ((r195 >> 17) | (r215 << 4));
        r63[11] = (byte) (r215 >> 4);
        r63[12] = (byte) (r215 >> 12);
        r63[13] = (byte) ((r215 >> 20) | (r255 + r255));
        r63[14] = (byte) (r255 >> 7);
        r63[15] = (byte) ((r255 >> 15) | (r296 << 6));
        r63[16] = (byte) (r296 >> 2);
        r63[17] = (byte) (r296 >> 10);
        r63[18] = (byte) ((r296 >> 18) | (r336 << 3));
        long r436 = r435 - (r1119 << 21);
        long r465 = (r464 - r618) + r1119;
        long r406 = r405 - r575;
        r63[19] = (byte) (r336 >> 5);
        r63[20] = (byte) (r336 >> 13);
        r63[21] = (byte) (r375 - r516);
        r63[22] = (byte) (r9 >> 8);
        r63[23] = (byte) ((r9 >> 16) | (r406 << 5));
        r63[24] = (byte) (r406 >> 3);
        r63[25] = (byte) (r406 >> 11);
        r63[26] = (byte) ((r406 >> 19) | (r436 << 2));
        r63[27] = (byte) (r436 >> 6);
        r63[28] = (byte) ((r436 >> 14) | (r465 << 7));
        r63[29] = (byte) (r465 >> 1);
        r63[30] = (byte) (r465 >> 9);
        r63[31] = (byte) (r465 >> 17);
        long[] r64 = new long[10];
        long[] r72 = zztw.zzg(r117);
        long[] r98 = new long[10];
        r98[0] = 1;
        long[] r10 = new long[10];
        long[] r1120 = new long[10];
        long[] r12 = new long[10];
        long[] r136 = new long[10];
        long[] r14 = new long[10];
        zztw.zzf(r1120, r72);
        zztw.zze(r12, r1120, zztr.zza);
        zztw.zzb(r1120, r1120, r98);
        zztw.zza(r12, r12, r98);
        long[] r2 = new long[10];
        zztw.zzf(r2, r12);
        zztw.zze(r2, r2, r12);
        zztw.zzf(r64, r2);
        zztw.zze(r64, r64, r12);
        zztw.zze(r64, r64, r1120);
        long[] r8 = new long[10];
        long[] r5 = new long[10];
        long[] r15 = new long[10];
        zztw.zzf(r8, r64);
        zztw.zzf(r5, r8);
        zztw.zzf(r5, r5);
        zztw.zze(r5, r64, r5);
        zztw.zze(r8, r8, r5);
        zztw.zzf(r8, r8);
        zztw.zze(r8, r5, r8);
        zztw.zzf(r5, r8);
        int r3 = 1;
    L11:
        if (r3 >= 5) goto L13;
        zztw.zzf(r5, r5);
        r3 = r3 + 1;
        goto L11
    L13:
        zztw.zze(r8, r5, r8);
        zztw.zzf(r5, r8);
        int r02 = 1;
    L15:
        if (r02 >= 10) goto L17;
        zztw.zzf(r5, r5);
        r02 = r02 + 1;
        goto L15
    L17:
        zztw.zze(r5, r5, r8);
        zztw.zzf(r15, r5);
        int r03 = 1;
    L18:
        if (r03 >= 20) goto L20;
        zztw.zzf(r15, r15);
        r03 = r03 + 1;
        goto L18
    L20:
        zztw.zze(r5, r15, r5);
        zztw.zzf(r5, r5);
        int r04 = 1;
    L22:
        if (r04 >= 10) goto L24;
        zztw.zzf(r5, r5);
        r04 = r04 + 1;
        goto L22
    L24:
        zztw.zze(r8, r5, r8);
        zztw.zzf(r5, r8);
        int r05 = 1;
    L26:
        if (r05 >= 50) goto L28;
        zztw.zzf(r5, r5);
        r05 = r05 + 1;
        goto L26
    L28:
        zztw.zze(r5, r5, r8);
        zztw.zzf(r15, r5);
        int r06 = 1;
    L30:
        if (r06 >= 100) goto L32;
        zztw.zzf(r15, r15);
        r06 = r06 + 1;
        goto L30
    L32:
        zztw.zze(r5, r15, r5);
        zztw.zzf(r5, r5);
        int r07 = 1;
    L33:
        if (r07 >= 50) goto L35;
        zztw.zzf(r5, r5);
        r07 = r07 + 1;
        goto L33
    L35:
        zztw.zze(r8, r5, r8);
        zztw.zzf(r8, r8);
        zztw.zzf(r8, r8);
        zztw.zze(r64, r8, r64);
        zztw.zze(r64, r64, r2);
        zztw.zze(r64, r64, r1120);
        zztw.zzf(r136, r64);
        zztw.zze(r136, r136, r12);
        zztw.zzb(r14, r136, r1120);
        if (zze(r14) == false) goto L43;
        zztw.zza(r14, r136, r1120);
        if (zze(r14) == true) goto L40;
        zztw.zze(r64, r64, zztr.zzc);
        goto L43
    L40:
        zzsj$$ExternalSyntheticBUOutline1.m("Cannot convert given bytes to extended projective coordinates. No square root exists for modulo 2^255-19");
        return false;
    L43:
        if (zze(r64) == true) goto L49;
        byte r08 = r117[31];
        byte r22 = UByte.MAX_VALUE;
        if (((r08 & UByte.MAX_VALUE) >> 7) == 0) goto L51;
        zzsj$$ExternalSyntheticBUOutline1.m("Cannot convert given bytes to extended projective coordinates. Computed x is zero and encoded x's least significant bit is not zero");
        return false;
    L51:
        if (zzf(r64) != ((r117[31] & r22) >> 7)) goto L53;
        zzm(r64, r64);
    L53:
        zztw.zze(r10, r64, r72);
        zzto r09 = new zzto(new zztn(r64, r72, r98), r10);
        zztl[] r32 = new zztl[8];
        r32[0] = new zztl(r09);
        zztm r1 = new zztm(new zztn(), new long[10]);
        zzi(r1, r09.zza);
        zzto r010 = new zzto(r1);
        int r52 = 1;
    L54:
        if (r52 >= 8) goto L56;
        zzg(r1, r010, r32[r52 - 1]);
        r32[r52] = new zztl(new zzto(r1));
        r52 = r52 + 1;
        goto L54
    L56:
        byte[] r011 = zzl(r63);
        byte[] r17 = zzl(r4);
        zztm r42 = new zztm(zzc);
        zzto r54 = new zzto();
        int r82 = r22;
    L57:
        if (r82 < 0) goto L64;
        if (r011[r82] != 0) goto L64;
        if (r17[r82] != 0) goto L64;
        r82 = r82 - 1;
    L64:
        if (r82 < 0) goto L76;
        zzi(r42, new zztn(r42));
        byte r23 = r011[r82];
        if (r23 <= 0) goto L68;
        zzto.zza(r54, r42);
        zzg(r42, r54, r32[r011[r82] / 2]);
    L70:
        byte r24 = r17[r82];
        if (r24 <= 0) goto L73;
        zzto.zza(r54, r42);
        zzg(r42, r54, zztr.zze[r17[r82] / 2]);
    L75:
        r82 = r82 - 1;
        goto L64
    L73:
        if (r24 >= 0) goto L75;
        zzto.zza(r54, r42);
        zzh(r42, r54, zztr.zze[(-r17[r82]) / 2]);
        goto L75
    L68:
        if (r23 >= 0) goto L70;
        zzto.zza(r54, r42);
        zzh(r42, r54, r32[(-r011[r82]) / 2]);
        goto L70
    L76:
        byte[] r012 = new zztn(r42).zzb();
        int r18 = 0;
    L77:
        if (r18 >= 32) goto L82;
        if (r012[r18] != r116[r18]) goto L80;
        r18 = r18 + 1;
        goto L77
    L80:
        return false;
    L82:
        return true;
    L49:
        r22 = UByte.MAX_VALUE;
    L85:
        return false;
    }

    public static void zzd() {
        if (zztr.zza == null) goto L5;
        return;
    L5:
        Segment$$ExternalSyntheticBUOutline1.m("Could not initialize Ed25519.");
    }

    public static /* synthetic */ boolean zze(long[] r3) {
        long[] r0 = new long[11];
        System.arraycopy(r3, 0, r0, 0, 10);
        zztw.zzd(r0);
        byte[] r32 = zztw.zzh(r0);
        int r02 = 0;
    L4:
        if (r02 >= 32) goto L10;
        if (r32[r02] != 0) goto L7;
        r02 = r02 + 1;
        goto L4
    L7:
        return true;
    L10:
        return false;
    }

    public static /* synthetic */ int zzf(long[] r1) {
        return zztw.zzh(r1)[0] & 1;
    }

    private static void zzg(zztm r7, zzto r8, zztk r9) {
        zztn r0 = r8.zza;
        zztn r1 = r7.zza;
        long[] r2 = new long[10];
        long[] r3 = r1.zza;
        long[] r4 = r0.zzb;
        long[] r5 = r0.zza;
        zztw.zza(r3, r4, r5);
        long[] r6 = r1.zzb;
        zztw.zzb(r6, r4, r5);
        zztw.zze(r6, r6, r9.zzb);
        long[] r42 = r9.zza;
        long[] r12 = r1.zzc;
        zztw.zze(r12, r3, r42);
        long[] r72 = r7.zzb;
        zztw.zze(r72, r8.zzb, r9.zzc);
        r9.zza(r3, r0.zzc);
        zztw.zza(r2, r3, r3);
        zztw.zzb(r3, r12, r6);
        zztw.zza(r6, r12, r6);
        zztw.zza(r12, r2, r72);
        zztw.zzb(r72, r2, r72);
    }

    private static void zzh(zztm r7, zzto r8, zztk r9) {
        zztn r0 = r8.zza;
        zztn r1 = r7.zza;
        long[] r2 = new long[10];
        long[] r3 = r1.zza;
        long[] r4 = r0.zzb;
        long[] r5 = r0.zza;
        zztw.zza(r3, r4, r5);
        long[] r6 = r1.zzb;
        zztw.zzb(r6, r4, r5);
        zztw.zze(r6, r6, r9.zza);
        long[] r42 = r9.zzb;
        long[] r12 = r1.zzc;
        zztw.zze(r12, r3, r42);
        long[] r72 = r7.zzb;
        zztw.zze(r72, r8.zzb, r9.zzc);
        r9.zza(r3, r0.zzc);
        zztw.zza(r2, r3, r3);
        zztw.zzb(r3, r12, r6);
        zztw.zza(r6, r12, r6);
        zztw.zzb(r12, r2, r72);
        zztw.zza(r72, r2, r72);
    }

    private static void zzi(zztm r6, zztn r7) {
        zztn r0 = r6.zza;
        long[] r1 = r0.zza;
        long[] r2 = r7.zza;
        long[] r3 = new long[10];
        zztw.zzf(r1, r2);
        long[] r4 = r0.zzc;
        long[] r5 = r7.zzb;
        zztw.zzf(r4, r5);
        long[] r62 = r6.zzb;
        zztw.zzf(r62, r7.zzc);
        zztw.zza(r62, r62, r62);
        long[] r72 = r0.zzb;
        zztw.zza(r72, r2, r5);
        zztw.zzf(r3, r72);
        zztw.zza(r72, r4, r1);
        zztw.zzb(r4, r4, r1);
        zztw.zzb(r1, r3, r72);
        zztw.zzb(r62, r62, r4);
    }

    private static int zzj(int r0, int r1) {
        int r02 = (~(r0 ^ r1)) & 255;
        int r03 = r02 & (r02 << 4);
        int r04 = r03 & (r03 << 2);
        return (r04 & (r04 + r04)) >> 7;
    }

    private static void zzk(zztk r6, int r7, byte r8) {
        zztk[][] r0 = zztr.zzd;
        zztk r1 = r0[r7][0];
        int r2 = (r8 & UByte.MAX_VALUE) >> 7;
        int r4 = (-r2) & r8;
        int r82 = r8 - (r4 + r4);
        r6.zzb(r1, zzj(r82, 1));
        r6.zzb(r0[r7][1], zzj(r82, 2));
        r6.zzb(r0[r7][2], zzj(r82, 3));
        r6.zzb(r0[r7][3], zzj(r82, 4));
        r6.zzb(r0[r7][4], zzj(r82, 5));
        r6.zzb(r0[r7][5], zzj(r82, 6));
        r6.zzb(r0[r7][6], zzj(r82, 7));
        r6.zzb(r0[r7][7], zzj(r82, 8));
        long[] r72 = r6.zzc;
        long[] r83 = r6.zza;
        long[] r02 = Arrays.copyOf(r6.zzb, 10);
        long[] r84 = Arrays.copyOf(r83, 10);
        long[] r73 = Arrays.copyOf(r72, 10);
        zzm(r73, r73);
        r6.zzb(new zztk(r02, r84, r73), r2);
    }

    private static byte[] zzl(byte[] r10) {
        byte[] r1 = new byte[256];
        int r3 = 0;
    L4:
        if (r3 >= 256) goto L6;
        r1[r3] = (byte) (1 & ((r10[r3 >> 3] & UByte.MAX_VALUE) >> (r3 & 7)));
        r3 = r3 + 1;
        goto L4
    L6:
        int r102 = 0;
    L7:
        if (r102 >= 256) goto L30;
        if (r1[r102] == 0) goto L29;
        int r32 = 1;
    L12:
        if (r32 > 6) goto L29;
        int r5 = r102 + r32;
        if (r5 >= 256) goto L29;
        byte r6 = r1[r5];
        if (r6 == 0) goto L28;
        byte r7 = r1[r102];
        int r62 = r6 << r32;
        int r8 = r7 + r62;
        if (r8 > 15) goto L20;
        r1[r102] = (byte) r8;
        r1[r5] = 0;
        goto L28
    L20:
        int r72 = r7 - r62;
        if (r72 < (-15)) goto L29;
        r1[r102] = (byte) r72;
    L23:
        if (r5 >= 256) goto L28;
        if (r1[r5] == 0) goto L26;
        r1[r5] = 0;
        r5 = r5 + 1;
        goto L23
    L26:
        r1[r5] = 1;
    L28:
        r32 = r32 + 1;
    L29:
        r102 = r102 + 1;
        goto L7
    L30:
        return r1;
    }

    private static void zzm(long[] r3, long[] r4) {
        int r0 = 0;
    L4:
        if (r0 >= r4.length) goto L6;
        r3[r0] = -r4[r0];
        r0 = r0 + 1;
        goto L4
    }

    private static long zzn(byte[] r6, int r7) {
        long r0 = r6[r7];
        int r2 = r6[r7 + 1] & UByte.MAX_VALUE;
        return ((r6[r7 + 2] & UByte.MAX_VALUE) << 16) | ((r0 & 255) | (r2 << 8));
    }

    private static long zzo(byte[] r3, int r4) {
        return ((r3[r4 + 3] & UByte.MAX_VALUE) << 24) | zzn(r3, r4);
    }
}
