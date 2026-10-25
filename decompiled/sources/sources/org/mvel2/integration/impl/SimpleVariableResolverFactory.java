package org.mvel2.integration.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.mvel2.integration.VariableResolver;
import org.mvel2.integration.VariableResolverFactory;

/* loaded from: classes5.dex */
public class SimpleVariableResolverFactory extends BaseVariableResolverFactory {
    public SimpleVariableResolverFactory(Map<String, Object> r3) {
        Iterator<Map.Entry<String, Object>> r32 = r3.entrySet().iterator();
    L4:
        if (r32.hasNext() == false) goto L6;
        Map.Entry<String, Object> r0 = r32.next();
        createVariable(r0.getKey(), r0.getValue());
        goto L4
    }

    @Override // org.mvel2.integration.impl.BaseVariableResolverFactory, org.mvel2.integration.VariableResolverFactory
    public VariableResolver createIndexedVariable(int r1, String r2, Object r3) {
        return null;
    }

    @Override // org.mvel2.integration.impl.BaseVariableResolverFactory, org.mvel2.integration.VariableResolverFactory
    public VariableResolver createIndexedVariable(int r1, String r2, Object r3, Class<?> r4) {
        return null;
    }

    @Override // org.mvel2.integration.VariableResolverFactory
    public VariableResolver createVariable(String r4, Object r5) {
        if (this.variableResolvers != null) goto L5;
        this.variableResolvers = new HashMap(5, 0.6f);
    L5:
        SimpleValueResolver r0 = new SimpleValueResolver(r5);
        this.variableResolvers.put(r4, r0);
        return r0;
    }

    @Override // org.mvel2.integration.VariableResolverFactory
    public VariableResolver createVariable(String r4, Object r5, Class<?> r6) {
        if (this.variableResolvers != null) goto L5;
        this.variableResolvers = new HashMap(5, 0.6f);
    L5:
        SimpleSTValueResolver r0 = new SimpleSTValueResolver(r5, r6);
        this.variableResolvers.put(r4, r0);
        return r0;
    }

    @Override // org.mvel2.integration.impl.BaseVariableResolverFactory, org.mvel2.integration.VariableResolverFactory
    public Set<String> getKnownVariables() {
        return this.variableResolvers.keySet();
    }

    @Override // org.mvel2.integration.impl.BaseVariableResolverFactory, org.mvel2.integration.VariableResolverFactory
    public VariableResolver getVariableResolver(String r2) {
        VariableResolver r0 = this.variableResolvers.get(r2);
        if (r0 == null) goto L5;
        return r0;
    L5:
        VariableResolverFactory r1 = this.nextFactory;
        if (r1 != null) goto L10;
        return null;
    L10:
        return r1.getVariableResolver(r2);
    }

    @Override // org.mvel2.integration.impl.BaseVariableResolverFactory, org.mvel2.integration.VariableResolverFactory
    public boolean isIndexedFactory() {
        return false;
    }

    @Override // org.mvel2.integration.VariableResolverFactory
    public boolean isResolveable(String r2) {
        if (this.variableResolvers.containsKey(r2) == true) goto L11;
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
    public boolean isTarget(String r1) {
        return this.variableResolvers.containsKey(r1);
    }

    @Override // org.mvel2.integration.impl.BaseVariableResolverFactory, org.mvel2.integration.VariableResolverFactory
    public VariableResolver setIndexedVariableResolver(int r1, VariableResolver r2) {
        return null;
    }

    @Override // org.mvel2.integration.impl.BaseVariableResolverFactory, org.mvel2.integration.VariableResolverFactory
    public int variableIndexOf(String r1) {
        return 0;
    }
}
