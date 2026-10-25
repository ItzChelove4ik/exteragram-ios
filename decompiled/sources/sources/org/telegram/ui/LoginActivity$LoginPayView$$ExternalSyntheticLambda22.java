package org.telegram.ui;

import com.android.billingclient.api.ProductDetails;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.LoginActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class LoginActivity$LoginPayView$$ExternalSyntheticLambda22 implements Runnable {
    public final /* synthetic */ LoginActivity.LoginPayView f$0;
    public final /* synthetic */ TLObject f$1;
    public final /* synthetic */ TLRPC.TL_error f$2;
    public final /* synthetic */ ProductDetails.OneTimePurchaseOfferDetails f$3;
    public final /* synthetic */ int f$4;
    public final /* synthetic */ ProductDetails f$5;
    public final /* synthetic */ TLRPC.TL_inputStorePaymentAuthCode f$6;
    public final /* synthetic */ String f$7;
    public final /* synthetic */ TLRPC.TL_payments_canPurchaseStore f$8;

    public /* synthetic */ LoginActivity$LoginPayView$$ExternalSyntheticLambda22(LoginActivity.LoginPayView r1, TLObject r2, TLRPC.TL_error r3, ProductDetails.OneTimePurchaseOfferDetails r4, int r5, ProductDetails r6, TLRPC.TL_inputStorePaymentAuthCode r7, String r8, TLRPC.TL_payments_canPurchaseStore r9) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
        this.f$6 = r7;
        this.f$7 = r8;
        this.f$8 = r9;
    }

    @Override // java.lang.Runnable
    public final void run() {
        LoginActivity.LoginPayView.$r8$lambda$7QeL_YEQuD8_aCJ12AczdSYbQZ8(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, this.f$8);
    }
}
