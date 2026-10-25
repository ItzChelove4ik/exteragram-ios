package org.telegram.ui;

import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class PaymentFormActivity$$ExternalSyntheticLambda61 implements Runnable {
    public final /* synthetic */ PaymentFormActivity f$0;
    public final /* synthetic */ TLRPC.TL_error f$1;
    public final /* synthetic */ TLRPC.TL_payments_sendPaymentForm f$2;

    public /* synthetic */ PaymentFormActivity$$ExternalSyntheticLambda61(PaymentFormActivity r1, TLRPC.TL_error r2, TLRPC.TL_payments_sendPaymentForm r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        PaymentFormActivity.$r8$lambda$d_V4HpOfHigP7g1TgU8SCz0Dnns(this.f$0, this.f$1, this.f$2);
    }
}
