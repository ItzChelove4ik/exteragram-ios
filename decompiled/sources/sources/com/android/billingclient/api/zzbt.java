package com.android.billingclient.api;

import androidx.core.util.Consumer;

/* loaded from: classes4.dex */
public final /* synthetic */ class zzbt implements Consumer {
    public final /* synthetic */ ConsumeResponseListener zza;
    public final /* synthetic */ ConsumeParams zzb;

    public /* synthetic */ zzbt(ConsumeResponseListener r1, ConsumeParams r2) {
        this.zza = r1;
        this.zzb = r2;
    }

    @Override // androidx.core.util.Consumer
    public final void accept(Object r2) {
        this.zza.onConsumeResponse((BillingResult) r2, this.zzb.getPurchaseToken());
    }
}
