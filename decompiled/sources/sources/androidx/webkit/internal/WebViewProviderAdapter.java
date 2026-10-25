package androidx.webkit.internal;

import androidx.webkit.WebViewCompat;
import org.chromium.support_lib_boundary.WebViewProviderBoundaryInterface;
import org.chromium.support_lib_boundary.util.BoundaryInterfaceReflectionUtil;

/* loaded from: classes4.dex */
public class WebViewProviderAdapter {
    final WebViewProviderBoundaryInterface mImpl;

    public WebViewProviderAdapter(WebViewProviderBoundaryInterface r1) {
        this.mImpl = r1;
    }

    public ScriptHandlerImpl addDocumentStartJavaScript(String r1, String[] r2) {
        return ScriptHandlerImpl.toScriptHandler(this.mImpl.addDocumentStartJavaScript(r1, r2));
    }

    public void addWebMessageListener(String r2, String[] r3, WebViewCompat.WebMessageListener r4) {
        this.mImpl.addWebMessageListener(r2, r3, BoundaryInterfaceReflectionUtil.createInvocationHandlerFor(new WebMessageListenerAdapter(r4)));
    }
}
