package org.telegram.messenger;

import org.telegram.messenger.MessagesController;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class MessagesController$$ExternalSyntheticLambda530 implements Runnable {
    public final /* synthetic */ MessagesController f$0;
    public final /* synthetic */ boolean[] f$1;
    public final /* synthetic */ TLRPC.Chat f$2;
    public final /* synthetic */ Runnable[] f$3;
    public final /* synthetic */ long f$4;
    public final /* synthetic */ int f$5;
    public final /* synthetic */ MessagesController.MessagesLoadedCallback f$6;

    public /* synthetic */ MessagesController$$ExternalSyntheticLambda530(MessagesController r1, boolean[] r2, TLRPC.Chat r3, Runnable[] r4, long r5, int r7, MessagesController.MessagesLoadedCallback r8) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r7;
        this.f$6 = r8;
    }

    @Override // java.lang.Runnable
    public final void run() {
        MessagesController.$r8$lambda$RBfbv6Tv3CgeH2BiexFvp7UZncs(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6);
    }
}
