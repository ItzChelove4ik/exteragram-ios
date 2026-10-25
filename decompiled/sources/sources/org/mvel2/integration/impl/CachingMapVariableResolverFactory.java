package org.mvel2.integration.impl;

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
public class CachingMapVariableResolverFactory extends BaseVariableResolverFactory {
    protected Map<String, Object> variables;

    public CachingMapVariableResolverFactory(Map r1) {
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
    public VariableResolver createVariable(String r4, Object r5) {
        VariableResolver r0 = getVariableResolver(r4);     // Catch: UnresolveablePropertyException -> L4
        r0.setValue(r5);     // Catch: UnresolveablePropertyException -> L4
        return r0;
    L4:
        SimpleSTValueResolver r02 = new SimpleSTValueResolver(r5, null, true);
        addResolver(r4, r02);
        return r02;
    }

    @Override // org.mvel2.integration.VariableResolverFactory
    public VariableResolver createVariable(String r4, Object r5, Class<?> r6) {
        VariableResolver r1 = getVariableResolver(r4);     // Catch: UnresolveablePropertyException -> L5
    L6:
        if (r1 != null) goto L8;
    L12:
        SimpleSTValueResolver r0 = new SimpleSTValueResolver(r5, r6, true);
        addResolver(r4, r0);
        return r0;
    L8:
        if (r1.getType() == null) goto L12;
        StaticFieldStub$$ExternalSyntheticBUOutline0.m("variable already defined within scope: ", r1.getType(), " ", r4);
        return null;
    L5:
        r1 = null;
        goto L6
    }

    public void externalize() {
        Iterator<Map.Entry<String, VariableResolver>> r0 = this.variableResolvers.entrySet().iterator();
    L4:
        if (r0.hasNext() == false) goto L8;
        Map.Entry<String, VariableResolver> r1 = r0.next();
        if (r1.getValue().getFlags() != (-1)) goto L4;
        this.variables.put(r1.getKey(), r1.getValue().getValue());
        goto L4
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
    public VariableResolver getVariableResolver(String r4) {
        VariableResolver r0 = this.variableResolvers.get(r4);
        if (r0 == null) goto L6;
        return r0;
    L6:
        if (this.variables.containsKey(r4) == false) goto L9;
        Map<String, VariableResolver> r02 = this.variableResolvers;
        SimpleSTValueResolver r2 = new SimpleSTValueResolver(this.variables.get(r4), null);
        r02.put(r4, r2);
        return r2;
    L9:
        VariableResolverFactory r3 = this.nextFactory;
        if (r3 != null) goto L12;
        Proto$ProtoContextFactory$$ExternalSyntheticBUOutline0.m(r4);
        return null;
    L12:
        return r3.getVariableResolver(r4);
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
