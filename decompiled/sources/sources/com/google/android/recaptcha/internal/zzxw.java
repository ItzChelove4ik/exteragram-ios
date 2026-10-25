package com.google.android.recaptcha.internal;

/* loaded from: classes5.dex */
public final class zzxw {
    public static final zzxw zza = null;
    public static final zzxw zzb = null;
    public static final zzxw zzc = null;
    public static final zzxw zzd = null;
    private final String zze;

    static {
        zza = new zzxw("TINK");
        zzb = new zzxw("CRUNCHY");
        zzc = new zzxw("LEGACY");
        zzd = new zzxw("NO_PREFIX");
    }

    private zzxw(String r1) {
        this.zze = r1;
    }

    public final String toString() {
        return this.zze;
    }
}
