package org.chromium.support_lib_boundary;

import android.os.Handler;
import java.lang.reflect.InvocationHandler;

/* loaded from: classes5.dex */
public interface WebMessagePortBoundaryInterface {
    void close();

    void postMessage(InvocationHandler r1);

    void setWebMessageCallback(InvocationHandler r1);

    void setWebMessageCallback(InvocationHandler r1, Handler r2);
}
