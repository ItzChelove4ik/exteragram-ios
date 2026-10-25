package com.exteragram.messenger.feed;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ChatActivity;

/* loaded from: classes4.dex */
public final /* synthetic */ class FeedMessageUtils$$ExternalSyntheticLambda0 implements RequestDelegate {
    public final /* synthetic */ ChatActivity f$0;

    public /* synthetic */ FeedMessageUtils$$ExternalSyntheticLambda0(ChatActivity r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r1, TLRPC.TL_error r2) {
        FeedMessageUtils.$r8$lambda$XflDMlSYrgi89Wgpq48L5M4D8XE(this.f$0, r1, r2);
    }
}
