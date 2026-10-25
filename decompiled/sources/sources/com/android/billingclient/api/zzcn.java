package com.android.billingclient.api;

import android.content.Context;
import com.google.android.datatransport.Encoding;
import com.google.android.datatransport.Event;
import com.google.android.datatransport.Transport;
import com.google.android.datatransport.cct.CCTDestination;
import com.google.android.datatransport.runtime.TransportRuntime;
import com.google.android.gms.internal.play_billing.zzc;
import com.google.android.gms.internal.play_billing.zzji;

/* loaded from: classes.dex */
final class zzcn {
    private boolean zza;
    private Transport zzb;

    public zzcn(Context r5) {
        TransportRuntime.initialize(r5);     // Catch: Throwable -> L5
        this.zzb = TransportRuntime.getInstance().newFactory(CCTDestination.INSTANCE).getTransport("PLAY_BILLING_LIBRARY", zzji.class, Encoding.of("proto"), new zzcm());     // Catch: Throwable -> L5
        return;
    L5:
        this.zza = true;
    }

    public final void zza(zzji r3) {
        if (this.zza == false) goto L10;
        zzc.zzn("BillingLogger", "Skipping logging since initialization failed.");
        return;
    L10:
        this.zzb.send(Event.ofData(r3));     // Catch: Throwable -> L8
        return;
    L8:
        zzc.zzn("BillingLogger", "logging failed.");
    }
}
