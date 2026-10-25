package com.exteragram.messenger.feed;

import java.util.function.Consumer;
import org.telegram.messenger.MessagesStorage;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BaseFragment;

/* loaded from: classes4.dex */
public final /* synthetic */ class FeedChannelActions$$ExternalSyntheticLambda0 implements MessagesStorage.BooleanCallback {
    public final /* synthetic */ TLRPC.Chat f$0;
    public final /* synthetic */ BaseFragment f$1;
    public final /* synthetic */ Consumer f$2;
    public final /* synthetic */ Runnable f$3;

    public /* synthetic */ FeedChannelActions$$ExternalSyntheticLambda0(TLRPC.Chat r1, BaseFragment r2, Consumer r3, Runnable r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.messenger.MessagesStorage.BooleanCallback
    public final void run(boolean r4) {
        FeedChannelActions.m1408$r8$lambda$93MDwBmj6nCXiUj2TGXsm0wccI(this.f$0, this.f$1, this.f$2, this.f$3, r4);
    }
}
