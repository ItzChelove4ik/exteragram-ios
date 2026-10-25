package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.LoginActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class LoginActivity$LoginActivityEmailCodeView$$ExternalSyntheticLambda0 implements RequestDelegate {
    public final /* synthetic */ LoginActivity.LoginActivityEmailCodeView f$0;
    public final /* synthetic */ String f$1;

    public /* synthetic */ LoginActivity$LoginActivityEmailCodeView$$ExternalSyntheticLambda0(LoginActivity.LoginActivityEmailCodeView r1, String r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r2, TLRPC.TL_error r3) {
        LoginActivity.LoginActivityEmailCodeView.$r8$lambda$lZPZcj4JdRYueyQZcmU6N9lJtmQ(this.f$0, this.f$1, r2, r3);
    }
}
