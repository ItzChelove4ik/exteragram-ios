package org.telegram.messenger;

import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.ConsumeResponseListener;
import com.android.billingclient.api.Purchase;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class BillingController$$ExternalSyntheticLambda12 implements ConsumeResponseListener {
    public final /* synthetic */ TLRPC.InputStorePaymentPurpose f$0;
    public final /* synthetic */ Purchase f$1;
    public final /* synthetic */ Runnable f$2;

    public /* synthetic */ BillingController$$ExternalSyntheticLambda12(TLRPC.InputStorePaymentPurpose r1, Purchase r2, Runnable r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // com.android.billingclient.api.ConsumeResponseListener
    public final void onConsumeResponse(BillingResult r3, String r4) {
        BillingController.$r8$lambda$fm6vzwhgSd8IHdkNWkSenXxj2L8(this.f$0, this.f$1, this.f$2, r3, r4);
    }
}
