package com.google.android.recaptcha.internal;

/* loaded from: classes5.dex */
public final class zzso {
    public static final zzso zza = null;
    public static final zzso zzb = null;
    public static final zzso zzc = null;
    public static final zzso zzd = null;
    public static final zzso zze = null;
    public static final zzso zzf = null;
    private final String zzg;

    static {
        zza = new zzso("UNKNOWN_PREFIX");
        zzb = new zzso("TINK");
        zzc = new zzso("LEGACY");
        zzd = new zzso("RAW");
        zze = new zzso("CRUNCHY");
        zzf = new zzso("WITH_ID_REQUIREMENT");
    }

    private zzso(String r1) {
        this.zzg = r1;
    }

    public final String toString() {
        return this.zzg;
    }
}
