package org.telegram.messenger;

import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class SecretChatHelper$$ExternalSyntheticLambda18 implements Runnable {
    public final /* synthetic */ SecretChatHelper f$0;
    public final /* synthetic */ TLRPC.EncryptedChat f$1;
    public final /* synthetic */ TLRPC.DecryptedMessage f$2;
    public final /* synthetic */ TLRPC.Message f$3;
    public final /* synthetic */ TLRPC.InputEncryptedFile f$4;
    public final /* synthetic */ MessageObject f$5;
    public final /* synthetic */ String f$6;

    public /* synthetic */ SecretChatHelper$$ExternalSyntheticLambda18(SecretChatHelper r1, TLRPC.EncryptedChat r2, TLRPC.DecryptedMessage r3, TLRPC.Message r4, TLRPC.InputEncryptedFile r5, MessageObject r6, String r7) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
        this.f$6 = r7;
    }

    @Override // java.lang.Runnable
    public final void run() {
        SecretChatHelper.$r8$lambda$ue3LqHm9DuOFAf_yZWfAl9SWphA(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6);
    }
}
