package org.telegram.ui;

import org.telegram.messenger.MessageObject;
import org.telegram.messenger.Utilities;
import org.telegram.ui.ChatActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChatActivity$ChatMessageCellDelegate$$ExternalSyntheticLambda51 implements Utilities.Callback {
    public final /* synthetic */ ChatActivity.ChatMessageCellDelegate f$0;
    public final /* synthetic */ MessageObject f$1;

    public /* synthetic */ ChatActivity$ChatMessageCellDelegate$$ExternalSyntheticLambda51(ChatActivity.ChatMessageCellDelegate r1, MessageObject r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r2) {
        ChatActivity.ChatMessageCellDelegate.$r8$lambda$PXhfKAfZ6ptT8Pc2jvmxSdlnn7M(this.f$0, this.f$1, (Long) r2);
    }
}
