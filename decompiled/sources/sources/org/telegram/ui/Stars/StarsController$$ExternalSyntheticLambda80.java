package org.telegram.ui.Stars;

import com.android.billingclient.api.BillingResult;
import java.util.ArrayList;
import java.util.List;
import org.telegram.messenger.BillingController;

/* loaded from: classes7.dex */
public final /* synthetic */ class StarsController$$ExternalSyntheticLambda80 implements BillingController.ProductDetailsResponseListenerLegacy {
    public final /* synthetic */ StarsController f$0;
    public final /* synthetic */ ArrayList f$1;

    public /* synthetic */ StarsController$$ExternalSyntheticLambda80(StarsController r1, ArrayList r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.BillingController.ProductDetailsResponseListenerLegacy
    public final void onProductDetailsResponse(BillingResult r2, List r3) {
        StarsController.$r8$lambda$K6rkSGrubSEbX_i6BgFNyH2BZTk(this.f$0, this.f$1, r2, r3);
    }
}
