package com.android.billingclient.api;

/* loaded from: classes4.dex */
public final /* synthetic */ class zzac implements Runnable {
    public final /* synthetic */ BillingClientImpl zza;
    public final /* synthetic */ BillingResult zzb;

    public /* synthetic */ zzac(BillingClientImpl r1, BillingResult r2) {
        this.zza = r1;
        this.zzb = r2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        BillingClientImpl.zzP(this.zza, this.zzb);
    }
}
