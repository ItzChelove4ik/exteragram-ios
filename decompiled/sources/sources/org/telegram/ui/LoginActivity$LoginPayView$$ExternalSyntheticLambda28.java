package org.telegram.ui;

import com.android.billingclient.api.ProductDetails;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.LoginActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class LoginActivity$LoginPayView$$ExternalSyntheticLambda28 implements Runnable {
    public final /* synthetic */ LoginActivity.LoginPayView f$0;
    public final /* synthetic */ ProductDetails f$1;
    public final /* synthetic */ Utilities.Callback f$2;
    public final /* synthetic */ TLRPC.TL_inputStorePaymentAuthCode f$3;

    public /* synthetic */ LoginActivity$LoginPayView$$ExternalSyntheticLambda28(LoginActivity.LoginPayView r1, ProductDetails r2, Utilities.Callback r3, TLRPC.TL_inputStorePaymentAuthCode r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        LoginActivity.LoginPayView.$r8$lambda$Quqyw9begn4vFn3x_kRODI7Rb64(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
