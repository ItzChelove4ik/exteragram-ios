package com.google.android.recaptcha.internal;

import java.util.Collections;
import java.util.HashMap;

/* loaded from: classes5.dex */
public abstract class zzyf {
    private static final zzvp zza = null;
    private static final zzvp zzb = null;
    private static final zzsm zzc = null;
    private static final zzsf zzd = null;
    private static final zztx zze = null;
    private static final int zzf = 0;

    static {
        zza = zzvp.zzc(zzyc.zza, zzxz.class, zzss.class);
        zzb = zzvp.zzc(zzye.zza, zzyb.class, zzst.class);
        zzc = zzug.zzb("type.googleapis.com/google.crypto.tink.EcdsaPrivateKey", zzss.class, zzwg.zzg());
        zzd = zzug.zzc("type.googleapis.com/google.crypto.tink.EcdsaPublicKey", zzst.class, 5, zzwi.zzi());
        zze = zzyd.zza;
        zzf = 2;
    }

    public static void zza(boolean r8) {
        int r82 = zzf;
        if (zzte.zza(r82) == false) goto L6;
        zzabk.zza(zzvb.zza());
        zzuw r0 = zzuw.zza();
        HashMap r1 = new HashMap();
        r1.put("ECDSA_P256", zzzn.zza);
        r1.put("ECDSA_P256_IEEE_P1363", zzzn.zzd);
        zzxs r2 = new zzxs(null);
        r2.zzc(zzxu.zza);
        r2.zzb(zzxt.zza);
        r2.zza(zzxv.zza);
        r2.zzd(zzxw.zzd);
        r1.put("ECDSA_P256_RAW", r2.zze());
        r1.put("ECDSA_P256_IEEE_P1363_WITHOUT_PREFIX", zzzn.zzf);
        r1.put("ECDSA_P384", zzzn.zzb);
        r1.put("ECDSA_P384_IEEE_P1363", zzzn.zze);
        zzxs r22 = new zzxs(null);
        r22.zzc(zzxu.zzc);
        zzxt r4 = zzxt.zzb;
        r22.zzb(r4);
        zzxv r5 = zzxv.zzb;
        r22.zza(r5);
        zzxw r6 = zzxw.zza;
        r22.zzd(r6);
        r1.put("ECDSA_P384_SHA512", r22.zze());
        zzxs r23 = new zzxs(null);
        r23.zzc(zzxu.zzb);
        r23.zzb(r4);
        r23.zza(r5);
        r23.zzd(r6);
        r1.put("ECDSA_P384_SHA384", r23.zze());
        r1.put("ECDSA_P521", zzzn.zzc);
        r1.put("ECDSA_P521_IEEE_P1363", zzzn.zzg);
        r0.zzc(Collections.unmodifiableMap(r1));
        zzux.zza().zzb(zza);
        zzux.zza().zzb(zzb);
        zzur.zza().zzb(zze, zzxx.class);
        zzty.zza().zzc(zzc, r82, true);
        zzty.zza().zzc(zzd, r82, false);
        return;
    L6:
        zzsj$$ExternalSyntheticBUOutline1.m("Can not use ECDSA in FIPS-mode, as BoringCrypto module is not available.");
    }
}
