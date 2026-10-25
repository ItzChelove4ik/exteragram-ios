package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/* loaded from: classes5.dex */
final class zzmc implements Runnable {
    final /* synthetic */ String zza;
    final /* synthetic */ String zzb;
    final /* synthetic */ zzr zzc;
    final /* synthetic */ boolean zzd;
    final /* synthetic */ com.google.android.gms.internal.measurement.zzcs zze;
    final /* synthetic */ zznl zzf;

    public zzmc(zznl r1, String r2, String r3, zzr r4, boolean r5, com.google.android.gms.internal.measurement.zzcs r6) {
        this.zza = r2;
        this.zzb = r3;
        this.zzc = r4;
        this.zzd = r5;
        this.zze = r6;
        Objects.requireNonNull(r1);
        this.zzf = r1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Bundle r0 = new Bundle();
        zznl r1 = this.zzf;     // Catch: Throwable -> L8 RemoteException -> L10
        zzgb r2 = r1.zzZ();     // Catch: Throwable -> L8 RemoteException -> L10
        if (r2 != null) goto L12;
        zzic r12 = r1.zzu;     // Catch: Throwable -> L8 RemoteException -> L10
        r12.zzaW().zzb().zzc("Failed to get user properties; not connected to service", this.zza, this.zzb);     // Catch: Throwable -> L8 RemoteException -> L10
        r12.zzk().zzat(this.zze, r0);
        return;
    L12:
        zzr r3 = this.zzc;     // Catch: Throwable -> L8 RemoteException -> L10
        Preconditions.checkNotNull(r3);     // Catch: Throwable -> L8 RemoteException -> L10
        List r22 = r2.zzp(this.zza, this.zzb, this.zzd, r3);     // Catch: Throwable -> L8 RemoteException -> L10
        int r32 = zzpp.$r8$clinit;     // Catch: Throwable -> L8 RemoteException -> L10
        Bundle r33 = new Bundle();     // Catch: Throwable -> L8 RemoteException -> L10
        if (r22 == null) goto L41;
        Iterator r23 = r22.iterator();     // Catch: Throwable -> L8 RemoteException -> L10
    L17:
        if (r23.hasNext() == false) goto L41;
        zzpl r4 = (zzpl) r23.next();     // Catch: Throwable -> L8 RemoteException -> L10
        String r5 = r4.zze;     // Catch: Throwable -> L8 RemoteException -> L10
        if (r5 != null) goto L20;
        Long r52 = r4.zzd;     // Catch: Throwable -> L8 RemoteException -> L10
        if (r52 != null) goto L23;
        Double r53 = r4.zzg;     // Catch: Throwable -> L8 RemoteException -> L10
        if (r53 == null) goto L17;
        r33.putDouble(r4.zzb, r53.doubleValue());     // Catch: Throwable -> L8 RemoteException -> L10
        goto L17
    L23:
        r33.putLong(r4.zzb, r52.longValue());     // Catch: Throwable -> L8 RemoteException -> L10
        goto L17
    L20:
        r33.putString(r4.zzb, r5);     // Catch: Throwable -> L8 RemoteException -> L10
    L41:
        r1.zzV();     // Catch: Throwable -> L31 RemoteException -> L33
        zzic r02 = r1.zzu;
        com.google.android.gms.internal.measurement.zzcs r7 = this.zze;
        r02.zzk().zzat(r7, r33);
        return;
    L33:
        RemoteException e = e;
    L36:
        this.zzf.zzu.zzaW().zzb().zzc("Failed to get user properties; remote exception", this.zza, e);     // Catch: Throwable -> L31
        zznl r03 = this.zzf;
        com.google.android.gms.internal.measurement.zzcs r72 = this.zze;
        r03.zzu.zzk().zzat(r72, r33);
        return;
    L31:
        th = move-exception;
        th = th;
        r0 = r33;
    L39:
        zznl r24 = this.zzf;
        com.google.android.gms.internal.measurement.zzcs r73 = this.zze;
        r24.zzu.zzk().zzat(r73, r0);
        throw th;
    L10:
        e = move-exception;
        r33 = r0;
        e = e;
    L8:
        th = th;
        goto L39
    }
}
