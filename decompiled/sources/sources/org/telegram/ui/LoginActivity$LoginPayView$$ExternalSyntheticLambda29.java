package org.telegram.ui;

import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.PurchasesResponseListener;
import java.util.List;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.LoginActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class LoginActivity$LoginPayView$$ExternalSyntheticLambda29 implements PurchasesResponseListener {
    public final /* synthetic */ LoginActivity.LoginPayView f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ TLRPC.TL_inputStorePaymentAuthCode f$2;
    public final /* synthetic */ TLRPC.TL_payments_canPurchaseStore f$3;
    public final /* synthetic */ Runnable f$4;

    public /* synthetic */ LoginActivity$LoginPayView$$ExternalSyntheticLambda29(LoginActivity.LoginPayView r1, String r2, TLRPC.TL_inputStorePaymentAuthCode r3, TLRPC.TL_payments_canPurchaseStore r4, Runnable r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // com.android.billingclient.api.PurchasesResponseListener
    public final void onQueryPurchasesResponse(BillingResult r8, List r9) {
        LoginActivity.LoginPayView.$r8$lambda$MMMgGcArebh0TiH2jec17Adf_vY(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, r8, r9);
    }
}
