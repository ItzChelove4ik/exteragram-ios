package com.google.android.gms.internal.play_billing;

/* loaded from: classes5.dex */
final class zzl extends zzd {
    public zzl() {
        super(null);
    }

    @Override // com.google.android.gms.internal.play_billing.zzd
    public final void zza(zzm r1, zzm r2) {
        r1.zzc = r2;
    }

    @Override // com.google.android.gms.internal.play_billing.zzd
    public final void zzb(zzm r1, Thread r2) {
        r1.zzb = r2;
    }

    @Override // com.google.android.gms.internal.play_billing.zzd
    public final boolean zzc(zzo r1, zzh r2, zzh r3) {
        monitor-enter(r1);
    L9:
        th = move-exception;
        throw th;
    L4:
        if (r1.zzd != r2) goto L11;
        r1.zzd = r3;     // Catch: Throwable -> L9
        monitor-exit(r1);     // Catch: Throwable -> L9
        return true;
    L11:
        monitor-exit(r1);     // Catch: Throwable -> L9
        return false;
    }

    @Override // com.google.android.gms.internal.play_billing.zzd
    public final boolean zzd(zzo r1, Object r2, Object r3) {
        monitor-enter(r1);
    L9:
        th = move-exception;
        throw th;
    L4:
        if (r1.zzc != r2) goto L11;
        r1.zzc = r3;     // Catch: Throwable -> L9
        monitor-exit(r1);     // Catch: Throwable -> L9
        return true;
    L11:
        monitor-exit(r1);     // Catch: Throwable -> L9
        return false;
    }

    @Override // com.google.android.gms.internal.play_billing.zzd
    public final boolean zze(zzo r1, zzm r2, zzm r3) {
        monitor-enter(r1);
    L9:
        th = move-exception;
        throw th;
    L4:
        if (r1.zze != r2) goto L11;
        r1.zze = r3;     // Catch: Throwable -> L9
        monitor-exit(r1);     // Catch: Throwable -> L9
        return true;
    L11:
        monitor-exit(r1);     // Catch: Throwable -> L9
        return false;
    }
}
