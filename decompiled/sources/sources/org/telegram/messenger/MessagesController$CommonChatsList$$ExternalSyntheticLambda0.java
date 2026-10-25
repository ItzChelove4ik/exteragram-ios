package org.telegram.messenger;

import org.telegram.messenger.MessagesController;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class MessagesController$CommonChatsList$$ExternalSyntheticLambda0 implements RequestDelegate {
    public final /* synthetic */ MessagesController.CommonChatsList f$0;
    public final /* synthetic */ int[] f$1;
    public final /* synthetic */ boolean f$2;

    public /* synthetic */ MessagesController$CommonChatsList$$ExternalSyntheticLambda0(MessagesController.CommonChatsList r1, int[] r2, boolean r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r3, TLRPC.TL_error r4) {
        MessagesController.CommonChatsList.$r8$lambda$sw8YVIvy0H62sLl4j7YcLibVBCI(this.f$0, this.f$1, this.f$2, r3, r4);
    }
}
