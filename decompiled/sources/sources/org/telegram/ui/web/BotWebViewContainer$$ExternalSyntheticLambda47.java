package org.telegram.ui.web;

import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.web.BotWebViewContainer;

/* loaded from: classes6.dex */
public final /* synthetic */ class BotWebViewContainer$$ExternalSyntheticLambda47 implements Runnable {
    public final /* synthetic */ BotWebViewContainer f$0;
    public final /* synthetic */ TLObject f$1;
    public final /* synthetic */ int f$2;
    public final /* synthetic */ BotWebViewContainer.MyWebView f$3;
    public final /* synthetic */ BotWebViewContainer.WebRequestContext f$4;
    public final /* synthetic */ TLRPC.TL_error f$5;

    public /* synthetic */ BotWebViewContainer$$ExternalSyntheticLambda47(BotWebViewContainer r1, TLObject r2, int r3, BotWebViewContainer.MyWebView r4, BotWebViewContainer.WebRequestContext r5, TLRPC.TL_error r6) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
    }

    @Override // java.lang.Runnable
    public final void run() {
        BotWebViewContainer.$r8$lambda$F5ikMaWrcNfUsXB12gJkNg6WGbU(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5);
    }
}
