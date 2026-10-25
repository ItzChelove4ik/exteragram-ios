package com.google.android.gms.measurement.internal;

import android.util.Log;
import java.util.Objects;

/* loaded from: classes5.dex */
final class zzjr implements zzgm {
    final /* synthetic */ zzic zza;

    public zzjr(zzjs r1, zzic r2) {
        this.zza = r2;
        Objects.requireNonNull(r1);
    }

    @Override // com.google.android.gms.measurement.internal.zzgm
    public final boolean zza() {
        return Log.isLoggable(this.zza.zzaW().zzn(), 3);
    }
}
