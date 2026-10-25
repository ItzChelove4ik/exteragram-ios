package androidx.webkit.internal;

import androidx.webkit.JavaScriptReplyProxy;
import java.lang.reflect.InvocationHandler;
import java.util.Objects;
import org.chromium.support_lib_boundary.JsReplyProxyBoundaryInterface;
import org.chromium.support_lib_boundary.util.BoundaryInterfaceReflectionUtil;

/* loaded from: classes4.dex */
public class JavaScriptReplyProxyImpl extends JavaScriptReplyProxy {
    private final JsReplyProxyBoundaryInterface mBoundaryInterface;

    /* renamed from: $r8$lambda$05zhUQJag_W78l1vIN5-l8vJcBk, reason: not valid java name */
    public static /* synthetic */ Object m966$r8$lambda$05zhUQJag_W78l1vIN5l8vJcBk(JsReplyProxyBoundaryInterface r1) {
        return new JavaScriptReplyProxyImpl(r1);
    }

    public JavaScriptReplyProxyImpl(JsReplyProxyBoundaryInterface r1) {
        this.mBoundaryInterface = r1;
    }

    public static JavaScriptReplyProxyImpl forInvocationHandler(InvocationHandler r1) {
        JsReplyProxyBoundaryInterface r12 = (JsReplyProxyBoundaryInterface) BoundaryInterfaceReflectionUtil.castToSuppLibClass(JsReplyProxyBoundaryInterface.class, r1);
        return (JavaScriptReplyProxyImpl) r12.getOrCreatePeer(new JavaScriptReplyProxyImpl$$ExternalSyntheticLambda0(r12));
    }

    @Override // androidx.webkit.JavaScriptReplyProxy
    public void postMessage(String r2) {
        if (WebViewFeatureInternal.WEB_MESSAGE_LISTENER.isSupportedByWebView() == false) goto L7;
        this.mBoundaryInterface.postMessage(r2);
        return;
    L7:
        throw WebViewFeatureInternal.getUnsupportedOperationException();
    }

    @Override // androidx.webkit.JavaScriptReplyProxy
    public void postMessage(byte[] r2) {
        Objects.requireNonNull(r2, "ArrayBuffer must be non-null");
        if (WebViewFeatureInternal.WEB_MESSAGE_ARRAY_BUFFER.isSupportedByWebView() == false) goto L7;
        this.mBoundaryInterface.postMessageWithPayload(BoundaryInterfaceReflectionUtil.createInvocationHandlerFor(new WebMessagePayloadAdapter(r2)));
        return;
    L7:
        throw WebViewFeatureInternal.getUnsupportedOperationException();
    }
}
