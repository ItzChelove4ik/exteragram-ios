package org.telegram.messenger;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class SecretChatHelper$$ExternalSyntheticLambda29 implements RequestDelegate {
    public final /* synthetic */ SecretChatHelper f$0;
    public final /* synthetic */ long f$1;

    public /* synthetic */ SecretChatHelper$$ExternalSyntheticLambda29(SecretChatHelper r1, long r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r4, TLRPC.TL_error r5) {
        SecretChatHelper.$r8$lambda$NenD2faGeypKgd4GP14slhfDY8k(this.f$0, this.f$1, r4, r5);
    }
}
