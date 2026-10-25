package com.android.billingclient.api;

import android.app.Activity;
import java.util.concurrent.Callable;

/* loaded from: classes4.dex */
public final /* synthetic */ class zzbz implements Callable {
    public final /* synthetic */ zzce zza;
    public final /* synthetic */ Activity zzb;
    public final /* synthetic */ BillingFlowParams zzc;

    public /* synthetic */ zzbz(zzce r1, Activity r2, BillingFlowParams r3) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
    }

    @Override // java.util.concurrent.Callable
    public final Object call() {
        return zzce.zzal(this.zza, this.zzb, this.zzc);
    }
}
