package org.telegram.ui;

import com.exteragram.messenger.utils.chats.WidePosts;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChatActivity$$ExternalSyntheticLambda368 implements RequestDelegate {
    public final /* synthetic */ ChatActivity f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ Utilities.Callback2 f$10;
    public final /* synthetic */ int f$2;
    public final /* synthetic */ long f$3;
    public final /* synthetic */ int f$4;
    public final /* synthetic */ MessageObject f$5;
    public final /* synthetic */ TLRPC.TL_messages_getDiscussionMessage f$6;
    public final /* synthetic */ TLRPC.Chat f$7;
    public final /* synthetic */ WidePosts.CommentsPostAuthorLoader f$8;
    public final /* synthetic */ MessageObject f$9;

    public /* synthetic */ ChatActivity$$ExternalSyntheticLambda368(ChatActivity r1, int r2, int r3, long r4, int r6, MessageObject r7, TLRPC.TL_messages_getDiscussionMessage r8, TLRPC.Chat r9, WidePosts.CommentsPostAuthorLoader r10, MessageObject r11, Utilities.Callback2 r12) {
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
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r15, TLRPC.TL_error r16) {
        ChatActivity.$r8$lambda$6zXQAToiC8te1EIAwANt3ew_S8k(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, this.f$8, this.f$9, this.f$10, r15, r16);
    }
}
