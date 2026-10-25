package org.telegram.ui;

import com.android.billingclient.api.BillingFlowParams;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.PremiumPreviewFragment;

/* loaded from: classes6.dex */
public final /* synthetic */ class PremiumPreviewFragment$$ExternalSyntheticLambda24 implements RequestDelegate {
    public final /* synthetic */ BaseFragment f$0;
    public final /* synthetic */ TLRPC.TL_inputStorePaymentPremiumSubscription f$1;
    public final /* synthetic */ PremiumPreviewFragment.SubscriptionTier f$2;
    public final /* synthetic */ BillingFlowParams.SubscriptionUpdateParams f$3;
    public final /* synthetic */ int f$4;
    public final /* synthetic */ TLRPC.TL_payments_canPurchaseStore f$5;

    public /* synthetic */ PremiumPreviewFragment$$ExternalSyntheticLambda24(BaseFragment r1, TLRPC.TL_inputStorePaymentPremiumSubscription r2, PremiumPreviewFragment.SubscriptionTier r3, BillingFlowParams.SubscriptionUpdateParams r4, int r5, TLRPC.TL_payments_canPurchaseStore r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r9, TLRPC.TL_error r10) {
        PremiumPreviewFragment.$r8$lambda$G01WmJm8qyaKHTztBLSZ2z44v0g(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, r9, r10);
    }
}
