package com.google.android.gms.internal.mlkit_vision_label;

/* loaded from: classes4.dex */
public final class zzde {
    private zzdh zza;
    private Integer zzb;
    private zzjn zzc;

    public zzde() {
    }

    public static /* bridge */ /* synthetic */ zzdh zzd(zzde r0) {
        return r0.zza;
    }

    public static /* bridge */ /* synthetic */ zzjn zzf(zzde r0) {
        return r0.zzc;
    }

    public static /* bridge */ /* synthetic */ Integer zzg(zzde r0) {
        return r0.zzb;
    }

    public final zzde zza(Integer r2) {
        this.zzb = Integer.valueOf(r2.intValue() & Integer.MAX_VALUE);
        return this;
    }

    public final zzde zzb(zzjn r1) {
        this.zzc = r1;
        return this;
    }

    public final zzde zzc(zzdh r1) {
        this.zza = r1;
        return this;
    }

    public final zzdj zze() {
        return new zzdj(this, null);
    }
}
