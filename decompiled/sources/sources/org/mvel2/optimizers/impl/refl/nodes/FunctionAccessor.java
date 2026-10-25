package org.mvel2.optimizers.impl.refl.nodes;

import org.mvel2.ast.FunctionInstance;
import org.mvel2.compiler.Accessor;
import org.mvel2.compiler.AccessorNode;
import org.mvel2.integration.VariableResolverFactory;

/* loaded from: classes5.dex */
public class FunctionAccessor extends BaseAccessor {
    private FunctionInstance function;
    private Accessor[] parameters;

    public FunctionAccessor(FunctionInstance r1, Accessor[] r2) {
        this.function = r1;
        this.parameters = r2;
    }

    @Override // org.mvel2.compiler.Accessor
    public Class getKnownEgressType() {
        return Object.class;
    }

    @Override // org.mvel2.compiler.Accessor
    public Object getValue(Object r5, Object r6, VariableResolverFactory r7) {
        Accessor[] r0 = this.parameters;
        if (r0 != null) goto L5;
    L9:
        Object[] r1 = null;
    L10:
        AccessorNode r02 = this.nextNode;
        FunctionInstance r4 = this.function;
        if (r02 == null) goto L15;
        return r02.getValue(r4.call(r5, r6, r7, r1), r6, r7);
    L15:
        return r4.call(r5, r6, r7, r1);
    L5:
        if (r0.length == 0) goto L9;
        int r03 = r0.length;
        r1 = new Object[r03];
        int r2 = 0;
    L7:
        if (r2 >= r03) goto L10;
        r1[r2] = this.parameters[r2].getValue(r5, r6, r7);
        r2 = r2 + 1;
        goto L7
    }

    @Override // org.mvel2.compiler.Accessor
    public Object setValue(Object r1, Object r2, VariableResolverFactory r3, Object r4) {
        throw new RuntimeException("can't write to function");
    }
}
