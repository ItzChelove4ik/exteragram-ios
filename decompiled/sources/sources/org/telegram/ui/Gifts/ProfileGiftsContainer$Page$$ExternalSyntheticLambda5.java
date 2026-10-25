package org.telegram.ui.Gifts;

import org.telegram.tgnet.tl.TL_stars;
import org.telegram.ui.Gifts.ProfileGiftsContainer;
import org.telegram.ui.Stars.StarGiftSheet;

/* loaded from: classes7.dex */
public final /* synthetic */ class ProfileGiftsContainer$Page$$ExternalSyntheticLambda5 implements StarGiftSheet.BoughtGiftCallback {
    public final /* synthetic */ ProfileGiftsContainer.Page f$0;
    public final /* synthetic */ TL_stars.SavedStarGift f$1;

    public /* synthetic */ ProfileGiftsContainer$Page$$ExternalSyntheticLambda5(ProfileGiftsContainer.Page r1, TL_stars.SavedStarGift r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.ui.Stars.StarGiftSheet.BoughtGiftCallback
    public final void onBoughtGift(TL_stars.TL_starGiftUnique r7, long r8, boolean r10) {
        ProfileGiftsContainer.Page.m14945$r8$lambda$YYm4dmUeH1XsfAc6FRssdgIqyo(this.f$0, this.f$1, r7, r8, r10);
    }
}
