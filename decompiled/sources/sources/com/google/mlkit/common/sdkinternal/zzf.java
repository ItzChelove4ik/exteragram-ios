package com.google.mlkit.common.sdkinternal;

import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.mlkit.common.MlKitException;
import java.util.concurrent.Callable;

/* loaded from: classes5.dex */
public final /* synthetic */ class zzf implements Runnable {
    public final /* synthetic */ Callable zza;
    public final /* synthetic */ TaskCompletionSource zzb;

    public /* synthetic */ zzf(Callable r1, TaskCompletionSource r2) {
        this.zza = r1;
        this.zzb = r2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Callable r0 = this.zza;
        TaskCompletionSource r4 = this.zzb;
        r4.setResult(r0.call());
        return;
    L9:
        e = move-exception;
        r4.setException(e);
        return;
    L6:
        e = move-exception;
        r4.setException(new MlKitException("Internal error has occurred when executing ML Kit tasks", 13, e));
    }
}
