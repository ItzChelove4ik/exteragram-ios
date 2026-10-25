package com.sun.jna;

import java.lang.reflect.Method;

/* loaded from: classes5.dex */
public class CallbackResultContext extends ToNativeContext {
    private Method method;

    public CallbackResultContext(Method r1) {
        this.method = r1;
    }

    public Method getMethod() {
        return this.method;
    }
}
