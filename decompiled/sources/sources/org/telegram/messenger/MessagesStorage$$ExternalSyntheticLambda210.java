package org.telegram.messenger;

import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class MessagesStorage$$ExternalSyntheticLambda210 implements Runnable {
    public final /* synthetic */ MessagesStorage f$0;
    public final /* synthetic */ TLRPC.EncryptedChat f$1;
    public final /* synthetic */ TLRPC.User f$2;
    public final /* synthetic */ TLRPC.Dialog f$3;

    public /* synthetic */ MessagesStorage$$ExternalSyntheticLambda210(MessagesStorage r1, TLRPC.EncryptedChat r2, TLRPC.User r3, TLRPC.Dialog r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        MessagesStorage.$r8$lambda$LLAVuOc0w2xleYfCWRROTTcBqT8(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
