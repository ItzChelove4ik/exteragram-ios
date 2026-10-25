package org.mvel2.integration.impl;

import org.mvel2.integration.VariableResolver;
import org.mvel2.integration.VariableResolverFactory;

/* loaded from: classes5.dex */
public class StackDelimiterResolverFactory extends StackDemarcResolverFactory {
    public StackDelimiterResolverFactory(VariableResolverFactory r1) {
        super(r1);
    }

    @Override // org.mvel2.integration.impl.StackDemarcResolverFactory, org.mvel2.integration.VariableResolverFactory
    public VariableResolver createVariable(String r3, Object r4) {
        VariableResolverFactory r2 = getDelegate();
        VariableResolverFactory r0 = r2.getNextFactory();
        r2.setNextFactory(null);
        VariableResolver r32 = r2.createVariable(r3, r4);
        r2.setNextFactory(r0);
        return r32;
    }
}
