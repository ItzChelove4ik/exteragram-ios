package org.mvel2.optimizers.impl.refl.nodes;

import java.io.Serializable;
import org.mvel2.MVEL;
import org.mvel2.ParserContext;
import org.mvel2.ast.WithNode;
import org.mvel2.compiler.AccessorNode;
import org.mvel2.compiler.ExecutableStatement;
import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.util.PropertyTools;

/* loaded from: classes5.dex */
public class WithAccessor implements AccessorNode {
    protected String nestParm;
    protected ExecutableStatement nestedStatement;
    private AccessorNode nextNode;
    protected WithNode.ParmValuePair[] withExpressions;

    public static final class ExecutablePairs implements Serializable {
        private Serializable setExpression;
        private ExecutableStatement statement;

        public ExecutablePairs() {
        }

        public ExecutablePairs(String r2, ExecutableStatement r3, Class r4, ParserContext r5) {
            if (r2 != null) goto L5;
        L10:
            this.statement = r3;
            return;
        L5:
            if (r2.length() == 0) goto L10;
            if (r4 == null) goto L8;
            Class<Object> r42 = PropertyTools.getReturnType(r4, r2, r5);
        L9:
            this.setExpression = MVEL.compileSetExpression(r2, r42, r5);
            goto L10
        L8:
            r42 = Object.class;
            goto L9
        }

        public Serializable getSetExpression() {
            return this.setExpression;
        }

        public ExecutableStatement getStatement() {
            return this.statement;
        }

        public void setSetExpression(Serializable r1) {
            this.setExpression = r1;
        }

        public void setStatement(ExecutableStatement r1) {
            this.statement = r1;
        }
    }

    public WithAccessor(ParserContext r3, String r4, char[] r5, int r6, int r7, Class r8) {
        r3.setBlockSymbols(true);
        this.withExpressions = WithNode.compileWithExpressions(r5, r6, r7, r4, r8, r3);
        r3.setBlockSymbols(false);
    }

    @Override // org.mvel2.compiler.Accessor
    public Class getKnownEgressType() {
        return Object.class;
    }

    @Override // org.mvel2.compiler.AccessorNode
    public AccessorNode getNextNode() {
        return this.nextNode;
    }

    @Override // org.mvel2.compiler.Accessor
    public Object getValue(Object r2, Object r3, VariableResolverFactory r4) {
        AccessorNode r0 = this.nextNode;
        if (r0 != null) goto L7;
        return processWith(r2, r3, r4);
    L7:
        return r0.getValue(processWith(r2, r3, r4), r3, r4);
    }

    public Object processWith(Object r5, Object r6, VariableResolverFactory r7) {
        WithNode.ParmValuePair[] r4 = this.withExpressions;
        int r0 = r4.length;
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L9;
        WithNode.ParmValuePair r2 = r4[r1];
        if (r2.getSetExpression() == null) goto L7;
        MVEL.executeSetExpression(r2.getSetExpression(), r5, r7, r2.getStatement().getValue(r5, r6, r7));
    L8:
        r1 = r1 + 1;
        goto L3
    L7:
        r2.getStatement().getValue(r5, r6, r7);
        goto L8
    L9:
        return r5;
    }

    @Override // org.mvel2.compiler.AccessorNode
    public AccessorNode setNextNode(AccessorNode r1) {
        this.nextNode = r1;
        return r1;
    }

    @Override // org.mvel2.compiler.Accessor
    public Object setValue(Object r2, Object r3, VariableResolverFactory r4, Object r5) {
        return this.nextNode.setValue(processWith(r2, r3, r4), r3, r4, r5);
    }
}
