package com.google.android.gms.internal.mlkit_vision_label;

/* loaded from: classes4.dex */
public enum zzkd extends Enum implements zzck {
    public static final zzkd zza = null;
    public static final zzkd zzb = null;
    public static final zzkd zzc = null;
    public static final zzkd zzd = null;
    private static final /* synthetic */ zzkd[] zze = null;
    private final int zzf;

    static {
        zzkd r0 = new zzkd("TYPE_UNKNOWN", 0, 0);
        zza = r0;
        zzkd r1 = new zzkd("TYPE_THIN", 1, 1);
        zzb = r1;
        zzkd r2 = new zzkd("TYPE_THICK", 2, 2);
        zzc = r2;
        zzkd r3 = new zzkd("TYPE_GMV", 3, 3);
        zzd = r3;
        zze = new zzkd[]{r0, r1, r2, r3};
    }

    zzkd(String r1, int r2, int r3) {
        this.zzf = r3;
    }

    public static zzkd[] values() {
        return (zzkd[]) zze.clone();
    }

    @Override // com.google.android.gms.internal.mlkit_vision_label.zzck
    public final int zza() {
        return this.zzf;
    }
}
