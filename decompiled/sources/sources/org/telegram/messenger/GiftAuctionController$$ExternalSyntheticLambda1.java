package org.telegram.messenger;

import java.util.ArrayList;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_payments;

/* loaded from: classes5.dex */
public final /* synthetic */ class GiftAuctionController$$ExternalSyntheticLambda1 implements Utilities.Callback {
    public final /* synthetic */ GiftAuctionController f$0;
    public final /* synthetic */ TL_payments.TL_StarGiftAuctionState f$1;
    public final /* synthetic */ Utilities.Callback2 f$2;
    public final /* synthetic */ TLRPC.TL_error f$3;

    public /* synthetic */ GiftAuctionController$$ExternalSyntheticLambda1(GiftAuctionController r1, TL_payments.TL_StarGiftAuctionState r2, Utilities.Callback2 r3, TLRPC.TL_error r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r4) {
        GiftAuctionController.$r8$lambda$S9ujSQRJ3bvbpq6TMQQgGf4_ne4(this.f$0, this.f$1, this.f$2, this.f$3, (ArrayList) r4);
    }
}
