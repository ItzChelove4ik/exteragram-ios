package com.google.android.recaptcha.internal;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.interfaces.RSAPublicKey;
import java.util.Arrays;

/* loaded from: classes5.dex */
final class zzadu implements zzst {
    private final RSAPublicKey zza;
    private final zzado zzb;
    private final zzado zzc;
    private final int zzd;
    private final byte[] zze;
    private final byte[] zzf;

    public /* synthetic */ zzadu(RSAPublicKey r2, zzado r3, zzado r4, int r5, byte[] r6, byte[] r7, byte[] r8) {
        if (zztf.zza() == true) goto L10;
        zzadx.zza(r3);
        if (r3.equals(r4) == false) goto L8;
        zzadx.zzb(r2.getModulus().bitLength());
        zzadx.zzc(r2.getPublicExponent());
        this.zza = r2;
        this.zzb = r3;
        this.zzc = r4;
        this.zzd = r5;
        this.zze = r6;
        this.zzf = r7;
        return;
    L8:
        zzsj$$ExternalSyntheticBUOutline1.m("sigHash and mgf1Hash must be the same");
        throw null;
    L10:
        zzsj$$ExternalSyntheticBUOutline1.m("Can not use RSA PSS in FIPS-mode, as BoringCrypto module is not available.");
        throw null;
    }

    private final void zzb(byte[] r23, byte[] r24) {
        RSAPublicKey r2 = this.zza;
        BigInteger r3 = r2.getPublicExponent();
        BigInteger r22 = r2.getModulus();
        int r4 = r22.bitLength() + 7;
        int r5 = r22.bitLength() + 6;
        if ((r4 / 8) != r23.length) goto L55;
        BigInteger r42 = new BigInteger(1, r23);
        if (r42.compareTo(r22) >= 0) goto L53;
        byte[] r1 = zzth.zzb(r42.modPow(r3, r22), r5 / 8);
        int r25 = r22.bitLength() - 1;
        zzado r32 = this.zzb;
        zzadx.zza(r32);
        zzadf r43 = zzadf.zzb;
        MessageDigest r33 = (MessageDigest) r43.zzb(zzadw.zzb(r32));
        r33.update(r24);
        byte[] r52 = this.zzf;
        if (r52.length == 0) goto L9;
        r33.update(r52);
    L9:
        byte[] r53 = r33.digest();
        int r8 = r33.getDigestLength();
        int r9 = r1.length;
        int r10 = this.zzd;
        if (r9 >= ((r8 + r10) + 2)) goto L12;
        zzsj$$ExternalSyntheticBUOutline1.m("inconsistent");
        return;
    L12:
        if (r1[r9 - 1] != (-68)) goto L49;
        int r11 = r9 - r8;
        int r13 = r11 - 1;
        byte[] r14 = Arrays.copyOf(r1, r13);
        int r15 = r14.length;
        byte[] r12 = Arrays.copyOfRange(r1, r15, r15 + r8);
        int r152 = 0;
    L14:
        long r18 = (r9 * 8) - r25;
        if (r152 >= r18) goto L21;
        if (((r14[r152 / 8] >> (7 - (r152 % 8))) & 1) != 0) goto L19;
        r152 = r152 + 1;
        goto L14
    L19:
        zzsj$$ExternalSyntheticBUOutline1.m("inconsistent");
        return;
    L21:
        MessageDigest r0 = (MessageDigest) r43.zzb(zzadw.zzb(this.zzc));
        int r26 = r0.getDigestLength();
        byte[] r44 = new byte[r13];
        int r6 = 0;
        int r7 = 0;
    L23:
        if (r6 > ((r11 - 2) / r26)) goto L25;
        r0.reset();
        r0.update(r12);
        r0.update(zzth.zzb(BigInteger.valueOf(r6), 4));
        byte[] r82 = r0.digest();
        int r92 = r82.length;
        System.arraycopy(r82, 0, r44, r7, Math.min(r92, r13 - r7));
        r7 = r7 + r92;
        r6 = r6 + 1;
        r0 = r0;
        goto L23
    L25:
        byte[] r02 = new byte[r13];
        int r27 = 0;
    L26:
        if (r27 >= r13) goto L28;
        r02[r27] = (byte) (r44[r27] ^ r14[r27]);
        r27 = r27 + 1;
        goto L26
    L28:
        int r28 = 0;
    L30:
        if (r28 > r18) goto L32;
        int r45 = r28 / 8;
        r02[r45] = (byte) ((~(1 << (7 - (r28 % 8)))) & r02[r45]);
        r28 = r28 + 1;
        goto L30
    L32:
        int r29 = 0;
    L33:
        int r46 = (r11 - r10) - 2;
        if (r29 >= r46) goto L41;
        if (r02[r29] != 0) goto L38;
        r29 = r29 + 1;
        goto L33
    L38:
        zzsj$$ExternalSyntheticBUOutline1.m("inconsistent");
        return;
    L41:
        if (r02[r46] != 1) goto L47;
        byte[] r03 = Arrays.copyOfRange(r02, r13 - r10, r13);
        int r83 = r8 + 8;
        byte[] r210 = new byte[r10 + r83];
        System.arraycopy(r53, 0, r210, 8, r53.length);
        System.arraycopy(r03, 0, r210, r83, r03.length);
        if (MessageDigest.isEqual(r33.digest(r210), r12) == false) goto L45;
        return;
    L45:
        zzsj$$ExternalSyntheticBUOutline1.m("inconsistent");
        return;
    L47:
        zzsj$$ExternalSyntheticBUOutline1.m("inconsistent");
        return;
    L49:
        zzsj$$ExternalSyntheticBUOutline1.m("inconsistent");
        return;
    L53:
        zzsj$$ExternalSyntheticBUOutline1.m("signature out of range");
        return;
    L55:
        zzsj$$ExternalSyntheticBUOutline1.m("invalid signature's length");
    }

    @Override // com.google.android.recaptcha.internal.zzst
    public final void zza(byte[] r3, byte[] r4) {
        byte[] r0 = this.zze;
        int r1 = r0.length;
        if (r1 != 0) goto L7;
        zzb(r3, r4);
        return;
    L7:
        if (zzwc.zzc(r0, r3) == false) goto L10;
        zzb(Arrays.copyOfRange(r3, r1, r3.length), r4);
        return;
    L10:
        zzsj$$ExternalSyntheticBUOutline1.m("Invalid signature (output prefix mismatch)");
    }
}
