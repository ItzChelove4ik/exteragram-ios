package com.google.android.gms.internal.measurement;

import com.google.android.gms.common.internal.Preconditions;
import java.util.Objects;

/* loaded from: classes.dex */
final class zzdl extends zzeo {
    final /* synthetic */ Boolean zza;
    final /* synthetic */ zzez zzb;

    public zzdl(zzez r1, Boolean r2) {
        this.zza = r2;
        Objects.requireNonNull(r1);
        this.zzb = r1;
        super(r1, true);
    }

    @Override // com.google.android.gms.internal.measurement.zzeo
    public final void zza() {
        ((zzcp) Preconditions.checkNotNull(this.zzb.zzS())).setMeasurementEnabled(this.zza.booleanValue(), this.zzi);
    }
}
