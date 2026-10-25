package com.google.android.gms.measurement.internal;

import java.util.Objects;
import java.util.concurrent.Callable;

/* loaded from: classes5.dex */
final class zzim implements Callable {
    final /* synthetic */ String zza;
    final /* synthetic */ String zzb;
    final /* synthetic */ String zzc;
    final /* synthetic */ zzjd zzd;

    public zzim(zzjd r1, String r2, String r3, String r4) {
        this.zza = r2;
        this.zzb = r3;
        this.zzc = r4;
        Objects.requireNonNull(r1);
        this.zzd = r1;
    }

    @Override // java.util.concurrent.Callable
    public final /* bridge */ /* synthetic */ Object call() {
        zzjd r0 = this.zzd;
        r0.zzL().zzaa();
        return r0.zzL().zzj().zzs(this.zza, this.zzb, this.zzc);
    }
}
