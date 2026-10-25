package org.telegram.ui.Gifts;

import org.telegram.tgnet.tl.TL_stars;
import org.telegram.ui.Gifts.GiftSheet;
import org.telegram.ui.Gifts.ProfileGiftsContainer;

/* loaded from: classes7.dex */
public final /* synthetic */ class ProfileGiftsContainer$Page$$ExternalSyntheticLambda8 implements Runnable {
    public final /* synthetic */ ProfileGiftsContainer.Page f$0;
    public final /* synthetic */ TL_stars.SavedStarGift f$1;
    public final /* synthetic */ GiftSheet.GiftCell f$2;

    public /* synthetic */ ProfileGiftsContainer$Page$$ExternalSyntheticLambda8(ProfileGiftsContainer.Page r1, TL_stars.SavedStarGift r2, GiftSheet.GiftCell r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ProfileGiftsContainer.Page.$r8$lambda$4BKIWhrnVJc8pF61m5XQj5n9mFw(this.f$0, this.f$1, this.f$2);
    }
}
