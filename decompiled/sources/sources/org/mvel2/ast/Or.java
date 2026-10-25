package org.mvel2.ast;

import org.mvel2.ParserContext;
import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.util.CompilerTools;

/* loaded from: classes5.dex */
public class Or extends BooleanNode {
    public Or(ASTNode r2, ASTNode r3, boolean r4, ParserContext r5) {
        super(r5);
        this.left = r2;
        CompilerTools.expectType(r5, r2, Boolean.class, r4);
        this.right = r3;
        CompilerTools.expectType(r5, r3, Boolean.class, r4);
    }

    @Override // org.mvel2.ast.ASTNode
    public Class getEgressType() {
        return Boolean.class;
    }

    @Override // org.mvel2.ast.ASTNode
    public Object getReducedValue(Object r1, Object r2, VariableResolverFactory r3) {
        throw new RuntimeException("improper use of AST element");
    }

    @Override // org.mvel2.ast.ASTNode
    public Object getReducedValueAccelerated(Object r2, Object r3, VariableResolverFactory r4) {
        if (((Boolean) this.left.getReducedValueAccelerated(r2, r3, r4)).booleanValue() == false) goto L5;
    L8:
        boolean r1 = true;
    L10:
        return Boolean.valueOf(r1);
    L5:
        if (((Boolean) this.right.getReducedValueAccelerated(r2, r3, r4)).booleanValue() == true) goto L8;
        r1 = false;
        goto L10
    }

    @Override // org.mvel2.ast.BooleanNode
    public ASTNode getRightMost() {
    L2:
        ASTNode r1 = this.right;
        if (r1 == null) goto L7;
        if ((r1 instanceof Or) == false) goto L7;
        Or this = (Or) r1;
    L7:
        return r1;
    }

    @Override // org.mvel2.ast.BooleanNode
    public void setRightMost(ASTNode r3) {
    L2:
        ASTNode r0 = this.right;
        if (r0 == null) goto L7;
        if ((r0 instanceof Or) == false) goto L7;
        Or this = (Or) r0;
    L7:
        this.right = r3;
    }

    @Override // org.mvel2.ast.ASTNode
    public String toString() {
        return "(" + this.left.toString() + " || " + this.right.toString() + ")";
    }
}
