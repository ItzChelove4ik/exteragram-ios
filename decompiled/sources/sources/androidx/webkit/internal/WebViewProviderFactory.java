package androidx.webkit.internal;

import android.webkit.WebView;
import org.chromium.support_lib_boundary.StaticsBoundaryInterface;
import org.chromium.support_lib_boundary.WebViewProviderBoundaryInterface;

/* loaded from: classes4.dex */
public interface WebViewProviderFactory {
    WebViewProviderBoundaryInterface createWebView(WebView r1);

    StaticsBoundaryInterface getStatics();

    String[] getWebViewFeatures();
}
