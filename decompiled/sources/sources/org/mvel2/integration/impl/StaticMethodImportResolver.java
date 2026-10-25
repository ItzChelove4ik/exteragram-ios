package org.mvel2.integration.impl;

import org.mvel2.integration.VariableResolver;
import org.mvel2.util.MethodStub;

/* loaded from: classes5.dex */
public class StaticMethodImportResolver implements VariableResolver {
    private MethodStub method;
    private String name;

    public StaticMethodImportResolver(String r1, MethodStub r2) {
        this.name = r1;
        this.method = r2;
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
        return null;
    }

    @Override // org.mvel2.integration.VariableResolver
    public /* bridge */ /* synthetic */ Object getValue() {
        return getValue();
    }

    @Override // org.mvel2.integration.VariableResolver
    public MethodStub getValue() {
        return this.method;
    }

    @Override // org.mvel2.integration.VariableResolver
    public void setStaticType(Class r1) {
    }

    @Override // org.mvel2.integration.VariableResolver
    public void setValue(Object r1) {
        this.method = (MethodStub) r1;
    }
}
