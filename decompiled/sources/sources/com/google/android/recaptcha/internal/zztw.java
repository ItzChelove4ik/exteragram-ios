package com.google.android.recaptcha.internal;

import java.util.Arrays;
import kotlin.UByte;

/* loaded from: classes5.dex */
public abstract class zztw {
    public static final /* synthetic */ int $r8$clinit = 0;
    private static final int[] zzb = null;
    private static final int[] zzc = null;
    private static final int[] zzd = null;
    private static final int[] zze = null;

    static {
        zzb = new int[]{0, 3, 6, 9, 12, 16, 19, 22, 25, 28};
        zzc = new int[]{0, 2, 3, 5, 6, 0, 1, 3, 4, 6};
        zzd = new int[]{67108863, 33554431};
        zze = new int[]{26, 25};
    }

    public static void zza(long[] r5, long[] r6, long[] r7) {
        int r0 = 0;
    L4:
        if (r0 >= 10) goto L6;
        r5[r0] = r6[r0] + r7[r0];
        r0 = r0 + 1;
        goto L4
    }

    public static void zzb(long[] r5, long[] r6, long[] r7) {
        int r0 = 0;
    L4:
        if (r0 >= 10) goto L6;
        r5[r0] = r6[r0] - r7[r0];
        r0 = r0 + 1;
        goto L4
    }

    public static void zzc(long[] r9, long[] r10) {
        int r0 = r9.length;
        if (r0 == 19) goto L5;
        long[] r2 = new long[19];
        System.arraycopy(r9, 0, r2, 0, r0);
        r9 = r2;
    L5:
        long r22 = r9[8];
        long r4 = r9[18];
        long r23 = r22 + (r4 << 4);
        r9[8] = r23;
        long r24 = r23 + (r4 + r4);
        r9[8] = r24;
        r9[8] = r24 + r4;
        long r25 = r9[7];
        long r42 = r9[17];
        long r26 = r25 + (r42 << 4);
        r9[7] = r26;
        long r27 = r26 + (r42 + r42);
        r9[7] = r27;
        r9[7] = r27 + r42;
        long r28 = r9[6];
        long r43 = r9[16];
        long r29 = r28 + (r43 << 4);
        r9[6] = r29;
        long r210 = r29 + (r43 + r43);
        r9[6] = r210;
        r9[6] = r210 + r43;
        long r211 = r9[5];
        long r44 = r9[15];
        long r212 = r211 + (r44 << 4);
        r9[5] = r212;
        long r213 = r212 + (r44 + r44);
        r9[5] = r213;
        r9[5] = r213 + r44;
        long r214 = r9[4];
        long r45 = r9[14];
        long r215 = r214 + (r45 << 4);
        r9[4] = r215;
        long r216 = r215 + (r45 + r45);
        r9[4] = r216;
        r9[4] = r216 + r45;
        long r217 = r9[3];
        long r46 = r9[13];
        long r218 = r217 + (r46 << 4);
        r9[3] = r218;
        long r219 = r218 + (r46 + r46);
        r9[3] = r219;
        r9[3] = r219 + r46;
        long r220 = r9[2];
        long r47 = r9[12];
        long r221 = r220 + (r47 << 4);
        r9[2] = r221;
        long r222 = r221 + (r47 + r47);
        r9[2] = r222;
        r9[2] = r222 + r47;
        long r223 = r9[1];
        long r48 = r9[11];
        long r224 = r223 + (r48 << 4);
        r9[1] = r224;
        long r225 = r224 + (r48 + r48);
        r9[1] = r225;
        r9[1] = r225 + r48;
        long r226 = r9[0];
        long r49 = r9[10];
        long r227 = r226 + (r49 << 4);
        r9[0] = r227;
        long r228 = r227 + (r49 + r49);
        r9[0] = r228;
        r9[0] = r228 + r49;
        zzd(r9);
        System.arraycopy(r9, 0, r10, 0, 10);
    }

