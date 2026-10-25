package com.google.android.gms.internal.mlkit_language_id_common;

import kotlin.jvm.internal.LongCompanionObject;

/* loaded from: classes4.dex */
public final class zzhm {
    private Long zza;
    private zzhx zzb;
    private Boolean zzc;

    public zzhm() {
    }

    public static /* bridge */ /* synthetic */ zzhx zze(zzhm r0) {
        return r0.zzb;
    }

    public static /* bridge */ /* synthetic */ Boolean zzf(zzhm r0) {
        return r0.zzc;
    }

    public static /* bridge */ /* synthetic */ Long zzg(zzhm r0) {
        return r0.zza;
    }

    public final zzhm zza(Long r5) {
        this.zza = Long.valueOf(r5.longValue() & LongCompanionObject.MAX_VALUE);
        return this;
    }

    public final zzhm zzb(zzhx r1) {
        this.zzb = r1;
        return this;
    }

    public final zzhm zzc(Boolean r1) {
        this.zzc = r1;
        return this;
    }

    public final zzho zzd() {
        return new zzho(this, null);
    }
}
