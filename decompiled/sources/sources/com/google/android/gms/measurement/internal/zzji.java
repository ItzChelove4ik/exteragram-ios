package com.google.android.gms.measurement.internal;

/* loaded from: classes5.dex */
public enum zzji extends Enum {
    public static final zzji zza = null;
    public static final zzji zzb = null;
    public static final zzji zzc = null;
    public static final zzji zzd = null;
    private static final /* synthetic */ zzji[] zzf = null;
    private final String zze;

    static {
        zzji r0 = new zzji("UNINITIALIZED", 0, "uninitialized");
        zza = r0;
        zzji r1 = new zzji("POLICY", 1, "eu_consent_policy");
        zzb = r1;
        zzji r2 = new zzji("DENIED", 2, "denied");
        zzc = r2;
        zzji r3 = new zzji("GRANTED", 3, "granted");
        zzd = r3;
        zzf = new zzji[]{r0, r1, r2, r3};
    }

    zzji(String r1, int r2, String r3) {
        this.zze = r3;
    }

    public static zzji[] values() {
        return (zzji[]) zzf.clone();
    }

    @Override // java.lang.Enum
    public final String toString() {
        return this.zze;
    }
}
