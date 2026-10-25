package com.google.android.gms.cast;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.google.android.gms.cast.CastRemoteDisplayLocalService;
import com.google.android.gms.cast.internal.Logger;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.stats.ConnectionTracker;

/* loaded from: classes4.dex */
final class zzad implements ServiceConnection {
    final /* synthetic */ String zza;
    final /* synthetic */ CastDevice zzb;
    final /* synthetic */ CastRemoteDisplayLocalService.Options zzc;
    final /* synthetic */ CastRemoteDisplayLocalService.NotificationSettings zzd;
    final /* synthetic */ Context zze;
    final /* synthetic */ CastRemoteDisplayLocalService.Callbacks zzf;

    public zzad(String r1, CastDevice r2, CastRemoteDisplayLocalService.Options r3, CastRemoteDisplayLocalService.NotificationSettings r4, Context r5, CastRemoteDisplayLocalService.Callbacks r6) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
        this.zzd = r4;
        this.zze = r5;
        this.zzf = r6;
    }

    @Override // android.content.ServiceConnection
    public final void onServiceConnected(ComponentName r9, IBinder r10) {
        CastRemoteDisplayLocalService r0 = ((zzaj) r10).zza;
        String r1 = this.zza;
        CastDevice r2 = this.zzb;
        CastRemoteDisplayLocalService.Options r3 = this.zzc;
        CastRemoteDisplayLocalService.NotificationSettings r4 = this.zzd;
        Context r5 = this.zze;
        CastRemoteDisplayLocalService.Callbacks r7 = this.zzf;
        if (r0.zze(r1, r2, r3, r4, r5, this, r7) == true) goto L11;
        CastRemoteDisplayLocalService.zzi().e("Connected but unable to get the service instance", new Object[0]);
        r7.onRemoteDisplaySessionError(new Status(CastStatusCodes.ERROR_SERVICE_CREATION_FAILED));
        CastRemoteDisplayLocalService.zzk().set(false);
        ConnectionTracker.getInstance().unbindService(r5, this);     // Catch: IllegalArgumentException -> L7
        return;
    L7:
        Logger r92 = CastRemoteDisplayLocalService.zzi();
        r92.d("No need to unbind service, already unbound", new Object[0]);
        return;
    }

    @Override // android.content.ServiceConnection
    public final void onServiceDisconnected(ComponentName r4) {
        CastRemoteDisplayLocalService.zzi().d("onServiceDisconnected", new Object[0]);
        Status r42 = new Status(CastStatusCodes.ERROR_SERVICE_DISCONNECTED, "Service Disconnected");
        this.zzf.onRemoteDisplaySessionError(r42);
        CastRemoteDisplayLocalService.zzk().set(false);
        ConnectionTracker.getInstance().unbindService(this.zze, this);     // Catch: IllegalArgumentException -> L5
        return;
    L5:
        Logger r43 = CastRemoteDisplayLocalService.zzi();
        r43.d("No need to unbind service, already unbound", new Object[0]);
    }
}
