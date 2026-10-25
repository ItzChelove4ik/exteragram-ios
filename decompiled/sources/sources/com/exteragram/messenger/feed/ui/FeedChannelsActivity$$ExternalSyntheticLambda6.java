package com.exteragram.messenger.feed.ui;

import androidx.core.util.Consumer;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes4.dex */
public final /* synthetic */ class FeedChannelsActivity$$ExternalSyntheticLambda6 implements Consumer {
    public final /* synthetic */ FeedChannelsActivity f$0;
    public final /* synthetic */ TLRPC.Chat f$1;

    public /* synthetic */ FeedChannelsActivity$$ExternalSyntheticLambda6(FeedChannelsActivity r1, TLRPC.Chat r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // androidx.core.util.Consumer
    public final void accept(Object r2) {
        FeedChannelsActivity.$r8$lambda$jwCrvnnUGaVtUIzGRQQdgmPoqhM(this.f$0, this.f$1, ((Boolean) r2).booleanValue());
    }
}
