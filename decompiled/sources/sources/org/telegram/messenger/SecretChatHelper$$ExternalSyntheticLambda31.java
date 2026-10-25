package org.telegram.messenger;

import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class SecretChatHelper$$ExternalSyntheticLambda31 implements Runnable {
    public final /* synthetic */ SecretChatHelper f$0;
    public final /* synthetic */ TLRPC.TL_encryptedChatDiscarded f$1;

    public /* synthetic */ SecretChatHelper$$ExternalSyntheticLambda31(SecretChatHelper r1, TLRPC.TL_encryptedChatDiscarded r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        SecretChatHelper.m4895$r8$lambda$eAtQREXpykXRCTEyCxR2SHfyXE(this.f$0, this.f$1);
    }
}
