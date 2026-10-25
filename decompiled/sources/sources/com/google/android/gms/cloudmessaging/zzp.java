package com.google.android.gms.cloudmessaging;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Looper;
import android.os.Messenger;
import android.util.Log;
import android.util.SparseArray;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.stats.ConnectionTracker;
import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

/* loaded from: classes.dex */
final class zzp implements ServiceConnection {
    int zza;
    final Messenger zzb;
    zzq zzc;
    final Queue zzd;
    final SparseArray zze;
    final /* synthetic */ zzv zzf;

    public /* synthetic */ zzp(zzv r3, byte[] r4) {
        Objects.requireNonNull(r3);
        this.zzf = r3;
        this.zza = 0;
        this.zzb = new Messenger(new com.google.android.gms.internal.cloudmessaging.zzv(Looper.getMainLooper(), new zzo(this)));
        this.zzd = new ArrayDeque();
        this.zze = new SparseArray();
    }

    @Override // android.content.ServiceConnection
    public final void onServiceConnected(ComponentName r2, IBinder r3) {
        if (Log.isLoggable("MessengerIpcClient", 2) == false) goto L5;
        Log.v("MessengerIpcClient", "Service connected");
    L5:
        zzv r22 = this.zzf;
        zzk r0 = new zzk(this, r3);
        r22.zze().execute(r0);
    }

    @Override // android.content.ServiceConnection
    public final void onServiceDisconnected(ComponentName r2) {
        if (Log.isLoggable("MessengerIpcClient", 2) == false) goto L5;
        Log.v("MessengerIpcClient", "Service disconnected");
    L5:
        zzv r22 = this.zzf;
        zzm r0 = new zzm(this);
        r22.zze().execute(r0);
    }

    public final synchronized boolean zza(zzs r7) {
        monitor-enter(this);
        int r0 = this.zza;     // Catch: Throwable -> L13
        if (r0 == 0) goto L18;
        if (r0 == 1) goto L15;
        if (r0 == 2) goto L10;
        monitor-exit(this);
        return false;
    L10:
        this.zzd.add(r7);     // Catch: Throwable -> L13
        zzb();     // Catch: Throwable -> L13
        monitor-exit(this);
        return true;
    L15:
        this.zzd.add(r7);     // Catch: Throwable -> L13
        monitor-exit(this);
        return true;
    L18:
        this.zzd.add(r7);     // Catch: Throwable -> L13
        if (this.zza != 0) goto L21;
        boolean r72 = true;
    L22:
        Preconditions.checkState(r72);     // Catch: Throwable -> L13
        if (Log.isLoggable("MessengerIpcClient", 2) == false) goto L25;
        Log.v("MessengerIpcClient", "Starting bind to GmsCore");     // Catch: Throwable -> L13
    L25:
        this.zza = 1;     // Catch: Throwable -> L13
        Intent r73 = new Intent("com.google.android.c2dm.intent.REGISTER");     // Catch: Throwable -> L13
        r73.setPackage("com.google.android.gms");     // Catch: Throwable -> L13
        ConnectionTracker r02 = ConnectionTracker.getInstance();     // Catch: Throwable -> L13 SecurityException -> L30
        zzv r1 = this.zzf;     // Catch: Throwable -> L13 SecurityException -> L30
        if (r02.bindService(r1.zzd(), r73, this, 1) == true) goto L32;
        zzc(0, "Unable to bind to service");     // Catch: Throwable -> L13 SecurityException -> L30
    L34:
        monitor-exit(this);
        return true;
    L32:
        r1.zze().schedule(new zzj(this), 30, TimeUnit.SECONDS);     // Catch: Throwable -> L13
    L30:
        e = move-exception;
        zzd(0, "Unable to bind to service", e);     // Catch: Throwable -> L13
        goto L34
    L21:
        r72 = false;
    L13:
        th = move-exception;
        throw th;
    }

    public final void zzb() {
        zzl r0 = new zzl(this);
        this.zzf.zze().execute(r0);
    }

    public final synchronized void zzc(int r2, String r3) {
        monitor-enter(this);
        zzd(r2, r3, null);     // Catch: Throwable -> L7
        monitor-exit(this);
        return;
    L7:
        th = move-exception;
        throw th;
    }

