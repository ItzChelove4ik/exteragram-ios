package org.telegram.messenger;

import org.telegram.tgnet.TLRPC;

/* loaded from: classes.dex */
public final /* synthetic */ class MessagesStorage$$ExternalSyntheticLambda119 implements Runnable {
    public final /* synthetic */ MessagesStorage f$0;
    public final /* synthetic */ long f$1;
    public final /* synthetic */ TLRPC.Poll f$2;
    public final /* synthetic */ TLRPC.PollResults f$3;

    public /* synthetic */ MessagesStorage$$ExternalSyntheticLambda119(MessagesStorage r1, long r2, TLRPC.Poll r4, TLRPC.PollResults r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r4;
        this.f$3 = r5;
    }

    @Override // java.lang.Runnable
    public final void run() {
        MessagesStorage.$r8$lambda$V6PSfXq1zp2Q0hfbl5Ue3El3omI(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
