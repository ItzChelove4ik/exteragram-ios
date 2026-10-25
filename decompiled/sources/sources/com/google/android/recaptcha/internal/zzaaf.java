package com.google.android.recaptcha.internal;

/* loaded from: classes5.dex */
public final class zzaaf {
    public static final zzaaf zza = null;
    public static final zzaaf zzb = null;
    public static final zzaaf zzc = null;
    private final String zzd;

    static {
        zza = new zzaaf("SHA256");
        zzb = new zzaaf("SHA384");
        zzc = new zzaaf("SHA512");
    }

    private zzaaf(String r1) {
        this.zzd = r1;
    }

    public final String toString() {
        return this.zzd;
    }
}
