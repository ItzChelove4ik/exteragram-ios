package org.telegram.ui;

import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ContactsActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class DialogOrContactPickerActivity$$ExternalSyntheticLambda1 implements ContactsActivity.ContactsActivityDelegate {
    public final /* synthetic */ DialogOrContactPickerActivity f$0;

    public /* synthetic */ DialogOrContactPickerActivity$$ExternalSyntheticLambda1(DialogOrContactPickerActivity r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.ui.ContactsActivity.ContactsActivityDelegate
    public final void didSelectContact(TLRPC.User r1, String r2, ContactsActivity r3) {
        DialogOrContactPickerActivity.$r8$lambda$pOoGcMWm6HrAbA6ZwAeeztsd1H0(this.f$0, r1, r2, r3);
    }
}
