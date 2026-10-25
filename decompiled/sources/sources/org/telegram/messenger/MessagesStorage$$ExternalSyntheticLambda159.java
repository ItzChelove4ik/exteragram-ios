package org.telegram.messenger;

import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class MessagesStorage$$ExternalSyntheticLambda159 implements Runnable {
    public final /* synthetic */ MessagesStorage f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ long f$2;
    public final /* synthetic */ TLRPC.TL_messageReactions f$3;

    public /* synthetic */ MessagesStorage$$ExternalSyntheticLambda159(MessagesStorage r1, int r2, long r3, TLRPC.TL_messageReactions r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r5;
    }

    @Override // java.lang.Runnable
    public final void run() {
        MessagesStorage.m4702$r8$lambda$Jy23wLM6vcKfZMXlVJgHgXteHA(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
