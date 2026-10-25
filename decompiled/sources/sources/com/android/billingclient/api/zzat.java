package com.android.billingclient.api;

/* loaded from: classes4.dex */
public final /* synthetic */ class zzat implements Runnable {
    public final /* synthetic */ BillingClientImpl zza;
    public final /* synthetic */ PurchasesResponseListener zzb;

    public /* synthetic */ zzat(BillingClientImpl r1, PurchasesResponseListener r2) {
        this.zza = r1;
        this.zzb = r2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        BillingClientImpl.zzI(this.zza, this.zzb);
    }
}
