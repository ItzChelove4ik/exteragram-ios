package com.exteragram.messenger.feed.ads;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes4.dex */
public final /* synthetic */ class FeedAdController$$ExternalSyntheticLambda4 implements Runnable {
    public final /* synthetic */ TLRPC.TL_error f$0;
    public final /* synthetic */ TLObject f$1;
    public final /* synthetic */ Utilities.Callback2 f$2;

    public /* synthetic */ FeedAdController$$ExternalSyntheticLambda4(TLRPC.TL_error r1, TLObject r2, Utilities.Callback2 r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        FeedAdController.m1424$r8$lambda$suri1kpT0eJ8sN4FqCkAUB1c0c(this.f$0, this.f$1, this.f$2);
    }
}
