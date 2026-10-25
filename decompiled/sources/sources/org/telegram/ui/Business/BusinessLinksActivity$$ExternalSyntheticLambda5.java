package org.telegram.ui.Business;

import org.telegram.tgnet.tl.TL_account;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.Components.EditTextBoldCursor;

/* loaded from: classes6.dex */
public final /* synthetic */ class BusinessLinksActivity$$ExternalSyntheticLambda5 implements AlertDialog.OnButtonClickListener {
    public final /* synthetic */ EditTextBoldCursor f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ TL_account.TL_businessChatLink f$2;

    public /* synthetic */ BusinessLinksActivity$$ExternalSyntheticLambda5(EditTextBoldCursor r1, int r2, TL_account.TL_businessChatLink r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.ui.ActionBar.AlertDialog.OnButtonClickListener
    public final void onClick(AlertDialog r3, int r4) {
        BusinessLinksActivity.$r8$lambda$cxMI8a8s6tOBqxsSlMOAHU39TGw(this.f$0, this.f$1, this.f$2, r3, r4);
    }
}
