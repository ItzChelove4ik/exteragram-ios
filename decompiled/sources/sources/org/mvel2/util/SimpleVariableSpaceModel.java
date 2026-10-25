package org.mvel2.util;

import org.mvel2.integration.VariableResolver;
import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.integration.impl.IndexVariableResolver;
import org.mvel2.integration.impl.IndexedVariableResolverFactory;
import org.mvel2.integration.impl.SimpleSTValueResolver;

/* loaded from: classes5.dex */
public class SimpleVariableSpaceModel extends VariableSpaceModel {
    public SimpleVariableSpaceModel(String[] r1) {
        super(r1);
    }

    public VariableResolverFactory createFactory(Object[] r6) {
        int r0 = this.allVars.length;
        VariableResolver[] r1 = new VariableResolver[r0];
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L10;
        if (r2 < r6.length) goto L7;
        r1[r2] = new SimpleSTValueResolver(null, null);
    L8:
        r2 = r2 + 1;
        goto L3
    L7:
        r1[r2] = new IndexVariableResolver(r2, r6);
        goto L8
    L10:
        return new IndexedVariableResolverFactory(this.allVars, r1);
    }
}
