package org.mvel2.optimizers.impl.refl.nodes;

import java.lang.reflect.Field;
import org.mvel2.DataConversion;
import org.mvel2.compiler.AccessorNode;
import org.mvel2.integration.PropertyHandler;
import org.mvel2.integration.VariableResolverFactory;
import org.mvel2.util.Make$Map$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
public class FieldAccessorNH implements AccessorNode {
    private boolean coercionRequired;
    private Field field;
    private AccessorNode nextNode;
    private PropertyHandler nullHandler;

    public FieldAccessorNH(Field r2, PropertyHandler r3) {
        this.coercionRequired = false;
        this.field = r2;
        this.nullHandler = r3;
    }

    public Field getField() {
        return this.field;
    }

    @Override // org.mvel2.compiler.Accessor
    public Class getKnownEgressType() {
        return this.field.getClass();
    }

    @Override // org.mvel2.compiler.AccessorNode
    public AccessorNode getNextNode() {
        return this.nextNode;
    }

    @Override // org.mvel2.compiler.Accessor
    public Object getValue(Object r2, Object r3, VariableResolverFactory r4) {
        Object r22 = this.field.get(r2);     // Catch: Exception -> L10
        if (r22 != null) goto L5;
        r22 = this.nullHandler.getProperty(this.field.getName(), r3, r4);     // Catch: Exception -> L10
    L5:
        AccessorNode r1 = this.nextNode;     // Catch: Exception -> L10
        if (r1 == null) goto L9;
        return r1.getValue(r22, r3, r4);
    L9:
        return r22;
    L10:
        e = move-exception;
        Make$Map$$ExternalSyntheticBUOutline0.m("unable to access field", e);
        return null;
    }

    public void setField(Field r1) {
        this.field = r1;
    }

    @Override // org.mvel2.compiler.AccessorNode
    public AccessorNode setNextNode(AccessorNode r1) {
        this.nextNode = r1;
        return r1;
    }

    @Override // org.mvel2.compiler.Accessor
    public Object setValue(Object r5, Object r6, VariableResolverFactory r7, Object r8) {
        boolean r0 = this.coercionRequired;
        AccessorNode r2 = this.nextNode;     // Catch: Exception -> L7 IllegalArgumentException -> L9
        if (r2 != null) goto L6;
        Field r22 = this.field;
        if (r0 == false) goto L15;
        Object r82 = DataConversion.convert(r5, r22.getClass());     // Catch: Exception -> L7 IllegalArgumentException -> L9
        r22.set(r5, r82);     // Catch: Exception -> L7 IllegalArgumentException -> L9
        return r82;
    L15:
        r22.set(r5, r8);     // Catch: Exception -> L7 IllegalArgumentException -> L9
        return r8;
    L6:
        return r2.setValue(r5, r6, r7, r8);
    L9:
        e = move-exception;
        if (r0 == true) goto L22;
        this.coercionRequired = true;
        return setValue(r5, r6, r7, r8);
    L22:
        Make$Map$$ExternalSyntheticBUOutline0.m("unable to bind property", e);
        return null;
    L7:
        e = move-exception;
        Make$Map$$ExternalSyntheticBUOutline0.m("unable to access field", e);
        return null;
    }
}
