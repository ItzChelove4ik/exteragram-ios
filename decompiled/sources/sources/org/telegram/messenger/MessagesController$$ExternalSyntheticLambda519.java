package org.telegram.messenger;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class MessagesController$$ExternalSyntheticLambda519 implements RequestDelegate {
    public final /* synthetic */ MessagesController f$0;
    public final /* synthetic */ TLRPC.Chat f$1;
    public final /* synthetic */ TLRPC.User f$2;
    public final /* synthetic */ TLRPC.Chat f$3;

    public /* synthetic */ MessagesController$$ExternalSyntheticLambda519(MessagesController r1, TLRPC.Chat r2, TLRPC.User r3, TLRPC.Chat r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r7, TLRPC.TL_error r8) {
        MessagesController.m4582$r8$lambda$ZUXI8sVzaW7TXn5X4RQdSqqhjk(this.f$0, this.f$1, this.f$2, this.f$3, r7, r8);
    }
}
