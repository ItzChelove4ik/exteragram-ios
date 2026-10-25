package androidx.webkit.internal;

import android.net.Uri;
import android.webkit.WebView;
import androidx.webkit.WebMessageCompat;
import androidx.webkit.WebViewCompat;
import java.lang.reflect.InvocationHandler;
import org.chromium.support_lib_boundary.WebMessageBoundaryInterface;
import org.chromium.support_lib_boundary.WebMessageListenerBoundaryInterface;
import org.chromium.support_lib_boundary.util.BoundaryInterfaceReflectionUtil;

/* loaded from: classes4.dex */
public class WebMessageListenerAdapter implements WebMessageListenerBoundaryInterface {
    private final WebViewCompat.WebMessageListener mWebMessageListener;

    public WebMessageListenerAdapter(WebViewCompat.WebMessageListener r1) {
        this.mWebMessageListener = r1;
    }

    @Override // org.chromium.support_lib_boundary.FeatureFlagHolderBoundaryInterface
    public String[] getSupportedFeatures() {
        return new String[]{"WEB_MESSAGE_LISTENER", "WEB_MESSAGE_ARRAY_BUFFER"};
    }

    @Override // org.chromium.support_lib_boundary.WebMessageListenerBoundaryInterface
    public void onPostMessage(WebView r7, InvocationHandler r8, Uri r9, boolean r10, InvocationHandler r11) {
        WebMessageCompat r2 = WebMessageAdapter.webMessageCompatFromBoundaryInterface((WebMessageBoundaryInterface) BoundaryInterfaceReflectionUtil.castToSuppLibClass(WebMessageBoundaryInterface.class, r8));
        if (r2 == null) goto L6;
        JavaScriptReplyProxyImpl r5 = JavaScriptReplyProxyImpl.forInvocationHandler(r11);
        this.mWebMessageListener.onPostMessage(r7, r2, r9, r10, r5);
        return;
    }
}
