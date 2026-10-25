package org.telegram.ui.web;

import android.webkit.PermissionRequest;
import androidx.core.util.Consumer;
import org.telegram.ui.web.BotWebViewContainer;

/* loaded from: classes6.dex */
public final /* synthetic */ class BotWebViewContainer$MyWebView$3$$ExternalSyntheticLambda17 implements Consumer {
    public final /* synthetic */ BotWebViewContainer.MyWebView.AnonymousClass3 f$0;
    public final /* synthetic */ PermissionRequest f$1;
    public final /* synthetic */ String f$2;

    public /* synthetic */ BotWebViewContainer$MyWebView$3$$ExternalSyntheticLambda17(BotWebViewContainer.MyWebView.AnonymousClass3 r1, PermissionRequest r2, String r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // androidx.core.util.Consumer
    public final void accept(Object r3) {
        BotWebViewContainer.MyWebView.AnonymousClass3.$r8$lambda$_7Kla_nw6y9QMdB_QtfCn5wldig(this.f$0, this.f$1, this.f$2, (Boolean) r3);
    }
}
