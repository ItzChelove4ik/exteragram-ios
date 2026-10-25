package org.telegram.ui.web;

import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class BotWebViewContainer$$ExternalSyntheticLambda37 implements Runnable {
    public final /* synthetic */ BotWebViewContainer f$0;
    public final /* synthetic */ TLObject f$1;
    public final /* synthetic */ TLRPC.TL_messages_requestUrlAuth f$2;
    public final /* synthetic */ String f$3;
    public final /* synthetic */ TLRPC.TL_error f$4;
    public final /* synthetic */ String f$5;

    public /* synthetic */ BotWebViewContainer$$ExternalSyntheticLambda37(BotWebViewContainer r1, TLObject r2, TLRPC.TL_messages_requestUrlAuth r3, String r4, TLRPC.TL_error r5, String r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
    }

    @Override // java.lang.Runnable
    public final void run() {
        BotWebViewContainer.$r8$lambda$0yjXzIN7PcjxhlSWY_7fV0AXBoI(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5);
    }
}
