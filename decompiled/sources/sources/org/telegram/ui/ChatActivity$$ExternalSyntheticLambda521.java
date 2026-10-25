package org.telegram.ui;

import org.telegram.messenger.MessagesStorage;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChatActivity$$ExternalSyntheticLambda521 implements Runnable {
    public final /* synthetic */ ChatActivity f$0;
    public final /* synthetic */ TLObject f$1;
    public final /* synthetic */ TLRPC.TL_error f$2;
    public final /* synthetic */ MessagesStorage f$3;

    public /* synthetic */ ChatActivity$$ExternalSyntheticLambda521(ChatActivity r1, TLObject r2, TLRPC.TL_error r3, MessagesStorage r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ChatActivity.$r8$lambda$Rw4pD8k1J8sI9yaEhLuCg05YHQ8(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
