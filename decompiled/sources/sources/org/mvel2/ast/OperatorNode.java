package org.mvel2.ast;

import org.mvel2.CompileException;
import org.mvel2.ParserContext;
import org.mvel2.debug.DebugTools;
import org.mvel2.integration.VariableResolverFactory;

/* loaded from: classes5.dex */
public class OperatorNode extends ASTNode {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private Integer operator;

    static {
    }

    public OperatorNode(Integer r1, char[] r2, int r3, ParserContext r4) {
        super(r4);
        this.expr = r2;
        this.operator = r1;
        this.literal = r1;
        this.start = r3;
    }

    @Override // org.mvel2.ast.ASTNode
    public Integer getOperator() {
        return this.operator;
    }

    @Override // org.mvel2.ast.ASTNode
    public Object getReducedValue(Object r1, Object r2, VariableResolverFactory r3) {
        throw new CompileException("illegal use of operator: " + DebugTools.getOperatorSymbol(this.operator.intValue()), this.expr, this.start);
    }

    @Override // org.mvel2.ast.ASTNode
    public Object getReducedValueAccelerated(Object r1, Object r2, VariableResolverFactory r3) {
        throw new CompileException("illegal use of operator: " + DebugTools.getOperatorSymbol(this.operator.intValue()), this.expr, this.start);
    }

    @Override // org.mvel2.ast.ASTNode
    public boolean isOperator() {
        return true;
    }

    @Override // org.mvel2.ast.ASTNode
    public boolean isOperator(Integer r1) {
        return r1.equals(this.operator);
    }
}
