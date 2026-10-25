package com.google.android.gms.tasks;

import java.util.Objects;

/* loaded from: classes.dex */
final class zzi implements Runnable {
    final /* synthetic */ Task zza;
    final /* synthetic */ zzj zzb;

    public zzi(zzj r1, Task r2) {
        this.zza = r2;
        Objects.requireNonNull(r1);
        this.zzb = r1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzj r0 = this.zzb;
        Object r1 = r0.zzc();
        monitor-enter(r1);
    L7:
        th = move-exception;
        throw th;
    L5:
        if (r0.zzd() == null) goto L9;
        r0.zzd().onComplete(this.zza);     // Catch: Throwable -> L7
    L9:
        monitor-exit(r1);     // Catch: Throwable -> L7
    }
}
