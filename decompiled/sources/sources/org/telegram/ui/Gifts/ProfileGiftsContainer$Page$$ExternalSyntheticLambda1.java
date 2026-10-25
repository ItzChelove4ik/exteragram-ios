package org.telegram.ui.Gifts;

import android.view.View;
import org.telegram.messenger.Utilities;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Gifts.ProfileGiftsContainer;

/* loaded from: classes7.dex */
public final /* synthetic */ class ProfileGiftsContainer$Page$$ExternalSyntheticLambda1 implements Utilities.Callback5 {
    public final /* synthetic */ ProfileGiftsContainer.Page f$0;

    public /* synthetic */ ProfileGiftsContainer$Page$$ExternalSyntheticLambda1(ProfileGiftsContainer.Page r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.messenger.Utilities.Callback5
    public final void run(Object r1, Object r2, Object r3, Object r4, Object r5) {
        this.f$0.onItemClick((UItem) r1, (View) r2, ((Integer) r3).intValue(), ((Float) r4).floatValue(), ((Float) r5).floatValue());
    }
}
