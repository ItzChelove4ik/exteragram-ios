package org.telegram.messenger;

import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class MessagesStorage$$ExternalSyntheticLambda224 implements Runnable {
    public final /* synthetic */ MessagesStorage f$0;
    public final /* synthetic */ TLRPC.ChatParticipants f$1;

    public /* synthetic */ MessagesStorage$$ExternalSyntheticLambda224(MessagesStorage r1, TLRPC.ChatParticipants r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        MessagesStorage.$r8$lambda$d9LgnHTBUzu4t8SptOgrhOHIFgA(this.f$0, this.f$1);
    }
}
