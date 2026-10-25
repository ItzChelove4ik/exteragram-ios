package org.mvel2.ast;

import org.mvel2.MVEL;
import org.mvel2.ParserContext;
import org.mvel2.compiler.ExecutableStatement;
import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.util.ParseTools;

/* loaded from: classes5.dex */
public class Substatement extends ASTNode {
    private ExecutableStatement statement;

    public Substatement(char[] r1, int r2, int r3, int r4, ParserContext r5) {
        super(r5);
        this.expr = r1;
        this.start = r2;
        this.offset = r3;
        this.fields = r4;
        if ((r4 & 16) == 0) goto L6;
        ExecutableStatement r12 = (ExecutableStatement) ParseTools.subCompileExpression(r1, r2, r3, r5);
        this.statement = r12;
        this.egressType = r12.getKnownEgressType();
        return;
    }

    @Override // org.mvel2.ast.ASTNode
    public Object getReducedValue(Object r2, Object r3, VariableResolverFactory r4) {
        return MVEL.eval(this.expr, this.start, this.offset, r2, r4);
    }

    @Override // org.mvel2.ast.ASTNode
    public Object getReducedValueAccelerated(Object r1, Object r2, VariableResolverFactory r3) {
        return this.statement.getValue(r1, r2, r3);
    }

    public ExecutableStatement getStatement() {
        return this.statement;
    }

    @Override // org.mvel2.ast.ASTNode
    public String toString() {
        ExecutableStatement r0 = this.statement;
        if (r0 != null) goto L7;
        return "(" + new String(this.expr, this.start, this.offset) + ")";
    L7:
        return r0.toString();
    }
}
