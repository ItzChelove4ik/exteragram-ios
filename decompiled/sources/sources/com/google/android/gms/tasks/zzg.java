package com.google.android.gms.tasks;

import java.util.Objects;

/* loaded from: classes5.dex */
final class zzg implements Runnable {
    final /* synthetic */ zzh zza;

    public zzg(zzh r1) {
        Objects.requireNonNull(r1);
        this.zza = r1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzh r2 = this.zza;
        Object r0 = r2.zzc();
        monitor-enter(r0);
    L7:
        th = move-exception;
        throw th;
    L5:
        if (r2.zzd() == null) goto L9;
        r2.zzd().onCanceled();     // Catch: Throwable -> L7
    L9:
        monitor-exit(r0);     // Catch: Throwable -> L7
    }
}
