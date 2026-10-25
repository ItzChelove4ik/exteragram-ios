package com.google.android.gms.measurement.internal;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import java.util.Objects;

/* loaded from: classes5.dex */
public final class zzhj implements ServiceConnection {
    final /* synthetic */ zzhk zza;
    private final String zzb;

    public zzhj(zzhk r1, String r2) {
        Objects.requireNonNull(r1);
        this.zza = r1;
        this.zzb = r2;
    }

    @Override // android.content.ServiceConnection
    public final void onServiceConnected(ComponentName r3, IBinder r4) {
        if (r4 != null) goto L16;
        this.zza.zza.zzaW().zze().zza("Install Referrer connection returned with null binder");
        return;
    L16:
        com.google.android.gms.internal.measurement.zzbs r32 = com.google.android.gms.internal.measurement.zzbr.zzb(r4);     // Catch: RuntimeException -> L8
        zzhk r42 = this.zza;
        if (r32 != null) goto L10;
        r42.zza.zzaW().zze().zza("Install Referrer Service implementation was not found");     // Catch: RuntimeException -> L8
        return;
    L10:
        zzic r43 = r42.zza;     // Catch: RuntimeException -> L8
        r43.zzaW().zzk().zza("Install Referrer Service connected");     // Catch: RuntimeException -> L8
        r43.zzaX().zzj(new zzhi(this, r32, this));     // Catch: RuntimeException -> L8
        return;
    L8:
        e = move-exception;
        this.zza.zza.zzaW().zze().zzb("Exception occurred while calling Install Referrer API", e);
    }

    @Override // android.content.ServiceConnection
    public final void onServiceDisconnected(ComponentName r1) {
        this.zza.zza.zzaW().zzk().zza("Install Referrer Service disconnected");
    }

    public final /* synthetic */ String zza() {
        return this.zzb;
    }
}
