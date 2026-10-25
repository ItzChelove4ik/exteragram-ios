package org.telegram.messenger;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_payments;

/* loaded from: classes5.dex */
public final /* synthetic */ class GiftAuctionController$$ExternalSyntheticLambda8 implements Utilities.Callback2 {
    public final /* synthetic */ GiftAuctionController f$0;
    public final /* synthetic */ long f$1;

    public /* synthetic */ GiftAuctionController$$ExternalSyntheticLambda8(GiftAuctionController r1, long r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r4, Object r5) {
        GiftAuctionController.$r8$lambda$2pOWcDgpd2k3Tk8iB4hI6ogb71Y(this.f$0, this.f$1, (TL_payments.TL_StarGiftAuctionState) r4, (TLRPC.TL_error) r5);
    }
}
