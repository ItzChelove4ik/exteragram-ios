package org.telegram.ui.Components.Premium.boosts;

import org.telegram.messenger.Utilities;
import org.telegram.ui.PaymentFormActivity;

/* loaded from: classes7.dex */
public final /* synthetic */ class BoostRepository$$ExternalSyntheticLambda37 implements PaymentFormActivity.PaymentFormCallback {
    public final /* synthetic */ Utilities.Callback f$0;
    public final /* synthetic */ Utilities.Callback f$1;

    public /* synthetic */ BoostRepository$$ExternalSyntheticLambda37(Utilities.Callback r1, Utilities.Callback r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.ui.PaymentFormActivity.PaymentFormCallback
    public final void onInvoiceStatusChanged(PaymentFormActivity.InvoiceStatus r2) {
        BoostRepository.$r8$lambda$6YUWeNj5TBmUQU6EBQNuQA00DlA(this.f$0, this.f$1, r2);
    }
}
