package androidx.webkit;

import android.annotation.SuppressLint;
import android.content.pm.PackageInfo;
import android.net.Uri;
import android.os.Build;
import android.webkit.WebView;
import androidx.webkit.internal.ApiHelperForO;
import androidx.webkit.internal.WebViewFeatureInternal;
import androidx.webkit.internal.WebViewGlueCommunicator;
import androidx.webkit.internal.WebViewProviderAdapter;
import androidx.webkit.internal.WebViewProviderFactory;
import java.util.Set;
import java.util.WeakHashMap;
import okhttp3.internal.url._UrlKt;
import org.chromium.support_lib_boundary.WebViewProviderBoundaryInterface;

/* loaded from: classes4.dex */
public abstract class WebViewCompat {
    private static final Uri EMPTY_URI = null;
    private static final Uri WILDCARD_URI = null;
    private static final WeakHashMap<WebView, WebViewProviderAdapter> sProviderAdapterCache = null;

    public interface WebMessageListener {
        void onPostMessage(WebView r1, WebMessageCompat r2, Uri r3, boolean r4, JavaScriptReplyProxy r5);
    }

    static {
        WILDCARD_URI = Uri.parse("*");
        EMPTY_URI = Uri.parse(_UrlKt.FRAGMENT_ENCODE_SET);
        sProviderAdapterCache = new WeakHashMap();
    }

    public static ScriptHandler addDocumentStartJavaScript(WebView r1, String r2, Set<String> r3) {
        if (WebViewFeatureInternal.DOCUMENT_START_SCRIPT.isSupportedByWebView() == false) goto L7;
        return getProvider(r1).addDocumentStartJavaScript(r2, (String[]) r3.toArray(new String[0]));
    L7:
        throw WebViewFeatureInternal.getUnsupportedOperationException();
    }

    public static void addWebMessageListener(WebView r1, String r2, Set<String> r3, WebMessageListener r4) {
        if (WebViewFeatureInternal.WEB_MESSAGE_LISTENER.isSupportedByWebView() == false) goto L7;
        getProvider(r1).addWebMessageListener(r2, (String[]) r3.toArray(new String[0]), r4);
        return;
    L7:
        throw WebViewFeatureInternal.getUnsupportedOperationException();
    }

    private static WebViewProviderBoundaryInterface createProvider(WebView r1) {
        return getFactory().createWebView(r1);
    }

    public static PackageInfo getCurrentLoadedWebViewPackage() {
        if (Build.VERSION.SDK_INT >= 26) goto L5;
        return getLoadedWebViewPackageInfo();
    L8:
        return null;
    L5:
        return ApiHelperForO.getCurrentWebViewPackage();
    }

    private static WebViewProviderFactory getFactory() {
        return WebViewGlueCommunicator.getFactory();
    }

    @SuppressLint({"PrivateApi", "BanUncheckedReflection"})
    private static PackageInfo getLoadedWebViewPackageInfo() {
        return (PackageInfo) Class.forName("android.webkit.WebViewFactory").getMethod("getLoadedPackageInfo", null).invoke(null, null);
    }

    private static WebViewProviderAdapter getProvider(WebView r3) {
        if (WebViewFeatureInternal.PROVIDER_WEAKLY_REF_WEBVIEW.isSupportedByWebView() == false) goto L9;
        WeakHashMap<WebView, WebViewProviderAdapter> r0 = sProviderAdapterCache;
        WebViewProviderAdapter r1 = r0.get(r3);
        if (r1 != null) goto L10;
        WebViewProviderAdapter r12 = new WebViewProviderAdapter(createProvider(r3));
        r0.put(r3, r12);
        return r12;
    L10:
        return r1;
    L9:
        return new WebViewProviderAdapter(createProvider(r3));
    }

    public static boolean isMultiProcessEnabled() {
        if (WebViewFeatureInternal.MULTI_PROCESS.isSupportedByWebView() == false) goto L7;
        return getFactory().getStatics().isMultiProcessEnabled();
    L7:
        throw WebViewFeatureInternal.getUnsupportedOperationException();
    }
}
