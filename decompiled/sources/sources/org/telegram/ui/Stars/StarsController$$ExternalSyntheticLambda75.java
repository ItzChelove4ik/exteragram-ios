package org.telegram.ui.Stars;

import org.telegram.messenger.MessageObject;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public final /* synthetic */ class StarsController$$ExternalSyntheticLambda75 implements RequestDelegate {
    public final /* synthetic */ StarsController f$0;
    public final /* synthetic */ MessageObject f$1;
    public final /* synthetic */ TLRPC.TL_inputInvoiceMessage f$2;
    public final /* synthetic */ Runnable f$3;

    public /* synthetic */ StarsController$$ExternalSyntheticLambda75(StarsController r1, MessageObject r2, TLRPC.TL_inputInvoiceMessage r3, Runnable r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r7, TLRPC.TL_error r8) {
        StarsController.$r8$lambda$Gz8WXjXfi_ny69Yznw8xuV_y2JM(this.f$0, this.f$1, this.f$2, this.f$3, r7, r8);
    }
}
