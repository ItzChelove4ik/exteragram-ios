package org.telegram.ui.Components;

import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Components.SharedMediaLayout;

/* loaded from: classes7.dex */
public final /* synthetic */ class SharedMediaLayout$SavedMessagesSearchAdapter$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ SharedMediaLayout.SavedMessagesSearchAdapter f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ TLRPC.TL_messages_search f$2;

    public /* synthetic */ SharedMediaLayout$SavedMessagesSearchAdapter$$ExternalSyntheticLambda1(SharedMediaLayout.SavedMessagesSearchAdapter r1, int r2, TLRPC.TL_messages_search r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        SharedMediaLayout.SavedMessagesSearchAdapter.$r8$lambda$CYmyU43KS4DISbXMF70I3UoswRw(this.f$0, this.f$1, this.f$2);
    }
}
