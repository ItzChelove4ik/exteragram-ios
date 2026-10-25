package org.telegram.ui;

import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;

/* loaded from: classes6.dex */
public final /* synthetic */ class ContactAddActivity$$ExternalSyntheticLambda12 implements AlertDialog.OnButtonClickListener {
    public final /* synthetic */ ContactAddActivity f$0;
    public final /* synthetic */ TLRPC.User f$1;

    public /* synthetic */ ContactAddActivity$$ExternalSyntheticLambda12(ContactAddActivity r1, TLRPC.User r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.ui.ActionBar.AlertDialog.OnButtonClickListener
    public final void onClick(AlertDialog r2, int r3) {
        ContactAddActivity.$r8$lambda$PXF9akA3dPK4_3lVMQcU3Q8_k2k(this.f$0, this.f$1, r2, r3);
    }
}
