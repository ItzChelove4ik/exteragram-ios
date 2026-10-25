package com.google.android.gms.tasks;

import java.util.Objects;

/* loaded from: classes.dex */
final class zzm implements Runnable {
    final /* synthetic */ Task zza;
    final /* synthetic */ zzn zzb;

    public zzm(zzn r1, Task r2) {
        this.zza = r2;
        Objects.requireNonNull(r1);
        this.zzb = r1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzn r0 = this.zzb;
        Object r1 = r0.zzc();
        monitor-enter(r1);
    L7:
        th = move-exception;
        throw th;
    L5:
        if (r0.zzd() == null) goto L9;
        r0.zzd().onSuccess(this.zza.getResult());     // Catch: Throwable -> L7
    L9:
        monitor-exit(r1);     // Catch: Throwable -> L7
    }
}
