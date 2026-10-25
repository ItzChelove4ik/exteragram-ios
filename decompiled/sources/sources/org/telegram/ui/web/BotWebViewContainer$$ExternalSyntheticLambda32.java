package org.telegram.ui.web;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes6.dex */
public final /* synthetic */ class BotWebViewContainer$$ExternalSyntheticLambda32 implements RequestDelegate {
    public final /* synthetic */ BotWebViewContainer f$0;
    public final /* synthetic */ TLRPC.TL_messages_requestUrlAuth f$1;
    public final /* synthetic */ String f$2;
    public final /* synthetic */ String f$3;

    public /* synthetic */ BotWebViewContainer$$ExternalSyntheticLambda32(BotWebViewContainer r1, TLRPC.TL_messages_requestUrlAuth r2, String r3, String r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r7, TLRPC.TL_error r8) {
        BotWebViewContainer.$r8$lambda$_lVrYXF0nZPDbQHa_1TELn0pyAM(this.f$0, this.f$1, this.f$2, this.f$3, r7, r8);
    }
}
