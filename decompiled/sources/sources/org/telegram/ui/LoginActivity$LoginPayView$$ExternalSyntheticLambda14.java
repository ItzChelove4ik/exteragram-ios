package org.telegram.ui;

import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.LoginActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class LoginActivity$LoginPayView$$ExternalSyntheticLambda14 implements Runnable {
    public final /* synthetic */ LoginActivity.LoginPayView f$0;
    public final /* synthetic */ TLObject f$1;
    public final /* synthetic */ TLRPC.TL_inputInvoicePremiumAuthCode f$2;
    public final /* synthetic */ TLRPC.TL_inputStorePaymentAuthCode f$3;
    public final /* synthetic */ TLRPC.TL_error f$4;

    public /* synthetic */ LoginActivity$LoginPayView$$ExternalSyntheticLambda14(LoginActivity.LoginPayView r1, TLObject r2, TLRPC.TL_inputInvoicePremiumAuthCode r3, TLRPC.TL_inputStorePaymentAuthCode r4, TLRPC.TL_error r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // java.lang.Runnable
    public final void run() {
        LoginActivity.LoginPayView.$r8$lambda$WNwNns9BGY9ywNV1JHuMAxTQ_l0(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
