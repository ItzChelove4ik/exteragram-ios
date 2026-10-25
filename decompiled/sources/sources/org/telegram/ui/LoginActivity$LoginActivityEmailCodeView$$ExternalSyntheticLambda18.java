package org.telegram.ui;

import android.os.Bundle;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.LoginActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class LoginActivity$LoginActivityEmailCodeView$$ExternalSyntheticLambda18 implements Runnable {
    public final /* synthetic */ LoginActivity.LoginActivityEmailCodeView f$0;
    public final /* synthetic */ TLObject f$1;
    public final /* synthetic */ Bundle f$2;
    public final /* synthetic */ TLRPC.TL_error f$3;
    public final /* synthetic */ TLRPC.TL_auth_resendCode f$4;

    public /* synthetic */ LoginActivity$LoginActivityEmailCodeView$$ExternalSyntheticLambda18(LoginActivity.LoginActivityEmailCodeView r1, TLObject r2, Bundle r3, TLRPC.TL_error r4, TLRPC.TL_auth_resendCode r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // java.lang.Runnable
    public final void run() {
        LoginActivity.LoginActivityEmailCodeView.$r8$lambda$KlqXUefPKQuEEULVtLMs5BpEUik(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
