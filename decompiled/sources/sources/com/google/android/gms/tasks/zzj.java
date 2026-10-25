package com.google.android.gms.tasks;

import java.util.concurrent.Executor;

/* loaded from: classes.dex */
final class zzj implements zzq {
    private final Executor zza;
    private final Object zzb;
    private OnCompleteListener zzc;

    public zzj(Executor r2, OnCompleteListener r3) {
        this.zzb = new Object();
        this.zza = r2;
        this.zzc = r3;
    }

    @Override // com.google.android.gms.tasks.zzq
    public final void zza(Task r3) {
        Object r0 = this.zzb;
        monitor-enter(r0);
    L8:
        th = move-exception;
        throw th;
    L5:
        if (this.zzc != null) goto L10;
        monitor-exit(r0);     // Catch: Throwable -> L8
        return;
    L10:
        monitor-exit(r0);     // Catch: Throwable -> L8
        this.zza.execute(new zzi(this, r3));
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

    public final /* synthetic */ OnCompleteListener zzd() {
        return this.zzc;
    }
}
