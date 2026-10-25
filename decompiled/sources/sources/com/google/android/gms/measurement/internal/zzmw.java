package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import java.util.ArrayList;
import java.util.Objects;

/* loaded from: classes5.dex */
final class zzmw implements Runnable {
    final /* synthetic */ String zza;
    final /* synthetic */ String zzb;
    final /* synthetic */ zzr zzc;
    final /* synthetic */ com.google.android.gms.internal.measurement.zzcs zzd;
    final /* synthetic */ zznl zze;

    public zzmw(zznl r1, String r2, String r3, zzr r4, com.google.android.gms.internal.measurement.zzcs r5) {
        this.zza = r2;
        this.zzb = r3;
        this.zzc = r4;
        this.zzd = r5;
        Objects.requireNonNull(r1);
        this.zze = r1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ArrayList r0 = new ArrayList();
        zznl r1 = this.zze;     // Catch: Throwable -> L11 RemoteException -> L13
        zzgb r2 = r1.zzZ();     // Catch: Throwable -> L11 RemoteException -> L13
        if (r2 != null) goto L9;
        zzic r12 = r1.zzu;     // Catch: Throwable -> L11 RemoteException -> L13
        r12.zzaW().zzb().zzc("Failed to get conditional properties; not connected to service", this.zza, this.zzb);     // Catch: Throwable -> L11 RemoteException -> L13
        zzpp r13 = r12.zzk();
        com.google.android.gms.internal.measurement.zzcs r6 = this.zzd;
    L7:
        r13.zzau(r6, r0);
        return;
    L9:
        zzr r3 = this.zzc;     // Catch: Throwable -> L11 RemoteException -> L13
        Preconditions.checkNotNull(r3);     // Catch: Throwable -> L11 RemoteException -> L13
        r0 = zzpp.zzav(r2.zzr(this.zza, this.zzb, r3));     // Catch: Throwable -> L11 RemoteException -> L13
        r1.zzV();     // Catch: Throwable -> L11 RemoteException -> L13
    L15:
        zznl r14 = this.zze;
        r6 = this.zzd;
        r13 = r14.zzu.zzk();
    L13:
        e = move-exception;
        this.zze.zzu.zzaW().zzb().zzd("Failed to get conditional properties; remote exception", this.zza, this.zzb, e);     // Catch: Throwable -> L11
    L11:
        th = move-exception;
        zznl r22 = this.zze;
        com.google.android.gms.internal.measurement.zzcs r62 = this.zzd;
        r22.zzu.zzk().zzau(r62, r0);
        throw th;
    }
}
