package org.telegram.ui.Gifts;

import android.view.View;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.tl.TL_stars;
import org.telegram.ui.Gifts.ProfileGiftsContainer;
import org.telegram.ui.Stars.StarsController;

/* loaded from: classes7.dex */
public final /* synthetic */ class ProfileGiftsContainer$UnpinSheet$$ExternalSyntheticLambda2 implements View.OnClickListener {
    public final /* synthetic */ ProfileGiftsContainer.UnpinSheet f$0;
    public final /* synthetic */ StarsController.GiftsList f$1;
    public final /* synthetic */ TL_stars.SavedStarGift f$2;
    public final /* synthetic */ Utilities.Callback0Return f$3;

    public /* synthetic */ ProfileGiftsContainer$UnpinSheet$$ExternalSyntheticLambda2(ProfileGiftsContainer.UnpinSheet r1, StarsController.GiftsList r2, TL_stars.SavedStarGift r3, Utilities.Callback0Return r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View r4) {
        ProfileGiftsContainer.UnpinSheet.$r8$lambda$dYFPZBO4wu3QSRLMJEf3TstmYmc(this.f$0, this.f$1, this.f$2, this.f$3, r4);
    }
}
