package org.mvel2.ast;

import org.mvel2.MVEL;
import org.mvel2.ParserContext;
import org.mvel2.compiler.ExecutableStatement;
import org.mvel2.integration.VariableResolver;
import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.math.MathProcessor;
import org.mvel2.util.ParseTools;

/* loaded from: classes5.dex */
public class IndexedOperativeAssign extends ASTNode {
    private final int operation;
    private final int register;
    private ExecutableStatement statement;

    public IndexedOperativeAssign(char[] r1, int r2, int r3, int r4, int r5, int r6, ParserContext r7) {
        super(r7);
        this.operation = r4;
        this.expr = r1;
        this.start = r2;
        this.offset = r3;
        this.register = r5;
        if ((r6 & 16) == 0) goto L6;
        ExecutableStatement r12 = (ExecutableStatement) ParseTools.subCompileExpression(r1, r2, r3, r7);
        this.statement = r12;
        this.egressType = r12.getKnownEgressType();
        return;
    }

    @Override // org.mvel2.ast.ASTNode
    public Object getReducedValue(Object r5, Object r6, VariableResolverFactory r7) {
        VariableResolver r62 = r7.getIndexedVariableResolver(this.register);
        Object r4 = MathProcessor.doOperations(r62.getValue(), this.operation, MVEL.eval(this.expr, this.start, this.offset, r5, r7));
        r62.setValue(r4);
        return r4;
    }

    @Override // org.mvel2.ast.ASTNode
    public Object getReducedValueAccelerated(Object r4, Object r5, VariableResolverFactory r6) {
        VariableResolver r0 = r6.getIndexedVariableResolver(this.register);
        Object r3 = MathProcessor.doOperations(r0.getValue(), this.operation, this.statement.getValue(r4, r5, r6));
        r0.setValue(r3);
        return r3;
    }
}
