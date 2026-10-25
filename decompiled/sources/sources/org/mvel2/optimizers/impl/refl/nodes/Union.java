package org.mvel2.optimizers.impl.refl.nodes;

import org.mvel2.ParserContext;
import org.mvel2.compiler.Accessor;
import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.optimizers.AccessorOptimizer;
import org.mvel2.optimizers.OptimizerFactory;

/* loaded from: classes5.dex */
public class Union implements Accessor {
    private Accessor accessor;
    private Accessor nextAccessor;
    private char[] nextExpr;
    private int offset;
    private ParserContext pCtx;
    private int start;

    public Union(ParserContext r1, Accessor r2, char[] r3, int r4, int r5) {
        this.accessor = r2;
        this.start = r4;
        this.offset = r5;
        this.nextExpr = r3;
        this.pCtx = r1;
    }

    private Object get(Object r13, Object r14, VariableResolverFactory r15) {
        Accessor r0 = this.nextAccessor;
        Accessor r1 = this.accessor;
        if (r0 != null) goto L7;
        Object r7 = r1.getValue(r13, r14, r15);
        AccessorOptimizer r2 = OptimizerFactory.getDefaultAccessorCompiler();
        Class r11 = this.accessor.getKnownEgressType();
        this.nextAccessor = r2.optimizeAccessor(this.pCtx, this.nextExpr, this.start, this.offset, r7, r14, r15, false, r11);
        return r2.getResultOptPass();
    L7:
        return r1.getValue(r13, r14, r15);
    }

    @Override // org.mvel2.compiler.Accessor
    public Class getKnownEgressType() {
        return this.nextAccessor.getKnownEgressType();
    }

    public Class getLeftIngressType() {
        return this.accessor.getKnownEgressType();
    }

    @Override // org.mvel2.compiler.Accessor
    public Object getValue(Object r2, Object r3, VariableResolverFactory r4) {
        Accessor r0 = this.nextAccessor;
        if (r0 != null) goto L7;
        return get(r2, r3, r4);
    L7:
        return r0.getValue(get(r2, r3, r4), r3, r4);
    }

    @Override // org.mvel2.compiler.Accessor
    public Object setValue(Object r2, Object r3, VariableResolverFactory r4, Object r5) {
        return this.nextAccessor.setValue(get(r2, r3, r4), r3, r4, r5);
    }
}
