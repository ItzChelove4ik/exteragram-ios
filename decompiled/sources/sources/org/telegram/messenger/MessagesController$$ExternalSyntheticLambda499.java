package org.telegram.messenger;

import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class MessagesController$$ExternalSyntheticLambda499 implements Runnable {
    public final /* synthetic */ MessagesController f$0;
    public final /* synthetic */ long f$1;
    public final /* synthetic */ TLRPC.TL_chatOnlines f$2;

    public /* synthetic */ MessagesController$$ExternalSyntheticLambda499(MessagesController r1, long r2, TLRPC.TL_chatOnlines r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        MessagesController.m4625$r8$lambda$qBdEmfwzp0xZ1EgKyLzYS2jiAo(this.f$0, this.f$1, this.f$2);
    }
}
