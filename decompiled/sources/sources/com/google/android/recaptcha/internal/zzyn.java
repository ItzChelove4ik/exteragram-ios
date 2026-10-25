package com.google.android.recaptcha.internal;

import java.util.Collections;
import java.util.HashMap;

/* loaded from: classes5.dex */
public abstract class zzyn {
    private static final zzvp zza = null;
    private static final zzvp zzb = null;
    private static final zzsm zzc = null;
    private static final zzsf zzd = null;
    private static final zzus zze = null;
    private static final zztx zzf = null;

    static {
        zza = zzvp.zzc(zzyl.zza, zzyi.class, zzss.class);
        zzb = zzvp.zzc(zzym.zza, zzyo.class, zzst.class);
        zzc = zzug.zzb("type.googleapis.com/google.crypto.tink.Ed25519PrivateKey", zzss.class, zzwl.zzg());
        zzd = zzug.zzc("type.googleapis.com/google.crypto.tink.Ed25519PublicKey", zzst.class, 5, zzwn.zzg());
        zze = zzyk.zza;
        zzf = zzyj.zza;
    }

    public static void zza(boolean r5) {
        if (zzte.zza(1) == false) goto L6;
        zzabt.zza(zzvb.zza());
        zzuw r0 = zzuw.zza();
        HashMap r1 = new HashMap();
        r1.put("ED25519", zzyh.zza(zzyg.zza));
        zzyg r2 = zzyg.zzd;
        r1.put("ED25519_RAW", zzyh.zza(r2));
        r1.put("ED25519WithRawOutput", zzyh.zza(r2));
        r0.zzc(Collections.unmodifiableMap(r1));
        zzur.zza().zzb(zzf, zzyh.class);
        zzut.zza().zzb(zze, zzyh.class);
        zzux.zza().zzb(zza);
        zzux.zza().zzb(zzb);
        zzty.zza().zzb(zzc, true);
        zzty.zza().zzb(zzd, false);
        return;
    L6:
        zzsj$$ExternalSyntheticBUOutline1.m("Registering AES GCM SIV is not supported in FIPS mode");
    }
}
