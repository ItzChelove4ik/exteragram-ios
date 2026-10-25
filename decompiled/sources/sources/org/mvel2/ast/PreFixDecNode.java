package org.mvel2.ast;

import org.mvel2.ParserContext;
import org.mvel2.integration.VariableResolver;
import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.math.MathProcessor;

/* loaded from: classes5.dex */
public class PreFixDecNode extends ASTNode {
    private String name;

    public PreFixDecNode(String r1, ParserContext r2) {
        super(r2);
        this.name = r1;
        if (r2 == null) goto L6;
        this.egressType = r2.getVarOrInputType(r1);
        return;
    }

    @Override // org.mvel2.ast.ASTNode
    public Object getReducedValue(Object r1, Object r2, VariableResolverFactory r3) {
        return getReducedValueAccelerated(r1, r2, r3);
    }

    @Override // org.mvel2.ast.ASTNode
    public Object getReducedValueAccelerated(Object r2, Object r3, VariableResolverFactory r4) {
        VariableResolver r1 = r4.getVariableResolver(this.name);
        Object r22 = MathProcessor.doOperations(r1.getValue(), 1, 101, 1);
        r1.setValue(r22);
        return r22;
    }
}
