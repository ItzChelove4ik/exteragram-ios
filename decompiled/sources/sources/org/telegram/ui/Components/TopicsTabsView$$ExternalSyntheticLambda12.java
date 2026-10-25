package org.telegram.ui.Components;

import org.telegram.messenger.MessagesController;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public final /* synthetic */ class TopicsTabsView$$ExternalSyntheticLambda12 implements Runnable {
    public final /* synthetic */ TopicsTabsView f$0;
    public final /* synthetic */ ItemOptions f$1;
    public final /* synthetic */ MessagesController f$2;
    public final /* synthetic */ TLRPC.TL_forumTopic f$3;

    public /* synthetic */ TopicsTabsView$$ExternalSyntheticLambda12(TopicsTabsView r1, ItemOptions r2, MessagesController r3, TLRPC.TL_forumTopic r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        TopicsTabsView.m13327$r8$lambda$pdWT5MgVBrOyvP7PWCQvFkc7N8(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
