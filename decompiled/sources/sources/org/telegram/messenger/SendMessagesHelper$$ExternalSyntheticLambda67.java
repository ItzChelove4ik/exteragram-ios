package org.telegram.messenger;

import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_keyboard;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.TwoStepVerificationActivity;

/* loaded from: classes5.dex */
public final /* synthetic */ class SendMessagesHelper$$ExternalSyntheticLambda67 implements Runnable {
    public final /* synthetic */ SendMessagesHelper f$0;
    public final /* synthetic */ TLRPC.TL_error f$1;
    public final /* synthetic */ TLObject f$2;
    public final /* synthetic */ TwoStepVerificationActivity f$3;
    public final /* synthetic */ boolean f$4;
    public final /* synthetic */ MessageObject f$5;
    public final /* synthetic */ TL_keyboard.KeyboardButtonProto f$6;
    public final /* synthetic */ ChatActivity f$7;

    public /* synthetic */ SendMessagesHelper$$ExternalSyntheticLambda67(SendMessagesHelper r1, TLRPC.TL_error r2, TLObject r3, TwoStepVerificationActivity r4, boolean r5, MessageObject r6, TL_keyboard.KeyboardButtonProto r7, ChatActivity r8) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
        this.f$6 = r7;
        this.f$7 = r8;
    }

    @Override // java.lang.Runnable
    public final void run() {
        SendMessagesHelper.m4930$r8$lambda$ed99C9PGwS6VtkdhreA3DQ2jXk(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7);
    }
}
