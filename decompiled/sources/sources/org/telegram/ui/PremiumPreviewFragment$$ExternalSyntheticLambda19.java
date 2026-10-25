package org.telegram.ui;

import com.android.billingclient.api.BillingFlowParams;
import com.android.billingclient.api.BillingResult;
import java.util.List;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.PremiumPreviewFragment;

/* loaded from: classes6.dex */
public final /* synthetic */ class PremiumPreviewFragment$$ExternalSyntheticLambda19 implements Runnable {
    public final /* synthetic */ BillingResult f$0;
    public final /* synthetic */ BaseFragment f$1;
    public final /* synthetic */ boolean f$2;
    public final /* synthetic */ List f$3;
    public final /* synthetic */ int f$4;
    public final /* synthetic */ BillingFlowParams.SubscriptionUpdateParams f$5;
    public final /* synthetic */ PremiumPreviewFragment.SubscriptionTier f$6;

    public /* synthetic */ PremiumPreviewFragment$$ExternalSyntheticLambda19(BillingResult r1, BaseFragment r2, boolean r3, List r4, int r5, BillingFlowParams.SubscriptionUpdateParams r6, PremiumPreviewFragment.SubscriptionTier r7) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
        this.f$6 = r7;
    }

    @Override // java.lang.Runnable
    public final void run() {
        PremiumPreviewFragment.m17583$r8$lambda$L3NU01o3sPubvPT51xDWyvXzLQ(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6);
    }
}
