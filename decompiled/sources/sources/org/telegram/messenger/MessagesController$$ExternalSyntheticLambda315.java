package org.telegram.messenger;

import org.telegram.tgnet.TLRPC;

/* loaded from: classes.dex */
public final /* synthetic */ class MessagesController$$ExternalSyntheticLambda315 implements Runnable {
    public final /* synthetic */ MessagesController f$0;
    public final /* synthetic */ long f$1;
    public final /* synthetic */ TLRPC.TL_message f$2;

    public /* synthetic */ MessagesController$$ExternalSyntheticLambda315(MessagesController r1, long r2, TLRPC.TL_message r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        MessagesController.$r8$lambda$YE54uhRYocfPXWpcZsZHeenHEpw(this.f$0, this.f$1, this.f$2);
    }
}
