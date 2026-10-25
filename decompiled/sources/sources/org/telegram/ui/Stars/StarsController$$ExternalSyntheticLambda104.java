package org.telegram.ui.Stars;

import android.app.Activity;
import com.android.billingclient.api.BillingResult;
import java.util.List;
import org.telegram.messenger.BillingController;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_stars;

/* loaded from: classes7.dex */
public final /* synthetic */ class StarsController$$ExternalSyntheticLambda104 implements BillingController.ProductDetailsResponseListenerLegacy {
    public final /* synthetic */ StarsController f$0;
    public final /* synthetic */ Utilities.Callback2 f$1;
    public final /* synthetic */ TLRPC.TL_inputStorePaymentStarsGift f$2;
    public final /* synthetic */ TL_stars.TL_starsGiftOption f$3;
    public final /* synthetic */ Activity f$4;

    public /* synthetic */ StarsController$$ExternalSyntheticLambda104(StarsController r1, Utilities.Callback2 r2, TLRPC.TL_inputStorePaymentStarsGift r3, TL_stars.TL_starsGiftOption r4, Activity r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // org.telegram.messenger.BillingController.ProductDetailsResponseListenerLegacy
    public final void onProductDetailsResponse(BillingResult r8, List r9) {
        StarsController.$r8$lambda$DcBbmFVSJsVnxgiDBFN8kRSx43Q(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, r8, r9);
    }
}
