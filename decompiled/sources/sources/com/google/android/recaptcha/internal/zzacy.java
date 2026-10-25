package com.google.android.recaptcha.internal;

/* loaded from: classes5.dex */
public enum zzacy extends Enum {
    public static final zzacy zza = null;
    public static final zzacy zzb = null;
    public static final zzacy zzc = null;
    private static final /* synthetic */ zzacy[] zzd = null;

    static {
        zzacy r0 = new zzacy("NIST_P256", 0);
        zza = r0;
        zzacy r1 = new zzacy("NIST_P384", 1);
        zzb = r1;
        zzacy r2 = new zzacy("NIST_P521", 2);
        zzc = r2;
        zzd = new zzacy[]{r0, r1, r2};
    }

    zzacy(String r1, int r2) {
    }

    public static zzacy[] values() {
        return (zzacy[]) zzd.clone();
    }
}
