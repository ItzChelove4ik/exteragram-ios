package org.telegram.messenger;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes.dex */
public final /* synthetic */ class MessagesController$$ExternalSyntheticLambda118 implements RequestDelegate {
    public final /* synthetic */ MessagesController f$0;
    public final /* synthetic */ long f$1;
    public final /* synthetic */ TLRPC.TL_messages_getMessagesViews f$2;

    public /* synthetic */ MessagesController$$ExternalSyntheticLambda118(MessagesController r1, long r2, TLRPC.TL_messages_getMessagesViews r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r4;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r7, TLRPC.TL_error r8) {
        MessagesController.m4517$r8$lambda$CDNyPXLOa81U3Bp6S6FOjaskpw(this.f$0, this.f$1, this.f$2, r7, r8);
    }
}
