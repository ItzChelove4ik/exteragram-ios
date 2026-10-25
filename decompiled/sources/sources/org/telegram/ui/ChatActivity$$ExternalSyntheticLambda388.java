package org.telegram.ui;

import com.exteragram.messenger.utils.chats.WidePosts;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChatActivity$$ExternalSyntheticLambda388 implements Runnable {
    public final /* synthetic */ ChatActivity f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ MessageObject f$10;
    public final /* synthetic */ Utilities.Callback2 f$11;
    public final /* synthetic */ int f$2;
    public final /* synthetic */ long f$3;
    public final /* synthetic */ TLObject f$4;
    public final /* synthetic */ int f$5;
    public final /* synthetic */ MessageObject f$6;
    public final /* synthetic */ TLRPC.TL_messages_getDiscussionMessage f$7;
    public final /* synthetic */ TLRPC.Chat f$8;
    public final /* synthetic */ WidePosts.CommentsPostAuthorLoader f$9;

    public /* synthetic */ ChatActivity$$ExternalSyntheticLambda388(ChatActivity r1, int r2, int r3, long r4, TLObject r6, int r7, MessageObject r8, TLRPC.TL_messages_getDiscussionMessage r9, TLRPC.Chat r10, WidePosts.CommentsPostAuthorLoader r11, MessageObject r12, Utilities.Callback2 r13) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r6;
        this.f$5 = r7;
        this.f$6 = r8;
        this.f$7 = r9;
        this.f$8 = r10;
        this.f$9 = r11;
        this.f$10 = r12;
        this.f$11 = r13;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ChatActivity.m7406$r8$lambda$7gVEnLXCCpBfb1QLVjONJjm7Sw(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, this.f$8, this.f$9, this.f$10, this.f$11);
    }
}
