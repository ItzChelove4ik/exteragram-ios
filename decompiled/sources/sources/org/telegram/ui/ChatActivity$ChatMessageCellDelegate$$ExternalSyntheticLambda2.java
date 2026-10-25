package org.telegram.ui;

import org.telegram.messenger.MessageObject;
import org.telegram.messenger.Utilities;
import org.telegram.ui.ChatActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChatActivity$ChatMessageCellDelegate$$ExternalSyntheticLambda2 implements Utilities.Callback {
    public final /* synthetic */ ChatActivity.ChatMessageCellDelegate f$0;
    public final /* synthetic */ MessageObject f$1;
    public final /* synthetic */ Runnable f$2;

    public /* synthetic */ ChatActivity$ChatMessageCellDelegate$$ExternalSyntheticLambda2(ChatActivity.ChatMessageCellDelegate r1, MessageObject r2, Runnable r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r3) {
        ChatActivity.ChatMessageCellDelegate.$r8$lambda$723SfoZgUK5PPMXYBfJqJwFSCZM(this.f$0, this.f$1, this.f$2, (Boolean) r3);
    }
}
