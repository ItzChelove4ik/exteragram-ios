package com.google.android.gms.internal.clearcut;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import kotlin.UByte;

/* loaded from: classes4.dex */
public abstract class zzk {
    private static int zza(byte[] r2, int r3) {
        int r0 = ((r2[r3] & UByte.MAX_VALUE) | ((r2[r3 + 1] & UByte.MAX_VALUE) << 8)) | ((r2[r3 + 2] & UByte.MAX_VALUE) << 16);
        return ((r2[r3 + 3] & UByte.MAX_VALUE) << 24) | r0;
    }

    private static long zza(long r3, long r5, long r7) {
        long r32 = (r3 ^ r5) * r7;
        long r33 = ((r32 ^ (r32 >>> 47)) ^ r5) * r7;
        return (r33 ^ (r33 >>> 47)) * r7;
    }

    public static long zza(byte[] r36) {
        byte[] r0 = r36;
        int r1 = r0.length;
        if (r1 >= 0) goto L5;
    L32:
        StringBuilder r2 = new StringBuilder(67);
        r2.append("Out of bound index with offput: 0 and length: ");
        r2.append(r1);
        throw new IndexOutOfBoundsException(r2.toString());
    L5:
        if (r1 > r0.length) goto L32;
        char r9 = '/';
        char r7 = 0;
        if (r1 > 32) goto L22;
        if (r1 > 16) goto L20;
        if (r1 < 8) goto L13;
        long r21 = (r1 << 1) - 7286425919675154353L;
        long r22 = zzb(r0, 0) - 7286425919675154353L;
        long r02 = zzb(r0, r1 - 8);
        return zza((Long.rotateRight(r02, 37) * r21) + r22, (Long.rotateRight(r22, 25) + r02) * r21, r21);
    L13:
        if (r1 >= 4) goto L15;
        if (r1 <= 0) goto L19;
        byte r23 = r0[0];
        byte r3 = r0[r1 >> 1];
        byte r03 = r0[r1 - 1];
        long r04 = (r1 + ((r03 & UByte.MAX_VALUE) << 2)) * (-4348849565147123417L);
        long r05 = r04 ^ (((r23 & UByte.MAX_VALUE) + ((r3 & UByte.MAX_VALUE) << 8)) * (-7286425919675154353L));
        return (r05 ^ (r05 >>> 47)) * (-7286425919675154353L);
    L19:
        return -7286425919675154353L;
    L15:
        return zza(r1 + ((zza(r0, 0) & 4294967295L) << 3), zza(r0, r1 - 4) & 4294967295L, (r1 << 1) - 7286425919675154353L);
    L20:
        long r212 = (r1 << 1) - 7286425919675154353L;
        long r5 = zzb(r0, 0) * (-5435081209227447693L);
        long r72 = zzb(r0, 8);
        long r92 = zzb(r0, r1 - 8) * r212;
        return zza((Long.rotateRight(r5 + r72, 43) + Long.rotateRight(r92, 30)) + (zzb(r0, r1 - 16) * (-7286425919675154353L)), (r5 + Long.rotateRight(r72 - 7286425919675154353L, 18)) + r92, r212);
    L22:
        char r8 = '@';
        if (r1 > 64) goto L26;
        long r213 = (r1 << 1) - 7286425919675154353L;
        long r52 = zzb(r0, 0) * (-7286425919675154353L);
        long r73 = zzb(r0, 8);
        long r93 = zzb(r0, r1 - 8) * r213;
        long r17 = (Long.rotateRight(r52 + r73, 43) + Long.rotateRight(r93, 30)) + (zzb(r0, r1 - 16) * (-7286425919675154353L));
        long r74 = zza(r17, (Long.rotateRight(r73 - 7286425919675154353L, 18) + r52) + r93, r213);
        long r94 = zzb(r0, 16) * r213;
        long r12 = zzb(r0, 24);
        long r14 = (r17 + zzb(r0, r1 - 32)) * r213;
        return zza((Long.rotateRight(r94 + r12, 43) + Long.rotateRight(r14, 30)) + ((r74 + zzb(r0, r1 - 24)) * r213), (r94 + Long.rotateRight(r12 + r52, 18)) + r14, r213);
    L26:
        long[] r6 = new long[2];
        long[] r11 = new long[2];
        long r4 = zzb(r0, 0) + 95310865018149119L;
        int r13 = r1 - 1;
        int r15 = (r13 / 64) << 6;
        int r16 = r13 & 63;
        int r162 = r15 + r16;
        int r19 = r162 - 63;
        long r24 = 2480279821605975764L;
        long r20 = 1390051526045402406L;
        int r222 = r16;
        int r18 = 0;
    L27:
        char r232 = r7;
        long r42 = Long.rotateRight(((r4 + r24) + r6[r7]) + zzb(r0, r18 + 8), 37) * (-5435081209227447693L);
        long r25 = Long.rotateRight((r24 + r6[1]) + zzb(r0, r18 + 48), 42) * (-5435081209227447693L);
        long r242 = r42 ^ r11[1];
        char r26 = r8;
        long r27 = r25 + (r6[r232] + zzb(r0, r18 + 40));
        long r202 = Long.rotateRight(r20 + r11[r232], 33) * (-5435081209227447693L);
        char r29 = r9;
        int r95 = r222;
        zza(r0, r18, r6[1] * (-5435081209227447693L), r242 + r11[r232], r6);
        int r30 = r18;
        long[] r223 = r6;
        zza(r0, r30 + 32, r202 + r11[1], r27 + zzb(r0, r30 + 16), r11);
        r18 = r30 + 64;
        if (r18 == r15) goto L29;
        r0 = r36;
        r4 = r202;
        r6 = r223;
        r7 = r232;
        r20 = r242;
        r8 = r26;
        r24 = r27;
        r222 = r95;
        r9 = r29;
        goto L27
    L29:
        long r34 = ((r242 & 255) << 1) - 5435081209227447693L;
        long r110 = r11[r232] + r95;
        r11[r232] = r110;
        long r32 = r223[r232] + r110;
        r223[r232] = r32;
        r11[r232] = r11[r232] + r32;
        long r111 = Long.rotateRight(((r202 + r27) + r223[r232]) + zzb(r0, r162 - 55), 37) * r34;
        long r33 = Long.rotateRight((r27 + r223[1]) + zzb(r0, r162 - 15), 42) * r34;
        long r122 = r111 ^ (r11[1] * 9);
        long r96 = r33 + ((r223[r232] * 9) + zzb(r0, r162 - 23));
        long r75 = Long.rotateRight(r242 + r11[r232], 33) * r34;
        zza(r0, r19, r223[1] * r34, r11[r232] + r122, r223);
        zza(r0, r162 - 31, r11[1] + r75, zzb(r0, r162 - 47) + r96, r11);
        return zza((zza(r223[r232], r11[r232], r34) + (((r96 >>> r29) ^ r96) * (-4348849565147123417L))) + r122, zza(r223[1], r11[1], r34) + r75, r34);
    }

    private static void zza(byte[] r6, int r7, long r8, long r10, long[] r12) {
        long r0 = zzb(r6, r7);
        long r2 = zzb(r6, r7 + 8);
        long r4 = zzb(r6, r7 + 16);
        long r62 = zzb(r6, r7 + 24);
        long r82 = r8 + r0;
        long r22 = (r2 + r82) + r4;
        long r102 = Long.rotateRight((r10 + r82) + r62, 21) + Long.rotateRight(r22, 44);
        r12[0] = r22 + r62;
        r12[1] = r102 + r82;
    }

    private static long zzb(byte[] r1, int r2) {
        ByteBuffer r12 = ByteBuffer.wrap(r1, r2, 8);
        r12.order(ByteOrder.LITTLE_ENDIAN);
        return r12.getLong();
    }
}
