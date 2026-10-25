package org.telegram.ui;

import org.telegram.tgnet.tl.TL_account;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.Cells.ChatMessageCell;
import org.telegram.ui.ChatActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChatActivity$ChatMessageCellDelegate$$ExternalSyntheticLambda28 implements AlertDialog.OnButtonClickListener {
    public final /* synthetic */ ChatActivity.ChatMessageCellDelegate f$0;
    public final /* synthetic */ ChatMessageCell f$1;
    public final /* synthetic */ boolean[] f$2;
    public final /* synthetic */ boolean f$3;
    public final /* synthetic */ TL_account.contentSettings f$4;

    public /* synthetic */ ChatActivity$ChatMessageCellDelegate$$ExternalSyntheticLambda28(ChatActivity.ChatMessageCellDelegate r1, ChatMessageCell r2, boolean[] r3, boolean r4, TL_account.contentSettings r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // org.telegram.ui.ActionBar.AlertDialog.OnButtonClickListener
    public final void onClick(AlertDialog r8, int r9) {
        ChatActivity.ChatMessageCellDelegate.m8209$r8$lambda$8A5VWk6HxQoUoPClMyXe8d83UI(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, r8, r9);
    }
}
