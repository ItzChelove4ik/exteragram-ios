package com.sun.jna;

import java.lang.reflect.Method;

/* loaded from: classes5.dex */
public class MethodResultContext extends FunctionResultContext {
    private final Method method;

    public MethodResultContext(Class<?> r1, Function r2, Object[] r3, Method r4) {
        super(r1, r2, r3);
        this.method = r4;
    }

    public Method getMethod() {
        return this.method;
    }
}
