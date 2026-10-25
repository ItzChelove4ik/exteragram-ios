package org.telegram.ui.web;

import android.net.Uri;
import android.webkit.WebView;
import androidx.webkit.JavaScriptReplyProxy;
import androidx.webkit.WebMessageCompat;
import androidx.webkit.WebViewCompat;
import org.telegram.ui.web.BotWebViewContainer;

/* loaded from: classes6.dex */
public final /* synthetic */ class BotWebViewContainer$$ExternalSyntheticLambda3 implements WebViewCompat.WebMessageListener {
    public final /* synthetic */ BotWebViewContainer.MyWebView f$0;

    public /* synthetic */ BotWebViewContainer$$ExternalSyntheticLambda3(BotWebViewContainer.MyWebView r1) {
        this.f$0 = r1;
    }

    @Override // androidx.webkit.WebViewCompat.WebMessageListener
    public final void onPostMessage(WebView r1, WebMessageCompat r2, Uri r3, boolean r4, JavaScriptReplyProxy r5) {
        BotWebViewContainer.$r8$lambda$x_5pHPcuK_VVNirD7rBSB3842Iw(this.f$0, r1, r2, r3, r4, r5);
    }
}
