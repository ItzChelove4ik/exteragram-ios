package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zzdg extends zzeo {
    final /* synthetic */ Bundle zza;
    final /* synthetic */ zzez zzb;

    public zzdg(zzez r1, Bundle r2) {
        this.zza = r2;
        Objects.requireNonNull(r1);
        this.zzb = r1;
        super(r1, true);
    }

    @Override // com.google.android.gms.internal.measurement.zzeo
    public final void zza() {
        ((zzcp) Preconditions.checkNotNull(this.zzb.zzS())).setConditionalUserProperty(this.zza, this.zzi);
    }
}
