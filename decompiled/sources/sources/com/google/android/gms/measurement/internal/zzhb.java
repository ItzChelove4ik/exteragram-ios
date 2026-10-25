package com.google.android.gms.measurement.internal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.google.android.gms.common.internal.Preconditions;

/* loaded from: classes5.dex */
final class zzhb extends BroadcastReceiver {
    private final zzpg zza;
    private boolean zzb;
    private boolean zzc;

    static {
    }

    public zzhb(zzpg r1) {
        Preconditions.checkNotNull(r1);
        this.zza = r1;
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context r3, Intent r4) {
        zzpg r32 = this.zza;
        r32.zzu();
        String r42 = r4.getAction();
        r32.zzaW().zzk().zzb("NetworkBroadcastReceiver received action", r42);
        if ("android.net.conn.CONNECTIVITY_CHANGE".equals(r42) == false) goto L8;
        boolean r43 = r32.zzi().zzb();
        if (this.zzc == r43) goto L10;
        this.zzc = r43;
        r32.zzaX().zzj(new zzha(this, r43));
        return;
    L10:
        return;
    L8:
        r32.zzaW().zze().zzb("NetworkBroadcastReceiver received unknown action", r42);
    }

    public final void zza() {
        zzpg r0 = this.zza;
        r0.zzu();
        r0.zzaX().zzg();
        if (this.zzb == false) goto L5;
        return;
    L5:
        r0.zzaZ().registerReceiver(this, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
        this.zzc = r0.zzi().zzb();
        r0.zzaW().zzk().zzb("Registering connectivity change receiver. Network connected", Boolean.valueOf(this.zzc));
        this.zzb = true;
    }

    public final void zzb() {
        zzpg r0 = this.zza;
        r0.zzu();
        r0.zzaX().zzg();
        r0.zzaX().zzg();
        if (this.zzb == true) goto L5;
        return;
    L5:
        r0.zzaW().zzk().zza("Unregistering connectivity change receiver");
        this.zzb = false;
        this.zzc = false;
        r0.zzaZ().unregisterReceiver(this);     // Catch: IllegalArgumentException -> L8
        return;
    L8:
        e = move-exception;
        this.zza.zzaW().zzb().zzb("Failed to unregister the network broadcast receiver", e);
    }

    public final /* synthetic */ zzpg zzc() {
        return this.zza;
    }
}
