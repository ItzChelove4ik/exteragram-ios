package org.telegram.messenger;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class SecretChatHelper$$ExternalSyntheticLambda11 implements RequestDelegate {
    public final /* synthetic */ SecretChatHelper f$0;
    public final /* synthetic */ TLRPC.DecryptedMessage f$1;
    public final /* synthetic */ TLRPC.EncryptedChat f$2;
    public final /* synthetic */ TLRPC.Message f$3;
    public final /* synthetic */ MessageObject f$4;
    public final /* synthetic */ String f$5;

    public /* synthetic */ SecretChatHelper$$ExternalSyntheticLambda11(SecretChatHelper r1, TLRPC.DecryptedMessage r2, TLRPC.EncryptedChat r3, TLRPC.Message r4, MessageObject r5, String r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r9, TLRPC.TL_error r10) {
        SecretChatHelper.$r8$lambda$dnrADGYuptFvIsfDYtfgCoNhjd8(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, r9, r10);
    }
}
