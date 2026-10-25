package com.google.android.gms.measurement.internal;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes5.dex */
final class zzme extends zzgd {
    final /* synthetic */ AtomicReference zza;

    public zzme(zznl r1, AtomicReference r2) {
        this.zza = r2;
        Objects.requireNonNull(r1);
    }

    @Override // com.google.android.gms.measurement.internal.zzge
    public final void zze(List r1) {
        AtomicReference r0 = this.zza;
        monitor-enter(r0);
        r0.set(r1);     // Catch: Throwable -> L7
        r0.notifyAll();     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return;
    L7:
        th = move-exception;
        throw th;
    }
}
