package com.google.android.gms.internal.measurement;

import android.app.Activity;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Objects;

/* loaded from: classes.dex */
final class zzeu extends zzeo {
    final /* synthetic */ Activity zza;
    final /* synthetic */ zzey zzb;

    public zzeu(zzey r1, Activity r2) {
        this.zza = r2;
        Objects.requireNonNull(r1);
        this.zzb = r1;
        super(r1.zza, true);
    }

    @Override // com.google.android.gms.internal.measurement.zzeo
    public final void zza() {
        ((zzcp) Preconditions.checkNotNull(this.zzb.zza.zzS())).onActivityPausedByScionActivityInfo(zzdd.zza(this.zza), this.zzj);
    }
}
