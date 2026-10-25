package org.telegram.ui.Business;

import android.widget.TextView;
import org.telegram.messenger.Utilities;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.Business.QuickRepliesController;
import org.telegram.ui.Components.EditTextBoldCursor;

/* loaded from: classes6.dex */
public final /* synthetic */ class QuickRepliesActivity$$ExternalSyntheticLambda2 implements AlertDialog.OnButtonClickListener {
    public final /* synthetic */ EditTextBoldCursor f$0;
    public final /* synthetic */ Utilities.Callback f$1;
    public final /* synthetic */ int f$2;
    public final /* synthetic */ QuickRepliesController.QuickReply f$3;
    public final /* synthetic */ TextView f$4;
    public final /* synthetic */ Utilities.Callback f$5;

    public /* synthetic */ QuickRepliesActivity$$ExternalSyntheticLambda2(EditTextBoldCursor r1, Utilities.Callback r2, int r3, QuickRepliesController.QuickReply r4, TextView r5, Utilities.Callback r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
    }

    @Override // org.telegram.ui.ActionBar.AlertDialog.OnButtonClickListener
    public final void onClick(AlertDialog r9, int r10) {
        QuickRepliesActivity.$r8$lambda$0pRxDBuy8ztEkr1uoTVgdmpUhUM(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, r9, r10);
    }
}
