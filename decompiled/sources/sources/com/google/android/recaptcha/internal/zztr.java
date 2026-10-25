package com.google.android.recaptcha.internal;

import java.lang.reflect.Array;
import java.math.BigInteger;

/* loaded from: classes5.dex */
abstract class zztr {
    static final long[] zza = null;
    static final long[] zzb = null;
    static final long[] zzc = null;
    static final zztk[][] zzd = null;
    static final zztk[] zze = null;
    private static final BigInteger zzf = null;
    private static final BigInteger zzg = null;
    private static final BigInteger zzh = null;
    private static final BigInteger zzi = null;

    static {
        BigInteger r2 = BigInteger.valueOf(2).pow(255).subtract(BigInteger.valueOf(19));
        zzf = r2;
        BigInteger r3 = BigInteger.valueOf(-121665).multiply(BigInteger.valueOf(121666).modInverse(r2)).mod(r2);
        zzg = r3;
        BigInteger r4 = BigInteger.valueOf(2).multiply(r3).mod(r2);
        zzh = r4;
        BigInteger r0 = BigInteger.valueOf(2);
        BigInteger r1 = BigInteger.ONE;
        BigInteger r02 = r0.modPow(r2.subtract(r1).divide(BigInteger.valueOf(4)), r2);
        zzi = r02;
        zztq r5 = new zztq(null);
        r5.zzd(BigInteger.valueOf(4).multiply(BigInteger.valueOf(5).modInverse(r2)).mod(r2));
        BigInteger r6 = r5.zzc();
        BigInteger r12 = r6.pow(2).subtract(r1).multiply(r3.multiply(r6.pow(2)).add(r1).modInverse(r2));
        BigInteger r62 = r12.modPow(r2.add(BigInteger.valueOf(3)).divide(BigInteger.valueOf(8)), r2);
        if (r62.pow(2).subtract(r12).mod(r2).equals(BigInteger.ZERO) == true) goto L5;
        r62 = r62.multiply(r02).mod(r2);
    L5:
        int r13 = 0;
        if (r62.testBit(0) == false) goto L8;
        r62 = r2.subtract(r62);
    L8:
        r5.zzb(r62);
        zza = zztw.zzg(zzb(r3));
        zzb = zztw.zzg(zzb(r4));
        zzc = zztw.zzg(zzb(r02));
        zzd = (zztk[][]) Array.newInstance(zztk.class, new int[]{32, 8});
        int r03 = 0;
        zztq r42 = r5;
    L9:
        if (r03 >= 32) goto L17;
        int r63 = 0;
        zztq r7 = r42;
    L11:
        if (r63 >= 8) goto L13;
        zzd[r03][r63] = zzc(r7);
        r7 = zza(r7, r42);
        r63 = r63 + 1;
        goto L11
    L13:
        int r64 = 0;
    L14:
        if (r64 >= 8) goto L16;
        r42 = zza(r42, r42);
        r64 = r64 + 1;
        goto L14
    L16:
        r03 = r03 + 1;
        goto L9
    L17:
        zztq r04 = zza(r5, r5);
        zze = new zztk[8];
    L18:
        if (r13 >= 8) goto L20;
        zze[r13] = zzc(r5);
        r5 = zza(r5, r04);
        r13 = r13 + 1;
        goto L18
    }

    private static zztq zza(zztq r6, zztq r7) {
        zztq r0 = new zztq(null);
        BigInteger r1 = r6.zza();
        BigInteger r12 = zzg.multiply(r1.multiply(r7.zza()).multiply(r6.zzc()).multiply(r7.zzc()));
        BigInteger r2 = zzf;
        BigInteger r13 = r12.mod(r2);
        BigInteger r3 = r6.zza();
        BigInteger r4 = r7.zza();
        BigInteger r32 = r3.multiply(r7.zzc()).add(r4.multiply(r6.zzc()));
        BigInteger r42 = BigInteger.ONE;
        r0.zzb(r32.multiply(r42.add(r13).modInverse(r2)).mod(r2));
        BigInteger r33 = r6.zzc();
        BigInteger r62 = r6.zza();
        r0.zzd(r33.multiply(r7.zzc()).add(r62.multiply(r7.zza())).multiply(r42.subtract(r13).modInverse(r2)).mod(r2));
        return r0;
    }

    private static byte[] zzb(BigInteger r4) {
        byte[] r0 = new byte[32];
        byte[] r42 = r4.toByteArray();
        int r1 = r42.length;
        int r3 = 0;
        System.arraycopy(r42, 0, r0, 32 - r1, r1);
    L4:
        if (r3 >= 16) goto L6;
        byte r43 = r0[r3];
        int r12 = 31 - r3;
        r0[r3] = r0[r12];
        r0[r12] = r43;
        r3 = r3 + 1;
        goto L4
    L6:
        return r0;
    }

    private static zztk zzc(zztq r6) {
        BigInteger r0 = r6.zzc().add(r6.zza());
        BigInteger r2 = zzf;
        return new zztk(zztw.zzg(zzb(r0.mod(r2))), zztw.zzg(zzb(r6.zzc().subtract(r6.zza()).mod(r2))), zztw.zzg(zzb(zzh.multiply(r6.zza()).multiply(r6.zzc()).mod(r2))));
    }
}
