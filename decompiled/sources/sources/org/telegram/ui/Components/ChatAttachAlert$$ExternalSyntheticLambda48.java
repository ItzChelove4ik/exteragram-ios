package org.telegram.ui.Components;

import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Components.ChatAttachAlertLocationLayout;

/* loaded from: classes7.dex */
public final /* synthetic */ class ChatAttachAlert$$ExternalSyntheticLambda48 implements ChatAttachAlertLocationLayout.LocationActivityDelegate {
    public final /* synthetic */ ChatAttachAlert f$0;

    public /* synthetic */ ChatAttachAlert$$ExternalSyntheticLambda48(ChatAttachAlert r1) {
        this.f$0 = r1;
    }

    @Override // org.telegram.ui.Components.ChatAttachAlertLocationLayout.LocationActivityDelegate
    public final void didSelectLocation(TLRPC.MessageMedia r1, int r2, boolean r3, int r4, long r5) {
        ChatAttachAlert.m9409$r8$lambda$QeU4qhmIsxgdBdGtLBvHApFegY(this.f$0, r1, r2, r3, r4, r5);
    }
}
