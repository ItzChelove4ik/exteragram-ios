package com.google.android.gms.internal.mlkit_vision_label;

/* loaded from: classes4.dex */
public final class zzjq {
    private zzjr zza;
    private Integer zzb;

    public zzjq() {
    }

    public static /* bridge */ /* synthetic */ zzjr zzc(zzjq r0) {
        return r0.zza;
    }

    public static /* bridge */ /* synthetic */ Integer zze(zzjq r0) {
        return r0.zzb;
    }

    public final zzjq zza(zzjr r1) {
        this.zza = r1;
        return this;
    }

    public final zzjq zzb(Integer r2) {
        this.zzb = Integer.valueOf(r2.intValue() & Integer.MAX_VALUE);
        return this;
    }

    public final zzjt zzd() {
        return new zzjt(this, null);
    }
}
