package com.android.billingclient.api;

import java.util.concurrent.Callable;

/* loaded from: classes4.dex */
public final /* synthetic */ class zzak implements Callable {
    public final /* synthetic */ BillingClientImpl zza;
    public final /* synthetic */ ConsumeResponseListener zzb;
    public final /* synthetic */ ConsumeParams zzc;

    public /* synthetic */ zzak(BillingClientImpl r1, ConsumeResponseListener r2, ConsumeParams r3) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
    }

    @Override // java.util.concurrent.Callable
    public final Object call() {
        BillingClientImpl.zzr(this.zza, this.zzb, this.zzc);
        return null;
    }
}
