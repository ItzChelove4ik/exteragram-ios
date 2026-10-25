package com.exteragram.messenger.feed;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes4.dex */
public final /* synthetic */ class FeedChatIntegration$$ExternalSyntheticLambda3 implements RequestDelegate {
    public final /* synthetic */ FeedChatIntegration f$0;

    public /* synthetic */ FeedChatIntegration$$ExternalSyntheticLambda3(FeedChatIntegration r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r1, TLRPC.TL_error r2) {
        FeedChatIntegration.$r8$lambda$gHBSJTMboFh6vfFPA1QzTJV6ZZg(this.f$0, r1, r2);
    }
}
