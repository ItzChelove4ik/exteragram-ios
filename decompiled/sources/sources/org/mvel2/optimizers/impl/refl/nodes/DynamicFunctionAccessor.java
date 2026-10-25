package org.mvel2.optimizers.impl.refl.nodes;

import org.mvel2.ast.Function;
import org.mvel2.ast.FunctionInstance;
import org.mvel2.compiler.Accessor;
import org.mvel2.compiler.AccessorNode;
import org.mvel2.integration.VariableResolverFactory;

/* loaded from: classes5.dex */
public class DynamicFunctionAccessor extends BaseAccessor {
    private Accessor[] parameters;

    public DynamicFunctionAccessor(Accessor[] r1) {
        this.parameters = r1;
    }

    @Override // org.mvel2.compiler.Accessor
    public Class getKnownEgressType() {
        return Object.class;
    }

    @Override // org.mvel2.compiler.Accessor
    public Object getValue(Object r6, Object r7, VariableResolverFactory r8) {
        if ((r6 instanceof FunctionInstance) == false) goto L5;
        Function r0 = ((FunctionInstance) r6).getFunction();
    L6:
        Accessor[] r1 = this.parameters;
        if (r1 != null) goto L9;
    L13:
        Object[] r2 = null;
    L14:
        AccessorNode r5 = this.nextNode;
        if (r5 == null) goto L19;
        return r5.getValue(r0.call(r6, r7, r8, r2), r7, r8);
    L19:
        return r0.call(r6, r7, r8, r2);
    L9:
        if (r1.length == 0) goto L13;
        int r12 = r1.length;
        r2 = new Object[r12];
        int r3 = 0;
    L11:
        if (r3 >= r12) goto L14;
        r2[r3] = this.parameters[r3].getValue(r6, r7, r8);
        r3 = r3 + 1;
        goto L11
    L5:
        r0 = (Function) r6;
        goto L6
    }

    @Override // org.mvel2.compiler.Accessor
    public Object setValue(Object r1, Object r2, VariableResolverFactory r3, Object r4) {
        throw new RuntimeException("can't write to function");
    }
}
