package com.android.billingclient.api;

import com.google.android.gms.internal.play_billing.zzc;

/* loaded from: classes4.dex */
public final /* synthetic */ class zzba implements Runnable {
    public final /* synthetic */ zzbc zza;

    public /* synthetic */ zzba(zzbc r1) {
        this.zza = r1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        BillingClientImpl.zzh(this.zza.zzb).onBillingServiceDisconnected();     // Catch: Throwable -> L5
        return;
    L5:
        th = move-exception;
        zzc.zzo("BillingClient", "Exception calling onBillingServiceDisconnected.", th);
    }
}
