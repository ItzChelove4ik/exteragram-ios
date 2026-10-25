package org.telegram.ui.Components;

import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;

/* loaded from: classes7.dex */
public final /* synthetic */ class SearchTagsList$$ExternalSyntheticLambda5 implements AlertDialog.OnButtonClickListener {
    public final /* synthetic */ EditTextBoldCursor f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ TLRPC.Reaction f$2;

    public /* synthetic */ SearchTagsList$$ExternalSyntheticLambda5(EditTextBoldCursor r1, int r2, TLRPC.Reaction r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.ui.ActionBar.AlertDialog.OnButtonClickListener
    public final void onClick(AlertDialog r3, int r4) {
        SearchTagsList.$r8$lambda$4wvGjXhRjmG_WOflWr5b0apO6zw(this.f$0, this.f$1, this.f$2, r3, r4);
    }
}
