package org.telegram.utils.proxy;

import android.net.Uri;
import android.webkit.WebView;
import androidx.webkit.JavaScriptReplyProxy;
import androidx.webkit.WebMessageCompat;
import androidx.webkit.WebViewCompat;

/* loaded from: classes6.dex */
public final /* synthetic */ class WebProxyTransport$$ExternalSyntheticLambda3 implements WebViewCompat.WebMessageListener {
    public final /* synthetic */ WebProxyTransport f$0;

    public /* synthetic */ WebProxyTransport$$ExternalSyntheticLambda3(WebProxyTransport r1) {
        this.f$0 = r1;
    }

    @Override // androidx.webkit.WebViewCompat.WebMessageListener
    public final void onPostMessage(WebView r1, WebMessageCompat r2, Uri r3, boolean r4, JavaScriptReplyProxy r5) {
        WebProxyTransport.m22246$r8$lambda$7c6xDsp285ya7HtCzYwYzQ8xcc(this.f$0, r1, r2, r3, r4, r5);
    }
}
