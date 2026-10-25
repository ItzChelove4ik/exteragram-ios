package org.telegram.messenger;

import org.telegram.messenger.GiftAuctionController;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class GiftAuctionController$$ExternalSyntheticLambda0 implements Utilities.Callback2 {
    public final /* synthetic */ GiftAuctionController f$0;
    public final /* synthetic */ GiftAuctionController.AuctionInternal f$1;
    public final /* synthetic */ Utilities.Callback2 f$2;

    public /* synthetic */ GiftAuctionController$$ExternalSyntheticLambda0(GiftAuctionController r1, GiftAuctionController.AuctionInternal r2, Utilities.Callback2 r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r3, Object r4) {
        GiftAuctionController.m4094$r8$lambda$ePayqhs1GaCU6p7bT6ZhmAlt9g(this.f$0, this.f$1, this.f$2, (TLRPC.payments_PaymentResult) r3, (TLRPC.TL_error) r4);
    }
}
