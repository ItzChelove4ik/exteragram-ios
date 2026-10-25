package org.telegram.ui.Components.Premium.boosts;

import com.android.billingclient.api.BillingResult;
import java.util.List;
import org.telegram.messenger.BillingController;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public final /* synthetic */ class BoostRepository$$ExternalSyntheticLambda11 implements BillingController.ProductDetailsResponseListenerLegacy {
    public final /* synthetic */ List f$0;
    public final /* synthetic */ TLRPC.Chat f$1;
    public final /* synthetic */ int f$2;
    public final /* synthetic */ Utilities.Callback f$3;

    public /* synthetic */ BoostRepository$$ExternalSyntheticLambda11(List r1, TLRPC.Chat r2, int r3, Utilities.Callback r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.messenger.BillingController.ProductDetailsResponseListenerLegacy
    public final void onProductDetailsResponse(BillingResult r7, List r8) {
        BoostRepository.$r8$lambda$dvQtAQnEVi0_YaDGkdHmWyjCbOE(this.f$0, this.f$1, this.f$2, this.f$3, r7, r8);
    }
}
