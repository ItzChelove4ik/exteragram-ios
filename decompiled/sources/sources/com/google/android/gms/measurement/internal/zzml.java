package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Objects;

/* loaded from: classes5.dex */
final class zzml implements Runnable {
    final /* synthetic */ zzr zza;
    final /* synthetic */ zznl zzb;

    public zzml(zznl r1, zzr r2) {
        this.zza = r2;
        Objects.requireNonNull(r1);
        this.zzb = r1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zznl r0 = this.zzb;
        zzgb r1 = r0.zzZ();
        if (r1 != null) goto L11;
        r0.zzu.zzaW().zze().zza("Failed to send app backgrounded");
        return;
    L11:
        zzr r2 = this.zza;     // Catch: RemoteException -> L8
        Preconditions.checkNotNull(r2);     // Catch: RemoteException -> L8
        r1.zzA(r2);     // Catch: RemoteException -> L8
        r0.zzV();     // Catch: RemoteException -> L8
        return;
    L8:
        e = move-exception;
        this.zzb.zzu.zzaW().zzb().zzb("Failed to send app backgrounded to the service", e);
    }
}
