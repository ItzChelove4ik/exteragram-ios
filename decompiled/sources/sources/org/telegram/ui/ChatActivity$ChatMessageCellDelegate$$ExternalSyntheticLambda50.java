package org.telegram.ui;

import org.telegram.messenger.browser.Browser;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ChatActivity;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChatActivity$ChatMessageCellDelegate$$ExternalSyntheticLambda50 implements Runnable {
    public final /* synthetic */ ChatActivity.ChatMessageCellDelegate f$0;
    public final /* synthetic */ Browser.Progress f$1;
    public final /* synthetic */ TLRPC.Chat f$2;
    public final /* synthetic */ int f$3;
    public final /* synthetic */ ChatActivity f$4;

    public /* synthetic */ ChatActivity$ChatMessageCellDelegate$$ExternalSyntheticLambda50(ChatActivity.ChatMessageCellDelegate r1, Browser.Progress r2, TLRPC.Chat r3, int r4, ChatActivity r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ChatActivity.ChatMessageCellDelegate.m8208$r8$lambda$3rtN9CzrevEQLk6zBXtHL1zJDo(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4);
    }
}
