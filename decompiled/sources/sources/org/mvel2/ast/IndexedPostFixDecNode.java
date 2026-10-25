package org.mvel2.ast;

import org.mvel2.ParserContext;
import org.mvel2.integration.VariableResolver;
import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.math.MathProcessor;
import org.mvel2.util.ParseTools;

/* loaded from: classes5.dex */
public class IndexedPostFixDecNode extends ASTNode {
    private int register;

    public IndexedPostFixDecNode(int r2, ParserContext r3) {
        super(r3);
        this.register = r2;
        this.egressType = r3.getVarOrInputType(r3.getIndexedVarNames()[r2]);
    }

    @Override // org.mvel2.ast.ASTNode
    public Object getReducedValue(Object r1, Object r2, VariableResolverFactory r3) {
        return getReducedValueAccelerated(r1, r2, r3);
    }

    @Override // org.mvel2.ast.ASTNode
    public Object getReducedValueAccelerated(Object r3, Object r4, VariableResolverFactory r5) {
        VariableResolver r2 = r5.getIndexedVariableResolver(this.register);
        Object r32 = r2.getValue();
        r2.setValue(MathProcessor.doOperations(ParseTools.resolveType(r32), r32, 1, 101, 1));
        return r32;
    }
}
