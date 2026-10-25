package org.telegram.messenger;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class MessagesController$$ExternalSyntheticLambda206 implements Runnable {
    public final /* synthetic */ MessagesController f$0;
    public final /* synthetic */ Utilities.Callback f$1;
    public final /* synthetic */ TLRPC.TL_messages_invitedUsers f$2;
    public final /* synthetic */ long f$3;

    public /* synthetic */ MessagesController$$ExternalSyntheticLambda206(MessagesController r1, Utilities.Callback r2, TLRPC.TL_messages_invitedUsers r3, long r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        MessagesController.m4502$r8$lambda$6SSXgKsoeXPmFLtJf1gUwT6IbY(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
