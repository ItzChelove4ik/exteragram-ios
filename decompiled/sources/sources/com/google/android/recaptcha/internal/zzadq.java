package com.google.android.recaptcha.internal;

import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.interfaces.RSAPublicKey;
import java.util.Arrays;

/* loaded from: classes5.dex */
final class zzadq implements zzst {
    private final RSAPublicKey zza;
    private final zzado zzb;
    private final byte[] zzc;
    private final byte[] zzd;

    public /* synthetic */ zzadq(RSAPublicKey r1, zzado r2, byte[] r3, byte[] r4, byte[] r5) {
        if (zztf.zza() == true) goto L6;
        zzadx.zza(r2);
        zzadx.zzb(r1.getModulus().bitLength());
        zzadx.zzc(r1.getPublicExponent());
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
        this.zzd = r4;
        return;
    L6:
        zzsj$$ExternalSyntheticBUOutline1.m("Conscrypt is not available, and we cannot use Java Implementation of RSA-PKCS1.5 in FIPS-mode.");
        throw null;
    }

    private final void zzb(byte[] r9, byte[] r10) {
        RSAPublicKey r0 = this.zza;
        BigInteger r1 = r0.getPublicExponent();
        BigInteger r02 = r0.getModulus();
        int r2 = (r02.bitLength() + 7) / 8;
        if (r2 != r9.length) goto L35;
        BigInteger r3 = new BigInteger(1, r9);
        if (r3.compareTo(r02) >= 0) goto L33;
        byte[] r92 = zzth.zzb(r3.modPow(r1, r02), r2);
        zzado r03 = this.zzb;
        zzadx.zza(r03);
        MessageDigest r12 = (MessageDigest) zzadf.zzb.zzb(zzadw.zzb(r03));
        r12.update(r10);
        byte[] r8 = this.zzd;
        if (r8.length == 0) goto L9;
        r12.update(r8);
    L9:
        byte[] r82 = r12.digest();
        int r102 = r03.ordinal();
        int r13 = 2;
        if (r102 != 2) goto L12;
        byte[] r103 = zzadp.zza("3031300d060960864801650304020105000420");
    L20:
        int r04 = r82.length;
        int r32 = r04 + 19;
        if (r2 < (r04 + 30)) goto L31;
        byte[] r05 = new byte[r2];
        r05[0] = 0;
        r05[1] = 1;
        int r4 = 0;
    L23:
        int r6 = r13 + 1;
        if (r4 >= ((r2 - r32) - 3)) goto L26;
        r05[r13] = -1;
        r4 = r4 + 1;
        r13 = r6;
        goto L23
    L26:
        r05[r13] = 0;
        System.arraycopy(r103, 0, r05, r6, 19);
        System.arraycopy(r82, 0, r05, r13 + 20, r82.length);
        if (MessageDigest.isEqual(r92, r05) == false) goto L29;
        return;
    L29:
        zzsj$$ExternalSyntheticBUOutline1.m("invalid signature");
        return;
    L31:
        zzsj$$ExternalSyntheticBUOutline1.m("intended encoded message length too short");
        return;
    L12:
        if (r102 != 3) goto L14;
        r103 = zzadp.zza("3041300d060960864801650304020205000430");
        goto L20
    L14:
        if (r102 != 4) goto L17;
        r103 = zzadp.zza("3051300d060960864801650304020305000440");
        goto L20
    L17:
        throw new GeneralSecurityException("Unsupported hash ".concat(r03.toString()));
    L33:
        zzsj$$ExternalSyntheticBUOutline1.m("signature out of range");
        return;
    L35:
        zzsj$$ExternalSyntheticBUOutline1.m("invalid signature's length");
    }

    @Override // com.google.android.recaptcha.internal.zzst
    public final void zza(byte[] r3, byte[] r4) {
        byte[] r0 = this.zzc;
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
