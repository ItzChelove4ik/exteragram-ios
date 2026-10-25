package org.telegram.ui;

import com.android.billingclient.api.BillingResult;
import java.util.List;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.LoginActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class LoginActivity$LoginPayView$$ExternalSyntheticLambda33 implements Runnable {
    public final /* synthetic */ LoginActivity.LoginPayView f$0;
    public final /* synthetic */ BillingResult f$1;
    public final /* synthetic */ List f$2;
    public final /* synthetic */ String f$3;
    public final /* synthetic */ TLRPC.TL_inputStorePaymentAuthCode f$4;
    public final /* synthetic */ TLRPC.TL_payments_canPurchaseStore f$5;
    public final /* synthetic */ Runnable f$6;

    public /* synthetic */ LoginActivity$LoginPayView$$ExternalSyntheticLambda33(LoginActivity.LoginPayView r1, BillingResult r2, List r3, String r4, TLRPC.TL_inputStorePaymentAuthCode r5, TLRPC.TL_payments_canPurchaseStore r6, Runnable r7) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
        this.f$6 = r7;
    }

    @Override // java.lang.Runnable
    public final void run() {
        LoginActivity.LoginPayView.m15961$r8$lambda$SWUKr0mRXWObZOGASPOAjH5Zz8(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6);
    }
}
