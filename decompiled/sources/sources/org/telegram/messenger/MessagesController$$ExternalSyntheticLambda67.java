package org.telegram.messenger;

import org.telegram.messenger.MessagesController;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class MessagesController$$ExternalSyntheticLambda67 implements RequestDelegate {
    public final /* synthetic */ MessagesController.IsInChatCheckedCallback f$0;

    public /* synthetic */ MessagesController$$ExternalSyntheticLambda67(MessagesController.IsInChatCheckedCallback r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r1, TLRPC.TL_error r2) {
        MessagesController.m4644$r8$lambda$vcm1p8SA8yqeWsAQH5BtBWx2kg(this.f$0, r1, r2);
    }
}
