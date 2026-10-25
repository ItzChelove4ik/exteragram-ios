package org.telegram.messenger;

import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class MessagesStorage$$ExternalSyntheticLambda134 implements Runnable {
    public final /* synthetic */ MessagesStorage f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ TLRPC.TL_forumTopic f$2;
    public final /* synthetic */ long f$3;
    public final /* synthetic */ int f$4;

    public /* synthetic */ MessagesStorage$$ExternalSyntheticLambda134(MessagesStorage r1, int r2, TLRPC.TL_forumTopic r3, long r4, int r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r6;
    }

    @Override // java.lang.Runnable
    public final void run() {
        MessagesStorage.$r8$lambda$KzhwnqblxrEXXksbnFaGsUxTsx4(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
