package org.telegram.messenger;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_payments;

/* loaded from: classes5.dex */
public final /* synthetic */ class GiftAuctionController$$ExternalSyntheticLambda7 implements Utilities.Callback2 {
    public final /* synthetic */ GiftAuctionController f$0;
    public final /* synthetic */ Utilities.Callback2 f$1;

    public /* synthetic */ GiftAuctionController$$ExternalSyntheticLambda7(GiftAuctionController r1, Utilities.Callback2 r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r2, Object r3) {
        GiftAuctionController.$r8$lambda$UvTc60NBLKyfkrPxAvHgx2Yibkk(this.f$0, this.f$1, (TL_payments.TL_StarGiftAuctionState) r2, (TLRPC.TL_error) r3);
    }
}
