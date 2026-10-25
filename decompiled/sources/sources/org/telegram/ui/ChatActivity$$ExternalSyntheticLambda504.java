package org.telegram.ui;

import com.exteragram.messenger.utils.chats.WidePosts;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChatActivity$$ExternalSyntheticLambda504 implements RequestDelegate {
    public final /* synthetic */ ChatActivity f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ int f$2;
    public final /* synthetic */ MessageObject f$3;
    public final /* synthetic */ TLRPC.TL_messages_getDiscussionMessage f$4;
    public final /* synthetic */ TLRPC.Chat f$5;
    public final /* synthetic */ int f$6;
    public final /* synthetic */ WidePosts.CommentsPostAuthorLoader f$7;
    public final /* synthetic */ MessageObject f$8;
    public final /* synthetic */ Utilities.Callback2 f$9;

    public /* synthetic */ ChatActivity$$ExternalSyntheticLambda504(ChatActivity r1, int r2, int r3, MessageObject r4, TLRPC.TL_messages_getDiscussionMessage r5, TLRPC.Chat r6, int r7, WidePosts.CommentsPostAuthorLoader r8, MessageObject r9, Utilities.Callback2 r10) {
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
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r13, TLRPC.TL_error r14) {
        ChatActivity.m7510$r8$lambda$hR1UOTap2VAtTL9rfG6bEvgW3M(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, this.f$8, this.f$9, r13, r14);
    }
}
