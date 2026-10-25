package org.telegram.ui.Components;

import org.telegram.messenger.MessagesController;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public final /* synthetic */ class TopicsTabsView$$ExternalSyntheticLambda15 implements Runnable {
    public final /* synthetic */ TopicsTabsView f$0;
    public final /* synthetic */ MessagesController f$1;
    public final /* synthetic */ TLRPC.TL_forumTopic f$2;
    public final /* synthetic */ ItemOptions f$3;
    public final /* synthetic */ ItemOptions f$4;

    public /* synthetic */ TopicsTabsView$$ExternalSyntheticLambda15(TopicsTabsView r1, MessagesController r2, TLRPC.TL_forumTopic r3, ItemOptions r4, ItemOptions r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // java.lang.Runnable
    public final void run() {
        TopicsTabsView.m13325$r8$lambda$eGnPIWHa_04cRgBeAqXNmEut1s(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
