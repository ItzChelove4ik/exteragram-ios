package org.telegram.messenger;

import java.util.HashMap;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_stories;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ChatActivity;

/* loaded from: classes5.dex */
public final /* synthetic */ class SendMessagesHelper$$ExternalSyntheticLambda37 implements Runnable {
    public final /* synthetic */ long f$0;
    public final /* synthetic */ TLRPC.BotInlineResult f$1;
    public final /* synthetic */ SendMessageChatArguments f$10;
    public final /* synthetic */ TL_stories.StoryItem f$11;
    public final /* synthetic */ ChatActivity.ReplyQuote f$12;
    public final /* synthetic */ long f$13;
    public final /* synthetic */ long f$14;
    public final /* synthetic */ AccountInstance f$2;
    public final /* synthetic */ HashMap f$3;
    public final /* synthetic */ BaseFragment f$4;
    public final /* synthetic */ MessageObject f$5;
    public final /* synthetic */ MessageObject f$6;
    public final /* synthetic */ boolean f$7;
    public final /* synthetic */ int f$8;
    public final /* synthetic */ int f$9;

    public /* synthetic */ SendMessagesHelper$$ExternalSyntheticLambda37(long r1, TLRPC.BotInlineResult r3, AccountInstance r4, HashMap r5, BaseFragment r6, MessageObject r7, MessageObject r8, boolean r9, int r10, int r11, SendMessageChatArguments r12, TL_stories.StoryItem r13, ChatActivity.ReplyQuote r14, long r15, long r17) {
        this.f$0 = r1;
        this.f$1 = r3;
        this.f$2 = r4;
        this.f$3 = r5;
        this.f$4 = r6;
        this.f$5 = r7;
        this.f$6 = r8;
        this.f$7 = r9;
        this.f$8 = r10;
        this.f$9 = r11;
        this.f$10 = r12;
        this.f$11 = r13;
        this.f$12 = r14;
        this.f$13 = r15;
        this.f$14 = r17;
    }

    @Override // java.lang.Runnable
    public final void run() {
        SendMessagesHelper.$r8$lambda$TEMEmB64MkaRJTk7fkPbuELgyE0(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, this.f$8, this.f$9, this.f$10, this.f$11, this.f$12, this.f$13, this.f$14);
    }
}
