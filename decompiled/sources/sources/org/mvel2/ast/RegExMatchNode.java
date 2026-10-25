package org.mvel2.ast;

import java.util.regex.Pattern;
import org.mvel2.MVEL;
import org.mvel2.ParserContext;
import org.mvel2.integration.VariableResolverFactory;

/* loaded from: classes5.dex */
public class RegExMatchNode extends ASTNode {
    private ASTNode node;
    private ASTNode patternNode;

    public RegExMatchNode(ASTNode r1, ASTNode r2, ParserContext r3) {
        super(r3);
        this.node = r1;
        this.patternNode = r2;
    }

    @Override // org.mvel2.ast.ASTNode
    public Class getEgressType() {
        return Boolean.class;
    }

    @Override // org.mvel2.ast.ASTNode
    public Object getReducedValue(Object r3, Object r4, VariableResolverFactory r5) {
        char[] r42 = this.expr;
        ASTNode r0 = this.patternNode;
        Pattern r43 = Pattern.compile(String.valueOf(MVEL.eval(r42, r0.start, r0.offset, r3, r5)));
        char[] r02 = this.expr;
        ASTNode r2 = this.node;
        return Boolean.valueOf(r43.matcher(String.valueOf(MVEL.eval(r02, r2.start, r2.offset, r3, r5))).matches());
    }

    @Override // org.mvel2.ast.ASTNode
    public Object getReducedValueAccelerated(Object r2, Object r3, VariableResolverFactory r4) {
        return Boolean.valueOf(Pattern.compile(String.valueOf(this.patternNode.getReducedValueAccelerated(r2, r3, r4))).matcher(String.valueOf(this.node.getReducedValueAccelerated(r2, r3, r4))).matches());
    }
}
