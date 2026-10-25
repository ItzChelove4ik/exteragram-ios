package com.google.android.gms.measurement.internal;

import java.util.Objects;
import java.util.concurrent.Callable;

/* loaded from: classes5.dex */
final class zzid implements Callable {
    final /* synthetic */ String zza;
    final /* synthetic */ zzjd zzb;

    public zzid(zzjd r1, String r2) {
        this.zza = r2;
        Objects.requireNonNull(r1);
        this.zzb = r1;
    }

    @Override // java.util.concurrent.Callable
    public final /* bridge */ /* synthetic */ Object call() {
        zzjd r0 = this.zzb;
        r0.zzL().zzaa();
        return r0.zzL().zzj().zzn(this.zza);
    }
}
