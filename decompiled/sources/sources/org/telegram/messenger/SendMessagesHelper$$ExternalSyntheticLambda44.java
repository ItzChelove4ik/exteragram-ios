package org.telegram.messenger;

import org.telegram.messenger.SendMessagesHelper;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class SendMessagesHelper$$ExternalSyntheticLambda44 implements Runnable {
    public final /* synthetic */ SendMessagesHelper f$0;
    public final /* synthetic */ TLObject f$1;
    public final /* synthetic */ TLRPC.InputMedia f$2;
    public final /* synthetic */ SendMessagesHelper.DelayedMessage f$3;

    public /* synthetic */ SendMessagesHelper$$ExternalSyntheticLambda44(SendMessagesHelper r1, TLObject r2, TLRPC.InputMedia r3, SendMessagesHelper.DelayedMessage r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        SendMessagesHelper.$r8$lambda$j7_k75OGQBNtdyt69Qpi_PQDRng(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
