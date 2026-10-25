package org.telegram.ui.web;

import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.web.BotWebViewContainer;

/* loaded from: classes6.dex */
public final /* synthetic */ class BotWebViewContainer$$ExternalSyntheticLambda48 implements Runnable {
    public final /* synthetic */ BotWebViewContainer f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ TLObject f$2;
    public final /* synthetic */ TLRPC.TL_error f$3;
    public final /* synthetic */ int f$4;
    public final /* synthetic */ BotWebViewContainer.MyWebView f$5;
    public final /* synthetic */ BotWebViewContainer.WebRequestContext f$6;

    public /* synthetic */ BotWebViewContainer$$ExternalSyntheticLambda48(BotWebViewContainer r1, String r2, TLObject r3, TLRPC.TL_error r4, int r5, BotWebViewContainer.MyWebView r6, BotWebViewContainer.WebRequestContext r7) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
        this.f$5 = r6;
        this.f$6 = r7;
    }

    @Override // java.lang.Runnable
    public final void run() {
        BotWebViewContainer.$r8$lambda$BJI_VGyiuJvL7FYUUN0FMPZYAFc(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6);
    }
}
