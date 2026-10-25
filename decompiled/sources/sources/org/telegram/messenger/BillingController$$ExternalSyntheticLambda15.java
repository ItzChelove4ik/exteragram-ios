package org.telegram.messenger;

import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.ConsumeResponseListener;
import com.android.billingclient.api.Purchase;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes5.dex */
public final /* synthetic */ class BillingController$$ExternalSyntheticLambda15 implements ConsumeResponseListener {
    public final /* synthetic */ Purchase f$0;
    public final /* synthetic */ List f$1;
    public final /* synthetic */ String f$2;
    public final /* synthetic */ AtomicInteger f$3;
    public final /* synthetic */ Runnable f$4;

    public /* synthetic */ BillingController$$ExternalSyntheticLambda15(Purchase r1, List r2, String r3, AtomicInteger r4, Runnable r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // com.android.billingclient.api.ConsumeResponseListener
    public final void onConsumeResponse(BillingResult r8, String r9) {
        BillingController.m3894$r8$lambda$Kl1L3qkMizzBY16StFyINnCgbs(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, r8, r9);
    }
}
