package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Collections;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes5.dex */
final class zzmv implements Runnable {
    final /* synthetic */ AtomicReference zza;
    final /* synthetic */ String zzb;
    final /* synthetic */ String zzc;
    final /* synthetic */ zzr zzd;
    final /* synthetic */ zznl zze;

    public zzmv(zznl r1, AtomicReference r2, String r3, String r4, String r5, zzr r6) {
        this.zza = r2;
        this.zzb = r4;
        this.zzc = r5;
        this.zzd = r6;
        Objects.requireNonNull(r1);
        this.zze = r1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        AtomicReference r0 = this.zza;
        monitor-enter(r0);
        zznl r2 = this.zze;     // Catch: Throwable -> L13 RemoteException -> L15
        zzgb r3 = r2.zzZ();     // Catch: Throwable -> L13 RemoteException -> L15
        if (r3 != null) goto L18;
        r2.zzu.zzaW().zzb().zzd("(legacy) Failed to get conditional properties; not connected to service", null, this.zzb, this.zzc);     // Catch: Throwable -> L13 RemoteException -> L15
        r0.set(Collections.EMPTY_LIST);     // Catch: Throwable -> L13 RemoteException -> L15
        r0.notify();     // Catch: Throwable -> L11
        monitor-exit(r0);     // Catch: Throwable -> L11
        return;
    L18:
        if (TextUtils.isEmpty(null) == false) goto L20;
        zzr r4 = this.zzd;     // Catch: Throwable -> L13 RemoteException -> L15
        Preconditions.checkNotNull(r4);     // Catch: Throwable -> L13 RemoteException -> L15
        r0.set(r3.zzr(this.zzb, this.zzc, r4));     // Catch: Throwable -> L13 RemoteException -> L15
    L21:
        r2.zzV();     // Catch: Throwable -> L13 RemoteException -> L15
        AtomicReference r7 = this.zza;     // Catch: Throwable -> L11
    L26:
        r7.notify();     // Catch: Throwable -> L11
        monitor-exit(r0);     // Catch: Throwable -> L11
        return;
    L20:
        r0.set(r3.zzs(null, this.zzb, this.zzc));     // Catch: Throwable -> L13 RemoteException -> L15
    L15:
        e = move-exception;
        this.zze.zzu.zzaW().zzb().zzd("(legacy) Failed to get conditional properties; remote exception", null, this.zzb, e);     // Catch: Throwable -> L13
        this.zza.set(Collections.EMPTY_LIST);     // Catch: Throwable -> L13
        r7 = this.zza;     // Catch: Throwable -> L11
    L13:
        th = move-exception;
        this.zza.notify();     // Catch: Throwable -> L11
        throw th;     // Catch: Throwable -> L11
    L11:
        th = move-exception;
        throw th;
    }
}
