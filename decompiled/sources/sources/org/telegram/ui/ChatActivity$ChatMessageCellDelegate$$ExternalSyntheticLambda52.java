package org.telegram.ui;

import java.util.HashSet;
import org.telegram.messenger.MessageObject;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ChatActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChatActivity$ChatMessageCellDelegate$$ExternalSyntheticLambda52 implements Runnable {
    public final /* synthetic */ ChatActivity.ChatMessageCellDelegate f$0;
    public final /* synthetic */ AlertDialog f$1;
    public final /* synthetic */ TLObject f$2;
    public final /* synthetic */ HashSet f$3;
    public final /* synthetic */ TLRPC.TL_inputGroupCallInviteMessage f$4;
    public final /* synthetic */ MessageObject f$5;
    public final /* synthetic */ TLRPC.TL_error f$6;

    public /* synthetic */ ChatActivity$ChatMessageCellDelegate$$ExternalSyntheticLambda52(ChatActivity.ChatMessageCellDelegate r1, AlertDialog r2, TLObject r3, HashSet r4, TLRPC.TL_inputGroupCallInviteMessage r5, MessageObject r6, TLRPC.TL_error r7) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
        this.f$6 = r7;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ChatActivity.ChatMessageCellDelegate.$r8$lambda$xn8CKOIpYZ0rv5QFx7GNUOW6K9g(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6);
    }
}
