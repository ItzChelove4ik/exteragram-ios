package com.google.android.recaptcha.internal;

import java.math.BigInteger;
import java.util.Collections;
import java.util.HashMap;

/* loaded from: classes5.dex */
public abstract class zzaap {
    private static final zzvp zza = null;
    private static final zzvp zzb = null;
    private static final zzsm zzc = null;
    private static final zzsf zzd = null;
    private static final zztx zze = null;
    private static final int zzf = 0;

    static {
        zza = zzvp.zzc(zzaam.zza, zzaaj.class, zzss.class);
        zzb = zzvp.zzc(zzaao.zza, zzaal.class, zzst.class);
        zzc = zzug.zzb("type.googleapis.com/google.crypto.tink.RsaSsaPssPrivateKey", zzss.class, zzxp.zzl());
        zzd = zzug.zzc("type.googleapis.com/google.crypto.tink.RsaSsaPssPublicKey", zzst.class, 5, zzxr.zzi());
        zze = zzaan.zza;
        zzf = 2;
    }

    public static void zza(boolean r10) {
        int r102 = zzf;
        if (zzte.zza(r102) == false) goto L6;
        int r0 = zzacl.$r8$clinit;
        zzacl.zza(zzvb.zza());
        zzuw r02 = zzuw.zza();
        HashMap r1 = new HashMap();
        BigInteger r2 = zzaah.zza;
        zzaae r22 = new zzaae(null);
        zzaaf r4 = zzaaf.zza;
        r22.zzd(r4);
        r22.zze(r4);
        r22.zzf(32);
        r22.zza(3072);
        BigInteger r7 = zzaah.zza;
        r22.zzb(r7);
        zzaag r8 = zzaag.zza;
        r22.zzc(r8);
        r1.put("RSA_SSA_PSS_3072_SHA256_F4", r22.zzg());
        zzaae r23 = new zzaae(null);
        r23.zzd(r4);
        r23.zze(r4);
        r23.zzf(32);
        r23.zza(3072);
        r23.zzb(r7);
        zzaag r42 = zzaag.zzd;
        r23.zzc(r42);
        r1.put("RSA_SSA_PSS_3072_SHA256_F4_RAW", r23.zzg());
        r1.put("RSA_SSA_PSS_3072_SHA256_SHA256_32_F4", zzzn.zzk);
        zzaae r24 = new zzaae(null);
        zzaaf r5 = zzaaf.zzc;
        r24.zzd(r5);
        r24.zze(r5);
        r24.zzf(64);
        r24.zza(4096);
        r24.zzb(r7);
        r24.zzc(r8);
        r1.put("RSA_SSA_PSS_4096_SHA512_F4", r24.zzg());
        zzaae r25 = new zzaae(null);
        r25.zzd(r5);
        r25.zze(r5);
        r25.zzf(64);
        r25.zza(4096);
        r25.zzb(r7);
        r25.zzc(r42);
        r1.put("RSA_SSA_PSS_4096_SHA512_F4_RAW", r25.zzg());
        r1.put("RSA_SSA_PSS_4096_SHA512_SHA512_64_F4", zzzn.zzl);
        r02.zzc(Collections.unmodifiableMap(r1));
        zzux.zza().zzb(zza);
        zzux.zza().zzb(zzb);
        zzur.zza().zzb(zze, zzaah.class);
        zzty.zza().zzc(zzc, r102, true);
        zzty.zza().zzc(zzd, r102, false);
        return;
    L6:
        zzsj$$ExternalSyntheticBUOutline1.m("Can not use RSA SSA PSS in FIPS-mode, as BoringCrypto module is not available.");
    }
}
