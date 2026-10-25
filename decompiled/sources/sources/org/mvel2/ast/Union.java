package org.mvel2.ast;

import okhttp3.internal.url._UrlKt;
import org.mvel2.ParserContext;
import org.mvel2.PropertyAccessor;
import org.mvel2.compiler.Accessor;
import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.optimizers.AccessorOptimizer;
import org.mvel2.optimizers.OptimizerFactory;

/* loaded from: classes5.dex */
public class Union extends ASTNode {
    private transient Accessor accessor;
    private ASTNode main;

    public Union(char[] r7, int r8, int r9, int r10, ASTNode r11, ParserContext r12) {
        super(r7, r8, r9, r10, r12);
        this.main = r11;
    }

    @Override // org.mvel2.ast.ASTNode
    public Accessor getAccessor() {
        return this.accessor;
    }

    public Class getLeftEgressType() {
        return this.main.getEgressType();
    }

    public ASTNode getMain() {
        return this.main;
    }

    @Override // org.mvel2.ast.ASTNode
    public Object getReducedValue(Object r8, Object r9, VariableResolverFactory r10) {
        return PropertyAccessor.get(this.expr, this.start, this.offset, this.main.getReducedValue(r8, r9, r10), r10, r9, this.pCtx);
    }

    @Override // org.mvel2.ast.ASTNode
    public Object getReducedValueAccelerated(Object r11, Object r12, VariableResolverFactory r13) {
        Accessor r0 = this.accessor;
        if (r0 != null) goto L5;
        AccessorOptimizer r02 = OptimizerFactory.getThreadAccessorOptimizer();     // Catch: Throwable -> L9
        this.accessor = r02.optimizeAccessor(this.pCtx, this.expr, this.start, this.offset, this.main.getReducedValueAccelerated(r11, r12, r13), r12, r13, false, this.main.getEgressType());     // Catch: Throwable -> L9
        Object r10 = r02.getResultOptPass();     // Catch: Throwable -> L9
        OptimizerFactory.clearThreadAccessorOptimizer();
        return r10;
    L9:
        th = move-exception;
        OptimizerFactory.clearThreadAccessorOptimizer();
        throw th;
    L5:
        return r0.getValue(this.main.getReducedValueAccelerated(r11, r12, r13), r12, r13);
    }

    @Override // org.mvel2.ast.ASTNode
    public String toString() {
        StringBuilder r0 = new StringBuilder();
        ASTNode r1 = this.main;
        String r2 = _UrlKt.FRAGMENT_ENCODE_SET;
        if (r1 == null) goto L5;
        String r12 = r1.toString();
    L6:
        r0.append(r12);
        r0.append("-[union]->");
        Accessor r3 = this.accessor;
        if (r3 == null) goto L9;
        r2 = r3.toString();
    L9:
        r0.append(r2);
        return r0.toString();
    L5:
        r12 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L6
    }
}
