package org.telegram.ui;

import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;

/* loaded from: classes6.dex */
public final /* synthetic */ class ProfileActivity$$ExternalSyntheticLambda162 implements AlertDialog.OnButtonClickListener {
    public final /* synthetic */ ProfileActivity f$0;
    public final /* synthetic */ TLRPC.UserFull f$1;

    public /* synthetic */ ProfileActivity$$ExternalSyntheticLambda162(ProfileActivity r1, TLRPC.UserFull r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.ui.ActionBar.AlertDialog.OnButtonClickListener
    public final void onClick(AlertDialog r2, int r3) {
        ProfileActivity.m17835$r8$lambda$oKLnrQW1Ek5qbEthGPpNLqM6ZU(this.f$0, this.f$1, r2, r3);
    }
}
