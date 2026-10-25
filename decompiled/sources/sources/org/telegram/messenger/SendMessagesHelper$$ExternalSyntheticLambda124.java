package org.telegram.messenger;

import android.graphics.Bitmap;
import org.telegram.messenger.MessageObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_stories;
import org.telegram.ui.ChatActivity;

/* loaded from: classes5.dex */
public final /* synthetic */ class SendMessagesHelper$$ExternalSyntheticLambda124 implements Runnable {
    public final /* synthetic */ SendMessagesHelper f$0;
    public final /* synthetic */ Bitmap[] f$1;
    public final /* synthetic */ int f$10;
    public final /* synthetic */ Object f$11;
    public final /* synthetic */ MessageObject.SendAnimationData f$12;
    public final /* synthetic */ TL_stories.StoryItem f$13;
    public final /* synthetic */ ChatActivity.ReplyQuote f$14;
    public final /* synthetic */ SendMessageChatArguments f$15;
    public final /* synthetic */ long f$16;
    public final /* synthetic */ long f$17;
    public final /* synthetic */ MessageSuggestionParams f$18;
    public final /* synthetic */ CharSequence f$19;
    public final /* synthetic */ String[] f$2;
    public final /* synthetic */ boolean f$20;
    public final /* synthetic */ TLRPC.Document f$3;
    public final /* synthetic */ VideoEditedInfo f$4;
    public final /* synthetic */ long f$5;
    public final /* synthetic */ MessageObject f$6;
    public final /* synthetic */ MessageObject f$7;
    public final /* synthetic */ boolean f$8;
    public final /* synthetic */ int f$9;

    public /* synthetic */ SendMessagesHelper$$ExternalSyntheticLambda124(SendMessagesHelper r1, Bitmap[] r2, String[] r3, TLRPC.Document r4, VideoEditedInfo r5, long r6, MessageObject r8, MessageObject r9, boolean r10, int r11, int r12, Object r13, MessageObject.SendAnimationData r14, TL_stories.StoryItem r15, ChatActivity.ReplyQuote r16, SendMessageChatArguments r17, long r18, long r20, MessageSuggestionParams r22, CharSequence r23, boolean r24) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
        this.f$6 = r8;
        this.f$7 = r9;
        this.f$8 = r10;
        this.f$9 = r11;
        this.f$10 = r12;
        this.f$11 = r13;
        this.f$12 = r14;
        this.f$13 = r15;
        this.f$14 = r16;
        this.f$15 = r17;
        this.f$16 = r18;
        this.f$17 = r20;
        this.f$18 = r22;
        this.f$19 = r23;
        this.f$20 = r24;
    }

    @Override // java.lang.Runnable
    public final void run() {
        SendMessagesHelper.m4941$r8$lambda$r6Io4elfBlW9dihbm1gSnA8jMI(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, this.f$8, this.f$9, this.f$10, this.f$11, this.f$12, this.f$13, this.f$14, this.f$15, this.f$16, this.f$17, this.f$18, this.f$19, this.f$20);
    }
}
