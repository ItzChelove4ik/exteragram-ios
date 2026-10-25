package com.google.android.recaptcha.internal;

import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.Provider;
import java.security.Signature;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.spec.RSAPrivateCrtKeySpec;
import org.mvel2.asm.Constants$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
public final class zzacd implements zzss {
    private static final byte[] zzb = null;
    private static final byte[] zzc = null;
    private static final byte[] zzd = null;
    Provider zza;
    private final RSAPrivateCrtKey zze;
    private final String zzf;
    private final byte[] zzg;
    private final byte[] zzh;
    private final zzst zzi;

    static {
        zzb = new byte[0];
        zzc = new byte[]{0};
        zzd = new byte[]{1, 2, 3};
    }

    private zzacd(RSAPrivateCrtKey r2, zzzt r3, byte[] r4, byte[] r5, zzst r6, Provider r7) {
        if (zzte.zza(2) == true) goto L5;
        zzsj$$ExternalSyntheticBUOutline1.m("Can not use RSA PKCS1.5 in FIPS-mode, as BoringCrypto module is not available.");
        throw null;
    L5:
        if (r3 != zzzt.zza) goto L7;
    L13:
        zzadx.zzb(r2.getModulus().bitLength());
        zzadx.zzc(r2.getPublicExponent());
        this.zze = r2;
        this.zzf = zzace.zzc(r3);
        this.zzg = r4;
        this.zzh = r5;
        this.zzi = r6;
        this.zza = r7;
        return;
    L7:
        if (r3 == zzzt.zzb) goto L13;
        if (r3 == zzzt.zzc) goto L13;
        throw new GeneralSecurityException("Unsupported hash: ".concat(String.valueOf(r3)));
    }

    public static zzss zzb(zzzx r16) {
        Provider r6 = zzace.zzb();
        if (r6 == null) goto L5;
        KeyFactory r0 = KeyFactory.getInstance("RSA", r6);
    L6:
        RSAPrivateCrtKey r1 = (RSAPrivateCrtKey) r0.generatePrivate(new RSAPrivateCrtKeySpec(r16.zze().zzd(), r16.zzd().zzc(), r16.zzi().zzb(zzsd.zza()), r16.zzf().zzb(zzsd.zza()), r16.zzh().zzb(zzsd.zza()), r16.zzj().zzb(zzsd.zza()), r16.zzk().zzb(zzsd.zza()), r16.zzl().zzb(zzsd.zza())));
        if (r6 == null) goto L10;
        zzst r02 = zzace.zze(r16.zze(), r6);
    L9:
        zzst r5 = r02;
        zzzt r2 = r16.zzd().zze();
        byte[] r3 = r16.zze().zze().zzc();
        if (r16.zzd().zzd().equals(zzzu.zzc) == false) goto L14;
        byte[] r4 = zzc;
    L15:
        zzacd r03 = new zzacd(r1, r2, r3, r4, r5, r6);
        r03.zza(zzd);
        return r03;
    L14:
        r4 = zzb;
        goto L15
    L10:
        r02 = zzadr.zzb(r16.zze());
        goto L9
    L5:
        r0 = (KeyFactory) zzadf.zzc.zzb("RSA");
        goto L6
    }

    @Override // com.google.android.recaptcha.internal.zzss
    public final byte[] zza(byte[] r4) {
        Provider r0 = this.zza;
        String r1 = this.zzf;
        if (r0 == null) goto L5;
        Signature r02 = Signature.getInstance(r1, r0);
    L6:
        r02.initSign(this.zze);
        r02.update(r4);
        byte[] r12 = this.zzh;
        if (r12.length <= 0) goto L9;
        r02.update(r12);
    L9:
        byte[] r03 = r02.sign();
        byte[] r13 = this.zzg;
        if (r13.length <= 0) goto L17;
        r03 = zzacu.zza(new byte[][]{r13, r03});
    L17:
        this.zzi.zza(r03, r4);     // Catch: GeneralSecurityException -> L14
        return r03;
    L14:
        e = move-exception;
        Constants$$ExternalSyntheticBUOutline0.m("RSA signature computation error", e);
        return null;
    L5:
        r02 = (Signature) zzadf.zza.zzb(r1);
        goto L6
    }
}
