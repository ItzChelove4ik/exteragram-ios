package org.telegram.messenger;

import android.graphics.Bitmap;
import java.util.HashMap;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_stories;
import org.telegram.ui.ChatActivity;

/* loaded from: classes5.dex */
public final /* synthetic */ class SendMessagesHelper$$ExternalSyntheticLambda65 implements Runnable {
    public final /* synthetic */ TLRPC.TL_document f$0;
    public final /* synthetic */ Bitmap[] f$1;
    public final /* synthetic */ int f$10;
    public final /* synthetic */ int f$11;
    public final /* synthetic */ TLRPC.TL_photo f$12;
    public final /* synthetic */ TLRPC.TL_game f$13;
    public final /* synthetic */ SendMessageChatArguments f$14;
    public final /* synthetic */ TL_stories.StoryItem f$15;
    public final /* synthetic */ ChatActivity.ReplyQuote f$16;
    public final /* synthetic */ long f$17;
    public final /* synthetic */ long f$18;
    public final /* synthetic */ AccountInstance f$19;
    public final /* synthetic */ String[] f$2;
    public final /* synthetic */ String f$3;
    public final /* synthetic */ long f$4;
    public final /* synthetic */ MessageObject f$5;
    public final /* synthetic */ MessageObject f$6;
    public final /* synthetic */ TLRPC.BotInlineResult f$7;
    public final /* synthetic */ HashMap f$8;
    public final /* synthetic */ boolean f$9;

    public /* synthetic */ SendMessagesHelper$$ExternalSyntheticLambda65(TLRPC.TL_document r1, Bitmap[] r2, String[] r3, String r4, long r5, MessageObject r7, MessageObject r8, TLRPC.BotInlineResult r9, HashMap r10, boolean r11, int r12, int r13, TLRPC.TL_photo r14, TLRPC.TL_game r15, SendMessageChatArguments r16, TL_stories.StoryItem r17, ChatActivity.ReplyQuote r18, long r19, long r21, AccountInstance r23) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r7;
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
        this.f$17 = r19;
        this.f$18 = r21;
        this.f$19 = r23;
    }

    @Override // java.lang.Runnable
    public final void run() {
        SendMessagesHelper.$r8$lambda$GQc6EqqqPEA9DR_xtbqFqzLuxUk(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, this.f$8, this.f$9, this.f$10, this.f$11, this.f$12, this.f$13, this.f$14, this.f$15, this.f$16, this.f$17, this.f$18, this.f$19);
    }
}
