package org.telegram.messenger;

import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class MessagesController$$ExternalSyntheticLambda192 implements Runnable {
    public final /* synthetic */ MessagesController f$0;
    public final /* synthetic */ TLRPC.TL_channels_sendAsPeers f$1;

    public /* synthetic */ MessagesController$$ExternalSyntheticLambda192(MessagesController r1, TLRPC.TL_channels_sendAsPeers r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        MessagesController.$r8$lambda$X2R0hJkIJLOolW6ft33kdpnQScU(this.f$0, this.f$1);
    }
}
