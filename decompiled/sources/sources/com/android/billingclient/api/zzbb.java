package com.android.billingclient.api;

import com.google.android.gms.internal.play_billing.zzc;

/* loaded from: classes4.dex */
public final /* synthetic */ class zzbb implements Runnable {
    public final /* synthetic */ zzbc zza;
    public final /* synthetic */ BillingResult zzb;

    public /* synthetic */ zzbb(zzbc r1, BillingResult r2) {
        this.zza = r1;
        this.zzb = r2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzbc r0 = this.zza;
        BillingResult r2 = this.zzb;
        BillingClientImpl.zzh(r0.zzb).onBillingSetupFinished(r2);     // Catch: Throwable -> L5
        return;
    L5:
        th = move-exception;
        zzc.zzo("BillingClient", "Exception calling onBillingSetupFinished.", th);
    }
}
