package org.mvel2.ast;

import org.mvel2.MVEL;
import org.mvel2.ParserContext;
import org.mvel2.compiler.ExecutableStatement;
import org.mvel2.integration.VariableResolver;
import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.math.MathProcessor;
import org.mvel2.util.ParseTools;

/* loaded from: classes5.dex */
public class OperativeAssign extends ASTNode {
    private int knownInType;
    private final int operation;
    private ExecutableStatement statement;
    private String varName;

    public OperativeAssign(String r2, char[] r3, int r4, int r5, int r6, int r7, ParserContext r8) {
        super(r8);
        this.knownInType = -1;
        this.varName = r2;
        this.operation = r6;
        this.expr = r3;
        this.start = r4;
        this.offset = r5;
        if ((r7 & 16) == 0) goto L11;
        ExecutableStatement r22 = (ExecutableStatement) ParseTools.subCompileExpression(r3, r4, r5, r8);
        this.statement = r22;
        this.egressType = r22.getKnownEgressType();
        if (r8.isStrongTyping() == false) goto L8;
        this.knownInType = ParseTools.__resolveType(this.egressType);
    L8:
        if (r8.hasVarOrInput(this.varName) == true) goto L12;
        r8.addInput(this.varName, this.egressType);
        return;
    L12:
        return;
    }

    @Override // org.mvel2.ast.ASTNode
    public Object getReducedValue(Object r5, Object r6, VariableResolverFactory r7) {
        VariableResolver r62 = r7.getVariableResolver(this.varName);
        Object r4 = MathProcessor.doOperations(r62.getValue(), this.operation, MVEL.eval(this.expr, this.start, this.offset, r5, r7));
        r62.setValue(r4);
        return r4;
    }

    @Override // org.mvel2.ast.ASTNode
    public Object getReducedValueAccelerated(Object r5, Object r6, VariableResolverFactory r7) {
        VariableResolver r0 = r7.getVariableResolver(this.varName);
        Object r4 = MathProcessor.doOperations(r0.getValue(), this.operation, this.knownInType, this.statement.getValue(r5, r6, r7));
        r0.setValue(r4);
        return r4;
    }
}
