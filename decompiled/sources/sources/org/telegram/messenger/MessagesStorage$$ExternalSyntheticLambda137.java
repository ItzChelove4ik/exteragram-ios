package org.telegram.messenger;

import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class MessagesStorage$$ExternalSyntheticLambda137 implements Runnable {
    public final /* synthetic */ MessagesStorage f$0;
    public final /* synthetic */ TLRPC.TL_messageReactions f$1;
    public final /* synthetic */ TLRPC.TL_messageReactions f$2;
    public final /* synthetic */ long f$3;

    public /* synthetic */ MessagesStorage$$ExternalSyntheticLambda137(MessagesStorage r1, TLRPC.TL_messageReactions r2, TLRPC.TL_messageReactions r3, long r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        MessagesStorage.$r8$lambda$suY5hcfe5QShxvwW_Q42eEcNbAc(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
