package org.telegram.messenger;

import org.telegram.messenger.SendMessagesHelper;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class SendMessagesHelper$$ExternalSyntheticLambda61 implements RequestDelegate {
    public final /* synthetic */ SendMessagesHelper f$0;
    public final /* synthetic */ TLRPC.InputMedia f$1;
    public final /* synthetic */ SendMessagesHelper.DelayedMessage f$2;

    public /* synthetic */ SendMessagesHelper$$ExternalSyntheticLambda61(SendMessagesHelper r1, TLRPC.InputMedia r2, SendMessagesHelper.DelayedMessage r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r3, TLRPC.TL_error r4) {
        SendMessagesHelper.$r8$lambda$yBKJn6U533mDrzowRIEZ6KfxQRw(this.f$0, this.f$1, this.f$2, r3, r4);
    }
}
