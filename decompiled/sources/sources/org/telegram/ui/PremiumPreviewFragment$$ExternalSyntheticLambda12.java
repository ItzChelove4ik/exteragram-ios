package org.telegram.ui;

import com.android.billingclient.api.BillingFlowParams;
import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.PurchasesResponseListener;
import java.util.List;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.PremiumPreviewFragment;

/* loaded from: classes6.dex */
public final /* synthetic */ class PremiumPreviewFragment$$ExternalSyntheticLambda12 implements PurchasesResponseListener {
    public final /* synthetic */ BaseFragment f$0;
    public final /* synthetic */ boolean f$1;
    public final /* synthetic */ int f$2;
    public final /* synthetic */ BillingFlowParams.SubscriptionUpdateParams f$3;
    public final /* synthetic */ PremiumPreviewFragment.SubscriptionTier f$4;

    public /* synthetic */ PremiumPreviewFragment$$ExternalSyntheticLambda12(BaseFragment r1, boolean r2, int r3, BillingFlowParams.SubscriptionUpdateParams r4, PremiumPreviewFragment.SubscriptionTier r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // com.android.billingclient.api.PurchasesResponseListener
    public final void onQueryPurchasesResponse(BillingResult r8, List r9) {
        PremiumPreviewFragment.m17584$r8$lambda$Qby2DM6ewhhIPl5MPOAlDB2ptE(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, r8, r9);
    }
}
