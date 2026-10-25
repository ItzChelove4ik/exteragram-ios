package org.telegram.ui;

import java.util.HashSet;
import org.telegram.messenger.MessageObject;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ChatActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChatActivity$ChatMessageCellDelegate$$ExternalSyntheticLambda13 implements RequestDelegate {
    public final /* synthetic */ ChatActivity.ChatMessageCellDelegate f$0;
    public final /* synthetic */ AlertDialog f$1;
    public final /* synthetic */ HashSet f$2;
    public final /* synthetic */ TLRPC.TL_inputGroupCallInviteMessage f$3;
    public final /* synthetic */ MessageObject f$4;

    public /* synthetic */ ChatActivity$ChatMessageCellDelegate$$ExternalSyntheticLambda13(ChatActivity.ChatMessageCellDelegate r1, AlertDialog r2, HashSet r3, TLRPC.TL_inputGroupCallInviteMessage r4, MessageObject r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r8, TLRPC.TL_error r9) {
        ChatActivity.ChatMessageCellDelegate.m8223$r8$lambda$sbD846cvCbng2q40FFDGT5tN50(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, r8, r9);
    }
}
