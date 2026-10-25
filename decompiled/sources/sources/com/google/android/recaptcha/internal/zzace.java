package com.google.android.recaptcha.internal;

import java.security.KeyFactory;
import java.security.NoSuchProviderException;
import java.security.Provider;
import java.security.Signature;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAPublicKeySpec;
import java.util.Arrays;

/* loaded from: classes5.dex */
public final class zzace implements zzst {
    private static final byte[] zza = null;
    private static final byte[] zzb = null;
    private final RSAPublicKey zzc;
    private final String zzd;
    private final byte[] zze;
    private final byte[] zzf;
    private final Provider zzg;

    static {
        zza = new byte[0];
        zzb = new byte[]{0};
    }

    private zzace(RSAPublicKey r2, zzzt r3, byte[] r4, byte[] r5, Provider r6) {
        if (zzte.zza(2) == false) goto L6;
        zzadx.zzb(r2.getModulus().bitLength());
        zzadx.zzc(r2.getPublicExponent());
        this.zzc = r2;
        this.zzd = zzc(r3);
        this.zze = r4;
        this.zzf = r5;
        this.zzg = r6;
        return;
    L6:
        zzsj$$ExternalSyntheticBUOutline1.m("Can not use RSA-PKCS1.5 in FIPS-mode, as BoringCrypto module is not available.");
        throw null;
    }

    public static Provider zzb() {
        if (zzwc.zza() == false) goto L6;
        zzwc.zzb().getClass();
    L6:
        return zzti.zza();
    }

    public static String zzc(zzzt r1) {
        if (r1 != zzzt.zza) goto L7;
        return "SHA256withRSA";
    L7:
        if (r1 != zzzt.zzb) goto L11;
        return "SHA384withRSA";
    L11:
        if (r1 != zzzt.zzc) goto L14;
        return "SHA512withRSA";
    L14:
        zzsj$$ExternalSyntheticBUOutline1.m("unknown hash type");
        return null;
    }

    public static zzst zzd(zzzz r1) {
        Provider r0 = zzb();
        if (r0 == null) goto L7;
        return zze(r1, r0);
    L7:
        throw new NoSuchProviderException("RSA-PKCS1.5 using Conscrypt is not supported.");
    }

    public static zzst zze(zzzz r7, Provider r8) {
        RSAPublicKey r2 = (RSAPublicKey) KeyFactory.getInstance("RSA", r8).generatePublic(new RSAPublicKeySpec(r7.zzd(), r7.zzf().zzc()));
        zzzt r3 = r7.zzf().zze();
        byte[] r4 = r7.zze().zzc();
        if (r7.zzf().zzd().equals(zzzu.zzc) == false) goto L6;
        byte[] r72 = zzb;
    L8:
        return new zzace(r2, r3, r4, r72, r8);
    L6:
        r72 = zza;
        goto L8
    }

    @Override // com.google.android.recaptcha.internal.zzst
    public final void zza(byte[] r5, byte[] r6) {
        byte[] r0 = this.zze;
        if (zzwc.zzc(r0, r5) == false) goto L12;
        String r1 = this.zzd;
        Provider r2 = this.zzg;
        RSAPublicKey r3 = this.zzc;
        Signature r12 = Signature.getInstance(r1, r2);
        r12.initVerify(r3);
        r12.update(r6);
        byte[] r4 = this.zzf;
        if (r4.length <= 0) goto L15;
        r12.update(r4);
    L15:
        if (r12.verify(Arrays.copyOfRange(r5, r0.length, r5.length)) == false) goto L10;
        return;
    L10:
        zzsj$$ExternalSyntheticBUOutline1.m("Invalid signature");
        return;
    L12:
        zzsj$$ExternalSyntheticBUOutline1.m("Invalid signature (output prefix mismatch)");
    }
}
