package com.sun.jna;

/* loaded from: classes5.dex */
public class FunctionParameterContext extends ToNativeContext {
    private Object[] args;
    private Function function;
    private int index;

    public FunctionParameterContext(Function r1, Object[] r2, int r3) {
        this.function = r1;
        this.args = r2;
        this.index = r3;
    }

    public Function getFunction() {
        return this.function;
    }

    public int getParameterIndex() {
        return this.index;
    }

    public Object[] getParameters() {
        return this.args;
    }
}
