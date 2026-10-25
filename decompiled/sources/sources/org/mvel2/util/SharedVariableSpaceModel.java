package org.mvel2.util;

import org.mvel2.integration.VariableResolver;
import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.integration.impl.IndexVariableResolver;
import org.mvel2.integration.impl.IndexedVariableResolverFactory;
import org.mvel2.integration.impl.SimpleValueResolver;

/* loaded from: classes5.dex */
public class SharedVariableSpaceModel extends VariableSpaceModel {
    private VariableResolver[] cachedGlobalResolvers;

    public SharedVariableSpaceModel(String[] r3, Object[] r4) {
        super(r3);
        this.cachedGlobalResolvers = new VariableResolver[r4.length];
        int r32 = 0;
    L4:
        if (r32 >= r4.length) goto L6;
        this.cachedGlobalResolvers[r32] = new IndexVariableResolver(r32, r4);
        r32 = r32 + 1;
        goto L4
    }

    public VariableResolverFactory createFactory() {
        int r0 = this.allVars.length;
        VariableResolver[] r1 = new VariableResolver[r0];
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L10;
        VariableResolver[] r3 = this.cachedGlobalResolvers;
        if (r2 < r3.length) goto L7;
        r1[r2] = new SimpleValueResolver(null);
    L8:
        r2 = r2 + 1;
        goto L3
    L7:
        r1[r2] = r3[r2];
        goto L8
    L10:
        return new IndexedVariableResolverFactory(this.allVars, r1);
    }
}
