package com.google.android.recaptcha.internal;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import kotlin.UByte;
import okio.Segment$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
public abstract class zzair {
    public static final /* synthetic */ int $r8$clinit = 0;

    static {
        Charset r0 = StandardCharsets.UTF_16;
    }

    public static int zza(int r1, int r2) {
        if ((r1 % 2) != 0) goto L7;
        int r0 = (~r2) & r1;
        return ((~r1) & r2) | r0;
    L7:
        return (r1 | r2) - (r1 & r2);
    }

    public static byte[] zzb(byte[] r19, byte[] r20, byte[] r21) {
        byte[] r6 = r20;
        byte[] r7 = r21;
        int[] r5 = {511133343, 1277647508, 107287496, 338123662};
        if (r6.length != 32) goto L34;
        char r11 = '\f';
        if (r7.length != 12) goto L32;
        int r12 = 16;
        int[] r9 = new int[16];
        int r1 = 0;
    L8:
        if (r1 >= 4) goto L10;
        r9[r1] = zza(r5[r1], 2131181306);
        r1 = r1 + 1;
        goto L8
    L10:
        int r13 = 4;
    L11:
        if (r13 >= 12) goto L13;
        r9[r13] = zze(r6, (r13 - 4) * 4);
        r13 = r13 + 1;
        goto L11
    L13:
        r9[12] = 1;
        int r3 = 13;
    L14:
        if (r3 >= 16) goto L16;
        r9[r3] = zze(r7, (r3 - 13) * 4);
        r3 = r3 + 1;
        goto L14
    L16:
        int[] r10 = new int[16];
        System.arraycopy(r9, 0, r10, 0, 16);
        int r14 = r19.length;
        byte[] r15 = new byte[r14];
        int r8 = 1;
        int r16 = 0;
        int r17 = r14;
    L17:
        if (r17 <= 0) goto L30;
        System.arraycopy(r10, 0, r9, 0, r12);
        r9[r11] = r8;
        int r2 = 0;
    L20:
        if (r2 >= 10) goto L22;
        zzd(0, 4, 8, 12, r5, r6, r7, r8, r9, r10);
        r6 = r20;
        r7 = r21;
        zzd(1, 5, 9, 13, r5, r6, r7, r8, r9, r10);
        zzd(2, 6, 10, 14, r5, r6, r7, r8, r9, r10);
        zzd(3, 7, 11, 15, r5, r6, r7, r8, r9, r10);
        zzd(0, 5, 10, 15, r5, r6, r7, r8, r9, r10);
        zzd(1, 6, 11, 12, r5, r6, r7, r8, r9, r10);
        zzd(2, 7, 8, 13, r5, r6, r7, r8, r9, r10);
        zzd(3, 4, 9, 14, r5, r6, r7, r8, r9, r10);
        r2 = r2 + 1;
        r17 = r17;
        goto L20
    L22:
        int r112 = r17;
        byte[] r22 = new byte[64];
        int r32 = 0;
    L23:
        if (r32 >= r12) goto L25;
        int r4 = r9[r32];
        int r62 = r32 * 4;
        r22[r62] = (byte) (r4 & 255);
        r22[r62 + 1] = (byte) ((r4 >> 8) & 255);
        r22[r62 + 2] = (byte) ((r4 >> 16) & 255);
        r22[r62 + 3] = (byte) ((r4 >> 24) & 255);
        r32 = r32 + 1;
        r12 = 16;
        goto L23
    L25:
        int r33 = 0;
    L27:
        if (r33 >= Math.min(64, r112)) goto L29;
        int r42 = r16 + r33;
        r15[r42] = (byte) zza(r22[r33], r19[r42]);
        r33 = r33 + 1;
        goto L27
    L29:
        r8 = r8 + 1;
        r17 = r112 - 64;
        r16 = r16 + 64;
        r6 = r20;
        r11 = '\f';
        r12 = 16;
        goto L17
    L30:
        byte[] r0 = new byte[r14 + 12];
        System.arraycopy(r7, 0, r0, 0, 12);
        System.arraycopy(r15, 0, r0, 12, r14);
        return r0;
    L32:
        Segment$$ExternalSyntheticBUOutline0.m();
        return null;
    L34:
        Segment$$ExternalSyntheticBUOutline0.m();
        return null;
    }

    public static final void zzc(int r0, int r1, int r2, int r3, int[] r4, byte[] r5, byte[] r6, int r7, int[] r8, int[] r9) {
        int r42 = r8[r0] + r8[r1];
        r8[r0] = r42;
        int r02 = zza(r8[r2], r42);
        int r12 = r02 >>> (32 - r3);
        r8[r2] = (r02 << r3) | r12;
    }

    public static final void zzd(int r14, int r15, int r16, int r17, int[] r18, byte[] r19, byte[] r20, int r21, int[] r22, int[] r23) {
        zzc(r14, r15, r17, 16, r18, r19, r20, r21, r22, r23);
        zzc(r16, r17, r15, 12, r18, r19, r20, r21, r22, r23);
        zzc(r14, r15, r17, 8, r18, r19, r20, r21, r22, r23);
        zzc(r16, r17, r15, 7, r18, r19, r20, r21, r22, r23);
    }

    private static final int zze(byte[] r3, int r4) {
        int r0 = r3[r4] & UByte.MAX_VALUE;
        int r1 = r3[r4 + 1] & UByte.MAX_VALUE;
        int r2 = r3[r4 + 2] & UByte.MAX_VALUE;
        int r32 = r3[r4 + 3] & UByte.MAX_VALUE;
        int r33 = r32 << 24;
        return r33 | (((r1 << 8) | r0) | (r2 << 16));
    }
}
