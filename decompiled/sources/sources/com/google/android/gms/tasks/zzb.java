package com.google.android.gms.tasks;

/* loaded from: classes5.dex */
final class zzb extends CancellationToken {
    private final zzw zza;

    public zzb() {
        this.zza = new zzw();
    }

    @Override // com.google.android.gms.tasks.CancellationToken
    public final boolean isCancellationRequested() {
        return this.zza.isComplete();
    }

    @Override // com.google.android.gms.tasks.CancellationToken
    public final CancellationToken onCanceledRequested(OnTokenCanceledListener r3) {
        zza r0 = new zza(this, r3);
        this.zza.addOnSuccessListener(TaskExecutors.MAIN_THREAD, r0);
        return this;
    }

    public final void zza() {
        this.zza.zzb(null);
    }
}
