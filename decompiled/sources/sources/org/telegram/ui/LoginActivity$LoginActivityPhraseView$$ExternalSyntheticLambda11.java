package org.telegram.ui;

import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.LoginActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class LoginActivity$LoginActivityPhraseView$$ExternalSyntheticLambda11 implements Runnable {
    public final /* synthetic */ LoginActivity.LoginActivityPhraseView f$0;
    public final /* synthetic */ TLRPC.TL_error f$1;
    public final /* synthetic */ TLObject f$2;
    public final /* synthetic */ TLRPC.TL_auth_signIn f$3;

    public /* synthetic */ LoginActivity$LoginActivityPhraseView$$ExternalSyntheticLambda11(LoginActivity.LoginActivityPhraseView r1, TLRPC.TL_error r2, TLObject r3, TLRPC.TL_auth_signIn r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        LoginActivity.LoginActivityPhraseView.$r8$lambda$G8OzuNBOcbjTbevXmhFQGIubAzk(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
