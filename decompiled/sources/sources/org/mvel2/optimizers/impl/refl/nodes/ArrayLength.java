package org.mvel2.optimizers.impl.refl.nodes;

import java.lang.reflect.Array;
import org.mvel2.compiler.AccessorNode;
import org.mvel2.integration.VariableResolverFactory;

/* loaded from: classes5.dex */
public class ArrayLength extends BaseAccessor {
    public ArrayLength() {
    }

    @Override // org.mvel2.compiler.Accessor
    public Class getKnownEgressType() {
        return Integer.class;
    }

    @Override // org.mvel2.compiler.Accessor
    public Object getValue(Object r1, Object r2, VariableResolverFactory r3) {
        AccessorNode r0 = this.nextNode;
        if (r0 == null) goto L7;
        return r0.getValue(Integer.valueOf(Array.getLength(r1)), r2, r3);
    L7:
        return Integer.valueOf(Array.getLength(r1));
    }

    @Override // org.mvel2.compiler.Accessor
    public Object setValue(Object r1, Object r2, VariableResolverFactory r3, Object r4) {
        return null;
    }
}
