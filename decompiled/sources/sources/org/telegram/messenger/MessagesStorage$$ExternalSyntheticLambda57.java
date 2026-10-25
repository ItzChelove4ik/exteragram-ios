package org.telegram.messenger;

import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class MessagesStorage$$ExternalSyntheticLambda57 implements Runnable {
    public final /* synthetic */ MessagesStorage f$0;
    public final /* synthetic */ long f$1;
    public final /* synthetic */ TLRPC.TL_forumTopic f$2;

    public /* synthetic */ MessagesStorage$$ExternalSyntheticLambda57(MessagesStorage r1, long r2, TLRPC.TL_forumTopic r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        MessagesStorage.m4722$r8$lambda$U592XSNl86iZovVePExfTdk1qI(this.f$0, this.f$1, this.f$2);
    }
}
