package com.google.android.recaptcha.internal;

import java.security.KeyFactory;
import java.security.NoSuchProviderException;
import java.security.Provider;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.spec.RSAPrivateCrtKeySpec;

/* loaded from: classes5.dex */
public final class zzacm implements zzss {
    private static final byte[] zza = null;
    private static final byte[] zzb = null;

    static {
        zza = new byte[0];
        zzb = new byte[]{0};
    }

    private zzacm(RSAPrivateCrtKey r1, zzaaf r2, zzaaf r3, int r4, byte[] r5, byte[] r6, Provider r7) {
        if (zzte.zza(2) == false) goto L6;
        zzadx.zzb(r1.getModulus().bitLength());
        zzadx.zzc(r1.getPublicExponent());
        zzacn.zzc(r2);
        zzacn.zzd(r2, r3, r4);
        return;
    L6:
        zzsj$$ExternalSyntheticBUOutline1.m("Cannot use RSA PSS in FIPS-mode, as BoringCrypto module is not available.");
        throw null;
    }

    public static zzss zzb(zzaaj r18) {
        Provider r7 = zzacn.zzb();
        if (r7 == null) goto L12;
        KeyFactory r0 = KeyFactory.getInstance("RSA", r7);
        zzaah r1 = r18.zzd();
        RSAPrivateCrtKey r02 = (RSAPrivateCrtKey) r0.generatePrivate(new RSAPrivateCrtKeySpec(r18.zze().zzd(), r1.zzc(), r18.zzi().zzb(zzsd.zza()), r18.zzf().zzb(zzsd.zza()), r18.zzh().zzb(zzsd.zza()), r18.zzj().zzb(zzsd.zza()), r18.zzk().zzb(zzsd.zza()), r18.zzl().zzb(zzsd.zza())));
        zzaaf r2 = r1.zze();
        zzaaf r3 = r1.zzf();
        int r4 = r1.zzg();
        byte[] r6 = r18.zze().zze().zzc();
        if (r1.zzd().equals(zzaag.zzc) == false) goto L8;
        byte[] r5 = zzb;
    L10:
        return new zzacm(r02, r2, r3, r4, r6, r5, r7);
    L8:
        r5 = zza;
        goto L10
    L12:
        throw new NoSuchProviderException("RSA SSA PSS using Conscrypt is not supported.");
    }

    @Override // com.google.android.recaptcha.internal.zzss
    public final byte[] zza(byte[] r1) {
        throw null;
    }
}
