package org.telegram.messenger;

import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class MessagesController$$ExternalSyntheticLambda390 implements Runnable {
    public final /* synthetic */ MessagesController f$0;
    public final /* synthetic */ long f$1;
    public final /* synthetic */ TLRPC.TL_messages_savedReactionsTags f$2;

    public /* synthetic */ MessagesController$$ExternalSyntheticLambda390(MessagesController r1, long r2, TLRPC.TL_messages_savedReactionsTags r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        MessagesController.m4610$r8$lambda$lIGmTJKRAkgFkgl3WECNxPZe8w(this.f$0, this.f$1, this.f$2);
    }
}
