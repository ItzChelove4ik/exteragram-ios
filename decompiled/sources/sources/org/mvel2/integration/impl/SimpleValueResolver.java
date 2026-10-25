package org.mvel2.integration.impl;

import org.mvel2.integration.VariableResolver;

/* loaded from: classes5.dex */
public class SimpleValueResolver implements VariableResolver {
    private Object value;

    public SimpleValueResolver(Object r1) {
        this.value = r1;
    }

    @Override // org.mvel2.integration.VariableResolver
    public int getFlags() {
        return 0;
    }

    @Override // org.mvel2.integration.VariableResolver
    public String getName() {
        return null;
    }

    @Override // org.mvel2.integration.VariableResolver
    public Class getType() {
        return Object.class;
    }

    @Override // org.mvel2.integration.VariableResolver
    public Object getValue() {
        return this.value;
    }

    @Override // org.mvel2.integration.VariableResolver
    public void setStaticType(Class r1) {
    }

    @Override // org.mvel2.integration.VariableResolver
    public void setValue(Object r1) {
        this.value = r1;
    }
}
