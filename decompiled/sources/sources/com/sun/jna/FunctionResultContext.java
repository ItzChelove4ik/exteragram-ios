package com.sun.jna;

/* loaded from: classes5.dex */
public class FunctionResultContext extends FromNativeContext {
    private Object[] args;
    private Function function;

    public FunctionResultContext(Class<?> r1, Function r2, Object[] r3) {
        super(r1);
        this.function = r2;
        this.args = r3;
    }

    public Object[] getArguments() {
        return this.args;
    }

    public Function getFunction() {
        return this.function;
    }
}
