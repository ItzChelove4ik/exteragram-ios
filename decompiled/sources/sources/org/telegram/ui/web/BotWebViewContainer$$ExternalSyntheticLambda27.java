package org.telegram.ui.web;

import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.web.BotWebViewContainer;

/* loaded from: classes6.dex */
public final /* synthetic */ class BotWebViewContainer$$ExternalSyntheticLambda27 implements RequestDelegate {
    public final /* synthetic */ BotWebViewContainer f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ int f$2;
    public final /* synthetic */ BotWebViewContainer.MyWebView f$3;
    public final /* synthetic */ BotWebViewContainer.WebRequestContext f$4;

    public /* synthetic */ BotWebViewContainer$$ExternalSyntheticLambda27(BotWebViewContainer r1, String r2, int r3, BotWebViewContainer.MyWebView r4, BotWebViewContainer.WebRequestContext r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // org.telegram.tgnet.RequestDelegate
    public final void run(TLObject r8, TLRPC.TL_error r9) {
        BotWebViewContainer.m21958$r8$lambda$MqavG0gbGiHEGu6PudBaunDB54(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, r8, r9);
    }
}
