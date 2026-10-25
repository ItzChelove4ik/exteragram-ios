package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.LoginActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class LoginActivity$LoginActivityPhraseView$$ExternalSyntheticLambda14 implements RequestDelegate {
    public final /* synthetic */ LoginActivity.LoginActivityPhraseView f$0;
    public final /* synthetic */ TLRPC.TL_auth_signIn f$1;

    public /* synthetic */ LoginActivity$LoginActivityPhraseView$$ExternalSyntheticLambda14(LoginActivity.LoginActivityPhraseView r1, TLRPC.TL_auth_signIn r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r2, TLRPC.TL_error r3) {
        LoginActivity.LoginActivityPhraseView.$r8$lambda$NY9F9NzTQ8S35cpfN4WRg1pCrTo(this.f$0, this.f$1, r2, r3);
    }
}
