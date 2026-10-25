package org.telegram.ui.Gifts;

import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.tl.TL_stars;
import org.telegram.ui.Gifts.ResaleGiftsFragment;

/* loaded from: classes7.dex */
public final /* synthetic */ class ResaleGiftsFragment$ResaleGiftsList$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ ResaleGiftsFragment.ResaleGiftsList f$0;
    public final /* synthetic */ TLObject f$1;
    public final /* synthetic */ TL_stars.getResaleStarGifts f$2;

    public /* synthetic */ ResaleGiftsFragment$ResaleGiftsList$$ExternalSyntheticLambda1(ResaleGiftsFragment.ResaleGiftsList r1, TLObject r2, TL_stars.getResaleStarGifts r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ResaleGiftsFragment.ResaleGiftsList.m14979$r8$lambda$juxFmajv_LqUZGslBhKrCmgT2s(this.f$0, this.f$1, this.f$2);
    }
}
