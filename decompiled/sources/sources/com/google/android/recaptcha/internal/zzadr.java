package com.google.android.recaptcha.internal;

import java.security.KeyFactory;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAPublicKeySpec;

/* loaded from: classes5.dex */
public abstract class zzadr implements zzst {
    static final zztv zza = null;
    private static final byte[] zzb = null;
    private static final byte[] zzc = null;

    static {
        zzb = new byte[0];
        zzc = new byte[]{0};
        zztu r0 = zztv.zza();
        r0.zza(zzado.zzc, zzzt.zza);
        r0.zza(zzado.zzd, zzzt.zzb);
        r0.zza(zzado.zze, zzzt.zzc);
        zza = r0.zzb();
    }

    public static zzst zzb(zzzz r7) {
        return zzace.zzd(r7);
    L4:
        RSAPublicKey r2 = (RSAPublicKey) ((KeyFactory) zzadf.zzc.zzb("RSA")).generatePublic(new RSAPublicKeySpec(r7.zzd(), r7.zzf().zzc()));
        zzado r3 = (zzado) zza.zzb(r7.zzf().zze());
        byte[] r4 = r7.zze().zzc();
        if (r7.zzf().zzd().equals(zzzu.zzc) == false) goto L8;
        byte[] r72 = zzc;
    L10:
        return new zzadq(r2, r3, r4, r72, null);
    L8:
        r72 = zzb;
        goto L10
    }
}
