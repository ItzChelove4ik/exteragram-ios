package org.telegram.ui;

import org.telegram.tgnet.TLRPC;
import org.telegram.ui.AvatarPreviewer;
import org.telegram.ui.Cells.ChatMessageCell;
import org.telegram.ui.ChatActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChatActivity$ChatMessageCellDelegate$$ExternalSyntheticLambda44 implements AvatarPreviewer.Callback {
    public final /* synthetic */ ChatActivity.ChatMessageCellDelegate f$0;
    public final /* synthetic */ TLRPC.Chat f$1;
    public final /* synthetic */ ChatMessageCell f$2;

    public /* synthetic */ ChatActivity$ChatMessageCellDelegate$$ExternalSyntheticLambda44(ChatActivity.ChatMessageCellDelegate r1, TLRPC.Chat r2, ChatMessageCell r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.ui.AvatarPreviewer.Callback
    public final void onMenuClick(AvatarPreviewer.MenuItem r3) {
        ChatActivity.ChatMessageCellDelegate.$r8$lambda$Pbw7ryapWHvvLgz94rUOd9VtrAc(this.f$0, this.f$1, this.f$2, r3);
    }
}
