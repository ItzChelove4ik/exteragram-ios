package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Objects;

/* loaded from: classes5.dex */
final class zzmo implements Runnable {
    final /* synthetic */ zzr zza;
    final /* synthetic */ boolean zzb;
    final /* synthetic */ zzbf zzc;
    final /* synthetic */ Bundle zzd;
    final /* synthetic */ zznl zze;

    public zzmo(zznl r1, boolean r2, zzr r3, boolean r4, zzbf r5, Bundle r6) {
        this.zza = r3;
        this.zzb = r4;
        this.zzc = r5;
        this.zzd = r6;
        Objects.requireNonNull(r1);
        this.zze = r1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zznl r0 = this.zze;
        zzgb r1 = r0.zzZ();
        zzic r2 = r0.zzu;
        if (r1 != null) goto L6;
        r2.zzaW().zzb().zza("Failed to send default event parameters to service");
        return;
    L6:
        zzbf r5 = null;
        boolean r22 = r2.zzc().zzp(null, zzfy.zzaW);
        zzr r4 = this.zza;
        if (r22 == false) goto L19;
        Preconditions.checkNotNull(r4);
        if (this.zzb == true) goto L12;
        r5 = this.zzc;
    L12:
        r0.zzm(r1, r5, r4);
        return;
    L19:
        Preconditions.checkNotNull(r4);     // Catch: RemoteException -> L16
        r1.zzu(this.zzd, r4);     // Catch: RemoteException -> L16
        r0.zzV();     // Catch: RemoteException -> L16
        return;
    L16:
        e = move-exception;
        this.zze.zzu.zzaW().zzb().zzb("Failed to send default event parameters to service", e);
    }
}
