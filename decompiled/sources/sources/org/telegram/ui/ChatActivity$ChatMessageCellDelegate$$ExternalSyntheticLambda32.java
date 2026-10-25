package org.telegram.ui;

import android.content.DialogInterface;
import org.telegram.messenger.MessageObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ChatActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChatActivity$ChatMessageCellDelegate$$ExternalSyntheticLambda32 implements Runnable {
    public final /* synthetic */ ChatActivity.ChatMessageCellDelegate f$0;
    public final /* synthetic */ TLRPC.Message f$1;
    public final /* synthetic */ AlertDialog[] f$2;
    public final /* synthetic */ MessageObject f$3;
    public final /* synthetic */ DialogInterface.OnDismissListener f$4;

    public /* synthetic */ ChatActivity$ChatMessageCellDelegate$$ExternalSyntheticLambda32(ChatActivity.ChatMessageCellDelegate r1, TLRPC.Message r2, AlertDialog[] r3, MessageObject r4, DialogInterface.OnDismissListener r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ChatActivity.ChatMessageCellDelegate.m8217$r8$lambda$T1FMrvcZMYx6YPJ087Fh3Um8A(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
