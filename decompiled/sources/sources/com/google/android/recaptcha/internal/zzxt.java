package com.google.android.recaptcha.internal;

import java.security.spec.ECParameterSpec;

/* loaded from: classes5.dex */
public final class zzxt {
    public static final zzxt zza = null;
    public static final zzxt zzb = null;
    public static final zzxt zzc = null;
    private final String zzd;
    private final ECParameterSpec zze;

    static {
        zza = new zzxt("NIST_P256", zztt.zza);
        zzb = new zzxt("NIST_P384", zztt.zzb);
        zzc = new zzxt("NIST_P521", zztt.zzc);
    }

    private zzxt(String r1, ECParameterSpec r2) {
        this.zzd = r1;
        this.zze = r2;
    }

    public final String toString() {
        return this.zzd;
    }

    public final ECParameterSpec zza() {
        return this.zze;
    }
}
