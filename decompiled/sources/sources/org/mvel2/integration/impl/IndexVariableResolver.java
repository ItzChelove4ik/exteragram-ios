package org.mvel2.integration.impl;

import org.mvel2.integration.VariableResolver;
import org.mvel2.util.ParseTools;

/* loaded from: classes5.dex */
public class IndexVariableResolver implements VariableResolver {
    private int indexPos;
    private Class type;
    private Object[] vars;

    public IndexVariableResolver(int r1, Object[] r2) {
        this.indexPos = r1;
        this.vars = r2;
        initializeType(r2[r1]);
    }

    private void initializeType(Object r2) {
        if (this.type != null) goto L11;
        if (r2 != null) goto L6;
        return;
    L6:
        if (ParseTools.isNumeric(r2) == false) goto L9;
        this.type = r2.getClass();
        return;
    L9:
        this.type = Object.class;
        return;
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
        return this.type;
    }

    @Override // org.mvel2.integration.VariableResolver
    public Object getValue() {
        return this.vars[this.indexPos];
    }

    @Override // org.mvel2.integration.VariableResolver
    public void setStaticType(Class r1) {
        this.type = r1;
    }

    @Override // org.mvel2.integration.VariableResolver
    public void setValue(Object r3) {
        initializeType(r3);
        Class r0 = this.type;
        if (r0 == Object.class) goto L8;
        if (r0 == null) goto L8;
        this.vars[this.indexPos] = SimpleSTValueResolver.handleTypeCoercion(r0, r3);
        return;
    L8:
        this.vars[this.indexPos] = r3;
    }
}
