package org.telegram.ui.web;

import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.web.BotWebViewContainer;

/* loaded from: classes6.dex */
public final /* synthetic */ class BotWebViewContainer$$ExternalSyntheticLambda13 implements Utilities.Callback2 {
    public final /* synthetic */ BotWebViewContainer f$0;
    public final /* synthetic */ BotWebViewContainer.WebRequestContext f$1;

    public /* synthetic */ BotWebViewContainer$$ExternalSyntheticLambda13(BotWebViewContainer r1, BotWebViewContainer.WebRequestContext r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // org.telegram.messenger.Utilities.Callback2
    public final void run(Object r2, Object r3) {
        BotWebViewContainer.$r8$lambda$HknNJkVURwhtMVCsBnHTRB2mxZk(this.f$0, this.f$1, (String) r2, (TLRPC.Document) r3);
    }
}
