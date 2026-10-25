package org.telegram.ui;

import android.os.Bundle;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_account;
import org.telegram.ui.LoginActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class LoginActivity$LoginActivitySetupEmail$$ExternalSyntheticLambda7 implements Runnable {
    public final /* synthetic */ LoginActivity.LoginActivitySetupEmail f$0;
    public final /* synthetic */ TLObject f$1;
    public final /* synthetic */ Bundle f$2;
    public final /* synthetic */ TLRPC.TL_error f$3;
    public final /* synthetic */ TL_account.sendVerifyEmailCode f$4;

    public /* synthetic */ LoginActivity$LoginActivitySetupEmail$$ExternalSyntheticLambda7(LoginActivity.LoginActivitySetupEmail r1, TLObject r2, Bundle r3, TLRPC.TL_error r4, TL_account.sendVerifyEmailCode r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // java.lang.Runnable
    public final void run() {
        LoginActivity.LoginActivitySetupEmail.$r8$lambda$oiEKE2v09Sd3ZPT5uw8FRmeo3jQ(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
