package org.telegram.messenger;

import java.util.concurrent.CountDownLatch;

/* loaded from: classes5.dex */
public final /* synthetic */ class MessagesStorage$$ExternalSyntheticLambda204 implements Runnable {
    public final /* synthetic */ MessagesStorage f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ boolean[] f$2;
    public final /* synthetic */ CountDownLatch f$3;

    public /* synthetic */ MessagesStorage$$ExternalSyntheticLambda204(MessagesStorage r1, int r2, boolean[] r3, CountDownLatch r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        MessagesStorage.m4761$r8$lambda$pvJY6rNH13VllXuJO5zfPxzFk(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
