package org.telegram.ui;

import android.widget.EditText;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;

/* loaded from: classes6.dex */
public final /* synthetic */ class ContactsActivity$$ExternalSyntheticLambda19 implements AlertDialog.OnButtonClickListener {
    public final /* synthetic */ ContactsActivity f$0;
    public final /* synthetic */ TLRPC.User f$1;
    public final /* synthetic */ EditText f$2;

    public /* synthetic */ ContactsActivity$$ExternalSyntheticLambda19(ContactsActivity r1, TLRPC.User r2, EditText r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.ui.ActionBar.AlertDialog.OnButtonClickListener
    public final void onClick(AlertDialog r3, int r4) {
        ContactsActivity.$r8$lambda$mu63MqzroJbiOxbXfWinYfrvdC8(this.f$0, this.f$1, this.f$2, r3, r4);
    }
}
