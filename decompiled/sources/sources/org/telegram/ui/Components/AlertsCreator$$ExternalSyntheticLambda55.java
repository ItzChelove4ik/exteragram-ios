package org.telegram.ui.Components;

import org.telegram.messenger.MessagesStorage;
import org.telegram.ui.ActionBar.AlertDialog;

/* loaded from: classes7.dex */
public final /* synthetic */ class AlertsCreator$$ExternalSyntheticLambda55 implements AlertDialog.OnButtonClickListener {
    public final /* synthetic */ MessagesStorage.BooleanCallback f$0;
    public final /* synthetic */ boolean[] f$1;

    public /* synthetic */ AlertsCreator$$ExternalSyntheticLambda55(MessagesStorage.BooleanCallback r1, boolean[] r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.ui.ActionBar.AlertDialog.OnButtonClickListener
    public final void onClick(AlertDialog r2, int r3) {
        AlertsCreator.$r8$lambda$yRG220QXrkXUFWJHQK1HM28YZSQ(this.f$0, this.f$1, r2, r3);
    }
}
