package com.exteragram.messenger.feed;

import java.util.function.Consumer;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ChatActivity;

/* loaded from: classes4.dex */
public final /* synthetic */ class FeedChannelActions$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ ChatActivity f$0;
    public final /* synthetic */ TLRPC.Chat f$1;
    public final /* synthetic */ Runnable f$2;
    public final /* synthetic */ Consumer f$3;

    public /* synthetic */ FeedChannelActions$$ExternalSyntheticLambda2(ChatActivity r1, TLRPC.Chat r2, Runnable r3, Consumer r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        FeedChannelActions.$r8$lambda$EHE5Su0wUEoEcAcmBHxtia6M4Vc(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
