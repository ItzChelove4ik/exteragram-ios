package org.telegram.ui.web;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.web.BotWebViewContainer;

/* loaded from: classes6.dex */
public final /* synthetic */ class BotWebViewContainer$$ExternalSyntheticLambda56 implements Utilities.Callback2 {
    public final /* synthetic */ BotWebViewContainer f$0;
    public final /* synthetic */ BotWebViewContainer.WebRequestContext f$1;
    public final /* synthetic */ String f$2;

    public /* synthetic */ BotWebViewContainer$$ExternalSyntheticLambda56(BotWebViewContainer r1, BotWebViewContainer.WebRequestContext r2, String r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r3, Object r4) {
        BotWebViewContainer.$r8$lambda$ueKMEashOQsH5Jm2BwK_4Z1JZ20(this.f$0, this.f$1, this.f$2, (TLRPC.Updates) r3, (TLRPC.TL_error) r4);
    }
}
