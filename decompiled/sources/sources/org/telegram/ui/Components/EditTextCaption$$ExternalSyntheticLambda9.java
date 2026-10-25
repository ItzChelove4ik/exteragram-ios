package org.telegram.ui.Components;

import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.Components.EditTextCaption;

/* loaded from: classes7.dex */
public final /* synthetic */ class EditTextCaption$$ExternalSyntheticLambda9 implements AlertDialog.OnButtonClickListener {
    public final /* synthetic */ EditTextCaption.InputDialogCallback f$0;
    public final /* synthetic */ EditTextBoldCursor f$1;

    public /* synthetic */ EditTextCaption$$ExternalSyntheticLambda9(EditTextCaption.InputDialogCallback r1, EditTextBoldCursor r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.ui.ActionBar.AlertDialog.OnButtonClickListener
    public final void onClick(AlertDialog r2, int r3) {
        EditTextCaption.$r8$lambda$1swod7mxhoES81vqde73vPHv6NU(this.f$0, this.f$1, r2, r3);
    }
}
