package org.mvel2.ast;

import org.mvel2.ParserContext;
import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.util.ParseTools;

/* loaded from: classes5.dex */
public class Contains extends ASTNode {
    private ASTNode stmt;
    private ASTNode stmt2;

    public Contains(ASTNode r1, ASTNode r2, ParserContext r3) {
        super(r3);
        this.stmt = r1;
        this.stmt2 = r2;
    }

    @Override // org.mvel2.ast.ASTNode
    public Class getEgressType() {
        return Boolean.class;
    }

    public ASTNode getFirstStatement() {
        return this.stmt;
    }

    @Override // org.mvel2.ast.ASTNode
    public Object getReducedValue(Object r1, Object r2, VariableResolverFactory r3) {
        throw new RuntimeException("operation not supported");
    }

    @Override // org.mvel2.ast.ASTNode
    public Object getReducedValueAccelerated(Object r2, Object r3, VariableResolverFactory r4) {
        return Boolean.valueOf(ParseTools.containsCheck(this.stmt.getReducedValueAccelerated(r2, r3, r4), this.stmt2.getReducedValueAccelerated(r2, r3, r4)));
    }

    public ASTNode getSecondStatement() {
        return this.stmt2;
    }
}
