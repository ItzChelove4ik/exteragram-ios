package com.google.android.gms.internal.mlkit_language_id_common;

/* loaded from: classes4.dex */
public enum zzhw extends Enum implements zzak {
    public static final zzhw zza = null;
    public static final zzhw zzb = null;
    public static final zzhw zzc = null;
    public static final zzhw zzd = null;
    private static final /* synthetic */ zzhw[] zze = null;
    private final int zzf;

    static {
        zzhw r0 = new zzhw("TYPE_UNKNOWN", 0, 0);
        zza = r0;
        zzhw r1 = new zzhw("TYPE_THIN", 1, 1);
        zzb = r1;
        zzhw r2 = new zzhw("TYPE_THICK", 2, 2);
        zzc = r2;
        zzhw r3 = new zzhw("TYPE_GMV", 3, 3);
        zzd = r3;
        zze = new zzhw[]{r0, r1, r2, r3};
    }

    zzhw(String r1, int r2, int r3) {
        this.zzf = r3;
    }

    public static zzhw[] values() {
        return (zzhw[]) zze.clone();
    }

    @Override // com.google.android.gms.internal.mlkit_language_id_common.zzak
    public final int zza() {
        return this.zzf;
    }
}
