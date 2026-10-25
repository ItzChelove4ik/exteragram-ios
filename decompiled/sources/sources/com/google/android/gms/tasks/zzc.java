package com.google.android.gms.tasks;

import java.util.Objects;

/* loaded from: classes.dex */
final class zzc implements Runnable {
    final /* synthetic */ Task zza;
    final /* synthetic */ zzd zzb;

    public zzc(zzd r1, Task r2) {
        this.zza = r2;
        Objects.requireNonNull(r1);
        this.zzb = r1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Task r0 = this.zza;
        boolean r1 = r0.isCanceled();
        zzd r2 = this.zzb;
        if (r1 == false) goto L21;
        r2.zzd().zze();
        return;
    L21:
        Object r02 = r2.zzc().then(r0);     // Catch: Exception -> L9 RuntimeExecutionException -> L11
        this.zzb.zzd().zza(r02);
        return;
    L11:
        e = move-exception;
        boolean r12 = e.getCause() instanceof Exception;
        zzd r3 = this.zzb;
        if (r12 == false) goto L19;
        Exception r03 = (Exception) e.getCause();
        r3.zzd().zzc(r03);
        return;
    L19:
        r3.zzd().zzc(e);
        return;
    L9:
        e = move-exception;
        this.zzb.zzd().zzc(e);
    }
}
