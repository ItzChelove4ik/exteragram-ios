package org.telegram.ui;

import org.telegram.messenger.MessagesController;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.FiltersSetupActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class FiltersSetupActivity$ListAdapter$$ExternalSyntheticLambda7 implements AlertDialog.OnButtonClickListener {
    public final /* synthetic */ FiltersSetupActivity.ListAdapter f$0;
    public final /* synthetic */ MessagesController.DialogFilter f$1;

    public /* synthetic */ FiltersSetupActivity$ListAdapter$$ExternalSyntheticLambda7(FiltersSetupActivity.ListAdapter r1, MessagesController.DialogFilter r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.ui.ActionBar.AlertDialog.OnButtonClickListener
    public final void onClick(AlertDialog r2, int r3) {
        FiltersSetupActivity.ListAdapter.$r8$lambda$xkfU7xJgcUNjA_GpwPyJvGaQC6s(this.f$0, this.f$1, r2, r3);
    }
}
