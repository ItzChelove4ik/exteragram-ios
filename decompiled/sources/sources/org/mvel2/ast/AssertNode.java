package org.mvel2.ast;

import org.mvel2.MVEL;
import org.mvel2.ParserContext;
import org.mvel2.compiler.ExecutableStatement;
import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.util.ParseTools;

/* loaded from: classes5.dex */
public class AssertNode extends ASTNode {
    public ExecutableStatement assertion;
    public ExecutableStatement fail;

    public AssertNode(char[] r1, int r2, int r3, int r4, ParserContext r5) {
        super(r5);
        this.expr = r1;
        this.start = r2;
        this.offset = r3;
        if ((r4 & 16) == 0) goto L6;
        this.assertion = (ExecutableStatement) ParseTools.subCompileExpression(r1, r2, r3, r5);
        return;
    }

    @Override // org.mvel2.ast.ASTNode
    public Object getReducedValue(Object r4, Object r5, VariableResolverFactory r6) {
    L9:
        Sign$$ExternalSyntheticBUOutline0.m("assertion does not contain a boolean statement", this.expr, this.start);
        return null;
    L4:
        if (((Boolean) MVEL.eval(this.expr, r4, r6)).booleanValue() == false) goto L8;
        return Boolean.TRUE;
    L8:
        throw new AssertionError("assertion failed in expression: ".concat(new String(this.expr, this.start, this.offset)));     // Catch: ClassCastException -> L9
    }

    @Override // org.mvel2.ast.ASTNode
    public Object getReducedValueAccelerated(Object r4, Object r5, VariableResolverFactory r6) {
    L9:
        Sign$$ExternalSyntheticBUOutline0.m("assertion does not contain a boolean statement", this.expr, this.start);
        return null;
    L4:
        if (((Boolean) this.assertion.getValue(r4, r5, r6)).booleanValue() == false) goto L8;
        return Boolean.TRUE;
    L8:
        throw new AssertionError("assertion failed in expression: ".concat(new String(this.expr, this.start, this.offset)));     // Catch: ClassCastException -> L9
    }
}
