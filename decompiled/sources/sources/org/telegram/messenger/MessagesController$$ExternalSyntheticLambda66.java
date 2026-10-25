package org.telegram.messenger;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class MessagesController$$ExternalSyntheticLambda66 implements Utilities.Callback2 {
    public final /* synthetic */ MessagesController f$0;
    public final /* synthetic */ Utilities.Callback2 f$1;

    public /* synthetic */ MessagesController$$ExternalSyntheticLambda66(MessagesController r1, Utilities.Callback2 r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r2, Object r3) {
        MessagesController.m4580$r8$lambda$YErwpb9Xvz7FNZJuCAhfXaKcss(this.f$0, this.f$1, (TLRPC.messages_Chats) r2, (TLRPC.TL_error) r3);
    }
}
