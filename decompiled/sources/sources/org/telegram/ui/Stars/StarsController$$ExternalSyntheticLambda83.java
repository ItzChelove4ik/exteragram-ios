package org.telegram.ui.Stars;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public final /* synthetic */ class StarsController$$ExternalSyntheticLambda83 implements RequestDelegate {
    public final /* synthetic */ StarsController f$0;
    public final /* synthetic */ Utilities.Callback2 f$1;
    public final /* synthetic */ TLRPC.TL_inputInvoiceStars f$2;

    public /* synthetic */ StarsController$$ExternalSyntheticLambda83(StarsController r1, Utilities.Callback2 r2, TLRPC.TL_inputInvoiceStars r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r3, TLRPC.TL_error r4) {
        StarsController.m19120$r8$lambda$RY7o0xHkRyTmLUlhpPeR0M4Jps(this.f$0, this.f$1, this.f$2, r3, r4);
    }
}
