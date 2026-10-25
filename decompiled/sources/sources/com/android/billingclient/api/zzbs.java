package com.android.billingclient.api;

/* loaded from: classes4.dex */
public final /* synthetic */ class zzbs implements Runnable {
    public final /* synthetic */ zzce zza;
    public final /* synthetic */ QueryProductDetailsParams zzb;
    public final /* synthetic */ ProductDetailsResponseListener zzc;

    public /* synthetic */ zzbs(zzce r1, QueryProductDetailsParams r2, ProductDetailsResponseListener r3) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzce.zzao(this.zza, this.zzb, this.zzc);
    }
}
