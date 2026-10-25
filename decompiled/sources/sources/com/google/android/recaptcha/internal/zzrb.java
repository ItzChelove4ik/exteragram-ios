package com.google.android.recaptcha.internal;

/* loaded from: classes5.dex */
final class zzrb extends zzra {
    static final zzrf zza = null;

    static {
        zza = new zzrb();
    }

    public zzrb() {
    }

    public static long zzd(long r5, long r7) {
        long r72 = (r7 ^ r5) * (-4132994306676758123L);
        long r52 = (r5 ^ (r72 ^ (r72 >>> 47))) * (-4132994306676758123L);
        return (r52 ^ (r52 >>> 47)) * (-4132994306676758123L);
    }

    private static void zze(byte[] r6, int r7, long r8, long r10, long[] r12) {
        long r82 = r8 + zzrn.zza(r6, r7);
        int r0 = r7 + 24;
        int r1 = r7 + 16;
        long r2 = zzrn.zza(r6, r7 + 8);
        long r4 = zzrn.zza(r6, r1);
        long r62 = zzrn.zza(r6, r0);
        long r22 = (r2 + r82) + r4;
        long r102 = Long.rotateRight((r10 + r82) + r62, 51) + Long.rotateRight(r22, 23);
        r12[0] = r22 + r62;
        r12[1] = r102 + r82;
    }

    public final String toString() {
        return "Hashing.fingerprint2011()";
    }

    @Override // com.google.android.recaptcha.internal.zzqx
    public final zzre zzb(byte[] r31, int r32, int r33) {
        byte[] r0 = r31;
        zzoq.zzg(0, r33, r0.length);
        if (r33 > 32) goto L17;
        long r3 = -4132994306676758123L;
        long r1 = (r33 * (-4132994306676758123L)) ^ (-1397348546323613475L);
        int r5 = 0;
    L6:
        if (r5 >= (r33 & (-8))) goto L8;
        long r15 = zzrn.zza(r0, r5) * (-4132994306676758123L);
        r1 = (r1 ^ ((r15 ^ (r15 >>> 47)) * (-4132994306676758123L))) * (-4132994306676758123L);
        r5 = r5 + 8;
        goto L6
    L8:
        int r52 = r33 & 7;
        if (r52 == 0) goto L14;
        int r152 = zzrn.$r8$clinit;
        int r153 = 0;
        long r16 = 0;
    L11:
        if (r153 >= Math.min(r52, 8)) goto L13;
        r16 = r16 | ((r0[r6 + r153] & 255) << (r153 * 8));
        r153 = r153 + 1;
        r3 = r3;
        goto L11
    L13:
        long r19 = r3;
        r1 = (r1 ^ r16) * r19;
    L15:
        long r12 = (r1 ^ (r1 >>> 47)) * r19;
        long r13 = r12 ^ (r12 >>> 47);
        long r21 = -6505348102511208375L;
        long r17 = 0;
    L23:
        if (r33 < 8) goto L25;
        long r34 = zzrn.zza(r0, 0);
    L27:
        if (r33 < 9) goto L29;
        long r122 = zzrn.zza(r0, r33 - 8);
    L30:
        long r02 = zzd(r13 + r122, r34);
        if (r02 != r17) goto L33;
    L35:
        r02 = r02 - 2;
    L36:
        int r2 = zzre.$r8$clinit;
        return new zzrd(r02);
    L33:
        if (r02 != 1) goto L36;
        r02 = 1;
        goto L35
    L29:
        r122 = r21;
        goto L30
    L25:
        r34 = r21;
        goto L27
    L14:
        r19 = -4132994306676758123L;
        goto L15
    L17:
        if (r33 > 64) goto L19;
        long r22 = zzrn.zza(r0, 24);
        int r6 = r33 - 16;
        r17 = 0;
        long r4 = zzrn.zza(r0, 0) + ((r33 + zzrn.zza(r0, r6)) * (-6505348102511208375L));
        long r9 = Long.rotateRight(r4 + r22, 52);
        long r192 = Long.rotateRight(r4, 37);
        long r42 = r4 + zzrn.zza(r0, 8);
        r21 = -6505348102511208375L;
        long r193 = r192 + Long.rotateRight(r42, 7);
        long r43 = r42 + zzrn.zza(r0, 16);
        long r23 = r22 + r43;
        long r92 = r9 + Long.rotateRight(r43, 31);
        long r44 = zzrn.zza(r0, 16) + zzrn.zza(r0, r33 - 32);
        long r232 = zzrn.zza(r0, r33 - 8);
        long r8 = Long.rotateRight(r44 + r232, 52);
        long r154 = Long.rotateRight(r44, 37);
        long r45 = r44 + zzrn.zza(r0, r33 - 24);
        long r155 = r154 + Long.rotateRight(r45, 7);
        long r46 = r45 + zzrn.zza(r0, r6);
        long r233 = r46 + r232;
        long r82 = r8 + Long.rotateRight(r46, 31);
        long r47 = r92 + r193;
        long r24 = ((r23 + (r82 + r155)) * (-4288712594273399085L)) + ((r233 + r47) * (-6505348102511208375L));
        long r14 = ((r24 ^ (r24 >>> 47)) * (-6505348102511208375L)) + r47;
        r13 = (r14 ^ (r14 >>> 47)) * (-4288712594273399085L);
        goto L23
    L19:
        r21 = -6505348102511208375L;
        r17 = 0;
        long r83 = zzrn.zza(r0, 0);
        long r48 = zzrn.zza(r0, r33 - 16) ^ (-8261664234251669945L);
        long r194 = zzrn.zza(r0, r33 - 56) ^ (-6505348102511208375L);
        long r25 = r33;
        long[] r62 = new long[2];
        long[] r10 = new long[2];
        zze(r0, r33 - 64, r25, r48, r62);
        r0 = r31;
        zze(r0, r33 - 32, r25 * (-8261664234251669945L), -6505348102511208375L, r10);
        long r18 = r62[1];
        long r195 = r194 + ((r18 ^ (r18 >>> 47)) * (-8261664234251669945L));
        long r110 = Long.rotateRight(r195 + r83, 39) * (-8261664234251669945L);
        int r93 = (r33 - 1) & (-64);
        char r63 = 0;
        long r49 = Long.rotateRight(r48, 33) * (-8261664234251669945L);
        long r26 = r110;
        int r111 = 0;
    L20:
        long r27 = Long.rotateRight(((r26 + r49) + r62[r63]) + zzrn.zza(r0, r111 + 16), 37) * (-8261664234251669945L);
        long r410 = Long.rotateRight((r49 + r62[1]) + zzrn.zza(r0, r111 + 48), 42) * (-8261664234251669945L);
        long r123 = r10[1] ^ r27;
        long r272 = r410 ^ r62[0];
        long r196 = Long.rotateRight(r195 ^ r10[0], 33);
        zze(r0, r111, r62[1] * (-8261664234251669945L), r10[0] + r123, r62);
        int r29 = r111;
        r0 = r31;
        long[] r64 = r10;
        r49 = r272;
        zze(r0, r29 + 32, r196 + r10[1], r49, r64);
        r111 = r29 + 64;
        r93 = r93 - 64;
        if (r93 == 0) goto L22;
        r10 = r64;
        r26 = r196;
        r63 = 0;
        r195 = r123;
        goto L20
    L22:
        r13 = zzd((zzd(r62[0], r64[0]) + ((r49 ^ (r49 >>> 47)) * (-8261664234251669945L))) + r123, zzd(r62[1], r64[1]) + r196);
        goto L23
    }
}
