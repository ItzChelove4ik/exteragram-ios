package com.google.android.gms.tasks;

import java.util.Objects;
import java.util.concurrent.Executor;

/* loaded from: classes.dex */
final class zze implements Runnable {
    final /* synthetic */ Task zza;
    final /* synthetic */ zzf zzb;

    public zze(zzf r1, Task r2) {
        this.zza = r2;
        Objects.requireNonNull(r1);
        this.zzb = r1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Task r0 = (Task) this.zzb.zzc().then(this.zza);     // Catch: Exception -> L9 RuntimeExecutionException -> L11
        zzf r2 = this.zzb;
        if (r0 != null) goto L7;
        r2.onFailure(new NullPointerException("Continuation returned null"));
        return;
    L7:
        Executor r1 = TaskExecutors.zza;
        r0.addOnSuccessListener(r1, r2);
        r0.addOnFailureListener(r1, r2);
        r0.addOnCanceledListener(r1, r2);
        return;
    L11:
        e = move-exception;
        boolean r12 = e.getCause() instanceof Exception;
        zzf r22 = this.zzb;
        if (r12 == false) goto L19;
        Exception r02 = (Exception) e.getCause();
        r22.zzd().zzc(r02);
        return;
    L19:
        r22.zzd().zzc(e);
        return;
    L9:
        e = move-exception;
        this.zzb.zzd().zzc(e);
    }
}
