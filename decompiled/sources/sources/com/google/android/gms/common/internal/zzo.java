package com.google.android.gms.common.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Message;
import android.os.StrictMode;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.stats.ConnectionTracker;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.Executor;

/* loaded from: classes4.dex */
final class zzo implements ServiceConnection, zzr {
    final /* synthetic */ zzq zza;
    private final Map zzb;
    private int zzc;
    private boolean zzd;
    private IBinder zze;
    private final zzn zzf;
    private ComponentName zzg;

    public zzo(zzq r1, zzn r2) {
        java.util.Objects.requireNonNull(r1);
        this.zza = r1;
        this.zzf = r2;
        this.zzb = new HashMap();
        this.zzc = 2;
    }

    @Override // android.content.ServiceConnection
    public final void onBindingDied(ComponentName r1) {
        onServiceDisconnected(r1);
    }

    @Override // android.content.ServiceConnection
    public final void onServiceConnected(ComponentName r5, IBinder r6) {
        zzq r0 = this.zza;
        HashMap r1 = r0.zzf();
        monitor-enter(r1);
        r0.zzh().removeMessages(1, this.zzf);     // Catch: Throwable -> L8
        this.zze = r6;     // Catch: Throwable -> L8
        this.zzg = r5;     // Catch: Throwable -> L8
        Iterator r02 = this.zzb.values().iterator();     // Catch: Throwable -> L8
    L6:
        if (r02.hasNext() == false) goto L10;
        ((ServiceConnection) r02.next()).onServiceConnected(r5, r6);     // Catch: Throwable -> L8
        goto L6
    L10:
        this.zzc = 1;     // Catch: Throwable -> L8
        monitor-exit(r1);     // Catch: Throwable -> L8
        return;
    L8:
        th = move-exception;
        throw th;
    }

    @Override // android.content.ServiceConnection
    public final void onServiceDisconnected(ComponentName r5) {
        zzq r0 = this.zza;
        HashMap r1 = r0.zzf();
        monitor-enter(r1);
        r0.zzh().removeMessages(1, this.zzf);     // Catch: Throwable -> L8
        this.zze = null;     // Catch: Throwable -> L8
        this.zzg = r5;     // Catch: Throwable -> L8
        Iterator r02 = this.zzb.values().iterator();     // Catch: Throwable -> L8
    L6:
        if (r02.hasNext() == false) goto L10;
        ((ServiceConnection) r02.next()).onServiceDisconnected(r5);     // Catch: Throwable -> L8
        goto L6
    L10:
        this.zzc = 2;     // Catch: Throwable -> L8
        monitor-exit(r1);     // Catch: Throwable -> L8
        return;
    L8:
        th = move-exception;
        throw th;
    }

    public final void zza(String r4) {
        zzn r42 = this.zzf;
        zzq r0 = this.zza;
        r0.zzh().removeMessages(1, r42);
        r0.zzi().unbindService(r0.zzg(), this);
        this.zzd = false;
        this.zzc = 2;
    }

    public final void zzb(ServiceConnection r1, ServiceConnection r2, String r3) {
        this.zzb.put(r1, r2);
    }

    public final void zzc(ServiceConnection r1, String r2) {
        this.zzb.remove(r1);
    }

    public final boolean zzd() {
        return this.zzd;
    }

    public final int zze() {
        return this.zzc;
    }

    public final boolean zzf(ServiceConnection r1) {
        return this.zzb.containsKey(r1);
    }

    public final boolean zzg() {
        return this.zzb.isEmpty();
    }

    public final IBinder zzh() {
        return this.zze;
    }

    public final ComponentName zzi() {
        return this.zzg;
    }

    public final /* synthetic */ ConnectionResult zzj(String r11, Executor r12) {
        Intent r5 = zzah.zza(this.zza.zzg(), this.zzf);     // Catch: zzaf -> L18
        this.zzc = 3;
        StrictMode.VmPolicy r1 = com.google.android.gms.common.util.zzd.zza();
        zzq r0 = this.zza;     // Catch: Throwable -> L9
        ConnectionTracker r2 = r0.zzi();     // Catch: Throwable -> L9
        Context r3 = r0.zzg();     // Catch: Throwable -> L9
        zzn r9 = this.zzf;     // Catch: Throwable -> L9
        boolean r10 = r2.zza(r3, r11, r5, this, 4225, r12);     // Catch: Throwable -> L9
        this.zzd = r10;     // Catch: Throwable -> L9
        if (r10 == false) goto L12;
        Message r102 = r0.zzh().obtainMessage(1, r9);     // Catch: Throwable -> L9
        r0.zzh().sendMessageDelayed(r102, r0.zzj());     // Catch: Throwable -> L9
        ConnectionResult r103 = ConnectionResult.RESULT_SUCCESS;     // Catch: Throwable -> L9
        StrictMode.setVmPolicy(r1);
        return r103;
    L12:
        this.zzc = 2;     // Catch: Throwable -> L9
        r0.zzi().unbindService(r0.zzg(), this);     // Catch: Throwable -> L9 IllegalArgumentException -> L21
    L14:
        ConnectionResult r104 = new ConnectionResult(16);     // Catch: Throwable -> L9
        StrictMode.setVmPolicy(r1);
        return r104;
    L9:
        th = move-exception;
        StrictMode.setVmPolicy(r1);
        throw th;
    L18:
        e = move-exception;
        return e.zza;
    }
}
