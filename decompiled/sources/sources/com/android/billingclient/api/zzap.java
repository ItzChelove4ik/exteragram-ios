package com.android.billingclient.api;

/* loaded from: classes4.dex */
public final /* synthetic */ class zzap implements Runnable {
    public final /* synthetic */ BillingClientImpl zza;
    public final /* synthetic */ ProductDetailsResponseListener zzb;

    public /* synthetic */ zzap(BillingClientImpl r1, ProductDetailsResponseListener r2) {
        this.zza = r1;
        this.zzb = r2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        BillingClientImpl.zzN(this.zza, this.zzb);
    }
}
