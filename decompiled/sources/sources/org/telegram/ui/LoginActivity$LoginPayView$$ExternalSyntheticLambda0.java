package org.telegram.ui;

import com.android.billingclient.api.Purchase;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.LoginActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class LoginActivity$LoginPayView$$ExternalSyntheticLambda0 implements RequestDelegate {
    public final /* synthetic */ LoginActivity.LoginPayView f$0;
    public final /* synthetic */ TLRPC.TL_inputStorePaymentAuthCode f$1;
    public final /* synthetic */ Purchase f$2;
    public final /* synthetic */ TLRPC.TL_payments_canPurchaseStore f$3;
    public final /* synthetic */ Runnable f$4;

    public /* synthetic */ LoginActivity$LoginPayView$$ExternalSyntheticLambda0(LoginActivity.LoginPayView r1, TLRPC.TL_inputStorePaymentAuthCode r2, Purchase r3, TLRPC.TL_payments_canPurchaseStore r4, Runnable r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r8, TLRPC.TL_error r9) {
        LoginActivity.LoginPayView.$r8$lambda$t51F0XiCL7UqeBnAVnNReVfAiiU(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, r8, r9);
    }
}
