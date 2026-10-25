package org.telegram.messenger;

import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class MessagesController$$ExternalSyntheticLambda537 implements Runnable {
    public final /* synthetic */ MessagesController f$0;
    public final /* synthetic */ TLRPC.TL_help_promoData f$1;
    public final /* synthetic */ TLRPC.TL_messages_peerDialogs f$2;
    public final /* synthetic */ long f$3;

    public /* synthetic */ MessagesController$$ExternalSyntheticLambda537(MessagesController r1, TLRPC.TL_help_promoData r2, TLRPC.TL_messages_peerDialogs r3, long r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        MessagesController.m4564$r8$lambda$TgTwxm9DttCTvJ773cCwhuVb0(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
