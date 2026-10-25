package org.telegram.ui;

import org.telegram.tgnet.TLRPC;
import org.telegram.ui.LoginActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class LoginActivity$LoginActivityNewPasswordView$$ExternalSyntheticLambda5 implements Runnable {
    public final /* synthetic */ LoginActivity.LoginActivityNewPasswordView f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ String f$2;
    public final /* synthetic */ TLRPC.TL_auth_recoverPassword f$3;

    public /* synthetic */ LoginActivity$LoginActivityNewPasswordView$$ExternalSyntheticLambda5(LoginActivity.LoginActivityNewPasswordView r1, String r2, String r3, TLRPC.TL_auth_recoverPassword r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        LoginActivity.LoginActivityNewPasswordView.m15855$r8$lambda$6VfXLw4byZaxdVlV0ocDkd70gM(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
