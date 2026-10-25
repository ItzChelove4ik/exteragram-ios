package org.mvel2.ast;

import org.mvel2.integration.VariableResolver;
import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.integration.impl.MapVariableResolverFactory;

/* loaded from: classes5.dex */
public class InvokationContextFactory extends MapVariableResolverFactory {
    private VariableResolverFactory protoContext;

    public InvokationContextFactory(VariableResolverFactory r1, VariableResolverFactory r2) {
        this.nextFactory = r1;
        this.protoContext = r2;
    }

    @Override // org.mvel2.integration.impl.MapVariableResolverFactory, org.mvel2.integration.VariableResolverFactory
    public VariableResolver createVariable(String r2, Object r3) {
        if (isResolveable(r2) == false) goto L9;
        if (this.protoContext.isResolveable(r2) == true) goto L9;
        return this.nextFactory.createVariable(r2, r3);
    L9:
        return this.protoContext.createVariable(r2, r3);
    }

    @Override // org.mvel2.integration.impl.MapVariableResolverFactory, org.mvel2.integration.VariableResolverFactory
    public VariableResolver createVariable(String r2, Object r3, Class<?> r4) {
        if (isResolveable(r2) == false) goto L9;
        if (this.protoContext.isResolveable(r2) == true) goto L9;
        return this.nextFactory.createVariable(r2, r3, r4);
    L9:
        return this.protoContext.createVariable(r2, r3, r4);
    }

    @Override // org.mvel2.integration.impl.MapVariableResolverFactory, org.mvel2.integration.impl.BaseVariableResolverFactory, org.mvel2.integration.VariableResolverFactory
    public VariableResolver getVariableResolver(String r2) {
        if (isResolveable(r2) == false) goto L9;
        if (this.protoContext.isResolveable(r2) == true) goto L9;
        return this.nextFactory.getVariableResolver(r2);
    L9:
        return this.protoContext.getVariableResolver(r2);
    }

    @Override // org.mvel2.integration.impl.BaseVariableResolverFactory, org.mvel2.integration.VariableResolverFactory
    public boolean isIndexedFactory() {
        return true;
    }

    @Override // org.mvel2.integration.impl.MapVariableResolverFactory, org.mvel2.integration.VariableResolverFactory
    public boolean isResolveable(String r2) {
        if (this.protoContext.isResolveable(r2) == false) goto L5;
        return true;
    L5:
        if (this.nextFactory.isResolveable(r2) == true) goto L11;
        return false;
    L11:
        return true;
    }

    @Override // org.mvel2.integration.impl.MapVariableResolverFactory, org.mvel2.integration.VariableResolverFactory
    public boolean isTarget(String r1) {
        return this.protoContext.isTarget(r1);
    }
}
