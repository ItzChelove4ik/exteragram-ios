package org.mvel2.integration.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.mvel2.UnresolveablePropertyException;
import org.mvel2.ast.Proto$ProtoContextFactory$$ExternalSyntheticBUOutline0;
import org.mvel2.integration.VariableResolver;
import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.util.StaticFieldStub$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
public class CachedMapVariableResolverFactory extends BaseVariableResolverFactory {
    protected Map<String, Object> variables;

    public CachedMapVariableResolverFactory() {
    }

    public CachedMapVariableResolverFactory(Map<String, Object> r6) {
        this.variables = r6;
        this.variableResolvers = new HashMap(r6.size() * 2);
        Iterator<Map.Entry<String, Object>> r62 = r6.entrySet().iterator();
    L4:
        if (r62.hasNext() == false) goto L6;
        Map.Entry<String, Object> r0 = r62.next();
        this.variableResolvers.put(r0.getKey(), new PrecachedMapVariableResolver(r0, r0.getKey()));
        goto L4
    }

    public CachedMapVariableResolverFactory(Map<String, Object> r6, VariableResolverFactory r7) {
        this.variables = r6;
        this.variableResolvers = new HashMap(r6.size() * 2);
        Iterator<Map.Entry<String, Object>> r62 = r6.entrySet().iterator();
    L4:
        if (r62.hasNext() == false) goto L6;
        Map.Entry<String, Object> r0 = r62.next();
        this.variableResolvers.put(r0.getKey(), new PrecachedMapVariableResolver(r0, r0.getKey()));
        goto L4
    L6:
        this.nextFactory = r7;
    }

    public VariableResolver addResolver(String r2, VariableResolver r3) {
        if (this.variableResolvers != null) goto L5;
        this.variableResolvers = new HashMap();
    L5:
        this.variableResolvers.put(r2, r3);
        return r3;
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
        VariableResolverFactory r0 = this.nextFactory;
        Map<String, Object> r1 = this.variables;
        if (r0 != null) goto L9;
        if (r1 == null) goto L8;
        return new HashSet(this.variables.keySet());
    L8:
        return new HashSet(0);
    L9:
        if (r1 == null) goto L13;
        return new HashSet(this.variables.keySet());
    L13:
        return new HashSet(0);
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
        Map<String, VariableResolver> r0 = this.variableResolvers;
        if (r0 != null) goto L5;
    L6:
        Map<String, Object> r02 = this.variables;
        if (r02 != null) goto L9;
    L10:
        VariableResolverFactory r1 = this.nextFactory;
        if (r1 != null) goto L13;
        return false;
    L13:
        if (r1.isResolveable(r2) == false) goto L20;
        return true;
    L20:
        return false;
    L9:
        if (r02.containsKey(r2) == false) goto L10;
        return true;
    L5:
        if (r0.containsKey(r2) == false) goto L6;
        return true;
    }

    @Override // org.mvel2.integration.VariableResolverFactory
    public boolean isTarget(String r1) {
        Map<String, VariableResolver> r0 = this.variableResolvers;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.containsKey(r1) == false) goto L10;
        return true;
    L10:
        return false;
    }
}
