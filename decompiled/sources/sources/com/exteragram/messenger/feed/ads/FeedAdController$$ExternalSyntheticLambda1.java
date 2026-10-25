package com.exteragram.messenger.feed.ads;

import org.telegram.messenger.AccountInstance;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes4.dex */
public final /* synthetic */ class FeedAdController$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ AccountInstance f$0;
    public final /* synthetic */ TLRPC.TL_messages_getHistory f$1;
    public final /* synthetic */ Utilities.Callback2 f$2;

    public /* synthetic */ FeedAdController$$ExternalSyntheticLambda1(AccountInstance r1, TLRPC.TL_messages_getHistory r2, Utilities.Callback2 r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        FeedAdController.$r8$lambda$r4x3bpNjl9gEMOc_86Ljc5rjyUQ(this.f$0, this.f$1, this.f$2);
    }
}
