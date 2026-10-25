package com.google.android.recaptcha.internal;

import java.math.BigInteger;
import java.util.HashMap;

/* loaded from: classes5.dex */
public abstract class zzaad {
    private static final zzvp zza = null;
    private static final zzvp zzb = null;
    private static final zzsm zzc = null;
    private static final zzsf zzd = null;
    private static final zztx zze = null;
    private static final int zzf = 0;

    static {
        zza = zzvp.zzc(zzaaa.zza, zzzx.class, zzss.class);
        zzb = zzvp.zzc(zzaac.zza, zzzz.class, zzst.class);
        zzc = zzug.zzb("type.googleapis.com/google.crypto.tink.RsaSsaPkcs1PrivateKey", zzss.class, zzxj.zzl());
        zzd = zzug.zzc("type.googleapis.com/google.crypto.tink.RsaSsaPkcs1PublicKey", zzst.class, 5, zzxl.zzi());
        zze = zzaab.zza;
        zzf = 2;
    }

    public static void zza(boolean r7) {
        int r72 = zzf;
        if (zzte.zza(r72) == false) goto L6;
        int r0 = zzacc.$r8$clinit;
        zzacc.zza(zzvb.zza());
        zzuw r02 = zzuw.zza();
        HashMap r1 = new HashMap();
        r1.put("RSA_SSA_PKCS1_3072_SHA256_F4", zzzn.zzh);
        BigInteger r2 = zzzv.zza;
        zzzs r22 = new zzzs(null);
        r22.zzd(zzzt.zza);
        r22.zza(3072);
        BigInteger r4 = zzzv.zza;
        r22.zzb(r4);
        zzzu r5 = zzzu.zzd;
        r22.zzc(r5);
        r1.put("RSA_SSA_PKCS1_3072_SHA256_F4_RAW", r22.zze());
        r1.put("RSA_SSA_PKCS1_3072_SHA256_F4_WITHOUT_PREFIX", zzzn.zzi);
        r1.put("RSA_SSA_PKCS1_4096_SHA512_F4", zzzn.zzj);
        zzzs r23 = new zzzs(null);
        r23.zzd(zzzt.zzc);
        r23.zza(4096);
        r23.zzb(r4);
        r23.zzc(r5);
        r1.put("RSA_SSA_PKCS1_4096_SHA512_F4_RAW", r23.zze());
        r02.zzc(r1);
        zzux.zza().zzb(zza);
        zzux.zza().zzb(zzb);
        zzur.zza().zzb(zze, zzzv.class);
        zzty.zza().zzc(zzc, r72, true);
        zzty.zza().zzc(zzd, r72, false);
        return;
    L6:
        zzsj$$ExternalSyntheticBUOutline1.m("Can not use RSA SSA PKCS1 in FIPS-mode, as BoringCrypto module is not available.");
    }
}
