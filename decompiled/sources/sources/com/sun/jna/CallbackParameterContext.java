package com.sun.jna;

import java.lang.reflect.Method;

/* loaded from: classes5.dex */
public class CallbackParameterContext extends FromNativeContext {
    private Object[] args;
    private int index;
    private Method method;

    public CallbackParameterContext(Class<?> r1, Method r2, Object[] r3, int r4) {
        super(r1);
        this.method = r2;
        this.args = r3;
        this.index = r4;
    }

    public Object[] getArguments() {
        return this.args;
    }

    public int getIndex() {
        return this.index;
    }

    public Method getMethod() {
        return this.method;
    }
}
