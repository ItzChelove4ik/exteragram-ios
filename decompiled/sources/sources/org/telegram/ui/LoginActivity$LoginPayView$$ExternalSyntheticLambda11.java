package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.LoginActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class LoginActivity$LoginPayView$$ExternalSyntheticLambda11 implements RequestDelegate {
    public final /* synthetic */ LoginActivity.LoginPayView f$0;
    public final /* synthetic */ TLRPC.TL_inputInvoicePremiumAuthCode f$1;
    public final /* synthetic */ TLRPC.TL_inputStorePaymentAuthCode f$2;

    public /* synthetic */ LoginActivity$LoginPayView$$ExternalSyntheticLambda11(LoginActivity.LoginPayView r1, TLRPC.TL_inputInvoicePremiumAuthCode r2, TLRPC.TL_inputStorePaymentAuthCode r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r3, TLRPC.TL_error r4) {
        LoginActivity.LoginPayView.$r8$lambda$ULXezlBtNDiqJvp4h9L48xPptWA(this.f$0, this.f$1, this.f$2, r3, r4);
    }
}
