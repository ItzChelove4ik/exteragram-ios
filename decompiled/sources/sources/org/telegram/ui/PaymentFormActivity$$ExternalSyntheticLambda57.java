package org.telegram.ui;

import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_account;

/* loaded from: classes6.dex */
public final /* synthetic */ class PaymentFormActivity$$ExternalSyntheticLambda57 implements Runnable {
    public final /* synthetic */ PaymentFormActivity f$0;
    public final /* synthetic */ TLRPC.TL_error f$1;
    public final /* synthetic */ TLObject f$2;
    public final /* synthetic */ String f$3;
    public final /* synthetic */ TL_account.getPassword f$4;

    public /* synthetic */ PaymentFormActivity$$ExternalSyntheticLambda57(PaymentFormActivity r1, TLRPC.TL_error r2, TLObject r3, String r4, TL_account.getPassword r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // java.lang.Runnable
    public final void run() {
        PaymentFormActivity.$r8$lambda$8_TJ_HnIyI5xA7QMT9GL3jvVxUQ(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
