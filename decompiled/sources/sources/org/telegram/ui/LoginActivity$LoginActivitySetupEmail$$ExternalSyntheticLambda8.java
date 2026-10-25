package org.telegram.ui;

import android.os.Bundle;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_account;
import org.telegram.ui.LoginActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class LoginActivity$LoginActivitySetupEmail$$ExternalSyntheticLambda8 implements Runnable {
    public final /* synthetic */ LoginActivity.LoginActivitySetupEmail f$0;
    public final /* synthetic */ TLObject f$1;
    public final /* synthetic */ Bundle f$2;
    public final /* synthetic */ TLRPC.TL_error f$3;
    public final /* synthetic */ TL_account.verifyEmail f$4;

    public /* synthetic */ LoginActivity$LoginActivitySetupEmail$$ExternalSyntheticLambda8(LoginActivity.LoginActivitySetupEmail r1, TLObject r2, Bundle r3, TLRPC.TL_error r4, TL_account.verifyEmail r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // java.lang.Runnable
    public final void run() {
        LoginActivity.LoginActivitySetupEmail.$r8$lambda$TIB10ea6oT7KWGOVRzRFO8GminY(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
