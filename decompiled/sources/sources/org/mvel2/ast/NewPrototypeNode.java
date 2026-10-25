package org.mvel2.ast;

import org.mvel2.ParserContext;
import org.mvel2.integration.VariableResolverFactory;

/* loaded from: classes5.dex */
public class NewPrototypeNode extends ASTNode {
    private String protoName;

    public NewPrototypeNode(TypeDescriptor r1, ParserContext r2) {
        super(r2);
        this.protoName = r1.getClassName();
    }

    @Override // org.mvel2.ast.ASTNode
    public Object getReducedValue(Object r1, Object r2, VariableResolverFactory r3) {
        return ((Proto) r3.getVariableResolver(this.protoName).getValue()).newInstance(r1, r2, r3);
    }

    @Override // org.mvel2.ast.ASTNode
    public Object getReducedValueAccelerated(Object r1, Object r2, VariableResolverFactory r3) {
        return ((Proto) r3.getVariableResolver(this.protoName).getValue()).newInstance(r1, r2, r3);
    }
}
