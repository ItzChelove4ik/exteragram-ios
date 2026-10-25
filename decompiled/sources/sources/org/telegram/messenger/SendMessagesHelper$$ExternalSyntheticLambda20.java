package org.telegram.messenger;

import java.util.ArrayList;
import org.telegram.tgnet.tl.TL_stories;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.Components.poll.PollSendParams;

/* loaded from: classes5.dex */
public final /* synthetic */ class SendMessagesHelper$$ExternalSyntheticLambda20 implements Runnable {
    public final /* synthetic */ ArrayList f$0;
    public final /* synthetic */ int[] f$1;
    public final /* synthetic */ SendMessageChatArguments f$10;
    public final /* synthetic */ long f$11;
    public final /* synthetic */ long f$12;
    public final /* synthetic */ MessageSuggestionParams f$13;
    public final /* synthetic */ PollSendParams f$14;
    public final /* synthetic */ Runnable f$15;
    public final /* synthetic */ AccountInstance f$2;
    public final /* synthetic */ long f$3;
    public final /* synthetic */ MessageObject f$4;
    public final /* synthetic */ MessageObject f$5;
    public final /* synthetic */ TL_stories.StoryItem f$6;
    public final /* synthetic */ ChatActivity.ReplyQuote f$7;
    public final /* synthetic */ boolean f$8;
    public final /* synthetic */ int f$9;

    public /* synthetic */ SendMessagesHelper$$ExternalSyntheticLambda20(ArrayList r1, int[] r2, AccountInstance r3, long r4, MessageObject r6, MessageObject r7, TL_stories.StoryItem r8, ChatActivity.ReplyQuote r9, boolean r10, int r11, SendMessageChatArguments r12, long r13, long r15, MessageSuggestionParams r17, PollSendParams r18, Runnable r19) {
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
        this.f$12 = r15;
        this.f$13 = r17;
        this.f$14 = r18;
        this.f$15 = r19;
    }

    @Override // java.lang.Runnable
    public final void run() {
        SendMessagesHelper.$r8$lambda$fZVClCS_DWvTInxmOEJZO4ZCd6o(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, this.f$8, this.f$9, this.f$10, this.f$11, this.f$12, this.f$13, this.f$14, this.f$15);
    }
}
