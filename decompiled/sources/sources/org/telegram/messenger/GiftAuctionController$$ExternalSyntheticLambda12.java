package org.telegram.messenger;

import org.telegram.messenger.GiftAuctionController;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class GiftAuctionController$$ExternalSyntheticLambda12 implements Utilities.Callback2 {
    public final /* synthetic */ GiftAuctionController f$0;
    public final /* synthetic */ Utilities.Callback2 f$1;
    public final /* synthetic */ GiftAuctionController.AuctionInternal f$2;
    public final /* synthetic */ TLRPC.TL_payments_getPaymentForm f$3;

    public /* synthetic */ GiftAuctionController$$ExternalSyntheticLambda12(GiftAuctionController r1, Utilities.Callback2 r2, GiftAuctionController.AuctionInternal r3, TLRPC.TL_payments_getPaymentForm r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r7, Object r8) {
        GiftAuctionController.$r8$lambda$wf99L_PWDnScf1dEP1pMauQrPBI(this.f$0, this.f$1, this.f$2, this.f$3, (TLRPC.PaymentForm) r7, (TLRPC.TL_error) r8);
    }
}
