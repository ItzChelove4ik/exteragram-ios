package org.mvel2.ast;

import org.mvel2.MVEL;
import org.mvel2.ParserContext;
import org.mvel2.compiler.ExecutableStatement;
import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.util.ParseTools;

/* loaded from: classes5.dex */
public class Negation extends ASTNode {
    private ExecutableStatement stmt;

    public Negation(char[] r1, int r2, int r3, int r4, ParserContext r5) {
        super(r5);
        this.expr = r1;
        this.start = r2;
        this.offset = r3;
        if ((r4 & 16) == 0) goto L11;
        ExecutableStatement r32 = (ExecutableStatement) ParseTools.subCompileExpression(r1, r2, r3, r5);
        this.stmt = r32;
        if (r32.getKnownEgressType() != null) goto L7;
        return;
    L7:
        if (ParseTools.boxPrimitive(this.stmt.getKnownEgressType()).isAssignableFrom(Boolean.class) == true) goto L13;
        Sign$$ExternalSyntheticBUOutline0.m("negation operator cannot be applied to non-boolean type", r1, r2);
        throw null;
    L13:
        return;
    }

    @Override // org.mvel2.ast.ASTNode
    public Class getEgressType() {
        return Boolean.class;
    }

    @Override // org.mvel2.ast.ASTNode
    public Object getReducedValue(Object r4, Object r5, VariableResolverFactory r6) {
        return Boolean.valueOf(!((Boolean) MVEL.eval(this.expr, this.start, this.offset, r4, r6)).booleanValue());
    L5:
        e = move-exception;
        Negation$$ExternalSyntheticBUOutline0.m("negation operator applied to non-boolean expression", this.expr, this.start, e);
        return null;
    L7:
        e = move-exception;
        Negation$$ExternalSyntheticBUOutline0.m("negation operator applied to a null value", this.expr, this.start, e);
        return null;
    }

    @Override // org.mvel2.ast.ASTNode
    public Object getReducedValueAccelerated(Object r1, Object r2, VariableResolverFactory r3) {
        return Boolean.valueOf(!((Boolean) this.stmt.getValue(r1, r2, r3)).booleanValue());
    }

    public ExecutableStatement getStatement() {
        return this.stmt;
    }
}
