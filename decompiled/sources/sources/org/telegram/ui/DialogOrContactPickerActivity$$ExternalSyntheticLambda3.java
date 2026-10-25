package org.telegram.ui;

import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;

/* loaded from: classes6.dex */
public final /* synthetic */ class DialogOrContactPickerActivity$$ExternalSyntheticLambda3 implements AlertDialog.OnButtonClickListener {
    public final /* synthetic */ DialogOrContactPickerActivity f$0;
    public final /* synthetic */ TLRPC.User f$1;

    public /* synthetic */ DialogOrContactPickerActivity$$ExternalSyntheticLambda3(DialogOrContactPickerActivity r1, TLRPC.User r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.ui.ActionBar.AlertDialog.OnButtonClickListener
    public final void onClick(AlertDialog r2, int r3) {
        DialogOrContactPickerActivity.m14314$r8$lambda$mAl6c87Jm_tZ6xrSa4D54wnYhU(this.f$0, this.f$1, r2, r3);
    }
}
