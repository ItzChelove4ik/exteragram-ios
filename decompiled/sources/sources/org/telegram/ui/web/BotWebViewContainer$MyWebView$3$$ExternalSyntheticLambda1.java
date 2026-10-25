package org.telegram.ui.web;

import android.webkit.JsPromptResult;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.Components.EditTextCaption;
import org.telegram.ui.web.BotWebViewContainer;

/* loaded from: classes6.dex */
public final /* synthetic */ class BotWebViewContainer$MyWebView$3$$ExternalSyntheticLambda1 implements AlertDialog.OnButtonClickListener {
    public final /* synthetic */ boolean[] f$0;
    public final /* synthetic */ JsPromptResult f$1;
    public final /* synthetic */ EditTextCaption f$2;

    public /* synthetic */ BotWebViewContainer$MyWebView$3$$ExternalSyntheticLambda1(boolean[] r1, JsPromptResult r2, EditTextCaption r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // org.telegram.ui.ActionBar.AlertDialog.OnButtonClickListener
    public final void onClick(AlertDialog r3, int r4) {
        BotWebViewContainer.MyWebView.AnonymousClass3.$r8$lambda$FlsajW65O1qximWu9YktPhKFj1I(this.f$0, this.f$1, this.f$2, r3, r4);
    }
}
