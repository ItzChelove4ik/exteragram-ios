package com.google.android.recaptcha.internal;

import c.f$$ExternalSyntheticBUOutline1;
import java.security.KeyFactory;
import java.security.NoSuchProviderException;
import java.security.Provider;
import java.security.Signature;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.MGF1ParameterSpec;
import java.security.spec.PSSParameterSpec;
import java.security.spec.RSAPublicKeySpec;

/* loaded from: classes5.dex */
public final class zzacn implements zzst {
    private static final byte[] zza = null;
    private static final byte[] zzb = null;
    private final RSAPublicKey zzc;
    private final String zzd;
    private final PSSParameterSpec zze;
    private final byte[] zzf;
    private final byte[] zzg;
    private final Provider zzh;

    static {
        zza = new byte[0];
        zzb = new byte[]{0};
    }

    private zzacn(RSAPublicKey r3, zzaaf r4, zzaaf r5, int r6, byte[] r7, byte[] r8, Provider r9) {
        if (zzte.zza(2) == true) goto L5;
        zzsj$$ExternalSyntheticBUOutline1.m("Cannot use RSA SSA PSS in FIPS-mode, as BoringCrypto module is not available.");
        throw null;
    L5:
        if (r4.equals(r5) == false) goto L8;
        zzadx.zzb(r3.getModulus().bitLength());
        zzadx.zzc(r3.getPublicExponent());
        this.zzc = r3;
        this.zzd = zzc(r4);
        this.zze = zzd(r4, r5, r6);
        this.zzf = r7;
        this.zzg = r8;
        this.zzh = r9;
        return;
    L8:
        zzsj$$ExternalSyntheticBUOutline1.m("sigHash and mgf1Hash must be the same");
        throw null;
    }

    public static Provider zzb() {
        if (zzwc.zza() == false) goto L6;
        zzwc.zzb().getClass();
    L6:
        return zzti.zza();
    }

    public static String zzc(zzaaf r1) {
        if (r1 != zzaaf.zza) goto L7;
        return "SHA256withRSA/PSS";
    L7:
        if (r1 != zzaaf.zzb) goto L11;
        return "SHA384withRSA/PSS";
    L11:
        if (r1 != zzaaf.zzc) goto L14;
        return "SHA512withRSA/PSS";
    L14:
        f$$ExternalSyntheticBUOutline1.m("Unsupported hash: ".concat(String.valueOf(r1)));
        return null;
    }

    public static PSSParameterSpec zzd(zzaaf r6, zzaaf r7, int r8) {
        zzaaf r1 = zzaaf.zza;
        if (r6 != r1) goto L6;
        String r62 = "SHA-256";
    L11:
        if (r7 != r1) goto L15;
        MGF1ParameterSpec r72 = MGF1ParameterSpec.SHA256;
    L21:
        return new PSSParameterSpec(r62, "MGF1", r72, r8, 1);
    L15:
        if (r7 != zzaaf.zzb) goto L18;
        r72 = MGF1ParameterSpec.SHA384;
        goto L21
    L18:
        if (r7 != zzaaf.zzc) goto L22;
        r72 = MGF1ParameterSpec.SHA512;
        goto L21
    L22:
        f$$ExternalSyntheticBUOutline1.m("Unsupported MGF1 hash: ".concat(String.valueOf(r7)));
        return null;
    L6:
        if (r6 != zzaaf.zzb) goto L9;
        r62 = "SHA-384";
        goto L11
    L9:
        if (r6 != zzaaf.zzc) goto L24;
        r62 = "SHA-512";
        goto L11
    L24:
        f$$ExternalSyntheticBUOutline1.m("Unsupported MD hash: ".concat(String.valueOf(r6)));
        return null;
    }

    public static zzst zze(zzaal r8) {
        Provider r7 = zzb();
        if (r7 == null) goto L12;
        RSAPublicKey r1 = (RSAPublicKey) KeyFactory.getInstance("RSA", r7).generatePublic(new RSAPublicKeySpec(r8.zzd(), r8.zzf().zzc()));
        zzaah r0 = r8.zzf();
        zzaaf r2 = r0.zze();
        zzaaf r3 = r0.zzf();
        int r4 = r0.zzg();
        byte[] r5 = r8.zze().zzc();
        if (r8.zzf().zzd().equals(zzaag.zzc) == false) goto L8;
        byte[] r82 = zzb;
    L10:
        return new zzacn(r1, r2, r3, r4, r5, r82, r7);
    L8:
        r82 = zza;
        goto L10
    L12:
        throw new NoSuchProviderException("RSA SSA PSS using Conscrypt is not supported.");
    }

    @Override // com.google.android.recaptcha.internal.zzst
    public final void zza(byte[] r5, byte[] r6) {
        byte[] r0 = this.zzf;
        if (zzwc.zzc(r0, r5) == false) goto L12;
        String r1 = this.zzd;
        Provider r2 = this.zzh;
        RSAPublicKey r3 = this.zzc;
        Signature r12 = Signature.getInstance(r1, r2);
        r12.initVerify(r3);
        r12.setParameter(this.zze);
        r12.update(r6);
        byte[] r4 = this.zzg;
        if (r4.length <= 0) goto L7;
        r12.update(r4);
    L7:
        int r42 = r5.length;
        int r62 = r0.length;
        if (r12.verify(r5, r62, r42 - r62) == false) goto L10;
        return;
    L10:
        zzsj$$ExternalSyntheticBUOutline1.m("signature verification failed");
        return;
    L12:
        zzsj$$ExternalSyntheticBUOutline1.m("Invalid signature (output prefix mismatch)");
    }
}
