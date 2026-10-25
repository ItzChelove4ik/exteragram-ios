package org.mvel2.integration.impl;

import java.util.HashMap;
import java.util.Map;
import org.mvel2.UnresolveablePropertyException;
import org.mvel2.integration.VariableResolver;
import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.util.StaticFieldStub$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
public class DefaultLocalVariableResolverFactory extends MapVariableResolverFactory implements LocalVariableResolverFactory {
    private boolean noTilt;

    public DefaultLocalVariableResolverFactory() {
        super(new HashMap());
        this.noTilt = false;
    }

    public DefaultLocalVariableResolverFactory(Map<String, Object> r1) {
        super(r1);
        this.noTilt = false;
    }

    public DefaultLocalVariableResolverFactory(Map<String, Object> r1, VariableResolverFactory r2) {
        super(r1, r2);
        this.noTilt = false;
    }

    public DefaultLocalVariableResolverFactory(Map<String, Object> r1, boolean r2) {
        super(r1);
        this.noTilt = false;
    }

    public DefaultLocalVariableResolverFactory(VariableResolverFactory r2) {
        super(new HashMap(), r2);
        this.noTilt = false;
    }

    public DefaultLocalVariableResolverFactory(VariableResolverFactory r2, String[] r3) {
        super(new HashMap(), r2);
        this.noTilt = false;
        this.indexedVariableNames = r3;
        this.indexedVariableResolvers = new VariableResolver[r3.length];
    }

    @Override // org.mvel2.integration.impl.MapVariableResolverFactory, org.mvel2.integration.VariableResolverFactory
    public VariableResolver createVariable(String r6, Object r7, Class<?> r8) {
        if (this.indexedVariableNames != null) goto L25;
        return super.createVariable(r6, r7, r8);
    L25:
        int r1 = variableIndexOf(r6);     // Catch: UnresolveablePropertyException -> L15
        if (r1 == (-1)) goto L14;
        SimpleValueResolver r2 = new SimpleValueResolver(r7);     // Catch: UnresolveablePropertyException -> L15
        VariableResolver[] r3 = this.indexedVariableResolvers;     // Catch: UnresolveablePropertyException -> L15
        if (r3[r1] != null) goto L12;
        r3[r1] = r2;     // Catch: UnresolveablePropertyException -> L15
    L12:
        this.variableResolvers.put(this.indexedVariableNames[r1], r2);     // Catch: UnresolveablePropertyException -> L15
        VariableResolver r12 = this.indexedVariableResolvers[r1];     // Catch: UnresolveablePropertyException -> L15
        boolean r22 = true;
    L16:
        if (r22 == true) goto L23;
        if (r12 == null) goto L23;
        if (r12.getType() == null) goto L23;
        StaticFieldStub$$ExternalSyntheticBUOutline0.m("variable already defined within scope: ", r12.getType(), " ", r6);
        return null;
    L23:
        MapVariableResolver r0 = new MapVariableResolver(this.variables, r6, r8);
        addResolver(r6, r0).setValue(r7);
        return r0;
    L14:
        return super.createVariable(r6, r7, r8);
    L15:
        r22 = false;
        r12 = null;
        goto L16
    }

    @Override // org.mvel2.integration.impl.BaseVariableResolverFactory, org.mvel2.integration.VariableResolverFactory
    public VariableResolver getIndexedVariableResolver(int r4) {
        String[] r0 = this.indexedVariableNames;
        if (r0 != null) goto L6;
        return null;
    L6:
        VariableResolver[] r1 = this.indexedVariableResolvers;
        VariableResolver r2 = r1[r4];
        if (r2 != null) goto L10;
        VariableResolver r3 = super.getVariableResolver(r0[r4]);
        r1[r4] = r3;
        return r3;
    L10:
        return r2;
    }

    @Override // org.mvel2.integration.impl.MapVariableResolverFactory, org.mvel2.integration.impl.BaseVariableResolverFactory, org.mvel2.integration.VariableResolverFactory
    public VariableResolver getVariableResolver(String r4) {
        if (this.indexedVariableNames == null) goto L5;
        int r0 = variableIndexOf(r4);
        if (r0 == (-1)) goto L14;
        VariableResolver[] r42 = this.indexedVariableResolvers;
        if (r42[r0] != null) goto L11;
        r42[r0] = new SimpleValueResolver(null);
    L11:
        this.variableResolvers.put(this.indexedVariableNames[r0], null);
        return this.indexedVariableResolvers[r0];
    L14:
        return super.getVariableResolver(r4);
    L5:
        return super.getVariableResolver(r4);
    }

    public VariableResolverFactory setNoTilt(boolean r1) {
        this.noTilt = r1;
        return this;
    }

    @Override // org.mvel2.integration.impl.BaseVariableResolverFactory, org.mvel2.integration.VariableResolverFactory
    public void setTiltFlag(boolean r2) {
        if (this.noTilt == true) goto L6;
        super.setTiltFlag(r2);
        return;
    }
}
