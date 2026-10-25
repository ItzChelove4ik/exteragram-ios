package org.mvel2.integration.impl;

import java.util.HashMap;
import java.util.Map;
import org.mvel2.ast.Function;
import org.mvel2.integration.VariableResolver;
import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.util.StaticFieldStub$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
public class FunctionVariableResolverFactory extends BaseVariableResolverFactory implements LocalVariableResolverFactory {
    private Function function;
    private boolean noTilt;

    public FunctionVariableResolverFactory(Function r2, VariableResolverFactory r3, String[] r4, Object[] r5) {
        int r0 = 0;
        this.noTilt = false;
        this.function = r2;
        this.variableResolvers = new HashMap();
        this.nextFactory = r3;
        this.indexedVariableNames = r4;
        this.indexedVariableResolvers = new VariableResolver[r4.length];
    L4:
        if (r0 >= r5.length) goto L6;
        this.variableResolvers.put(this.indexedVariableNames[r0], null);
        this.indexedVariableResolvers[r0] = new SimpleValueResolver(r5[r0]);
        r0 = r0 + 1;
        goto L4
    }

    private int increaseRegisterTableSize() {
        String[] r0 = this.indexedVariableNames;
        VariableResolver[] r1 = this.indexedVariableResolvers;
        int r2 = r0.length;
        int r3 = r2 + 1;
        this.indexedVariableNames = new String[r3];
        this.indexedVariableResolvers = new VariableResolver[r3];
        int r32 = 0;
    L4:
        if (r32 >= r0.length) goto L6;
        this.indexedVariableNames[r32] = r0[r32];
        this.indexedVariableResolvers[r32] = r1[r32];
        r32 = r32 + 1;
        goto L4
    L6:
        return r2;
    }

    @Override // org.mvel2.integration.impl.BaseVariableResolverFactory, org.mvel2.integration.VariableResolverFactory
    public VariableResolver createIndexedVariable(int r3, String r4, Object r5) {
        int r32 = r3 - this.indexOffset;
        VariableResolver[] r0 = this.indexedVariableResolvers;
        VariableResolver r1 = r0[r32];
        if (r1 == null) goto L5;
        r1.setValue(r5);
    L6:
        this.variableResolvers.put(r4, null);
        return this.indexedVariableResolvers[r32];
    L5:
        r0[r32] = new SimpleValueResolver(r5);
        goto L6
    }

    @Override // org.mvel2.integration.impl.BaseVariableResolverFactory, org.mvel2.integration.VariableResolverFactory
    public VariableResolver createIndexedVariable(int r1, String r2, Object r3, Class<?> r4) {
        int r12 = r1 - this.indexOffset;
        VariableResolver[] r22 = this.indexedVariableResolvers;
        VariableResolver r42 = r22[r12];
        if (r42 == null) goto L5;
        r42.setValue(r3);
    L7:
        return this.indexedVariableResolvers[r12];
    L5:
        r22[r12] = new SimpleValueResolver(r3);
        goto L7
    }

    @Override // org.mvel2.integration.VariableResolverFactory
    public VariableResolver createVariable(String r4, Object r5) {
        VariableResolver r0 = getVariableResolver(r4);
        if (r0 != null) goto L6;
        int r02 = increaseRegisterTableSize();
        this.indexedVariableNames[r02] = r4;
        this.indexedVariableResolvers[r02] = new SimpleValueResolver(r5);
        this.variableResolvers.put(r4, null);
        return this.indexedVariableResolvers[r02];
    L6:
        r0.setValue(r5);
        return r0;
    }

    @Override // org.mvel2.integration.VariableResolverFactory
    public VariableResolver createVariable(String r3, Object r4, Class<?> r5) {
        Map<String, VariableResolver> r52 = this.variableResolvers;
        if (r52 == null) goto L5;
        VariableResolver r53 = r52.get(r3);
    L6:
        if (r53 == null) goto L13;
        if (r53.getType() == null) goto L13;
        StaticFieldStub$$ExternalSyntheticBUOutline0.m("variable already defined within scope: ", r53.getType(), " ", r3);
        return null;
    L13:
        return createIndexedVariable(variableIndexOf(r3), r3, r4);
    L5:
        r53 = null;
        goto L6
    }

    public Function getFunction() {
        return this.function;
    }

    @Override // org.mvel2.integration.impl.BaseVariableResolverFactory, org.mvel2.integration.VariableResolverFactory
    public VariableResolver getIndexedVariableResolver(int r3) {
        VariableResolver[] r0 = this.indexedVariableResolvers;
        VariableResolver r1 = r0[r3];
        if (r1 != null) goto L6;
        VariableResolver r2 = super.getVariableResolver(this.indexedVariableNames[r3]);
        r0[r3] = r2;
        return r2;
    L6:
        return r1;
    }

    public VariableResolver[] getIndexedVariableResolvers() {
        return this.indexedVariableResolvers;
    }

    @Override // org.mvel2.integration.impl.BaseVariableResolverFactory, org.mvel2.integration.VariableResolverFactory
    public VariableResolver getVariableResolver(String r4) {
        int r0 = variableIndexOf(r4);
        if (r0 == (-1)) goto L10;
        VariableResolver[] r42 = this.indexedVariableResolvers;
        if (r42[r0] != null) goto L7;
        r42[r0] = new SimpleValueResolver(null);
    L7:
        this.variableResolvers.put(this.indexedVariableNames[r0], null);
        return this.indexedVariableResolvers[r0];
    L10:
        return super.getVariableResolver(r4);
    }

    @Override // org.mvel2.integration.impl.BaseVariableResolverFactory, org.mvel2.integration.VariableResolverFactory
    public boolean isIndexedFactory() {
        return true;
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
    public boolean isTarget(String r2) {
        if (this.variableResolvers.containsKey(r2) == false) goto L5;
        return true;
    L5:
        if (variableIndexOf(r2) != (-1)) goto L11;
        return false;
    L11:
        return true;
    }

    public void setIndexOffset(int r1) {
        this.indexOffset = r1;
    }

    public void setIndexedVariableResolvers(VariableResolver[] r1) {
        this.indexedVariableResolvers = r1;
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

    public void updateParameters(Object[] r5) {
        int r0 = 0;
    L4:
        if (r0 >= r5.length) goto L6;
        this.indexedVariableResolvers[r0] = new SimpleValueResolver(r5[r0]);
        r0 = r0 + 1;
        goto L4
    }
}
