package org.telegram.ui;

import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_update;
import org.telegram.ui.LoginActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class LoginActivity$LoginPayView$$ExternalSyntheticLambda3 implements Runnable {
    public final /* synthetic */ LoginActivity.LoginPayView f$0;
    public final /* synthetic */ TLRPC.TL_inputStorePaymentAuthCode f$1;
    public final /* synthetic */ TL_update.TL_updateSentPhoneCode f$2;

    public /* synthetic */ LoginActivity$LoginPayView$$ExternalSyntheticLambda3(LoginActivity.LoginPayView r1, TLRPC.TL_inputStorePaymentAuthCode r2, TL_update.TL_updateSentPhoneCode r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        LoginActivity.LoginPayView.$r8$lambda$DVs7mRwM7jtY0AYXACXphxWHTqQ(this.f$0, this.f$1, this.f$2);
    }
}
