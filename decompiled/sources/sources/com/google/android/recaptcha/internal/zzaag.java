package com.google.android.recaptcha.internal;

/* loaded from: classes5.dex */
public final class zzaag {
    public static final zzaag zza = null;
    public static final zzaag zzb = null;
    public static final zzaag zzc = null;
    public static final zzaag zzd = null;
    private final String zze;

    static {
        zza = new zzaag("TINK");
        zzb = new zzaag("CRUNCHY");
        zzc = new zzaag("LEGACY");
        zzd = new zzaag("NO_PREFIX");
    }

    private zzaag(String r1) {
        this.zze = r1;
    }

    public final String toString() {
        return this.zze;
    }
}
