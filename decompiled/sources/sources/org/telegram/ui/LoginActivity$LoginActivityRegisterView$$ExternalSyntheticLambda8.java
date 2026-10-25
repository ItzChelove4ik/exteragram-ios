package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.LoginActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class LoginActivity$LoginActivityRegisterView$$ExternalSyntheticLambda8 implements RequestDelegate {
    public final /* synthetic */ LoginActivity.LoginActivityRegisterView f$0;

    public /* synthetic */ LoginActivity$LoginActivityRegisterView$$ExternalSyntheticLambda8(LoginActivity.LoginActivityRegisterView r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r1, TLRPC.TL_error r2) {
        LoginActivity.LoginActivityRegisterView.$r8$lambda$1lrm5CJCAeKjTtaiHcaHt14gnvs(this.f$0, r1, r2);
    }
}
