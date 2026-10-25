package org.telegram.ui;

import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class ChatActivity$$ExternalSyntheticLambda271 implements Runnable {
    public final /* synthetic */ ChatActivity f$0;
    public final /* synthetic */ TLRPC.User f$1;
    public final /* synthetic */ TLRPC.UserFull f$2;

    public /* synthetic */ ChatActivity$$ExternalSyntheticLambda271(ChatActivity r1, TLRPC.User r2, TLRPC.UserFull r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ChatActivity.m7571$r8$lambda$ziTRCsHqPLG7tozLUAtegGkJs(this.f$0, this.f$1, this.f$2);
    }
}
