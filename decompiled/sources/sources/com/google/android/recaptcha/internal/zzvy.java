package com.google.android.recaptcha.internal;

import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes5.dex */
public final class zzvy implements zzsq {
    private final Map zza;
    private final Map zzb;
    private final Map zzc;
    private final Map zzd;

    public /* synthetic */ zzvy(zzvx r2, byte[] r3) {
        this.zza = new HashMap(r2.zze());
        this.zzb = new HashMap(r2.zzf());
        this.zzc = new HashMap(r2.zzg());
        this.zzd = new HashMap(r2.zzh());
    }

    @Override // com.google.android.recaptcha.internal.zzsq
    public final zzse zza(zzsp r3, zzsy r4) {
        Map r2 = this.zzb;
        String r0 = r3.zzb();
        if (r2.containsKey(r0) == true) goto L5;
        StringBuilder r42 = new StringBuilder(String.valueOf(r0).length() + 47);
        r42.append("No Key Parser for requested key type ");
        r42.append(r0);
        r42.append(" available");
        throw new GeneralSecurityException(r42.toString());
    L5:
        return ((zzub) r2.get(r0)).zza(r3, r4);
    }

    @Override // com.google.android.recaptcha.internal.zzsq
    public final zzsp zzb(zzse r2, zzsy r3) {
        Map r1 = this.zza;
        if (r1.containsKey(r2.getClass()) == true) goto L5;
        String r22 = String.valueOf(r2.getClass());
        StringBuilder r0 = new StringBuilder(r22.length() + 32);
        r0.append("No Key serializer for ");
        r0.append(r22);
        r0.append(" available");
        throw new GeneralSecurityException(r0.toString());
    L5:
        return ((zzue) r1.get(r2.getClass())).zza(r2, r3);
    }

    public final boolean zzc(zzsp r1) {
        return this.zzb.containsKey(r1.zzb());
    }

    public final /* synthetic */ Map zzd() {
        return this.zza;
    }

    public final /* synthetic */ Map zze() {
        return this.zzb;
    }

    public final /* synthetic */ Map zzf() {
        return this.zzc;
    }

    public final /* synthetic */ Map zzg() {
        return this.zzd;
    }
}
