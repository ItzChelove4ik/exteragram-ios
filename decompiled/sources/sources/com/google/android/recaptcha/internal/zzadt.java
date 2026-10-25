package com.google.android.recaptcha.internal;

import java.security.KeyFactory;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.spec.RSAPrivateCrtKeySpec;

/* loaded from: classes5.dex */
public abstract class zzadt implements zzss {
    private static final byte[] zza = null;
    private static final byte[] zzb = null;

    static {
        zza = new byte[0];
        zzb = new byte[]{0};
    }

    public static zzss zzb(zzaaj r12) {
        return zzacm.zzb(r12);
    L4:
        RSAPrivateCrtKey r2 = (RSAPrivateCrtKey) ((KeyFactory) zzadf.zzc.zzb("RSA")).generatePrivate(new RSAPrivateCrtKeySpec(r12.zze().zzd(), r12.zzd().zzc(), r12.zzi().zzb(zzsd.zza()), r12.zzf().zzb(zzsd.zza()), r12.zzh().zzb(zzsd.zza()), r12.zzj().zzb(zzsd.zza()), r12.zzk().zzb(zzsd.zza()), r12.zzl().zzb(zzsd.zza())));
        zzaah r0 = r12.zzd();
        zztv r3 = zzadv.zza;
        zzado r4 = (zzado) r3.zzb(r0.zze());
        zzado r32 = (zzado) r3.zzb(r0.zzf());
        int r5 = r0.zzg();
        byte[] r6 = r12.zze().zze().zzc();
        if (r12.zzd().zzd().equals(zzaag.zzc) == false) goto L8;
        byte[] r122 = zzb;
    L10:
        return new zzads(r2, r4, r32, r5, r6, r122, null);
    L8:
        r122 = zza;
        goto L10
    }
}
