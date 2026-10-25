package com.google.mlkit.common.sdkinternal;

import com.google.android.gms.tasks.TaskCompletionSource;

/* loaded from: classes5.dex */
public final /* synthetic */ class zzl implements Runnable {
    public final /* synthetic */ ModelResource zza;
    public final /* synthetic */ TaskCompletionSource zzb;

    public /* synthetic */ zzl(ModelResource r1, TaskCompletionSource r2) {
        this.zza = r1;
        this.zzb = r2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zza.zzb(this.zzb);
    }
}
