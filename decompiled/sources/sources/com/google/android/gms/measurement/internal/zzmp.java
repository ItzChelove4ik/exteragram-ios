package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import java.util.Objects;

/* loaded from: classes5.dex */
final class zzmp implements Runnable {
    final /* synthetic */ zzbh zza;
    final /* synthetic */ String zzb;
    final /* synthetic */ com.google.android.gms.internal.measurement.zzcs zzc;
    final /* synthetic */ zznl zzd;

    public zzmp(zznl r1, zzbh r2, String r3, com.google.android.gms.internal.measurement.zzcs r4) {
        this.zza = r2;
        this.zzb = r3;
        this.zzc = r4;
        Objects.requireNonNull(r1);
        this.zzd = r1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        byte[] r0 = null;
        zznl r1 = this.zzd;     // Catch: Throwable -> L11 RemoteException -> L13
        zzgb r2 = r1.zzZ();     // Catch: Throwable -> L11 RemoteException -> L13
        if (r2 != null) goto L9;
        zzic r12 = r1.zzu;     // Catch: Throwable -> L11 RemoteException -> L13
        r12.zzaW().zzb().zza("Discarding data. Failed to send event to service to bundle");     // Catch: Throwable -> L11 RemoteException -> L13
        zzpp r13 = r12.zzk();
        com.google.android.gms.internal.measurement.zzcs r5 = this.zzc;
    L7:
        r13.zzar(r5, r0);
        return;
    L9:
        r0 = r2.zzk(this.zza, this.zzb);     // Catch: Throwable -> L11 RemoteException -> L13
        r1.zzV();     // Catch: Throwable -> L11 RemoteException -> L13
    L15:
        zznl r14 = this.zzd;
        r5 = this.zzc;
        r13 = r14.zzu.zzk();
    L13:
        e = move-exception;
        this.zzd.zzu.zzaW().zzb().zzb("Failed to send event to the service to bundle", e);     // Catch: Throwable -> L11
    L11:
        th = move-exception;
        zznl r22 = this.zzd;
        com.google.android.gms.internal.measurement.zzcs r52 = this.zzc;
        r22.zzu.zzk().zzar(r52, null);
        throw th;
    }
}
