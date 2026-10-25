package org.telegram.ui;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class PaymentFormActivity$$ExternalSyntheticLambda44 implements RequestDelegate {
    public final /* synthetic */ PaymentFormActivity f$0;
    public final /* synthetic */ boolean f$1;
    public final /* synthetic */ String f$2;

    public /* synthetic */ PaymentFormActivity$$ExternalSyntheticLambda44(PaymentFormActivity r1, boolean r2, String r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r3, TLRPC.TL_error r4) {
        PaymentFormActivity.$r8$lambda$mtR83BqOEeNRvywAqqn65S_NasI(this.f$0, this.f$1, this.f$2, r3, r4);
    }
}
