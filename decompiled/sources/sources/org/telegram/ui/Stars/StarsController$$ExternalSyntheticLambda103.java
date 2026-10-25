package org.telegram.ui.Stars;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public final /* synthetic */ class StarsController$$ExternalSyntheticLambda103 implements RequestDelegate {
    public final /* synthetic */ StarsController f$0;
    public final /* synthetic */ Utilities.Callback2 f$1;
    public final /* synthetic */ TLRPC.TL_inputInvoiceStars f$2;

    public /* synthetic */ StarsController$$ExternalSyntheticLambda103(StarsController r1, Utilities.Callback2 r2, TLRPC.TL_inputInvoiceStars r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r3, TLRPC.TL_error r4) {
        StarsController.$r8$lambda$THMz2LmQwYNPlVSJyL7mrwLGn_A(this.f$0, this.f$1, this.f$2, r3, r4);
    }
}
