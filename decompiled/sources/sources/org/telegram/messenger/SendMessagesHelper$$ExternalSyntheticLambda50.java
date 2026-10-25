package org.telegram.messenger;

import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ChatActivity;

/* loaded from: classes5.dex */
public final /* synthetic */ class SendMessagesHelper$$ExternalSyntheticLambda50 implements Runnable {
    public final /* synthetic */ SendMessagesHelper f$0;
    public final /* synthetic */ TLObject f$1;
    public final /* synthetic */ TLRPC.TL_messages_requestUrlAuth f$2;
    public final /* synthetic */ ChatActivity f$3;
    public final /* synthetic */ String f$4;
    public final /* synthetic */ boolean f$5;

    public /* synthetic */ SendMessagesHelper$$ExternalSyntheticLambda50(SendMessagesHelper r1, TLObject r2, TLRPC.TL_messages_requestUrlAuth r3, ChatActivity r4, String r5, boolean r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
    }

    @Override // java.lang.Runnable
    public final void run() {
        SendMessagesHelper.m4938$r8$lambda$oo1C_KC83LNBHM10yZw1y34Cm0(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5);
    }
}
