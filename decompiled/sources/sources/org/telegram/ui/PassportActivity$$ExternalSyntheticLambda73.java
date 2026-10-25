package org.telegram.ui;

import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;

/* loaded from: classes6.dex */
public final /* synthetic */ class PassportActivity$$ExternalSyntheticLambda73 implements AlertDialog.OnButtonClickListener {
    public final /* synthetic */ PassportActivity f$0;
    public final /* synthetic */ TLRPC.TL_auth_passwordRecovery f$1;

    public /* synthetic */ PassportActivity$$ExternalSyntheticLambda73(PassportActivity r1, TLRPC.TL_auth_passwordRecovery r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.ui.ActionBar.AlertDialog.OnButtonClickListener
    public final void onClick(AlertDialog r2, int r3) {
        PassportActivity.m16393$r8$lambda$0P0T3OLbaloQc0yRrVSn0EdYM4(this.f$0, this.f$1, r2, r3);
    }
}
