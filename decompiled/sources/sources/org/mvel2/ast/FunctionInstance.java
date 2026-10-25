package org.mvel2.ast;

import org.mvel2.integration.VariableResolverFactory;

/* loaded from: classes5.dex */
public class FunctionInstance {
    protected final Function function;

    public FunctionInstance(Function r1) {
        this.function = r1;
    }

    public Object call(Object r1, Object r2, VariableResolverFactory r3, Object[] r4) {
        return this.function.call(r1, r2, r3, r4);
    }

    public Function getFunction() {
        return this.function;
    }
}
