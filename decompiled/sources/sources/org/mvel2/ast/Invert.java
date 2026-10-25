package org.mvel2.ast;

import org.mvel2.CompileException;
import org.mvel2.MVEL;
import org.mvel2.ParserContext;
import org.mvel2.compiler.ExecutableStatement;
import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.util.CompilerTools;
import org.mvel2.util.ParseTools;

/* loaded from: classes5.dex */
public class Invert extends ASTNode {
    private ExecutableStatement stmt;

    public Invert(char[] r1, int r2, int r3, int r4, ParserContext r5) {
        super(r5);
        this.expr = r1;
        this.start = r2;
        this.offset = r3;
        if ((r4 & 16) == 0) goto L6;
        ExecutableStatement r12 = (ExecutableStatement) ParseTools.subCompileExpression(r1, r2, r3, r5);
        this.stmt = r12;
        CompilerTools.expectType(r5, r12, Integer.class, true);
        return;
    }

    @Override // org.mvel2.ast.ASTNode
    public Object getReducedValue(Object r3, Object r4, VariableResolverFactory r5) {
        Object r32 = MVEL.eval(this.expr, this.start, this.offset, r3, r5);
        if ((r32 instanceof Integer) == false) goto L7;
        return Integer.valueOf(~((Integer) r32).intValue());
    L7:
        if (r32 != null) goto L9;
        String r33 = "null";
    L11:
        throw new CompileException("was expecting type: Integer; but found type: ".concat(r33), this.expr, this.start);
    L9:
        r33 = r32.getClass().getName();
        goto L11
    }

    @Override // org.mvel2.ast.ASTNode
    public Object getReducedValueAccelerated(Object r1, Object r2, VariableResolverFactory r3) {
        return Integer.valueOf(~((Integer) this.stmt.getValue(r1, r2, r3)).intValue());
    }
}
