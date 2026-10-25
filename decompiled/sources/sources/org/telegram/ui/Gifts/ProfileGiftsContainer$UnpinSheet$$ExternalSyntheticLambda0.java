package org.telegram.ui.Gifts;

import java.util.ArrayList;
import org.telegram.messenger.Utilities;
import org.telegram.ui.Components.UniversalAdapter;
import org.telegram.ui.Gifts.ProfileGiftsContainer;
import org.telegram.ui.Stars.StarsController;

/* loaded from: classes7.dex */
public final /* synthetic */ class ProfileGiftsContainer$UnpinSheet$$ExternalSyntheticLambda0 implements Utilities.Callback2 {
    public final /* synthetic */ ProfileGiftsContainer.UnpinSheet f$0;
    public final /* synthetic */ StarsController.GiftsList f$1;

    public /* synthetic */ ProfileGiftsContainer$UnpinSheet$$ExternalSyntheticLambda0(ProfileGiftsContainer.UnpinSheet r1, StarsController.GiftsList r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r2, Object r3) {
        ProfileGiftsContainer.UnpinSheet.$r8$lambda$FXBTuScgBzl6Pr4J46noBekenNs(this.f$0, this.f$1, (ArrayList) r2, (UniversalAdapter) r3);
    }
}
