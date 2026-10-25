package org.telegram.ui.iv;

import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Components.ChatAttachAlert;
import org.telegram.ui.Components.ChatAttachAlertLocationLayout;

/* loaded from: classes7.dex */
public final /* synthetic */ class ChatAttachAlertRichLayout$$ExternalSyntheticLambda18 implements ChatAttachAlertLocationLayout.LocationActivityDelegate {
    public final /* synthetic */ ChatAttachAlertRichLayout f$0;
    public final /* synthetic */ BlockRow f$1;
    public final /* synthetic */ ChatAttachAlert f$2;

    public /* synthetic */ ChatAttachAlertRichLayout$$ExternalSyntheticLambda18(ChatAttachAlertRichLayout r1, BlockRow r2, ChatAttachAlert r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.ui.Components.ChatAttachAlertLocationLayout.LocationActivityDelegate
    public final void didSelectLocation(TLRPC.MessageMedia r10, int r11, boolean r12, int r13, long r14) {
        ChatAttachAlertRichLayout.$r8$lambda$Vqj5jiHbz40PIawjVspmypVBH1k(this.f$0, this.f$1, this.f$2, r10, r11, r12, r13, r14);
    }
}
