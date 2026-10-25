package org.telegram.messenger;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class SecretChatHelper$$ExternalSyntheticLambda23 implements RequestDelegate {
    public final /* synthetic */ SecretChatHelper f$0;
    public final /* synthetic */ TLRPC.EncryptedChat f$1;

    public /* synthetic */ SecretChatHelper$$ExternalSyntheticLambda23(SecretChatHelper r1, TLRPC.EncryptedChat r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r2, TLRPC.TL_error r3) {
        SecretChatHelper.m4898$r8$lambda$zcecztImSWB5kLlzIQ41wG8XI(this.f$0, this.f$1, r2, r3);
    }
}
