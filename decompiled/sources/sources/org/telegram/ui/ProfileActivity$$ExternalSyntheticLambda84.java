package org.telegram.ui;

import java.util.ArrayList;
import org.telegram.messenger.MessagesController;
import org.telegram.ui.Components.FiltersListBottomSheet;

/* loaded from: classes6.dex */
public final /* synthetic */ class ProfileActivity$$ExternalSyntheticLambda84 implements FiltersListBottomSheet.FiltersListBottomSheetDelegate {
    public final /* synthetic */ ProfileActivity f$0;
    public final /* synthetic */ ArrayList f$1;

    public /* synthetic */ ProfileActivity$$ExternalSyntheticLambda84(ProfileActivity r1, ArrayList r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.ui.Components.FiltersListBottomSheet.FiltersListBottomSheetDelegate
    public final void didSelectFilter(MessagesController.DialogFilter r2, boolean r3) {
        ProfileActivity.$r8$lambda$M8WzbBo52ydR9oxLE6K6Hxip9eA(this.f$0, this.f$1, r2, r3);
    }
}
