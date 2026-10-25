package org.mvel2.ast;

import org.mvel2.DataConversion;
import org.mvel2.ParserContext;
import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.util.CompilerTools;

/* loaded from: classes5.dex */
public class Convertable extends ASTNode {
    private ASTNode clsStmt;
    private ASTNode stmt;

    public Convertable(ASTNode r1, ASTNode r2, ParserContext r3) {
        super(r3);
        this.stmt = r1;
        this.clsStmt = r2;
        CompilerTools.expectType(r3, r2, Class.class, true);
    }

    @Override // org.mvel2.ast.ASTNode
    public Class getEgressType() {
        return Boolean.class;
    }

    @Override // org.mvel2.ast.ASTNode
    public Object getReducedValue(Object r3, Object r4, VariableResolverFactory r5) {
        Object r0 = this.stmt.getReducedValue(r3, r4, r5);     // Catch: ClassCastException -> L12
        if (r0 == null) goto L5;
        Class r32 = (Class) this.clsStmt.getReducedValue(r3, r4, r5);     // Catch: ClassCastException -> L12
        if (r32 == null) goto L11;
        return Boolean.valueOf(DataConversion.canConvert(r32, r0.getClass()));
    L11:
        throw new ClassCastException();     // Catch: ClassCastException -> L12
    L5:
        return Boolean.FALSE;
    L12:
        Instance$$ExternalSyntheticBUOutline0.m("not a class reference: ", this.clsStmt.getName());
        return null;
    }

    @Override // org.mvel2.ast.ASTNode
    public Object getReducedValueAccelerated(Object r2, Object r3, VariableResolverFactory r4) {
        Object r0 = this.stmt.getReducedValueAccelerated(r2, r3, r4);
        if (r0 != null) goto L5;
    L7:
        boolean r1 = false;
    L9:
        return Boolean.valueOf(r1);
    L5:
        if (DataConversion.canConvert((Class) this.clsStmt.getReducedValueAccelerated(r2, r3, r4), r0.getClass()) == false) goto L7;
        r1 = true;
        goto L9
    }
}
