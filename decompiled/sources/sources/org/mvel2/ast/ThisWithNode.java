package org.mvel2.ast;

import org.mvel2.MVEL;
import org.mvel2.ParserContext;
import org.mvel2.ast.WithNode;
import org.mvel2.integration.VariableResolverFactory;

/* loaded from: classes5.dex */
public class ThisWithNode extends WithNode {
    public ThisWithNode(char[] r1, int r2, int r3, int r4, int r5, int r6, ParserContext r7) {
        super(r1, r2, r3, r4, r5, r6, r7);
    }

    @Override // org.mvel2.ast.WithNode, org.mvel2.ast.ASTNode
    public Object getReducedValue(Object r1, Object r2, VariableResolverFactory r3) {
        return getReducedValueAccelerated(r1, r2, r3);
    }

    @Override // org.mvel2.ast.WithNode, org.mvel2.ast.ASTNode
    public Object getReducedValueAccelerated(Object r5, Object r6, VariableResolverFactory r7) {
        if (r6 == null) goto L11;
        WithNode.ParmValuePair[] r4 = this.withExpressions;
        int r0 = r4.length;
        int r1 = 0;
    L4:
        if (r1 >= r0) goto L10;
        WithNode.ParmValuePair r2 = r4[r1];
        if (r2.getSetExpression() == null) goto L8;
        MVEL.executeSetExpression(r2.getSetExpression(), r6, r7, r2.getStatement().getValue(r5, r6, r7));
    L9:
        r1 = r1 + 1;
        goto L4
    L8:
        r2.getStatement().getValue(r6, r6, r7);
        goto L9
    L10:
        return r6;
    L11:
        Sign$$ExternalSyntheticBUOutline0.m("with-block against null pointer (this)", this.expr, this.start);
        return null;
    }
}
