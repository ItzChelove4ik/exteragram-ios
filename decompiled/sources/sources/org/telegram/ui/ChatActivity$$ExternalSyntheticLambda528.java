package org.telegram.ui;

import org.telegram.messenger.MessageObject;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.browser.Browser;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Cells.ChatMessageCell;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChatActivity$$ExternalSyntheticLambda528 implements Utilities.Callback2 {
    public final /* synthetic */ ChatActivity f$0;
    public final /* synthetic */ Browser.Progress f$1;
    public final /* synthetic */ int[] f$2;
    public final /* synthetic */ ChatMessageCell f$3;
    public final /* synthetic */ MessageObject f$4;

    public /* synthetic */ ChatActivity$$ExternalSyntheticLambda528(ChatActivity r1, Browser.Progress r2, int[] r3, ChatMessageCell r4, MessageObject r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r8, Object r9) {
        ChatActivity.m7559$r8$lambda$xEQbwa0CqLftK7lPaatqkAnWa0(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, (TLRPC.messages_Messages) r8, (TLRPC.TL_error) r9);
    }
}