    public static void zzd(long[] r14) {
        r14[10] = 0;
        int r4 = 0;
    L4:
        if (r4 >= 10) goto L6;
        long r8 = r14[r4];
        long r6 = r8 / 67108864;
        r14[r4] = r8 - (r6 << 26);
        int r5 = r4 + 1;
        long r82 = r14[r5] + r6;
        r14[r5] = r82;
        long r62 = r82 / 33554432;
        r14[r5] = r82 - (r62 << 25);
        r4 = r4 + 2;
        r14[r4] = r14[r4] + r62;
        goto L4
    L6:
        long r83 = r14[0];
        long r10 = r14[10];
        long r84 = r83 + (r10 << 4);
        r14[0] = r84;
        long r85 = r84 + (r10 + r10);
        r14[0] = r85;
        long r86 = r85 + r10;
        r14[0] = r86;
        r14[10] = 0;
        long r0 = r86 / 67108864;
        r14[0] = r86 - (r0 << 26);
        r14[1] = r14[1] + r0;
    }

    public static void zze(long[] r84, long[] r85, long[] r86) {
        long r1 = r85[0];
        long r3 = r86[0];
        long r5 = r1 * r3;
        long r8 = r86[1];
        long r12 = r85[1];
        long r10 = (r1 * r8) + (r12 * r3);
        long r17 = r86[2];
        long r21 = r85[2];
        long r14 = (((r12 + r12) * r8) + (r1 * r17)) + (r21 * r3);
        long r26 = r86[3];
        long r30 = r85[3];
        long r19 = (((r12 * r17) + (r21 * r8)) + (r1 * r26)) + (r30 * r3);
        long r35 = r86[4];
        long r39 = r85[4];
        long r28 = (r12 * r26) + (r30 * r8);
        long r23 = (((r21 * r17) + (r28 + r28)) + (r1 * r35)) + (r39 * r3);
        long r44 = r86[5];
        long r48 = r85[5];
        long r282 = (((((r21 * r26) + (r30 * r17)) + (r12 * r35)) + (r39 * r8)) + (r1 * r44)) + (r48 * r3);
        long r53 = r86[6];
        long r57 = r85[6];
        long r32 = ((r30 * r26) + (r12 * r44)) + (r48 * r8);
        long r322 = ((((r32 + r32) + (r21 * r35)) + (r39 * r17)) + (r1 * r53)) + (r57 * r3);
        long r62 = r86[7];
        long r66 = r85[7];
        long r37 = (((((((r30 * r35) + (r39 * r26)) + (r21 * r44)) + (r48 * r17)) + (r12 * r53)) + (r57 * r8)) + (r1 * r62)) + (r66 * r3);
        long r71 = r86[8];
        long r75 = r85[8];
        long r46 = (((r30 * r44) + (r48 * r26)) + (r12 * r62)) + (r66 * r8);
        long r41 = (((((r39 * r35) + (r46 + r46)) + (r21 * r53)) + (r57 * r17)) + (r1 * r71)) + (r75 * r3);
        long r80 = r86[9];
        long r82 = r85[9];
        long r462 = (((((((((r39 * r44) + (r48 * r35)) + (r30 * r53)) + (r57 * r26)) + (r21 * r62)) + (r66 * r17)) + (r12 * r71)) + (r75 * r8)) + (r1 * r80)) + (r3 * r82);
        long r13 = ((((r48 * r44) + (r30 * r62)) + (r66 * r26)) + (r12 * r80)) + (r8 * r82);
        long r122 = (((r48 * r62) + (r66 * r44)) + (r30 * r80)) + (r26 * r82);
        long r172 = ((r66 * r62) + (r48 * r80)) + (r44 * r82);
        long r212 = (((r66 * r71) + (r75 * r62)) + (r57 * r80)) + (r53 * r82);
        long r662 = (r66 * r80) + (r62 * r82);
        zzc(new long[]{r5, r10, r14, r19, r23, r282, r322, r37, r41, r462, ((((r13 + r13) + (r39 * r53)) + (r57 * r35)) + (r21 * r71)) + (r75 * r17), (((((((r48 * r53) + (r57 * r44)) + (r39 * r62)) + (r66 * r35)) + (r30 * r71)) + (r75 * r26)) + (r21 * r80)) + (r17 * r82), (((r57 * r53) + (r122 + r122)) + (r39 * r71)) + (r75 * r35), (((((r57 * r62) + (r66 * r53)) + (r48 * r71)) + (r75 * r44)) + (r39 * r80)) + (r35 * r82), ((r172 + r172) + (r57 * r71)) + (r75 * r53), r212, (r75 * r71) + (r662 + r662), (r75 * r80) + (r71 * r82), (r82 + r82) * r80}, r84);
    }

