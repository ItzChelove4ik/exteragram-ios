package org.telegram.messenger;

import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class MessagesStorage$$ExternalSyntheticLambda152 implements Runnable {
    public final /* synthetic */ MessagesStorage f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ long f$2;
    public final /* synthetic */ TLRPC.Message f$3;

    public /* synthetic */ MessagesStorage$$ExternalSyntheticLambda152(MessagesStorage r1, int r2, long r3, TLRPC.Message r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r5;
    }

    @Override // java.lang.Runnable
    public final void run() {
        MessagesStorage.$r8$lambda$7QbqChG5zRtWDiYD3Ex4OGNdpSg(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
