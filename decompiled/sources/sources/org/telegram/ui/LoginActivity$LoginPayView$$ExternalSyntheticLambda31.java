package org.telegram.ui;

import androidx.core.util.Consumer;
import com.android.billingclient.api.BillingResult;
import org.telegram.messenger.Utilities;
import org.telegram.ui.LoginActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class LoginActivity$LoginPayView$$ExternalSyntheticLambda31 implements Consumer {
    public final /* synthetic */ Utilities.Callback f$0;

    public /* synthetic */ LoginActivity$LoginPayView$$ExternalSyntheticLambda31(Utilities.Callback r1) {
        this.f$0 = r1;
    }

    @Override // androidx.core.util.Consumer
    public final void accept(Object r1) {
        LoginActivity.LoginPayView.$r8$lambda$pAGp1uDnxfAJNNKcngfU5B1nZnE(this.f$0, (BillingResult) r1);
    }
}
