package org.telegram.ui.Stars;

import android.app.Activity;
import com.android.billingclient.api.BillingResult;
import java.util.List;
import org.telegram.messenger.BillingController;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_stars;

/* loaded from: classes7.dex */
public final /* synthetic */ class StarsController$$ExternalSyntheticLambda63 implements BillingController.ProductDetailsResponseListenerLegacy {
    public final /* synthetic */ Utilities.Callback2 f$0;
    public final /* synthetic */ TLRPC.TL_inputStorePaymentStarsTopup f$1;
    public final /* synthetic */ TL_stars.TL_starsTopupOption f$2;
    public final /* synthetic */ Activity f$3;

    public /* synthetic */ StarsController$$ExternalSyntheticLambda63(Utilities.Callback2 r1, TLRPC.TL_inputStorePaymentStarsTopup r2, TL_stars.TL_starsTopupOption r3, Activity r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.messenger.BillingController.ProductDetailsResponseListenerLegacy
    public final void onProductDetailsResponse(BillingResult r7, List r8) {
        StarsController.$r8$lambda$r4b2cuGA4duXl6gnBi77d8Tcdtw(this.f$0, this.f$1, this.f$2, this.f$3, r7, r8);
    }
}
