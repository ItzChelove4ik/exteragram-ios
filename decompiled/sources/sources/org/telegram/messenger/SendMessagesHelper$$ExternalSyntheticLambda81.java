package org.telegram.messenger;

import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class SendMessagesHelper$$ExternalSyntheticLambda81 implements Runnable {
    public final /* synthetic */ SendMessagesHelper f$0;
    public final /* synthetic */ TLRPC.TL_error f$1;
    public final /* synthetic */ TLRPC.TL_messages_forwardMessages f$2;

    public /* synthetic */ SendMessagesHelper$$ExternalSyntheticLambda81(SendMessagesHelper r1, TLRPC.TL_error r2, TLRPC.TL_messages_forwardMessages r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        SendMessagesHelper.m4923$r8$lambda$WLwg3ZFTi55qaSu4Q3dQwjMtY(this.f$0, this.f$1, this.f$2);
    }
}
