package com.android.billingclient.api;

import java.util.concurrent.Callable;

/* loaded from: classes4.dex */
public final /* synthetic */ class zzal implements Callable {
    public final /* synthetic */ BillingClientImpl zza;
    public final /* synthetic */ ProductDetailsResponseListener zzb;
    public final /* synthetic */ QueryProductDetailsParams zzc;

    public /* synthetic */ zzal(BillingClientImpl r1, ProductDetailsResponseListener r2, QueryProductDetailsParams r3) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
    }

    @Override // java.util.concurrent.Callable
    public final Object call() {
        BillingClientImpl.zzs(this.zza, this.zzb, this.zzc);
        return null;
    }
}
