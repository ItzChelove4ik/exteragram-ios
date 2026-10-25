package org.telegram.messenger;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class MessagesController$$ExternalSyntheticLambda147 implements Utilities.Callback2 {
    public final /* synthetic */ MessagesController f$0;
    public final /* synthetic */ Utilities.Callback f$1;

    public /* synthetic */ MessagesController$$ExternalSyntheticLambda147(MessagesController r1, Utilities.Callback r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r2, Object r3) {
        MessagesController.m4619$r8$lambda$o6dl6DZGnTqwxvxppVVWJqEdOE(this.f$0, this.f$1, (TLRPC.messages_Chats) r2, (TLRPC.TL_error) r3);
    }
}
