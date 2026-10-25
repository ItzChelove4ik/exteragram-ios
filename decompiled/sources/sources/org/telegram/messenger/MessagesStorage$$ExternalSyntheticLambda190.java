package org.telegram.messenger;

import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class MessagesStorage$$ExternalSyntheticLambda190 implements Runnable {
    public final /* synthetic */ MessagesStorage f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ TLRPC.Message f$2;

    public /* synthetic */ MessagesStorage$$ExternalSyntheticLambda190(MessagesStorage r1, int r2, TLRPC.Message r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        MessagesStorage.$r8$lambda$FHYqYFaQvMUMbbwftDaBO93fe5Y(this.f$0, this.f$1, this.f$2);
    }
}
