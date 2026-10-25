package org.telegram.messenger;

import java.util.concurrent.CountDownLatch;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes.dex */
public final /* synthetic */ class MessagesStorage$$ExternalSyntheticLambda249 implements Runnable {
    public final /* synthetic */ MessagesStorage f$0;
    public final /* synthetic */ TLRPC.Chat[] f$1;
    public final /* synthetic */ long f$2;
    public final /* synthetic */ CountDownLatch f$3;

    public /* synthetic */ MessagesStorage$$ExternalSyntheticLambda249(MessagesStorage r1, TLRPC.Chat[] r2, long r3, CountDownLatch r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r5;
    }

    @Override // java.lang.Runnable
    public final void run() {
        MessagesStorage.$r8$lambda$zAI5SfxXIj4EoPLSCvqM9Q77jxU(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
