package com.google.android.gms.internal.cast;

import com.google.android.gms.tasks.TaskCompletionSource;

/* loaded from: classes4.dex */
final /* synthetic */ class zzcl implements Runnable {
    private final /* synthetic */ TaskCompletionSource zza;

    public /* synthetic */ zzcl(TaskCompletionSource r1) {
        this.zza = r1;
    }

    @Override // java.lang.Runnable
    public final /* synthetic */ void run() {
        zzcn.zzc(this.zza);
    }
}
