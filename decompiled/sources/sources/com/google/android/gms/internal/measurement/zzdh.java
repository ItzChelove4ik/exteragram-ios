package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zzdh extends zzeo {
    final /* synthetic */ String zza;
    final /* synthetic */ String zzb;
    final /* synthetic */ Bundle zzc;
    final /* synthetic */ zzez zzd;

    public zzdh(zzez r1, String r2, String r3, Bundle r4) {
        this.zza = r2;
        this.zzb = r3;
        this.zzc = r4;
        Objects.requireNonNull(r1);
        this.zzd = r1;
        super(r1, true);
    }

    @Override // com.google.android.gms.internal.measurement.zzeo
    public final void zza() {
        ((zzcp) Preconditions.checkNotNull(this.zzd.zzS())).clearConditionalUserProperty(this.zza, this.zzb, this.zzc);
    }
}
