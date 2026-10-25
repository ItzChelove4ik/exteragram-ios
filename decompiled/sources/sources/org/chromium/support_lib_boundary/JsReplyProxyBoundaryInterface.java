package org.chromium.support_lib_boundary;

import java.lang.reflect.InvocationHandler;

/* loaded from: classes5.dex */
public interface JsReplyProxyBoundaryInterface extends IsomorphicObjectBoundaryInterface {
    void executeJavaScript(String r1, InvocationHandler r2);

    void postMessage(String r1);

    void postMessageWithPayload(InvocationHandler r1);
}
