package org.telegram.messenger;

import android.app.Activity;
import com.android.billingclient.api.BillingFlowParams;
import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.PurchasesResponseListener;
import java.util.List;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class BillingController$$ExternalSyntheticLambda13 implements PurchasesResponseListener {
    public final /* synthetic */ BillingController f$0;
    public final /* synthetic */ Activity f$1;
    public final /* synthetic */ AccountInstance f$2;
    public final /* synthetic */ TLRPC.InputStorePaymentPurpose f$3;
    public final /* synthetic */ List f$4;
    public final /* synthetic */ BillingFlowParams.SubscriptionUpdateParams f$5;

    public /* synthetic */ BillingController$$ExternalSyntheticLambda13(BillingController r1, Activity r2, AccountInstance r3, TLRPC.InputStorePaymentPurpose r4, List r5, BillingFlowParams.SubscriptionUpdateParams r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
    }

    @Override // com.android.billingclient.api.PurchasesResponseListener
    public final void onQueryPurchasesResponse(BillingResult r9, List r10) {
        BillingController.$r8$lambda$ki18f08mA4CzVhS_YfQCrLrODws(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, r9, r10);
    }
}
