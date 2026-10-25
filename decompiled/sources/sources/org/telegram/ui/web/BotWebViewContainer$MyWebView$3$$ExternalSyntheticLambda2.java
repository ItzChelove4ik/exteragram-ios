package org.telegram.ui.web;

import android.content.DialogInterface;
import android.webkit.JsPromptResult;
import org.telegram.ui.web.BotWebViewContainer;

/* loaded from: classes6.dex */
public final /* synthetic */ class BotWebViewContainer$MyWebView$3$$ExternalSyntheticLambda2 implements DialogInterface.OnDismissListener {
    public final /* synthetic */ boolean[] f$0;
    public final /* synthetic */ JsPromptResult f$1;

    public /* synthetic */ BotWebViewContainer$MyWebView$3$$ExternalSyntheticLambda2(boolean[] r1, JsPromptResult r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public final void onDismiss(DialogInterface r2) {
        BotWebViewContainer.MyWebView.AnonymousClass3.$r8$lambda$46yWweFpbnX7LDYh2i48Aea3vk4(this.f$0, this.f$1, r2);
    }
}
