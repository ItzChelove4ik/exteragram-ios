package org.telegram.messenger;

import org.telegram.tgnet.TLRPC;

/* loaded from: classes.dex */
public final /* synthetic */ class MessagesStorage$$ExternalSyntheticLambda32 implements Runnable {
    public final /* synthetic */ MessagesStorage f$0;
    public final /* synthetic */ long f$1;
    public final /* synthetic */ boolean f$2;
    public final /* synthetic */ TLRPC.InputPeer f$3;
    public final /* synthetic */ long f$4;

    public /* synthetic */ MessagesStorage$$ExternalSyntheticLambda32(MessagesStorage r1, long r2, boolean r4, TLRPC.InputPeer r5, long r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r4;
        this.f$3 = r5;
        this.f$4 = r6;
    }

    @Override // java.lang.Runnable
    public final void run() {
        MessagesStorage.$r8$lambda$uiR9_jzGnyvMawmHPoD55aiCUts(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
