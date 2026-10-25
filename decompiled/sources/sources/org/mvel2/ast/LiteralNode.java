package org.mvel2.ast;

import org.mvel2.ParserContext;
import org.mvel2.compiler.BlankLiteral;
import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.util.NullType;

/* loaded from: classes5.dex */
public class LiteralNode extends ASTNode {
    public LiteralNode(Object r1, Class r2, ParserContext r3) {
        this(r1, r3);
        this.egressType = r2;
    }

    public LiteralNode(Object r1, ParserContext r2) {
        super(r2);
        this.literal = r1;
        if (r1 == null) goto L8;
        Class<?> r12 = r1.getClass();
        this.egressType = r12;
        if (r12 != BlankLiteral.class) goto L10;
        this.egressType = Object.class;
        return;
    L10:
        return;
    L8:
        this.egressType = NullType.class;
    }

    @Override // org.mvel2.ast.ASTNode
    public Object getLiteralValue() {
        return this.literal;
    }

    @Override // org.mvel2.ast.ASTNode
    public Object getReducedValue(Object r1, Object r2, VariableResolverFactory r3) {
        return this.literal;
    }

    @Override // org.mvel2.ast.ASTNode
    public Object getReducedValueAccelerated(Object r1, Object r2, VariableResolverFactory r3) {
        return this.literal;
    }

    @Override // org.mvel2.ast.ASTNode
    public boolean isLiteral() {
        return true;
    }

    @Override // org.mvel2.ast.ASTNode
    public void setLiteralValue(Object r1) {
        this.literal = r1;
    }

    @Override // org.mvel2.ast.ASTNode
    public String toString() {
        return "Literal<" + this.literal + ">";
    }
}
