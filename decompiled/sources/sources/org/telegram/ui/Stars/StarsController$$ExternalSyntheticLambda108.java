package org.telegram.ui.Stars;

import org.telegram.messenger.Utilities;
import org.telegram.ui.PaymentFormActivity;

/* loaded from: classes7.dex */
public final /* synthetic */ class StarsController$$ExternalSyntheticLambda108 implements PaymentFormActivity.PaymentFormCallback {
    public final /* synthetic */ Utilities.Callback2 f$0;

    public /* synthetic */ StarsController$$ExternalSyntheticLambda108(Utilities.Callback2 r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.ui.PaymentFormActivity.PaymentFormCallback
    public final void onInvoiceStatusChanged(PaymentFormActivity.InvoiceStatus r1) {
        StarsController.$r8$lambda$wJQar96j6YtvG_3jhJiROy7mhts(this.f$0, r1);
    }
}
