package org.telegram.ui;

import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.LoginActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class LoginActivity$LoginActivityQrView$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ LoginActivity.LoginActivityQrView f$0;
    public final /* synthetic */ TLRPC.TL_error f$1;
    public final /* synthetic */ TLObject f$2;

    public /* synthetic */ LoginActivity$LoginActivityQrView$$ExternalSyntheticLambda1(LoginActivity.LoginActivityQrView r1, TLRPC.TL_error r2, TLObject r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        LoginActivity.LoginActivityQrView.$r8$lambda$Wgw8WCSeiSx3tRSKLGCAKtVf0iE(this.f$0, this.f$1, this.f$2);
    }
}
