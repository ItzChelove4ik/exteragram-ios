package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Objects;

/* loaded from: classes5.dex */
final class zzmk implements Runnable {
    final /* synthetic */ zzr zza;
    final /* synthetic */ zznl zzb;

    public zzmk(zznl r1, zzr r2, boolean r3) {
        this.zza = r2;
        Objects.requireNonNull(r1);
        this.zzb = r1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zznl r0 = this.zzb;
        zzgb r1 = r0.zzZ();
        if (r1 != null) goto L15;
        r0.zzu.zzaW().zzb().zza("Discarding data. Failed to send app launch");
        return;
    L15:
        zzr r2 = this.zza;     // Catch: RemoteException -> L9
        Preconditions.checkNotNull(r2);     // Catch: RemoteException -> L9
        zzic r3 = r0.zzu;     // Catch: RemoteException -> L9
        zzal r4 = r3.zzc();     // Catch: RemoteException -> L9
        zzfx r5 = zzfy.zzaW;     // Catch: RemoteException -> L9
        if (r4.zzp(null, r5) == false) goto L11;
        r0.zzm(r1, null, r2);     // Catch: RemoteException -> L9
    L11:
        r1.zzg(r2);     // Catch: RemoteException -> L9
        r0.zzu.zzm().zzo();     // Catch: RemoteException -> L9
        r3.zzc().zzp(null, r5);     // Catch: RemoteException -> L9
        r0.zzm(r1, null, r2);     // Catch: RemoteException -> L9
        r0.zzV();     // Catch: RemoteException -> L9
        return;
    L9:
        e = move-exception;
        this.zzb.zzu.zzaW().zzb().zzb("Failed to send app launch to the service", e);
    }
}
