package org.telegram.ui.Stars;

import android.app.Activity;
import com.android.billingclient.api.BillingResult;
import java.util.List;
import org.telegram.messenger.BillingController;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public final /* synthetic */ class StarsController$$ExternalSyntheticLambda84 implements BillingController.ProductDetailsResponseListenerLegacy {
    public final /* synthetic */ StarsController f$0;
    public final /* synthetic */ Utilities.Callback2 f$1;
    public final /* synthetic */ TLRPC.TL_inputStorePaymentStarsGiveaway f$2;
    public final /* synthetic */ Activity f$3;

    public /* synthetic */ StarsController$$ExternalSyntheticLambda84(StarsController r1, Utilities.Callback2 r2, TLRPC.TL_inputStorePaymentStarsGiveaway r3, Activity r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.messenger.BillingController.ProductDetailsResponseListenerLegacy
    public final void onProductDetailsResponse(BillingResult r7, List r8) {
        StarsController.$r8$lambda$OPmEMTqK6ThW5eY8TyhdjP8ULNI(this.f$0, this.f$1, this.f$2, this.f$3, r7, r8);
    }
}
