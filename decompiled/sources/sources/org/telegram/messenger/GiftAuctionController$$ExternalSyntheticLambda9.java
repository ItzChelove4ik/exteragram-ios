package org.telegram.messenger;

import org.telegram.messenger.GiftAuctionController;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_payments;

/* loaded from: classes5.dex */
public final /* synthetic */ class GiftAuctionController$$ExternalSyntheticLambda9 implements Utilities.Callback2 {
    public final /* synthetic */ GiftAuctionController f$0;
    public final /* synthetic */ Utilities.Callback f$1;
    public final /* synthetic */ GiftAuctionController.AuctionInternal f$2;

    public /* synthetic */ GiftAuctionController$$ExternalSyntheticLambda9(GiftAuctionController r1, Utilities.Callback r2, GiftAuctionController.AuctionInternal r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r3, Object r4) {
        GiftAuctionController.$r8$lambda$57B5lgs5bt_KT3ZK3IumFIz34Ig(this.f$0, this.f$1, this.f$2, (TL_payments.TL_StarGiftAuctionAcquiredGifts) r3, (TLRPC.TL_error) r4);
    }
}
