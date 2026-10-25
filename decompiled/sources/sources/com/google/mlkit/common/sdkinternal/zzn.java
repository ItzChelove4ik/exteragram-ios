package com.google.mlkit.common.sdkinternal;

import com.google.android.gms.tasks.CancellationToken;
import com.google.android.gms.tasks.CancellationTokenSource;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.concurrent.Callable;

/* loaded from: classes5.dex */
public final /* synthetic */ class zzn implements Runnable {
    public final /* synthetic */ ModelResource zza;
    public final /* synthetic */ CancellationToken zzb;
    public final /* synthetic */ CancellationTokenSource zzc;
    public final /* synthetic */ Callable zzd;
    public final /* synthetic */ TaskCompletionSource zze;

    public /* synthetic */ zzn(ModelResource r1, CancellationToken r2, CancellationTokenSource r3, Callable r4, TaskCompletionSource r5) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
        this.zzd = r4;
        this.zze = r5;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zza.zza(this.zzb, this.zzc, this.zzd, this.zze);
    }
}
