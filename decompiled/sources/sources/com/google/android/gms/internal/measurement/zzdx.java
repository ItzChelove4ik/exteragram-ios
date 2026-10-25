package com.google.android.gms.internal.measurement;

import com.google.android.gms.common.internal.Preconditions;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zzdx extends zzeo {
    final /* synthetic */ zzcm zza;
    final /* synthetic */ zzez zzb;

    public zzdx(zzez r1, zzcm r2) {
        this.zza = r2;
        Objects.requireNonNull(r1);
        this.zzb = r1;
        super(r1, true);
    }

    @Override // com.google.android.gms.internal.measurement.zzeo
    public final void zza() {
        ((zzcp) Preconditions.checkNotNull(this.zzb.zzS())).getCurrentScreenName(this.zza);
    }

    @Override // com.google.android.gms.internal.measurement.zzeo
    public final void zzb() {
        this.zza.zzb(null);
    }
}
