package com.google.android.recaptcha.internal;

import java.security.KeyFactory;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAPublicKeySpec;

/* loaded from: classes5.dex */
public abstract class zzadv implements zzst {
    static final zztv zza = null;
    private static final byte[] zzb = null;
    private static final byte[] zzc = null;

    static {
        zztu r0 = zztv.zza();
        r0.zza(zzado.zzc, zzaaf.zza);
        r0.zza(zzado.zzd, zzaaf.zzb);
        r0.zza(zzado.zze, zzaaf.zzc);
        zza = r0.zzb();
        zzb = new byte[0];
        zzc = new byte[]{0};
    }

    public static zzst zzb(zzaal r10) {
        return zzacn.zze(r10);
    L4:
        RSAPublicKey r2 = (RSAPublicKey) ((KeyFactory) zzadf.zzc.zzb("RSA")).generatePublic(new RSAPublicKeySpec(r10.zzd(), r10.zzf().zzc()));
        zzaah r0 = r10.zzf();
        zztv r3 = zza;
        zzado r4 = (zzado) r3.zzb(r0.zze());
        zzado r32 = (zzado) r3.zzb(r0.zzf());
        int r5 = r0.zzg();
        byte[] r6 = r10.zze().zzc();
        if (r10.zzf().zzd().equals(zzaag.zzc) == false) goto L8;
        byte[] r102 = zzc;
    L10:
        return new zzadu(r2, r4, r32, r5, r6, r102, null);
    L8:
        r102 = zzb;
        goto L10
    }
}
