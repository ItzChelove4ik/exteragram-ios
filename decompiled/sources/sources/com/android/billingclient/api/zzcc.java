package com.android.billingclient.api;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.google.android.gms.internal.play_billing.zzc;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zzcc implements ServiceConnection {
    final /* synthetic */ zzce zza;

    public /* synthetic */ zzcc(zzce r1, zzcd r2) {
        Objects.requireNonNull(r1);
        this.zza = r1;
    }

    @Override // android.content.ServiceConnection
    public final void onServiceConnected(ComponentName r2, IBinder r3) {
        zzc.zzm("BillingClientTesting", "Billing Override Service connected.");
        zzce r1 = this.zza;
        zzce.zzaq(r1, com.google.android.gms.internal.play_billing.zzat.zzc(r3));
        zzce.zzar(r1, 2);
        zzce.zzax(r1, 26);
    }

    @Override // android.content.ServiceConnection
    public final void onServiceDisconnected(ComponentName r2) {
        zzc.zzn("BillingClientTesting", "Billing Override Service disconnected.");
        zzce r1 = this.zza;
        zzce.zzaq(r1, null);
        zzce.zzar(r1, 0);
    }
}
