package org.telegram.ui.Components.Premium.boosts;

import com.android.billingclient.api.BillingResult;
import java.util.List;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BaseFragment;

/* loaded from: classes7.dex */
public final /* synthetic */ class BoostRepository$$ExternalSyntheticLambda38 implements Runnable {
    public final /* synthetic */ TLRPC.TL_error f$0;
    public final /* synthetic */ Utilities.Callback f$1;
    public final /* synthetic */ TLObject f$2;
    public final /* synthetic */ List f$3;
    public final /* synthetic */ BillingResult f$4;
    public final /* synthetic */ Utilities.Callback f$5;
    public final /* synthetic */ BaseFragment f$6;
    public final /* synthetic */ TLRPC.TL_inputStorePaymentPremiumGiftCode f$7;

    public /* synthetic */ BoostRepository$$ExternalSyntheticLambda38(TLRPC.TL_error r1, Utilities.Callback r2, TLObject r3, List r4, BillingResult r5, Utilities.Callback r6, BaseFragment r7, TLRPC.TL_inputStorePaymentPremiumGiftCode r8) {
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
        BoostRepository.$r8$lambda$7baPAvWLWuDmI8IBz6DwQS52J2g(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7);
    }
}
