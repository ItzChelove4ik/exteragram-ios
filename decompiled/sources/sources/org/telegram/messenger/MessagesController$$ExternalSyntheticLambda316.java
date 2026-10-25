package org.telegram.messenger;

import org.telegram.tgnet.TLRPC;

/* loaded from: classes.dex */
public final /* synthetic */ class MessagesController$$ExternalSyntheticLambda316 implements Runnable {
    public final /* synthetic */ MessagesController f$0;
    public final /* synthetic */ long f$1;
    public final /* synthetic */ TLRPC.TL_message f$2;

    public /* synthetic */ MessagesController$$ExternalSyntheticLambda316(MessagesController r1, long r2, TLRPC.TL_message r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        MessagesController.m4509$r8$lambda$8GVanBsbjdefKhlFmEF0CFQI(this.f$0, this.f$1, this.f$2);
    }
}
