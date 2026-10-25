package org.mvel2.optimizers.impl.refl.nodes;

import org.mvel2.MVEL;
import org.mvel2.compiler.AccessorNode;
import org.mvel2.integration.PropertyHandler;
import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.util.Make$Map$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
public class PropertyHandlerAccessor extends BaseAccessor {
    private Class conversionType;
    private PropertyHandler propertyHandler;
    private String propertyName;

    public PropertyHandlerAccessor(String r1, Class r2, PropertyHandler r3) {
        this.propertyName = r1;
        this.conversionType = r2;
        this.propertyHandler = r3;
    }

    @Override // org.mvel2.compiler.Accessor
    public Class getKnownEgressType() {
        return Object.class;
    }

    @Override // org.mvel2.compiler.Accessor
    public Object getValue(Object r3, Object r4, VariableResolverFactory r5) {
        boolean r0 = this.conversionType.isAssignableFrom(r3.getClass());
        AccessorNode r1 = this.nextNode;
        if (r0 == true) goto L18;
        String r2 = this.propertyName;
        if (r1 == null) goto L9;
        return r1.getValue(MVEL.getProperty(r2, r3), r4, r5);
    L9:
        return MVEL.getProperty(r2, r3);
    L18:
        if (r1 == null) goto L14;
        return r1.getValue(this.propertyHandler.getProperty(this.propertyName, r3, r5), r4, r5);
    L14:
        return this.propertyHandler.getProperty(this.propertyName, r3, r5);
    L15:
        e = move-exception;
        Make$Map$$ExternalSyntheticBUOutline0.m("unable to access field", e);
        return null;
    }

    @Override // org.mvel2.compiler.Accessor
    public Object setValue(Object r2, Object r3, VariableResolverFactory r4, Object r5) {
        AccessorNode r32 = this.nextNode;
        PropertyHandler r0 = this.propertyHandler;
        if (r32 == null) goto L7;
        return r32.setValue(r0.getProperty(this.propertyName, r2, r4), r2, r4, r5);
    L7:
        return r0.setProperty(this.propertyName, r2, r4, r5);
    }
}
