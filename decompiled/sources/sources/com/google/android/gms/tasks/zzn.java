package com.google.android.gms.tasks;

import java.util.concurrent.Executor;

/* loaded from: classes.dex */
final class zzn implements zzq {
    private final Executor zza;
    private final Object zzb;
    private OnSuccessListener zzc;

    public zzn(Executor r2, OnSuccessListener r3) {
        this.zzb = new Object();
        this.zza = r2;
        this.zzc = r3;
    }

    @Override // com.google.android.gms.tasks.zzq
    public final void zza(Task r3) {
        if (r3.isSuccessful() == false) goto L17;
        Object r0 = this.zzb;
        monitor-enter(r0);
    L10:
        th = move-exception;
        throw th;
    L7:
        if (this.zzc != null) goto L12;
        monitor-exit(r0);     // Catch: Throwable -> L10
        return;
    L12:
        monitor-exit(r0);     // Catch: Throwable -> L10
        this.zza.execute(new zzm(this, r3));
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

    public final /* synthetic */ OnSuccessListener zzd() {
        return this.zzc;
    }
}
