package org.mvel2.ast;

import org.mvel2.integration.VariableResolverFactory;

/* loaded from: classes5.dex */
public class PrototypalFunctionInstance extends FunctionInstance {
    private final VariableResolverFactory resolverFactory;

    public PrototypalFunctionInstance(Function r1, VariableResolverFactory r2) {
        super(r1);
        this.resolverFactory = r2;
    }

    @Override // org.mvel2.ast.FunctionInstance
    public Object call(Object r3, Object r4, VariableResolverFactory r5, Object[] r6) {
        return this.function.call(r3, r4, new InvokationContextFactory(r5, this.resolverFactory), r6);
    }

    public VariableResolverFactory getResolverFactory() {
        return this.resolverFactory;
    }

    public String toString() {
        return "function_prototype:" + this.function.getName();
    }
}
