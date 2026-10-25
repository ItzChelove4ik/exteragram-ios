package com.google.android.gms.internal.measurement;

import android.app.Activity;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zzew extends zzeo {
    final /* synthetic */ Activity zza;
    final /* synthetic */ zzcm zzb;
    final /* synthetic */ zzey zzc;

    public zzew(zzey r1, Activity r2, zzcm r3) {
        this.zza = r2;
        this.zzb = r3;
        Objects.requireNonNull(r1);
        this.zzc = r1;
        super(r1.zza, true);
    }

    @Override // com.google.android.gms.internal.measurement.zzeo
    public final void zza() {
        ((zzcp) Preconditions.checkNotNull(this.zzc.zza.zzS())).onActivitySaveInstanceStateByScionActivityInfo(zzdd.zza(this.zza), this.zzb, this.zzj);
    }
}
