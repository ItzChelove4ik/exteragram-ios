package org.telegram.messenger;

import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class MessagesStorage$$ExternalSyntheticLambda140 implements Runnable {
    public final /* synthetic */ MessagesStorage f$0;
    public final /* synthetic */ TLRPC.Message f$1;
    public final /* synthetic */ long f$2;

    public /* synthetic */ MessagesStorage$$ExternalSyntheticLambda140(MessagesStorage r1, TLRPC.Message r2, long r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        MessagesStorage.m4693$r8$lambda$DQUTfrakAStwczibGySrcyz2FI(this.f$0, this.f$1, this.f$2);
    }
}
