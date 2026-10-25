package org.telegram.ui;

import android.view.View;
import com.android.billingclient.api.ProductDetails;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.LoginActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class LoginActivity$LoginPayView$$ExternalSyntheticLambda25 implements View.OnClickListener {
    public final /* synthetic */ LoginActivity.LoginPayView f$0;
    public final /* synthetic */ ProductDetails f$1;
    public final /* synthetic */ TLRPC.TL_inputStorePaymentAuthCode f$2;
    public final /* synthetic */ String f$3;
    public final /* synthetic */ TLRPC.TL_payments_canPurchaseStore f$4;

    public /* synthetic */ LoginActivity$LoginPayView$$ExternalSyntheticLambda25(LoginActivity.LoginPayView r1, ProductDetails r2, TLRPC.TL_inputStorePaymentAuthCode r3, String r4, TLRPC.TL_payments_canPurchaseStore r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View r7) {
        LoginActivity.LoginPayView.$r8$lambda$QS97KYWCZDCyFcEic1NGuSuZCnY(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, r7);
    }
}
