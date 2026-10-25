package org.telegram.messenger;

import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class MessagesStorage$$ExternalSyntheticLambda156 implements Runnable {
    public final /* synthetic */ MessagesStorage f$0;
    public final /* synthetic */ TLRPC.EncryptedChat f$1;
    public final /* synthetic */ boolean f$2;

    public /* synthetic */ MessagesStorage$$ExternalSyntheticLambda156(MessagesStorage r1, TLRPC.EncryptedChat r2, boolean r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        MessagesStorage.m4736$r8$lambda$b6VatsmnrU86GgPC_SvKAU22jc(this.f$0, this.f$1, this.f$2);
    }
}
