package org.mvel2.integration.impl;

import java.util.Set;
import org.mvel2.integration.VariableResolver;
import org.mvel2.integration.VariableResolverFactory;

/* loaded from: classes5.dex */
public class StackResetResolverFactory implements VariableResolverFactory {
    private VariableResolverFactory delegate;

    public StackResetResolverFactory(VariableResolverFactory r2) {
        r2.setTiltFlag(false);
        this.delegate = r2;
    }

    @Override // org.mvel2.integration.VariableResolverFactory
    public VariableResolver createIndexedVariable(int r1, String r2, Object r3) {
        return this.delegate.createIndexedVariable(r1, r2, r3);
    }

    @Override // org.mvel2.integration.VariableResolverFactory
    public VariableResolver createIndexedVariable(int r1, String r2, Object r3, Class<?> r4) {
        return this.delegate.createIndexedVariable(r1, r2, r3, r4);
    }

    @Override // org.mvel2.integration.VariableResolverFactory
    public VariableResolver createVariable(String r1, Object r2) {
        return this.delegate.createVariable(r1, r2);
    }

    @Override // org.mvel2.integration.VariableResolverFactory
    public VariableResolver createVariable(String r1, Object r2, Class<?> r3) {
        return this.delegate.createVariable(r1, r2, r3);
    }

    public VariableResolverFactory getDelegate() {
        return this.delegate;
    }

    @Override // org.mvel2.integration.VariableResolverFactory
    public VariableResolver getIndexedVariableResolver(int r1) {
        return this.delegate.getIndexedVariableResolver(r1);
    }

    @Override // org.mvel2.integration.VariableResolverFactory
    public Set<String> getKnownVariables() {
        return this.delegate.getKnownVariables();
    }

    @Override // org.mvel2.integration.VariableResolverFactory
    public VariableResolverFactory getNextFactory() {
        return this.delegate.getNextFactory();
    }

    @Override // org.mvel2.integration.VariableResolverFactory
    public VariableResolver getVariableResolver(String r1) {
        return this.delegate.getVariableResolver(r1);
    }

    @Override // org.mvel2.integration.VariableResolverFactory
    public boolean isIndexedFactory() {
        return this.delegate.isIndexedFactory();
    }

    @Override // org.mvel2.integration.VariableResolverFactory
    public boolean isResolveable(String r1) {
        return this.delegate.isResolveable(r1);
    }

    @Override // org.mvel2.integration.VariableResolverFactory
    public boolean isTarget(String r1) {
        return this.delegate.isTarget(r1);
    }

    public void setDelegate(VariableResolverFactory r1) {
        if (this == r1) goto L5;
        this.delegate = r1;
        return;
    }

    @Override // org.mvel2.integration.VariableResolverFactory
    public VariableResolver setIndexedVariableResolver(int r1, VariableResolver r2) {
        return this.delegate.setIndexedVariableResolver(r1, r2);
    }

    @Override // org.mvel2.integration.VariableResolverFactory
    public VariableResolverFactory setNextFactory(VariableResolverFactory r1) {
        return this.delegate.setNextFactory(r1);
    }

    @Override // org.mvel2.integration.VariableResolverFactory
    public void setTiltFlag(boolean r2) {
        if (this.delegate.tiltFlag() == true) goto L6;
        this.delegate.setTiltFlag(r2);
        return;
    }

    @Override // org.mvel2.integration.VariableResolverFactory
    public boolean tiltFlag() {
        return this.delegate.tiltFlag();
    }

    @Override // org.mvel2.integration.VariableResolverFactory
    public int variableIndexOf(String r1) {
        return this.delegate.variableIndexOf(r1);
    }
}
