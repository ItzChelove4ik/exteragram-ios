package com.google.android.gms.tasks;

/* loaded from: classes.dex */
public class TaskCompletionSource<TResult> {
    private final zzw zza;

    public TaskCompletionSource() {
        this.zza = new zzw();
    }

    public TaskCompletionSource(CancellationToken r2) {
        this.zza = new zzw();
        r2.onCanceledRequested(new zzs(this));
    }

    public Task<TResult> getTask() {
        return this.zza;
    }

    public void setException(Exception r1) {
        this.zza.zzc(r1);
    }

    public void setResult(TResult r1) {
        this.zza.zza(r1);
    }

    public boolean trySetException(Exception r1) {
        return this.zza.zzd(r1);
    }

    public boolean trySetResult(TResult r1) {
        return this.zza.zzb(r1);
    }

    public final /* synthetic */ zzw zza() {
        return this.zza;
    }
}
