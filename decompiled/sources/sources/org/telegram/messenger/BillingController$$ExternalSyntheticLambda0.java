package org.telegram.messenger;

import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.ProductDetailsResponseListener;
import com.android.billingclient.api.QueryProductDetailsResult;
import org.telegram.messenger.BillingController;

/* loaded from: classes5.dex */
public final /* synthetic */ class BillingController$$ExternalSyntheticLambda0 implements ProductDetailsResponseListener {
    public final /* synthetic */ BillingController.ProductDetailsResponseListenerLegacy f$0;

    public /* synthetic */ BillingController$$ExternalSyntheticLambda0(BillingController.ProductDetailsResponseListenerLegacy r1) {
        this.f$0 = r1;
    }

    @Override // com.android.billingclient.api.ProductDetailsResponseListener
    public final void onProductDetailsResponse(BillingResult r1, QueryProductDetailsResult r2) {
        BillingController.m3893$r8$lambda$EhN0Ji9MOdfwSA_8tZH6GDJHwk(this.f$0, r1, r2);
    }
}
