package org.telegram.ui.Stars;

import org.telegram.messenger.MessageObject;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public final /* synthetic */ class StarsController$$ExternalSyntheticLambda91 implements Runnable {
    public final /* synthetic */ StarsController f$0;
    public final /* synthetic */ boolean[] f$1;
    public final /* synthetic */ MessageObject f$2;
    public final /* synthetic */ TLRPC.InputInvoice f$3;
    public final /* synthetic */ TLRPC.TL_payments_paymentFormStars f$4;
    public final /* synthetic */ Utilities.Callback f$5;

    public /* synthetic */ StarsController$$ExternalSyntheticLambda91(StarsController r1, boolean[] r2, MessageObject r3, TLRPC.InputInvoice r4, TLRPC.TL_payments_paymentFormStars r5, Utilities.Callback r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
    }

    @Override // java.lang.Runnable
    public final void run() {
        StarsController.$r8$lambda$n3MoO2ER5Tq2R9Y9OwCy9_utRUg(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5);
    }
}
