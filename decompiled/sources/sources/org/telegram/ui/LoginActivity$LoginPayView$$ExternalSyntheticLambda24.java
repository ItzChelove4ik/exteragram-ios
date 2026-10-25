package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.LoginActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class LoginActivity$LoginPayView$$ExternalSyntheticLambda24 implements RequestDelegate {
    public final /* synthetic */ LoginActivity.LoginPayView f$0;

    public /* synthetic */ LoginActivity$LoginPayView$$ExternalSyntheticLambda24(LoginActivity.LoginPayView r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r1, TLRPC.TL_error r2) {
        LoginActivity.LoginPayView.m15960$r8$lambda$DnI5Y6tQUVF4tQuev2YVroc8M(this.f$0, r1, r2);
    }
}
