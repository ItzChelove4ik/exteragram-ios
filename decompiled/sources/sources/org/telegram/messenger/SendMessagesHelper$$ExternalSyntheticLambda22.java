package org.telegram.messenger;

import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_keyboard;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.TwoStepVerificationActivity;

/* loaded from: classes5.dex */
public final /* synthetic */ class SendMessagesHelper$$ExternalSyntheticLambda22 implements TwoStepVerificationActivity.TwoStepVerificationActivityDelegate {
    public final /* synthetic */ SendMessagesHelper f$0;
    public final /* synthetic */ boolean f$1;
    public final /* synthetic */ MessageObject f$2;
    public final /* synthetic */ TL_keyboard.KeyboardButtonProto f$3;
    public final /* synthetic */ TwoStepVerificationActivity f$4;
    public final /* synthetic */ ChatActivity f$5;

    public /* synthetic */ SendMessagesHelper$$ExternalSyntheticLambda22(SendMessagesHelper r1, boolean r2, MessageObject r3, TL_keyboard.KeyboardButtonProto r4, TwoStepVerificationActivity r5, ChatActivity r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
    }

    @Override // org.telegram.ui.TwoStepVerificationActivity.TwoStepVerificationActivityDelegate
    public final void didEnterPassword(TLRPC.InputCheckPasswordSRP r8) {
        SendMessagesHelper.$r8$lambda$NcNRAo3lMaZTshI9Dsf_RfSrUfA(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, r8);
    }
}
