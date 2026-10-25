package org.telegram.ui.Gifts;

import org.telegram.tgnet.tl.TL_stars;
import org.telegram.ui.Stars.StarGiftSheet;

/* loaded from: classes7.dex */
public final /* synthetic */ class ResaleGiftsFragment$$ExternalSyntheticLambda32 implements StarGiftSheet.BoughtGiftCallback {
    public final /* synthetic */ ResaleGiftsFragment f$0;

    public /* synthetic */ ResaleGiftsFragment$$ExternalSyntheticLambda32(ResaleGiftsFragment r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.ui.Stars.StarGiftSheet.BoughtGiftCallback
    public final void onBoughtGift(TL_stars.TL_starGiftUnique r1, long r2, boolean r4) {
        ResaleGiftsFragment.$r8$lambda$6N2GBEQBYt0BtCxKxTEjZA55__k(this.f$0, r1, r2, r4);
    }
}
