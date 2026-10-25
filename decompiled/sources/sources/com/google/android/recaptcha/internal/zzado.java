package com.google.android.recaptcha.internal;

/* loaded from: classes5.dex */
public enum zzado extends Enum {
    public static final zzado zza = null;
    public static final zzado zzb = null;
    public static final zzado zzc = null;
    public static final zzado zzd = null;
    public static final zzado zze = null;
    private static final /* synthetic */ zzado[] zzf = null;

    static {
        zzado r0 = new zzado("SHA1", 0);
        zza = r0;
        zzado r1 = new zzado("SHA224", 1);
        zzb = r1;
        zzado r2 = new zzado("SHA256", 2);
        zzc = r2;
        zzado r3 = new zzado("SHA384", 3);
        zzd = r3;
        zzado r4 = new zzado("SHA512", 4);
        zze = r4;
        zzf = new zzado[]{r0, r1, r2, r3, r4};
    }

    zzado(String r1, int r2) {
    }

    public static zzado[] values() {
        return (zzado[]) zzf.clone();
    }
}
