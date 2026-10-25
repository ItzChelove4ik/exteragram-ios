package org.telegram.ui;

import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;

/* loaded from: classes6.dex */
public final /* synthetic */ class DialogsActivity$$ExternalSyntheticLambda154 implements AlertDialog.OnButtonClickListener {
    public final /* synthetic */ DialogsActivity f$0;
    public final /* synthetic */ TLRPC.User f$1;

    public /* synthetic */ DialogsActivity$$ExternalSyntheticLambda154(DialogsActivity r1, TLRPC.User r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.ui.ActionBar.AlertDialog.OnButtonClickListener
    public final void onClick(AlertDialog r2, int r3) {
        DialogsActivity.$r8$lambda$_9UyOKwkavViMjS0bUBfk49IOSA(this.f$0, this.f$1, r2, r3);
    }
}
