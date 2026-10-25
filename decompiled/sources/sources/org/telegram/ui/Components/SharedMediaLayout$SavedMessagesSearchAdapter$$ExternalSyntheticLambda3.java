package org.telegram.ui.Components;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Components.SharedMediaLayout;

/* loaded from: classes7.dex */
public final /* synthetic */ class SharedMediaLayout$SavedMessagesSearchAdapter$$ExternalSyntheticLambda3 implements RequestDelegate {
    public final /* synthetic */ SharedMediaLayout.SavedMessagesSearchAdapter f$0;
    public final /* synthetic */ int f$1;

    public /* synthetic */ SharedMediaLayout$SavedMessagesSearchAdapter$$ExternalSyntheticLambda3(SharedMediaLayout.SavedMessagesSearchAdapter r1, int r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r2, TLRPC.TL_error r3) {
        SharedMediaLayout.SavedMessagesSearchAdapter.m12983$r8$lambda$20jNDy4m7EmvjeSy6Xrse20V8s(this.f$0, this.f$1, r2, r3);
    }
}
