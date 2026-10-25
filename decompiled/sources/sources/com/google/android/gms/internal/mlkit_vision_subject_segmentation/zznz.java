package com.google.android.gms.internal.mlkit_vision_subject_segmentation;

/* loaded from: classes4.dex */
public enum zznz extends Enum implements zzcb {
    public static final zznz zza = null;
    public static final zznz zzb = null;
    public static final zznz zzc = null;
    public static final zznz zzd = null;
    private static final /* synthetic */ zznz[] zze = null;
    private final int zzf;

    static {
        zznz r0 = new zznz("TYPE_UNKNOWN", 0, 0);
        zza = r0;
        zznz r1 = new zznz("TYPE_THIN", 1, 1);
        zzb = r1;
        zznz r2 = new zznz("TYPE_THICK", 2, 2);
        zzc = r2;
        zznz r3 = new zznz("TYPE_GMV", 3, 3);
        zzd = r3;
        zze = new zznz[]{r0, r1, r2, r3};
    }

    zznz(String r1, int r2, int r3) {
        this.zzf = r3;
    }

    public static zznz[] values() {
        return (zznz[]) zze.clone();
    }

    @Override // com.google.android.gms.internal.mlkit_vision_subject_segmentation.zzcb
    public final int zza() {
        return this.zzf;
    }
}
