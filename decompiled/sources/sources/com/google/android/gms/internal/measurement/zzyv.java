package com.google.android.gms.internal.measurement;

/* loaded from: classes4.dex */
public enum zzyv extends Enum {
    public static final zzyv zza = null;
    public static final zzyv zzb = null;
    public static final zzyv zzc = null;
    public static final zzyv zzd = null;
    public static final zzyv zze = null;
    private static final /* synthetic */ zzyv[] zzg = null;
    private final int zzf;

    static {
        zzyv r0 = new zzyv("SMALL", 0, 10);
        zza = r0;
        zzyv r1 = new zzyv("MEDIUM", 1, 20);
        zzb = r1;
        zzyv r2 = new zzyv("LARGE", 2, 50);
        zzc = r2;
        zzyv r4 = new zzyv("FULL", 3, -1);
        zzd = r4;
        zzyv r5 = new zzyv("NONE", 4, 0);
        zze = r5;
        zzg = new zzyv[]{r0, r1, r2, r4, r5};
    }

    zzyv(String r1, int r2, int r3) {
        this.zzf = r3;
    }

    public static zzyv[] values() {
        return (zzyv[]) zzg.clone();
    }

    public final int zza() {
        return this.zzf;
    }
}
