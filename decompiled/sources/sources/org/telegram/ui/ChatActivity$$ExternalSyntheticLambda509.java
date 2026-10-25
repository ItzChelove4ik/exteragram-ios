package org.telegram.ui;

import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChatActivity$$ExternalSyntheticLambda509 implements Runnable {
    public final /* synthetic */ ChatActivity f$0;
    public final /* synthetic */ TLRPC.TL_error f$1;
    public final /* synthetic */ TLRPC.TL_messages_editMessage f$2;

    public /* synthetic */ ChatActivity$$ExternalSyntheticLambda509(ChatActivity r1, TLRPC.TL_error r2, TLRPC.TL_messages_editMessage r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ChatActivity.$r8$lambda$lZlpqUpMwSGHQFxBj4TA8Qs4eKo(this.f$0, this.f$1, this.f$2);
    }
}
