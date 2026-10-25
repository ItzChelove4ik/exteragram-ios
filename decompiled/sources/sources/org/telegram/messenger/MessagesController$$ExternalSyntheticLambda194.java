package org.telegram.messenger;

import org.telegram.messenger.MessagesController;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class MessagesController$$ExternalSyntheticLambda194 implements RequestDelegate {
    public final /* synthetic */ MessagesController f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ MessagesController.ChatlistUpdatesStat f$2;

    public /* synthetic */ MessagesController$$ExternalSyntheticLambda194(MessagesController r1, int r2, MessagesController.ChatlistUpdatesStat r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r3, TLRPC.TL_error r4) {
        MessagesController.$r8$lambda$gG4v0mJPCZxNaDzxcDBes5EY5M4(this.f$0, this.f$1, this.f$2, r3, r4);
    }
}
