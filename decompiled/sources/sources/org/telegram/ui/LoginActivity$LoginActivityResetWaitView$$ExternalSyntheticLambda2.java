package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.LoginActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class LoginActivity$LoginActivityResetWaitView$$ExternalSyntheticLambda2 implements RequestDelegate {
    public final /* synthetic */ LoginActivity.LoginActivityResetWaitView f$0;

    public /* synthetic */ LoginActivity$LoginActivityResetWaitView$$ExternalSyntheticLambda2(LoginActivity.LoginActivityResetWaitView r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r1, TLRPC.TL_error r2) {
        LoginActivity.LoginActivityResetWaitView.$r8$lambda$nKpKjSAUUkK377eTCOfL7Uuu7ZU(this.f$0, r1, r2);
    }
}
