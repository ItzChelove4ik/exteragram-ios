package com.android.billingclient.api;

import androidx.core.util.Consumer;
import java.util.ArrayList;

/* loaded from: classes4.dex */
public final /* synthetic */ class zzbr implements Consumer {
    public final /* synthetic */ ProductDetailsResponseListener zza;

    public /* synthetic */ zzbr(ProductDetailsResponseListener r1) {
        this.zza = r1;
    }

    @Override // androidx.core.util.Consumer
    public final void accept(Object r4) {
        QueryProductDetailsResult r0 = new QueryProductDetailsResult(new ArrayList(), new ArrayList());
        ProductDetailsResponseListener r3 = this.zza;
        r3.onProductDetailsResponse((BillingResult) r4, r0);
    }
}
