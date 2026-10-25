package org.telegram.ui;

import android.os.Bundle;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_account;
import org.telegram.ui.LoginActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class LoginActivity$LoginActivitySetupEmail$$ExternalSyntheticLambda1 implements RequestDelegate {
    public final /* synthetic */ LoginActivity.LoginActivitySetupEmail f$0;
    public final /* synthetic */ Bundle f$1;
    public final /* synthetic */ TL_account.verifyEmail f$2;

    public /* synthetic */ LoginActivity$LoginActivitySetupEmail$$ExternalSyntheticLambda1(LoginActivity.LoginActivitySetupEmail r1, Bundle r2, TL_account.verifyEmail r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r3, TLRPC.TL_error r4) {
        LoginActivity.LoginActivitySetupEmail.m15924$r8$lambda$2P2RcbSwuO3MplRPAVbfZlPN9s(this.f$0, this.f$1, this.f$2, r3, r4);
    }
}
