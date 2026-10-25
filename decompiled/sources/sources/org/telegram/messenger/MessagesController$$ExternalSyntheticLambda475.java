package org.telegram.messenger;

import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_update;

/* loaded from: classes5.dex */
public final /* synthetic */ class MessagesController$$ExternalSyntheticLambda475 implements Runnable {
    public final /* synthetic */ MessagesController f$0;
    public final /* synthetic */ MessageObject f$1;
    public final /* synthetic */ TLRPC.TL_messageMediaPoll f$2;
    public final /* synthetic */ TL_update.TL_updateMessagePoll f$3;

    public /* synthetic */ MessagesController$$ExternalSyntheticLambda475(MessagesController r1, MessageObject r2, TLRPC.TL_messageMediaPoll r3, TL_update.TL_updateMessagePoll r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        MessagesController.m4649$r8$lambda$xGqtIcCPIqdd0ej22l7R5mmc8(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
