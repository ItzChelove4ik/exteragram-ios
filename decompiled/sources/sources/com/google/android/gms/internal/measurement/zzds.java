package com.google.android.gms.internal.measurement;

import java.util.Objects;

/* loaded from: classes4.dex */
final class zzds extends zzcu {
    final /* synthetic */ Runnable zza;

    public zzds(zzdt r1, Runnable r2) {
        this.zza = r2;
        Objects.requireNonNull(r1);
    }

    @Override // com.google.android.gms.internal.measurement.zzcv
    public final void zze() {
        this.zza.run();
    }
}
