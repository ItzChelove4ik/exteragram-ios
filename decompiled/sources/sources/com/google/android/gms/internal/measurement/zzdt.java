package com.google.android.gms.internal.measurement;

import com.google.android.gms.common.internal.Preconditions;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zzdt extends zzeo {
    final /* synthetic */ Runnable zza;
    final /* synthetic */ zzez zzb;

    public zzdt(zzez r1, Runnable r2) {
        this.zza = r2;
        Objects.requireNonNull(r1);
        this.zzb = r1;
        super(r1, true);
    }

    @Override // com.google.android.gms.internal.measurement.zzeo
    public final void zza() {
        ((zzcp) Preconditions.checkNotNull(this.zzb.zzS())).retrieveAndUploadBatches(new zzds(this, this.zza));
    }
}
