package com.google.android.recaptcha.internal;

/* loaded from: classes5.dex */
public final class zzsg {
    public static final zzsg zza = null;
    public static final zzsg zzb = null;
    public static final zzsg zzc = null;
    private final String zzd;

    static {
        zza = new zzsg("ENABLED");
        zzb = new zzsg("DISABLED");
        zzc = new zzsg("DESTROYED");
    }

    private zzsg(String r1) {
        this.zzd = r1;
    }

    public final String toString() {
        return this.zzd;
    }
}
