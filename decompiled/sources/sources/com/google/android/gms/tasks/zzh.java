package com.google.android.gms.tasks;

import java.util.concurrent.Executor;

/* loaded from: classes.dex */
final class zzh implements zzq {
    private final Executor zza;
    private final Object zzb;
    private OnCanceledListener zzc;

    public zzh(Executor r2, OnCanceledListener r3) {
        this.zzb = new Object();
        this.zza = r2;
        this.zzc = r3;
    }

    @Override // com.google.android.gms.tasks.zzq
    public final void zza(Task r2) {
        if (r2.isCanceled() == false) goto L17;
        Object r22 = this.zzb;
        monitor-enter(r22);
    L10:
        th = move-exception;
        throw th;
    L7:
        if (this.zzc != null) goto L12;
        monitor-exit(r22);     // Catch: Throwable -> L10
        return;
    L12:
        monitor-exit(r22);     // Catch: Throwable -> L10
        this.zza.execute(new zzg(this));
        return;
    }

    @Override // com.google.android.gms.tasks.zzq
    public final void zzb() {
        Object r0 = this.zzb;
        monitor-enter(r0);
        this.zzc = null;     // Catch: Throwable -> L8
        monitor-exit(r0);     // Catch: Throwable -> L8
        return;
    L8:
        th = move-exception;
        throw th;
    }

    public final /* synthetic */ Object zzc() {
        return this.zzb;
    }

    public final /* synthetic */ OnCanceledListener zzd() {
        return this.zzc;
    }
}
