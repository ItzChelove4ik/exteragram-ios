package com.google.android.gms.internal.mlkit_genai_prompt;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.RemoteException;
import android.util.Log;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

/* loaded from: classes4.dex */
public abstract class zzgb implements ServiceConnection {
    protected final Object zza;
    protected zzpk zzb;
    protected zzgu zzc;
    final /* synthetic */ zzgl zzd;

    public zzgb(zzgl r1) {
        Objects.requireNonNull(r1);
        this.zzd = r1;
        this.zza = new Object();
        this.zzc = new zzgu();
        zza();
    }

    @Override // android.content.ServiceConnection
    public final void onBindingDied(ComponentName r3) {
        zzf(zzdo.zze(603, "AiCore service binding died.", null));
    }

    @Override // android.content.ServiceConnection
    public final void onNullBinding(ComponentName r3) {
        zzf(zzdo.zze(605, "AiCore service returns null on binding.", null));
    }

    @Override // android.content.ServiceConnection
    public final void onServiceDisconnected(ComponentName r5) {
        Object r52 = this.zza;
        monitor-enter(r52);
        this.zzc.zzb();     // Catch: Throwable -> L7
        this.zzb.zzi(zzdo.zze(602, "AiCore service disconnected.", null));     // Catch: Throwable -> L7
        zza();     // Catch: Throwable -> L7
        monitor-exit(r52);     // Catch: Throwable -> L7
        return;
    L7:
        th = move-exception;
        throw th;
    }

    public final void zza() {
        Object r0 = this.zza;
        monitor-enter(r0);
        this.zzb = zzpk.zza();     // Catch: Throwable -> L7
        this.zzc = new zzgu();     // Catch: Throwable -> L7
        monitor-exit(r0);     // Catch: Throwable -> L7
        return;
    L7:
        th = move-exception;
        throw th;
    }

    public abstract void zzb();

    public final void zzc() {
        Object r0 = this.zza;
        monitor-enter(r0);
        zzpk r1 = this.zzb;     // Catch: Throwable -> L14
        if (r1 != null) goto L7;
    L25:
        this.zzd.zza.unbindService(this);     // Catch: Throwable -> L14
        this.zzc.zzb();     // Catch: Throwable -> L14
        monitor-exit(r0);     // Catch: Throwable -> L14
        return;
    L7:
        if (r1.isDone() == false) goto L25;
        if (this.zzb.isCancelled() == true) goto L24;
        zzgl r12 = this.zzd;     // Catch: Throwable -> L14
        if (r12.zzo() == true) goto L24;
        ((zzdh) zzot.zzi(this.zzb)).zza().asBinder().unlinkToDeath(r12, 0);     // Catch: Throwable -> L14 RuntimeException -> L16 ExecutionException -> L18 NoSuchElementException -> L20
    L20:
        e = move-exception;
        Log.w(zzgl.zzp(), "Attempted to unlink a death recipient that was not registered. This is safe to ignore.", e);     // Catch: Throwable -> L14
    L16:
        e = e;
    L22:
        Log.e(zzgl.zzp(), "Failed to get service for unbind, unable to call unlinkToDeath. Cause: ", e);     // Catch: Throwable -> L14
    L18:
        e = e;
    L24:
        Log.i(zzgl.zzp(), "Service context future was cancelled, no need to unlinkToDeath.");     // Catch: Throwable -> L14
    L14:
        th = move-exception;
        throw th;
    }

    public final boolean zzd(Intent r4) {
        zzgl r0 = this.zzd;
        int r2 = 1;
        if (true != r0.zzs()) goto L7;
        r2 = 65;
    L7:
        return r0.zza.bindService(r4, this, r2);
    }

    public final void zze(zzan r4) {
        Object r0 = this.zza;
        monitor-enter(r0);
        this.zzb.zzh(zzdh.zzc(r4, this.zzc));     // Catch: Throwable -> L8
        zzgl r1 = this.zzd;     // Catch: Throwable -> L8
        if (r1.zzo() == false) goto L16;
    L12:
        monitor-exit(r0);     // Catch: Throwable -> L8
        return;
    L16:
        r4.asBinder().linkToDeath(r1, 0);     // Catch: Throwable -> L8 RemoteException -> L10
    L10:
        e = move-exception;
        zzf(zzdo.zze(6, "AICore service died before linking death recipient.", e));     // Catch: Throwable -> L8
    L8:
        th = move-exception;
        throw th;
    }

    public final void zzf(zzdo r3) {
        Object r0 = this.zza;
        monitor-enter(r0);
        this.zzb.zzi(r3);     // Catch: Throwable -> L8
        monitor-exit(r0);     // Catch: Throwable -> L8
        this.zzd.zzj();
        return;
    L8:
        th = move-exception;
        throw th;
    }
}
