package org.telegram.ui.Gifts;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_stars;
import org.telegram.ui.Gifts.ResaleGiftsFragment;

/* loaded from: classes7.dex */
public final /* synthetic */ class ResaleGiftsFragment$ResaleGiftsList$$ExternalSyntheticLambda0 implements RequestDelegate {
    public final /* synthetic */ ResaleGiftsFragment.ResaleGiftsList f$0;
    public final /* synthetic */ TL_stars.getResaleStarGifts f$1;

    public /* synthetic */ ResaleGiftsFragment$ResaleGiftsList$$ExternalSyntheticLambda0(ResaleGiftsFragment.ResaleGiftsList r1, TL_stars.getResaleStarGifts r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r2, TLRPC.TL_error r3) {
        ResaleGiftsFragment.ResaleGiftsList.m14978$r8$lambda$esF0R8guhoV21LgyUuNNwCOcE(this.f$0, this.f$1, r2, r3);
    }
}
