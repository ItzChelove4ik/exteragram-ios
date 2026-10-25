package com.google.android.recaptcha.internal;

/* loaded from: classes5.dex */
public final class zzxu {
    public static final zzxu zza = null;
    public static final zzxu zzb = null;
    public static final zzxu zzc = null;
    private final String zzd;

    static {
        zza = new zzxu("SHA256");
        zzb = new zzxu("SHA384");
        zzc = new zzxu("SHA512");
    }

    private zzxu(String r1) {
        this.zzd = r1;
    }

    public final String toString() {
        return this.zzd;
    }
}
