package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_account;

/* loaded from: classes6.dex */
public final /* synthetic */ class PaymentFormActivity$$ExternalSyntheticLambda67 implements RequestDelegate {
    public final /* synthetic */ PaymentFormActivity f$0;
    public final /* synthetic */ TL_account.getTmpPassword f$1;

    public /* synthetic */ PaymentFormActivity$$ExternalSyntheticLambda67(PaymentFormActivity r1, TL_account.getTmpPassword r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r2, TLRPC.TL_error r3) {
        PaymentFormActivity.m16598$r8$lambda$v42IaoxCvXwQpVbT4TNF_wL_Uo(this.f$0, this.f$1, r2, r3);
    }
}
