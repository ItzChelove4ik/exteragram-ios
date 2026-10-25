package com.google.android.gms.tasks;

import java.util.concurrent.ExecutionException;

/* loaded from: classes5.dex */
final class zzae<T> implements zzab<T> {
    private final Object zza;
    private final int zzb;
    private final zzw zzc;
    private int zzd;
    private int zze;
    private int zzf;
    private Exception zzg;
    private boolean zzh;

    public zzae(int r2, zzw r3) {
        this.zza = new Object();
        this.zzb = r2;
        this.zzc = r3;
    }

    private final void zza() {
        int r0 = this.zzd;
        int r1 = this.zze;
        int r02 = (r0 + r1) + this.zzf;
        int r2 = this.zzb;
        if (r02 == r2) goto L5;
        return;
    L5:
        if (this.zzg == null) goto L8;
        zzw r03 = this.zzc;
        int r4 = String.valueOf(r1).length();
        StringBuilder r5 = new StringBuilder(((r4 + 8) + String.valueOf(r2).length()) + 24);
        r5.append(r1);
        r5.append(" out of ");
        r5.append(r2);
        r5.append(" underlying tasks failed");
        r03.zzc(new ExecutionException(r5.toString(), this.zzg));
        return;
    L8:
        boolean r04 = this.zzh;
        zzw r6 = this.zzc;
        if (r04 == false) goto L12;
        r6.zze();
        return;
    L12:
        r6.zza(null);
    }

    @Override // com.google.android.gms.tasks.OnCanceledListener
    public final void onCanceled() {
        Object r0 = this.zza;
        monitor-enter(r0);
        this.zzf++;
        this.zzh = true;     // Catch: Throwable -> L7
        zza();     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return;
    L7:
        th = move-exception;
        throw th;
    }

    @Override // com.google.android.gms.tasks.OnFailureListener
    public final void onFailure(Exception r3) {
        Object r0 = this.zza;
        monitor-enter(r0);
        this.zze++;
        this.zzg = r3;     // Catch: Throwable -> L7
        zza();     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return;
    L7:
        th = move-exception;
        throw th;
    }

    @Override // com.google.android.gms.tasks.OnSuccessListener
    public final void onSuccess(T r2) {
        Object r22 = this.zza;
        monitor-enter(r22);
        this.zzd++;
        zza();     // Catch: Throwable -> L7
        monitor-exit(r22);     // Catch: Throwable -> L7
        return;
    L7:
        th = move-exception;
        throw th;
    }
}
