package com.android.billingclient.api;

import androidx.core.util.Consumer;

/* loaded from: classes4.dex */
public final /* synthetic */ class zzby implements Consumer {
    public final /* synthetic */ zzce zza;

    public /* synthetic */ zzby(zzce r1) {
        this.zza = r1;
    }

    @Override // androidx.core.util.Consumer
    public final void accept(Object r1) {
        zzce.zzap(this.zza, (BillingResult) r1);
    }
}
