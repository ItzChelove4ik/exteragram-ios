package org.mvel2.integration.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.mvel2.ParserContext;
import org.mvel2.integration.VariableResolver;
import org.mvel2.integration.VariableResolverFactory;

/* loaded from: classes5.dex */
public class TypeInjectionResolverFactoryImpl extends MapVariableResolverFactory implements TypeInjectionResolverFactory {
    public TypeInjectionResolverFactoryImpl() {
        this.variables = new HashMap();
    }

    public TypeInjectionResolverFactoryImpl(Map<String, Object> r1) {
        this.variables = r1;
    }

    public TypeInjectionResolverFactoryImpl(Map<String, Object> r1, VariableResolverFactory r2) {
        super(r1, r2);
    }

    public TypeInjectionResolverFactoryImpl(Map<String, Object> r1, boolean r2) {
        super(r1);
    }

    public TypeInjectionResolverFactoryImpl(ParserContext r3, VariableResolverFactory r4) {
        Map<String, Object> r0 = r3.getImports();
        if (r3.hasFunction() == false) goto L5;
        r4 = new TypeInjectionResolverFactoryImpl(r3.getFunctions(), r4);
    L5:
        super(r0, r4);
    }

    @Override // org.mvel2.integration.impl.MapVariableResolverFactory, org.mvel2.integration.VariableResolverFactory
    public VariableResolver createVariable(String r3, Object r4) {
        if (this.nextFactory != null) goto L6;
        this.nextFactory = new MapVariableResolverFactory(new HashMap());
    L6:
        return this.nextFactory.createVariable(r3, r4);
    }

    @Override // org.mvel2.integration.impl.MapVariableResolverFactory, org.mvel2.integration.VariableResolverFactory
    public VariableResolver createVariable(String r3, Object r4, Class<?> r5) {
        if (this.nextFactory != null) goto L6;
        this.nextFactory = new MapVariableResolverFactory(new HashMap());
    L6:
        return this.nextFactory.createVariable(r3, r4, r5);
    }

    @Override // org.mvel2.integration.impl.MapVariableResolverFactory, org.mvel2.integration.impl.BaseVariableResolverFactory, org.mvel2.integration.VariableResolverFactory
    public Set<String> getKnownVariables() {
        VariableResolverFactory r1 = this.nextFactory;
        if (r1 != null) goto L7;
        return new HashSet(0);
    L7:
        return r1.getKnownVariables();
    }
}
