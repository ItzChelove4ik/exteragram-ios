package org.telegram.messenger;

import java.util.ArrayList;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class MessagesController$$ExternalSyntheticLambda295 implements RequestDelegate {
    public final /* synthetic */ MessagesController f$0;
    public final /* synthetic */ ArrayList f$1;

    public /* synthetic */ MessagesController$$ExternalSyntheticLambda295(MessagesController r1, ArrayList r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r2, TLRPC.TL_error r3) {
        MessagesController.$r8$lambda$8vaRZ7CGHRO6jSV6Z80u6tpt1zw(this.f$0, this.f$1, r2, r3);
    }
}
