package org.telegram.ui.Components;

import org.telegram.messenger.MessagesStorage;
import org.telegram.ui.ActionBar.AlertDialog;

/* loaded from: classes7.dex */
public final /* synthetic */ class AlertsCreator$$ExternalSyntheticLambda95 implements AlertDialog.OnButtonClickListener {
    public final /* synthetic */ int[] f$0;
    public final /* synthetic */ MessagesStorage.IntCallback f$1;

    public /* synthetic */ AlertsCreator$$ExternalSyntheticLambda95(int[] r1, MessagesStorage.IntCallback r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.ui.ActionBar.AlertDialog.OnButtonClickListener
    public final void onClick(AlertDialog r2, int r3) {
        AlertsCreator.$r8$lambda$UUjKrJ6ZjUf0_T77LY4OzU4PxIk(this.f$0, this.f$1, r2, r3);
    }
}
