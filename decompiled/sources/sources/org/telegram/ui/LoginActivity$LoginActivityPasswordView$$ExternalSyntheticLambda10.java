package org.telegram.ui;

import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.LoginActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class LoginActivity$LoginActivityPasswordView$$ExternalSyntheticLambda10 implements AlertDialog.OnButtonClickListener {
    public final /* synthetic */ LoginActivity.LoginActivityPasswordView f$0;
    public final /* synthetic */ TLRPC.TL_auth_passwordRecovery f$1;

    public /* synthetic */ LoginActivity$LoginActivityPasswordView$$ExternalSyntheticLambda10(LoginActivity.LoginActivityPasswordView r1, TLRPC.TL_auth_passwordRecovery r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.ui.ActionBar.AlertDialog.OnButtonClickListener
    public final void onClick(AlertDialog r2, int r3) {
        LoginActivity.LoginActivityPasswordView.$r8$lambda$3y5MvEhd94JnbAm7fxKDwdHcYIw(this.f$0, this.f$1, r2, r3);
    }
}
