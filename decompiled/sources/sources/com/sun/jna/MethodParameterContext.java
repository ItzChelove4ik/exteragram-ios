package com.sun.jna;

import java.lang.reflect.Method;

/* loaded from: classes5.dex */
public class MethodParameterContext extends FunctionParameterContext {
    private Method method;

    public MethodParameterContext(Function r1, Object[] r2, int r3, Method r4) {
        super(r1, r2, r3);
        this.method = r4;
    }

    public Method getMethod() {
        return this.method;
    }
}
