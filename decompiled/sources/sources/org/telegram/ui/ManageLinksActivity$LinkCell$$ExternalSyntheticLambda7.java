package org.telegram.ui;

import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ManageLinksActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class ManageLinksActivity$LinkCell$$ExternalSyntheticLambda7 implements AlertDialog.OnButtonClickListener {
    public final /* synthetic */ ManageLinksActivity.LinkCell f$0;
    public final /* synthetic */ TLRPC.TL_chatInviteExported f$1;

    public /* synthetic */ ManageLinksActivity$LinkCell$$ExternalSyntheticLambda7(ManageLinksActivity.LinkCell r1, TLRPC.TL_chatInviteExported r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.ui.ActionBar.AlertDialog.OnButtonClickListener
    public final void onClick(AlertDialog r2, int r3) {
        ManageLinksActivity.LinkCell.$r8$lambda$q0JHAFqGMNNv53UZ8H0yTrFxykM(this.f$0, this.f$1, r2, r3);
    }
}
