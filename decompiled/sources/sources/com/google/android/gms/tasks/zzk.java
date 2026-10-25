package com.google.android.gms.tasks;

import com.google.android.gms.common.internal.Preconditions;
import java.util.Objects;

/* loaded from: classes5.dex */
final class zzk implements Runnable {
    final /* synthetic */ Task zza;
    final /* synthetic */ zzl zzb;

    public zzk(zzl r1, Task r2) {
        this.zza = r2;
        Objects.requireNonNull(r1);
        this.zzb = r1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzl r0 = this.zzb;
        Object r1 = r0.zzc();
        monitor-enter(r1);
    L7:
        th = move-exception;
        throw th;
    L5:
        if (r0.zzd() == null) goto L9;
        r0.zzd().onFailure((Exception) Preconditions.checkNotNull(this.zza.getException()));     // Catch: Throwable -> L7
    L9:
        monitor-exit(r1);     // Catch: Throwable -> L7
    }
}
