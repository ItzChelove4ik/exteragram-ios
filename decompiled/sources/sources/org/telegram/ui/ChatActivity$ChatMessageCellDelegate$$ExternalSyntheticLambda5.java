package org.telegram.ui;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ChatActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChatActivity$ChatMessageCellDelegate$$ExternalSyntheticLambda5 implements Utilities.Callback {
    public final /* synthetic */ ChatActivity.ChatMessageCellDelegate f$0;
    public final /* synthetic */ TLRPC.Message f$1;

    public /* synthetic */ ChatActivity$ChatMessageCellDelegate$$ExternalSyntheticLambda5(ChatActivity.ChatMessageCellDelegate r1, TLRPC.Message r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r2) {
        ChatActivity.ChatMessageCellDelegate.$r8$lambda$dH0_Lz50KLsLG6dM45T83dW9ORE(this.f$0, this.f$1, (String) r2);
    }
}
