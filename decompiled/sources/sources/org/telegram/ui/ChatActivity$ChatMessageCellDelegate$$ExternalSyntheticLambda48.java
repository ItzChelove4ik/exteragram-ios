package org.telegram.ui;

import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ChatActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChatActivity$ChatMessageCellDelegate$$ExternalSyntheticLambda48 implements Runnable {
    public final /* synthetic */ ChatActivity.ChatMessageCellDelegate f$0;
    public final /* synthetic */ TLRPC.Chat f$1;

    public /* synthetic */ ChatActivity$ChatMessageCellDelegate$$ExternalSyntheticLambda48(ChatActivity.ChatMessageCellDelegate r1, TLRPC.Chat r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ChatActivity.ChatMessageCellDelegate.$r8$lambda$yBE4HC1cGQJNrjln0GK06S_iQbI(this.f$0, this.f$1);
    }
}
