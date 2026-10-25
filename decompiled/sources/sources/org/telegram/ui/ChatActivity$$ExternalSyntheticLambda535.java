package org.telegram.ui;

import com.exteragram.messenger.utils.chats.WidePosts;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChatActivity$$ExternalSyntheticLambda535 implements Runnable {
    public final /* synthetic */ ChatActivity f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ MessageObject f$10;
    public final /* synthetic */ Utilities.Callback2 f$11;
    public final /* synthetic */ TLObject f$2;
    public final /* synthetic */ TLRPC.TL_error f$3;
    public final /* synthetic */ int f$4;
    public final /* synthetic */ MessageObject f$5;
    public final /* synthetic */ TLRPC.TL_messages_getDiscussionMessage f$6;
    public final /* synthetic */ TLRPC.Chat f$7;
    public final /* synthetic */ int f$8;
    public final /* synthetic */ WidePosts.CommentsPostAuthorLoader f$9;

    public /* synthetic */ ChatActivity$$ExternalSyntheticLambda535(ChatActivity r1, int r2, TLObject r3, TLRPC.TL_error r4, int r5, MessageObject r6, TLRPC.TL_messages_getDiscussionMessage r7, TLRPC.Chat r8, int r9, WidePosts.CommentsPostAuthorLoader r10, MessageObject r11, Utilities.Callback2 r12) {
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
        ChatActivity.$r8$lambda$eVfrNFIhXWAMD5asnlNLw4Xikv4(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, this.f$8, this.f$9, this.f$10, this.f$11);
    }
}
