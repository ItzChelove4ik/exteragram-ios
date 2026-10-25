package org.mvel2.integration.impl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.mvel2.ast.Proto$ProtoContextFactory$$ExternalSyntheticBUOutline0;
import org.mvel2.integration.VariableResolver;
import org.mvel2.integration.VariableResolverFactory;

/* loaded from: classes5.dex */
public class IndexedVariableResolverFactory extends BaseVariableResolverFactory {
    public IndexedVariableResolverFactory(String[] r1, Object[] r2) {
        this.indexedVariableNames = r1;
        this.indexedVariableResolvers = createResolvers(r2, r1.length);
    }

    public IndexedVariableResolverFactory(String[] r2, Object[] r3, VariableResolverFactory r4) {
        this.indexedVariableNames = r2;
        MapVariableResolverFactory r0 = new MapVariableResolverFactory();
        this.nextFactory = r0;
        r0.setNextFactory(r4);
        this.indexedVariableResolvers = createResolvers(r3, r2.length);
    }

    public IndexedVariableResolverFactory(String[] r1, VariableResolver[] r2) {
        this.indexedVariableNames = r1;
        this.indexedVariableResolvers = r2;
    }

    private static VariableResolver[] createResolvers(Object[] r4, int r5) {
        VariableResolver[] r0 = new VariableResolver[r5];
        int r1 = 0;
    L3:
        if (r1 >= r5) goto L9;
        if (r1 < r4.length) goto L7;
        VariableResolver r2 = new SimpleValueResolver(null);
    L8:
        r0[r1] = r2;
        r1 = r1 + 1;
        goto L3
    L7:
        r2 = new IndexVariableResolver(r1, r4);
        goto L8
    L9:
        return r0;
    }

    private VariableResolver getResolver(String r4) {
        int r0 = 0;
    L3:
        String[] r1 = this.indexedVariableNames;
        if (r0 >= r1.length) goto L10;
        if (r1[r0].equals(r4) == true) goto L8;
        r0 = r0 + 1;
        goto L3
    L8:
        return this.indexedVariableResolvers[r0];
    L10:
        return null;
    }

    public VariableResolver addResolver(String r1, VariableResolver r2) {
        this.variableResolvers.put(r1, r2);
        return r2;
    }

    public void clear() {
    }

    @Override // org.mvel2.integration.impl.BaseVariableResolverFactory, org.mvel2.integration.VariableResolverFactory
    public VariableResolver createIndexedVariable(int r1, String r2, Object r3) {
        VariableResolver r0 = this.indexedVariableResolvers[r1];
        r0.setValue(r3);
        return r0;
    }

    @Override // org.mvel2.integration.VariableResolverFactory
    public VariableResolver createVariable(String r1, Object r2) {
        VariableResolver r0 = getResolver(r1);
        if (r0 == null) goto L5;
        r0.setValue(r2);
    L5:
        return r0;
    }

    @Override // org.mvel2.integration.VariableResolverFactory
    public VariableResolver createVariable(String r1, Object r2, Class<?> r3) {
        VariableResolver r0 = getResolver(r1);
        if (r0 != null) goto L5;
    L8:
        return r0;
    L5:
        if ((r0 instanceof SimpleSTValueResolver) == false) goto L7;
        ((SimpleSTValueResolver) r0).setStaticType(r3);
    L7:
        r0.setValue(r2);
        goto L8
    }

    @Override // org.mvel2.integration.impl.BaseVariableResolverFactory, org.mvel2.integration.VariableResolverFactory
    public VariableResolver getIndexedVariableResolver(int r1) {
        return this.indexedVariableResolvers[r1];
    }

    @Override // org.mvel2.integration.impl.BaseVariableResolverFactory, org.mvel2.integration.VariableResolverFactory
    public Set<String> getKnownVariables() {
        return new HashSet(Arrays.asList(this.indexedVariableNames));
    }

    @Override // org.mvel2.integration.impl.BaseVariableResolverFactory, org.mvel2.integration.VariableResolverFactory
    public VariableResolver getVariableResolver(String r2) {
        VariableResolver r0 = getResolver(r2);
        if (r0 == null) goto L5;
        return r0;
    L5:
        VariableResolverFactory r1 = this.nextFactory;
        if (r1 != null) goto L8;
        Proto$ProtoContextFactory$$ExternalSyntheticBUOutline0.m(r2);
        return null;
    L8:
        return r1.getVariableResolver(r2);
    }

    @Override // org.mvel2.integration.impl.BaseVariableResolverFactory, org.mvel2.integration.VariableResolverFactory
    public boolean isIndexedFactory() {
        return true;
    }

    @Override // org.mvel2.integration.VariableResolverFactory
    public boolean isResolveable(String r2) {
        if (isTarget(r2) == true) goto L11;
        VariableResolverFactory r1 = this.nextFactory;
        if (r1 != null) goto L7;
        return false;
    L7:
        if (r1.isResolveable(r2) == true) goto L14;
        return false;
    L14:
        return true;
    L11:
        return true;
    }

    @Override // org.mvel2.integration.VariableResolverFactory
    public boolean isTarget(String r5) {
        String[] r4 = this.indexedVariableNames;
        int r0 = r4.length;
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L9;
        if (r4[r2].equals(r5) == true) goto L6;
        r2 = r2 + 1;
        goto L3
    L6:
        return true;
    L9:
        return false;
    }
}
