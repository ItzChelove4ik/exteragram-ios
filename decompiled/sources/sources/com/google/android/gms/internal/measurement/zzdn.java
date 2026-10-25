package com.google.android.gms.internal.measurement;

import com.google.android.gms.common.internal.Preconditions;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zzdn extends zzeo {
    final /* synthetic */ zzez zza;

    public zzdn(zzez r2) {
        Objects.requireNonNull(r2);
        this.zza = r2;
        super(r2, true);
    }

    @Override // com.google.android.gms.internal.measurement.zzeo
    public final void zza() {
        zzez r0 = this.zza;
        if (r0.zzP() == false) goto L6;
        ((zzcp) Preconditions.checkNotNull(r0.zzS())).resetAnalyticsDataWithElapsedTime(this.zzi, this.zzj);
        return;
    L6:
        ((zzcp) Preconditions.checkNotNull(r0.zzS())).resetAnalyticsData(this.zzi);
    }
}
