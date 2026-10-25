package org.telegram.ui;

import org.telegram.messenger.MessagesStorage;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.TopicsFragment;

/* loaded from: classes6.dex */
public final /* synthetic */ class TopicsFragment$2$$ExternalSyntheticLambda2 implements MessagesStorage.BooleanCallback {
    public final /* synthetic */ TopicsFragment.AnonymousClass2 f$0;
    public final /* synthetic */ TLRPC.Chat f$1;

    public /* synthetic */ TopicsFragment$2$$ExternalSyntheticLambda2(TopicsFragment.AnonymousClass2 r1, TLRPC.Chat r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.MessagesStorage.BooleanCallback
    public final void run(boolean r2) {
        TopicsFragment.AnonymousClass2.$r8$lambda$pYosptSIHGeb1N5SAhAi9CVhPGQ(this.f$0, this.f$1, r2);
    }
}
