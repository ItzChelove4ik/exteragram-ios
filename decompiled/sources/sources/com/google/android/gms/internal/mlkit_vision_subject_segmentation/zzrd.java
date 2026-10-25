package com.google.android.gms.internal.mlkit_vision_subject_segmentation;

import kotlin.jvm.internal.LongCompanionObject;

/* loaded from: classes4.dex */
public final class zzrd {
    private zzoa zza;
    private Long zzb;
    private zzrz zzc;

    public zzrd() {
    }

    public static /* bridge */ /* synthetic */ zzoa zza(zzrd r0) {
        return r0.zza;
    }

    public static /* bridge */ /* synthetic */ zzrz zzf(zzrd r0) {
        return r0.zzc;
    }

    public static /* bridge */ /* synthetic */ Long zzg(zzrd r0) {
        return r0.zzb;
    }

    public final zzrd zzb(Long r5) {
        this.zzb = Long.valueOf(r5.longValue() & LongCompanionObject.MAX_VALUE);
        return this;
    }

    public final zzrd zzc(zzoa r1) {
        this.zza = r1;
        return this;
    }

    public final zzrd zzd(zzrz r1) {
        this.zzc = r1;
        return this;
    }

    public final zzrf zze() {
        return new zzrf(this, null);
    }
}
