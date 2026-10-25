package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.LoginActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class LoginActivity$LoginActivitySmsView$$ExternalSyntheticLambda8 implements RequestDelegate {
    public final /* synthetic */ LoginActivity.LoginActivitySmsView f$0;

    public /* synthetic */ LoginActivity$LoginActivitySmsView$$ExternalSyntheticLambda8(LoginActivity.LoginActivitySmsView r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r1, TLRPC.TL_error r2) {
        LoginActivity.LoginActivitySmsView.$r8$lambda$Oph6KOQ39wvtd1ScisTul2yI4Pk(this.f$0, r1, r2);
    }
}
