package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.LoginActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class LoginActivity$LoginActivityRecoverView$$ExternalSyntheticLambda1 implements RequestDelegate {
    public final /* synthetic */ LoginActivity.LoginActivityRecoverView f$0;
    public final /* synthetic */ String f$1;

    public /* synthetic */ LoginActivity$LoginActivityRecoverView$$ExternalSyntheticLambda1(LoginActivity.LoginActivityRecoverView r1, String r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r2, TLRPC.TL_error r3) {
        LoginActivity.LoginActivityRecoverView.m15891$r8$lambda$vhrFR_ZginkDgBHbtZUasmcy14(this.f$0, this.f$1, r2, r3);
    }
}
