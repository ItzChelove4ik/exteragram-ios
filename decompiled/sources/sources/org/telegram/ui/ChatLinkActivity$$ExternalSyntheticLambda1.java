package org.telegram.ui;

import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChatLinkActivity$$ExternalSyntheticLambda1 implements AlertDialog.OnButtonClickListener {
    public final /* synthetic */ ChatLinkActivity f$0;
    public final /* synthetic */ TLRPC.ChatFull f$1;
    public final /* synthetic */ TLRPC.Chat f$2;

    public /* synthetic */ ChatLinkActivity$$ExternalSyntheticLambda1(ChatLinkActivity r1, TLRPC.ChatFull r2, TLRPC.Chat r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.ui.ActionBar.AlertDialog.OnButtonClickListener
    public final void onClick(AlertDialog r3, int r4) {
        ChatLinkActivity.m8342$r8$lambda$SYcRFuQWX6AGwn3Etc8MvYgyA(this.f$0, this.f$1, this.f$2, r3, r4);
    }
}
