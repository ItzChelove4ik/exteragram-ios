package com.google.android.gms.internal.measurement;

/* loaded from: classes4.dex */
public enum zzzb extends Enum {
    public static final zzzb zza = null;
    public static final zzzb zzb = null;
    public static final zzzb zzc = null;
    public static final zzzb zzd = null;
    public static final zzzb zze = null;
    private static final /* synthetic */ zzzb[] zzg = null;
    private final boolean zzf;

    static {
        zzzb r0 = new zzzb("GENERAL", 0, false, true);
        zza = r0;
        zzzb r1 = new zzzb("BOOLEAN", 1, false, false);
        zzb = r1;
        zzzb r4 = new zzzb("CHARACTER", 2, false, false);
        zzc = r4;
        zzzb r5 = new zzzb("INTEGRAL", 3, true, false);
        zzd = r5;
        zzzb r2 = new zzzb("FLOAT", 4, true, true);
        zze = r2;
        zzg = new zzzb[]{r0, r1, r4, r5, r2};
    }

    zzzb(String r1, int r2, boolean r3, boolean r4) {
        this.zzf = r4;
    }

    public static zzzb[] values() {
        return (zzzb[]) zzg.clone();
    }

    public final boolean zza() {
        return this.zzf;
    }
}
