package org.telegram.messenger;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_payments;

/* loaded from: classes5.dex */
public final /* synthetic */ class GiftAuctionController$$ExternalSyntheticLambda10 implements Utilities.Callback2 {
    public final /* synthetic */ GiftAuctionController f$0;
    public final /* synthetic */ Utilities.Callback2 f$1;
    public final /* synthetic */ long f$2;

    public /* synthetic */ GiftAuctionController$$ExternalSyntheticLambda10(GiftAuctionController r1, Utilities.Callback2 r2, long r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r7, Object r8) {
        GiftAuctionController.$r8$lambda$yReGjVA4j1TyXMyDRd9DvOg38us(this.f$0, this.f$1, this.f$2, (TL_payments.TL_StarGiftAuctionState) r7, (TLRPC.TL_error) r8);
    }
}
