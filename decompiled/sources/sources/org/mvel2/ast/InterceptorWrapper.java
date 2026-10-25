package org.mvel2.ast;

import org.mvel2.ParserContext;
import org.mvel2.integration.Interceptor;
import org.mvel2.integration.VariableResolverFactory;

/* loaded from: classes5.dex */
public class InterceptorWrapper extends ASTNode {
    private Interceptor interceptor;
    private ASTNode node;

    public InterceptorWrapper(Interceptor r1, ASTNode r2, ParserContext r3) {
        super(r3);
        this.interceptor = r1;
        this.node = r2;
    }

    @Override // org.mvel2.ast.ASTNode
    public Object getReducedValue(Object r3, Object r4, VariableResolverFactory r5) {
        this.interceptor.doBefore(this.node, r5);
        Interceptor r0 = this.interceptor;
        Object r32 = this.node.getReducedValue(r3, r4, r5);
        r0.doAfter(r32, this.node, r5);
        return r32;
    }

    @Override // org.mvel2.ast.ASTNode
    public Object getReducedValueAccelerated(Object r3, Object r4, VariableResolverFactory r5) {
        this.interceptor.doBefore(this.node, r5);
        Interceptor r0 = this.interceptor;
        Object r32 = this.node.getReducedValueAccelerated(r3, r4, r5);
        r0.doAfter(r32, this.node, r5);
        return r32;
    }
}
