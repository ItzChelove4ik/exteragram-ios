package org.telegram.ui;

import java.util.ArrayList;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChatActivity$$ExternalSyntheticLambda538 implements Runnable {
    public final /* synthetic */ ChatActivity f$0;
    public final /* synthetic */ ArrayList f$1;
    public final /* synthetic */ int f$10;
    public final /* synthetic */ MessageObject f$11;
    public final /* synthetic */ TLRPC.TL_messages_discussionMessage f$2;
    public final /* synthetic */ TLRPC.messages_Messages f$3;
    public final /* synthetic */ TLRPC.Chat f$4;
    public final /* synthetic */ TLRPC.TL_messages_getDiscussionMessage f$5;
    public final /* synthetic */ Utilities.Callback2 f$6;
    public final /* synthetic */ int f$7;
    public final /* synthetic */ MessageObject f$8;
    public final /* synthetic */ int f$9;

    public /* synthetic */ ChatActivity$$ExternalSyntheticLambda538(ChatActivity r1, ArrayList r2, TLRPC.TL_messages_discussionMessage r3, TLRPC.messages_Messages r4, TLRPC.Chat r5, TLRPC.TL_messages_getDiscussionMessage r6, Utilities.Callback2 r7, int r8, MessageObject r9, int r10, int r11, MessageObject r12) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
        this.f$6 = r7;
        this.f$7 = r8;
        this.f$8 = r9;
        this.f$9 = r10;
        this.f$10 = r11;
        this.f$11 = r12;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ChatActivity.$r8$lambda$2NKeGPZjiRkZ9ZaUx9mZD7fUS7g(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, this.f$8, this.f$9, this.f$10, this.f$11);
    }
}
