package org.telegram.ui.iv;

import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.Components.EditTextBoldCursor;
import org.telegram.ui.iv.RichEditorListView;

/* loaded from: classes7.dex */
public final /* synthetic */ class RichInlineButtonEditor$$ExternalSyntheticLambda13 implements AlertDialog.OnButtonClickListener {
    public final /* synthetic */ boolean f$0;
    public final /* synthetic */ Runnable f$1;
    public final /* synthetic */ EditTextBoldCursor f$2;
    public final /* synthetic */ RichEditorListView.BlockButtonEdit f$3;

    public /* synthetic */ RichInlineButtonEditor$$ExternalSyntheticLambda13(boolean r1, Runnable r2, EditTextBoldCursor r3, RichEditorListView.BlockButtonEdit r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.ui.ActionBar.AlertDialog.OnButtonClickListener
    public final void onClick(AlertDialog r7, int r8) {
        RichInlineButtonEditor.$r8$lambda$agvEds9tSky5DHnVmGKbHvOwxzo(this.f$0, this.f$1, this.f$2, this.f$3, r7, r8);
    }
}
