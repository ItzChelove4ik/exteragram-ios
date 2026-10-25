package org.telegram.ui.web;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.web.BotWebViewContainer;

/* loaded from: classes6.dex */
public final /* synthetic */ class BotWebViewContainer$$ExternalSyntheticLambda55 implements Utilities.Callback2 {
    public final /* synthetic */ BotWebViewContainer f$0;
    public final /* synthetic */ BotWebViewContainer.WebRequestContext f$1;
    public final /* synthetic */ String f$2;
    public final /* synthetic */ TLRPC.User f$3;

    public /* synthetic */ BotWebViewContainer$$ExternalSyntheticLambda55(BotWebViewContainer r1, BotWebViewContainer.WebRequestContext r2, String r3, TLRPC.User r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r7, Object r8) {
        BotWebViewContainer.m21956$r8$lambda$LGRuyph4w60BFo6oRQxQ0YEEcc(this.f$0, this.f$1, this.f$2, this.f$3, (TLRPC.Updates) r7, (TLRPC.TL_error) r8);
    }
}
