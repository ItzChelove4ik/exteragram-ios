package com.google.android.gms.tasks;

import java.util.concurrent.Executor;

/* loaded from: classes.dex */
final class zzl implements zzq {
    private final Executor zza;
    private final Object zzb;
    private OnFailureListener zzc;

    public zzl(Executor r2, OnFailureListener r3) {
        this.zzb = new Object();
        this.zza = r2;
        this.zzc = r3;
    }

    @Override // com.google.android.gms.tasks.zzq
    public final void zza(Task r3) {
        if (r3.isSuccessful() == false) goto L5;
        return;
    L5:
        if (r3.isCanceled() == true) goto L21;
        Object r0 = this.zzb;
        monitor-enter(r0);
    L12:
        th = move-exception;
        throw th;
    L9:
        if (this.zzc != null) goto L14;
        monitor-exit(r0);     // Catch: Throwable -> L12
        return;
    L14:
        monitor-exit(r0);     // Catch: Throwable -> L12
        this.zza.execute(new zzk(this, r3));
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

    public final /* synthetic */ OnFailureListener zzd() {
        return this.zzc;
    }
}
