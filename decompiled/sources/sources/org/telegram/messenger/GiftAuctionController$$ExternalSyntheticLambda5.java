package org.telegram.messenger;

import java.util.ArrayList;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.tl.TL_payments;

/* loaded from: classes5.dex */
public final /* synthetic */ class GiftAuctionController$$ExternalSyntheticLambda5 implements Utilities.Callback {
    public final /* synthetic */ GiftAuctionController f$0;
    public final /* synthetic */ long f$1;
    public final /* synthetic */ TL_payments.TL_StarGiftAuctionState f$2;

    public /* synthetic */ GiftAuctionController$$ExternalSyntheticLambda5(GiftAuctionController r1, long r2, TL_payments.TL_StarGiftAuctionState r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r4;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r4) {
        GiftAuctionController.$r8$lambda$z0VZehep859EzyJgCG3137xF44k(this.f$0, this.f$1, this.f$2, (ArrayList) r4);
    }
}
