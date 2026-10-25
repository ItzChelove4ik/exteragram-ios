package org.telegram.messenger;

import android.graphics.Bitmap;
import java.util.HashMap;
import org.telegram.messenger.SendMessagesHelper;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_stories;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.Components.poll.PollSendParams;

/* loaded from: classes5.dex */
public final /* synthetic */ class SendMessagesHelper$$ExternalSyntheticLambda48 implements Runnable {
    public final /* synthetic */ Bitmap f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ String f$10;
    public final /* synthetic */ long f$11;
    public final /* synthetic */ MessageObject f$12;
    public final /* synthetic */ MessageObject f$13;
    public final /* synthetic */ boolean f$14;
    public final /* synthetic */ int f$15;
    public final /* synthetic */ int f$16;
    public final /* synthetic */ TL_stories.StoryItem f$17;
    public final /* synthetic */ ChatActivity.ReplyQuote f$18;
    public final /* synthetic */ SendMessageChatArguments f$19;
    public final /* synthetic */ MessageObject f$2;
    public final /* synthetic */ long f$20;
    public final /* synthetic */ boolean f$21;
    public final /* synthetic */ TLRPC.PhotoSize f$22;
    public final /* synthetic */ long f$23;
    public final /* synthetic */ long f$24;
    public final /* synthetic */ MessageSuggestionParams f$25;
    public final /* synthetic */ PollSendParams f$26;
    public final /* synthetic */ TLRPC.TL_inputPollAnswer f$3;
    public final /* synthetic */ AccountInstance f$4;
    public final /* synthetic */ VideoEditedInfo f$5;
    public final /* synthetic */ TLRPC.TL_document f$6;
    public final /* synthetic */ String f$7;
    public final /* synthetic */ HashMap f$8;
    public final /* synthetic */ SendMessagesHelper.SendingMediaInfo f$9;

    public /* synthetic */ SendMessagesHelper$$ExternalSyntheticLambda48(Bitmap r1, String r2, MessageObject r3, TLRPC.TL_inputPollAnswer r4, AccountInstance r5, VideoEditedInfo r6, TLRPC.TL_document r7, String r8, HashMap r9, SendMessagesHelper.SendingMediaInfo r10, String r11, long r12, MessageObject r14, MessageObject r15, boolean r16, int r17, int r18, TL_stories.StoryItem r19, ChatActivity.ReplyQuote r20, SendMessageChatArguments r21, long r22, boolean r24, TLRPC.PhotoSize r25, long r26, long r28, MessageSuggestionParams r30, PollSendParams r31) {
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
        this.f$12 = r14;
        this.f$13 = r15;
        this.f$14 = r16;
        this.f$15 = r17;
        this.f$16 = r18;
        this.f$17 = r19;
        this.f$18 = r20;
        this.f$19 = r21;
        this.f$20 = r22;
        this.f$21 = r24;
        this.f$22 = r25;
        this.f$23 = r26;
        this.f$24 = r28;
        this.f$25 = r30;
        this.f$26 = r31;
    }

    @Override // java.lang.Runnable
    public final void run() {
        SendMessagesHelper.m4929$r8$lambda$eRqTR7KRRS3qekdEb6Yv83YQbI(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, this.f$8, this.f$9, this.f$10, this.f$11, this.f$12, this.f$13, this.f$14, this.f$15, this.f$16, this.f$17, this.f$18, this.f$19, this.f$20, this.f$21, this.f$22, this.f$23, this.f$24, this.f$25, this.f$26);
    }
}
