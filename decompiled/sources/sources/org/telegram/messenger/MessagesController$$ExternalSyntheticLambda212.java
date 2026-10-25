package org.telegram.messenger;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class MessagesController$$ExternalSyntheticLambda212 implements Runnable {
    public final /* synthetic */ MessagesController f$0;
    public final /* synthetic */ TLRPC.Updates f$1;
    public final /* synthetic */ Utilities.Callback f$2;
    public final /* synthetic */ TLRPC.TL_messages_invitedUsers f$3;
    public final /* synthetic */ long f$4;

    public /* synthetic */ MessagesController$$ExternalSyntheticLambda212(MessagesController r1, TLRPC.Updates r2, Utilities.Callback r3, TLRPC.TL_messages_invitedUsers r4, long r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // java.lang.Runnable
    public final void run() {
        MessagesController.$r8$lambda$4QWy5Obb7mJRZZLy5AGgi7np328(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
