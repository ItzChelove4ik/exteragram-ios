package org.telegram.messenger;

import org.telegram.messenger.SendMessagesHelper;
import org.telegram.tgnet.TLObject;

/* loaded from: classes5.dex */
public final /* synthetic */ class SendMessagesHelper$$ExternalSyntheticLambda43 implements Runnable {
    public final /* synthetic */ SendMessagesHelper f$0;
    public final /* synthetic */ TLObject f$1;
    public final /* synthetic */ SendMessagesHelper.DelayedMessage f$2;

    public /* synthetic */ SendMessagesHelper$$ExternalSyntheticLambda43(SendMessagesHelper r1, TLObject r2, SendMessagesHelper.DelayedMessage r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        SendMessagesHelper.m4924$r8$lambda$Z0mmRoe_3ZqiIuyBCStRviR0tU(this.f$0, this.f$1, this.f$2);
    }
}