    public static void zzf(long[] r58, long[] r59) {
        long r1 = r59[0];
        long r3 = r1 * r1;
        long r8 = r59[1];
        long r5 = (r1 + r1) * r8;
        long r13 = r59[2];
        long r10 = (r8 * r8) + (r1 * r13);
        long r18 = r59[3];
        long r15 = (r8 * r13) + (r1 * r18);
        long r29 = r59[4];
        long r20 = ((r13 * r13) + ((r8 * 4) * r18)) + ((r1 + r1) * r29);
        long r32 = r59[5];
        long r24 = ((r13 * r18) + (r8 * r29)) + (r1 * r32);
        long r37 = r59[6];
        long r26 = (((r18 * r18) + (r13 * r29)) + (r1 * r37)) + ((r8 + r8) * r32);
        long r43 = r59[7];
        long r34 = (((r18 * r29) + (r13 * r32)) + (r8 * r37)) + (r1 * r43);
        long r47 = r59[8];
        long r51 = (r8 * r43) + (r18 * r32);
        long r45 = ((r13 * r37) + (r1 * r47)) + (r51 + r51);
        long r40 = (r29 * r29) + (r45 + r45);
        long r56 = r59[9];
        long r452 = ((((r29 * r32) + (r18 * r37)) + (r13 * r43)) + (r8 * r47)) + (r1 * r56);
        long r53 = (r18 * r43) + (r8 * r56);
        long r12 = (((r32 * r32) + (r29 * r37)) + (r13 * r47)) + (r53 + r53);
        long r82 = (((r32 * r37) + (r29 * r43)) + (r18 * r47)) + (r13 * r56);
        long r512 = (r32 * r43) + (r18 * r56);
        long r49 = (r29 * r47) + (r512 + r512);
        long r182 = ((r37 * r43) + (r32 * r47)) + (r29 * r56);
        long r292 = ((r43 * r43) + (r37 * r47)) + ((r32 + r32) * r56);
        long r322 = (r43 * r47) + (r37 * r56);
        zzc(new long[]{r3, r5, r10 + r10, r15 + r15, r20, r24 + r24, r26 + r26, r34 + r34, r40, r452 + r452, r12 + r12, r82 + r82, (r37 * r37) + (r49 + r49), r182 + r182, r292 + r292, r322 + r322, (r47 * r47) + ((r43 * 4) * r56), (r47 + r47) * r56, (r56 + r56) * r56}, r58);
    }

    public static long[] zzg(byte[] r12) {
        long[] r1 = new long[10];
        int r2 = 0;
    L3:
        if (r2 >= 10) goto L5;
        int r3 = zzb[r2];
        int r4 = r12[r3] & UByte.MAX_VALUE;
        int r5 = r12[r3 + 1] & UByte.MAX_VALUE;
        long r42 = r4 | (r5 << 8);
        long r32 = ((r42 | ((r12[r3 + 2] & UByte.MAX_VALUE) << 16)) | ((r12[r3 + 3] & UByte.MAX_VALUE) << 24)) >> zzc[r2];
        int[] r6 = zzd;
        r1[r2] = r32 & r6[r2 & 1];
        r2 = r2 + 1;
        goto L3
    L5:
        return r1;
    }

