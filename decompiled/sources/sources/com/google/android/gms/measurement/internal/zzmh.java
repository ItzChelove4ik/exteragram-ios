package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Objects;

/* loaded from: classes5.dex */
final class zzmh implements Runnable {
    final /* synthetic */ zzr zza;
    final /* synthetic */ zznl zzb;

    public zzmh(zznl r1, zzr r2) {
        this.zza = r2;
        Objects.requireNonNull(r1);
        this.zzb = r1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zznl r0 = this.zzb;
        zzgb r1 = r0.zzZ();
        if (r1 != null) goto L12;
        r0.zzu.zzaW().zzb().zza("Failed to reset data on the service: not connected to service");
        return;
    L12:
        zzr r02 = this.zza;     // Catch: RemoteException -> L8
        Preconditions.checkNotNull(r02);     // Catch: RemoteException -> L8
        r1.zzt(r02);     // Catch: RemoteException -> L8
    L10:
        this.zzb.zzV();
        return;
    L8:
        e = move-exception;
        this.zzb.zzu.zzaW().zzb().zzb("Failed to reset data on the service: remote exception", e);
        goto L10
    }
}
