package com.google.android.gms.measurement.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.RemoteException;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.stats.ConnectionTracker;
import java.util.Objects;

/* loaded from: classes5.dex */
public final class zznf implements ServiceConnection, BaseGmsClient.BaseConnectionCallbacks, BaseGmsClient.BaseOnConnectionFailedListener {
    final /* synthetic */ zznl zza;
    private volatile boolean zzb;
    private volatile zzgo zzc;

    public zznf(zznl r1) {
        Objects.requireNonNull(r1);
        this.zza = r1;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient.BaseConnectionCallbacks
    public final void onConnected(Bundle r3) {
        this.zza.zzu.zzaX().zzd();
        monitor-enter(this);
        Preconditions.checkNotNull(this.zzc);     // Catch: Throwable -> L6 Throwable -> L8
        zzgb r32 = (zzgb) this.zzc.getService();     // Catch: Throwable -> L6 Throwable -> L8
        this.zza.zzu.zzaX().zzj(new zzna(this, r32));     // Catch: Throwable -> L6 Throwable -> L8
    L10:
        monitor-exit(this);     // Catch: Throwable -> L6
        return;
    L6:
        th = move-exception;
        throw th;
    L9:
        this.zzc = null;     // Catch: Throwable -> L6
        this.zzb = false;     // Catch: Throwable -> L6
        goto L10
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient.BaseOnConnectionFailedListener
    public final void onConnectionFailed(ConnectionResult r3) {
        zznl r0 = this.zza;
        r0.zzu.zzaX().zzd();
        zzgu r02 = r0.zzu.zzf();
        if (r02 == null) goto L5;
        r02.zzk().zzb("Service connection failed", r3);
    L5:
        monitor-enter(this);
        this.zzb = false;     // Catch: Throwable -> L11
        this.zzc = null;     // Catch: Throwable -> L11
        monitor-exit(this);     // Catch: Throwable -> L11
        this.zza.zzu.zzaX().zzj(new zzne(this, r3));
        return;
    L11:
        th = move-exception;
        throw th;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient.BaseConnectionCallbacks
    public final void onConnectionSuspended(int r3) {
        zzic r32 = this.zza.zzu;
        r32.zzaX().zzd();
        r32.zzaW().zzj().zza("Service connection suspended");
        r32.zzaX().zzj(new zznb(this));
    }

    @Override // android.content.ServiceConnection
    public final void onServiceConnected(ComponentName r4, IBinder r5) {
        this.zza.zzu.zzaX().zzd();
        monitor-enter(this);
        if (r5 != null) goto L11;
        this.zzb = false;     // Catch: Throwable -> L9
        this.zza.zzu.zzaW().zzb().zza("Service connected with null binder");     // Catch: Throwable -> L9
        monitor-exit(this);     // Catch: Throwable -> L9
        return;
    L11:
        zzgb r0 = null;
        String r1 = r5.getInterfaceDescriptor();     // Catch: Throwable -> L9 RemoteException -> L22
        if ("com.google.android.gms.measurement.internal.IMeasurementService".equals(r1) == false) goto L20;
        IInterface r12 = r5.queryLocalInterface("com.google.android.gms.measurement.internal.IMeasurementService");     // Catch: Throwable -> L9 RemoteException -> L22
        if ((r12 instanceof zzgb) == false) goto L18;
        zzgb r13 = (zzgb) r12;     // Catch: Throwable -> L9 RemoteException -> L22
    L17:
        r0 = r13;
        this.zza.zzu.zzaW().zzk().zza("Bound to IMeasurementService interface");     // Catch: Throwable -> L9 RemoteException -> L22
    L23:
        if (r0 != null) goto L27;
        this.zzb = false;     // Catch: Throwable -> L9
        ConnectionTracker r42 = ConnectionTracker.getInstance();     // Catch: Throwable -> L9 IllegalArgumentException -> L32
        zznl r52 = this.zza;     // Catch: Throwable -> L9 IllegalArgumentException -> L32
        r42.unbindService(r52.zzu.zzaZ(), r52.zzY());     // Catch: Throwable -> L9 IllegalArgumentException -> L32
    L28:
        monitor-exit(this);     // Catch: Throwable -> L9
        return;
    L27:
        this.zza.zzu.zzaX().zzj(new zzmy(this, r0));     // Catch: Throwable -> L9
        goto L28
    L18:
        r13 = new zzfz(r5);     // Catch: Throwable -> L9 RemoteException -> L22
        goto L17
    L20:
        this.zza.zzu.zzaW().zzb().zzb("Got binder with a wrong descriptor", r1);     // Catch: Throwable -> L9 RemoteException -> L22
    L22:
        this.zza.zzu.zzaW().zzb().zza("Service connect failed to get IMeasurementService");     // Catch: Throwable -> L9
    L9:
        th = move-exception;
        throw th;
    }

    @Override // android.content.ServiceConnection
    public final void onServiceDisconnected(ComponentName r4) {
        zzic r0 = this.zza.zzu;
        r0.zzaX().zzd();
        r0.zzaW().zzj().zza("Service disconnected");
        r0.zzaX().zzj(new zzmz(this, r4));
    }

    public final void zza(Intent r6) {
        zznl r0 = this.zza;
        r0.zzg();
        Context r02 = r0.zzu.zzaZ();
        ConnectionTracker r1 = ConnectionTracker.getInstance();
        monitor-enter(this);
        boolean r2 = this.zzb;     // Catch: Throwable -> L10
        zznl r3 = this.zza;
        if (r2 == false) goto L12;
        r3.zzu.zzaW().zzk().zza("Connection attempt already in progress");     // Catch: Throwable -> L10
        monitor-exit(this);     // Catch: Throwable -> L10
        return;
    L12:
        r3.zzu.zzaW().zzk().zza("Using local app measurement service");     // Catch: Throwable -> L10
        this.zzb = true;     // Catch: Throwable -> L10
        r1.bindService(r02, r6, r3.zzY(), 129);     // Catch: Throwable -> L10
        monitor-exit(this);     // Catch: Throwable -> L10
        return;
    L10:
        th = move-exception;
        throw th;
    }

    public final void zzb() {
        if (this.zzc != null) goto L5;
    L9:
        this.zzc = null;
        return;
    L5:
        if (this.zzc.isConnected() == false) goto L7;
    L8:
        this.zzc.disconnect();
        goto L9
    L7:
        if (this.zzc.isConnecting() == false) goto L9;
        goto L8
    }

    public final void zzc() {
        zznl r0 = this.zza;
        r0.zzg();
        Context r02 = r0.zzu.zzaZ();
        monitor-enter(this);
    L9:
        th = move-exception;
        throw th;
    L5:
        if (this.zzb == false) goto L12;
        this.zza.zzu.zzaW().zzk().zza("Connection attempt already in progress");     // Catch: Throwable -> L9
        monitor-exit(this);     // Catch: Throwable -> L9
        return;
    L12:
        if (this.zzc != null) goto L14;
    L20:
        this.zzc = new zzgo(r02, Looper.getMainLooper(), this, this);     // Catch: Throwable -> L9
        this.zza.zzu.zzaW().zzk().zza("Connecting to remote service");     // Catch: Throwable -> L9
        this.zzb = true;     // Catch: Throwable -> L9
        Preconditions.checkNotNull(this.zzc);     // Catch: Throwable -> L9
        this.zzc.checkAvailabilityAndConnect();     // Catch: Throwable -> L9
        monitor-exit(this);     // Catch: Throwable -> L9
        return;
    L14:
        if (this.zzc.isConnecting() == false) goto L16;
    L17:
        this.zza.zzu.zzaW().zzk().zza("Already awaiting connection attempt");     // Catch: Throwable -> L9
        monitor-exit(this);     // Catch: Throwable -> L9
        return;
    L16:
        if (this.zzc.isConnected() == false) goto L20;
        goto L17
    }

    public final /* synthetic */ void zzd(boolean r1) {
        this.zzb = false;
    }
}
