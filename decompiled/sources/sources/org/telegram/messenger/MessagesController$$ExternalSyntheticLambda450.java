package org.telegram.messenger;

import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class MessagesController$$ExternalSyntheticLambda450 implements Runnable {
    public final /* synthetic */ MessagesController f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ TLRPC.TL_messages_getHistory f$2;
    public final /* synthetic */ TLRPC.TL_error f$3;

    public /* synthetic */ MessagesController$$ExternalSyntheticLambda450(MessagesController r1, int r2, TLRPC.TL_messages_getHistory r3, TLRPC.TL_error r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        MessagesController.$r8$lambda$ycQEppkuNBx58quHNzbHkpIvw8c(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
