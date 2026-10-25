package com.google.mlkit.common.sdkinternal;

import com.google.android.gms.tasks.CancellationToken;
import com.google.android.gms.tasks.CancellationTokenSource;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.concurrent.Executor;

/* loaded from: classes5.dex */
public final /* synthetic */ class zzm implements Executor {
    public final /* synthetic */ Executor zza;
    public final /* synthetic */ CancellationToken zzb;
    public final /* synthetic */ CancellationTokenSource zzc;
    public final /* synthetic */ TaskCompletionSource zzd;

    public /* synthetic */ zzm(Executor r1, CancellationToken r2, CancellationTokenSource r3, TaskCompletionSource r4) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
        this.zzd = r4;
    }

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable r2) {
        this.zza.execute(r2);     // Catch: RuntimeException -> L5
        return;
    L5:
        e = move-exception;
        if (this.zzb.isCancellationRequested() == false) goto L9;
        this.zzc.cancel();
    L10:
        throw e;
    L9:
        this.zzd.setException(e);
        goto L10
    }
}
