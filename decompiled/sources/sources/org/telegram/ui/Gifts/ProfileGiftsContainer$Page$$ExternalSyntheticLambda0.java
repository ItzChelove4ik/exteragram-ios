package org.telegram.ui.Gifts;

import java.util.ArrayList;
import org.telegram.messenger.Utilities;
import org.telegram.ui.Components.UniversalAdapter;
import org.telegram.ui.Gifts.ProfileGiftsContainer;

/* loaded from: classes7.dex */
public final /* synthetic */ class ProfileGiftsContainer$Page$$ExternalSyntheticLambda0 implements Utilities.Callback2 {
    public final /* synthetic */ ProfileGiftsContainer.Page f$0;

    public /* synthetic */ ProfileGiftsContainer$Page$$ExternalSyntheticLambda0(ProfileGiftsContainer.Page r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r1, Object r2) {
        this.f$0.fillItems((ArrayList) r1, (UniversalAdapter) r2);
    }
}
