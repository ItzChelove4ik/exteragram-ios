package org.telegram.ui.Components;

import org.telegram.messenger.MessagesStorage;
import org.telegram.ui.ActionBar.AlertDialog;

/* loaded from: classes7.dex */
public final /* synthetic */ class AlertsCreator$$ExternalSyntheticLambda201 implements AlertDialog.OnButtonClickListener {
    public final /* synthetic */ EditTextBoldCursor f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ MessagesStorage.StringCallback f$2;

    public /* synthetic */ AlertsCreator$$ExternalSyntheticLambda201(EditTextBoldCursor r1, int r2, MessagesStorage.StringCallback r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.ui.ActionBar.AlertDialog.OnButtonClickListener
    public final void onClick(AlertDialog r3, int r4) {
        AlertsCreator.$r8$lambda$VsuoOj6LgHZM26KtLRCL7eTiDlo(this.f$0, this.f$1, this.f$2, r3, r4);
    }
}
