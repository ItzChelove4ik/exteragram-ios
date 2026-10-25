package org.telegram.ui.Components;

import android.widget.EditText;
import org.telegram.messenger.Utilities;
import org.telegram.ui.ActionBar.AlertDialog;

/* loaded from: classes7.dex */
public final /* synthetic */ class AlertsCreator$$ExternalSyntheticLambda194 implements AlertDialog.OnButtonClickListener {
    public final /* synthetic */ Utilities.Callback f$0;
    public final /* synthetic */ EditText f$1;

    public /* synthetic */ AlertsCreator$$ExternalSyntheticLambda194(Utilities.Callback r1, EditText r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.ui.ActionBar.AlertDialog.OnButtonClickListener
    public final void onClick(AlertDialog r2, int r3) {
        AlertsCreator.$r8$lambda$8RQ9EXD23RcWTImIAoCrrgBN7RE(this.f$0, this.f$1, r2, r3);
    }
}
