package org.telegram.ui.Components.Premium.boosts;

import com.android.billingclient.api.BillingResult;
import java.util.List;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BaseFragment;

/* loaded from: classes7.dex */
public final /* synthetic */ class BoostRepository$$ExternalSyntheticLambda30 implements RequestDelegate {
    public final /* synthetic */ Utilities.Callback f$0;
    public final /* synthetic */ List f$1;
    public final /* synthetic */ BillingResult f$2;
    public final /* synthetic */ Utilities.Callback f$3;
    public final /* synthetic */ BaseFragment f$4;
    public final /* synthetic */ TLRPC.TL_inputStorePaymentPremiumGiftCode f$5;

    public /* synthetic */ BoostRepository$$ExternalSyntheticLambda30(Utilities.Callback r1, List r2, BillingResult r3, Utilities.Callback r4, BaseFragment r5, TLRPC.TL_inputStorePaymentPremiumGiftCode r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r9, TLRPC.TL_error r10) {
        BoostRepository.m12082$r8$lambda$9IAmvtxDgt8WGAwH1j51UxhvQ(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, r9, r10);
    }
}
