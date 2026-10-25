package com.google.android.gms.internal.mlkit_vision_subject_segmentation;

/* loaded from: classes4.dex */
public final class zzdt {
    private zzdw zza;
    private Integer zzb;
    private zznc zzc;

    public zzdt() {
    }

    public static /* bridge */ /* synthetic */ zzdw zzd(zzdt r0) {
        return r0.zza;
    }

    public static /* bridge */ /* synthetic */ zznc zzf(zzdt r0) {
        return r0.zzc;
    }

    public static /* bridge */ /* synthetic */ Integer zzg(zzdt r0) {
        return r0.zzb;
    }

    public final zzdt zza(Integer r2) {
        this.zzb = Integer.valueOf(r2.intValue() & Integer.MAX_VALUE);
        return this;
    }

    public final zzdt zzb(zznc r1) {
        this.zzc = r1;
        return this;
    }

    public final zzdt zzc(zzdw r1) {
        this.zza = r1;
        return this;
    }

    public final zzdy zze() {
        return new zzdy(this, null);
    }
}
