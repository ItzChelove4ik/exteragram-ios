package org.telegram.ui;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.tl.TL_account;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.Cells.ChatMessageCell;
import org.telegram.ui.ChatActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChatActivity$ChatMessageCellDelegate$$ExternalSyntheticLambda21 implements Utilities.Callback {
    public final /* synthetic */ ChatActivity.ChatMessageCellDelegate f$0;
    public final /* synthetic */ AlertDialog f$1;
    public final /* synthetic */ ChatMessageCell f$2;

    public /* synthetic */ ChatActivity$ChatMessageCellDelegate$$ExternalSyntheticLambda21(ChatActivity.ChatMessageCellDelegate r1, AlertDialog r2, ChatMessageCell r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.messenger.Utilities.Callback
    public final void run(Object r3) {
        ChatActivity.ChatMessageCellDelegate.$r8$lambda$L3bWG1CHlObo1YTIl8mKL8KQBAY(this.f$0, this.f$1, this.f$2, (TL_account.contentSettings) r3);
    }
}
