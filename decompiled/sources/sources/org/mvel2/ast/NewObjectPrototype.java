package org.mvel2.ast;

import java.util.HashMap;
import org.mvel2.ParserContext;
import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.integration.impl.MapVariableResolverFactory;

/* loaded from: classes5.dex */
public class NewObjectPrototype extends ASTNode {
    private Function function;

    public NewObjectPrototype(ParserContext r1, Function r2) {
        super(r1);
        this.function = r2;
    }

    @Override // org.mvel2.ast.ASTNode
    public Object getReducedValue(Object r3, Object r4, VariableResolverFactory r5) {
        MapVariableResolverFactory r0 = new MapVariableResolverFactory(new HashMap(), r5);
        this.function.getCompiledBlock().getValue(r3, r4, r0);
        return new PrototypalFunctionInstance(this.function, r0);
    }

    @Override // org.mvel2.ast.ASTNode
    public Object getReducedValueAccelerated(Object r1, Object r2, VariableResolverFactory r3) {
        return getReducedValue(r1, r2, r3);
    }
}
