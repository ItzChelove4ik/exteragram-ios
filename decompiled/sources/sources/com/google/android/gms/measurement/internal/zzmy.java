package com.google.android.gms.measurement.internal;

import java.util.Objects;

/* loaded from: classes5.dex */
final class zzmy implements Runnable {
    final /* synthetic */ zzgb zza;
    final /* synthetic */ zznf zzb;

    public zzmy(zznf r1, zzgb r2) {
        this.zza = r2;
        Objects.requireNonNull(r1);
        this.zzb = r1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zznf r0 = this.zzb;
        monitor-enter(r0);
        r0.zzd(false);     // Catch: Throwable -> L8
        zznl r1 = r0.zza;     // Catch: Throwable -> L8
        if (r1.zzh() == true) goto L10;
        r1.zzu.zzaW().zzk().zza("Connected to service");     // Catch: Throwable -> L8
        r1.zzL(this.zza);     // Catch: Throwable -> L8
    L10:
        monitor-exit(r0);     // Catch: Throwable -> L8
        return;
    L8:
        th = move-exception;
        throw th;
    }
}
