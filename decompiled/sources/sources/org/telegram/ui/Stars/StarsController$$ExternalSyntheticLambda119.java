package org.telegram.ui.Stars;

import org.telegram.messenger.Utilities;
import org.telegram.ui.PaymentFormActivity;

/* loaded from: classes7.dex */
public final /* synthetic */ class StarsController$$ExternalSyntheticLambda119 implements PaymentFormActivity.PaymentFormCallback {
    public final /* synthetic */ Utilities.Callback2 f$0;

    public /* synthetic */ StarsController$$ExternalSyntheticLambda119(Utilities.Callback2 r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.ui.PaymentFormActivity.PaymentFormCallback
    public final void onInvoiceStatusChanged(PaymentFormActivity.InvoiceStatus r1) {
        StarsController.$r8$lambda$0CbP5pj7F7P1HDfgSiiePMUv7Lo(this.f$0, r1);
    }
}
