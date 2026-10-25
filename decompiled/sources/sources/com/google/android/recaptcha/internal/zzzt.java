package com.google.android.recaptcha.internal;

/* loaded from: classes5.dex */
public final class zzzt {
    public static final zzzt zza = null;
    public static final zzzt zzb = null;
    public static final zzzt zzc = null;
    private final String zzd;

    static {
        zza = new zzzt("SHA256");
        zzb = new zzzt("SHA384");
        zzc = new zzzt("SHA512");
    }

    private zzzt(String r1) {
        this.zzd = r1;
    }

    public final String toString() {
        return this.zzd;
    }
}
