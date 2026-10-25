package org.telegram.ui;

import org.telegram.messenger.MessageObject;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.Components.AlertsCreator;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChatActivity$ChatMessageCellDelegate$$ExternalSyntheticLambda54 implements AlertsCreator.ScheduleDatePickerDelegate {
    public final /* synthetic */ ChatActivity.ChatMessageCellDelegate f$0;
    public final /* synthetic */ MessageObject f$1;

    public /* synthetic */ ChatActivity$ChatMessageCellDelegate$$ExternalSyntheticLambda54(ChatActivity.ChatMessageCellDelegate r1, MessageObject r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.ui.Components.AlertsCreator.ScheduleDatePickerDelegate
    public final void didSelectDate(boolean r2, int r3, int r4) {
        ChatActivity.ChatMessageCellDelegate.$r8$lambda$slSJwSfsyqnDkxv99hc2fxa6KL4(this.f$0, this.f$1, r2, r3, r4);
    }
}
