package com.exteragram.messenger.feed.ads;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes4.dex */
public final /* synthetic */ class FeedAdController$$ExternalSyntheticLambda2 implements Utilities.Callback {
    public final /* synthetic */ TLRPC.TL_messages_getHistory f$0;
    public final /* synthetic */ Runnable f$1;
    public final /* synthetic */ Utilities.Callback2 f$2;

    public /* synthetic */ FeedAdController$$ExternalSyntheticLambda2(TLRPC.TL_messages_getHistory r1, Runnable r2, Utilities.Callback2 r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r3) {
        FeedAdController.$r8$lambda$F74A0h3yQjwWcCURFdXXPBFC7aY(this.f$0, this.f$1, this.f$2, (TLRPC.Chat) r3);
    }
}
