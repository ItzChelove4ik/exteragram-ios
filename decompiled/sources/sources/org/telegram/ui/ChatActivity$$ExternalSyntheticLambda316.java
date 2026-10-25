package org.telegram.ui;

import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Components.AlertsCreator;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChatActivity$$ExternalSyntheticLambda316 implements AlertsCreator.ScheduleDatePickerDelegate {
    public final /* synthetic */ ChatActivity f$0;
    public final /* synthetic */ TLRPC.BotInlineResult f$1;
    public final /* synthetic */ Long f$2;

    public /* synthetic */ ChatActivity$$ExternalSyntheticLambda316(ChatActivity r1, TLRPC.BotInlineResult r2, Long r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.ui.Components.AlertsCreator.ScheduleDatePickerDelegate
    public final void didSelectDate(boolean r7, int r8, int r9) {
        ChatActivity.$r8$lambda$Cdg9BLS0GkkB31SsZ8OJqma3rkE(this.f$0, this.f$1, this.f$2, r7, r8, r9);
    }
}
