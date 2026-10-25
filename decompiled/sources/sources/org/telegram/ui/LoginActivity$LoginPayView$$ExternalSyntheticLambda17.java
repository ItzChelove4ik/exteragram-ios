package org.telegram.ui;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.LoginActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class LoginActivity$LoginPayView$$ExternalSyntheticLambda17 implements Utilities.Callback {
    public final /* synthetic */ LoginActivity.LoginPayView f$0;
    public final /* synthetic */ TLRPC.TL_inputStorePaymentAuthCode f$1;
    public final /* synthetic */ TLRPC.PaymentForm f$2;

    public /* synthetic */ LoginActivity$LoginPayView$$ExternalSyntheticLambda17(LoginActivity.LoginPayView r1, TLRPC.TL_inputStorePaymentAuthCode r2, TLRPC.PaymentForm r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r3) {
        LoginActivity.LoginPayView.$r8$lambda$wDUFaR2M3bHW6YlYsgwyD4FRAaU(this.f$0, this.f$1, this.f$2, (TLRPC.payments_PaymentResult) r3);
    }
}
