package org.telegram.messenger;

import org.telegram.messenger.SendMessagesHelper;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class SendMessagesHelper$$ExternalSyntheticLambda101 implements RequestDelegate {
    public final /* synthetic */ SendMessagesHelper f$0;
    public final /* synthetic */ SendMessagesHelper.DelayedMessage f$1;
    public final /* synthetic */ String f$2;

    public /* synthetic */ SendMessagesHelper$$ExternalSyntheticLambda101(SendMessagesHelper r1, SendMessagesHelper.DelayedMessage r2, String r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r3, TLRPC.TL_error r4) {
        SendMessagesHelper.m4914$r8$lambda$F4uqV5VeF_RmT14JcziFw8B3xc(this.f$0, this.f$1, this.f$2, r3, r4);
    }
}
