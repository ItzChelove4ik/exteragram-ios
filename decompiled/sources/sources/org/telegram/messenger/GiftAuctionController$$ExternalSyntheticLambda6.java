package org.telegram.messenger;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_stars;

/* loaded from: classes5.dex */
public final /* synthetic */ class GiftAuctionController$$ExternalSyntheticLambda6 implements Utilities.Callback2 {
    public final /* synthetic */ Utilities.Callback f$0;

    public /* synthetic */ GiftAuctionController$$ExternalSyntheticLambda6(Utilities.Callback r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r1, Object r2) {
        GiftAuctionController.$r8$lambda$m0WTiozIdKGkc19Ify0S1ASljAE(this.f$0, (TL_stars.starGiftUpgradeAttributes) r1, (TLRPC.TL_error) r2);
    }
}
