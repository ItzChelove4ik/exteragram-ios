package org.telegram.messenger;

import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class SecretChatHelper$$ExternalSyntheticLambda6 implements Runnable {
    public final /* synthetic */ SecretChatHelper f$0;
    public final /* synthetic */ TLRPC.Message f$1;
    public final /* synthetic */ TLRPC.messages_SentEncryptedMessage f$2;
    public final /* synthetic */ int f$3;

    public /* synthetic */ SecretChatHelper$$ExternalSyntheticLambda6(SecretChatHelper r1, TLRPC.Message r2, TLRPC.messages_SentEncryptedMessage r3, int r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        SecretChatHelper.$r8$lambda$kDQAbv69pIJcU5j5ukdWC5mX8Gc(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
