package org.telegram.messenger;

import androidx.core.view.inputmethod.InputContentInfoCompat;
import java.util.ArrayList;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_stories;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.Components.poll.PollSendParams;

/* loaded from: classes5.dex */
public final /* synthetic */ class SendMessagesHelper$$ExternalSyntheticLambda66 implements Runnable {
    public final /* synthetic */ ArrayList f$0;
    public final /* synthetic */ long f$1;
    public final /* synthetic */ MessageObject f$10;
    public final /* synthetic */ boolean f$11;
    public final /* synthetic */ int f$12;
    public final /* synthetic */ int f$13;
    public final /* synthetic */ TL_stories.StoryItem f$14;
    public final /* synthetic */ ChatActivity.ReplyQuote f$15;
    public final /* synthetic */ SendMessageChatArguments f$16;
    public final /* synthetic */ long f$17;
    public final /* synthetic */ boolean f$18;
    public final /* synthetic */ long f$19;
    public final /* synthetic */ boolean f$2;
    public final /* synthetic */ long f$20;
    public final /* synthetic */ MessageSuggestionParams f$21;
    public final /* synthetic */ PollSendParams f$22;
    public final /* synthetic */ boolean f$23;
    public final /* synthetic */ InputContentInfoCompat f$24;
    public final /* synthetic */ boolean f$25;
    public final /* synthetic */ boolean f$3;
    public final /* synthetic */ boolean f$4;
    public final /* synthetic */ AccountInstance f$5;
    public final /* synthetic */ long f$6;
    public final /* synthetic */ MessageObject f$7;
    public final /* synthetic */ TLRPC.TL_inputPollAnswer f$8;
    public final /* synthetic */ MessageObject f$9;

    public /* synthetic */ SendMessagesHelper$$ExternalSyntheticLambda66(ArrayList r1, long r2, boolean r4, boolean r5, boolean r6, AccountInstance r7, long r8, MessageObject r10, TLRPC.TL_inputPollAnswer r11, MessageObject r12, MessageObject r13, boolean r14, int r15, int r16, TL_stories.StoryItem r17, ChatActivity.ReplyQuote r18, SendMessageChatArguments r19, long r20, boolean r22, long r23, long r25, MessageSuggestionParams r27, PollSendParams r28, boolean r29, InputContentInfoCompat r30, boolean r31) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r4;
        this.f$3 = r5;
        this.f$4 = r6;
        this.f$5 = r7;
        this.f$6 = r8;
        this.f$7 = r10;
        this.f$8 = r11;
        this.f$9 = r12;
        this.f$10 = r13;
        this.f$11 = r14;
        this.f$12 = r15;
        this.f$13 = r16;
        this.f$14 = r17;
        this.f$15 = r18;
        this.f$16 = r19;
        this.f$17 = r20;
        this.f$18 = r22;
        this.f$19 = r23;
        this.f$20 = r25;
        this.f$21 = r27;
        this.f$22 = r28;
        this.f$23 = r29;
        this.f$24 = r30;
        this.f$25 = r31;
    }

    @Override // java.lang.Runnable
    public final void run() {
        SendMessagesHelper.$r8$lambda$iqfiX52t_A0OZlK8QUtY16dpSJU(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, this.f$8, this.f$9, this.f$10, this.f$11, this.f$12, this.f$13, this.f$14, this.f$15, this.f$16, this.f$17, this.f$18, this.f$19, this.f$20, this.f$21, this.f$22, this.f$23, this.f$24, this.f$25);
    }
}
