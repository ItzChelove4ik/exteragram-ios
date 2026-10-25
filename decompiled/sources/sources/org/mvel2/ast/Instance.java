package org.mvel2.ast;

import org.mvel2.ParserContext;
import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.util.CompilerTools;

/* loaded from: classes5.dex */
public class Instance extends ASTNode {
    private ASTNode clsStmt;
    private ASTNode stmt;

    public Instance(ASTNode r1, ASTNode r2, ParserContext r3) {
        super(r3);
        this.stmt = r1;
        this.clsStmt = r2;
        CompilerTools.expectType(r3, r2, Class.class, true);
    }

    public ASTNode getClassStatement() {
        return this.clsStmt;
    }

    @Override // org.mvel2.ast.ASTNode
    public Class getEgressType() {
        return Boolean.class;
    }

    @Override // org.mvel2.ast.ASTNode
    public Object getReducedValue(Object r3, Object r4, VariableResolverFactory r5) {
        Class r0 = (Class) this.clsStmt.getReducedValue(r3, r4, r5);     // Catch: ClassCastException -> L8
        if (r0 == null) goto L7;
        return Boolean.valueOf(r0.isInstance(this.stmt.getReducedValue(r3, r4, r5)));
    L7:
        throw new ClassCastException();     // Catch: ClassCastException -> L8
    L8:
        Instance$$ExternalSyntheticBUOutline0.m("not a class reference: ", this.clsStmt.getName());
        return null;
    }

    @Override // org.mvel2.ast.ASTNode
    public Object getReducedValueAccelerated(Object r2, Object r3, VariableResolverFactory r4) {
        return Boolean.valueOf(((Class) this.clsStmt.getReducedValueAccelerated(r2, r3, r4)).isInstance(this.stmt.getReducedValueAccelerated(r2, r3, r4)));
    }

    public ASTNode getStatement() {
        return this.stmt;
    }
}
