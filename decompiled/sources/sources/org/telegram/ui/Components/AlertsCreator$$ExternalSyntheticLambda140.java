package org.telegram.ui.Components;

import org.telegram.messenger.Utilities;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.Cells.CheckBoxCell;

/* loaded from: classes7.dex */
public final /* synthetic */ class AlertsCreator$$ExternalSyntheticLambda140 implements AlertDialog.OnButtonClickListener {
    public final /* synthetic */ Utilities.Callback2 f$0;
    public final /* synthetic */ CheckBoxCell f$1;
    public final /* synthetic */ AlertDialog[] f$2;

    public /* synthetic */ AlertsCreator$$ExternalSyntheticLambda140(Utilities.Callback2 r1, CheckBoxCell r2, AlertDialog[] r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.ui.ActionBar.AlertDialog.OnButtonClickListener
    public final void onClick(AlertDialog r3, int r4) {
        AlertsCreator.$r8$lambda$MSxnwsmqBQLnTqouFS7kXjhlKXY(this.f$0, this.f$1, this.f$2, r3, r4);
    }
}
