package org.telegram.messenger;

import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class MessagesController$$ExternalSyntheticLambda360 implements Runnable {
    public final /* synthetic */ MessagesController f$0;
    public final /* synthetic */ TLRPC.Chat f$1;
    public final /* synthetic */ TLRPC.TL_messages_invitedUsers f$2;

    public /* synthetic */ MessagesController$$ExternalSyntheticLambda360(MessagesController r1, TLRPC.Chat r2, TLRPC.TL_messages_invitedUsers r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        MessagesController.$r8$lambda$IEs6i6dBYXvjf40KDJu_IL_AFhs(this.f$0, this.f$1, this.f$2);
    }
}
