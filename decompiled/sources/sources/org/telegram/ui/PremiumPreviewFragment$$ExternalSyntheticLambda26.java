package org.telegram.ui;

import com.android.billingclient.api.BillingFlowParams;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.PremiumPreviewFragment;

/* loaded from: classes6.dex */
public final /* synthetic */ class PremiumPreviewFragment$$ExternalSyntheticLambda26 implements Runnable {
    public final /* synthetic */ TLObject f$0;
    public final /* synthetic */ BaseFragment f$1;
    public final /* synthetic */ TLRPC.TL_inputStorePaymentPremiumSubscription f$2;
    public final /* synthetic */ PremiumPreviewFragment.SubscriptionTier f$3;
    public final /* synthetic */ BillingFlowParams.SubscriptionUpdateParams f$4;
    public final /* synthetic */ int f$5;
    public final /* synthetic */ TLRPC.TL_error f$6;
    public final /* synthetic */ TLRPC.TL_payments_canPurchaseStore f$7;

    public /* synthetic */ PremiumPreviewFragment$$ExternalSyntheticLambda26(TLObject r1, BaseFragment r2, TLRPC.TL_inputStorePaymentPremiumSubscription r3, PremiumPreviewFragment.SubscriptionTier r4, BillingFlowParams.SubscriptionUpdateParams r5, int r6, TLRPC.TL_error r7, TLRPC.TL_payments_canPurchaseStore r8) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
        this.f$6 = r7;
        this.f$7 = r8;
    }

    @Override // java.lang.Runnable
    public final void run() {
        PremiumPreviewFragment.$r8$lambda$qsVx08UW1jm3uI3klLUQ7wtXk0k(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7);
    }
}
