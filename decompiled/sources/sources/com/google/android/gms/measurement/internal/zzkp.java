package com.google.android.gms.measurement.internal;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes5.dex */
final class zzkp implements Runnable {
    final /* synthetic */ AtomicReference zza;
    final /* synthetic */ zzlj zzb;

    public zzkp(zzlj r1, AtomicReference r2) {
        this.zza = r2;
        Objects.requireNonNull(r1);
        this.zzb = r1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        AtomicReference r0 = this.zza;
        monitor-enter(r0);
        zzlj r1 = this.zzb;     // Catch: Throwable -> L10
        r0.set(Long.valueOf(r1.zzu.zzc().zzl(r1.zzu.zzv().zzj(), zzfy.zzac)));     // Catch: Throwable -> L10
        this.zza.notify();     // Catch: Throwable -> L8
        monitor-exit(r0);     // Catch: Throwable -> L8
        return;
    L8:
        th = move-exception;
        throw th;
    L10:
        th = move-exception;
        this.zza.notify();     // Catch: Throwable -> L8
        throw th;     // Catch: Throwable -> L8
    }
}
