package org.telegram.ui.Gifts;

import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public final /* synthetic */ class SendGiftSheet$$ExternalSyntheticLambda16 implements Runnable {
    public final /* synthetic */ SendGiftSheet f$0;
    public final /* synthetic */ TLObject f$1;
    public final /* synthetic */ TLRPC.TL_inputStorePaymentGiftPremium f$2;
    public final /* synthetic */ TLRPC.TL_error f$3;
    public final /* synthetic */ TLRPC.TL_payments_canPurchaseStore f$4;

    public /* synthetic */ SendGiftSheet$$ExternalSyntheticLambda16(SendGiftSheet r1, TLObject r2, TLRPC.TL_inputStorePaymentGiftPremium r3, TLRPC.TL_error r4, TLRPC.TL_payments_canPurchaseStore r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // java.lang.Runnable
    public final void run() {
        SendGiftSheet.m15007$r8$lambda$DZyrL9mmqxOqFEGsXAvmy4Ortw(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
