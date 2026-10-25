package org.telegram.messenger;

import org.telegram.tgnet.QuickAckDelegate;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class SendMessagesHelper$$ExternalSyntheticLambda109 implements QuickAckDelegate {
    public final /* synthetic */ SendMessagesHelper f$0;
    public final /* synthetic */ TLRPC.Message f$1;

    public /* synthetic */ SendMessagesHelper$$ExternalSyntheticLambda109(SendMessagesHelper r1, TLRPC.Message r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.tgnet.QuickAckDelegate
    public final void run() {
        SendMessagesHelper.$r8$lambda$UvvhRMwtKSSKzlnkuLrFyAsWCks(this.f$0, this.f$1);
    }
}
