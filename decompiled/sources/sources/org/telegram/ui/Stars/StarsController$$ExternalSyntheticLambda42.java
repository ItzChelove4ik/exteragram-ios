package org.telegram.ui.Stars;

import org.telegram.messenger.MessageObject;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public final /* synthetic */ class StarsController$$ExternalSyntheticLambda42 implements Runnable {
    public final /* synthetic */ StarsController f$0;
    public final /* synthetic */ boolean[] f$1;
    public final /* synthetic */ MessageObject f$2;
    public final /* synthetic */ TLRPC.InputInvoice f$3;
    public final /* synthetic */ TLRPC.TL_payments_paymentFormStars f$4;
    public final /* synthetic */ boolean[] f$5;
    public final /* synthetic */ int f$6;
    public final /* synthetic */ Utilities.Callback f$7;
    public final /* synthetic */ Utilities.Callback f$8;

    public /* synthetic */ StarsController$$ExternalSyntheticLambda42(StarsController r1, boolean[] r2, MessageObject r3, TLRPC.InputInvoice r4, TLRPC.TL_payments_paymentFormStars r5, boolean[] r6, int r7, Utilities.Callback r8, Utilities.Callback r9) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
        this.f$6 = r7;
        this.f$7 = r8;
        this.f$8 = r9;
    }

    @Override // java.lang.Runnable
    public final void run() {
        StarsController.m19143$r8$lambda$zeDjwoRmH29ug1SxAEdPkheeI(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, this.f$8);
    }
}
