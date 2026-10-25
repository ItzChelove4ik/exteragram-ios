package org.mvel2.optimizers.impl.refl.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.mvel2.compiler.Accessor;
import org.mvel2.integration.VariableResolverFactory;

/* loaded from: classes5.dex */
public class ListCreator implements Accessor {
    private Accessor[] values;

    public ListCreator(Accessor[] r1) {
        this.values = r1;
    }

    @Override // org.mvel2.compiler.Accessor
    public Class getKnownEgressType() {
        return List.class;
    }

    @Override // org.mvel2.compiler.Accessor
    public Object getValue(Object r4, Object r5, VariableResolverFactory r6) {
        Object[] r0 = new Object[getValues().length];
        int r1 = 0;
    L4:
        if (r1 >= getValues().length) goto L7;
        r0[r1] = getValues()[r1].getValue(r4, r5, r6);
        r1 = r1 + 1;
        goto L4
    L7:
        return new ArrayList(Arrays.asList(r0));
    }

    public Accessor[] getValues() {
        return this.values;
    }

    @Override // org.mvel2.compiler.Accessor
    public Object setValue(Object r1, Object r2, VariableResolverFactory r3, Object r4) {
        return null;
    }
}
