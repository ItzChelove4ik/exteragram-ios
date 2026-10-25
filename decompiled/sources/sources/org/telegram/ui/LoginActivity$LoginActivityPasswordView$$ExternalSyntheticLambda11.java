package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.LoginActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class LoginActivity$LoginActivityPasswordView$$ExternalSyntheticLambda11 implements RequestDelegate {
    public final /* synthetic */ LoginActivity.LoginActivityPasswordView f$0;

    public /* synthetic */ LoginActivity$LoginActivityPasswordView$$ExternalSyntheticLambda11(LoginActivity.LoginActivityPasswordView r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r1, TLRPC.TL_error r2) {
        LoginActivity.LoginActivityPasswordView.$r8$lambda$Ys7BG20cEjYxymI5nmCCvbIVWxM(this.f$0, r1, r2);
    }
}
