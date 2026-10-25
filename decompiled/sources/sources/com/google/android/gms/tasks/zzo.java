package com.google.android.gms.tasks;

import java.util.Objects;
import java.util.concurrent.CancellationException;
import java.util.concurrent.Executor;

/* loaded from: classes5.dex */
final class zzo implements Runnable {
    final /* synthetic */ Task zza;
    final /* synthetic */ zzp zzb;

    public zzo(zzp r1, Task r2) {
        this.zza = r2;
        Objects.requireNonNull(r1);
        this.zzb = r1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Task r0 = this.zzb.zzc().then(this.zza.getResult());     // Catch: Exception -> L9 RuntimeExecutionException -> L11 CancellationException -> L15
        zzp r2 = this.zzb;
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
        zzp r22 = this.zzb;
        if (r12 == false) goto L21;
        r22.onFailure((Exception) e.getCause());
        return;
    L21:
        r22.onFailure(e);
        return;
    L15:
        this.zzb.onCanceled();
        return;
    L9:
        e = move-exception;
        this.zzb.onFailure(e);
    }
}
