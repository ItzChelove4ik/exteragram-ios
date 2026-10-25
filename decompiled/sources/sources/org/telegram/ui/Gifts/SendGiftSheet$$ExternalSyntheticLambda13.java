package org.telegram.ui.Gifts;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public final /* synthetic */ class SendGiftSheet$$ExternalSyntheticLambda13 implements RequestDelegate {
    public final /* synthetic */ SendGiftSheet f$0;
    public final /* synthetic */ TLRPC.TL_inputStorePaymentGiftPremium f$1;
    public final /* synthetic */ TLRPC.TL_payments_canPurchaseStore f$2;

    public /* synthetic */ SendGiftSheet$$ExternalSyntheticLambda13(SendGiftSheet r1, TLRPC.TL_inputStorePaymentGiftPremium r2, TLRPC.TL_payments_canPurchaseStore r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r3, TLRPC.TL_error r4) {
        SendGiftSheet.$r8$lambda$DIzWM4xxni3f72kfrPLRhJVQHIY(this.f$0, this.f$1, this.f$2, r3, r4);
    }
}
