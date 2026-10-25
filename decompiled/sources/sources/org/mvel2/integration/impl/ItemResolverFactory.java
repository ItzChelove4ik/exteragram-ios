package org.mvel2.integration.impl;

import org.mvel2.MVEL$$ExternalSyntheticBUOutline0;
import org.mvel2.integration.VariableResolver;
import org.mvel2.integration.VariableResolverFactory;

/* loaded from: classes5.dex */
public class ItemResolverFactory extends BaseVariableResolverFactory {
    private final ItemResolver resolver;

    public static class ItemResolver implements VariableResolver {
        private final String name;
        private Class type;
        public Object value;

        public ItemResolver(String r2) {
            this.type = Object.class;
            this.name = r2;
        }

        public ItemResolver(String r1, Class r2) {
            this.name = r1;
            this.type = r2;
        }

        @Override // org.mvel2.integration.VariableResolver
        public int getFlags() {
            return 0;
        }

        @Override // org.mvel2.integration.VariableResolver
        public String getName() {
            return this.name;
        }

        @Override // org.mvel2.integration.VariableResolver
        public Class getType() {
            return this.type;
        }

        @Override // org.mvel2.integration.VariableResolver
        public Object getValue() {
            return this.value;
        }

        @Override // org.mvel2.integration.VariableResolver
        public void setStaticType(Class r1) {
            this.type = r1;
        }

        @Override // org.mvel2.integration.VariableResolver
        public void setValue(Object r1) {
            this.value = r1;
        }
    }

    public ItemResolverFactory(ItemResolver r1, VariableResolverFactory r2) {
        this.resolver = r1;
        this.nextFactory = r2;
    }

    @Override // org.mvel2.integration.VariableResolverFactory
    public VariableResolver createVariable(String r2, Object r3) {
        if (isTarget(r2) == false) goto L7;
        this.resolver.setValue(r3);
        return this.resolver;
    L7:
        return this.nextFactory.createVariable(r2, r3);
    }

    @Override // org.mvel2.integration.VariableResolverFactory
    public VariableResolver createVariable(String r1, Object r2, Class<?> r3) {
        if (isTarget(r1) == false) goto L5;
        MVEL$$ExternalSyntheticBUOutline0.m("variable already defined in scope: ", r1);
        return null;
    L5:
        return this.nextFactory.createVariable(r1, r2);
    }

    @Override // org.mvel2.integration.impl.BaseVariableResolverFactory, org.mvel2.integration.VariableResolverFactory
    public VariableResolver getVariableResolver(String r2) {
        if (isTarget(r2) == false) goto L7;
        return this.resolver;
    L7:
        return this.nextFactory.getVariableResolver(r2);
    }

    @Override // org.mvel2.integration.VariableResolverFactory
    public boolean isResolveable(String r2) {
        if (this.resolver.getName().equals(r2) == true) goto L11;
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
    public boolean isTarget(String r1) {
        return this.resolver.getName().equals(r1);
    }
}
