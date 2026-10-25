package org.mvel2.integration.impl;

import org.mvel2.DataConversion;
import org.mvel2.integration.VariableResolver;
import org.mvel2.util.StaticFieldStub$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
public class SimpleSTValueResolver implements VariableResolver {
    private Class type;
    private boolean updated;
    private Object value;

    public SimpleSTValueResolver(Object r2, Class r3) {
        this.updated = false;
        this.value = handleTypeCoercion(r3, r2);
        this.type = r3;
    }

    public SimpleSTValueResolver(Object r2, Class r3, boolean r4) {
        this.updated = false;
        this.value = handleTypeCoercion(r3, r2);
        this.type = r3;
        this.updated = r4;
    }

    public static Object handleTypeCoercion(Class r3, Object r4) {
        if (r3 == null) goto L14;
        if (r4 == null) goto L14;
        if (r4.getClass() == r3) goto L14;
        if (DataConversion.canConvert(r3, r4.getClass()) == true) goto L15;
        StaticFieldStub$$ExternalSyntheticBUOutline0.m("cannot assign ", r4.getClass().getName(), " to type: ", r3.getName());
        return null;
    L15:
        return DataConversion.convert(r4, r3);
    L10:
        StaticFieldStub$$ExternalSyntheticBUOutline0.m("cannot convert value of ", r4.getClass().getName(), " to: ", r3.getName());
        return null;
    L14:
        return r4;
    }

    @Override // org.mvel2.integration.VariableResolver
    public int getFlags() {
        if (this.updated == false) goto L6;
        return -1;
    L6:
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
        return this.value;
    }

    @Override // org.mvel2.integration.VariableResolver
    public void setStaticType(Class r1) {
        this.type = r1;
    }

    @Override // org.mvel2.integration.VariableResolver
    public void setValue(Object r2) {
        this.updated = true;
        this.value = handleTypeCoercion(this.type, r2);
    }
}
