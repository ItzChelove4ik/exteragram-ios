package org.mvel2.ast;

import org.mvel2.ParserContext;
import org.mvel2.integration.VariableResolverFactory;

/* loaded from: classes5.dex */
public class IntMult extends BinaryOperation implements IntOptimized {
    public IntMult(ASTNode r2, ASTNode r3, ParserContext r4) {
        super(2, r4);
        this.left = r2;
        this.right = r3;
    }

    @Override // org.mvel2.ast.ASTNode
    public Class getEgressType() {
        return Integer.class;
    }

    @Override // org.mvel2.ast.BinaryOperation, org.mvel2.ast.ASTNode
    public Object getReducedValue(Object r2, Object r3, VariableResolverFactory r4) {
        return Integer.valueOf(((Integer) this.left.getReducedValue(r2, r3, r4)).intValue() * ((Integer) this.right.getReducedValue(r2, r3, r4)).intValue());
    }

    @Override // org.mvel2.ast.BinaryOperation, org.mvel2.ast.ASTNode
    public Object getReducedValueAccelerated(Object r2, Object r3, VariableResolverFactory r4) {
        return Integer.valueOf(((Integer) this.left.getReducedValueAccelerated(r2, r3, r4)).intValue() * ((Integer) this.right.getReducedValueAccelerated(r2, r3, r4)).intValue());
    }
}
