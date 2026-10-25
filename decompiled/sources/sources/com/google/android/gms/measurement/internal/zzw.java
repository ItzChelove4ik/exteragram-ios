package com.google.android.gms.measurement.internal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.google.android.gms.internal.measurement.zzaif;

/* loaded from: classes5.dex */
public final class zzw extends BroadcastReceiver {
    private final zzic zza;

    public zzw(zzic r1) {
        this.zza = r1;
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context r2, Intent r3) {
        if (r3 != null) goto L5;
        this.zza.zzaW().zze().zza("App receiver called with null intent");
        return;
    L5:
        String r22 = r3.getAction();
        if (r22 != null) goto L9;
        this.zza.zzaW().zze().zza("App receiver called with null action");
        return;
    L9:
        int r32 = r22.hashCode();
        if (r32 == (-1928239649)) goto L19;
        if (r32 == 1279883384) goto L15;
    L25:
        this.zza.zzaW().zze().zza("App receiver called with unknown action");
        return;
    L15:
        if (r22.equals("com.google.android.gms.measurement.BATCHES_AVAILABLE") == false) goto L25;
        zzic r23 = this.zza;
        r23.zzaW().zzk().zza("[sgtm] App Receiver notified batches are available");
        r23.zzaX().zzj(new zzt(this));
        return;
    L19:
        if (r22.equals("com.google.android.gms.measurement.TRIGGERS_AVAILABLE") == false) goto L25;
        zzic r1 = this.zza;
        zzaif.zza();
        if (r1.zzc().zzp(null, zzfy.zzaP) == true) goto L23;
        return;
    L23:
        r1.zzaW().zzk().zza("App receiver notified triggers are available");
        r1.zzaX().zzj(new zzu(r1));
    }

    public final /* synthetic */ void zza() {
        this.zza.zzx().zzh(((Long) zzfy.zzC.zzb(null)).longValue());
    }
}
