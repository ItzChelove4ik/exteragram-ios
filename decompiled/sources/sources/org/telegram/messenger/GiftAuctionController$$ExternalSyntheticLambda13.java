package org.telegram.messenger;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_payments;

/* loaded from: classes5.dex */
public final /* synthetic */ class GiftAuctionController$$ExternalSyntheticLambda13 implements Utilities.Callback2 {
    public final /* synthetic */ GiftAuctionController f$0;

    public /* synthetic */ GiftAuctionController$$ExternalSyntheticLambda13(GiftAuctionController r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r1, Object r2) {
        GiftAuctionController.m4093$r8$lambda$dnq_zUJa2huaIKew7G6facGuTM(this.f$0, (TL_payments.StarGiftActiveAuctions) r1, (TLRPC.TL_error) r2);
    }
}
