package com.google.android.recaptcha.internal;

/* loaded from: classes5.dex */
public final class zzsn {
    public static final zzsn zza = null;
    public static final zzsn zzb = null;
    public static final zzsn zzc = null;
    public static final zzsn zzd = null;
    public static final zzsn zze = null;
    private final String zzf;

    static {
        zza = new zzsn("UNKNOWN_KEYMATERIAL");
        zzb = new zzsn("SYMMETRIC");
        zzc = new zzsn("ASYMMETRIC_PRIVATE");
        zzd = new zzsn("ASYMMETRIC_PUBLIC");
        zze = new zzsn("REMOTE");
    }

    private zzsn(String r1) {
        this.zzf = r1;
    }

    public final String toString() {
        return this.zzf;
    }
}
