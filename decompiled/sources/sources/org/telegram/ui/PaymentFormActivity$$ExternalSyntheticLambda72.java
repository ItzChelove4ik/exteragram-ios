package org.telegram.ui;

import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_account;

/* loaded from: classes6.dex */
public final /* synthetic */ class PaymentFormActivity$$ExternalSyntheticLambda72 implements Runnable {
    public final /* synthetic */ PaymentFormActivity f$0;
    public final /* synthetic */ TLObject f$1;
    public final /* synthetic */ TLRPC.TL_error f$2;
    public final /* synthetic */ TL_account.getTmpPassword f$3;

    public /* synthetic */ PaymentFormActivity$$ExternalSyntheticLambda72(PaymentFormActivity r1, TLObject r2, TLRPC.TL_error r3, TL_account.getTmpPassword r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        PaymentFormActivity.$r8$lambda$Jox2wIxMQRbDPHDEhXoq3tBFVio(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
