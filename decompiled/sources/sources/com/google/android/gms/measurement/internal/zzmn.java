package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import java.util.Objects;

/* loaded from: classes5.dex */
final class zzmn implements Runnable {
    final /* synthetic */ zzlu zza;
    final /* synthetic */ zznl zzb;

    public zzmn(zznl r1, zzlu r2) {
        this.zza = r2;
        Objects.requireNonNull(r1);
        this.zzb = r1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zznl r0 = this.zzb;
        zzgb r1 = r0.zzZ();
        if (r1 != null) goto L16;
        r0.zzu.zzaW().zzb().zza("Failed to send current screen to service");
        return;
    L16:
        zzlu r2 = this.zza;     // Catch: RemoteException -> L9
        if (r2 != null) goto L11;
        r1.zzl(0, null, null, r0.zzu.zzaZ().getPackageName());     // Catch: RemoteException -> L9
    L12:
        r0.zzV();     // Catch: RemoteException -> L9
        return;
    L11:
        r1.zzl(r2.zzc, r2.zza, r2.zzb, r0.zzu.zzaZ().getPackageName());     // Catch: RemoteException -> L9
    L9:
        e = move-exception;
        this.zzb.zzu.zzaW().zzb().zzb("Failed to send current screen to the service", e);
    }
}
