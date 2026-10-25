package org.telegram.ui;

import org.telegram.messenger.MessagesController;
import org.telegram.ui.Components.FiltersListBottomSheet;
import org.telegram.ui.DialogsActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class DialogsActivity$12$$ExternalSyntheticLambda2 implements FiltersListBottomSheet.FiltersListBottomSheetDelegate {
    public final /* synthetic */ DialogsActivity.AnonymousClass12 f$0;

    public /* synthetic */ DialogsActivity$12$$ExternalSyntheticLambda2(DialogsActivity.AnonymousClass12 r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.ui.Components.FiltersListBottomSheet.FiltersListBottomSheetDelegate
    public final void didSelectFilter(MessagesController.DialogFilter r1, boolean r2) {
        DialogsActivity.AnonymousClass12.$r8$lambda$FAPqxq7xtFo4hsyjf6ExHyvbhnA(this.f$0, r1, r2);
    }
}
