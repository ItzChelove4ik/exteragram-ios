package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Objects;

/* loaded from: classes5.dex */
final class zzmj implements Runnable {
    final /* synthetic */ zzr zza;
    final /* synthetic */ com.google.android.gms.internal.measurement.zzcs zzb;
    final /* synthetic */ zznl zzc;

    public zzmj(zznl r1, zzr r2, com.google.android.gms.internal.measurement.zzcs r3) {
        this.zza = r2;
        this.zzb = r3;
        Objects.requireNonNull(r1);
        this.zzc = r1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        String r1 = null;
        zznl r2 = this.zzc;     // Catch: Throwable -> L15 RemoteException -> L17
        zzic r3 = r2.zzu;     // Catch: Throwable -> L15 RemoteException -> L17
        if (r3.zzd().zzl().zzo(zzjk.zzb) == true) goto L6;
        r3.zzaW().zzh().zza("Analytics storage consent denied; will not get app instance id");     // Catch: Throwable -> L15 RemoteException -> L17
        r2.zzu.zzj().zzQ(null);     // Catch: Throwable -> L15 RemoteException -> L17
        r3.zzd().zze.zzb(null);     // Catch: Throwable -> L15 RemoteException -> L17
    L9:
        zzpp r0 = r3.zzk();
        com.google.android.gms.internal.measurement.zzcs r6 = this.zzb;
    L10:
        r0.zzao(r6, r1);
        return;
    L6:
        zzgb r4 = r2.zzZ();     // Catch: Throwable -> L15 RemoteException -> L17
        if (r4 != null) goto L12;
        r3.zzaW().zzb().zza("Failed to get app instance id");     // Catch: Throwable -> L15 RemoteException -> L17
        goto L9
    L12:
        zzr r5 = this.zza;     // Catch: Throwable -> L15 RemoteException -> L17
        Preconditions.checkNotNull(r5);     // Catch: Throwable -> L15 RemoteException -> L17
        r1 = r4.zzm(r5);     // Catch: Throwable -> L15 RemoteException -> L17
        if (r1 == null) goto L19;
        r2.zzu.zzj().zzQ(r1);     // Catch: Throwable -> L15 RemoteException -> L17
        r3.zzd().zze.zzb(r1);     // Catch: Throwable -> L15 RemoteException -> L17
    L19:
        r2.zzV();     // Catch: Throwable -> L15 RemoteException -> L17
    L22:
        zznl r02 = this.zzc;
        r6 = this.zzb;
        r0 = r02.zzu.zzk();
    L15:
        th = move-exception;
        zznl r22 = this.zzc;
        com.google.android.gms.internal.measurement.zzcs r62 = this.zzb;
        r22.zzu.zzk().zzao(r62, null);
        throw th;
    L17:
        e = move-exception;
        this.zzc.zzu.zzaW().zzb().zzb("Failed to get app instance id", e);     // Catch: Throwable -> L15
        goto L22
    }
}
