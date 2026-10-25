package org.telegram.messenger;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_keyboard;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.TwoStepVerificationActivity;

/* loaded from: classes5.dex */
public final /* synthetic */ class SendMessagesHelper$$ExternalSyntheticLambda56 implements RequestDelegate {
    public final /* synthetic */ SendMessagesHelper f$0;
    public final /* synthetic */ TwoStepVerificationActivity f$1;
    public final /* synthetic */ boolean f$2;
    public final /* synthetic */ MessageObject f$3;
    public final /* synthetic */ TL_keyboard.KeyboardButtonProto f$4;
    public final /* synthetic */ ChatActivity f$5;

    public /* synthetic */ SendMessagesHelper$$ExternalSyntheticLambda56(SendMessagesHelper r1, TwoStepVerificationActivity r2, boolean r3, MessageObject r4, TL_keyboard.KeyboardButtonProto r5, ChatActivity r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r9, TLRPC.TL_error r10) {
        SendMessagesHelper.$r8$lambda$ELivVBWPrqNe2I1vsHTA46C9dIg(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, r9, r10);
    }
}
