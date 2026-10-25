package org.mvel2.integration;

import java.io.Serializable;
import java.util.Set;

/* loaded from: classes5.dex */
public interface VariableResolverFactory extends Serializable {
    VariableResolver createIndexedVariable(int r1, String r2, Object r3);

    VariableResolver createIndexedVariable(int r1, String r2, Object r3, Class<?> r4);

    VariableResolver createVariable(String r1, Object r2);

    VariableResolver createVariable(String r1, Object r2, Class<?> r3);

    VariableResolver getIndexedVariableResolver(int r1);

    Set<String> getKnownVariables();

    VariableResolverFactory getNextFactory();

    VariableResolver getVariableResolver(String r1);

    boolean isIndexedFactory();

    boolean isResolveable(String r1);

    boolean isTarget(String r1);

    VariableResolver setIndexedVariableResolver(int r1, VariableResolver r2);

    VariableResolverFactory setNextFactory(VariableResolverFactory r1);

    void setTiltFlag(boolean r1);

    boolean tiltFlag();

    int variableIndexOf(String r1);
}
