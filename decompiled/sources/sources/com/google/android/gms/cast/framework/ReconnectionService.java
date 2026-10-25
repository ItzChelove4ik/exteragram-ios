package com.google.android.gms.cast.framework;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.cast.internal.Logger;

/* loaded from: classes4.dex */
public class ReconnectionService extends Service {
    private static final Logger zza = null;
    private zzas zzb;

    static {
        zza = new Logger("ReconnectionService");
    }

    public ReconnectionService() {
    }

    @Override // android.app.Service
    public IBinder onBind(Intent r4) {
        zzas r3 = this.zzb;
        if (r3 != null) goto L9;
    L8:
        return null;
    L9:
        return r3.zzg(r4);
    L6:
        e = move-exception;
        zza.d(e, "Unable to call %s on %s.", new Object[]{"onBind", zzas.class.getSimpleName()});
        goto L8
    }

    @Override // android.app.Service
    public void onCreate() {
        CastContext r0 = CastContext.getSharedInstance(this);
        zzas r02 = com.google.android.gms.internal.cast.zzay.zzd(this, r0.getSessionManager().zzd(), r0.zzc().zza());
        this.zzb = r02;
        if (r02 == null) goto L12;
        r02.zze();     // Catch: RemoteException -> L6
    L8:
        super.onCreate();
        return;
    L6:
        e = move-exception;
        zza.d(e, "Unable to call %s on %s.", new Object[]{"onCreate", zzas.class.getSimpleName()});
        goto L8
    }

    @Override // android.app.Service
    public void onDestroy() {
        zzas r0 = this.zzb;
        if (r0 == null) goto L12;
        r0.zzh();     // Catch: RemoteException -> L6
    L8:
        super.onDestroy();
        return;
    L6:
        e = move-exception;
        zza.d(e, "Unable to call %s on %s.", new Object[]{"onDestroy", zzas.class.getSimpleName()});
        goto L8
    }

    @Override // android.app.Service
    public int onStartCommand(Intent r2, int r3, int r4) {
        zzas r1 = this.zzb;
        if (r1 != null) goto L9;
    L8:
        return 2;
    L9:
        return r1.zzf(r2, r3, r4);
    L6:
        e = move-exception;
        zza.d(e, "Unable to call %s on %s.", new Object[]{"onStartCommand", zzas.class.getSimpleName()});
        goto L8
    }
}
