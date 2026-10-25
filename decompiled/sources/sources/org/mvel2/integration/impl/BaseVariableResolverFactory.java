package org.mvel2.integration.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.mvel2.ast.Proto$ProtoContextFactory$$ExternalSyntheticBUOutline0;
import org.mvel2.integration.VariableResolver;
import org.mvel2.integration.VariableResolverFactory;

/* loaded from: classes5.dex */
public abstract class BaseVariableResolverFactory implements VariableResolverFactory {
    protected int indexOffset;
    protected String[] indexedVariableNames;
    protected VariableResolver[] indexedVariableResolvers;
    protected VariableResolverFactory nextFactory;
    private boolean tiltFlag;
    protected Map<String, VariableResolver> variableResolvers;

    public BaseVariableResolverFactory() {
        this.variableResolvers = new HashMap();
        this.indexOffset = 0;
    }

    public void appendFactory(VariableResolverFactory r2) {
        VariableResolverFactory r0 = this.nextFactory;
        if (r0 != null) goto L7;
        this.nextFactory = r2;
        return;
    L7:
        if (r0.getNextFactory() == null) goto L9;
        r0 = r0.getNextFactory();
        goto L7
    L9:
        r0.setNextFactory(this.nextFactory);
    }

    @Override // org.mvel2.integration.VariableResolverFactory
    public VariableResolver createIndexedVariable(int r3, String r4, Object r5) {
        VariableResolverFactory r0 = this.nextFactory;
        if (r0 == null) goto L7;
        return r0.createIndexedVariable(r3 - this.indexOffset, r4, r5);
    L7:
        throw new RuntimeException("cannot create indexed variable: " + r4 + "(" + r3 + "). operation not supported by resolver: " + getClass().getName());
    }

    @Override // org.mvel2.integration.VariableResolverFactory
    public VariableResolver createIndexedVariable(int r2, String r3, Object r4, Class<?> r5) {
        VariableResolverFactory r0 = this.nextFactory;
        if (r0 == null) goto L7;
        return r0.createIndexedVariable(r2 - this.indexOffset, r3, r4, r5);
    L7:
        throw new RuntimeException("cannot access indexed variable: " + r3 + "(" + r2 + ").  operation not supported by resolver.: " + getClass().getName());
    }

    public String[] getIndexedVariableNames() {
        return this.indexedVariableNames;
    }

    @Override // org.mvel2.integration.VariableResolverFactory
    public VariableResolver getIndexedVariableResolver(int r4) {
        VariableResolverFactory r0 = this.nextFactory;
        if (r0 == null) goto L7;
        return r0.getIndexedVariableResolver(r4 - this.indexOffset);
    L7:
        throw new RuntimeException("cannot access indexed variable: " + r4 + ".  operation not supported by resolver: " + getClass().getName());
    }

    @Override // org.mvel2.integration.VariableResolverFactory
    public Set<String> getKnownVariables() {
        if (this.nextFactory == null) goto L5;
        HashSet r0 = new HashSet(this.variableResolvers.keySet());
        r0.addAll(this.nextFactory.getKnownVariables());
        return r0;
    L5:
        return new HashSet(this.variableResolvers.keySet());
    }

    @Override // org.mvel2.integration.VariableResolverFactory
    public VariableResolverFactory getNextFactory() {
        return this.nextFactory;
    }

    @Override // org.mvel2.integration.VariableResolverFactory
    public VariableResolver getVariableResolver(String r2) {
        if (isResolveable(r2) == true) goto L5;
    L12:
        Proto$ProtoContextFactory$$ExternalSyntheticBUOutline0.m(r2);
        return null;
    L5:
        if (this.variableResolvers.containsKey(r2) == true) goto L7;
        VariableResolverFactory r1 = this.nextFactory;
        if (r1 == null) goto L12;
        return r1.getVariableResolver(r2);
    L7:
        return this.variableResolvers.get(r2);
    }

    public Map<String, VariableResolver> getVariableResolvers() {
        return this.variableResolvers;
    }

    public void insertFactory(VariableResolverFactory r2) {
        if (this.nextFactory != null) goto L6;
        this.nextFactory = r2;
        return;
    L6:
        this.nextFactory = r2;
        r2.setNextFactory(r2);
    }

    @Override // org.mvel2.integration.VariableResolverFactory
    public boolean isIndexedFactory() {
        return false;
    }

    public boolean isNextResolveable(String r1) {
        VariableResolverFactory r0 = this.nextFactory;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.isResolveable(r1) == false) goto L10;
        return true;
    L10:
        return false;
    }

    public void setIndexedVariableNames(String[] r1) {
        this.indexedVariableNames = r1;
    }

    @Override // org.mvel2.integration.VariableResolverFactory
    public VariableResolver setIndexedVariableResolver(int r2, VariableResolver r3) {
        VariableResolver[] r0 = this.indexedVariableResolvers;
        if (r0 != null) goto L6;
        VariableResolver[] r02 = new VariableResolver[this.indexedVariableNames.length];
        this.indexedVariableResolvers = r02;
        r02[r2 - this.indexOffset] = r3;
        return r3;
    L6:
        r0[r2 - this.indexOffset] = r3;
        return r3;
    }

    @Override // org.mvel2.integration.VariableResolverFactory
    public VariableResolverFactory setNextFactory(VariableResolverFactory r1) {
        if (r1 == this) goto L5;
        this.nextFactory = r1;
    L5:
        return this.nextFactory;
    }

    @Override // org.mvel2.integration.VariableResolverFactory
    public void setTiltFlag(boolean r1) {
        this.tiltFlag = r1;
        VariableResolverFactory r0 = this.nextFactory;
        if (r0 == null) goto L6;
        r0.setTiltFlag(r1);
        return;
    }

    public void setVariableResolvers(Map<String, VariableResolver> r1) {
        this.variableResolvers = r1;
    }

    @Override // org.mvel2.integration.VariableResolverFactory
    public boolean tiltFlag() {
        return this.tiltFlag;
    }

    @Override // org.mvel2.integration.VariableResolverFactory
    public int variableIndexOf(String r4) {
        if (this.indexedVariableNames == null) goto L11;
        int r0 = 0;
    L5:
        String[] r1 = this.indexedVariableNames;
        if (r0 >= r1.length) goto L15;
        if (r4.equals(r1[r0]) == true) goto L9;
        r0 = r0 + 1;
        goto L5
    L9:
        return r0;
    L15:
        return -1;
    L11:
        return -1;
    }
}
