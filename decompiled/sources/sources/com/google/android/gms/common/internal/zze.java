package com.google.android.gms.common.internal;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;

/* loaded from: classes4.dex */
public final class zze implements ServiceConnection {
    final /* synthetic */ BaseGmsClient zza;
    private final int zzb;

    public zze(BaseGmsClient r1, int r2) {
        java.util.Objects.requireNonNull(r1);
        this.zza = r1;
        this.zzb = r2;
    }

    @Override // android.content.ServiceConnection
    public final void onServiceConnected(ComponentName r4, IBinder r5) {
        BaseGmsClient r42 = this.zza;
        if (r5 != null) goto L6;
        r42.zzf(16);
        return;
    L6:
        Object r0 = r42.zzh();
        monitor-enter(r0);
        IInterface r1 = r5.queryLocalInterface("com.google.android.gms.common.internal.IGmsServiceBroker");     // Catch: Throwable -> L13
        if (r1 != null) goto L11;
    L15:
        IGmsServiceBroker r12 = new zzaa(r5);     // Catch: Throwable -> L13
    L16:
        r42.zzi(r12);     // Catch: Throwable -> L13
        monitor-exit(r0);     // Catch: Throwable -> L13
        this.zza.zzb(0, null, this.zzb);
        return;
    L11:
        if ((r1 instanceof IGmsServiceBroker) == false) goto L15;
        r12 = (IGmsServiceBroker) r1;     // Catch: Throwable -> L13
    L13:
        th = move-exception;
        throw th;
    }

    @Override // android.content.ServiceConnection
    public final void onServiceDisconnected(ComponentName r3) {
        BaseGmsClient r32 = this.zza;
        Object r0 = r32.zzh();
        monitor-enter(r0);
        r32.zzi(null);     // Catch: Throwable -> L9
        monitor-exit(r0);     // Catch: Throwable -> L9
        BaseGmsClient r33 = this.zza;
        int r2 = this.zzb;
        Handler r34 = r33.zzb;
        r34.sendMessage(r34.obtainMessage(6, r2, 1));
        return;
    L9:
        th = move-exception;
        throw th;
    }
}
