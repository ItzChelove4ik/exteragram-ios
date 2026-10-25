package org.telegram.messenger;

import java.util.HashMap;
import org.telegram.messenger.SendMessagesHelper;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public final /* synthetic */ class SendMessagesHelper$$ExternalSyntheticLambda104 implements RequestDelegate {
    public final /* synthetic */ SendMessagesHelper f$0;
    public final /* synthetic */ TLRPC.TL_messages_addPollAnswer f$1;
    public final /* synthetic */ boolean f$10;
    public final /* synthetic */ TLObject f$2;
    public final /* synthetic */ MessageObject f$3;
    public final /* synthetic */ String f$4;
    public final /* synthetic */ SendMessagesHelper.DelayedMessage f$5;
    public final /* synthetic */ boolean f$6;
    public final /* synthetic */ SendMessagesHelper.DelayedMessage f$7;
    public final /* synthetic */ Object f$8;
    public final /* synthetic */ HashMap f$9;

    public /* synthetic */ SendMessagesHelper$$ExternalSyntheticLambda104(SendMessagesHelper r1, TLRPC.TL_messages_addPollAnswer r2, TLObject r3, MessageObject r4, String r5, SendMessagesHelper.DelayedMessage r6, boolean r7, SendMessagesHelper.DelayedMessage r8, Object r9, HashMap r10, boolean r11) {
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
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r14, TLRPC.TL_error r15) {
        SendMessagesHelper.$r8$lambda$oN29P4yRtSspLEXmZGLyuDq9XHg(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, this.f$8, this.f$9, this.f$10, r14, r15);
    }
}
