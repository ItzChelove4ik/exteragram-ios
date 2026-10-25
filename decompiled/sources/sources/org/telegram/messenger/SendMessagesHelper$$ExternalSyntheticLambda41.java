package org.telegram.messenger;

import java.util.List;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_keyboard;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.TwoStepVerificationActivity;

/* loaded from: classes5.dex */
public final /* synthetic */ class SendMessagesHelper$$ExternalSyntheticLambda41 implements Runnable {
    public final /* synthetic */ SendMessagesHelper f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ TLRPC.TL_error f$10;
    public final /* synthetic */ TLRPC.InputCheckPasswordSRP f$11;
    public final /* synthetic */ boolean f$12;
    public final /* synthetic */ List f$2;
    public final /* synthetic */ boolean f$3;
    public final /* synthetic */ TLObject f$4;
    public final /* synthetic */ MessageObject f$5;
    public final /* synthetic */ TL_keyboard.KeyboardButtonProto f$6;
    public final /* synthetic */ ChatActivity f$7;
    public final /* synthetic */ TwoStepVerificationActivity f$8;
    public final /* synthetic */ TLObject[] f$9;

    public /* synthetic */ SendMessagesHelper$$ExternalSyntheticLambda41(SendMessagesHelper r1, String r2, List r3, boolean r4, TLObject r5, MessageObject r6, TL_keyboard.KeyboardButtonProto r7, ChatActivity r8, TwoStepVerificationActivity r9, TLObject[] r10, TLRPC.TL_error r11, TLRPC.InputCheckPasswordSRP r12, boolean r13) {
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
        this.f$12 = r13;
    }

    @Override // java.lang.Runnable
    public final void run() {
        SendMessagesHelper.$r8$lambda$C_Q_CqdN20qvMfxtoB5dFGY3kso(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, this.f$8, this.f$9, this.f$10, this.f$11, this.f$12);
    }
}