    public final synchronized void zzd(int r6, String r7, Throwable r8) {
        monitor-enter(this);
    L6:
        th = move-exception;
        throw th;
    L4:
        if (Log.isLoggable("MessengerIpcClient", 3) == false) goto L8;
        Log.d("MessengerIpcClient", "Disconnected: ".concat(String.valueOf(r7)));     // Catch: Throwable -> L6
    L8:
        int r0 = this.zza;     // Catch: Throwable -> L6
        if (r0 == 0) goto L34;
        if (r0 == 1) goto L20;
        if (r0 == 2) goto L20;
        if (r0 == 3) goto L16;
        monitor-exit(this);
        return;
    L16:
        this.zza = 4;     // Catch: Throwable -> L6
        monitor-exit(this);
        return;
    L20:
        if (Log.isLoggable("MessengerIpcClient", 2) == false) goto L22;
        Log.v("MessengerIpcClient", "Unbinding service");     // Catch: Throwable -> L6
    L22:
        this.zza = 4;     // Catch: Throwable -> L6
        zzv r02 = this.zzf;     // Catch: Throwable -> L6
        ConnectionTracker.getInstance().unbindService(r02.zzd(), this);     // Catch: Throwable -> L6
        zzt r03 = new zzt(r6, r7, r8);     // Catch: Throwable -> L6
        Queue r62 = this.zzd;     // Catch: Throwable -> L6
        Iterator r72 = r62.iterator();     // Catch: Throwable -> L6
    L24:
        if (r72.hasNext() == false) goto L26;
        ((zzs) r72.next()).zzd(r03);     // Catch: Throwable -> L6
        goto L24
    L26:
        r62.clear();     // Catch: Throwable -> L6
        int r63 = 0;
    L27:
        SparseArray r73 = this.zze;     // Catch: Throwable -> L6
        if (r63 >= r73.size()) goto L30;
        ((zzs) r73.valueAt(r63)).zzd(r03);     // Catch: Throwable -> L6
        r63 = r63 + 1;     // Catch: Throwable -> L6
        goto L27
    L30:
        r73.clear();     // Catch: Throwable -> L6
        monitor-exit(this);
        return;
    L34:
        throw new IllegalStateException();     // Catch: Throwable -> L6
    }

    public final synchronized void zze() {
        monitor-enter(this);
    L12:
        th = move-exception;
        throw th;
    L4:
        if (this.zza == 2) goto L6;
    L17:
        monitor-exit(this);
        return;
    L6:
        if (this.zzd.isEmpty() == false) goto L17;
        if (this.zze.size() != 0) goto L17;
        if (Log.isLoggable("MessengerIpcClient", 2) == false) goto L14;
        Log.v("MessengerIpcClient", "Finished handling requests, unbinding");     // Catch: Throwable -> L12
    L14:
        this.zza = 3;     // Catch: Throwable -> L12
        zzv r0 = this.zzf;     // Catch: Throwable -> L12
        ConnectionTracker.getInstance().unbindService(r0.zzd(), this);     // Catch: Throwable -> L12
        monitor-exit(this);
    }

    public final synchronized void zzf() {
        monitor-enter(this);
    L8:
        th = move-exception;
        throw th;
    L4:
        if (this.zza != 1) goto L10;
        zzc(1, "Timed out while binding");     // Catch: Throwable -> L8
        monitor-exit(this);
        return;
    L10:
        monitor-exit(this);
    }

    public final synchronized void zzg(int r5) {
        monitor-enter(this);
        SparseArray r0 = this.zze;     // Catch: Throwable -> L8
        zzs r1 = (zzs) r0.get(r5);     // Catch: Throwable -> L8
        if (r1 == null) goto L10;
        StringBuilder r3 = new StringBuilder(String.valueOf(r5).length() + 20);     // Catch: Throwable -> L8
        r3.append("Timing out request: ");     // Catch: Throwable -> L8
        r3.append(r5);     // Catch: Throwable -> L8
        Log.w("MessengerIpcClient", r3.toString());     // Catch: Throwable -> L8
        r0.remove(r5);     // Catch: Throwable -> L8
        r1.zzd(new zzt(3, "Timed out waiting for response", null));     // Catch: Throwable -> L8
        zze();     // Catch: Throwable -> L8
        monitor-exit(this);
        return;
    L10:
        monitor-exit(this);
        return;
    L8:
        th = move-exception;
        throw th;
    }
}
