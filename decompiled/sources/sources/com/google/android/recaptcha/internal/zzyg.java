package com.google.android.recaptcha.internal;

/* loaded from: classes5.dex */
public final class zzyg {
    public static final zzyg zza = null;
    public static final zzyg zzb = null;
    public static final zzyg zzc = null;
    public static final zzyg zzd = null;
    private final String zze;

    static {
        zza = new zzyg("TINK");
        zzb = new zzyg("CRUNCHY");
        zzc = new zzyg("LEGACY");
        zzd = new zzyg("NO_PREFIX");
    }

    private zzyg(String r1) {
        this.zze = r1;
    }

    public final String toString() {
        return this.zze;
    }
}
