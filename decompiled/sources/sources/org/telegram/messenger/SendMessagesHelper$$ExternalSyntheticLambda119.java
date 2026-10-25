package org.telegram.messenger;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ChatActivity;

/* loaded from: classes5.dex */
public final /* synthetic */ class SendMessagesHelper$$ExternalSyntheticLambda119 implements RequestDelegate {
    public final /* synthetic */ SendMessagesHelper f$0;
    public final /* synthetic */ TLRPC.TL_messages_requestUrlAuth f$1;
    public final /* synthetic */ ChatActivity f$2;
    public final /* synthetic */ String f$3;
    public final /* synthetic */ boolean f$4;

    public /* synthetic */ SendMessagesHelper$$ExternalSyntheticLambda119(SendMessagesHelper r1, TLRPC.TL_messages_requestUrlAuth r2, ChatActivity r3, String r4, boolean r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r8, TLRPC.TL_error r9) {
        SendMessagesHelper.$r8$lambda$45kmZvwdTEk0ydavREZFwXSKbk8(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, r8, r9);
    }
}
