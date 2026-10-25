package org.telegram.ui;

import org.telegram.tgnet.TLRPC;
import org.telegram.ui.LoginActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class LoginActivity$LoginActivityResetWaitView$$ExternalSyntheticLambda3 implements Runnable {
    public final /* synthetic */ LoginActivity.LoginActivityResetWaitView f$0;
    public final /* synthetic */ TLRPC.TL_error f$1;

    public /* synthetic */ LoginActivity$LoginActivityResetWaitView$$ExternalSyntheticLambda3(LoginActivity.LoginActivityResetWaitView r1, TLRPC.TL_error r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        LoginActivity.LoginActivityResetWaitView.m15921$r8$lambda$JVkUXcPKUTSfSEZMO3S7KMnXro(this.f$0, this.f$1);
    }
}
