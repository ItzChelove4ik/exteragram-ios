package org.mvel2.integration.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.mvel2.UnresolveablePropertyException;
import org.mvel2.ast.Proto$ProtoContextFactory$$ExternalSyntheticBUOutline0;
import org.mvel2.integration.VariableResolver;
import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.util.StaticFieldStub$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
public class MapVariableResolverFactory extends BaseVariableResolverFactory {
    protected Map<String, Object> variables;

    public MapVariableResolverFactory() {
        this.variables = new HashMap();
    }

    public MapVariableResolverFactory(Map r1) {
        this.variables = r1;
    }

    public MapVariableResolverFactory(Map<String, Object> r1, VariableResolverFactory r2) {
        this.variables = r1;
        this.nextFactory = r2;
    }

    public MapVariableResolverFactory(Map<String, Object> r1, boolean r2) {
        this.variables = r1;
    }

    public VariableResolver addResolver(String r1, VariableResolver r2) {
        this.variableResolvers.put(r1, r2);
        return r2;
    }

    public void clear() {
        this.variableResolvers.clear();
        this.variables.clear();
    }

    @Override // org.mvel2.integration.VariableResolverFactory
    public VariableResolver createVariable(String r3, Object r4) {
        VariableResolver r0 = getVariableResolver(r3);     // Catch: UnresolveablePropertyException -> L4
        r0.setValue(r4);     // Catch: UnresolveablePropertyException -> L4
        return r0;
    L4:
        MapVariableResolver r02 = new MapVariableResolver(this.variables, r3);
        addResolver(r3, r02).setValue(r4);
        return r02;
    }

    @Override // org.mvel2.integration.VariableResolverFactory
    public VariableResolver createVariable(String r4, Object r5, Class<?> r6) {
        VariableResolver r1 = getVariableResolver(r4);     // Catch: UnresolveablePropertyException -> L5
    L6:
        if (r1 != null) goto L8;
    L12:
        MapVariableResolver r0 = new MapVariableResolver(this.variables, r4, r6);
        addResolver(r4, r0).setValue(r5);
        return r0;
    L8:
        if (r1.getType() == null) goto L12;
        StaticFieldStub$$ExternalSyntheticBUOutline0.m("variable already defined within scope: ", r1.getType(), " ", r4);
        return null;
    L5:
        r1 = null;
        goto L6
    }

    @Override // org.mvel2.integration.impl.BaseVariableResolverFactory, org.mvel2.integration.VariableResolverFactory
    public Set<String> getKnownVariables() {
        if (this.variables == null) goto L5;
        HashSet r0 = new HashSet(this.variables.keySet());
    L6:
        VariableResolverFactory r2 = this.nextFactory;
        if (r2 == null) goto L9;
        r0.addAll(r2.getKnownVariables());
    L9:
        return r0;
    L5:
        r0 = new HashSet(0);
        goto L6
    }

    @Override // org.mvel2.integration.impl.BaseVariableResolverFactory, org.mvel2.integration.VariableResolverFactory
    public VariableResolver getVariableResolver(String r3) {
        VariableResolver r0 = this.variableResolvers.get(r3);
        if (r0 == null) goto L6;
        return r0;
    L6:
        if (this.variables.containsKey(r3) == false) goto L9;
        Map<String, VariableResolver> r02 = this.variableResolvers;
        MapVariableResolver r1 = new MapVariableResolver(this.variables, r3);
        r02.put(r3, r1);
        return r1;
    L9:
        VariableResolverFactory r2 = this.nextFactory;
        if (r2 != null) goto L12;
        Proto$ProtoContextFactory$$ExternalSyntheticBUOutline0.m(r3);
        return null;
    L12:
        return r2.getVariableResolver(r3);
    }

    @Override // org.mvel2.integration.VariableResolverFactory
    public boolean isResolveable(String r2) {
        if (this.variableResolvers.containsKey(r2) == true) goto L15;
        Map<String, Object> r0 = this.variables;
        if (r0 != null) goto L7;
    L8:
        VariableResolverFactory r1 = this.nextFactory;
        if (r1 != null) goto L11;
        return false;
    L11:
        if (r1.isResolveable(r2) == true) goto L19;
        return false;
    L19:
        return true;
    L7:
        if (r0.containsKey(r2) == false) goto L8;
        return true;
    L15:
        return true;
    }

    @Override // org.mvel2.integration.VariableResolverFactory
    public boolean isTarget(String r1) {
        return this.variableResolvers.containsKey(r1);
    }
}
