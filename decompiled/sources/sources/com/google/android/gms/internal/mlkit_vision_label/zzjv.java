package com.google.android.gms.internal.mlkit_vision_label;

import kotlin.jvm.internal.LongCompanionObject;

/* loaded from: classes4.dex */
public final class zzjv {
    private Long zza;
    private zzke zzb;
    private Boolean zzc;
    private Boolean zzd;
    private Boolean zze;

    public zzjv() {
    }

    public static /* bridge */ /* synthetic */ zzke zzg(zzjv r0) {
        return r0.zzb;
    }

    public static /* bridge */ /* synthetic */ Boolean zzh(zzjv r0) {
        return r0.zzd;
    }

    public static /* bridge */ /* synthetic */ Boolean zzi(zzjv r0) {
        return r0.zze;
    }

    public static /* bridge */ /* synthetic */ Boolean zzj(zzjv r0) {
        return r0.zzc;
    }

    public static /* bridge */ /* synthetic */ Long zzk(zzjv r0) {
        return r0.zza;
    }

    public final zzjv zza(Boolean r1) {
        this.zzd = r1;
        return this;
    }

    public final zzjv zzb(Boolean r1) {
        this.zze = r1;
        return this;
    }

    public final zzjv zzc(Long r5) {
        this.zza = Long.valueOf(r5.longValue() & LongCompanionObject.MAX_VALUE);
        return this;
    }

    public final zzjv zzd(zzke r1) {
        this.zzb = r1;
        return this;
    }

    public final zzjv zze(Boolean r1) {
        this.zzc = r1;
        return this;
    }

    public final zzjx zzf() {
        return new zzjx(this, null);
    }
}
