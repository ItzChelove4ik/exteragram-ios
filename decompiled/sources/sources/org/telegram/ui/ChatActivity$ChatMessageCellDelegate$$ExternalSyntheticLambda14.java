package org.telegram.ui;

import android.content.DialogInterface;
import org.telegram.ui.ChatActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChatActivity$ChatMessageCellDelegate$$ExternalSyntheticLambda14 implements DialogInterface.OnCancelListener {
    public final /* synthetic */ ChatActivity.ChatMessageCellDelegate f$0;
    public final /* synthetic */ int f$1;

    public /* synthetic */ ChatActivity$ChatMessageCellDelegate$$ExternalSyntheticLambda14(ChatActivity.ChatMessageCellDelegate r1, int r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // android.content.DialogInterface.OnCancelListener
    public final void onCancel(DialogInterface r2) {
        ChatActivity.ChatMessageCellDelegate.m8216$r8$lambda$Q8sslD1Fbr1gZAaNa1ttDyo6Y(this.f$0, this.f$1, r2);
    }
}
