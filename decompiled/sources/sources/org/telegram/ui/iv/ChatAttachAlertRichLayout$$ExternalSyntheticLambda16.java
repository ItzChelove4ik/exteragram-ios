package org.telegram.ui.iv;

import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Components.ChatAttachAlert;
import org.telegram.ui.Components.ChatAttachAlertLocationLayout;

/* loaded from: classes7.dex */
public final /* synthetic */ class ChatAttachAlertRichLayout$$ExternalSyntheticLambda16 implements ChatAttachAlertLocationLayout.LocationActivityDelegate {
    public final /* synthetic */ ChatAttachAlertRichLayout f$0;
    public final /* synthetic */ ChatAttachAlert f$1;

    public /* synthetic */ ChatAttachAlertRichLayout$$ExternalSyntheticLambda16(ChatAttachAlertRichLayout r1, ChatAttachAlert r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.ui.Components.ChatAttachAlertLocationLayout.LocationActivityDelegate
    public final void didSelectLocation(TLRPC.MessageMedia r9, int r10, boolean r11, int r12, long r13) {
        ChatAttachAlertRichLayout.$r8$lambda$pTPnhfFjt3pfL7D5HHHacDtiFq4(this.f$0, this.f$1, r9, r10, r11, r12, r13);
    }
}
