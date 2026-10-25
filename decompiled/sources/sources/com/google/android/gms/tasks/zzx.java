package com.google.android.gms.tasks;

import java.util.concurrent.Callable;

/* loaded from: classes.dex */
final class zzx implements Runnable {
    final /* synthetic */ zzw zza;
    final /* synthetic */ Callable zzb;

    public zzx(zzw r1, Callable r2) {
        this.zza = r1;
        this.zzb = r2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zza.zza(this.zzb.call());     // Catch: Throwable -> L4 Exception -> L6
        return;
    L6:
        e = move-exception;
        this.zza.zzc(e);
        return;
    L4:
        th = move-exception;
        this.zza.zzc(new RuntimeException(th));
    }
}
