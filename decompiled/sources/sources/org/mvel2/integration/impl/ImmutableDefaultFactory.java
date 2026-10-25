package org.mvel2.integration.impl;

import java.util.Set;
import org.mvel2.ScriptRuntimeException;
import org.mvel2.UnresolveablePropertyException;
import org.mvel2.integration.VariableResolver;
import org.mvel2.integration.VariableResolverFactory;

/* loaded from: classes5.dex */
public class ImmutableDefaultFactory implements VariableResolverFactory {
    private boolean tiltFlag;

    public ImmutableDefaultFactory() {
    }

    private void throwError() {
        throw new ScriptRuntimeException("cannot assign variables; no variable resolver factory available.");
    }

    @Override // org.mvel2.integration.VariableResolverFactory
    public VariableResolver createIndexedVariable(int r1, String r2, Object r3) {
        throwError();
        return null;
    }

    @Override // org.mvel2.integration.VariableResolverFactory
    public VariableResolver createIndexedVariable(int r1, String r2, Object r3, Class<?> r4) {
        throwError();
        return null;
    }

    @Override // org.mvel2.integration.VariableResolverFactory
    public VariableResolver createVariable(String r1, Object r2) {
        throwError();
        return null;
    }

    @Override // org.mvel2.integration.VariableResolverFactory
    public VariableResolver createVariable(String r1, Object r2, Class<?> r3) {
        throwError();
        return null;
    }

    @Override // org.mvel2.integration.VariableResolverFactory
    public VariableResolver getIndexedVariableResolver(int r1) {
        throwError();
        return null;
    }

    @Override // org.mvel2.integration.VariableResolverFactory
    public Set<String> getKnownVariables() {
        return null;
    }

    @Override // org.mvel2.integration.VariableResolverFactory
    public VariableResolverFactory getNextFactory() {
        return null;
    }

    @Override // org.mvel2.integration.VariableResolverFactory
    public VariableResolver getVariableResolver(String r1) {
        throw new UnresolveablePropertyException(r1);
    }

    @Override // org.mvel2.integration.VariableResolverFactory
    public boolean isIndexedFactory() {
        return false;
    }

    @Override // org.mvel2.integration.VariableResolverFactory
    public boolean isResolveable(String r1) {
        return false;
    }

    @Override // org.mvel2.integration.VariableResolverFactory
    public boolean isTarget(String r1) {
        return false;
    }

    @Override // org.mvel2.integration.VariableResolverFactory
    public VariableResolver setIndexedVariableResolver(int r1, VariableResolver r2) {
        throwError();
        return null;
    }

    @Override // org.mvel2.integration.VariableResolverFactory
    public VariableResolverFactory setNextFactory(VariableResolverFactory r1) {
        throw new RuntimeException("cannot chain to this factory");
    }

    @Override // org.mvel2.integration.VariableResolverFactory
    public void setTiltFlag(boolean r1) {
        this.tiltFlag = r1;
    }

    @Override // org.mvel2.integration.VariableResolverFactory
    public boolean tiltFlag() {
        return this.tiltFlag;
    }

    @Override // org.mvel2.integration.VariableResolverFactory
    public int variableIndexOf(String r1) {
        return -1;
    }
}
