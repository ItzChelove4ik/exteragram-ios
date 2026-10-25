package org.mvel2.integration.impl;

import java.util.Map;
import org.mvel2.DataConversion;
import org.mvel2.integration.VariableResolver;
import org.mvel2.util.StaticFieldStub$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
public class PrecachedMapVariableResolver implements VariableResolver {
    private Map.Entry entry;
    private Class<?> knownType;
    private String name;

    public PrecachedMapVariableResolver(Map.Entry r1, String r2) {
        this.entry = r1;
        this.name = r2;
    }

    public PrecachedMapVariableResolver(Map.Entry r1, String r2, Class r3) {
        this.name = r2;
        this.knownType = r3;
        this.entry = r1;
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
        return this.knownType;
    }

    @Override // org.mvel2.integration.VariableResolver
    public Object getValue() {
        return this.entry.getValue();
    }

    public void setName(String r1) {
        this.name = r1;
    }

    @Override // org.mvel2.integration.VariableResolver
    public void setStaticType(Class r1) {
        this.knownType = r1;
    }

    @Override // org.mvel2.integration.VariableResolver
    public void setValue(Object r3) {
        if (this.knownType == null) goto L15;
        if (r3 == null) goto L15;
        if (r3.getClass() == this.knownType) goto L15;
        Class<?> r0 = r3.getClass();
        if (DataConversion.canConvert(this.knownType, r0) == true) goto L17;
        StaticFieldStub$$ExternalSyntheticBUOutline0.m("cannot assign ", r3.getClass().getName(), " to type: ", this.knownType.getName());
        return;
    L17:
        r3 = DataConversion.convert(r3, this.knownType);     // Catch: Exception -> L11
    L11:
        StaticFieldStub$$ExternalSyntheticBUOutline0.m("cannot convert value of ", r3.getClass().getName(), " to: ", this.knownType.getName());
        return;
    L15:
        this.entry.setValue(r3);
    }
}
