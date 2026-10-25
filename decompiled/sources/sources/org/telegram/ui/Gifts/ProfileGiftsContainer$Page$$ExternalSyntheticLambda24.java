package org.telegram.ui.Gifts;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.tl.TL_stars;
import org.telegram.ui.Gifts.ProfileGiftsContainer;

/* loaded from: classes7.dex */
public final /* synthetic */ class ProfileGiftsContainer$Page$$ExternalSyntheticLambda24 implements Utilities.Callback {
    public final /* synthetic */ ProfileGiftsContainer.Page f$0;
    public final /* synthetic */ TL_stars.SavedStarGift f$1;

    public /* synthetic */ ProfileGiftsContainer$Page$$ExternalSyntheticLambda24(ProfileGiftsContainer.Page r1, TL_stars.SavedStarGift r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r2) {
        ProfileGiftsContainer.Page.m14950$r8$lambda$wkNf8HwGIwWQpJnlVIQQwYTfDk(this.f$0, this.f$1, (String) r2);
    }
}
