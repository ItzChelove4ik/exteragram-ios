package org.telegram.messenger;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class MessagesController$$ExternalSyntheticLambda220 implements RequestDelegate {
    public final /* synthetic */ MessagesController f$0;
    public final /* synthetic */ TLRPC.Chat f$1;
    public final /* synthetic */ TLRPC.User f$2;

    public /* synthetic */ MessagesController$$ExternalSyntheticLambda220(MessagesController r1, TLRPC.Chat r2, TLRPC.User r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r3, TLRPC.TL_error r4) {
        MessagesController.$r8$lambda$vNZ29OS2rAW_Y7RF0bMIIjjbFKs(this.f$0, this.f$1, this.f$2, r3, r4);
    }
}
