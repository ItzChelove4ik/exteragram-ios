package com.google.android.recaptcha.internal;

/* loaded from: classes5.dex */
public final class zzzu {
    public static final zzzu zza = null;
    public static final zzzu zzb = null;
    public static final zzzu zzc = null;
    public static final zzzu zzd = null;
    private final String zze;

    static {
        zza = new zzzu("TINK");
        zzb = new zzzu("CRUNCHY");
        zzc = new zzzu("LEGACY");
        zzd = new zzzu("NO_PREFIX");
    }

    private zzzu(String r1) {
        this.zze = r1;
    }

    public final String toString() {
        return this.zze;
    }
}
