package com.android.billingclient.api;

import java.util.concurrent.Callable;

/* loaded from: classes4.dex */
public final /* synthetic */ class zzx implements Callable {
    public final /* synthetic */ BillingClientImpl zza;
    public final /* synthetic */ String zzb;
    public final /* synthetic */ String zzc;

    public /* synthetic */ zzx(BillingClientImpl r1, String r2, String r3) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
    }

    @Override // java.util.concurrent.Callable
    public final Object call() {
        return BillingClientImpl.zzd(this.zza, this.zzb, this.zzc);
    }
}
