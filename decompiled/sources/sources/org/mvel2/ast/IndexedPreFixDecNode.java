package org.mvel2.ast;

import org.mvel2.ParserContext;
import org.mvel2.integration.VariableResolver;
import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.math.MathProcessor;

/* loaded from: classes5.dex */
public class IndexedPreFixDecNode extends ASTNode {
    private int register;

    public IndexedPreFixDecNode(int r2, ParserContext r3) {
        super(r3);
        this.register = r2;
        this.egressType = r3.getVarOrInputType(r3.getIndexedVarNames()[r2]);
    }

    @Override // org.mvel2.ast.ASTNode
    public Object getReducedValue(Object r1, Object r2, VariableResolverFactory r3) {
        return getReducedValueAccelerated(r1, r2, r3);
    }

    @Override // org.mvel2.ast.ASTNode
    public Object getReducedValueAccelerated(Object r2, Object r3, VariableResolverFactory r4) {
        VariableResolver r1 = r4.getIndexedVariableResolver(this.register);
        Object r22 = MathProcessor.doOperations(r1.getValue(), 1, 101, 1);
        r1.setValue(r22);
        return r22;
    }
}
