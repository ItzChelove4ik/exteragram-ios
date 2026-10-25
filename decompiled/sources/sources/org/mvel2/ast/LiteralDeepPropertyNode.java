package org.mvel2.ast;

import org.mvel2.ParserContext;
import org.mvel2.PropertyAccessor;
import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.optimizers.AccessorOptimizer;
import org.mvel2.optimizers.OptimizerFactory;

/* loaded from: classes5.dex */
public class LiteralDeepPropertyNode extends ASTNode {
    private Object literal;

    public LiteralDeepPropertyNode(char[] r1, int r2, int r3, int r4, Object r5, ParserContext r6) {
        super(r6);
        this.fields = r4;
        this.expr = r1;
        this.start = r2;
        this.offset = r3;
        this.literal = r5;
    }

    @Override // org.mvel2.ast.ASTNode
    public Object getReducedValue(Object r8, Object r9, VariableResolverFactory r10) {
        return PropertyAccessor.get(this.expr, this.start, this.offset, this.literal, r10, r9, this.pCtx);
    }

    @Override // org.mvel2.ast.ASTNode
    public Object getReducedValueAccelerated(Object r11, Object r12, VariableResolverFactory r13) {
        if (this.accessor != null) goto L5;
        AccessorOptimizer r0 = OptimizerFactory.getThreadAccessorOptimizer();     // Catch: Throwable -> L9
        this.accessor = r0.optimizeAccessor(this.pCtx, this.expr, this.start, this.offset, this.literal, r12, r13, false, null);     // Catch: Throwable -> L9
        Object r10 = r0.getResultOptPass();     // Catch: Throwable -> L9
        OptimizerFactory.clearThreadAccessorOptimizer();
        return r10;
    L9:
        th = move-exception;
        OptimizerFactory.clearThreadAccessorOptimizer();
        throw th;
    L5:
        return this.accessor.getValue(this.literal, r12, r13);
    }
}
