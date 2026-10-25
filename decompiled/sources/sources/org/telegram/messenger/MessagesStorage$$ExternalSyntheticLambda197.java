package org.telegram.messenger;

import java.util.concurrent.CountDownLatch;

/* loaded from: classes5.dex */
public final /* synthetic */ class MessagesStorage$$ExternalSyntheticLambda197 implements Runnable {
    public final /* synthetic */ MessagesStorage f$0;
    public final /* synthetic */ long f$1;
    public final /* synthetic */ int f$2;
    public final /* synthetic */ boolean[] f$3;
    public final /* synthetic */ CountDownLatch f$4;

    public /* synthetic */ MessagesStorage$$ExternalSyntheticLambda197(MessagesStorage r1, long r2, int r4, boolean[] r5, CountDownLatch r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r4;
        this.f$3 = r5;
        this.f$4 = r6;
    }

    @Override // java.lang.Runnable
    public final void run() {
        MessagesStorage.$r8$lambda$TEQQiDbWcfyQiSkVEzTZn7iSk_Y(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
