package org.telegram.messenger;

import org.telegram.messenger.MessagesController;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class MessagesController$$ExternalSyntheticLambda174 implements RequestDelegate {
    public final /* synthetic */ MessagesController f$0;
    public final /* synthetic */ long f$1;
    public final /* synthetic */ MessagesController.SponsoredMessagesInfo f$2;

    public /* synthetic */ MessagesController$$ExternalSyntheticLambda174(MessagesController r1, long r2, MessagesController.SponsoredMessagesInfo r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r4;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r7, TLRPC.TL_error r8) {
        MessagesController.$r8$lambda$1rAOkyE_ZUoqOExyrTOjeN_RNB8(this.f$0, this.f$1, this.f$2, r7, r8);
    }
}
