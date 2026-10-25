package org.telegram.ui;

import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;

/* loaded from: classes6.dex */
public final /* synthetic */ class ContactsActivity$$ExternalSyntheticLambda18 implements AlertDialog.OnButtonClickListener {
    public final /* synthetic */ ContactsActivity f$0;
    public final /* synthetic */ TLRPC.User f$1;
    public final /* synthetic */ String f$2;

    public /* synthetic */ ContactsActivity$$ExternalSyntheticLambda18(ContactsActivity r1, TLRPC.User r2, String r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.ui.ActionBar.AlertDialog.OnButtonClickListener
    public final void onClick(AlertDialog r3, int r4) {
        ContactsActivity.$r8$lambda$TXk57Rtq5qgMLLjMPYDRNhSVdkM(this.f$0, this.f$1, this.f$2, r3, r4);
    }
}
