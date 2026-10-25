package org.telegram.messenger;

import java.util.ArrayList;
import java.util.HashMap;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_stories;
import org.telegram.ui.Components.poll.PollSendParams;

/* loaded from: classes5.dex */
public final /* synthetic */ class SendMessagesHelper$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ MessageObject f$0;
    public final /* synthetic */ AccountInstance f$1;
    public final /* synthetic */ ArrayList f$10;
    public final /* synthetic */ boolean f$11;
    public final /* synthetic */ int f$12;
    public final /* synthetic */ int f$13;
    public final /* synthetic */ TL_stories.StoryItem f$14;
    public final /* synthetic */ SendMessageChatArguments f$15;
    public final /* synthetic */ long f$16;
    public final /* synthetic */ boolean f$17;
    public final /* synthetic */ long f$18;
    public final /* synthetic */ PollSendParams f$19;
    public final /* synthetic */ TLRPC.TL_document f$2;
    public final /* synthetic */ int f$20;
    public final /* synthetic */ MessageObject f$3;
    public final /* synthetic */ HashMap f$4;
    public final /* synthetic */ String f$5;
    public final /* synthetic */ long f$6;
    public final /* synthetic */ MessageObject f$7;
    public final /* synthetic */ MessageObject f$8;
    public final /* synthetic */ String f$9;

    public /* synthetic */ SendMessagesHelper$$ExternalSyntheticLambda2(MessageObject r1, AccountInstance r2, TLRPC.TL_document r3, MessageObject r4, HashMap r5, String r6, long r7, MessageObject r9, MessageObject r10, String r11, ArrayList r12, boolean r13, int r14, int r15, TL_stories.StoryItem r16, SendMessageChatArguments r17, long r18, boolean r20, long r21, PollSendParams r23, int r24) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
        this.f$6 = r7;
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
        this.f$18 = r21;
        this.f$19 = r23;
        this.f$20 = r24;
    }

    @Override // java.lang.Runnable
    public final void run() {
        SendMessagesHelper.$r8$lambda$DAjYcVRLBiqC54_1T_gvANZor2w(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, this.f$8, this.f$9, this.f$10, this.f$11, this.f$12, this.f$13, this.f$14, this.f$15, this.f$16, this.f$17, this.f$18, this.f$19, this.f$20);
    }
}
