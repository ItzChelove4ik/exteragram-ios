package org.mvel2.ast;

import org.mvel2.MVEL;
import org.mvel2.ParserContext;
import org.mvel2.compiler.Accessor;
import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.integration.impl.StackDemarcResolverFactory;
import org.mvel2.util.ParseTools;

/* loaded from: classes5.dex */
public class ReturnNode extends ASTNode {
    public ReturnNode(char[] r1, int r2, int r3, int r4, ParserContext r5) {
        super(r5);
        this.expr = r1;
        this.start = r2;
        this.offset = r3;
        if ((r4 & 16) == 0) goto L6;
        setAccessor((Accessor) ParseTools.subCompileExpression(r1, r2, r3, r5));
        return;
    }

    @Override // org.mvel2.ast.ASTNode
    public Integer getOperator() {
        return 99;
    }

    @Override // org.mvel2.ast.ASTNode
    public Object getReducedValue(Object r3, Object r4, VariableResolverFactory r5) {
        r5.setTiltFlag(true);
        return MVEL.eval(this.expr, this.start, this.offset, r3, new StackDemarcResolverFactory(r5));
    }

    @Override // org.mvel2.ast.ASTNode
    public Object getReducedValueAccelerated(Object r5, Object r6, VariableResolverFactory r7) {
        if (this.accessor != null) goto L5;
        setAccessor((Accessor) ParseTools.subCompileExpression(this.expr, this.start, this.offset, this.pCtx));
    L5:
        r7.setTiltFlag(true);
        return this.accessor.getValue(r5, r6, new StackDemarcResolverFactory(r7));
    }

    @Override // org.mvel2.ast.ASTNode
    public boolean isOperator() {
        return true;
    }

    @Override // org.mvel2.ast.ASTNode
    public boolean isOperator(Integer r1) {
        if (99 != r1.intValue()) goto L6;
        return true;
    L6:
        return false;
    }
}
