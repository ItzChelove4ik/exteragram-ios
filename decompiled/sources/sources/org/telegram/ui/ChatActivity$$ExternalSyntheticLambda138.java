package org.telegram.ui;

import org.telegram.messenger.MessageObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Cells.ChatMessageCell;
import org.telegram.ui.Components.ItemOptions;
import org.telegram.ui.Components.ScrimOptions;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChatActivity$$ExternalSyntheticLambda138 implements Runnable {
    public final /* synthetic */ ChatActivity f$0;
    public final /* synthetic */ ItemOptions f$1;
    public final /* synthetic */ MessageObject f$2;
    public final /* synthetic */ ChatMessageCell f$3;
    public final /* synthetic */ String f$4;
    public final /* synthetic */ TLRPC.TL_messageEntityFormattedDate f$5;
    public final /* synthetic */ ScrimOptions f$6;

    public /* synthetic */ ChatActivity$$ExternalSyntheticLambda138(ChatActivity r1, ItemOptions r2, MessageObject r3, ChatMessageCell r4, String r5, TLRPC.TL_messageEntityFormattedDate r6, ScrimOptions r7) {
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
        ChatActivity.$r8$lambda$cNBHUEp2fPRS4KsGbmacKZKCP2U(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6);
    }
}
