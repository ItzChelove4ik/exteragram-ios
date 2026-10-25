package com.google.android.gms.measurement.internal;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes5.dex */
final class zzmf extends zzgg {
    final /* synthetic */ AtomicReference zza;
    final /* synthetic */ zznl zzb;

    public zzmf(zznl r1, AtomicReference r2) {
        this.zza = r2;
        Objects.requireNonNull(r1);
        this.zzb = r1;
    }

    @Override // com.google.android.gms.measurement.internal.zzgh
    public final void zze(zzoq r4) {
        AtomicReference r0 = this.zza;
        monitor-enter(r0);
        this.zzb.zzu.zzaW().zzk().zzb("[sgtm] Got upload batches from service. count", Integer.valueOf(r4.zza.size()));     // Catch: Throwable -> L7
        r0.set(r4);     // Catch: Throwable -> L7
        r0.notifyAll();     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return;
    L7:
        th = move-exception;
        throw th;
    }
}
