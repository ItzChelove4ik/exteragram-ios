package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes5.dex */
final class zzmi implements Runnable {
    final /* synthetic */ AtomicReference zza;
    final /* synthetic */ zzr zzb;
    final /* synthetic */ zznl zzc;

    public zzmi(zznl r1, AtomicReference r2, zzr r3) {
        this.zza = r2;
        this.zzb = r3;
        Objects.requireNonNull(r1);
        this.zzc = r1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        AtomicReference r0 = this.zza;
        monitor-enter(r0);
        zznl r1 = this.zzc;     // Catch: Throwable -> L12 RemoteException -> L14
        zzic r2 = r1.zzu;     // Catch: Throwable -> L12 RemoteException -> L14
        if (r2.zzd().zzl().zzo(zzjk.zzb) == true) goto L16;
        r2.zzaW().zzh().zza("Analytics storage consent denied; will not get app instance id");     // Catch: Throwable -> L12 RemoteException -> L14
        r1.zzu.zzj().zzQ(null);     // Catch: Throwable -> L12 RemoteException -> L14
        r2.zzd().zze.zzb(null);     // Catch: Throwable -> L12 RemoteException -> L14
        r0.set(null);     // Catch: Throwable -> L12 RemoteException -> L14
    L7:
        r0.notify();     // Catch: Throwable -> L10
        monitor-exit(r0);     // Catch: Throwable -> L10
        return;
    L16:
        zzgb r3 = r1.zzZ();     // Catch: Throwable -> L12 RemoteException -> L14
        if (r3 != null) goto L19;
        r2.zzaW().zzb().zza("Failed to get app instance id");     // Catch: Throwable -> L12 RemoteException -> L14
        goto L7
    L19:
        zzr r4 = this.zzb;     // Catch: Throwable -> L12 RemoteException -> L14
        Preconditions.checkNotNull(r4);     // Catch: Throwable -> L12 RemoteException -> L14
        r0.set(r3.zzm(r4));     // Catch: Throwable -> L12 RemoteException -> L14
        String r32 = (String) r0.get();     // Catch: Throwable -> L12 RemoteException -> L14
        if (r32 == null) goto L22;
        r1.zzu.zzj().zzQ(r32);     // Catch: Throwable -> L12 RemoteException -> L14
        r2.zzd().zze.zzb(r32);     // Catch: Throwable -> L12 RemoteException -> L14
    L22:
        r1.zzV();     // Catch: Throwable -> L12 RemoteException -> L14
        AtomicReference r5 = this.zza;     // Catch: Throwable -> L10
    L27:
        r5.notify();     // Catch: Throwable -> L10
        monitor-exit(r0);     // Catch: Throwable -> L10
        return;
    L12:
        th = move-exception;
        this.zza.notify();     // Catch: Throwable -> L10
        throw th;     // Catch: Throwable -> L10
    L14:
        e = move-exception;
        this.zzc.zzu.zzaW().zzb().zzb("Failed to get app instance id", e);     // Catch: Throwable -> L12
        r5 = this.zza;     // Catch: Throwable -> L10
    L10:
        th = move-exception;
        throw th;
    }
}
