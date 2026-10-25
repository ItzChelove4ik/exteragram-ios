package com.google.android.gms.measurement.internal;

/* loaded from: classes5.dex */
enum zzoe extends Enum {
    public static final zzoe zza = null;
    public static final zzoe zzb = null;
    public static final zzoe zzc = null;
    public static final zzoe zzd = null;
    private static final /* synthetic */ zzoe[] zze = null;

    static {
        zzoe r0 = new zzoe("CONSENT", 0);
        zza = r0;
        zzoe r1 = new zzoe("LEGITIMATE_INTEREST", 1);
        zzb = r1;
        zzoe r2 = new zzoe("FLEXIBLE_CONSENT", 2);
        zzc = r2;
        zzoe r3 = new zzoe("FLEXIBLE_LEGITIMATE_INTEREST", 3);
        zzd = r3;
        zze = new zzoe[]{r0, r1, r2, r3};
    }

    zzoe(String r1, int r2) {
    }

    public static zzoe[] values() {
        return (zzoe[]) zze.clone();
    }
}
