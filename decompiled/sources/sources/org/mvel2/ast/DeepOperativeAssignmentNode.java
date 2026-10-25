package org.mvel2.ast;

import org.mvel2.MVEL;
import org.mvel2.ParserContext;
import org.mvel2.PropertyAccessor;
import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.math.MathProcessor;

/* loaded from: classes5.dex */
public class DeepOperativeAssignmentNode extends DeepAssignmentNode {
    private final int operation;

    public DeepOperativeAssignmentNode(char[] r1, int r2, int r3, int r4, int r5, String r6, ParserContext r7) {
        super(r1, r2, r3, r4, r5, r6, r7);
        this.operation = r5;
    }

    @Override // org.mvel2.ast.DeepAssignmentNode, org.mvel2.ast.ASTNode
    public Object getReducedValue(Object r5, Object r6, VariableResolverFactory r7) {
        Object r52 = MathProcessor.doOperations(PropertyAccessor.get(this.property, r5, r7, r6, this.pCtx), this.operation, MVEL.eval(this.expr, this.start, this.offset, r5, r7));
        PropertyAccessor.set(r52, r7, this.property, r52, this.pCtx);
        return r52;
    }
}
