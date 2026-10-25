package com.google.android.gms.internal.measurement;

import com.google.android.gms.common.internal.Preconditions;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zzed extends zzeo {
    final /* synthetic */ String zza;
    final /* synthetic */ zzcm zzb;
    final /* synthetic */ zzez zzc;

    public zzed(zzez r1, String r2, zzcm r3) {
        this.zza = r2;
        this.zzb = r3;
        Objects.requireNonNull(r1);
        this.zzc = r1;
        super(r1, true);
    }

    @Override // com.google.android.gms.internal.measurement.zzeo
    public final void zza() {
        ((zzcp) Preconditions.checkNotNull(this.zzc.zzS())).getMaxUserProperties(this.zza, this.zzb);
    }

    @Override // com.google.android.gms.internal.measurement.zzeo
    public final void zzb() {
        this.zzb.zzb(null);
    }
}
