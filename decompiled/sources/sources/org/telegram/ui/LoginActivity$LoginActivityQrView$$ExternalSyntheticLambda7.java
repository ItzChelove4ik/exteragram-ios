package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.LoginActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class LoginActivity$LoginActivityQrView$$ExternalSyntheticLambda7 implements RequestDelegate {
    public final /* synthetic */ LoginActivity.LoginActivityQrView f$0;

    public /* synthetic */ LoginActivity$LoginActivityQrView$$ExternalSyntheticLambda7(LoginActivity.LoginActivityQrView r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r1, TLRPC.TL_error r2) {
        LoginActivity.LoginActivityQrView.$r8$lambda$dWUracvDOGpWfTar08GCjjXvWOQ(this.f$0, r1, r2);
    }
}
