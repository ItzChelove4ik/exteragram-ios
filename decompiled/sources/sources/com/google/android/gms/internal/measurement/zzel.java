package com.google.android.gms.internal.measurement;

import com.google.android.gms.common.internal.Preconditions;
import java.util.Objects;

/* loaded from: classes.dex */
final class zzel extends zzeo {
    final /* synthetic */ zzeq zza;
    final /* synthetic */ zzez zzb;

    public zzel(zzez r1, zzeq r2) {
        this.zza = r2;
        Objects.requireNonNull(r1);
        this.zzb = r1;
        super(r1, true);
    }

    @Override // com.google.android.gms.internal.measurement.zzeo
    public final void zza() {
        ((zzcp) Preconditions.checkNotNull(this.zzb.zzS())).registerOnMeasurementEventListener(this.zza);
    }
}
