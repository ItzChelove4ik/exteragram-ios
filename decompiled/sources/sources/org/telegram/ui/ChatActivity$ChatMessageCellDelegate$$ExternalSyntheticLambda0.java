package org.telegram.ui;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Cells.ChatMessageCell;
import org.telegram.ui.ChatActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChatActivity$ChatMessageCellDelegate$$ExternalSyntheticLambda0 implements Utilities.Callback2 {
    public final /* synthetic */ ChatActivity.ChatMessageCellDelegate f$0;
    public final /* synthetic */ ChatMessageCell f$1;

    public /* synthetic */ ChatActivity$ChatMessageCellDelegate$$ExternalSyntheticLambda0(ChatActivity.ChatMessageCellDelegate r1, ChatMessageCell r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r2, Object r3) {
        ChatActivity.ChatMessageCellDelegate.$r8$lambda$wNXoUYVb7Js9Kw21iW3JzELEd9w(this.f$0, this.f$1, (TLRPC.TL_channels_channelParticipant) r2, (TLRPC.TL_error) r3);
    }
}