    public static byte[] zzh(long[] r17) {
        long[] r1 = Arrays.copyOf(r17, 10);
        int r2 = 0;
        int r3 = 0;
    L3:
        int r9 = 2;
        if (r3 >= 2) goto L9;
        int r92 = 0;
    L6:
        if (r92 >= 9) goto L8;
        long r10 = r1[r92];
        int r12 = -((int) (((r10 >> 31) & r10) >> zze[r92 & 1]));
        r1[r92] = r10 + (r12 << r14);
        r92 = r92 + 1;
        r1[r92] = r1[r92] - r12;
        goto L6
    L8:
        long r93 = r1[9];
        r1[9] = r93 + (r6 << 25);
        r1[0] = r1[0] - ((-((int) (((r93 >> 31) & r93) >> 25))) * 19);
        r3 = r3 + 1;
        goto L3
    L9:
        long r102 = r1[0];
        r1[0] = r102 + (r3 << 26);
        r1[1] = r1[1] - (-((int) (((r102 >> 31) & r102) >> 26)));
        int r32 = 0;
    L10:
        if (r32 >= 2) goto L15;
        int r11 = r2;
    L12:
        if (r11 >= 9) goto L14;
        long r122 = r1[r11];
        int r172 = r2;
        long r22 = r122 >> zze[r11 & 1];
        r1[r11] = r122 & zzd[r14];
        r11 = r11 + 1;
        r1[r11] = r1[r11] + ((int) r22);
        r2 = r172;
        r32 = r32;
        goto L12
    L14:
        r32 = r32 + 1;
        goto L10
    L15:
        int r173 = r2;
        long r23 = r1[9];
        long r112 = r23 >> 25;
        r1[9] = r23 & 33554431;
        r1[r173] = r1[r173] + (((int) r112) * 19);
        int r24 = ~((((int) r2) - 67108845) >> 31);
        int r33 = 1;
    L16:
        if (r33 >= 10) goto L18;
        int r4 = ~(((int) r1[r33]) ^ zzd[r33 & 1]);
        int r42 = r4 & (r4 << 16);
        int r43 = r42 & (r42 << 8);
        int r44 = r43 & (r43 << 4);
        int r45 = r44 & (r44 << 2);
        r24 = r24 & ((r45 & (r45 + r45)) >> 31);
        r33 = r33 + 1;
        goto L16
    L18:
        r1[r173] = r1[r173] - (67108845 & r24);
        long r5 = 33554431 & r24;
        r1[1] = r1[1] - r5;
    L19:
        if (r9 >= 10) goto L21;
        r1[r9] = r1[r9] - (67108863 & r24);
        int r34 = r9 + 1;
        r1[r34] = r1[r34] - r5;
        r9 = r9 + 2;
        goto L19
    L21:
        int r25 = r173;
    L22:
        if (r25 >= 10) goto L24;
        r1[r25] = r1[r25] << zzc[r25];
        r25 = r25 + 1;
        goto L22
    L24:
        byte[] r26 = new byte[32];
        int r35 = r173;
    L25:
        if (r35 >= 10) goto L27;
        int r46 = zzb[r35];
        long r52 = r26[r46];
        long r7 = r1[r35];
        r26[r46] = (byte) (r52 | (r7 & 255));
        r26[r46 + 1] = (byte) (r26[r5] | ((r7 >> 8) & 255));
        r26[r46 + 2] = (byte) (r26[r5] | ((r7 >> 16) & 255));
        r26[r46 + 3] = (byte) (r26[r4] | ((r7 >> 24) & 255));
        r35 = r35 + 1;
        goto L25
    L27:
        return r26;
    }
}
