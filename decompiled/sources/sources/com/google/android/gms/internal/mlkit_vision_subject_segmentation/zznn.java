package com.google.android.gms.internal.mlkit_vision_subject_segmentation;

import kotlin.jvm.internal.LongCompanionObject;

/* loaded from: classes4.dex */
public final class zznn {
    private Long zza;
    private zzoa zzb;
    private Boolean zzc;

    public zznn() {
    }

    public static /* bridge */ /* synthetic */ zzoa zze(zznn r0) {
        return r0.zzb;
    }

    public static /* bridge */ /* synthetic */ Boolean zzf(zznn r0) {
        return r0.zzc;
    }

    public static /* bridge */ /* synthetic */ Long zzg(zznn r0) {
        return r0.zza;
    }

    public final zznn zza(Long r5) {
        this.zza = Long.valueOf(r5.longValue() & LongCompanionObject.MAX_VALUE);
        return this;
    }

    public final zznn zzb(zzoa r1) {
        this.zzb = r1;
        return this;
    }

    public final zznn zzc(Boolean r1) {
        this.zzc = r1;
        return this;
    }

    public final zznp zzd() {
        return new zznp(this, null);
    }
}
