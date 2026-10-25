package com.google.android.gms.cloudmessaging;

import android.os.IBinder;
import android.os.RemoteException;

/* loaded from: classes.dex */
final /* synthetic */ class zzk implements Runnable {
    private final /* synthetic */ zzp zza;
    private final /* synthetic */ IBinder zzb;

    public /* synthetic */ zzk(zzp r1, IBinder r2) {
        this.zza = r1;
        this.zzb = r2;
    }

    @Override // java.lang.Runnable
    public final /* synthetic */ void run() {
        IBinder r0 = this.zzb;
        zzp r3 = this.zza;
        monitor-enter(r3);
        if (r0 != null) goto L22;
        r3.zzc(0, "Null service connection");     // Catch: Throwable -> L9
        monitor-exit(r3);     // Catch: Throwable -> L9
        return;
    L22:
        r3.zzc = new zzq(r0);     // Catch: Throwable -> L9 RemoteException -> L16
    L13:
        r3.zza = 2;     // Catch: Throwable -> L9
        r3.zzb();     // Catch: Throwable -> L9
        monitor-exit(r3);     // Catch: Throwable -> L9
        return;
    L16:
        e = move-exception;
        r3.zzc(0, e.getMessage());     // Catch: Throwable -> L9
        return;
    L9:
        th = move-exception;
        throw th;
    }
}
