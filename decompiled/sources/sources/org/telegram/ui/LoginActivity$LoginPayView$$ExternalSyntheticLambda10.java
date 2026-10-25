package org.telegram.ui;

import com.android.billingclient.api.BillingResult;
import java.util.List;
import org.telegram.messenger.BillingController;
import org.telegram.ui.LoginActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class LoginActivity$LoginPayView$$ExternalSyntheticLambda10 implements BillingController.ProductDetailsResponseListenerLegacy {
    public final /* synthetic */ LoginActivity.LoginPayView f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ String f$2;
    public final /* synthetic */ String f$3;
    public final /* synthetic */ int f$4;

    public /* synthetic */ LoginActivity$LoginPayView$$ExternalSyntheticLambda10(LoginActivity.LoginPayView r1, String r2, String r3, String r4, int r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // org.telegram.messenger.BillingController.ProductDetailsResponseListenerLegacy
    public final void onProductDetailsResponse(BillingResult r8, List r9) {
        LoginActivity.LoginPayView.$r8$lambda$f6V1g7fWPBHGQHpxk2fgip2r30g(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, r8, r9);
    }
}
