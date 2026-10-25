package org.mvel2.ast;

import org.mvel2.ParserContext;
import org.mvel2.integration.VariableResolverFactory;

/* loaded from: classes5.dex */
public class EndOfStatement extends ASTNode {
    public EndOfStatement(ParserContext r1) {
        super(r1);
        this.literal = getOperator();
    }

    @Override // org.mvel2.ast.ASTNode
    public Integer getOperator() {
        return 37;
    }

    @Override // org.mvel2.ast.ASTNode
    public Object getReducedValue(Object r1, Object r2, VariableResolverFactory r3) {
        return null;
    }

    @Override // org.mvel2.ast.ASTNode
    public Object getReducedValueAccelerated(Object r1, Object r2, VariableResolverFactory r3) {
        return null;
    }

    @Override // org.mvel2.ast.ASTNode
    public boolean isOperator() {
        return true;
    }

    @Override // org.mvel2.ast.ASTNode
    public boolean isOperator(Integer r1) {
        if (r1.intValue() != 37) goto L6;
        return true;
    L6:
        return false;
    }
}
