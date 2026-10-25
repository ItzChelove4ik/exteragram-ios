package org.telegram.ui.iv;

import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Components.ChatAttachAlert;
import org.telegram.ui.Components.ChatAttachAlertLocationLayout;

/* loaded from: classes7.dex */
public final /* synthetic */ class RichEditor$$ExternalSyntheticLambda57 implements ChatAttachAlertLocationLayout.LocationActivityDelegate {
    public final /* synthetic */ RichEditor f$0;
    public final /* synthetic */ ChatAttachAlert f$1;

    public /* synthetic */ RichEditor$$ExternalSyntheticLambda57(RichEditor r1, ChatAttachAlert r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.ui.Components.ChatAttachAlertLocationLayout.LocationActivityDelegate
    public final void didSelectLocation(TLRPC.MessageMedia r9, int r10, boolean r11, int r12, long r13) {
        RichEditor.m21664$r8$lambda$JR9rvaYYSAXnNJJIjHgLQBIpXI(this.f$0, this.f$1, r9, r10, r11, r12, r13);
    }
}
