package org.telegram.messenger;

import org.telegram.tgnet.TLRPC;

/* loaded from: classes.dex */
public final /* synthetic */ class MessagesStorage$$ExternalSyntheticLambda31 implements Runnable {
    public final /* synthetic */ MessagesStorage f$0;
    public final /* synthetic */ TLRPC.Dialog f$1;
    public final /* synthetic */ TLRPC.InputPeer f$2;
    public final /* synthetic */ long f$3;

    public /* synthetic */ MessagesStorage$$ExternalSyntheticLambda31(MessagesStorage r1, TLRPC.Dialog r2, TLRPC.InputPeer r3, long r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        MessagesStorage.m4709$r8$lambda$M_A2levZ4WCHwpF8PtNDbTQ8I(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
