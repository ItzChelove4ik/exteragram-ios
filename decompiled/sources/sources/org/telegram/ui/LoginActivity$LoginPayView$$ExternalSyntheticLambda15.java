package org.telegram.ui;

import com.android.billingclient.api.ProductDetails;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.LoginActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class LoginActivity$LoginPayView$$ExternalSyntheticLambda15 implements RequestDelegate {
    public final /* synthetic */ LoginActivity.LoginPayView f$0;
    public final /* synthetic */ ProductDetails.OneTimePurchaseOfferDetails f$1;
    public final /* synthetic */ int f$2;
    public final /* synthetic */ ProductDetails f$3;
    public final /* synthetic */ TLRPC.TL_inputStorePaymentAuthCode f$4;
    public final /* synthetic */ String f$5;
    public final /* synthetic */ TLRPC.TL_payments_canPurchaseStore f$6;

    public /* synthetic */ LoginActivity$LoginPayView$$ExternalSyntheticLambda15(LoginActivity.LoginPayView r1, ProductDetails.OneTimePurchaseOfferDetails r2, int r3, ProductDetails r4, TLRPC.TL_inputStorePaymentAuthCode r5, String r6, TLRPC.TL_payments_canPurchaseStore r7) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
        this.f$6 = r7;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r10, TLRPC.TL_error r11) {
        LoginActivity.LoginPayView.m15962$r8$lambda$fAM1VfcHj8hYxAfUp1E7BVLJiM(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, r10, r11);
    }
}
